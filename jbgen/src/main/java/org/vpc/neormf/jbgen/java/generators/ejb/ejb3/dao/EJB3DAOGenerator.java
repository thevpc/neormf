/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpc.neormf.jbgen.java.generators.ejb.ejb3.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.util.*;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBRelation;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.JavaSourceCodeFieldsSwitcher;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.projects.J2eeTarget;

/**
 *
 * @author walid
 */
public class EJB3DAOGenerator extends JBGenDAOGenerator {

    public EJB3DAOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO) && entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0);
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DAO);
    }
    JavaClassSource theClass = null;

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DAO));
        theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setName(entityInfo.getDAOName());
        theClass.addImport("java.util.*");
        theClass.addImport("javax.persistence.PersistenceContext");
        theClass.addImport("javax.persistence.EntityManager");
        theClass.addImport(entityInfo.getDataPackage() + "." + entityInfo.getBeanName());
        theClass.addImport(entityInfo.getEntityPackage() + "." + entityInfo.getBeanName() + "Entity");
        theClass.addImport(entityInfo.getDataPackage() + "." + entityInfo.getBeanName() + "PropertyList");
        theClass.addImport(entityInfo.getDataPackage() + "." + entityInfo.getBeanName() + "Key");
        theClass.addImport(entityInfo.getDataPackage() + "." + entityInfo.getBeanName() + "OrderList");
        theClass.setPackage(entityInfo.getDAOPackage());
        theClass.addField(new JavaField("em",
                "EntityManager",
                null,
                "\n\t@PersistenceContext\n\tprivate",
                null));
        generateConstructor(entityInfo, theClass);
        generateCreateMethod(entityInfo, theClass);
        generateUpdateMethod(entityInfo, theClass);
        generateDeleteMethod(entityInfo, theClass);
        generateFindByKeyMethod(entityInfo, theClass);
        generateFindMethod(entityInfo, theClass);
        generateDto2EntityMethod(entityInfo, theClass);
        generateEntity2DtoMethod(entityInfo, theClass);


        DBColumn[] extraColumns = entityInfo.getColumns(false, false, true);
        for (int i = 0; i < extraColumns.length; i++) {
            theClass.addMethod(createMethodGetSpecificField(entityInfo, extraColumns[i]));
        }


        entityInfo.setGeneratedClass("DataAccessObject", theClass);
        try {
            if (theClass.rewrite(destFolder, getLog())) {
                getLog().info(" generating EJB3 DAO Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public void generateConstructor(final DAOInfo entityInfo, JavaClassSource theClass) {
        JavaMethod m = new JavaMethod(entityInfo.getDAOName(),
                "void",
                null,
                "public",
                null,
                "Constructor",
                "");
        declareMethod(m, entityInfo, theClass);
    }

    public void generateCreateMethod(final DAOInfo entityInfo, JavaClassSource theClass) {
        JavaMethod m = new JavaMethod("create",
                "void",
                new JavaParam[]{new JavaParam("dto", entityInfo.getDTOName(), null)},
                "public",
                null,
                "Create a new " + entityInfo.getDTOName() + "Entity",
                entityInfo.getDTOName() + "Entity entity = new " + entityInfo.getDTOName() + "Entity();\n" +
                "dto2entity(dto, entity);\n" +
                "em.persist(entity);");
        declareMethod(m, entityInfo, theClass);
    }

    public void generateDeleteMethod(final DAOInfo entityInfo, JavaClassSource theClass) {
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        String parameters = "";
        for (int i = 0; i < pkColumns.length; i++) {
            parameters = parameters + ".setParameter(\"" + pkColumns[i].getBeanFieldName() + "\",key." + JavaUtils.businessGetterName(pkColumns[i]) + "())";
        }
        JavaMethod m = new JavaMethod("delete",
                "void",
                new JavaParam[]{new JavaParam("key", entityInfo.getDTOName() + "Key", null)},
                "public",
                null,
                "Delete a " + entityInfo.getDTOName() + "Entity",
                entityInfo.getDTOName() + "Entity entity = (" + entityInfo.getBeanName() + "Entity) em.createNamedQuery(\"" + entityInfo.getBeanName() + "Entity.findByKeys\")" + parameters + ".getResultList().get(0);\n" +
                "if(entity!=null){\n\tem.remove(entity);\n}");
        declareMethod(m, entityInfo, theClass);
    }

    public void generateFindByKeyMethod(final DAOInfo entityInfo, JavaClassSource theClass) {
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        String parameters = "";
        for (int i = 0; i < pkColumns.length; i++) {
            parameters = parameters + ".setParameter(\"" + pkColumns[i].getBeanFieldName() + "\",key." + JavaUtils.businessGetterName(pkColumns[i]) + "())";
        }
        JavaMethod m = new JavaMethod("findByKey",
                entityInfo.getDTOName(),
                new JavaParam[]{new JavaParam("key", entityInfo.getDTOName() + "Key", null), new JavaParam("propertyList", entityInfo.getDTOName() + "PropertyList", null)},
                "public",
                null,
                "Find by primary keys for " + entityInfo.getDTOName() + "Entity",
                entityInfo.getDTOName() + "Entity entity = (" + entityInfo.getBeanName() + "Entity) em.createNamedQuery(\"" + entityInfo.getBeanName() + "Entity.findByKeys\")" + parameters + ".getResultList().get(0);\n" +
                entityInfo.getDTOName() + " dto = null;\n" +
                "entity2dto(entity, dto, propertyList);\n" +
                "return dto;");
        declareMethod(m, entityInfo, theClass);
    }

    public void generateUpdateMethod(final DAOInfo entityInfo, JavaClassSource theClass) {
        StringBuilder body = new StringBuilder();
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        String parameters = "";
        for (int i = 0; i < pkColumns.length; i++) {
            parameters = parameters + " \n\t\t\t\t\t\t\t\t.setParameter(\"" + pkColumns[i].getBeanFieldName() + "\",dto." + JavaUtils.businessGetterName(pkColumns[i]) + "())";
        }
        body.append(entityInfo.getDTOName()).append("Entity entity = (").append(entityInfo.getBeanName()).append("Entity) em.createNamedQuery(\"").append(entityInfo.getBeanName()).append("Entity.findByKeys\")").append(parameters).append("\n\t\t\t\t\t\t\t\t.getResultList()\n\t\t\t\t\t\t\t\t.get(0);\n");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            DBColumn[] columns = entityInfo.getColumns(true, true, true);
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    boolean ignoreColumn = false;
                    boolean found = false;
                    int indexFound = 0;
                    if (!columns[i].isFormulaImpl()) {
                        for (DBRelation r : columns[i].getTable().getImportRelations()) {
                            found = false;
                            indexFound = 0;
                            for (DBColumn colomn : r.getFkColumns()) {
                                if (colomn.getColumnName().equals(columns[i].getColumnName())) {
                                    found = true;
                                    break;
                                }
                                indexFound++;
                            }
                            if (found && indexFound > 0) {
                                ignoreColumn = true;
                            } else {
                                if (found) {
                                    String relativeBeanName = r.getPkTable().getDAOInfo().getBeanName();
                                    body.append("  if(dto.").append(JavaUtils.method(c, "contains")).append("()){\n").append("    entity.set").append(relativeBeanName).append("((").append(relativeBeanName).append("Entity)").append("em.createNamedQuery(\"").append(relativeBeanName).append("Entity.findBy").append(columns[i].getBeanFieldName()).append("\")").append(".setParameter(\"").append(columns[i].getBeanFieldName()).append("\",dto.").append(JavaUtils.businessGetterName(c)).append("()).getResultList().get(0));\n").append("  }\n");
                                    theClass.addImport(r.getPkTable().getDAOInfo().getEntityPackage() + "." + relativeBeanName + "Entity");
                                    break;
                                }
                            }
                        }
                        if (!found && !ignoreColumn) {
                            body.append("  if(dto.").append(JavaUtils.method(c, "contains")).append("()){\n").append("    entity.").append(JavaUtils.businessSetterName(c)).append("(dto.").append(JavaUtils.businessGetterName(c)).append("());\n").append("  }\n");
                        }
                    } else {
                        DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                        if (!isJoinTable(tableInfo.getDAOInfo().getPrimaryKeys())) {
                            body.append("  if(dto.").append(JavaUtils.method(tableInfo.getDAOInfo().getBeanName(), "contains")).append("()){\n");
                            body.append("   ").append(tableInfo.getDAOInfo().getBeanName()).append("Entity ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName() + "Entity = new " + tableInfo.getDAOInfo().getBeanName() + "Entity();\n"));
                            body.append("   ").append(tableInfo.getDAOInfo().getDAOName()).append(" ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getDAOName())).append(" = new ").append(tableInfo.getDAOInfo().getDAOName()).append("();\n");
                            body.append("   ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getDAOName())).append(".dto2entity(dto.").append(JavaUtils.businessGetterName(tableInfo.getDAOInfo().getBeanName(), null)).append("(), ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append("Entity);\n");
                            body.append("    entity.").append(JavaUtils.businessSetterName(tableInfo.getDAOInfo().getBeanName())).append("(").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append("Entity);\n").append("  }\n");
                            theClass.addImport(tableInfo.getDAOInfo().getEntityPackage() + "." + tableInfo.getDAOInfo().getBeanName() + "Entity");
                        } else {
                            body.append("  if(dto.").append(JavaUtils.method(c, "contains")).append("()){\n").append("    entity.").append(JavaUtils.businessSetterName(c)).append("(dto.").append(JavaUtils.businessGetterName(c)).append("());\n").append("  }\n");
                        }

                    }


                }
            }
        }
        body.append("em.merge(entity);");
        JavaMethod m = new JavaMethod("update",
                "void",
                new JavaParam[]{new JavaParam("dto", entityInfo.getDTOName(), null)},
                "public",
                null,
                "Update a " + entityInfo.getDTOName() + "Entity",
                body);
        declareMethod(m, entityInfo, theClass);
    }

    public void generateFindMethod(final DAOInfo entityInfo, JavaClassSource theClass) {

        JavaSourceCodeFieldsSwitcher l = new JavaSourceCodeFieldsSwitcher("order.iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {

            @Override
            public String getFieldNameCode(DBColumn dbColumn) {
                return "if(orderFirst){\n\torderFirst=false;\n}else{\n\tquery.append(\" , \");\n}\nquery.append(\"e." + dbColumn.getBeanFieldName() + "\");\n";
            }
        };
        JavaSourceCodeFieldsSwitcher s = new JavaSourceCodeFieldsSwitcher("prototype.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {

            @Override
            public String getFieldNameCode(DBColumn dbColumn) {
                return "if(firstCriteria){\n\tfirstCriteria=false;\n}else{\n\tquery.append(\" , \");\n}\nquery.append(\"e." + dbColumn.getBeanFieldName() + "=\"+prototype." + JavaUtils.businessGetterName(dbColumn) + "());\n";
            }
        };
        StringBuilder sb = new StringBuilder();
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("e.").append(columns[i].getBeanFieldName());
        }
        JavaMethod m = new JavaMethod("find",
                "Collection <" + entityInfo.getBeanName() + ">",
                new JavaParam[]{new JavaParam("propertyList", entityInfo.getDTOName() + "PropertyList", null), new JavaParam("prototype", entityInfo.getBeanName(), null), new JavaParam("order", entityInfo.getDTOName() + "OrderList", null)},
                "public",
                null,
                "",
                "Collection <" + entityInfo.getBeanName() + "> collection = null;\n" +
                "Collection <" + entityInfo.getBeanName() + "Entity> collection1;\n" +
                entityInfo.getBeanName() + " dto = new " + entityInfo.getBeanName() + "();\n" +
                "StringBuffer query = null;\n" +
                "query.append(\"SELECT e \");\n" +
                "query.append(\" FROM " + entityInfo.getBeanName() + "Entity e \");\n" +
                "if (prototype != null){\n" +
                "\tquery.append(\" WHERE \");\n" +
                "boolean firstCriteria=true;\n" +
                getWhereClause(entityInfo) +
                "}\n" +
                "if(order!=null){\n" +
                "boolean orderFirst=true;\nquery.append(\" ORDER BY \");\n" +
                l.getCode() +
                "}\n" +
                "query.append(\" asc \");\n" +
                "collection1 = em.createQuery(query.toString()).getResultList();\n" +
                "for(Iterator j=collection1.iterator();j.hasNext();){\n\t" +
                "entity2dto((" + entityInfo.getBeanName() + "Entity)j.next(),dto,propertyList);\n\t" +
                "collection.add(dto);\n}\n" +
                "return collection;\n");
        declareMethod(m, entityInfo, theClass);
    }

    private StringBuilder getWhereClause(final DAOInfo entityInfo) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("for(Iterator i=prototype.keySet().iterator();i.hasNext();){\n");
        buffer.append("\tString selectedFieldName=(String)i.next();\n");
        buffer.append("\tint selectedFieldId=selectedFieldName.hashCode();\n");
        buffer.append("\tswitch(selectedFieldId){\n");
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            if(columns[i].isFormulaImpl()){
                DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                if(!isJoinTable(tableInfo.getDAOInfo().getPrimaryKeys())){
                    buffer.append("\t\tcase ").append(columns[i].getBeanFieldName().hashCode()).append(":{  //field ").append(tableInfo.getDAOInfo().getBeanName()).append("\n");
                    buffer.append("\t\tif(firstCriteria){\n");
                    buffer.append("\t\t\tfirstCriteria=false;\n");
                    buffer.append("\t\t}else{\n");
                    buffer.append("\t\t\tquery.append(\" , \");\n");
                    buffer.append("\t\t}\n");
                    buffer.append("\t\tquery.append(\"e.").append(tableInfo.getDAOInfo().getBeanName()).append("=\"+prototype.").append(JavaUtils.businessGetterName(tableInfo.getDAOInfo().getBeanName(), null)).append("());\n");
                    buffer.append("\t\tbreak;\n");
                    buffer.append("\t}\n");
                }else{
                    buffer.append("\t\tcase ").append(columns[i].getBeanFieldName().hashCode()).append(":{  //field ").append(columns[i].getBeanFieldName()).append("\n");
                    buffer.append("\t\tif(firstCriteria){\n");
                    buffer.append("\t\t\tfirstCriteria=false;\n");
                    buffer.append("\t\t}else{\n");
                    buffer.append("\t\t\tquery.append(\" , \");\n");
                    buffer.append("\t\t}\n");
                    buffer.append("\t\tquery.append(\"e.").append(columns[i].getBeanFieldName()).append("=\"+prototype.").append(JavaUtils.businessGetterName(columns[i])).append("());\n");
                    buffer.append("\t\tbreak;\n");
                    buffer.append("\t}\n");
                }
            }else{
                buffer.append("\t\tcase ").append(columns[i].getBeanFieldName().hashCode()).append(":{  //field ").append(columns[i].getBeanFieldName()).append("\n");
                    buffer.append("\t\tif(firstCriteria){\n");
                    buffer.append("\t\t\tfirstCriteria=false;\n");
                    buffer.append("\t\t}else{\n");
                    buffer.append("\t\t\tquery.append(\" , \");\n");
                    buffer.append("\t\t}\n");
                buffer.append("\t\tquery.append(\"e.").append(columns[i].getBeanFieldName()).append("=\"+prototype.").append(JavaUtils.businessGetterName(columns[i])).append("());\n");
                    buffer.append("\t\tbreak;\n");
                    buffer.append("\t}\n");
            }
        }
        buffer.append("\t\tdefault :{\n");
        buffer.append("\t\t  // default\n");
        buffer.append("\t\t}\n");
        buffer.append("\t}\n");
        buffer.append("}\n");
        return buffer;
    }

    public void generateDto2EntityMethod(final DAOInfo entityInfo, JavaClassSource theClass) {
        StringBuilder body = new StringBuilder();
        body.append("for(Iterator i=src.keySet().iterator();i.hasNext();){\n");
        body.append("\tString selectedFieldName=(String)i.next();\n");
        body.append("\tint selectedFieldId=selectedFieldName.hashCode();\n");
        body.append("\tswitch(selectedFieldId){\n");
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            boolean ignoreColumn = false;
            boolean found = false;
            int indexFound = 0;
            if (!columns[i].isFormulaImpl()) {
                //if (!columns[i].isPk()) {
                    for (DBRelation r : columns[i].getTable().getImportRelations()) {
                        found = false;
                        indexFound = 0;
                        for (DBColumn c : r.getFkColumns()) {
                            if (c.getColumnName().equals(columns[i].getColumnName())) {
                                found = true;
                                break;
                            }
                            indexFound++;
                        }
                        if (found && indexFound > 0) {
                            ignoreColumn = true;
                        } else {
                            if (found) {
                                String relativeBeanName = r.getPkTable().getDAOInfo().getBeanName();
                                body.append("\t\tcase ").append(columns[i].getBeanFieldName().hashCode()).append(":{  //field ").append(columns[i].getBeanFieldName()).append("\n");
                                body.append("\t\t\tif(src.").append(JavaUtils.method(columns[i], "contains")).append("()){\n");
                                body.append("\t\t\t\tdest.set").append(relativeBeanName).append("((").append(relativeBeanName).append("Entity)").append("em.createNamedQuery(\"").append(relativeBeanName).append("Entity.findBy").append(columns[i].getBeanFieldName()).append("\")").append(".setParameter(\"").append(columns[i].getBeanFieldName()).append("\",src.").append(JavaUtils.businessGetterName(columns[i])).append("()).getResultList().get(0));\n");
                                body.append("\t\t\t}\n\t\t\tbreak;\n\t\t}\n");
                                theClass.addImport(r.getPkTable().getDAOInfo().getEntityPackage()+"."+relativeBeanName+"Entity");
                                break;
                            }
                        }
                    }
                //}
                if (!found && !ignoreColumn) {
                    body.append("\t\tcase ").append(columns[i].getBeanFieldName().hashCode()).append(":{  //field ").append(columns[i].getBeanFieldName()).append("\n");
                    body.append("\t\t\tif(src.").append(JavaUtils.method(columns[i], "contains")).append("()){\n");
                    body.append("\t\t\t\tdest.").append(JavaUtils.businessSetterName(columns[i])).append("(src.").append(JavaUtils.businessGetterName(columns[i])).append("());\n");
                    body.append("\t\t\t}\n\t\t\tbreak;\n\t\t}\n");
                }
            } else {
                DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                if (!isJoinTable(tableInfo.getDAOInfo().getPrimaryKeys())) {
                    body.append("\t\tcase ").append(columns[i].getBeanFieldName().hashCode()).append(":{  //field ").append(columns[i].getBeanFieldName()).append("\n");
                    body.append("\t\t\tif(src.").append(JavaUtils.method(tableInfo.getDAOInfo().getBeanName(), "contains")).append("()){\n");
                    body.append("\t\t\t\t").append(tableInfo.getDAOInfo().getBeanName()).append("Entity ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName() + "Entity = new " + tableInfo.getDAOInfo().getBeanName() + "Entity();\n"));
                    body.append("\t\t\t\t").append(tableInfo.getDAOInfo().getDAOName()).append(" ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getDAOName())).append(" = new ").append(tableInfo.getDAOInfo().getDAOName()).append("();\n");
                    body.append("\t\t\t\t").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getDAOName())).append(".dto2entity(src.").append(JavaUtils.businessGetterName(tableInfo.getDAOInfo().getBeanName(), null)).append("(), ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append("Entity);\n");
                    body.append("\t\t\t\tdest.").append(JavaUtils.businessSetterName(tableInfo.getDAOInfo().getBeanName())).append("(").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append("Entity);\n");
                    body.append("\t\t\t}\n\t\t\tbreak;\n\t\t}\n");
                } else {

                }
            }
        }
        body.append("\t\tdefault :{\n");
        body.append("\t\t  // default\n");
        body.append("\t\t}\n");
        body.append("\t}\n");
        body.append("}\n");
        JavaSourceCodeFieldsSwitcher s = new JavaSourceCodeFieldsSwitcher("src.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {

            @Override
            public String getFieldNameCode(DBColumn dbColumn) {
                return "if(src." + JavaUtils.method(dbColumn, "contains") + "()){\n" +
                        "  dest." + JavaUtils.businessSetterName(dbColumn) + "(src." + JavaUtils.businessGetterName(dbColumn) + "());\n" +
                        "}";
            }
        };
        JavaMethod m = new JavaMethod("dto2entity",
                "void",
                new JavaParam[]{
            new JavaParam("src", entityInfo.getDTOName(), null),
            new JavaParam("dest", entityInfo.getDTOName() + "Entity", null)
        },
                "public",
                null,
                "",
                body.toString());
        theClass.addMethod(m);
    }

    public void generateEntity2DtoMethod(final DAOInfo entityInfo, JavaClassSource theClass) {
        StringBuilder body = new StringBuilder();
        body.append("if(propertyList==null){\n\tpropertyList.addAllProperties();\n}\n");
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            boolean ignoreColumn = false;
            boolean found = false;
            int indexFound = 0;
            String PkColumn = null;
            if (columns[i].isPk()) {/*
                body.append("if(propertyList." + JavaUtils.method(columns[i].getBeanFieldName(), "contains") + "()){\n");
                body.append("\tdest." + JavaUtils.businessSetterName(columns[i]) + "(src." + JavaUtils.businessGetterName(columns[i]) + "());\n}\n");*/
            }
            if (columns[i].isFormulaImpl()) {
                DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                if (!isJoinTable(tableInfo.getDAOInfo().getPrimaryKeys())) {
                    body.append("if(propertyList.").append(JavaUtils.method(columns[i].getBeanFieldName(), "contains")).append("()){\n");
                    body.append("\t").append(tableInfo.getDAOInfo().getDTOName()).append(" ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append(" = new ").append(tableInfo.getDAOInfo().getBeanName()).append("();\n");
                    body.append("\t").append(tableInfo.getDAOInfo().getDAOName()).append(" ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getDAOName())).append(" = new ").append(tableInfo.getDAOInfo().getDAOName()).append("();\n");
                    body.append("\t").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getDAOName())).append(".entity2dto(src.").append(JavaUtils.businessGetterName(tableInfo.getDAOInfo().getBeanName(), null)).append("(), ").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append(", null);\n");
                    body.append("\tdest.").append(JavaUtils.businessSetterName(tableInfo.getDAOInfo().getBeanName())).append("(").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append(");\n}\n");
                    theClass.addImport(tableInfo.getDAOInfo().getDataPackage()+"."+tableInfo.getDAOInfo().getBeanName());
                } else {
//                    DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                    body.append("if(propertyList.").append(JavaUtils.method(columns[i].getBeanFieldName(), "contains")).append("()){\n");
                    body.append("\tdest.").append(JavaUtils.businessSetterName(columns[i])).append("(src.").append(JavaUtils.businessGetterName(columns[i])).append("());\n}\n");
                }
            } else {
                for (DBRelation r : columns[i].getTable().getImportRelations()) {
                    found = false;
                    indexFound =
                            0;
                    for (DBColumn c : r.getFkColumns()) {
                        if (c.getColumnName().equals(columns[i].getColumnName())) {
                            for (DBColumn d : r.getPkColumns()) {
                                PkColumn = d.getColumnName();
                                break;
                            }
                            found = true;
                            break;
                        }

                        indexFound++;
                    }

                    if (found && indexFound > 0) {
                        ignoreColumn = true;
                    } else {
                        if (found && !ignoreColumn) {
                            body.append("if(propertyList.").append(JavaUtils.method(columns[i].getBeanFieldName(), "contains")).append("()){\n");
                            body.append("\tdest.").append(JavaUtils.businessSetterName(columns[i])).append("(src.get").append(r.getPkTable().getDAOInfo().getBeanName()).append("().").append(JavaUtils.businessGetterName(PkColumn, null)).append("());\n}\n");
                            break;
                        }
                    }
                }
                if (!found && !ignoreColumn) {
                    body.append("if(propertyList.").append(JavaUtils.method(columns[i].getBeanFieldName(), "contains")).append("()){\n");
                    body.append("\tdest.").append(JavaUtils.businessSetterName(columns[i])).append("(src.").append(JavaUtils.businessGetterName(columns[i])).append("());\n}\n");
                }

            }
        }
        JavaMethod m = new JavaMethod("entity2dto",
                "void",
                new JavaParam[]{
            new JavaParam("src", entityInfo.getDTOName() + "Entity", null),
            new JavaParam("dest", entityInfo.getDTOName(), null),
            new JavaParam("propertyList", entityInfo.getDTOName() + "PropertyList", null)
        },
                "public",
                null,
                "",
                body);
        theClass.addMethod(m);
    }

    private JavaMethod createMethodGetSpecificField(DAOInfo entityInfo, DBColumn column) {
        String body = null;
        FieldFormulaImpl fieldFormulaImpl = column.getGetterImpl();
        switch (fieldFormulaImpl.getType()) {
            /*case code: {
            body = fieldFormulaImpl.getBody().getValue();
            break;
            }
            case sqlQuery:
            case sqlView: {
            body = getMethodGetSpecificFieldBodyForSQL_VIEW(entityInfo, column);
            break;
            }
            case sqlCall:
            case sqlFunction: {
            body = getMethodGetSpecificFieldBodyForSQL_SQL_CALL(entityInfo, column);
            break;
            }*/
            case sqlMasterDetail: {
                body = getMethodGetSpecificFieldBodyForRELATION(entityInfo, column);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unhandled Formula type " + fieldFormulaImpl.getTypeName());
            }

        }
        ArrayList ep = new ArrayList();
        if (column.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
            RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
            String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
            String rol = relationDesc.getDetailTable().getDAOInfo().getOrderListName();
            String rp = relationDesc.getDetailTable().getDAOInfo().getBeanName();
            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rp);
            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rol);
            ep.add(new JavaParam("propertyList", rpl, null));
            ep.add(new JavaParam("prototype", rp, null));
            ep.add(new JavaParam("order", rol, null));
        }

        ep.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        return new JavaMethod(JavaUtils.businessGetterName(column),
                column.getBusinessDataTypeName(),
                (JavaParam[]) ep.toArray(new JavaParam[ep.size()]),
                "public",
                null,
                null,
                body);

    }

    private String getMethodGetSpecificFieldBodyForRELATION(DAOInfo entityInfo, DBColumn column) {
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
        DBTableInfo detailTable = relationDesc.getDetailTable();
        StringBuilder body = new StringBuilder();
        detailTable.getDAOInfo().checkGenerateFilter(J2eeTarget.MODULE_DAO);
        theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDAOPackage() + "." + detailTable.getDAOInfo().getDAOName());
        body.append("  ").append(detailTable.getDAOInfo().getDAOName()).append(" details=new ").append(detailTable.getDAOInfo().getDAOName()).append("();\n");
        body.append("  return new ArrayList(details.find(");
        body.append("propertyList,prototype,order");
        body.append("));\n");
        return body.toString();
    }

    private boolean isJoinTable(DBColumn[] pkColumns) {
        int index = 0;
        if (pkColumns.length > 1) {
            DBTableInfo thisTableName = pkColumns[0].getTable();
            for (int i = 0; i < pkColumns.length; i++) {
                for (DBRelation r : thisTableName.getImportRelations()) {
                    DBTableInfo pkTable = r.getPkTable();
                    DBColumn[] dbc = pkTable.getPkColumns();
                    for (DBColumn column : r.getPkColumns()) {
                        for (int j = 0; j < dbc.length; j++) {
                            if (column.getColumnName().equals(dbc[j].getColumnName())) {
                                index++;
                            }
                        }
                    }
                }
                break;
            }
        }
        if (index >=2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "EJB3 Generator";
    }

    private static JavaMethod declareMethod(JavaMethod m, DAOInfo entityInfo, JavaClassSource theClass) {
        JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
        JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }
}

package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 17:11:33
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
class EjbEntityCMPBeanGenerator extends JBGenDAOGenerator {

    public EjbEntityCMPBeanGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        if(entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0")<0){
            return false;
        }
        if (
                !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                        && !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
                    
                ) {
            return false;
        }
        if (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                        || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")
                ) {
            throw new IllegalArgumentException("Could not create both of CMP and BMP EJB for " + entityInfo.getBeanName() + " (table " + entityInfo.getEntityName() + ").\n check your module filters :\n generate.ejb-cmp-remote ; generate.ejb-cmp-remote ; generate.ejb-bmp-remote ; generate.ejb-cmp-local ; generate.ejb-bmp-local");
        }
        if(entityInfo.getGeneratedClass("PrimaryKey")==null){
            return false;
        }
        return true;
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-cmp-remote");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-bmp-remote");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-cmp-local");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-bmp-local");
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        JBGenProjectInfo moduleCodeStyle = entityInfo.getProjectInfo();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public abstract");
        theClass.setName(entityInfo.getDOBeanName());
        theClass.setInterfaces(new String[]{"EntityBean"});

        theClass.addAllImports(new String[]{
                "java.util.Iterator",
                "java.util.Collection",
                "java.util.ArrayList",
                "javax.ejb.EntityBean",
                "javax.ejb.EntityContext",
                "javax.ejb.EJBException",
                "javax.ejb.RemoveException",
                "java.rmi.RemoteException",
                "javax.ejb.FinderException",
                "javax.ejb.CreateException",
                entityInfo.getDataPackage() + ".*"
        });

        theClass.setPackage(entityInfo.getEntityPackage());


        theClass.addField(new JavaField("entityContext", "EntityContext", null, "private", null));
        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        for (int i = 0; i < columns.length; i++) {
            DataTypeConverterFactory factory = columns[i].getSqlConverterFactory();
            if (factory != null) {
                theClass.addField(new JavaField(columns[i].getConverterFieldName(),
                        "org.vpc.neormf.commons.types.converters.DataTypeConverter", null, "final private static", factory.getConverterExpression()));
            }
        }

        theClass.addMethod(new JavaMethod("ejbCreate", entityInfo.getDataKeyName(), new JavaParam[]{
                new JavaParam("data", entityInfo.getDTOName(), null)
        }, "public",
                new String[]{"CreateException"}, "EJB Constructor",
                "update(data);\nreturn data.get" + entityInfo.getDataKeyName() + "();"));

        theClass.addMethod(new JavaMethod("ejbPostCreate", "void", new JavaParam[]{
                new JavaParam("data", entityInfo.getDTOName(), null)
        }, "public",
                new String[]{"CreateException"}, "EJB Constructor",
                ""));


        Map map = new Hashtable();
        for (int i = 0; i < columns.length; i++) {
            JavaField field = new JavaField(columns[i].getBeanFieldName(),
                    columns[i].getSqlDataType().toJavaType().getName(),
                    null,
                    "private",
                    null);
            Object o = map.get(new Integer(field.getName().hashCode()));
            if (o == null) {
                map.put(new Integer(field.getName().hashCode()), columns[i]);
            } else if (o instanceof DBColumn) {
                Vector v = new Vector();
                v.add(o);
                v.add(columns[i]);
                map.put(new Integer(field.getName().hashCode()), v);
            } else if (o instanceof Vector) {
                Vector v = (Vector) o;
                v.add(columns[i]);
                map.put(new Integer(field.getName().hashCode()), v);
            }
            theClass.addMethod(new JavaMethod(JavaUtils.sqlGetterName(columns[i]), field.getType(), null, "public abstract",
                    null, "getter for " + field.getName()));
            theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(columns[i]), "void", new JavaParam[]{new JavaParam("value", field.getType(), null)}, "public abstract",
                    null, "setter for " + field.getName()));
        }

        StringBuilder methodCorps = new StringBuilder();
        methodCorps.append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        methodCorps.append("for(Iterator i=propertyList.iterator();i.hasNext();){\n");
        methodCorps.append("  String selectedFieldName=(String)i.next();\n");
        methodCorps.append("  int selectedFieldId=selectedFieldName.hashCode();\n");
        methodCorps.append("  switch(selectedFieldId){\n");
        for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            methodCorps.append("    case ").append(((Integer) entry.getKey()).intValue()).append(":{\n");
            if (entry.getValue() instanceof DBColumn) {
                DBColumn c = (DBColumn) entry.getValue();
                methodCorps.append("       //field ").append(c.getBeanFieldVar()).append("\n");
                DataTypeConverterFactory factory = c.getSqlConverterFactory();
                if (factory == null) {
                    methodCorps.append("      data.").append(JavaUtils.businessSetterName(c)).append("(").append(JavaUtils.sqlGetterName(c)).append("());\n");
                } else {
                    methodCorps.append("      data.").append(JavaUtils.businessSetterName(c)).append("(").append(JavaUtils.objectToPrimitive(c.getConverterFieldName()
                            + ".sqlToBusiness(" +
                            JavaUtils.primitiveToObject(JavaUtils.sqlGetterName(c) + "()", factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName()) + ")"
                            , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(c.getSqlDataType()).toJavaType().getName())).append(");\n");
                    //businessToSQL
                    //sqlToBusiness
                }
            } else {
                Vector v = (Vector) entry.getValue();
                for (int j = 0; j < v.size(); j++) {
                    DBColumn c = (DBColumn) v.get(j);
                    String ifEleif = (j == 0) ? "if" : "}else if";
                    methodCorps.append("         ").append(ifEleif).append("(\"").append(c.getBeanFieldVar()).append("\".equals(selectedFieldName)){\n");
                    methodCorps.append("      //field ").append(c.getBeanFieldVar()).append("\n");
                    methodCorps.append("      data.").append(JavaUtils.businessSetterName(c)).append("(").append(JavaUtils.sqlGetterName(c)).append("());\n");
                }
                methodCorps.append("         }else{\n");
                methodCorps.append("            throw new IllegalArgumentException(\"unknown field \"+selectedFieldName);");
                methodCorps.append("         }");
            }
            methodCorps.append("      break;\n");
            methodCorps.append("    }\n");

        }
        methodCorps.append("    default:{\n");
        methodCorps.append("      throw new IllegalArgumentException(\"unknown field \"+selectedFieldName);\n");
        methodCorps.append("    }\n");
        methodCorps.append("  }\n");
        methodCorps.append("}\n");
        methodCorps.append("return data;\n");
        theClass.addMethod(new JavaMethod("getData", entityInfo.getDTOName(), new JavaParam[]{
                new JavaParam("propertyList", entityInfo.getPropertyListName(), null)
        }, "public",
                null, "default filtred fields requests",
                methodCorps));

//------------------------------------------------------------
        methodCorps = new StringBuilder();
        methodCorps.append("for(Iterator i=data.keySet().iterator();i.hasNext();){\n");
        methodCorps.append("  String selectedFieldName=(String)i.next();\n");
        methodCorps.append("  int selectedFieldId=selectedFieldName.hashCode();\n");
        methodCorps.append("  switch(selectedFieldId){\n");
        for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            methodCorps.append("    case ").append(((Integer) entry.getKey()).intValue()).append(":{\n");
            if (entry.getValue() instanceof DBColumn) {
                DBColumn c = (DBColumn) entry.getValue();
                methodCorps.append("         //field ").append(c.getBeanFieldVar()).append("\n");
                DataTypeConverterFactory factory = c.getSqlConverterFactory();
                if (factory == null) {
                    methodCorps.append("         ").append(JavaUtils.businessSetterName(c)).append("(data.").append(JavaUtils.businessGetterName(c)).append("());\n");
                } else {
                    methodCorps.append("      ").append(JavaUtils.businessSetterName(c)).append("(").append(JavaUtils.objectToPrimitive(c.getConverterFieldName()
                            + ".businessToSQL(" +
                            JavaUtils.primitiveToObject("data." + (JavaUtils.businessGetterName(c)) + "()", factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(c.getSqlDataType()).toJavaType().getName()) + ")"
                            , factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName())).append(");\n");
                    //businessToSQL
                    //sqlToBusiness
                }

            } else {
                Vector v = (Vector) entry.getValue();
                for (int j = 0; j < v.size(); j++) {
                    DBColumn c = (DBColumn) v.get(j);
                    String ifEleif = (j == 0) ? "if" : "}else if";
                    methodCorps.append("         ").append(ifEleif).append("(\"").append(c.getBeanFieldVar()).append("\".equals(selectedFieldName)){\n");
                    methodCorps.append("         //field ").append(c.getBeanFieldVar()).append("\n");
                    methodCorps.append("         ").append(JavaUtils.businessSetterName(c)).append("(data.").append(JavaUtils.businessGetterName(c)).append("());\n");
                }
                methodCorps.append("         }else{\n");
                methodCorps.append("            throw new IllegalArgumentException(\"unknown field \"+selectedFieldName);");
                methodCorps.append("         }");
            }
            methodCorps.append("    break;\n");
            methodCorps.append("    }\n");

        }
        methodCorps.append("    default:{\n");
        methodCorps.append("      throw new IllegalArgumentException(\"unknown field \"+selectedFieldName);\n");
        methodCorps.append("    }\n");
        methodCorps.append("  }\n");
        methodCorps.append("}\n");
        theClass.addMethod(new JavaMethod("update", "void", new JavaParam[]{
                new JavaParam("data", entityInfo.getDTOName(), null)
        }, "public", null, "default updates",
                methodCorps.toString()));


        theClass.addMethod(new JavaMethod("setEntityContext", "void", new JavaParam[]{
                new JavaParam("entityContext", "EntityContext", null)
        }, "public", new String[]{"EJBException"}, null, "this.entityContext = entityContext;"));

        theClass.addMethod(createCollectionFinder("all", null, entityInfo));
        theClass.addMethod(createSingletonFinder("byPrimaryKey", new JavaParam[]{
                new JavaParam("key", entityInfo.getDataKeyName(), null)
        }, entityInfo));
        theClass.addMethod(new JavaMethod("_transform_", "Collection", new JavaParam[]{
                new JavaParam("src", "Collection", null),
                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
        }, "private",
                new String[]{"RemoteException"}, null,

                "ArrayList list=new ArrayList();\n" +
                        "for(Iterator i=src.iterator();i.hasNext();){\n" +
                        "  " + entityInfo.getEntityRemoteName() + " e=(" + entityInfo.getEntityRemoteName() + ")i.next();\n" +
                        "  list.add(e.getData(propertyList));\n" +
                        "}\n" +
                        "return list;"));

        theClass.addMethod(new JavaMethod("unsetEntityContext", "void", null, "public", null, null, "this.entityContext = null;"));
        theClass.addMethod(new JavaMethod("ejbRemove", "void", null, "public", new String[]{"RemoveException", "EJBException"}, null, ""));
        theClass.addMethod(new JavaMethod("ejbActivate", "void", null, "public", new String[]{"EJBException"}, null, ""));
        theClass.addMethod(new JavaMethod("ejbPassivate", "void", null, "public", new String[]{"EJBException"}, null, ""));
        theClass.addMethod(new JavaMethod("ejbLoad", "void", null, "public", new String[]{"EJBException"}, null, ""));
        theClass.addMethod(new JavaMethod("ejbStore", "void", null, "public", new String[]{"EJBException"}, null, ""));
        entityInfo.setGeneratedClass("CMPEntityBean", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating CMP Bean Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }

    }

    private JavaMethod createSingletonFinder(String name, JavaParam[] params, DAOInfo entityInfo) {
        Vector vparams = new Vector();
        StringBuilder p = new StringBuilder();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                vparams.add(params[i]);
                if (i > 0) {
                    p.append(",");
                }
                p.append(params[i].getName());
            }
        }
        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));


        return new JavaMethod("ejbHomeGet" + JavaUtils.toJavaIdentifier(name, true), entityInfo.getDTOName(), (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
                new String[]{"RemoteException", "FinderException"}, null,

                entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
                        entityInfo.getEntityRemoteName() + " src=home.find" + JavaUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
                        "return src.getData(propertyList);");
    }

    private JavaMethod createCollectionFinder(String name, JavaParam[] params, DAOInfo entityInfo) {
        Vector vparams = new Vector();
        StringBuilder p = new StringBuilder();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                vparams.add(params[i]);
                if (i > 0) {
                    p.append(",");
                }
                p.append(params[i].getName());
            }
        }
        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));


        return new JavaMethod("ejbHomeGet" + JavaUtils.toJavaIdentifier(name, true), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
                new String[]{"RemoteException", "FinderException"}, null,

                entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
                        "Collection src=home.find" + JavaUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
                        "return _transform_(src,propertyList);");
    }

    public String toString() {
        return "Entity Bean CMP Generator";
    }

}

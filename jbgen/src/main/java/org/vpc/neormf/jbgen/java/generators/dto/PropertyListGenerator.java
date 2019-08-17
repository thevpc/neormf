package org.vpc.neormf.jbgen.java.generators.dto;

import org.vpc.neormf.commons.beans.Criteria;
import org.vpc.neormf.commons.beans.OrderList;
import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.FieldFormulaImpl;
import org.vpc.neormf.jbgen.util.FieldFormulaType;
import org.vpc.neormf.jbgen.util.RelationDesc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

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
public class PropertyListGenerator extends JBGenDAOGenerator {


    public PropertyListGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
//                        || entityInfo.doGenerateTables("data")
                        /*|| entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                        || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                        || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
                        || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")
                        || entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)*/
        );
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.addImport("org.vpc.neormf.commons.beans.DTOMetaData");
        theClass.setName(entityInfo.getPropertyListName());
        theClass.setSuperClass("org.vpc.neormf.commons.beans.PropertyList");
        theClass.setInterfaces(null);
        theClass.setPackage(entityInfo.getDataPackage());

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "public", null, "Constructor",
                ""));

        DBColumn[] columns = entityInfo.getColumns(true, true, true);

        for (DBColumn column : columns) {
            String fieldName = entityInfo.getDTOName() + "." + column.getBeanFieldConstant();
//            JavaField field = new JavaField(column.getBeanFieldConstant(),
//                    "String",
//                    null,
//                    "public static final",
//                    "\"" + columns[i].getBeanFieldName() + "\"");
//            theClass.addField(field);
            FieldFormulaImpl getterImpl = column.getGetterImpl();
            if (getterImpl.getType() == FieldFormulaType.sqlMasterDetail || getterImpl.getType() == FieldFormulaType.sqlDetailMaster) {
                RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
                String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getFullPropertyListName());
                theClass.addImport(Criteria.class.getName());
                theClass.addImport(OrderList.class.getName());
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "add"), theClass.getName(), new JavaParam[]{
                        new JavaParam("list", rpl, null),
                        new JavaParam("criteria", "Criteria", null),
                        new JavaParam("order", "OrderList", null)
                }, "public", null, null,
                        "super.addProperty(" + fieldName + ",new Object[]{list,criteria,order});\n" +
                                "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "remove"), theClass.getName(), null, "public", null, null,
                        "super.removeProperty(" + fieldName + ");\n" +
                                "return this;"));
//                theClass.addMethod(new JavaMethod(column.method("insert"), theClass.getName(), new JavaParam[]{
//                    new JavaParam("list",rpl,null),
//                    new JavaParam("index", "int", null)
//                }, "public", null, null,
//                        "super.insertProperty(" + field.getName() + ",list,index);\n" +
//                        "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "contains"), "boolean", null, "public", null, null,
                        "return super.containsProperty(" + fieldName + ");"));
            } else {
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "add"), theClass.getName(), null, "public", null, null,
                        "super.addProperty(" + fieldName + ");\n" +
                                "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "remove"), theClass.getName(), null, "public", null, null,
                        "super.removeProperty(" + fieldName + ");\n" +
                                "return this;"));
//                theClass.addMethod(new JavaMethod(column.method("insert"), theClass.getName(), new JavaParam[]{
//                    new JavaParam("index", "int", null)
//                }, "public", null, null,
//                        "super.insertProperty(" + field.getName() + ",index);\n" +
//                        "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "contains"), "boolean", null, "public", null, null,
                        "return super.containsProperty(" + fieldName + ");"));
            }
        }
        theClass.addMethod(new JavaMethod("addAllProperties", "void", null, "public", null, null,
                "super.addAllProperties("+entityInfo.getDTOName()+"._ALL_PROPERTIES);\n"));

        theClass.addMethod(new JavaMethod("metadata", "DTOMetaData", null, "public", null, null,
                "return " + entityInfo.getDTOName()+ "._METADATA;"));

        entityInfo.setGeneratedClass("PropertyList", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating PropertyList Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Property List Generator";
    }

}

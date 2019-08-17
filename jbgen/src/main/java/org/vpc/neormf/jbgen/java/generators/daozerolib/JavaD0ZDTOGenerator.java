package org.vpc.neormf.jbgen.java.generators.daozerolib;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
class JavaD0ZDTOGenerator extends JBGenDAOGenerator {


    public JavaD0ZDTOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (
//                entityInfo.doGenerateTables("table")
//                        &&
                        entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
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
        theClass.setName(entityInfo.getDTOName());
//        theClass.setSuperClass("DataTransfertObject");
        theClass.setInterfaces(new String[]{"Cloneable"});
        theClass.addImport("java.util.*");

        theClass.setPackage(entityInfo.getDataPackage());


        DBColumn[] columns = entityInfo.getColumns(true, true, true);
//        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();

//        EntityInfo.OrdreInfo[] order = entityInfo.getOrder();

        theClass.addField(new JavaField("dataMap", "HashMap", null, "private", null));

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "public", null, "Constructor",
                "dataMap=new HashMap();"));
        theClass.addMethod(new JavaMethod("getProperty", "Object", new JavaParam[]{new JavaParam("name", "String", null)}, "public",
                null, "generic getter",
                "return dataMap.get(name);"));
        theClass.addMethod(new JavaMethod("setProperty", "void", new JavaParam[]{new JavaParam("name", "String", null),new JavaParam("value", "Object", null)}, "public",
                null, "generic getter",
                "dataMap.put(name,value);"));

        for (int i = 0; i < columns.length; i++) {
            JavaField field = new JavaField(columns[i].getBeanFieldName(),
                    columns[i].getBusinessDataTypeName(),
                    null,
                    "private",
                    null);
            String cst = columns[i].getFullBeanFieldConstant();
//                        theClass.addField(field);
            theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(columns[i]), field.getType(), null, "public",
                    null, "getter for " + field.getName(),
                    "return " + JavaUtils.objectToPrimitive("dataMap.get(" + cst + ")", field.getType()) + ";"));
            theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(columns[i]), theClass.getName(), new JavaParam[]{
                    new JavaParam("value", field.getType(), null)
            }, "public",
                    null, "setter for " + field.getName(),
                    "dataMap.put(" + cst + "," + JavaUtils.primitiveToObject("value", field.getType()) + ");\n" +
                            "return this;"));
            theClass.addMethod(new JavaMethod("contains" + JBGenUtils.capitalize(field.getName()), "boolean", null, "public", null, "true if record contains the field " + field.getName(),
                    "return dataMap.containsKey(" + cst + ");"));
            theClass.addMethod(new JavaMethod("unset" + JBGenUtils.capitalize(field.getName()), theClass.getName() , null, "public", null, "remove the field " + field.getName(),
                    "dataMap.remove(" + cst + ");\n" +
                            "return this;"));
        }


        if (entityInfo.getPrimaryKeys().length > 0) {
            theClass.addMethod(createMethodSpecificGetDataKey(entityInfo));
        }
        theClass.addMethod(new JavaMethod("size", "int", null, "public", null, null, "return dataMap.size();"));
        theClass.addMethod(new JavaMethod("propertiesSet", "Set", null, "public", null, null, "return dataMap.keySet();"));

        entityInfo.setGeneratedClass("DataTransfertObject", theClass);
        ArrayList arraySet = (ArrayList) entityInfo.getProjectInfo().getUserProperties().get("generatedClasses.DataTransfertObject");
        if (arraySet == null) {
            arraySet = new ArrayList();
            entityInfo.getProjectInfo().getUserProperties().put("generatedClasses.DataTransfertObject", arraySet);
        }
        arraySet.add(theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating DataTransfertObject Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + "...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }


    public JavaMethod createMethodSpecificGetDataKey(DAOInfo entityInfo) {
        StringBuilder getDataKeyBuffer = new StringBuilder();
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        for (int i = 0; i < pkColumns.length; i++) {
            String cst = pkColumns[i].getFullBeanFieldConstant();
            getDataKeyBuffer.append("Object k").append(i).append("=dataMap.get(").append(cst).append(");\n");
            getDataKeyBuffer.append("if(k").append(i).append("==null){\n");
            getDataKeyBuffer.append("  return null;\n");
            getDataKeyBuffer.append("}\n");
        }
        getDataKeyBuffer.append("return new ").append(entityInfo.getDataKeyName()).append("(");
        for (int i = 0; i < pkColumns.length; i++) {
            if (i > 0) {
                getDataKeyBuffer.append(",");
            }
            getDataKeyBuffer.append(JavaUtils.objectToPrimitive("k" + i,
                    pkColumns[i].getBusinessDataTypeName()
            ));
        }
        getDataKeyBuffer.append(");");
        return (new JavaMethod("get" + entityInfo.getDataKeyName(), entityInfo.getDataKeyName(), null, "public",
                null, null, getDataKeyBuffer));
    }

    public String toString() {
        return "DataTransfertObject Generator";
    }

}

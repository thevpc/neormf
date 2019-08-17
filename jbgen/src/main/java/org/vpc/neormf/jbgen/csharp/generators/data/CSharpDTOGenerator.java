/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.csharp.generators.data;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.*;
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
class CSharpDTOGenerator extends JBGenDAOGenerator {

    public CSharpDTOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
        );
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        CSharpClassSource theClass = new CSharpClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setName(entityInfo.getDTOName());
        theClass.setSuperClass("DataTransfertObject");
        theClass.addImport("System");
        theClass.addImport("System.Collections");
        theClass.addImport("org.vpc.neormf.commons.beans");
        theClass.addImport("org.vpc.neormf.commons.util");
        theClass.addImport("org.vpc.neormf.commons.types");
        theClass.addImport("org.vpc.neormf.commons.types.converters");

        theClass.setPackage(entityInfo.getDataPackage());


        StringBuilder infoContent = new StringBuilder();
        infoContent.append(" new DTOMetaData(\n")
                .append("// Bean Name\n")
                .append("\"").append(entityInfo.getBeanName()).append("\"\n")
                .append("// Bean Fields(name,title,column,type,sqlType,sqlConverter)...\n")
                .append(",new DTOFieldMetaData[]{\n");
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        for (int i = 0; i < columns.length; i++) {
            String businessDataTypeCode = CSharpUtils.getImportedCode(columns[i].getBusinessDataType());
            String sqlDataTypeCode = CSharpUtils.getImportedCode(columns[i].getSqlDataType());
            if (businessDataTypeCode.equals(sqlDataTypeCode)) {
                businessDataTypeCode = "null";
            }
            infoContent
                    .append("  new DTOFieldMetaData(\"").append(columns[i].getBeanFieldName()).append("\", ")
                    .append("\"").append(columns[i].getTitle()).append("\", ")
                    .append("\"").append(columns[i].getColumnName()).append("\", ")
                    .append(businessDataTypeCode).append(",")
                    .append(sqlDataTypeCode).append(",")
                    .append((columns[i].getSqlConverterFactory() == null ? "null" : columns[i].getSqlConverterFactory().getConverterExpression()))
                    .append(")");
            if ((i + 1) < columns.length) {
                infoContent.append(",");
            }
            infoContent.append("\n");
        }
        infoContent.append("},\n");

        infoContent.append("// Primary fields\n");
        infoContent.append("new String[]{");
        for (int i = 0; i < pkColumns.length; i++) {
            infoContent.append("\"").append(pkColumns[i].getBeanFieldName()).append("\"");
            if ((i + 1) < pkColumns.length) {
                infoContent.append(",");
            }
        }
        infoContent.append("},\n");


        infoContent
                .append("// Title Field Name\n")
                .append(entityInfo.getTitleFieldName() == null ? "null" : ('"' + entityInfo.getTitleFieldName() + '"')).append(",\n")
                .append("// DataTransfertObject Class Name\n")
                .append("typeof(").append(entityInfo.getFullDTOName()).append("),\n")
                .append("// DataKey Class Name\n")
                .append(entityInfo.getPrimaryKeys().length > 0 ?
                        ("typeof(" + entityInfo.getFullDataKeyName() + ')') : "null").append(",\n")
                .append("// PropertyList Class Name\n")
                .append("typeof(").append(entityInfo.getFullPropertyListName()).append(')').append(",\n");
        DAOInfo.OrdreInfo[] order = entityInfo.getOrder();
        infoContent.append("// Default Order by fields\n");
        if (order == null) {
            infoContent.append("null,null,\n");
        } else {
            infoContent.append("new String[]{");
            for (int i = 0; i < order.length; i++) {
                if (i > 0) {
                    infoContent.append("\n,");
                }
                infoContent.append("\"").append(order[i].fieldName).append("\"");
            }
            infoContent.append("},new bool[]{");
            for (int i = 0; i < order.length; i++) {
                if (i > 0) {
                    infoContent.append("\n,");
                }
                infoContent.append(order[i].isAsc);
            }
            infoContent.append("},\n");
        }
        infoContent.append("// Extra Properties\n");
        infoContent.append("(Hashtable)Maps.fill(new Hashtable(),new Object[]{\n");
        infoContent.append("  \"BeanName\",\n");
        infoContent.append("  \"DAOType\",\n");
//        infoContent.append("  \"EntityHomeClassName\",\n");
//        infoContent.append("  \"EntityRemoteClassName\",\n");
//        infoContent.append("  \"BusinessDelegate\"\n");
        infoContent.append("},new Object[]{\n");
        infoContent.append("  \"").append(entityInfo.getBeanName()).append("\",\n");
        infoContent.append("   typeof(").append(entityInfo.getFullDAOName()).append("),\n");
//        infoContent.append("  \"").append(entityInfo.getFullEntityHomeName()).append("\",\n");
//        infoContent.append("  \"").append(entityInfo.getFullEntityRemoteName()).append("\",\n");
//        infoContent.append("  \"").append(entityInfo.getSessionInfo().getFullConnectorName()).append("\"\n");
        infoContent.append("}\n")
                .append(")")
                .append(")\n");

        theClass.addField(new CSharpField("INFO", "DTOMetaData", null, "public static ", infoContent.toString()));

        theClass.addMethod(new CSharpMethod(theClass.getName(), null, null, "public", null, "Constructor",
                ""));
        for (int i = 0; i < columns.length; i++) {
            CSharpField field = new CSharpField(columns[i].getBeanFieldName(),
                    CSharpUtils.getCSharpClassName(columns[i].getBusinessDataType().toJavaType()),
                    null,
                    "private",
                    null);
            String cst = columns[i].getFullBeanFieldConstant();
//                        theClass.addField(field);
            theClass.addAttribute(new CSharpAttribute(
                    CSharpUtils.businessAttribute(columns[i]), 
                    field.getType(),
                    "public",
                    "attribute for " + field.getName(),
                    "  return " + CSharpUtils.objectToPrimitive("base.GetProperty(" + cst + ")", field.getType()) + ";",
                    "  base.SetProperty(" + cst + "," + CSharpUtils.primitiveToObject("value", field.getType()) + ");"
            ));
            theClass.addMethod(new CSharpMethod("Contains" + JBGenUtils.capitalize(field.getName()), "bool", null, "public", null, "true if record contains the field " + field.getName(),
                    "return base.ContainsProperty(" + cst + ");"));
            theClass.addMethod(new CSharpMethod("Unset" + JBGenUtils.capitalize(field.getName()), "void", null, "public", null, "remove the field " + field.getName(),
                    "base.UnsetProperty(" + cst + ");"));
        }

//        StringBuffer getDataKeyBuffer=new StringBuffer();
//        for(int i=0;i<pkColumns.length;i++){
//            String cst=entityInfo.getPropertyListName()+"."+pkColumns[i].beanFieldConstant;
//            getDataKeyBuffer.append("Object k"+i+"=super.getProperty("+cst+");\n");
//            getDataKeyBuffer.append("if(k"+i+"==null){\n");
//            getDataKeyBuffer.append("  return null;\n");
//            getDataKeyBuffer.append("}\n");
//        }
//        getDataKeyBuffer.append("return new "+entityInfo.getDataKeyName()+"(");
//        for(int i=0;i<pkColumns.length;i++){
//            if(i>0){
//                getDataKeyBuffer.append(",");
//            }
//            getDataKeyBuffer.append(Utils.objectToPrimitive("k"+i,
//                    pkColumns[i].businessDataType.toJavaType().getName()
//            ));
//        }
//        getDataKeyBuffer.append(");");
//        theClass.addMethod(new JavaMethod("public", entityInfo.getDataKeyName(),
//                "get"+entityInfo.getDataKeyName(), null, null, null,getDataKeyBuffer));

        if (entityInfo.getPrimaryKeys().length > 0) {
            theClass.addMethod(createMethodSpecificGetDataKey(entityInfo));
            theClass.addMethod(new CSharpMethod("GetDataKey", "org.vpc.neormf.commons.beans.DataKey", null, "public override", null, null,
                    "return Get" + entityInfo.getDataKeyName() + "();"));
        }

        theClass.addMethod(createMethodInfo(entityInfo));
//        entityInfo.setGeneratedClass("DataTransfertObject", theClass);
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
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    protected CSharpMethod createMethodInfo(DAOInfo entityInfo) {
        return new CSharpMethod("Info", "DTOMetaData", null, "public override", null, null, "return INFO;");
    }

    public CSharpMethod createMethodSpecificGetDataKey(DAOInfo entityInfo) {
        StringBuilder getDataKeyBuffer = new StringBuilder();
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        for (int i = 0; i < pkColumns.length; i++) {
            String cst = pkColumns[i].getFullBeanFieldConstant();
            getDataKeyBuffer.append("Object k").append(i).append("=base.GetProperty(").append(cst).append(");\n");
            getDataKeyBuffer.append("if(k").append(i).append("==null){\n");
            getDataKeyBuffer.append("  return null;\n");
            getDataKeyBuffer.append("}\n");
        }
        getDataKeyBuffer.append("return new ").append(entityInfo.getDataKeyName()).append("(");
        for (int i = 0; i < pkColumns.length; i++) {
            if (i > 0) {
                getDataKeyBuffer.append(",");
            }
            getDataKeyBuffer.append(CSharpUtils.objectToPrimitive("k" + i,(pkColumns[i].getBusinessDataType().toJavaType())));
        }
        getDataKeyBuffer.append(");");
        return (new CSharpMethod("Get" + entityInfo.getDataKeyName(), entityInfo.getDataKeyName(), null, "public",
                null, null, getDataKeyBuffer));
    }

    public String toString() {
        return "CSharp DataTransfertObject Generator";
    }

}

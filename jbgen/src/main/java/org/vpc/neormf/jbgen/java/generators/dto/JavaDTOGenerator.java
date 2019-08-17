/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.dto;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.vpc.neormf.commons.beans.RelationInfo.Role;
import org.vpc.neormf.jbgen.dbsupport.DBRelation;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;

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
class JavaDTOGenerator extends JBGenDAOGenerator {

    public JavaDTOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO) //                || entityInfo.doGenerateTables("data")
                /*|| entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")*/);
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    JavaClassSource theClass;
    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setName(entityInfo.getDTOName());
        theClass.setSuperClass("DataTransfertObject");
        theClass.setInterfaces(new String[]{"Cloneable"});
        theClass.addImport("java.util.*");
        theClass.addImport("org.vpc.neormf.commons.beans.*");

        theClass.setPackage(entityInfo.getDataPackage());
        DBColumn[] columns = entityInfo.getColumns(true, true, true);

        for (int i = 0; i < columns.length; i++) {
            DBColumn column = columns[i];
            JavaField field = new JavaField(column.getBeanFieldConstant(),
                    "String",
                    null,
                    "public static final",
                    "\"" + columns[i].getBeanFieldName() + "\"");
            theClass.addField(field);
        }
        StringBuilder sbfdefaultVal = new StringBuilder("{");
        for (int i = 0; i < theClass.getFields().size(); i++) {
            if (i > 0) {
                sbfdefaultVal.append(",");
            }
            sbfdefaultVal.append(theClass.getField(i).getName());
        }
        sbfdefaultVal.append("}");
        theClass.addField(new JavaField("_ALL_PROPERTIES",
                "String[]",
                null,
                "public static final",
                sbfdefaultVal.toString()));

        boolean optionNoSeparateMetadata=entityInfo.isJBGenOptionEnabled("option-no-separate-metadata",false);
        theClass.addField(new JavaField("_METADATA", entityInfo.getDTOMetaDataName(), null, "public static final", optionNoSeparateMetadata?metaDataString(entityInfo):entityInfo.getDTOMetaDataName()+"._INSTANCE"));

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "public", null, "Constructor",
                ""));
        JavaField field = null;
        String cst = "";
        String name = "";
        for (int i = 0; i < columns.length; i++) {
            field = new JavaField(columns[i].getBeanFieldName(),
                    columns[i].getBusinessDataTypeName(),
                    null,
                    "private",
                    null);
            name = columns[i].getColumnName();
            cst = entityInfo.getDTOName() + "." + columns[i].getBeanFieldConstant();
//                        theClass.addField(field);
            theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(columns[i]), field.getType(), null, "public",
                    null, ("getter for " + field.getName())+"\n"+("@return value for the field"),
                    "return " + JavaUtils.objectToPrimitive("super.getProperty(" + cst + ")", field.getType()) + ";"));
            theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(name), "void", new JavaParam[]{
                new JavaParam("value", field.getType(), null)
            }, "public",
                    null, ("setter for " + field.getName())+"\n"+("@param value new value for the field"),
                    "super.setProperty(" + cst + "," + JavaUtils.primitiveToObject("value", field.getType()) + ");"));
            theClass.addMethod(new JavaMethod("contains" + JBGenUtils.capitalize(field.getName()), "boolean", null, "public", null, ("true if record contains the field " + field.getName())+"\n"+("@return true if record contains the field " + field.getName()),
                    "return super.containsProperty(" + cst + ");"));
            theClass.addMethod(new JavaMethod("unset" + JBGenUtils.capitalize(field.getName()), "void", null, "public", null, "remove the field " + field.getName(),
                    "super.unsetProperty(" + cst + ");"));
        }

//        StringBuffer getDataKeyBuffer=new StringBuffer();
//        for(int i=0;i<pkColumns.length;i++){
//            String cst=entityInfo.getDTOName()+"."+pkColumns[i].beanFieldConstant;
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
            theClass.addMethod(new JavaMethod("getDataKey", "org.vpc.neormf.commons.beans.DataKey", null, "public", null, null,
                    "return get" + entityInfo.getDataKeyName() + "();"));
        }

        theClass.addMethod(createMethodInfo(entityInfo));
        theClass.addMethod(createMethodInfo2(entityInfo));
        entityInfo.setGeneratedClass("DataTransfertObject", theClass);
        ArrayList arraySet = (ArrayList) entityInfo.getProjectInfo().getUserProperties().get("generatedClasses.DataTransfertObject");
        if (arraySet == null) {
            arraySet = new ArrayList();
            entityInfo.getProjectInfo().getUserProperties().put("generatedClasses.DataTransfertObject", arraySet);
        }
        arraySet.add(theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder, getLog())) {
                getLog().info(" generating DataTransfertObject Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + "...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    protected String metaDataString(DAOInfo entityInfo) {
        theClass.addImport("org.vpc.neormf.commons.util.*");
        theClass.addImport("org.vpc.neormf.commons.types.*");
        theClass.addImport("org.vpc.neormf.commons.types.converters.*");
        theClass.addImport(entityInfo.getProjectInfo().getModulePackage() + "." + entityInfo.getProjectInfo().getModuleName());
        StringBuilder infoContent = new StringBuilder();
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        infoContent.append(" new DTOMetaData(\n").append("// Bean Name\n").append("\"").append(entityInfo.getBeanName()).append("\"\n").append("// Bean Fields(name,title,column,type,sqlType,sqlConverter)...\n").append(",new DTOFieldMetaData[]{\n");
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        for (int i = 0; i < columns.length; i++) {
            String businessDataTypeCode = JavaUtils.getImportedCode(columns[i].getBusinessDataType());
            String sqlDataTypeCode = JavaUtils.getImportedCode(columns[i].getSqlDataType());
            if (businessDataTypeCode.equals(sqlDataTypeCode)) {
                businessDataTypeCode = "null";
            }
//            if (columns[i].isFormulaImpl()) {
//                infoContent.append("  new DTOFieldMetaData(\"").append(columns[i].getBeanFieldName()).append("\", ").append("\"").append(columns[i].getTitle()).append("\", ").append("\"").append(columns[i].getColumnName()).append("\", ").append(businessDataTypeCode).append(",").append(sqlDataTypeCode).append(",").append((columns[i].getSqlConverterFactory() == null ? "null" : columns[i].getSqlConverterFactory().getConverterExpression()));
//                if (!isJoinTable(columns[i].getDAOInfo().getPrimaryKeys())) {
//                    DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
//                    infoContent.append("  new DTOFieldMetaData(\"").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append("\", ").append("\"").append(tableInfo.getDAOInfo().getBeanName()).append("\", ").append("\"").append(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName())).append("\", ").append(businessDataTypeCode).append(",").append(sqlDataTypeCode).append(",").append((columns[i].getSqlConverterFactory() == null ? "null" : columns[i].getSqlConverterFactory().getConverterExpression()));
//                } else {
//                }
//            } else {
//                infoContent.append("  new DTOFieldMetaData(\"").append(columns[i].getBeanFieldName()).append("\", ").append("\"").append(columns[i].getTitle()).append("\", ").append("\"").append(columns[i].getColumnName()).append("\", ").append(businessDataTypeCode).append(",").append(sqlDataTypeCode).append(",").append((columns[i].getSqlConverterFactory() == null ? "null" : columns[i].getSqlConverterFactory().getConverterExpression()));
//            }
            infoContent.append("  new DTOFieldMetaData(\"").append(columns[i].getBeanFieldName()).append("\", ").append("\"").append(columns[i].getTitle()).append("\", ").append("\"").append(columns[i].getColumnName()).append("\", ").append(businessDataTypeCode).append(",").append(sqlDataTypeCode).append(",").append((columns[i].getSqlConverterFactory() == null ? "null" : columns[i].getSqlConverterFactory().getConverterExpression()));
            String modString = "";
            modString = modString + (columns[i].isRequiredOnInsert() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.REQUIRED_ON_INSERT") : "");
            modString = modString + (columns[i].isForbiddenOnInsert() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.FORBIDDEN_ON_INSERT") : "");
            modString = modString + (columns[i].isForbiddenOnUpdate() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.FORBIDDEN_ON_UPDATE") : "");
            modString = modString + (columns[i].isForbiddenOnSearch() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.FORBIDDEN_ON_SEARCH") : "");
            modString = modString + (columns[i].isAutoIdentity() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.AUTO_IDENTITY") : "");
            modString = modString + (columns[i].isFormulaImpl() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.FORMULA") : "");
            modString = modString + (columns[i].getRelation() != null ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.RELATION_1N") : "");
            modString = modString + (columns[i].isPk() ? ((modString.length() == 0 ? "" : "|") + "DTOFieldMetaData.PRIMARY_KEY") : "");
            if (modString.length() == 0) {
                modString = "DTOFieldMetaData.DEFAULT";
            }

            infoContent.append(",").append(modString);
            if(columns[i].getRelationLinks().size()>0){
                infoContent.append(",new RelationRoleIndex[]{");//
                boolean firstrelationLink=true;
                for (DBColumn.RelationLink relationLink : columns[i].getRelationLinks()) {
                    if(firstrelationLink){
                        firstrelationLink=false;
                    }else{
                        infoContent.append(",");
                    }
                    if(relationLink.getRole()==Role.Foreign){
                        infoContent.append("new RelationRoleIndex(").append(entityInfo.getProjectInfo().getModuleName()).append(".Relations.").append(relationLink.getRelation().getName()).append(".getForeignRole(),").append(relationLink.getIndex() - 1).append(")");
                    }else{
                        infoContent.append("new RelationRoleIndex(").append(entityInfo.getProjectInfo().getModuleName()).append(".Relations.").append(relationLink.getRelation().getName()).append(".getPrimaryRole(),").append(relationLink.getIndex() - 1).append(")");
                    }
                }
                infoContent.append("}");
            }else{
                infoContent.append(",RelationRoleIndex.NO_ROLES");//

            }
            //infoContent.append(columns[i].getRelation() == null ? "null" : entityInfo.getProjectInfo().getModuleName() + ".Relations." + columns[i].getRelation().getName() + ".getFirstRole()");
            infoContent.append(",").append(columns[i].getPopulationOrder());//
            if(columns[i].getRequiredFields().length==0){
                infoContent.append(",DTOFieldMetaData.NO_FIELDS_REQUIRED");//
            }else{
                infoContent.append(",new String[]{");
                String[] requiredFields = columns[i].getRequiredFields();
                for (int i1 = 0; i1 < requiredFields.length; i1++) {
                    String s = requiredFields[i1];
                    DBColumn requiredColumn = entityInfo.getColumnByFieldName(s, true);
                    if (i1>0) {
                        infoContent.append(",");
                    }
                    infoContent.append(requiredColumn.getBeanFieldConstant());
                }
                infoContent.append("}");
            }
            infoContent.append(")");
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


        infoContent.append("// Title Field Name\n").append(entityInfo.getTitleFieldName() == null ? "null" : ('"' + entityInfo.getTitleFieldName() + '"')).append(",\n").append("// DataTransfertObject Class Name\n").append('"').append(entityInfo.getFullDTOName()).append("\",\n").append("// DataKey Class Name\n").append(entityInfo.getPrimaryKeys().length > 0 ? ('"' + entityInfo.getFullDataKeyName() + '"') : "null").append(",\n").append("// PropertyList Class Name\n").append('"').append(entityInfo.getFullPropertyListName()).append('"').append(",\n").append("// OrderList Class Name\n").append('"').append(entityInfo.getFullOrderListName()).append('"').append(",\n");
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
            infoContent.append("},new boolean[]{");
            for (int i = 0; i < order.length; i++) {
                if (i > 0) {
                    infoContent.append("\n,");
                }
                infoContent.append(order[i].isAsc);
            }
            infoContent.append("},\n");
        }
        infoContent.append("// Extra Properties\n");
        infoContent.append("(Properties)Maps.fill(new Properties(),new Object[]{\n");
        infoContent.append("  \"BeanName\",\n");
        infoContent.append("  \"BusinessObjectName\",\n");
        infoContent.append("  \"EntityHomeClassName\",\n");
        infoContent.append("  \"EntityRemoteClassName\",\n");
        infoContent.append("  \"BusinessDelegateClassName\"\n");
        infoContent.append("},new Object[]{\n");
        infoContent.append("  \"").append(entityInfo.getBeanName()).append("\",\n");
        infoContent.append("  \"").append(entityInfo.getBOName()).append("\",\n");
        infoContent.append("  \"").append(entityInfo.getFullEntityHomeName()).append("\",\n");
        infoContent.append("  \"").append(entityInfo.getFullEntityRemoteName()).append("\",\n");
        infoContent.append("  \"").append(entityInfo.getBOInfo().getFullConnectorName()).append("\"\n");
        infoContent.append("}\n").append(")").append(")\n");
        return infoContent.toString();
    }
    protected JavaMethod createMethodInfo(DAOInfo entityInfo) {
        return new JavaMethod("metadata", entityInfo.getDTOMetaDataName(), null, "public", null, null, "return _METADATA;");
    }

    protected JavaMethod createMethodInfo2(DAOInfo entityInfo) {
        return new JavaMethod("getMetadata",entityInfo.getDTOMetaDataName(), null, "public", null, null, "return _METADATA;");
    }

    public JavaMethod createMethodSpecificGetDataKey(DAOInfo entityInfo) {
        StringBuilder getDataKeyBuffer = new StringBuilder();
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        for (int i = 0; i < pkColumns.length; i++) {
            String cst = entityInfo.getDTOName() + "." + pkColumns[i].getBeanFieldConstant();
            getDataKeyBuffer.append("Object k").append(i).append("=super.getProperty(").append(cst).append(");\n");
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
                    pkColumns[i].getBusinessDataTypeName()));
        }
        getDataKeyBuffer.append(");");
        return (new JavaMethod("get" + entityInfo.getDataKeyName(), entityInfo.getDataKeyName(), null, "public",
                null, null, getDataKeyBuffer));
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
        if (index >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "DataTransfertObject Generator";
    }
}

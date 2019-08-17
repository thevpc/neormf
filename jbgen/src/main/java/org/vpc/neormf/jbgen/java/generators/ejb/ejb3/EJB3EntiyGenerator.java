/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.ejb.ejb3;

import org.vpc.neormf.jbgen.java.generators.dto.*;
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
import org.vpc.neormf.jbgen.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.DateTimeType;
import org.vpc.neormf.commons.types.DateType;
import org.vpc.neormf.commons.types.StringType;
import org.vpc.neormf.commons.types.TimeType;
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
public class EJB3EntiyGenerator extends JBGenDAOGenerator {

    public EJB3EntiyGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO) && entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0);
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }
    JavaClassSource theClass = null;

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        theClass = new JavaClassSource();
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        theClass.setComments(entityInfo.getComments());
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        String NamedQueries = "\n@NamedQueries({ ";
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].isFormulaImpl()) {
                NamedQueries = NamedQueries + "@NamedQuery(name = \"" + entityInfo.getDTOName() + "Entity.findBy" + columns[i].getBeanFieldName() + "\"" + ", query = \"SELECT e FROM " + entityInfo.getDTOName() + "Entity e WHERE e." + columns[i].getBeanFieldName() + "= :" + columns[i].getBeanFieldName() + "\"),\n\t\t";
            }
        }
        NamedQueries = NamedQueries + "@NamedQuery(name = \"" + entityInfo.getDTOName() + "Entity.findByKeys\", query = \"SELECT e FROM " + entityInfo.getDTOName() + "Entity e WHERE 1=1";
        for (int i = 0; i < pkColumns.length; i++) {
            NamedQueries = NamedQueries + " AND e." + pkColumns[i].getBeanFieldName() + "= :" + pkColumns[i].getBeanFieldName();
        }
        NamedQueries = NamedQueries + "\"";

        theClass.setName(entityInfo.getDTOName() + "Entity");
        theClass.setInterfaces(new String[]{"Serializable"});
        theClass.addImport("java.util.*");
        theClass.addImport("java.io.Serializable");
        theClass.addImport("javax.persistence.Entity");
        theClass.addImport("javax.persistence.Table");
        theClass.addImport("javax.persistence.Column");
        theClass.addImport("javax.persistence.Id");
        theClass.addImport("javax.persistence.NamedQuery");
        theClass.addImport("javax.persistence.NamedQueries");
        theClass.setPackage(entityInfo.getEntityPackage());

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "public", null, "Default Constructor", ""));


        String tableType = "";
        if (isJoinTable(pkColumns)) {
            tableType = "//Table de jointure\n";
            getJoinEntityCode(entityInfo);
        } else {
            getEntityCode(entityInfo);
        }
        theClass.setModifiers(tableType + "@Entity \n" +
                "@Table(name=\"" + entityInfo.getTables()[0].getTableName() + "\") " +
                NamedQueries +
                ")})\npublic");


        entityInfo.setGeneratedClass("Entity", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder, getLog())) {
                getLog().info(" generating EJB3 Entity Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }

    }

    private void getEntityCode(DAOInfo entityInfo) {
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        boolean oneToOne = false;
        for (int i = 0; i < columns.length; i++) {
            DataType dataType = columns[i].getBusinessDataType();
            JavaField field = null;
            JavaMethod setMethodForImportedColomn = null;
            JavaMethod getMethodForImportedColomn = null;
            String modifiers = null;
            boolean ignoreColumn = false;
            boolean found = false;
            if (columns[i].isPk()) {
                String temporalType = "";
                if ((dataType instanceof DateTimeType) || (dataType instanceof DateType) || (dataType instanceof TimeType)) {
                    theClass.addImport("javax.persistence.Temporal");
                    theClass.addImport("javax.persistence.TemporalType");
                    if (dataType instanceof DateTimeType) {
                        temporalType = "\n@Temporal(TemporalType.TIMESTAMP)";
                    } else {
                        if (dataType instanceof DateType) {
                            temporalType = "\n@Temporal(TemporalType.DATE)";
                        } else {
                            temporalType = "\n@Temporal(TemporalType.TIME)";
                        }
                    }
                }
                modifiers = ("@Id \n@Column (name=\"" + columns[i].getColumnName() + "\", nullable = " + columns[i].isNullable() + ")" + temporalType + "\n");
                field = new JavaField(columns[i].getBeanFieldName(),
                        columns[i].getBusinessDataTypeName(),
                        null,
                        modifiers + " private",
                        null);
            } else if (columns[i].isFormulaImpl()) {
                modifiers = getAnnotationRelationForDetails(i, columns, entityInfo);
                if (!isJoinTable(columns[i].getRelation().getFkTable().getPkColumns())) {
                    DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                    field = new JavaField(tableInfo.getDAOInfo().getBeanName(),
                            tableInfo.getDAOInfo().getBeanName() + "Entity",
                            null,
                            modifiers + " private",
                            null);
                } else {
                    field = new JavaField(columns[i].getBeanFieldName(),
                            columns[i].getBusinessDataTypeName(),
                            null,
                            modifiers + " private",
                            null);
                }
            } else {
                String temporalType = "";
                if ((dataType instanceof DateTimeType) || (dataType instanceof DateType) || (dataType instanceof TimeType)) {
                    theClass.addImport("javax.persistence.Temporal");
                    theClass.addImport("javax.persistence.TemporalType");
                    if (dataType instanceof DateTimeType) {
                        temporalType = "\n@Temporal(TemporalType.TIMESTAMP)";
                    } else {
                        if (dataType instanceof DateType) {
                            temporalType = "\n@Temporal(TemporalType.DATE)";
                        } else {
                            temporalType = "\n@Temporal(TemporalType.TIME)";
                        }
                    }
                }
                int indexFound = 0;
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
                    } else if (found) {
                        DBTableInfo tableInfo = columns[i].getTable();
                        for (DBRelation relation : tableInfo.getImportRelations()) {
                            DBTableInfo pkTable = r.getPkTable();
                            DBColumn[] dbc = pkTable.getPkColumns();
                            for (DBColumn column : r.getPkColumns()) {
                                for (int j = 0; j < dbc.length; j++) {
                                    if (column.getColumnName().equals(dbc[j].getColumnName())) {
                                        oneToOne = true;
                                    }
                                }
                            }
                        }
                        if (oneToOne) {
                            modifiers = ("@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)\n@JoinColumn (name=\"" + columns[i].getColumnName() + "\", nullable = " + columns[i].isNullable() + ")\n");
                            for (DBRelation dbr : columns[i].getTable().getImportRelations()) {
                                theClass.addImport(dbr.getPkTable().getDAOInfo().getEntityPackage() + "." + dbr.getPkTable().getDAOInfo().getDTOName() + "Entity");
                            }
                            theClass.addImport("javax.persistence.OneToOne");
                            theClass.addImport("javax.persistence.JoinColumn");
                            theClass.addImport("javax.persistence.CascadeType");
                            theClass.addImport("javax.persistence.FetchType");
                        } else {
                            modifiers = ("@ManyToOne\n@JoinColumn (name=\"" + columns[i].getColumnName() + "\", nullable = " + columns[i].isNullable() + ")" + temporalType + "\n");
                            for (DBRelation dbr : columns[i].getTable().getImportRelations()) {
                                theClass.addImport(dbr.getPkTable().getDAOInfo().getEntityPackage() + "." + dbr.getPkTable().getDAOInfo().getDTOName() + "Entity");
                            }
                            theClass.addImport("javax.persistence.ManyToOne");
                            theClass.addImport("javax.persistence.JoinColumn");

                        }
                        getMethodForImportedColomn = new JavaMethod("get" + r.getPkTable().getDAOInfo().getBeanName(),
                                r.getPkTable().getDAOInfo().getBeanName() + "Entity",
                                null,
                                "public",
                                null,
                                "getter for " + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()),
                                "return " + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()) + ";");
                        setMethodForImportedColomn = new JavaMethod("set" + r.getPkTable().getDAOInfo().getBeanName(),
                                "void",
                                new JavaParam[]{new JavaParam(JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()), r.getPkTable().getDAOInfo().getBeanName() + "Entity ", null)},
                                "public",
                                null,
                                "setter for " + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()),
                                "this." + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()) + "=" + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()) + ";");
                        field = new JavaField(JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()),
                                r.getPkTable().getDAOInfo().getBeanName() + "Entity",
                                null,
                                modifiers + " private",
                                null);
                        break;
                    }
                }
                if (!found) {
                    modifiers = ("@Column (name=\"" + columns[i].getColumnName() + "\", nullable = " + columns[i].isNullable() + ")" + temporalType + "\n");
                    field = new JavaField(columns[i].getBeanFieldName(),
                            columns[i].getBusinessDataTypeName(),
                            null,
                            modifiers + " private",
                            null);
                }
            }
            if (!ignoreColumn) {
                theClass.addField(field);
                if (!found) {
                    if (columns[i].isFormulaImpl() && !isJoinTable(columns[i].getRelation().getFkTable().getPkColumns())) {
                        DBTableInfo tableInfo = columns[i].getRelation().getFkTable();
                        theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(tableInfo.getDAOInfo().getBeanName(), null),
                                field.getType(),
                                null,
                                "public",
                                null,
                                "getter for " + field.getName(),
                                "return " + tableInfo.getDAOInfo().getBeanName() + ";"));
                        theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(tableInfo.getDAOInfo().getBeanName()),
                                "void",
                                new JavaParam[]{new JavaParam(JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName()), field.getType(), null)},
                                "public",
                                null,
                                "setter for " + field.getName(),
                                "this." + tableInfo.getDAOInfo().getBeanName() + " = " + JBGenUtils.decapitalize(tableInfo.getDAOInfo().getBeanName()) + ";"));
                    } else {
                        theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(columns[i]),
                                field.getType(),
                                null,
                                "public",
                                null,
                                "getter for " + field.getName(),
                                "return " + columns[i].getBeanFieldName() + ";"));
                        theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(columns[i]),
                                "void",
                                new JavaParam[]{new JavaParam(columns[i].getBeanFieldName(), field.getType(), null)},
                                "public",
                                null,
                                "setter for " + field.getName(),
                                "this." + columns[i].getBeanFieldName() + " = " + columns[i].getBeanFieldName() + ";"));
                    }
                } else {
                    theClass.addMethod(getMethodForImportedColomn);
                    theClass.addMethod(setMethodForImportedColomn);
                }
            }
        }
    }

    private String getAnnotationRelationForDetails(int i, DBColumn[] columns, DAOInfo entityInfo) {
        StringBuffer sb = new StringBuffer();
        FieldFormulaImpl impl = columns[i].getGetterImpl();
        String temporalType = "";
        DataType dataType = columns[i].getBusinessDataType();
        if ((dataType instanceof DateTimeType) || (dataType instanceof DateType) || (dataType instanceof TimeType)) {
            theClass.addImport("javax.persistence.Temporal");
            theClass.addImport("javax.persistence.TemporalType");
            if (dataType instanceof DateTimeType) {
                temporalType = "\n@Temporal(TemporalType.TIMESTAMP)";
            } else {
                if (dataType instanceof DateType) {
                    temporalType = "\n@Temporal(TemporalType.DATE)";
                } else {
                    temporalType = "\n@Temporal(TemporalType.TIME)";
                }
            }
        }
        switch (impl.getType()) {
            case sqlMasterDetail: {
                RelationDesc rdesc = new RelationDesc(impl.getBody(), columns[i], entityInfo);
                String[] detailsFk = rdesc.getDetailPrimaryFields();
                if (!isJoinTable(columns[i].getRelation().getFkTable().getPkColumns())) {
                    theClass.addImport("javax.persistence.OneToOne");
                    sb.append("@OneToOne(mappedBy = \"");
                    sb.append(JBGenUtils.decapitalize(entityInfo.getBeanName()));
                    sb.append("\", fetch = FetchType.EAGER");
                } else {
                    theClass.addImport("javax.persistence.OneToMany");
                    sb.append("@OneToMany(mappedBy = \"");
                    sb.append(JBGenUtils.decapitalize(entityInfo.getBeanName()));
                    sb.append("\", fetch = FetchType.LAZY");
                }
                theClass.addImport(rdesc.getDetailTable().getDAOInfo().getEntityPackage() + "." + rdesc.getDetailTable().getDAOInfo().getBeanName() + "Entity");
                theClass.addImport("javax.persistence.FetchType");
                sb.append(", targetEntity=").append(rdesc.getDetailTable().getDAOInfo().getBeanName()).append("Entity.class)").append(temporalType).append("\n");
                break;
            }
            default: {

            }
            }
        return sb.toString();
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

    private void getJoinEntityCode(DAOInfo entityInfo) {
        theClass.addSubClass(getEmbeddablePKClass(entityInfo));
        getEmbeddedIdField(entityInfo);
        addOthersField(entityInfo);
        mappingTheImportedRelations(entityInfo);
        addFullConstructorForJoinTable(entityInfo);
    }

    private JavaClassSource getEmbeddablePKClass(DAOInfo entityInfo) {
        JavaClassSource idClass = new JavaClassSource();
        StringBuffer classModifier = new StringBuffer();
        String fieldModifier;
        StringBuffer body = new StringBuffer();
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        JavaField field = null;
        theClass.addImport("javax.persistence.Embeddable");
        classModifier.append("@Embeddable\n");
        classModifier.append("public static");
        idClass.setModifiers(classModifier.toString());
        idClass.setName(entityInfo.getBeanName() + "PK");
        idClass.setInterfaces(new String[]{"Serializable"});
        for (int i = 0; i < pkColumns.length; i++) {
            body.append("this.").append(pkColumns[i].getBeanFieldName()).append(" = ").append(pkColumns[i].getBeanFieldName()).append(";\n");
        }
        JavaMethod fullConstructor = new JavaMethod(idClass.getName(),
                null,
                null,
                "public",
                null,
                "Full Constructor for " + idClass.getName(),
                body.toString());
        for (int i = 0; i < pkColumns.length; i++) {
            DataType dataType = pkColumns[i].getBusinessDataType();
            String temporalType = "";
            if ((dataType instanceof DateTimeType) || (dataType instanceof DateType) || (dataType instanceof TimeType)) {
                theClass.addImport("javax.persistence.Temporal");
                theClass.addImport("javax.persistence.TemporalType");
                if (dataType instanceof DateTimeType) {
                    temporalType = "\n@Temporal(TemporalType.TIMESTAMP)";
                } else {
                    if (dataType instanceof DateType) {
                        temporalType = "\n@Temporal(TemporalType.DATE)";
                    } else {
                        temporalType = "\n@Temporal(TemporalType.TIME)";
                    }
                }
            }

            //if (found) {
            fieldModifier = "@Column (name = \"" + pkColumns[i].getColumnName() + "\")" + temporalType + "\nprivate";
            field = new JavaField(pkColumns[i].getBeanFieldName(),
                    pkColumns[i].getBusinessDataTypeName(),
                    null,
                    fieldModifier,
                    null);
            idClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(pkColumns[i]),
                    field.getType(),
                    null,
                    "public",
                    null,
                    "getter for " + field.getName(),
                    "return " + pkColumns[i].getBeanFieldName() + ";"));
            idClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(pkColumns[i]),
                    "void",
                    new JavaParam[]{new JavaParam(pkColumns[i].getBeanFieldName(), field.getType(), null)},
                    "public",
                    null,
                    "setter for " + field.getName(),
                    "this." + pkColumns[i].getBeanFieldName() + "=" + pkColumns[i].getBeanFieldName() + ";"));
            idClass.addField(field);
            fullConstructor.addParam(new JavaParam(pkColumns[i].getBeanFieldName(), pkColumns[i].getBusinessDataTypeName(), null));
        //}
        }
        idClass.addMethod(new JavaMethod(idClass.getName(), null, null, "public", null, "Default Constructor for " + idClass.getName(), ""));
        idClass.addMethod(fullConstructor);
        return idClass;
    }

    private void getEmbeddedIdField(DAOInfo entityInfo) {
        String idFieldName = JBGenUtils.decapitalize(entityInfo.getBeanName()) + "PK";
        String idFieldType = entityInfo.getBeanName() + "PK";
        String idFieldComments = "clé composite de la classe" + entityInfo.getBeanName();
        String idFieldModifiers = "@EmbeddedId\nprivate";
        String idFieldDefaultValue = " new " + entityInfo.getBeanName() + "PK()";
        JavaField idField = new JavaField(idFieldName, idFieldType, idFieldComments, idFieldModifiers, idFieldDefaultValue);
        theClass.addImport("javax.persistence.EmbeddedId");
        theClass.addField(idField);
        theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(entityInfo.getBeanName() + "PK", null),
                entityInfo.getBeanName() + "PK",
                null,
                "public",
                null,
                "getter for " + entityInfo.getBeanName() + "PK",
                "return " + JBGenUtils.decapitalize(entityInfo.getBeanName()) + "PK;"));
        theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(entityInfo.getBeanName() + "PK"),
                "void",
                new JavaParam[]{new JavaParam(JBGenUtils.decapitalize(entityInfo.getBeanName()) + "PK", entityInfo.getBeanName() + "PK", null)},
                "public",
                null,
                "setter for " + entityInfo.getBeanName() + "PK",
                "this." + JBGenUtils.decapitalize(entityInfo.getBeanName()) + "PK = " + JBGenUtils.decapitalize(entityInfo.getBeanName()) + "PK;"));
    }

    private void addOthersField(DAOInfo entityInfo) {
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        String modifier;
        JavaField field = null;
        boolean found = false;
        int indexFound = 0;
        for (int i = 0; i < columns.length; i++) {
            found = false;
            if (columns[i].isPk()) {
                for (DBRelation r : columns[i].getTable().getImportRelations()) {
                    indexFound = 0;
                    for (DBColumn c : r.getFkColumns()) {
                        if (c.getColumnName().equals(columns[i].getColumnName())) {
                            found = true;
                            break;
                        }
                        indexFound++;
                    }
                }
            }
            if (!found) {
                String temporalType = "";
                DataType dataType = columns[i].getBusinessDataType();
                if ((dataType instanceof DateTimeType) || (dataType instanceof DateType) || (dataType instanceof TimeType)) {
                    theClass.addImport("javax.persistence.Temporal");
                    theClass.addImport("javax.persistence.TemporalType");
                    if (dataType instanceof DateTimeType) {
                        temporalType = "\n@Temporal(TemporalType.TIMESTAMP)";
                    } else {
                        if (dataType instanceof DateType) {
                            temporalType = "\n@Temporal(TemporalType.DATE)";
                        } else {
                            temporalType = "\n@Temporal(TemporalType.TIME)";
                        }
                    }
                }
                modifier = "@Column (name = \"" + columns[i].getColumnName() + "\")" + temporalType + "\n private";
                field = new JavaField(columns[i].getBeanFieldName(),
                        columns[i].getBusinessDataTypeName(),
                        null,
                        modifier,
                        null);
                theClass.addField(field);
                theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(columns[i]),
                        field.getType(),
                        null,
                        "public",
                        null,
                        "getter for " + field.getName(),
                        "return " + columns[i].getBeanFieldName() + ";"));
                theClass.addMethod(new JavaMethod(JavaUtils.businessSetterName(columns[i]),
                        "void",
                        new JavaParam[]{new JavaParam(columns[i].getBeanFieldName(), field.getType(), null)},
                        "public",
                        null,
                        "setter for " + field.getName(),
                        "this." + columns[i].getBeanFieldName() + " = " + columns[i].getBeanFieldName() + ";"));
            }
        }
    }

    private void mappingTheImportedRelations(DAOInfo entityInfo) {
        String modifier;
        JavaField field = null;
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        if (pkColumns.length > 1) {
            DBTableInfo thisTableName = pkColumns[0].getTable();
            for (int i = 0; i < pkColumns.length; i++) {
                for (DBRelation r : thisTableName.getImportRelations()) {
                    DBTableInfo pkTable = r.getPkTable();
                    DBColumn[] dbc = pkTable.getPkColumns();
                    for (DBColumn column : r.getPkColumns()) {
                        for (int j = 0; j < dbc.length; j++) {
                            if (column.getColumnName().equals(dbc[j].getColumnName())) {
                                modifier = "@ManyToOne\n@JoinColumn (name = \"" + column.getColumnName() + "\", insertable = false, updatable = false)\n private";
                                field = new JavaField(JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()),
                                        r.getPkTable().getDAOInfo().getBeanName() + "Entity",
                                        null,
                                        modifier,
                                        null);
                                theClass.addField(field);
                                theClass.addMethod(new JavaMethod("get" + r.getPkTable().getDAOInfo().getBeanName(),
                                        r.getPkTable().getDAOInfo().getBeanName() + "Entity",
                                        null,
                                        "public",
                                        null,
                                        "getter for " + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()),
                                        "return " + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()) + ";"));
                                theClass.addMethod(new JavaMethod("set" + r.getPkTable().getDAOInfo().getBeanName(),
                                        "void",
                                        new JavaParam[]{new JavaParam(JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()), r.getPkTable().getDAOInfo().getBeanName() + "Entity ", null)},
                                        "public",
                                        null,
                                        "setter for " + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()),
                                        "this." + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()) + "=" + JBGenUtils.decapitalize(r.getPkTable().getDAOInfo().getBeanName()) + ";"));
                            }
                        }

                    }
                }
                for (DBRelation dbr : pkColumns[i].getTable().getImportRelations()) {
                    theClass.addImport(dbr.getPkTable().getDAOInfo().getEntityPackage() + "." + dbr.getPkTable().getDAOInfo().getDTOName() + "Entity");
                }
                break;
            }
        }
        theClass.addImport("javax.persistence.JoinColumn");
        theClass.addImport("javax.persistence.ManyToOne");
    }

    public void addFullConstructorForJoinTable(DAOInfo entityInfo) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        boolean found = false;
        sb1.append("// affectation des valeurs aux clés \n");
        sb2.append("// associations bidirectionnelles\n");
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        String name1 = "";
        String name2 = "";
        String name3 = "";
        int index = 0;
        ArrayList<String> arrayList = new ArrayList<String>();
        DBTableInfo joinTable = pkColumns[0].getTable();
        for (DBRelation relation : joinTable.getImportRelations()) {
            DBTableInfo pkTable = relation.getPkTable();
            DBColumn[] pkTablePkColumns = pkTable.getDAOInfo().getColumns(true, false, false);
            for (DBColumn dBColumn : relation.getFkColumns()) {
            //System.out.println(dBColumn.getColumnName());
            }
            for (DBColumn dbc : relation.getPkColumns()) {
                for (int i = 0; i < pkTablePkColumns.length; i++) {
                    if (pkTablePkColumns[i].getColumnName().equals(dbc.getColumnName())) {
                        for (DBColumn column : relation.getFkColumns()) {
                            //System.out.println(relation.getFkName() + " " + column.getColumnName());
                            arrayList.add(column.getColumnName());
                            sb1.append(JavaUtils.businessGetterName(entityInfo.getBeanName(), null)).append("PK().").append(JavaUtils.businessSetterName(column.getColumnName())).append("(").append(JBGenUtils.decapitalize(pkTable.getDAOInfo().getBeanName())).append(".").append(JavaUtils.businessGetterName(dbc.getColumnName(), null)).append("());\n");
                        }

                    } else {

                    }
                }

            }
        }
        for (int j = 0; j < pkColumns.length; j++) {
            System.out.println(pkColumns[j].getColumnName());
        }
        boolean fk = false;
        int k=0;
        for (int i = 0; i < pkColumns.length; i++) {
            fk = false;
            for (int j = 0; j < arrayList.size(); j++) {
                if (pkColumns[i].getColumnName().equals(arrayList.get(j))) {
                    fk=true;
                    k=i;
                    break;
                }
            }
            if(!fk){
                sb1.append(JavaUtils.businessGetterName(entityInfo.getBeanName(), null)).append("PK().").append(JavaUtils.businessSetterName(pkColumns[k].getBeanFieldName())).append("(this.").append(JBGenUtils.decapitalize(pkColumns[k].getBeanFieldName())).append(");\n");
            }
        }

//        DBTableInfo thisTableName = pkColumns[0].getTable();
//            for (int i = 0; i < pkColumns.length; i++) {
//                for (DBRelation r : thisTableName.getImportRelations()) {
//                    DBTableInfo pkTable = r.getPkTable();
//                    DBColumn[] dbc = pkTable.getPkColumns();
//                    for (DBColumn column : r.getPkColumns()) {
//                        for (int j = 0; j < dbc.length; j++) {
//                            if (column.getColumnName().equals(dbc[j].getColumnName())) {
//                                name1=column.getColumnName();
//                                name2=dbc[j].getColumnName();
//                                index++;
//                            }else{
//                                name3=column.getColumnName();
//                            }
//                        }
//                    }
//                }
//                break;
//            }
//        System.out.println("la table est "+pkColumns[0].getTableName()+" et index est "+index+" et column.getColumnName() est "+name1+" et dbc[j].getColumnName() est "+name2+" et name3 est "+name3);
/*
        Collection<DBRelation> relations = pkColumns[0].getTable().getImportRelations();
        int nbRelation = relations.size();
        DBTableInfo thisTableName = pkColumns[0].getTable();
        for (DBRelation r : thisTableName.getImportRelations()) {
        DBTableInfo pkTable = r.getPkTable();
        DBColumn[] pkTablePkColumns = pkTable.getPkColumns();
        //for (DBColumn column : r.getPkColumns()) {
        for (int j = 0; j < pkColumns.length; j++) {
        for (int i = 0; i < pkTablePkColumns.length; i++) {
        if (pkColumns[j].getColumnName().equals(pkTablePkColumns[i].getColumnName())) {
        System.out.println("je suis dans la table " + thisTableName + ", la colonne " + pkColumns[j].getColumnName() + " est trouvé dans la table " + pkTable.getTableName());
        //break;
        } else {
        System.out.println("je suis dans la table " + thisTableName + ", la colonne " + pkColumns[j].getColumnName() + " n'a pas été trouvée");
        //break;
        }break;
        }break;
        }
        //break;
        //}
        }*/


        /*for (int i = 0; i < pkColumns.length; i++) {
        for (DBRelation r : pkColumns[i].getTable().getImportRelations()) {
        found = false;
        for (DBColumn c : r.getFkColumns()) {
        if (c.getColumnName().equals(pkColumns[i].getColumnName())) {
        found = true;
        break;
        }
        }
        }
        System.out.println(found);
        }*/
        for (int i = 0; i < pkColumns.length; i++) {
            for (DBRelation r : pkColumns[i].getTable().getImportRelations()) {
                DBTableInfo pkTable = r.getPkTable();
                sb2.append("this.").append(JavaUtils.businessSetterName(pkTable.getDAOInfo().getBeanName())).append("(").append(JBGenUtils.decapitalize(pkTable.getDAOInfo().getBeanName())).append(");\n");
                DBColumn[] dbc = pkTable.getPkColumns();
                for (DBColumn column : r.getPkColumns()) {
                    for (int j = 0; j < dbc.length; j++) {
                        if (column.getColumnName().equals(dbc[j].getColumnName())) {
                            //sb1.append(JavaUtils.businessGetterName(entityInfo.getBeanName() + "PK()." + JavaUtils.businessSetterName(pkColumns[i]) + "(" + JBGenUtils.decapitalize(dbc[j].getDAOInfo().getBeanName()) + "." + JavaUtils.businessGetterName(dbc[j]) + "());\n", null));
                            sb3.append(JBGenUtils.decapitalize(dbc[j].getDAOInfo().getBeanName())).append(".").append(JavaUtils.businessGetterName(JBGenUtils.decapitalize(entityInfo.getBeanName()), null)).append("List().add(this);\n");
                        }
                        break;
                    }
                }
            }
            break;
        }
        JavaMethod fullConstructor = new JavaMethod(theClass.getName(),
                null,
                null,
                "public",
                null,
                "Full Constructor",
                sb1.append(sb2).append(sb3).toString());
        for (int i = 0; i < pkColumns.length; i++) {
            for (DBRelation r : pkColumns[i].getTable().getImportRelations()) {
                DBTableInfo pkTable = r.getPkTable();
                DBColumn[] dbc = pkTable.getPkColumns();
                for (DBColumn column : r.getPkColumns()) {
                    for (int j = 0; j < dbc.length; j++) {
                        if (column.getColumnName().equals(dbc[j].getColumnName())) {
                            fullConstructor.addParam(new JavaParam(JBGenUtils.decapitalize(dbc[j].getDAOInfo().getBeanName()), pkTable.getDAOInfo().getBeanName() + "Entity", null));
                        }
                    }
                }
            }
            break;
        }
        theClass.addMethod(fullConstructor);
    }

    @Override
    public String toString() {
        return "EJB3 Generator";
    }
}

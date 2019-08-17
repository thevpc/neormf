/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.commons.beans;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.converters.DataTypeConverter;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 5 avr. 2004 Time: 14:16:00
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DTOFieldMetaData implements Serializable {
    public static final String[] NO_FIELDS_REQUIRED = {};
    public static final int DEFAULT = 0;
    public static final int REQUIRED_ON_INSERT = 1;
    public static final int FORBIDDEN_ON_INSERT = 2;
    public static final int FORBIDDEN_ON_UPDATE = 4;
    public static final int FORBIDDEN_ON_SEARCH = 8;
    public static final int AUTO_IDENTITY = 16;
    public static final int FORMULA = 32;
    public static final int RELATION_1N = 64;
    public static final int RELATION_N1 = 128;
    public static final int RELATION_11 = 512;
    public static final int PRIMARY_KEY = 1024;
    private String fieldName;
    private String columnName;
    private String fieldTitle;
    private DataType fieldType;
    private DataType sqlType;
    private transient DTOMetaData dataInfo;
    private DataTypeConverter converter;
    private Hashtable clientProperties;
    private int modifiers;
    private RelationRoleIndex[] relationRoleIndices;
    private int populationOrder;
    private String[] requiredFields;

    public DTOFieldMetaData(String fieldName, String fieldTitle, String columnName, DataType fieldType, DataType sqlType, DataTypeConverter converter) {
        this(fieldName, fieldTitle, columnName, fieldType, sqlType, converter, DEFAULT, null, 0, NO_FIELDS_REQUIRED);
    }

    public DTOFieldMetaData(String fieldName, String fieldTitle, String columnName, DataType fieldType, DataType sqlType, DataTypeConverter converter, int modifiers, RelationRoleIndex[] relationRoleIndices, int populationOrder, String[] requiredFields) {
        this.populationOrder = populationOrder;
        this.requiredFields = requiredFields == null ? NO_FIELDS_REQUIRED : requiredFields;
        if (relationRoleIndices == null) {
            this.relationRoleIndices = new RelationRoleIndex[0];
        } else {
            this.relationRoleIndices = relationRoleIndices;
            for (RelationRoleIndex rri : relationRoleIndices) {
                rri.setField(this);
            }
        }
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.converter = converter;
        this.fieldTitle = fieldTitle;
        if (this.fieldTitle == null) {
            this.fieldTitle = this.fieldName;
        }
        this.fieldType = fieldType == null ? sqlType : fieldType;
        this.sqlType = sqlType == null ? fieldType : sqlType;
        this.modifiers = modifiers;
    }

    public boolean isRequiredOnInsert() {
        return (modifiers & REQUIRED_ON_INSERT) == REQUIRED_ON_INSERT;
    }

    public boolean isForbiddenOnInsert() {
        return (modifiers & FORBIDDEN_ON_INSERT) == FORBIDDEN_ON_INSERT;
    }

    public boolean isForbiddenOnUpdate() {
        return (modifiers & FORBIDDEN_ON_UPDATE) == FORBIDDEN_ON_UPDATE;
    }

    public boolean isForbiddenOnSearch() {
        return (modifiers & FORBIDDEN_ON_SEARCH) == FORBIDDEN_ON_SEARCH;
    }

    public boolean isAutoIdentity() {
        return (modifiers & AUTO_IDENTITY) == AUTO_IDENTITY;
    }

    public boolean isPrimaryKey() {
        return (modifiers & PRIMARY_KEY) == PRIMARY_KEY;
    }

    public boolean isFormula() {
        return (modifiers & FORMULA) == FORMULA;
    }

    public boolean isRelation11() {
        return (modifiers & RELATION_11) == RELATION_11;
    }

    public boolean isRelation1N() {
        return (modifiers & RELATION_1N) == RELATION_1N;
    }

    public boolean isRelationN1() {
        return (modifiers & RELATION_N1) == RELATION_N1;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldTitle() {
        return fieldTitle;
    }

    public DataType getFieldType() {
        return fieldType;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getSqlType() {
        return sqlType;
    }

    public DataTypeConverter getConverter() {
        return converter;
    }

    public DTOMetaData getDTOMetaData() {
        return dataInfo;
    }

    public void setDTOMetaData(DTOMetaData dataInfo) {
        this.dataInfo = dataInfo;
    }

    public Object getClientProperty(String name) {
        return clientProperties == null ? null : clientProperties.get(name);
    }

    public void setClientProperty(String name, Object value) {
        if (value == null) {
            if (clientProperties != null) {
                clientProperties.remove(name);
            }

        } else {
            if (clientProperties == null) {
                clientProperties = new Hashtable();
            }
            clientProperties.put(name, value);
        }
    }

    public int getModifiers() {
        return modifiers;
    }

    public boolean isPK() {
        return isOneOfModifiers(PRIMARY_KEY);
    }

    public boolean isRegular() {
        return !isOneOfModifiers(FORMULA | RELATION_11 | RELATION_1N | RELATION_N1);
    }

    public boolean isAllModifiers(int others) {
        return (modifiers & others) == others;
    }

    public boolean isOneOfModifiers(int others) {
        return (modifiers & others) != 0;
    }

    public RelationRoleIndex[] getRelationRoleIndices() {
        return relationRoleIndices;
    }

    public String[] getRequiredFields() {
        return requiredFields;
    }

    public int getPopulationOrder() {
        return populationOrder;
    }

    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

}

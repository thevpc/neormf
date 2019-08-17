package org.vpc.neormf.jbgen.dbsupport;

import org.vpc.neormf.commons.beans.RelationInfo;
import org.vpc.neormf.commons.beans.RelationInfo.Cardinality;
import org.vpc.neormf.commons.beans.RelationInfo.Role;
import org.vpc.neormf.commons.sql.DAOFieldKind;
import org.vpc.neormf.commons.types.*;
import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.types.AnyTypeDelegate;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.FieldFormulaImpl;
import org.vpc.neormf.jbgen.util.FieldFormulaType;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 15:17:38
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DBColumn implements Comparable<DBColumn> {

    private boolean nullable;
    private String columnName;
    private String tableName;
    private String title;
    private int sqlType;
    private String typeName;
    private int columnSize;
    private int bufferLenght;
    private int decimalDegits;
    private int numRecRadix;
    private DBTableInfo table;
    private String beanFieldName;
    private String beanFieldConstant;
    private String beanFieldVar;
    private DataType businessDataType;
    private DataType sqlDataType;
    private DataTypeConverterFactory sqlConverterFactory;
    private DAOFieldKind fieldKind = DAOFieldKind.REGULAR;
    private FieldFormulaImpl getterImpl;
    private FieldFormulaImpl setterImpl;
    private FieldFormulaImpl creatorImpl;
    private boolean requiredOnInsert = false;
    private boolean forbiddenOnInsert = false;
    private boolean forbiddenOnUpdate = false;
    private boolean forbiddenOnSearch = false;
    //    public int primaryKey=-1;
    private boolean autoIdentity = false;
    private int pkPosition = -1;
    private int pkKeySeq;
    private String pkName;
    private DAOInfo dao;
    private DBRelation relation;
    private RelationInfo.Cardinality cardinality;
    private List<RelationLink> relationLinks = new ArrayList<RelationLink>();
    private int populationOrder;
    private String[] requiredFields = new String[0];
    public static Comparator<DBColumn> POPULATION_COMPARATOR = new Comparator<DBColumn>() {
        public int compare(DBColumn o1, DBColumn o2) {
            int order1 = o1.getPopulationOrder();
            int order2 = o2.getPopulationOrder();
            return order1 - order2;
        }
    };

    public static class RelationLink {
        private DBRelation relation;
        private RelationInfo.Cardinality cardinality;
        private RelationInfo.Role role;
        private int index;

        public RelationLink(DBRelation relation, int index, RelationInfo.Role role, Cardinality cardinality) {
            this.relation = relation;
            this.cardinality = cardinality;
            this.role = role;
            this.index = index;
        }

        public Cardinality getCardinality() {
            return cardinality;
        }

        public DBRelation getRelation() {
            return relation;
        }

        public Role getRole() {
            return role;
        }

        public int getIndex() {
            return index;
        }


    }

    public DBColumn(boolean nullable,
                    String tableName,
                    String columnName,
                    int sqlType,
                    String typeName,
                    int columnSize,
                    int bufferLenght,
                    int decimalDegits,
                    int numRecRadix) {
        this(nullable,
                tableName,
                columnName,
                sqlType,
                typeName,
                columnSize,
                bufferLenght,
                decimalDegits,
                numRecRadix,
                null);
    }

    private DBColumn(boolean nullable,
                     String tableName,
                     String columnName,
                     int sqlType,
                     String typeName,
                     int columnSize,
                     int bufferLenght,
                     int decimalDegits,
                     int numRecRadix,
                     DBTableInfo dbTable) {
        this.nullable = nullable;
        this.tableName = tableName;
        this.columnName = columnName;
        this.sqlType = sqlType;
        this.typeName = typeName;
        this.columnSize = columnSize;
        this.bufferLenght = bufferLenght;
        this.decimalDegits = decimalDegits;
        this.numRecRadix = numRecRadix;
        this.table = dbTable;
        this.beanFieldName = JavaUtils.toJavaIdentifier(this.columnName, false);
        this.beanFieldConstant = JBGenUtils.getJavaConstant(this.columnName);
        this.beanFieldVar = JavaUtils.getJavaVar(this.columnName);
    }

    public DBColumn(ResultSet columnsResultSet, DBTableInfo dbTable) throws SQLException {
        this("YES".equalsIgnoreCase(columnsResultSet.getString("IS_NULLABLE")),
                columnsResultSet.getString("TABLE_NAME"),
                columnsResultSet.getString("COLUMN_NAME"),
                columnsResultSet.getInt("DATA_TYPE"),
                columnsResultSet.getString("TYPE_NAME"),
                columnsResultSet.getInt("COLUMN_SIZE"),
                columnsResultSet.getInt("BUFFER_LENGTH"),
                columnsResultSet.getInt("DECIMAL_DIGITS"),
                columnsResultSet.getInt("NUM_PREC_RADIX"),
                dbTable);
    }

    public DataType getDataTypeForSQLType(boolean nullable,
                                          int sqlType,
                                          String typeName,
                                          int columnSize,
                                          int bufferLength,
                                          int decimalDegits,
                                          int numRecRadix) {
        ConfigNode[] replaceConfigNodes = dao.getFieldConfig(this.beanFieldName);
        String values = null;
        for (int i = 0; i < replaceConfigNodes.length; i++) {
            ConfigNode configNode = replaceConfigNodes[i];
            nullable = configNode.getAttributeBoolean("nullable", nullable);
            columnSize = configNode.getAttributeInt("columnsize", columnSize);
            bufferLength = configNode.getAttributeInt("bufferlength", bufferLength);
            decimalDegits = configNode.getAttributeInt("decimaldegits", decimalDegits);
            numRecRadix = configNode.getAttributeInt("numrecradix", numRecRadix);
            typeName = configNode.getAttribute("typename", typeName);
            values = configNode.getAttribute("values", values);
        }
        DataType theType = null;
        switch (sqlType) {
            case Types.LONGVARBINARY:
            case Types.LONGVARCHAR:
            case Types.VARBINARY:
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.BINARY: {
                int min = 0;
                int max = columnSize;
                boolean multiline = false;
                ConfigNode[] configNodes = dao.getFieldConfig(this.beanFieldName);
                for (int i = 0; i < configNodes.length; i++) {
                    ConfigNode configNode = configNodes[i];
                    min = configNode.getAttributeInt("min", min);
                    max = configNode.getAttributeInt("max", max);
                    multiline = configNode.getAttributeBoolean("multiline", multiline);
                }
                theType = new StringType(nullable, min, max, multiline);
                if (values != null) {
                    ArrayList valuesList = new ArrayList();
                    ArrayList namesList = new ArrayList();
                    String[] valuesArray = values.split(";");
                    for (int i = 0; i < valuesArray.length; i++) {
                        String s = valuesArray[i];
                        if (s.indexOf('=') >= 0) {
                            String[] s2 = s.split("=");
                            valuesList.add(s2[0].trim());
                            namesList.add(s2[1].trim());
                        } else {
                            valuesList.add(s.trim());
                            namesList.add(s.trim());
                        }
                    }
                    theType = new ListChoiceType(nullable,
                            valuesList.toArray(),
                            namesList.toArray(),
                            theType);
                }
                break;
            }
            case Types.DOUBLE:
            case Types.REAL:
            case Types.FLOAT: {
                if (columnSize >= 0 && decimalDegits >= 0) {
                    double m = Math.pow(10, columnSize - decimalDegits);
                    double min = -m;
                    double max = +m;
                    ConfigNode[] configNodes = dao.getFieldConfig(this.beanFieldName);
                    for (int i = 0; i < configNodes.length; i++) {
                        ConfigNode configNode = configNodes[i];
                        min = configNode.getAttributeDouble("min", min);
                        max = configNode.getAttributeDouble("max", max);
                    }
                    theType = new DoubleType(nullable, min, max);
                } else {
                    theType = new DoubleType(nullable);
                }
                if (values != null) {
                    ArrayList valuesList = new ArrayList();
                    ArrayList namesList = new ArrayList();
                    String[] valuesArray = values.split(";");
                    for (int i = 0; i < valuesArray.length; i++) {
                        String s = valuesArray[i];
                        if (s.indexOf('=') >= 0) {
                            String[] s2 = s.split("=");
                            valuesList.add(new Double(Double.parseDouble(s2[0].trim())));
                            namesList.add(s2[1].trim());
                        } else {
                            valuesList.add(new Double(Double.parseDouble(s.trim())));
                            namesList.add(s.trim());
                        }
                    }
                    theType = new ListChoiceType(nullable,
                            valuesList.toArray(),
                            namesList.toArray(),
                            theType);
                }
                break;
            }
            case Types.BIGINT:
            case Types.INTEGER:
            case Types.SMALLINT:
            case Types.TINYINT: {
                int min = Integer.MIN_VALUE;
                int max = Integer.MAX_VALUE;
                if (columnSize >= 0) {
                    int m = (int) Math.pow(10, columnSize);
                    min = -m - 1;
                    max = m;
                }
                ConfigNode[] configNodes = dao.getFieldConfig(this.beanFieldName);
                for (int i = 0; i < configNodes.length; i++) {
                    ConfigNode configNode = configNodes[i];
                    min = configNode.getAttributeInt("min", min);
                    max = configNode.getAttributeInt("max", max);
                }
                theType = new IntType(nullable, min, max);
                if (values != null) {
                    ArrayList valuesList = new ArrayList();
                    ArrayList namesList = new ArrayList();
                    String[] valuesArray = values.split(";");
                    for (int i = 0; i < valuesArray.length; i++) {
                        String s = valuesArray[i];
                        if (s.indexOf('=') >= 0) {
                            String[] s2 = s.split("=");
                            valuesList.add(new Integer(Integer.parseInt(s2[0].trim())));
                            namesList.add(s2[1].trim());
                        } else {
                            valuesList.add(new Integer(Integer.parseInt(s.trim())));
                            namesList.add(s.trim());
                        }
                    }
                    theType = new ListChoiceType(nullable,
                            valuesList.toArray(),
                            namesList.toArray(),
                            theType);
                }
                break;
            }

            case Types.DECIMAL:
            case Types.NUMERIC: {
                if (decimalDegits == 0) {
                    theType = getDataTypeForSQLType(
                            nullable, Types.INTEGER, typeName, columnSize, bufferLength, decimalDegits, numRecRadix);
                } else {
                    theType = getDataTypeForSQLType(
                            nullable, Types.DOUBLE, typeName, columnSize, bufferLength, decimalDegits, numRecRadix);
                }
                break;
            }
            case Types.DATE: {
                theType = new DateType(nullable);
                break;
            }
            case Types.TIME: {
                theType = new TimeType(nullable, null, null);
                break;
            }
            case Types.TIMESTAMP: {
                theType = new DateTimeType(nullable);
                break;
            }
            case Types.BIT: {
                theType = new BooleanType(nullable);
                break;
            }
            case Types.JAVA_OBJECT: {
                if ("List".equalsIgnoreCase(typeName)) {
                    typeName = "java.util.List";
                }
                if (typeName == null) {
                    typeName = "java.lang.Object";
                }
                theType = new AnyTypeDelegate(typeName, nullable);
                break;
            }
            case Types.CLOB: {
                theType = new StringType(nullable);
                break;
            }
            case Types.BLOB: {
                theType = new BlobType(nullable);
                break;
            }
        }
        String prefix = tableName + ".";
        if (theType == null) {
            if (typeName.toUpperCase().startsWith("TIMESTAMP")) {
                getDAOInfo().getLog().warning(prefix + beanFieldName + " : FIXED : Unknown Type " + typeName + " : " + sqlType + " : fixed to TIMESTAMP");
                theType = new DateTimeType(nullable);
            }
        }
        if (theType == null) {
            getDAOInfo().getLog().warning(prefix + beanFieldName + " : Unknown Type " + typeName + " : " + sqlType);
            theType = new AnyType(Object.class, nullable);
        }
        return theType;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getColumnNameCoted() {
        try {
            return dao.getProjectInfo().getRdbmsSupport().coteIdentifier(dao, getColumnName());
        } catch (SQLException e) {
            return getColumnName();
        }
    }

    public String getColumnName2() {
        return JBGenUtils.replaceString(getColumnNameCoted(), "\"", "\\\"");
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getColumnBufferLength() {
        return bufferLenght;
    }

    public void setBufferLenght(int bufferLenght) {
        this.bufferLenght = bufferLenght;
    }

    public int getColumnDecimalDigits() {
        return decimalDegits;
    }

    public void setDecimalDegits(int decimalDegits) {
        this.decimalDegits = decimalDegits;
    }

    public int getColumnNumRecRadix() {
        return numRecRadix;
    }

    public void setNumRecRadix(int numRecRadix) {
        this.numRecRadix = numRecRadix;
    }

    public DBTableInfo getQuasiTable() {
        if (table == null) {
            return table;
        } else {
            return getDAOInfo().getTables()[0];
        }
    }

    public DBTableInfo getTable() {
        return table;
    }

    public void setTable(DBTableInfo table) {
        this.table = table;
    }

    public String getConverterFieldName() {
        return getBeanFieldName()+"__converter";
    }
    
    public String getBeanFieldName() {
        return beanFieldName;
    }

    public void setBeanFieldName(String beanFieldName) {
        this.beanFieldName = beanFieldName;
    }

    public String getBeanFieldConstant() {
        return beanFieldConstant;
    }

    public String getFullBeanFieldConstant() {
        return getDAOInfo().getDTOName() + "." + beanFieldConstant;
    }

    public String getFullBeanFieldConstant2() {
        return getDAOInfo().getFullDTOName() + "." + beanFieldConstant;
    }

    public void setBeanFieldConstant(String beanFieldConstant) {
        this.beanFieldConstant = beanFieldConstant;
    }

    public String getBeanFieldVar() {
        return beanFieldVar;
    }

    public void setBeanFieldVar(String beanFieldVar) {
        this.beanFieldVar = beanFieldVar;
    }

    public String getSQLDataTypeName() {
        DataType d = getSqlDataType();
        if (d instanceof AnyTypeDelegate) {
            return ((AnyTypeDelegate) d).getJavaClassName();
        }
        Class aClass = d.toJavaType();
        if(!dao.isJBGenOptionEnabled("option-no-autoboxing",false)){
            if (aClass.isPrimitive()) {
                if (aClass.equals(Integer.TYPE)) {
                    aClass = Integer.class;
                } else if (aClass.equals(Long.TYPE)) {
                    aClass = Long.class;
                } else if (aClass.equals(Double.TYPE)) {
                    aClass = Double.class;
                } else if (aClass.equals(Boolean.TYPE)) {
                    aClass = Boolean.class;
                } else if (aClass.equals(Short.TYPE)) {
                    aClass = Short.class;
                } else if (aClass.equals(Float.TYPE)) {
                    aClass = Float.class;
                } else if (aClass.equals(Byte.TYPE)) {
                    aClass = Byte.class;
                } else if (aClass.equals(Character.TYPE)) {
                    aClass = Character.class;
                }
            }
        }
        return aClass.getName();
    }

    public String getBusinessDataTypeName() {
        DataType d = getBusinessDataType();
        if (d instanceof AnyTypeDelegate) {
            return ((AnyTypeDelegate) d).getJavaClassName();
        }
        Class aClass = d.toJavaType();
        if(!dao.isJBGenOptionEnabled("option-no-autoboxing",false)){
            if (aClass.isPrimitive()) {
                if (aClass.equals(Integer.TYPE)) {
                    aClass = Integer.class;
                } else if (aClass.equals(Long.TYPE)) {
                    aClass = Long.class;
                } else if (aClass.equals(Double.TYPE)) {
                    aClass = Double.class;
                } else if (aClass.equals(Boolean.TYPE)) {
                    aClass = Boolean.class;
                } else if (aClass.equals(Short.TYPE)) {
                    aClass = Short.class;
                } else if (aClass.equals(Float.TYPE)) {
                    aClass = Float.class;
                } else if (aClass.equals(Byte.TYPE)) {
                    aClass = Byte.class;
                } else if (aClass.equals(Character.TYPE)) {
                    aClass = Character.class;
                }
            }
        }
        return aClass.getName();
    }

    public DataType getBusinessDataType() {
        return businessDataType;
    }

    public void setBusinessDataType(DataType businessDataType) {
        this.businessDataType = businessDataType;
    }

    public DataType getSqlDataType() {
        return sqlDataType;
    }

    public void setSqlDataType(DataType sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    public DataTypeConverterFactory getSqlConverterFactory() {
        return sqlConverterFactory;
    }

    public void setSqlConverterFactory(DataTypeConverterFactory sqlConverterFactory) {
        this.sqlConverterFactory = sqlConverterFactory;
    }

    public boolean isAutoIdentity() {
        return autoIdentity;
    }

    public void setAutoIdentity(boolean autoIdentity) {
        this.autoIdentity = autoIdentity;
    }

    public int getPkPosition() {
        return pkPosition;
    }

    public void setPkPosition(int pkPosition) {
        this.pkPosition = pkPosition;
        if (pkPosition >= 0) {
            if (!requiredOnInsert && !isAutoIdentity()) {
                requiredOnInsert = true;
            }
        }
        if (pkPosition < 0 && requiredOnInsert) {
            requiredOnInsert = false;
        }
    }

    public int getColumnPkKeySeq() {
        return pkKeySeq;
    }

    public void setPkKeySeq(int pkKeySeq) {
        this.pkKeySeq = pkKeySeq;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public boolean isRequiredOnInsert() {
        return requiredOnInsert && !isAutoIdentity();
    }

    public boolean isForbiddenOnInsert() {
        return forbiddenOnInsert
                && (getterImpl == null || FieldFormulaType.none.equals(getterImpl.getType()))
                && (creatorImpl == null || FieldFormulaType.none.equals(creatorImpl.getType()))
                ;
    }

    public boolean isForbiddenOnUpdate() {
        return forbiddenOnUpdate;
    }

    public boolean isForbiddenOnSearch() {
        return forbiddenOnSearch;
    }

    public void setRequiredOnInsert(boolean requiredOnInsert) {
        this.requiredOnInsert = requiredOnInsert;
    }

    public void setForbiddenOnInsert(boolean forbiddenOnInsert) {
        this.forbiddenOnInsert = forbiddenOnInsert;
    }

    public void setForbiddenOnUpdate(boolean forbiddenOnUpdate) {
        this.forbiddenOnUpdate = forbiddenOnUpdate;
    }

    public void setForbiddenOnSearch(boolean forbiddenOnSearch) {
        this.forbiddenOnSearch = forbiddenOnSearch;
    }

    public String getTableName() {
        return tableName;
    }

    public DAOInfo getDAOInfo() {
        return dao;
    }

    public FieldFormulaImpl getSetterImpl() {
        return setterImpl;
    }

    public FieldFormulaImpl getGetterImpl() {
        return getterImpl;
    }

    public FieldFormulaImpl getCreatorImpl() {
        return creatorImpl;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isFormulaImpl() {
        return getSetterImpl().getType() != FieldFormulaType.none || getGetterImpl().getType() != FieldFormulaType.none || getCreatorImpl().getType() != FieldFormulaType.none;
    }

    public boolean isPk() {
        return pkPosition >= 0;
    }

    public DBRelation getRelation() {
        return relation;
    }

    public void setRelation(DBRelation relation) {
        this.relation = relation;
    }

    public void setDao(DAOInfo dao) {
        this.dao = dao;
        this.sqlDataType = getDataTypeForSQLType(nullable,
                sqlType,
                typeName,
                columnSize,
                bufferLenght,
                decimalDegits,
                numRecRadix);
        this.businessDataType = sqlDataType; // defaultValue
        this.title = this.dao.getFieldTitle(this);
        DataType s = this.dao.getFieldSqlDataType(this, sqlDataType);
        if (s != null) {
            sqlDataType = s;
        }
        this.sqlConverterFactory = this.dao.getFieldConverterFactory(this);
        this.businessDataType = this.dao.getFieldDataType(this, sqlDataType);
        if (this.businessDataType == null) {
            this.businessDataType = this.sqlConverterFactory == null ? sqlDataType : this.sqlConverterFactory.getConverter(getDAOInfo().getProjectInfo()).getBusinessDataType(sqlDataType);
        }
        // see later how to remove this
//        if(this.table==null){
//            if(this.getterImpl.getType()==FieldFormulaTypes.none){
//                throw new IllegalArgumentException("You should declare getterImpl for Field "+entityInfo.getBeanName()+"."+beanFieldName);
//            }
//        }


        this.requiredOnInsert = this.dao.isRequiredOnInsert(this, !sqlDataType.isNullable());
        this.forbiddenOnInsert = this.dao.isForbiddenOnInsert(this, false);
        this.forbiddenOnUpdate = this.dao.isForbiddenOnUpdate(this, false);
        this.forbiddenOnSearch = this.dao.isForbiddenOnSearch(this, false);
    }

    public void setCreatorImpl(FieldFormulaImpl creatorImpl) {
        this.creatorImpl = creatorImpl;
    }

    public void setGetterImpl(FieldFormulaImpl getterImpl) {
        this.getterImpl = getterImpl;
    }

    public void setSetterImpl(FieldFormulaImpl setterImpl) {
        this.setterImpl = setterImpl;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }

    public void addRelationLink(RelationLink r) {
        relationLinks.add(r);
    }

    public List<RelationLink> getRelationLinks() {
        return relationLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBColumn dbColumn = (DBColumn) o;

        if (autoIdentity != dbColumn.autoIdentity) return false;
        if (bufferLenght != dbColumn.bufferLenght) return false;
        if (columnSize != dbColumn.columnSize) return false;
        if (decimalDegits != dbColumn.decimalDegits) return false;
        if (forbiddenOnInsert != dbColumn.forbiddenOnInsert) return false;
        if (forbiddenOnSearch != dbColumn.forbiddenOnSearch) return false;
        if (forbiddenOnUpdate != dbColumn.forbiddenOnUpdate) return false;
        if (nullable != dbColumn.nullable) return false;
        if (numRecRadix != dbColumn.numRecRadix) return false;
        if (pkKeySeq != dbColumn.pkKeySeq) return false;
        if (pkPosition != dbColumn.pkPosition) return false;
        if (populationOrder != dbColumn.populationOrder) return false;
        if (requiredOnInsert != dbColumn.requiredOnInsert) return false;
        if (sqlType != dbColumn.sqlType) return false;
        if (beanFieldConstant != null ? !beanFieldConstant.equals(dbColumn.beanFieldConstant) : dbColumn.beanFieldConstant != null)
            return false;
        if (beanFieldName != null ? !beanFieldName.equals(dbColumn.beanFieldName) : dbColumn.beanFieldName != null)
            return false;
        if (beanFieldVar != null ? !beanFieldVar.equals(dbColumn.beanFieldVar) : dbColumn.beanFieldVar != null)
            return false;
        if (businessDataType != null ? !businessDataType.equals(dbColumn.businessDataType) : dbColumn.businessDataType != null)
            return false;
        if (cardinality != dbColumn.cardinality) return false;
        if (columnName != null ? !columnName.equals(dbColumn.columnName) : dbColumn.columnName != null) return false;
        if (creatorImpl != null ? !creatorImpl.equals(dbColumn.creatorImpl) : dbColumn.creatorImpl != null)
            return false;
        if (dao != null ? !dao.equals(dbColumn.dao) : dbColumn.dao != null) return false;
        if (getterImpl != null ? !getterImpl.equals(dbColumn.getterImpl) : dbColumn.getterImpl != null) return false;
        if (pkName != null ? !pkName.equals(dbColumn.pkName) : dbColumn.pkName != null) return false;
        if (relation != null ? !relation.equals(dbColumn.relation) : dbColumn.relation != null) return false;
        if (relationLinks != null ? !relationLinks.equals(dbColumn.relationLinks) : dbColumn.relationLinks != null)
            return false;
        if (!Arrays.equals(requiredFields, dbColumn.requiredFields)) return false;
        if (setterImpl != null ? !setterImpl.equals(dbColumn.setterImpl) : dbColumn.setterImpl != null) return false;
        if (sqlConverterFactory != null ? !sqlConverterFactory.equals(dbColumn.sqlConverterFactory) : dbColumn.sqlConverterFactory != null)
            return false;
        if (sqlDataType != null ? !sqlDataType.equals(dbColumn.sqlDataType) : dbColumn.sqlDataType != null)
            return false;
        if (table != null ? !table.equals(dbColumn.table) : dbColumn.table != null) return false;
        if (tableName != null ? !tableName.equals(dbColumn.tableName) : dbColumn.tableName != null) return false;
        if (title != null ? !title.equals(dbColumn.title) : dbColumn.title != null) return false;
        if (typeName != null ? !typeName.equals(dbColumn.typeName) : dbColumn.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (nullable ? 1 : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + sqlType;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + columnSize;
        result = 31 * result + bufferLenght;
        result = 31 * result + decimalDegits;
        result = 31 * result + numRecRadix;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (beanFieldName != null ? beanFieldName.hashCode() : 0);
        result = 31 * result + (beanFieldConstant != null ? beanFieldConstant.hashCode() : 0);
        result = 31 * result + (beanFieldVar != null ? beanFieldVar.hashCode() : 0);
        result = 31 * result + (businessDataType != null ? businessDataType.hashCode() : 0);
        result = 31 * result + (sqlDataType != null ? sqlDataType.hashCode() : 0);
        result = 31 * result + (sqlConverterFactory != null ? sqlConverterFactory.hashCode() : 0);
        result = 31 * result + (getterImpl != null ? getterImpl.hashCode() : 0);
        result = 31 * result + (setterImpl != null ? setterImpl.hashCode() : 0);
        result = 31 * result + (creatorImpl != null ? creatorImpl.hashCode() : 0);
        result = 31 * result + (requiredOnInsert ? 1 : 0);
        result = 31 * result + (forbiddenOnInsert ? 1 : 0);
        result = 31 * result + (forbiddenOnUpdate ? 1 : 0);
        result = 31 * result + (forbiddenOnSearch ? 1 : 0);
        result = 31 * result + (autoIdentity ? 1 : 0);
        result = 31 * result + pkPosition;
        result = 31 * result + pkKeySeq;
        result = 31 * result + (pkName != null ? pkName.hashCode() : 0);
        result = 31 * result + (dao != null ? dao.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (cardinality != null ? cardinality.hashCode() : 0);
        result = 31 * result + (relationLinks != null ? relationLinks.hashCode() : 0);
        result = 31 * result + populationOrder;
        result = 31 * result + (requiredFields != null ? requiredFields.hashCode() : 0);
        return result;
    }

    private String getCodeName() {
        return (dao == null ? "" : dao.getBeanName()) + "." + (beanFieldName == null ? "" : beanFieldName) + "/" + (tableName == null ? "" : tableName) + "." + (columnName == null ? "" : columnName);
    }

    public int compareTo(DBColumn o) {
        String s1 = getCodeName();
        String s2 = o.getCodeName();
        int x = s1.compareTo(s2);
        if (x == 0) {
            return new Integer(hashCode()).compareTo(o.hashCode());
        }
        return x;
    }

    public int getPopulationOrder() {
        return populationOrder;
    }

    public void setPopulationOrder(int populationOrder) {
        this.populationOrder = populationOrder;
    }

    public String[] getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(String[] requiredFields) {
        this.requiredFields = requiredFields;
    }

    public DAOFieldKind getFieldKind() {
        return fieldKind;
    }

    public void setFieldKind(DAOFieldKind fieldKind) {
        this.fieldKind = fieldKind;
    }
}

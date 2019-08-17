package org.vpc.neormf.jbgen.csharp.util;

import org.vpc.neormf.commons.types.*;
import org.vpc.neormf.jbgen.csharp.generators.types.CSharpDataTypeGenManager;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.SQLPattern;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.info.AbstractInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;

import java.sql.Timestamp;
import java.sql.Types;
import java.sql.Time;
import java.util.NoSuchElementException;

public class CSharpUtils {

    public static boolean isPrimitive(DataType type){
        return isPrimitive(type.toJavaType());
    }

    public static String toSqlDbType(int javaSqlType){
        switch(javaSqlType)
        {
            case Types.ARRAY:
                {
                    return "SqlDbType.NText";
                }
            case Types.BIGINT:
                {
                    return "SqlDbType.BigInt";
                }
            case Types.BINARY:
                {
                    return "SqlDbType.Binary";
                }
            case Types.BIT:
                {
                    return "SqlDbType.Bit";
                }
            case Types.BLOB:
                {
                    return "SqlDbType.VarBinary";
                }
            case Types.BOOLEAN:
                {
                    return "SqlDbType.Bit";
                }
            case Types.CHAR:
                {
                    return "SqlDbType.Char";
                }
            case Types.CLOB:
                {
                    return "SqlDbType.VarBinary";
                }
            case Types.DATALINK:
                {
                    return "SqlDbType.VarBinary";
                }
            case Types.DATE:
                {
                    return "SqlDbType.DateTime";
                }
            case Types.DECIMAL:
                {
                    return "SqlDbType.Decimal";
                }
            case Types.DISTINCT:
                {
                    return "SqlDbType.VarBinary";
                }
            case Types.DOUBLE:
                {
                    return "SqlDbType.Float";
                }
            case Types.FLOAT:
                {
                    return "SqlDbType.Float";
                }
            case Types.INTEGER:
                {
                    return "SqlDbType.Int";
                }
            case Types.JAVA_OBJECT:
                {
                    return "SqlDbType.Variant";
                }
            case Types.LONGVARBINARY:
                {
                    return "SqlDbType.VarBinary";
                }
            case Types.LONGVARCHAR:
                {
                    return "SqlDbType.NText";
                }
            case Types.NULL:
                {
                    return "SqlDbType.Variant";
                }
            case Types.NUMERIC:
                {
                    return "SqlDbType.Real";
                }
            case Types.OTHER:
                {
                    return "SqlDbType.Variant";
                }
            case Types.REF:
                {
                    return "SqlDbType.Variant";
                }
            case Types.SMALLINT:
                {
                    return "SqlDbType.SmallInt";
                }
            case Types.STRUCT:
                {
                    return "SqlDbType.Variant";
                }
            case Types.TIME:
                {
                    return "SqlDbType.DateTime";
                }
            case Types.TIMESTAMP:
                {
                    return "SqlDbType.Timestamp";
                }
            case Types.TINYINT:
                {
                    return "SqlDbType.TinyInt";
                }
            case Types.VARBINARY:
                {
                    return "SqlDbType.VarBinary";
                }
            case Types.VARCHAR:
                {
                    return "SqlDbType.VarChar";
                }
        }
        return "SqlDbType.Variant";
    }
    public static boolean isPrimitive(Class clz){
        return isPrimitive(getCSharpClassName(clz));
    }

    public static boolean isPrimitive(String typeName) {
        if ("int".equals(typeName)) {
            return true;
        } else if ("long".equals(typeName)) {
            return true;

        } else if ("float".equals(typeName)) {
            return true;

        } else if ("double".equals(typeName)) {
            return true;

        } else if ("char".equals(typeName)) {
            return true;

        } else if ("byte".equals(typeName)) {
            return true;

        } else if ("bool".equals(typeName)) {
            return true;

        } else if ("DateTime".equals(typeName)) {
            return true;

        } else if ("TimeSpan".equals(typeName)) {
            return true;

        } else {
            return false;
        }
    }

    public static String getCSharpClassName(Class clazz){
        if(clazz==String.class){
            return "String";
        }else if(Boolean.class==clazz || Boolean.TYPE==clazz){
            return "bool";
        }else if(Timestamp.class==clazz){
            return "DateTime";
        }else if(Time.class==clazz){
            return "TimeSpan";
        }else if(java.util.List.class==clazz){
            return "ArrayList";
        }else if(java.sql.Date.class==clazz){
            return "DateTime";
        }else if(java.util.Date.class==clazz){
            return "DateTime";
        }else if(clazz.isPrimitive()){
            return clazz.getName();
        }else {
            return clazz.getName();
        }
    }

    public static String objectToPrimitive(String value, Class type) {
        return objectToPrimitive(value,getCSharpClassName(type));
    }

    public static String objectToPrimitive(String value, String type) {
        return "((" + type + ")" + value + ")";
    }

    public static String primitiveToObject(String value, Class type) {
        return primitiveToObject(value,getCSharpClassName(type));
    }
    public static String primitiveToObject(String value, String type) {
        return value;
    }

    public static String getImportedCode(DataType dataType) {
        String s = CSharpDataTypeGenManager.getDatatypeGenerator(dataType.getClass()).toCode(dataType);
        s = JBGenUtils.replaceString(s, "org.vpc.neormf.commons.types.converters.", "");
        s = JBGenUtils.replaceString(s, "org.vpc.neormf.commons.types.", "");
        return s;
    }

    public static void decorateMethod(CSharpMethod javaMethod, JavaDoc.Decoration decoration) {
        JavaDoc javaDoc = new JavaDoc(javaMethod.getComments());
        int pos = javaDoc.indexOfDecoration(decoration.getType(), decoration.getName(), 0);
        if (pos >= 0) {
//            JavaDoc.Decoration oldDecoration=(JavaDoc.Decoration) javaDoc.getDocItems()[pos];
            javaDoc.removeDocItem(pos);
        }
        javaDoc.addDocItem(decoration);
        javaMethod.setComments(javaDoc.toString());
    }



    public static String getPreparedStatementSetterName(DBColumn c) {
        return "Set" + getPreparedStatementMethodSuffixName(c);
    }

    public static String getPreparedStatementGetterName(DBColumn c) {
        return "Get" + getPreparedStatementMethodSuffixName(c);
    }

    public static String getPreparedStatementMethodSuffixName(DBColumn c) {
        DataType dataType = c.getSqlDataType();
        if (dataType instanceof AbstractChoiceType) {
            dataType = ((AbstractChoiceType) dataType).getElementType();
        }
        if (dataType instanceof StringType) {
            return "String";
        } else if (dataType instanceof IntType) {
            return "Int";
        } else if (dataType instanceof DoubleType) {
            return "Double";
        } else if (dataType instanceof DateType) {
            return "Date";
        } else if (dataType instanceof DateTimeType) {
            return "Timestamp";
        } else if (dataType instanceof TimeType) {
            return "Time";
        } else if (dataType instanceof BooleanType) {
            return "Boolean";
        } else if (dataType instanceof BlobType) {
            return "BinaryStream";
        } else {
            throw new NoSuchElementException("Unknown type " + dataType);
        }
    }

    public static String businessGetterName(DBColumn c) {
        if (c.getBusinessDataType().toJavaType().equals(Boolean.TYPE)) {
            return method(c,"Is");
        } else {
            return method(c,"Get");
        }
    }

    public static String businessAttribute(DBColumn c) {
        return method(c,"");
    }

    public static String sqlGetterName(DBColumn c ) {
        if (c.getSqlDataType().toJavaType().equals(Boolean.TYPE)) {
            return method(c,"Is");
        } else {
            return method(c,"Get");
        }
    }

    public static String setterName(DBColumn c) {
        return method(c,"Set");
    }

    public static String method(String name){
       return Character.toUpperCase(name.charAt(0))+name.substring(1);
    }
    public static String method(DBColumn c, String prefix) {
        return prefix + getCSharpMethod(c.getColumnName());
    }

    public static String getCSharpMethod(String name) {
        return JBGenUtils.capitalize(JavaUtils.toJavaIdentifier(name, true));
    }


    public static String getDataFieldSourceCode(DAOInfo e, String fieldNameExpression) {
        return e.getDTOName() + ".INFO.GetField(" + fieldNameExpression + ")";
    }

    public static String getDataFieldSourceCode(DAOInfo e, DBColumn dbColumn) {
        return getDataFieldSourceCode(e,dbColumn.getFullBeanFieldConstant());
        //return getDataFieldSourceCode(e,e.getPropertyListName() + "." + JBGenUtils.getJavaConstant(dbColumn.getColumnName()));
    }




    public static String getPreparedStatementMethodSetterName(int type) {
        switch (type) {
            case Types.ARRAY:
                {
                    return "SetArray";
                }
            case Types.BIGINT:
                {
                    return "SetBigDecimal";
                }
            case Types.BINARY:
                {
                    return "SetBinaryStream";
                }
            case Types.BIT:
                {
                    return "SetBoolean";
                }
            case Types.BLOB:
                {
                    return "SetBlob";

                }
//            case Types.BOOLEAN:
//                {
//                    return "SetBoolean";
//                }
//            case Types.DATALINK:
//                {
//                    throw new IllegalArgumentException("Not supported DATALINK ");
//                }
            case Types.CHAR:
                {
                    return "SetString";
                }
            case Types.CLOB:
                {
                    return "SetClob";

                }
            case Types.DATE:
                {
                    return "SetDate";
                }
            case Types.DECIMAL:
                {
                    return "SetDouble";
                }
            case Types.DISTINCT:
                {
                    throw new IllegalArgumentException("Not supported DISTINCT");
                }
            case Types.DOUBLE:
                {
                    return "SetDouble";
                }
            case Types.FLOAT:
                {
                    return "SetFloat";
                }
            case Types.INTEGER:
                {
                    return "SetInt";
                }
            case Types.JAVA_OBJECT:
                {
                    return "SetObject";
                }
            case Types.LONGVARBINARY:
                {
                    return "SetBinaryStream";
                }
            case Types.LONGVARCHAR:
                {
                    return "SetBinaryStream";
                }
            case Types.NULL:
                {
                    return "SetNull";
                }
            case Types.NUMERIC:
                {
                    return "SetDouble";
                }
            case Types.REAL:
                {
                    return "SetDouble";
                }
            case Types.SMALLINT:
                {
                    return "SetInt";
                }
            case Types.STRUCT:
                {
                    return "SetObject";
                }
            case Types.TIME:
                {
                    return "SetTime";
                }
            case Types.TIMESTAMP:
                {
                    return "SetTimestamp";
                }
            case Types.TINYINT:
                {
                    return "SetInt";
                }
            case Types.VARBINARY:
                {
                    return "SetBinaryStream";
                }
            case Types.VARCHAR:
                {
                    return "SetString";
                }
        }
        throw new NoSuchElementException("No Type " + type);
    }

    public static String getPreparedStatementMethodGetterName(int type) {
        switch (type) {
            case Types.ARRAY:
                {
                    return "GetArray";
                }
            case Types.BIGINT:
                {
                    return "GetBigDecimal";
                }
            case Types.BINARY:
                {
                    return "GetBinaryStream";
                }
            case Types.BIT:
                {
                    return "GetBoolean";
                }
            case Types.BLOB:
                {
                    return "GetBlob";

                }
//            case Types.BOOLEAN:
//                {
//                    return "getBoolean";
//                }
//            case Types.DATALINK:
//                {
//                    throw new IllegalArgumentException("Not supported DATALINK ");
//                }
            case Types.CHAR:
                {
                    return "GetString";
                }
            case Types.CLOB:
                {
                    return "GetClob";

                }
            case Types.DATE:
                {
                    return "GetDate";
                }
            case Types.DECIMAL:
                {
                    return "GetDouble";
                }
            case Types.DISTINCT:
                {
                    throw new IllegalArgumentException("Not supported DISTINCT");
                }
            case Types.DOUBLE:
                {
                    return "GetDouble";
                }
            case Types.FLOAT:
                {
                    return "GetFloat";
                }
            case Types.INTEGER:
                {
                    return "GetInt";
                }
            case Types.JAVA_OBJECT:
                {
                    return "GetObject";
                }
            case Types.LONGVARBINARY:
                {
                    return "GetBinaryStream";
                }
            case Types.LONGVARCHAR:
                {
                    return "GetBinaryStream";
                }
            case Types.NULL:
                {
                    return "GetNull";
                }
            case Types.NUMERIC:
                {
                    return "GetDouble";
                }
            case Types.REAL:
                {
                    return "GetDouble";
                }
            case Types.SMALLINT:
                {
                    return "GetInt";
                }
            case Types.STRUCT:
                {
                    return "GetObject";
                }
            case Types.TIME:
                {
                    return "GetTime";
                }
            case Types.TIMESTAMP:
                {
                    return "GetTimestamp";
                }
            case Types.TINYINT:
                {
                    return "GetInt";
                }
            case Types.VARBINARY:
                {
                    return "GetBinaryStream";
                }
            case Types.VARCHAR:
                {
                    return "GetString";
                }
        }
        throw new NoSuchElementException("No Type " + type);
    }

    public static String getConvertCSharpToSQLExpression(DBColumn c, String expression, JBGenProjectInfo project) {
        return getConvertCSharpToSQLExpression(c, expression,null,project);
    }

    public static String getConvertCSharpToSQLExpression(DBColumn c, String expression,String converterName, JBGenProjectInfo project) {
        DataTypeConverterFactory factory = c.getSqlConverterFactory();
        if (factory == null) {
            return expression;
        } else {
            if(converterName==null){
                converterName=c.getConverterFieldName();
            }
            return "(" +
                    objectToPrimitive(converterName
                    + ".BusinessToSQL(" +
                    primitiveToObject(expression, factory.getConverter(project).getBusinessDataType(c.getSqlDataType()).toJavaType().getName()) + ")"
                            , factory.getConverter(project).getSQLDataType().toJavaType().getName())
                    +
                    ")";
        }
    }
    public static String getConvertSQLToCSharpExpression(DBColumn c, String expression, JBGenProjectInfo project) {
        return getConvertSQLToCSharpExpression(c,expression,null,project);
    }

    public static String getConvertSQLToCSharpExpression(DBColumn c, String expression,String converterName, JBGenProjectInfo project) {
        DataTypeConverterFactory factory = c.getSqlConverterFactory();
        if (factory == null) {
            return expression;
        } else {
            if(converterName==null){
                converterName=c.getConverterFieldName();
            }
            return "(" + objectToPrimitive(converterName
                    + ".SqlToBusiness(" +
                    primitiveToObject(expression, factory.getConverter(project).getSQLDataType().toJavaType().getName()) + ")"
                    , factory.getConverter(project).getBusinessDataType(c.getSqlDataType()).toJavaType())
                    +
                    ")";
        }
    }


    public static String getPreparedStatementMethodSuffixName(DataType dataType) {
        if (dataType instanceof AbstractChoiceType) {
            dataType = ((AbstractChoiceType) dataType).getElementType();
        }
        if (dataType instanceof StringType) {
            return "String";
        } else if (dataType instanceof IntType) {
            return "Int";
        } else if (dataType instanceof DoubleType) {
            return "Double";
        } else if (dataType instanceof DateType) {
            return "Date";
        } else if (dataType instanceof DateTimeType) {
            return "Timestamp";
        } else if (dataType instanceof TimeType) {
            return "Time";
        } else if (dataType instanceof BooleanType) {
            return "Boolean";
        } else {
            throw new NoSuchElementException("Unknown type " + dataType);
        }
    }



    public static String getPopulateSqlStatementParamsProviderArrayList(DBColumn dbColumn,
                                                                        String var_$sqlStatementParamsProviderArrayList$,
                                                                        String var_$statement$) {

        SQLPattern sqlString = JBGenUtils.getSearchSQL(dbColumn,true);
        SQLPattern.SQLCallParam[] ps = sqlString.getParams();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ps.length; i++) {
            SQLPattern.SQLCallParam p = ps[i];
            sb.append(var_$sqlStatementParamsProviderArrayList$).append(".Add(new SqlStatementParamsProvider(){\n");
            sb.append("  public int PopulateStatement(PreparedStatement statement,int startIndex) throws SQLException{\n");
            Integer type = null;
            DBColumn c = dbColumn.getDAOInfo().getColumnByFieldName(p.getName(), false);
            if (c == null) {
                JavaParam[] javaParams = dbColumn.getDAOInfo().getMethodGetDataExtraParams();
                int javaParamsIndex = -1;
                for (int j = 0; type == null && j < javaParams.length; j++) {
                    if (javaParams[j].getName().equals(p.getName())) {
                        javaParamsIndex = j;
                        try {
                            type = new Integer(JBGenUtils.toSqlType(Class.forName(javaParams[j].getType())));
                        } catch (ClassNotFoundException e) {
                            throw new IllegalArgumentException(e.toString());
                        }
                    }
                }
                if (type == null) {
                    throw new IllegalArgumentException("Unknown " + p.getName());
                }
                sb.append("    ").append(var_$statement$).append(".").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(javaParams[javaParamsIndex].getName()).append(");\n");
            } else {
                type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                sb.append("  ").append(var_$statement$).append(".").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",this.").append(c.getBeanFieldVar()).append(");\n");
            }
            sb.append("    return 1;\n");
            sb.append("  }\n");
            sb.append("});\n");
        }
        return sb.toString();
    }


//    public static String getLogErrorCSharpCode(AbstractInfo info,String module, String prepareExpression, String principalExpression, String logExpression, String throwableExpression) {
//        return getLogCSharpCode(info,"Error",module, prepareExpression, principalExpression, logExpression, throwableExpression);
//    }
//
//    public static String getLogDebugCSharpCode(AbstractInfo info,String module, String prepareExpression, String principalExpression, String logExpression, String throwableExpression) {
//        return getLogCSharpCode(info,"Debug",module, prepareExpression, principalExpression, logExpression, throwableExpression);
//    }

    public static String getLogCSharpCode(AbstractInfo info,String logType,String module, String prepareExpression, String principalExpression, String logExpression, String throwableExpression) {
        StringBuilder log = new StringBuilder();
        if (info.generateStandardOutputLog(module)) {
            //log.append("  //START Log \n");
            if (prepareExpression != null) {
                log.append(prepareExpression);
            }
            log.append("/**LOG**/Console.WriteLine(DateTime.Now+\" : <").append(logType).append("><\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
            //log.append("  //END Log \n");
        }
        if (info.generateLog4NetLog(module)) {
//            log.append("  //START Log \n");

            log.append("if(_log_.Is").append(logType).append("Enabled){\n");
            if (prepareExpression != null) {
                log.append(JBGenUtils.indent(prepareExpression));
            }
            log.append(JBGenUtils.indent("MDC.Set(\"mdc_user\","+principalExpression+");\n"));
            if (throwableExpression == null) {
                log.append(JBGenUtils.indent("_log_."+logType+"(" + logExpression + ");\n"));
            } else {
                log.append(JBGenUtils.indent("_log_."+logType+"("+ logExpression + "," + throwableExpression + ");\n"));
            }
            log.append("}\n");
//            log.append("  //END Log \n");
        }
        return log.toString();
    }


}

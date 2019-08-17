using System;
using System.Collections;
using System.Text;
using org.vpc.neormf.commons.jwrapper;
using org.vpc.neormf.commons.sql;
using org.vpc.neormf.commons.types;
/**
 * Util class for SQL functions library
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 20 avr. 2004 Time: 15:27:00
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.sql
{
	public class SQLUtils 
				 {
    public static String DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    private SQLUtils() {
    }


    public static int ToSqlType(DataType dataType) {
        return ToSqlType(dataType.ToImplType());
    }

    public static String LogQuery(String query, ArrayList parameters) {
        if (parameters != null && parameters.Count > 0) {
            StringBuilder sb = new StringBuilder();
            int max = query.Length;
            int paramPos = 0;
            for (int i = 0; i < max; i++) {
                char c = query[i];
                switch (c) {
                    case '?': {
                        Object o = parameters[paramPos];
                        if (o == null) {
                            sb.Append("null");
                        } else if (o is String) {
                            sb.Append(SqlStringVal((String) o));
                        } else if (o is int || o is long || o is double || o is float) {
                            sb.Append(Convert.ToString(o));
                        } else if (o is DateTime) {
                            sb.Append(SqlTimestampVal((DateTime) o));
                        } else if (o is DateTime) {
                            sb.Append(SqlTimestampVal((((DateTime) o))));
                        } else {
                            sb.Append("?<Unknown " + o + ">");
                        }
                        paramPos++;
                        break;
                    }
                    default: {
                        sb.Append(c);
						break;
                    }
                }
            }
            return sb.ToString();
        } else {
            return query;
        }
    }

    public static String LogQuery(String query) {
        return query;
    }

    public static int ToSqlType(Type javaType) {
        if (javaType.IsSubclassOf(typeof(String))) {
            return Types.VARCHAR;
        } else if (javaType.IsSubclassOf(typeof(int))) {
            return Types.INTEGER;
        } else if (javaType.IsSubclassOf(typeof(long))) {
            return Types.BIGINT;
        } else if (javaType.IsSubclassOf(typeof(double))) {
            return Types.DOUBLE;
        } else if (javaType.IsSubclassOf(typeof(float))) {
            return Types.FLOAT;
        } else if (javaType.IsSubclassOf(typeof(byte))) {
            return Types.TINYINT;
        } else if (javaType.IsSubclassOf(typeof(DateTime))) {
            return Types.DATE;
        } else if (javaType.IsSubclassOf(typeof(DateTime))) {
            return Types.TIME;
        } else if (javaType.IsSubclassOf(typeof(DateTime))) {
            return Types.TIMESTAMP;
        } else {
            throw new NoSuchElementException(javaType.Name);
        }
    }

    public static String createSQLParamList(int size) {
    	StringBuilder sb = new StringBuilder("?");
        for (int i = 1; i < size; i++) {
            sb.Append(",?");
        }
        return sb.ToString();
    }

    public static int toSQLType(String sqlTypeName) {
        sqlTypeName = sqlTypeName.ToUpper();
        if ("ARRAY".Equals(sqlTypeName)) {
            return Types.ARRAY;
        } else if ("BIT".Equals(sqlTypeName)) {
            return Types.BIT;
        } else if ("TINYINT".Equals(sqlTypeName)) {
            return Types.TINYINT;
        } else if ("SMALLINT".Equals(sqlTypeName)) {
            return Types.SMALLINT;
        } else if ("INTEGER".Equals(sqlTypeName)) {
            return Types.INTEGER;
        } else if ("BIGINT".Equals(sqlTypeName)) {
            return Types.BIGINT;
        } else if ("FLOAT".Equals(sqlTypeName)) {
            return Types.FLOAT;
        } else if ("REAL".Equals(sqlTypeName)) {
            return Types.REAL;
        } else if ("DOUBLE".Equals(sqlTypeName)) {
            return Types.DOUBLE;
        } else if ("NUMERIC".Equals(sqlTypeName)) {
            return Types.NUMERIC;
        } else if ("DECIMAL".Equals(sqlTypeName)) {
            return Types.DECIMAL;
        } else if ("CHAR".Equals(sqlTypeName)) {
            return Types.CHAR;
        } else if ("VARCHAR".Equals(sqlTypeName)) {
            return Types.VARCHAR;
        } else if ("LONGVARCHAR".Equals(sqlTypeName)) {
            return Types.LONGVARCHAR;
        } else if ("DATE".Equals(sqlTypeName)) {
            return Types.DATE;
        } else if ("TIME".Equals(sqlTypeName)) {
            return Types.TIME;
        } else if ("TIMESTAMP".Equals(sqlTypeName)) {
            return Types.TIMESTAMP;
        } else if ("BINARY".Equals(sqlTypeName)) {
            return Types.BINARY;
        } else if ("VARBINARY".Equals(sqlTypeName)) {
            return Types.VARBINARY;
        } else if ("LONGVARBINARY".Equals(sqlTypeName)) {
            return Types.LONGVARBINARY;
        } else if ("NULL".Equals(sqlTypeName)) {
            return Types.NULL;
        } else if ("OTHER".Equals(sqlTypeName)) {
            return Types.OTHER;
        } else if ("JAVA_OBJECT".Equals(sqlTypeName)) {
            return Types.JAVA_OBJECT;
        } else if ("DISTINCT".Equals(sqlTypeName)) {
            return Types.DISTINCT;
        } else if ("STRUCT".Equals(sqlTypeName)) {
            return Types.STRUCT;
        } else if ("ARRAY".Equals(sqlTypeName)) {
            return Types.ARRAY;
        } else if ("BLOB".Equals(sqlTypeName)) {
            return Types.BLOB;
        } else if ("CLOB".Equals(sqlTypeName)) {
            return Types.CLOB;
        } else if ("REF".Equals(sqlTypeName)) {
            return Types.REF;
//        } else if ("DATALINK".Equals(sqlTypeName)) {
//            return Types.DATALINK;
//        } else if ("BOOLEAN".Equals(sqlTypeName)) {
//            return Types.BOOLEAN;
        } else {
            throw new NoSuchElementException(sqlTypeName);
        }
    }

    public static String GetTypeName(int type) {
		throw new Exception("Not implemented yet");
//        Field[] f = Types.class.getFields();
//        for (int i = 0; i < f.length; i++) {
//            try {
//                if (f[i].getInt(null) == type) {
//                    return f[i].getName();
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e.ToString());
//            }
//        }
//        return "<<UNKNOWN:" + type + ">>";
    }

    public static int getTypeByName(String name) {
		throw new Exception("Not implemented yet");
//		try 
//		{
//            return Types.class.getField(name).getInt(null);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e.ToString());
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e.ToString());
//        }
    }

    public static String GetPreparedStatementMethodSetterName(int type) {
        switch (type) {
            case Types.ARRAY: {
                return "setArray";
            }
            case Types.BIGINT: {
                return "setBigDecimal";
            }
            case Types.BINARY: {
                return "setBinaryStream";
            }
            case Types.BIT: {
                return "setBoolean";
            }
            case Types.BLOB: {
                return "setBlob";

            }
//            case Types.BOOLEAN:
//                {
//                    return "setBoolean";
//                }
//            case Types.DATALINK:
//                {
//                    throw new IllegalArgumentException("Not supported DATALINK ");
//                }
            case Types.CHAR: {
                return "setString";
            }
            case Types.CLOB: {
                return "setClob";

            }
            case Types.DATE: {
                return "setDate";
            }
            case Types.DECIMAL: {
                return "setDouble";
            }
            case Types.DISTINCT: {
                throw new Exception("Not supported DISTINCT");
            }
            case Types.DOUBLE: {
                return "setDouble";
            }
            case Types.FLOAT: {
                return "setFloat";
            }
            case Types.INTEGER: {
                return "setInt";
            }
            case Types.JAVA_OBJECT: {
                return "setObject";
            }
            case Types.LONGVARBINARY: {
                return "setBinaryStream";
            }
            case Types.LONGVARCHAR: {
                return "setBinaryStream";
            }
            case Types.NULL: {
                return "setNull";
            }
            case Types.NUMERIC: {
                return "setDouble";
            }
            case Types.REAL: {
                return "setDouble";
            }
            case Types.SMALLINT: {
                return "setInt";
            }
            case Types.STRUCT: {
                return "setObject";
            }
            case Types.TIME: {
                return "setTime";
            }
            case Types.TIMESTAMP: {
                return "setTimestamp";
            }
            case Types.TINYINT: {
                return "setInt";
            }
            case Types.VARBINARY: {
                return "setBinaryStream";
            }
            case Types.VARCHAR: {
                return "setString";
            }
        }
        throw new NoSuchElementException("No Type " + type);
    }

    public static String GetPreparedStatementMethodGetterName(int type) {
        switch (type) {
            case Types.ARRAY: {
                return "getArray";
            }
            case Types.BIGINT: {
                return "getBigDecimal";
            }
            case Types.BINARY: {
                return "getBinaryStream";
            }
            case Types.BIT: {
                return "getBoolean";
            }
            case Types.BLOB: {
                return "getBlob";

            }
//            case Types.BOOLEAN:
//                {
//                    return "getBoolean";
//                }
//            case Types.DATALINK:
//                {
//                    throw new IllegalArgumentException("Not supported DATALINK ");
//                }
            case Types.CHAR: {
                return "getString";
            }
            case Types.CLOB: {
                return "getClob";

            }
            case Types.DATE: {
                return "getDate";
            }
            case Types.DECIMAL: {
                return "getDouble";
            }
            case Types.DISTINCT: {
                throw new Exception("Not supported DISTINCT");
            }
            case Types.DOUBLE: {
                return "getDouble";
            }
            case Types.FLOAT: {
                return "getFloat";
            }
            case Types.INTEGER: {
                return "getInt";
            }
            case Types.JAVA_OBJECT: {
                return "getObject";
            }
            case Types.LONGVARBINARY: {
                return "getBinaryStream";
            }
            case Types.LONGVARCHAR: {
                return "getBinaryStream";
            }
            case Types.NULL: {
                return "getNull";
            }
            case Types.NUMERIC: {
                return "getDouble";
            }
            case Types.REAL: {
                return "getDouble";
            }
            case Types.SMALLINT: {
                return "getInt";
            }
            case Types.STRUCT: {
                return "getObject";
            }
            case Types.TIME: {
                return "getTime";
            }
            case Types.TIMESTAMP: {
                return "getTimestamp";
            }
            case Types.TINYINT: {
                return "getInt";
            }
            case Types.VARBINARY: {
                return "getBinaryStream";
            }
            case Types.VARCHAR: {
                return "getString";
            }
        }
        throw new NoSuchElementException("No Type " + type);
    }

    public static String SqlStringVal(String val) {
        if (val == null) {
            return "NULL";
        }
        StringBuilder sb = new StringBuilder();
        sb.Append("'");
        for (int i = 0; i < val.Length; i++) {
            char c = val[i];
            if (c == '\'') {
                sb.Append("'");
            }
            sb.Append(c);
        }
        sb.Append("'");
        return sb.ToString();
    }

    public static String SqlTimestampVal(DateTime timestampVal) {
//        if (timestampVal == null) {
//            return "NULL";
//        }
        String val = DATE_FORMAT.format(timestampVal);
        StringBuilder sb = new StringBuilder();
        sb.Append("'");
        for (int i = 0; i < val.Length; i++) {
            char c = val[i];
            if (c == '\'') {
                sb.Append("'");
            }
            sb.Append(c);
        }
        sb.Append("'");
        return "TO_DATE(" + sb.ToString() + ",'" + DATE_FORMAT_PATTERN + "')";
    }

}
}

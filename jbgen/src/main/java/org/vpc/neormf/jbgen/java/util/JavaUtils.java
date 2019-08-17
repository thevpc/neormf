package org.vpc.neormf.jbgen.java.util;

import org.vpc.neormf.commons.util.Maps;
import org.vpc.neormf.commons.types.*;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.*;
import org.vpc.neormf.jbgen.java.types.JavaDataTypeGenManager;
import org.vpc.neormf.jbgen.java.generators.log.JavaLogManager;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.util.SQLPattern;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.AbstractInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;

import java.util.*;
import java.sql.Types;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 f�vr. 2006
 * Time: 22:11:18
 * To change this template use File | Settings | File Templates.
 */
public class JavaUtils {

    public static final Map JAVA_PRIMITIVE_TO_OBJECT_TYPES = Collections.unmodifiableMap(Maps.fill(new Hashtable(),
            new Object[]{
        "boolean",
        "byte",
        "char",
        "int",
        "long",
        "float",
        "double"
    },
            new Object[]{
        "Boolean",
        "Byte",
        "Character",
        "Integer",
        "Long",
        "Float",
        "Double"
    }));
    public static final Set JAVA_KEYWORDS = Collections.unmodifiableSet(new TreeSet(Arrays.asList(new String[]{
        "int",
        "long",
        "double",
        "float",
        "byte",
        "char",
        "boolean",
        "public",
        "static",
        "void",
        "private",
        "final",
        "transient",
        "class",
        "interface",
        "switch",
    })));

    

    public static Class getPrimitiveClass(Class c) {
        if (Long.class.equals(c)) {
            return Long.TYPE;

        } else if (Integer.class.equals(c)) {
            return Integer.TYPE;

        } else if (Double.class.equals(c)) {
            return Double.TYPE;

        } else if (Float.class.equals(c)) {
            return Float.TYPE;

        } else if (Byte.class.equals(c)) {
            return Byte.TYPE;

        } else if (Character.class.equals(c)) {
            return Character.TYPE;

        } else if (Boolean.class.equals(c)) {
            return Boolean.TYPE;
        } else {
            return c;
        }
    }

    public static String toJavaIdentifier(String n, boolean capitalize) {
        if (n.toLowerCase().equals(n) || n.toUpperCase().equals(n)) {
            StringBuilder sb = new StringBuilder();
            char last = 0;
            for (int i = 0; i < n.length(); i++) {
                char c = n.charAt(i);
                if (c == '_') {
                    if (last == '_') {
                        sb.append('_');
                    }
                } else {
                    if (last == '_') {
                        c = Character.toUpperCase(c);
                    } else {
                        c = Character.toLowerCase(c);
                    }
                    sb.append(c);
                }
                last = c;
            }
            if (capitalize) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
            return sb.toString();
        } else {
            if (capitalize) {
                StringBuilder sb = new StringBuilder(n);
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                return sb.toString();
            } else {
                return n;
            }
        }
    }

    public static String getJavaMethod(String name) {
        return toJavaIdentifier(name, true);
    }

    public static String getJavaPackage(String name) {
        String s = toJavaIdentifier(name, false).toLowerCase();
        if (JAVA_KEYWORDS.contains(s)) {
            return "the" + s;
        }
        return s;
    }

    public static String getJavaBean(String name) {
        String s = toJavaIdentifier(name, false);
        return s;
    }

    public static String getJavaClass(String name) {
        String s = toJavaIdentifier(name, true);
        return s;
    }

    public static String getJavaVar(String name) {
        String s = toJavaIdentifier(name, false);
        if (JAVA_KEYWORDS.contains(s)) {
            return "the" + JBGenUtils.capitalize(s);
        }
        return s;
    }

    public static Class primitiveToObjectClass(Class clazz) {
        if (clazz == Boolean.TYPE) {
            return Boolean.class;
        } else if (clazz == Integer.TYPE) {
            return Integer.class;
        } else if (clazz == Long.TYPE) {
            return Long.class;
        } else if (clazz == Float.TYPE) {
            return Float.class;
        } else if (clazz == Short.TYPE) {
            return Short.class;
        } else if (clazz == Double.TYPE) {
            return Double.class;
        } else if (clazz == Character.TYPE) {
            return Character.class;
        } else if (clazz == Byte.TYPE) {
            return Byte.class;
        }
        return clazz;
    }

    public static String toJavaComments(String body) {
        StringTokenizer st = new StringTokenizer(body, "\n", true);
        StringBuilder sb = new StringBuilder("/**\n* ");
        String s = null;
        while (st.hasMoreTokens()) {
            s = st.nextToken();
            if ("\n".equals(s)) {
                char[] c = new char[2];
                Arrays.fill(c, ' ');
                c[0] = '*';
                sb.append("\n").append(new String(c));
            } else {
                sb.append(s);
            }
        }
        sb.append("\n");
        sb.append("*/\n");
        return sb.toString();
    }

    public static String objectToPrimitive(String value, String type) {
        if ("int".equals(type)) {
            return "((Integer)" + value + ").intValue()";
        } else if ("double".equals(type)) {
            return "((Double)" + value + ").doubleValue()";
        } else if ("float".equals(type)) {
            return "((Float)" + value + ").floatValue()";
        } else if ("boolean".equals(type)) {
            return "((Boolean)" + value + ").booleanValue()";
        } else {
            return "((" + type + ")" + value + ")";
        }
    }

    public static String primitiveToObject(String value, String type) {
        if ("int".equals(type)) {
            return "new Integer(" + value + ")";
        } else if ("double".equals(type)) {
            return "new Double(" + value + ")";
        } else if ("float".equals(type)) {
            return "new Float(" + value + ")";
        } else if ("boolean".equals(type)) {
            return "((" + value + ")?Boolean.TRUE:Boolean.FALSE)";
        } else {
            return value;
        }
    }

    public static String getPreparedStatementMethodSetterName(int type) {
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
                throw new IllegalArgumentException("Not supported DISTINCT");
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

    public static String getPreparedStatementMethodGetterName(int type) {
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
                throw new IllegalArgumentException("Not supported DISTINCT");
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

    public static String getConvertJavaToSQLExpression(DBColumn c, String expression, JBGenProjectInfo project) {
        return getConvertJavaToSQLExpression(c, expression, null, project);
    }

    public static String getConvertJavaToSQLExpression(DBColumn c, String expression, String converterName, JBGenProjectInfo project) {
        DataTypeConverterFactory factory = c.getSqlConverterFactory();
        if (factory == null) {
            return expression;
        } else {
            if (converterName == null) {
                converterName = c.getConverterFieldName();
            }
            return "(" +
                    objectToPrimitive(converterName + ".businessToSQL(" +
                    primitiveToObject(expression, factory.getConverter(project).getBusinessDataType(c.getSqlDataType()).toJavaType().getName()) + ")", factory.getConverter(project).getSQLDataType().toJavaType().getName()) +
                    ")";
        }
    }

    public static String getConvertSQLToJavaExpression(DBColumn c, String expression, JBGenProjectInfo project) {
        return getConvertSQLToJavaExpression(c, expression, null, project);
    }

    public static String getConvertSQLToJavaExpression(DBColumn c, String expression, String converterName, JBGenProjectInfo project) {
        DataTypeConverterFactory factory = c.getSqlConverterFactory();
        if (factory == null) {
            return expression;
        } else {
            if (converterName == null) {
                converterName = c.getConverterFieldName();
            }
            return "(" + objectToPrimitive(converterName + ".sqlToBusiness(" +
                    primitiveToObject(expression, factory.getConverter(project).getSQLDataType().toJavaType().getName()) + ")", factory.getConverter(project).getBusinessDataType(c.getSqlDataType()).toJavaType().getName()) +
                    ")";
        }
    }

    public static String getImportedCode(DataType dataType) {
        String s = JavaDataTypeGenManager.getDatatypeGenerator(dataType.getClass()).toCode(dataType);
        s = JBGenUtils.replaceString(s, "org.vpc.neormf.commons.types.converters.", "");
        s = JBGenUtils.replaceString(s, "org.vpc.neormf.commons.types.", "");
        return s;
    }

    public static String getPopulateStatementCode(DBColumn column,
            SQLPattern pattern,
            String statementVarName,
            String[] imports,
            String[] sourceFolders,
            boolean sqlColumns) throws IllegalArgumentException {
        DAOInfo entityInfo = column.getDAOInfo();
        SQLPattern.SQLCallParam[] sqlCallParams = pattern.getParams();
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < sqlCallParams.length; i++) {
            SQLPattern.SQLCallParam p = sqlCallParams[i];
            if (p.isReturnParam()) {
                //TODO take into consideration converter ??
                body.append("  ").append(statementVarName).append(".registerOutParameter(").append(p.getPos()).append(",java.sql.Types.").append(JBGenUtils.getSQLTypeName(column.getSqlType())).append(");\n");
            } else if (p.isInParam()) {
                Integer type = null;
                DBColumn c = entityInfo.getColumnByFieldName(p.getName(), false);
                if (c == null) {
                    JavaParam[] javaParams = entityInfo.getMethodGetDataExtraParams();
                    int javaParamsIndex = -1;
                    for (int j = 0; type == null && j < javaParams.length; j++) {
                        if (javaParams[j].getName().equals(p.getName())) {
                            javaParamsIndex = j;
                            try {
                                type = new Integer(JBGenUtils.toSqlType(Class.forName(getFullyQualifiedTypeName(javaParams[j].getType(),
                                        imports,
                                        sourceFolders))));
                            } catch (ClassNotFoundException e) {
                                throw new IllegalArgumentException(e.toString());
                            }
                        }
                    }
                    if (type == null) {
                        throw new IllegalArgumentException("Unknown " + p.getName());
                    }
                    body.append("  ").append(statementVarName).append(".").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(javaParams[javaParamsIndex].getName()).append(");\n");
                } else {
                    if (!sqlColumns) {
                        type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                        body.append("  ").append(statementVarName).append(".").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",this.").append(c.getBeanFieldVar()).append(");\n");
                    }
                }
            } else {
            // do nothing
            }
        }
        return body.toString();
    }

    public static void decorateMethod(JavaMethod javaMethod, JavaDoc.Decoration decoration) {
        JavaDoc javaDoc = new JavaDoc(javaMethod.getComments());
        int pos = javaDoc.indexOfDecoration(decoration.getType(), decoration.getName(), 0);
        if (pos >= 0) {
//            JavaDoc.Decoration oldDecoration=(JavaDoc.Decoration) javaDoc.getDocItems()[pos];
            javaDoc.removeDocItem(pos);
        }
        javaDoc.addDocItem(decoration);
        javaMethod.setComments(javaDoc.toString());
    }

    public static JavaMethod createMethodCloseConnection(JBGenProjectInfo moduleInfo) {
        StringBuilder body = new StringBuilder();
        body.append("if(connection!=null){\n").append("  try{ \n").append("    connection.close();\n").append("  }catch(Exception e){\n").append("    throw new EJBException(\"Could not close connection : \"+e);\n").append("  }\n").append("}");
        return new JavaMethod("_closeConnection_", "void", new JavaParam[]{new JavaParam("connection", "java.sql.Connection", null)}, "private",
                new String[]{"EJBException"}, null,
                body);

    }

    public static JavaMethod createMethodOpenConnection(JBGenProjectInfo moduleCodeStyle) {
        StringBuilder body = new StringBuilder();
        body.append("try{\n").append("  javax.naming.InitialContext ic = new javax.naming.InitialContext();\n").append("  javax.sql.DataSource ds = (javax.sql.DataSource) ic.lookup(\"").append(moduleCodeStyle.getDataSourceName()).append("\");\n").append("  return ds.getConnection();\n").append("}catch(Exception e){\n").append("   throw new EJBException(\"Could not open connection : \"+e);\n").append("}");
        return new JavaMethod("_openConnection_", "java.sql.Connection", null, "private",
                new String[]{"EJBException"}, null,
                body);

    }

    public static void generateEntityGetHomeMethod(DAOInfo entityInfo, JavaClassSource theClass) {
        String fieldName = entityInfo.getEntityHomeNameVarName();
        JavaField javaField = theClass.getField(fieldName);
        if (javaField == null) {
            theClass.addImport(entityInfo.getDataPackage() + ".*");
            theClass.addImport(entityInfo.getEntityPackage() + ".*");
            theClass.addField(new JavaField(fieldName, entityInfo.getEntityHomeName(), null, "private", null));
            JavaMethod m = new JavaMethod("get" + entityInfo.getEntityHomeName(), entityInfo.getEntityHomeName(), null, "private",
                    new String[]{"NamingException"}, null,
                    "if(" + entityInfo.getEntityHomeNameVarName() + "==null){\n" +
                    "    EjbSessionBusinessDelegate ejbClient=new EjbSessionBusinessDelegate();\n" +
                    "    ejbClient.connect(\"localhost\",-1,null,null,\"" + entityInfo.getProjectInfo().getModuleName() + "\",\"" + entityInfo.getProjectInfo().getTargetApplicationServer() + "\");\n" +
                    "    " + entityInfo.getEntityHomeNameVarName() + "=(" + entityInfo.getEntityHomeName() + ") ejbClient.getHome(" + entityInfo.getEntityHomeName() + ".class);\n" +
                    "}\n" +
                    "return " + entityInfo.getEntityHomeNameVarName() + ";");
            theClass.addMethod(m);
//            declareMethod(m,entityInfo,theClass);
        }
    }

    public static void generateSessionRemote(BOInfo sessionInfo, JavaClassSource theClass) {
        String fieldName = sessionInfo.getSessionHomeNameVarName();
        JavaField javaField = theClass.getField(fieldName);
        if (javaField == null) {
            theClass.addImport(sessionInfo.getSessionPackage() + ".*");
            theClass.addField(new JavaField(fieldName, sessionInfo.getSessionHomeName(), null, "private", null));
            JavaMethod m = new JavaMethod("get" + sessionInfo.getSessionRemoteName(), sessionInfo.getSessionRemoteName(), null, "private",
                    new String[]{"NamingException", "RemoteException", "CreateException"}, null,
                    "if(" + sessionInfo.getSessionHomeNameVarName() + "==null){\n" +
                    "    EjbSessionBusinessDelegate ejbClient=new EjbSessionBusinessDelegate();\n" +
                    "    ejbClient.connect(\"localhost\",-1,null,null,\"" + sessionInfo.getProjectInfo().getModuleName() + "\",\"" + sessionInfo.getProjectInfo().getTargetApplicationServer() + "\");\n" +
                    "    " + sessionInfo.getSessionHomeNameVarName() + "=(" + sessionInfo.getSessionHomeName() + ") ejbClient.getHome(" + sessionInfo.getSessionHomeName() + ".class);\n" +
                    "}\n" +
                    "return " + sessionInfo.getSessionHomeNameVarName() + ".create();");
            theClass.addMethod(m);
//            declareMethod(m,entityInfo,theClass);
        }
    }

    public static void generateGetDAOMethod(DAOInfo entityInfo, JavaClassSource theClass) {
        theClass.addImport(entityInfo.getDataPackage() + ".*");
        theClass.addImport(entityInfo.getDAOPackage() + ".*");
        String methodLinkName = "get" + entityInfo.getDAOName();
        String varLinkName = entityInfo.getDAONameVarName();
        JavaField javaField = theClass.getField(varLinkName);
        if (javaField == null) {
            theClass.addField(new JavaField(varLinkName, entityInfo.getDAOName(), null, "private", null));

            JavaMethod m = new JavaMethod(methodLinkName, entityInfo.getDAOName(), null, "private",
                    new String[]{}, null,
                    "if(" + entityInfo.getDAONameVarName() + "==null){\n" +
                    "    " + varLinkName + " =new " + entityInfo.getDAOName() + "();\n" +
                    "}\n" +
                    "return " + varLinkName + ";\n");
            theClass.addMethod(m);
        }
    }

    public static String getPopulateSqlStatementParamsProviderArrayList(DBColumn dbColumn,
            String var_sqlStatementParamsProviderArrayList,
            String var_statement) {

        SQLPattern sqlString = JBGenUtils.getSearchSQL(dbColumn,true);
        SQLPattern.SQLCallParam[] ps = sqlString.getParams();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ps.length; i++) {
            SQLPattern.SQLCallParam p = ps[i];
            sb.append(var_sqlStatementParamsProviderArrayList).append(".add(new SqlStatementParamsProvider(){\n");
            sb.append("  public int populateStatement(PreparedStatement statement,int startIndex) throws SQLException{\n");
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
                sb.append("    ").append(var_statement).append(".").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(javaParams[javaParamsIndex].getName()).append(");\n");
            } else {
                type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                sb.append("  ").append(var_statement).append(".").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",this.").append(c.getBeanFieldVar()).append(");\n");
            }
            sb.append("    return 1;\n");
            sb.append("  }\n");
            sb.append("});\n");
        }
        return sb.toString();
    }

    public static String getFullyQualifiedTypeName(String typeName, String[] imports, String[] sourcepath) {
        String initialTypeName = typeName;
        StringBuilder arrayBrackets = new StringBuilder();

        while (typeName.endsWith("[]")) {
            arrayBrackets.append("[]");
            typeName = typeName.substring(0, typeName.length() - 2);
        }

        if (JAVA_PRIMITIVE_TO_OBJECT_TYPES.keySet().contains(typeName)) {
            return typeName + arrayBrackets;
        }
        if (typeName.indexOf('.') > 0) {
            return typeName + arrayBrackets;
        }
        try {
            Class.forName("java.lang." + typeName);
            return "java.lang." + typeName + arrayBrackets;
        } catch (ClassNotFoundException e) {
        }
        if (imports != null) {
            for (int i = 0; i < imports.length; i++) {
                String importItem = imports[i];
                int x = importItem.lastIndexOf('.');
                String impPkg = importItem.substring(0, x);
                String impCls = importItem.substring(x + 1);
                String full = null;
                if ("*".equals(impCls)) {
                    full = impPkg + "." + typeName;
                } else if (impCls.equals(typeName)) {
                    return importItem + arrayBrackets;
                } else {
                    continue;
                }
                try {
                    Class.forName(full);
                    return full + arrayBrackets;
                } catch (ClassNotFoundException e) {
                }
                if (sourcepath != null) {
                    for (int j = 0; j < sourcepath.length; j++) {
                        String n = sourcepath[j] + "/" + full.replace('.', '/');
                        if (new File(n + ".java").exists()) {
                            return full + arrayBrackets;
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Could not resolve class " + initialTypeName);
    }

    public static String getPreparedStatementSetterExpression(DBColumn c,String psVarName,int posVarName,String value) {
        return getPreparedStatementSetterExpression(c,psVarName,String.valueOf(posVarName),value);
    }
    
    public static String getPreparedStatementSetterExpression(DBColumn c,String psVarName,String posVarName,String value) {
        String setter=null;
        DataType dataType = c.getSqlDataType();
        if (dataType instanceof AbstractChoiceType) {
            dataType = ((AbstractChoiceType) dataType).getElementType();
        }
        if (dataType instanceof StringType) {
            setter="String";
        } else if (dataType instanceof IntType) {
            setter="Int";
        } else if (dataType instanceof DoubleType) {
            setter="Double";
        } else if (dataType instanceof DateType) {
            setter="Date";
        } else if (dataType instanceof DateTimeType) {
            setter="Timestamp";
        } else if (dataType instanceof TimeType) {
            setter="Time";
        } else if (dataType instanceof BooleanType) {
            setter="Boolean";
        } else if (dataType instanceof BlobType) {
            setter="BinaryStream";
        } else {
            throw new NoSuchElementException("Unknown type " + dataType);
        }
        setter= "set" + setter;

        value=c.getDAOInfo().getProjectInfo().getConvertJavaToSQLExpression(c, value);
        if (dataType instanceof BlobType) {
            return "SQLUtils.writeBlobData("+value+","+psVarName+","+posVarName+");";
        }else{
            return (psVarName+".")
                +(setter)
                +("(")
                +(posVarName)
                +(",")
                +(value)
                +");";
        }
    }
    
    public static String getPreparedStatementSetterName(DBColumn c) {
        String setter=null;
        DataType dataType = c.getSqlDataType();
        if (dataType instanceof AbstractChoiceType) {
            dataType = ((AbstractChoiceType) dataType).getElementType();
        }
        if (dataType instanceof StringType) {
            setter="String";
        } else if (dataType instanceof IntType) {
            setter="Int";
        } else if (dataType instanceof DoubleType) {
            setter="Double";
        } else if (dataType instanceof DateType) {
            setter="Date";
        } else if (dataType instanceof DateTimeType) {
            setter="Timestamp";
        } else if (dataType instanceof TimeType) {
            setter="Time";
        } else if (dataType instanceof BooleanType) {
            setter="Boolean";
        } else if (dataType instanceof BlobType) {
            setter="BinaryStream";
        } else {
            throw new NoSuchElementException("Unknown type " + dataType);
        }
        return setter;
    }
    
    public static String getCriteriaSetterName(DBColumn c) {
        return getCriteriaSetterName(c.getSqlDataType());
    }
    public static String getCriteriaSetterName(DataType dataType) {
        String setter=null;
        if (dataType instanceof AbstractChoiceType) {
            dataType = ((AbstractChoiceType) dataType).getElementType();
        }
        if (dataType instanceof StringType) {
            setter="String";
        } else if (dataType instanceof IntType) {
            setter="Int";
        } else if (dataType instanceof DoubleType) {
            setter="Double";
        } else if (dataType instanceof DateType) {
            setter="Date";
        } else if (dataType instanceof DateTimeType) {
            setter="Timestamp";
        } else if (dataType instanceof TimeType) {
            setter="Time";
        } else if (dataType instanceof BooleanType) {
            setter="Boolean";
        } else if (dataType instanceof BlobType) {
            setter="BlobData";
        } else {
            setter="AnyData";
//        } else {
//            throw new NoSuchElementException("Unknown type " + dataType);
        }
        return "set"+setter;
    }

    public static String getPreparedStatementGetterExpression(DBColumn c, String rsVarName, int indexExpression) {
        return getPreparedStatementGetterExpression(c, rsVarName, String.valueOf(indexExpression));
    }

    public static String getPreparedStatementGetterExpression(DBColumn c, String rsVarName, String indexExpression) {
        DataType dataType = c.getSqlDataType();
        if (dataType instanceof AbstractChoiceType) {
            dataType = ((AbstractChoiceType) dataType).getElementType();
        }
        String rs_get = null;
        String rs_suffix = null;
        if (dataType instanceof BlobType) {

        } else {
            if (dataType instanceof StringType) {
                rs_suffix = "String";
            } else if (dataType instanceof IntType) {
                rs_suffix = "Int";
            } else if (dataType instanceof DoubleType) {
                rs_suffix = "Double";
            } else if (dataType instanceof DateType) {
                rs_suffix = "Date";
            } else if (dataType instanceof DateTimeType) {
                rs_suffix = "Timestamp";
            } else if (dataType instanceof TimeType) {
                rs_suffix = "Time";
            } else if (dataType instanceof BooleanType) {
                rs_suffix = "Boolean";
            } else if (dataType instanceof BlobType) {
                rs_suffix = "Blob";
            } else {
                throw new NoSuchElementException("Unknown type " + dataType);
            }

            if (dataType instanceof BlobType) {
                rs_get = "new BlobData(" + rsVarName + ".get"+rs_suffix+"(" + indexExpression + "))";
            } else {
                rs_get = "" + rsVarName + ".get"+rs_suffix+"(" + indexExpression + ")";
            }

        }

        return c.getDAOInfo().getProjectInfo().getConvertSQLToJavaExpression(c,rs_get);
    }


    public static String businessGetterName(DBColumn c) {
        Class btype = c.getBusinessDataType().toJavaType();
        if (btype.equals(Boolean.TYPE) || btype.equals(Boolean.class)) {
            return method(c, "is");
        } else {
            return method(c, "get");
        }
    }

    public static String businessGetterName(String name, String type) {
        if ("boolean".equals(type) || "java.lang.Boolean".equals(type) || "Boolean".equals(type)) {
            return method(name, "is");
        } else {
            return method(name, "get");
        }
    }

    public static String sqlGetterName(DBColumn c) {
        Class btype = c.getSqlDataType().toJavaType();
        if (btype.equals(Boolean.TYPE) || btype.equals(Boolean.class)) {
            return method(c, "is");
        } else {
            return method(c, "get");
        }
    }

    public static String businessSetterName(DBColumn c) {
        return method(c, "set");
    }

    public static String businessSetterName(String name) {
        return method(name, "set");
    }

    public static String method(DBColumn c, String prefix) {
        return prefix + JavaUtils.getJavaMethod(c.getColumnName());
    }

    public static String method(String name, String prefix) {
        return prefix + JavaUtils.getJavaMethod(name);
    }

    public static String getDataFieldSourceCode(DAOInfo e, String fieldNameExpression) {
        return e.getDTOName() + "._METADATA.getField(" + fieldNameExpression + ")";
    }

    public static String getDataFieldSourceCode(DAOInfo e, DBColumn dbColumn) {
        return getDataFieldSourceCode(e, e.getDTOName() + "." + JBGenUtils.getJavaConstant(dbColumn.getColumnName()));
    }

    public static void initializeClassLog(AbstractInfo info,String module,JavaClassSource theClass) {
        if (info.generateStandardOutputLog(module)) {

        }else if(info.generateLog4JLog(module)){
            String logEntityCatagory = info.getLogCatagory();
            if (logEntityCatagory == null || logEntityCatagory.length() == 0) {
                logEntityCatagory = theClass.getName();
            }
            logEntityCatagory = "\"" + logEntityCatagory + "\"";
            theClass.addField(new JavaField("_log_", "org.apache.log4j.Logger", null, "private static final", "org.apache.log4j.Logger.getLogger(" + logEntityCatagory + ")"));
        }else if(info.generateJavaLog(module)){
            String logEntityCatagory = info.getLogCatagory();
            if (logEntityCatagory == null || logEntityCatagory.length() == 0) {
                logEntityCatagory = theClass.getName();
            }
            logEntityCatagory = "\"" + logEntityCatagory + "\"";
            theClass.addField(new JavaField("_log_", "java.util.logging.Logger", null, "private static final", "java.util.logging.Logger.getLogger(" + logEntityCatagory + ")"));
        }
    }
    public static String getLogJavaCode(AbstractInfo info, JavaLogManager.Level logType, String module, String prepareExpression, String principalExpression, String logExpression, String throwableExpression) {
        return getLogJavaCode(info, logType, module, prepareExpression, principalExpression, logExpression, throwableExpression,null,null);
    }
    public static String getLogJavaCode(AbstractInfo info, JavaLogManager.Level logType, String module, String prepareExpression, String principalExpression, String logExpression, String throwableExpression,String mdc_entity,String mdc_op) {
        StringBuilder log = new StringBuilder();
        if (info.generateStandardOutputLog(module)) {
            //log.append("  //START Log \n");
            if (prepareExpression != null) {
                log.append(prepareExpression);
            }
            StringBuilder message=new StringBuilder();
            message.append("new java.util.Date() ")
                    .append("+\"<").append(logType).append(">")
                    .append("<\"+").append(principalExpression).append("+\">")
                    .append(" : \"+").append(logExpression).append("")
                    ;
            if(throwableExpression!=null){
                message.append("+\" : [EXCEPTION] : \" + ").append(throwableExpression);
            }
            switch (logType){
                case INFO:
                case DEBUG:{
                    log.append("/**LOG**/System.out.println(").append(message).append(");\n");
                    break;
                }
                case ERROR:{
                    log.append("/**LOG**/System.err.println(").append(message).append(");\n");
                    if(throwableExpression!=null){
                        log.append("/**LOG**/").append(throwableExpression).append(".printStackTrace();\n");
                    }
                    break;
                }
            }
        //log.append("  //END Log \n");
        }else  if (info.generateLog4JLog(module)) {
//            log.append("  //START Log \n");
            String logTypeString=null;
            switch (logType){
                case DEBUG:{
                    logTypeString="DEBUG";
                    break;
                }
                case ERROR:{
                    logTypeString="ERROR";
                    break;
                }
                case INFO:{
                    logTypeString="INFO";
                    break;
                }
            }

            log.append("if(_log_.isEnabledFor(org.apache.log4j.Priority.").append(logTypeString).append(")){\n");
            if (mdc_entity!=null) {
                log.append(JBGenUtils.indent( "org.apache.log4j.MDC.put(\"mdc_entity\",\"" + mdc_entity + "\");\n"));
            }
            if (mdc_op!=null) {
                log.append(JBGenUtils.indent("org.apache.log4j.MDC.put(\"mdc_operation\",\"" + mdc_op + "\");\n"));
            }
            if (principalExpression!=null) {
                log.append(JBGenUtils.indent("org.apache.log4j.MDC.put(\"mdc_user\"," + principalExpression + ");\n"));
            }
            if (prepareExpression != null) {
                log.append(JBGenUtils.indent(prepareExpression));
            }

            if (throwableExpression == null) {
                log.append(JBGenUtils.indent("_log_." + logTypeString.toLowerCase() + "(" + logExpression + ");\n"));
            } else {
                log.append(JBGenUtils.indent("_log_." + logTypeString.toLowerCase() + "(" + logExpression + "," + throwableExpression + ");\n"));
            }
            log.append("}\n");
//            log.append("  //END Log \n");
        }else  if (info.generateJavaLog(module)) {
//            log.append("  //START Log \n");
            String logTypeString=null;
            switch (logType){
                case DEBUG:{
                    logTypeString="java.util.logging.Level.FINEST";
                    break;
                }
                case ERROR:{
                    logTypeString="java.util.logging.Level.SEVERE";
                    break;
                }
                case INFO:{
                    logTypeString="java.util.logging.Level.INFO";
                    break;
                }
            }

            log.append("if(_log_.isLoggable(").append(logTypeString).append(")){\n");
            if (prepareExpression != null) {
                log.append(JBGenUtils.indent(prepareExpression));
            }
            if (throwableExpression == null) {
                log.append(JBGenUtils.indent("_log_.log(" + logTypeString + "," + logExpression + ");\n"));
            } else {
                log.append(JBGenUtils.indent("_log_.log(" + logTypeString + "," + logExpression + "," + throwableExpression + ");\n"));
            }
            log.append("}\n");
        }
        return log.toString();
    }

    public static String method(String name){
       return name;
    }

    public static String toStringLiteral(String name){
        StringBuilder sb=new StringBuilder();
        sb.append('\"');
        int len=name.length();
        for(int i=0;i<len;i++){
            char c=name.charAt(i);
            switch (c){
                case '\n':{
                    sb.append("\\n");
                    break;
                }
                case '\f':{
                    sb.append("\\f");
                    break;
                }
                case '\b':{
                    sb.append("\\b");
                    break;
                }
                case '\t':{
                    sb.append("\\t");
                    break;
                }
                case '\r':{
                    sb.append("\\r");
                    break;
                }
                case '\"':{
                    sb.append("\\");
                    sb.append("\"");
                    break;
                }
                case '\\':{
                    sb.append("\\\\");
                    break;
                }
                default:{
                    sb.append(c);
                    break;
                }
            }
        }
        sb.append('\"');
       return sb.toString();
    }

    public static String getNormalizedTypeName(String type){
        if(type.startsWith("java.lang.") && type.indexOf('.',10)<0){
            return (type.substring(10));
        }else{
            return type;
        }
    }
}

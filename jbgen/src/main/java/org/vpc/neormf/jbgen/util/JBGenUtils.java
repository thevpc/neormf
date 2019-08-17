package org.vpc.neormf.jbgen.util;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:33:25
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public final class JBGenUtils {

    private JBGenUtils() {
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static Object castTo(Object o, Class clazz) throws ClassCastException {
        if (o != null && !clazz.isAssignableFrom(o.getClass())) {
            throw new ClassCastException("Expected class " + clazz.getName() + " but intercepted " + o.getClass() + " for object " + o);
        }
        return o;
    }

    public static String getPath(String parent, String child) {
        if (parent == null || parent.length() == 0) {
            return (child == null || child.length() == 0) ? "." : child;
        } else if (child == null || child.length() == 0) {
            return parent;
        } else {
            return new File(child).isAbsolute() ? child : (parent + File.separator + child);
        }
    }

    public static DBColumn[] concat(DBColumn[] a, DBColumn[] b) {
        DBColumn[] c = new DBColumn[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static boolean isBaseType(Class c) {
        return (Long.class.equals(c) ||
                Integer.class.equals(c) ||
                Double.class.equals(c) ||
                Float.class.equals(c) ||
                Byte.class.equals(c) ||
                Character.class.equals(c) ||
                Boolean.class.equals(c)
        );
    }

    public static boolean isNumericType(Class c) {
        return (
                Long.TYPE.equals(c) ||
                        Integer.TYPE.equals(c) ||
                        Double.TYPE.equals(c) ||
                        Float.TYPE.equals(c) ||
                        Byte.TYPE.equals(c) ||
                        (c.getSuperclass() != null && c.getSuperclass().equals(Number.class))
        );
    }

    public static String removeTail(String str, int count) {
        if (str.length() <= count) {
            return "";
        } else {
            return str.substring(0, str.length() - count);
        }
    }

    public static String getClassName(Class c) {
        String cn = c.getName();
        int x = cn.lastIndexOf('.');
        if (x > 0) {
            return cn.substring(x + 1);
        } else {
            return cn;
        }
    }

    public static String toRegexpPattern(String dosLikePattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dosLikePattern.length(); i++) {
            char c = dosLikePattern.charAt(i);
            switch (c) {
                case '*': {
                    sb.append(".*");
                    break;
                }
                case '?': {
                    sb.append(".");
                    break;
                }
                case '$': {
                    sb.append("\\$");
                    break;
                }
                case '.': {
                    sb.append("\\.");
                    break;
                }
                case ')': {
                    sb.append("\\)");
                    break;
                }
                case '(': {
                    sb.append("\\(");
                    break;
                }
                case '\\': {
                    sb.append("\\");
                    i++;
                    c = dosLikePattern.charAt(i);
                    sb.append(c);
                    break;
                }
                default: {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static boolean parseBoolean(String value) {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        } else if ("false".equalsIgnoreCase(value)) {
            return false;
        } else {
            throw new IllegalArgumentException("expected 'true' or 'false' for boolean value");
        }
    }

    public static String getJavaConstant(String n) {
        if (n.toLowerCase().equals(n)) {
            return n.toUpperCase();
        } else if (n.toUpperCase().equals(n)) {
            return n;
        } else {
            StringBuilder sb = new StringBuilder();
            char last = 0;
            for (int i = 0; i < n.length(); i++) {
                char c = n.charAt(i);
                if (i > 0 && Character.isUpperCase(c) && Character.isLowerCase(last)) {
                    sb.append("_");
                }
                sb.append(Character.toUpperCase(c));
                last = c;
            }
            return sb.toString();
        }
    }

    public static String capitalize(String n) {
        StringBuilder sb = new StringBuilder(n);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String decapitalize(String n) {
        StringBuilder sb = new StringBuilder(n);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String toEnglishSpelling(String n, boolean capitalize) {
        String pattern = JavaUtils.toJavaIdentifier(n, capitalize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            if (Character.isUpperCase(pattern.charAt(i))) {
                if (i > 0) {
                    sb.append(" ");
                }
                if (capitalize) {
                    sb.append(Character.toUpperCase(pattern.charAt(i)));
                }
            } else {
                sb.append(pattern.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String generate(String pattern, Map vars) {
        String s = pattern;
        if (vars != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder currentVar = null;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '{': {
                        if (currentVar != null) {
                            throw new IllegalArgumentException("Unexpected '{'");
                        }
                        currentVar = new StringBuilder();
                        break;
                    }
                    case '}': {
                        if (currentVar == null) {
                            throw new IllegalArgumentException("Unexpected '}'");
                        }
                        int fctPos = currentVar.toString().indexOf(':');
                        String vname = null;
                        String vfct = null;
                        if (fctPos >= 0) {
                            vname = currentVar.toString().substring(0, fctPos);
                            vfct = currentVar.toString().substring(fctPos + 1);
                        } else {
                            vname = currentVar.toString();
                        }
                        String vval;
                        if (vars.containsKey(vname)) {
                            vval = (String) vars.get(vname);
                        } else {
                            throw new IllegalArgumentException("Unknown VAR '" + currentVar + "'");
                        }
                        if (vval != null) {
                            if (vfct != null) {
                                if ("capital".equalsIgnoreCase(vfct)) {
                                    vval = JBGenUtils.capitalize(vval);
                                } else if ("bean".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaBean(vval);
                                } else if ("class".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaClass(vval);
                                } else if ("constant".equalsIgnoreCase(vfct)) {
                                    vval = JBGenUtils.getJavaConstant(vval);
                                } else if ("package".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaPackage(vval);
                                } else if ("var".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaVar(vval);
                                } else if ("lower".equalsIgnoreCase(vfct)) {
                                    vval = vval.toLowerCase();
                                } else if ("upper".equalsIgnoreCase(vfct)) {
                                    vval = vval.toUpperCase();
                                } else {
                                    throw new NoSuchElementException("only the following functions ares supported : capital,bean,constant,package,var,lower,upper");
                                }
                            }
                            sb.append(vval);
                        } else {
                            sb.append("");
                        }

                        currentVar = null;
                        break;
                    }
                    default: {
                        if (currentVar != null) {
                            currentVar.append(c);
                        } else {
                            sb.append(c);
                        }
                        break;
                    }
                }
            }
            s = sb.toString();
        }

        return s;
    }

    public static final int EQUAL = 0;
    public static final int CASE_DIFFERENT = 1;
    public static final int MISPELT = 2;
    public static final int DIFFERENT = 3;

    public static int distance(String s1, String s2) {
        if (s1.equals(s2)) {
            return EQUAL;
        } else if (s1.equalsIgnoreCase(s2)) {
            return CASE_DIFFERENT;
        } else if (
                JavaUtils.toJavaIdentifier(s1, false).equalsIgnoreCase(JavaUtils.toJavaIdentifier(s2, false))
                        ||
                        getJavaConstant(s1).equalsIgnoreCase(JBGenUtils.getJavaConstant(s2))
                ) {
            return MISPELT;
        } else {
            return DIFFERENT;
        }
    }

    public static void checkInPossibleValues(String value, Set possibleValues) throws NoSuchElementException {
        if (!possibleValues.contains(value)) {
            int currentDistance = JBGenUtils.DIFFERENT;
            String nearestString = null;
            for (Iterator iterator = possibleValues.iterator(); iterator.hasNext();) {
                String s = (String) iterator.next();
                int distance = JBGenUtils.distance(s, value);
                if (distance == JBGenUtils.EQUAL) {
                    break;
                }
                if (currentDistance > distance) {
                    currentDistance = distance;
                    nearestString = s;
                }
            }
            switch (currentDistance) {
                case JBGenUtils.CASE_DIFFERENT: {
                    throw new NoSuchElementException("Element " + value + " is mispelt! Verify char case. You may want " + nearestString);
                }
                case JBGenUtils.MISPELT: {
                    throw new NoSuchElementException("Element " + value + " is mispelt! You may want " + nearestString);
                }
                case JBGenUtils.DIFFERENT: {
                    throw new NoSuchElementException("Element " + value + " is unknown. Please use one of the following \n" + possibleValues);
                }
                default: {
                    // never
                }
            }
        }
    }

    public static String[] getStringArray(String pattern, String separators, boolean trim) {
        StringTokenizer st = separators == null ? new StringTokenizer(pattern) : new StringTokenizer(pattern, separators);
        ArrayList al = new ArrayList();
        if (trim) {
            while (st.hasMoreTokens()) {
                al.add(st.nextToken().trim());
            }
        } else {
            while (st.hasMoreTokens()) {
                al.add(st.nextToken());
            }
        }
        return (String[]) al.toArray(new String[al.size()]);
    }

//    public static String indent(String body) {
//        return indent(body, 2, false);
//    }
//
//    public static String indent(String body, int indent, boolean skipFirstLine) {
//        StringTokenizer st = new StringTokenizer(body, "\n", true);
//        StringBuffer sb = new StringBuffer();
//        boolean isFirstline = true;
//        while (st.hasMoreTokens()) {
//            String s = st.nextToken();
//            boolean doIndent = true;
//            if (isFirstline) {
//                if (skipFirstLine) {
//                    doIndent = false;
//                }
//                isFirstline = false;
//            }
//            if (doIndent) {
//                char[] c = new char[indent];
//                Arrays.fill(c, ' ');
//                s = new String(c) + s;
//            }
//            sb.append(s);
//        }
//        return sb.toString();
//    }

    public static String indent(String body) {
        return indent(body, 2);
    }

    public static String indent(String body, int count) {
        return indent(body, count, false, true);
    }

    public static String removeIndents(String body) {
        return removeIndents(body, getIndent(body));
    }

    public static String removeIndents(String body, int indentAmount) {
        int min = -1;
        StringTokenizer st = new StringTokenizer(body, "\n", true);
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            if (line.equals("\n")) {
                sb.append(line);
            } else {
                int max = Math.min(line.length(), indentAmount);
                int i = 0;
                while (i < max && " ".indexOf(line.charAt(i)) >= 0) {
                    i++;
                }
                line = line.substring(i);
                sb.append(line);
            }
        }
        return sb.toString();
    }

    public static int getIndent(String body) {
        int min = -1;
        StringTokenizer st = new StringTokenizer(body, "\n", false);
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            int max = line.length();
            int i = 0;
            while (i < max && " ".indexOf(line.charAt(i)) >= 0) {
                i++;
            }
            if (min == -1 || i < min) {
                min = i;
            }
        }
        return min < 0 ? 0 : min;
    }

    public static String indent(String body, int indent, boolean skipFirstLine, boolean indentReplace) {
        if (indentReplace) {
            body = removeIndents(body, getIndent(body));
        }
        StringTokenizer st = new StringTokenizer(body, "\n", true);
        StringBuilder sb = new StringBuilder();
        boolean isFirstline = true;
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            boolean doIndent = true;
            boolean wasNewLine = false;
            if (isFirstline) {
                if (skipFirstLine) {
                    doIndent = false;
                }
                isFirstline = false;
            }
            if (doIndent) {
                char[] c = new char[indent];
                Arrays.fill(c, ' ');
                boolean newLine = s.equals("\n");
                if (!newLine || (newLine && wasNewLine)) {
                    s = new String(c) + s;
                }
                wasNewLine = newLine;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public static int toSqlType(Class javaType) {
        if (javaType == String.class) {
            return Types.VARCHAR;
        } else if (javaType == Integer.class || javaType == Integer.TYPE) {
            return Types.INTEGER;
        } else if (javaType == Long.class || javaType == Long.TYPE) {
            return Types.BIGINT;
        } else if (javaType == Double.class || javaType == Double.TYPE) {
            return Types.DOUBLE;
        } else if (javaType == Float.class || javaType == Float.TYPE) {
            return Types.FLOAT;
        } else if (javaType == Byte.class || javaType == Byte.TYPE) {
            return Types.TINYINT;
        } else if (javaType == java.sql.Date.class) {
            return Types.DATE;
        } else if (javaType == java.sql.Time.class) {
            return Types.TIME;
        } else if (javaType == java.sql.Timestamp.class) {
            return Types.TIMESTAMP;
        } else {
            throw new NoSuchElementException(javaType.getName());
        }
    }

    public static int toSQLType(String sqlTypeName) {
        sqlTypeName = sqlTypeName.toUpperCase();
        if ("ARRAY".equals(sqlTypeName)) {
            return Types.ARRAY;
        } else if ("BIT".equals(sqlTypeName)) {
            return Types.BIT;
        } else if ("TINYINT".equals(sqlTypeName)) {
            return Types.TINYINT;
        } else if ("SMALLINT".equals(sqlTypeName)) {
            return Types.SMALLINT;
        } else if ("INTEGER".equals(sqlTypeName)) {
            return Types.INTEGER;
        } else if ("BIGINT".equals(sqlTypeName)) {
            return Types.BIGINT;
        } else if ("FLOAT".equals(sqlTypeName)) {
            return Types.FLOAT;
        } else if ("REAL".equals(sqlTypeName)) {
            return Types.REAL;
        } else if ("DOUBLE".equals(sqlTypeName)) {
            return Types.DOUBLE;
        } else if ("NUMERIC".equals(sqlTypeName)) {
            return Types.NUMERIC;
        } else if ("DECIMAL".equals(sqlTypeName)) {
            return Types.DECIMAL;
        } else if ("CHAR".equals(sqlTypeName)) {
            return Types.CHAR;
        } else if ("VARCHAR".equals(sqlTypeName)) {
            return Types.VARCHAR;
        } else if ("LONGVARCHAR".equals(sqlTypeName)) {
            return Types.LONGVARCHAR;
        } else if ("DATE".equals(sqlTypeName)) {
            return Types.DATE;
        } else if ("TIME".equals(sqlTypeName)) {
            return Types.TIME;
        } else if ("TIMESTAMP".equals(sqlTypeName)) {
            return Types.TIMESTAMP;
        } else if ("BINARY".equals(sqlTypeName)) {
            return Types.BINARY;
        } else if ("VARBINARY".equals(sqlTypeName)) {
            return Types.VARBINARY;
        } else if ("LONGVARBINARY".equals(sqlTypeName)) {
            return Types.LONGVARBINARY;
        } else if ("NULL".equals(sqlTypeName)) {
            return Types.NULL;
        } else if ("OTHER".equals(sqlTypeName)) {
            return Types.OTHER;
        } else if ("JAVA_OBJECT".equals(sqlTypeName) || "OBJECT".equals(sqlTypeName)) {
            return Types.JAVA_OBJECT;
        } else if ("DISTINCT".equals(sqlTypeName)) {
            return Types.DISTINCT;
        } else if ("STRUCT".equals(sqlTypeName)) {
            return Types.STRUCT;
        } else if ("ARRAY".equals(sqlTypeName)) {
            return Types.ARRAY;
        } else if ("BLOB".equals(sqlTypeName)) {
            return Types.BLOB;
        } else if ("CLOB".equals(sqlTypeName)) {
            return Types.CLOB;
        } else if ("REF".equals(sqlTypeName)) {
            return Types.REF;
//        } else if ("DATALINK".equals(sqlTypeName)) {
//            return Types.DATALINK;
//        } else if ("BOOLEAN".equals(sqlTypeName)) {
//            return Types.BOOLEAN;
        } else {
            throw new NoSuchElementException(sqlTypeName);
        }
    }

    public static boolean deleteFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                for (int i = 0; i < children.length; i++) {
                    deleteFile(children[i]);
                }
                return file.delete();
            } else {
                return file.delete();
            }
        }
        return false;
    }

    public static String getCompoundMethodName(String name, String type) {
        String s = getJavaConstant(name);
        int x = s.indexOf("_");
        if (x > 0) {
            String firstName = name.substring(0, x);
            String lastName = name.substring(x);
            if (getPrefixNames().contains(decapitalize(lastName))) {
                return name + capitalize(type);
            } else {
                return firstName + capitalize(type) + capitalize(lastName);
            }
        } else {
            return name + capitalize(type);
        }
    }

    public static final Set getPrefixNames() {
        return new TreeSet(Arrays.asList(new Object[]{
                "all"
        }));
    }

    public static String getSQLTypeName(int type) {
        Field[] f = Types.class.getFields();
        for (int i = 0; i < f.length; i++) {
            try {
                if (f[i].getInt(null) == type) {
                    return f[i].getName();
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.toString());
            }
        }
        return "<<UNKNOWN>>";
    }

    public static int getSQLTypeByName(String name) {
        try {
            return Types.class.getField(name).getInt(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.toString());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static File[] searchFilesByDosFilterName(String name, File rootFolder) {
        return searchFilesByRegExpName(toRegexpPattern(name), rootFolder);
    }

    public static File[] searchFilesByRegExpName(final String expression, File rootFolder) {
        FilenameFilter filenameFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(expression);
            }
        };
        return searchFiles(filenameFilter, rootFolder);
    }

    public static File[] searchFilesByName(final String name, File rootFolder) {
        FilenameFilter filenameFilter = new FilenameFilter() {
            public boolean accept(File dir, String fileName) {
                return fileName.equals(name);
            }
        };
        return searchFiles(filenameFilter, rootFolder);
    }

    public static File[] searchFiles(FilenameFilter filenameFilter, File rootFolder) {
        Stack stack = new Stack();
        stack.push(rootFolder);
        ArrayList files = new ArrayList();
        FileFilter directories = new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
        while (!stack.isEmpty()) {
            File parent = (File) stack.pop();
            File[] children = parent.listFiles(filenameFilter);
            for (int i = 0; i < children.length; i++) {
                files.add(children[i]);
            }
            children = parent.listFiles(directories);
            for (int i = 0; i < children.length; i++) {
                stack.add(children[i]);
            }
        }
        return (File[]) files.toArray(new File[files.size()]);
    }

    public static boolean hasContent(File file, String sourceContent, TLog tlog) throws IOException {
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        BufferedReader r1 = new BufferedReader(new FileReader(file));
        BufferedReader r2 = new BufferedReader(new StringReader(sourceContent));
        String s1 = null;
        String s2 = null;
        while ((s1 = r1.readLine()) != null) {
            s2 = r2.readLine();
            if (s2 == null || !s1.equals(s2)) {
                //TODO
                String log = System.getProperty("log.info.fileDiff");
                if (log != null) {
                    if ("true".equalsIgnoreCase(log)) {
                        tlog.info("DIFF <" + file.getName() + "> : '" + s1 + "' ==> '" + s2 + "'");
                    } else if ("full".equalsIgnoreCase(log)) {
                        tlog.info("DIFF <" + file.getCanonicalPath() + "> : '" + s1 + "' ==> '" + s2 + "'");
                    } else if ("false".equalsIgnoreCase(log)) {
                        // do nothing
                    }
                }
                return false;
            }
        }
        if ((s2 = r2.readLine()) != null) {
            tlog.info(">> DIFF <" + file.getName() + "> : new content is longer");
            return false;
        }
        return true;
    }

    public static boolean write(File file, String content, boolean forceGenerate, TLog tlog) throws IOException {
        file = file.getCanonicalFile();
        FileOutputStream fileOutputStream = null;
        try {
            if (!forceGenerate) {
                if (JBGenUtils.hasContent(file, content, tlog)) {
                    return false;
                }
            }
            file.getParentFile().mkdirs();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return true;
    }


    public static String[] split(String message, String delemeters, boolean trim) {
        ArrayList list = new ArrayList();
        if (message != null) {
            for (StringTokenizer tokenizer = new StringTokenizer(message, delemeters); tokenizer.hasMoreTokens();) {
                String s = tokenizer.nextToken();
                if (trim) {
                    s.trim();
                }
                list.add(s);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String replaceString(String str, String oldportion, String newPortion) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while ((i + oldportion.length()) <= str.length()) {
            if (str.substring(i, i + oldportion.length()).equals(oldportion)) {
                sb.append(newPortion);
                i += oldportion.length();
            } else {
                sb.append(str.charAt(i));
                i++;
            }
        }
        while (i < str.length()) {
            sb.append(str.charAt(i));
            i++;
        }
        return sb.toString();
    }

//    public static boolean isCodeColumn(DBColumn dbColumn){
//        return dbColumn.getPkPosition()>=0
//                // temporary work around... should i test with DB relations ???
//                || dbColumn.getBeanFieldName().endsWith("Id")
//                || dbColumn.getBeanFieldName().endsWith("Code");
//    }


    public static boolean isSearchableColumn(DBColumn column) throws IllegalArgumentException {
        FieldFormulaImpl fieldFormulaImpl = column.getGetterImpl();
        return
                fieldFormulaImpl.getType() == FieldFormulaType.none
                        ||
                        fieldFormulaImpl.getType() == FieldFormulaType.sqlQuery
                        ||
                        fieldFormulaImpl.getType() == FieldFormulaType.sqlView
                        ||
                        fieldFormulaImpl.getType() == FieldFormulaType.sqlFunction;
    }

    public static SQLPattern getSearchSQL(DBColumn column) throws IllegalArgumentException {
        return getSearchSQL(column, false);
    }

    public static String getSearchSQLString(DBColumn column, String forceLoadLiveFormulaExpression) throws IllegalArgumentException {
        String s1 = getSearchSQL(column, column.getGetterImpl(), true).getQuery();
        String s2 = getSearchSQL(column, column.getGetterImpl(), false).getQuery();
        if(s1.equals(s2)){
            return JavaUtils.toStringLiteral(s1);
        }else{
            return forceLoadLiveFormulaExpression+" ? "+JavaUtils.toStringLiteral(s1)+" : "+JavaUtils.toStringLiteral(s2);
        }
    }

    public static SQLPattern getSearchSQL(DBColumn column, boolean forceLive) throws IllegalArgumentException {
        return getSearchSQL(column, column.getGetterImpl(), forceLive);
    }

    public static SQLPattern getSearchSQL(DBColumn column, FieldFormulaImpl fieldFormulaImpl, boolean forceLive) throws IllegalArgumentException {
        SQLPattern colPatern;
        if (column.getTable() == null) {
            //when field added
            colPatern = new SQLPattern("(NULL)", column, true);
        } else {
            colPatern = new SQLPattern(column.getTable().getTableName2() + "." + column.getColumnNameCoted(), column, false);
        }


        SQLPattern q = null;
        if (fieldFormulaImpl.getType() == FieldFormulaType.sqlQuery) {
            q = new SQLPattern("(" + fieldFormulaImpl.getBody().getValue() + ")", column, true);
        } else if (fieldFormulaImpl.getType() == FieldFormulaType.sqlView) {
            //        if(fieldFormula.getBody())
            String[] viewName_fieldName = JBGenUtils.split(fieldFormulaImpl.getBody().getValue(), ".", false);
            DAOInfo entityInfo = column.getDAOInfo();
            if (viewName_fieldName.length != 2) {
                throw new IllegalArgumentException(entityInfo.getBeanName() + "." + column.getBeanFieldName() + " : expected ViewName.FieldName");
            }
            String viewName = viewName_fieldName[0];
            String viewFieldName = viewName_fieldName[1];
            String[] viewPk = null;
            if (viewName_fieldName[0].indexOf('(') > 0) {
                MethodDescList.MethodDesc methodDesc = new MethodDescList(viewName).getMethods()[0];
                viewName = methodDesc.getName();
                viewPk = methodDesc.getParams();
            }
            StringBuilder fullViewQuery = new StringBuilder("SELECT ");
            fullViewQuery
                    .append(viewName).append(".").append(viewFieldName)
                    .append(" FROM ")
                    .append(viewName)
                    .append(" WHERE ");
            DBColumn[] pk = entityInfo.getColumns(true, false, false);
            DBTableInfo view = entityInfo.getProjectInfo().getDBTableByName(viewName, true);
            DBColumn viewField = view.getColumn(viewFieldName);
            if (viewField == null) {
                throw new IllegalArgumentException(entityInfo.getBeanName() + "." + column.getBeanFieldName() + " : Unkown View field " + viewName + "." + viewFieldName);
            }
            if (viewPk != null && viewPk.length != pk.length) {
                String[] tablePkeys = new String[pk.length];
                for (int i = 0; i < pk.length; i++) {
                    tablePkeys[i] = pk[i].getColumnNameCoted();
                }
                throw new IllegalArgumentException(entityInfo.getBeanName() + "." + column.getBeanFieldName() + " : Bad View pkey (" + Arrays.asList(viewPk) + ") for table " + entityInfo.getTables()[0].getTableName() + " primary keys (" + Arrays.asList(tablePkeys) + ")");
            }
            for (int i = 0; i < pk.length; i++) {
                String viewPkName = viewPk == null ? pk[i].getColumnName() : viewPk[i];
                String viewPkNameCoted = viewPk == null ? pk[i].getColumnNameCoted() : viewPk[i];
                DBColumn viewColumn = view.getColumn(viewPkName);
                if (viewColumn == null) {
                    throw new NoSuchElementException(entityInfo.getBeanName() + "." + column.getBeanFieldName() + " : Unknown Primary key View Field : " + view.getTableName() + "." + viewPkName + " for " + column.getDAOInfo().getBeanName() + "." + column.getBeanFieldName());
                }
                if (i > 0) {
                    fullViewQuery.append(" AND ");
                }
                fullViewQuery.append(pk[i].getTable().getTableName2()).append(".").append(pk[i].getColumnNameCoted());
                fullViewQuery.append(" = ");
                fullViewQuery.append(viewName).append(".").append(viewPkNameCoted);
            }
            q = new SQLPattern("(" + fullViewQuery.toString() + ")", column, false);
        } else if (fieldFormulaImpl.getType() == FieldFormulaType.none) {
            if (column.getTable() == null) {
                //when field added
                q = new SQLPattern("(NULL)", column, true);
            } else {
                q = new SQLPattern(column.getTable().getTableNameCoted() + "." + column.getColumnNameCoted(), column, false);
            }
        } else if (fieldFormulaImpl.getType() == FieldFormulaType.sqlFunction) {
            q = new SQLPattern(fieldFormulaImpl.getBody().getValue(), column, true);
        } else {
            q = null;
        }
        if (column.getDAOInfo().isCheckQueries()) {
            if (q != null
                    && column.getGetterImpl().getType() != FieldFormulaType.none
                    && column.getGetterImpl().getType() != FieldFormulaType.sqlFunction
                    && column.getGetterImpl().getType() != FieldFormulaType.sqlCall
                    ) {
                Statement stmt = null;
                String fullQuery =
                        null;
                try {
                    Connection connection = column.getDAOInfo().getProjectInfo().getConnection();
                    stmt = connection.createStatement();
                    fullQuery = "Select "
                            + q.getQuery()
                            + " FROM "
                            + column.getDAOInfo().getTables()[0].getTableNameQ()
                            + " WHERE 1=2";
                } catch (SQLException e) {
                    column.getDAOInfo().getLog().error("Unable to test formula : " + column.getDAOInfo().getEntityName() + "." + column.getBeanFieldName() + " : " + e);
                    return q;
                }
                try {
                    stmt.executeQuery(fullQuery);
                } catch (SQLException e) {
                    throw new IllegalArgumentException(column.getDAOInfo().getEntityName() + "." + column.getBeanFieldName() + " : " + e
                            + "\nTEST QUERY : \n" + fullQuery);
                } finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            throw new IllegalArgumentException(column.getDAOInfo().getEntityName() + "." + column.getBeanFieldName() + " : " + e
                                    + "\nTEST QUERY : \n" + fullQuery);
                        }
                    }
                }
            }
        }
        if (q != null) {
            switch (column.getFieldKind()) {
                case REGULAR: {
                    q = colPatern;
                    break;
                }
                case LIVE_FORMULA: {
                    break;
                }
                case STORED_ONCE_FORMULA:
                case STORED_FORMULA: {
                    if (!forceLive) {
                        q = colPatern;
                    }
                    break;
                }
            }
        }

        return q;
    }


    public static String parseValue(String s, Properties vars) {
        if (s != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder currentVar = null;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '{': {
                        if (currentVar != null) {
                            throw new IllegalArgumentException("Unexpected '{'");
                        }
                        currentVar = new StringBuilder();
                        break;
                    }
                    case '}': {
                        if (currentVar == null) {
                            throw new IllegalArgumentException("Unexpected '}'");
                        }
                        int fctPos = currentVar.toString().indexOf(':');
                        String vname = null;
                        String vfct = null;
                        if (fctPos >= 0) {
                            vname = currentVar.toString().substring(0, fctPos);
                            vfct = currentVar.toString().substring(fctPos + 1);
                        } else {
                            vname = currentVar.toString();
                        }
                        String vval;
                        if (vars != null && vars.containsKey(vname)) {
                            vval = vars.getProperty(vname);
//                        } else if (getString(vname) != null) {
//                            vval = getString(vname);
                        } else {
                            throw new NoSuchElementException("Unknown VAR '" + currentVar + "' in value '" + s + "' with vars = " + (vars == null ? "{}" : vars.keySet().toString()));
                        }
                        if (vval != null) {
                            if (vfct != null) {
                                if ("capital".equalsIgnoreCase(vfct)) {
                                    vval = JBGenUtils.capitalize(vval);
                                } else if ("bean".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaBean(vval);
                                } else if ("class".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaClass(vval);
                                } else if ("constant".equalsIgnoreCase(vfct)) {
                                    vval = JBGenUtils.getJavaConstant(vval);
                                } else if ("package".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaPackage(vval);
                                } else if ("var".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaVar(vval);
                                } else if ("lower".equalsIgnoreCase(vfct)) {
                                    vval = vval.toLowerCase();
                                } else if ("upper".equalsIgnoreCase(vfct)) {
                                    vval = vval.toUpperCase();
                                } else {
                                    throw new NoSuchElementException("only the following functions ares supported : capital,bean,constant,package,var,lower,upper");
                                }
                            }
                            sb.append(vval);
                        } else {
                            sb.append("");
                        }

                        currentVar = null;
                        break;
                    }
                    default: {
                        if (currentVar != null) {
                            currentVar.append(c);
                        } else {
                            sb.append(c);
                        }
                        break;
                    }
                }
            }
            s = sb.toString();
        }

        return s;
    }

    public static boolean stringMatches(String string, String pattern, boolean caseInsensitive) {
        if (pattern.startsWith("regexp:")) {
            pattern = pattern.substring("regexp:".length());
        } else {
            pattern = toRegexpPattern(pattern);
        }
        if (!caseInsensitive) {
            return string.matches(pattern);
        } else {
            return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(string).matches();
        }
    }

    public static boolean stringMatches(String string, String pattern) {
        if (pattern.startsWith("regexp:")) {
            pattern = pattern.substring("regexp:".length());
        } else {
            pattern = toRegexpPattern(pattern);
        }
        return string.matches(pattern);
    }

    public static boolean getBoolean(String value, boolean defaultValue) {
        if (value != null && value.length() > 0) {
            if ("true".equalsIgnoreCase(value)) {
                return true;
            }
            if ("false".equalsIgnoreCase(value)) {
                return false;
            }
            throw new IllegalArgumentException("expected true/false");
        }
        return defaultValue;
    }

    public static List filterList(Collection base, String pattern, boolean caseInsensitive) {
        pattern = pattern.replace('+', '|');
        pattern = pattern.replace(',', '|');
        pattern = pattern.replace(';', '|');
        ArrayList good = new ArrayList();
        for (Iterator i = base.iterator(); i.hasNext();) {
            String s = (String) i.next();
            if (stringMatches(s, pattern, caseInsensitive)) {
                good.add(s);
            }
        }
        return good;
    }

    /**
     * retourne le path relatif
     *
     * @param parent
     * @param son
     * @return relative path
     */
    public static String getRelativePath(File parent, File son) {
        String parentPath;
        String sonPath;
        try {
            parentPath = parent.getCanonicalPath();
            sonPath = son.getCanonicalPath();
        } catch (IOException e) {
            parentPath = parent.getAbsolutePath();
            sonPath = son.getAbsolutePath();
        }
        if (sonPath.startsWith(parentPath)) {
            String p = sonPath.substring(parentPath.length());
            if (p.startsWith("/") || p.startsWith("\\")) {
                p = p.substring(1);
            }
            return p;
        }
        return null;
    }

    /**
     * retourne le nom du fichier (sans l'extension)
     *
     * @param f fichier
     * @return file name
     */
    public static String getFileName(File f) {
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i == 0) {
            return "";
        } else if (i > 0) {
            return s.substring(0, i);
        } else {
            return s;
        }
    }

    /**
     * The same as String.substring but will never throw Exception on unvalid indexes but return ""
     *
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static String substring(String string, int start, int end) {
        if (string == null || string.length() == 0) {
            return "";
        }
        if (start < 0) {
            start = 0;
        }
        if (end > string.length()) {
            end = string.length();
        }
        if (end <= start) {
            return "";
        } else {
            return string.substring(start, end);
        }
    }

    public static void askFileReadOnly(File file) {
        if (!file.canWrite()) {
            JPanel p = new JPanel(new BorderLayout());
            p.add(new JLabel("JBGgen could not write to the following file. please check it out before pressing continue button."), BorderLayout.NORTH);
            JTextField jTextField = new JTextField(file.getPath());
            p.add(jTextField, BorderLayout.CENTER);
            int r = JOptionPane.showConfirmDialog(null, p, "Readonly file", JOptionPane.OK_CANCEL_OPTION);
            if (r != JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

}

//
//switch(type){
//    case Types.ARRAY :{
//
//    }
//    case Types.BIGINT :{
//
//    }
//    case Types.BINARY :{
//
//    }
//    case Types.BIT  :{
//
//    }
//    case Types.BLOB  :{
//
//    }
//    case Types.BOOLEAN  :{
//
//    }
//    case Types.CHAR  :{
//
//    }
//    case Types.CLOB  :{
//
//    }
//    case Types.DATALINK  :{
//
//    }
//    case Types.DATE  :{
//
//    }
//    case Types.DECIMAL  :{
//
//    }
//    case Types.DISTINCT  :{
//
//    }
//    case Types.DOUBLE  :{
//
//    }
//    case Types.FLOAT  :{
//
//    }
//    case Types.INTEGER  :{
//
//    }
//    case Types.JAVA_OBJECT  :{
//
//    }
//    case Types.LONGVARBINARY  :{
//
//    }
//    case Types.LONGVARCHAR  :{
//
//    }
//    case Types.NULL  :{
//
//    }
//    case Types.NUMERIC  :{
//
//    }
//    case Types.REAL  :{
//
//    }
//    case Types.SMALLINT :{
//
//    }
//    case Types.STRUCT :{
//
//    }
//    case Types.TIME :{
//
//    }
//    case Types.TIMESTAMP :{
//
//    }
//    case Types.TINYINT :{
//
//    }
//    case Types.VARBINARY :{
//
//    }
//    case Types.VARCHAR :{
//
//    }
//}

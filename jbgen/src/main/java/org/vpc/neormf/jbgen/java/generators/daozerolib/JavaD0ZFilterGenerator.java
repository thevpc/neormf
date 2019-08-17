package org.vpc.neormf.jbgen.java.generators.daozerolib;

import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.JavaSourceCodeFieldsSwitcher;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.util.SQLPattern;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

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
class JavaD0ZFilterGenerator extends JBGenDAOGenerator {


    public JavaD0ZFilterGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        boolean bd = (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
//                && entityInfo.doGenerateTables("table")
                );
        boolean be = false;/*(
                entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")
                || (entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO) && bd)
                );*/
        if(!bd && !be){
            return false;
        }
        boolean nopk = entityInfo.getPrimaryKeys().length == 0;
        if (be && nopk  && entityInfo.isUpdatable()) {
            throw new RuntimeException("No primary key found for " + entityInfo.getBeanName());
        } else {

        }
        return !nopk && (bd || be);
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.addImport("java.util.*");
        theClass.addImport("java.sql.*");
        theClass.addImport(entityInfo.getDataPackage() + ".*");
        theClass.setName(entityInfo.getDataFilterName());
        theClass.setInterfaces(null);
        theClass.setPackage(entityInfo.getDAOPackage());
        theClass.addProperty("whereClause","String",null);
        theClass.addProperty("joins","String",null);
        theClass.addProperty("distinct","boolean",null);
        theClass.addProperty("minRowIndex","int","-1");
        theClass.addProperty("maxRowIndex","int","-1");
        theClass.addField(new JavaField("orderFields","ArrayList",null,"private","new ArrayList()"));
        theClass.addField(new JavaField("orderAsc","ArrayList",null,"private","new ArrayList()"));

        theClass.addField(new JavaField("params","HashMap",null,"private","new HashMap()"));

        theClass.addMethod(new JavaMethod("getOrderIterator","Iterator",null,"public",null,null,"return orderFields.iterator();"));
        theClass.addMethod(new JavaMethod("getOrderCount","int",null,"public",null,null,"return orderFields.size();"));

        theClass.addMethod(new JavaMethod("isOrderAscendent","boolean",new JavaParam[]{new JavaParam("index","int",null)}, "public",null,null,"return ((Boolean)orderAsc.get(index)).booleanValue();"));
        theClass.addMethod(new JavaMethod("setString",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","String",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.VARCHAR);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setDouble",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","double",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.DOUBLE);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setInt",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","int",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.INTEGER);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setBoolean",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","boolean",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.BOOLEAN);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setFloat",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","float",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.FLOAT);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setDate",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","java.sql.Date",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.DATE);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setTime",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","java.sql.Time",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.TIME);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setTimestamp",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","java.sql.Timestamp",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.TIMESTAMP);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("setLong",entityInfo.getDataFilterName(),
                new JavaParam[]{new JavaParam("pos","int",null),new JavaParam("value","long",null)}, "public",null,null,
                        "        SqlParam p = new SqlParam();\n" +
                        "        p.setPos(pos);\n" +
                        "        p.setValue(value);\n" +
                        "        p.setSqlType(Types.BIGINT);\n" +
                        "        params.put(new Integer(pos), p);\n" +
                        "        return this;"
        ));

        theClass.addMethod(new JavaMethod("populateStatement","int",new JavaParam[]{
                new JavaParam("ps","PreparedStatement",null),
                new JavaParam("startPosition","int",null)
        }, "public",new String[]{"SQLException"},null,
                        "        int count = 0;\n" +
                        "        for (Iterator i = params.entrySet().iterator(); i.hasNext();) {\n" +
                        "            Map.Entry entry = (Map.Entry) i.next();\n" +
                        "            count += ((SqlParam) entry.getValue()).populateStatement(ps, startPosition);\n" +
                        "        }\n" +
                        "        return count;"
        ));

        theClass.addMethod(new JavaMethod("merge",theClass.getName(),new JavaParam[]{
                new JavaParam("other",theClass.getName(),null),
        }, "public",null,null,
                                "        if (other == null) {\n" +
                                "            return this;\n" +
                                "        }\n" +
                                "        "+theClass.getName()+" newCriteria = new "+theClass.getName()+"();\n" +
                                "\n" +
                                "        StringBuffer sb = new StringBuffer();\n" +
                                "        if (whereClause != null) {\n" +
                                "            sb.append(whereClause);\n" +
                                "        }\n" +
                                "        if (other.whereClause != null) {\n" +
                                "            if (sb.length() > 0) {\n" +
                                "                sb.append(\" AND \");\n" +
                                "            }\n" +
                                "            sb.append(other.whereClause);\n" +
                                "        }\n" +
                                "        newCriteria.whereClause = sb.length() == 0 ? null : sb.toString();\n" +
                                "\n" +
                                "        sb = new StringBuffer();\n" +
                                "        if (joins != null) {\n" +
                                "            sb.append(joins);\n" +
                                "        }\n" +
                                "        if (other.joins != null) {\n" +
                                "            sb.append(\" \");\n" +
                                "            sb.append(other.joins);\n" +
                                "        }\n" +
                                "        newCriteria.joins = sb.length() == 0 ? null : sb.toString();\n" +
                                "        int pos = 1;\n" +
                                "        for (int i = 0; i < params.size(); i++) {\n" +
                                "            newCriteria.params.put(new Integer(pos), params.get(new Integer(i + 1)));\n" +
                                "            pos++;\n" +
                                "        }\n" +
                                "        for (int i = 0; i < other.params.size(); i++) {\n" +
                                "            SqlParam p = (SqlParam) other.params.get(new Integer(i + 1));\n" +
                                "            p.setPos(pos);\n" +
                                "            newCriteria.params.put(new Integer(pos), p);\n" +
                                "            pos++;\n" +
                                "        }\n" +
                                "        if (minRowIndex >= 0) {\n" +
                                "            newCriteria.minRowIndex = minRowIndex;\n" +
                                "        }\n" +
                                "        if (other.minRowIndex >= 0) {\n" +
                                "            newCriteria.minRowIndex = other.minRowIndex;\n" +
                                "        }\n" +
                                "        if (maxRowIndex >= 0) {\n" +
                                "            newCriteria.maxRowIndex = maxRowIndex;\n" +
                                "        }\n" +
                                "        if (other.maxRowIndex >= 0) {\n" +
                                "            newCriteria.maxRowIndex = other.maxRowIndex;\n" +
                                "        }\n" +
                                "        if (distinct || other.isDistinct()) {\n" +
                                "            newCriteria.distinct = other.distinct;\n" +
                                "        }\n" +
                                "        return newCriteria;"
        ));

        JavaClassSource sqlParam=new JavaClassSource();
        sqlParam.setName("SqlParam");
        sqlParam.setModifiers("private static");
        sqlParam.addProperty("pos","int",null);
        sqlParam.addProperty("name","String",null);
        sqlParam.addProperty("value","Object",null);
        sqlParam.addProperty("sqlType","int",null);
        sqlParam.addMethod(new JavaMethod("populateStatement","int",new JavaParam[]{
                new JavaParam("ps","PreparedStatement",null),
                new JavaParam("startPosition","int",null)
        }, "public",new String[]{"SQLException"},null,
                "int rpos=pos+startPosition-1;\n" +
                        "        if (value == null) {\n" +
                        "            ps.setNull(rpos, sqlType);\n" +
                        "            return 1;\n" +
                        "        }\n" +
                        "        switch (sqlType) {\n" +
                        "            case Types.VARCHAR:\n" +
                        "                {\n" +
                        "                    ps.setString(rpos, (String) value);\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.INTEGER:\n" +
                        "                {\n" +
                        "                    ps.setInt(rpos, ((Integer) value).intValue());\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.DOUBLE:\n" +
                        "                {\n" +
                        "                    ps.setDouble(rpos, ((Double) value).doubleValue());\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.SMALLINT:\n" +
                        "                {\n" +
                        "                    ps.setShort(rpos, ((Short) value).shortValue());\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.TIME:\n" +
                        "                {\n" +
                        "                    ps.setTime(rpos, ((Time) value));\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.TIMESTAMP:\n" +
                        "                {\n" +
                        "                    ps.setTimestamp(rpos, ((Timestamp) value));\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.BIGINT:\n" +
                        "                {\n" +
                        "                    ps.setLong(rpos, ((Long) value).longValue());\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.FLOAT:\n" +
                        "                {\n" +
                        "                    ps.setFloat(rpos, ((Float) value).floatValue());\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            case Types.DATE:\n" +
                        "                {\n" +
                        "                    ps.setDate(rpos, ((java.sql.Date) value));\n" +
                        "                    break;\n" +
                        "                }\n" +
                        "            default:\n" +
                        "                {\n" +
                        "                    throw new IllegalArgumentException(\"Unhandled sql type \" + sqlType);\n" +
                        "                }\n" +
                        "        }\n" +
                        "        return 1;"
        ));


        theClass.addMethod(new JavaMethod("orderBy",theClass.getName(),new JavaParam[]{
                new JavaParam("orderColumn","String",null),
                new JavaParam("asc","boolean",null)
        }, "public",null,null,
                        "        orderFields.add(orderColumn);\n" +
                        "        orderAsc.add(asc?Boolean.TRUE:Boolean.FALSE);\n" +
                        "        return this;"
        ));
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            String cst = entityInfo.getDTOName() + "." + columns[i].getBeanFieldConstant();
            theClass.addMethod(new JavaMethod(JavaUtils.method(columns[i].getBeanFieldName(),"orderBy"),theClass.getName(),new JavaParam[]{
                    new JavaParam("asc","boolean",null)
            }, "public",null,null,
                            "        orderBy("+cst+",asc);\n" +
                            "        return this;"
            ));
        }
        addMethodBuildCriteriaQBE(theClass,entityInfo);
        theClass.addSubClass(sqlParam);
        entityInfo.setGeneratedClass("Filter", theClass);

//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));

        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Primary Key Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    private JavaMethod addMethodBuildCriteriaQBE(JavaClassSource theClass, final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();

        body
                .append("  // building criteria\n");
        body.append("if (prototype != null && prototype.size() > 0) {\n").append("  StringBuffer whereClause = new StringBuffer();\n")
                .append("  int pos=params.size()+1;\n").append(new JavaSourceCodeFieldsSwitcher("prototype.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                String sb =
                        "if (whereClause.length() > 1) {\n" +
                                "  whereClause.append(\" AND \");\n" +
                                "}\n";
                SQLPattern colExpression = null;

                colExpression = JBGenUtils.getSearchSQL(dbColumn);
                if (colExpression == null) {
                    return "throw new SQLException(\"ForbiddenFieldOnSearchException " + dbColumn.getBeanFieldName() + "\");\n";
                }
                String javaColumnValueExpression = entityInfo.getProjectInfo().getConvertJavaToSQLExpression(dbColumn, "prototype." + JavaUtils.businessGetterName(dbColumn) + "()");
                String searchCriteriaPattern = dbColumn.getDAOInfo().getSearchCriteriaPattern(dbColumn);

                sb += (dbColumn.getSqlDataType().toJavaType().getName() + " columnValue = " + javaColumnValueExpression + ";\n");

                StringBuilder sqlExpression = new StringBuilder();
                StringBuilder javaExpression = new StringBuilder();

                StringBuilder currentVar = null;
                for (int i = 0; i < searchCriteriaPattern.length(); i++) {
                    char c = searchCriteriaPattern.charAt(i);
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
                            String vType = null;
                            if (fctPos >= 0) {
                                vType = currentVar.toString().substring(0, fctPos);
                                vname = currentVar.toString().substring(fctPos + 1);
                            } else {
                                vname = currentVar.toString();
                            }
                            if (vname.equals("columnName")) {
                                sqlExpression.append(colExpression.getQuery());
                            } else {
                                String setter =
                                        (vType == null) ? JavaUtils.getCriteriaSetterName(dbColumn) :
                                                (vType.equals("String")) ? "setString" :
                                                        (vType.equals("int")) ? "setInt" :
                                                                (vType.equals("double")) ? "setDouble" :
                                                                        (vType.equals("Date")) ? "setDate" :
                                                                                (vType.equals("Time")) ? "setTime" :
                                                                                        (vType.equals("Timestamp")) ? "setTimestamp" :
                                                                                                null;
                                if (setter == null) {
                                    throw new NoSuchElementException("Unknown field type " + vType);
                                }
                                sqlExpression.append("?");
                                javaExpression.append(setter).append("(pos++,").append(vname).append(");\n");
                            }

                            currentVar = null;
                            break;
                        }
                        default: {
                            if (currentVar != null) {
                                currentVar.append(c);
                            } else {
                                sqlExpression.append(c);
                            }
                            break;
                        }
                    }
                }
                sb += "whereClause.append(\"" + sqlExpression + "\");\n";
                sb += javaExpression;
                return sb + "break;\n";
            }

            public String getSwitchDefaultCode() {
                return "throw new SQLException(\"UnknownFieldException \"+selectedFieldName);\n";
            }
        }.getCode(2)).append("\tsetWhereClause(whereClause.toString());\n").append("}\n");
        body.append(getUserCode("findAll", entityInfo)).append("\n");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        body
                .append("  return this;\n");

        JavaMethod m = new JavaMethod("setAll", entityInfo.getDataFilterName(),
                (JavaParam[]) params.toArray(new JavaParam[params.size()]),
                "public", // for external use
//                "private",
                new String[]{"SQLException"}, null,
                body);
        theClass.addMethod(m);
        return m;
    }

    public String toString() {
        return "Filter Generator";
    }

    private String getUserCode(String key, DAOInfo entityInfo) {
        return entityInfo.getUserCodeForDO(key);
    }
}

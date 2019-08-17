/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.csharp.generators.dbc;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.sql.dbsupport.RDBMSSupport;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpClassSource;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpField;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpMethod;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpParam;
import org.vpc.neormf.jbgen.csharp.util.CSharpSourceCodeFieldsSwitcher;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import static org.vpc.neormf.jbgen.csharp.util.CSharpUtils.*;

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
public class CSharpDAOGenerator extends JBGenDAOGenerator {
    public static final String METHOD_TO_CRITERIA="ToCriteria";

    public CSharpDAOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        DBTableInfo[] tables = entityInfo.getTables();
        for (DBTableInfo table : tables) {
            if(DBTableInfo.VIRTUAL_TABLE_TYPE.equals(table.getTableType())){
                System.err.println(table.getTableName()+" is virtual, ignore DAO "+entityInfo.getBeanName());
                return false;
            }
        }
        return (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
                        && (entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
//                        || entityInfo.doGenerateTables("data")
                )
        )
                ;
//        if (
//                !entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
//                ) {
//            return false;
//        }
//        return true;
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DAO);
    }

    private Hashtable getFieldsHashcodeMapping(DBColumn[] columns) {
        Hashtable map = new Hashtable();
        for (int i = 0; i < columns.length; i++) {
            Object o = map.get(new Integer(columns[i].getBeanFieldName().hashCode()));
            if (o == null) {
                map.put(new Integer(columns[i].getBeanFieldName().hashCode()), columns[i]);
            } else if (o instanceof DBColumn) {
                Vector v = new Vector();
                v.add(o);
                v.add(columns[i]);
                map.put(new Integer(columns[i].getBeanFieldName().hashCode()), v);
            } else if (o instanceof Vector) {
                Vector v = (Vector) o;
                v.add(columns[i]);
                map.put(new Integer(columns[i].getBeanFieldName().hashCode()), v);
            }
        }
        return map;
    }

    CSharpClassSource theClass = null;
    DAOInfo entityInfo = null;

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        this.entityInfo=entityInfo;
        JBGenProjectInfo moduleCodeStyle = entityInfo.getProjectInfo();
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_DAO));
        theClass = new CSharpClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public ");
        theClass.setName(entityInfo.getDAOName());
        theClass.setSuperClass("DataAccessObject");
        theClass.addAllImports(new String[]{
                "System",

                "System.Collections",
                "System.Data",
                "System.Text",
                "System.Data.SqlClient",
//                "com.microsoft",
                "org.vpc.neormf.commons.jwrapper",

                //"java.rmi.RemoteException",

                "org.vpc.neormf.commons.beans",
                "org.vpc.neormf.commons.exceptions",
                "org.vpc.neormf.commons.types.converters",
                "org.vpc.neormf.commons.sql",

                entityInfo.getDataPackage() + ""
        });
        theClass.setPackage(entityInfo.getDAOPackage());


        if (entityInfo.generateLog4NetLog(J2eeTarget.MODULE_DAO)) {
            theClass.addImport("log4net");//		private static readonly ILog log = new ModuleLog("Administration",LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType));

            String logEntityCatagory = entityInfo.getLogCatagory();
            if (logEntityCatagory == null || logEntityCatagory.length() == 0) {
                logEntityCatagory = "System.Reflection.MethodBase.GetCurrentMethod().DeclaringType";
            } else {
                logEntityCatagory = "\"" + logEntityCatagory + "\"";
            }
            theClass.addField(new CSharpField("_log_", "ILog", null, "private static readonly", "LogManager.GetLogger(" + logEntityCatagory + ")"));
//            theClass.addField(new CSharpField("_log_", "Logger", null, "private static final", "LogManager.GetLogger(\"" + entityInfo.getLogEntityCatagory() + "\")"));
        }
        if (cache != null) {
            theClass.addField(new CSharpField("_cache_", "DataCache", null, "private static final", "new DataCache(" + cache.getMaxExpression() + "," + cache.getTimeoutExpression() + ")"));
            theClass.addField(new CSharpField("_cacheProperties_", entityInfo.getPropertyListName(), null, "private static ", "null"));
            theClass.addMethod(new CSharpMethod(method("getCachedData"), entityInfo.getDTOName(), new CSharpParam[]{new CSharpParam("key", entityInfo.getDataKeyName(), null)}, "public", new String[]{/*"SQLException"*/}, "cache",
                    entityInfo.getDTOName() + " data=(" + entityInfo.getDTOName() + ") _cache_.get(key);\n" +
                            "if(data==null){\n" +
                            "  data=findByKey(_cacheProperties_,key);\n" +
                            "  _cache_."+method("add")+"(data);\n" +
                            "}\n" +
                            "return data;\n"
            ));
        }

        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        for (int i = 0; i < columns.length; i++) {
            DataTypeConverterFactory factory = columns[i].getSqlConverterFactory();
            if (factory != null) {
                theClass.addField(new CSharpField(columns[i].getConverterFieldName(),
                        "org.vpc.neormf.commons.types.converters.DataTypeConverter", null, "private static", factory.getConverterExpression()));
            }
        }

        addConstructor0(entityInfo);
        addConstructor1(entityInfo);
        addConstructor2(entityInfo);
        addConstructor3(entityInfo);

        if (entityInfo.isUpdatable()) {
            createMethodCreate(entityInfo);
            createMethodUpdateData(entityInfo);
            createMethodRemoveData(entityInfo);
            createMethodRemoveAllData(entityInfo);
        }

        if (entityInfo.getPrimaryKeys().length > 0) {
            addMethodGetData(entityInfo);
            addMethod_exists(entityInfo);
        }

        addMethodGetAll(entityInfo);
        addMethodGetAllArray(entityInfo);
        addMethodGetAllQBE(entityInfo);
        addMethodGetAllQBEArray(entityInfo);
        addMethodBuildCriteriaQBE(entityInfo);

        DBColumn[] extraColumns = entityInfo.getColumns(false, false, true);
        for (int i = 0; i < extraColumns.length; i++) {
            createMethodGetSpecificField(entityInfo, extraColumns[i]);
        }

//        entityInfo.setGeneratedClass("DataAccessObject", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating DataAccessObject Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    private CSharpMethod createMethodUpdateData(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false);
        body
                .append("if(data.Size()==0){\n")
                .append("  return;\n")
                .append("}\n");

        body.append("// START Prologue Checking\n");
        for (int i = 0; i < allColumns.length; i++) {
            if (allColumns[i].isForbiddenOnUpdate()) {
                body.append("  if(data.Contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("    throw new ForbiddenFieldOnUpdateException(").append(getDataFieldSourceCode(entityInfo, allColumns[i])).append(");\n");
                body.append("  }\n");
            }
        }
        body.append("// END   ForbiddenFieldOnUpdate Cheking\n");
        body.append("\n");
        body.append(getUserCode("preUpdate",entityInfo));


        body.append("SqlConnection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");
        body.append("  StringBuilder _updateStatement_ = new StringBuilder();\n");
        body.append("  int _ucount_ = 0;\n");
        body.append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  bool _firstColumn_=true;\n");
            body.append("  _updateStatement_.Append( \"");
            body
                    .append("UPDATE ")
                    .append(dbTables[t].getTableName2())
                    .append(" SET \");\n");
            boolean first = true;
            DBColumn[] columns = dbTables[t].getColumns();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    body
                            .append("  if(data.").append(method(c, "Contains")).append("()){\n")
                            .append("    if(_firstColumn_){\n")
                            .append("      _firstColumn_=false;\n")
                            .append("    }else{\n")
                            .append("      _updateStatement_.Append(\", \");\n")
                            .append("    }\n")
                            .append("    _updateStatement_.Append(\"").append(c.getColumnName2()).append("=? \");\n")
                            .append("  }\n");
                }
            }

            body
                    .append("_updateStatement_.Append(\" WHERE ");
            first = true;
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(" AND ");
                    }
                    body
                            .append(c.getColumnName2());
                    body
                            .append(" = ? ");
                }
            }
            body.append("\");\n");

            DBColumn[] pkColumns = new DBColumn[0];
            StringBuilder prepareLog = new StringBuilder();
            prepareLog.append("  ArrayList _logElements_=new ArrayList();\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    prepareLog.append("  if(data.").append(method(c, "Contains")).append("()){\n");
                    prepareLog.append("    _logElements_.Add(data.GetProperty(").append(c.getFullBeanFieldConstant()).append("));\n");
                    prepareLog.append("  }\n");
                }
            }
            pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    prepareLog
                            .append("  _logElements_.Add(")
                            .append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(pkColumns[c.getPkPosition()], "data." + businessAttribute(pkColumns[c.getPkPosition()]) + ""))
                            .append(");\n");
                }
            }
            body.append(getLogDebugCode("Update",prepareLog.toString(), "SQLUtils.LogQuery(_updateStatement_.ToString(),_logElements_)"));

            body.append("  _prepStmt_ = new PreparedStatement(_conn_,_updateStatement_.ToString());\n");
            body
                    .append("  int _pos_=1;\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    body
                            .append("  if(data.").append(method(c, "Contains")).append("()){\n");

                    if (isPrimitive(c.getBusinessDataType())) {
                        body.append("if(data.GetProperty(").append(c.getFullBeanFieldConstant()).append(")==null){\n");
                        body
                                .append("    _prepStmt_.SetNull")
                                .append("(")
                                .append("_pos_++")
                                .append(",")
                                .append(toSqlDbType(c.getSqlType()))
                                .append(");\n");
                        body.append("}else{\n");
                        body
                                .append("    _prepStmt_.")
                                .append(getPreparedStatementSetterName(c))
                                .append("(")
                                .append("_pos_++")
                                .append(",")
                                .append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(c, "data." + businessAttribute(c) + ""))
                                .append(");\n");
                        body.append("}\n");
                    } else {
                        body
                                .append("    _prepStmt_.")
                                .append(getPreparedStatementSetterName(c))
                                .append("(")
                                .append("_pos_++")
                                .append(",")
                                .append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(c, "data." + businessAttribute(c) + ""))
                                .append(");\n");
                    }


                    body
                            .append("  }\n");
                }
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    body
                            .append("  _prepStmt_.")
                            .append(getPreparedStatementSetterName(c))
                            .append("(")
                            .append("_pos_++")
                            .append(",")
                            .append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(pkColumns[c.getPkPosition()], "data." + businessAttribute(pkColumns[c.getPkPosition()]) + ""))
                            .append(");\n");
                }
            }

            body
                    .append("  _ucount_=_prepStmt_.ExecuteUpdate();\n")
                    .append("  _prepStmt_.Close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new UpdateDataException();\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postUpdate",entityInfo));
        body
                .append("}catch(SqlException sqlExcp){\n")
                .append(JBGenUtils.indent(getLogErrorCode("Update",null, "sqlExcp", "sqlExcp")))
                .append(JBGenUtils.indent("throw new UpdateDataException(sqlExcp);\n"))
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        return declareMethod(new CSharpMethod("Update", "void", new CSharpParam[]{
                new CSharpParam("data", entityInfo.getDTOName(), null)
        }, "public",
                null, null,
                body),
                entityInfo, theClass
        );
    }



    private void createMethodRemoveAllData(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("PreparedStatement _prepStmt_=null;\n")
                .append("SqlConnection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");

        body.append("  StringBuilder _statement_ = new StringBuilder(\"DELETE FROM ");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            if (t > 0) {
                body.append(",");
            }
            body.append(dbTables[t].getTableName2());
        }
        body.append("\");\n");

        body.append("  if(criteria!=null && criteria.GetJoins()!=null){\n");
        body.append("    _statement_.Append(\" \");\n");
        body.append("    _statement_.Append(criteria.GetJoins());\n");
        body.append("  }\n");
        DBColumn[] pk = entityInfo.getPrimaryKeys();
        boolean first ;
        if (dbTables.length > 1) {
            body.append("    _statement_.Append(\"");
            body.append(" WHERE ");
            first = true;
            for (int t = 1; t < dbTables.length; t++) {
                DBColumn[] pki = dbTables[t].getPkColumns();
                for (int p = 0; p < pk.length; p++) {
                    if (first) {
                        first = false;
                    } else {
                        body.append(" AND ");
                    }
                    body.append(dbTables[t].getTableName2());
                    body.append(".");
                    body.append(pki[p].getColumnName2());
                    body.append("=");
                    body.append(dbTables[0].getTableName2());
                    body.append(".");
                    body.append(pk[p].getColumnName2());
                }
            }
            body.append("\");\n");
            body.append("  if(criteria!=null && criteria.GetWhereClause()!=null){\n");
            body.append("    _statement_.Append(\" AND \");\n");
            body.append("    _statement_.Append(criteria.GetWhereClause());\n");
            body.append("  }\n");
        } else {
            body.append("  if(criteria!=null && criteria.GetWhereClause()!=null){\n");
            body.append("    _statement_.Append(\" WHERE \");\n");
            body.append("    _statement_.Append(criteria.GetWhereClause());\n");
            body.append("  }\n");
        }
        body.append(getLogDebugCode("Delete", null,
                "_statement_+(criteria==null?\"\":(\"\\n\\tcriteria=\"+criteria))"
        )).append("\n");

//        body.append("  ArrayList list=new ArrayList();\n");
        body.append("  _prepStmt_ = new PreparedStatement(_conn_,_statement_.ToString());\n");

        body.append("  int _statementParamPos_=1;\n");
//        body.append("    for(Iterator _i_=_sqlStatementParamsProviderArrayList_.iterator();_i_.hasNext();){\n");
//        body.append("      SqlStatementParamsProvider _p_=(SqlStatementParamsProvider) _i_.next(); \n");
//        body.append("      _statementParamPos_+=_p_.populateStatement(_prepStmt_,_statementParamPos_); \n");
//        body.append("    }\n");
        body.append("  if(criteria!=null){\n");
        body.append("    criteria.PopulateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("  }\n");
        body
                .append("  return _prepStmt_.ExecuteUpdate();\n")
                .append("}catch(SqlException sqlExcp){\n")
                .append(JBGenUtils.indent(getLogErrorCode("Delete",null, "sqlExcp", "sqlExcp")))
                .append(JBGenUtils.indent("throw new RemoveDataException(sqlExcp);\n"))
                        //.append("}finally{\n")
                        //.append("  _closeConnection_(_conn_);\n")
                .append("}finally{\n")
                .append("  if(_prepStmt_!=null){\n")
                .append("    _prepStmt_.Close();\n")
                .append("  }")
                .append("}");

        ArrayList params = new ArrayList();
//        params.add(new CSharpParam("criteria", entityInfo.getDataFilterName(), null));
        params.add(new CSharpParam("criteria", "Criteria", null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));

        CSharpMethod m = new CSharpMethod("Delete", "int",
                (CSharpParam[]) params.toArray(new CSharpParam[params.size()])
//        new JavaParam[]{
//                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
//                new JavaParam("criteria", "Criteria", null),
//                new JavaParam("order", "OrderList", null),
//                }
                , "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
    }
    private void createMethodRemoveData(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("SqlConnection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");

        body.append(getUserCode("preDelete",entityInfo));
        body.append("  int _ucount_=0;\n")
                .append("  String _removeStatement_ = null;\n")
                .append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {

            body.append("  _removeStatement_ = \"");
            body
                    .append("DELETE FROM ")
                    .append(dbTables[t].getTableName2())
                    .append(" WHERE ");
            boolean first = true;
            DBColumn[] columns = dbTables[t].getColumns();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(" AND ");
                    }
                    body
                            .append(c.getColumnName2());
                    body
                            .append(" = ? ");
                }
            }
            body.append("\";\n");
            body.append("  _prepStmt_ = new PreparedStatement(_conn_,_removeStatement_);\n");
            int pos = 1;
            DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    body.append("  _prepStmt_.");
                    body.append(getPreparedStatementSetterName(c));
                    body.append("(");
                    body.append(pos);
                    body.append(",");
                    body.append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(pkColumns[c.getPkPosition()], "(key." + (businessGetterName(pkColumns[c.getPkPosition()])) + "())")).append(");\n");
                    pos++;
                }
            }

            body.append(getLogDebugCode("Delete",null, "SQLUtils.LogQuery(_removeStatement_,null)"));

            body
                    .append("  _ucount_=_prepStmt_.ExecuteUpdate();\n")
                    .append("  _prepStmt_.Close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new RemoveDataException();\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postDelete",entityInfo));

        body
                .append("}catch(SqlException sqlExcp){\n")
                .append(JBGenUtils.indent(getLogErrorCode("Delete",null, "sqlExcp", "sqlExcp")))
                .append(JBGenUtils.indent("throw new RemoveDataException(sqlExcp);\n"))
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        declareMethod(new CSharpMethod("Delete", "void", new CSharpParam[]{
                new CSharpParam("key", entityInfo.getDataKeyName(), null)
        }, "public",
                null, null,
                body),
                entityInfo,
                theClass
        );
    }

    private void addConstructor0(final DAOInfo entityInfo) {
        CSharpMethod m = new CSharpMethod(theClass.getName(),
                null,
                new CSharpParam[0]
                , "public",
                new String[]{
                }, "DataAccessObject Constructor",
                "");
        entityInfo.declareMethod(theClass, m, "entity");
    }

    private void addConstructor1(final DAOInfo entityInfo) {
        CSharpMethod m = new CSharpMethod(theClass.getName(), null,
                new CSharpParam[]{new CSharpParam("other", "DataAccessObject", null)}
                , "public",
                new String[]{
                }, "DataAccessObject Constructor",
                "base(other)", "");
        entityInfo.declareMethod(theClass, m, "entity");
    }

    private void addConstructor2(final DAOInfo entityInfo) {
        CSharpMethod m = new CSharpMethod(theClass.getName(), null,
                new CSharpParam[]{new CSharpParam("cnx", "SqlConnection", null)}
                , "public",
                new String[]{
                }, "DataAccessObject Constructor",
                "base()", "SetConnection(cnx);");
        entityInfo.declareMethod(theClass, m, "entity");
    }

    private void addConstructor3(final DAOInfo entityInfo) {
        CSharpMethod m = new CSharpMethod(theClass.getName(), null,
                new CSharpParam[]{
                        new CSharpParam("cnx", "SqlConnection", null),
                        new CSharpParam("caller", "String", null),
                }
                , "public",
                new String[]{
                }, "DataAccessObject Constructor",
                "base()", "SetConnection(cnx);\nSetCallerPrincipalName(caller);");
        entityInfo.declareMethod(theClass, m, "entity");
    }

    private void createMethodCreate(final DAOInfo entityInfo) throws SQLException {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false);
        body.append("// START Prologue initialization\n");
        body.append(createBodyDeclareVars(entityInfo));
//        for (int i = 0; i < allColumns.length; i++) {
//            body.append(allColumns[i].getBeanFieldVar()).append("=").append(entityInfo.getFieldInitialValue(allColumns[i])).append(";\n");
//        }
        body.append("// END   Prologue initialization\n");

        body.append("// START Prologue checking\n");

        for (int i = 0; i < allColumns.length; i++) {
            if (allColumns[i].isForbiddenOnInsert()) {
                body.append("if(data.Contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("  throw new ForbiddenFieldOnInsertException(").append(getDataFieldSourceCode(allColumns[i].getDAOInfo(), allColumns[i])).append(");\n");
                body.append("}\n");
            } else if (allColumns[i].isRequiredOnInsert()) {
                body.append("if(!data.Contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("  throw new RequiredFieldOnInsertException(").append(getDataFieldSourceCode(allColumns[i].getDAOInfo(), allColumns[i])).append(");\n");
                body.append("}\n");
            }
        }
        body.append("// END  Prologue checking\n");
        body.append("\n");
        body
                .append("SqlConnection  _conn_=null;\n")
                .append("String _insertStatement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");

        DBColumn[] keys = entityInfo.getColumns(true, false, false);
        if (entityInfo.isAutoDentity()) {
            RDBMSSupport rdbmsSupport = entityInfo.getProjectInfo().getRdbmsSupport();
            if (rdbmsSupport.getType() == RDBMSSupport.SEQUENCE) {
                if (keys.length != 1) {
                    StringBuilder allKeysBuffer = new StringBuilder();
                    for (int i = 0; i < keys.length; i++) {
                        if (i > 0) {
                            allKeysBuffer.append(",");
                        }
                        DBColumn key = keys[i];
                        allKeysBuffer.append(key.getBeanFieldName());
                    }
                    throw new IllegalArgumentException(entityInfo.getBeanName() + " : Unhandled Sequence on multiple columns : " + allKeysBuffer);
                }
                body.append("\n");
                body.append("  // START Sequence Handling\n");
//                String sequenceName = entityInfo.getSequenceName();
                body.append("  String _selectNewIdStatement_ = \"").append(rdbmsSupport.getSequenceString(entityInfo)).append("\";\n");

                body.append(getLogDebugCode("Insert",null, "\"<Sequence Handling Query> \"+SQLUtils.LogQuery(_selectNewIdStatement_)"));

                body.append("  _prepStmt_ = new PreparedStatement(_conn_,_selectNewIdStatement_);\n");
                body.append("  ResultSet _rs_=_prepStmt_.ExecuteQuery();\n");
                body.append("  if(_rs_.MoveNext()){\n");
                //            body.append("    this.").append(keys[0].getBeanFieldVar()).append("= _rs_."+keys[0].getPreparedStatementGetterName()+"(1);\n");
                body.append("    data.").append(businessAttribute(keys[0])).append("=(").append("_rs_.").append(getPreparedStatementGetterName(keys[0])).append("(1));\n");
                //            body.append("    data.setOrderId(newId);\n");
                body.append("    _rs_.Close();\n");
                body.append("    _prepStmt_.Close();\n");
                body.append("  }\n");
                body.append("  _rs_.Close();\n");
                body.append("  _prepStmt_.Close();\n");
                body.append("  // END Sequence Handling\n");
            }
        } else {
            for (int i = 0; i < keys.length; i++) {
                DBColumn dbColumn = keys[i];
                body.append("  ").append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(dbColumn, "(data." + (businessAttribute(dbColumn)) + ")")).append(";\n");
            }
        }

        body.append("\n");
        body.append(getUserCode("preInsert",entityInfo));

        body.append("  // START Local Fields Updates\n");
        body.append(new CSharpSourceCodeFieldsSwitcher("data.KeySet().GetEnumerator()", CSharpSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (dbColumn.isFormulaImpl()) {
//                    return "// do nothing ; " + dbColumn.getBeanFieldName() + " is a view field";
                    StringBuilder sb = new StringBuilder("// " + dbColumn.getBeanFieldName() + " is a view field\n");
                    if (dbColumn.isForbiddenOnInsert()) {
                        sb.append("throw new ForbiddenFieldOnInsertException(").append(getDataFieldSourceCode(entityInfo, dbColumn)).append(");\n");
                    } else {
                        sb.append("break;\n");
                    }
                    return sb.toString();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(getFieldUserCode(dbColumn.getBeanFieldName(),"preInsert",entityInfo));
                    if (isPrimitive(dbColumn.getBusinessDataType())) {
                        sb.append("if(data.GetProperty(").append(dbColumn.getFullBeanFieldConstant()).append(")==null){\n");
                        sb.append("  ").append(dbColumn.getBeanFieldVar()).append("_isNull_=true;\n");
                        sb.append("}else{\n");
                        sb.append("  ");
                        sb.append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(dbColumn, "(data." + (businessAttribute(dbColumn)) + ")")).append(";\n");
                        sb.append("}\n");
                    } else {
                        sb.append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(dbColumn, "(data." + (businessAttribute(dbColumn)) + ")")).append(";\n");
                    }
                    sb.append(getFieldUserCode(dbColumn.getBeanFieldName(),"postInsert",entityInfo));
                    sb.append("break;\n");
                    return sb.toString();
                }
            }

            public String getSwitchDefaultCode() {
                return "throw new UnknownFieldException(selectedFieldName);";
            }
        }.getCode(2));
        body.append("  // END   Local Fields Updates\n");
        body.append("\n");
        body.append("  // START Database persistance\n");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  _insertStatement_ = \"");
            body
                    .append("INSERT INTO ")
                    .append(dbTables[t].getTableName2())
                    .append("(");
            boolean first = true;
            DBColumn[] ucolumns = dbTables[t].getUpdatableColumns();
            DBColumn[] pkcolumns = dbTables[t].getPkColumns();
            DBColumn[] bpkcolumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < pkcolumns.length; i++) {
                DBColumn c = pkcolumns[i];
                if (!c.isAutoIdentity()) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(", ");
                    }
                    body
                            .append(c.getColumnName2());
                }
            }
            for (int i = 0; i < ucolumns.length; i++) {
                DBColumn c = ucolumns[i];
                if (!c.isAutoIdentity()) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(", ");
                    }
                    body
                            .append(c.getColumnName2());
                }
            }

            body
                    .append(") VALUES (");
            first = true;
            for (int i = 0; i < pkcolumns.length; i++) {
                DBColumn c = pkcolumns[i];
                if (!c.isAutoIdentity()) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(", ");
                    }
                    body
                            .append("?");
                }
            }
            for (int i = 0; i < ucolumns.length; i++) {
                DBColumn c = ucolumns[i];
                if (!c.isAutoIdentity()) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(", ");
                    }
                    body
                            .append("?");
                }
            }
            body.append(")\";\n");

            //LOG
            StringBuilder prepareLog = new StringBuilder();
            prepareLog.append("  ArrayList _logElements_=new ArrayList();\n");
            DBColumn[] bpk_u_columns = JBGenUtils.concat(bpkcolumns, ucolumns);
            for (int i = 0; i < bpk_u_columns.length; i++) {
                DBColumn c = bpk_u_columns[i];
                if (!c.isAutoIdentity()) {
                    prepareLog
                            .append("    _logElements_.Add(")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            body.append(getLogDebugCode("Insert",prepareLog.toString(), "SQLUtils.LogQuery(_insertStatement_,_logElements_)"));

            body.append("  _prepStmt_ = new PreparedStatement(_conn_,_insertStatement_);\n");
            int pos = 1;
            for (int i = 0; i < bpk_u_columns.length; i++) {
                DBColumn c = bpk_u_columns[i];
                if (!c.isAutoIdentity()) {
                    if (isPrimitive(c.getBusinessDataType())) {
                        body.append("if(").append(c.getBeanFieldVar()).append("_isNull_){\n");
                        body
                                .append("  _prepStmt_.SetNull(")
                                .append(pos)
                                .append(",")
                                .append(toSqlDbType(c.getSqlType()))
                                .append(");\n");
                        body.append("}else{\n");
                        body
                                .append("  _prepStmt_.")
                                .append(getPreparedStatementSetterName(c))
                                .append("(")
                                .append(pos)
                                .append(",")
                                .append(c.getBeanFieldVar())
                                .append(");\n");
                        body.append("}\n");
                    } else {
                        body
                                .append("  _prepStmt_.")
                                .append(getPreparedStatementSetterName(c))
                                .append("(")
                                .append(pos)
                                .append(",")
                                .append(c.getBeanFieldVar())
                                .append(");\n");
                    }
                    pos++;
                }
            }

            body
                    .append("  _prepStmt_.ExecuteUpdate();\n")
                    .append("  _prepStmt_.Close();\n");
        }
        body.append("\n");

        if (entityInfo.isAutoDentity()) {
            RDBMSSupport rdbmsSupport = entityInfo.getProjectInfo().getRdbmsSupport();
            if (rdbmsSupport.getType() == RDBMSSupport.AUTO_INCREMENT) {
                if (keys.length != 1) {
                    StringBuilder allKeysBuffer = new StringBuilder();
                    for (int i = 0; i < keys.length; i++) {
                        if (i > 0) {
                            allKeysBuffer.append(",");
                        }
                        DBColumn key = keys[i];
                        allKeysBuffer.append(key.getBeanFieldName());
                    }
                    throw new IllegalArgumentException(entityInfo.getBeanName() + " : Unhandled Sequence on multiple columns : " + allKeysBuffer);
                }
                body.append("\n");
                body.append("  // START Sequence Handling\n");
                body.append("  String _selectNewIdStatement_ = \"").append(rdbmsSupport.getSequenceString(entityInfo)).append("\";\n");

                body.append(getLogDebugCode("Insert",null, "\"<Sequence Handling Query> \"+SQLUtils.LogQuery(_selectNewIdStatement_)"));

                body.append("  _prepStmt_ = new PreparedStatement(_conn_,_selectNewIdStatement_);\n");
                body.append("  ResultSet _rs_=_prepStmt_.ExecuteQuery();\n");
                body.append("  if(_rs_.MoveNext()){\n");
                //            body.append("    this.").append(keys[0].getBeanFieldVar()).append("= _rs_."+keys[0].getPreparedStatementGetterName()+"(1);\n");
                body.append("    data.").append(businessAttribute(keys[0])).append("=(").append("_rs_.").append(getPreparedStatementGetterName(keys[0])).append("(1));\n");
                //            body.append("    data.setOrderId(newId);\n");
                body.append("    _rs_.Close();\n");
                body.append("    _prepStmt_.Close();\n");
                body.append("  }\n");
                body.append("  _rs_.Close();\n");
                body.append("  _prepStmt_.Close();\n");
                body.append("  // END Sequence Handling\n");
            }
        }

        body.append(getUserCode("postInsert",entityInfo));
        body.append("  // END   Database persistance\n");
        body.append("\n");
        body
                .append("  // returning Identifier;\n").append("  return data.Get").append(entityInfo.getDataKeyName()).append("();\n")
                .append("}catch(SqlException sqlExcp){\n")
                .append(JBGenUtils.indent(getLogErrorCode("Insert",null, "sqlExcp", "sqlExcp")))
                .append(JBGenUtils.indent("throw new CreateDataException(sqlExcp);\n"))
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new CSharpParam("data", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodCreateDataExtraParams()));
        CSharpMethod m = new CSharpMethod("Insert", entityInfo.getDataKeyName(),
                (CSharpParam[]) params.toArray(new CSharpParam[params.size()])
                , "public",
                null
                , "DataAccessObject Constructor",
                body);
//        String roles=(entityInfo.getMethodPatternValue(theClass,m,"roles",null);

        declareMethod(m, entityInfo, theClass);
    }

    private static CSharpMethod declareMethod(CSharpMethod m, DAOInfo entityInfo, CSharpClassSource theClass) {
        decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
//        CSharpUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }


    //TODO invalid
    private String getMethodGetSpecificFieldBodyForSQL_VIEW(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("SqlConnection  _conn_=null;\n")
                .append("String _selectStatement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("ResultSet _rs_  = null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");

        body.append("  _selectStatement_=\"Select (").append(JBGenUtils.getSearchSQL(column).getQuery()).append(") FROM ").append(entityInfo.getTables()[0].getTableName2()).append(" WHERE ");
        DBColumn[] columns = entityInfo.getColumns(true, false, false);
        boolean first = true;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (first) {
                first = false;
            } else {
                body
                        .append(" AND ");
            }
            body
                    .append(c.getColumnName2())
                    .append(" = ")
                    .append("?");
        }
        body.append("\";\n");
        body.append("  _prepStmt_ = new PreparedStatement(_conn_,_selectStatement_);\n");
        int pos = 1;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("  _prepStmt_.")
                        .append(getPreparedStatementSetterName(c))
                        .append("(")
                        .append(pos)
                        .append(",")
                        .append(c.getBeanFieldVar())
                        .append(");\n");
                pos++;
            }
        }
        body.append(getLogDebugCode("Select", null, "_selectStatement_")).append("\n");

        body
                .append("  _rs_=_prepStmt_.ExecuteQuery();\n")
                .append("  if(_rs_.MoveNext()){\n")
                .append("    return _rs_.")
                .append(getPreparedStatementGetterName(column))
                .append("(1);\n");
        body
                .append("  }else{\n")
                .append("    throw new DataNotFoundException();\n")
                .append("  }\n");
        body
                .append("}catch(SqlException sqlExcp){\n")
                .append("  throw new DataRetrievalException(sqlExcp);\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_rs_!=null){\n")
                .append("      _rs_.Close();\n")
                .append("      _prepStmt_.Close();\n")
                .append("    }\n")
//                .append("    _closeConnection_(_conn_);\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new DataRetrievalException(e);\n")
                .append("  }\n")
                .append("}");
        return body.toString();
    }

    //TODO invalid
    private String getMethodGetSpecificFieldBodyForSQL_SQL_CALL(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("SqlConnection  _conn_=null;\n")
                .append("String _call_ = null;\n")
                .append("java.sql.CallableStatement _callStmt_  = null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");
        SQLPattern pattern = new SQLPattern(column.getGetterImpl().getBody().getValue(), column, false);
        if (pattern.indexOfParamByInOutType(SQLPattern.SQLCallParam.RETURN) == null) {
            pattern = new SQLPattern("{RETURN} := " + column.getGetterImpl().getBody(), column, false);
        }
        body.append("  _call_=\"{CALL ").append(pattern.getQuery()).append("}\";\n");
        body.append("  _callStmt_ = _conn_.prepareCall(_call_);\n");
        SQLPattern.SQLCallParam[] sqlCallParams = pattern.getParams();
        int retPos = -1;
        for (int i = 0; i < sqlCallParams.length; i++) {
            SQLPattern.SQLCallParam p = sqlCallParams[i];
            if (p.isReturnParam()) {
                //TODO take into consideration converter ??
                body.append("  _callStmt_.registerOutParameter(").append(p.getPos()).append(",java.sql.Types.").append(JBGenUtils.getSQLTypeName(column.getSqlType())).append(");\n");
                retPos = i;
            } else if (p.isInParam()) {
                Integer type = null;
                DBColumn c = entityInfo.getColumnByFieldName(p.getName(), false);
                if (c == null) {
                    CSharpParam[] javaParams = entityInfo.getMethodGetDataExtraCSharpParams();
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
                    body.append("  _callStmt_.").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(javaParams[javaParamsIndex].getName()).append(");\n");
                } else {
                    type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                    body.append("  _callStmt_.").append(getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(c.getBeanFieldVar()).append(");\n");
                }
            } else {
                // do nothing
            }
        }
        if (retPos < 0) {
            throw new IllegalArgumentException("Expected {RETURN}");
        }
        SQLPattern.SQLCallParam p = sqlCallParams[retPos];
        body.append("  return _callStmt_.").append(getPreparedStatementMethodGetterName(column.getSqlType())).append("(").append(p.getPos()).append(");\n");
        body
                .append("}catch(SqlException sqlExcp){\n")
                .append("  throw new DataRetrievalException(sqlExcp);\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_callStmt_!=null){\n")
                .append("      _callStmt_.Close();\n")
                .append("    }\n")
//                .append("    _closeConnection_(_conn_);\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new DataRetrievalException(e);\n")
                .append("  }\n")
                .append("}\n");
        return body.toString();
    }

    private void createMethodGetSpecificField(DAOInfo entityInfo, DBColumn column) {
        String body ;
        FieldFormulaImpl fieldFormulaImpl = column.getGetterImpl();
        switch (fieldFormulaImpl.getType()) {
            case code: {
                body = fieldFormulaImpl.getBody().getValue();
                break;
            }
            case sqlQuery:
            case sqlView: {
                body = getMethodGetSpecificFieldBodyForSQL_VIEW(entityInfo, column);
                break;
            }
            case sqlCall:
            case sqlFunction: {
                body = getMethodGetSpecificFieldBodyForSQL_SQL_CALL(entityInfo, column);
                break;
            }
            case sqlMasterDetail: {
                body = getMethodGetSpecificFieldBodyForRELATION_MasterToDetail(entityInfo, column);
                break;
            }
            case sqlDetailMaster: {
                body = getMethodGetSpecificFieldBodyForRELATION_DetailToMaster(entityInfo, column);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unhandled Formula type " + fieldFormulaImpl.getTypeName());
            }
        }
        ArrayList ep = new ArrayList();
        StringBuilder prefixCode = new StringBuilder();
        if (entityInfo.getPrimaryKeys().length > 0) {
            ep.add(new CSharpParam("tableKey", entityInfo.getDataKeyName(), null));
            if (column.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
                RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
                String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage());
                ep.add(new CSharpParam("propertyList", rpl, null));
                ep.add(new CSharpParam("criteria", "Criteria", null));
                ep.add(new CSharpParam("order", "OrderList", null));
            }
            DBColumn[] k = entityInfo.getPrimaryKeys();
            for (int i = 0; i < k.length; i++) {
                prefixCode.append(k[i].getBusinessDataTypeName()).append(" ").append(k[i].getBeanFieldVar()).append("=tableKey.").append(businessGetterName(k[i])).append("();\n");
            }
        }
        ep.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        CSharpMethod m = new CSharpMethod(businessGetterName(column),
                getCSharpClassName(column.getBusinessDataType().toJavaType()),
                (CSharpParam[]) ep.toArray(new CSharpParam[ep.size()]),
                "public",
                null,
                null,
                prefixCode + body);
        declareMethod(m,entityInfo,theClass);

    }

    private void addMethodGetData(DAOInfo entityInfo) {

        StringBuilder body = new StringBuilder();
        body
                .append("String where=\"");

        DBColumn[] columns = entityInfo.getColumns(true, false, false);
        boolean first = true;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (first) {
                first = false;
            } else {
                body
                        .append(" AND ");
            }
            body
                    .append(c.getTable().getTableName2())
                    .append(".")
                    .append(c.getColumnName2())
                    .append(" = ")
                    .append("?");
        }
        body.append("\";\n");
        body.append("Criteria criteria=new Criteria(where);\n");

        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            body.append("criteria.");
            body.append("Set");
            body.append(getPreparedStatementMethodSuffixName(c.getBusinessDataType())).append("(").append(i + 1).append(",").append("primaryKey.").append(businessGetterName(c)).append("());\n");
        }

        body
                .append("ICollection collection=Select(propertyList,criteria,null);\n")
                .append("if(collection.Count>0){\n")
                .append("  for(IEnumerator i=collection.GetEnumerator();i.MoveNext();){\n").append("    return (").append(entityInfo.getDTOName()).append(") i.Current;\n")
                .append("  }\n")
                .append("}\n")
                .append(" throw new DataNotFoundException();\n");

        CSharpMethod m = new CSharpMethod("Select", entityInfo.getDTOName(), new CSharpParam[]{
                new CSharpParam("propertyList", entityInfo.getPropertyListName(), null),
                new CSharpParam("primaryKey", entityInfo.getDataKeyName(), null)
        }, "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
    }

    private CSharpParam[] getSelectParams(DAOInfo entityInfo) {
        ArrayList params = new ArrayList();
        params.add(new CSharpParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new CSharpParam("criteria", "Criteria", null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));
        params.add(new CSharpParam("order", "OrderList", null));
        return (CSharpParam[]) params.toArray(new CSharpParam[params.size()]);
    }

    private CSharpParam[] getSelectQBEParams(DAOInfo entityInfo) {
        ArrayList params = new ArrayList();
        params.add(new CSharpParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new CSharpParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));
        params.add(new CSharpParam("order", "OrderList", null));
        return (CSharpParam[]) params.toArray(new CSharpParam[params.size()]);
    }

    private CSharpMethod addMethodGetAllArray(DAOInfo entityInfo) {
        CSharpParam[] params = getSelectParams(entityInfo);

        StringBuilder body = new StringBuilder();
        body
                .append("ICollection collection=Select(")
                .append(CSharpParam.toCommaSeparatedString(params))
                .append(");\n").append("return (").append(entityInfo.getDTOName()).append("[]) new ArrayList(collection).ToArray(typeof(").append(entityInfo.getDTOName()).append("));");

        CSharpMethod m = new CSharpMethod("SelectArray", entityInfo.getDTOName()+"[]",
                params
                , "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
        return m;
    }

    private CSharpMethod addMethodGetAllQBEArray(DAOInfo entityInfo) {
        CSharpParam[] params = getSelectQBEParams(entityInfo);

        StringBuilder body = new StringBuilder();
        body
                .append("ICollection collection=Select(")
                .append(CSharpParam.toCommaSeparatedString(params))
                .append(");\n").append("return (").append(entityInfo.getDTOName()).append("[]) new ArrayList(collection).ToArray(typeof(").append(entityInfo.getDTOName()).append("));");

        CSharpMethod m = new CSharpMethod("SelectArray", entityInfo.getDTOName()+"[]",
                params
                , "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
        return m;
    }

    private void addMethod_exists(DAOInfo entityInfo) {

        StringBuilder body = new StringBuilder();
        body
                .append(entityInfo.getPropertyListName())
                .append(" propertyList=new ").append(entityInfo.getPropertyListName()).append("();\n");
        DBColumn[] primaryKeys = entityInfo.getPrimaryKeys();
        for (int i = 0; i < primaryKeys.length; i++) {
            DBColumn dbColumn = entityInfo.getPrimaryKeys()[i];
            body.append("propertyList.Add").append(JBGenUtils.capitalize(dbColumn.getBeanFieldName())).append("();\n");
        }
        body
                .append("String where=\"");

        DBColumn[] columns = entityInfo.getColumns(true, false, false);
        boolean first = true;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (first) {
                first = false;
            } else {
                body
                        .append(" AND ");
            }
            body
                    .append(c.getTable().getTableName2())
                    .append(".")
                    .append(c.getColumnName2())
                    .append(" = ")
                    .append("?");
        }
        body.append("\";\n");
        body.append("Criteria criteria=new Criteria(where);\n");

        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            body.append("criteria.");
            body.append("Set");
            body.append(getPreparedStatementMethodSuffixName(c.getBusinessDataType())).append("(").append(i + 1).append(",").append("primaryKey.").append(businessGetterName(c)).append("());\n");
        }

        body
                .append("ICollection collection=Select(propertyList,criteria,null);\n")
                .append("if(collection.Count>0){\n")
                .append("  for(IEnumerator i=collection.GetEnumerator();i.MoveNext();){\n").append("    return true;\n")
                .append("  }\n")
                .append("}\n")
                .append("return false;\n");

        CSharpMethod m = new CSharpMethod("Exists", "bool", new CSharpParam[]{
                new CSharpParam("primaryKey", entityInfo.getDataKeyName(), null)
        }, "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
    }

    private void addMethodGetAll(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("SqlConnection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=GetConnection();\n");

        body.append("  StringBuilder _selectStatement_ = new StringBuilder(\"SELECT \");\n");

        body.append("  bool _isDistinct=(criteria!=null && criteria.IsDistinct());\n");
        body.append("  if(_isDistinct){\n");
        body.append("    _selectStatement_.Append(\"DISTINCT \");\n");
        body.append("  }\n");
        body.append("  ArrayList _sqlStatementParamsProviderArrayList_=new ArrayList();\n");
        body.append("  if(propertyList==null){\n");
        body.append("    if(_isDistinct){\n");
        {
            ArrayList a = new ArrayList();
            a.addAll(Arrays.asList(entityInfo.getColumns(true, true, true)));
            DBColumn[] allpkPlusColumns = (DBColumn[]) a.toArray(new DBColumn[a.size()]);

            boolean added = false;
            body.append("    _selectStatement_.Append(\"");

            StringBuilder postBody = new StringBuilder();
            for (int i = 0; i < allpkPlusColumns.length; i++) {
                DBColumn column = allpkPlusColumns[i];
                if (JBGenUtils.isSearchableColumn(column)) {
                    if (added) {
                        body.append(",");
                    } else {
                        added = true;
                    }
                    SQLPattern sqlPattern = JBGenUtils.getSearchSQL(column);
                    body.append("(").append(sqlPattern.getQuery()).append(")");
                    postBody.append(JBGenUtils.indent(getPopulateSqlStatementParamsProviderArrayList(column, "_sqlStatementParamsProviderArrayList_", "statement")));

                } else {
//                throw new IllegalArgumentException("Unsupported type "+fieldFormulaImpl.getTypeName());
                }
            }
            body.append("\");\n");
            if (postBody.length() > 0) {
                body.append(postBody);
            }
        }
        body.append("    }else{\n");

        {
            ArrayList a = new ArrayList();
            a.addAll(Arrays.asList(entityInfo.getColumns(true, false, false)));
            a.addAll(Arrays.asList(entityInfo.getColumns(true, true, true)));
            DBColumn[] allpkPlusColumns = (DBColumn[]) a.toArray(new DBColumn[a.size()]);

            boolean added = false;
            body.append("    _selectStatement_.Append(\"");

            StringBuilder postBody = new StringBuilder();
            for (int i = 0; i < allpkPlusColumns.length; i++) {
                DBColumn column = allpkPlusColumns[i];
                if (JBGenUtils.isSearchableColumn(column)) {
                    if (added) {
                        body.append(",");
                    } else {
                        added = true;
                    }
                    SQLPattern sqlPattern = JBGenUtils.getSearchSQL(column);
                    body.append("(").append(sqlPattern.getQuery()).append(")");
                    postBody.append(JBGenUtils.indent(getPopulateSqlStatementParamsProviderArrayList(column, "_sqlStatementParamsProviderArrayList_", "statement")));

                } else {
    //                throw new IllegalArgumentException("Unsupported type "+fieldFormulaImpl.getTypeName());
                }
            }
            body.append("\");\n");
            if (postBody.length() > 0) {
                body.append(postBody);
            }
        }
        body.append("    }\n");

        body.append("  }else{\n");
        body.append("    StringBuilder sb=new StringBuilder();\n");
        body.append("    if(!_isDistinct){\n");
        body.append("      sb.Append(\"");
        DBColumn[] pkcolumns = entityInfo.getColumns(true, false, false);
        for (int i = 0; i < pkcolumns.length; i++) {
            DBColumn c = pkcolumns[i];
            if (i > 0) {
                body.append(",");
            }
            body.append(c.getColumnName2());
        }
        body.append("\");\n");
        body.append("    }\n");

        body.append(JBGenUtils.indent(new CSharpSourceCodeFieldsSwitcher("propertyList.GetEnumerator()", CSharpSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (JBGenUtils.isSearchableColumn(dbColumn)) {
                    return
                            "if (sb.Length > 0) {\n" +
                                    "  sb.Append(\" , \");\n" +
                                    "}\n" +
                                    "sb.Append(\"" +
                                    JBGenUtils.getSearchSQL(dbColumn).getQuery() +
                                    "\");\n" +
                                    getPopulateSqlStatementParamsProviderArrayList(dbColumn, "_sqlStatementParamsProviderArrayList_", "statement");
                } else {
                    return "// do nothing; query type = " + dbColumn.getGetterImpl().getTypeName();
                }
            }
        }.getCode(), 4, false, true));
        body.append("    _selectStatement_.Append(sb.ToString());\n");
        body.append("  }\n");

        body
                .append("  _selectStatement_.Append(\" FROM ");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            if (t > 0) {
                body.append(",");
            }
            body.append(dbTables[t].getTableName2());
        }
        body.append("\");\n");
        body.append("  if(criteria!=null && criteria.GetJoins()!=null){\n");
        body.append("    _selectStatement_.Append(\" \");\n");
        body.append("    _selectStatement_.Append(criteria.GetJoins());\n");
        body.append("  }\n");
        DBColumn[] pk = entityInfo.getPrimaryKeys();
        boolean first = true;
        if (dbTables.length > 1) {
            body.append("    _selectStatement_.Append(\"");
            body.append(" WHERE ");
            first = true;
            for (int t = 1; t < dbTables.length; t++) {
                DBColumn[] pki = dbTables[t].getPkColumns();
                for (int p = 0; p < pk.length; p++) {
                    if (first) {
                        first = false;
                    } else {
                        body.append(" AND ");
                    }
                    body.append(dbTables[t].getTableName2());
                    body.append(".");
                    body.append(pki[p].getColumnName2());
                    body.append("=");
                    body.append(dbTables[0].getTableName2());
                    body.append(".");
                    body.append(pk[p].getColumnName2());
                }
            }
            body.append("\");\n");
            body.append("  if(criteria!=null && criteria.GetWhereClause()!=null){\n");
            body.append("    _selectStatement_.Append(\" AND \");\n");
            body.append("    _selectStatement_.Append(criteria.GetWhereClause());\n");
            body.append("  }\n");
        } else {
            body.append("  if(criteria!=null && criteria.GetWhereClause()!=null){\n");
            body.append("    _selectStatement_.Append(\" WHERE \");\n");
            body.append("    _selectStatement_.Append(criteria.GetWhereClause());\n");
            body.append("  }\n");
        }

        body.append("    if(order!=null){\n");
        body.append("      _selectStatement_.Append(\" ORDER BY \");\n");

        body.append("      bool orderFirst=true;\n");

        CSharpSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
                new CSharpSourceCodeFieldsSwitcher("order.GetEnumerator()",
                        "OrderList.OrderItem item=(OrderList.OrderItem) i.Current;" +
                                "String selectedFieldName=item.GetFieldName();"
                        , false, entityInfo.getColumns(true, true, true)) {
                    public String getFieldNameCode(DBColumn dbColumn) {
                        StringBuilder sb = new StringBuilder();
                        if (JBGenUtils.isSearchableColumn(dbColumn)) {
                            sb.append("if(orderFirst){\n");
                            sb.append("  orderFirst=false;\n");
                            sb.append("}else{\n");
                            sb.append("  _selectStatement_.Append(\" , \");\n");
                            sb.append("}\n");
                            sb.append("_selectStatement_.Append(\"").append(JBGenUtils.getSearchSQL(dbColumn).getQuery()).append("\");\n");
                            sb.append("_selectStatement_.Append(\" \").Append(item.IsAscendent()?\"ASC\":\"DESC\");\n");
                            sb.append("break;\n");
                        } else {
                            sb.append("throw new ForbiddenFieldOnSearchException(").append(getDataFieldSourceCode(dbColumn.getDAOInfo(), dbColumn)).append(");\n");
                        }
                        return sb.toString();
                    }
                };
        sourceCodeFieldsSwitcher.setSwitchDefaultCode(new StringBuilder()
                .append("//WHEN UNKNOWN FIELD PASSED AS IS TO SQL\n")
                .append("if(orderFirst){\n")
                .append("  orderFirst=false;\n")
                .append("}else{\n")
                .append("  _selectStatement_.Append(\" , \");\n")
                .append("}\n")
                .append("_selectStatement_.Append(selectedFieldName);\n")
                .append("_selectStatement_.Append(\" \").Append(item.IsAscendent()?\"ASC\":\"DESC\");\n")
                .append("break;\n")
                .toString());
        sourceCodeFieldsSwitcher.setAcceptFieldColumnNames(entityInfo.isAcceptColumnNamesInOrderClause());
        body.append(sourceCodeFieldsSwitcher.getCode(6));
        body.append("    }\n");
//        body.append("if(order!=null){\n");
//        body.append("  _selectStatement_.append(\" ORDER BY \");\n");
//        body.append("  boolean orderFirst=true;\n");
//        body.append("  for(Iterator i=order.iterator();i.hasNext();){\n");
//        body.append("    if(orderFirst){\n");
//        body.append("      orderFirst=false;\n");
//        body.append("    }else{\n");
//        body.append("      _selectStatement_.append(\" , \");\n");
//        body.append("    }\n");
//        body.append("    OrderList.OrderItem item=(OrderList.OrderItem) i.MoveNext();\n");
//        body.append("    DTOFieldMetaData _dataField_=").append(entityInfo.getDataContentName()).append(".INFO.getField(item.getFieldName());\n");
//        body.append("    if(_dataField_!=null){\n");
//        body.append("      _selectStatement_.append(_dataField_.getColumnName());\n");
//        body.append("    }else{\n");
//        body.append("      _selectStatement_.append(item.getFieldName());\n");
//        body.append("    }\n");
//        body.append("    _selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
//        body.append("  }\n");
//        body.append("}\n");

        body.append(getLogDebugCode("Select", null,
                "_selectStatement_+(criteria==null?\"\":(\"\\n\\tcriteria=\"+criteria))+(order==null?\"\":(\"\\n\\torder=\"+order))"
        )).append("\n");
        body.append("    ArrayList list=new ArrayList();\n");
        body.append("    PreparedStatement _prepStmt_ = new PreparedStatement(_conn_,_selectStatement_.ToString());\n");
        body.append("    int _min_=-1;\n");
        body.append("    int _max_=-1;\n");
        body.append("    int _statementParamPos_=1;\n");
        body.append("    for(IEnumerator _i_=_sqlStatementParamsProviderArrayList_.GetEnumerator();_i_.MoveNext();){\n");
        body.append("      SqlStatementParamsProvider _p_=(SqlStatementParamsProvider) _i_.Current; \n");
        body.append("      _statementParamPos_+=_p_.PopulateStatement(_prepStmt_,_statementParamPos_); \n");
        body.append("    }\n");
        body.append("    if(criteria!=null){\n");
        body.append("      criteria.PopulateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("      _min_=criteria.GetMinRowIndex();\n");
        body.append("      _max_=criteria.GetMaxRowIndex();\n");
        body.append("    }\n");
        body
                .append("    ResultSet _rs_=_prepStmt_.ExecuteQuery();\n")
                .append("    int _count_=0;\n")
                .append("    while(_count_<_min_ && _rs_.MoveNext()){\n")
                .append("      _count_++;\n")
                .append("    }\n");
        body
                .append("  if(propertyList==null){\n");
        body
                .append("    while((_max_<0 || _count_<=_max_) && _rs_.MoveNext()){\n")
                .append("      _count_++;\n");
            body.append("      int _col_=1;\n");
        if (pkcolumns.length > 0) {
            body.append("      ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = null;\n");
            body.append("      if(!_isDistinct){\n");
            body.append("        ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");

            for (int i = 0; i < pkcolumns.length; i++) {
                if (i > 0) {
                    body.append(",");
                }
                body.append(
                        entityInfo.getProjectInfo().getConvertSQLToCSharpExpression(pkcolumns[i], ("_rs_." + getPreparedStatementGetterName(pkcolumns[i]) + "(" + (i+1) + ")"))
                );
            }
            body.append(");\n");
            body.append("        _col_=").append(pkcolumns.length+1).append(";\n");
            body.append("      }\n");
        }
        body.append("      ").append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        DBColumn[] allColumns = entityInfo.getColumns(true, true, true);
//        int _col_ = pkcolumns.length + 1;
        for (int i = 0; i < allColumns.length; i++) {
            DBColumn dbColumn = allColumns[i];
            String thisField = null;
            if (JBGenUtils.isSearchableColumn(dbColumn)) {
                thisField = ("_rs_." + getPreparedStatementGetterName(dbColumn) + "(_col_++)");
                DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
                if (factory == null) {
                    body.append("      data.").append(businessAttribute(dbColumn)).append("=(").append(thisField).append(");\n");
                } else {
                    body.append("      data.").append(businessAttribute(dbColumn)).append("=(").append(objectToPrimitive(dbColumn.getConverterFieldName()
                            + ".SqlToBusiness(" +
                            primitiveToObject(thisField, factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType()) + ")"
                            , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(dbColumn.getSqlDataType()).toJavaType())).append(");\n");
                    //businessToSQL
                    //sqlToBusiness
                }
                if (isPrimitive(dbColumn.getBusinessDataType())) {
                    body.append("      if(_rs_.WasNull()){\n");
                    body.append("        data.SetProperty(").append(dbColumn.getFullBeanFieldConstant()).append(",null);\n");
                    body.append("      }\n");
                }
            } else {
                String s = dbColumn.getDAOInfo().getMethodGetDataExtraParamNamesString(false);
                if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                    String s0 = "_tableKey_";
                    if (dbColumn.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
                        RelationDesc relationDesc = new RelationDesc(dbColumn.getGetterImpl().getBody(), dbColumn, entityInfo);
                        String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                        theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage());
                        s0 += ",(" + rpl + ")(propertyList==null?null:(((Object[])propertyList.GetPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[0]))";
                        s0 += ",(Criteria)(propertyList==null?null:(((Object[])propertyList.GetPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[1]))";
                        s0 += ",(OrderList)(propertyList==null?null:(((Object[])propertyList.GetPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[2]))";
                    }
                    if (s.length() > 0) {
                        s = s0 + "," + s;
                    } else {
                        s = s0;
                    }
                }
                thisField = (businessGetterName(dbColumn) + "(" + s + ")");
                body.append("      data.").append(businessAttribute(dbColumn)).append("=(").append(thisField).append(");\n");
            }
        }
        //
        body.append("      list.Add(data);\n");
        body.append("    }\n");
        body.append("  }else{\n");
        body.append("    while((_max_<0 || _count_<=_max_) && _rs_.MoveNext()){\n");
        body.append("      _count_++;\n");
        body.append("      int _col_=1;\n");
        if (pkcolumns.length > 0) {
            body.append("      ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = null;\n");
            body.append("      if(!_isDistinct){\n");
            body.append("        ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");

            for (int i = 0; i < pkcolumns.length; i++) {
                if (i > 0) {
                    body.append(",");
                }
                body.append(
                        entityInfo.getProjectInfo().getConvertSQLToCSharpExpression(pkcolumns[i], ("_rs_." + getPreparedStatementGetterName(pkcolumns[i]) + "(" + (i+1) + ")"))
                );
            }
            body.append(");\n");
            body.append("        _col_=").append(pkcolumns.length+1).append(";\n");
            body.append("      }\n");
        }
        body.append("      ").append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        body.append(JBGenUtils.indent(new CSharpSourceCodeFieldsSwitcher("propertyList.KeySet().GetEnumerator()", CSharpSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
            int rsPos = 0;

            public String getFieldNameCode(DBColumn dbColumn) {
                String thisField = null;
                if (JBGenUtils.isSearchableColumn(dbColumn)) {
                    String code = "";
                    thisField =
                            ("_rs_." + getPreparedStatementGetterName(dbColumn) + "(_col_++)");
                    DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
                    if (factory == null) {
                        code = ("      data." + businessAttribute(dbColumn) + "=(" + thisField + ");\n");
                    } else {
                        code = ("      data." + businessAttribute(dbColumn) + "=(" +
                                objectToPrimitive(dbColumn.getConverterFieldName()
                                        + ".SqlToBusiness(" +
                                        primitiveToObject(thisField, getCSharpClassName(factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType())) + ")"
                                        , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(dbColumn.getSqlDataType()).toJavaType())
                                +
                                ");\n");
                        //businessToSQL
                        //sqlToBusiness
                    }

                    if (isPrimitive(dbColumn.getBusinessDataType())) {
                        code += ("      if(_rs_.WasNull()){\n");
                        code += ("        data.SetProperty(") + (dbColumn.getFullBeanFieldConstant()) + (",null);\n");
                        code += ("      }\n");
                    }
                    return code;
                } else {
                    String s = dbColumn.getDAOInfo().getMethodGetDataExtraParamNamesString(false);
                    if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                        String s0 = "_tableKey_";
                        if (dbColumn.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
                            RelationDesc relationDesc = new RelationDesc(dbColumn.getGetterImpl().getBody(), dbColumn, entityInfo);
                            String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage());
                            s0 += ",(" + rpl + ")(propertyList==null?null:(((Object[])propertyList.GetPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[0]))";
                            s0 += ",(Criteria)(propertyList==null?null:(((Object[])propertyList.GetPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[1]))";
                            s0 += ",(OrderList)(propertyList==null?null:(((Object[])propertyList.GetPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[2]))";
                        }
                        if (s.length() > 0) {
                            s = s0 + "," + s;
                        } else {
                            s = s0;
                        }
                    }
                    thisField =
                            (businessGetterName(dbColumn) + "(" + s + ")");
                    return ("      data." + businessAttribute(dbColumn) + "=(" + thisField + ");\n");
                }
            }
        }.getCode(), 6, false, true));

        body
                .append("      list.Add(data);\n")
                .append("    }\n")
                .append("  }\n")
                .append("  _rs_.Close();\n")
                .append("  _prepStmt_.Close();\n")
                .append("  return list;\n");
        body
                .append("}catch(SqlException sqlExcp){\n")
                .append(JBGenUtils.indent(getLogErrorCode("Select",null, "sqlExcp", "sqlExcp")))
                .append(JBGenUtils.indent("throw new DataRetrievalException(sqlExcp);\n"))
                        //.append("}finally{\n")
                        //.append("  _closeConnection_(_conn_);\n")
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new CSharpParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new CSharpParam("criteria", "Criteria", null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));
        params.add(new CSharpParam("order", "OrderList", null));

        CSharpMethod m = new CSharpMethod("Select", "ICollection",
                (CSharpParam[]) params.toArray(new CSharpParam[params.size()])
//        new CSharpParam[]{
//                new CSharpParam("propertyList", entityInfo.getPropertyListName(), null),
//                new CSharpParam("criteria", "Criteria", null),
//                new CSharpParam("order", "OrderList", null),
//                }
                , "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
    }

    private String getMethodGetSpecificFieldBodyForRELATION_MasterToDetail(DAOInfo entityInfo, DBColumn column) {
//        EntityInfo localEntity = entityInfo;
        // fields ??
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
        String[] localFields = relationDesc.getForeignFields();
        String[] masterFields = relationDesc.getDetailPrimaryFields();
        DBTableInfo masterTable = relationDesc.getDetailTable();


        StringBuilder body = new StringBuilder();
        masterTable.getDAOInfo().checkGenerateFilter(J2eeTarget.MODULE_DAO);

        body.append("  ").append(masterTable.getDAOInfo().getDAOName()).append(" details=new ").append(masterTable.getDAOInfo().getDAOName()).append("();");
        body.append("  details.SetConnection(GetConnection());\n");
        body.append("  details.SetCallerPrincipalName(GetCallerPrincipalName());\n");
        StringBuilder criteriaBuffer = new StringBuilder();
        DAOInfo masterEntityInfo = null;
        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(masterTable.getTableName(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < masterFields.length; i++) {
            DBColumn c = masterEntityInfo.getColumnByFieldName(masterFields[i], true);
            if (i > 0) {
                criteriaBuffer.append(" AND ");
            }
            criteriaBuffer.append(masterTable.getTableName2()).append(".").append(c.getColumnName2());
            criteriaBuffer.append(" = ? ");
        }
        body.append("  Criteria relationCriteria=new Criteria(\"").append(criteriaBuffer.toString()).append("\");\n");
        for (int i = 0; i < localFields.length; i++) {
            DBColumn c = entityInfo.getColumnByFieldName(localFields[i], true);
            body.append("  relationCriteria").append(".").append(getPreparedStatementSetterName(c)).append("(").append(i + 1).append(", ").append(c.getBeanFieldVar()).append(");\n");
        }

        body.append("  return new ArrayList(details.Select(");
        body.append("propertyList,relationCriteria.Merge(criteria),order");
        body.append("));\n");
        return body.toString();
    }

    private String getMethodGetSpecificFieldBodyForRELATION_DetailToMaster(DAOInfo entityInfo, DBColumn column) {
//        EntityInfo localEntity = entityInfo;
        // fields ??
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
        String[] localFields = relationDesc.getForeignFields();
        String[] masterFields = relationDesc.getDetailPrimaryFields();
        DBTableInfo masterTable = relationDesc.getDetailTable();


        StringBuilder body = new StringBuilder();
        masterTable.getDAOInfo().checkGenerateFilter(J2eeTarget.MODULE_DAO);

        body.append("  ").append(masterTable.getDAOInfo().getDAOName()).append(" details=new ").append(masterTable.getDAOInfo().getDAOName()).append("();");
        body.append("  details.SetConnection(GetConnection());\n");
        body.append("  details.SetCallerPrincipalName(GetCallerPrincipalName());\n");
        StringBuilder criteriaBuffer = new StringBuilder();
        DAOInfo masterEntityInfo = null;
        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(masterTable.getTableName(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < masterFields.length; i++) {
            DBColumn c = masterEntityInfo.getColumnByFieldName(masterFields[i], true);
            if (i > 0) {
                criteriaBuffer.append(" AND ");
            }
            criteriaBuffer.append(masterTable.getTableName2()).append(".").append(c.getColumnName2());
            criteriaBuffer.append(" = ? ");
        }
        body.append("  Criteria relationCriteria=new Criteria(\"").append(criteriaBuffer.toString()).append("\");\n");
        for (int i = 0; i < localFields.length; i++) {
            DBColumn c = entityInfo.getColumnByFieldName(localFields[i], true);
            body.append("  relationCriteria").append(".").append(getPreparedStatementSetterName(c)).append("(").append(i + 1).append(", ").append(c.getBeanFieldVar()).append(");\n");
        }

        body.append("  return new ArrayList(details.Select(");
        body.append("propertyList,relationCriteria.Merge(criteria),order");
        body.append("));\n");
        return body.toString();
    }


    private String createBodyDeclareVars(DAOInfo entityInfo) {
        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            CSharpField field = new CSharpField(columns[i].getBeanFieldVar(),
                    columns[i].getSqlDataType(),
                    null,
                    "",
                    null);
            field.addDefaultInitialization();
            stringBuffer.append(field.getSourceCode());
            stringBuffer.append("\n");
            if (field.isPrimitive()) {
                CSharpField field_null = new CSharpField(columns[i].getBeanFieldVar() + "_isNull_",
                        "bool",
                        null,
                        "",
                        null);
                field_null.addDefaultInitialization();
                stringBuffer.append(field_null.getSourceCode());
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    private void addMethodGetAllQBE(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        CSharpParam[] paramsArray = getSelectQBEParams(entityInfo);

        ArrayList _buildCriteriaQBE_Params = new ArrayList();
        _buildCriteriaQBE_Params.add(new CSharpParam("prototype", entityInfo.getDTOName(), null));
        _buildCriteriaQBE_Params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        CSharpParam[] _buildCriteriaQBE_ParamsArray = (CSharpParam[]) _buildCriteriaQBE_Params.toArray(new CSharpParam[_buildCriteriaQBE_Params.size()]);

        body.append("  return Select(propertyList,"+METHOD_TO_CRITERIA+"(");
        body.append(CSharpParam.toCommaSeparatedString(_buildCriteriaQBE_ParamsArray));
        body.append("),order);\n");

        CSharpMethod m = new CSharpMethod("Select", "ICollection",
                paramsArray
                , "public",
                null, null,
                body);
        declareMethod(m, entityInfo, theClass);
    }

    private void addMethodBuildCriteriaQBE(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();

        body
                .append("Criteria criteria=null;\n");
        body
                .append("// building criteria\n");
        body.append("if (prototype != null && prototype.Size() > 0) {\n").append("  StringBuilder whereClause = new StringBuilder();\n").append("  criteria = new Criteria();\n").append("  int pos=1;\n").append(new CSharpSourceCodeFieldsSwitcher("prototype.KeySet().GetEnumerator()", CSharpSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                String sb =
                        "if (whereClause.Length > 1) {\n" +
                                "  whereClause.Append(\" AND \");\n" +
                                "}\n";
                SQLPattern colExpression;

                colExpression = JBGenUtils.getSearchSQL(dbColumn);
                if (colExpression == null) {
                    return "throw new ForbiddenFieldOnSearchException(" + getDataFieldSourceCode(dbColumn.getDAOInfo(), dbColumn) + ");\n";
                }
                String javaColumnValueExpression = entityInfo.getProjectInfo().getConvertCSharpToSQLExpression(dbColumn, "prototype." + businessAttribute(dbColumn) + "");
                String searchCriteriaPattern = dbColumn.getDAOInfo().getSearchCriteriaPattern(dbColumn);

                sb += (getCSharpClassName(dbColumn.getSqlDataType().toJavaType()) + " columnValue = " + javaColumnValueExpression + ";\n");

                StringBuilder sqlExpression = new StringBuilder();
                StringBuilder javaExpression = new StringBuilder();

                StringBuilder currentVar = null;
                for (int i = 0; i < searchCriteriaPattern.length(); i++) {
                    char c = searchCriteriaPattern.charAt(i);
                    switch (c) {
                        case'{': {
                            if (currentVar != null) {
                                throw new IllegalArgumentException("Unexpected '{'");
                            }
                            currentVar = new StringBuilder();
                            break;
                        }
                        case'}': {
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
                                        (vType == null) ? getPreparedStatementSetterName(dbColumn) :
                                                (vType.equals("String")) ? "SetString" :
                                                        (vType.equals("int")) ? "SetInt" :
                                                                (vType.equals("double")) ? "SetDouble" :
                                                                        (vType.equals("Date")) ? "SetDate" :
                                                                                (vType.equals("Time")) ? "SetTime" :
                                                                                        (vType.equals("Timestamp")) ? "SetTimestamp" :
                                                                                                null;
                                if (setter == null) {
                                    throw new NoSuchElementException("Unknown field type " + vType);
                                }
                                sqlExpression.append("?");
                                javaExpression.append("criteria.").append(setter).append("(pos++,").append(vname).append(");\n");
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
                sb += "whereClause.Append(\"" + sqlExpression + "\");\n";
                sb += javaExpression;
                return sb + "break;\n";
            }

            public String getSwitchDefaultCode() {
                return "throw new UnknownFieldException(selectedFieldName);";
            }
        }.getCode(2)).append("\tcriteria.SetWhereClause(whereClause.ToString());\n").append("}\n");
        //TODO c'est koa ca????
        body.append(getUserCode("findAll",entityInfo)).append("\n");

        ArrayList params = new ArrayList();
        params.add(new CSharpParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        body
                .append("return criteria;\n");

        CSharpMethod m = new CSharpMethod(METHOD_TO_CRITERIA, "Criteria",
                (CSharpParam[]) params.toArray(new CSharpParam[params.size()]),
                "private static", // for external use
//                "private",
                null, null,
                body);
        declareMethod(m,entityInfo,theClass);
    }

    public String toString() {
        return "DataAccessObject Generator";
    }

    private String getUserCode(String key, DAOInfo entityInfo) {
        return entityInfo.getUserCodeForDO(key);
    }

    private String getFieldUserCode(String key, String field, DAOInfo entityInfo) {
        return entityInfo.getUserCodeForField(field, key);
    }

//    private String getUserCode(String key, DAOInfo entityInfo) {
//        return entityInfo.getJavaUserCode(toString(), null, key);
//    }


    private String getLogDebugCode(String operation,String prepareExpression, String logExpression) {
        if(prepareExpression!=null){
            prepareExpression=prepareExpression+"\n";
        }else{
            prepareExpression="";
        }
        prepareExpression=prepareExpression+"MDC.Set(\"mdc_entity\",\""+entityInfo.getBeanName()+"\");\n";
        prepareExpression=prepareExpression+"MDC.Set(\"mdc_operation\",\""+operation+"\");\n";
        return getLogCode("Debug",prepareExpression,logExpression,null);
    }

    private String getLogErrorCode(String operation,String prepareExpression, String logExpression, String throwableExpression) {
        if(prepareExpression!=null){
            prepareExpression=prepareExpression+"\n";
        }else{
            prepareExpression="";
        }
        prepareExpression=prepareExpression+"MDC.Set(\"mdc_entity\",\""+entityInfo.getBeanName()+"\");\n";
        prepareExpression=prepareExpression+"MDC.Set(\"mdc_operation\",\""+operation+"\");\n";
        return getLogCode("Error",prepareExpression,logExpression,throwableExpression);
    }

    private String getLogCode(String type, String prepareExpression, String logExpression, String throwableExpression) {
        return getLogCSharpCode(entityInfo,type,J2eeTarget.MODULE_DAO,prepareExpression,"GetCallerPrincipalName()",logExpression,throwableExpression);
    }

}

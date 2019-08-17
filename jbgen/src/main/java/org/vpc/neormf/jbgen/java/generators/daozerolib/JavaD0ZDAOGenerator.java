package org.vpc.neormf.jbgen.java.generators.daozerolib;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.sql.dbsupport.RDBMSSupport;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.log.JavaLogManager;
import org.vpc.neormf.jbgen.java.generators.ejb.JavaSourceCodeFieldsSwitcher;
import org.vpc.neormf.jbgen.java.model.javaclass.*;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import static org.vpc.neormf.jbgen.java.util.JavaUtils.getLogJavaCode;
import org.vpc.neormf.jbgen.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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
public class JavaD0ZDAOGenerator extends JBGenDAOGenerator {

    public JavaD0ZDAOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (
//                entityInfo.doGenerateTables("table")
//                        &&
                        entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
                        && entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
        )

                ;
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

    JavaClassSource theClass = null;

    DAOInfo entityInfo = null;
    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        this.entityInfo=entityInfo ;
        JBGenProjectInfo moduleCodeStyle = entityInfo.getProjectInfo();
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_DAO));
        theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public ");
        theClass.setName(entityInfo.getDAOName());
        theClass.addAllImports(new String[]{
                "java.util.*",

                "java.sql.*",

                //"java.rmi.RemoteException",

                entityInfo.getDataPackage() + ".*"
        });
        theClass.setPackage(entityInfo.getDAOPackage());


        JavaUtils.initializeClassLog(entityInfo,J2eeTarget.MODULE_DAO,theClass);

        theClass.addProperty("connection", "Connection",null);
        theClass.addProperty("callerPrincipalName", "String",null);

        if (cache != null) {
            theClass.addField(new JavaField("_cache_", "DataCache", null, "private static final", "new DataCache(" + cache.getMaxExpression() + "," + cache.getTimeoutExpression() + ")"));
            theClass.addField(new JavaField("_cacheProperties_", entityInfo.getPropertyListName(), null, "private static final", "null"));
            theClass.addMethod(new JavaMethod("getCachedData", entityInfo.getDTOName(), new JavaParam[]{new JavaParam("key", entityInfo.getDataKeyName(), null)}, "public", new String[]{"SQLException"}, "cache",
                    entityInfo.getDTOName() + " data=(" + entityInfo.getDTOName() + ") _cache_.get(key);\n" +
                            "if(data==null){\n" +
                            "  data=getData(_cacheProperties_,key);\n" +
                            "  _cache_.add(data);\n" +
                            "}\n" +
                            "return data;\n"
            ));
        }

        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        for (int i = 0; i < columns.length; i++) {
            DataTypeConverterFactory factory = columns[i].getSqlConverterFactory();
            if (factory != null) {
//                theClass.addField(new JavaField(columns[i].getBeanFieldName() + "_converter_",
//                        "org.vpc.neormf.commons.types.converters.DataTypeConverter", null, "final private static", factory.getConverterExpression()));
            }
        }
        createConstructor0(entityInfo);
        createConstructor1(entityInfo);

        if (entityInfo.isUpdatable()) {
            addMethodInsert(entityInfo);
            addMethodUpdate(entityInfo);
            addMethodDelete(entityInfo);
            addMethodDeleteAll(entityInfo);
        }

        if (entityInfo.getPrimaryKeys().length > 0) {
            addMethodSelect(entityInfo);
        }

        addMethodSelectAll(entityInfo);
        addMethodSelectAll2(entityInfo);

        DBColumn[] extraColumns = entityInfo.getColumns(false, false, true);
        for (int i = 0; i < extraColumns.length; i++) {
            theClass.addMethod(createMethodGetSpecificField(entityInfo, extraColumns[i]));
        }

        entityInfo.setGeneratedClass("DataAccessObject", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating DataAccessObject Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

//    private JavaMethod createSingletonFinder(String name, JavaParam[] params, EntityInfo entityInfo) {
//        Vector vparams = new Vector();
//        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//        StringBuffer p = new StringBuffer();
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                vparams.add(params[i]);
//                if (i > 0) {
//                    p.append(",");
//                }
//                p.append(params[i].getName());
//            }
//        }
//
//
//        return new JavaMethod("ejbHomeGet" + JBGenUtils.toJavaIdentifier(name, true), entityInfo.getDataContentName(),
//                (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]),
//                "public",
//                new String[]{"FinderException"}, null,
//
//                entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
//                "try{" +
//                "  " + entityInfo.getEntityRemoteName() + " src=home.find" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
//                "  return src.getData(propertyList" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ");\n" +
//                "} catch (RemoteException e) {\n" +
//                "    throw new FinderException(e.getMessage());\n" +
//                "}");
//    }

    private String createBodyUpdate(DAOInfo entityInfo) {
        Map map = getFieldsHashcodeMapping(entityInfo.getColumns(false, true, false));
        StringBuilder methodCorps = new StringBuilder();
        methodCorps.append("for(Iterator i=data.keySet().iterator();i.hasNext();){\n");
        methodCorps.append("  String selectedFieldName=(String)i.next();\n");
        methodCorps.append("  int selectedFieldId=selectedFieldName.hashCode();\n");
        methodCorps.append("  switch(selectedFieldId){\n");
        for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            methodCorps.append("    case ").append(((Integer) entry.getKey()).intValue()).append(":{\n");
            if (entry.getValue() instanceof DBColumn) {
                DBColumn c = (DBColumn) entry.getValue();
//                String fid = Utils.capitalize(f.name);
                methodCorps.append("         //field ").append(c.getBeanFieldVar()).append("\n");
                DataTypeConverterFactory factory = c.getSqlConverterFactory();
                if (factory == null) {
                    methodCorps.append("         ").append(c.getBeanFieldVar()).append("=(data.").append((JavaUtils.businessGetterName(c))).append("());\n");
                } else {
                    methodCorps.append("      ");
                    methodCorps.append(c.getBeanFieldVar());
                    methodCorps.append("=(");
                    methodCorps.append(JavaUtils.objectToPrimitive(c.getConverterFieldName()
                            + ".businessToSQL(" +
                            JavaUtils.primitiveToObject("data." + (JavaUtils.businessGetterName(c)) + "()", factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(c.getSqlDataType()).toJavaType().getName()) + ")"
                            , factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName()));
                    methodCorps.append(");\n");
                    //businessToSQL
                    //sqlToBusiness
                }

            } else {
                Vector v = (Vector) entry.getValue();
                for (int j = 0; j < v.size(); j++) {
                    DBColumn c = (DBColumn) v.get(j);
                    String ifEleif = (j == 0) ? "if" : "}else if";
                    methodCorps.append("         ").append(ifEleif).append("(\"").append(c.getBeanFieldVar()).append("\".equals(selectedFieldName)){\n");
                    methodCorps.append("         //field ").append(c.getBeanFieldVar()).append("\n");
                    methodCorps.append("         ").append(c.getBeanFieldVar()).append("=(data.").append(JavaUtils.businessGetterName(c)).append("());\n");
                }
                methodCorps.append("         }else{\n");
                methodCorps.append("            throw new SQLException(\"UnknownFieldException \"+selectedFieldName);");
                methodCorps.append("         }");
            }
            methodCorps.append("        break;\n");
            methodCorps.append("    }\n");

        }
        methodCorps.append("    default:{\n");
        methodCorps.append("      throw new SQLException(\"UnknownFieldException\"+selectedFieldName);");
        methodCorps.append("    }\n");
        methodCorps.append("  }\n");
        methodCorps.append("}\n");
        return methodCorps.toString();
    }

    private JavaMethod addMethodUpdate(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false);
        body
                .append("if(data.size()==0){\n")
                .append("  return;\n")
                .append("}\n");

        body.append("// START Prologue Cheking\n");
        for (int i = 0; i < allColumns.length; i++) {
            if (allColumns[i].isForbiddenOnUpdate()) {
                body.append("  if(data.contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("    throw new SQLException(\"ForbiddenFieldOnUpdateException ").append(JavaUtils.getDataFieldSourceCode(entityInfo, allColumns[i])).append(");\n");
                body.append("  }\n");
            }
        }
        body.append("// END   ForbiddenFieldOnUpdate Cheking\n");
        body.append("\n");
        body.append(getUserCode("preUpdate", entityInfo));
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        if (cache != null) {
            body.append("\n// remove cache\n");
            body.append("_cache_.remove(data.getDataKey());\n\n");
        }


        body.append("Connection  _conn_;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");
        body.append("  StringBuffer _statement_ = new StringBuffer();\n");
        body.append("  int _ucount_;\n");
        body.append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  boolean _firstColumn_=true;\n");
            body.append("  _statement_.append( \"");
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
                            .append("  if(data.").append(JavaUtils.method(c, "contains")).append("()){\n")
                            .append("    if(_firstColumn_){\n")
                            .append("      _firstColumn_=false;\n")
                            .append("    }else{\n")
                            .append("      _statement_.append(\", \");\n")
                            .append("    }\n")
                            .append("    _statement_.append(\"").append(c.getColumnName2()).append("=? \");\n")
                            .append("  }\n");
                }
//                if (c.getPkPosition() < 0) {
//                    if (first) {
//                        first = false;
//                    } else {
//                        body
//                                .append(", ");
//                    }
//                    body
//                            .append(c.getColumnName());
//                    body
//                            .append(" = ? ");
//                }
            }

            body
                    .append("_statement_.append(\" WHERE ");
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
            prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    prepareLog.append("  if(data.").append(JavaUtils.method(c, "contains")).append("()){\n");
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(c, "data." + JavaUtils.businessGetterName(c) + "()"))
                            .append(");\n");
                    prepareLog.append("  }\n");
                }
            }
            pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "data." + JavaUtils.businessGetterName(pkColumns[c.getPkPosition()]) + "()"))
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG,prepareLog.toString(), "\"<UPDATE-DATA> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_statement_.toString(),_logElements_)",null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_statement_.toString());\n");
            body
                    .append("  int _pos_=0;\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    body
                            .append("  if(data.").append(JavaUtils.method(c, "contains")).append("()){\n")
                            .append("    ")
                            .append(JavaUtils.getPreparedStatementSetterExpression(c,"_prepStmt_","++_pos_",("data." + JavaUtils.businessGetterName(c) + "()")))
                            .append("\n")
                            .append("  }\n");
                }
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    body
                            .append("  ")
                            .append(JavaUtils.getPreparedStatementSetterExpression(c,"_prepStmt_","++_pos_",("data." + JavaUtils.businessGetterName(c) + "()")))
                            .append("\n");
                }
            }

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new SQLException(\"UpdateDataException\");\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postUpdate", entityInfo));
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null,"sqlExcp"))
                .append("  throw new SQLException(\"UpdateDataException \"+sqlExcp);\n")
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        return declareMethod(new JavaMethod("update", "void", new JavaParam[]{
                new JavaParam("data", entityInfo.getDTOName(), null)
        }, "public",
                new String[]{"SQLException"}, null,
                body),
                entityInfo, theClass
        );
    }


    private JavaMethod addMethodDelete(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        if (cache != null) {
            body.append("// remove cache\n");
            body.append("_cache_.remove(key);\n\n");
        }
        body
                .append("Connection  _conn_;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append(getUserCode("preDelete", entityInfo));
        body.append("  int _ucount_;\n")
                .append("  String _statement_ ;\n")
                .append("  PreparedStatement _prepStmt_;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {

            body.append("  _statement_ = \"");
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
            body.append("  _prepStmt_ = _conn_.prepareStatement(_statement_);\n");
            int pos = 1;
            DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    body.append("  ");
                    body.append(JavaUtils.getPreparedStatementSetterExpression(c,"_prepStmt_",pos,("(key." + (JavaUtils.businessGetterName(pkColumns[c.getPkPosition()])) + "())")))
                    .append("\n");
                    pos++;
                }
            }

            // TODO ajouter le code
            body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<REMOVE> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_statement_,null)",null));

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new SQLException(\"RemoveDataException\");\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postDelete", entityInfo));

        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new SQLException(\"RemoveDataException \"+sqlExcp);\n")
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        return declareMethod(new JavaMethod("delete", "void", new JavaParam[]{
                new JavaParam("key", entityInfo.getDataKeyName(), null)
        }, "public",
                new String[]{
                        "SQLException"
                }, null,
                body),
                entityInfo,
                theClass
        );
    }

    private JavaMethod createConstructor0(final DAOInfo entityInfo) {
        JavaMethod m = new JavaMethod(theClass.getName(),
                null,
                new JavaParam[0]
                , "public",
                new String[]{
                }, "DataAccessObject Constructor",
                "super();");
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }

    private JavaMethod createConstructor1(final DAOInfo entityInfo) {
        JavaMethod m = new JavaMethod(theClass.getName(),
                null,
                new JavaParam[]{new JavaParam("con","Connection",null)}
                , "public",
                new String[]{
                }, "DataAccessObject Constructor",
                "super();\nsetConnection(con);");
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }


    private JavaMethod addMethodInsert(final DAOInfo entityInfo) throws SQLException{
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
                body.append("if(data.contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("  throw new SQLException(\"ForbiddenFieldOnInsertException ").append(allColumns[i].getBeanFieldName()).append("\");\n");
                body.append("}\n");
            } else if (allColumns[i].isRequiredOnInsert()) {
                body.append("if(!data.contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("  throw new SQLException(\"RequiredFieldOnInsertException ").append(allColumns[i].getBeanFieldName()).append("\");\n");
                body.append("}\n");
            }
        }
        body.append("// END  Prologue checking\n");
        body.append("\n");
        body
                .append("Connection  _conn_=null;\n")
                .append("String _statement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        DBColumn[] keys = entityInfo.getColumns(true, false, false);
        if (entityInfo.isAutoDentity()) {
            RDBMSSupport rdbmsSupport = entityInfo.getProjectInfo().getRdbmsSupport();
            if (rdbmsSupport.getType()==RDBMSSupport.SEQUENCE) {
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
                String sequenceName = entityInfo.getSequenceName();
                body.append("  String _selectNewIdStatement_ = \"").append(rdbmsSupport.getSequenceString(entityInfo)).append("\";\n");

                body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<Sequence Handling Query> \"+org.vpc.neormf.commons.sql.SQLUtils.LogQuery(_selectNewIdStatement_)",null));

                body.append("  _prepStmt_ = _conn_.prepareStatement(_selectNewIdStatement_);\n");
                body.append("  ResultSet _rs_=_prepStmt_.executeQuery();\n");
                body.append("  if(_rs_.next()){\n");
                body.append("    data.").append(JavaUtils.businessSetterName(keys[0])).append("(").append(JavaUtils.getPreparedStatementGetterExpression(keys[0], "_rs_", 1)).append(");\n");
                body.append("  }\n");
                body.append("  _rs_.close();\n");
                body.append("  _prepStmt_.close();\n");
                body.append("  // END Sequence Handling\n");
            }
        } else {
            for (int i = 0; i < keys.length; i++) {
                DBColumn dbColumn = keys[i];
                body.append("  ").append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(dbColumn, "(data." + (JavaUtils.businessGetterName(dbColumn)) + "())")).append(";\n");
            }
        }

        body.append("\n");
        body.append(getUserCode("preInsert", entityInfo));

        body.append("  // START Local Fields Updates\n");
        body.append(new JavaSourceCodeFieldsSwitcher("data.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (dbColumn.isFormulaImpl()) {
//                    return "// do nothing ; " + dbColumn.getBeanFieldName() + " is a view field";
                    StringBuilder sb = new StringBuilder("// " + dbColumn.getBeanFieldName() + " is a view field\n");
                    if (dbColumn.isForbiddenOnInsert()) {
                        sb.append("throw new SQLException(\"ForbiddenFieldOnInsertException \" + ").append(JavaUtils.getDataFieldSourceCode(entityInfo, dbColumn)).append(");\n");
                    } else {
                        sb.append("break;");
                    }
                    return sb.toString();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(entityInfo.getUserCodeForField(dbColumn.getBeanFieldName(),"preInsert"));
                    //TODO added taha 2005/08/15
                    sb.append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(dbColumn, "(data." + (JavaUtils.businessGetterName(dbColumn)) + "())")).append(";\n");
                    sb.append(entityInfo.getUserCodeForField(dbColumn.getBeanFieldName(),"postInsert"));
                    sb.append("break;");
                    return sb.toString();
                }
            }

            public String getSwitchDefaultCode() {
                return "throw new SQLException(\"UnknownFieldException\" + selectedFieldName);";
            }
        }.getCode());
        body.append("  // END   Local Fields Updates\n");
        body.append("\n");
        body.append("  // START Database persistance\n");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  _statement_ = \"");
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
                            .append(c.getColumnName());
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
            prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
            for (int i = 0; i < bpkcolumns.length; i++) {
                DBColumn c = bpkcolumns[i];
                if (!c.isAutoIdentity()) {
                    prepareLog
                            .append("    _logElements_.add(")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            for (int i = 0; i < ucolumns.length; i++) {
                DBColumn c = ucolumns[i];
                if (!c.isAutoIdentity()) {
                    prepareLog.append("  _logElements_.add(")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<INSERT> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_statement_,_logElements_)",null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_statement_);\n");
            int pos = 1;
            for (int i = 0; i < bpkcolumns.length; i++) {
                DBColumn c = bpkcolumns[i];
                if (!c.isAutoIdentity()) {
                    body
                            .append("  ")
                            .append(JavaUtils.getPreparedStatementSetterExpression(c,"_prepStmt_",pos,c.getBeanFieldVar()))
                            .append("\n");
                    pos++;
                }
            }
            for (int i = 0; i < ucolumns.length; i++) {
                DBColumn c = ucolumns[i];
                if (!c.isAutoIdentity()) {
                    body
                            .append("  ")
                            .append(JavaUtils.getPreparedStatementSetterExpression(c,"_prepStmt_",pos,c.getBeanFieldVar()))
                            .append("\n");
                    pos++;
                }
            }

            body
                    .append("  _prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n");
        }
        body.append("\n");

        if(entityInfo.isAutoDentity()){
            RDBMSSupport rdbmsSupport = entityInfo.getProjectInfo().getRdbmsSupport();
            if (rdbmsSupport.getType()==RDBMSSupport.AUTO_INCREMENT) {
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

                body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<Sequence Handling Query> \"+org.vpc.neormf.commons.sql.SQLUtils.LogQuery(_selectNewIdStatement_)",null));

                body.append("  _prepStmt_ = _conn_.prepareStatement(_selectNewIdStatement_);\n");
                body.append("  ResultSet _rs_=_prepStmt_.executeQuery();\n");
                body.append("  if(_rs_.next()){\n");
                //            body.append("    this.").append(keys[0].getBeanFieldVar()).append("= _rs_."+keys[0].getPreparedStatementGetterName()+"(1);\n");
                body.append("    data.").append(JavaUtils.businessSetterName(keys[0])).append("(").append(JavaUtils.getPreparedStatementGetterExpression(keys[0], "_rs_", 1)).append(");\n");
                //            body.append("    data.setOrderId(newId);\n");
                body.append("  }\n");
                body.append("  _rs_.close();\n");
                body.append("  _prepStmt_.close();\n");
                body.append("  // END Sequence Handling\n");
            }
        }


        body.append(getUserCode("postInsert", entityInfo));
        body.append("  // END   Database persistance\n");
        body.append("\n");
        body
                .append("  // returning Identifier;\n").append("  return data.get").append(entityInfo.getDataKeyName()).append("();\n")
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new SQLException(\"CreateDataException \"+sqlExcp);\n")
                .append("}catch(RuntimeException rtmExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "rtmExcp"))
                .append("  throw rtmExcp;\n")
//                .append("}finally{\n")
//                .append("  //_closeConnection_(_conn_);\n")
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("data", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodCreateDataExtraParams()));
        JavaMethod m = new JavaMethod("insert", entityInfo.getDataKeyName(),
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
                , "public",
                new String[]{
                        "SQLException"
                }, "DataAccessObject Constructor",
                body);
//        String roles=(entityInfo.getMethodPatternValue(theClass,m,"roles",null);

        return declareMethod(m, entityInfo, theClass);
    }

    private static JavaMethod declareMethod(JavaMethod m, DAOInfo entityInfo, JavaClassSource theClass) {
        JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
        JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }


    //TODO invalid
    private String getMethodGetSpecificFieldBodyForSQL_VIEW(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("Connection  _conn_=null;\n")
                .append("String _statement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("ResultSet _rs_  = null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append("  _statement_=\"Select (").append(JBGenUtils.getSearchSQL(column).getQuery()).append(") FROM ").append(entityInfo.getTables()[0].getTableName2()).append(" WHERE ");
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
                    .append(c.getColumnName())
                    .append(" = ")
                    .append("?");
        }
        body.append("\";\n");
        body.append("  _prepStmt_ = _conn_.prepareStatement(_statement_);\n");
        int pos = 1;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("  .")
                        .append(JavaUtils.getPreparedStatementSetterExpression(c,"_prepStmt_",pos,c.getBeanFieldVar()))
                        .append("\n");
                pos++;
            }
        }

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "_statement_",null));

        body
                .append("  _rs_=_prepStmt_.executeQuery();\n")
                .append("  if(_rs_.next()){\n")
                .append("    return ")
                .append(JavaUtils.getPreparedStatementGetterExpression(column,"_rs_",1))
                .append(";\n");
        body
                .append("  }else{\n")
                .append("    throw new SQLException(\"DataNotFoundException\");\n")
                .append("  }\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new SQLException(\"DataRetrievalException \"+sqlExcp);\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_rs_!=null){\n")
                .append("      _rs_.close();\n")
                .append("      _prepStmt_.close();\n")
                .append("    }\n")
//                .append("    _closeConnection_(_conn_);\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new SQLException(\"DataRetrievalException \" +e);\n")
                .append("  }\n")
                .append("}");
        return body.toString();
    }

    //TODO invalid
    private String getMethodGetSpecificFieldBodyForSQL_SQL_CALL(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("Connection  _conn_=null;\n")
                .append("String _call_ = null;\n")
                .append("java.sql.CallableStatement _callStmt_  = null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");
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
                    JavaParam[] javaParams = entityInfo.getMethodGetDataExtraParams();
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
                    body.append("  _callStmt_.").append(JavaUtils.getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(javaParams[javaParamsIndex].getName()).append(");\n");
                } else {
                    type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                    body.append("  _callStmt_.").append(JavaUtils.getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(c.getBeanFieldVar()).append(");\n");
                }
            } else {
                // do nothing
            }
        }
        if (retPos < 0) {
            throw new IllegalArgumentException("Expected {RETURN}");
        }
        SQLPattern.SQLCallParam p = sqlCallParams[retPos];
        body.append("  return _callStmt_.").append(JavaUtils.getPreparedStatementMethodGetterName(column.getSqlType())).append("(").append(p.getPos()).append(");\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append("  throw new DataRetrievalException(sqlExcp);\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_callStmt_!=null){\n")
                .append("      _callStmt_.close();\n")
                .append("    }\n")
//                .append("    _closeConnection_(_conn_);\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new DataRetrievalException(e);\n")
                .append("  }\n")
                .append("}\n");
        return body.toString();
    }

    private JavaMethod createMethodGetSpecificField(DAOInfo entityInfo, DBColumn column) {
        String body = null;
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
                body = getMethodGetSpecificFieldBodyForRELATION(entityInfo, column);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unhandled Formula type " + fieldFormulaImpl.getTypeName());
            }
        }
        ArrayList ep = new ArrayList();
        StringBuilder prefixCode = new StringBuilder();
        if (entityInfo.getPrimaryKeys().length > 0) {
            ep.add(new JavaParam("tableKey", entityInfo.getDataKeyName(), null));
            if (column.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
                RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
                String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
                ep.add(new JavaParam("propertyList", rpl, null));
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getFullDataFilterName());
                ep.add(new JavaParam("criteria", relationDesc.getDetailTable().getDAOInfo().getDataFilterName(), null));
            }
            DBColumn[] k = entityInfo.getPrimaryKeys();
            for (int i = 0; i < k.length; i++) {
                prefixCode.append(k[i].getBusinessDataTypeName()).append(" ").append(k[i].getBeanFieldVar()).append("=tableKey.").append(JavaUtils.businessGetterName(k[i])).append("();\n");
            }
        }
        ep.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        return new JavaMethod(JavaUtils.businessGetterName(column),
                column.getBusinessDataTypeName(),
                (JavaParam[]) ep.toArray(new JavaParam[ep.size()]),
                "public",
                new String[]{"SQLException"},
                null,
                prefixCode + body);

    }


    private JavaMethod addMethodSelectAll2(DAOInfo entityInfo) {
        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));

        JavaMethod m = new JavaMethod("select", entityInfo.getDTOName()+"[]",
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
                , "public",
                new String[]{"SQLException"}, null,
                "return select(propertyList,("+entityInfo.getDataFilterName()+")null);"
                );
        declareMethod(m, entityInfo, theClass);
        return m;

    }

    private JavaMethod addMethodSelect(DAOInfo entityInfo) {

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
                    .append(c.getColumnName())
                    .append(" = ")
                    .append("?");
        }
        body.append("\";\n");
        body.append(entityInfo.getDataFilterName()).append(" criteria=new ").append(entityInfo.getDataFilterName()).append("();\n");
        body.append("criteria.setWhereClause(where);\n");

        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            body.append("criteria.");
            body.append("set");
            body.append(JavaUtils.getCriteriaSetterName(c.getBusinessDataType())).append("(").append(i + 1).append(",").append("primaryKey.").append(JavaUtils.businessGetterName(c)).append("());\n");
        }

        body.append(entityInfo.getDTOName()).append("[] _found_=select(propertyList,criteria);\n")
                .append("if(_found_.length>0){\n")
                .append("  return _found_[0];\n")
                .append("}\n")
                .append("return null;\n");

        JavaMethod m = new JavaMethod("select", entityInfo.getDTOName(), new JavaParam[]{
                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
                new JavaParam("primaryKey", entityInfo.getDataKeyName(), null)
        }, "public",
                new String[]{"SQLException"}, null,
                body);
        declareMethod(m, entityInfo, theClass);
        return m;
    }


    private JavaMethod addMethodSelectAll(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append("  StringBuffer _statement_ = new StringBuffer(\"SELECT \");\n");

        body.append("  if(criteria!=null && criteria.isDistinct()){\n");
        body.append("    _statement_.append(\"DISTINCT \");\n");
        body.append("  }\n");
//        body.append("  ArrayList $sqlStatementParamsProviderArrayList$=new ArrayList();\n");
        body.append("  if(propertyList==null){\n");
        ArrayList a = new ArrayList();
        a.addAll(Arrays.asList(entityInfo.getColumns(true, false, false)));
        a.addAll(Arrays.asList(entityInfo.getColumns(true, true, true)));
        DBColumn[] allpkPlusColumns = (DBColumn[]) a.toArray(new DBColumn[a.size()]);

        boolean added = false;
        body.append("    _statement_.append(\"");
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
                postBody.append(JBGenUtils.indent(JavaUtils.getPopulateSqlStatementParamsProviderArrayList(column, "_sqlStatementParamsProviderArrayList_", "statement")));

            } else {
//                throw new IllegalArgumentException("Unsupported type "+fieldFormulaImpl.getTypeName());
            }
        }
        body.append("\");\n");
        if (postBody.length() > 0) {
            body.append(postBody);
        }
        body.append("  }else{\n");
        body.append("    StringBuffer sb=new StringBuffer(\"");
        DBColumn[] pkcolumns = entityInfo.getColumns(true, false, false);
        for (int i = 0; i < pkcolumns.length; i++) {
            DBColumn c = pkcolumns[i];
            if (i > 0) {
                body.append(",");
            }
            body.append(c.getColumnName());
        }
        body.append("\");\n");
        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("propertyList.propertiesIterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (JBGenUtils.isSearchableColumn(dbColumn)) {
                    return
                            "if (sb.length() > 0) {\n" +
                                    "  sb.append(\" , \");\n" +
                                    "}\n" +
                                    "sb.append(\"" +
                                    JBGenUtils.getSearchSQL(dbColumn).getQuery() +
                                    "\");\n" +
                                    JavaUtils.getPopulateSqlStatementParamsProviderArrayList(dbColumn, "_sqlStatementParamsProviderArrayList_", "statement");
                } else {
                    return "// do nothing; query type = " + dbColumn.getGetterImpl().getTypeName();
                }
            }
        }.getCode(), 4, false, true));
        body.append("    _statement_.append(sb.toString());\n");
        body.append("  }\n");

        body
                .append("  _statement_.append(\" FROM ");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            if (t > 0) {
                body.append(",");
            }
            body.append(dbTables[t].getTableName2());
        }
        body.append("\");\n");
        body.append("  if(criteria!=null && criteria.getJoins()!=null){\n");
        body.append("    _statement_.append(\" \");\n");
        body.append("    _statement_.append(criteria.getJoins());\n");
        body.append("  }\n");
        DBColumn[] pk = entityInfo.getPrimaryKeys();
        boolean first = true;
        if (dbTables.length > 1) {
            body.append("    _statement_.append(\"");
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
                    body.append(pki[p].getColumnName());
                    body.append("=");
                    body.append(dbTables[0].getTableName2());
                    body.append(".");
                    body.append(pk[p].getColumnName());
                }
            }
            body.append("\");\n");
            body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
            body.append("    _statement_.append(\" AND \");\n");
            body.append("    _statement_.append(criteria.getWhereClause());\n");
            body.append("  }\n");
        } else {
            body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
            body.append("    _statement_.append(\" WHERE \");\n");
            body.append("    _statement_.append(criteria.getWhereClause());\n");
            body.append("  }\n");
        }

        body.append("  if(criteria!=null && criteria.getOrderCount()>0){\n");
        body.append("    _statement_.append(\" ORDER BY \");\n");

        body.append("    boolean orderFirst=true;\n");
        body.append("    int orderIteratorIndex=0;\n");

        JavaSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
                new JavaSourceCodeFieldsSwitcher("criteria.getOrderIterator()",
                        "boolean asc=criteria.isOrderAscendent(orderIteratorIndex++);\n" +
                        "String selectedFieldName=(String) i.next();"
                        , false, entityInfo.getColumns(true, true, true)) {
                    public String getFieldNameCode(DBColumn dbColumn) {
                        StringBuilder sb = new StringBuilder();
                        if (JBGenUtils.isSearchableColumn(dbColumn)) {
                            sb.append("if(orderFirst){\n");
                            sb.append("  orderFirst=false;\n");
                            sb.append("}else{\n");
                            sb.append("  _statement_.append(\" , \");\n");
                            sb.append("}\n");
                            sb.append("_statement_.append(\"").append(JBGenUtils.getSearchSQL(dbColumn).getQuery()).append("\");\n");
                            sb.append("_statement_.append(\" \").append(asc?\"ASC\":\"DESC\");\n");
                            sb.append("break;");
                        } else {
                            sb.append("throw new SQLException(\"ForbiddenFieldOnSearchException ").append(dbColumn.getBeanFieldName()).append("\");\n");
                        }
                        return sb.toString();
                    }
                };
        sourceCodeFieldsSwitcher.setSwitchDefaultCode(new StringBuilder()
                .append("//WHEN UNKNOWN FIELD PASSED AS IS TO SQL\n")
                .append("if(orderFirst){\n")
                .append("  orderFirst=false;\n")
                .append("}else{\n")
                .append("  _statement_.append(\" , \");\n")
                .append("}\n")
                .append("_statement_.append(selectedFieldName);\n")
                .append("_statement_.append(\" \").append(asc?\"ASC\":\"DESC\");\n")
                .append("break;")
                .toString());
        sourceCodeFieldsSwitcher.setAcceptFieldColumnNames(entityInfo.isAcceptColumnNamesInOrderClause());
        body.append(sourceCodeFieldsSwitcher.getCode(4));
        body.append("  }\n");
//        body.append("if(order!=null){\n");
//        body.append("  _statement_.append(\" ORDER BY \");\n");
//        body.append("  boolean orderFirst=true;\n");
//        body.append("  for(Iterator i=order.iterator();i.hasNext();){\n");
//        body.append("    if(orderFirst){\n");
//        body.append("      orderFirst=false;\n");
//        body.append("    }else{\n");
//        body.append("      _statement_.append(\" , \");\n");
//        body.append("    }\n");
//        body.append("    OrderList.OrderItem item=(OrderList.OrderItem) i.next();\n");
//        body.append("    DTOFieldMetaData _dataField_=").append(entityInfo.getDataContentName()).append(".INFO.getField(item.getFieldName());\n");
//        body.append("    if(_dataField_!=null){\n");
//        body.append("      _statement_.append(_dataField_.getColumnName());\n");
//        body.append("    }else{\n");
//        body.append("      _statement_.append(item.getFieldName());\n");
//        body.append("    }\n");
//        body.append("    _statement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
//        body.append("  }\n");
//        body.append("}\n");

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<QUERY> \"+_statement_\n"
                + "                 +\"\\n          Criteria =\"+criteria\n"
                + "                 +\"\\n          Order    =\"+order"
        ,null));

        body.append("  ArrayList list=new ArrayList();\n");
        body.append("  PreparedStatement _prepStmt_ = _conn_.prepareStatement(_statement_.toString());\n");
        body.append("  int _min_=-1;\n");
        body.append("  int _max_=-1;\n");
        body.append("  int _statementParamPos_=1;\n");
//        body.append("    for(Iterator $i$=$sqlStatementParamsProviderArrayList$.iterator();$i$.hasNext();){\n");
//        body.append("      SqlStatementParamsProvider $p$=(SqlStatementParamsProvider) $i$.next(); \n");
//        body.append("      $statementParamPos$+=$p$.populateStatement(_prepStmt_,$statementParamPos$); \n");
//        body.append("    }\n");
        body.append("  if(criteria!=null){\n");
        body.append("    criteria.populateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("    _min_=criteria.getMinRowIndex();\n");
        body.append("    _max_=criteria.getMaxRowIndex();\n");
        body.append("  }\n");
        body
                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
                .append("  int _count_=0;\n")
                .append("  while(_count_<_min_ && _rs_.next()){\n")
                .append("    _count_++;\n")
                .append("  }\n");
        body
                .append("  if(propertyList==null){\n");
        body
                .append("    while((_max_<0 || _count_<=_max_) && _rs_.next()){\n")
                .append("      _count_++;\n");
        if (pkcolumns.length > 0) {
            body.append("      ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");
            for (int i = 0; i < pkcolumns.length; i++) {
                if (i > 0) {
                    body.append(",");
                }
                body.append(JavaUtils.getPreparedStatementGetterExpression(pkcolumns[i],"_rs_",(i + 1) ));
            }
            body.append(");\n");
        }
        body.append("      ").append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        DBColumn[] allColumns = entityInfo.getColumns(true, true, true);
        int _col_ = pkcolumns.length + 1;
        for (int i = 0; i < allColumns.length; i++) {
            DBColumn dbColumn = allColumns[i];
            String thisField = null;
            if (JBGenUtils.isSearchableColumn(dbColumn)) {
                thisField = JavaUtils.getPreparedStatementGetterExpression(dbColumn,"_rs_",(_col_++));
                DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
                if (factory == null) {
                    body.append("      data.").append(JavaUtils.businessSetterName(dbColumn)).append("(").append(thisField).append(");\n");
                } else {
                    body.append("      data.").append(JavaUtils.businessSetterName(dbColumn)).append("(").append(JavaUtils.objectToPrimitive(dbColumn.getConverterFieldName()
                            + ".sqlToBusiness(" +
                            JavaUtils.primitiveToObject(thisField, factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName()) + ")"
                            , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(dbColumn.getSqlDataType()).toJavaType().getName())).append(");\n");
                    //businessToSQL
                    //sqlToBusiness
                }
            } else {
                String s = dbColumn.getDAOInfo().getMethodGetDataExtraParamNamesString(false);
                if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                    String s0 = "_tableKey_";
                    if (dbColumn.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
                        RelationDesc relationDesc = new RelationDesc(dbColumn.getGetterImpl().getBody(), dbColumn, entityInfo);
                        String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                        theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
//                        s0+=",("+rpl+")((Object[])propertyList.getPropertyConstraints("+(dbColumn.getEntityInfo().getPropertyListName())+"."+dbColumn.getBeanFieldConstant()+"))[0]";
//                        s0+=",(Criteria)((Object[])propertyList.getPropertyConstraints("+(dbColumn.getEntityInfo().getPropertyListName())+"."+dbColumn.getBeanFieldConstant()+"))[1]";
//                        s0+=",(OrderList)((Object[])propertyList.getPropertyConstraints("+(dbColumn.getEntityInfo().getPropertyListName())+"."+dbColumn.getBeanFieldConstant()+"))[2]";
                        s0 += ",(" + rpl + ")null,("+relationDesc.getDetailTable().getDAOInfo().getDataFilterName()+")null";
                    }
                    if (s.length() > 0) {
                        s = s0 + "," + s;
                    } else {
                        s = s0;
                    }
                }
                thisField = (JavaUtils.businessGetterName(dbColumn) + "(" + s + ")");
                body.append("      data.").append(JavaUtils.businessSetterName(dbColumn)).append("(").append(thisField).append(");\n");
            }
        }
        //
        body.append("      list.add(data);\n");
        body.append("    }\n");
        body.append("  }else{\n");
        body.append("    while((_max_<0 || _count_<=_max_) && _rs_.next()){\n");
        body.append("      _count_++;\n");
        body.append("      int _col_=").append((pkcolumns.length + 1)).append(";\n");
        if (pkcolumns.length > 0) {
            body.append("      ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");
            for (int i = 0; i < pkcolumns.length; i++) {
                if (i > 0) {
                    body.append(",");
                }
                body.append(JavaUtils.getPreparedStatementGetterExpression(pkcolumns[i],"_rs_",(i + 1)));
//                body.append("_rs_.").append(pkcolumns[i].getPreparedStatementGetterName()).append("(").append((i + 1)).append(")");
            }
            body.append(");\n");
        }
        body.append("      ").append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("propertyList.propertiesSet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
            int rsPos = 0;

            public String getFieldNameCode(DBColumn dbColumn) {
                String thisField = null;
                if (JBGenUtils.isSearchableColumn(dbColumn)) {
                    thisField =JavaUtils.getPreparedStatementGetterExpression(dbColumn,"_rs_","(_col_++)");
                    DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
                    if (factory == null) {
                        return ("        data." + JavaUtils.businessSetterName(dbColumn) + "(" + thisField + ");\n");
                    } else {
                        return ("        data." + JavaUtils.businessSetterName(dbColumn) + "(" +
                                JavaUtils.objectToPrimitive(dbColumn.getConverterFieldName()
                                        + ".sqlToBusiness(" +
                                        JavaUtils.primitiveToObject(thisField, factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName()) + ")"
                                        , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(dbColumn.getSqlDataType()).toJavaType().getName())
                                +
                                ");\n");
                        //businessToSQL
                        //sqlToBusiness
                    }
                } else {
                    String s = dbColumn.getDAOInfo().getMethodGetDataExtraParamNamesString(false);
                    String prefix="";
                    if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                        String s0 = "_tableKey_";
                        if (dbColumn.getGetterImpl().getType() == FieldFormulaType.sqlMasterDetail) {
                            RelationDesc relationDesc = new RelationDesc(dbColumn.getGetterImpl().getBody(), dbColumn, entityInfo);
                            String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
                            prefix+="        "+rpl+" _c_ ="+ "(" + rpl + ")(propertyList==null?null:(((Object[])propertyList.getPropertyConstraints(" + (dbColumn.getFullBeanFieldConstant()) + "))[0]));\n";
                            prefix+="        "+relationDesc.getDetailTable().getDAOInfo().getDataFilterName()+" _f_ ="+ "(" + relationDesc.getDetailTable().getDAOInfo().getDataFilterName() + ")(propertyList==null?null:(((Object[])propertyList.getPropertyConstraints(" + (dbColumn.getDAOInfo().getPropertyListName()) + "." + dbColumn.getBeanFieldConstant() + "))[1]));\n";
                            s0 += ",_c_,_f_";
                        }
                        if (s.length() > 0) {
                            s = s0 + "," + s;
                        } else {
                            s = s0;
                        }
                    }
                    thisField =
                            (JavaUtils.businessGetterName(dbColumn) + "(" + s + ")");
                    return (prefix+"        data." + JavaUtils.businessSetterName(dbColumn) + "(" + thisField + ");\n");
                }
            }
        }.getCode(), 4, false, true));

        body
                .append("      list.add(data);\n")
                .append("    }\n")
                .append("  }\n")
                .append("  _rs_.close();\n")
                .append("  _prepStmt_.close();\n").append("  return (").append(entityInfo.getDTOName()).append("[]) list.toArray(new ").append(entityInfo.getDTOName()).append("[list.size()]);");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new SQLException(\"DataRetrievalException \"+sqlExcp);\n")
                        //.append("}finally{\n")
                        //.append("  _closeConnection_(_conn_);\n")
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new JavaParam("criteria", entityInfo.getDataFilterName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));

        JavaMethod m = new JavaMethod("select", entityInfo.getDTOName()+"[]",
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
//        new JavaParam[]{
//                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
//                new JavaParam("criteria", "Criteria", null),
//                new JavaParam("order", "OrderList", null),
//                }
                , "public",
                new String[]{"SQLException"}, null,
                body);
        declareMethod(m, entityInfo, theClass);
        return m;
    }

    private JavaMethod addMethodDeleteAll(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("PreparedStatement _prepStmt_=null;\n")
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append("  StringBuffer _statement_ = new StringBuffer(\"DELETE FROM ");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            if (t > 0) {
                body.append(",");
            }
            body.append(dbTables[t].getTableName2());
        }
        body.append("\");\n");

        body.append("  if(criteria!=null && criteria.getJoins()!=null){\n");
        body.append("    _statement_.append(\" \");\n");
        body.append("    _statement_.append(criteria.getJoins());\n");
        body.append("  }\n");
        DBColumn[] pk = entityInfo.getPrimaryKeys();
        boolean first = true;
        if (dbTables.length > 1) {
            body.append("    _statement_.append(\"");
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
                    body.append(pki[p].getColumnName());
                    body.append("=");
                    body.append(dbTables[0].getTableName2());
                    body.append(".");
                    body.append(pk[p].getColumnName());
                }
            }
            body.append("\");\n");
            body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
            body.append("    _statement_.append(\" AND \");\n");
            body.append("    _statement_.append(criteria.getWhereClause());\n");
            body.append("  }\n");
        } else {
            body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
            body.append("    _statement_.append(\" WHERE \");\n");
            body.append("    _statement_.append(criteria.getWhereClause());\n");
            body.append("  }\n");
        }

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<QUERY> \"+_statement_\n"
                + "                 +\"\\n          Criteria =\"+criteria\n"
                + "                 +\"\\n          Order    =\"+order"
        ,null));

        body.append("  ArrayList list=new ArrayList();\n");
        body.append("  _prepStmt_ = _conn_.prepareStatement(_statement_.toString());\n");

        body.append("  int _statementParamPos_=1;\n");
//        body.append("    for(Iterator _i_=_sqlStatementParamsProviderArrayList_.iterator();_i_.hasNext();){\n");
//        body.append("      SqlStatementParamsProvider _p_=(SqlStatementParamsProvider) _i_.next(); \n");
//        body.append("      _statementParamPos_+=_p_.populateStatement(_prepStmt_,_statementParamPos_); \n");
//        body.append("    }\n");
        body.append("  if(criteria!=null){\n");
        body.append("    criteria.populateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("  }\n");
        body
                .append("  return _prepStmt_.executeUpdate();\n")
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new SQLException(\"DataRetrievalException \"+sqlExcp);\n")
                        //.append("}finally{\n")
                        //.append("  _closeConnection_(_conn_);\n")
                .append("}finally{\n")
                .append("  if(_prepStmt_!=null){\n")
                .append("    _prepStmt_.close();\n")
                .append("  }")
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("criteria", entityInfo.getDataFilterName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));

        JavaMethod m = new JavaMethod("delete", "int",
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
//        new JavaParam[]{
//                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
//                new JavaParam("criteria", "Criteria", null),
//                new JavaParam("order", "OrderList", null),
//                }
                , "public",
                new String[]{"SQLException"}, null,
                body);
        declareMethod(m, entityInfo, theClass);
        return m;
    }

    private String getMethodGetSpecificFieldBodyForRELATION(DAOInfo entityInfo, DBColumn column) {
//        EntityInfo localEntity = entityInfo;
        // fields ??
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);

        StringBuilder body = new StringBuilder();
        relationDesc.getDetailTable().getDAOInfo().checkGenerateFilter(J2eeTarget.MODULE_DAO);
        theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getFullDAOName());

        body.append("  ").append(relationDesc.getDetailTable().getDAOInfo().getDAOName()).append(" details=new ").append(relationDesc.getDetailTable().getDAOInfo().getDAOName()).append("();");
        body.append("  details.setConnection(getConnection());\n");
        body.append("  details.setCallerPrincipalName(getCallerPrincipalName());\n");
        StringBuilder criteriaBuffer = new StringBuilder();
        DAOInfo masterEntityInfo = null;
        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(relationDesc.getDetailTable().getTableName2(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < relationDesc.getDetailPrimaryFields().length; i++) {
            DBColumn c = masterEntityInfo.getColumnByFieldName(relationDesc.getDetailPrimaryFields()[i], true);
            if (i > 0) {
                criteriaBuffer.append(" AND ");
            }
            criteriaBuffer.append(relationDesc.getDetailTable().getTableName2()).append(".").append(c.getColumnName());
            criteriaBuffer.append(" = ? ");
        }
        body.append("  ").append(relationDesc.getDetailTable().getDAOInfo().getDataFilterName()).append(" relationCriteria=new ").append(relationDesc.getDetailTable().getDAOInfo().getDataFilterName()).append("();\n");
        body.append("  relationCriteria.setWhereClause(\"").append(criteriaBuffer.toString()).append("\");\n");
        for (int i = 0; i < relationDesc.getForeignFields().length; i++) {
            DBColumn c = entityInfo.getColumnByFieldName(relationDesc.getForeignFields()[i], true);
            body.append("  relationCriteria").append(".").append(JavaUtils.getCriteriaSetterName(c)).append("(").append(i + 1).append(", ").append(c.getBeanFieldVar()).append(");\n");
        }

        body.append("  return new ArrayList(java.util.Arrays.asList(details.find(");
        body.append("propertyList,relationCriteria.merge(criteria)");
        body.append(")));\n");
//
//
//        body.append("  StringBuffer _statement_ = new StringBuffer(\"");
//        body
//                .append("SELECT ");
//
//        boolean first = true;
//        DBColumn[] rcolumns = masterTable.getColumns();
//        for (int i = 0; i < rcolumns.length; i++) {
//            DBColumn c = rcolumns[i];
//            if (first) {
//                first = false;
//            } else {
//                body
//                        .append(", ");
//            }
//            body
//                    .append(c.getTable().getTableName())
//                    .append(".")
//                    .append(c.getColumnName());
//        }
//
//        body
//                .append(" FROM ");
//        body.append(masterTable.getTableName());
//
//        body.append(" WHERE ");
//        EntityInfo masterEntityInfo = null;
//        try {
//            masterEntityInfo = entityInfo.getModuleInfo().getEntityForTable(masterTable.getTableName(), true);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 0; i < masterFields.length; i++) {
//            DBColumn c = masterEntityInfo.getColumnByFieldName(masterFields[i], true);
//            if (i > 0) {
//                body.append(" AND ");
//            }
//            body.append(masterTable.getTableName()).append(".").append(c.getColumnName());
//            body.append(" = ? ");
//        }
//        body.append("\");\n");
//        body.append("  ArrayList list=new ArrayList();\n");
//        body.append("  PreparedStatement _prepStmt_ = getConnection().prepareStatement(_statement_.toString());\n");
//        EntityInfo relEntityInfo = null;
//        try {
//            relEntityInfo = entityInfo.getModuleInfo().getEntityForTable(masterTable.getTableName(), true);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 0; i < localFields.length; i++) {
//            DBColumn c = entityInfo.getColumnByFieldName(localFields[i], true);
//            body.append("  _prepStmt_").append(".").append(JavaUtils.getPreparedStatementSetterName(c)).append("(").append(i + 1).append(", ").append(c.getBeanFieldVar()).append(");\n");
//        }
//        body
//                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
//                .append("  while(_rs_.next()){\n")
//                .append("    ").append(relEntityInfo.getFullDataContentName()).append(" relData = new ").append(relEntityInfo.getFullDataContentName()).append("();\n")
//                ;
//        int pos = 1;
//        for (int i = 0; i < rcolumns.length; i++) {
//            DBColumn c = rcolumns[i];
//            DataTypeConverterFactory factory = c.getSqlConverterFactory();
//            String converterName = c.getEntityInfo().getBeanName() + "_" + c.getConverterFieldName();
//            if (factory != null) {
//                if (theClass.getField(converterName) == null) {
//                    theClass.addField(new JavaField(converterName,
//                            "org.vpc.neormf.commons.types.converters.DataTypeConverter", null, "final private static", factory.getConverterExpression()));
//                }
//            }
//            body
//                    .append("    ")
//                    .append("relData.").append(JavaUtils.setterName(c)).append("(")
////                    .append("_rs_.")
//                    .append(JBGenUtils.getConvertSQLToJavaExpression(c, "_rs_." + c.getPreparedStatementGetterName() + "(" + pos + ")", converterName))
////                    .append(c.getPreparedStatementGetterName())
////                    .append("(")
////                    .append(pos)
////                    .append(")")
//                    .append(");\n");
//            pos++;
//        }
//        body
//                .append("    list.add(relData);\n");
//        body
//                .append("  }\n")
//                .append("  _rs_.close();\n")
//                .append("  _prepStmt_.close();\n")
//                .append("  return list;\n");
        return body.toString();
    }

//    private JavaMethod createMethodEjbHomeGetCount(EntityInfo entityInfo) {
//        StringBuffer body = new StringBuffer();
//        body
//                .append("Connection  _conn_=null;\n")
//                .append("try{\n")
//                .append("  _conn_=_openConnection_();\n");
//
//        body.append("  StringBuffer _statement_ = new StringBuffer(\"");
//        body
//                .append("SELECT Count(1) FROM ");
//        DBTable[] dbTables = entityInfo.getTables();
//        for (int t = 0; t < dbTables.length; t++) {
//            if (t > 0) {
//                body.append(",");
//            }
//            body.append(dbTables[t].getTableName());
//        }
//        body.append("\");\n");
//        body.append("if(criteria!=null && criteria.getJoins()!=null){\n");
//        body.append("  _statement_.append(\",\");\n");
//        body.append("  _statement_.append(criteria.getJoins());\n");
//        body.append("}\n");
//        DBColumn[] pk = entityInfo.getPrimaryKeys();
//        if (dbTables.length > 1) {
//            body.append("  _statement_.append(\"");
//            body.append(" WHERE ");
//            boolean first = true;
//            for (int t = 1; t < dbTables.length; t++) {
//                DBColumn[] pki = dbTables[t].getPkColumns();
//                for (int p = 0; p < pk.length; p++) {
//                    if (first) {
//                        first = false;
//                    } else {
//                        body.append(" AND ");
//                    }
//                    body.append(dbTables[t].getTableName());
//                    body.append(".");
//                    body.append(pki[p].getColumnName());
//                    body.append("=");
//                    body.append(dbTables[0].getTableName());
//                    body.append(".");
//                    body.append(pk[p].getColumnName());
//                }
//            }
//            body.append("\");\n");
//        }
//        body.append("if(criteria!=null && criteria.getWhereClause()!=null){\n");
//        body.append("  _statement_.append(\" AND \");\n");
//        body.append("  _statement_.append(criteria.getWhereClause());\n");
//        body.append("}\n");
//
//        body.append("  PreparedStatement _prepStmt_ = _conn_.populateStatement(_statement_.toString());\n");
//        body
//                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
//                .append("  int _count_=0;\n")
//                .append("  if(_rs_.next()){\n")
//                .append("    _count_=_rs_.getInt(1);\n")
//                .append("  }\n")
//                .append("  _rs_.close();\n")
//                .append("  _prepStmt_.close();\n")
//                .append("  return _count_;\n")
//                .append("}catch(SQLException sqlExcp){\n")
//                .append("  throw new EJBException(sqlExcp.getMessage());\n")
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
//                .append("}");
//        return new JavaMethod("ejbHomeGetCount", "int", new JavaParam[]{
//            new JavaParam("criteria", "Criteria", null)
//        }, "public",
//                new String[]{"EJBException"}, null,
//                body);
//    }

//    private JavaMethod createMethodEjbHomeUpdate(EntityInfo entityInfo) {
//        return new JavaMethod("ejbHomeUpdate", "void", new JavaParam[]{
//            new JavaParam("oldData", entityInfo.getDataContentName(), null),
//            new JavaParam("newData", entityInfo.getDataContentName(), null)
//        }, "public",
//                new String[]{"EJBException"}, null,
//                "try{\n" +
//                "  " + entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
//                "  home.findByPrimaryKey(data.get" + entityInfo.getDataKeyName() + "()).setData(oldData,newData);\n" +
//                "} catch (RemoteException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "} catch (FinderException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "}");
//    }

//    private JavaMethod createCollectionFinder(String name, JavaParam[] params, EntityInfo entityInfo) {
//        Vector vparams = new Vector();
//        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//
//        StringBuffer p = new StringBuffer();
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                vparams.add(params[i]);
//                if (i > 0) {
//                    p.append(",");
//                }
//                p.append(params[i].getName());
//            }
//        }
//
//
//        return new JavaMethod("ejbHomeGet" + JBGenUtils.toJavaIdentifier(name, true), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{"FinderException"}, null,
//
//                entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
//                "try{\n" +
//                "  Collection src=home.find" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
//                "  return _transform_(src,propertyList" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ");\n" +
//                "} catch (RemoteException e) {\n" +
//                "    throw new FinderException(e.getMessage());\n" +
//                "}");
//    }

    private String createBodyDeclareVars(DAOInfo entityInfo) {
        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            JavaField field = new JavaField(columns[i].getBeanFieldVar(),
                    columns[i].getSqlDataType().toJavaType().getName(),
                    null,
                    "",
                    null);
            field.addDefaultInitialization();
            stringBuffer.append(field.getSourceCode());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }


//    private JavaMethod addMethodGetAllQBE(EntityInfo entityInfo) {
//        StringBuffer body = new StringBuffer();
//
//        body
//                .append("  Criteria criteria=null;\n");
//        body
//        .append("  // building criteria\n");
//        body.append("if (prototype != null && prototype.size() > 0) {\n" +
//                "  StringBuffer sb = new StringBuffer(prototype.size());\n" +
//                "  criteria = new Criteria();\n" +
//                "  int pos=1;\n" +
//                new JavaSourceCodeFieldsSwitcher(
//                        "prototype.keySet()",false,false,entityInfo.getColumns(true,true,true)
//                ){
//                    public String getFieldNameCode(DBColumn dbColumn) {
//                        String sb=
//                            "if (sb.length() > 1) {\n" +
//                            "  sb.append(\" AND \");\n" +
//                            "}\n";
//                        String colExpression=null;
//                        if(dbColumn.getQuery()==null){
//                            colExpression=dbColumn.getTable().getTableName()+"."+dbColumn.getColumnName();
//                        }else if(dbColumn.getQueryType()==FieldFormulaTypes.SQL_QUERY){
//                            colExpression="("+dbColumn.getQuery()+")";
//                        }else{
//                            return "throw new ForbiddenFieldOnSearchException(\"field \'"+dbColumn.getBeanFieldName()+"\'is not searchable\");\n";
//                        }
//                        if(
//                                dbColumn.getPkPosition()>=0||
//                                JBGenUtils.isNumericType(dbColumn.getSqlDataType().toJavaType()) ||
//                                ChoiceType.class.isAssignableFrom(dbColumn.getBusinessDataType().getClass()) ||
//                                BooleanType.class.isAssignableFrom(dbColumn.getBusinessDataType().getClass()) ||
//                                AnyType.class.isAssignableFrom(dbColumn.getBusinessDataType().getClass())
//                        ){
//                            sb+="sb.append(\""+colExpression+" = ? \");\n" +
//                                "criteria."+dbColumn.getPreparedStatementSetterName()+"(pos,"+JBGenUtils.getConvertJavaToSQLExpression(dbColumn, "prototype."+JavaUtils.businessGetterName(dbColumn)+"()")+");\n";
//                        }else if(dbColumn.getSqlDataType().toJavaType().equals(String.class)){
//                            sb+="sb.append(\""+colExpression+" Like ? \");\n" +
//                                "criteria."+dbColumn.getPreparedStatementSetterName()+"(pos,"+JBGenUtils.getConvertJavaToSQLExpression(dbColumn, "prototype."+JavaUtils.businessGetterName(dbColumn)+"()")+"+\"%\");\n";
//                        }else if(Date.class.isAssignableFrom(dbColumn.getSqlDataType().toJavaType())){
//                            //TODO take into consideration date inetrvalle
//                            sb+="sb.append(\""+colExpression+" = ? \");\n" +
//                                "criteria."+dbColumn.getPreparedStatementSetterName()+"(pos,"+JBGenUtils.getConvertJavaToSQLExpression(dbColumn, "prototype."+JavaUtils.businessGetterName(dbColumn)+"()")+");\n";
//                        }else{
//                            return "throw new ForbiddenFieldOnSearchException(\"field \'"+dbColumn.getBeanFieldName()+"\' is not searchable\");\n";
//                        }
//                        return sb+"pos++;\nbreak;\n";
//                    }
//
//                    public String getSwitchDefaultCode() {
//                        return "throw new UnknownFieldException(selectedFieldName);\n";
//                    }
//                }.getFieldNameCode(2)+
//                "\tcriteria.setWhereClause(sb.toString());\n"+
//                "}\n"
//                );
//        body
//                .append("  return find(propertyList,criteria,null);\n");
//
//        JavaMethod m = new JavaMethod("find", "Collection", new JavaParam[]{
//            new JavaParam("propertyList", entityInfo.getFullPropertyListName(), null),
//            new JavaParam("prototype", entityInfo.getDataContentName(), null),
//        }, "public",
//                new String[]{"SQLException"}, null,
//                body);
//        return m;
//    }



    public String toString() {
        return "DataAccessObject Generator";
    }

    private String getUserCode(String key, DAOInfo entityInfo) {
        return entityInfo.getUserCodeForDO(key);
    }

    private String getLogCode(JavaLogManager.Level type, String prepareExpression, String logExpression, String throwableExpression) {
        return getLogJavaCode(entityInfo, type, J2eeTarget.MODULE_DAO, prepareExpression, "getCallerPrincipalName()", logExpression, throwableExpression);
    }
}

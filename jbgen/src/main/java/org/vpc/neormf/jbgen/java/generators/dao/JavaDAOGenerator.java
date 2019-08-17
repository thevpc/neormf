/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.dao;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.sql.dbsupport.RDBMSSupport;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.log.JavaLogManager;
import org.vpc.neormf.jbgen.java.generators.ejb.JavaSourceCodeFieldsSwitcher;
import org.vpc.neormf.jbgen.java.model.javaclass.*;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.vpc.neormf.jbgen.java.util.JavaUtils.*;
import static org.vpc.neormf.jbgen.java.util.JavaUtils.getDataFieldSourceCode;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.commons.sql.CodeLanguage;

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
public class JavaDAOGenerator extends JBGenDAOGenerator {
    public static final String METHOD_TO_CRITERIA = "toCriteria";

    public JavaDAOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        DBTableInfo[] tables = entityInfo.getTables();
        for (DBTableInfo table : tables) {
            if (DBTableInfo.VIRTUAL_TABLE_TYPE.equals(table.getTableType())) {
                System.err.println(table.getTableName() + " is virtual, ignore DAO " + entityInfo.getBeanName());
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
        this.entityInfo = entityInfo;
        JBGenProjectInfo moduleCodeStyle = entityInfo.getProjectInfo();
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_DAO));
        theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public ");
        theClass.setName(entityInfo.getDAOName());
        theClass.setSuperClass("DataAccessObject");
        theClass.addAllImports(new String[]{
                "java.util.*",

                "java.sql.*",

                //"java.rmi.RemoteException",

                "org.vpc.neormf.commons.beans.*",
                "org.vpc.neormf.commons.exceptions.*",
                "org.vpc.neormf.commons.types.converters.*",
                "org.vpc.neormf.commons.sql.*",

                entityInfo.getDataPackage() + ".*"
        });
        theClass.setPackage(entityInfo.getDAOPackage());

        JavaUtils.initializeClassLog(entityInfo, J2eeTarget.MODULE_DAO, theClass);
        if (cache != null) {
            theClass.addField(new JavaField("_cache_", "DataCache", null, "private static final", "new DataCache(" + cache.getMaxExpression() + "," + cache.getTimeoutExpression() + ")"));
            theClass.addField(new JavaField("_cacheProperties_", entityInfo.getPropertyListName(), null, "private static final", "null"));
            theClass.addMethod(new JavaMethod(method("getCachedData"), entityInfo.getDTOName(), new JavaParam[]{new JavaParam("key", entityInfo.getDataKeyName(), null)}, "public", new String[]{"SQLException"}, "cache",
                    entityInfo.getDTOName() + " data=(" + entityInfo.getDTOName() + ") _cache_.get(key);\n" +
                            "if(data==null){\n" +
                            "  data=findByKey(_cacheProperties_,key);\n" +
                            "  _cache_." + method("add") + "(data);\n" +
                            "}\n" +
                            "return data;\n"
            ));
        }

        DBColumn[] columns = entityInfo.getColumns(true, true, false, false);
        for (int i = 0; i < columns.length; i++) {
            DataTypeConverterFactory factory = columns[i].getSqlConverterFactory();
            if (factory != null) {
                theClass.addField(new JavaField(columns[i].getConverterFieldName(),
                        "org.vpc.neormf.commons.types.converters.DataTypeConverter", null, "final private static", factory.getConverterExpression()));
            }
        }
        addMetaDataField(entityInfo);
        createConstructor0(entityInfo);
        createConstructor1(entityInfo);
        createConstructor2(entityInfo);

        if (entityInfo.isUpdatable()) {
            createMethodCreate(entityInfo);
            createMethodUpdateData(entityInfo);
//            createMethodUpdateFormulas(entityInfo);
            createMethodRemoveData(entityInfo);
        }

        if (entityInfo.getPrimaryKeys().length > 0) {
            addMethodGetData(entityInfo);
        }

        addMethodGetAll(entityInfo);
        addMethodGetAllQBE(entityInfo);
        addMethodBuildCriteriaQBE(entityInfo);

        DBColumn[] extraColumns = entityInfo.getColumns(false, false, true, false);
        for (int i = 0; i < extraColumns.length; i++) {
            theClass.addMethod(createMethodGetSpecificField(entityInfo, extraColumns[i]));
        }

        entityInfo.setGeneratedClass("DataAccessObject", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder, getLog())) {
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

    private String createBodySetData(DAOInfo entityInfo) {
        Map map = getFieldsHashcodeMapping(entityInfo.getColumns(false, true, false, true));
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
                    methodCorps.append("         ").append(c.getBeanFieldVar()).append("=(data.").append((businessGetterName(c))).append("());\n");
                } else {
                    methodCorps.append("      ");
                    methodCorps.append(c.getBeanFieldVar());
                    methodCorps.append("=(");
                    methodCorps.append(objectToPrimitive(c.getConverterFieldName()
                            + ".businessToSQL(" +
                            primitiveToObject("data." + (businessGetterName(c)) + "()", factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(c.getSqlDataType()).toJavaType().getName()) + ")"
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
                    methodCorps.append("         ").append(c.getBeanFieldVar()).append("=(data.").append(businessGetterName(c)).append("());\n");
                }
                methodCorps.append("         }else{\n");
                methodCorps.append("            throw new UnknownFieldException(selectedFieldName);");
                methodCorps.append("         }");
            }
            methodCorps.append("        break;\n");
            methodCorps.append("    }\n");

        }
        methodCorps.append("    default:{\n");
        methodCorps.append("      throw new UnknownFieldException(selectedFieldName);");
        methodCorps.append("    }\n");
        methodCorps.append("  }\n");
        methodCorps.append("}\n");
        return methodCorps.toString();
    }

    private JavaMethod createMethodUpdateData(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false, true);
        body
                .append("if(data.size()==0){\n")
                .append("  return;\n")
                .append("}\n");

        boolean prologueChecking = false;
        for (DBColumn allColumn : allColumns) {
            if (allColumn.isForbiddenOnUpdate()) {
                if (!prologueChecking) {
                    prologueChecking = true;
                    body.append("// START Prologue Checking\n");
                }
                body.append("  if(data.").append(method("contains")).append(JBGenUtils.capitalize(allColumn.getBeanFieldName())).append("()){\n");
                body.append("    throw new ForbiddenFieldOnUpdateException(").append(getDataFieldSourceCode(entityInfo, allColumn)).append(");\n");
                body.append("  }\n");
            }
        }
        if (prologueChecking) {
            body.append("// END   Prologue Cheking\n");
        }


        body.append("\n");
        body.append(getUserCode("preUpdate", entityInfo));
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        if (cache != null) {
            body.append("\n// Remove cache\n");
            body.append("_cache_.remove(data.getDataKey());\n\n");
        }


        body.append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");
        body.append("  StringBuffer _updateStatement_ = new StringBuffer();\n");
        body.append("  int _ucount_ = 0;\n");
        body.append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  boolean _firstColumn_=true;\n");
            body.append("  _updateStatement_.").append(method("append")).append("( \"");
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
                            .append("  if(data.").append(method(c, method("contains"))).append("()){\n")
                            .append("    if(_firstColumn_){\n")
                            .append("      _firstColumn_=false;\n")
                            .append("    }else{\n").append("      _updateStatement_.").append(method("append")).append("(\", \");\n")
                            .append("    }\n")
                            .append("    _updateStatement_.append(\"").append(c.getColumnName2()).append("=? \");\n")
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
                    .append("_updateStatement_.append(\" WHERE ");
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
            if (entityInfo.isJBGenOptionEnabled("option-no-autoboxing", false)) {
                prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList(" + columns.length + ");\n");
            } else {
                prepareLog.append("  ArrayList _logElements_=new ArrayList(" + columns.length + ");\n");
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    prepareLog.append("  if(data.").append(method(c, "contains")).append("()){\n");
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(c, "data." + businessGetterName(c) + "()"))
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
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "data." + businessGetterName(pkColumns[c.getPkPosition()]) + "()"))
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<UPDATE-DATA> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_updateStatement_.toString(),_logElements_)", null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_updateStatement_.toString());\n");
            body
                    .append("  int _pos_=1;\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    body
                            .append("  if(data.").append(method(c, "contains")).append("()){\n")
                            .append("    ")
                            .append(getPreparedStatementSetterExpression(c, "_prepStmt_", "_pos_++", ("data." + businessGetterName(c) + "()")))
                            .append("\n")
                            .append("  }\n");
                }
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    DBColumn cc = pkColumns[c.getPkPosition()];
                    body
                            .append(getPreparedStatementSetterExpression(cc, "_prepStmt_", "_pos_++", ("data." + businessGetterName(cc) + "()")))
                            .append("\n");
                }
            }

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new UpdateDataException();\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postUpdate", entityInfo));
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new UpdateDataException(sqlExcp);\n")
                .append("}catch(RuntimeException exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw exc;\n")
                .append("}catch(Throwable exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw new UpdateDataException(exc);\n")
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("update", "void", new JavaParam[]{
                new JavaParam("data", entityInfo.getDTOName(), null)
        }, "public",
                new String[]{"SQLException"}, null,
                body);
        theClass.addMethod(new JavaMethod("defaultUpdate", "void",
                new JavaParam[]{new JavaParam("data", "DataTransfertObject", null)}
                , "public",
                new String[]{
                        "SQLException"
                }, null,
                "" + m.getName() + "((" + entityInfo.getDTOName() + ")data);"));
        return declareMethod(m,
                entityInfo, theClass
        );
    }

    private JavaMethod createMethodUpdateFormulas(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false, true);

        body.append("// START Prologue Checking\n");
        body.append("  DTOMetaData _METADATA=").append(entityInfo.getDTOName()).append("._METADATA;\n");
        body.append("  for(Iterator _i_=propertyList.propertiesIterator();_i_.hasNext();){\n");
        body.append("    String _fieldName=(String)_i_.next();\n");
        body.append("    if(").append(entityInfo.getDTOName()).append("._METADATA.getField(_fieldName).is){\n");
        body.append("      throw new ForbiddenFieldOnUpdateException(").append(entityInfo.getDTOName()).append("._METADATA.getField(_fieldName));\n");
        body.append("    }\n");
        body.append("  }\n");
        body.append("// END   Prologue Cheking\n");
        boolean prologueChecking = false;
        for (DBColumn allColumn : allColumns) {
            if (allColumn.isForbiddenOnUpdate()) {
                if (!prologueChecking) {
                    prologueChecking = true;
                }
                body.append("  if(data.").append(method("contains")).append(JBGenUtils.capitalize(allColumn.getBeanFieldName())).append("()){\n");
                body.append("    throw new ForbiddenFieldOnUpdateException(").append(getDataFieldSourceCode(entityInfo, allColumn)).append(");\n");
                body.append("  }\n");
            }
        }
        if (prologueChecking) {
        }


        body.append("\n");
        body.append(getUserCode("preUpdate", entityInfo));
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        if (cache != null) {
            body.append("\n// Remove cache\n");
            body.append("_cache_.remove(data.getDataKey());\n\n");
        }


        body.append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");
        body.append("  StringBuffer _updateStatement_ = new StringBuffer();\n");
        body.append("  int _ucount_ = 0;\n");
        body.append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  boolean _firstColumn_=true;\n");
            body.append("  _updateStatement_.").append(method("append")).append("( \"");
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
                            .append("  if(data.").append(method(c, method("contains"))).append("()){\n")
                            .append("    if(_firstColumn_){\n")
                            .append("      _firstColumn_=false;\n")
                            .append("    }else{\n").append("      _updateStatement_.").append(method("append")).append("(\", \");\n")
                            .append("    }\n")
                            .append("    _updateStatement_.append(\"").append(c.getColumnName2()).append("=? \");\n")
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
                    .append("_updateStatement_.append(\" WHERE ");
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
            if (entityInfo.isJBGenOptionEnabled("option-no-autoboxing", false)) {
                prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList(" + columns.length + ");\n");
            } else {
                prepareLog.append("  ArrayList _logElements_=new ArrayList(" + columns.length + ");\n");
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    prepareLog.append("  if(data.").append(method(c, "contains")).append("()){\n");
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(c, "data." + businessGetterName(c) + "()"))
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
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "data." + businessGetterName(pkColumns[c.getPkPosition()]) + "()"))
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<UPDATE-DATA> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_updateStatement_.toString(),_logElements_)", null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_updateStatement_.toString());\n");
            body
                    .append("  int _pos_=1;\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    body
                            .append("  if(data.").append(method(c, "contains")).append("()){\n")
                            .append("    ")
                            .append(getPreparedStatementSetterExpression(c, "_prepStmt_", "_pos_++", ("data." + businessGetterName(c) + "()")))
                            .append("\n")
                            .append("  }\n");
                }
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    DBColumn cc = pkColumns[c.getPkPosition()];
                    body
                            .append(getPreparedStatementSetterExpression(cc, "_prepStmt_", "_pos_++", ("data." + businessGetterName(cc) + "()")))
                            .append("\n");
                }
            }

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new UpdateDataException();\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postUpdate", entityInfo));
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new UpdateDataException(sqlExcp);\n")
                .append("}catch(RuntimeException exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw exc;\n")
                .append("}catch(Throwable exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw new UpdateDataException(exc);\n")
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("updateFormulas", "void", new JavaParam[]{
                new JavaParam("propertyList", entityInfo.getPropertyListName(), null)
        }, "public",
                new String[]{"SQLException"}, null,
                body);
        theClass.addMethod(new JavaMethod("defaultUpdateFormulas", "void",
                new JavaParam[]{new JavaParam("propertyList", "PropertyList", null)}
                , "public",
                new String[]{
                        "SQLException"
                }, null,
                "" + m.getName() + "((" + entityInfo.getPropertyListName() + ")propertyList);"));
        return declareMethod(m,
                entityInfo, theClass
        );
    }


    private JavaMethod createMethodRemoveData(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DAOInfo.BeanCache cache = entityInfo.getDAOCache();
        if (cache != null) {
            body.append("// remove cache\n");
            body.append("_cache_.remove(key);\n\n");
        }
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append(getUserCode("preDelete", entityInfo));
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
            body.append("  _prepStmt_ = _conn_.prepareStatement(_removeStatement_);\n");
            int pos = 1;
            DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    DBColumn cc = pkColumns[c.getPkPosition()];
                    body.append("  ").append(getPreparedStatementSetterExpression(cc, "_prepStmt_", pos, ("(key." + (businessGetterName(cc) + "())"))));
                    body.append("\n");
                    pos++;
                }
            }

            // TODO ajouter le code
            body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<REMOVE> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_removeStatement_,null)", null));

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new RemoveDataException();\n")
                    .append("  }\n");
        }
        body.append(getUserCode("postDelete", entityInfo));

        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new RemoveDataException(sqlExcp);\n")
//                .append("}finally{\n")
//                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("delete", "void", new JavaParam[]{
                new JavaParam("key", entityInfo.getDataKeyName(), null)
        }, "public",
                new String[]{
                        "SQLException"
                }, null,
                body);
        theClass.addMethod(new JavaMethod("defaultDelete", "void",
                new JavaParam[]{new JavaParam("key", "DataKey", null)}
                , "public",
                new String[]{
                        "SQLException"
                }, null,
                m.getName() + "((" + entityInfo.getDataKeyName() + ")key);"));
        return declareMethod(m,
                entityInfo,
                theClass
        );
    }

    private JavaField addMetaDataField(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body.append("new DAOMetaData(");
        body.append(entityInfo.getDTOName()).append(".").append("_METADATA");
        body.append(",new DAOFieldMetaData[]{");
        boolean first = true;
        for (DBColumn column : entityInfo.getColumns(true, true, true)) {
            if (first) {
                first = false;
            } else {
                body.append(",");
            }
            body.append("\n  new DAOFieldMetaData(");
            body.append("\n    ").append(entityInfo.getDTOName()).append(".").append("_METADATA.getField(").append(column.getFullBeanFieldConstant()).append(")");
            body.append("\n    ,DAOFieldKind.").append(column.getFieldKind().toString());
            String getterImpl = column.getGetterImpl().toUserCode(CodeLanguage.JAVA);
            String setterImpl = column.getSetterImpl().toUserCode(CodeLanguage.JAVA);
            body.append("\n    ").append(",").append(getterImpl);
            if (!setterImpl.equals(getterImpl)) {
                body.append("\n    ").append(",").append(setterImpl);
            }
            body.append("\n  )");
        }
        body.append("\n}");
        body.append(")");
        JavaField field = new JavaField(
                "METADATA",
                "DAOMetaData",
                "Metadata",
                "public static final",
                body.toString()
        );
        theClass.addField(field);
        return field;
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
        JavaMethod m = new JavaMethod(theClass.getName(), null,
                new JavaParam[]{new JavaParam("other", "DataAccessObject", null)}
                , "public",
                new String[]{
                }, "DataAccessObject Constructor\n@param other instance to copy",
                "super(other);");
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }

    private JavaMethod createConstructor2(final DAOInfo entityInfo) {
        JavaMethod m = new JavaMethod(theClass.getName(), null,
                new JavaParam[]{
                        new JavaParam("cnx", "Connection", null)
                        , new JavaParam("principal", "String", null)
                }
                , "public",
                new String[]{
                }, "DataAccessObject Constructor\n@param cnx Connection to use\n@param principal Cureent User Principal",
                "super(cnx,principal);");
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }


    private JavaMethod createMethodCreate(final DAOInfo entityInfo) throws SQLException {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false, true);
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
                body.append("  throw new ForbiddenFieldOnInsertException(").append(getDataFieldSourceCode(allColumns[i].getDAOInfo(), allColumns[i])).append(");\n");
                body.append("}\n");
            } else if (allColumns[i].isRequiredOnInsert()) {
                body.append("if(!data.contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("  throw new RequiredFieldOnInsertException(").append(getDataFieldSourceCode(allColumns[i].getDAOInfo(), allColumns[i])).append(");\n");
                body.append("}\n");
            }
        }
        body.append("// END  Prologue checking\n");
        body.append("\n");
        body
                .append("Connection  _conn_=null;\n")
                .append("String _insertStatement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        DBColumn[] keys = entityInfo.getColumns(true, false, false, true);
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
                body.append("  String _selectNewIdStatement_ = \"").append(rdbmsSupport.getSequenceString(entityInfo)).append("\";\n");

                body.append(getLogCode(JavaLogManager.Level.DEBUG, "Insert", null, "\"<Sequence Handling Query> \"+org.vpc.neormf.commons.sql.SQLUtils.LogQuery(_selectNewIdStatement_)", null));

                body.append("  _prepStmt_ = _conn_.prepareStatement(_selectNewIdStatement_);\n");
                body.append("  ResultSet _rs_=_prepStmt_.executeQuery();\n");
                body.append("  if(_rs_.next()){\n");
                //            body.append("    this.").append(keys[0].getBeanFieldVar()).append("= _rs_."+keys[0].getPreparedStatementGetterName()+"(1);\n");
                body.append("    data.").append(businessSetterName(keys[0])).append("(").append(getPreparedStatementGetterExpression(keys[0], "_rs_", 1)).append(");\n");
                //            body.append("    data.setOrderId(newId);\n");
                body.append("    _rs_.close();\n");
                body.append("    _prepStmt_.close();\n");
                body.append("  }\n");
                body.append("  _rs_.close();\n");
                body.append("  _prepStmt_.close();\n");
                body.append("  // END Sequence Handling\n");
            }
        } else {
            for (int i = 0; i < keys.length; i++) {
                DBColumn dbColumn = keys[i];
                body.append("  ").append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(dbColumn, "(data." + (businessGetterName(dbColumn)) + "())")).append(";\n");
            }
        }

        body.append("\n");
        body.append(getUserCode("preInsert", entityInfo));

        body.append("  // START Local Fields Updates\n");
        body.append(new JavaSourceCodeFieldsSwitcher("data.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (dbColumn.isFormulaImpl()) {
//                    return "// do nothing ; " + dbColumn.getBeanFieldName() + " is a view field";
                    StringBuilder sb = new StringBuilder("// " + dbColumn.getBeanFieldName() + " is a view field\n");
                    if (dbColumn.isForbiddenOnInsert()) {
                        sb.append("throw new ForbiddenFieldOnInsertException(").append(getDataFieldSourceCode(entityInfo, dbColumn)).append(");\n");
                    } else {
                        sb.append("break;");
                    }
                    return sb.toString();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(getFieldUserCode(dbColumn.getBeanFieldName(), "preInsert", entityInfo));
                    //sb.append(dbColumn.getBeanFieldVar()).append("=").append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(dbColumn, "(data." + (businessGetterName(dbColumn)) + "())")).append(";\n");
                    sb.append(dbColumn.getBeanFieldVar()).append("=").append("(data." + businessGetterName(dbColumn) + "())").append(";\n");
                    sb.append(getFieldUserCode(dbColumn.getBeanFieldName(), "postInsert", entityInfo));
                    sb.append("break;");
                    return sb.toString();
                }
            }

            public String getSwitchDefaultCode() {
                return "throw new UnknownFieldException(selectedFieldName);";
            }
        }.getCode());
        body.append("  // END   Local Fields Updates\n");
        body.append("\n");
        body.append("  // START Database persistance\n");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (DBTableInfo dbTable : dbTables) {
            body.append("  _insertStatement_ = \"");
            body
                    .append("INSERT INTO ")
                    .append(dbTable.getTableName2())
                    .append("(");
            boolean first = true;
            DBColumn[] ucolumns = dbTable.getUpdatableColumns();
            DBColumn[] pkcolumns = dbTable.getPkColumns();
            DBColumn[] bpkcolumns = entityInfo.getPrimaryKeys();
            for (DBColumn c : pkcolumns) {
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
            for (DBColumn c : ucolumns) {
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
            if (entityInfo.isJBGenOptionEnabled("option-no-autoboxing", false)) {
                prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList(" + bpkcolumns.length + ");\n");
            } else {
                prepareLog.append("  ArrayList _logElements_=new ArrayList(" + bpkcolumns.length + ");\n");
            }
            for (DBColumn c : bpkcolumns) {
                if (!c.isAutoIdentity()) {
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            for (DBColumn c : ucolumns) {
                if (!c.isAutoIdentity()) {
                    prepareLog.append("  _logElements_.add(")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<INSERT> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_insertStatement_,_logElements_)", null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_insertStatement_);\n");
            int pos = 1;
            for (DBColumn c : bpkcolumns) {
                if (!c.isAutoIdentity()) {
                    body
                            .append("  ")
                            .append(getPreparedStatementSetterExpression(c, "_prepStmt_", pos, c.getBeanFieldVar()))
                            .append("\n");
                    pos++;
                }
            }
            for (DBColumn c : ucolumns) {
                if (!c.isAutoIdentity()) {
                    body
                            .append("  ")
                            .append(getPreparedStatementSetterExpression(c, "_prepStmt_", pos, c.getBeanFieldVar()))
                            .append("\n");
                    pos++;
                }
            }

            body
                    .append("  _prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n");
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

                body.append(getLogCode(JavaLogManager.Level.DEBUG, "Insert", null, "\"<Sequence Handling Query> \"+SQLUtils.logQuery(_selectNewIdStatement_)", null));

                body.append("  _prepStmt_ = _conn_.prepareStatement(_selectNewIdStatement_);\n");
                body.append("  ResultSet _rs_=_prepStmt_.executeQuery();\n");
                body.append("  if(_rs_.next()){\n");
                //            body.append("    this.").append(keys[0].getBeanFieldVar()).append("= _rs_."+keys[0].getPreparedStatementGetterName()+"(1);\n");
                body.append("    data.").append(businessSetterName(keys[0])).append("(").append(getPreparedStatementGetterExpression(keys[0], "_rs_", 1)).append(");\n");
                //            body.append("    data.setOrderId(newId);\n");
                body.append("    _rs_.close();\n");
                body.append("    _prepStmt_.close();\n");
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
                .append("  throw new CreateDataException(sqlExcp);\n")
                .append("}catch(RuntimeException rtmExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "rtmExcp"))
                .append("  throw rtmExcp;\n")
                .append("}catch(Throwable exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw new CreateDataException(exc);\n")
//                .append("}finally{\n")
//                .append("  //_closeConnection_(_conn_);\n")
                .append("}");

        ArrayList<JavaParam> params = new ArrayList<JavaParam>();
        params.add(new JavaParam("data", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodCreateDataExtraParams()));
        JavaMethod m = new JavaMethod("create", entityInfo.getDataKeyName(),
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
                , "public",
                new String[]{
                        "SQLException", "FieldException"
                }, "DataAccessObject Constructor\n" +
                        "@param data instance to persist\n" +
                        "@return primary key\n" +
                        "@throws java.sql.SQLException when SQL problem\n" +
                        "@throws org.vpc.neormf.commons.exceptions.FieldException when Logic Problem\n"
                ,
                body);
//        String roles=(entityInfo.getMethodPatternValue(theClass,m,"roles",null);

        theClass.addMethod(new JavaMethod("defaultInsert", "DataKey",
                new JavaParam[]{new JavaParam("data", "DataTransfertObject", null)}
                , "public",
                new String[]{
                        "SQLException", "FieldException"
                }, null,
                "return " + m.getName() + "((" + entityInfo.getDTOName() + ")data);"));
        return declareMethod(m, entityInfo, theClass);
    }

    private static JavaMethod declareMethod(JavaMethod m, DAOInfo entityInfo, JavaClassSource theClass) {
        decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
        decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }


    //TODO invalid
    private String getMethodGetSpecificFieldBodyForSQL_VIEW(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("Connection  _conn_=null;\n")
                .append("String _selectStatement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("ResultSet _rs_  = null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append("  _selectStatement_=\"Select (").append(JBGenUtils.getSearchSQL(column, true).getQuery()).append(") FROM ").append(entityInfo.getTables()[0].getTableName2()).append(" WHERE ");
        DBColumn[] columns = entityInfo.getColumns(true, false, false, true);
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
        body.append("  _prepStmt_ = _conn_.prepareStatement(_selectStatement_);\n");
        int pos = 1;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("  ")
                        .append(getPreparedStatementSetterExpression(c, "_prepStmt_", pos, c.getBeanFieldVar()))
                        .append("\n");
                pos++;
            }
        }

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "_selectStatement_", null));

        body
                .append("  _rs_=_prepStmt_.executeQuery();\n")
                .append("  if(_rs_.next()){\n")
                .append("    return ")
                .append(getPreparedStatementGetterExpression(column, "_rs_", 1))
                .append(";\n");
        body
                .append("  }else{\n")
                .append("    throw new DataNotFoundException();\n")
                .append("  }\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new DataRetrievalException(sqlExcp);\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_rs_!=null){\n")
                .append("      _rs_.close();\n")
                .append("      _prepStmt_.close();\n")
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
            ep.add(new JavaParam("tableKey", entityInfo.getDataKeyName(), null));
            ep.add(new JavaParam("tableInstance", entityInfo.getDTOName(), null));
            FieldFormulaType getterType = column.getGetterImpl().getType();
            if (getterType == FieldFormulaType.sqlMasterDetail || getterType == FieldFormulaType.sqlDetailMaster) {
                RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
                String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                String rol = relationDesc.getDetailTable().getDAOInfo().getOrderListName();
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rol);
                ep.add(new JavaParam("propertyList", rpl, null));
                ep.add(new JavaParam("criteria", "Criteria", null));
                ep.add(new JavaParam("order", rol, null));
            }
            DBColumn[] k = entityInfo.getPrimaryKeys();
            for (int i = 0; i < k.length; i++) {
                prefixCode.append(k[i].getBusinessDataTypeName()).append(" ").append(k[i].getBeanFieldVar()).append("=tableKey.").append(businessGetterName(k[i])).append("();\n");
            }
        }
        ep.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        return new JavaMethod(businessGetterName(column),
                column.getBusinessDataTypeName(),
                (JavaParam[]) ep.toArray(new JavaParam[ep.size()]),
                "protected",
                new String[]{"SQLException"},
                null,
                prefixCode + body);

    }


    private JavaMethod addMethodGetData(DAOInfo entityInfo) {

        StringBuilder body = new StringBuilder();
        body
                .append("String where=\"");

        DBColumn[] columns = entityInfo.getColumns(true, false, false, true);
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
            body.append(getCriteriaSetterName(c.getBusinessDataType())).append("(").append(i + 1).append(",").append("primaryKey.").append(businessGetterName(c)).append("());\n");
        }

        body
                .append("List collection=find(propertyList,criteria,null);\n")
                .append("if(collection.size()>0){\n")
                .append("  for(Iterator i=collection.iterator();i.hasNext();){\n")
                .append("    return (").append(entityInfo.getDTOName()).append(") i.next();\n")
                .append("  }\n")
                .append("}\n")
                .append(" throw new DataNotFoundException();\n");

        JavaMethod m = new JavaMethod("findByKey", entityInfo.getDTOName(), new JavaParam[]{
                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
                new JavaParam("primaryKey", entityInfo.getDataKeyName(), null)
        }, "public",
                new String[]{"SQLException"}, null,
                body);
        theClass.addMethod(new JavaMethod("defaultFindByKey", "DataTransfertObject",
                new JavaParam[]{
                        new JavaParam("propertyList", "PropertyList", null),
                        new JavaParam("primaryKey", "DataKey", null),
                }
                , "public",
                new String[]{
                        "SQLException"
                }, null,
                "return " + m.getName() + "((" + entityInfo.getPropertyListName() + ")propertyList,(" + entityInfo.getDataKeyName() + ")primaryKey);"));
        DBColumn[] pkcolumns = entityInfo.getColumns(true, false, false);
        declareMethod(new JavaMethod("exists", "boolean",
                new JavaParam[]{
                        new JavaParam("key", entityInfo.getDataKeyName(), null),
                }
                , "public",
                new String[]{
                        "SQLException"
                }, null,
                "try {\n" +
                        "    " + entityInfo.getPropertyListName() + " props = new " + entityInfo.getPropertyListName() + "();\n" +
                        "    props." + method(pkcolumns[0], "add") + "();\n" +
                        "    defaultFindByKey(props,key);\n" +
                        "} catch (DataNotFoundException e) {\n" +
                        "    return false;\n" +
                        "}\n" +
                        "return true;"
        ), entityInfo, theClass);
        theClass.addMethod(new JavaMethod("defaultExists", "boolean",
                new JavaParam[]{
                        new JavaParam("key", "DataKey", null),
                }
                , "public",
                new String[]{
                        "SQLException"
                }, null,
                "return exists((" + entityInfo.getDataKeyName() + ")key);"
        ));
        declareMethod(m, entityInfo, theClass);
        return m;
    }

//    private JavaMethod addMethodGetAll(EntityInfo entityInfo) {
//        StringBuffer body = new StringBuffer();
//
//        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"SELECT \");\n");
//
//        body.append("if(propertyList==null){\n");
//        ArrayList a = new ArrayList();
//        a.addAll(Arrays.asList(entityInfo.getColumns(true, false, false)));
//        a.addAll(Arrays.asList(entityInfo.getColumns(true, true, true)));
//        DBColumn[] allpkPlusColumns = (DBColumn[]) a.toArray(new DBColumn[a.size()]);
//
//        boolean added = false;
//        body.append("_selectStatement_.append(\"");
//        for (int i = 0; i < allpkPlusColumns.length; i++) {
//            DBColumn c = allpkPlusColumns[i];
//            if (JBGenUtils.isSearchableColumn(c)) {
//                if (added) {
//                    body.append(",");
//                } else {
//                    added = true;
//                }
//                body.append("(").append(JBGenUtils.getSearchSQL(c).getQuery()).append(")");
//            } else {
////                throw new IllegalArgumentException("Unsupported type "+fieldFormulaImpl.getTypeName());
//            }
//        }
//        body.append("\");\n");
//        body.append("}else{\n");
//        body.append("  StringBuffer sb=new StringBuffer(\"");
//        DBColumn[] pkcolumns = entityInfo.getColumns(true, false, false);
//        for (int i = 0; i < pkcolumns.length; i++) {
//            DBColumn c = pkcolumns[i];
//            if (i > 0) {
//                body.append(",");
//            }
//            body.append(c.getColumnName());
//        }
//        body.append("\");\n");
//        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("propertyList.propertiesSet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
//            public String getFieldNameCode(DBColumn dbColumn) {
//                if (dbColumn.getGetterImpl().getType() < 0) {
//                    return
//                            "if (sb.length() > 0) {\n" +
//                            "  sb.append(\" , \");\n" +
//                            "}\n" +
//                            "sb.append(\"" + dbColumn.getTable().getTableName() + "." + dbColumn.getColumnName() + "\");";
//                } else {
//                    return "// do nothing; query type = " + dbColumn.getGetterImpl().getTypeName();
//                }
//            }
//        }.getCode(), 2, false, true));
//        body.append("  _selectStatement_.append(sb.toString());\n");
//        body.append("}\n");
//
//        body
//                .append("_selectStatement_.append(\" FROM ");
//        DBTable[] dbTables = entityInfo.getTables();
//        for (int t = 0; t < dbTables.length; t++) {
//            if (t > 0) {
//                body.append(",");
//            }
//            body.append(dbTables[t].getTableName());
//        }
//        body.append("\");\n");
//        body.append("if(criteria!=null && criteria.getJoins()!=null){\n");
//        body.append("  _selectStatement_.append(\",\");\n");
//        body.append("  _selectStatement_.append(criteria.getJoins());\n");
//        body.append("}\n");
//        DBColumn[] pk = entityInfo.getPrimaryKeys();
//        boolean first = true;
//        if (dbTables.length > 1) {
//            body.append("  _selectStatement_.append(\"");
//            body.append(" WHERE ");
//            first = true;
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
//            body.append("if(criteria!=null && criteria.getWhereClause()!=null){\n");
//            body.append("  _selectStatement_.append(\" AND \");\n");
//            body.append("  _selectStatement_.append(criteria.getWhereClause());\n");
//            body.append("}\n");
//        } else {
//            body.append("if(criteria!=null && criteria.getWhereClause()!=null){\n");
//            body.append("  _selectStatement_.append(\" WHERE \");\n");
//            body.append("  _selectStatement_.append(criteria.getWhereClause());\n");
//            body.append("}\n");
//        }
//
//        body.append("  if(order!=null){\n");
//        body.append("    _selectStatement_.append(\" ORDER BY \");\n");
//
//        body.append("    boolean orderFirst=true;\n");
//
//        JavaSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
//                new JavaSourceCodeFieldsSwitcher("order.iterator()",
//                        "OrderList.OrderItem item=(OrderList.OrderItem) i.next();" +
//                "String selectedFieldName=item.getFieldName();"
//                        , false, entityInfo.getColumns(true, true, true)) {
//                    public String getFieldNameCode(DBColumn dbColumn) {
//                        StringBuffer sb = new StringBuffer();
//                        if (JBGenUtils.isSearchableColumn(dbColumn)) {
//                            sb.append("if(orderFirst){\n");
//                            sb.append("  orderFirst=false;\n");
//                            sb.append("}else{\n");
//                            sb.append("  _selectStatement_.append(\" , \");\n");
//                            sb.append("}\n");
//                            sb.append("_selectStatement_.append(\"" + JBGenUtils.getSearchSQL(dbColumn).getQuery() + "\");\n");
//                            sb.append("_selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
//                            sb.append("break;");
//                        } else {
//                            sb.append("throw new ForbiddenFieldOnSearchException(" + dbColumn.getEntityInfo().getDataFieldSourceCode(dbColumn) + ");\n");
//                        }
//                        return sb.toString();
//                    }
//                };
//        sourceCodeFieldsSwitcher.setSwitchDefaultCode(new StringBuffer()
//                .append("//WHEN UNKNOWN FIELD PASSED AS IS TO SQL\n")
//                .append("if(orderFirst){\n")
//                .append("  orderFirst=false;\n")
//                .append("}else{\n")
//                .append("  _selectStatement_.append(\" , \");\n")
//                .append("}\n")
//                .append("_selectStatement_.append(selectedFieldName);\n")
//                .append("_selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n")
//                .append("break;")
//                .toString());
//        sourceCodeFieldsSwitcher.setAcceptFieldColumnNames(entityInfo.getConfig().getBooleanProperty("order.acceptColumnNames", false));
//        body.append(sourceCodeFieldsSwitcher.getCode(4));
//        body.append("  }\n");
////        body.append("if(order!=null){\n");
////        body.append("  _selectStatement_.append(\" ORDER BY \");\n");
////        body.append("  boolean orderFirst=true;\n");
////        body.append("  for(Iterator i=order.iterator();i.hasNext();){\n");
////        body.append("    if(orderFirst){\n");
////        body.append("      orderFirst=false;\n");
////        body.append("    }else{\n");
////        body.append("      _selectStatement_.append(\" , \");\n");
////        body.append("    }\n");
////        body.append("    OrderList.OrderItem item=(OrderList.OrderItem) i.next();\n");
////        body.append("    DTOFieldMetaData _dataField_=").append(entityInfo.getDataContentName()).append(".INFO.getField(item.getFieldName());\n");
////        body.append("    if(_dataField_!=null){\n");
////        body.append("      _selectStatement_.append(_dataField_.getColumnName());\n");
////        body.append("    }else{\n");
////        body.append("      _selectStatement_.append(item.getFieldName());\n");
////        body.append("    }\n");
////        body.append("    _selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
////        body.append("  }\n");
////        body.append("}\n");
//
//        if (entityInfo.generateStandardOutputLog("db")) {
//            body.append("  System.out.println(new java.util.Date()+\" : <QUERY> \"+_selectStatement_);\n");
//            body.append("  System.out.println(\"          Criteria =\"+criteria);\n");
//            body.append("  System.out.println(\"          Order    =\"+order);\n");
//        } else if (entityInfo.generateLog4JLog("db")) {
//            body.append("  if(_log_.isDebugEnabled()){\n");
//            body.append("  _log_.debug(\"<QUERY> \"+_selectStatement_\n");
//            body.append("  +\"\\n          Criteria =\"+criteria\n");
//            body.append("  +\"\\n          Order    =\"+order);\n");
//            body.append("  }\n");
//        }
//
//        body.append("  ArrayList list=new ArrayList();\n");
//        body.append("  PreparedStatement _prepStmt_ = getConnection().prepareStatement(_selectStatement_.toString());\n");
//        body.append("  int _min_=-1;\n");
//        body.append("  int _max_=-1;\n");
//        body.append("  if(criteria!=null){\n");
//        body.append("    criteria.populateStatement(_prepStmt_,_statementParamPos_);\n");
//        body.append("    _min_=criteria.getMinRowIndex();\n");
//        body.append("    _max_=criteria.getMaxRowIndex();\n");
//        body.append("  }\n");
//        body
//                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
//                .append("  int _count_=0;\n")
//                .append("  while(_count_<_min_ && _rs_.next()){\n")
//                .append("    _count_++;\n")
//                .append("  }\n");
//        body
//                .append("if(propertyList==null){\n");
//        body
//                .append("  while((_max_<0 || _count_<=_max_) && _rs_.next()){\n")
//                .append("    _count_++;\n");
//        if (pkcolumns.length > 0) {
//            body.append("    ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");
//            for (int i = 0; i < pkcolumns.length; i++) {
//                if (i > 0) {
//                    body.append(",");
//                }
//                body.append("_rs_." + pkcolumns[i].getPreparedStatementGetterName() + "(" + (i + 1) + ")");
//            }
//            body.append(");\n");
//        }
//        body.append("    " + entityInfo.getDataContentName() + " data=new " + entityInfo.getDataContentName() + "();\n");
//        DBColumn[] allColumns = entityInfo.getColumns(true, true, true);
//        int _col_ = pkcolumns.length + 1;
//        for (int i = 0; i < allColumns.length; i++) {
//            DBColumn dbColumn = allColumns[i];
//            String thisField = null;
//            if (JBGenUtils.isSearchableColumn(dbColumn)) {
//                thisField =
//                        ("_rs_." + dbColumn.getPreparedStatementGetterName() + "(" + (_col_++) + ")");
//            } else {
//                String s = dbColumn.getEntityInfo().getMethodGetDataExtraParamNamesString(false);
//                if (dbColumn.getEntityInfo().getPrimaryKeys().length > 0) {
//                    if (s.length() > 0) {
//                        s += ",";
//                    }
//                    s += "_tableKey_";
//                }
//                thisField =
//                        (JavaUtils.businessGetterName(dbColumn) + "(" + s + ")");
//            }
//            DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
//            if (factory == null) {
//                body
//                        .append("    data." + JavaUtils.setterName(dbColumn) + "(" + thisField + ");\n");
//            } else {
//                body
//                        .append("    data." + JavaUtils.setterName(dbColumn) + "(" +
//                        JBGenUtils.objectToPrimitive(dbColumn.getConverterFieldName()
//                        + ".sqlToBusiness(" +
//                        JBGenUtils.primitiveToObject(thisField, factory.getConverter().getSQLDataType().toJavaType().getName()) + ")"
//                                , factory.getConverter().getBusinessDataTypeName())
//                        +
//                        ");\n");
//                //businessToSQL
//                //sqlToBusiness
//            }
//        }
//        //
//        body
//                .append("    list.add(data);\n")
//                .append("  }\n")
//                .append("}else{\n")
//                .append("  while((_max_<0 || _count_<=_max_) && _rs_.next()){\n")
//                .append("    _count_++;\n")
//                .append("    int _col_=" + (pkcolumns.length + 1) + ";\n");
//        if (pkcolumns.length > 0) {
//            body.append("    ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");
//            for (int i = 0; i < pkcolumns.length; i++) {
//                if (i > 0) {
//                    body.append(",");
//                }
//                body.append("_rs_." + pkcolumns[i].getPreparedStatementGetterName() + "(" + (i + 1) + ")");
//            }
//            body.append(");\n");
//        }
//        body
//                .append("    " + entityInfo.getDataContentName() + " data=new " + entityInfo.getDataContentName() + "();\n");
//        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("propertyList.propertiesSet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
//            int rsPos = 0;
//
//            public String getFieldNameCode(DBColumn dbColumn) {
//                String thisField = null;
//                if (JBGenUtils.isSearchableColumn(dbColumn)) {
//                    thisField =
//                            ("_rs_." + dbColumn.getPreparedStatementGetterName() + "(_col_++)");
//                } else {
//                    String s = dbColumn.getEntityInfo().getMethodGetDataExtraParamNamesString(false);
//                    if (dbColumn.getEntityInfo().getPrimaryKeys().length > 0) {
//                        if (s.length() > 0) {
//                            s += ",";
//                        }
//                        s += "_tableKey_";
//                    }
//                    thisField =
//                            (JavaUtils.businessGetterName(dbColumn) + "(" + s + ")");
////                            (JavaUtils.businessGetterName(dbColumn) + "(" + dbColumn.getEntityInfo().getMethodGetDataExtraParamNamesString(false) + ")");
//                }
//                DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
//                if (factory == null) {
//                    return ("      data." + JavaUtils.setterName(dbColumn) + "(" + thisField + ");\n");
//                } else {
//                    return ("      data." + JavaUtils.setterName(dbColumn) + "(" +
//                            JBGenUtils.objectToPrimitive(dbColumn.getConverterFieldName()
//                            + ".sqlToBusiness(" +
//                            JBGenUtils.primitiveToObject(thisField, factory.getConverter().getSQLDataType().toJavaType().getName()) + ")"
//                                    , factory.getConverter().getBusinessDataTypeName())
//                            +
//                            ");\n");
//                    //businessToSQL
//                    //sqlToBusiness
//                }
//            }
//        }.getCode(), 4, false, true));
//
//        body
//                .append("    list.add(data);\n")
//                .append("  }\n")
//                .append("}\n")
//                .append("_rs_.close();\n")
//                .append("_prepStmt_.close();\n")
//                .append("return list;\n");
//        JavaMethod m = new JavaMethod("find", "Collection", new JavaParam[]{
//            new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
//            new JavaParam("criteria", "Criteria", null),
//            new JavaParam("order", "OrderList", null),
//        }, "public",
//                new String[]{"SQLException"}, null,
//                body);
//        declareMethod(m, entityInfo, theClass);
//        return m;
//    }

    private JavaMethod addMethodGetAll(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=getConnection();\n");

        body.append("  boolean forceLoadLiveFormula=false;\n");
        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"SELECT \");\n");

        body.append("  if(criteria!=null && criteria.isDistinct()){\n");
        body.append("    _selectStatement_.append(\"DISTINCT \");\n");
        body.append("  }\n");
        body.append("  ArrayList _sqlStatementParamsProviderArrayList_=new ArrayList();\n");


        body.append("  List  _orderedList = null;\n");
        body.append("  if(propertyList!=null){\n");
        body.append("    _orderedList = new ArrayList(propertyList.propertiesSet());\n");
        body.append("    Collections.sort(_orderedList," + entityInfo.getDTOName() + "._METADATA.getPopulationOrderComparator());\n");
        body.append("  }\n");

        body.append("  if(_orderedList==null){\n");
        ArrayList a = new ArrayList();
        a.addAll(Arrays.asList(entityInfo.getColumns(true, false, false, true)));
        a.addAll(Arrays.asList(entityInfo.getColumns(true, true, true, true)));
        DBColumn[] allpkPlusColumns = (DBColumn[]) a.toArray(new DBColumn[a.size()]);


        final StringBuilder queryTrue = new StringBuilder();
        final StringBuilder postQueryTrue = new StringBuilder();
        final StringBuilder queryFalse = new StringBuilder();
        final StringBuilder postQueryFalse = new StringBuilder();
        final boolean optionDefaultAllColumns = entityInfo.isJBGenOptionEnabled("option-default-all-columns", false);
        queryTrue.append(
                new ListUserCode<DBColumn>(allpkPlusColumns) {
                    public String stringify(DBColumn column) {
                        SQLPattern sqlPattern = JBGenUtils.getSearchSQL(column, true);
                        postQueryTrue.append(JBGenUtils.indent(getPopulateSqlStatementParamsProviderArrayList(column, "_sqlStatementParamsProviderArrayList_", "statement")));
                        return "(" + sqlPattern.getQuery() + ")";
                    }

                    @Override
                    public boolean accept(DBColumn o) {
                        return JBGenUtils.isSearchableColumn(o) && (optionDefaultAllColumns || (o.getGetterImpl().getType() == FieldFormulaType.none));
                    }
                }
        );
        queryFalse.append(
                new ListUserCode<DBColumn>(allpkPlusColumns) {
                    public String stringify(DBColumn column) {
                        SQLPattern sqlPattern = JBGenUtils.getSearchSQL(column, false);
                        postQueryFalse.append(JBGenUtils.indent(getPopulateSqlStatementParamsProviderArrayList(column, "_sqlStatementParamsProviderArrayList_", "statement")));
                        return "(" + sqlPattern.getQuery() + ")";
                    }

                    @Override
                    public boolean accept(DBColumn o) {
                        return JBGenUtils.isSearchableColumn(o) && (optionDefaultAllColumns || (o.getGetterImpl().getType() == FieldFormulaType.none));
                    }
                }
        );
        if (queryTrue.toString().equals(queryFalse.toString())) {
            body.append("    _selectStatement_.append(").append(JavaUtils.toStringLiteral(queryTrue.toString())).append(");\n");
        } else {
            body.append("    if(forceLoadLiveFormula){\n");
            body.append("      _selectStatement_.append(").append(JavaUtils.toStringLiteral(queryTrue.toString())).append(");\n");
            body.append("    }else{\n");
            body.append("      _selectStatement_.append(").append(JavaUtils.toStringLiteral(queryFalse.toString())).append(");\n");
            body.append("    }\n");
        }

        body.append("  }else{\n");
        body.append("    StringBuffer sb=new StringBuffer(");
        DBColumn[] pkcolumns = entityInfo.getColumns(true, false, false);
        body.append(JavaUtils.toStringLiteral(new ListUserCode<DBColumn>(pkcolumns) {
            public String stringify(DBColumn o) {
                return JBGenUtils.getSearchSQL(o, false).getQuery();
            }
        }.toString()));
        body.append(");\n");
        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("_orderedList.iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (JBGenUtils.isSearchableColumn(dbColumn)) {
                    return
                            "if (sb.length() > 0) {\n" +
                                    "  sb.append(\" , \");\n" +
                                    "}\n" +


                                    "sb.append(" +
                                    JBGenUtils.getSearchSQLString(dbColumn, "forceLoadLiveFormula") +
                                    ");" +
                                    getPopulateSqlStatementParamsProviderArrayList(dbColumn, "_sqlStatementParamsProviderArrayList_", "statement");
                } else {
                    return "// do nothing; query type = " + dbColumn.getGetterImpl().getTypeName();
                }
            }
        }.getCode(), 4, false, true));
        body.append("    _selectStatement_.append(sb.toString());\n");
        body.append("  }\n");

        body
                .append("  _selectStatement_.append(\" FROM ");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            if (t > 0) {
                body.append(",");
            }
            body.append(dbTables[t].getTableName2());
        }
        body.append("\");\n");
        body.append("  if(criteria!=null && criteria.getJoins()!=null){\n");
        body.append("    _selectStatement_.append(\" \");\n");
        body.append("    _selectStatement_.append(criteria.getJoins());\n");
        body.append("  }\n");
        DBColumn[] pk = entityInfo.getPrimaryKeys();
        boolean first = true;
        if (dbTables.length > 1) {
            body.append("    _selectStatement_.append(\"");
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
            body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
            body.append("    _selectStatement_.append(\" AND \");\n");
            body.append("    _selectStatement_.append(criteria.getWhereClause());\n");
            body.append("  }\n");
        } else {
            body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
            body.append("    _selectStatement_.append(\" WHERE \");\n");
            body.append("    _selectStatement_.append(criteria.getWhereClause());\n");
            body.append("  }\n");
        }

        body.append("    if(order!=null){\n");
        body.append("      _selectStatement_.append(\" ORDER BY \");\n");

        body.append("      boolean orderFirst=true;\n");
//        boolean optionReduceCodeSoft=entityInfo.isJBGenOptionEnabled("option-reduce-code-soft",false);
        JavaSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
                new JavaSourceCodeFieldsSwitcher("order.iterator()",
                        "OrderList.OrderItem item=(OrderList.OrderItem) i.next();\n" +
                                "String selectedFieldName=item.getFieldName();"
                        , false, entityInfo.getColumns(true, true, true)) {
                    public String getFieldNameCode(DBColumn dbColumn) {
                        StringBuilder sb = new StringBuilder();
                        if (JBGenUtils.isSearchableColumn(dbColumn)) {
                            sb.append("if(orderFirst){\n");
                            sb.append("  orderFirst=false;\n");
                            sb.append("}else{\n");
                            sb.append("  _selectStatement_.append(\" , \");\n");
                            sb.append("}\n");
                            sb.append("_selectStatement_.append(").append(JBGenUtils.getSearchSQLString(dbColumn, "forceLoadLiveFormula")).append(");\n");
                            sb.append("_selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
                            sb.append("break;");
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
                .append("  _selectStatement_.append(\" , \");\n")
                .append("}\n")
                .append("_selectStatement_.append(selectedFieldName);\n")
                .append("_selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n")
                .append("break;")
                .toString());
        sourceCodeFieldsSwitcher.setAcceptFieldColumnNames(entityInfo.isAcceptColumnNamesInOrderClause());
        body.append(sourceCodeFieldsSwitcher.getCode(4));
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
//        body.append("    OrderList.OrderItem item=(OrderList.OrderItem) i.next();\n");
//        body.append("    DTOFieldMetaData _dataField_=").append(entityInfo.getDataContentName()).append(".INFO.getField(item.getFieldName());\n");
//        body.append("    if(_dataField_!=null){\n");
//        body.append("      _selectStatement_.append(_dataField_.getColumnName());\n");
//        body.append("    }else{\n");
//        body.append("      _selectStatement_.append(item.getFieldName());\n");
//        body.append("    }\n");
//        body.append("    _selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
//        body.append("  }\n");
//        body.append("}\n");

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<QUERY> \"+_selectStatement_\n"
                + "                 +\"\\n          Criteria =\"+criteria\n"
                + "                 +\"\\n          Order    =\"+order"
                , null));

        body.append("    ArrayList list=new ArrayList();\n");
        body.append("    PreparedStatement _prepStmt_ = _conn_.prepareStatement(_selectStatement_.toString());\n");
        body.append("    int _min_=-1;\n");
        body.append("    int _max_=-1;\n");
        body.append("    int _statementParamPos_=1;\n");
        body.append("    for(Iterator _i_=_sqlStatementParamsProviderArrayList_.iterator();_i_.hasNext();){\n");
        body.append("      SqlStatementParamsProvider _p_=(SqlStatementParamsProvider) _i_.next(); \n");
        body.append("      _statementParamPos_+=_p_.populateStatement(_prepStmt_,_statementParamPos_); \n");
        body.append("    }\n");
        body.append("    if(criteria!=null){\n");
        body.append("      criteria.populateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("      _min_=criteria.getMinRowIndex();\n");
        body.append("      _max_=criteria.getMaxRowIndex();\n");
        body.append("    }\n");
        body
                .append("    ResultSet _rs_=_prepStmt_.executeQuery();\n")
                .append("    int _count_=0;\n")
                .append("    while(_count_<_min_ && _rs_.next()){\n")
                .append("      _count_++;\n")
                .append("    }\n");
        body
                .append("  if(_orderedList==null){\n");
        body
                .append("    while((_max_<0 || _count_<=_max_) && _rs_.next()){\n")
                .append("      _count_++;\n");
        if (pkcolumns.length > 0) {
            body.append("      ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");
            for (int i = 0; i < pkcolumns.length; i++) {
                if (i > 0) {
                    body.append(",");
                }
                body.append(
                        getPreparedStatementGetterExpression(pkcolumns[i], "_rs_", (i + 1))
                );
            }
            body.append(");\n");
        }
        body.append("      ").append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        body.append("      ").append("data.setDTOValidatable(false);\n");
        DBColumn[] allColumns = entityInfo.getColumns(true, true, true);
        int _col_ = pkcolumns.length + 1;
        for (int i = 0; i < allColumns.length; i++) {
            DBColumn dbColumn = allColumns[i];
            String thisField = null;
            if (JBGenUtils.isSearchableColumn(dbColumn)) {
                thisField = (getPreparedStatementGetterExpression(dbColumn, "_rs_", (_col_++)));
                DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
                body.append("      data.").append(businessSetterName(dbColumn)).append("(").append(thisField).append(");\n");
//                if (factory == null) {
//                } else {
//                    body.append("      data.").append(businessSetterName(dbColumn)).append("(").append(objectToPrimitive(dbColumn.getConverterFieldName()
//                            + ".sqlToBusiness(" +
//                            primitiveToObject(thisField, factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName()) + ")"
//                            , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(dbColumn.getSqlDataType()).toJavaType().getName())).append(");\n");
//                    //businessToSQL
//                    //sqlToBusiness
//                }
            } else {
                if (optionDefaultAllColumns) {
                    String s = dbColumn.getDAOInfo().getMethodGetDataExtraParamNamesString(false);
                    if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                        String s0 = "_tableKey_,data";
                        FieldFormulaType getterType = dbColumn.getGetterImpl().getType();
                        if (getterType == FieldFormulaType.sqlMasterDetail || getterType == FieldFormulaType.sqlDetailMaster) {
                            RelationDesc relationDesc = new RelationDesc(dbColumn.getGetterImpl().getBody(), dbColumn, entityInfo);
                            String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                            String rol = relationDesc.getDetailTable().getDAOInfo().getOrderListName();
                            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
//                        s0+=",("+rpl+")((Object[])propertyList.getPropertyConstraints("+(dbColumn.getEntityInfo().getPropertyListName())+"."+dbColumn.getBeanFieldConstant()+"))[0]";
//                        s0+=",(Criteria)((Object[])propertyList.getPropertyConstraints("+(dbColumn.getEntityInfo().getPropertyListName())+"."+dbColumn.getBeanFieldConstant()+"))[1]";
//                        s0+=",(OrderList)((Object[])propertyList.getPropertyConstraints("+(dbColumn.getEntityInfo().getPropertyListName())+"."+dbColumn.getBeanFieldConstant()+"))[2]";
                            s0 += ",(" + rpl + ")null,(Criteria)null,(" + rol + ")null";
                        }
                        if (s.length() > 0) {
                            s = s0 + "," + s;
                        } else {
                            s = s0;
                        }
                    }
                    thisField = (businessGetterName(dbColumn) + "(" + s + ")");
                    body.append("      data.").append(businessSetterName(dbColumn)).append("(").append(thisField).append(");\n");
                }
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
                body.append(
                        (getPreparedStatementGetterExpression(pkcolumns[i], "_rs_", (i + 1)))
                );
//                body.append("_rs_.").append(pkcolumns[i].getPreparedStatementGetterName()).append("(").append((i + 1)).append(")");
            }
            body.append(");\n");
        }
        body.append("      ").append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");
        body.append("      ").append("data.setDTOValidatable(false);\n");
        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("_orderedList.iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
            int rsPos = 0;

            public String getFieldNameCode(DBColumn dbColumn) {
                String thisField = null;
                if (JBGenUtils.isSearchableColumn(dbColumn)) {
                    thisField = getPreparedStatementGetterExpression(dbColumn, "_rs_", "(_col_++)");
                    DataTypeConverterFactory factory = dbColumn.getSqlConverterFactory();
                    return ("        data." + businessSetterName(dbColumn) + "(" + thisField + ");\n");
//                    if (factory == null) {
//                    } else {
//                        return ("        data." + businessSetterName(dbColumn) + "(" +
//                                objectToPrimitive(dbColumn.getConverterFieldName()
//                                        + ".sqlToBusiness(" +
//                                        primitiveToObject(thisField, factory.getConverter(entityInfo.getProjectInfo()).getSQLDataType().toJavaType().getName()) + ")"
//                                        , factory.getConverter(entityInfo.getProjectInfo()).getBusinessDataType(dbColumn.getSqlDataType()).toJavaType().getName())
//                                +
//                                ");\n");
//                        //businessToSQL
//                        //sqlToBusiness
//                    }
                } else {
                    String s = dbColumn.getDAOInfo().getMethodGetDataExtraParamNamesString(false);
                    if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                        String s0 = "_tableKey_,data";
                        FieldFormulaType getterType = dbColumn.getGetterImpl().getType();
                        if (getterType == FieldFormulaType.sqlMasterDetail || getterType == FieldFormulaType.sqlDetailMaster) {
                            RelationDesc relationDesc = new RelationDesc(dbColumn.getGetterImpl().getBody(), dbColumn, entityInfo);
                            String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                            String rol = relationDesc.getDetailTable().getDAOInfo().getOrderListName();
                            theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage() + "." + rpl);
                            s0 += ",(" + rpl + ")(propertyList==null?null:(((Object[])propertyList.getPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[0]))";
                            s0 += ",(Criteria)(propertyList==null?null:(((Object[])propertyList.getPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[1]))";
                            s0 += ",(" + rol + ")(propertyList==null?null:(((Object[])propertyList.getPropertyConstraints(" + dbColumn.getFullBeanFieldConstant() + "))[2]))";
                        }
                        if (s.length() > 0) {
                            s = s0 + "," + s;
                        } else {
                            s = s0;
                        }
                    }
                    thisField =
                            (businessGetterName(dbColumn) + "(" + s + ")");
                    return ("        data." + businessSetterName(dbColumn) + "(" + thisField + ");\n");
                }
            }
        }.getCode(), 4, false, true));

        body
                .append("      list.add(data);\n")
                .append("    }\n")
                .append("  }\n")
                .append("  _rs_.close();\n")
                .append("  _prepStmt_.close();\n")
                .append("  return list;\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new DataRetrievalException(sqlExcp);\n");
        body
                .append("}catch(RuntimeException exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw exc;\n");
        body
                .append("}catch(Throwable exc){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "exc"))
                .append("  throw new DataRetrievalException(exc);\n");
        body
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new JavaParam("criteria", "Criteria", null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));
        params.add(new JavaParam("order", entityInfo.getOrderListName(), null));

        JavaMethod m = new JavaMethod("find", "List",
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
//        new JavaParam[]{
//                new JavaParam("propertyList", entityInfo.getPropertyListName(), null),
//                new JavaParam("criteria", "Criteria", null),
//                new JavaParam("order", "OrderList", null),
//                }
                , "public",
                new String[]{"SQLException", "FieldException"}, null,
                body);
        theClass.addMethod(new JavaMethod("defaultFind", "List",
                new JavaParam[]{
                        new JavaParam("propertyList", "PropertyList", null),
                        new JavaParam("criteria", "Criteria", null),
                        new JavaParam("order", "OrderList", null),
                }
                , "public",
                new String[]{
                        "SQLException", "FieldException"
                }, null,
                "return " + m.getName() + "((" + entityInfo.getPropertyListName() + ")propertyList,criteria,(" + entityInfo.getOrderListName() + ")order);"));
        declareMethod(m, entityInfo, theClass);
        return m;
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
        String fdn = relationDesc.getDetailTable().getDAOInfo().getFullDAOName();
        theClass.addImport(fdn);

        body.append("  ").append(masterTable.getDAOInfo().getDAOName()).append(" master=new ").append(masterTable.getDAOInfo().getDAOName()).append("();\n");
        body.append("  master.setConnection(getConnection());\n");
        body.append("  master.setCallerPrincipalName(getCallerPrincipalName());\n");
        StringBuilder criteriaBuffer = new StringBuilder();
        DAOInfo masterEntityInfo = null;
        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(masterTable.getTableName2(), true);
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
            body.append("  relationCriteria").append(".").append(getCriteriaSetterName(c)).append("(").append(i + 1).append(", tableInstance.").append(businessGetterName(c)).append("());\n");
        }
        switch (column.getCardinality()) {
            case ONE_TO_MANY: {
                body.append("  return (master.find(");
                body.append("propertyList,relationCriteria.merge(criteria),order");
                body.append("));\n");
                break;
            }
            case MANY_TO_ONE:
            case ONE_TO_ONE: {
                body.append("  Collection _found_= (master.find(");
                body.append("propertyList,relationCriteria.merge(criteria),order");
                body.append("));\n");
                body.append("  for(Iterator _i_=_found_.iterator();_i_.hasNext();){\n");
                body.append("    return (").append(column.getBusinessDataTypeName()).append(")_i_.next();\n");
                body.append("  }\n");
                body.append("  return null;\n");
                break;
            }
            default: {
                new IllegalArgumentException("Cardinality insupported for masterDetail");
            }
        }
//
//
//        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"");
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
//        body.append("  PreparedStatement _prepStmt_ = getConnection().prepareStatement(_selectStatement_.toString());\n");
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

    private String getMethodGetSpecificFieldBodyForRELATION_MasterToDetail(DAOInfo entityInfo, DBColumn column) {
//        EntityInfo localEntity = entityInfo;
        // fields ??
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
        String[] localFields = relationDesc.getForeignFields();
        String[] masterFields = relationDesc.getDetailPrimaryFields();
        DBTableInfo masterTable = relationDesc.getDetailTable();


        StringBuilder body = new StringBuilder();
        masterTable.getDAOInfo().checkGenerateFilter(J2eeTarget.MODULE_DAO);
        String fdn = relationDesc.getDetailTable().getDAOInfo().getFullDAOName();
        theClass.addImport(fdn);

        body.append("  ").append(masterTable.getDAOInfo().getDAOName()).append(" details=new ").append(masterTable.getDAOInfo().getDAOName()).append("();\n");
        body.append("  details.setConnection(getConnection());\n");
        body.append("  details.setCallerPrincipalName(getCallerPrincipalName());\n");
        StringBuilder criteriaBuffer = new StringBuilder();
        DAOInfo masterEntityInfo = null;
        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(masterTable.getTableName2(), true);
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
            body.append("  relationCriteria").append(".").append(getCriteriaSetterName(c)).append("(").append(i + 1).append(", ").append(c.getBeanFieldVar()).append(");\n");
        }
        switch (column.getCardinality()) {
            case ONE_TO_MANY: {
                body.append("  return (details.find(");
                body.append("propertyList,relationCriteria.merge(criteria),order");
                body.append("));\n");
                break;
            }
            case ONE_TO_ONE: {
                body.append("  Collection _found_= (details.find(");
                body.append("propertyList,relationCriteria.merge(criteria),order");
                body.append("));\n");
                body.append("  for(Iterator _i_=_found_.iterator();_i_.hasNext();){\n");
                body.append("    return (").append(column.getBusinessDataTypeName()).append(")_i_.next();\n");
                body.append("  }\n");
                body.append("  return null;\n");
                break;
            }
            default: {
                new IllegalArgumentException("Cardinality insupported for masterDetail");
            }
        }
//
//
//        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"");
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
//        body.append("  PreparedStatement _prepStmt_ = getConnection().prepareStatement(_selectStatement_.toString());\n");
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
//        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"");
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
//        body.append("  _selectStatement_.append(\",\");\n");
//        body.append("  _selectStatement_.append(criteria.getJoins());\n");
//        body.append("}\n");
//        DBColumn[] pk = entityInfo.getPrimaryKeys();
//        if (dbTables.length > 1) {
//            body.append("  _selectStatement_.append(\"");
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
//        body.append("  _selectStatement_.append(\" AND \");\n");
//        body.append("  _selectStatement_.append(criteria.getWhereClause());\n");
//        body.append("}\n");
//
//        body.append("  PreparedStatement _prepStmt_ = _conn_.populateStatement(_selectStatement_.toString());\n");
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
                    columns[i].getBusinessDataTypeName(),
                    //columns[i].getSQLDataTypeName(),
                    null,
                    "",
                    null);
            field.addDefaultInitialization();
            stringBuffer.append(field.getSourceCode());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    private JavaMethod addMethodGetAllQBE(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.add(new JavaParam("order", entityInfo.getOrderListName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        JavaParam[] paramsArray = (JavaParam[]) params.toArray(new JavaParam[params.size()]);

        ArrayList _buildCriteriaQBE_Params = new ArrayList();
        _buildCriteriaQBE_Params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        _buildCriteriaQBE_Params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        JavaParam[] _buildCriteriaQBE_ParamsArray = (JavaParam[]) _buildCriteriaQBE_Params.toArray(new JavaParam[_buildCriteriaQBE_Params.size()]);

        body.append("  return find(propertyList," + METHOD_TO_CRITERIA + "(");
        body.append(JavaParam.toCommaSeparatedString(_buildCriteriaQBE_ParamsArray));
        body.append("),order);\n");

        JavaMethod m = new JavaMethod("find", "List",
                paramsArray
                , "public",
                new String[]{"SQLException"}, null,
                body);
        declareMethod(m, entityInfo, theClass);
        return m;
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


    private JavaMethod addMethodBuildCriteriaQBE(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();

        body
                .append("  Criteria criteria=null;\n");
        body
                .append("  // building criteria\n");
        body.append("if (prototype != null && prototype.size() > 0) {\n").append("  StringBuffer whereClause = new StringBuffer();\n").append("  criteria = new Criteria();\n").append("  int pos=1;\n").append(new JavaSourceCodeFieldsSwitcher("prototype.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
            public String getFieldNameCode(DBColumn dbColumn) {
                if (dbColumn.isForbiddenOnSearch()) {
                    return "throw new ForbiddenFieldOnSearchException(" + getDataFieldSourceCode(dbColumn.getDAOInfo(), dbColumn) + ");\n";
                }
                String sb =
                        "if (whereClause.length() > 1) {\n" +
                                "  whereClause.append(\" AND \");\n" +
                                "}\n";
                SQLPattern colExpression;

                colExpression = JBGenUtils.getSearchSQL(dbColumn, false);
                if (colExpression == null) {
                    return "throw new ForbiddenFieldOnSearchException(" + getDataFieldSourceCode(dbColumn.getDAOInfo(), dbColumn) + ");\n";
                }
                String javaColumnValueExpression = entityInfo.getProjectInfo().getConvertJavaToSQLExpression(dbColumn, "prototype." + businessGetterName(dbColumn) + "()");
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
                            String vname;
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
                                        (vType == null) ? getCriteriaSetterName(dbColumn) :
                                                (vType.equals("String")) ? method("setString") :
                                                        (vType.equals("int")) ? method("setInt") :
                                                                (vType.equals("double")) ? method("setDouble") :
                                                                        (vType.equals("Date")) ? method("setDate") :
                                                                                (vType.equals("Time")) ? method("setTime") :
                                                                                        (vType.equals("Timestamp")) ? method("setTimestamp") :
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
                sb += "whereClause.append(" + JavaUtils.toStringLiteral(sqlExpression.toString()) + ");\n";
                sb += javaExpression;
                return sb + "break;\n";
//                        String sb=
//                            "if (whereClause.length() > 1) {\n" +
//                            "  whereClause.append(\" AND \");\n" +
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
//                                JBGenUtils.isCodeColumn(dbColumn)||
//                                dbColumn.getPkPosition()>=0||
//                                JBGenUtils.isNumericType(dbColumn.getSqlDataType().toJavaType()) ||
//                                ChoiceType.class.isAssignableFrom(dbColumn.getBusinessDataType().getClass()) ||
//                                BooleanType.class.isAssignableFrom(dbColumn.getBusinessDataType().getClass()) ||
//                                AnyType.class.isAssignableFrom(dbColumn.getBusinessDataType().getClass())
//                        ){
//                            sb+="whereClause.append(\""+colExpression+" = ? \");\n" +
//                                "criteria."+dbColumn.getPreparedStatementSetterName()+"(pos,"+JBGenUtils.getConvertJavaToSQLExpression(dbColumn, "prototype."+JavaUtils.businessGetterName(dbColumn)+"()")+");\n";
//                        }else if(dbColumn.getSqlDataType().toJavaType().equals(String.class)){
//                            sb+="whereClause.append(\""+colExpression+" Like ? \");\n" +
//                                "criteria."+dbColumn.getPreparedStatementSetterName()+"(pos,"+JBGenUtils.getConvertJavaToSQLExpression(dbColumn, "prototype."+JavaUtils.businessGetterName(dbColumn)+"()")+"+\"%\");\n";
//                        }else if(Date.class.isAssignableFrom(dbColumn.getSqlDataType().toJavaType())){
//                            //TODO take into consideration date inetrvalle
//                            sb+="whereClause.append(\""+colExpression+" = ? \");\n" +
//                                "criteria."+dbColumn.getPreparedStatementSetterName()+"(pos,"+JBGenUtils.getConvertJavaToSQLExpression(dbColumn, "prototype."+JavaUtils.businessGetterName(dbColumn)+"()")+");\n";
//                        }else{
//                            return "throw new ForbiddenFieldOnSearchException(\"field \'"+dbColumn.getBeanFieldName()+"\' is not searchable\");\n";
//                        }
//                        return sb+"pos++;\nbreak;\n";
            }

            public String getSwitchDefaultCode() {
                return "throw new UnknownFieldException(selectedFieldName);\n";
            }
        }.getCode(2)).append("\tcriteria.setWhereClause(whereClause.toString());\n").append("}\n");
        //TODO pkoa???
        body.append(getUserCode("find", entityInfo)).append("\n");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        body
                .append("  return criteria;\n");

        JavaMethod m = new JavaMethod(METHOD_TO_CRITERIA, "Criteria",
                (JavaParam[]) params.toArray(new JavaParam[params.size()]),
                "public static", // for external use
//                "private",
                null, null,
                body);
        theClass.addMethod(m);
        return m;
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

//    private static String getLogDebugCode(DAOInfo entityInfo, String prepareExpression, String logExpression) {
//        return JavaUtils.getLogJavaCode(entityInfo, JavaLogManager.Level.DEBUG, J2eeTarget.MODULE_DAO,prepareExpression,"getCallerPrincipalName()",logExpression,null);
//        //return entityInfo.getLogDebugJavaCode(type, prepareExpression, "getCallerPrincipalName()", logExpression);
//    }

    private String getLogCode(JavaLogManager.Level type, String operation, String prepareExpression, String logExpression, String throwableExpression) {
//        if (prepareExpression != null) {
//            prepareExpression = prepareExpression + "\n";
//        } else {
//            prepareExpression = "";
//        }
//        if (entityInfo.generateLog4JLog(J2eeTarget.MODULE_DAO)) {
//            prepareExpression = prepareExpression + "MDC.put(\"mdc_entity\",\"" + entityInfo.getBeanName() + "\");\n";
//            prepareExpression = prepareExpression + "MDC.put(\"mdc_operation\",\"" + operation + "\");\n";
//        }
//        return getLogCode(type, prepareExpression, logExpression, throwableExpression);
        return getLogJavaCode(entityInfo, type, J2eeTarget.MODULE_DAO, prepareExpression, "getCallerPrincipalName()", logExpression, throwableExpression, entityInfo.getBeanName(), operation);
    }

    private String getLogCode(JavaLogManager.Level type, String prepareExpression, String logExpression, String throwableExpression) {
        return getLogJavaCode(entityInfo, type, J2eeTarget.MODULE_DAO, prepareExpression, "getCallerPrincipalName()", logExpression, throwableExpression);
    }

}

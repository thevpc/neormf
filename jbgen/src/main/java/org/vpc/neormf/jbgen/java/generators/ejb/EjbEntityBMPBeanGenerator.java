/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.*;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.dao.JavaDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.log.JavaLogManager;
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
 * TODO il faut que cette classe utilise le DAO et ne plus faire du SQL toute seule!!!
 */
class EjbEntityBMPBeanGenerator extends JBGenDAOGenerator {

    public EjbEntityBMPBeanGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        if(entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0){
            return false;
        }
        if (
                !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                && !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")
                && entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0")<0
        ) {
            return false;
        }
        if (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
        ) {
            throw new IllegalArgumentException("Could not create both of CMP and BMP EJB for " + entityInfo.getBeanName() + " (table " + entityInfo.getEntityName() + ").\n check your module filters :\n generate.ejb-cmp-remote ; generate.ejb-cmp-remote ; generate.ejb-bmp-remote ; generate.ejb-cmp-local ; generate.ejb-bmp-local");
        }
        if(entityInfo.getGeneratedClass("PrimaryKey")==null){
            return false;
        }
        return true;
    }

    public String getDescription() {
        return null;
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-cmp-remote");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-bmp-remote");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-cmp-local");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-bmp-local");
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

    JavaClassSource theClass;
    File destFolder;

    DAOInfo entityInfo = null;
    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        this.entityInfo=entityInfo;
        JBGenProjectInfo moduleCodeStyle = entityInfo.getProjectInfo();
        if (
                !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                && !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")
        ) {
            return;
        }
        if (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
        ) {
            throw new IllegalArgumentException("Could not create both of CMP and BMP EJB for " + entityInfo.getBeanName() + " (table " + entityInfo.getEntityName() + ").\n check your module filters :\n generate.ejb-cmp-remote ; generate.ejb-cmp-remote ; generate.ejb-bmp-remote ; generate.ejb-cmp-local ; generate.ejb-bmp-local");
        }
        destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB));
        theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public ");
        theClass.setName(entityInfo.getDOBeanName());
        theClass.setInterfaces(new String[]{"EntityBean"});
        theClass.addAllImports(new String[]{
            "java.util.*",
            "java.sql.*",
            "java.rmi.*",
            "javax.ejb.*",
            "org.vpc.neormf.commons.beans.*",
            "org.vpc.neormf.commons.sql.*",
            "org.vpc.neormf.commons.exceptions.*",
            "org.vpc.neormf.commons.types.converters.*",
            "javax.naming.*",
            "javax.sql.*",
            entityInfo.getDataPackage() + ".*"
        });
        theClass.setPackage(entityInfo.getEntityPackage());


        JavaUtils.initializeClassLog(entityInfo,J2eeTarget.MODULE_EJB,theClass);
        theClass.addField(new JavaField("entityContext", "EntityContext", null, "private", null));
        theClass.addField(new JavaField("_noChanges_", "boolean", null, "transient private", null));
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            DataTypeConverterFactory factory = columns[i].getSqlConverterFactory();
            if (factory != null) {
                theClass.addField(new JavaField(columns[i].getConverterFieldName() ,
                        "DataTypeConverter", null, "final private static", factory.getConverterExpression()));
            }
        }

        String[] entityReferencedHomes = entityInfo.getEntityReferences();
        for (int j = 0; j < entityReferencedHomes.length; j++) {
            JavaUtils.generateEntityGetHomeMethod(moduleCodeStyle.getDAOInfo(entityReferencedHomes[j]), theClass);
        }

        for (int i = 0; i < columns.length; i++) {
            JavaField field = new JavaField(columns[i].getBeanFieldVar(),
//                    columns[i].sqlDataType.toJavaType().getName(),
                    columns[i].getBusinessDataTypeName(),
                    null,
                    "private",
                    null);
            theClass.addField(field);
        }

        declareMethod(createMethodEjbCreate(entityInfo), entityInfo, theClass);
        theClass.addMethod(createMethodPostEjbCreate(entityInfo));
        declareMethod(createMethodGetData(entityInfo), entityInfo, theClass);
        declareMethod(createMethodSetData(entityInfo), entityInfo, theClass);
        declareMethod(createMethodEjbFindAll(entityInfo), entityInfo, theClass);
        declareMethod(createMethodEjbFindAllQBE(entityInfo), entityInfo, theClass);
        declareMethod(createMethodEjbFindByPrimaryKey(entityInfo), entityInfo, theClass);
        declareMethod(createMethodEjbHomeGetCount(entityInfo), entityInfo, theClass);
        declareMethod(createMethodEjbHomeGetCountQBE(entityInfo), entityInfo, theClass);
        declareMethod(createMethodEjbHomeRemoveAll(entityInfo), entityInfo, theClass);
        if (entityInfo.doOptimizeGetAll()) {
            declareMethod(createMethodEjbGetAllQuickly(entityInfo), entityInfo, theClass);
            declareMethod(createMethodEjbGetAllQuicklyQBE(entityInfo), entityInfo, theClass);
        } else {
            declareMethod(createCollectionFinder("all", entityInfo.getMethodGetDataExtraParams(), entityInfo), entityInfo, theClass);
            declareMethod(createCollectionFinderQBE("all", entityInfo.getMethodGetDataExtraParams(), entityInfo), entityInfo, theClass);
        }
//        theClass.addMethod(createMethodEjbHomeUpdate(entityInfo));


//------------------------------------------------------------


        theClass.addMethod(new JavaMethod("setEntityContext", "void", new JavaParam[]{
            new JavaParam("entityContext", "EntityContext", null)
        }, "public", new String[]{"EJBException"}, null, "this.entityContext = entityContext;"));


        theClass.addMethod(createMethodTransform(entityInfo));
        theClass.addMethod(new JavaMethod("unsetEntityContext", "void", null, "public", null, null, "this.entityContext = null;"));
        theClass.addMethod(new JavaMethod("ejbPassivate", "void", null, "public", new String[]{"EJBException"}, null, ""));
        theClass.addMethod(createMethodEjbLoad(entityInfo));
        theClass.addMethod(createMethodEjbStore(entityInfo));
        theClass.addMethod(createMethodEjbRemove(entityInfo));
        theClass.addMethod(createMethodEjbActivate(entityInfo));
        theClass.addMethod(JavaUtils.createMethodOpenConnection(entityInfo.getProjectInfo()));
        theClass.addMethod(createMethodCloseConnection(entityInfo));
        theClass.addMethod(createMethodBuildCriteriaQBE(entityInfo));

        DBColumn[] extraColumns = entityInfo.getColumns(false, false, true);
        for (int i = 0; i < extraColumns.length; i++) {
            theClass.addMethod(createMethodGetSpecificField(entityInfo, extraColumns[i]));
        }
        columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            DBColumn dbColumn = columns[i];
            FieldFormulaType setterType = dbColumn.getSetterImpl().getType();
            if (setterType != FieldFormulaType.none) {
                theClass.addMethod(createMethodSetSpecificField(entityInfo, dbColumn));
            }

        }

        entityInfo.setGeneratedClass("EntityBean", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating BMP Bean Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    private static JavaMethod declareMethod(JavaMethod m, DAOInfo entityInfo, JavaClassSource theClass) {
        JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
        JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
        entityInfo.declareMethod(theClass, m, "entity");
        return m;
    }


    private JavaMethod createMethodTransform(DAOInfo entityInfo) {
        Vector vparams = new Vector();
        vparams.add(new JavaParam("src", "Collection", null));
        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));

        return new JavaMethod("_transform_", "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "private",
                new String[]{"RemoteException"}, null,

                "ArrayList list=new ArrayList();\n" +
                "for(Iterator i=src.iterator();i.hasNext();){\n" +
                "  " + entityInfo.getEntityRemoteName() + " e=(" + entityInfo.getEntityRemoteName() + ")i.next();\n" +
                "  list.add(e.getData(propertyList" + entityInfo.getMethodGetDataExtraParamNamesString(true) + "));\n" +
                "}\n" +
                "return list;");
    }


//    private JavaMethod createMethodOpenConnection2(EntityInfo entityInfo) {
//        ModuleMetaData moduleCodeStyle = entityInfo.getModuleCodeStyle();
//        StringBuffer body = new StringBuffer();
//        body
//                .append("try{\n")
//                .append("  Class.forName(\"").append(entityInfo.getModuleCodeStyle().getConnectionDriverName()).append("\");\n")
////        #      _conn_=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@syspc138:1521:iris3","ro_ejb","ro_ejb");\n\
//
//                .append("  return .sql.DriverManager.getConnection(\"").append(entityInfo.getModuleCodeStyle().getConnectionURL()).append("\",\"" + entityInfo.getModuleCodeStyle().getConnectionUserName() + "\",\"" + entityInfo.getModuleCodeStyle().getConnectionUserPassword() + "\"));\n")
//                .append("}catch(Exception e){\n")
//                .append("   throw new EJBException(\"Could not open connection : \"+e);\n")
//                .append("}");
//        return new JavaMethod("_openConnection_", "Connection", null, "private",
//                              new String[]{"EJBException"}, null,
//                              body);
//
//    }

    protected JavaMethod createMethodCloseConnection(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("if(connection!=null){\n")
                .append("  try{ \n")
                .append("    connection.close();\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new EJBException(\"Could not close connection : \"+e);\n")
                .append("  }\n")
                .append("}");
        return new JavaMethod("_closeConnection_", "void", new JavaParam[]{new JavaParam("connection", "Connection", null)}, "private",
                new String[]{"EJBException"}, null,
                body);

    }

    private JavaMethod createMethodSetData(final DAOInfo entityInfo) {
//        String preSetDataCode=entityInfo.getPreUpdateUserCode();
        StringBuilder methodCorps = new StringBuilder();
        String codeReplace = getUserCode("update.userCodeReplace",entityInfo, false);
        if (codeReplace != null) {
            //check
            ArrayList kList = new ArrayList();
            kList.add("preUpdate");
            kList.add("postUpdate");
            String[] k = (String[]) kList.toArray(new String[kList.size()]);
            for (int i = 0; i < k.length; i++) {
                String c = getUserCode(k[i],entityInfo,false);
                if (c != null) {
                    throw new IllegalArgumentException(entityInfo.getBeanName() + " : data.userCodeReplace.userCode is not allowed with " + k[i]);
                }
            }
            kList = new ArrayList();
            DBColumn[] cc = entityInfo.getColumns(true, true, false);
            for (int i = 0; i < cc.length; i++) {
                //TODO toujours faut!!!!
                String c = entityInfo.getUserCodeForField(cc[i].getBeanFieldName(),"preInsert");
                if (c != null) {
                    throw new IllegalArgumentException(entityInfo.getBeanName() + " : data.userCodeReplace.userCode is not allowed with " + k[i]);
                }
            }

            // replaceCode
            methodCorps.append(codeReplace);
        } else {
            methodCorps.append("// START DirtyUpdate Cheking\n");
            methodCorps.append("if(oldData!=null){\n");
            DBColumn[] allColumns = entityInfo.getColumns(true, true, false);
            methodCorps.append(new JavaSourceCodeFieldsSwitcher("oldData.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
                public String getFieldNameCode(DBColumn dbColumn) {
                    if (dbColumn.isFormulaImpl()) {
                        return "// do nothing " + dbColumn.getBeanFieldName() + " is a view field";
                    }
                    return
                            dbColumn.getBusinessDataTypeName() + " _val_=(oldData." + (JavaUtils.businessGetterName(dbColumn)) + "());\n" +
                            "if(" +
                            (dbColumn.getBusinessDataType().toJavaType().isPrimitive()
                            ? (
                            "_val_!=" + dbColumn.getBeanFieldVar()
                            )
                            : (
                            "!((_val_==" + dbColumn.getBeanFieldVar() + ") || (_val_!=null && _val_.equals(" + dbColumn.getBeanFieldVar() + ")))"
                            )
                            ) + "){\n" +
                            "  throw new DirtyUpdateException(\"" + dbColumn.getBeanFieldName() + " has changed since your last read\");\n" +
                            "}\n";
                }
            }.getCode(2));
            methodCorps.append("}\n");
            methodCorps.append("// END   DirtyUpdate Cheking\n");
            methodCorps.append("\n");

            methodCorps.append("// START ForbiddenFieldOnUpdate Cheking\n");
            for (int i = 0; i < allColumns.length; i++) {
                if (allColumns[i].isForbiddenOnUpdate()) {
                    methodCorps.append("  if(newData.contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                    methodCorps.append("    throw new ForbiddenFieldOnUpdateException(").append(JavaUtils.getDataFieldSourceCode(entityInfo, allColumns[i])).append(");\n");
                    methodCorps.append("  }\n");
                }
            }
            methodCorps.append("// END   ForbiddenFieldOnUpdate Cheking\n");
            methodCorps.append("\n");

            //        methodCorps.append(new JavaSourceCodeFieldsSwitcher("newData.keySet()", false,true,entityInfo.getColumns(true, true, true)) {
            //            public String getFieldNameCode(DBColumn dbColumn) {
            //                if (dbColumn.getQueryType() > 0) {
            //                    return "// do nothing ; " + dbColumn.getBeanFieldName() + " is a view field";
            //                }
            //                return
            //                        dbColumn.getBusinessDataTypeName() + " _val_=(oldData." + (dbColumn.businessGetterName()) + "());\n" +
            //                        "if(" +
            //                        (dbColumn.getBusinessDataType().toJavaType().isPrimitive()
            //                        ? (
            //                        "_val_!=" + dbColumn.getBeanFieldVar()
            //                        )
            //                        : (
            //                        "!((_val_==" + dbColumn.getBeanFieldVar() + ") || (_val_!=null && _val_.equals(" + dbColumn.getBeanFieldVar() + ")))"
            //                        )
            //                        ) + "){\n" +
            //                        "  throw new DirtyUpdateException(\"" + dbColumn.getBeanFieldName() + " has changed since your last read\");\n" +
            //                        "}\n";
            //            }
            //        }.getFieldNameCode(2));
            //        methodCorps.append("}\n");

            methodCorps.append(getUserCode("preUpdate", entityInfo));

            methodCorps.append("// START Local Fields Updates\n");
            methodCorps.append(new JavaSourceCodeFieldsSwitcher("newData.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
                public String getFieldNameCode(DBColumn dbColumn) {
                    FieldFormulaType setterType = dbColumn.getSetterImpl().getType();
                    if (setterType != FieldFormulaType.none) {
                        return JavaUtils.businessSetterName(dbColumn) + "(newData." + (JavaUtils.businessGetterName(dbColumn)) + "());\n" +
                                "break;";
                    }
                    if (dbColumn.isFormulaImpl()) {
                        return "throw new ForbiddenFieldOnUpdateException(" + JavaUtils.getDataFieldSourceCode(entityInfo,dbColumn) + ");\n";
                    } else if (dbColumn.getPkPosition() >= 0) {
                        return "//Primary field " + dbColumn.getBeanFieldVar() + "\n" +
                                dbColumn.getBusinessDataTypeName() + " _val_=(newData." + (JavaUtils.businessGetterName(dbColumn)) + "());\n" +
                                "if(" +
                                (dbColumn.getBusinessDataType().toJavaType().isPrimitive()
                                ? (
                                "_val_!=" + dbColumn.getBeanFieldVar()
                                )
                                : (
                                "!((_val_==" + dbColumn.getBeanFieldVar() + ") || (_val_!=null && _val_.equals(" + dbColumn.getBeanFieldVar() + ")))"
                                )
                                ) + "){\n" +
                                "  throw new IllegalUpdateKeyException(\"" + dbColumn.getBeanFieldName() + " is incompatible with current Record\");\n" +
                                "}\n" +
                                "break;";
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(entityInfo.getUserCodeForField(dbColumn.getBeanFieldName(),"preUpdate"));
                        sb.append(dbColumn.getBeanFieldVar()).append("=(newData.").append(JavaUtils.businessGetterName(dbColumn)).append("());\n");
                        sb.append("  _noChanges_=false;\n");
                        sb.append(entityInfo.getUserCodeForField(dbColumn.getBeanFieldName(),"postUpdate"));
                        sb.append("break;");
                        return sb.toString();
                    }
                }

                public String getSwitchDefaultCode() {
                    return "throw new UnknownFieldException(selectedFieldName);";
                }
            }.getCode());

            methodCorps.append("// END   Local Fields Updates\n");
            methodCorps.append(getUserCode("postUpdate", entityInfo));
        }
        JavaMethod m = new JavaMethod("update", "void", new JavaParam[]{
            new JavaParam("oldData", entityInfo.getDTOName(), null),
            new JavaParam("newData", entityInfo.getDTOName(), null)
        }, "public", new String[]{
            "FieldException", "DirtyUpdateException", "IllegalUpdateKeyException"
        }, "default updates",
                methodCorps.toString());
        return m;
    }

    private String getUserCode(String key, DAOInfo entityInfo,boolean commentsIfNotFound) {
        return entityInfo.getUserCodeForDO(key);
    }

    private String getUserCode(String key, DAOInfo entityInfo) {
        return entityInfo.getUserCodeForDO(key);
    }

    private JavaMethod createMethodGetData(DAOInfo entityInfo) {
//        String preGetDataCode=entityInfo.getPreSelectUserCode();
//        String postGetDataCode=entityInfo.getPostSelectUserCode();
        Map map = getFieldsHashcodeMapping(entityInfo.getColumns(true, true, true));
        StringBuilder methodCorps = new StringBuilder();
        methodCorps.append(entityInfo.getDTOName()).append(" data=new ").append(entityInfo.getDTOName()).append("();\n");

        methodCorps.append(getUserCode("preSelect", entityInfo));

        methodCorps.append("// START Null Property List Handle (all fields are retreived)\n");
        methodCorps.append("if(propertyList==null){\n");
        DBColumn[] columns = entityInfo.getColumns(true, true, true);
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            String thisField =
                    c.getGetterImpl().getType() == FieldFormulaType.none ?
                    ("this." + c.getBeanFieldVar()) :
                    ("this." + JavaUtils.businessGetterName(c) + "(null" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ")");
            methodCorps.append("      data.").append(JavaUtils.businessSetterName(c)).append("(").append(thisField).append(");\n");
        }
        methodCorps.append("  return data;\n");
        methodCorps.append("}\n");
        methodCorps.append("// END   Null Property List Handle\n");
        methodCorps.append("\n");

        methodCorps.append("// START User Property List Handle\n");
        methodCorps.append("for(Iterator i=propertyList.iterator();i.hasNext();){\n");
        methodCorps.append("  String selectedFieldName=(String)i.next();\n");
        methodCorps.append("  int selectedFieldId=selectedFieldName.hashCode();\n");
        methodCorps.append("  switch(selectedFieldId){\n");

        for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            methodCorps.append("    case ").append(((Integer) entry.getKey()).intValue()).append(":{\n");
            if (entry.getValue() instanceof DBColumn) {
                DBColumn c = (DBColumn) entry.getValue();
                String thisField =
                        c.getGetterImpl().getType() == FieldFormulaType.none ?
                        ("this." + c.getBeanFieldVar())
                        : ("this." + JavaUtils.businessGetterName(c) + "(null" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ")");
//                String fid = Utils.capitalize(f.name);
                methodCorps.append("       //field ").append(c.getBeanFieldVar()).append("\n");
                methodCorps.append("      data.").append(JavaUtils.businessSetterName(c)).append("(").append(thisField).append(");\n");
            } else {
                Vector v = (Vector) entry.getValue();
                for (int j = 0; j < v.size(); j++) {
                    DBColumn c = (DBColumn) v.get(j);
                    String thisField = c.getGetterImpl().getType() == FieldFormulaType.none ?
                            ("this." + c.getBeanFieldVar()) :
                            ("this." + JavaUtils.businessGetterName(c) + "(null" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ")");
                    String ifEleif = (j == 0) ? "if" : "}else if";
                    methodCorps.append("         ").append(ifEleif).append("(\"").append(c.getBeanFieldName()).append("\".equals(selectedFieldName)){\n");
                    methodCorps.append("      //field ").append(c.getBeanFieldName()).append("\n");
                    methodCorps.append("      data.").append(JavaUtils.businessSetterName(c)).append("(").append(thisField).append(");\n");
                }
                methodCorps.append("         }else{\n");
                methodCorps.append("            throw new UnknownFieldException(selectedFieldName);");
                methodCorps.append("         }");
            }
            methodCorps.append("          break;\n");
            methodCorps.append("    }\n");

        }
        methodCorps.append("    default:{\n");
        methodCorps.append("      throw new UnknownFieldException(selectedFieldName);\n");
        methodCorps.append("    }\n");
        methodCorps.append("  }\n");
        methodCorps.append("}\n");

        methodCorps.append("// END   User Property List Handle\n");
        methodCorps.append(getUserCode("postSelect", entityInfo));

        methodCorps.append("return data;\n");
        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        JavaMethod m = new JavaMethod("getData", entityInfo.getDTOName(),
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
                , "public",
                new String[]{
                    "FieldException"
                }, "default filtred fields requests",
                methodCorps);
        return m;
    }

    private JavaMethod createMethodPostEjbCreate(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        //
        DBColumn[] columns = entityInfo.getColumns(false, true, true);
        for (int i = 0; i < columns.length; i++) {
            DBColumn dbColumn = columns[i];
            FieldFormulaType setterImplType = dbColumn.getCreatorImpl().getType();
            switch (setterImplType) {
                case sqlMasterDetail:
                    {
                        ConfigNode q = null;
                        q = dbColumn.getCreatorImpl().getBody();
                        RelationDesc relationDesc = new RelationDesc(q,
                                dbColumn,
                                entityInfo);
                        if (relationDesc.getDetailTable().getDAOInfo().doGenerateEntity()) {
                            body.append("try {\n" + "      Collection ").append(dbColumn.getBeanFieldVar()).append(" =data.").append(JavaUtils.businessGetterName(dbColumn)).append("();\n" + "      if(").append(dbColumn.getBeanFieldVar()).append("!=null){;\n" + "        org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate ejbClient=new org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate();\n" + "        ejbClient.connect(\"localhost\",-1,null,null,\"").append(entityInfo.getProjectInfo().getModuleName()).append("\",\"").append(entityInfo.getProjectInfo().getTargetApplicationServer()).append("\");\n");
                            body.append("        ").append(relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName()).append(" home = (").append(relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName()).append(") ejbClient.getHome(").append(relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName()).append(".class);\n" + "        for (Iterator i = ").append(dbColumn.getBeanFieldVar()).append(".iterator(); i.hasNext();) {\n" + "          ").append(relationDesc.getDetailTable().getDAOInfo().getFullDTOName()).append(" line = (").append(relationDesc.getDetailTable().getDAOInfo().getFullDTOName()).append(") i.next();\n");
                            for (int j = 0; j < relationDesc.getForeignFields().length; j++) {
                                body.append("          line.").append(JavaUtils.businessSetterName(relationDesc.getDetailTable().getDAOInfo().getColumnByFieldName(relationDesc.getDetailPrimaryFields()[j], true))).append("(" + "this.").append(entityInfo.getColumnByFieldName(relationDesc.getForeignFields()[j], true).getBeanFieldVar()).append(");\n");
                            }

                            body.append("          home.create(line);\n" +
                                    "        }\n" +
                                    "      }\n" +
                                    "}catch (NamingException e) {\n" +
                                    "   throw new EJBException(e);\n" +
                                    "} catch (RemoteException e) {\n" +
                                    "   throw new EJBException(e);\n" +
                                    "}\n");
                        } else if (relationDesc.getDetailTable().getDAOInfo().doGenerateDAO()) {
                            body.append("   java.sql.Connection  _conn");
                            body.append(i);
                            body.append("_=_openConnection_();\n" + "try {\n" + "      _conn_=_openConnection_();\n" + "      ");
                            body.append(relationDesc.getDetailTable().getDAOInfo().getFullDAOName());
                            body.append(" _dbcon_ = new ");
                            body.append(relationDesc.getDetailTable().getDAOInfo().getFullDAOName());
                            body.append("();\n" + "      _dbcon_.setConnection(_conn");
                            body.append(i);
                            body.append("_);\n" + "      Collection ");
                            body.append(dbColumn.getBeanFieldVar());
                            body.append(" =data.");
                            body.append(JavaUtils.businessGetterName(dbColumn));
                            body.append("();\n" + "      for (Iterator i = ");
                            body.append(dbColumn.getBeanFieldVar());
                            body.append(".iterator(); i.hasNext();) {\n" + "          ");
                            body.append(relationDesc.getDetailTable().getDAOInfo().getFullDTOName());
                            body.append(" line = (");
                            body.append(relationDesc.getDetailTable().getDAOInfo().getFullDTOName());
                            body.append(") i.next();\n" + "          _dbcon_.add(line);\n" + "      }\n" + "}catch (SQLException sqlExcp) {\n");
                            body.append(
                                    getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp")
                            );
                            body.append("   sqlExcp.printStackTrace();\n" + "   throw new EJBException(sqlExcp.getMessage());\n" + "} finally {\n" + "   _closeConnection_(_conn");
                            body.append(i);
                            body.append("_);\n" + "}\n");
                        } else {
                            throw new IllegalArgumentException("Table " + relationDesc.getDetailTable().getTableName() + " has neither Entity nor DataAccessObject associated to it.");
                        }
                        break;
                    }
                case none:
                    {
                        break;
                    }
                default:
                    {
                        throw new IllegalArgumentException("Unsuppported FieldFormulaType " + setterImplType);
                    }
            }
        }
        //
        body.append(getUserCode("postInsert", entityInfo));
        ArrayList params = new ArrayList();
        params.add(new JavaParam("data", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodCreateDataExtraParams()));

        JavaMethod m = new JavaMethod("ejbPostCreate", "void",
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
                , "public",
                new String[]{"CreateException"}, "EJB Constructor",
                body.toString());
        return m;
    }

    private JavaMethod createMethodEjbCreate(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        DBColumn[] allColumns = entityInfo.getColumns(true, true, false);
        body.append("// START Prologue initialization\n");
        for (int i = 0; i < allColumns.length; i++) {
            body.append("this.").append(allColumns[i].getBeanFieldVar()).append("=").append(entityInfo.getFieldInitialValue(allColumns[i])).append(";\n");
        }
        body.append("// END   Prologue initialization\n");

        body.append("// START Prologue checking\n");

        for (int i = 0; i < allColumns.length; i++) {
            if (allColumns[i].isRequiredOnInsert()) {
                body.append("if(!data.contains").append(JBGenUtils.capitalize(allColumns[i].getBeanFieldName())).append("()){\n");
                body.append("  throw new RequiredFieldOnInsertException(").append(JavaUtils.getDataFieldSourceCode(allColumns[i].getDAOInfo(), allColumns[i])).append(");\n");
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
                .append("  _conn_=_openConnection_();\n");

        DBColumn[] keys = entityInfo.getColumns(true, false, false);
        String sequenceName = entityInfo.getSequenceName();
        if (sequenceName != null) {
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
            body.append("  String _selectNewIdStatement_ = \"SELECT ").append(sequenceName).append(".NEXTVAL FROM dual\";\n");

            body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<Sequence Handling Query> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_selectNewIdStatement_)",null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_selectNewIdStatement_);\n");
            body.append("  ResultSet _rs_=_prepStmt_.executeQuery();\n");
            body.append("  if(_rs_.next()){\n");
            //            body.append("    this.").append(keys[0].getBeanFieldVar()).append("= _rs_."+keys[0].getPreparedStatementGetterName()+"(1);\n");
            body.append("    data.").append(JavaUtils.businessSetterName(keys[0])).append("(").append(JavaUtils.getPreparedStatementGetterExpression(keys[0], "_rs_", "1")).append(");\n");
            //            body.append("    data.setOrderId(newId);\n");
            body.append("    _rs_.close();\n");
            body.append("    _prepStmt_.close();\n");
            body.append("  }\n");
            body.append("  _rs_.close();\n");
            body.append("  _prepStmt_.close();\n");
            body.append("  // END Sequence Handling\n");

        } else {
            for (DBColumn dbColumn : keys) {
                body.append("  ").append(dbColumn.getBeanFieldVar()).append(" = ")
                        .append("data.").append((JavaUtils.businessGetterName(dbColumn))).append("();\n");
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
                        sb.append("throw new ForbiddenFieldOnInsertException(").append(JavaUtils.getDataFieldSourceCode(entityInfo, dbColumn)).append(");\n");
                    } else {
                        sb.append("break;");
                    }
                    return sb.toString();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(entityInfo.getUserCodeForField(dbColumn.getBeanFieldName(),"preInsert"));
                    sb.append(dbColumn.getBeanFieldVar()).append("=(data.").append(JavaUtils.businessGetterName(dbColumn)).append("());\n");
                    sb.append(entityInfo.getUserCodeForField(dbColumn.getBeanFieldName(),"postInsert"));
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
            prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
            for (int i = 0; i < bpkcolumns.length; i++) {
                DBColumn c = bpkcolumns[i];
                if (!c.isAutoIdentity()) {
                    prepareLog
                            .append("    _logElements_.add(")
                            .append("this.")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            for (int i = 0; i < ucolumns.length; i++) {
                DBColumn c = ucolumns[i];
                if (!c.isAutoIdentity()) {
                    prepareLog.append("  _logElements_.add(")
                            .append("this.")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<INSERT> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_insertStatement_,_logElements_)",null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_insertStatement_);\n");
            int pos = 1;
            for (int i = 0; i < bpkcolumns.length; i++) {
                DBColumn c = bpkcolumns[i];
                if (!c.isAutoIdentity()) {
                    body
                            .append("  _prepStmt_.")
                            .append(JavaUtils.getPreparedStatementSetterName(c))
                            .append("(")
                            .append(pos)
                            .append(",this.")
                            .append(c.getBeanFieldVar())
                            .append(");\n");
                    pos++;
                }
            }
            for (int i = 0; i < ucolumns.length; i++) {
                DBColumn c = ucolumns[i];
                if (!c.isAutoIdentity()) {
                    body
                            .append("  _prepStmt_.")
                            .append(JavaUtils.getPreparedStatementSetterName(c))
                            .append("(")
                            .append(pos)
                            .append(",")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(c, "this." + c.getBeanFieldVar()))
                            .append(");\n");
                    pos++;
                }
            }

            body
                    .append("  _prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n");
        }
        body.append("  // END   Database persistance\n");
        body.append("\n");
        body
                .append("  // returning Identifier;\n")
                .append("  _noChanges_=true;\n").append("  return data.get").append(entityInfo.getDataKeyName()).append("();\n")
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new CreateException(sqlExcp.getMessage());\n")
                .append("}catch(RuntimeException rtmExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "rtmExcp"))
                .append("  throw rtmExcp;\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("data", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodCreateDataExtraParams()));
        JavaMethod m = new JavaMethod("ejbCreate", entityInfo.getDataKeyName(),
                (JavaParam[]) params.toArray(new JavaParam[params.size()])
                , "public",
                new String[]{
                    "CreateException", "FieldException"
                }, "EJB Constructor",
                body);
//        String roles=(entityInfo.getMethodPatternValue(theClass,m,"roles",null);
        return m;
    }

    private String getMethodGetSpecificFieldBodyForSQL_VIEW(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("Connection  _conn_=null;\n")
                .append("String _selectStatement_ = null;\n")
                .append("PreparedStatement _prepStmt_  = null;\n")
                .append("ResultSet _rs_  = null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");
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

        StringBuilder prepareLog = new StringBuilder();
        prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                prepareLog.append("    _logElements_.add(")
                        .append("this.")
                        .append(c.getBeanFieldVar())
                        .append(");\n");
            }
        }
        body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<SELECT> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_selectStatement_,_logElements_)",null));

        body.append("  _prepStmt_ = _conn_.prepareStatement(_selectStatement_);\n");
        int pos = 1;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.isPk()) {
                String var = c.isPk() ?
                        ("(tableKey==null? this." + c.getBeanFieldVar() + " : tableKey." + c.getBeanFieldVar() + ")")
                        : ("this." + c.getBeanFieldVar());
                body
                        .append("  _prepStmt_.")
                        .append(JavaUtils.getPreparedStatementSetterName(c))
                        .append("(")
                        .append(pos)
                        .append(",")
                        .append(var)
                        .append(");\n");
                pos++;
            }
        }
        body
                .append("  _rs_=_prepStmt_.executeQuery();\n")
                .append("  if(_rs_.next()){\n")
                .append("    return ")
                .append(JavaUtils.getPreparedStatementGetterExpression(column,"_rs_","1"))
                .append(";\n");
        body
                .append("  }else{\n")
                .append("    throw new EJBException(\"Loading row failed.\");\n")
                .append("  }\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_rs_!=null){\n")
                .append("      _rs_.close();\n")
                .append("      _prepStmt_.close();\n")
                .append("    }\n")
                .append("    _closeConnection_(_conn_);\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new EJBException(e);\n")
                .append("  }\n")
                .append("}");
        return body.toString();
    }

    private String getMethodGetSpecificFieldBodyForSQL_SQL_CALL(DAOInfo entityInfo, DBColumn column) {
        StringBuilder body = new StringBuilder();
        body.append("Connection  _conn_=null;\n")
                .append("String _call_ = null;\n")
                .append("java.sql.CallableStatement _callStmt_  = null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");
        SQLPattern pattern = new SQLPattern(column.getGetterImpl().getBody().getValue(), column, false);
        if (pattern.indexOfParamByInOutType(SQLPattern.SQLCallParam.RETURN) == null) {
            pattern = new SQLPattern("{RETURN} := " + column.getGetterImpl().getBody(), column, false);
        }
        body.append("  _call_=\"{CALL ").append(pattern.getQuery()).append("}\";\n");

        SQLPattern.SQLCallParam[] sqlCallParams;
        StringBuilder prepareLog = new StringBuilder();
        prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
        sqlCallParams = pattern.getParams();

        for (int i = 0; i < sqlCallParams.length; i++) {
            SQLPattern.SQLCallParam p = sqlCallParams[i];
            if (p.isReturnParam()) {
                //TODO take into consideration converter ??
                prepareLog.append("    _logElements_.add(\"out?:\"+").append(p.getPos()).append(");\n");
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
                    prepareLog.append("  _logElements_.add(\"in(").append(p.getPos()).append("):\"+").append(javaParams[javaParamsIndex].getName()).append(");\n");
                } else {
                    type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                    String var = c.isPk() ?
                            ("(tableKey==null? this." + c.getBeanFieldVar() + " : tableKey." + c.getBeanFieldVar() + ")")
                            : ("this." + c.getBeanFieldVar());
                    prepareLog.append("  _logElements_.add(\"in(").append(p.getPos()).append("):\"+").append(var).append(");\n");
                }
            } else {
                // do nothing
            }
        }

        body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<CALL> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_call_,_logElements_)",null));


        body.append("  _callStmt_ = _conn_.prepareCall(_call_);\n");
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
                    String var = c.isPk() ?
                            ("(tableKey==null? this." + c.getBeanFieldVar() + " : tableKey." + c.getBeanFieldVar() + ")")
                            : ("this." + c.getBeanFieldVar());

                    type = new Integer(JBGenUtils.toSqlType(c.getSqlDataType().toJavaType()));
                    body.append("  _callStmt_.").append(JavaUtils.getPreparedStatementMethodSetterName(type.intValue())).append("(").append(p.getPos()).append(",").append(var).append(");\n");
                }
            } else {
                // do nothing
            }
        }
        if (retPos < 0) {
            throw new IllegalArgumentException("Expected {RETURN}");
        }
        SQLPattern.SQLCallParam p = sqlCallParams[retPos];
//        body.append(getLogDebugCode(entityInfo,"db",null,"\"><CALL> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_call_)"));
        body.append("  _callStmt_.execute();\n");
        body.append("  return _callStmt_.").append(JavaUtils.getPreparedStatementMethodGetterName(column.getSqlType())).append("(").append(p.getPos()).append(");\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  try{\n")
                .append("    if(_callStmt_!=null){\n")
                .append("      _callStmt_.close();\n")
                .append("    }\n")
                .append("    _closeConnection_(_conn_);\n")
                .append("  }catch(Exception e){\n")
                .append("    throw new EJBException(e);\n")
                .append("  }\n")
                .append("}");
        return body.toString();
    }

    private JavaMethod createMethodGetSpecificField(DAOInfo entityInfo, DBColumn column) {
        String body = null;
        switch (column.getGetterImpl().getType()) {
            case code:
                {
                    body = column.getGetterImpl().getBody().getValue();
                    break;
                }
            case sqlQuery:
            case sqlView:
                {
                    body = getMethodGetSpecificFieldBodyForSQL_VIEW(entityInfo, column);
                    break;
                }
            case sqlCall:
            case sqlFunction:
                {
                    body = getMethodGetSpecificFieldBodyForSQL_SQL_CALL(entityInfo, column);
                    break;
                }
            case sqlMasterDetail:
                {
                    body = getMethodGetSpecificFieldBodyForRELATION(entityInfo, column);
                    break;
                }
            default:
                {
                    throw new IllegalArgumentException("Unhandled Formula type " +column.getGetterImpl().getType());
                }
        }

        ArrayList p = new ArrayList(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        p.add(0, new JavaParam("tableKey", entityInfo.getDataKeyName(), null));
        return new JavaMethod(JavaUtils.businessGetterName(column),
                column.getBusinessDataTypeName(),
                (JavaParam[]) p.toArray(new JavaParam[p.size()]),
                "public",
                new String[]{"EJBException"},
                null,
                body);

    }

    private JavaMethod createMethodSetSpecificField(DAOInfo entityInfo, DBColumn column) {
        String body = null;
        FieldFormulaImpl fieldFormula = column.getSetterImpl();
        switch (fieldFormula.getType()) {
            case code:
                {
                    body = fieldFormula.getBody().getValue();
                    break;
                }
//            case sqlQuery:
//                {
//                    body = getMethodSetSpecificFieldBodyForSQL_VIEW(entityInfo, column);
//                    break;
//                }
//            case sqlCall:
//                {
//                    body = getMethodGetSpecificFieldBodyForSQL_SQL_CALL(entityInfo, column);
//                    break;
//                }
            case sqlMasterDetail:
                {
                    body = getMethodSetSpecificFieldBodyForRELATION(entityInfo, column);
                    break;
                }
            default:
                {
                    throw new IllegalArgumentException("Unhandled Formula type " + fieldFormula.getTypeName());
                }
        }
        return new JavaMethod(JavaUtils.businessSetterName(column),
                "void",
                new JavaParam[]{
                    new JavaParam("value",
                            column.getBusinessDataTypeName(),
                            null)
                },
                "public",
                new String[]{"EJBException"},
                null,
                body);

    }

    private JavaMethod createMethodEjbLoad(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("_noChanges_=false;\n")
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n")
                .append("  PreparedStatement _prepStmt_=null;\n")
                .append("  ResultSet _rs_=null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        body.append("  String _selectStatement_ = null;\n");
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  _selectStatement_ = \"");
            body
                    .append("SELECT ");

            boolean first = true;
            DBColumn[] columns = dbTables[t].getColumns();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(", ");
                    }
                    body
                            .append(c.getTable().getTableName2())
                            .append(".")
                            .append(c.getColumnName2());
                }
            }

            body
                    .append(" FROM ")
                    .append(dbTables[t].getTableName2())
                    .append(" WHERE ");

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
                            .append(c.getTable().getTableName2())
                            .append(".")
                            .append(c.getColumnName2())
                            .append(" = ")
                            .append("?");
                }
            }
            body.append("\";\n");


            DBColumn[] pkColumns = new DBColumn[0];
            StringBuilder prepareLog = new StringBuilder();
            prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
            pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "this." + pkColumns[c.getPkPosition()].getBeanFieldVar()))
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<EJB-LOAD> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_selectStatement_,_logElements_)",null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_selectStatement_);\n");
            int pos = 1;
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    body
                            .append("  _prepStmt_.")
                            .append(JavaUtils.getPreparedStatementSetterName(c))
                            .append("(")
                            .append(pos)
                            .append(",")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "this." + pkColumns[c.getPkPosition()].getBeanFieldVar()))
                            .append(");\n");
                    pos++;
                }
            }

            body
                    .append("  _rs_=_prepStmt_.executeQuery();\n")
                    .append("  if(_rs_.next()){\n");
            pos = 1;
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    String part =
                            "    this." + c.getBeanFieldVar() + " = " +
                            (JavaUtils.getPreparedStatementGetterExpression(c,"_rs_",String.valueOf(pos))) +
                            ";\n";
                    if (c.getSqlConverterFactory() != null
                            && JavaUtils.JAVA_PRIMITIVE_TO_OBJECT_TYPES.containsKey(c.getBusinessDataTypeName())
                    ) {
                        body.append("    try{\n" + "  ").append(part).append("    }catch(NullPointerException _npe_){\n" + "       String _errMsg_= \"Field ").append(c.getDAOInfo().getBeanName()).append(".").append(c.getBeanFieldName()).append(" caused NullPointerException due to a non expected null value retreived from Database\";" + "       ")
                                .append(getLogCode(JavaLogManager.Level.ERROR, null, null,"_errMsg_")).append("       throw new CorruptedDatabaseException(_errMsg_);\n" + "    }\n");
                    } else {
                        body.append(part);
                    }
                    pos++;
                }
            }
            body
                    .append("  }else{\n").append("  ").append(getLogCode(JavaLogManager.Level.ERROR, null, "\"Loading row failed.may be for bad caches...\"", null))
                    .append("    throw new EJBException(\"Loading row failed.\");\n")
                    .append("  }\n")
                    .append("  _rs_.close();\n")
                    .append("  _prepStmt_.close();\n");
        }
        body
                .append("  _noChanges_=true;\n")
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null,"sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        return new JavaMethod("ejbLoad", "void", null, "public",
                new String[]{"EJBException"}, null,
                body);
    }


    private JavaMethod createMethodEjbStore(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("if(_noChanges_){\n")
                .append("  return;\n")
                .append("}\n")
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");
        body.append("  String _updateStatement_ = null;\n");
        body.append("  int _ucount_ = 0;\n");
        body.append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            body.append("  _updateStatement_ = \"");
            body
                    .append("UPDATE ")
                    .append(dbTables[t].getTableName2())
                    .append(" SET ");
            boolean first = true;
            DBColumn[] columns = dbTables[t].getColumns();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    if (first) {
                        first = false;
                    } else {
                        body
                                .append(", ");
                    }
                    body
                            .append(c.getColumnName2());
                    body
                            .append(" = ? ");
                }
            }

            body
                    .append(" WHERE ");
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
            body.append("\";\n");

            DBColumn[] pkColumns = new DBColumn[0];
            StringBuilder prepareLog = new StringBuilder();
            prepareLog.append("  org.vpc.neormf.commons.util.PrimitiveArrayList _logElements_=new org.vpc.neormf.commons.util.PrimitiveArrayList();\n");
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(c, "this." + c.getBeanFieldVar()))
                            .append(");\n");
                }
            }
            pkColumns = entityInfo.getPrimaryKeys();
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    prepareLog
                            .append("  _logElements_.add(")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "this." + pkColumns[c.getPkPosition()].getBeanFieldVar()))
                            .append(");\n");
                }
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, prepareLog.toString(), "\"<EJB-STORE> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_updateStatement_,_logElements_)",null));

            body.append("  _prepStmt_ = _conn_.prepareStatement(_updateStatement_);\n");
            int pos = 1;
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() < 0) {
                    body
                            .append("  _prepStmt_.")
                            .append(JavaUtils.getPreparedStatementSetterName(c))
                            .append("(")
                            .append(pos)
                            .append(",")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(c, "this." + c.getBeanFieldVar()))
                            .append(");\n");
                    pos++;
                }
            }
            for (int i = 0; i < columns.length; i++) {
                DBColumn c = columns[i];
                if (c.getPkPosition() >= 0) {
                    body
                            .append("  _prepStmt_.")
                            .append(JavaUtils.getPreparedStatementSetterName(c))
                            .append("(")
                            .append(pos)
                            .append(",")
                            .append(entityInfo.getProjectInfo().getConvertJavaToSQLExpression(pkColumns[c.getPkPosition()], "this." + pkColumns[c.getPkPosition()].getBeanFieldVar()))
                            .append(");\n");
                    pos++;
                }
            }

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new EJBException(\"Storing row failed.\");\n")
                    .append("  }\n");
        }
        body
                .append("  _noChanges_=true;\n")
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        return new JavaMethod("ejbStore", "void", null, "public",
                new String[]{"EJBException"}, null,
                body);
    }

    private JavaMethod createMethodEjbRemove(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("  _noChanges_=false;\n")
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n")
                .append("  int _ucount_=0;\n")
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
                    body
                            .append("  _prepStmt_.")
                            .append(JavaUtils.getPreparedStatementSetterName(c))
                            .append("(")
                            .append(pos)
                            .append(",this.")
                            .append(pkColumns[c.getPkPosition()].getBeanFieldVar())
                            .append(");\n");
                    pos++;
                }
            }

            // TODO ajouter le code
            body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<REMOVE> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_removeStatement_,null)",null));

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  if(_ucount_<=0){\n")
                    .append("    throw new RemoveException(\"Removing row failed.\");\n")
                    .append("  }\n");
        }
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        return new JavaMethod("ejbRemove", "void", null, "public",
                new String[]{
                    "RemoveException", "EJBException"
                }, null,
                body);
    }

    private JavaMethod createMethodEjbHomeRemoveAll(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("  _noChanges_=false;\n")
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n")
                .append("  int _ucount_=0;\n")
                .append("  String _removeStatement_ = null;\n")
                .append("  PreparedStatement _prepStmt_ = null;\n");

        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {

            body.append("  _removeStatement_ = \"");
            body
                    .append("DELETE FROM ")
                    .append(dbTables[t].getTableName2());
            body.append("\";\n");
            body.append("  _prepStmt_ = _conn_.prepareStatement(_removeStatement_);\n");

            body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<REMOVE> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_removeStatement_)",null));

            body
                    .append("  _ucount_=_prepStmt_.executeUpdate();\n")
                    .append("  _prepStmt_.close();\n");
        }
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("ejbHomeRemoveAll", "void", null, "public",
                new String[]{
                    "RemoveException", "EJBException"
                }, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbActivate(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body.append(entityInfo.getDataKeyName());
        body.append(" key = (");
        body.append(entityInfo.getDataKeyName());
        body.append(" ) entityContext.getPrimaryKey();\n");
        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("this.")
                        .append(c.getBeanFieldVar())
                        .append(" = key.")
                        .append(c.getBeanFieldVar())
                        .append(";\n");
            }
        }
        return new JavaMethod("ejbActivate", "void", null, "public",
                new String[]{"EJBException"}, null,
                body);
    }

    private JavaMethod createMethodEjbFindByPrimaryKey(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");

        body.append("  String _selectStatement_ = \"");
        body
                .append("SELECT 1 ")
                .append(" FROM ")
                .append(entityInfo.getTables()[0].getTableName2())
                .append(" WHERE ");

        boolean first = true;
        DBColumn[] columns = entityInfo.getColumns(true, true, false);
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
                        .append(c.getColumnName2())
                        .append(" = ")
                        .append("?");
            }
        }
        body.append("\";\n");


        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<FIND-BY-PRIMARY-KEY> \"+org.vpc.neormf.commons.util.Utils.dump(primaryKey)+\" : \" + _selectStatement_",null));

        body.append("  PreparedStatement _prepStmt_ = _conn_.prepareStatement(_selectStatement_);\n");
        int pos = 1;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("  _prepStmt_.")
                        .append(JavaUtils.getPreparedStatementSetterName(c))
                        .append("(")
                        .append(pos)
                        .append(",primaryKey.")
                        .append(c.getBeanFieldVar())
                        .append(");\n");
                pos++;
            }
        }

        body
                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
                .append("  if(_rs_.next()){\n")
                .append("    _rs_.close();\n")
                .append("    _prepStmt_.close();\n")
                .append("    return primaryKey;\n")
                .append("  }\n")
                .append("  _rs_.close();\n")
                .append("  _prepStmt_.close();\n")
                .append("  throw new ObjectNotFoundException(\"Row for id \" + primaryKey + \" not found\");\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("ejbFindByPrimaryKey", entityInfo.getDataKeyName(), new JavaParam[]{
            new JavaParam("primaryKey", entityInfo.getDataKeyName(), null)
        }, "public",
                new String[]{"FinderException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbFindAll(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");

        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"");
        body
                .append("SELECT ");

        boolean first = true;
        DBColumn[] columns = entityInfo.getColumns(true, true, false);
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                if (first) {
                    first = false;
                } else {
                    body
                            .append(", ");
                }
                body
                        .append(c.getTable().getTableName2())
                        .append(".")
                        .append(c.getColumnName2());
            }
        }

        body
                .append(" FROM ");
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
        if (dbTables.length > 1) {
            body.append("  _selectStatement_.append(\"");
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
        body.append("  ArrayList _sqlStatementParamsProviderArrayList_=new ArrayList();\n");
        body.append("  if(order!=null){\n");
        body.append("    _selectStatement_.append(\" ORDER BY \");\n");

        body.append("    boolean orderFirst=true;\n");


        JavaSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
                new JavaSourceCodeFieldsSwitcher("order.iterator()",
                        "OrderList.OrderItem item=(OrderList.OrderItem) i.next();" +
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
                            SQLPattern sqlString = JBGenUtils.getSearchSQL(dbColumn);
                            sb.append("_selectStatement_.append(\"").append(sqlString.getQuery()).append("\");\n");

                            sb.append("_selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
//                            sb.append(JBGenUtils.getPopulateSqlStatementParamsProviderArrayList(dbColumn,"_sqlStatementParamsProviderArrayList_","statement"));
                            sb.append("break;");
                        } else {
                            sb.append("throw new ForbiddenFieldOnSearchException(").append(JavaUtils.getDataFieldSourceCode(dbColumn.getDAOInfo(), dbColumn)).append(");\n");
                        }
                        return sb.toString();
                    }
                };
        sourceCodeFieldsSwitcher.setSwitchDefaultCode(new StringBuilder()
                .append("//WHEN UNKNOWN FIELD PASSE IT AS IS TO SQL\n")
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
        body.append("  }\n");
        //OLD
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

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<FIND> : \"" +
                "\n\t+\"QUERY = \"+_selectStatement_" +
                "\n\t+\"\\tCRITERIA = \"+criteria" +
                "\n\t+\"\\tORDER = \"+order" +
                "\n\t",null));
        body.append("  ArrayList list=new ArrayList();\n");
        body.append("  int _min_=-1;\n");
        body.append("  int _max_=-1;\n");
        body.append("  int _statementParamPos_=criteria==null?1:criteria.getParamCount()+1;\n");
        body.append("  PreparedStatement _prepStmt_ = _conn_.prepareStatement(_selectStatement_.toString());\n");
        body.append("  if(criteria!=null){\n");
        body.append("    criteria.populateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("    _min_=criteria.getMinRowIndex();\n");
        body.append("    _max_=criteria.getMaxRowIndex();\n");
        body.append("  }\n");
        body.append("  for(Iterator _i_=_sqlStatementParamsProviderArrayList_.iterator();_i_.hasNext();){\n");
        body.append("    SqlStatementParamsProvider _p_=(SqlStatementParamsProvider) _i_.next(); \n");
        body.append("    _statementParamPos_+=_p_.populateStatement(_prepStmt_,_statementParamPos_); \n");
        body.append("  }\n");
        body
                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
                .append("  int _count_=0;\n")
                .append("  while(_count_<_min_ && _rs_.next()){\n")
                .append("    _count_++;\n")
                .append("  }\n")
                .append("  while((_max_<0 || _count_<=_max_) && _rs_.next()){\n")
                .append("    _count_++;\n");
        int pos = 1;
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("    ")
                        .append(c.getBeanFieldVar())
                        .append("  = ")
                        .append(JavaUtils.getPreparedStatementGetterExpression(c,"_rs_",String.valueOf(pos)))
                        .append(";\n");
                pos++;
            }
        }
        body
                .append("  ")
                .append(entityInfo.getDataKeyName())
                .append(" key = new ")
                .append(entityInfo.getDataKeyName())
                .append("();\n");
        for (int i = 0; i < columns.length; i++) {
            DBColumn c = columns[i];
            if (c.getPkPosition() >= 0) {
                body
                        .append("    key.")
                        .append(c.getBeanFieldVar())
                        .append("  = ")
                        .append(c.getBeanFieldVar())
                        .append(";\n");
            }
        }

        body
                .append("    list.add(key);\n");
        body
                .append("  }\n")
                .append("  _rs_.close();\n")
                .append("  _prepStmt_.close();\n")
                .append("  return list;\n");
        body
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("ejbFindAll", "Collection", new JavaParam[]{
            new JavaParam("criteria", "Criteria", null),
            new JavaParam("order", "OrderList", null),
        }, "public",
                new String[]{"EJBException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbGetAllQuickly(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");

        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"SELECT \");\n");

        body.append("  ArrayList _sqlStatementParamsProviderArrayList_=new ArrayList();\n");
        body.append("  if(propertyList==null){\n");
        ArrayList a = new ArrayList();
        a.addAll(Arrays.asList(entityInfo.getColumns(true, false, false)));
        a.addAll(Arrays.asList(entityInfo.getColumns(true, true, true)));
        DBColumn[] allpkPlusColumns = (DBColumn[]) a.toArray(new DBColumn[a.size()]);

        boolean added = false;
        body.append("    _selectStatement_.append(\"");
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
            body.append(c.getColumnName2());
        }
        body.append("\");\n");
        body.append(JBGenUtils.indent(new JavaSourceCodeFieldsSwitcher("propertyList.propertiesSet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, true, entityInfo.getColumns(true, true, true)) {
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

        JavaSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
                new JavaSourceCodeFieldsSwitcher("order.iterator()",
                        "OrderList.OrderItem item=(OrderList.OrderItem) i.next();" +
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
                            sb.append("_selectStatement_.append(\"").append(JBGenUtils.getSearchSQL(dbColumn).getQuery()).append("\");\n");
                            sb.append("_selectStatement_.append(\" \").append(item.isAscendent()?\"ASC\":\"DESC\");\n");
                            sb.append("break;");
                        } else {
                            sb.append("throw new ForbiddenFieldOnSearchException(").append(JavaUtils.getDataFieldSourceCode(dbColumn.getDAOInfo(), dbColumn)).append(");\n");
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
                +"                 +\"\\n          Criteria =\"+criteria\n"
                +"                 +\"\\n          Order    =\"+order"
        ,null));

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
                body.append(JavaUtils.getPreparedStatementGetterExpression(pkcolumns[i],"_rs_",(i + 1)));
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
                    if (s.length() > 0) {
                        s = "_tableKey_," + s;
                    } else {
                        s = "_tableKey_";
                    }
                }
                thisField = (JavaUtils.businessGetterName(dbColumn) + "(" + s + ")");
                body.append("      data.").append(JavaUtils.businessSetterName(dbColumn)).append("(").append(thisField).append(");\n");
            }
        }
        //
        body
                .append("      list.add(data);\n")
                .append("    }\n")
                .append("  }else{\n")
                .append("    while((_max_<0 || _count_<=_max_) && _rs_.next()){\n")
                .append("      _count_++;\n").append("      int _col_=").append(pkcolumns.length + 1).append(";\n");
        if (pkcolumns.length > 0) {
            body.append("      ").append(entityInfo.getDataKeyName()).append(" ").append("_tableKey_ = new ").append(entityInfo.getDataKeyName()).append("(");
            for (int i = 0; i < pkcolumns.length; i++) {
                if (i > 0) {
                    body.append(",");
                }
                body.append(JavaUtils.getPreparedStatementGetterExpression(pkcolumns[i],"_rs_",(i + 1)));
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
                    if (dbColumn.getDAOInfo().getPrimaryKeys().length > 0) {
                        if (s.length() > 0) {
                            s = "_tableKey_," + s;
                        } else {
                            s = "_tableKey_";
                        }
                    }
                    thisField =
                            (JavaUtils.businessGetterName(dbColumn) + "(" + s + ")");
                    return ("        data." + JavaUtils.businessSetterName(dbColumn) + "(" + thisField + ");\n");
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
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");

        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new JavaParam("criteria", "Criteria", null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindDataExtraParams()));
        params.add(new JavaParam("order", "OrderList", null));

        JavaMethod m = new JavaMethod("ejbHomeGetAll", "Collection",
                (JavaParam[]) params.toArray(new JavaParam[params.size()]),
                "public",
                new String[]{"FieldException", "FinderException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbFindAllQBE(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        ArrayList params = new ArrayList();
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        JavaParam[] paramsArray = (JavaParam[]) params.toArray(new JavaParam[params.size()]);

        body.append("  return ejbFindAll("+ JavaDAOGenerator.METHOD_TO_CRITERIA+"(");
        body.append(JavaParam.toCommaSeparatedString(paramsArray));
        body.append("),null);\n");

        JavaMethod m = new JavaMethod("ejbFindAll",
                "Collection",
                ((JavaParam[]) params.toArray(new JavaParam[params.size()])),
                "public",
                new String[]{"FieldException", "EJBException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbGetAllQuicklyQBE(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        JavaParam[] findAllDataExtraParams = entityInfo.getMethodFindDataExtraParams();
        ArrayList ejbHomeGetAllQBE_params = new ArrayList();
        ejbHomeGetAllQBE_params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        ejbHomeGetAllQBE_params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        ejbHomeGetAllQBE_params.addAll(Arrays.asList(findAllDataExtraParams));
        if (!entityInfo.removeOrderListForGetAllQBE()) {
            ejbHomeGetAllQBE_params.add(new JavaParam("order", "OrderList", null));
        }

        ArrayList _buildCriteriaQBE__params = new ArrayList();
        _buildCriteriaQBE__params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        _buildCriteriaQBE__params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));

        body.append("  return ejbHomeGetAll(propertyList,");
        body.append(JavaDAOGenerator.METHOD_TO_CRITERIA).append("(");
        body.append(JavaParam.toCommaSeparatedString((JavaParam[]) _buildCriteriaQBE__params.toArray(new JavaParam[_buildCriteriaQBE__params.size()])));
        body.append("),");
        if (findAllDataExtraParams.length > 0) {
            body.append(JavaParam.toCommaSeparatedString(findAllDataExtraParams));
            body.append(",");
        }
        if (!entityInfo.removeOrderListForGetAllQBE()) {
            body.append("order");
        } else {
            body.append("null");
        }
        body.append(");\n");

        JavaMethod m = new JavaMethod("ejbHomeGetAll",
                "Collection",
                ((JavaParam[]) ejbHomeGetAllQBE_params.toArray(new JavaParam[ejbHomeGetAllQBE_params.size()])),
                "public",
                new String[]{"FinderException", "FieldException", "EJBException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbFindAllDataQBE(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));

        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        JavaParam[] paramsArray = (JavaParam[]) params.toArray(new JavaParam[params.size()]);

        body.append("  return ejbFindAllData(").append(JavaDAOGenerator.METHOD_TO_CRITERIA).append("(");
        body.append(JavaParam.toCommaSeparatedString(paramsArray));
        body.append("),null);\n");

        JavaMethod m = new JavaMethod("ejbFindAllData",
                "Collection",
                ((JavaParam[]) params.toArray(new JavaParam[params.size()])),
                "public",
                new String[]{"FieldException", "EJBException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbHomeGetCountQBE(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        ArrayList params = new ArrayList();
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        JavaParam[] paramsArray = (JavaParam[]) params.toArray(new JavaParam[params.size()]);

        body.append("  return ejbHomeGetCount("+JavaDAOGenerator.METHOD_TO_CRITERIA+"(");
        body.append(JavaParam.toCommaSeparatedString(paramsArray));
        body.append("));\n");

        JavaMethod m = new JavaMethod("ejbHomeGetCount", "int",
                (JavaParam[]) params.toArray(new JavaParam[params.size()]),
                "public",
                new String[]{"FieldException", "EJBException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodBuildCriteriaQBE(final DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();

        body
                .append("  Criteria criteria=null;\n");
        body
                .append("  // building criteria\n");
        body.append("if (prototype != null && prototype.size() > 0) {\n" +
                "  StringBuffer whereClause = new StringBuffer();\n" +
                "  criteria = new Criteria();\n" +
                "  int pos=1;\n");
        JavaSourceCodeFieldsSwitcher sourceCodeFieldsSwitcher =
                new JavaSourceCodeFieldsSwitcher("prototype.keySet().iterator()", JavaSourceCodeFieldsSwitcher.ITERATE_COLLECTION, false, entityInfo.getColumns(true, true, true)) {
                    public String getFieldNameCode(DBColumn dbColumn) {
                        String sb =
                                "if (whereClause.length() > 1) {\n" +
                                "  whereClause.append(\" AND \");\n" +
                                "}\n";
                        SQLPattern colExpression = null;
                        colExpression = JBGenUtils.getSearchSQL(dbColumn);
                        if (colExpression == null) {
                            return "throw new ForbiddenFieldOnSearchException(" + JavaUtils.getDataFieldSourceCode(dbColumn.getDAOInfo(),dbColumn) + ");\n";
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
                                case '{':
                                    {
                                        if (currentVar != null) {
                                            throw new IllegalArgumentException("Unexpected '{'");
                                        }
                                        currentVar = new StringBuilder();
                                        break;
                                    }
                                case '}':
                                    {
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
                                            javaExpression.append("criteria.").append(setter).append("(pos++,").append(vname).append(");\n");
                                        }

                                        currentVar = null;
                                        break;
                                    }
                                default:
                                    {
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
                };
        sourceCodeFieldsSwitcher.setSwitchDefaultCode("throw new UnknownFieldException(selectedFieldName);\n");
        body.append(sourceCodeFieldsSwitcher.getCode(2));
        body.append("\tcriteria.setWhereClause(whereClause.toString());\n" +
                "}\n");
        body.append(getUserCode("findAll", entityInfo)).append("\n");

        ArrayList<JavaParam> params = new ArrayList<JavaParam>();
        params.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodFindExtraParams()));
        body
                .append("  return criteria;\n");

        JavaMethod m = new JavaMethod(JavaDAOGenerator.METHOD_TO_CRITERIA, "Criteria",
                (JavaParam[]) params.toArray(new JavaParam[params.size()]),
                "public static", // for external use
//                "private",
                new String[]{"FieldException"}, null,
                body);
        return m;
    }

    private String getMethodGetSpecificFieldBodyForRELATION(DAOInfo entityInfo, DBColumn column) {
        DAOInfo localEntity = entityInfo;
        // fields ??
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
        DAOInfo masterEntityInfo = null;
        StringBuilder sqlJoin = new StringBuilder();

        Class retClass = null;
        try {
            retClass = Class.forName(column.getBusinessDataTypeName());
        } catch (ClassNotFoundException e) {
            try {
                retClass = Class.forName("java.util." + column.getBusinessDataTypeName());
            } catch (ClassNotFoundException e1) {
                throw new IllegalArgumentException("Unknown type " + column.getBusinessDataTypeName());
            }
        }
        if (!retClass.equals(Collection.class) && !Set.class.equals(retClass) && !List.class.equals(retClass)) {
            throw new IllegalArgumentException("You should use aither Collection, Set or List for field " + column.getBeanFieldName());
        }

        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(relationDesc.getDetailTable().getTableName(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < relationDesc.getDetailPrimaryFields().length; i++) {
            DBColumn c = masterEntityInfo.getColumnByFieldName(relationDesc.getDetailPrimaryFields()[i], true);
            if (i > 0) {
                sqlJoin.append(" AND ");
            }
            sqlJoin.append(relationDesc.getDetailTable().getTableName2()).append(".").append(c.getColumnName2());
            sqlJoin.append(" = ? ");
        }

        if (relationDesc.getDetailTable().getDAOInfo().doGenerateEntity()) {
            String r = "try {\n" +
                    "  org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate client=new org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate();\n" +
                    "  client.connect(\"localhost\",-1,null,null,\"" + entityInfo.getProjectInfo().getModuleName() + "\",\"" + entityInfo.getProjectInfo().getTargetApplicationServer() + "\");\n" +
                    "  " + relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName() + " home = (" + relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName() + ") client.getHome(" + relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName() + ".class);\n" +
                    "  Criteria _criteria_ = new Criteria(\"" + sqlJoin.toString() + "\");\n";

            for (int i = 0; i < relationDesc.getForeignFields().length; i++) {
                DBColumn c = localEntity.getColumnByFieldName(relationDesc.getForeignFields()[i], true);
                if (c.getPkPosition() >= 0) {
                    r += ("  _criteria_." + JavaUtils.getCriteriaSetterName(c) + "(" + (i + 1) +
                            ", " +
                            "tableKey==null? this." + c.getBeanFieldVar() + " : tableKey." + c.getBeanFieldVar() +
                            ");\n");
                } else {
                    r += ("  _criteria_." + JavaUtils.getCriteriaSetterName(c) + "(" + (i + 1) +
                            ", " +
                            "this." + c.getBeanFieldVar() +
                            ");\n");
                }
            }

            r += "  Collection _col_= home.find(null,_criteria_,null);\n";
            if (retClass.equals(Collection.class)) {
                r += "  return _col_;\n";
            } else if (Set.class.equals(retClass)) {
                r += "  return new ArrayList(_col_);\n";
            } else if (List.class.equals(retClass)) {
                r += "  return new ArrayList(_col_);\n";
            } else {
                throw new IllegalArgumentException("You should use aither Collection, Set or List for field " + column.getBeanFieldName());
            }
            r += "}catch (FinderException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "}catch (NamingException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "} catch (RemoteException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "}\n";
            return r;
        } else if (relationDesc.getDetailTable().getDAOInfo().doGenerateDAO()) {
            String r = "   java.sql.Connection  _conn_=_openConnection_();\n" +
                    "try {\n" +
                    "  _conn_=_openConnection_();\n" +
                    "  " + relationDesc.getDetailTable().getDAOInfo().getFullDAOName() + " _dbcon_ = new " + relationDesc.getDetailTable().getDAOInfo().getFullDAOName() + "();\n" +
                    "  _dbcon_.setConnection(_conn_);\n" +
                    "  Criteria _criteria_ = new Criteria(\"" + sqlJoin.toString() + "\");\n";

            for (int i = 0; i < relationDesc.getForeignFields().length; i++) {
                DBColumn c = localEntity.getColumnByFieldName(relationDesc.getForeignFields()[i], true);
                r += ("  _criteria_." + JavaUtils.getCriteriaSetterName(c) + "(" + (i + 1) + ", this." + c.getBeanFieldVar() + ");\n");
            }

            r += "  Collection  _col_=_dbcon_.find(null,_criteria_,null);\n";
            if (retClass.equals(Collection.class)) {
                r += "  return _col_;\n";
            } else if (Set.class.equals(retClass)) {
                r += "  return new ArrayList(_col_);\n";
            } else if (List.class.equals(retClass)) {
                r += "  return new ArrayList(_col_);\n";
            } else {
                throw new IllegalArgumentException("You should use either Collection, Set or List for field " + column.getBeanFieldName());
            }

            r += "}catch (SQLException sqlExcp) {\n" +
                    getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp") +
                    "   throw new EJBException(sqlExcp.getMessage());\n" +
                    "} finally {\n" +
                    "   _closeConnection_(_conn_);\n" +
                    "}\n";
            return r;
        } else {
            StringBuilder body = new StringBuilder();
            body
                    .append("Connection  _conn_=null;\n")
                    .append("try{\n")
                    .append("  _conn_=_openConnection_();\n");

            body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"");
            body
                    .append("SELECT ");

            boolean first = true;
            DBColumn[] rcolumns = relationDesc.getDetailTable().getColumns();
            for (int i = 0; i < rcolumns.length; i++) {
                DBColumn c = rcolumns[i];
                if (first) {
                    first = false;
                } else {
                    body
                            .append(", ");
                }
                body
                        .append(c.getTable().getTableName2())
                        .append(".")
                        .append(c.getColumnName2());
            }

            body
                    .append(" FROM ");
            body.append(relationDesc.getDetailTable().getTableName2());

            body.append(" WHERE ");
            body.append(sqlJoin.toString());

            body.append("\");\n");
            body.append("  ArrayList list=new ArrayList();\n");
            body.append("  PreparedStatement _prepStmt_ = _conn_.prepareStatement(_selectStatement_.toString());\n");
            DAOInfo relEntityInfo = null;
            try {
                relEntityInfo = localEntity.getProjectInfo().getDAOInfoForTable(relationDesc.getDetailTable().getTableName(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < relationDesc.getForeignFields().length; i++) {
                DBColumn c = localEntity.getColumnByFieldName(relationDesc.getForeignFields()[i], true);
                body.append("  _prepStmt_").append(".").append(JavaUtils.getPreparedStatementSetterName(c)).append("(").append(i + 1).append(", this.").append(c.getBeanFieldVar()).append(");\n");
            }
            body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<QUERY> \"+org.vpc.neormf.commons.sql.SQLUtils.logQuery(_selectStatement_)",null));
            body
                    .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
                    .append("  while(_rs_.next()){\n")
                    .append("    ").append(relEntityInfo.getFullDTOName()).append(" relData = new ").append(relEntityInfo.getFullDTOName()).append("();\n")
                    ;
            int pos = 1;
            for (int i = 0; i < rcolumns.length; i++) {
                DBColumn c = rcolumns[i];
                body
                        .append("    ")
                        .append("relData.").append(JavaUtils.businessSetterName(c)).append("(")
                        .append(JavaUtils.getPreparedStatementGetterExpression(c,"_rs_",pos ))
                        //.append(JavaUtils.getPreparedStatementGetterName(c))
//                        .append("_rs_.")
//                        .append("(")
//                        .append(pos)
//                        .append(")")
                        .append(");\n");
                pos++;
            }
            body
                    .append("    list.add(relData);\n");
            body
                    .append("  }\n")
                    .append("  _rs_.close();\n")
                    .append("  _prepStmt_.close();\n")
                    .append("  return list;\n");
            body
                    .append("}catch(SQLException sqlExcp){\n")
                    .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                    .append("  throw new EJBException(sqlExcp.getMessage());\n")
                    .append("}finally{\n")
                    .append("  _closeConnection_(_conn_);\n")
                    .append("}");
            return body.toString();
        }
    }

    private Class getCollectionClass(DBColumn column) {
        Class retClass = null;
        try {
            retClass = Class.forName(column.getBusinessDataTypeName());
        } catch (ClassNotFoundException e) {
            try {
                retClass = Class.forName("java.util." + column.getBusinessDataTypeName());
            } catch (ClassNotFoundException e1) {
                throw new IllegalArgumentException("Unknown type " + column.getBusinessDataTypeName());
            }
        }
        if (!retClass.equals(Collection.class) && !Set.class.equals(retClass) && !List.class.equals(retClass)) {
            throw new IllegalArgumentException("You should use aither Collection, Set or List for field " + column.getBeanFieldName());
        }
        return retClass;
    }

    private String getMethodSetSpecificFieldBodyForRELATION(DAOInfo entityInfo, DBColumn column) {
        DAOInfo localEntity = entityInfo;
        // fields ??
        RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
        DAOInfo masterEntityInfo = null;
        StringBuilder sqlJoin = new StringBuilder();

        Class retClass = getCollectionClass(column);

        try {
            masterEntityInfo = entityInfo.getProjectInfo().getDAOInfoForTable(relationDesc.getDetailTable().getTableName(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < relationDesc.getDetailPrimaryFields().length; i++) {
            DBColumn c = masterEntityInfo.getColumnByFieldName(relationDesc.getDetailPrimaryFields()[i], true);
            if (i > 0) {
                sqlJoin.append(" AND ");
            }
            sqlJoin.append(relationDesc.getDetailTable().getTableName2()).append(".").append(c.getColumnName2());
            sqlJoin.append(" = ? ");
        }

        if (relationDesc.getDetailTable().getDAOInfo().doGenerateEntity()) {
            String r = "try {\n" +
                    "  org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate client=new org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate();\n" +
                    "  client.connect(\"localhost\",-1,null,null,\"" + entityInfo.getProjectInfo().getModuleName() + "\",\"" + entityInfo.getProjectInfo().getTargetApplicationServer() + "\");\n" +
                    "  " + relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName() + " home = (" + relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName() + ") client.getHome(" + relationDesc.getDetailTable().getDAOInfo().getFullEntityHomeName() + ".class);\n" +
                    "  Criteria _criteria_ = new Criteria(\"" + sqlJoin.toString() + "\");\n";

            for (int i = 0; i < relationDesc.getForeignFields().length; i++) {
                DBColumn c = localEntity.getColumnByFieldName(relationDesc.getForeignFields()[i], true);
                r += ("  _criteria_." + JavaUtils.getCriteriaSetterName(c) + "(" + (i + 1) + ", this." + c.getBeanFieldVar() + ");\n");
            }

            r += "  Collection all=home.findAll(_criteria_,null);\n" +
                    "  for(Iterator i=all.iterator();i.hasNext();){\n" +
                    "    " + relationDesc.getDetailTable().getDAOInfo().getFullEntityRemoteName() + " r=(" + relationDesc.getDetailTable().getDAOInfo().getFullEntityRemoteName() + ")i.next();\n" +
                    "    r.remove();\n" +
                    "  }\n" +
                    "  if(value!=null){\n" +
                    "    for (Iterator i = value.iterator(); i.hasNext();) {\n" +
                    "      " + relationDesc.getDetailTable().getDAOInfo().getFullDTOName() + " line = (" + relationDesc.getDetailTable().getDAOInfo().getFullDTOName() + ") i.next();\n"
                    ;
            for (int j = 0; j < relationDesc.getForeignFields().length; j++) {
                r +=
                        "      line." + JavaUtils.businessSetterName(relationDesc.getDetailTable().getDAOInfo().getColumnByFieldName(relationDesc.getDetailPrimaryFields()[j], true)) + "(" +
                        "this." + relationDesc.getDetailTable().getDAOInfo().getColumnByFieldName(relationDesc.getForeignFields()[j], true).getBeanFieldVar()
                        + ");\n"
                        ;
            }

            r += "      home.create(line);\n" +
                    "    }\n" +
                    "  }\n" +
                    "}catch (CreateException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "}catch (RemoveException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "}catch (FinderException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "}catch (NamingException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "} catch (RemoteException e) {\n" +
                    "   throw new EJBException(e);\n" +
                    "}\n";
            return r;
        } else if (relationDesc.getDetailTable().getDAOInfo().doGenerateDAO()) {
            String r = "   java.sql.Connection  _conn_=_openConnection_();\n" +
                    "try {\n" +
                    "  _conn_=_openConnection_();\n" +
                    "  " + relationDesc.getDetailTable().getDAOInfo().getFullDAOName() + " _dbcon_ = new " + relationDesc.getDetailTable().getDAOInfo().getFullDAOName() + "();\n" +
                    "  _dbcon_.setConnection(_conn_);\n" +
                    "  Criteria _criteria_ = new Criteria(\"" + sqlJoin.toString() + "\");\n";

            for (int i = 0; i < relationDesc.getForeignFields().length; i++) {
                DBColumn c = localEntity.getColumnByFieldName(relationDesc.getForeignFields()[i], true);
                r += ("  _criteria_." + JavaUtils.getCriteriaSetterName(c) + "(" + (i + 1) + ", this." + c.getBeanFieldVar() + ");\n");
            }

            r += "}catch (SQLException sqlExcp) {\n" +
                    getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp") +
                    "   throw new EJBException(sqlExcp.getMessage());\n" +
                    "} finally {\n" +
                    "   _closeConnection_(_conn_);\n" +
                    "}\n";
            return r;
        } else {
            throw new IllegalArgumentException("Neither Bean or DataAccessObject was resolved for " + relationDesc.getDetailTable().getDAOInfo().getBeanName());
        }
    }

    private JavaMethod createMethodEjbHomeGetCount(DAOInfo entityInfo) {
        StringBuilder body = new StringBuilder();
        body
                .append("Connection  _conn_=null;\n")
                .append("try{\n")
                .append("  _conn_=_openConnection_();\n");

        body.append("  StringBuffer _selectStatement_ = new StringBuffer(\"");
        body
                .append("SELECT Count(1) FROM ");
        DBTableInfo[] dbTables = entityInfo.getTables();
        for (int t = 0; t < dbTables.length; t++) {
            if (t > 0) {
                body.append(",");
            }
            body.append(dbTables[t].getTableName2());
        }
        body.append("\");\n");
        body.append("  if(criteria!=null && criteria.getJoins()!=null){\n");
        body.append("    _selectStatement_.append(\",\");\n");
        body.append("    _selectStatement_.append(criteria.getJoins());\n");
        body.append("  }\n");
        DBColumn[] pk = entityInfo.getPrimaryKeys();
        if (dbTables.length > 1) {
            body.append("  _selectStatement_.append(\"");
            body.append(" WHERE ");
            boolean first = true;
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
        }
        body.append("  if(criteria!=null && criteria.getWhereClause()!=null){\n");
        body.append("    _selectStatement_.append(\" WHERE \");\n");
        body.append("    _selectStatement_.append(criteria.getWhereClause());\n");
        body.append("  }\n");

        body.append(getLogCode(JavaLogManager.Level.DEBUG, null, "\"<FIND> : \"" +
                "\n\t+\"QUERY = \"+_selectStatement_" +
                "\n\t+\"\\tCRITERIA = \"+criteria" +
                "\n\t",null));

        body.append("  PreparedStatement _prepStmt_ = _conn_.prepareStatement(_selectStatement_.toString());\n");
        body.append("  if(criteria!=null){\n");
        body.append("    criteria.populateStatement(_prepStmt_,_statementParamPos_);\n");
        body.append("  }\n");
        body
                .append("  ResultSet _rs_=_prepStmt_.executeQuery();\n")
                .append("  int _count_=0;\n")
                .append("  if(_rs_.next()){\n")
                .append("    _count_=_rs_.getInt(1);\n")
                .append("  }\n")
                .append("  _rs_.close();\n")
                .append("  _prepStmt_.close();\n")
                .append("  return _count_;\n")
                .append("}catch(SQLException sqlExcp){\n")
                .append(getLogCode(JavaLogManager.Level.ERROR, null, null, "sqlExcp"))
                .append("  throw new EJBException(sqlExcp.getMessage());\n")
                .append("}finally{\n")
                .append("  _closeConnection_(_conn_);\n")
                .append("}");
        JavaMethod m = new JavaMethod("ejbHomeGetCount", "int", new JavaParam[]{
            new JavaParam("criteria", "Criteria", null)
        }, "public",
                new String[]{"FieldException", "EJBException"}, null,
                body);
        return m;
    }

    private JavaMethod createMethodEjbHomeUpdate(DAOInfo entityInfo) {
        JavaMethod m = new JavaMethod("ejbHomeUpdate", "void", new JavaParam[]{
            new JavaParam("oldData", entityInfo.getDTOName(), null),
            new JavaParam("newData", entityInfo.getDTOName(), null)
        }, "public",
                new String[]{"EJBException"}, null,
                "try{\n" +
                "  " + entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
                "  home.findByPrimaryKey(data.get" + entityInfo.getDataKeyName() + "()).update(oldData,newData);\n" +
                "} catch (RemoteException e) {\n" +
                "  throw new EJBException(e);\n" +
                "} catch (FinderException e) {\n" +
                "  throw new EJBException(e);\n" +
                "}");
        return m;
    }

    private String getLogCode(JavaLogManager.Level type, String prepareExpression, String logExpression, String throwableExpression) {
        return getLogJavaCode(entityInfo, type, J2eeTarget.MODULE_DAO, prepareExpression, "entityContext.getCallerPrincipal()==null?\"Unknown\":entityContext.getCallerPrincipal().getName()", logExpression, throwableExpression);
    }

//    private static String getLogFatalCode(EntityInfo entityInfo, String type, String prepareExpression, String logExpression) {
//        StringBuffer log = new StringBuffer();
//        if (entityInfo.generateStandardOutputLog(type)) {
//            log.append("  //START Log \n");
//            if (prepareExpression != null) {
//                log.append("  " + JBGenUtils.indent(prepareExpression));
//            }
//            log.append("  System.out.println(new java.util.Date()+\" : <ERROR><\"+entityContext.getCallerPrincipal().getName()+\">\"+" + logExpression + ");\n");
//            log.append("  //END Log \n");
//        } else if (entityInfo.generateLog4JLog(type)) {
//            log.append("  //START Log \n");
//            if (prepareExpression != null) {
//                log.append("  " + JBGenUtils.indent(prepareExpression));
//            }
//            log.append("    _log_.fatal(\"<\"+entityContext.getCallerPrincipal().getName()+\">\"+" + logExpression + ");\n");
//            log.append("  //END Log \n");
//        }
//        return log.toString();
//    }

    private JavaMethod createCollectionFinder(String name, JavaParam[] params, DAOInfo entityInfo) {
        ArrayList vparams = new ArrayList();
        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        vparams.add(new JavaParam("criteria", "Criteria", null));
        if (params != null) {
            vparams.addAll(Arrays.asList(params));
        }
        vparams.add(new JavaParam("order", "OrderList", null));

        StringBuilder p = new StringBuilder("criteria,order");


        JavaMethod m = new JavaMethod("ejbHomeGet" + JavaUtils.toJavaIdentifier(name, true), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
                new String[]{
                    "FieldException", "FinderException"
                }, null,

                entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
                "try{\n" +
                "  Collection src=home.find" + JavaUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
                "  return _transform_(src,propertyList" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ");\n" +
                "} catch (RemoteException e) {\n" +
                "    throw new FinderException(e.getMessage());\n" +
                "}");
        return m;
    }

    private JavaMethod createCollectionFinderQBE(String name, JavaParam[] params, DAOInfo entityInfo) {

        ArrayList nonDuplicateParams = new ArrayList();
        JavaParam[] dp = null;
        dp = params == null ? new JavaParam[0] : params;
        for (int i = 0; i < dp.length; i++) {
            nonDuplicateParams.add(dp[i]);
        }
        dp = entityInfo.getMethodFindExtraParams();
        for (int i = dp.length - 1; i >= 0; i--) {
            if (!nonDuplicateParams.contains(dp[i])) {
                nonDuplicateParams.add(0, dp[i]);
            }
        }

        Vector vparams = new Vector();
        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        vparams.add(new JavaParam("prototype", entityInfo.getDTOName(), null));
        vparams.addAll(nonDuplicateParams);
        if (!entityInfo.removeOrderListForGetAllQBE()) {
            vparams.add(new JavaParam("order", "OrderList", null));
        }

        StringBuilder p = new StringBuilder("prototype");

        p.append(entityInfo.getMethodFindAllExtraParamNamesString(true));

        JavaMethod m = new JavaMethod("ejbHomeGet" + JavaUtils.toJavaIdentifier(name, true), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
                new String[]{"FieldException", "FinderException"}, null,

                entityInfo.getEntityHomeName() + " home=(" + entityInfo.getEntityHomeName() + ")entityContext.getEJBHome();\n" +
                "try{\n" +
                "  Collection src=home.find" + JavaUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
                "  return _transform_(src,propertyList" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ");\n" +
                "} catch (RemoteException e) {\n" +
                "    throw new FinderException(e.getMessage());\n" +
                "}");
        return m;
    }

    public String toString() {
        return "Entity Bean BMP Generator";
    }

}

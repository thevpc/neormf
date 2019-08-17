package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.AbstractInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.JBGenBOGenerator;
import org.vpc.neormf.jbgen.java.generators.log.JavaLogManager;
import org.vpc.neormf.jbgen.java.model.javaclass.*;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import static org.vpc.neormf.jbgen.java.util.JavaUtils.getLogJavaCode;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
class EjbSessionBeanGenerator extends JBGenBOGenerator {

    public static enum Type {

        Statefull, Stateless
    }
    private Type type;

    public EjbSessionBeanGenerator(JBGenMain jbgen, Type type) {
        super(jbgen);
        this.type = type;
    }

    public boolean accept(Connection connection, BOInfo sessionInfo) {
        if (!sessionInfo.doGenerateSession()) {
            return false;
        }
        return true;
    }

    public void performExtraChecks(BOInfo sessionInfo) throws NoSuchElementException {
    //sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB + ".session-remote");
    //sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB + ".session-local");
    }
    BOInfo sessinoInfo;
    public void generate(Connection connection, BOInfo sessionInfo) throws SQLException, IOException {
        this.sessinoInfo=sessinoInfo;
        JBGenProjectInfo moduleCodeStyle = sessionInfo.getProjectInfo();

        boolean ejb3 = sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0;
        boolean ejb2 = sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") < 0;
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(sessionInfo.getComments());

        theClass.setName(sessionInfo.getSessionBeanName());
        if (ejb2) {
            theClass.setModifiers("public ");
            theClass.setInterfaces(new String[]{"SessionBean"});
            theClass.addImport("java.rmi.*");
            theClass.addImport("java.sql.*");
            theClass.addImport("javax.naming.*");
            theClass.addImport("org.vpc.neormf.commons.ejb.*");
            theClass.addImport("org.vpc.neormf.commons.beans.*");
            theClass.addImport("org.vpc.neormf.commons.exceptions.*");
            theClass.addImport("org.vpc.neormf.commons.sql.*");

        } else {
            theClass.setModifiers("@" + type + "\npublic ");
            theClass.setInterfaces(new String[]{sessionInfo.getSessionRemoteName()});
        }

        DAOInfo[] entityInfos = sessionInfo.getDAOInfos();

        theClass.addImport("java.util.*");
        theClass.addImport("javax.ejb.*");


        theClass.setPackage(sessionInfo.getSessionPackage());


        JavaUtils.initializeClassLog(sessionInfo,J2eeTarget.MODULE_DAO,theClass);

        if (ejb2) {
            theClass.addField(new JavaField("sessionContext", "SessionContext", null, "private", null));
            theClass.addMethod(new JavaMethod("ejbCreate", "void", null, "public", new String[]{"CreateException"}, null, ""));
        }

        String[] entityReferencedHomes = sessionInfo.getEjbReferences();
        for (int j = 0; j < entityReferencedHomes.length; j++) {
            AbstractInfo entityInfo = null;
            try {
                entityInfo = moduleCodeStyle.getDAOInfo(entityReferencedHomes[j]);
                JavaUtils.generateEntityGetHomeMethod((DAOInfo) entityInfo, theClass);
            } catch (NoSuchElementException e) {
                entityInfo = moduleCodeStyle.getBOInfo(entityReferencedHomes[j]);
                JavaUtils.generateSessionRemote((BOInfo) entityInfo, theClass);
            }
        }

        for (int j = 0; j < entityInfos.length; j++) {
            if (entityInfos[j].isGeneratedClass("EntityBean") || entityInfos[j].isGeneratedClass("CMPEntityBean")) {
                new EntityHandler().generateSessionPartForEntity(entityInfos[j], theClass);
            } else if (entityInfos[j].isGeneratedClass("DataAccessObject")) {
                new DAOHandler().generateSessionPartForDAO(entityInfos[j], theClass);
            }
        }


        if (ejb2) {
            theClass.addMethod(new JavaMethod("setSessionContext", "void", new JavaParam[]{
                new JavaParam("sessionContext", "SessionContext", null)
            }, "public", new String[]{"EJBException"}, null, "this.sessionContext = sessionContext;"));

            theClass.addMethod(new JavaMethod("unsetSessionContext", "void", null, "public", null, null, "this.sessionContext = null;"));
            theClass.addMethod(new JavaMethod("ejbRemove", "void", null, "public", new String[]{"EJBException"}, null, ""));
            theClass.addMethod(new JavaMethod("ejbActivate", "void", null, "public", new String[]{"EJBException"}, null, ""));
            theClass.addMethod(new JavaMethod("ejbPassivate", "void", null, "public", new String[]{"EJBException"}, null, ""));
            theClass.addMethod(new JavaMethod("ejbLoad", "void", null, "public", new String[]{"EJBException"}, null, ""));
            theClass.addMethod(new JavaMethod("ejbStore", "void", null, "public", new String[]{"EJBException"}, null, ""));
            theClass.addMethod(JavaUtils.createMethodOpenConnection(moduleCodeStyle));
            theClass.addMethod(JavaUtils.createMethodCloseConnection(moduleCodeStyle));
        }

        sessionInfo.setGeneratedClass("SessionBean", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder, getLog())) {
                getLog().info(" generating Session Bean Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            sessionInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    private static JavaMethod declareMethod(JavaMethod m, DAOInfo entityInfo, JavaClassSource theClass) {
        entityInfo.getBOInfo().declareMethod(theClass, m, "session");
        return m;
    }

//    private JavaMethod createMethodOpenConnection2(ModuleMetaData moduleCodeStyle) {
//        StringBuffer body = new StringBuffer();
//        body
//                .append("try{\n")
//                .append("  Class.forName(\"").append(moduleCodeStyle.getConnectionDriverName()).append("\");\n")
////        #      $conn$=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@syspc138:1521:iris3","ro_ejb","ro_ejb");\n\
//
//                .append("  return java.sql.DriverManager.getConnection(\"").append(moduleCodeStyle.getConnectionURL()).append("\",\"" + moduleCodeStyle.getConnectionUserName() + "\",\"" + moduleCodeStyle.getConnectionUserPassword() + "\");\n")
//                .append("}catch(Exception e){\n")
//                .append("   throw new EJBException(\"Could not open connection : \"+e);\n")
//                .append("}");
//        return new JavaMethod("$openConnection2$", "java.sql.Connection", null, "private",
//                new String[]{"EJBException"}, null,
//                body);
//
//    }

//    private JavaMethod createCollectionFinder(String name, JavaParam[] params, EntityInfo entityInfo) {
//        Vector vparams = new Vector();
//        StringBuffer p = new StringBuffer("propertyList");
//        p.append(entityInfo.getMethodGetDataExtraParamNamesString(true));
//        p.append(",criteria,order");
//
//        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//        vparams.add(new JavaParam("criteria", "Criteria", null));
//        vparams.add(new JavaParam("order", "OrderList", null));
//
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                vparams.add(params[i]);
//                p.append(params[i].getName());
//            }
//        }
//
//
//        return new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{"FinderException"}, null,
//                "try{\n" +
//                "  return get" + entityInfo.getEntityHomeName() + "().get" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
//                "} catch (NamingException e) {\n" +
//                "    throw new EJBException(e);\n" +
//                "} catch (RemoteException e) {\n" +
//                "    throw new EJBException(e);\n" +
//                "}");
//    }

//    private JavaMethod createCollectionFinderDataAccessObject(String name, JavaParam[] params, EntityInfo entityInfo) {
//        Vector vparams = new Vector();
//        StringBuffer p = new StringBuffer("propertyList");
//        p.append(entityInfo.getMethodGetDataExtraParamNamesString(true));
//        p.append(",criteria,order");
//
//        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//        vparams.add(new JavaParam("criteria", "Criteria", null));
//        vparams.add(new JavaParam("order", "OrderList", null));
//
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                vparams.add(params[i]);
//                p.append(params[i].getName());
//            }
//        }
//
//
//        return new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{"FinderException"}, null,
//                "try{\n" +
//                "  return get" + entityInfo.getDAOName() + "().get" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
//                "} catch (java.sql.SQLException e) {\n" +
//                "    throw new EJBException(e);\n" +
//                "}");
//    }

//    private JavaMethod createCollectionFinder2ForDAO(String name, JavaParam[] params, EntityInfo entityInfo) {
//        String proptoType1 = JBGenUtils.decapitalize(entityInfo.getDataContentName() + "Prototype");
//
//        Vector vparams = new Vector();
//        StringBuffer p = new StringBuffer("propertyList,criteria");
//        p.append(entityInfo.getMethodGetDataExtraParamNamesString(true));
//        p.append(",null");
//
//        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//        vparams.add(new JavaParam(proptoType1, entityInfo.getDataContentName(), null));
////        vparams.add(new JavaParam("order", "OrderList", null));
//
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                vparams.add(params[i]);
//                p.append(params[i].getName());
//            }
//        }
//
//
//        JavaMethod m = new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{}, null,
//                addDAOEnveloppe(entityInfo,
//                        "Criteria criteria=SQLUtils.toCriteria(" + proptoType1 + ",null);\n" +
//                "return $dao$.get" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n"));
//        m.setUserProperty("SessionPublic", Boolean.TRUE);
//        return m;
//    }

//    private JavaMethod createCollectionCounter(String name, JavaParam[] params, EntityInfo entityInfo) {
//        Vector vparams = new Vector();
//        StringBuffer p = new StringBuffer("criteria");
//
//        vparams.add(new JavaParam("criteria", "Criteria", null));
//
//        if (params != null) {
//            for (int i = 0; i < params.length; i++) {
//                vparams.add(params[i]);
//                p.append(params[i].getName());
//            }
//        }
//
//
//        return new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{"FinderException"}, null,
//                "try{\n" +
//                "  return get" + entityInfo.getEntityHomeName() + "().getCount" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
//                "} catch (NamingException e) {\n" +
//                "    throw new EJBException(e);\n" +
//                "} catch (RemoteException e) {\n" +
//                "    throw new EJBException(e);\n" +
//                "}");
//    }
    public String toString() {
        return "Session Bean Generator";
    }

    private String getLogCode(BOInfo sessionInfo, JavaMethod javaMethod) {
        StringBuffer sb = new StringBuffer(sessionInfo.getSessionName() + "." + javaMethod.getName() + "(");
        //TODO , add fields ???
        sb.append("...");
        sb.append(")");
        return getLogCode(JavaLogManager.Level.INFO, null, "\"" + JBGenUtils.replaceString(sb.toString(), "\"", "\\\"") + "\"",null);
    }

    private String getLogCode(JavaLogManager.Level type, String prepareExpression, String logExpression, String throwableExpression) {
        return getLogJavaCode(sessinoInfo, type, J2eeTarget.MODULE_EJB, prepareExpression, "sessionContext.getCallerPrincipal()==null?\"Unknown\":sessionContext.getCallerPrincipal().getName()", logExpression, throwableExpression);
    }

//    private String getLogInfoCode(BOInfo sessinoInfo, String type, String prepareExpression, String logExpression) {
//        return sessinoInfo.getLogInfoJavaCode(type, prepareExpression, , logExpression);
//    }

//    private String getLogDebugCode(SessionInfo entityInfo, String type, String prepareExpression, String logExpression) {
//        return entityInfo.getLogDebugJavaCode(type, prepareExpression, "sessionContext.getCallerPrincipal()==null?\"Unknown\":entityContext.getCallerPrincipal().getName()",logExpression);
//    }
//    private String getLogErrorCode(BOInfo entityInfo, String type, String prepareExpression, String logExpression, String throwableExpression) {
//        return entityInfo.getLogErrorJavaCode(type, prepareExpression, "sessionContext.getCallerPrincipal()==null?\"Unknown\":sessionContext.getCallerPrincipal().getName()", logExpression, throwableExpression);
//    }
//
    private class DAOHandler {

        private String addDAOEnveloppe(DAOInfo entityInfo, String body) {
            boolean ejb3 = entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0;
            boolean ejb2 = entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") < 0;
            if (ejb2) {
                String b = JBGenUtils.indent(body, 2, false, true);
                if (!b.endsWith("\n")) {
                    b += "\n";
                }
                return "java.sql.Connection _conn_=null;\n" +
                        "try{\n" +
                        "  _conn_=_openConnection_();\n" +
                        "  " + entityInfo.getDAOName() + " _dao_=get" + entityInfo.getDAOName() + "();\n" +
                        "  _dao_.setConnection(_conn_);\n" +
                        "  _dao_.setCallerPrincipalName(sessionContext.getCallerPrincipal().getName());\n" +
                        b +
                        "}catch(java.sql.SQLException e){\n" +
                        getLogCode(JavaLogManager.Level.ERROR, null, null, "e") +
                        "  throw new EJBException(e);\n" +
                        "}finally{\n" +
                        "  get" + entityInfo.getDAOName() + "().setConnection(null);\n" +
                        "  _closeConnection_(_conn_);\n" +
                        "}\n";
            } else {
                String b = JBGenUtils.indent(body, 2, false, true);
                if (!b.endsWith("\n")) {
                    b += "\n";
                }
                return "" +
                        "  " + entityInfo.getDAOName() + " _dao_=get" + entityInfo.getDAOName() + "();\n" +
                        //"  _dao_.setCallerPrincipalName(sessionContext.getCallerPrincipal().getName());\n" +
                        b;
            }
        }

        private void generateSessionPartForDAO(DAOInfo entityInfo, JavaClassSource theClass) throws IOException {
            theClass.addImport(entityInfo.getDataPackage() + ".*");
            JavaUtils.generateGetDAOMethod(entityInfo, theClass);

            JavaClassSource javaClassSourceDAO = null;
            try {
                javaClassSourceDAO = entityInfo.getReloadedGeneratedClass("DataAccessObject");
            } catch (NoSuchElementException e) {
                // No DAO , ignore
                return;
            }
            for (Iterator i = javaClassSourceDAO.getMethods().iterator(); i.hasNext();) {
                JavaMethod javaMethod = (JavaMethod) i.next();
                JavaDoc.Decoration remoteDecoration = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "visibility");
                if (remoteDecoration == null) {
                    continue;
                } else if (remoteDecoration.getParamsList().getParam("client") == null && remoteDecoration.getParamsList().getParam("session") == null) {
                    continue;
                }
                javaMethod = (JavaMethod) javaMethod.clone();
                StringBuffer body = new StringBuffer();
                if (!javaMethod.getType().equals("void")) {
                    body.append("return ");
                }
                body.append("_dao_.");
                body.append(javaMethod.getName());
                body.append("(");
                JavaParam[] javaParams = javaMethod.getParams();
                for (int j = 0; j < javaParams.length; j++) {
                    if (j > 0) {
                        body.append(",");
                    }
                    JavaParam javaParam = javaParams[j];
                    body.append(javaParam.getName());
                }
                body.append(");");

                javaMethod.setBody(addDAOEnveloppe(entityInfo, body.toString()));

                //TODO renaming should be handled better than tis
                if (javaMethod.getName().equals("create")) {
                    javaMethod.setName("add");
                }
                javaMethod.removeException("SQLException");
                javaMethod.setName(javaMethod.getName() + entityInfo.getClassName());
                declareMethod(javaMethod, entityInfo, theClass);
            }
//
//            generateCollectionFinderForDAO("all", null, entityInfo,theClass);
//            generateCollectionFinderForDAO("all", null, entityInfo,theClass);
        }

//        private void generateSessionPartForDAOOld(EntityInfo entityInfo, JavaClassSource theClass) {
//            theClass.addImport(entityInfo.getDataPackage() + ".*");
//            JBGenUtils.generateGetDAOMethod(entityInfo,theClass);
//            generateCollectionFinderForDAO("all", null, entityInfo,theClass);
//            generateCollectionFinderForDAOQBE("all", null, entityInfo,theClass);
//        }

//        private JavaMethod generateCollectionFinderForDAO(String name, JavaParam[] params, EntityInfo entityInfo,JavaClassSource theClass) {
//            Vector vparams = new Vector();
//            StringBuffer p = new StringBuffer("propertyList");
//            p.append(entityInfo.getMethodGetDataExtraParamNamesString(true));
//            p.append(",criteria,order");
//
//            vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//            vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//            vparams.add(new JavaParam("criteria", "Criteria", null));
//            vparams.add(new JavaParam("order", "OrderList", null));
//
//            if (params != null) {
//                for (int i = 0; i < params.length; i++) {
//                    vparams.add(params[i]);
//                    p.append(params[i].getName());
//                }
//            }
//
//
//            JavaMethod m = new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                    new String[]{}, null,"");
//            m.setBody(
//                    addDAOEnveloppe(entityInfo,
//                            "return $ado$.get" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n")
//            );
//            declareMethod(m,entityInfo,theClass);
////        m.setUserProperty("SessionPublic", Boolean.TRUE);
//            return m;
//        }
//
//        private JavaMethod generateCollectionFinderForDAOQBE(String name, JavaParam[] params, EntityInfo entityInfo,JavaClassSource theClass ) {
//            Vector vparams = new Vector();
//            StringBuffer p = new StringBuffer("propertyList,prototype");
//            p.append(entityInfo.getMethodGetDataExtraParamNamesString(true));
//
//            vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//            vparams.add(new JavaParam("prototype", entityInfo.getDataContentName(), null));
//            vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//
//            if (params != null) {
//                for (int i = 0; i < params.length; i++) {
//                    vparams.add(params[i]);
//                    p.append(params[i].getName());
//                }
//            }
//
//
//            JavaMethod m = new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                    new String[]{}, null,"");
//            m.setBody(
//                    addDAOEnveloppe(entityInfo,
//                    "return _dao_.get" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n")
//            );
//            declareMethod(m,entityInfo,theClass);
//            return m;
//        }
    }

    private class EntityHandler {

        private void generateSessionPartForEntity(DAOInfo entityInfo, JavaClassSource theClass) throws IOException {
            JavaUtils.generateEntityGetHomeMethod(entityInfo, theClass);

            JavaClassSource javaClassSourceEntityRemote = null;
            javaClassSourceEntityRemote = entityInfo.getReloadedGeneratedClass("EntityRemote");


            JavaClassSource javaClassSourceEntityHome = null;
            javaClassSourceEntityHome = entityInfo.getReloadedGeneratedClass("EntityHome");

            for (Iterator i = javaClassSourceEntityHome.getMethods().iterator(); i.hasNext();) {
                JavaMethod javaMethod = (JavaMethod) i.next();
                JavaDoc.Decoration remoteDecoration = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "visibility");
                if (remoteDecoration == null) {
                    continue;
                } else if (remoteDecoration.getParamsList().getParam("client") == null && remoteDecoration.getParamsList().getParam("session") == null) {
                    continue;
                }
                if (javaMethod.getName().startsWith("find")) {
                // do nothing, do not export
                } else if (javaMethod.getName().equals("create")) {
                    JavaMethod javaMethodSession = (JavaMethod) javaMethod.clone();
                    javaMethodSession.removeException("RemoteException");
                    javaMethodSession.setType(entityInfo.getDataKeyName());
                    javaMethodSession.setName(JBGenUtils.getCompoundMethodName("add", entityInfo.getBeanName()));

                    StringBuffer body = new StringBuffer();


                    body.append("try {\n");
                    body.append(" return ");
                    body.append("(");
                    body.append(entityInfo.getDataKeyName());
                    body.append(")");
                    body.append(" get").append(entityInfo.getEntityHomeName()).append("().").append(javaMethod.getName()).append("(");
                    for (int j = 0; j < javaMethod.getParams().length; j++) {
                        if (j > 0) {
                            body.append(",");
                        }
                        body.append(javaMethod.getParams()[j].getName());
                    }
                    body.append(").getPrimaryKey();\n");
                    body.append("} catch (RemoteException e) {\n");
                    body.append("  throw new EJBException(e);\n");
                    body.append("} catch (NamingException e) {\n");
                    body.append("  throw new EJBException(e);\n");
                    body.append("}");
                    javaMethodSession.setBody(body.toString());
                    declareMethod(javaMethodSession, entityInfo, theClass);
                } else {
                    JavaMethod javaMethodSession = (JavaMethod) javaMethod.clone();
                    javaMethodSession.removeException("RemoteException");
                    String name = javaMethodSession.getName();
                    javaMethodSession.setName(JBGenUtils.getCompoundMethodName(name, entityInfo.getBeanName()));

                    StringBuffer body = new StringBuffer();
                    body.append("try {\n");
                    if (!"void".equals(javaMethodSession.getType())) {
                        body.append(" return ");
                    }
                    body.append(" get").append(entityInfo.getEntityHomeName()).append("().").append(javaMethod.getName()).append("(");
                    if (javaMethod.getParams() != null) {
                        for (int j = 0; j < javaMethod.getParams().length; j++) {
                            if (j > 0) {
                                body.append(",");
                            }
                            body.append(javaMethod.getParams()[j].getName());
                        }
                    }
                    body.append(");\n");
                    body.append("} catch (RemoteException e) {\n");
                    body.append("  throw new EJBException(e);\n");
                    body.append("} catch (NamingException e) {\n");
                    body.append("  throw new EJBException(e);\n");
                    body.append("}");
                    javaMethodSession.setBody(body.toString());
                    declareMethod(javaMethodSession, entityInfo, theClass);
                }
            }
            for (Iterator i = javaClassSourceEntityRemote.getMethods().iterator(); i.hasNext();) {
                JavaMethod javaMethod = (JavaMethod) i.next();

                JavaDoc.Decoration remoteDecoration = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "visibility");
                if (remoteDecoration == null) {
                    continue;
                } else if (remoteDecoration.getParamsList().getParam("client") == null && remoteDecoration.getParamsList().getParam("session") == null) {
                    continue;
                }

                JavaMethod javaMethodSession = (JavaMethod) javaMethod.clone();

                javaMethodSession.removeException("RemoteException");
                javaMethodSession.addException("ObjectNotFoundException");
                javaMethodSession.addException("FinderException");
                String name = javaMethodSession.getName();
                boolean defaultSetter = false;
                boolean defaultGetter = false;
                if ("findByKey".equals(name)) {
                    defaultGetter = true;
                    name = "get";
                } else if ("update".equals(name)) {
                    defaultSetter = true;
                    name = "set";
                }

                javaMethodSession.setName(JBGenUtils.getCompoundMethodName(name, entityInfo.getBeanName()));

                StringBuffer body = new StringBuffer();
                body.append(getLogCode(entityInfo.getBOInfo(), javaMethod));
                body.append("try {\n");
                if (defaultSetter) {
                    body.append(" ").append(entityInfo.getDataKeyName()).append(" key=").append(("newData.get" + entityInfo.getDataKeyName() + "()")).append(";\n");
                }
//            String keyExpression=defaultSetter? ("newData.get"+entityInfo.getDataKeyName()+"()") : "key";
//            if(!defaultGetter){
//                body.append(" " + entityInfo.getDataKeyName() + " key=").append(keyExpression).append(";\n");
//            }
                body.append(" if(key==null){\n");
                body.append("   throw new ObjectNotFoundException(\"Key cannot be null\");\n");
                body.append(" }\n");
                if (!"void".equals(javaMethodSession.getType())) {
                    body.append(" return ");
                }
                body.append(" get").append(entityInfo.getEntityHomeName()).append("().findByPrimaryKey(key).").append(javaMethod.getName()).append("(");
                for (int j = 0; j < javaMethod.getParams().length; j++) {
                    if (j > 0) {
                        body.append(",");
                    }
                    body.append(javaMethod.getParams()[j].getName());
                }
                body.append(");\n");
                body.append("} catch (RemoteException e) {\n");
                body.append("  throw new EJBException(e);\n");
                body.append("} catch (NamingException e) {\n");
                body.append("  throw new EJBException(e);\n");
                body.append("}");
                javaMethodSession.setBody(body.toString());
                if (!defaultSetter) {
                    javaMethodSession.addParam(new JavaParam("key", entityInfo.getDataKeyName(), null));
                }
                declareMethod(javaMethodSession, entityInfo, theClass);
            }
//        theClass.addMethod(new JavaMethod("add" + entityInfo.getClassName(), entityInfo.getDataKeyName(), new JavaParam[]{
//            new JavaParam("data", entityInfo.getDataContentName(), null)
//        }, "public",
//                new String[]{"CreateException"}, "EJB Constructor",
//                "try {\n" +
//                "  return (" + entityInfo.getDataKeyName() + ")get" + entityInfo.getEntityHomeName() + "().create(data).getPrimaryKey();\n" +
//                "} catch (RemoteException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "} catch (NamingException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "}"));

            /////////////////

//        ArrayList list = new ArrayList();
//        list.add(new JavaParam("key", entityInfo.getDataKeyName(), null));
//        list.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//        list.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//
//        theClass.addMethod(new JavaMethod("get" + entityInfo.getClassName(), entityInfo.getDataContentName(),
//                (JavaParam[]) list.toArray(new JavaParam[list.size()])
//                , "public",
//                new String[]{"FinderException"}, null,
//                "try {\n" +
//                "  return get" + entityInfo.getEntityHomeName() + "().findByPrimaryKey(key).getData(propertyList" + entityInfo.getMethodGetDataExtraParamNamesString(true) + ");\n" +
//                "} catch (RemoteException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "} catch (NamingException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "}"));
//
//
//        /////////////////
//
//        theClass.addMethod(new JavaMethod("set" + entityInfo.getClassName(), "void", new JavaParam[]{
//            new JavaParam("data", entityInfo.getDataContentName(), null)
//        }, "public",
//                new String[]{"FinderException"}, null,
//                "try {\n" +
//                "  get" + entityInfo.getEntityHomeName() + "().findByPrimaryKey(data.get" + entityInfo.getDataKeyName() + "()).setData(data);\n" +
//                "} catch (RemoteException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "} catch (NamingException e) {\n" +
//                "  throw new EJBException(e);\n" +
//                "}"));
            JavaMethod m = new JavaMethod("remove" + entityInfo.getClassName(), "void", new JavaParam[]{
                new JavaParam("key", entityInfo.getDataKeyName(), null)
            }, "public",
                    new String[]{"ObjectNotFoundException", "FinderException", "RemoveException"}, null, "");
            m.setBody(getLogCode(entityInfo.getBOInfo(), m) +
                    "try {\n" +
                    "  get" + entityInfo.getEntityHomeName() + "().findByPrimaryKey(key).remove();\n" +
                    "} catch (RemoteException e) {\n" +
                    "  throw new EJBException(e);\n" +
                    "} catch (NamingException e) {\n" +
                    "  throw new EJBException(e);\n" +
                    "}");
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
            declareMethod(m, entityInfo, theClass);

            m = new JavaMethod("removeAll" + entityInfo.getClassName(), "void", new JavaParam[]{
                new JavaParam("key", entityInfo.getDataKeyName() + "[]", null)
            }, "public",
                    new String[]{"FinderException", "RemoveException"}, null, "");

            m.setBody(getLogCode(entityInfo.getBOInfo(), m) +
                    "try {\n" +
                    "  for(int i=0;i<key.length;i++){\n" +
                    "    get" + entityInfo.getEntityHomeName() + "().findByPrimaryKey(key[i]).remove();\n" +
                    "  }\n" +
                    "} catch (RemoteException e) {\n" +
                    "  throw new EJBException(e);\n" +
                    "} catch (NamingException e) {\n" +
                    "  throw new EJBException(e);\n" +
                    "}");
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
            declareMethod(m, entityInfo, theClass);

            m = new JavaMethod("bulkAdd" + entityInfo.getClassName(), "void",
                    new JavaParam[]{
                new JavaParam("data", entityInfo.getDTOName() + "[]", null)
            },
                    "public",
                    new String[]{"RemoveException", "CreateException"}, null, "");
            m.setBody(getLogCode(entityInfo.getBOInfo(), m) +
                    "try {\n" +
                    "  get" + entityInfo.getEntityHomeName() + "().removeAll();\n" +
                    "  for(int i=0;i<data.length;i++){\n" +
                    "    get" + entityInfo.getEntityHomeName() + "().create(data[i]);\n" +
                    "  }\n" +
                    "} catch (RemoteException e) {\n" +
                    "  throw new EJBException(e);\n" +
                    "} catch (NamingException e) {\n" +
                    "  throw new EJBException(e);\n" +
                    "}");
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@class:generator JBGen"));
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:visibility " + entityInfo.getMethodVisibility(theClass, m, "entity")));
            declareMethod(m, entityInfo, theClass);

//        theClass.addMethod(createCollectionFinder("all", null, entityInfo));
//        theClass.addMethod(createCollectionFinder2("all", null, entityInfo));
        }
//        private JavaMethod createCollectionFinder2(String name, JavaParam[] params, EntityInfo entityInfo) {
//            Vector vparams = new Vector();
//            String proptoType1 = JBGenUtils.decapitalize(entityInfo.getDataContentName() + "Prototype");
//            StringBuffer p = new StringBuffer("propertyList,criteria")
//                    .append(entityInfo.getMethodGetDataExtraParamNamesString(true))
//                    .append(",null");
//            vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
//            vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//            vparams.add(new JavaParam(proptoType1, entityInfo.getDataContentName(), null));
////        vparams.add(new JavaParam("order", "OrderList", null));
//
//            if (params != null) {
//                for (int i = 0; i < params.length; i++) {
//                    vparams.add(params[i]);
//                    p.append(",");
//                    p.append(params[i].getName());
//                }
//            }
//
//            JavaMethod m = new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                    new String[]{"FinderException"}, null,
//                    "try{\n" +
//                    "  Criteria criteria=SQLUtils.toCriteria(" + proptoType1 + ",null);\n" +
//                    "  return get" + entityInfo.getEntityHomeName() + "().get" + JBGenUtils.toJavaIdentifier(name, true) + "(" + p + ");\n" +
//                    "} catch (NamingException e) {\n" +
//                    "    throw new EJBException(e);\n" +
//                    "} catch (RemoteException e) {\n" +
//                    "    throw new EJBException(e);\n" +
//                    "}");
//            JBGenUtils.decorateMethod(m,new JavaDoc.Decoration("@ejb:visibility client"));
////        m.setUserProperty("SessionPublic", Boolean.TRUE);
//            return m;
//        }
    }
}

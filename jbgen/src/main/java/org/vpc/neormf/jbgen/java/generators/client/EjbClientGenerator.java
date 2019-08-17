package org.vpc.neormf.jbgen.java.generators.client;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.generators.JBGenBOGenerator;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
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
public class EjbClientGenerator extends JBGenBOGenerator {

    public EjbClientGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, BOInfo sessionInfo) {
        return sessionInfo.doGenerateBusinessDelegate();
    }

    public void performExtraChecks(BOInfo sessionInfo) throws NoSuchElementException {
        sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB_BUSINESS_DELEGATE);
    }

    public void generate(Connection connection, BOInfo sessionInfo) throws SQLException, IOException {
        JBGenProjectInfo moduleCodeStyle = sessionInfo.getProjectInfo();
        if (getAlreadyGenerated(moduleCodeStyle).contains(sessionInfo)) {
            return;
        }
        getAlreadyGenerated(moduleCodeStyle).add(sessionInfo);
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB_BUSINESS_DELEGATE));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(sessionInfo.getComments());
        theClass.setModifiers("public ");
        theClass.setName(sessionInfo.getEjbClientName());
        theClass.setSuperClass("EjbSessionBusinessDelegate");
//        theClass.interfaces = new String[]{"SessionBean"};

        DAOInfo[] entityInfos = sessionInfo.getDAOInfos();
        boolean ejb3 = sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0;
        boolean ejb2 = sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") < 0;
        if (ejb2) {
            theClass.addImport("java.sql.*");
            theClass.addImport("org.vpc.neormf.commons.*");
            theClass.addImport("org.vpc.neormf.commons.beans.*");
            theClass.addImport("org.vpc.neormf.commons.exceptions.*");
        }
        theClass.addImport("java.util.*");
        theClass.addImport("javax.naming.*");
        theClass.addImport("javax.ejb.*");
        theClass.addImport("java.rmi.*");
        theClass.addImport("org.vpc.neormf.commons.CommunicationException");
        theClass.addImport("org.vpc.neormf.commons.ejb.*");


        theClass.addImport(sessionInfo.getSessionPackage() + ".*");
        for (int j = 0; j < entityInfos.length; j++) {
            theClass.addImport(entityInfos[j].getDataPackage() + ".*");
//            theClass.addImport(entityInfos[j].getEntityCodeStyle().getEntityPackage()+".*");
        }

        theClass.setPackage(sessionInfo.getEjbClientPackage());

        theClass.addField(new JavaField(sessionInfo.getSessionRemoteNameVarName(), sessionInfo.getSessionRemoteName(), null, "private", null));

        theClass.addMethod(new JavaMethod(theClass.getName(), "", null, "public",
                null, null,
                "setApplicationServerName(\"" + moduleCodeStyle.getTargetApplicationServer() + "\");\n" +
                "setModuleName(\"" + moduleCodeStyle.getModuleName() + "\");\n"));

        theClass.addMethod(new JavaMethod("get" + sessionInfo.getSessionRemoteName(), sessionInfo.getSessionRemoteName(), null, "private",
                new String[]{
                    "NamingException",
                    "RemoteException",
                    "CreateException"
                }, null,
                "if(" + sessionInfo.getSessionRemoteNameVarName() + "==null){\n" +
                "    SimpleEJBClient ejbClient=getDelegate();\n" +
                ((ejb2) ? ("    " + sessionInfo.getSessionRemoteNameVarName() + "=((" + sessionInfo.getSessionHomeName() + ") ejbClient.getHome(" + sessionInfo.getSessionHomeName() + ".class)).create();\n") : ("    " + sessionInfo.getSessionRemoteNameVarName() + "=((" + sessionInfo.getSessionRemoteName() + ") ejbClient.getInitialContext().lookup(\"" + sessionInfo.getSessionBeanName() + "\"));\n")) +
                "}\n" +
                "return " + sessionInfo.getSessionRemoteNameVarName() + ";"));

        JavaClassSource javaClassSourceSessionRemote = null;
        try {
            javaClassSourceSessionRemote = sessionInfo.getReloadedGeneratedClass("SessionRemote");
        } catch (NoSuchElementException e) {
            try {
                javaClassSourceSessionRemote = sessionInfo.getReloadedGeneratedClass("SessionRemote");
            } catch (NoSuchElementException e1) {
            return;
        }
        }

//        for (Iterator i = javaClassSourceSessionRemote.getMethods().iterator(); i.hasNext();) {
//            JavaMethod javaMethodOld = (JavaMethod) i.next();
//            JavaMethod javaMethod = (JavaMethod) javaMethodOld.clone();
//            StringBuffer body = new StringBuffer("try {\n");
//
//            if (!"void".equals(javaMethod.getType())) {
//                body.append(" return ");
//            }
//
//            body.append(" get" + sessionInfo.getSessionRemoteName() + "().")
//                    .append(javaMethod.getName())
//                    .append("(");
//            if (javaMethod.getParams()!=null) {
//                for (int j = 0; j < javaMethod.getParams().length; j++) {
//                    if (j > 0) {
//                        body.append(",");
//                    }
//                    body.append(javaMethod.getParams()[j].getName());
//                }
//            }
//
//            body.append(");\n");
//            javaMethod.removeAllExceptions();
//            javaMethod.addException("CommunicationException");
//
//            if(javaMethodOld.getExceptions().contains("CreateException")){
//                javaMethod.addException("CreateDataException");
//                body.append("} catch (javax.ejb.CreateException e) {\n");
//                body.append("  throw new CreateDataException(e.getMessage());\n");
//            }
//            if(javaMethodOld.getExceptions().contains("RemoveException")){
//                javaMethod.addException("RemoveDataException");
//                body.append("} catch (javax.ejb.RemoveException e) {\n");
//                body.append("  throw new RemoveDataException(e.getMessage());\n");
//            }
//            if(javaMethodOld.getExceptions().contains("ObjectNotFoundException")){
//                javaMethod.addException("DataNotFoundException");
//                body.append("} catch (javax.ejb.ObjectNotFoundException e) {\n");
//                body.append("  throw new DataNotFoundException(e.getMessage());\n");
//            }
//            body.append("} catch (Throwable e) {\n");
//            body.append("  CommunicationException ce=createCommunicationException(e);\n");
//            body.append("  if(ce.getCause() instanceof RuntimeException){\n");
//            body.append("    throw (RuntimeException)(ce.getCause());\n");
//            body.append("  }\n");
//            body.append("  throw ce;\n");
//            body.append("}");
//            javaMethod.setBody(body.toString());
//            theClass.addMethod(javaMethod);
//        }



        for (Iterator i = javaClassSourceSessionRemote.getMethods().iterator(); i.hasNext();) {
            JavaMethod javaMethodOld = (JavaMethod) i.next();

            JavaDoc.Decoration remoteDecoration = new JavaDoc(javaMethodOld.getComments()).getDecoration("ejb", "visibility");
            if (remoteDecoration == null) {
                continue;
            } else if (remoteDecoration.getParamsList().getParam("client") == null) {
                continue;
            }
            JavaMethod javaMethod = (JavaMethod) javaMethodOld.clone();
            StringBuilder body = new StringBuilder("try {\n");

            if (!"void".equals(javaMethod.getType())) {
                body.append(" return ");
            }

            body.append(" get").append(sessionInfo.getSessionRemoteName()).append("().").append(javaMethod.getName()).append("(");
            if (javaMethod.getParams() != null) {
                for (int j = 0; j < javaMethod.getParams().length; j++) {
                    if (j > 0) {
                        body.append(",");
                    }
                    body.append(javaMethod.getParams()[j].getName());
                }
            }

            body.append(");\n");
            javaMethod.removeAllExceptions();
            javaMethod.addException("CommunicationException");
            Collection oldExceptions = javaMethodOld.getExceptions();
            for (Iterator iterator = oldExceptions.iterator(); iterator.hasNext();) {
                String exceptionClass = (String) iterator.next();
                if ("CreateException".equals(exceptionClass)) {
                    javaMethod.addException("CreateDataException");
                    body.append("} catch (javax.ejb.CreateException e) {\n");
                    body.append("  throw new CreateDataException(e.getMessage());\n");
                } else if ("RemoveException".equals(exceptionClass)) {
                    javaMethod.addException("RemoveDataException");
                    body.append("} catch (javax.ejb.RemoveException e) {\n");
                    body.append("  throw new RemoveDataException(e.getMessage());\n");
                } else if ("ObjectNotFoundException".equals(exceptionClass)) {
                    javaMethod.addException("DataNotFoundException");
                    body.append("} catch (javax.ejb.ObjectNotFoundException e) {\n");
                    body.append("  throw new DataNotFoundException(e.getMessage());\n");
                } else if ("RemoteException".equals(exceptionClass)) {
                    // do nothing, will be handled later
                } else if ("FinderException".equals(exceptionClass)) {
                    // do nothing, will be handled later
                } else {
                    String fullClassName = JavaUtils.getFullyQualifiedTypeName(exceptionClass,
                            javaClassSourceSessionRemote.getImports(),
                            sessionInfo.getProjectInfo().getSourcePaths());
                    javaMethod.addException(fullClassName);
                    body.append("} catch (").append(fullClassName).append(" e) {\n");
                    body.append("  throw e;\n");
                }
            }
            body.append("} catch (RuntimeException e) {\n");
            body.append("  throw e;\n");
            body.append("} catch (Throwable e) {\n");
            body.append("  CommunicationException ce=createCommunicationException(e);\n");
            body.append("  if(ce.getCause() instanceof RuntimeException){\n");
            body.append("    throw (RuntimeException)(ce.getCause());\n");
            body.append("  }\n");
            body.append("  throw ce;\n");
            body.append("}");
            javaMethod.setBody(body.toString());
            theClass.addMethod(javaMethod);
        }

//        for (int j = 0; j < entityInfos.length; j++) {
//            theClass.addMethod(new JavaMethod("add" + entityInfos[j].getClassName(), entityInfos[j].getDataKeyName(), new JavaParam[]{
//                new JavaParam("data", entityInfos[j].getDataContentName(), null)
//            }, "public",
//                    new String[]{"CommunicationException"}, null,
//                    "try {\n" +
//                    "  return get" + sessionInfo.getSessionRemoteName() + "()." + "add" + entityInfos[j].getClassName() + "(data);\n" +
//                    "} catch (Throwable e) {\n" +
//                    "  throw new CommunicationException(e);\n" +
//                    "}"));
//
//            ArrayList params = new ArrayList();
//            params.add(new JavaParam("key", entityInfos[j].getDataKeyName(), null));
//            params.add(new JavaParam("propertyList", entityInfos[j].getPropertyListName(), null));
//            params.addAll(Arrays.asList(entityInfos[j].getMethodGetDataExtraParams()));
//
//            theClass.addMethod(new JavaMethod("get" + entityInfos[j].getClassName(), entityInfos[j].getDataContentName(),
//                    (JavaParam[]) params.toArray(new JavaParam[params.size()])
//                    , "public",
//                    new String[]{"CommunicationException"}, null,
//                    "try {\n" +
//                    "  return get" + sessionInfo.getSessionRemoteName() + "()." + "get" + entityInfos[j].getClassName() + "(key,propertyList" + entityInfos[j].getMethodGetDataExtraParamNamesString(true) + ");\n" +
//                    "} catch (Throwable e) {\n" +
//                    "  throw new CommunicationException(e);\n" +
//                    "}"));
//
//            theClass.addMethod(new JavaMethod("set" + entityInfos[j].getClassName(), "void", new JavaParam[]{
//                new JavaParam("data", entityInfos[j].getDataContentName(), null)
//            }, "public",
//                    new String[]{"CommunicationException"}, null,
//                    "try {\n" +
//                    "  get" + sessionInfo.getSessionRemoteName() + "()." + "set" + entityInfos[j].getClassName() + "(data);\n" +
//                    "} catch (Throwable e) {\n" +
//                    "  throw new CommunicationException(e);\n" +
//                    "}"));
//
//            theClass.addMethod(new JavaMethod("delete" + entityInfos[j].getClassName(), "void", new JavaParam[]{
//                new JavaParam("key", entityInfos[j].getDataKeyName(), null)
//            }, "public",
//                    new String[]{"CommunicationException"}, null,
//                    "try {\n" +
//                    "  get" + sessionInfo.getSessionRemoteName() + "()." + "delete" + entityInfos[j].getClassName() + "(key);\n" +
//                    "} catch (Throwable e) {\n" +
//                    "  throw new CommunicationException(e);\n" +
//                    "}"));
//
//            theClass.addMethod(new JavaMethod("deleteAll" + entityInfos[j].getClassName(), "void", new JavaParam[]{
//                new JavaParam("keys", entityInfos[j].getDataKeyName() + "[]", null)
//            }, "public",
//                    new String[]{"CommunicationException"}, null,
//                    "try {\n" +
//                    "  get" + sessionInfo.getSessionRemoteName() + "()." + "deleteAll" + entityInfos[j].getClassName() + "(keys);\n" +
//                    "} catch (Throwable e) {\n" +
//                    "  throw new CommunicationException(e);\n" +
//                    "}"));
//
//            theClass.addMethod(createCollectionFinder("all", null, entityInfos[j], sessionInfo));
//            theClass.addMethod(createCollectionFinder2("all", null, entityInfos[j], sessionInfo));
//        }


//        entityInfo.setGeneratedClass("Connector", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        sessionInfo.setGeneratedClass("BusinessDelegate", theClass);
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating EJB Client Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            sessionInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

//    private JavaMethod createCollectionFinder(String name, JavaParam[] params, EntityInfo dbTableGroup, SessionInfo sessionInfo) {
//        Vector vparams = new Vector();
//        StringBuffer p = new StringBuffer("propertyList").append(dbTableGroup.getMethodGetDataExtraParamNamesString(true)).append(",criteria,order");
//        vparams.add(new JavaParam("propertyList", dbTableGroup.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(dbTableGroup.getMethodGetDataExtraParams()));
//
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
//        return new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + dbTableGroup.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{"CommunicationException"}, null,
//                "try{\n" +
//                "  return get" + sessionInfo.getSessionRemoteName() + "()." + "get" + JBGenUtils.toJavaIdentifier(name, true) + dbTableGroup.getClassName() + "(" + p + ");\n" +
//                "} catch (Throwable e) {\n" +
//                "  CommunicationException ce=createCommunicationException(e);\n" +
//                "  if(ce.getCause() instanceof RuntimeException){\n" +
//                "    throw (RuntimeException)(ce.getCause());\n" +
//                "  }\n" +
//                "  throw ce;\n" +
//                "}");
//    }

//    private JavaMethod createCollectionFinder2(String name, JavaParam[] params, EntityInfo dbTableGroup, SessionInfo sessionInfo) {
//        String proptoType1 = JBGenUtils.decapitalize(dbTableGroup.getDataContentName() + "Prototype");
//        Vector vparams = new Vector();
//        StringBuffer p = new StringBuffer("propertyList").append(dbTableGroup.getMethodGetDataExtraParamNamesString(true)).append(",").append(proptoType1).append(",order");
//        vparams.add(new JavaParam("propertyList", dbTableGroup.getPropertyListName(), null));
//        vparams.addAll(Arrays.asList(dbTableGroup.getMethodGetDataExtraParams()));
//        vparams.add(new JavaParam(proptoType1, dbTableGroup.getDataContentName(), null));
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
//        return new JavaMethod("get" + JBGenUtils.toJavaIdentifier(name, true) + dbTableGroup.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
//                new String[]{"CommunicationException"}, null,
//                "try{\n" +
//                "  return get" + sessionInfo.getSessionRemoteName() + "()." + "get" + JBGenUtils.toJavaIdentifier(name, true) + dbTableGroup.getClassName() + "(" + p + ");\n" +
//                "} catch (Throwable e) {\n" +
//                "  CommunicationException ce=createCommunicationException(e);\n" +
//                "  if(ce.getCause() instanceof RuntimeException){\n" +
//                "    throw (RuntimeException)(ce.getCause());\n" +
//                "  }\n" +
//                "  throw ce;\n" +
//                "}");
//    }
    private HashSet getAlreadyGenerated(JBGenProjectInfo moduleInfo) {
        HashSet alreadyGenerated = (HashSet) moduleInfo.getUserProperties().get(getClass().getName() + ".alreadyGenerated");
        if (alreadyGenerated == null) {
            alreadyGenerated = new HashSet();
            moduleInfo.getUserProperties().put(getClass().getName() + ".alreadyGenerated", alreadyGenerated);
        }
        return alreadyGenerated;
    }

    public String toString() {
        return "EJB Client Connector Generator";
    }
}

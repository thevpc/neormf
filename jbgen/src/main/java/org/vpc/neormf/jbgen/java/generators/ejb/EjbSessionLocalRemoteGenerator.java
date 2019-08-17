package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.generators.JBGenBOGenerator;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;

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
class EjbSessionLocalRemoteGenerator extends JBGenBOGenerator {

    public static enum Type {

        Local, Remote
    }
    private Type type;

    public EjbSessionLocalRemoteGenerator(JBGenMain jbgen, Type type) {
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
        if (Type.Local.equals(type)) {
            sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB + ".session-local");
        } else {
            sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB + ".session-remote");
        }
    }

    public void generate(Connection connection, BOInfo sessionInfo) throws SQLException, IOException {
        JBGenProjectInfo moduleCodeStyle = sessionInfo.getProjectInfo();
        if (getAlreadyGenerated(moduleCodeStyle).contains(sessionInfo)) {
            return;
        }
        getAlreadyGenerated(moduleCodeStyle).add(sessionInfo);
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB));
        JavaClassSource theClass = new JavaClassSource();
        boolean ejb3 = sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0;
        boolean ejb2 = sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") < 0;
        theClass.setClassType("interface");
        theClass.setComments(sessionInfo.getComments());
        if(ejb2){
            theClass.setModifiers("public");
        }else{
            theClass.setModifiers("@"+type+"\npublic");
        }
        theClass.setName(sessionInfo.getSessionRemoteName());
        if (ejb2) {
            theClass.setSuperClass("EJBObject");
        }

        DAOInfo[] entityInfos = sessionInfo.getDAOInfos();

        theClass.addImport("java.util.*");
        theClass.addImport("java.sql.*");
        theClass.addImport("javax.ejb.*");
        theClass.addImport("java.rmi.RemoteException");
        theClass.addImport("javax.naming.NamingException");
        theClass.addImport("org.vpc.neormf.commons.exceptions.*");
        theClass.addImport("org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate");
        theClass.addImport("org.vpc.neormf.commons.beans.*");
        for (int j = 0; j < entityInfos.length; j++) {
            theClass.addImport(entityInfos[j].getDataPackage() + ".*");
            if (entityInfos[j].doGenerateEntity()) {
                theClass.addImport(entityInfos[j].getEntityPackage() + ".*");
            }
        }
        theClass.setPackage(sessionInfo.getSessionPackage());

        JavaClassSource javaClassSourceSessionBean = null;
        try {
            javaClassSourceSessionBean = sessionInfo.getReloadedGeneratedClass("SessionBean");
        } catch (NoSuchElementException e) {
            //Session not Created, ignore
            return;
        }
        for (Iterator i = javaClassSourceSessionBean.getMethods().iterator(); i.hasNext();) {
            JavaMethod javaMethod = (JavaMethod) i.next();
            JavaDoc.Decoration remoteDecoration = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "visibility");
            if (remoteDecoration == null) {
                continue;
            } else if (remoteDecoration.getParamsList().getParam("client") == null && remoteDecoration.getParamsList().getParam("session") == null) {
                continue;
            }
            javaMethod = (JavaMethod) javaMethod.clone();
            javaMethod.setBody(null);
//            javaMethod.removeException("EJBException");
            javaMethod.removeAllExceptions();
            if (ejb2) {
                javaMethod.addException("RemoteException");
            }
            theClass.addMethod(javaMethod);
        }

//        for (int j = 0; j < entityInfos.length; j++) {
//            theClass.addMethod(new JavaMethod("add" + entityInfos[j].getClassName(), entityInfos[j].getDataKeyName(), new JavaParam[]{
//                new JavaParam("data", entityInfos[j].getDataContentName(), null)
//            }, "public",
//                    new String[]{"CreateException", "RemoteException"}, null));
//
//            ////////////////////
//            ArrayList params = new ArrayList();
//            params.add(new JavaParam("key", entityInfos[j].getDataKeyName(), null));
//            params.add(new JavaParam("propertyList", entityInfos[j].getPropertyListName(), null));
//            params.addAll(Arrays.asList(entityInfos[j].getMethodGetDataExtraParams()));
//
//            theClass.addMethod(new JavaMethod("get" + entityInfos[j].getClassName(), entityInfos[j].getDataContentName(), (JavaParam[]) params.toArray(new JavaParam[params.size()]), "public",
//                    new String[]{"RemoteException", "FinderException"}, null));
//
//            ////////////////////
//
//
//            theClass.addMethod(new JavaMethod("set" + entityInfos[j].getClassName(), "void", new JavaParam[]{
//                new JavaParam("data", entityInfos[j].getDataContentName(), null)
//            }, "public",
//                    new String[]{"RemoteException", "FinderException"}, null));
//
//            theClass.addMethod(new JavaMethod("delete" + entityInfos[j].getClassName(), "void", new JavaParam[]{
//                new JavaParam("key", entityInfos[j].getDataKeyName(), null)
//            }, "public",
//                    new String[]{"RemoteException", "FinderException", "RemoveException"}, null));
//
//            theClass.addMethod(new JavaMethod("deleteAll" + entityInfos[j].getClassName(), "void", new JavaParam[]{
//                new JavaParam("key", entityInfos[j].getDataKeyName() + "[]", null)
//            }, "public",
//                    new String[]{"RemoteException", "FinderException", "RemoveException"}, null));
//
//            theClass.addMethod(createCollectionFinder("all", null, entityInfos[j]));
//            theClass.addMethod(createCollectionFinder2("all", null, entityInfos[j]));
//        }


        ;
        sessionInfo.setGeneratedClass("SessionRemote", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder, getLog())) {
                getLog().info(" generating Session Remote Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            sessionInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    private JavaMethod createCollectionFinder(String name, JavaParam[] params, DAOInfo entityInfo) {
        Vector vparams = new Vector();
        StringBuilder p = new StringBuilder("propertyList");
        JavaParam[] extraDataParams = entityInfo.getMethodGetDataExtraParams();
        for (int i = 0; i < extraDataParams.length; i++) {
            p.append(", ").append(extraDataParams[i].getName());
        }
        p.append(",criteria,order");
        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));

        vparams.add(new JavaParam("criteria", "Criteria", null));
        vparams.add(new JavaParam("order", "OrderList", null));


        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                vparams.add(params[i]);
                p.append(params[i].getName());
            }
        }


        return new JavaMethod("get" + JavaUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
                new String[]{"RemoteException", "FinderException"}, null);
    }

    private JavaMethod createCollectionFinder2(String name, JavaParam[] params, DAOInfo entityInfo) {
        Vector vparams = new Vector();
        String proptoType1 = JBGenUtils.decapitalize(entityInfo.getDTOName() + "Prototype");
        StringBuilder p = new StringBuilder("propertyList");
        JavaParam[] extraDataParams = entityInfo.getMethodGetDataExtraParams();
        for (int i = 0; i < extraDataParams.length; i++) {
            p.append(", ").append(extraDataParams[i].getName());
        }
        p.append(",").append(proptoType1).append(",order");

        vparams.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        vparams.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
        vparams.add(new JavaParam(proptoType1, entityInfo.getDTOName(), null));
        vparams.add(new JavaParam("order", "OrderList", null));

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                vparams.add(params[i]);
                p.append(params[i].getName());
            }
        }


        return new JavaMethod("get" + JavaUtils.toJavaIdentifier(name, true) + entityInfo.getClassName(), "Collection", (JavaParam[]) vparams.toArray(new JavaParam[vparams.size()]), "public",
                new String[]{"RemoteException", "FinderException"}, null);
    }

    public String toString() {
        return "Session Remote Generator";
    }

    private HashSet getAlreadyGenerated(JBGenProjectInfo moduleInfo) {
        HashSet alreadyGenerated = (HashSet) moduleInfo.getUserProperties().get(getClass().getName() + ".alreadyGenerated");
        if (alreadyGenerated == null) {
            alreadyGenerated = new HashSet();
            moduleInfo.getUserProperties().put(getClass().getName() + ".alreadyGenerated", alreadyGenerated);
        }
        return alreadyGenerated;
    }
}

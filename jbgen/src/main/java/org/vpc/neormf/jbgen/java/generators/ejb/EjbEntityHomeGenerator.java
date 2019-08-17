package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
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
class EjbEntityHomeGenerator extends JBGenDAOGenerator {

    public EjbEntityHomeGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        if(entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0){
            return false;
        }
        if (
                !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                && !entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                && entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0")<0
        ) {
            return false;
        }
        if(entityInfo.getGeneratedClass("PrimaryKey")==null){
            return false;
        }
        return true;
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-cmp-remote");
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".entity-bmp-remote");
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        JBGenProjectInfo moduleCodeStyle = entityInfo.getProjectInfo();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setClassType("interface");
        theClass.setSuperClass("EJBHome");
        theClass.setName(entityInfo.getEntityHomeName());
        theClass.setPackage(entityInfo.getEntityPackage());
        theClass.setInterfaces(null);
        theClass.addAllImports(new String[]{
            "java.util.*",
            "org.vpc.neormf.commons.beans.*",
            "org.vpc.neormf.commons.exceptions.*",
            "javax.ejb.*",
            "java.rmi.*",
            "javax.ejb.*",
            entityInfo.getDataPackage() + ".*"
        });

        JavaClassSource javaClassSourceEntityBean = null;
        javaClassSourceEntityBean = entityInfo.getReloadedGeneratedClass("EntityBean","CMPEntityBean");
        for (Iterator i = javaClassSourceEntityBean.getMethods().iterator(); i.hasNext();) {
            JavaMethod javaMethod = (JavaMethod) i.next();

            JavaDoc.Decoration remoteDecoration = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "visibility");
            if (remoteDecoration == null) {
                continue;
            } else if (
                    remoteDecoration.getParamsList().getParam("client") == null
                    && remoteDecoration.getParamsList().getParam("session") == null
                    && remoteDecoration.getParamsList().getParam("entity") == null) {
                continue;
            }

            if (
                    !javaMethod.getName().startsWith("ejbCreate")
                    && !javaMethod.getName().startsWith("ejbFind")
                    && !javaMethod.getName().startsWith("ejbHome")
            ) {
                continue;
            }

            javaMethod.removeException("EJBException");
            if (javaMethod.getName().startsWith("ejbHome")) {
                javaMethod = (JavaMethod) javaMethod.clone();
                javaMethod.setName(JBGenUtils.decapitalize(javaMethod.getName().substring("ejbHome".length())));
                javaMethod.setBody(null);
                javaMethod.addException("RemoteException");
                theClass.addMethod(javaMethod);
            } else if (javaMethod.getName().equals("ejbFindByPrimaryKey")) {
                javaMethod = (JavaMethod) javaMethod.clone();
                javaMethod.setName(JBGenUtils.decapitalize(javaMethod.getName().substring("ejb".length())));
                javaMethod.setBody(null);
                javaMethod.setType(entityInfo.getEntityRemoteName());
                javaMethod.addException("RemoteException");
                javaMethod.addException("FinderException");
                theClass.addMethod(javaMethod);
            } else if (
                    javaMethod.getName().startsWith("ejbFind")
            ) {
                javaMethod = (JavaMethod) javaMethod.clone();
                javaMethod.setName(JBGenUtils.decapitalize(javaMethod.getName().substring("ejb".length())));
                javaMethod.setBody(null);
                javaMethod.addException("RemoteException");
                javaMethod.addException("FinderException");
                theClass.addMethod(javaMethod);
            } else if (
                    javaMethod.getName().startsWith("ejbCreate")
            ) {
                javaMethod = (JavaMethod) javaMethod.clone();
                javaMethod.setType(entityInfo.getEntityRemoteName());
                javaMethod.setName(JBGenUtils.decapitalize(javaMethod.getName().substring("ejb".length())));
                javaMethod.setBody(null);
                javaMethod.addException("RemoteException");
                javaMethod.addException("CreateException");
                theClass.addMethod(javaMethod);
            }
        }

//        theClass.addMethod(new JavaMethod("create", entityInfo.getEntityRemoteName(), new JavaParam[]{
//            new JavaParam("data", entityInfo.getDataContentName(), null)
//        }, "public", new String[]{"RemoteException", "CreateException"}, null));
//
//        ArrayList params = new ArrayList();
//        params.add(new JavaParam("config", entityInfo.getPropertyListName(), null));
//        params.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//        params.add(new JavaParam("key", entityInfo.getDataKeyName(), null));
//
//        theClass.addMethod(new JavaMethod("getByPrimaryKey", entityInfo.getDataContentName(),
//                (JavaParam[]) params.toArray(new JavaParam[params.size()]),
//                "public", new String[]{"RemoteException", "FinderException"}, null));
//
//
//        //////////////////////////////////////////
//        params = new ArrayList();
//        params.add(new JavaParam("config", entityInfo.getPropertyListName(), null));
//        params.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));
//        params.add(new JavaParam("criteria", "Criteria", null));
//        params.add(new JavaParam("order", "OrderList", null));
//
//        theClass.addMethod(new JavaMethod("find", "java.util.Collection", (JavaParam[]) params.toArray(new JavaParam[params.size()]), "public",
//                new String[]{"RemoteException", "FinderException"}, null));
//        //////////////////////////////////////////
//
//
//        theClass.addMethod(new JavaMethod("getCount", "int", new JavaParam[]{
//            new JavaParam("criteria", "Criteria", null)
//        }, "public",
//                new String[]{"RemoteException"}, null));
//        theClass.addMethod(new JavaMethod("update", "void", new JavaParam[]{
//            new JavaParam("data", entityInfo.getDataContentName(), null)
//        }, "public",
//                new String[]{"RemoteException"}, null));
//
//        theClass.addMethod(new JavaMethod("findAll", "java.util.Collection", new JavaParam[]{
//            new JavaParam("criteria", "Criteria", null),
//            new JavaParam("order", "OrderList", null)
//        }, "public",
//                new String[]{"RemoteException", "FinderException"}, null));
//        theClass.addMethod(new JavaMethod("findByPrimaryKey", entityInfo.getEntityRemoteName(), new JavaParam[]{new JavaParam("key", entityInfo.getDataKeyName(), null)}, "public", new String[]{"RemoteException", "FinderException"}, "Do not use, for EJB compatibility"));

        entityInfo.setGeneratedClass("EntityHome", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Home Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Entity Home Generator";
    }
}

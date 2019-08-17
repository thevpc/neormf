package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
class EjbEntityRemoteGenerator extends JBGenDAOGenerator {

    public EjbEntityRemoteGenerator(JBGenMain jbgen) {
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
        theClass.setSuperClass("EJBObject");
        theClass.setName(entityInfo.getEntityRemoteName());
        theClass.setPackage(entityInfo.getEntityPackage());
        theClass.setInterfaces(null);
        theClass.addAllImports(new String[]{
            "javax.ejb.*",
            "java.rmi.*",
            "org.vpc.neormf.commons.exceptions.*",
            entityInfo.getDataPackage() + ".*"
        });

        ArrayList params = new ArrayList();
        params.add(new JavaParam("propertyList", entityInfo.getPropertyListName(), null));
        params.addAll(Arrays.asList(entityInfo.getMethodGetDataExtraParams()));

        JavaClassSource javaClass;
        javaClass = entityInfo.getReloadedGeneratedClass("EntityBean","CMPEntityBean");
        for (Iterator i = javaClass.getMethods().iterator(); i.hasNext();) {
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
                    javaMethod.getName().startsWith("ejbCreate")
                    || javaMethod.getName().startsWith("ejbFind")
                    || javaMethod.getName().startsWith("ejbHome")
            ) {
                continue;
            }
            javaMethod = (JavaMethod) javaMethod.clone();
            javaMethod.setBody(null);
            javaMethod.addException("RemoteException");
            theClass.addMethod(javaMethod);
        }

        entityInfo.setGeneratedClass("EntityRemote", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Remote Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Entity Remote Generator";
    }

}

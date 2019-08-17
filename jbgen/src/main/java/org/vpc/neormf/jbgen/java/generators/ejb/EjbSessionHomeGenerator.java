package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.generators.JBGenBOGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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
class EjbSessionHomeGenerator extends JBGenBOGenerator {

    public EjbSessionHomeGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, BOInfo sessionInfo) {
        if (
                !sessionInfo.doGenerateSession()
                || 
                sessionInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0
        ) {
            return false;
        }
        return true;
    }

    public void performExtraChecks(BOInfo sessionInfo) throws NoSuchElementException {
        sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".session-remote");
        sessionInfo.checkGenerateFilter(J2eeTarget.MODULE_EJB+".session-local");
    }

    public void generate(Connection connection, BOInfo sessionInfo) throws SQLException, IOException {
        JBGenProjectInfo moduleCodeStyle = sessionInfo.getProjectInfo();
        if (getAlreadyGenerated(moduleCodeStyle).contains(sessionInfo)) {
            return;
        }
        getAlreadyGenerated(moduleCodeStyle).add(sessionInfo);
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_EJB));
        JavaClassSource theClass = new JavaClassSource();

        theClass.setClassType("interface");
        theClass.setComments(sessionInfo.getComments());
        theClass.setModifiers("public ");
        theClass.setName(sessionInfo.getSessionHomeName());
        theClass.setSuperClass("EJBHome");

        ArrayList imports = new ArrayList();
        imports.add("javax.ejb.*");
        imports.add("java.rmi.*");
        theClass.addAllImports((String[]) imports.toArray(new String[imports.size()]));

        theClass.setPackage(sessionInfo.getSessionPackage());

        theClass.addMethod(new JavaMethod("create", sessionInfo.getSessionRemoteName(), null, "public", new String[]{"CreateException", "RemoteException"}, null));

        sessionInfo.setGeneratedClass("SessionHome", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Session Home Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            sessionInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Session Home Generator";
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

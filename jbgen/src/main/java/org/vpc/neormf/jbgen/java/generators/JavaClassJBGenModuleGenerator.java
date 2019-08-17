package org.vpc.neormf.jbgen.java.generators;

import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 8 juil. 2004 Time: 11:04:26
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class JavaClassJBGenModuleGenerator extends JBGenDAOGenerator {

    protected JavaClassJBGenModuleGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    private JavaClassSource javaClassSource;
    private File destFolder;

    public JavaClassSource getJavaClassSource() {
        return javaClassSource;
    }

    public void setJavaClassSource(JavaClassSource javaClassSource) {
        this.javaClassSource = javaClassSource;
    }

    public JavaClassSource loadPreviousVersion() throws IOException {
        File classFile = getJavaClassSource().getFile(getDestFolder());
        if (classFile.exists()) {
            if (!classFile.canWrite()) {
                System.err.println("File READ ONLY .......??????????");
            }
            return new JavaClassSource(classFile);
        }
        return null;
    }

    public boolean storeNewVersion() throws IOException {
        JavaClassSource theClass = getJavaClassSource();
        try {
//            JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
                //TODO??            entityInfo.getModuleInfo().addGeneratedFile(theClass.getFile());
                return true;
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
        return false;
    }

    public File getDestFolder() {
        return destFolder;
    }

    public void setDestFolder(File destFolder) {
        this.destFolder = destFolder;
    }

}

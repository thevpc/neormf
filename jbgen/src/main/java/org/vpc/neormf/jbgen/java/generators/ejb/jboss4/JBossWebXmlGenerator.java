package org.vpc.neormf.jbgen.java.generators.ejb.jboss4;

import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 29 mars 2004 Time: 19:14:09
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JBossWebXmlGenerator extends AbsractModuleGenerator {

    public JBossWebXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        File destFolder = new File(moduleCodestyle.getTargetWebInfFolder());

        StringWriter stringWriter = new StringWriter();
        stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringWriter.write("<!DOCTYPE jboss-web PUBLIC \"-//JBoss//DTD Web Application 2.4//EN\"\n");
        stringWriter.write("    \"http://www.jboss.org/j2ee/dtd/jboss-web_4_0.dtd\">\n");
        stringWriter.write("<jboss-web>\n");
        if (moduleCodestyle.getSecurityDomain() != null) {
            stringWriter.write("  <security-domain>java:/jaas/" + moduleCodestyle.getSecurityDomain() + "</security-domain>\n");
        } else {
//            System.err.println("[JBossXmlGenerator] Missing 'module.security-domain' in 'Module.module' file");
        }
        stringWriter.write("</jboss-web>\n");
        stringWriter.close();
        try {
            if (JBGenUtils.write(new File(destFolder, "jboss-web.xml"), stringWriter.getBuffer().toString(), false,getLog())) {
                getLog().info(" generating jboss-web.xml to " + destFolder.getCanonicalPath() + "...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "jboss.xml Generator";
    }
}

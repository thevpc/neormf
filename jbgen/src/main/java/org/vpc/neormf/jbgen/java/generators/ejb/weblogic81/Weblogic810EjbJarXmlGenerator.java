package org.vpc.neormf.jbgen.java.generators.ejb.weblogic81;

import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
public class Weblogic810EjbJarXmlGenerator extends AbsractModuleGenerator {

    public Weblogic810EjbJarXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        DAOInfo[] entities = moduleCodestyle.getAllEntities();
        File destFolder = new File(moduleCodestyle.getTargetMetaInfFolder());
        File out = new File(destFolder, "weblogic-ejb-jar.xml");
        getLog().info(" generating weblogic-ejb-jar.xml  to " + destFolder.getCanonicalPath() + "...");

        out.getParentFile().mkdirs();
        PrintStream fileWriter = new PrintStream(new FileOutputStream(out));
        fileWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        fileWriter.println("<!DOCTYPE weblogic-ejb-jar PUBLIC \"-//BEA Systems, Inc.//DTD WebLogic 8.1.0 EJB//EN\" \"http://www.bea.com/servers/wls810/dtd/weblogic-ejb-jar.dtd\">");
        fileWriter.println("<weblogic-ejb-jar>");
        for (int i = 0; i < entities.length; i++) {
            DAOInfo entityInfo = entities[i];
            boolean isCMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local");
            boolean isBMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local");
            if (!isBMP && !isCMP) {
                continue;
            }
            fileWriter.println("<weblogic-enterprise-bean>");
            fileWriter.println("<ejb-name>" + entityInfo.getBeanName() + "</ejb-name>");

            fileWriter.println("<entity-descriptor>");
            fileWriter.println("<persistence>");
            fileWriter.println("<persistence-use>");
            fileWriter.println("<type-identifier>WebLogic_CMP_RDBMS</type-identifier>");
            fileWriter.println("<type-version>6.0</type-version>");
            fileWriter.println("<type-storage>META-INF/weblogic-cmp-rdbms-jar.xml</type-storage>");
            fileWriter.println("</persistence-use>");
            fileWriter.println("</persistence>");
            fileWriter.println("<enable-dynamic-queries>true</enable-dynamic-queries>");
            fileWriter.println("</entity-descriptor>");
            fileWriter.println("<enable-call-by-reference>false</enable-call-by-reference>");
            fileWriter.println("<jndi-name>" + entities[i].getBeanName() + "</jndi-name>");
            fileWriter.println("</weblogic-enterprise-bean>");
        }
        BOInfo[] sessionInfos = moduleCodestyle.getAllBOs();
        for (int i = 0; i < sessionInfos.length; i++) {
            BOInfo si = sessionInfos[i];
            boolean isGen = si.doGenerateSession();
            if (!isGen) {
                continue;
            }
            fileWriter.println("<weblogic-enterprise-bean>");
            fileWriter.println("<ejb-name>" + sessionInfos[i].getSessionName() + "</ejb-name>");
            fileWriter.println("<jndi-name>" + sessionInfos[i].getBeanName() + "</jndi-name>");
            fileWriter.println("</weblogic-enterprise-bean>");
        }
        fileWriter.println("</weblogic-ejb-jar>");
        fileWriter.close();
    }
}

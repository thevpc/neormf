/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.ejbversion.EjbJarXml11Generator;
import org.vpc.neormf.jbgen.java.generators.ejb.ejbversion.EjbJarXml20Generator;
import org.vpc.neormf.jbgen.java.generators.ejb.oracle9ias.Oracle9iASOrionXmlGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.weblogic81.Weblogic810CmpRdbms20JarXmlGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.weblogic81.Weblogic810EjbJarXmlGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.jboss4.JBossXmlGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.jboss4.JBossWebXmlGenerator;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.IOException;
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
public class J2eeDeploymentGenerator extends AbsractModuleGenerator {

    public J2eeDeploymentGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo project) throws SQLException, IOException {
        // target AS
        // EJB version
        String targetApplicationServer = project.getTargetApplicationServer();
        String targetEjbVersion = project.getTargetEjbVersion();
        if (JBGenProjectInfo.WEBLOGIC8.equals(targetApplicationServer)) {
            if ("2.0".equals(targetEjbVersion) || "2.1".equals(targetEjbVersion)) {
                new EjbJarXml20Generator(getJbgen()).generate(connection, project);
                new Weblogic810EjbJarXmlGenerator(getJbgen()).generate(connection, project);
                new Weblogic810CmpRdbms20JarXmlGenerator(getJbgen()).generate(connection, project);
            } else if ("3.0".equals(targetEjbVersion)) {
            //nothing
            } else {
                throw new IllegalArgumentException("Weblogic8 uses ejb 2.0. please change the value of target.ejb.version to 2.0");
            }
        } else if (JBGenProjectInfo.O9IAS.equals(targetApplicationServer)) {
            if ("1.1".equals(targetEjbVersion)) {
                new EjbJarXml11Generator(getJbgen()).generate(connection, project);
                new Oracle9iASOrionXmlGenerator(getJbgen()).generate(connection, project);
            } else {
                throw new IllegalArgumentException("Oracle 9iAS 9.0.2 uses ejb 1.1. please change the value of target.ejb.version to 1.1");
            }
        } else if (JBGenProjectInfo.OC4J.equals(targetApplicationServer)) {
            if ("1.1".equals(targetEjbVersion)) {
                new EjbJarXml11Generator(getJbgen()).generate(connection, project);
                new Oracle9iASOrionXmlGenerator(getJbgen()).generate(connection, project);
            } else if ("1.1".equals(targetEjbVersion)) {
                new EjbJarXml11Generator(getJbgen()).generate(connection, project);
                new Oracle9iASOrionXmlGenerator(getJbgen()).generate(connection, project);
            } else if ("3.0".equals(targetEjbVersion)) {
            //nothing
            } else {
                throw new IllegalArgumentException("Only ejb " + JBGenProjectInfo.EJB_VERSIONS + " are supported for OC4J. please change the value of target.ejb.version to 3.0");
            }
        } else if (JBGenProjectInfo.JBOSS4.equals(targetApplicationServer)) {
            if ("1.1".equals(targetEjbVersion)) {
                new EjbJarXml11Generator(getJbgen()).generate(connection, project);
                new JBossXmlGenerator(getJbgen()).generate(connection, project);
                new JBossWebXmlGenerator(getJbgen()).generate(connection, project);
            } else if ("2.0".equals(targetEjbVersion) || "2.1".equals(targetEjbVersion)) {
                new EjbJarXml20Generator(getJbgen()).generate(connection, project);
                new JBossXmlGenerator(getJbgen()).generate(connection, project);
                new JBossWebXmlGenerator(getJbgen()).generate(connection, project);
            } else if ("3.0".equals(targetEjbVersion)) {
            //nothing
            } else {
                throw new IllegalArgumentException("Only ejb " + JBGenProjectInfo.EJB_VERSIONS + " are supported for Jboss4. please change the value of target.ejb.version to 2.1");
            }
        } else if (JBGenProjectInfo.GLASSFISH2.equals(targetApplicationServer)) {
            if ("1.1".equals(targetEjbVersion)) {
                new EjbJarXml11Generator(getJbgen()).generate(connection, project);
            } else if ("2.0".equals(targetEjbVersion) || "2.1".equals(targetEjbVersion)) {
                new EjbJarXml20Generator(getJbgen()).generate(connection, project);
            } else if ("3.0".equals(targetEjbVersion)) {
            //new EjbJarXml20Generator(getJbgen()).generate(connection, moduleCodestyle);
            } else {
                throw new IllegalArgumentException("Only ejb " + JBGenProjectInfo.EJB_VERSIONS + " are supported for GlassFishV2. please change the value of target.ejb.version to 2.1");
            }
        } else {
            throw new IllegalArgumentException("Unsupported Server " + targetApplicationServer);
        }

    }

    public String toString() {
        return "J2EE Deployment Descriptors Generator";
    }
}

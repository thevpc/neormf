/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.ejb.oracle9ias;

import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
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

public class Oracle9iASOrionXmlGenerator extends AbsractModuleGenerator {

    public Oracle9iASOrionXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        DAOInfo[] entities = moduleCodestyle.getAllEntities();
        BOInfo[] sessions = moduleCodestyle.getAllBOs();
        File destFolder = new File(moduleCodestyle.getTargetMetaInfFolder());

        StringWriter stringWriter = new StringWriter();

        stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringWriter.write("<!DOCTYPE orion-ejb-jar PUBLIC \"-//Evermind//DTD Enterprise JavaBeans 1.1 runtime//EN\" \"http://xmlns.oracle.com/ias/dtds/orion-ejb-jar.dtd\">\n");
        stringWriter.write("<orion-ejb-jar>\n");
        stringWriter.write("<enterprise-beans>\n");
        for (int i = 0; i < entities.length; i++) {
            if (entities[i].doGenerateEntity()) {
                stringWriter.write("  <entity-deployment name=\"");
                stringWriter.write(entities[i].getBeanName() + "\"");
                stringWriter.write(" />\n");
            }
        }
        for (int i = 0; i < sessions.length; i++) {
            if (sessions[i].doGenerateSession()) {
//                stringWriter.write("  <entity-deployment name=\"");
                stringWriter.write("  <session-deployment name=\"");
                stringWriter.write(sessions[i].getBeanName() + "\"");
                stringWriter.write(" />\n");
            }
        }
        stringWriter.write("</enterprise-beans>\n");
        stringWriter.write("  <assembly-descriptor>\n");
        stringWriter.write("      <default-method-access>\n");
        stringWriter.write("         <security-role-mapping name=\"&lt;default-ejb-caller-role>\" impliesAll=\"true\"/>\n");
        stringWriter.write("      </default-method-access>\n");
        stringWriter.write("  </assembly-descriptor>\n");
        stringWriter.write("</orion-ejb-jar>\n");
        stringWriter.close();
//        JBGenUtils.askFileReadOnly(new File(destFolder, "orion-ejb-jar.xml"));
        try {
            if (JBGenUtils.write(new File(destFolder, "orion-ejb-jar.xml"), stringWriter.getBuffer().toString(), false,getLog())) {
                getLog().info(" generating orion-ejb-jar.xml to " + destFolder.getCanonicalPath() + "...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }

    }
}

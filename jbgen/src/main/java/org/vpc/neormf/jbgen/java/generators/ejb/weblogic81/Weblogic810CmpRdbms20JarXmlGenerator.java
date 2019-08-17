/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.ejb.weblogic81;

import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
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

public class Weblogic810CmpRdbms20JarXmlGenerator extends AbsractModuleGenerator {

    public Weblogic810CmpRdbms20JarXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        DAOInfo[] entities = moduleCodestyle.getAllEntities();
        File destFolder = new File(moduleCodestyle.getTargetMetaInfFolder());
        File out = new File(destFolder, "weblogic-cmp-rdbms-jar.xml");
        getLog().info(" generating weblogic-cmp-rdbms-jar.xml to " + destFolder.getCanonicalPath() + "...");

        out.getParentFile().mkdirs();
        PrintStream fileWriter = new PrintStream(new FileOutputStream(out));
        fileWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        fileWriter.println("<!DOCTYPE weblogic-rdbms-jar PUBLIC \"-//BEA Systems, Inc.//DTD WebLogic 8.1.0 EJB RDBMS Persistence//EN\" \"http://www.bea.com/servers/wls810/dtd/weblogic-rdbms20-persistence-810.dtd\">");
        fileWriter.println("<weblogic-rdbms-jar>");
        for (int i = 0; i < entities.length; i++) {
            fileWriter.println("<weblogic-rdbms-bean>");
            fileWriter.println("<ejb-name>" + entities[i].getBeanName() + "</ejb-name>");
            fileWriter.println("<data-source-name>" + moduleCodestyle.getDataSourceName() + "</data-source-name>");
            fileWriter.println("<table-map>");
            fileWriter.println("<table-name>" + entities[i].getEntityName() + "</table-name>");
            DBColumn[] columns = entities[i].getColumns(true, true, false);
            for (int j = 0; j < columns.length; j++) {
                DBColumn dbColumn = columns[j];
                fileWriter.println("<field-map>");
                fileWriter.println("<cmp-field>" + dbColumn.getBeanFieldName() + "</cmp-field>");
                fileWriter.println("<dbms-column>" + dbColumn.getColumnName() + "</dbms-column>");
                fileWriter.println("</field-map>");
            }
            fileWriter.println("</table-map>");

            if (
                    entities[i].doGenerateCmpLocalEntity() ||
                    entities[i].doGenerateCmpRemoteEntity()
            ) {

                fileWriter.println("<weblogic-pattern>");
                fileWriter.println("  <pattern-method>");
                fileWriter.println("    <method-name>findAll</method-name>");
                fileWriter.println("    <method-params />");
                fileWriter.println("  </pattern-method>");
//            fileWriter.println("  <max-elements>16</max-elements>");
                fileWriter.println("  <include-updates>false</include-updates>");
                fileWriter.println("</weblogic-pattern>");
            }
            fileWriter.println("</weblogic-rdbms-bean>");
        }
        fileWriter.println("</weblogic-rdbms-jar>");
        fileWriter.close();
    }
}

package org.vpc.neormf.jbgen.java.generators.ejb.jboss4;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.info.SessionDeployInfo;
import org.vpc.neormf.jbgen.*;
import org.vpc.neormf.jbgen.info.DAOInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
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
 * @creation on Date: 29 mars 2004 Time: 19:14:09
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JBossXmlGenerator extends AbsractModuleGenerator {

    public JBossXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        DAOInfo[] entities = moduleCodestyle.getAllEjbEntities();
        File destFolder = new File(moduleCodestyle.getTargetMetaInfFolder());

        StringWriter stringWriter = new StringWriter();
        stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringWriter.write("<!DOCTYPE jboss PUBLIC \"-//JBoss//DTD JBOSS 4.0//EN\" \"http://www.jboss.org/j2ee/dtd/jboss_4_0.dtd\">\n");
        stringWriter.write("<jboss>\n");

        if(moduleCodestyle.getSecurityDomain()!=null){
            stringWriter.write("  <security-domain>java:/jaas/"+moduleCodestyle.getSecurityDomain()+"</security-domain>\n");
        }else{
//            System.err.println("[JBossXmlGenerator] Missing 'module.security-domain' in 'Module.module' file");
        }

        stringWriter.write("  <enterprise-beans>\n");

        BOInfo[] sessionInfos = moduleCodestyle.getAllBOs();
        for (int i = 0; i < sessionInfos.length; i++) {
            BOInfo si = sessionInfos[i];
            boolean isGenLoc = si.doGenerateLocalSession();
            boolean isGenRem = si.doGenerateRemoteSession();
            boolean isGen = isGenRem || isGenLoc;
            if (!isGen) {
                continue;
            }
            stringWriter.write("    <session>\n");
            stringWriter.write("      <ejb-name>" + si.getSessionName() + "</ejb-name>\n");
            stringWriter.write("      <jndi-name>" + sessionInfos[i].getBeanName() + "</jndi-name>\n");
            String[] ejbRefNames = si.getEjbReferences();
            for (int j = 0; j < ejbRefNames.length; j++) {
                try {
                    BOInfo info = moduleCodestyle.getBOInfo(ejbRefNames[j]);
                    stringWriter.write("      <ejb-ref>\n");
                    stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                    stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                    stringWriter.write("      <home>" + info.getFullSessionHomeName() + "</home>\n");
                    stringWriter.write("      <remote>" + info.getFullSessionRemoteName() + "</remote>\n");
                    stringWriter.write("      </ejb-ref>\n");
                } catch (NoSuchElementException e) {
                    try {
                        DAOInfo info = moduleCodestyle.getDAOInfo(ejbRefNames[j]);
                        stringWriter.write("      <ejb-ref>\n");
                        stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                        stringWriter.write("      <ejb-ref-type>Entity</ejb-ref-type>\n");
                        stringWriter.write("      <home>" + info.getFullEntityHomeName() + "</home>\n");
                        stringWriter.write("      <remote>" + info.getFullEntityRemoteName() + "</remote>\n");
                        stringWriter.write("      </ejb-ref>\n");
                    } catch (NoSuchElementException e1) {
                        try {
                            SessionDeployInfo info = moduleCodestyle.getExtraSessionsByName(ejbRefNames[j]);
                            stringWriter.write("      <ejb-ref>\n");
                            stringWriter.write("      <ejb-ref-name>ejb/" + info.getEjbName() + "Ref</ejb-ref-name>\n");
                            stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                            stringWriter.write("      <home>" + info.getHome() + "</home>\n");
                            stringWriter.write("      <remote>" + info.getRemote() + "</remote>\n");
                            stringWriter.write("      </ejb-ref>\n");
                        } catch (NoSuchElementException e2) {
                            throw new NoSuchElementException("No Bean is named " + ejbRefNames[j]);
                        }
                    }
                }
            }
            stringWriter.write("    </session>\n");
        }
        SessionDeployInfo[] sessionDeployInfos = moduleCodestyle.getExtraSessions();
        for (int i = 0; i < sessionDeployInfos.length; i++) {
            SessionDeployInfo si = sessionDeployInfos[i];
            stringWriter.write("    <session>\n");
            stringWriter.write("      <ejb-name>" + si.getEjbName() + "</ejb-name>\n");
            stringWriter.write("      <jndi-name>" + si.getEjbName() + "</jndi-name>\n");
            stringWriter.write("    </session>\n");
        }
        for (int i = 0; i < entities.length; i++) {
            DAOInfo entityInfo = entities[i];

            stringWriter.write("    <entity>\n");
            stringWriter.write("      <display-name>" + entityInfo.getBeanName() + "</display-name>\n");
            stringWriter.write("      <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>\n");
            stringWriter.write("      <home>" + entityInfo.getFullEntityHomeName() + "</home>\n");
            stringWriter.write("      <remote>" + entityInfo.getFullEntityRemoteName() + "</remote>\n");
            stringWriter.write("      <ejb-class>" + entityInfo.getFullEntityBeanName() + "</ejb-class>\n");
            if (entityInfo.doGenerateCmpEntity()) {
                stringWriter.write("      <persistence-type>Container</persistence-type>\n");
            } else {
                stringWriter.write("      <persistence-type>Bean</persistence-type>\n");
            }
            stringWriter.write("      <prim-key-class>" + entityInfo.getFullDataKeyName() + "</prim-key-class>\n");
            //<prim-key-class>java.lang.String</prim-key-class>
            stringWriter.write("      <reentrant>False</reentrant>\n");
            if (entityInfo.doGenerateCmpEntity()) {
                stringWriter.write("      <cmp-version>2.x</cmp-version>\n");
                stringWriter.write("      <abstract-schema-name>" + entityInfo.getBeanName() + "</abstract-schema-name>\n");
                DBColumn[] columns = entityInfo.getColumns(true, true, false);
                for (int j = 0; j < columns.length; j++) {
                    stringWriter.write("      <cmp-field><field-name>" + columns[j].getBeanFieldName() + "</field-name></cmp-field>\n");
                }
                StringBuffer q = new StringBuffer();
                q.append("SELECT OBJECT(a) FROM ").append(entityInfo.getBeanName()).append(" AS a");
                stringWriter.write("      <pattern><pattern-method><method-name>findAll</method-name><method-params/></pattern-method><ejb-ql>" + q + "</ejb-ql></pattern>\n");
            }
            String[] ejbRefs = entityInfo.getEjbReferences();
            for (int j = 0; j < ejbRefs.length; j++) {
                String ejbName = ejbRefs[j];
                try {
                    BOInfo info = moduleCodestyle.getBOInfo(ejbName);
                    stringWriter.write("      <ejb-ref>\n");
                    stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                    stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                    stringWriter.write("      <home>" + info.getFullSessionHomeName() + "</home>\n");
                    stringWriter.write("      <remote>" + info.getFullSessionRemoteName() + "</remote>\n");
                    stringWriter.write("      </ejb-ref>\n");
                } catch (NoSuchElementException e) {
                    try {
                        DAOInfo info = moduleCodestyle.getDAOInfo(ejbName);
                        stringWriter.write("      <ejb-ref>\n");
                        stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                        stringWriter.write("      <ejb-ref-type>Entity</ejb-ref-type>\n");
                        stringWriter.write("      <home>" + info.getFullEntityHomeName() + "</home>\n");
                        stringWriter.write("      <remote>" + info.getFullEntityRemoteName() + "</remote>\n");
                        stringWriter.write("      </ejb-ref>\n");
                    } catch (NoSuchElementException e1) {
                        try {
                            SessionDeployInfo info = moduleCodestyle.getExtraSessionsByName(ejbName);
                            stringWriter.write("      <ejb-ref>\n");
                            stringWriter.write("      <ejb-ref-name>ejb/" + info.getEjbName() + "Ref</ejb-ref-name>\n");
                            stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                            stringWriter.write("      <home>" + info.getHome() + "</home>\n");
                            stringWriter.write("      <remote>" + info.getRemote() + "</remote>\n");
                            stringWriter.write("      </ejb-ref>\n");
                        } catch (NoSuchElementException e2) {
                            throw new NoSuchElementException("No Bean is named " + ejbName);
                        }
                    }
                }
            }
            stringWriter.write("    </entity>\n");
        }
        stringWriter.write("  </enterprise-beans>\n");
        stringWriter.write("</jboss>\n");
        stringWriter.close();
        //TODO refaire la meme chose pour tous les XML
//        JBGenUtils.askFileReadOnly(new File(destFolder, "ejb-jar.xml"));
        try {
            if (JBGenUtils.write(new File(destFolder, "jboss.xml"), stringWriter.getBuffer().toString(), false,getLog())) {
                getLog().info(" generating jboss.xml to " + destFolder.getCanonicalPath() + "...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }


    public String toString() {
        return "jboss.xml Generator";
    }
}

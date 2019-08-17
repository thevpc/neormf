package org.vpc.neormf.jbgen.java.generators.ejb.ejbversion;

import org.vpc.neormf.jbgen.info.SessionDeployInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.*;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

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
public class EjbJarXml20Generator extends AbsractModuleGenerator {

    public EjbJarXml20Generator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        DAOInfo[] entities = moduleCodestyle.getAllEntities();
        File destFolder = new File(moduleCodestyle.getTargetMetaInfFolder());
        File out = new File(destFolder, "ejb-jar.xml");
        getLog().info(" generating ejb-jar.xml to " + destFolder.getCanonicalPath() + "...");

        out.getParentFile().mkdirs();
        PrintStream fileWriter = new PrintStream(new FileOutputStream(out));
        fileWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        fileWriter.println("<!DOCTYPE ejb-jar PUBLIC \"-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN\" \"http://java.sun.com/dtd/ejb-jar_2_0.dtd\">");
        fileWriter.println("<ejb-jar>");
        fileWriter.println("  <display-name>" + moduleCodestyle.getModuleName() + "</display-name>");

        fileWriter.println("  <enterprise-beans>");

        BOInfo[] sessionInfos = moduleCodestyle.getAllBOs();
        for (int i = 0; i < sessionInfos.length; i++) {
            BOInfo si = sessionInfos[i];
            boolean isGenLoc = si.doGenerateLocalSession();
            boolean isGenRem = si.doGenerateRemoteSession();
            boolean isGen = isGenRem || isGenLoc;
            if (!isGen) {
                continue;
            }
            fileWriter.println("    <session>");
            fileWriter.println("      <description>" + si.getSessionName() + "</description>");
            fileWriter.println("      <display-name>" + si.getSessionName() + "</display-name>");
            fileWriter.println("      <ejb-name>" + si.getSessionName() + "</ejb-name>");
            fileWriter.println("      <home>" + si.getFullSessionHomeName() + "</home>");
            fileWriter.println("      <remote>" + si.getFullSessionRemoteName() + "</remote>");
            fileWriter.println("      <ejb-class>" + si.getFullSessionBeanName() + "</ejb-class>");
            fileWriter.println("      <session-type>Stateless</session-type>");
            fileWriter.println("      <transaction-type>Container</transaction-type>");
            String[] ejbRefs = si.getEjbReferences();
            for (int j = 0; j < ejbRefs.length; j++) {
                String ejbName = ejbRefs[j];
                try {
                    BOInfo info = moduleCodestyle.getBOInfo(ejbName);
                    fileWriter.println("      <ejb-ref>");
                    fileWriter.println("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>");
                    fileWriter.println("      <ejb-ref-type>Session</ejb-ref-type>");
                    fileWriter.println("      <home>" + info.getFullSessionHomeName() + "</home>");
                    fileWriter.println("      <remote>" + info.getFullSessionRemoteName() + "</remote>");
                    fileWriter.println("      </ejb-ref>");
                } catch (NoSuchElementException e) {
                    try {
                        DAOInfo info = moduleCodestyle.getDAOInfo(ejbName);
                        fileWriter.println("      <ejb-ref>");
                        fileWriter.println("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>");
                        fileWriter.println("      <ejb-ref-type>Entity</ejb-ref-type>");
                        fileWriter.println("      <home>" + info.getFullEntityHomeName() + "</home>");
                        fileWriter.println("      <remote>" + info.getFullEntityRemoteName() + "</remote>");
                        fileWriter.println("      </ejb-ref>");
                    } catch (NoSuchElementException e1) {
                        try {
                            SessionDeployInfo info = moduleCodestyle.getExtraSessionsByName(ejbName);
                            fileWriter.println("      <ejb-ref>");
                            fileWriter.println("      <ejb-ref-name>ejb/" + info.getEjbName() + "Ref</ejb-ref-name>");
                            fileWriter.println("      <ejb-ref-type>Session</ejb-ref-type>");
                            fileWriter.println("      <home>" + info.getHome() + "</home>");
                            fileWriter.println("      <remote>" + info.getRemote() + "</remote>");
                            fileWriter.println("      </ejb-ref>");
                        } catch (NoSuchElementException e2) {
                            throw new NoSuchElementException("No Bean is named " + ejbName);
                        }
                    }
                }
            }
            fileWriter.println("    </session>");
        }
        for (int i = 0; i < entities.length; i++) {
            DAOInfo entityInfo = entities[i];

            boolean isCMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local");
            boolean isBMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local");
            if (!isBMP && !isCMP) {
                continue;
            }

            fileWriter.println("    <entity>");
            fileWriter.println("      <display-name>" + entityInfo.getBeanName() + "</display-name>");
            fileWriter.println("      <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>");
            fileWriter.println("      <home>" + entityInfo.getFullEntityHomeName() + "</home>");
            fileWriter.println("      <remote>" + entityInfo.getFullEntityRemoteName() + "</remote>");
            fileWriter.println("      <ejb-class>" + entityInfo.getFullEntityBeanName() + "</ejb-class>");
            if (isCMP) {
                fileWriter.println("      <persistence-type>Container</persistence-type>");
            } else {
                fileWriter.println("      <persistence-type>Bean</persistence-type>");
            }
            fileWriter.println("      <prim-key-class>" + entityInfo.getFullDataKeyName() + "</prim-key-class>");
            //<prim-key-class>java.lang.String</prim-key-class>
            fileWriter.println("      <reentrant>False</reentrant>");
            if (isCMP) {
                fileWriter.println("      <cmp-version>2.x</cmp-version>");
                fileWriter.println("      <abstract-schema-name>" + entityInfo.getBeanName() + "</abstract-schema-name>");
                DBColumn[] columns = entityInfo.getColumns(true, true, false);
                for (int j = 0; j < columns.length; j++) {
                    fileWriter.println("      <cmp-field><field-name>" + columns[j].getBeanFieldName() + "</field-name></cmp-field>");
                }
                StringBuffer q = new StringBuffer();
                q.append("SELECT OBJECT(a) FROM ").append(entityInfo.getBeanName()).append(" AS a");

                //            if(order!=null){
                //                q.append(" ORDER BY ");
                //                for(int j=0;j<order.length;j++){
                //                    if(j>0){
                //                        q.append(", ");
                //                    }
                //                    q.append(order[j].fieldName);
                //                    q.append(" ");
                //                    q.append(order[j].isAsc?" ASC" : "DESC");
                //                }
                //            }
                if (
                        entities[i].doGenerateCmpLocalEntity() ||
                        entities[i].doGenerateCmpRemoteEntity()
                ) {
                    fileWriter.println("      <pattern><pattern-method><method-name>findAll</method-name><method-params/></pattern-method><ejb-ql>" + q + "</ejb-ql></pattern>");
                }
                //            for(int j=0;j<dbTables[i].primaryKeys.length;j++){
                //                fileWriter.println("      <primkey-field>"+Utils.toJavaIdentifier(dbTables[i].primaryKeys[j].columnName,false)+"</primkey-field>");
                //            }
            }
            String[] ejbRefs = entityInfo.getEjbReferences();
            for (int j = 0; j < ejbRefs.length; j++) {
                String ejbName = ejbRefs[j];
                try {
                    BOInfo info = moduleCodestyle.getBOInfo(ejbName);
                    fileWriter.println("      <ejb-ref>");
                    fileWriter.println("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>");
                    fileWriter.println("      <ejb-ref-type>Session</ejb-ref-type>");
                    fileWriter.println("      <home>" + info.getFullSessionHomeName() + "</home>");
                    fileWriter.println("      <remote>" + info.getFullSessionRemoteName() + "</remote>");
                    fileWriter.println("      </ejb-ref>");
                } catch (NoSuchElementException e) {
                    try {
                        DAOInfo info = moduleCodestyle.getDAOInfo(ejbName);
                        fileWriter.println("      <ejb-ref>");
                        fileWriter.println("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>");
                        fileWriter.println("      <ejb-ref-type>Entity</ejb-ref-type>");
                        fileWriter.println("      <home>" + info.getFullEntityHomeName() + "</home>");
                        fileWriter.println("      <remote>" + info.getFullEntityRemoteName() + "</remote>");
                        fileWriter.println("      </ejb-ref>");
                    } catch (NoSuchElementException e1) {
                        try {
                            SessionDeployInfo info = moduleCodestyle.getExtraSessionsByName(ejbName);
                            fileWriter.println("      <ejb-ref>");
                            fileWriter.println("      <ejb-ref-name>ejb/" + info.getEjbName() + "Ref</ejb-ref-name>");
                            fileWriter.println("      <ejb-ref-type>Session</ejb-ref-type>");
                            fileWriter.println("      <home>" + info.getHome() + "</home>");
                            fileWriter.println("      <remote>" + info.getRemote() + "</remote>");
                            fileWriter.println("      </ejb-ref>");
                        } catch (NoSuchElementException e2) {
                            throw new NoSuchElementException("No Bean is named " + ejbName);
                        }
                    }
                }
            }
            fileWriter.println("    </entity>");
        }
        fileWriter.println("  </enterprise-beans>");
        fileWriter.println("  <assembly-descriptor>");
        for (int i = 0; i < entities.length; i++) {
            DAOInfo entityInfo = entities[i];
            boolean isCMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local");
            boolean isBMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local");
            if (!isBMP && !isCMP) {
                continue;
            }
            fileWriter.println("    <container-transaction>");
            fileWriter.println("      <method>");
            fileWriter.println("        <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>");
            fileWriter.println("        <method-name>*</method-name>");
            fileWriter.println("      </method>");
            fileWriter.println("      <trans-attribute>Required</trans-attribute>");
            fileWriter.println("    </container-transaction>");
        }

        for (int i = 0; i < sessionInfos.length; i++) {
            BOInfo si = sessionInfos[i];
            boolean isGen = si.doGenerateSession();
            if (!isGen) {
                continue;
            }
            fileWriter.println("    <container-transaction>");
            fileWriter.println("      <method>");
            fileWriter.println("        <ejb-name>" + si.getSessionName() + "</ejb-name>");
            fileWriter.println("        <method-name>*</method-name>");
            fileWriter.println("      </method>");
            fileWriter.println("      <trans-attribute>Required</trans-attribute>");
            fileWriter.println("    </container-transaction>");
        }

        fileWriter.println("  </assembly-descriptor>");
        fileWriter.println("</ejb-jar>");
        fileWriter.close();
    }

    public String toString() {
        return "ejb-jar.xml 2.0 Generator";
    }

}

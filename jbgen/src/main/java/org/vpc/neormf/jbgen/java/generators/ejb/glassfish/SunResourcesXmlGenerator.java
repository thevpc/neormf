/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.ejb.glassfish;

import java.io.File;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.xml.XmlNode;

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
public class SunResourcesXmlGenerator extends AbsractModuleGenerator {

    public SunResourcesXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }
    
    public void generate(Connection connection, JBGenProjectInfo project) throws SQLException, IOException {
        // target AS
        // EJB version
        String targetApplicationServer = project.getTargetApplicationServer();
        //String targetEjbVersion = project.getTargetEjbVersion();
        if(!JBGenProjectInfo.GLASSFISH2.equals(targetApplicationServer)){
            return;
        }
        if(!project.getDataSourceName().startsWith("jdbc/")){
            throw new IllegalArgumentException("Jdbc Datasources usually starts with jdbc/ (ex: jdbc/mydatasource)");
        }
        String metaInf = project.getTargetSetupFolder();
        File xml = new File(metaInf + "/sun-resources.xml");
        XmlNode resources=xml.exists()?XmlNode.load(xml):(new XmlNode("resources"));
        XmlNode jdbc_resource=resources.getChild("jdbc-resource<jndi-name=\""+project.getDataSourceName()+"\">", false);
        if(jdbc_resource==null){
            String p=project.getDataSourceName();
            int x=p.lastIndexOf('/');
            int y=p.lastIndexOf(':');
            x=x>y?x:y;
            if(x>0){
                p=p.substring(x+1);
            }
            jdbc_resource=new XmlNode("jdbc-resource")
                    .setAttribute("enabled", "true")
                    .setAttribute("object-type", "user")
                    .setAttribute("pool-name", p)
                    .setAttribute("jndi-name", project.getDataSourceName())
            ;
            resources.add(jdbc_resource);
            XmlNode jdbc_pool=new XmlNode("jdbc-connection-pool")
                    .setAttribute("allow-non-component-callers", "false")
                    .setAttribute("associate-with-thread", "false")
                    .setAttribute("connection-creation-retry-attempts", "0")
                    .setAttribute("connection-creation-retry-interval-in-seconds", "10")
                    .setAttribute("connection-leak-reclaim", "false")
                    .setAttribute("connection-leak-timeout-in-seconds", "0")
                    .setAttribute("connection-validation-method", "auto-commit")
                    .setAttribute("datasource-classname", project.getConnectionDataSource())
                    .setAttribute("fail-all-connections", "false")
                    .setAttribute("idle-timeout-in-seconds", "300")
                    .setAttribute("is-connection-validation-required", "false")
                    .setAttribute("is-isolation-level-guaranteed", "true")
                    .setAttribute("lazy-connection-association", "false")
                    .setAttribute("lazy-connection-enlistment", "false")
                    .setAttribute("match-connections", "false")
                    .setAttribute("max-connection-usage-count", "0")
                    .setAttribute("max-pool-size", "32")
                    .setAttribute("max-wait-time-in-millis", "60000")
                    .setAttribute("name", p)
                    .setAttribute("non-transactional-connections", "false")
                    .setAttribute("pool-resize-quantity", "2")
                    .setAttribute("res-type", "javax.sql.DataSource")
                    .setAttribute("statement-timeout-in-seconds", "-1")
                    .setAttribute("steady-pool-size", "8")
                    .setAttribute("validate-atmost-once-period-in-seconds", "0")
                    .setAttribute("wrap-jdbc-objects", "false")
                    .add(new XmlNode("property").setAttribute("name", "URL").setAttribute("value", project.getConnectionURL()))
                    .add(new XmlNode("property").setAttribute("name", "User").setAttribute("value", project.getConnectionUser()))
                    .add(new XmlNode("property").setAttribute("name", "Password").setAttribute("value", project.getConnectionPassword()));
                Properties pr=project.getDriverUrlParser().parse(project.getConnectionDriver(),project.getConnectionURL());
                for (Entry<Object, Object> entry : pr.entrySet()) {
                    jdbc_pool.add(new XmlNode("property").setAttribute("name", (String)entry.getKey()).setAttribute("value", (String)entry.getValue()));
                }
                resources.add(jdbc_pool);
                String header="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        +"<!DOCTYPE resources PUBLIC \"-//Sun Microsystems, Inc.//DTD Application Server 9.0 Resource Definitions //EN\" \"http://www.sun.com/software/appserver/dtds/sun-resources_1_3.dtd\">";
                resources.store(xml,header);
                getLog().info("generated = " + xml.getCanonicalPath());
                getLog().info(" generating "+xml.getName()+" to " + xml.getCanonicalPath() + "...");
        }
    }

    @Override
    public String toString() {
        return "SunResourcesXmlGenerator";
    }
}

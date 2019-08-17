/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.web;

import java.io.File;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class WebXmlGenerator extends AbsractModuleGenerator {

    public WebXmlGenerator(JBGenMain jbgen) {
        super(jbgen);
    }
    
    public void generate(Connection connection, JBGenProjectInfo project) throws SQLException, IOException {
        // target AS
        // EJB version
        //String targetApplicationServer = project.getTargetApplicationServer();
        String targetEjbVersion = project.getTargetEjbVersion();
        if("3.0".compareTo(targetEjbVersion)<=0){
            return;
        }
        File webModule = new File(project.getTargetWebInfFolder());
        File webxml = new File(webModule + "/web.xml");
        XmlNode webapp=webxml.exists()?XmlNode.load(webxml):(new XmlNode("web-app").setAttribute("version", "2.5").setAttribute("xmlns", "http://java.sun.com/xml/ns/javaee"));
        XmlNode session_config=webapp.getChild("welcome-file-list", false);
        if(session_config==null){
            session_config=new XmlNode("session-config").add(new XmlNode("session-timeout").setText("30"));
            webapp.add(session_config);
        }
        XmlNode welcome_file_list=webapp.getChild("welcome-file-list",false);
        if(welcome_file_list==null){
            welcome_file_list=(new XmlNode("welcome-file-list").add(new XmlNode("welcome-file").setText("index.jsp")));
            webapp.add(welcome_file_list);
        }
        XmlNode[] ejb_ref_arr=webapp.getChildren("ejb-ref");
        BOInfo[] sessionInfos = project.getAllBOs();
        for (BOInfo s : sessionInfos) {
            XmlNode found=null;
            for (XmlNode e : ejb_ref_arr) {
                found=e.getChild("ejb-ref-name",false);
                if(!s.getBeanName().equals(found.getText())){
                    found=null;
                }
                if(found!=null){
                    break;
                }
            }
            if(found==null){
                webapp.add(new XmlNode("ejb-ref")
                        .add(new XmlNode("ejb-ref-name").setText(s.getBeanName()))
                        .add(new XmlNode("ejb-ref-type").setText("Session"))
                        .add(new XmlNode("home").setText(s.getFullSessionHomeName()))
                        .add(new XmlNode("remote").setText(s.getFullSessionRemoteName()))
                        .add(new XmlNode("ejb-link").setText(s.getBeanName()))
               );
            }
        }
        webapp.store(webxml);
        getLog().info(" generating web.xml to " + webxml.getCanonicalPath() + "...");
    }

    @Override
    public String toString() {
        return "WebXmlGenerator";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpc.neormf.jbgen.java.generators.web.iceface;

import org.vpc.neormf.jbgen.java.generators.ejb.ejb3.dao.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.vpc.neormf.commons.types.BooleanType;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.DateType;
import org.vpc.neormf.commons.types.ListChoiceType;
import org.vpc.neormf.commons.types.StringType;
import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.projects.J2eeTarget;

/**
 *
 * @author Ould Med Lemine Brahim
 */
public class EJB3JspxIceFaceGenerator extends JBGenDAOGenerator {

    public EJB3JspxIceFaceGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO) 
        && entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0);
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DAO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DAO),entityInfo.getBeanName()+".jspx");
        PrintStream ps=new PrintStream(destFolder);
        ps.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
        ps.println("<jsp:directive.page contentType=\"text/html;charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"/>");
        ps.println("<jsp:root version=\"1.2\" " +
        		"xmlns:jsp=\"http://java.sun.com/JSP/Page\" " +
        		"xmlns:f=\"http://java.sun.com/jsf/core\" " +
        		"xmlns:h=\"http://java.sun.com/jsf/html\" " +
        		"xmlns:ice=\"http://www.icesoft.com/icefaces/component\">");
        ps.println("<jsp:directive.page contentType=\"text/html;charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"/>");
        ps.println("<f:view>");
        ps.println("<html>");
        ps.println("<body>");
        ps.println("<h>"+entityInfo.getBeanName()+"</h>");
        ps.println("<ice:form partialSubmit=\"true\" >");        
        for(DBColumn col:entityInfo.getColumns(true, true, true)){
            IceFaceWidget w=generateWidget(col);
            switch(w.size){
                case S1x1:{  
                	ps.println("<ice:panelGrid columns=\"2\" rowClasses=\"textFieldNameRow, textFieldNameRow, textFieldCommentsRow\">");
                	ps.println(w.labelHtml);                                  
                	ps.println(w.editorHtml); 
                	ps.println(" </ice:panelGrid>");
                   break;
                }
                case S2x2:{
                	ps.println("<ice:panelGrid columns=\"1\" rowClasses=\"textFieldNameRow, textFieldNameRow, textFieldCommentsRow\">");
                	ps.println(w.labelHtml);                                  
                	ps.println(w.editorHtml); 
                	ps.println(" </ice:panelGrid>");
                   break;
                }
            }
        }
        ps.println("</ice:form>");
        ps.println("</body>");
        ps.println("</html>");
        ps.println("</f:view>");
    }
    
    IceFaceWidget generateWidget(DBColumn col){
        IceFaceWidget w=new IceFaceWidget();
        w.labelHtml="<ice:outputLabel value=\"#{"+col.getDAOInfo().getBeanName()+"."+col.getBeanFieldName()+"}\">";
        
        w.column=col;
        DataType dt=col.getBusinessDataType();
        if(dt instanceof StringType){
            if(((StringType)dt).isNullable()) {
                //if(((StringType)dt).isNullable())?
                w.editorHtml = "<ice:inputText id=\"" + col.getBeanFieldName() + "\" value=\"#{" + col.getDAOInfo().getBeanName() + "." + col.getBeanFieldName() + "}\" partialSubmit=\"true\"/>";
            }
            w.size=EJB3JspxIceFaceGenerator.WidgetSize.S1x1;            
        }else if(dt instanceof DateType){
            if(((DateType)dt).isNullable()) {
                w.editorHtml = "<ice:selectInputDate id=\"date2\" value=\" \" renderAsPopup=\"true\">" + "<f:convertDateTime pattern=\"MM/dd/yyyy\" timeZone=\" \"/>" + "</ice:selectInputDate>";
            }
            w.size=EJB3JspxIceFaceGenerator.WidgetSize.S1x1;         
        }else if(dt instanceof BooleanType){
            if(((BooleanType)dt).isNullable()) {
                w.editorHtml = "<h:selectBooleanCheckbox value=\" \" />";
            }
            w.size=EJB3JspxIceFaceGenerator.WidgetSize.S1x1;            
        } else if(dt instanceof ListChoiceType){
            if(((ListChoiceType)dt).isNullable()) {
                w.editorHtml = "<h:selectBooleanCheckbox value=\" \" />";
            }
            w.size=EJB3JspxIceFaceGenerator.WidgetSize.S1x1;            
        } else {
            w.editorHtml="<h:selectBooleanCheckbox value=\" \" />";
            w.size=EJB3JspxIceFaceGenerator.WidgetSize.S1x1;            
        }        
        return w;
    }
    
    private static enum WidgetSize{
        S1x1,
        S2x2,
        S2x4
    }

    private static class IceFaceWidget{
        private DBColumn column;
        private String labelHtml;
        private String editorHtml;
        private WidgetSize size;
        
    }    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

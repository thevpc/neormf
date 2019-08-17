/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.csharp.generators.data;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpClassSource;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpField;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpMethod;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 17:11:33
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
class CSharpModuleInfoGenerator extends AbsractModuleGenerator {


    public CSharpModuleInfoGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodeStyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
//        EntityInfo[] entityInfo = moduleCodeStyle.getAllEntities();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_DTO));
        CSharpClassSource theClass = new CSharpClassSource();
        theClass.setComments(moduleCodeStyle.getComments());
        theClass.setModifiers("public");
        theClass.setName(moduleCodeStyle.getModuleName());
        theClass.setSuperClass("ModuleMetaData");
        theClass.setPackage(moduleCodeStyle.getModulePackage());
        theClass.addImport("System");
        theClass.addImport("org.vpc.neormf.commons.beans");


        StringBuilder infoContent = new StringBuilder();
        infoContent.append(" return new String[]{");
        ArrayList arraySet = (ArrayList) moduleCodeStyle.getUserProperties().get("generatedClasses.DataTransfertObject");
        if (arraySet != null) {
            boolean first=true;
            for (Iterator i = arraySet.iterator(); i.hasNext();) {
                if(first){
                    first=false;
                }else{
                    infoContent.append(",");
                }
                infoContent.append("\n");
                CSharpClassSource javaClassSource = (CSharpClassSource) i.next();
                infoContent.append("\"");
                infoContent.append(javaClassSource.getFQName());
                infoContent.append("\"");
            }
        }
        infoContent.append("\n");
        infoContent.append(" };");

        theClass.addField(new CSharpField("INSTANCE", theClass.getName(), null, "public static ", "new " + theClass.getName() + "()"));

        theClass.addMethod(new CSharpMethod(theClass.getName(), null, null, "private", null, "Constructor",
                ""));
        theClass.addMethod(new CSharpMethod("GetDTOList", "String[]", null, "public override", null, null,
                infoContent.toString()));

        arraySet = (ArrayList) moduleCodeStyle.getUserProperties().get("generatedClasses.DataTransfertObject");
        if (arraySet == null) {
            arraySet = new ArrayList();
            moduleCodeStyle.getUserProperties().put("generatedClasses.ModuleMetaData", arraySet);
        }
        arraySet.add(theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating ModuleMetaData Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + "...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "CSharp ModuleMetaData Generator";
    }

}

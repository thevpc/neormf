package org.vpc.neormf.jbgen.csharp.generators.text;

import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpClassSource;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 7 mai 2004 Time: 19:18:54
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class CSharpDTOClassNamesListGenerator extends AbsractModuleGenerator {

    public CSharpDTOClassNamesListGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodeStyle) throws SQLException, IOException {
        String targetFileName = moduleCodeStyle.getString("target.debug.data",null,false);
        if (targetFileName != null && targetFileName.length() != 0) {
            File targetFile = new File(targetFileName);

            StringWriter stringWriter = new StringWriter();

            ArrayList arraySet = (ArrayList) moduleCodeStyle.getUserProperties().get("generatedClasses.DataTransfertObject");
            if (arraySet != null) {
                for (Iterator i = arraySet.iterator(); i.hasNext();) {
                    CSharpClassSource javaClassSource = (CSharpClassSource) i.next();
                    stringWriter.write(javaClassSource.getPackage() + "." + javaClassSource.getName());
                    stringWriter.write(System.getProperty("line.separator"));
                }
            }
            stringWriter.close();
//            JBGenUtils.askFileReadOnly(targetFile);
            try {
                if (JBGenUtils.write(targetFile, stringWriter.getBuffer().toString(), false,getLog())) {
                    getLog().info(" generating DataTransfertObject ClassName List to " + targetFile.getCanonicalPath() + "...");
                }
            } catch (FileNotFoundException e) {
                getLog().error("Readonly file : " + e);
            }
        }

    }
}

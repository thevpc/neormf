//package org.vpc.neormf.jbgen.java.generators.text;
//
//import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
//import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
//import org.vpc.neormf.jbgen.java.model.config.ModuleMetaData;
//import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
//import org.vpc.neormf.jbgen.util.JBGenUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//
///**
// * class presentation
// *
// * @author taha BEN SALAH (tbsalah)
// * @version 1.0
// * @copyrights (c) 2004, Vpc Open Source Foundary
// * @project Neormf
// * @creation on Date: 7 mai 2004 Time: 19:18:54
// * @modification on ---- by -----
// * @modification on ---- by -----
// * @modification on ---- by -----
// */
//public class DataContentClassNamesListGenerator extends AbsractModuleGenerator {
//    public void generate(Connection connection, ModuleMetaData moduleCodeStyle) throws SQLException, IOException {
//        String targetFileName = moduleCodeStyle.getString(
//                "generate.list.data","log","DataContentClassNamesList",false);
//        if (targetFileName != null && targetFileName.length() != 0) {
//            File targetFile = new File(targetFileName);
//
//            StringWriter stringWriter = new StringWriter();
//
//            ArrayList arraySet = (ArrayList) moduleCodeStyle.getUserProperties().get("generatedClasses.DataTransfertObject");
//            if (arraySet != null) {
//                for (Iterator i = arraySet.iterator(); i.hasNext();) {
//                    JavaClassSource javaClassSource = (JavaClassSource) i.next();
//                    stringWriter.write(javaClassSource.getPackage() + "." + javaClassSource.getName());
//                    stringWriter.write(System.getProperty("line.separator"));
//                }
//            }
//            stringWriter.close();
////            JBGenUtils.askFileReadOnly(targetFile);
//            try {
//                if (JBGenUtils.write(targetFile, stringWriter.getBuffer().toString(), false)) {
//                    getLog().info(" generating DataTransfertObject ClassName List to " + targetFile.getCanonicalPath() + "...");
//                }
//            } catch (FileNotFoundException e) {
//                getLog().error("Readonly file : " + e);
//            }
//        }
//
//    }
//}

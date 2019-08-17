package org.vpc.neormf.jbgen.java.generators.dto;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * @creation on Date: 24 mars 2004 Time: 17:11:33
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class PrimaryKeyGenerator extends JBGenDAOGenerator {


    public PrimaryKeyGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        boolean bd = (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
//                || entityInfo.doGenerateTables("data")
                );
        boolean be = false;/*(
                entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-remote")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-cmp-local")
                || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB+".entity-bmp-local")
                || (entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO) && bd)
                );*/
        if(!bd && !be){
            return false;
        }
        boolean nopk = entityInfo.getPrimaryKeys().length == 0;
        if (be && nopk  && entityInfo.isUpdatable()) {
            throw new RuntimeException("No primary key found for " + entityInfo.getBeanName());
        } else {

        }
        return !nopk && (bd || be);
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setName(entityInfo.getDataKeyName());
        theClass.addImport("org.vpc.neormf.commons.beans.DTOMetaData");
        theClass.setSuperClass("org.vpc.neormf.commons.beans.DataKey");
        theClass.setInterfaces(null);
        theClass.setPackage(entityInfo.getDataPackage());
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        JavaParam[] constParams = new JavaParam[pkColumns.length];
        StringBuilder constructorBody = new StringBuilder();
        for (int i = 0; i < constParams.length; i++) {
            constParams[i] = new JavaParam(pkColumns[i].getBeanFieldVar(),
                    pkColumns[i].getBusinessDataTypeName(),
                    null);
            theClass.addField(new JavaField(constParams[i].getName(), constParams[i].getType(), null, "public", null));
            constructorBody.append("this.").append(constParams[i].getName()).append("=").append(constParams[i].getName()).append(";\n");
        }

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "public", null, "Constructor",
                "super();"));

        theClass.addMethod(new JavaMethod(theClass.getName(), null, constParams, "public", null, "Constructor",
                constructorBody.toString()));

        for (int i = 0; i < constParams.length; i++) {
            theClass.addMethod(new JavaMethod(JavaUtils.businessGetterName(pkColumns[i]), constParams[i].getType(), null, "public", null, null,
                    "return " + constParams[i].getName() + ";"));
        }
        StringBuilder getPartBuffer = new StringBuilder();
        if (constParams.length > 1) {
            getPartBuffer.append("switch(index){\n");
            for (int i = 0; i < constParams.length; i++) {
                getPartBuffer.append("  case ").append(i).append(":{\n");
                getPartBuffer.append("    return ").append(JavaUtils.primitiveToObject(constParams[i].getName(), constParams[i].getType())).append(";\n");
                getPartBuffer.append("  }\n");
            }
            getPartBuffer.append("  default:{\n");
            getPartBuffer.append("    throw new ArrayIndexOutOfBoundsException(index);\n");
            getPartBuffer.append("  }\n");
            getPartBuffer.append("}");
        } else if (constParams.length == 1) {
            getPartBuffer.append("return ").append(JavaUtils.primitiveToObject(constParams[0].getName(), constParams[0].getType())).append(";\n");
        } else {
            getPartBuffer.append("return null;\n");
        }
        theClass.addMethod(new JavaMethod("keyPartAt", "Object", new JavaParam[]{
            new JavaParam("index", "int", null)
        }, "public", null, null,
                getPartBuffer.toString()));
        theClass.addMethod(new JavaMethod("keySize", "int", null, "public", null, null,
                "return " + constParams.length + ";"));

        theClass.addMethod(new JavaMethod("metadata", "DTOMetaData", null, "public", null, null,
                "return " + entityInfo.getDTOName()+ "._METADATA;"));

        entityInfo.setGeneratedClass("PrimaryKey", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));

        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Primary Key Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Primary Key Generator";
    }

}

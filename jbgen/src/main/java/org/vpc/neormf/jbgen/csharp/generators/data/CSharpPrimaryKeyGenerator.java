package org.vpc.neormf.jbgen.csharp.generators.data;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpClassSource;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpField;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpMethod;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpParam;

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
class CSharpPrimaryKeyGenerator extends JBGenDAOGenerator {

    public CSharpPrimaryKeyGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        boolean bd = (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
        );
        boolean be = (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
        );
        if(!(bd || be)){
            return false;
        }
        boolean nopk = entityInfo.getPrimaryKeys().length == 0;
        if (be && nopk && entityInfo.isUpdatable()) {
            throw new RuntimeException("No primary key found for " + entityInfo.getBeanName());
        }
        return !nopk;
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        CSharpClassSource theClass = new CSharpClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setName(entityInfo.getDataKeyName());
        theClass.setSuperClass("org.vpc.neormf.commons.beans.DataKey");
        theClass.addImport("System");
        theClass.addImport("org.vpc.neormf.commons.beans");
        theClass.setInterfaces(null);
        theClass.setPackage(entityInfo.getDataPackage());
        DBColumn[] pkColumns = entityInfo.getPrimaryKeys();
        CSharpParam[] constParams = new CSharpParam[pkColumns.length];
        StringBuilder constructorBody = new StringBuilder();
        for (int i = 0; i < constParams.length; i++) {
            constParams[i] = new CSharpParam(pkColumns[i].getBeanFieldVar(),
                    CSharpUtils.getCSharpClassName(pkColumns[i].getBusinessDataType().toJavaType()),
                    null);
            theClass.addField(new CSharpField(constParams[i].getName(), constParams[i].getType(), null, "public", null));
            constructorBody.append("this.").append(constParams[i].getName()).append("=").append(constParams[i].getName()).append(";\n");
        }

        theClass.addMethod(new CSharpMethod(theClass.getName(), null, null, "public", null, "Constructor",
                ""));

        theClass.addMethod(new CSharpMethod(theClass.getName(), null, constParams, "public", null, "Constructor",
                constructorBody.toString()));

        for (int i = 0; i < constParams.length; i++) {
            theClass.addMethod(new CSharpMethod(CSharpUtils.businessGetterName(pkColumns[i]), constParams[i].getType(), null, "public", null, null,
                    "return " + constParams[i].getName() + ";"));
        }
        StringBuilder getPartBuffer = new StringBuilder();
        if (constParams.length > 1) {
            getPartBuffer.append("switch(index){\n");
            for (int i = 0; i < constParams.length; i++) {
                getPartBuffer.append("  case ").append(i).append(":{\n");
                getPartBuffer.append("    return ").append(CSharpUtils.primitiveToObject(constParams[i].getName(), constParams[i].getType())).append(";\n");
                getPartBuffer.append("  }\n");
            }
            getPartBuffer.append("  default:{\n");
            getPartBuffer.append("    throw new Exception(\"ArrayIndexOutOfBoundsException \"+index);\n");
            getPartBuffer.append("  }\n");
            getPartBuffer.append("}");
        } else if (constParams.length == 1) {
            getPartBuffer.append("return ").append(CSharpUtils.primitiveToObject(constParams[0].getName(), constParams[0].getType())).append(";\n");
        } else {
            getPartBuffer.append("return null;\n");
        }
        theClass.addMethod(new CSharpMethod("KeyPartAt", "Object", new CSharpParam[]{
                new CSharpParam("index", "int", null)
        }, "public override", null, null,
                getPartBuffer.toString()));
        theClass.addMethod(new CSharpMethod("KeySize", "int", null, "public override", null, null,
                "return " + constParams.length + ";"));

        theClass.addMethod(new CSharpMethod("Info", "DTOMetaData", null, "public override", null, null,
                "return " + entityInfo.getDTOName()+ ".INFO;"));

//        entityInfo.setGeneratedClass("PrimaryKey", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));

        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating Primary Key Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "CSharp Primary Key Generator";
    }

}

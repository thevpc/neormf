package org.vpc.neormf.jbgen.csharp.generators.data;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpClassSource;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpField;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpMethod;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpParam;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.util.FieldFormulaImpl;
import org.vpc.neormf.jbgen.util.FieldFormulaType;
import org.vpc.neormf.jbgen.util.RelationDesc;

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
class CSharpPropertyListGenerator extends JBGenDAOGenerator {

    public CSharpPropertyListGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
                        || entityInfo.doGenerateBean(J2eeTarget.MODULE_DAO)
        );
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        CSharpClassSource theClass = new CSharpClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.setName(entityInfo.getPropertyListName());
        theClass.setSuperClass("org.vpc.neormf.commons.beans.PropertyList");
        theClass.setInterfaces(null);
        theClass.addImport("System");
        theClass.addImport("System.Collections");
        theClass.addImport("org.vpc.neormf.commons.beans");
        theClass.setPackage(entityInfo.getDataPackage());

        theClass.addMethod(new CSharpMethod(theClass.getName(), null, null, "public", null, "Constructor",
                ""));

        DBColumn[] columns = entityInfo.getColumns(true, true, true);

        for (int i = 0; i < columns.length; i++) {
            DBColumn column = columns[i];
            CSharpField field = new CSharpField(column.getBeanFieldConstant(),
                    "String",
                    null,
                    "public const",
                    "\"" + columns[i].getBeanFieldName() + "\"");
            theClass.addField(field);
            FieldFormulaImpl getterImpl = column.getGetterImpl();
            if (getterImpl.getType() == FieldFormulaType.sqlMasterDetail) {
                RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
                String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getDataPackage());
                theClass.addImport("org.vpc.neormf.commons.beans");
                theClass.addMethod(new CSharpMethod(CSharpUtils.method(column,"Add"), theClass.getName(), new CSharpParam[]{
                        new CSharpParam("list", rpl, null),
                        new CSharpParam("criteria", "Criteria", null),
                        new CSharpParam("order", "OrderList", null)
                }, "public", null, null,
                        "base.AddProperty(" + field.getName() + ",new Object[]{list,criteria,order});\n" +
                                "return this;"));
                theClass.addMethod(new CSharpMethod(CSharpUtils.method(column,"Remove"), theClass.getName(), null, "public", null, null,
                        "base.RemoveProperty(" + field.getName() + ");\n" +
                                "return this;"));
//                theClass.addMethod(new CSharpMethod(column.method("insert"), theClass.getName(), new CSharpParam[]{
//                    new CSharpParam("list",rpl,null),
//                    new CSharpParam("index", "int", null)
//                }, "public", null, null,
//                        "super.insertProperty(" + field.getName() + ",list,index);\n" +
//                        "return this;"));
                theClass.addMethod(new CSharpMethod(CSharpUtils.method(column,"Contains"), "bool", null, "public", null, null,
                        "return base.ContainsProperty(" + field.getName() + ");"));
            } else {
                theClass.addMethod(new CSharpMethod(CSharpUtils.method(column,"Add"), theClass.getName(), null, "public", null, null,
                        "base.AddProperty(" + field.getName() + ");\n" +
                                "return this;"));
                theClass.addMethod(new CSharpMethod(CSharpUtils.method(column,"Remove"), theClass.getName(), null, "public", null, null,
                        "base.RemoveProperty(" + field.getName() + ");\n" +
                                "return this;"));
//                theClass.addMethod(new CSharpMethod(column.method("insert"), theClass.getName(), new CSharpParam[]{
//                    new CSharpParam("index", "int", null)
//                }, "public", null, null,
//                        "super.insertProperty(" + field.getName() + ",index);\n" +
//                        "return this;"));
                theClass.addMethod(new CSharpMethod(CSharpUtils.method(column,"Contains"), "bool", null, "public", null, null,
                        "return base.ContainsProperty(" + field.getName() + ");"));
            }
        }
        theClass.addMethod(new CSharpMethod("AddAllProperties", "void", null, "public override", null, null,
                "base.AddAllProperties(ALL_PROPERTIES);\n"));
        StringBuilder sbfdefaultVal = new StringBuilder("{");
        for (int i = 0; i < theClass.getFields().size(); i++) {
            if (i > 0) {
                sbfdefaultVal.append(",");
            }
            sbfdefaultVal.append(theClass.getField(i).getName());
        }
        sbfdefaultVal.append("}");
        theClass.addField(new CSharpField("ALL_PROPERTIES",
                "String[]",
                null,
                "public static",
                sbfdefaultVal.toString()));

        theClass.addMethod(new CSharpMethod("Info", "DTOMetaData", null, "public override", null, null,
                "return " + entityInfo.getDTOName()+ ".INFO;"));

//        entityInfo.setGeneratedClass("PropertyList", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating PropertyList Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "CSharp Property List Generator";
    }

}

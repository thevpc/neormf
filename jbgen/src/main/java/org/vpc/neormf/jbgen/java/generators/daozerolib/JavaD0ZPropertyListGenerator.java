package org.vpc.neormf.jbgen.java.generators.daozerolib;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.java.generators.JBGenDAOGenerator;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
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
public class JavaD0ZPropertyListGenerator extends JBGenDAOGenerator {


    public JavaD0ZPropertyListGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public boolean accept(Connection connection, DAOInfo entityInfo) {
        return (
                entityInfo.doGenerateBean(J2eeTarget.MODULE_DTO)
//                        &&
//                        entityInfo.doGenerateTables("table")
        );
    }

    public void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException {
        entityInfo.checkGenerateFilter(J2eeTarget.MODULE_DTO);
    }

    public void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException {
        File destFolder = new File(entityInfo.getProjectInfo().getModuleFolder(J2eeTarget.MODULE_DTO));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(entityInfo.getComments());
        theClass.setModifiers("public");
        theClass.addImport("java.util.*");
        theClass.setName(entityInfo.getPropertyListName());
        theClass.setInterfaces(null);
        theClass.setPackage(entityInfo.getDataPackage());
        theClass.addField(new JavaField("set", "LinkedHashMap", null, "private", "new LinkedHashMap()"));

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "public", null, "Constructor",
                ""));

        DBColumn[] columns = entityInfo.getColumns(true, true, true);

        for (int i = 0; i < columns.length; i++) {
            DBColumn column = columns[i];
            JavaField field = new JavaField(column.getBeanFieldConstant(),
                    "String",
                    null,
                    "public static final",
                    "\"" + columns[i].getBeanFieldName() + "\"");
            theClass.addField(field);
            FieldFormulaImpl getterImpl = column.getGetterImpl();
            if (getterImpl.getType() == FieldFormulaType.sqlMasterDetail) {
                RelationDesc relationDesc = new RelationDesc(column.getGetterImpl().getBody(), column, entityInfo);
                String rpl = relationDesc.getDetailTable().getDAOInfo().getPropertyListName();
                theClass.addImport(relationDesc.getDetailTable().getDAOInfo().getFullPropertyListName());
                theClass.addImport(entityInfo.getFullDataFilterName());
//                theClass.addImport(OrderList.class.getName());
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "add"), theClass.getName(), new JavaParam[]{
                        new JavaParam("list", rpl, null),
                        new JavaParam("criteria", "String", null),
                        new JavaParam("params", "Object[]", null),
                        new JavaParam("order", "String", null)
                }, "public", null, null,
                        "set.put(" + field.getName() + ",new Object[]{list,criteria,order});\n" +
                                "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "remove"), theClass.getName(), null, "public", null, null,
                        "set.remove(" + field.getName() + ");\n" +
                                "return this;"));
//                theClass.addMethod(new JavaMethod(column.method("insert"), theClass.getName(), new JavaParam[]{
//                    new JavaParam("list",rpl,null),
//                    new JavaParam("index", "int", null)
//                }, "public", null, null,
//                        "super.insertProperty(" + field.getName() + ",list,index);\n" +
//                        "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils. method(column, "contains"), "boolean", null, "public", null, null,
                        "return set.containsKey(" + field.getName() + ");"));
            } else {
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "add"), theClass.getName(), null, "public", null, null,
                        "set.put(" + field.getName() + ",null);\n" +
                                "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "remove"), theClass.getName(), null, "public", null, null,
                        "set.remove(" + field.getName() + ");\n" +
                                "return this;"));
//                theClass.addMethod(new JavaMethod(column.method("insert"), theClass.getName(), new JavaParam[]{
//                    new JavaParam("index", "int", null)
//                }, "public", null, null,
//                        "super.insertProperty(" + field.getName() + ",index);\n" +
//                        "return this;"));
                theClass.addMethod(new JavaMethod(JavaUtils.method(column, "contains"), "boolean", null, "public", null, null,
                        "return set.containsKey(" + field.getName() + ");"));
            }
        }

        theClass.addMethod(new JavaMethod("propertiesIterator","Iterator",null,"public",null,null,"return set.keySet().iterator();"));
        theClass.addMethod(new JavaMethod("size","int",null,"public",null,null,"return set.size();"));
        theClass.addMethod(new JavaMethod("propertiesSet","Set",null,"public",null,null,"return set.keySet();"));
        theClass.addMethod(new JavaMethod("getPropertyConstraints","Object",new JavaParam[]{new JavaParam("propertyName","String",null)}, "public",null,null,
                        "        return set.get(propertyName);"
        ));

        entityInfo.setGeneratedClass("PropertyList", theClass);
//        JBGenUtils.askFileReadOnly(theClass.getFile(destFolder));
        try {
            if (theClass.rewrite(destFolder,getLog())) {
                getLog().info(" generating PropertyList Class " + theClass.getPackage() + "." + theClass.getName() + " to " + destFolder.getCanonicalPath() + " ...");
            }
            entityInfo.getProjectInfo().addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Property List Generator";
    }

}

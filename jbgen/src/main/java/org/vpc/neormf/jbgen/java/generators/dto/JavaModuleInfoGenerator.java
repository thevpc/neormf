package org.vpc.neormf.jbgen.java.generators.dto;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.projects.J2eeTarget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.vpc.neormf.jbgen.dbsupport.DBRelation;

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
class JavaModuleInfoGenerator extends AbsractModuleGenerator {


    public JavaModuleInfoGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodeStyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
//        EntityInfo[] entityInfo = moduleCodeStyle.getAllEntities();
        File destFolder = new File(moduleCodeStyle.getModuleFolder(J2eeTarget.MODULE_DTO));
        JavaClassSource theClass = new JavaClassSource();
        theClass.setComments(moduleCodeStyle.getComments());
        theClass.setModifiers("public");
        theClass.setName(moduleCodeStyle.getModuleName());
        theClass.setSuperClass("ModuleMetaData");
        theClass.setPackage(moduleCodeStyle.getModulePackage());
//        theClass.addImport("System");
        theClass.addImport("org.vpc.neormf.commons.beans.*");


        StringBuilder relationsContent = new StringBuilder();
        relationsContent.append("new RelationInfo[]{");
        StringBuilder infoContent = new StringBuilder();
        infoContent.append("new String[]{");
        ArrayList arraySet = (ArrayList) moduleCodeStyle.getUserProperties().get("generatedClasses.DataTransfertObject");
        if (arraySet != null) {
            boolean first = true;
            for (Iterator i = arraySet.iterator(); i.hasNext();) {
                if (first) {
                    first = false;
                } else {
                    infoContent.append(",");
                }
                infoContent.append("\n");
                JavaClassSource javaClassSource = (JavaClassSource) i.next();
                infoContent.append("  \"");
                infoContent.append(javaClassSource.getPackage()==null?"":(javaClassSource.getPackage()+"."));
                infoContent.append(javaClassSource.getName());
                infoContent.append("\"");
            }
        }
        infoContent.append("\n");
        infoContent.append(" }");

        JavaClassSource relationsClass=new JavaClassSource();
        relationsClass.setName("Relations");
        relationsClass.setModifiers("public static final");
        for (DBRelation rr: moduleCodeStyle.getRelations()) {
            StringBuilder ri=new StringBuilder();
            ri.append("new RelationInfo(");
            ri.append("\"").append(rr.getName()).append("\"");
            ri.append(",new String[]{");
            for (int i = 0; i < rr.getFkColumns().size(); i++) {
                if (i>0) {
                    ri.append(",");
                }
                ri.append("\"").append(rr.getFkColumns().get(i).getBeanFieldName()).append("\"");
            }
            ri.append("}");
            ri.append(",new String[]{");
            for (int i = 0; i < rr.getPkColumns().size(); i++) {
                if (i>0) {
                    ri.append(",");
                }
                ri.append("\"").append(rr.getPkColumns().get(i).getBeanFieldName()).append("\"");
            }
            ri.append("}");
            ri.append(",null");
            ri.append(",null");
            ri.append(",RelationInfo.Type.AGGREGATION");
            ri.append(",RelationInfo.Cardinality.ONE_TO_MANY");
            ri.append(",\"").append(rr.getFkTable().getDAOInfo().getFullDTOName()).append("\"");
            ri.append(",\"").append(rr.getPkTable().getDAOInfo().getFullDTOName()).append("\"");
            ri.append(")");
            JavaField jf=new JavaField(rr.getName(), "RelationInfo", null, "public static final",ri.toString());
            relationsClass.addField(jf);
            if(relationsContent.charAt(relationsContent.length()-1)!='{'){
               relationsContent.append(",\n");
            }else{
               relationsContent.append("\n");
            }
            relationsContent.append("  Relations.").append(rr.getName());
        }
//        for (DAOInfo dAOInfo : moduleCodeStyle.getAllEntities()) {
//            for (DBColumn col : dAOInfo.getColumns(false, false, true)) {
//                if(col.getRelation()!=null){
//                    DBRelation rr=col.getRelation();
//                    StringBuilder ri=new StringBuilder();
//                    ri.append("new RelationInfo(");
//                    ri.append("\"").append(rr.getName()).append("\"");
//                    ri.append(",\"").append(col.getBeanFieldName()).append("\"");
//                    ri.append(",null");
//                    ri.append(",\"").append(col.getBeanFieldName()).append("\"");
//                    ri.append(",\"").append(col.getBeanFieldName()).append("\"");
//                    ri.append(",RelationInfo.Type.AGGREGATION");
//                    ri.append(",RelationInfo.Cardinality.ONE_TO_MANY");
//                    ri.append(",\"").append(rr.getFkTable().getDAOInfo().getFullDTOName()).append("\"");
//                    ri.append(",\"").append(rr.getPkTable().getDAOInfo().getFullDTOName()).append("\"");
//                    ri.append(")");
//                    JavaField jf=new JavaField(rr.getName(), "RelationInfo", null, "public static final",ri.toString());
//                    relationsClass.addField(jf);
//                    if(relationsContent.charAt(relationsContent.length()-1)!='{'){
//                       relationsContent.append("    ,\n");
//                    }else{
//                       relationsContent.append("    \n");
//                    }
//                    relationsContent.append("Relations.").append(rr.getName());
//                }
//            }
//        }
        relationsContent.append("\n}");
        theClass.addSubClass(relationsClass);

        
        theClass.addField(new JavaField("INSTANCE", theClass.getName(), null, "public static final ", "new " + theClass.getName() + "()"));
        theClass.addField(new JavaField("RELATIONS_LIST", "RelationInfo[]", null, "public static final ", relationsContent.toString()));
        theClass.addField(new JavaField("DTO_CLASS_NAMES_LIST", "String[]", null, "public static final ", infoContent.toString()));

        theClass.addMethod(new JavaMethod(theClass.getName(), null, null, "private", null, "Constructor",""));
        theClass.addMethod(new JavaMethod("getDTOList", "String[]", null, "public", null, null,"return DTO_CLASS_NAMES_LIST;"));
        theClass.addMethod(new JavaMethod("getRelationsList", "RelationInfo[]", null, "public", null, null,"return RELATIONS_LIST;"));

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
            moduleCodeStyle.addGeneratedFile(theClass.getFile());
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }

    public String toString() {
        return "Java ModuleMetaData Generator";
    }

}

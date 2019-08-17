package org.vpc.neormf.jbgen.java.generators.ejb.ejbversion;

import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.java.generators.AbsractModuleGenerator;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.*;
import org.vpc.neormf.jbgen.info.*;
import org.vpc.neormf.commons.util.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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
public class EjbJarXml11Generator extends AbsractModuleGenerator {

    public EjbJarXml11Generator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodestyle) throws SQLException, IOException {
        getLog().info(" --------- " + this + " ...");
        DAOInfo[] entities = moduleCodestyle.getAllEjbEntities();
        File destFolder = new File(moduleCodestyle.getTargetMetaInfFolder());

        StringWriter stringWriter = new StringWriter();
        stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringWriter.write("<!DOCTYPE ejb-jar PUBLIC \"-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN\" \"http://java.sun.com/j2ee/dtds/ejb-jar_1_1.dtd\">\n");
        stringWriter.write("<ejb-jar>\n");
        stringWriter.write("  <display-name>" + moduleCodestyle.getModuleName() + "</display-name>\n");

        stringWriter.write("  <enterprise-beans>\n");

        BOInfo[] sessionInfos = moduleCodestyle.getAllBOs();
        for (int i = 0; i < sessionInfos.length; i++) {
            BOInfo si = sessionInfos[i];
            boolean isGenLoc = si.doGenerateLocalSession();
            boolean isGenRem = si.doGenerateRemoteSession();
            boolean isGen = isGenRem || isGenLoc;
            if (!isGen) {
                continue;
            }
            stringWriter.write("    <session>\n");
            stringWriter.write("      <description>" + si.getSessionName() + "</description>\n");
            stringWriter.write("      <display-name>" + si.getSessionName() + "</display-name>\n");
            stringWriter.write("      <ejb-name>" + si.getSessionName() + "</ejb-name>\n");
            stringWriter.write("      <home>" + si.getFullSessionHomeName() + "</home>\n");
            stringWriter.write("      <remote>" + si.getFullSessionRemoteName() + "</remote>\n");
            stringWriter.write("      <ejb-class>" + si.getFullSessionBeanName() + "</ejb-class>\n");
            stringWriter.write("      <session-type>Stateless</session-type>\n");
            stringWriter.write("      <transaction-type>Container</transaction-type>\n");
            String[] ejbRefs = si.getEjbReferences();
            for (int j = 0; j < ejbRefs.length; j++) {
                String ejbName = ejbRefs[j];
                try {
                    BOInfo info = moduleCodestyle.getBOInfo(ejbName);
                    stringWriter.write("      <ejb-ref>\n");
                    stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                    stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                    stringWriter.write("      <home>" + info.getFullSessionHomeName() + "</home>\n");
                    stringWriter.write("      <remote>" + info.getFullSessionRemoteName() + "</remote>\n");
                    stringWriter.write("      </ejb-ref>\n");
                } catch (NoSuchElementException e) {
                    try {
                        DAOInfo info = moduleCodestyle.getDAOInfo(ejbName);
                        stringWriter.write("      <ejb-ref>\n");
                        stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                        stringWriter.write("      <ejb-ref-type>Entity</ejb-ref-type>\n");
                        stringWriter.write("      <home>" + info.getFullEntityHomeName() + "</home>\n");
                        stringWriter.write("      <remote>" + info.getFullEntityRemoteName() + "</remote>\n");
                        stringWriter.write("      </ejb-ref>\n");
                    } catch (NoSuchElementException e1) {
                        try {
                            SessionDeployInfo info = moduleCodestyle.getExtraSessionsByName(ejbName);
                            stringWriter.write("      <ejb-ref>\n");
                            stringWriter.write("      <ejb-ref-name>ejb/" + info.getEjbName() + "Ref</ejb-ref-name>\n");
                            stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                            stringWriter.write("      <home>" + info.getHome() + "</home>\n");
                            stringWriter.write("      <remote>" + info.getRemote() + "</remote>\n");
                            stringWriter.write("      </ejb-ref>\n");
                        } catch (NoSuchElementException e2) {
                            throw new NoSuchElementException("No Bean is named " + ejbName);
                        }
                    }
                }
            }
            stringWriter.write("    </session>\n");
        }
        SessionDeployInfo[] sessionDeployInfos = moduleCodestyle.getExtraSessions();
        for (int i = 0; i < sessionDeployInfos.length; i++) {
            SessionDeployInfo si = sessionDeployInfos[i];
            stringWriter.write("    <session>\n");
            stringWriter.write("      <description>" + si.getDescription() + "</description>\n");
            stringWriter.write("      <display-name>" + si.getDisplayName() + "</display-name>\n");
            stringWriter.write("      <ejb-name>" + si.getEjbName() + "</ejb-name>\n");
            stringWriter.write("      <home>" + si.getHome() + "</home>\n");
            stringWriter.write("      <remote>" + si.getRemote() + "</remote>\n");
            stringWriter.write("      <ejb-class>" + si.getEjbClass() + "</ejb-class>\n");
            stringWriter.write("      <session-type>" + si.getSessionType() + "</session-type>\n");
            stringWriter.write("      <transaction-type>" + si.getTransactionType() + "</transaction-type>\n");
            stringWriter.write("    </session>\n");
        }
        for (int i = 0; i < entities.length; i++) {
            DAOInfo entityInfo = entities[i];

            stringWriter.write("    <entity>\n");
            stringWriter.write("      <display-name>" + entityInfo.getBeanName() + "</display-name>\n");
            stringWriter.write("      <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>\n");
            stringWriter.write("      <home>" + entityInfo.getFullEntityHomeName() + "</home>\n");
            stringWriter.write("      <remote>" + entityInfo.getFullEntityRemoteName() + "</remote>\n");
            stringWriter.write("      <ejb-class>" + entityInfo.getFullEntityBeanName() + "</ejb-class>\n");
            if (entityInfo.doGenerateCmpEntity()) {
                stringWriter.write("      <persistence-type>Container</persistence-type>\n");
            } else {
                stringWriter.write("      <persistence-type>Bean</persistence-type>\n");
            }
            stringWriter.write("      <prim-key-class>" + entityInfo.getFullDataKeyName() + "</prim-key-class>\n");
            //<prim-key-class>java.lang.String</prim-key-class>
            stringWriter.write("      <reentrant>False</reentrant>\n");
            if (entityInfo.doGenerateCmpEntity()) {
                stringWriter.write("      <cmp-version>2.x</cmp-version>\n");
                stringWriter.write("      <abstract-schema-name>" + entityInfo.getBeanName() + "</abstract-schema-name>\n");
                DBColumn[] columns = entityInfo.getColumns(true, true, false);
                for (int j = 0; j < columns.length; j++) {
                    stringWriter.write("      <cmp-field><field-name>" + columns[j].getBeanFieldName() + "</field-name></cmp-field>\n");
                }
                StringBuffer q = new StringBuffer();
                q.append("SELECT OBJECT(a) FROM ").append(entityInfo.getBeanName()).append(" AS a");
                stringWriter.write("      <pattern><pattern-method><method-name>findAll</method-name><method-params/></pattern-method><ejb-ql>" + q + "</ejb-ql></pattern>\n");
            }
            String[] ejbRefs = entityInfo.getEjbReferences();
            for (int j = 0; j < ejbRefs.length; j++) {
                String ejbName = ejbRefs[j];
                try {
                    BOInfo info = moduleCodestyle.getBOInfo(ejbName);
                    stringWriter.write("      <ejb-ref>\n");
                    stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                    stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                    stringWriter.write("      <home>" + info.getFullSessionHomeName() + "</home>\n");
                    stringWriter.write("      <remote>" + info.getFullSessionRemoteName() + "</remote>\n");
                    stringWriter.write("      </ejb-ref>\n");
                } catch (NoSuchElementException e) {
                    try {
                        DAOInfo info = moduleCodestyle.getDAOInfo(ejbName);
                        stringWriter.write("      <ejb-ref>\n");
                        stringWriter.write("      <ejb-ref-name>ejb/" + info.getBeanName() + "Ref</ejb-ref-name>\n");
                        stringWriter.write("      <ejb-ref-type>Entity</ejb-ref-type>\n");
                        stringWriter.write("      <home>" + info.getFullEntityHomeName() + "</home>\n");
                        stringWriter.write("      <remote>" + info.getFullEntityRemoteName() + "</remote>\n");
                        stringWriter.write("      </ejb-ref>\n");
                    } catch (NoSuchElementException e1) {
                        try {
                            SessionDeployInfo info = moduleCodestyle.getExtraSessionsByName(ejbName);
                            stringWriter.write("      <ejb-ref>\n");
                            stringWriter.write("      <ejb-ref-name>ejb/" + info.getEjbName() + "Ref</ejb-ref-name>\n");
                            stringWriter.write("      <ejb-ref-type>Session</ejb-ref-type>\n");
                            stringWriter.write("      <home>" + info.getHome() + "</home>\n");
                            stringWriter.write("      <remote>" + info.getRemote() + "</remote>\n");
                            stringWriter.write("      </ejb-ref>\n");
                        } catch (NoSuchElementException e2) {
                            throw new NoSuchElementException("No Bean is named " + ejbName);
                        }
                    }
                }
            }
            stringWriter.write("    </entity>\n");
        }
        stringWriter.write("  </enterprise-beans>\n");
        stringWriter.write("  <assembly-descriptor>\n");


        // TAG <security-role>
        stringWriter.write("    <!-- security roles -->\n");
        EjbRoleInfo[] ejbRole = moduleCodestyle.getEjbRoles();
        for (int i = 0; i < ejbRole.length; i++) {
            EjbRoleInfo role = ejbRole[i];
            stringWriter.write("    <security-role>\n");
            stringWriter.write("      <description>" + role.getRoleDesc() + "</description>\n");
            stringWriter.write("      <role-name>" + role.getRoleName() + "</role-name>\n");
            stringWriter.write("    </security-role>\n");
        }

        // TAG <method-permission>
        stringWriter.write("    <!-- method permissions for Entity Beans -->\n");
        for (int r = 0; r < ejbRole.length; r++) {
            EjbRoleInfo role = ejbRole[r];
            stringWriter.write("<method-permission>\n");
            stringWriter.write("  <role-name>" + role.getRoleName() + "</role-name>\n");
            stringWriter.write("    <!-- method permissions for Entity Beans -->\n");
            for (int i = 0; i < entities.length; i++) {
                DAOInfo entityInfo = entities[i];
                TreeSet treeSet = new TreeSet(Arrays.asList(entityInfo.getBeanRoles("entity")));
                if (treeSet.contains(role.getRoleName())) {
                    stringWriter.write("    <!--" + role.getRoleName() + " : " + entityInfo.getBeanName() + " : " + Utils.dump(entityInfo.getBeanRolesKeys("entity")) + " -->\n");
                    stringWriter.write("    <method>\n");
                    stringWriter.write("        <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>\n");
                    stringWriter.write("        <method-name>*</method-name>\n");
                    stringWriter.write("    </method>\n");
                    continue;
                }

                JavaClassSource javaClassSourceEntityRemote = entityInfo.getReloadedGeneratedClass("EntityRemote");

                JavaClassSource javaClassSourceEntityHome = entityInfo.getReloadedGeneratedClass("EntityHome");
                for (Iterator it = javaClassSourceEntityHome.getMethods().iterator(); it.hasNext();) {
                    JavaMethod javaMethod = (JavaMethod) it.next();
                    JavaDoc methodDoc = new JavaDoc(javaMethod.getComments());
                    JavaDoc.Decoration roles = methodDoc.getDecoration("ejb", "method-permission");
                    boolean addPermission = false;
                    if (roles != null) {
                        if (roles.getParamsString().equals("*")) {
                            addPermission = true;
                        } else {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(roles.getParamsString(), " \t,;"); stringTokenizer.hasMoreTokens();) {
                                String roleName = stringTokenizer.nextToken();
                                if (role.getRoleName().equals(roleName)) {
                                    addPermission = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        addPermission = true;
                    }
                    if (addPermission) {
                        stringWriter.write("    <method>\n");
                        stringWriter.write("        <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>\n");
                        stringWriter.write("        <method-intf>Home</method-intf>\n");
                        stringWriter.write("        <method-name>" + javaMethod.getName() + "</method-name>\n");
                        stringWriter.write("        <method-params>\n");
                        JavaParam[] javaParams = javaMethod.getParams();
                        for (int j = 0; j < javaParams.length; j++) {
                            JavaParam javaParam = javaParams[j];
                            stringWriter.write("            <method-param>" +
                                    JavaUtils.getFullyQualifiedTypeName(javaParam.getType(),
                                            javaClassSourceEntityHome.getImports(),
                                            moduleCodestyle.getSourcePaths())
                                    + "</method-param>\n");

                        }
                        stringWriter.write("        </method-params>\n");
                        stringWriter.write("    </method>\n");
                    }
                }
                for (Iterator it = javaClassSourceEntityRemote.getMethods().iterator(); it.hasNext();) {
                    JavaMethod javaMethod = (JavaMethod) it.next();
                    JavaDoc.Decoration roles = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "method-permission");
                    boolean addPermission = false;
                    if (roles != null) {
                        if (roles.getParamsString().equals("*")) {
                            addPermission = true;
                        } else {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(roles.getParamsString(), " \t,;"); stringTokenizer.hasMoreTokens();) {
                                String roleName = stringTokenizer.nextToken();
                                if (role.getRoleName().equals(roleName)) {
                                    addPermission = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        addPermission = true;
                    }
                    if (addPermission) {
                        stringWriter.write("    <method>\n");
                        stringWriter.write("        <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>\n");
                        stringWriter.write("        <method-intf>Remote</method-intf>\n");
                        stringWriter.write("        <method-name>" + javaMethod.getName() + "</method-name>\n");
                        stringWriter.write("        <method-params>\n");
                        JavaParam[] javaParams = javaMethod.getParams();
                        for (int j = 0; j < javaParams.length; j++) {
                            JavaParam javaParam = javaParams[j];
                            stringWriter.write("            <method-param>" +
                                    JavaUtils.getFullyQualifiedTypeName(javaParam.getType(),
                                            javaClassSourceEntityHome.getImports(),
                                            moduleCodestyle.getSourcePaths())
                                    + "</method-param>\n");

                        }
                        stringWriter.write("        </method-params>\n");
                        stringWriter.write("    </method>\n");
                    }
                }
            }
            stringWriter.write("    <!-- method permissions for Session Beans -->\n");
            for (int i = 0; i < sessionInfos.length; i++) {
                BOInfo sessionInfo = sessionInfos[i];
                TreeSet treeSet = new TreeSet(Arrays.asList(sessionInfo.getBeanRoles("session")));
                if (treeSet.contains(role.getRoleName())) {
                    stringWriter.write("    <!--" + role.getRoleName() + " : " + sessionInfo.getBeanName() + " : " + Utils.dump(sessionInfo.getBeanRolesKeys("session")) + " -->\n");
                    stringWriter.write("    <method>\n");
                    stringWriter.write("        <ejb-name>" + sessionInfo.getBeanName() + "</ejb-name>\n");
                    stringWriter.write("        <method-name>*</method-name>\n");
                    stringWriter.write("    </method>\n");
                    continue;
                }

                JavaClassSource javaClassSourceSessionRemote = sessionInfo.getReloadedGeneratedClass("SessionRemote");
                JavaClassSource javaClassSourceSessionHome = sessionInfo.getReloadedGeneratedClass("SessionHome");

                for (Iterator it = javaClassSourceSessionHome.getMethods().iterator(); it.hasNext();) {
                    JavaMethod javaMethod = (JavaMethod) it.next();
                    JavaDoc methodDoc = new JavaDoc(javaMethod.getComments());
                    JavaDoc.Decoration roles = methodDoc.getDecoration("ejb", "method-permission");
                    boolean addPermission = false;
                    if (roles != null) {
                        if (roles.getParamsString().equals("*")) {
                            addPermission = true;
                        } else {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(roles.getParamsString(), " \t,;"); stringTokenizer.hasMoreTokens();) {
                                String roleName = stringTokenizer.nextToken();
                                if (role.getRoleName().equals(roleName)) {
                                    addPermission = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        addPermission = true;
                    }
                    if (addPermission) {
                        stringWriter.write("    <method>\n");
                        stringWriter.write("        <ejb-name>" + sessionInfo.getBeanName() + "</ejb-name>\n");
                        stringWriter.write("        <method-intf>Home</method-intf>\n");
                        stringWriter.write("        <method-name>" + javaMethod.getName() + "</method-name>\n");
                        stringWriter.write("        <method-params>\n");
                        JavaParam[] javaParams = javaMethod.getParams();
                        for (int j = 0; j < javaParams.length; j++) {
                            JavaParam javaParam = javaParams[j];
                            stringWriter.write("            <method-param>" +
                                    JavaUtils.getFullyQualifiedTypeName(javaParam.getType(),
                                            javaClassSourceSessionHome.getImports(),
                                            moduleCodestyle.getSourcePaths())
                                    + "</method-param>\n");

                        }
                        stringWriter.write("        </method-params>\n");
                        stringWriter.write("    </method>\n");
                    }
                }

                for (Iterator it = javaClassSourceSessionRemote.getMethods().iterator(); it.hasNext();) {
                    JavaMethod javaMethod = (JavaMethod) it.next();
                    JavaDoc.Decoration roles = new JavaDoc(javaMethod.getComments()).getDecoration("ejb", "method-permission");
                    boolean addPermission = false;
                    if (roles != null) {
                        if (roles.getParamsString().trim().equals("*")) {
                            addPermission = true;
                        } else {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(roles.getParamsString(), " \t,;"); stringTokenizer.hasMoreTokens();) {
                                String roleName = stringTokenizer.nextToken();
                                if (role.getRoleName().equals(roleName)) {
                                    addPermission = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        addPermission = true;
                    }
                    if (addPermission) {
                        stringWriter.write("    <method>\n");
                        stringWriter.write("        <ejb-name>" + sessionInfo.getBeanName() + "</ejb-name>\n");
                        stringWriter.write("        <method-intf>Remote</method-intf>\n");
                        stringWriter.write("        <method-name>" + javaMethod.getName() + "</method-name>\n");
                        stringWriter.write("        <method-params>\n");
                        JavaParam[] javaParams = javaMethod.getParams();
                        for (int j = 0; j < javaParams.length; j++) {
                            JavaParam javaParam = javaParams[j];
                            stringWriter.write("            <method-param>" +
                                    JavaUtils.getFullyQualifiedTypeName(javaParam.getType(),
                                            javaClassSourceSessionRemote.getImports(),
                                            moduleCodestyle.getSourcePaths())
                                    + "</method-param>\n");

                        }
                        stringWriter.write("        </method-params>\n");
                        stringWriter.write("    </method>\n");
                    }
                }
            }
            stringWriter.write("    </method-permission>\n");

//            for (int i = 0; i < sessionInfos.length; i++) {
//                SessionInfo si = sessionInfos[i];
//                if (si.doGenerateSession()) {
//                    stringWriter.write("    <container-transaction>\n");
//                    stringWriter.write("      <method>\n");
//                    stringWriter.write("        <ejb-name>" + si.getSessionName() + "</ejb-name>\n");
//                    stringWriter.write("        <method-name>*</method-name>\n");
//                    stringWriter.write("      </method>\n");
//                    stringWriter.write("      <trans-attribute>Required</trans-attribute>\n");
//                    stringWriter.write("    </container-transaction>\n");
//                }
//            }
        }
        // TAG <container-transaction>
        stringWriter.write("    <!-- container transactions for Entity Beans -->\n");
        for (int i = 0; i < entities.length; i++) {
            DAOInfo entityInfo = entities[i];
            stringWriter.write("    <container-transaction>\n");
            stringWriter.write("      <method>\n");
            stringWriter.write("        <ejb-name>" + entityInfo.getBeanName() + "</ejb-name>\n");
            stringWriter.write("        <method-name>*</method-name>\n");
            stringWriter.write("      </method>\n");
            stringWriter.write("      <trans-attribute>Required</trans-attribute>\n");
            stringWriter.write("    </container-transaction>\n");
        }
        stringWriter.write("    <!-- container transactions for Session Beans -->\n");
        for (int i = 0; i < sessionInfos.length; i++) {
            BOInfo si = sessionInfos[i];
            if (si.doGenerateSession()) {
                stringWriter.write("    <container-transaction>\n");
                stringWriter.write("      <method>\n");
                stringWriter.write("        <ejb-name>" + si.getSessionName() + "</ejb-name>\n");
                stringWriter.write("        <method-name>*</method-name>\n");
                stringWriter.write("      </method>\n");
                stringWriter.write("      <trans-attribute>Required</trans-attribute>\n");
                stringWriter.write("    </container-transaction>\n");
            }
        }

//            <role-name>Guests</role-name>
//            <method>
//                <ejb-name>TieRouteSession</ejb-name>
//                <method-name>create</method-name>
//            </method>

        stringWriter.write("  </assembly-descriptor>\n");
        stringWriter.write("</ejb-jar>\n");
        stringWriter.close();
        //TODO refaire la meme chose pour tous les XML
//        JBGenUtils.askFileReadOnly(new File(destFolder, "ejb-jar.xml"));
        try {
            if (JBGenUtils.write(new File(destFolder, "ejb-jar.xml"), stringWriter.getBuffer().toString(), false,getLog())) {
                getLog().info(" generating ejb-jar.xml to " + destFolder.getCanonicalPath() + "...");
            }
        } catch (FileNotFoundException e) {
            getLog().error("Readonly file : " + e);
        }
    }


    public String toString() {
        return "ejb-jar.xml 1.1 Generator";
    }
}

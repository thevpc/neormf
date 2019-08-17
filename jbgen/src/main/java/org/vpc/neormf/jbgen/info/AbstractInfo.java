package org.vpc.neormf.jbgen.info;

import org.vpc.neormf.jbgen.config.ConfigFilter;
import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpClassSource;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpMethod;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaDoc;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;
import org.vpc.neormf.jbgen.JBGenVersion;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights GPL (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 27 juil. 2004 Time: 11:27:50
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class AbstractInfo extends JBGenInfo {
    private JBGenProjectInfo projectInfo;
    private Hashtable<String,JavaClassSource> generatedClasses = new Hashtable<String,JavaClassSource>();

    public AbstractInfo(JBGenProjectInfo projectInfo, String prefix) throws IOException {
        this.projectInfo = projectInfo;
        this.config = projectInfo.createConfig(projectInfo.getConfig(), null/*prefix*/);
    }
    public abstract String getLogCatagory() ;
    protected String getMethodPatternKey(JavaClassSource source, JavaMethod method, String classType, String suffix) {
        String p =
                "<"
                        + "name=" + method.getName()
                        + ",beanName='" + getBeanName() + "'"
                        + ",className='" + source.getName() + "'"
                        + ",classType='" + classType + "'"
                        + ",classPackage='" + source.getPackage() + "'"
                        + ",upperName=" + method.getName().toUpperCase()
                        + ",returnType='" + method.getType() + "'"
                        + ",paramsCount=" + method.getParams().length;
        for (int i = 0; i < method.getParams().length; i++) {
            p += ",paramName[" + (i + 1) + "]='" + method.getParams()[i].getName() + "'";
            p += ",paramType[" + (i + 1) + "]='" + method.getParams()[i].getType() + "'";
        }
        p += ">";
        return "method." + p + "." + suffix;
    }

    protected String getMethodPatternKey(CSharpClassSource source, CSharpMethod method, String classType, String suffix) {
        String p =
                "<"
                        + "name=" + method.getName()
                        + ",beanName='" + getBeanName() + "'"
                        + ",className='" + source.getName() + "'"
                        + ",classType='" + classType + "'"
                        + ",classPackage='" + source.getPackage() + "'"
                        + ",upperName=" + method.getName().toUpperCase()
                        + ",returnType='" + method.getType() + "'"
                        + ",paramsCount=" + method.getParams().length;
        for (int i = 0; i < method.getParams().length; i++) {
            p += ",paramName[" + (i + 1) + "]='" + method.getParams()[i].getName() + "'";
            p += ",paramType[" + (i + 1) + "]='" + method.getParams()[i].getType() + "'";
        }
        p += ">";
        return "method." + p + "." + suffix;
    }

    public String[] getBeanRoles(String classType) {
        String p =
                "<"
                        + "name=*"
                        + ",beanName=" + getBeanName()
                        + ",className=*"
                        + ",classType='" + classType + "'"
                        + ",classPackage=*"
                        + ",upperName=*"
                        + ",returnType=*"
                        + ",paramsCount=*"
                        + ">";

        ConfigNode[] s = config.getNodes("method." + p + ".ejb.method-permission", false);
        TreeSet<String> list = new TreeSet<String>();
        for (ConfigNode value : s) {
            for (StringTokenizer stringTokenizer = new StringTokenizer(value.getValue(), " \n\r\t,;"); stringTokenizer.hasMoreTokens();) {
                String r = stringTokenizer.nextToken();
                list.add(r);
            }
        }
        if (list.contains("*")) {
            return new String[]{"*"};
        }
        return list.toArray(new String[list.size()]);
    }

    public String[] getBeanRolesKeys(String classType) {
        String p =
                "<"
                        + "name=*"
                        + ",beanName=" + getBeanName()
                        + ",className=*"
                        + ",classType='" + classType + "'"
                        + ",classPackage=*"
                        + ",upperName=*"
                        + ",returnType=*"
                        + ",paramsCount=*"
                        + ">";
        return ConfigNode.getAllPaths(config.getNodes("method." + p + ".ejb.method-permission", false));
    }

    public String getMethodRolesString(JavaClassSource source, JavaMethod method, String classType) {
        String[] s = getMethodRoles(source, method, classType);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(s[i]);
        }
        return sb.toString();
    }

    public String getMethodRolesString(CSharpClassSource source, CSharpMethod method, String classType) {
        String[] s = getMethodRoles(source, method, classType);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(s[i]);
        }
        return sb.toString();
    }

    public String getMethodVisibility(JavaClassSource source, JavaMethod method, String classType) {
        String v = getMethodPatternValue(source, method, classType, "visibility", "client");
        TreeSet<String> ts = new TreeSet<String>();
        ts.add("entity");
        ts.add("session");
        ts.add("client");
        if (ts.contains(v)) {
            return v;
        }
        throw new IllegalArgumentException("must use visibility from the list above : " + ts);
    }

    public String getMethodVisibility(CSharpClassSource source, CSharpMethod method, String classType) {
        String v = getMethodPatternValue(source, method, classType, "visibility", "client");
        TreeSet<String> ts = new TreeSet<String>();
        ts.add("entity");
        ts.add("session");
        ts.add("client");
        if (ts.contains(v)) {
            return v;
        }
        throw new IllegalArgumentException("must use visibility from the list above : " + ts);
    }

    public String[] getMethodRoles(JavaClassSource source, JavaMethod method, String classType) {
        ConfigNode[] s = config.getNodes(
                getMethodPatternKey(source, method, classType, J2eeTarget.MODULE_EJB + ".method-permission"),
                false);
        TreeSet<String> list = new TreeSet<String>();
        for (ConfigNode value : s) {
            for (StringTokenizer stringTokenizer = new StringTokenizer(value.getValue(), " \n\r\t,;"); stringTokenizer.hasMoreTokens();) {
                String r = stringTokenizer.nextToken();
                list.add(r);
            }
        }
        if (list.contains("*")) {
            return new String[]{"*"};
        }
        return list.toArray(new String[list.size()]);
    }

    public String[] getMethodRoles(CSharpClassSource source, CSharpMethod method, String classType) {
        ConfigNode[] s = config.getNodes(
                getMethodPatternKey(source, method, classType, J2eeTarget.MODULE_EJB + ".method-permission"),
                false);
        TreeSet<String> list = new TreeSet<String>();
        for (ConfigNode value : s) {
            for (StringTokenizer stringTokenizer = new StringTokenizer(value.getValue(), " \n\r\t,;"); stringTokenizer.hasMoreTokens();) {
                String r = stringTokenizer.nextToken();
                list.add(r);
            }
        }
        if (list.contains("*")) {
            return new String[]{"*"};
        }
        return list.toArray(new String[list.size()]);
    }

    public String[] getMethodRolesKeys(JavaClassSource source, JavaMethod method, String classType) {
        return ConfigNode.getAllPaths(config.getNodes(
                getMethodPatternKey(source, method, classType, J2eeTarget.MODULE_EJB + ".method-permission"),
                false));

    }


    public JBGenProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(JBGenProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public abstract String getBeanName();

    public String getClassName() {
        return JBGenUtils.capitalize(getBeanName());
    }

    public String getMethodPatternValue(JavaClassSource source, JavaMethod method, String classType, String suffix, String defaultValue) {
        ConfigNode[] c = getConfig().getNodes(
                getMethodPatternKey(source, method, classType, suffix)
                , false);
        if (c.length == 0) {
            return defaultValue;
        }
        return c[0].getValue();
    }

    public String getMethodPatternValue(CSharpClassSource source, CSharpMethod method, String classType, String suffix, String defaultValue) {
        ConfigNode[] c = getConfig().getNodes(
                getMethodPatternKey(source, method, classType, suffix)
                , true);
        if (c.length == 0) {
            return defaultValue;
        }
        return c[0].getValue();
    }

//    public String getLogDebugJavaCode(String type, String prepareExpression, String principalExpression, String logExpression) {
//        StringBuffer log = new StringBuffer();
//        if (generateStandardOutputLog(type)) {
//            log.append("  //START Log \n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("  System.out.println(new java.util.Date()+\" : <\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  //END Log \n");
//        } else if (generateLog4JLog(type)) {
//            log.append("  if(_log_.isDebugEnabled()){\n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("    _log_.debug(\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  }\n");
//        } else if (generateJavaLog(type)) {
//            log.append("  if(_log_.isLoggable(java.util.Level.FINEST)){\n");
//            //log.append("  if(_log_.isDebugEnabled()){\n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("    _log_.log(java.util.Level.FINEST,\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  }\n");
//        }
//        return log.toString();
//    }
//
//    public String getLogInfoJavaCode(String type, String prepareExpression, String principalExpression, String logExpression) {
//        StringBuffer log = new StringBuffer();
//        if (generateStandardOutputLog(type)) {
//            log.append("  //START Log \n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("  System.out.println(new java.util.Date()+\" : <\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  //END Log \n");
//        } else if (generateLog4JLog(type)) {
//            log.append("  if(_log_.isInfoEnabled()){\n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("    _log_.info(\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  }\n");
//        } else if (generateJavaLog(type)) {
//            log.append("  if(_log_.isLoggable(java.util.Level.INFO)){\n");
//            //log.append("  if(_log_.isDebugEnabled()){\n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("    _log_.log(java.util.Level.INFO,\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  }\n");
//        }
//        return log.toString();
//    }
//
//    public String getLogErrorJavaCode(String type, String prepareExpression, String principalExpression, String logExpression, String throwableExpression) {
//        StringBuffer log = new StringBuffer();
//        if (generateStandardOutputLog(type)) {
//            log.append("  //START Log \n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            log.append("  System.out.println(new java.util.Date()+\" : <ERROR><\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            log.append("  //END Log \n");
//        } else if (generateLog4JLog(type)) {
//            log.append("  //START Log \n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            if (throwableExpression == null) {
//                log.append("    _log_.error(\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            } else {
//                log.append("    _log_.error(\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(",").append(throwableExpression).append(");\n");
//            }
//            log.append("  //END Log \n");
//        } else if (generateJavaLog(type)) {
//            log.append("  //START Log \n");
//            log.append("  if(_log_.isLoggable(java.util.Level.SEVERE)){\n");
//            //log.append("  if(_log_.isDebugEnabled()){\n");
//            if (prepareExpression != null) {
//                log.append("  ").append(JBGenUtils.indent(prepareExpression));
//            }
//            if (throwableExpression == null) {
//                log.append("    _log_.log(java.util.Level.SEVERE,\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(");\n");
//            } else {
//                log.append("    _log_.log(java.util.Level.SEVERE,\"<\"+").append(principalExpression).append("+\">\"+").append(logExpression).append(",").append(throwableExpression).append(");\n");
//            }
//
//            log.append("  }\n");
//            log.append("  //END Log \n");
//        }
//        return log.toString();
//    }


    public String getLogOption(String option) {
        String prj = projectInfo.getValidTarget().getName();
        ConfigNode target = getConfig().getNode("target." + prj + ".log-generation", false);
        if (target == null) {
            return null;
        }
        ConfigNode[] nodes = target.getChildren(ConfigFilter.valueOf("option-log<name=\"" + option + "\">"));
        if(nodes.length>0){
            return nodes[0].getValue();
        }
        return null;
    }

    public boolean generateLog(String api, String logKey) {
        String prj = projectInfo.getValidTarget().getName();
        ConfigNode target0 = getConfig().getNode("target." + prj + ".log-generation", false);
        if (target0 == null) {
            return false;
        }
        if (!target0.isEnabledPath()
                || !target0.matchesAttributeRegexp("api", api, false))
        {
            return false;
        }

        ConfigNode target = getConfig().getNode("target." + prj + "." + logKey, false);
        if (target == null) {
            return false;
        }
        if (target.isEnabledPath() && target.getAttributeBoolean("log", true)) {
            return true;
        }
        return false;
    }

    public boolean generateLog4JLog(String logKey) {
        return generateLog("log4j", logKey);
    }
    public boolean generateJavaLog(String logKey) {
        return generateLog("javalog", logKey);
    }

    public boolean generateLog4NetLog(String logKey) {
        return generateLog("log4net", logKey);
    }

    public boolean generateStandardOutputLog(String logKey) {
        return generateLog("stdout", logKey);
    }


    public Properties getVars() {
        if (getProjectInfo().getTarget() != null) {
            addVar("ModulePackage", getProjectInfo().getModulePackage());
            addVar("TargetMetaInfFolder", getProjectInfo().getTargetMetaInfFolder());
//            addVar("TargetMetaInfFolder_data", getModuleInfo().getTargetSrcFolder(J2eeTarget.MODULE_DTO));
//            addVar("TargetMetaInfFolder_key", getModuleInfo().getTargetSrcFolder(J2eeTarget.MODULE_DTO));
        }
        return super.getVars();
    }

    public void setGeneratedClass(String id, JavaClassSource javaClassSource) {
//        System.out.println("Class Type "+this.getClass()+":"+getBeanName()+"["+id+"] Generated");
        generatedClasses.put(id, javaClassSource);
    }

    public JavaClassSource getReloadedGeneratedClass(String ... id) throws IOException,NoSuchElementException {
        for (String s : id) {
            JavaClassSource source = generatedClasses.get(s);
            if(source!=null){
                return new JavaClassSource(source.getFile());
            }
        }
        throw new NoSuchElementException("Class Type " + this.getClass() + ":" + getBeanName() + "[" + Arrays.toString(id) + "] Not yet Generated");
    }

    public boolean isGeneratedClass(String id) {
        return getGeneratedClass(id)!=null;
    }

    public JavaClassSource getGeneratedClass(String id) {
        return generatedClasses.get(id);
    }

    public boolean doExcludeMethod(JavaClassSource source, JavaMethod javaMethod, String classType, boolean defaultValue) {
        String value = getMethodPatternValue(source, javaMethod, classType, "exclude", null);
        if (value == null) {
            return defaultValue;
        }
        return JBGenUtils.parseBoolean(value);
    }

    public boolean doExcludeMethod(CSharpClassSource source, CSharpMethod javaMethod, String classType, boolean defaultValue) {
        String value = getMethodPatternValue(source, javaMethod, classType, "exclude", null);
        if (value == null) {
            return defaultValue;
        }
        return JBGenUtils.parseBoolean(value);
    }

    public boolean doReplaceMethod(JavaClassSource source, JavaMethod javaMethod, String classType, boolean defaultValue) {
        String value = getMethodPatternValue(source, javaMethod, classType, "replace", null);
        if (value == null) {
            return defaultValue;
        }
        return JBGenUtils.parseBoolean(value);
    }

    public boolean doReplaceMethod(CSharpClassSource source, CSharpMethod javaMethod, String classType, boolean defaultValue) {
        String value = getMethodPatternValue(source, javaMethod, classType, "replace", null);
        if (value == null) {
            return defaultValue;
        }
        return JBGenUtils.parseBoolean(value);
    }

    public String doReplaceWithMethod(JavaClassSource source, JavaMethod javaMethod, String classType) {
        return getMethodPatternValue(source, javaMethod, classType, "replaceWith", null);
    }

    public String doReplaceWithMethod(CSharpClassSource source, CSharpMethod javaMethod, String classType) {
        return getMethodPatternValue(source, javaMethod, classType, "replaceWith", null);
    }

    public JavaMethod renameMethod(JavaClassSource source, JavaMethod javaMethod, String classType) {
        String newName = getMethodPatternValue(source, javaMethod, classType, "renameTo", null);
        if (newName != null) {
            javaMethod.setName(newName);
        }
        return javaMethod;
    }

    public CSharpMethod renameMethod(CSharpClassSource source, CSharpMethod javaMethod, String classType) {
        String newName = getMethodPatternValue(source, javaMethod, classType, "renameTo", null);
        if (newName != null) {
            javaMethod.setName(newName);
        }
        return javaMethod;
    }

    public boolean doGenerateBean(String generationKeyName) {
        //String f;
        try {
            String prj = projectInfo.getValidTarget().getName();
            ConfigNode target = getConfig().getNode("target." + prj + "." + generationKeyName, false);
            if (target == null) {
                return false;
            }
            if(!target.isEnabledPath()){
                return false;
            }
            ConfigNode[] includes = getConfig().getNodes("target." + prj + "." + generationKeyName + ".include", false);
            ConfigNode[] excludes = getConfig().getNodes("target." + prj + "." + generationKeyName + ".exclude", false);
            boolean included = true;
            ConfigFilter fltr = new ConfigFilter("*");
            fltr.setAttribute("name", getBeanName());
            for (ConfigNode include : includes) {
                if (include.accept(fltr)) {
                    included = true;
                    break;
                }
            }
            boolean excluded = false;
            for (ConfigNode exclude : excludes) {
                if (exclude.accept(fltr)) {
                    excluded = true;
                    break;
                }
            }
            if (included && !excluded) {
                return true;
            } else {
                return false;
                //System.out.println(table.getTableName() + " skipped : included=" + included + " ; excluded=" + excluded);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getStepIntoMethodLog(JavaMethod m) {
        return null;
    }

    public String getStepIntoMethodLog(CSharpMethod m) {
        return null;
    }

    public JavaMethod declareMethod(JavaClassSource theClass,
                                    JavaMethod m,
                                    String classType) {
        String roles = getMethodRolesString(theClass, m, classType);
        if (roles.length() > 0) {
            JavaUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:method-permission " + getMethodRolesString(theClass, m, classType)));
        }
        boolean exclude = doExcludeMethod(theClass, m, classType, false);
        String replaceWith = doReplaceWithMethod(theClass, m, classType);
        boolean replace = doReplaceMethod(theClass, m, classType, (replaceWith != null));
        if ((replace || (replaceWith != null)) && exclude) {
            throw new IllegalArgumentException(getBeanName() + " : Could not exclude and replace method " + m.getName());
        }
        if (replaceWith != null && !replace) {
            throw new IllegalArgumentException(getBeanName() + " : When replaceWith is armed avoide using replace=false");
        }
        if (!exclude) {
            m = renameMethod(theClass, m, classType);
            if (replaceWith != null) {
                m.setBody(replaceWith);
                theClass.addMethod(m);
            } else if (!replace) {
                String logString = getStepIntoMethodLog(m);
                if (logString == null) {
                    logString = "";
                }
                m.setBody(logString +
                        m.getBody());
                theClass.addMethod(m);
            }
        }
        return m;
    }

    public CSharpMethod declareMethod(CSharpClassSource theClass,
                                      CSharpMethod m,
                                      String classType) {
        String roles = getMethodRolesString(theClass, m, classType);
        if (roles.length() > 0) {
            CSharpUtils.decorateMethod(m, new JavaDoc.Decoration("@ejb:method-permission " + getMethodRolesString(theClass, m, classType)));
        }
        boolean exclude = doExcludeMethod(theClass, m, classType, false);
        String replaceWith = doReplaceWithMethod(theClass, m, classType);
        boolean replace = doReplaceMethod(theClass, m, classType, (replaceWith != null));
        if ((replace || (replaceWith != null)) && exclude) {
            throw new IllegalArgumentException(getBeanName() + " : Could not exclude and replace method " + m.getName());
        }
        if (replaceWith != null && !replace) {
            throw new IllegalArgumentException(getBeanName() + " : When replaceWith is armed avoide using replace=false");
        }
        if (!exclude) {
            m = renameMethod(theClass, m, classType);
            if (replaceWith != null) {
                m.setBody(replaceWith);
                theClass.addMethod(m);
            } else if (!replace) {
                String logString = getStepIntoMethodLog(m);
                if (logString == null) {
                    logString = "";
                }
                m.setBody(logString +
                        m.getBody());
                theClass.addMethod(m);
            }
        }
        return m;
    }


    public String getJavaUserCode(String clazz, String method, String key) {
        boolean commentsIfFound = isJBGenOptionEnabled("option-user-code-comments-if-found", true);
        boolean commentsIfNotFound = isJBGenOptionEnabled("option-user-code-comments-if-not-found", true);
        String userCode = getString(key, null, false);
        if (userCode == null) {
            if (!commentsIfNotFound) {
                return "";
            }
            userCode = "// code retreived from " + key;
        }
        if (!userCode.endsWith("\n")) {
            userCode = userCode + "\n";
        }
        StringBuilder corps = new StringBuilder();
        if (commentsIfFound) {
            corps.append(JBGenUtils.indent("// START " + key + "\n"));
        }
        corps.append(JBGenUtils.indent(userCode));
        if (commentsIfFound) {
            corps.append(JBGenUtils.indent("// END   " + key + "\n"));
        }
        return corps.toString();
    }



    public TLog getLog(){
        return projectInfo.getLog();
    }

//    public String getProjectPackage() {
//            return getCodeStyleObjectName("root-package", "neormfrootpackage");
//    }
}

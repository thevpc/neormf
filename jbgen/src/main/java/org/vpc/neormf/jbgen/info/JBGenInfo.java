package org.vpc.neormf.jbgen.info;

import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.config.JBGenConfig;
import org.vpc.neormf.jbgen.JBGenVersion;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 nov. 2006
 * Time: 12:03:16
 * To change this template use File | Settings | File Templates.
 */
public class JBGenInfo {
    protected JBGenConfig config;
    private Properties vars = new Properties();

    public ConfigNode getNode(String path,boolean required){
        return config.getNode(path,required);
    }

    public ConfigNode[] getNodes(String path){
        return config.getNodes(path,false);
    }

    public String getString(String nodePath, String defaultValue,boolean required){
        return getString(nodePath, "value",defaultValue,required);
    }

    public String getString(String nodePath, String attribName, String defaultValue,boolean required,Properties vars){
        return config.getString(nodePath,attribName,required,defaultValue,vars);
    }
    public String getString(String nodePath, String attribName, String defaultValue,boolean required){
        return getString(nodePath,attribName,defaultValue,required,getVars());
    }

    public void addVar(String name, String value) {
        vars.put(name, value);
    }
    public Properties getVars() {
        return vars;
    }

    protected JBGenConfig getConfig() {
        return config;
    }

    public String getCodeStyleObjectName(String type, String defaultValue) {
        return getString("codestyle.object-name<type=\"" + type + "\">", defaultValue, false);
    }


    public boolean isJBGenLogOptionEnabled(String name, boolean defaultValue) {
        ConfigNode configNode = getConfig().getNode("options.option-log<name=\"" + name + "\">", false,false,true);
        if (configNode == null) {
            return defaultValue;
        }
        return configNode.isEnabledPath();
    }

    public boolean isJBGenOptionEnabled(String name, boolean defaultValue) {
        ConfigNode configNode = getConfig().getNode("options."+name, false,false,true);
        if (configNode == null) {
            return defaultValue;
        }
        return configNode.isEnabledPath();
    }
    public String getComments(){
        ConfigNode configNode = getConfig().getNode("options.comments", false,false,true);
        String cw=getCommentsWarning();
        String cn=getCommentsNeormf();
        if (configNode == null) {
            return cw+cn;
//            return "DO NOT EDIT MANUALLY\n"
//                    + "GENERATED AUTOMATICALLY BY JBGen (" + JBGenVersion.getMajorVersion() + ")\n"
//                    + "@author Taha BEN SALAH (taha.bensalah@gmail.com)\n" + "@framework neormf (license GPL)\n"
//                    //                +"@modification on " + new java.util.Date() + " by " + System.getProperty("user.name")
//                    ;
        }
        return cw+cn+configNode.getValue();
    }

    public String getCommentsWarning(){
        if(isJBGenOptionEnabled("option-comments-no-warnings",false)){
            return "WARNINGS : DO NOT EDIT MANUALLY\n"
                    + "GENERATED AUTOMATICALLY BY Neormf JBGen (" + JBGenVersion.getMajorVersion() + ")"
                    + "\n";
        }
        return "";
    }

    public String getCommentsNeormf(){
        if(isJBGenOptionEnabled("option-comments-no-ignore-framework",false)){
            return "@framework neormf (license GPL)" +
                   "           WEBSITE : neormf.sourceforge.net\n"+
                   "           AUTHOR : taha.bensalah@gmail.com\n";
            //                +"@modification on " + new java.util.Date() + " by " + System.getProperty("user.name")
        }
        return "";
    }
}

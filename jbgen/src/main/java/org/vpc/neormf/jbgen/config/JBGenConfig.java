package org.vpc.neormf.jbgen.config;

import java.util.Map;
import java.util.Comparator;
import java.util.Properties;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 01:24:39
 * To change this template use File | Settings | File Templates.
 */
public interface JBGenConfig {
    void setFile(File file);
    String getString(String propertyName,String defaultValue);

    int getInt(String propertyName, int defaultValue);

    long getLong(String propertyName, long defaultValue);

    boolean getBoolean(String propertyName, boolean defaultValue);

    String getString(String key, String defaultValue, boolean required, String desc, boolean emptyIsNull, Properties vars) throws NoSuchElementException;

    void load(File propertiesFile) throws IOException;

    ConfigNode[] getNodes(ConfigFilter[] path);

    public ConfigNode[] getNodes(String path, boolean reorder);
    public ConfigNode[] getNodes(String path, boolean reorder,boolean onlyEnabled);

    ConfigNode getNode(String path, boolean required);

    String getString(String nodePath, String attribName, boolean required, String defaultValue, Properties vars);
    JBGenConfig getDefaults();

    ConfigNode getNode(String nodePath, boolean required, boolean onlyEnabled, boolean checkAmbiguity);
}

package org.vpc.neormf.jbgen.config;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 20:14:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractConfig implements JBGenConfig {
    private String prefix;
    private JBGenConfig defaults;
    private File file;
    private JBGenMain jbgen;


    public void setFile(File file) {
        this.file = file;
    }

    public AbstractConfig(JBGenConfig defaults, String prefix, JBGenMain jbgen) {
        this.defaults = defaults;
        this.prefix = prefix;
        this.jbgen = jbgen;
    }

    public int getInt(String propertyName, int defaultValue) {
        String s = getString(propertyName, null);
        if (s != null && s.length() > 0) {
            return Integer.parseInt(s);
        }
        return defaultValue;
    }

    public long getLong(String propertyName, long defaultValue) {
        String s = getString(propertyName, null);
        if (s != null && s.length() > 0) {
            return Long.parseLong(s);
        }
        return defaultValue;
    }

    public boolean getBoolean(String propertyName, boolean defaultValue) {
        String s = getString(propertyName, null);
        if (s != null && s.length() > 0) {
            if ("true".equalsIgnoreCase(s)) {
                return true;
            }
            if ("false".equalsIgnoreCase(s)) {
                return false;
            }
        }
        return defaultValue;
    }

    public String getString(String key, String defaultValue, boolean required, String desc, boolean emptyIsNull, Properties vars) throws NoSuchElementException {
        String s = getString(key, null);
        desc = (desc != null && desc.length() > 0) ? " (" + desc + ")" : "";
        if (s == null || (emptyIsNull && s.length() == 0)) {
            if (required && defaultValue == null) {
                s = getString(key, null);
                throw new NoSuchElementException("Required Property missing : " + key + desc);
            }
//            getLog().info("Using default value '"+defaultValue+"' for "+key+desc);
            s = defaultValue;
        }
        if (s != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder currentVar = null;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '{': {
                        if (currentVar != null) {
                            throw new IllegalArgumentException("Unexpected '{'");
                        }
                        currentVar = new StringBuilder();
                        break;
                    }
                    case '}': {
                        if (currentVar == null) {
                            throw new IllegalArgumentException("Unexpected '}'");
                        }
                        int fctPos = currentVar.toString().indexOf(':');
                        String vname = null;
                        String vfct = null;
                        if (fctPos >= 0) {
                            vname = currentVar.toString().substring(0, fctPos);
                            vfct = currentVar.toString().substring(fctPos + 1);
                        } else {
                            vname = currentVar.toString();
                        }
                        String vval;
                        if (vars != null && vars.containsKey(vname)) {
                            vval = vars.getProperty(vname);
                        } else if (getString("variables." + vname, null) != null) {
                            vval = getString("variables." + vname, null);
                        } else {
                            throw new NoSuchElementException("Unknown VAR '" + currentVar + "'");
                        }
                        if (vval != null) {
                            if (vfct != null) {
                                if ("capital".equalsIgnoreCase(vfct)) {
                                    vval = JBGenUtils.capitalize(vval);
                                } else if ("bean".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaBean(vval);
                                } else if ("class".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaClass(vval);
                                } else if ("constant".equalsIgnoreCase(vfct)) {
                                    vval = JBGenUtils.getJavaConstant(vval);
                                } else if ("package".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaPackage(vval);
                                } else if ("var".equalsIgnoreCase(vfct)) {
                                    vval = JavaUtils.getJavaVar(vval);
                                } else if ("lower".equalsIgnoreCase(vfct)) {
                                    vval = vval.toLowerCase();
                                } else if ("upper".equalsIgnoreCase(vfct)) {
                                    vval = vval.toUpperCase();
                                } else {
                                    throw new NoSuchElementException("only the following functions ares supported : capital,bean,constant,package,var,lower,upper");
                                }
                            }
                            sb.append(vval);
                        } else {
                            sb.append("");
                        }

                        currentVar = null;
                        break;
                    }
                    default: {
                        if (currentVar != null) {
                            currentVar.append(c);
                        } else {
                            sb.append(c);
                        }
                        break;
                    }
                }
            }
            s = sb.toString();
        }

        return s;
    }

    public ConfigNode getNode(String nodePath, boolean required) {
        return getNode(nodePath, required, true,true);
    }

    public ConfigNode getNode(String nodePath, boolean required, boolean onlyEnabled, boolean checkAmbiguity) {
        ConfigNode[] nodes = getNodes(nodePath, false, onlyEnabled);
        if (nodes.length == 0) {
            if (required) {
                throw new IllegalArgumentException("Node Not found " + nodePath);
            }
            return null;
        } else if (nodes.length > 1) {
            if (checkAmbiguity) {
                int[] levels = new int[10];
                for (int i = 0; i < nodes.length; i++) {
                    int lev = nodes[i].getLevel() - 1;
                    levels[lev] = levels[lev] + 1;
                    if (levels[lev] > 1) {
                        getLog().error("Ambiguity reached for " + nodePath + "  : " + nodes[i]);
                    }
                }
            }
        }
        return nodes[0];
    }

    public String getString(String nodePath, String attribName, boolean required, String defaultValue, Properties vars) {
        ConfigNode node = getNode(nodePath, false);
        if (node == null) {
            if (required) {
                throw new IllegalArgumentException("Node Not found " + nodePath + "[" + attribName + "]");
            }
            return JBGenUtils.parseValue(defaultValue, vars);
        }
        return node.getAttribute(attribName, defaultValue, required, null, false, vars);
    }

    public JBGenConfig getDefaults() {
        return defaults;
    }


    public String getString(String propertyName, String defaultValue) {
        return getString(propertyName, "value", true, defaultValue, null);
    }

    public ConfigNode[] getNodes(String path, boolean reorder) {
        return getNodes(path, reorder, true);
    }

    public ConfigNode[] getNodes(String path, boolean reorder, boolean onlyEnabled) {
        return getNodes(ConfigFilter.valueOf(path, reorder, onlyEnabled));
    }

    private static boolean defaultShowWarnings = Boolean.getBoolean("jbgen-debug");
    private boolean showWarnings = defaultShowWarnings;

    public ConfigNode[] getNodes(ConfigFilter[] path) {
        ArrayList all = new ArrayList();
        all.addAll(Arrays.asList(getCurrentNodes(path)));
        if (defaults != null) {
            ((AbstractConfig) defaults).showWarnings = false;
            ConfigNode[] pnodes = defaults.getNodes(path);
            all.addAll(Arrays.asList(pnodes));
            if (prefix != null) {
                ArrayList x = new ArrayList();
                x.addAll(Arrays.asList(path));
                x.addAll(Arrays.asList(ConfigFilter.valueOf(prefix, false, true)));
                ConfigNode[] ppnodes = defaults.getNodes((ConfigFilter[]) x.toArray(new ConfigFilter[x.size()]));
                all.addAll(Arrays.asList(ppnodes));
            }
            ((AbstractConfig) defaults).showWarnings = defaultShowWarnings;
        }
        if (all.size() == 0) {
            if (showWarnings) {
                String s = ConfigFilter.toString(path);
                getLog().error("!!NOT FOUND!! : " + s);
//                new Throwable().printStackTrace();
            }
        }
        return (ConfigNode[]) all.toArray(new ConfigNode[all.size()]);
    }

    protected abstract ConfigNode[] getCurrentNodes(ConfigFilter[] path);

    protected TLog getLog() {
        return jbgen.getLog();
    }
}

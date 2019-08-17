package org.vpc.neormf.jbgen.config;

import org.vpc.neormf.jbgen.util.PropertiesUtils;

import java.util.*;

public class ConfigFilter {
    private String tag;
    private Hashtable attributes;
    private boolean bestFirst;
    private boolean onlyEnabled;

    private static class AttElement {
        String name;
        String value;
        boolean mandatory;

        public AttElement(String name, String value, boolean mandatory) {
            this.name = name;
            this.value = value;
            this.mandatory = mandatory;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(tag);
        if (attributes != null && attributes.size() > 0) {
            boolean first = true;
            sb.append("<");
            for (Iterator i = attributes.entrySet().iterator(); i.hasNext();) {
                Map.Entry entry = (Map.Entry) i.next();
                if (!first) {
                    sb.append(",");
                } else {
                    first = false;
                }
                AttElement elem = (AttElement) entry.getValue();
                String k = (String) entry.getKey();
                String v = elem.value;
                boolean m = elem.mandatory;
                if (k.indexOf(' ') >= 0) {
                    k = "\"" + k + "\"";
                }
                if (v.indexOf(' ') >= 0) {
                    v = "\"" + v + "\"";
                }
                sb.append(k).append(m ? "!" : "").append("=").append(v);
            }
            sb.append(">");
        }
        return sb.toString();
    }

    public static ConfigFilter[] valueOf(String path) {
        return valueOf(path, false, true);
    }

    public static String toString(ConfigFilter[] config) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < config.length; i++) {
            ConfigFilter configFilter = config[i];
            if (!first) {
                sb.append(".");
            } else {
                first = false;
            }
            sb.append(configFilter.toString());
        }
        return sb.toString();
    }

    public static ConfigFilter[] valueOf(String path, boolean reorder, boolean onlyEnabled) {
        PropertiesUtils.PropEntry[] list = new PropertiesUtils.PropList(path).getEntries();
        ConfigFilter[] f = new ConfigFilter[list.length];
        for (int i = 0; i < f.length; i++) {
            Map map = list[i].toMap();
            f[i] = new ConfigFilter(list[i].getPrefix());
            for (Iterator j = map.entrySet().iterator(); j.hasNext();) {
                Map.Entry entry = (Map.Entry) j.next();
                String k = (String) entry.getKey();
                String v = (String) entry.getValue();
                boolean mand = false;
                if (k.endsWith("!")) {
                    k = k.substring(0, k.length() - 1);
                    mand = true;
                }
                f[i].setAttribute(k, v, mand);
            }
            f[i].setBestFirst(reorder);
            f[i].setOnlyEnabled(onlyEnabled);
        }
        return f;
    }

    public ConfigFilter(String type) {
        this.tag = type;
    }

    public String getTag() {
        return tag;
    }

    public ConfigFilter setAttribute(String attrib, String value) {
        return setAttribute(attrib, value, false);
    }

    public ConfigFilter setAttribute(String attrib, String value, boolean mandatory) {
        if (value == null) {
            if (attributes != null) {
                attributes.remove(attrib);
            }
        } else {
            if (attributes == null) {
                attributes = new Hashtable();
            }
            attributes.put(attrib, new AttElement(attrib, value, mandatory));
        }
        return this;
    }

    public String getAttribute(String attrib) {
        if (attributes == null) {
            return null;
        }
        AttElement element = (AttElement) attributes.get(attrib);
        return element == null ? null : element.value;
    }

    public boolean isMandatoryAttribute(String attrib) {
        if (attributes == null) {
            return false;
        }
        AttElement element = (AttElement) attributes.get(attrib);
        return element == null ? false : element.mandatory;
    }

    public Collection getAttributes() {
        ArrayList ts = new ArrayList();
        if (attributes != null) {
            for (Iterator i = attributes.values().iterator(); i.hasNext();) {
                AttElement element = (AttElement) i.next();
                ts.add(element.name);
            }
        }
        return ts;

    }


    public boolean isBestFirst() {
        return bestFirst;
    }

    public ConfigFilter setBestFirst(boolean bestFirst) {
        this.bestFirst = bestFirst;
        return this;
    }

    public boolean isOnlyEnabled() {
        return onlyEnabled;
    }

    public void setOnlyEnabled(boolean onlyEnabled) {
        this.onlyEnabled = onlyEnabled;
    }
}

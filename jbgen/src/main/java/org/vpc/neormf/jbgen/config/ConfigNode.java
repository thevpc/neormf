package org.vpc.neormf.jbgen.config;

import org.vpc.neormf.commons.util.Utils;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class ConfigNode {
    public static final String ATTRIBUTE_VALUE="value";
    public static final String ATTRIBUTE_NAME="name";
    public static final String ATTRIBUTE_ENABLE="enable";
    public static final ConfigNode[] EMPTY = new ConfigNode[0];
    private String tag;
    private ArrayList children;
    private Hashtable attributes;
    private ConfigNode parent;
    private ConfigContext context;
    private int level;
    private File file;

    public ConfigNode(String type) {
        this.tag = type;
    }

    public static ConfigNode load(File file) throws IOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ConfigHandler handler = new ConfigHandler();
            parser.parse(file, handler);
            ConfigNode root = handler.getRoot();
            root.file=file;
            return root;
        } catch (SAXException x) {
            throw new IOException(x.toString());
        } catch (ParserConfigurationException x) {
            throw new IOException(x.toString());
        }
    }

    public void store() throws FileNotFoundException {
        store(file);
    }

    public void store(File file) throws FileNotFoundException {
        this.file=file;
        PrintStream out = null;
        try {
            out = new PrintStream(file);
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            store(out, 0);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void store(PrintStream out, int indent) {
        StringBuilder indentString = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            indentString.append(' ');
        }
        out.print(indentString);
        out.print("<");
        out.print(getTag());
        for (Iterator i = getAttributes().entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            Object k = entry.getKey();
            Object v = entry.getValue();
            if (!ATTRIBUTE_VALUE.equals(k) && !(ATTRIBUTE_ENABLE.equals(k) && "true".equals(v))) {
                out.print(" " + k);
                out.print("=\"" + v + "\"");
            }
        }
        ConfigNode[] nodes = getChildren();
        if (nodes.length == 0) {
            String v = getAttribute(ATTRIBUTE_VALUE);
            if (v == null || v.length() == 0) {
                out.println("/>");
            } else {
                out.print(">");
                out.print(getGoodXMLString(v, indentString.toString()));
                out.print("</");
                out.print(getTag());
                out.println(">");
            }
        } else {
            out.println(">");
            String v = getAttribute(ATTRIBUTE_VALUE);
            if (v != null && v.length() > 0) {
                out.print(getGoodXMLString(v, indentString.toString()));
            }
            boolean someChildren = nodes.length > 0;
            for (int i = 0; i < nodes.length; i++) {
                ConfigNode child = nodes[i];
                child.store(out, indent + 2);
            }
            if (someChildren) {
                out.print(indentString);
            }
            out.print("</");
            out.print(getTag());
            out.println(">");
        }
    }

    public boolean isLongString(String s) {
        return s.length() > 40;
    }

    public String getGoodXMLString(String s, String indent) {
        if (s.length() > 40 || s.indexOf('\n') >= 0 || s.indexOf('\r') >= 0) {
            return "\n" + indent + (isBizarreString(s) ? ("<![CDATA[" + s + "]]>") : s) + "\n" + indent;
        } else {
            return (isBizarreString(s) ? ("<![CDATA[" + s + "]]>") : s);
        }
    }

    public boolean isBizarreString(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 32 || c == '<') {
                return true;
            }
        }
        return false;
    }


    public String getPath() {
        String s = getTag();
        if (parent != null && parent.parent != null) {
            return parent.toString() + "." + s;
        }
        return s;
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
                String k = (String) entry.getKey();
                String v = (String) entry.getValue();
                if (k.indexOf(' ') >= 0) {
                    k = "\"" + k + "\"";
                }
                if (v.indexOf(' ') >= 0) {
                    v = "\"" + v + "\"";
                }
                sb.append(k).append("=").append(v);
            }
            sb.append(">");
        }

        String s = sb.toString();
        if (parent != null) {
            return parent.toString() + "." + s;
        }
        return s;
    }

    public ConfigNode add(ConfigNode n) {
        if (children == null) {
            children = new ArrayList();
        }
        n.parent = this;
        children.add(n);
        return this;
    }

    public void remove(ConfigNode n) {
        if (children != null) {
            if (children.remove(n)) {
                n.parent = null;
            }
        }
    }


    public ConfigNode getParent() {
        return parent;
    }

    public ConfigNode getChildOrCreateIt(String name) {
        ConfigNode child = getChild(name, false);
        if (child == null) {
            child = new ConfigNode(name);
            add(child);
        }
        return child;
    }

    public ConfigNode getChild(String name, boolean required) {
        ConfigNode[] nodes = getChildren(name, false, false);
        if (nodes.length == 0) {
            if (required) {
                throw new IllegalArgumentException("Not found child node " + name + " for " + this);
            }
            return null;
        }
        if (nodes.length > 1) {
            throw new IllegalArgumentException("Ambigous child node " + name + " for " + this);
        }
        return nodes[0];
    }

    public int indexOf(ConfigNode child) {
        if (children == null) {
            return -1;
        }
        return children.indexOf(child);
    }

    public ConfigNode[] getChildren() {
        if (children == null) {
            return EMPTY;
        }
        return (ConfigNode[]) children.toArray(new ConfigNode[children.size()]);
    }


    public String getValue() {
        return getAttribute(ATTRIBUTE_VALUE);
    }

    public String getName() {
        return getAttribute(ATTRIBUTE_NAME);
    }

    public String getTag() {
        return tag;
    }

    public ConfigNode setAttribute(String attrib, String value) {
        if (attributes == null) {
            attributes = new Hashtable();
        }
        if (value == null) {
            attributes.remove(attrib);
        } else {
            attributes.put(attrib, value);
        }
        return this;
    }

    public String getAttribute(String key, String defaultValue, boolean required, String desc, boolean emptyIsNull, Properties vars) {
        String s = getAttribute(key);
        desc = (desc != null && desc.length() > 0) ? " (" + desc + ")" : "";
        if (s == null || (emptyIsNull && s.length() == 0)) {
            if (required && defaultValue == null) {
                throw new NoSuchElementException("Required Property missing : " + key + desc + " for " + this);
            }
//            getLog().info("Using default value '"+defaultValue+"' for "+key+desc);
            s = defaultValue;
        }
        return JBGenUtils.parseValue(s, vars);
    }

    public String getAttribute(String attrib, String defaultValue) {
        String v = getAttribute(attrib);
        if (v == null) {
            return defaultValue;
        }
        return v;
    }

    public double getAttributeDouble(String attrib, double val) {
        String d = getAttribute(attrib);
        return (d == null || d.length() == 0) ? val : Double.parseDouble(d);
    }

    public int getAttributeInt(String attrib, int val) {
        String d = getAttribute(attrib);
        return (d == null || d.length() == 0) ? val : Integer.parseInt(d);
    }

    public boolean matchesAttributeRegexp(String attrib, String value, boolean val) {
        String dosLikePattern = getAttribute(attrib);
        if (dosLikePattern == null) {
            return val;
        }
        if (value == null) {
            return val;
        }
        return Utils.extendedMatching(dosLikePattern, value, true);
    }

    public boolean matchesCaseInsensitiveAttributeRegexp(String attrib, String value, boolean val) {
        String dosLikePattern = getAttribute(attrib);
        if (dosLikePattern == null) {
            return val;
        }
        if (value == null) {
            return val;
        }
        return Utils.extendedMatching(dosLikePattern, value, false);
    }

    public boolean getAttributeBoolean(String attrib, boolean val) {
        String d = getAttribute(attrib);
        return (d == null || d.length() == 0) ? val : Boolean.valueOf(d).booleanValue();
    }

    public String getAttribute(String attrib) {
        if (attributes == null) {
            return null;
        }
        return (String) attributes.get(attrib);
    }

    public Map getAttributes() {
        return attributes == null ? new Hashtable() : Collections.unmodifiableMap(attributes);
    }


    /**
     * <machin name="*.dos">
     * <machin name="regexp://*.dos">
     *
     * @param propName
     * @param propValue
     * @return
     */
    public boolean acceptAttribute(String propName, String propValue) {
        return bimatching(propValue, getAttribute(propName), false) != 0;
//        String n = getAttribute(propName);
//        return Utils.extendedMatching(n,propValue, false);
    }

    public int bimatching(String a, String b, boolean caseSensitive) {
        if (Utils.extendedMatching(a, b, false)) {
            return 1;
        }
        if (Utils.extendedMatching(b, a, false)) {
            return -1;
        }
        return 0;
    }

    public boolean accept(ConfigFilter filter) {
        if (filter.isOnlyEnabled() && !isEnabledPath()) {
            return false;
        }
        if (bimatching(getTag(), filter.getTag(), false) == 0) {
            return false;
        }
        if (attributes != null) {
            for (Iterator i = attributes.entrySet().iterator(); i.hasNext();) {
                Map.Entry entry = (Map.Entry) i.next();
                String k = (String) entry.getKey();
                String valueFilter = filter.getAttribute(k);
                if (valueFilter != null && !acceptAttribute(k, valueFilter)) {
                    return false;
                }
            }
            for (Iterator i = filter.getAttributes().iterator(); i.hasNext();) {
                String fk = (String) i.next();
                if(filter.isMandatoryAttribute(fk) && !attributes.containsKey(fk)){
                    return false;
                }
            }

        }
        return true;
    }

    public ConfigNode[] getChildren(ConfigFilter[] filter) {
        if (filter.length == 0) {
            return EMPTY;
        } else if (filter.length == 1) {
            return getChildren(filter[0]);
        } else {
            ConfigFilter[] filter2 = new ConfigFilter[filter.length - 1];
            System.arraycopy(filter, 1, filter2, 0, filter2.length);
            ConfigNode[] nodes = getChildren(filter[0]);
//            if(nodes.length==0){
//                TODO !!
//                System.err.println("!! "+this.getPath()+"."+filter[0].getTag()+" not found");
//                System.err.print("");
//            }
            ArrayList all = new ArrayList();
            for (int i = 0; i < nodes.length; i++) {
                all.addAll(Arrays.asList(nodes[i].getChildren(filter2)));
            }
            return (ConfigNode[]) all.toArray(new ConfigNode[all.size()]);
        }
    }

    public ConfigNode getChild(int index) {
        return children == null ? null : (ConfigNode) children.get(index);
    }

    public int size() {
        return children == null ? 0 : children.size();
    }

    public ConfigNode[] getChildren(String filter) {
        return getChildren(ConfigFilter.valueOf(filter, false, true));
    }

    public ConfigNode[] getChildren(String filter, boolean reorder, boolean onlyEnabled) {
        return getChildren(ConfigFilter.valueOf(filter, reorder, onlyEnabled));
    }

    public boolean isEnabled() {
        return Boolean.valueOf(getAttribute(ATTRIBUTE_ENABLE, "true"));
    }

    public boolean isEnabledPath() {
        ConfigNode par = getParent();
        return isEnabled() && (par == null || par.isEnabled());
    }

    public ConfigNode[] getChildren(ConfigFilter filter) {
        ArrayList all = new ArrayList();
        if (children != null) {
            for (Iterator i = children.iterator(); i.hasNext();) {
                ConfigNode configNode = (ConfigNode) i.next();
                if (configNode.accept(filter)) {
                    all.add(configNode);
                }
            }
        }
        if (filter.isBestFirst()) {
            Collections.sort(all, new Comparator() {
                public int compare(Object o1, Object o2) {
                    ConfigNode a1 = (ConfigNode) o1;
                    ConfigNode a2 = (ConfigNode) o2;
                    TreeSet ts = new TreeSet();
                    if (a1.attributes != null) {
                        ts.addAll(a1.attributes.keySet());
                    }
                    if (a2.attributes != null) {
                        ts.addAll(a2.attributes.keySet());
                    }
                    for (Iterator i = ts.iterator(); i.hasNext();) {
                        String k = (String) i.next();
                        String s1 = a1.getAttribute(k);
                        String s2 = a1.getAttribute(k);
                        if (s1 != null && s2 == null) {
                            return 1;
                        } else if (s1 == null && s2 != null) {
                            return -1;
                        } else if (s1 != null /*&& s2!=null*/) {//surely s1!=null && s2!=null
                            int t1 = s1.startsWith("regexp:") ? 3 : s1.startsWith("exp://") ? 2 : s1.startsWith("value://") ? 1 : (s1.indexOf('*') >= 0 || s1.indexOf('?') >= 0) ? 2 : 1;
                            int t2 = s2.startsWith("regexp:") ? 3 : s2.startsWith("exp://") ? 2 : s2.startsWith("value://") ? 1 : (s2.indexOf('*') >= 0 || s2.indexOf('?') >= 0) ? 2 : 1;
                            if (t1 > t2) {
                                return 1;
                            } else if (t1 < t2) {
                                return -1;
                            } else {
                                int x = s1.compareTo(s2);
                                if (x != 0) {
                                    return x;
                                }
                            }
                        }
                    }
                    return 0;
                }
            });
        }
        return (ConfigNode[]) all.toArray(new ConfigNode[all.size()]);
    }


    public ConfigContext getContext() {
        return context;
    }

    public void setContext(ConfigContext context) {
        this.context = context;
    }

    public static String[] getAllPaths(ConfigNode[] nodes) {
        String[] all = new String[nodes.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = nodes[i].getPath();
        }
        return all;
    }

    public static String[] getAllTags(ConfigNode[] nodes) {
        String[] all = new String[nodes.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = nodes[i].getTag();
        }
        return all;
    }

    public static String[] getAllNames(ConfigNode[] nodes) {
        String[] all = new String[nodes.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = nodes[i].getName();
        }
        return all;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name){
        setAttribute(ATTRIBUTE_NAME,name);
    }

    public void setValue(String value){
        setAttribute(ATTRIBUTE_VALUE,value);
    }

    public void setEnabled(boolean value){
        setAttribute(ATTRIBUTE_ENABLE,value?"true":"false");
    }

    public void remove(){
        if(parent!=null){
            parent.remove(this);
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

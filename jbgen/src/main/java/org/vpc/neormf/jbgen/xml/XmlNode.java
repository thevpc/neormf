package org.vpc.neormf.jbgen.xml;

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

public class XmlNode {
    public static final XmlNode[] EMPTY = new XmlNode[0];
    private String tag;
    private ArrayList children;
    private Hashtable attributes;
    private XmlNode parent;
    private int level;
    private File file;
    private String text;

    public XmlNode(String type) {
        this.tag = type;
    }

    public static XmlNode load(File file) throws IOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(false);
            factory.setXIncludeAware(false);
            //factory.setFeature("http://xml.org/sax/features/namespaces", false);
            //factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            //factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            //factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SAXParser parser = factory.newSAXParser();
            XmlHandler handler = new XmlHandler();
            parser.parse(file, handler);
            XmlNode root = handler.getRoot();
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
        store(file, null);
    }
    public void store(File file,String header) throws FileNotFoundException {
        this.file=file;
        PrintStream out = null;
        try {
            out = new PrintStream(file);
            if(header==null){
                header="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            }
            out.println(header);
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
                out.print(" " + k);
                out.print("=\"" + v + "\"");
        }
        XmlNode[] nodes = getChildren();
        if (nodes.length == 0) {
            String v = getText();
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
            String v = getText();
            if (v != null && v.length() > 0) {
                out.print(getGoodXMLString(v, indentString.toString()));
            }
            boolean someChildren = nodes.length > 0;
            for (int i = 0; i < nodes.length; i++) {
                XmlNode child = nodes[i];
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
        if (s.length() > 80 || s.indexOf('\n') >= 0 || s.indexOf('\r') >= 0) {
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

    @Override
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

    public XmlNode add(XmlNode n) {
        if (children == null) {
            children = new ArrayList();
        }
        n.parent = this;
        children.add(n);
        return this;
    }

    public void remove(XmlNode n) {
        if (children != null) {
            if (children.remove(n)) {
                n.parent = null;
            }
        }
    }


    public XmlNode getParent() {
        return parent;
    }

    public XmlNode getChildOrCreateIt(String name) {
        XmlNode child = getChild(name, false);
        if (child == null) {
            child = new XmlNode(name);
            add(child);
        }
        return child;
    }

    public XmlNode getChild(String name, boolean required) {
        XmlNode[] nodes = getChildren(name, false, false);
        if (nodes.length == 0) {
            if (required) {
                throw new IllegalArgumentException("Not found child node " + name + " for " + this);
            }
            return null;
        }
        if (nodes.length > 1) {
            throw new IllegalArgumentException("Ambogous child node " + name + " for " + this);
        }
        return nodes[0];
    }

    public int indexOf(XmlNode child) {
        if (children == null) {
            return -1;
        }
        return children.indexOf(child);
    }

    public XmlNode[] getChildren() {
        if (children == null) {
            return EMPTY;
        }
        return (XmlNode[]) children.toArray(new XmlNode[children.size()]);
    }


    public String getTag() {
        return tag;
    }

    public XmlNode setAttribute(String attrib, String value) {
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

    public boolean accept(XmlFilter filter) {
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

    public XmlNode[] getChildren(XmlFilter[] filter) {
        if (filter.length == 0) {
            return EMPTY;
        } else if (filter.length == 1) {
            return getChildren(filter[0]);
        } else {
            XmlFilter[] filter2 = new XmlFilter[filter.length - 1];
            System.arraycopy(filter, 1, filter2, 0, filter2.length);
            XmlNode[] nodes = getChildren(filter[0]);
//            if(nodes.length==0){
//                TODO !!
//                System.err.println("!! "+this.getPath()+"."+filter[0].getTag()+" not found");
//                System.err.print("");
//            }
            ArrayList all = new ArrayList();
            for (int i = 0; i < nodes.length; i++) {
                all.addAll(Arrays.asList(nodes[i].getChildren(filter2)));
            }
            return (XmlNode[]) all.toArray(new XmlNode[all.size()]);
        }
    }

    public XmlNode getChild(int index) {
        return children == null ? null : (XmlNode) children.get(index);
    }

    public int size() {
        return children == null ? 0 : children.size();
    }

    public XmlNode[] getChildren(String filter) {
        return getChildren(XmlFilter.valueOf(filter, false, true));
    }

    public XmlNode[] getChildren(String filter, boolean reorder, boolean onlyEnabled) {
        return getChildren(XmlFilter.valueOf(filter, reorder, onlyEnabled));
    }

    public XmlNode[] getChildren(XmlFilter filter) {
        ArrayList all = new ArrayList();
        if (children != null) {
            for (Iterator i = children.iterator(); i.hasNext();) {
                XmlNode configNode = (XmlNode) i.next();
                if (configNode.accept(filter)) {
                    all.add(configNode);
                }
            }
        }
        if (filter.isBestFirst()) {
            Collections.sort(all, new Comparator() {
                public int compare(Object o1, Object o2) {
                    XmlNode a1 = (XmlNode) o1;
                    XmlNode a2 = (XmlNode) o2;
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
        return (XmlNode[]) all.toArray(new XmlNode[all.size()]);
    }


    public static String[] getAllPaths(XmlNode[] nodes) {
        String[] all = new String[nodes.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = nodes[i].getPath();
        }
        return all;
    }

    public static String[] getAllTags(XmlNode[] nodes) {
        String[] all = new String[nodes.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = nodes[i].getTag();
        }
        return all;
    }

    public static String[] getAllNames(XmlNode[] nodes) {
        String[] all = new String[nodes.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = nodes[i].getAttribute("name", null);
        }
        return all;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public XmlNode setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }
    
}

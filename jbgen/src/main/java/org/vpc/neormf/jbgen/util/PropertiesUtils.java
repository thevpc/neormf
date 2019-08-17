package org.vpc.neormf.jbgen.util;

import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 2 juil. 2004 Time: 10:25:42
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class PropertiesUtils {

    public static Object getValueForBestPropertyKeyMatching(Map map, String key, final Comparator propertyVarNamesComparator) {
        String bestKey = getBestPropertyKeyMatching(map, key, propertyVarNamesComparator);
        return
                bestKey == null ? null :
                (
                (map instanceof Properties) ?
                ((Properties) map).getProperty(bestKey)
                :
                map.get(bestKey)
                );
    }

    public static String getBestPropertyKeyMatching(Map map, String key, final Comparator propertyVarNamesComparator) {
//        if("field.<nam\:*Name>.search".equals(key)){
//            System.out.println("?");
//        }
        String[] s = getAllPropertyKeysMatching(map, key, propertyVarNamesComparator);
        if (s.length == 0) {
            return null;
        }
        return s[0];
    }

    public static String[] getAllPropertyKeysMatching(Map map, String key, final Comparator propertyVarNamesComparator) {
        ArrayList al = new ArrayList();
        if (map instanceof Properties) {
            for (Enumeration e = ((Properties) map).propertyNames(); e.hasMoreElements();) {
                String s = (String) e.nextElement();
                if (isPropertyMatching(key, s)) {
                    al.add(s);
                }
            }
        } else {
            for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                String s = (String) i.next();
                if (isPropertyMatching(key, s)) {
                    al.add(s);
                }
            }
        }
        String[] s = (String[]) al.toArray(new String[al.size()]);
        Arrays.sort(s, new Comparator() {
            public int compare(Object o1, Object o2) {
                return comparePropertyPrecedence((String) o1, (String) o2, propertyVarNamesComparator);
            }
        });
        return s;
    }

    /**
     * [toto.<name=titi,type=tata>.taftaf].matches(toto.<type=tit*>.<name=taftaf>)
     */
    public static boolean isPropertyMatching(String property, String propertyPattern) {


        PropList p1 = new PropList(property);
        PropList p2 = new PropList(propertyPattern);
        return p1.matches(p2);
    }

    public static int comparePropertyPrecedence(String property1, String property2) {
        return comparePropertyPrecedence(property1, property2, null);
    }

    /**
     * returns
     *
     * @param property1
     * @param property2
     * @param propertyVarNamesOrder
     * @return +1 if property1 is stricter than property2; -1 if property2 is stricter than property1 ; else 0
     */
    public static int comparePropertyPrecedence(String property1, String property2, Comparator propertyVarNamesOrder) {
        PropList p1 = new PropList(property1);
        PropList p2 = new PropList(property2);
        return p1.compareTo(p2, propertyVarNamesOrder);
    }

    public static class PropEntry {
        LinkedHashMap props;
        String prefix="";
        String suffix="";

        public String getPrefix() {
            return prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public Map toMap(){
            return new LinkedHashMap(props);
        }
        public PropEntry(String element) {
            props = new LinkedHashMap();
            if (element.indexOf('<')>=0 && element.indexOf('>')>element.indexOf('<')) {
                if (element.indexOf('<')>0) {
                    prefix=element.substring(0,element.indexOf('<'));
                }
                if (element.indexOf('>')>0) {
                    suffix=element.substring(element.indexOf('>')+1);
                }
                element = element.substring(prefix.length()+1, element.length()-suffix.length() - 1);

                Splitter splitter = new Splitter();
                splitter.setDelemeters(",=");
                splitter.setReturnDelemeters(true);
                splitter.setParentheses(new Splitter.Parenthese[0]);
                splitter.setCotes(new Splitter.Cote[]{
                    new Splitter.Cote('\'', '\'', '\\'),
                    new Splitter.Cote('\"', '\"', '\\'),
                });
                Splitter.Token[] tokens = splitter.split(element);
                String name = null;
                final int EXPECT_NAME = 0;
                final int EXPECT_EQ = 1;
                final int EXPECT_VALUE = 2;
                final int EXPECT_DELEMETER = 3;
                int status = EXPECT_NAME;
                for (int i = 0; i < tokens.length; i++) {
                    Splitter.Token token = tokens[i];
                    switch (status) {
                        case EXPECT_NAME:
                            {
                                if (token instanceof Splitter.Delimiter) {
                                    throw new IllegalArgumentException("Expected name");
                                }
                                name = token.getBody();
                                status = EXPECT_EQ;
                                break;
                            }
                        case EXPECT_EQ:
                            {
                                if (!(token instanceof Splitter.Delimiter && token.getBody().equals("="))) {
                                    throw new IllegalArgumentException("Expected '='");
                                }
                                status = EXPECT_VALUE;
                                break;
                            }
                        case EXPECT_VALUE:
                            {
                                if (token instanceof Splitter.Delimiter) {
                                    throw new IllegalArgumentException("Expected value");
                                }
                                props.put(name, token.getBody());
                                status = EXPECT_DELEMETER;
                                break;
                            }
                        case EXPECT_DELEMETER:
                            {
                                if (!(token instanceof Splitter.Delimiter && token.getBody().equals(","))) {
                                    throw new IllegalArgumentException("Expected ','");
                                }
                                status = EXPECT_NAME;
                                break;
                            }
                    }
                }
                if (status != EXPECT_DELEMETER && status != EXPECT_NAME) {
                    throw new IllegalArgumentException("Expected ','");
                }
//                for (StringTokenizer st1 = new StringTokenizer(element, ","); st1.hasMoreTokens();) {
//                    String s1 = st1.nextToken();
//                    String name = null;
//                    String value = null;
//                    for (StringTokenizer st2 = new StringTokenizer(s1, "="); st2.hasMoreTokens();) {
//                        String s2 = st2.nextToken();
//                        if (name == null) {
//                            name = s2;
//                        } else if (value == null) {
//                            value = s2;
//                        } else {
//                            throw new IllegalArgumentException("expected <varName=varValue,...>");
//                        }
//                    }
//                    if (name == null && value == null) {
//                        throw new IllegalArgumentException("expected <varName=varValue,...>");
//                    }
//                    if (value == null) {
//                        value = name;
//                        name = "name";
//                    }
//                    props.put(name, value);
//                }
            } else {
               prefix=element;
            }
        }

        public boolean matches(PropEntry other) {
            for (Iterator i = other.props.entrySet().iterator(); i.hasNext();) {
                Map.Entry entry = (Map.Entry) i.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                if (props.containsKey(key)) {
                    String sValue = (String) props.get(key);
                    if (!sValue.equals(value) && !JBGenUtils.stringMatches(sValue, value)) {
                        return false;
                    }
                } else {
                    for (Iterator j = props.entrySet().iterator(); j.hasNext();) {
                        Map.Entry sEntry = (Map.Entry) j.next();
                        String sKey = (String) sEntry.getKey();
                        String sValue = (String) sEntry.getValue();
                        if (!JBGenUtils.stringMatches(sKey, key) || !JBGenUtils.stringMatches(sValue, value)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
//        public boolean matches(PropEntry other) {
//            for (Iterator i = other.props.entrySet().iterator(); i.hasNext();) {
//                Map.Entry entry = (Map.Entry) i.next();
//                String key = (String) entry.getKey();
//                String value = (String) entry.getValue();
//                if (props.containsKey(key)) {
//                    String sValue = (String) props.get(key);
//                    if (sValue.equals(value) || JBGenUtils.stringMatches(sValue, value)) {
//                        return true;
//                    }
//                } else {
//                    for (Iterator j = props.entrySet().iterator(); j.hasNext();) {
//                        Map.Entry sEntry = (Map.Entry) j.next();
//                        String sKey = (String) sEntry.getKey();
//                        String sValue = (String) sEntry.getValue();
//                        if (JBGenUtils.stringMatches(sKey, key) && JBGenUtils.stringMatches(sValue, value)) {
//                            return true;
//                        }
//                    }
//                }
//            }
//            return false;
//        }

        public int compareTo(PropEntry o, Comparator propertyVarNamesOrder) {
            String[] s1 = (String[]) props.keySet().toArray(new String[0]);
            String[] s2 = (String[]) o.props.keySet().toArray(new String[0]);
            if (propertyVarNamesOrder == null) {
                propertyVarNamesOrder = new Comparator() {
                    public int compare(Object o1, Object o2) {
                        return ((String) o1).compareTo((String)o2);
                    }
                };
            }
            Arrays.sort(s1, propertyVarNamesOrder);
            Arrays.sort(s2, propertyVarNamesOrder);
            for (int i = 0; i < Math.min(s1.length, s2.length); i++) {
                int x = s1[i].compareTo(s2[i]);
                if (x != 0) {
                    return x > 0 ? 1 : -1;
                }
            }
            return 0;
        }
    }

    static int comprareStrings(String str1, String str2) {
        return str1.compareTo(str2); //normalement un regexp est > e une valeur
    }

    public static class PropList {
        PropEntry[] entries;

        public PropList(String pattern) {
            ArrayList list = new ArrayList();
            Splitter splitter = new Splitter();
            splitter.setDelemeters(".");
            splitter.setCotes(new Splitter.Cote[]{new Splitter.Cote('<', '>', '\\')});
            Splitter.Token[] tokens = splitter.split(pattern);
            for (int i = 0; i < tokens.length; i++) {
                Splitter.Token token = tokens[i];
                list.add(new PropEntry(token.getOriginalBody()));
            }
            entries = (PropEntry[]) list.toArray(new PropEntry[list.size()]);
        }

        public PropEntry[] getEntries() {
            return (PropEntry[]) entries.clone();
        }

        public boolean matches(PropList other) {
            if (other.entries.length != entries.length) {
                return false;
            }
            for (int i = 0; i < entries.length; i++) {
                PropEntry propEntry = entries[i];
                if (!propEntry.matches(other.entries[i])) {
                    return false;
                }
            }
            return true;
        }

        public int compareTo(PropList o, Comparator propertyVarNamesComparator) {
            if (o.entries.length != entries.length) {
                int x = o.entries.length - entries.length;
                return x > 0 ? 1 : -1;
            }
            for (int i = 0; i < entries.length; i++) {
                int x = entries[i].compareTo(o.entries[i], propertyVarNamesComparator);
                if (x != 0) {
                    return x;
                }
            }
            return 0;
        }

    }
}

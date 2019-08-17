package org.vpc.neormf.jbgen.util;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

/**
 * Simple class parser for strings like the following :
 * <PRE>
 * method1(param11,param12),method2(param21,param22),...
 * </PRE>
 * Using state machine :
 * <PRE>
 * 0 : END  --> X
 * 0 : name --> 1
 * 1 : '('  --> 2
 * 2 : name --> 3
 * 3 : ','  --> 2
 * 3 : ')'  --> 4
 * 4 : ','  --> 0
 * 4 : END  --> X
 * </PRE>
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 22 avr. 2004 Time: 19:17:19
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class MethodDescList {
    private LinkedHashMap methods;
    private String extraWordChars;
    private String pattern;

    private void parse() {
        if (pattern == null) {
            methods = new LinkedHashMap();
            return;
        }
        try {
            int status = 0;
            methods = new LinkedHashMap();
            StreamTokenizer st = new StreamTokenizer(new StringReader(pattern));
            st.resetSyntax();
            st.wordChars('a', 'z');
            st.wordChars('A', 'Z');
            st.wordChars('_', '_');
            st.wordChars(128 + 32, 255);
            st.wordChars('0', '9');
            if (extraWordChars != null) {
                for (int i = 0; i < extraWordChars.length(); i++) {
                    int c = extraWordChars.charAt(i);
                    st.wordChars(c, c);
                }
            }
            st.whitespaceChars(0, ' ');
//            st.commentChar('/');
//            st.quoteChar('"');
//            st.quoteChar('\'');
//            st.parseNumbers();
            String currentGroupName = null;
            ArrayList currentGroupValues = null;
            while (true) {
                int token = st.nextToken();
                switch (status) {
                    case 0:
                        {
                            if (token == StreamTokenizer.TT_EOF) {
                                return;
                            } else if (token == StreamTokenizer.TT_WORD) {
                                currentGroupName = st.sval;
                                currentGroupValues = new ArrayList();
                                status = 1;
                                break;
                            }
                            throw new IllegalArgumentException("Expected WORD but got " + st);
                        }
                    case 1:
                        {
                            if (token == '(') {
                                status = 2;
                                break;
                            }
                            throw new IllegalArgumentException("Expected ')' but got " + st);
                        }
                    case 2:
                        {
                            if (token == StreamTokenizer.TT_WORD) {
                                currentGroupValues.add(st.sval);
                                status = 3;
                                break;
                            } else if (token == ')') {
                                methods.put(currentGroupName,
                                        new MethodDesc(currentGroupName,
                                                (String[]) currentGroupValues.toArray(new String[currentGroupValues.size()])));
                                currentGroupName = null;
                                currentGroupValues = null;
                                status = 4;
                                break;
                            }
                            throw new IllegalArgumentException("Expected WORD but got " + st);
                        }
                    case 3:
                        {
                            if (token == ',') {
                                status = 2;
                                break;
                            } else if (token == ')') {
                                methods.put(currentGroupName,
                                        new MethodDesc(currentGroupName,
                                                (String[]) currentGroupValues.toArray(new String[currentGroupValues.size()])));
                                currentGroupName = null;
                                currentGroupValues = null;
                                status = 4;
                                break;
                            }
                            throw new IllegalArgumentException("Expected ',' or ')' but got " + st);
                        }
                    case 4:
                        {
                            if (token == ',') {
                                status = 0;
                                break;
                            } else if (token == StreamTokenizer.TT_EOF) {
                                return;
                            }
                            throw new IllegalArgumentException("Expected ',' but got " + st);
                        }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MethodDescList(String pattern, String extraWordChars) {
        this.pattern = pattern;
        setExtraWordChars(extraWordChars);
        parse();
    }

    public MethodDescList(String pattern) {
        this.pattern = pattern;
        parse();
    }

    public String getPattern() {
        return pattern;
    }

    public MethodDesc[] getMethods() {
        Collection c = methods.values();
        return (MethodDesc[]) c.toArray(new MethodDesc[c.size()]);
    }

    public MethodDesc getMethodDesc(String name) {
        return (MethodDesc) methods.get(name);
    }

    public int length() {
        return methods.size();
    }


    public MethodDesc getMethodByParam(String param, int startIndex, int endIndex) {
        for (Iterator i = methods.values().iterator(); i.hasNext();) {
            MethodDesc m = (MethodDesc) i.next();
            String[] params = m.getParams();
            int startIndex2 = Math.max(0, startIndex);
            int endIndex2 = Math.max(params.length, endIndex);
            for (int j = startIndex2; j < endIndex2; j++) {
                if (params[j].equals(param)) {
                    return m;
                }
            }
        }
        return null;
    }

    public MethodDesc getMethodByParamIgnoreCase(String param, int startIndex, int endIndex) {
        for (Iterator i = methods.values().iterator(); i.hasNext();) {
            MethodDesc m = (MethodDesc) i.next();
            String[] params = m.getParams();
            startIndex = Math.max(0, startIndex);
            endIndex = Math.max(0, Math.min(params.length, endIndex));
            for (int j = 0; j < endIndex; j++) {
                if (params[j].equalsIgnoreCase(param)) {
                    return m;
                }
            }
        }
        return null;
    }

    public String getExtraWordChars() {
        return extraWordChars;
    }

    public void setExtraWordChars(String extraWordChars) {
        this.extraWordChars = extraWordChars;
    }

    public static class MethodDesc {
        private String name;
        private String[] params;

        public MethodDesc(String name, String[] params) {
            this.name = name;
            this.params = params;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getParams() {
            return params;
        }

        public void setParams(String[] params) {
            this.params = params;
        }
    }
}

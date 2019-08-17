package org.vpc.neormf.jbgen.util;


import org.vpc.neormf.commons.util.Utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Simple class parser for strings like the following :
 * <PRE>
 * method1(param11,param12)=someValue,method2(param21,param22)=someValue,...
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
public class ParamsList {
    private ArrayList arrayList = new ArrayList();
    private String pattern;
    private String separators = " \t";
    private boolean whiteSeparators = true;

    private boolean acceptNullValue = true;
    private boolean acceptNonNullValue = true;

    private boolean acceptMethod = true;
    private boolean acceptAttribute = true;

    private boolean acceptMethodParamNullValue = true;
    private boolean acceptMethodParamNonNullValue = true;
    private boolean acceptMethodParamAsMethod = false;
    private boolean acceptMethodParamAsAttribute = true;

    private boolean acceptMethodNullParam = true;
    private boolean parsed = false;
    private char openParChar = '(';
    private char closeParChar = ')';

    public ParamsList(String pattern) {
        this.pattern = pattern;
        setSeparators(separators);
    }

//    public ParamsList(String pattern,String separators,boolean allowNullValue,boolean parseMethodParam,boolean allowNullParam) {
//        this.pattern = pattern;
//        this.acceptNullValue=allowNullValue;
//        this.acceptMethod=parseMethodParam;
//        this.acceptMethodNullParam=allowNullParam;
//        setSeparators(separators);
//    }


    public boolean isWhiteSeparators() {
        return whiteSeparators;
    }

    public boolean isAcceptNullValue() {
        return acceptNullValue;
    }

    public void setAcceptNullValue(boolean acceptNullValue) {
        this.parsed = false;
        this.acceptNullValue = acceptNullValue;
    }

    public boolean isAcceptMethod() {
        return acceptMethod;
    }

    public void setAcceptMethod(boolean acceptMethod) {
        this.parsed = false;
        this.acceptMethod = acceptMethod;
    }

    public boolean isAcceptMethodNullParam() {
        return acceptMethodNullParam;
    }

    public void setAcceptMethodNullParam(boolean acceptMethodNullParam) {
        this.parsed = false;
        this.acceptMethodNullParam = acceptMethodNullParam;
    }

    public String getSeparators() {
        return separators;
    }

    public void setParentheses(String parentheses) {
        this.parsed=false;
        this.openParChar = parentheses.charAt(0);
        this.closeParChar = parentheses.charAt(1);
        this.parsed = false;
    }

    public void setSeparators(String separators) {
        this.parsed = false;
        this.separators = separators;
        if (this.separators == null || separators.length() == 0) {
            this.separators = " ";
        }
        whiteSeparators = false;
        for (int i = 0; i < this.separators.length(); i++) {
            if (Character.isWhitespace(this.separators.charAt(i))) {
                whiteSeparators = true;
            }
        }
    }

    private void parse() {
        if (parsed) {
            return;
        }
        if (pattern == null) {
            return;
        }
        final int EXPECT_VAR_NAME = 0;
        final int EXPECT_VAR_VALUE = 1;
        final int EXPECT_EQUAL = 2;
        final int EXPECT_SEP = 3;
        int status = EXPECT_VAR_NAME;
        StringBuilder current = null;
        String currentKey = null;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            boolean someSeparators = false;
            while (i < pattern.length() && Character.isWhitespace(c = pattern.charAt(i))) {
                i++;
                someSeparators = true;
            }
            if (i >= pattern.length()) {
                break;
            }
            switch (status) {
                case EXPECT_VAR_NAME:
                case EXPECT_VAR_VALUE:
                    {
                        if (current == null) {
                            current = new StringBuilder();
                        }
                        if (c == '\"' || c == '\'') {
                            char cote = c;
                            i++;
                            switch_01:
                            while (i < pattern.length()) {
                                c = pattern.charAt(i);
                                if (c == '\\') {
                                    i++;
                                    c = pattern.charAt(i);
                                    switch (c) {
                                        case 'n':
                                            {
                                                current.append("\n");
                                                break;
                                            }
                                        case 't':
                                            {
                                                current.append("\t");
                                                break;
                                            }
                                        case 'f':
                                            {
                                                current.append("\f");
                                                break;
                                            }
                                        case '\'':
                                            {
                                                current.append("'");
                                                break;
                                            }
                                        case '"':
                                            {
                                                current.append("\"");
                                                break;
                                            }
                                        default:
                                            {
                                                throw new IllegalArgumentException("bad escape char " + c);
                                            }
                                    }
                                } else if (c == cote) {
                                    i--;
                                    break switch_01;
                                } else {
                                    current.append(c);
                                }
                            }
                        } else {
                            current.append(c);
                            int openPar = 0;
                            i++;
                            while (
                                    i < pattern.length()
                                    &&
                                    (
                                    (
                                    !Character.isWhitespace(c = pattern.charAt(i))
                                    && separators.indexOf(c) < 0
                                    && c != '='
                                    )
                                    || openPar > 0
                                    )
                                    ) {
                                if (c == openParChar) {
                                    openPar++;
                                } else if (c == closeParChar) {
                                    openPar--;
                                }
                                current.append(c);
                                i++;
                            }
                            i--;
                        }
                        if (status == EXPECT_VAR_NAME) {
                            currentKey = current.toString();
                            current = null;
                            status = EXPECT_EQUAL;
                        } else {
                            arrayList.add(createParam(currentKey, current.toString()));
                            current = null;
                            status = EXPECT_SEP;
                        }
                        break;
                    }
                case EXPECT_SEP:
                    {
                        if (whiteSeparators) {
                            i--;
                        } else {
                            if (separators.indexOf(c) < 0) {
                                throw new IllegalArgumentException("Expected one of '" + separators + "' and got " + c + " at " + i);
                            }
                        }
                        status = EXPECT_VAR_NAME;
                        break;
                    }
                case EXPECT_EQUAL:
                    {
                        if (c != '=') {
                            if (!acceptNullValue) {
                                throw new IllegalArgumentException("Expected '='");
                            }
                            i--;
                            arrayList.add(createParam(currentKey, null));
                            current = null;
                            status = EXPECT_SEP;
                        } else {
                            status = EXPECT_VAR_VALUE;
                        }
                        break;
                    }
                default:
                    {
                        throw new IllegalArgumentException("Unknow state " + status);
                    }
            }
        }
        switch (status) {
            case EXPECT_VAR_NAME:
                {
                    throw new IllegalArgumentException("unexpected string termination : was looking for a var name");
                }
            case EXPECT_VAR_VALUE:
                {
                    throw new IllegalArgumentException("unexpected string termination : was looking for a var value");
                }
            case EXPECT_EQUAL:
                {
                    if (acceptNullValue) {
                        arrayList.add(createParam(currentKey, null));
                        current = null;
                        status = EXPECT_SEP;
                        break;
                    } else {
                        throw new IllegalArgumentException("unexpected string termination : was looking for a '=' token");
                    }
                }
            case EXPECT_SEP:
                {
                    //okkay
                    break;
                }
            default:
                {
                    throw new IllegalArgumentException("Unknown status " + status);
                }
        }
        parsed = true;
    }


    public String getPattern() {
        return pattern;
    }

    public Param[] getParams() {
        parse();
        return (Param[]) arrayList.toArray(new Param[arrayList.size()]);
    }

    public String toString() {
        return Utils.dump(getParams());
    }

    public Param getParam(String name) {
        parse();
        for (Iterator i = arrayList.iterator(); i.hasNext();) {
            Param param = (Param) i.next();
            if (param.getName().equals(name)) {
                return param;
            }
        }
        return null;
    }

    public int length() {
        parse();
        return arrayList.size();
    }

    private Param createParam(String name, String value) {
        Param p = new Param(name, value, arrayList.size()
                , acceptNullValue
                , acceptNonNullValue
                , acceptMethodParamAsAttribute
                , acceptMethodParamAsMethod
                , openParChar
                , closeParChar
        );
        return p;
    }

    public void setPattern(String pattern) {
        this.parsed = false;
        this.pattern = pattern;
    }

    public boolean isAcceptNonNullValue() {
        return acceptNonNullValue;
    }

    public void setAcceptNonNullValue(boolean acceptNonNullValue) {
        this.parsed = false;
        this.acceptNonNullValue = acceptNonNullValue;
    }

    public boolean isAcceptAttribute() {
        return acceptAttribute;
    }

    public void setAcceptAttribute(boolean acceptAttribute) {
        this.parsed = false;
        this.acceptAttribute = acceptAttribute;
    }

    public boolean isAcceptMethodParamNullValue() {
        return acceptMethodParamNullValue;
    }

    public void setAcceptMethodParamNullValue(boolean acceptMethodParamNullValue) {
        this.parsed = false;
        this.acceptMethodParamNullValue = acceptMethodParamNullValue;
    }

    public boolean isAcceptMethodParamNonNullValue() {
        return acceptMethodParamNonNullValue;
    }

    public void setAcceptMethodParamNonNullValue(boolean acceptMethodParamNonNullValue) {
        this.parsed = false;
        this.acceptMethodParamNonNullValue = acceptMethodParamNonNullValue;
    }

    public boolean isAcceptMethodParamAsMethod() {
        return acceptMethodParamAsMethod;
    }

    public void setAcceptMethodParamAsMethod(boolean acceptMethodParamAsMethod) {
        this.parsed = false;
        this.acceptMethodParamAsMethod = acceptMethodParamAsMethod;
    }

    public boolean isAcceptMethodParamAsAttribute() {
        return acceptMethodParamAsAttribute;
    }

    public void setAcceptMethodParamAsAttribute(boolean acceptMethodParamAsAttribute) {
        this.parsed = false;
        this.acceptMethodParamAsAttribute = acceptMethodParamAsAttribute;
    }

    public static class Param {
        private String name;
        private String value;
        private int position;
        private ParamsList nameParams;
        private char openParChar;
        private char closeParChar;


        public Param(String name, String value, int position
                     , boolean acceptNullValue
                     , boolean acceptNonNullValue
                     , boolean acceptAsAttribute
                     , boolean acceptAsMethod
                     , char openParChar
                     , char closeParChar
        ) {
            this.openParChar=openParChar;
            this.closeParChar=closeParChar;
            this.name = name;
            this.value = value;
            if (this.value == null && !acceptNullValue) {
                throw new IllegalArgumentException("Expected a non null value for " + name);
            }
            if (this.value != null && !acceptNonNullValue) {
                throw new IllegalArgumentException("Expected a null value for " + name);
            }
            this.position = position;
            if (acceptAsMethod) {
                int i = name.indexOf(openParChar);
                if (i >= 0) {
                    if (name.charAt(name.length() - 1) == closeParChar) {
                        this.name = name.substring(0, i);
                        this.nameParams = new ParamsList(name.substring(i + 1, name.length() - 1));
                        this.nameParams.setSeparators(",");
                        this.nameParams.setAcceptAttribute(acceptAsAttribute);
                        this.nameParams.setAcceptMethod(acceptAsMethod);
                        this.nameParams.setAcceptMethodParamAsAttribute(acceptAsAttribute);
                        this.nameParams.setAcceptMethodParamAsMethod(acceptAsMethod);
                        this.nameParams.setAcceptMethodParamNonNullValue(acceptNonNullValue);
                        this.nameParams.setAcceptMethodParamNullValue(acceptNullValue);
                        this.nameParams.setAcceptNonNullValue(acceptNonNullValue);
                        this.nameParams.setAcceptNullValue(acceptNullValue);
                    }
                }
                if (!acceptAsAttribute && this.nameParams == null) {
                    throw new IllegalArgumentException("Expected method a method for " + name);
                }
            } else if (!acceptAsAttribute) {
                throw new IllegalArgumentException("One or both of acceptAsMethod and acceptAsAttribute sould be true" + name);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public ParamsList getNameParams() {
            return nameParams;
        }

        public int getPosition() {
            return position;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            boolean dumpName = false;
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (!Character.isJavaIdentifierPart(c)) {
                    dumpName = true;
                    break;
                }
            }
            if (dumpName) {
                sb.append(Utils.dump(name));
            } else {
                sb.append(name);
            }
            if (nameParams != null) {
                sb.append(openParChar);
                String s = nameParams.toString();
                sb.append(s.substring(1, s.length() - 1));
                sb.append(closeParChar);
            }
            if (value != null) {
                sb.append("=");
                sb.append(Utils.dump(value));
            }
            return sb.toString();
        }
    }

}

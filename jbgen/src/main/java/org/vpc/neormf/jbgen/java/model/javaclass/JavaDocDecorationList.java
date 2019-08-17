package org.vpc.neormf.jbgen.java.model.javaclass;

import org.vpc.neormf.jbgen.util.ParamsList;
import org.vpc.neormf.commons.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 7 juil. 2004 Time: 15:24:50
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JavaDocDecorationList {
    Decoration[] decorations = null;

    public JavaDocDecorationList(String comments) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(comments));
        String line = null;
        try {
//            String cachedLine=null;
//            while((line=((cachedLine!=null)? cachedLine : bufferedReader.readLine()))!=null){
            ArrayList list = new ArrayList();
            while ((line = bufferedReader.readLine()) != null) {
                String trimmedLine = line.trim();
//                cachedLine=null;
                if (trimmedLine.startsWith("@")) {
                    Decoration d = new Decoration(line);
                    StringBuilder sb = new StringBuilder(line);
                    if (d.getParamsString() == null) {
                        while ((line = bufferedReader.readLine()) != null && line.length() > 0) {
                            sb.append("\n").append(line);
                        }
                        d = new Decoration(sb.toString());
                    }
                    list.add(d);
                }
            }
            decorations = (Decoration[]) list.toArray(new Decoration[list.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decorations.length; i++) {
            Decoration decoration = decorations[i];
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(decoration.toString());
        }
        return sb.toString();
    }

    public static class Decoration {
        private String type;
        private String name;
        ParamsList paramsList = null;
        String paramsString = null;


        public Decoration(String comments) {
            comments = comments.trim();
            if (!comments.startsWith("@")) {
                throw new IllegalArgumentException("bad comments");
            }
            int i = comments.indexOf(' ');
            paramsString = null;
            if (i <= 0) {
                type = comments.substring(1);
            } else {
                type = comments.substring(1, i).trim();
                paramsString = comments.substring(i + 1).trim();
            }
            i = type.indexOf(':');
            if (i > 0) {
                String t = type;
                type = t.substring(0, i).trim();
                name = t.substring(i + 1);
            }
            if (paramsString != null && paramsString.length() == 0) {
                paramsString = null;
            }
        }

        public void parseParamsList() {
            if (paramsList == null) {
                paramsList = new ParamsList(paramsString);
                paramsList.setSeparators(" ,");
                paramsList.setAcceptAttribute(true);
                paramsList.setAcceptMethod(false);
                paramsList.setAcceptNonNullValue(true);
                paramsList.setAcceptNullValue(true);
//                ," ,",true,false,false
            }
        }

        public ParamsList getParamsList() {
            parseParamsList();
            return paramsList;
        }

        public String toString() {
            if (paramsList != null) {
                String s = paramsList.toString();
                s = s.substring(1, s.length() - 1);
                return type + (name != null ? (":" + name) : "") + " " + s;
            } else {
                return type + (name != null ? (":" + name) : "") + (paramsString == null ? "" : (" " + paramsString));
            }
        }

        public String getParamsString() {
            return paramsString;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

}

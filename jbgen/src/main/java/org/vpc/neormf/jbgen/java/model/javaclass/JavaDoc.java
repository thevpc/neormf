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
public class JavaDoc {
    private DocItem[] docItems = null;

    public Decoration getDecorationByName(String name) {
        return getDecorationByName(name, 0);
    }

    public Decoration getDecoration(String type, String name) {
        return getDecoration(type, name, 0);
    }

    public ParamsList.Param getParam(String decorationType, String decorationName, String paramName) {
        Decoration d = getDecoration(decorationType, decorationName);
        if (d != null) {
            return d.getParamsList().getParam(paramName);
        }
        return null;
    }

    public Decoration getDecorationByName(String name, int startIndex) {
        Decoration[] d = getDecorations();
        for (int i = startIndex; i < d.length; i++) {
            Decoration decoration = d[i];
            if (Utils.equals(decoration.getName(), name)) {
                return decoration;
            }
        }
        return null;
    }

    public int indexOfDecorationByName(String name, int startIndex) {
        for (int i = startIndex; i < docItems.length; i++) {
            if (!(docItems[i] instanceof Decoration)) {
                continue;
            }
            Decoration decoration = (Decoration) docItems[i];
            if (Utils.equals(decoration.getName(), name)) {
                return i;
            }
        }
        return -1;
    }

    public Decoration getDecoration(String type, String name, int index) {
        Decoration[] d = getDecorations();
        for (int i = index; i < d.length; i++) {
            Decoration decoration = d[i];
            if (Utils.equals(decoration.getName(), name) && Utils.equals(decoration.getType(), type)) {
                return decoration;
            }
        }
        return null;
    }

    public int indexOfDecoration(String type, String name, int startIndex) {
        for (int i = startIndex; i < docItems.length; i++) {
            if (!(docItems[i] instanceof Decoration)) {
                continue;
            }
            Decoration decoration = (Decoration) docItems[i];
            if (Utils.equals(decoration.getName(), name) && Utils.equals(decoration.getType(), type)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(DocItem docItem, int startIndex) {
        for (int i = startIndex; i < docItems.length; i++) {
            if (Utils.equals(docItem, docItems[i])) {
                return i;
            }
        }
        return -1;
    }

    public Decoration getDecorationByType(String type) {
        return getDecorationByType(type, 0);
    }

    public Decoration getDecorationByType(String type, int startIndex) {
        Decoration[] d = getDecorations();
        for (int i = startIndex; i < d.length; i++) {
            Decoration decoration = d[i];
            if (Utils.equals(decoration.getType(), type)) {
                return decoration;
            }
        }
        return null;
    }

    public int indexOfDecorationByType(String type, int startIndex) {
        for (int i = startIndex; i < docItems.length; i++) {
            if (!(docItems[i] instanceof Decoration)) {
                continue;
            }
            Decoration decoration = (Decoration) docItems[i];
            if (Utils.equals(decoration.getType(), type)) {
                return i;
            }
        }
        return -1;
    }


    public JavaDoc(String comments) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(comments == null ? "" : comments));
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
                } else {
                    list.add(new PlainDoc(trimmedLine));
                }
            }
            docItems = (DocItem[]) list.toArray(new DocItem[list.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < docItems.length; i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(docItems[i].toString());
        }
        return sb.toString();
    }

    public DocItem[] getDocItems() {
        return docItems;
    }

    public Decoration[] getDecorations() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < docItems.length; i++) {
            DocItem docItem = docItems[i];
            if (docItem instanceof Decoration) {
                arrayList.add(docItem);
            }
        }
        return (Decoration[]) arrayList.toArray(new Decoration[arrayList.size()]);
    }

    public DocItem removeDocItem(int pos) {
        ArrayList list = new ArrayList();
        DocItem found = null;
        for (int i = 0; i < docItems.length; i++) {
            if (i != pos) {
                list.add(docItems[i]);
            } else {
                found = docItems[i];
            }
        }
        if (found != null) {
            docItems = (DocItem[]) list.toArray(new DocItem[list.size()]);
        }
        return found;
    }

    public boolean removeDocItem(DocItem docItem) {
        if (docItem == null) {
            return false;
        }
        ArrayList list = new ArrayList();
        boolean found = false;
        for (int i = 0; i < docItems.length; i++) {
            if (!docItem.equals(docItems[i])) {
                list.add(docItems[i]);
            } else {
                found = true;
            }
        }
        if (found) {
            docItems = (DocItem[]) list.toArray(new DocItem[list.size()]);
        }
        return found;
    }

    public boolean addDocItem(DocItem docItem) {
        if (docItem instanceof PlainDoc) {
            return addDocItem((PlainDoc) docItem);
        } else {
            return addDocItem((Decoration) docItem);
        }
    }

    public boolean addDocItem(PlainDoc doc) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < docItems.length; i++) {
            list.add(docItems[i]);
        }
        list.add(doc);
        docItems = (DocItem[]) list.toArray(new DocItem[list.size()]);
        return true;
    }

    /**
     * return true if added
     *
     * @param decoration
     * @return true if added
     */
    public boolean addDocItem(Decoration decoration) {
        if (decoration == null) {
            return false;
        }
        ArrayList list = new ArrayList();
        boolean found = false;
        for (int i = 0; i < docItems.length; i++) {
            if (decoration.equals(docItems[i])) {
                found = true;
                break;
            }
            list.add(docItems[i]);
        }
        if (!found) {
            list.add(decoration);
            docItems = (DocItem[]) list.toArray(new DocItem[list.size()]);
        }
        return !found;
    }

    public static class PlainDoc extends DocItem {
        String doc;

        public PlainDoc(String doc) {
            this.doc = doc;
            if (doc == null) {
                doc = "";
            }
        }

        public String getDoc() {
            return doc;
        }

        public String toString() {
            return doc;
        }

        public boolean equals(Object obj) {
            return (obj instanceof PlainDoc) && toString().equals(obj.toString());
        }
    }

    public static abstract class DocItem {
        private DocItem() {
        }
    }

    public static class Decoration extends DocItem {
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
                paramsList.setAcceptNullValue(true);
                paramsList.setAcceptNonNullValue(true);
                paramsList.setAcceptAttribute(true);
                paramsList.setAcceptMethod(false);
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
                return "@" + type + (name != null ? (":" + name) : "") + " " + s;
            } else {
                return "@" + type + (name != null ? (":" + name) : "") + (paramsString == null ? "" : (" " + paramsString));
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

        public boolean equals(Object obj) {
            return (obj instanceof Decoration) && toString().equals(obj.toString());
        }
    }

}

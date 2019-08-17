package org.vpc.neormf.jbgen.csharp.model.csharpclass;

import org.vpc.neormf.jbgen.java.util.JavaUtils;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 14:42:27
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class CSharpParam implements Cloneable {
    private String comments;
    private String name;
    private String type;
    private boolean isFinal;

    public CSharpParam(String name, String type, String comments) {
        this(name, type, comments, false);
    }

    public CSharpParam(String name, String type, String comments, boolean isFinal) {
        this.setName(name);
        this.setType(type);
        this.setComments(comments);
        this.isFinal = isFinal;
    }

    public String getSourceCode() {
        StringBuilder out = new StringBuilder();
        if (getComments() != null) {
            out.append(JavaUtils.toJavaComments(getComments()));
            out.append("\n");
        }
        if (isFinal) {
            out.append("final ");
        }
        out.append(getType());
        out.append(" ");
        out.append(getName());
        return out.toString();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String toCommaSeparatedString(CSharpParam[] javaParams) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < javaParams.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(javaParams[i].getName());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CSharpParam) {
            CSharpParam p = (CSharpParam) obj;
            return name.equals(p.name) && type.equals(p.type);
        }
        return false;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

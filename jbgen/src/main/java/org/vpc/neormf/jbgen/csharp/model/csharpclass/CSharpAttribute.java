package org.vpc.neormf.jbgen.csharp.model.csharpclass;

import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 14:40:51
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

public class CSharpAttribute implements Cloneable {
    private String name;
    private String comments;
    private String modifiers;
    private String type;
    private StringBuilder bodyGet;
    private StringBuilder bodySet;

    private HashMap userProperties = new HashMap();

    public CSharpAttribute() {
    }

    public CSharpAttribute(String name, String type, String modifiers, String comments, String bodyGet, String bodySet) {
        this(name, type, modifiers, comments, bodyGet==null?null:new StringBuilder(bodyGet), bodySet==null?null:new StringBuilder(bodySet));
    }
    public CSharpAttribute(String name, String type, String modifiers, String comments, StringBuilder bodyGet, StringBuilder bodySet) {
        this.setModifiers(modifiers);
        this.setType(type);
        this.setName(name);
        this.setComments(comments);
        this.bodyGet = bodyGet;
        this.bodySet = bodySet;
    }

    public String getSourceCode() {
        StringBuilder sb = new StringBuilder();

        if (getComments() != null) {
            sb.append(JavaUtils.toJavaComments(getComments()));
        }
        if (getModifiers() != null) {
            sb.append(getModifiers());
            sb.append(" ");
        }
        if (getType() != null) {
            sb.append(getType());
            sb.append(" ");
        }
        sb.append(getName());
        sb.append("{\n");
        if (bodyGet != null) {
            sb.append("get{\n");
            sb.append(JBGenUtils.indent(bodyGet.toString(), 2, false, true));
            sb.append("\n}\n");
        }
        if (bodyGet != null) {
            sb.append("set{\n");
            sb.append(JBGenUtils.indent(bodySet.toString(), 2, false, true));
            sb.append("\n}\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public Set userProperties() {
        return userProperties.keySet();
    }

    public boolean containsUserProperty(String key) {
        return userProperties.containsKey(key);
    }

    public Object getUserProperty(String key) {
        return userProperties.get(key);
    }

    public void setUserProperty(String key, Object value) {
        userProperties.put(key, value);
    }

    public void removeUserProperty(String key) {
        userProperties.remove(key);
    }

    public void setBodyGet(String body) {
        this.bodyGet = (bodyGet == null) ? null : new StringBuilder(body);
    }

    public String getBodyGet() {
        return this.bodyGet.toString();
    }

    public void setBodySet(String body) {
        this.bodySet = (bodySet == null) ? null : new StringBuilder(body);
    }

    public String getBodySet() {
        return this.bodySet.toString();
    }

    public Object clone() {
        try {
            CSharpAttribute m = (CSharpAttribute) super.clone();
            m.bodyGet = bodyGet == null ? null : new StringBuilder(bodyGet.toString());
            m.bodySet = bodySet == null ? null : new StringBuilder(bodySet.toString());
            return m;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return getSourceCode();
    }
    
}

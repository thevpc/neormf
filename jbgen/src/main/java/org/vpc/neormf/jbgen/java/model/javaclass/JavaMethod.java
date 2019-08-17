package org.vpc.neormf.jbgen.java.model.javaclass;

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

public class JavaMethod implements Cloneable {
    private String name;
    private String comments;
    private String modifiers;
    private Vector exceptions = new Vector();
    private String type;
    private JavaParam[] params = new JavaParam[0];
    private StringBuilder body;

    private HashMap userProperties = new HashMap();

    public JavaMethod() {
    }

    public JavaMethod(String name, String type, JavaParam[] params, String modifiers, String[] exceptions, String comments) {
        this(name, type, params, modifiers, exceptions, comments, (StringBuilder) null);
    }

    public JavaMethod(String name, String type, JavaParam[] params, String modifiers, String[] exceptions, String comments, String body) {
        this(name, type, params, modifiers, exceptions, comments, body == null ? (StringBuilder) null : new StringBuilder(body));
    }

    public JavaMethod(String name, String type, JavaParam[] params, String modifiers, String[] exceptions, String comments, StringBuilder body) {
        this.setModifiers(modifiers);
        this.setType(type);
        this.setName(name);
        this.setParams(params);
        this.setComments(comments);
        this.body = body;
        if (exceptions != null) {
            this.getExceptions().addAll(Arrays.asList(exceptions));
        }
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
            sb.append(JavaUtils.getNormalizedTypeName(getType()));
            sb.append(" ");
        }
        sb.append(getName());
        sb.append("(");
        if (getParams() != null) {
            for (int i = 0; i < getParams().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(getParams()[i].getSourceCode());
            }
        }
        sb.append(")");
        for (int i = 0; i < getExceptions().size(); i++) {
            if (i == 0) {
                sb.append(" throws ");
            } else {
                sb.append(" , ");
            }
            sb.append(getExceptions().get(i));
        }
        if (body != null) {
            sb.append("{\n");
            sb.append(JBGenUtils.indent(body.toString(), 2, false, true));
            sb.append("\n}\n");
        } else {
            sb.append(";");
        }
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

    public void removeException(String exception) {
        getExceptions().remove(exception);
    }

    public void addException(String exception) {
        if (!getExceptions().contains(exception)) {
            getExceptions().add(exception);
        }
    }

    public void removeAllExceptions() {
        exceptions.clear();
    }

    public void setBody(String body) {
        this.body = (body == null) ? null : new StringBuilder(body);
    }

    public String getBody() {
        return this.body.toString();
    }

    public Object clone() {
        try {
            JavaMethod m = (JavaMethod) super.clone();
            m.exceptions = exceptions == null ? null : ((Vector) exceptions.clone());
            if (params == null) {
                m.params = null;
            } else {
                m.params = new JavaParam[params.length];
                for (int i = 0; i < params.length; i++) {
                    m.params[i] = (JavaParam) params[i].clone();
                }
            }
            m.body = body == null ? null : new StringBuilder(body.toString());
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

    public JavaParam[] getParams() {
        return params;
    }

    public void setParams(JavaParam[] params) {
        if (params == null) {
            params = new JavaParam[0];
        }
        this.params = params;
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

    public Vector getExceptions() {
        return exceptions;
    }

    public void setExceptions(Vector exceptions) {
        this.exceptions = exceptions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addParam(JavaParam javaParam, int position) {
        ArrayList list = new ArrayList();
        if (params != null) {
            list.addAll(Arrays.asList(params));
        }
        list.add(position, javaParam);
        params = (JavaParam[]) list.toArray(new JavaParam[list.size()]);
    }

    public void addParam(JavaParam javaParam) {
        ArrayList list = new ArrayList();
        if (params != null) {
            list.addAll(Arrays.asList(params));
        }
        list.add(javaParam);
        params = (JavaParam[]) list.toArray(new JavaParam[list.size()]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (getModifiers() != null) {
            sb.append(getModifiers());
            sb.append(" ");
        }
        if (getType() != null) {
            sb.append(getType());
            sb.append(" ");
        }
        sb.append(getName());
        sb.append("(");
        if (getParams() != null) {
            for (int i = 0; i < getParams().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(getParams()[i].getSourceCode());
            }
        }
        sb.append(")");
        for (int i = 0; i < getExceptions().size(); i++) {
            if (i == 0) {
                sb.append(" throws ");
            } else {
                sb.append(" , ");
            }
            sb.append(getExceptions().get(i));
        }
        return sb.toString();
    }
}

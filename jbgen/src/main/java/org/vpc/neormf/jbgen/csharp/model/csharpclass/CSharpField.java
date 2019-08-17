package org.vpc.neormf.jbgen.csharp.model.csharpclass;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 14:42:27
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

public class CSharpField extends JavaParam implements Cloneable {
    private String modifiers;
    private String defaultValue;

    public CSharpField(String name, DataType type, String comments, String modifiers, String defaultValue) {
        this(name,
                CSharpUtils.getCSharpClassName(type.toJavaType())
                , comments, modifiers, defaultValue
        );
    }

    public CSharpField(String name, String type, String comments, String modifiers, String defaultValue) {
        super(name, type, comments);
        this.modifiers = modifiers;
        this.defaultValue = defaultValue;
    }


    public String getSourceCode() {
        StringBuilder out = new StringBuilder();
        if (getComments() != null) {
            out.append(JavaUtils.toJavaComments(getComments()));
            out.append("\n");
        }
        if (modifiers != null) {
            out.append(modifiers);
            out.append(" ");
        }
        out.append(getType());
        out.append(" ");
        out.append(getName());
        if (defaultValue != null) {
            out.append("=");
            out.append(defaultValue);
        }
        out.append(";");
        return out.toString();
    }

    public boolean isPrimitive() {
        if ("int".equals(getType())) {
            return true;
        } else if ("long".equals(getType())) {
            return true;

        } else if ("float".equals(getType())) {
            return true;

        } else if ("double".equals(getType())) {
            return true;

        } else if ("char".equals(getType())) {
            return true;

        } else if ("byte".equals(getType())) {
            return true;

        } else if ("bool".equals(getType())) {
            return true;

        } else if ("DateTime".equals(getType())) {
            return true;

        } else if ("TimeSpan".equals(getType())) {
            return true;

        } else {
            return false;
        }
    }

    public void addDefaultInitialization() {
        if ("int".equals(getType())) {
            defaultValue = "0";
        } else if ("long".equals(getType())) {
            defaultValue = "0L";

        } else if ("float".equals(getType())) {
            defaultValue = "0F";

        } else if ("double".equals(getType())) {
            defaultValue = "0D";

        } else if ("char".equals(getType())) {
            defaultValue = "\'\\0'";

        } else if ("byte".equals(getType())) {
            defaultValue = "0";

        } else if ("bool".equals(getType())) {
            defaultValue = "false";

        } else if ("DateTime".equals(getType())) {
            /**
             * TODO
             * TODO workaround car la valeur DateTime.MinValue ne peut pas
             * TODO etre inseree dans la base
             * TODO L'ideal c'est de ne plus inserer par defaut tous les camps
             *
             **/
            defaultValue = "DateTime.Now";

        } else if ("TimeSpan".equals(getType())) {
            defaultValue = "TimeSpan.Now";

        } else {
            defaultValue = "null";
        }
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}

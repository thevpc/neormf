package org.vpc.neormf.jbgen.java.types;

import org.vpc.neormf.commons.types.DataType;


/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:26:31
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public final class AnyTypeDelegate extends DataType {
    public static final int TYPE = -1;
    private String javaClassName;

    public AnyTypeDelegate() {
        this("java.lang.Object",true);
    }
    
    public AnyTypeDelegate(String javaClass, boolean nullable) {
        super(nullable);
        this.javaClassName = javaClass;
    }

    public Class toJavaType() {
        return Object.class;
    }

    public int getTypeId() {
        return TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AnyTypeDelegate that = (AnyTypeDelegate) o;

        if (javaClassName != null ? !javaClassName.equals(that.javaClassName) : that.javaClassName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (javaClassName != null ? javaClassName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"<"+javaClassName+">";
    }

    public String getJavaClassName() {
        return javaClassName;
    }
    
    
    
}

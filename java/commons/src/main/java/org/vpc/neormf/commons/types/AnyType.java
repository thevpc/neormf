package org.vpc.neormf.commons.types;


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
public final class AnyType extends DataType {
    public static final int TYPE = -1;
    private Class javaClass;

    public AnyType() {
        this(Object.class,true);
    }
    
    public AnyType(Class javaClass, boolean nullable) {
        super(nullable);
        this.javaClass = javaClass;
    }

    public Class toJavaType() {
        return javaClass;
    }

    public int getTypeId() {
        return TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AnyType anyType = (AnyType) o;

        if (javaClass != null ? !javaClass.equals(anyType.javaClass) : anyType.javaClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (javaClass != null ? javaClass.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"<"+javaClass.getSimpleName()+">";
    }
    
    
}

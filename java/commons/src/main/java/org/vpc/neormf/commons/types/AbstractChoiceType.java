package org.vpc.neormf.commons.types;

import java.util.List;

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
public abstract class AbstractChoiceType extends DataType {
    public static final int TYPE = -2;
    private DataType elementType;

    public AbstractChoiceType(boolean nullable, DataType elementType) {
        super(nullable);
        this.elementType = elementType;
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        return elementType.validateValue(object, fieldName);
    }

    public Class toJavaType() {
        return elementType.toJavaType();
    }


    public DataType getElementType() {
        return elementType;
    }


    /**
     *
     * @return list of Element
     */
    public abstract List elements();

    public abstract String getKeyRenderer(Object key);

    public int getTypeId() {
        return TYPE;
    }

    public static class Element{
        Object key;
        String renderer;

        public Element(Object key, String value) {
            this.key = key;
            this.renderer = value;
        }

        public Object getKey() {
            return key;
        }

        public String getRenderer() {
            return renderer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractChoiceType that = (AbstractChoiceType) o;

        if (elementType != null ? !elementType.equals(that.elementType) : that.elementType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (elementType != null ? elementType.hashCode() : 0);
        return result;
    }
}

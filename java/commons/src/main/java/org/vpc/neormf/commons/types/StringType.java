package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.util.Utils;

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
public class StringType extends DataType {
    public static final int TYPE = 10;
    private int minLength = 0;
    private int maxLength = 256;
    private boolean multiline = false;

    public StringType() {
        this(true);
    }
    
    public StringType(boolean nullable) {
        super(nullable);
    }
    public StringType(boolean nullable, int minLength, int maxLength,boolean multiline) {
        super(nullable);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.multiline=multiline;
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        String s = (String) Utils.castTo(object, String.class);
        if (s != null && s.length() == 0) {
            s = null;
        }
        s = (String) super.validateValue(s, fieldName);
        if (s == null) {
            return null;
        }
        if (this.minLength > 0 && s.length() < this.minLength) {
            throw new DataException("org.vpc.neormf.commons.types.StringType.TooShort.Message", new Object[]{fieldName});
        }
        if (this.maxLength > 0 && s.length() > this.maxLength) {
            throw new DataException("org.vpc.neormf.commons.types.StringType.TooLong.Message", new Object[]{fieldName});
        }
        return s;
    }

    public Class toJavaType() {
        return String.class;
    }

    public int getTypeId() {
        return TYPE;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean isMultiline() {
        return multiline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StringType that = (StringType) o;

        if (maxLength != that.maxLength) return false;
        if (minLength != that.minLength) return false;
        if (multiline != that.multiline) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + minLength;
        result = 31 * result + maxLength;
        result = 31 * result + (multiline ? 1 : 0);
        return result;
    }
}

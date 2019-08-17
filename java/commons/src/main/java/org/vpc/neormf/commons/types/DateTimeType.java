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
public class DateTimeType extends DataType {
    public static final DateTimeType DATETIME_NULLABLE = new DateTimeType(true);
    public static final DateTimeType DATETIME_NON_NULLABLE= new DateTimeType(false);
    public static final int TYPE = 13;
    private long min = 0;
    private long max = 0;

    public DateTimeType() {
        this(true);
    }
    public DateTimeType(boolean nullable) {
        super(nullable);
    }

    public DateTimeType(boolean nullable, java.sql.Timestamp min, java.sql.Timestamp max) {
        super(nullable);
        this.min = min == null ? 0 : validate(min.getTime());
        this.max = max == null ? 0 : validate(max.getTime());
    }

    protected long validate(long date) {
        return date;
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object == null) {
            return null;
        }
        java.util.Date s = (java.util.Date) Utils.castTo(object, java.util.Date.class);
        long l = validate(s.getTime());
        if (this.min > 0 && l < this.min) {
            throw new DataException("org.vpc.neormf.commons.types.DateTimeType.TooLow.Message", new Object[]{fieldName});
        }
        if (this.max > 0 && l > this.max) {
            throw new DataException("org.vpc.neormf.commons.types.DateTimeType.TooLow.Message", new Object[]{fieldName});
        }
        return new java.sql.Timestamp(s.getTime());
    }

    public Class toJavaType() {
        return java.sql.Timestamp.class;
    }

    public int getTypeId() {
        return TYPE;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DateTimeType that = (DateTimeType) o;

        if (max != that.max) return false;
        if (min != that.min) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (min ^ (min >>> 32));
        result = 31 * result + (int) (max ^ (max >>> 32));
        return result;
    }
}

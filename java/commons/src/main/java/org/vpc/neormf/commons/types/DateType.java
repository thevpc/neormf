package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.util.Utils;

import java.util.Calendar;
import java.util.Date;

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
public class DateType extends DataType {
    public static final DateType DATE_NULLABLE = new DateType(true);
    public static final DateType DATE_NON_NULLABLE= new DateType(false);
    public static final int TYPE = 12;
    private long min = 0;
    private long max = 0;

    public DateType() {
        this(true);
    }
    public DateType(boolean nullable) {
        super(nullable);
    }

    public DateType(boolean nullable, java.util.Date min, java.util.Date max) {
        super(nullable);
        this.min = min == null ? 0 :validate(min.getTime());
        this.max = max == null ? 0 : validate(max.getTime());
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object == null) {
            return null;
        }
        java.util.Date s = (java.util.Date) Utils.castTo(object, java.util.Date.class);
        long l = validate(s.getTime());
        s = new java.sql.Date(l);
        if (this.min > 0 && l < this.min) {
            throw new DataException("org.vpc.neormf.commons.types.DateType.TooLow.Message", new Object[]{fieldName});
        }
        if (this.max > 0 && l > this.max) {
            throw new DataException("org.vpc.neormf.commons.types.DateType.TooHight.Message", new Object[]{fieldName});
        }
        return s;
    }

    protected long validate(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        return c.getTime().getTime();
    }

    public Class toJavaType() {
        return java.sql.Date.class;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public int getTypeId() {
        return TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DateType dateType = (DateType) o;

        if (max != dateType.max) return false;
        if (min != dateType.min) return false;

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

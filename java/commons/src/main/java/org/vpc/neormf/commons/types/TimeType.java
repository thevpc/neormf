package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.util.Utils;

import java.sql.Time;
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
public class TimeType extends DataType {
    public static final TimeType TIME_NULLABLE = new TimeType(true);
    public static final TimeType TIME_NON_NULLABLE = new TimeType(false);
    public static final int TYPE = 11;
    private long min = 0;
    private long max = 0;

    public TimeType() {
        this(true);
    }

    public TimeType(boolean nullable) {
        super(nullable);
    }

    public TimeType(boolean nullable, java.util.Date min, java.util.Date max) {
        super(nullable);
        this.min = min == null ? 0 : min.getTime();
        this.max = max == null ? 0 : max.getTime();
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object == null) {
            return null;
        }
        java.util.Date s = (java.util.Date) Utils.castTo(object, java.util.Date.class);
        long l = validate(s.getTime());
        s = new java.sql.Time(l);
        if (this.min > 0 && l < this.min) {
            throw new DataException("org.vpc.neormf.commons.types.TimeType.TooLow.Message", new Object[]{fieldName});
        }
        if (this.max > 0 && l > this.max) {
            throw new DataException("org.vpc.neormf.commons.types.TimeType.TooHight.Message", new Object[]{fieldName});
        }
        return s;
    }

    private long validate(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        c.set(Calendar.YEAR, 1900);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime().getTime();
    }

    public Class toJavaType() {
        return Time.class;
    }

    public int getTypeId() {
        return TYPE;
    }

    public long getMax() {
        return max;
    }

    public long getMin() {
        return min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TimeType timeType = (TimeType) o;

        if (max != timeType.max) return false;
        if (min != timeType.min) return false;

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

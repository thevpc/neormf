package org.vpc.neormf.commons.types;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;

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
public class TimeDayType extends DateTimeType {
    public static final TimeDayType TIMEDAY_NULLABLE = new TimeDayType(true);
    public static final TimeDayType TIMEDAY_NON_NULLABLE= new TimeDayType(false);
    public static final int TYPE = 14;
    public TimeDayType() {
        this(true);
    }
    public TimeDayType(boolean nullable) {
        super(nullable);
    }

    public TimeDayType(boolean nullable, Timestamp min, Timestamp max) {
        super(nullable,min,max);
    }

    public Timestamp getTimestamp(Time time,int day) {
        if(time==null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DAY_OF_YEAR, day);
        return new Timestamp(c.getTimeInMillis());
    }

    protected long validate(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.YEAR, 1900);
        return c.getTime().getTime();
    }

    public Class toJavaType() {
        return java.sql.Timestamp.class;
    }

    public Time getTime(long date){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_YEAR, 0);
        c.set(Calendar.YEAR, 1900);
        return new Time(c.getTime().getTime());
    }

    public int getDay(long date){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        return c.get(Calendar.DAY_OF_YEAR);
    }

    public Timestamp applyTimeDay(java.sql.Date date,Timestamp pattern){
        Calendar p=Calendar.getInstance();
        p.setTime(pattern);

        Calendar d=Calendar.getInstance();
        d.setTime(date);
        d.set(Calendar.MILLISECOND,p.get(Calendar.MILLISECOND));
        d.set(Calendar.SECOND,p.get(Calendar.SECOND));
        d.set(Calendar.MINUTE,p.get(Calendar.MINUTE));
        d.set(Calendar.HOUR_OF_DAY,p.get(Calendar.HOUR_OF_DAY));
        d.set(Calendar.DAY_OF_YEAR,d.get(Calendar.DAY_OF_YEAR)+p.get(Calendar.DAY_OF_YEAR)-1);
        return new Timestamp(d.getTimeInMillis());
    }

    public int getTypeId() {
        return TYPE;
    }
}

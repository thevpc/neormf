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
public class DayMonthType extends DateType {
    public static final DayMonthType DAYMONTH_NULLABLE = new DayMonthType(true);
    public static final DayMonthType DAYMONTH_NON_NULLABLE= new DayMonthType(false);
    public static final int TYPE = 13;
    public DayMonthType() {
        this(true);
    }
    public DayMonthType(boolean nullable) {
        super(nullable);
    }

    public DayMonthType(boolean nullable, Date min, Date max) {
        super(nullable,min,max);
    }

    protected long validate(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.YEAR, 1900);
        return c.getTime().getTime();
    }

    public int getTypeId() {
        return TYPE;
    }
}

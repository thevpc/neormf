using System;
using org.vpc.neormf.commons.util;
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
namespace org.vpc.neormf.commons.types
{
	public class DateType : DataType {
    public static DateType DATE_NULLABLE = new DateType(true);
    public static DateType DATE_NON_NULLABLE= new DateType(false);
    public static int TYPE = 12;
    private long min = 0;
    private long max = 0;

    public DateType():this(true) {
    }
    public DateType(bool nullable):base(nullable) {
        
    }

    public DateType(bool nullable, DateTime min, DateTime max):base(nullable){
        
        this.min = Validate(min.Ticks);
        this.max = Validate(max.Ticks);
    }

    public override Object ValidateValue(Object obj, String fieldName) {
        obj = base.ValidateValue(obj, fieldName);
        if (obj == null) {
            return null;
        }
        DateTime s = (DateTime) Utils.CastTo(obj, typeof(DateTime));
        long l = Validate(s.Ticks);
        s = new DateTime(l);
        if (this.min > 0 && l < this.min) {
            throw new DataException("org.vpc.neormf.commons.types.DateType.TooLow.Message", new Object[]{fieldName});
        }
        if (this.max > 0 && l > this.max) {
            throw new DataException("org.vpc.neormf.commons.types.DateType.TooHight.Message", new Object[]{fieldName});
        }
        return s;
    }

    protected virtual long Validate(long date) {
		DateTime d=new DateTime(date);
        return new DateTime(d.Year,d.Month,d.Day, 0,0,0,0).Ticks;
    }

    public override Type ToImplType() {
        return typeof(DateTime);
    }

    public long GetMin() {
        return min;
    }

    public long GetMax() {
        return max;
    }

    public override int GetTypeId() {
        return TYPE;
    }
}
}
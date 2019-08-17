using System;
using System.Globalization;
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
	public class TimeType : DataType 
						  {
    public static TimeType TIME_NULLABLE = new TimeType(true);
    public static TimeType TIME_NON_NULLABLE = new TimeType(false);
    public static int TYPE = 11;
    private long min = 0;
    private long max = 0;

    public TimeType():this(true) {
        
    }

    public TimeType(bool nullable) : base(nullable){
        
    }

    public TimeType(bool nullable, TimeSpan min, TimeSpan max) :base(nullable){
        
        this.min = min.Ticks;
        this.max = max.Ticks;
    }

    public override Object ValidateValue(Object obj, String fieldName) {
        obj = base.ValidateValue(obj, fieldName);
        if (obj == null) {
            return null;
        }
        TimeSpan s = (TimeSpan) Utils.CastTo(obj, typeof(TimeSpan));
        long l = Validate(s.Ticks);
        s = new TimeSpan(l);
        if (this.min > 0 && l < this.min) {
            throw new DataException("org.vpc.neormf.commons.types.TimeType.TooLow.Message", new Object[]{fieldName});
        }
        if (this.max > 0 && l > this.max) {
            throw new DataException("org.vpc.neormf.commons.types.TimeType.TooHight.Message", new Object[]{fieldName});
        }
        return s;
    }

    private long Validate(long date) {
		TimeSpan d=new TimeSpan(date);
        return new TimeSpan(d.Hours,d.Minutes,d.Seconds,d.Milliseconds).Ticks;
    }

    public override Type ToImplType() {
        return typeof(TimeSpan);
    }

    public override int GetTypeId() {
        return TYPE;
    }


}
}
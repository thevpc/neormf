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
	public class DateTimeType : DataType{
    public static DateTimeType DATETIME_NULLABLE = new DateTimeType(true);
    public static DateTimeType DATETIME_NON_NULLABLE= new DateTimeType(false);
    public static int TYPE = 13;
    private long min = 0;
    private long max = 0;

    public DateTimeType():this(true) {
    }
    public DateTimeType(bool nullable) :base(nullable){
    }

    public DateTimeType(bool nullable, DateTime min, DateTime max):base(nullable){
        this.min = Validate(min.Ticks);
        this.max = Validate(max.Ticks);
    }

    protected virtual long Validate(long date) {
        return date;
    }

    public override Object ValidateValue(Object obj, String fieldName) {
        obj = base.ValidateValue(obj, fieldName);
        if (obj == null) {
            return null;
        }
        DateTime s = (DateTime) Utils.CastTo(obj, typeof(DateTime));
        long l = Validate(s.Ticks);
        if (this.min > 0 && l < this.min) {
            throw new DataException("org.vpc.neormf.commons.types.DateTimeType.TooLow.Message", new Object[]{fieldName});
        }
        if (this.max > 0 && l > this.max) {
            throw new DataException("org.vpc.neormf.commons.types.DateTimeType.TooLow.Message", new Object[]{fieldName});
        }
        return s;
    }

    public override Type ToImplType() {
        return typeof(DateTime);
    }

    public override int GetTypeId() {
        return TYPE;
    }

    public long GetMin() {
        return min;
    }

    public long GetMax() {
        return max;
    }
}
}
using System;
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
	public class TimeDayType : DateTimeType{
		public static TimeDayType TIMEDAY_NULLABLE = new TimeDayType(true);
		public static TimeDayType TIMEDAY_NON_NULLABLE= new TimeDayType(false);
		public static new int TYPE = 14;
		public TimeDayType():this(true) 
		{
			
		}
		public TimeDayType(bool nullable) :base(nullable)
		{
			
		}

		public TimeDayType(bool nullable, DateTime min, DateTime max):base(nullable,min,max) 
		{
			
		}

		public DateTime GetTimestamp(DateTime time,int day) 
		{
			return new DateTime(time.Year,1,1,time.Hour,time.Minute,time.Second,time.Millisecond);
		}

		protected override long Validate(long date) 
		{
			DateTime time=new DateTime(date);
			return new DateTime(1900,1,time.Day,time.Hour,time.Minute,time.Second,time.Millisecond).Ticks;
		}

		public override Type ToImplType() 
		{
			return typeof(DateTime);
									  }

		public DateTime GetTime(long date)
		{
			DateTime time=new DateTime(date);
			return new DateTime(1900,1,1,time.Hour,time.Minute,time.Second,time.Millisecond);
		}

		public int GetDay(long date)
		{
			DateTime time=new DateTime(date);
			return time.DayOfYear;
		}

		public DateTime ApplyTimeDay(DateTime date,DateTime pattern)
		{
			DateTime ddd= new DateTime(date.Year,date.Month,date.Day,pattern.Hour,pattern.Minute,pattern.Second,pattern.Millisecond);
			if(pattern.DayOfYear>1)
			{
				ddd.AddDays(pattern.DayOfYear-1);
			}
			return ddd;
		}

		public override int GetTypeId() 
		{
			return TYPE;
		}
	}
}
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
	public class DayMonthType : DateType 
							  {
								  public static DayMonthType DAYMONTH_NULLABLE = new DayMonthType(true);
		public static DayMonthType DAYMONTH_NON_NULLABLE= new DayMonthType(false);
		public static new int TYPE = 13;
		public DayMonthType():this(true) 
		{
			
		}
		public DayMonthType(bool nullable) :base(nullable)
		{
			
		}

		public DayMonthType(bool nullable, DateTime min, DateTime max) :base(nullable,min,max)
		{
			
		}

		protected override long Validate(long date) 
		{
			DateTime d = new DateTime(date);
			return new DateTime(1900,d.Month,d.Day,0,0,0,0).Ticks;
		}

		public override int GetTypeId() 
		{
			return TYPE;
		}
	}
}
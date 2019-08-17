using System;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de SimpleDateFormat.
	/// </summary>
	public class SimpleDateFormat
	{
		private String pattern;
		
		public SimpleDateFormat(String pattern)
		{
			this.pattern=pattern;
		}
		
		public String format(Object o)
		{
			return Convert.ToString(o);
		}
		
		public String GetPattern(){
			return pattern;
		}
	}
}

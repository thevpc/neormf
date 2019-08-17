using System;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de Locale.
	/// </summary>
	public class Locale
	{
		public static Locale FRENSH=new Locale("fr","Francais");
		public static Locale ENGLISH=new Locale("en","English");
		private static Locale current=FRENSH;

		public static Locale Current
		{
			get
			{
				return current;
			}
			set
			{
				current=value==null?FRENSH:value;
			}
		}

		private String name;
		private String code;

		public Locale(String code,String name)
		{
			this.code=code;
			this.name=name;
		}
		
		public String Name
		{
			get
			{
				return name;
			}
		}

		public String Code
		{
			get
			{
				return code;
			}
		}
	}
}

using System;

namespace org.vpc.neormf.commons.beans
{
	/// <summary>
	/// Description résumée de DynamicDataContent.
	/// </summary>
	public class DynamicDataContent:DataContent
	{
		public DynamicDataContent():base()
		{
		}

		public String GetString(String name)
		{
			return (String)GetProperty(name);
		}
		
		public String GetString(String name,String defaultValue)
		{
			if(ContainsProperty(name)&& GetProperty(name)!=null)
			{
				return (String)GetProperty(name);
			}else
			{
				return defaultValue;
			}
		}
		
		public String GetConvertString(String name,String defaultValue)
		{
			if(ContainsProperty(name)&& GetProperty(name)!=null)
			{
				return Convert.ToString(GetProperty(name));
			}
			else
			{
				return defaultValue;
			}
		}

		public int GetInt(String name)
		{
			return (int)GetProperty(name);
		}

		public int GetInt(String name,int defaultValue)
		{
			if(ContainsProperty(name)&& GetProperty(name)!=null)
			{
				return (int)GetProperty(name);
			}
			else
			{
				return defaultValue;
			}
		}

		public int GetConvertInt(String name,int defaultValue)
		{
			if(ContainsProperty(name) && GetProperty(name)!=null)
			{
				return Convert.ToInt32(GetProperty(name));
			}
			else
			{
				return defaultValue;
			}
		}
		
		public double GetDouble(String name)
		{
			return (double)GetProperty(name);
		}

		public double GetDouble(String name,double defaultValue)
		{
			if(ContainsProperty(name)&& GetProperty(name)!=null)
			{
				return (int)GetProperty(name);
			}
			else
			{
				return defaultValue;
			}
		}

		public double GetConvertDouble(String name,double defaultValue)
		{
			if(ContainsProperty(name)&& GetProperty(name)!=null)
			{
				return Convert.ToDouble(GetProperty(name));
			}
			else
			{
				return defaultValue;
			}
		}
	}
}

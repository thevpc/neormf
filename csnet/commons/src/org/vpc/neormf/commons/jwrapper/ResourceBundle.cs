using System;
using System.Collections;
using org.vpc.neormf.commons.jwrapper;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de ResourceBundle.
	/// </summary>
	public abstract class ResourceBundle
	{
		private static Hashtable cached=new Hashtable();

		protected ResourceBundle()
		{
		}

		public static ResourceBundle GetBundle(String name)
		{
			return GetBundle(name,Locale.Current);
		}
		
		public static ResourceBundle GetBundle(String name,Locale locale)
		{
			String k=name+"_"+locale.Code;
			ResourceBundle r=(ResourceBundle)cached[k];
			if(r==null)
			{
				if(name.StartsWith("file:"))
				{
					String n=name.Substring(5);
					if(!n.ToLower().EndsWith(".lang"))
					{
						n=n+".lang";
					}
					r=new XmlFileResourceBundle(n,locale);
					cached[k]=r;
				}
				else if(name.IndexOf(':')<0)
				{
					String n=name;
					if(!n.ToLower().EndsWith(".lang"))
					{
						n=n+".lang";
					}
					r=new XmlFileResourceBundle(n,locale);
					cached[k]=r;
				}else{
					throw new MissingResourceException("only 'file:' protocol is supported");
				}
			}
			return r;
		}

		public abstract String GetString(String key);

		public String GetString(String key,Object[] parameters,String defaultValue)
		{
			String v=GetString(key,defaultValue);
			if(parameters!=null)
			{
				for (int i = 0; i < parameters.Length; i++)
				{
					v=v.Replace("{"+i+"}",Convert.ToString(parameters[i]));
				}
			}
			return v;
		}

		public String GetString(String key,Object[] parameters)
		{
			String v=GetString(key);
			if(parameters!=null)
			{
				for (int i = 0; i < parameters.Length; i++)
				{
					v=v.Replace("{"+i+"}",Convert.ToString(parameters[i]));
				}
			}
			return v;
		}

		public String GetString(String key,String defaultValue)
		{
			try
			{
				return GetString(key);
			}catch(MissingResourceException)
			{
				return defaultValue;
			}
		}
	}
}

using System;
using System.Collections;
using org.vpc.neormf.commons.jwrapper;
/**
 */
namespace org.vpc.neormf.commons.util
{
	public class NeormfMessages 
	{
		public static Hashtable mappedBundles=new Hashtable();

		private Locale locale=null;
		private ResourceBundle defaultBundle=null;
		private static String defaultBundleBaseName="org.vpc.neormf.commons.util.neormf-messages";

		private ResourceBundle replaceBundle=null;
		private static String replaceBundleName=null;


		public NeormfMessages(Locale locale) 
		{
			this.locale = locale;
		}

		public Locale getLocale() 
		{
			return locale;
		}

		public static void SetBundleBaseName(String bundleBaseName) 
		{
			replaceBundleName = bundleBaseName;
			mappedBundles.Clear();
		}

		public String GetString(String key,String defaultValue)
		{
			return GetString(key,null,defaultValue);
		}

		public String GetString(String key)
		{
			return GetString(key, (Object[])null);
		}

		public String GetString(String key,Object[] parameters)
		{
			if(defaultBundle==null)
			{
				try 
				{
					defaultBundle=ResourceBundle.GetBundle(defaultBundleBaseName,locale);
				}catch(Exception e)
				{
					Console.WriteLine(e);
				}
			}
			if(replaceBundle==null && replaceBundleName!=null)
			{
				replaceBundle=ResourceBundle.GetBundle(replaceBundleName,locale);
			}
			String value=null;
			if(replaceBundle!=null)
			{
				try 
				{
					value=replaceBundle.GetString(key,parameters);
				} 
				catch (MissingResourceException e) 
				{
					Console.WriteLine(e);
					if(defaultBundle!=null)
					{
						value=defaultBundle.GetString(key,parameters);
					}
				}
			}
			return value;
		}

		public String GetString(String key,Object[] parameters,String defaultValue)
		{
			if(defaultBundle==null)
			{
				try 
				{
					defaultBundle=ResourceBundle.GetBundle(defaultBundleBaseName,locale);
				}
				catch(Exception e)
				{
					Console.WriteLine(e);
				}
			}
			if(replaceBundle==null && replaceBundleName!=null)
			{
				replaceBundle=ResourceBundle.GetBundle(replaceBundleName,locale);
			}
			String value=null;
			if(replaceBundle!=null)
			{
				try 
				{
					value=replaceBundle.GetString(key,parameters);
				} 
				catch (MissingResourceException e) 
				{
					Console.WriteLine(e);
					if(defaultBundle!=null)
					{
						try 
						{
							value=defaultBundle.GetString(key,parameters);
						} 
						catch (MissingResourceException e1) 
						{
							Console.WriteLine(e1);
							//
						}
					}
				}
			}
			return (value==null)?defaultValue:value;
		}


		public static String GetString(String key, Locale locale)
		{
			NeormfMessages messages = (NeormfMessages) mappedBundles[locale];
			if(messages==null)
			{
				messages=new NeormfMessages(locale);
				mappedBundles.Add(locale,messages);
			}
			return messages.GetString(key);
		}

		public static String GetString(String key, String defaultValue,Locale locale)
		{
			NeormfMessages messages = (NeormfMessages) mappedBundles[locale];
			if(messages==null)
			{
				messages=new NeormfMessages(locale);
				mappedBundles.Add(locale,messages);
			}
			return messages.GetString(key,defaultValue);
		}

		public static String GetString(String key, Object[] parameters,Locale locale)
		{
			return GetString(key, parameters,"<"+key+"$NOT_FOUND>",locale);
		}

		public static String GetString(String key, Object[] parameters,String defaultValue,Locale locale)
		{
			NeormfMessages messages = (NeormfMessages) mappedBundles[locale];
			if(messages==null)
			{
				messages=new NeormfMessages(locale);
				mappedBundles.Add(locale,messages);
			}
			return messages.GetString(key,parameters,defaultValue);
		}

	}
}
using System;
using System.Collections;
using System.IO;
using System.Xml;
using org.vpc.neormf.commons.jwrapper;
using org.vpc.neormf.commons.util;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de XmlFileResourceBundle.
	/// </summary>
	public class XmlFileResourceBundle : ResourceBundle
	{
		Hashtable values=new Hashtable();
		String file;
		Locale locale;
		public XmlFileResourceBundle(String file,Locale locale)
		{
			this.file=file;
			this.locale=locale;
			Load(file);
		}

		private void Load(String file)
		{
			bool found=false;
			int x=file.LastIndexOf('.');
			if(x>0)
			{
				String before=file.Substring(0,x);
				String after=file.Substring(x+1);
				String theFile=before+'_'+locale.Name+'.'+after;
				if(File.Exists(theFile))
				{
					Load0(theFile,true);
					found=true;
				}
				theFile=before+'.'+after;
				if(File.Exists(theFile))
				{
					Load0(theFile,false);
					found=true;
				}
			}
			if(!found)
			{
				throw new MissingResourceException("Could not locate resource bundle : "+file);
			}
		}
		
		private void Load0(String file,bool doOverrideValues)
		{
			XmlDocument config = new XmlDocument();
			config.Load(file);
			foreach(XmlNode node in config.DocumentElement)
			{
				if("key".ToLower().Equals(node.Name))
				{
					String k=node.Attributes["name"].Value;
					if(doOverrideValues || !values.ContainsKey(k))
					{
						values[node.Attributes["name"].Value]=XmlUtils.FromEscapeString(node.InnerText);	
					}
				}else if("module".ToLower().Equals(node.Name))
				{
					String prefix=null;
					if(node.Attributes["prefix"]!=null){
						prefix=node.Attributes["prefix"].Value;
					}
					if(prefix==null)
					{
						prefix="";
					}else if(!prefix.EndsWith("."))
					{
						prefix=prefix+".";
					}
					foreach(XmlNode subnode in node.ChildNodes)
					{
						String k=subnode.Attributes["name"].Value;
						if(doOverrideValues || !values.ContainsKey(k))
						{
							values[prefix+subnode.Attributes["name"].Value]=XmlUtils.FromEscapeString(subnode.InnerText);	
						}
					}
				}else
				{
					throw new Exception("Corrupted Bundle File. expected 'key' or 'module' node");
				}
			}
		}

		public String GetFile()
		{
			return file;
		}

		public override String GetString(String key)
		{
			String v=(String)values[key];
			if(v==null)
			{
				throw new MissingResourceException("Missing "+key);
			}
			return v;
		}
	}
}

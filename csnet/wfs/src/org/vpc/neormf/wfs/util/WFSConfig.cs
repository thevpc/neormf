using System;
using System.IO;
using System.Xml;
using System.Xml.Serialization;
using org.vpc.neormf.commons.util;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de ApplicationConfig.
	/// </summary>
	[XmlRoot("WFSConfig", Namespace="", IsNullable=false)]
	public class WFSConfig
	{
		private String optionFile;
		private bool optionAutoSave=true;

		private bool hideDisabledActions=true;
		private String lastLogin=null;

		public WFSConfig()
		{
			
		}
		public WFSConfig(String file)
		{
			this.optionFile=file;
		}

		[XmlIgnoreAttribute()]
		public string OptionFile
		{
			get { return optionFile; }
			set { optionFile = value; }
		}

		[XmlIgnoreAttribute()]
		public bool OptionAutoSave
		{
			get { return optionAutoSave; }
			set { optionAutoSave = value; }
		}

		public bool HideDisabledActions
		{
			get { return hideDisabledActions; }
			set
			{
				hideDisabledActions = value;
				if(OptionAutoSave)
				{
					Save();
				}
			}
		}

		public string LastLogin
		{
			get { return lastLogin; }
			set
			{
				lastLogin = value;
				if(OptionAutoSave)
				{
					Save();
				}
			}
		}


		public bool Save()
		{
			XmlDocument doc=new XmlDocument();
			XmlNode node=doc.CreateElement("WFSConfig");
			doc.AppendChild(node);
			XmlUtils.AppendTextNode(node,"LastLogin",LastLogin);
			XmlUtils.AppendTextNode(node,"HideDisabledActions",Convert.ToString(HideDisabledActions));
			doc.Save(optionFile);
			return true;
		}

		public bool Load()
		{
			if(File.Exists(optionFile))
			{
				XmlDocument config = new XmlDocument();
				String strXPath;
				config.Load(optionFile);
				strXPath = "/WFSConfig/";
				this.lastLogin = InnerXml(config,strXPath+"LastLogin","").Trim();
				this.hideDisabledActions = Convert.ToBoolean(InnerXml(config,strXPath+"HideDisabledActions","False").Trim());
				return true;
			}
			return false;
		}

		private static String InnerXml(XmlDocument config,String path,String def)
		{
			XmlNode node=config.SelectSingleNode(path);
			if(node==null)
			{
				return def;
			}
			return XmlUtils.FromEscapeString(node.InnerXml);
		}
	}
}

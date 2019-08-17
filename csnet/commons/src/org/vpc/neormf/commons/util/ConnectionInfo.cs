using System;
using System.Xml;
using org.vpc.neormf.commons.util;

namespace org.vpc.neormf.commons.util
{
	/// <summary>
	/// Description résumée de ConnectionInfo.
	/// </summary>
	public class ConnectionInfo
	{
		public ConnectionInfo()
		{

		}

		public static ConnectionInfo LoadXml(String file,bool reencryptPwd)
		{
			XmlDocument config = new XmlDocument();
			string strXPath;

			config.Load(file);
			strXPath = "/ConnectionInfo/";

			ConnectionInfo info=new ConnectionInfo();
			info.Server = InnerXml(config,strXPath+"Server","localhost").Trim();
			info.Login = InnerXml(config,strXPath+"Login","sa").Trim();
			info.Password = InnerXml(config,strXPath+"Password","");
			info.Database = InnerXml(config,strXPath+"Database","no_database").Trim();
			info.Timeout = Convert.ToInt32(InnerXml(config,strXPath+"Timeout","300").Trim());
			info.Lifetime = Convert.ToInt32(InnerXml(config,strXPath+"LifeTime","1800").Trim());
			info.IntegratedSecurity = Convert.ToBoolean(InnerXml(config,strXPath+"IntegratedSecurity","false").Trim());
			XmlNode node=config.SelectSingleNode(strXPath+"Password");
			info.PlainPassword = (node !=null && node.Attributes["Plain"]!=null && Convert.ToBoolean(node.Attributes["Plain"].Value.Trim()));
			if(!info.PlainPassword)
			{
				info.Password=Decrypt(info.Password);
			}else if(reencryptPwd)
			{
				info.PlainPassword=false;
				info.SaveXml(file);
			}
			return info;
		}
		
		private static String Decrypt(String x)
		{
			byte[] bytIn = System.Convert.FromBase64String(x);
			return new string(System.Text.ASCIIEncoding.ASCII.GetChars(bytIn));
		}

		private static String Encrypt(String x)
		{
			return Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(x));
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


		public void SaveXml(String file)
		{
			XmlDocument doc=new XmlDocument();
			XmlNode node=doc.CreateElement("ConnectionInfo");
			doc.AppendChild(node);
			XmlUtils.AppendTextNode(node,"Server",Server);
			XmlUtils.AppendTextNode(node,"Login",Convert.ToString(Login));
			XmlElement elem=XmlUtils.AppendNonNullTextNode(node,"Password",Convert.ToString(
				PlainPassword?Password:Encrypt(Password)
				));
			XmlUtils.AppendAttribute(elem,"Plain",Convert.ToString(PlainPassword));
			XmlUtils.AppendTextNode(node,"Database",Convert.ToString(Database));
			XmlUtils.AppendTextNode(node,"IntegratedSecurity",Convert.ToString(IntegratedSecurity));
			XmlUtils.AppendTextNode(node,"Timeout",Convert.ToString(Timeout));
			XmlUtils.AppendTextNode(node,"LifeTime",Convert.ToString(Lifetime));
			doc.Save(file);
		}

		private String server;
		private String login;
		private String password;
		private String database;
		private int timeout;
		private int lifetime;
		private bool plainPassword;
		private bool integratedSecurity;

		public bool PlainPassword
		{
			get { return plainPassword; }
			set { plainPassword = value; }
		}

		public bool IntegratedSecurity
		{
			get { return integratedSecurity; }
			set { integratedSecurity = value; }
		}

		public string Server
		{
			get { return server; }
			set { server = value; }
		}

		public string Login
		{
			get { return login; }
			set { login = value; }
		}

		public string Password
		{
			get { return password; }
			set { password = value; }
		}

		public string Database
		{
			get { return database; }
			set { database = value; }
		}

		public int Timeout
		{
			get { return timeout; }
			set { timeout = value; }
		}

		public int Lifetime
		{
			get { return lifetime; }
			set { lifetime = value; }
		}


		public String ConnectionString
		{
			get
			{
				return "Server="+Server+"; "
					+"Integrated Security="+integratedSecurity+"; "
					+"User ID="+Login+"; "
					+"pwd="+Password+"; "
					+"Database="+Database+"; "
					+((Timeout>=0)? "Connect Timeout="+Timeout+"; ":"")
					+((Lifetime>=0)? "Connection Lifetime="+Lifetime+";":"")
					;

			}
		}
	}
}

using System;
using System.Data.SqlClient;
using System.Windows.Forms;
using org.vpc.neormf.commons.beans;
using org.vpc.neormf.commons.util;
using org.vpc.neormf.commons.jwrapper;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de ApplicationSession.
	/// </summary>
	public class WFSApplication
	{
		private static WFSApplication current;
		private String messagesBundlePath;
		private ConnectionInfo connectionInfo;
		private WFSConfig wfsConfig;
		private String wfsConfigFile="Configuration\\Config.xml";
		private ModuleInfo module;
		private SecurityBO securityBO;

		public static WFSApplication Current
		{
			get
			{
				return current;
			}
			set
			{
				current=value;
			}
		}

		public string MessagesBundlePath
		{
			get { return messagesBundlePath; }
			set { messagesBundlePath = value; }
		}

		public ResourceBundle Messages
		{
			get { return ResourceBundle.GetBundle(MessagesBundlePath); }
		}

		public void InitializeApplication()
		{
			Utils.UpdateFromBundle(MessagesBundlePath, Module);
		}

		public WFSApplication(ModuleInfo module)
		{
			this.module=module;
		}

		public ModuleInfo Module
		{
			get { return module; }
		}

		public string CallerPrincipalName
		{
			get
			{
				if(Security!=null)
				{
					String n=Security.CallerPrincipalName;
					if(n!=null)
					{
						return null;
					}
				}
				return "ananymous";
			}
		}

//		public String CallerPrincipalName
//		{
//			get { return Security.CallerPrincipalName; }
//		}


//		public ConnectionInfo GetConnectionInfo()
//		{
//			}
//		}

		public void SetConnectionInfo(String relativeDatabasexmlPath)
		{
			try
			{
				ConnectionInfo=ConnectionInfo.LoadXml(FilePath(relativeDatabasexmlPath), true);
			}
			catch (Exception e)
			{
				Console.WriteLine(e);
				throw e;
			}
		}

		public ConnectionInfo ConnectionInfo
		{
			get { return connectionInfo; }
			set { connectionInfo = value; }
		}

		public SqlConnection NewConnection()
		{
			ConnectionInfo info = ConnectionInfo;
			SqlConnection s = new SqlConnection(info.ConnectionString);
			s.Open();
			return s;
		}

		public String FilePath(String relative)
		{
			return Application.StartupPath+"\\"+relative;
		}


		public string WfsConfigFile
		{
			get { return wfsConfigFile; }
			set
			{
				wfsConfigFile = value;
				wfsConfig=null;
			}
		}

		public WFSConfig WFSConfig
		{
			get
			{
				if(wfsConfig==null)
				{
					wfsConfig=new WFSConfig(FilePath(WfsConfigFile));
					wfsConfig.Load();
				}
				return wfsConfig;
			}
		}

		public SecurityBO Security
		{
			get { return securityBO; }
			set { securityBO = value; }
		}

	}
}
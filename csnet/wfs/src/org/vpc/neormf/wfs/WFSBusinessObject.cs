using System;
using System.Data.SqlClient;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de AbstractBO.
	/// </summary>
	public class WFSBusinessObject
	{
		private WFSApplication app;

		public WFSBusinessObject(WFSApplication app)
		{
			this.app = app;
		}

		public WFSApplication Application
		{
			get { return app; }
		}

		public SqlConnection NewConnection()
		{
			return app.NewConnection();
		}
		
		private String BuildMessageKey(String key)
		{
			if(key.StartsWith("."))
			{
				return GetType().FullName + key;
			}
			else
			{
				return key;
			}
		}
		
		public String GetMessage(String key)
		{
			return WFSApplication.Current.Messages.GetString(BuildMessageKey(key));
		}
		
		public String GetMessage(String key,String defaultValue)
		{
			return WFSApplication.Current.Messages.GetString(BuildMessageKey(key),defaultValue);
		}

		public String GetMessage(String key,Object[] values)
		{
			return WFSApplication.Current.Messages.GetString(BuildMessageKey(key),values);
		}
		
		public String GetMessage(String key,Object[] values,String defaultValue)
		{
			return WFSApplication.Current.Messages.GetString(BuildMessageKey(key),values,defaultValue);
		}
		
	}
}
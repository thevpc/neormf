using System;
using System.Collections;
using System.Data.SqlClient;
using System.Reflection;
using org.vpc.neormf.commons.beans;

namespace org.vpc.neormf.wfs.util
{
	/// <summary>
	/// Description résumée de SecurityBO.
	/// </summary>
	public abstract class SecurityBO : WFSBusinessObject
	{

		public delegate void SecurityChanged(object o, AppSecurityChangeEvent e);
		public event SecurityChanged EventSecurityChanged;

		public class AppSecurityChangeEvent: EventArgs
		{
			public AppSecurityChangeEvent()
			{
			}
		}
		
		public SecurityBO(WFSApplication app) : base(app)
		{
		}

		public abstract String CallerPrincipalName
		{
			get;
			set;
		}

		protected void OnEvent(AppSecurityChangeEvent e)
		{
			if(EventSecurityChanged!=null)
			{
				EventSecurityChanged(new object(),e);
			}
		}    

		protected void FireAppSecurityChangeEvent()
		{
			OnEvent(new AppSecurityChangeEvent());
		}

		public void RequireNothing()
		{
		}

	}
}
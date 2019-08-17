using System;
using org.vpc.neormf.commons.jwrapper;

namespace org.vpc.neormf.util
{
	/// <summary>
	/// Description résumée de RequiredPrivilegesException.
	/// </summary>
	public class RequiredPrivilegesException:BusinessException
	{
		public RequiredPrivilegesException(ResourceBundle bundle):base(bundle.GetString("Default.RequiredPrivilegesException"))
		{
		}
	}
}

using System;
using org.vpc.neormf.commons.jwrapper;

namespace org.vpc.neormf.util
{
	/// <summary>
	/// Description r�sum�e de RequiredPrivilegesException.
	/// </summary>
	public class RequiredPrivilegesException:BusinessException
	{
		public RequiredPrivilegesException(ResourceBundle bundle):base(bundle.GetString("Default.RequiredPrivilegesException"))
		{
		}
	}
}

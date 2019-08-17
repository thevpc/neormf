using System;
using org.vpc.neormf.commons.jwrapper;

namespace org.vpc.neormf.util
{
	/// <summary>
	/// Description résumée de BusinessException.
	/// </summary>
	public class BusinessException : Exception
	{
		public BusinessException(string message,ResourceBundle bundle) : base(
			bundle.GetString(message))
		{
		}
		public BusinessException(string message,Object[] parameters,ResourceBundle bundle) : base(
			bundle.GetString(message,parameters))
		{
		}

		public BusinessException(string message) : base(message)
		{
		}

		public BusinessException(string message, Exception innerException) : base(message, innerException)
		{
		}

		public BusinessException() : base()
		{
		}
	}
}
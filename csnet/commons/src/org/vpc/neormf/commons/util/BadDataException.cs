using System;
using org.vpc.neormf.commons.jwrapper;

namespace org.vpc.neormf.util
{
	/// <summary>
	/// Exception for Bad User Data Input.
	/// </summary>
	/// <remarks>
	///  Authors : 
	///    (1) Taha BEN SALAH ; Mapsys Tunisia
	///  Creation Date         : 2005-10-06 by (1)
	///  Last Modification     : 2005-10-11 by (1)
	/// </remarks>
	public class BadDataException : BusinessException
	{
		public BadDataException(string message, ResourceBundle bundle) : base(message, bundle)
		{
		}

		public BadDataException(string message, object[] parameters, ResourceBundle bundle) : base(message, parameters, bundle)
		{
		}

		public BadDataException(string message, Exception innerException) : base(message, innerException)
		{
		}

		public BadDataException()
		{
		}

		/// <summary>
		/// constructor
		/// </summary>
		/// <param name="message">Message d'erreur</param>
		public BadDataException(String message) : base(message)
		{
		}
	}
}
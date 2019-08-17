using System;
/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Root Optimizer
 * @creation on Date: 24 mai 2004 Time: 20:16:25
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.exceptions
{
	public class UpdateDataException : AbstractNeormfRuntimeException
	{
		public UpdateDataException(Exception error) : base("org.vpc.neormf.commons.exceptions.UpdateDataException.Message", null, error)
		{
		}

		public UpdateDataException(String message, Object[] parameters) : base(message, parameters)
		{
		}

		public UpdateDataException(Object[] parameters) : base("org.vpc.neormf.commons.exceptions.UpdateDataException.Message", parameters)
		{
		}

		public UpdateDataException() : this((Object[]) null)
		{
		}
	}
}
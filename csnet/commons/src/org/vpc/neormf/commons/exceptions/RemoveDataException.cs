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
	public class RemoveDataException : AbstractNeormfRuntimeException
	{
		public RemoveDataException(Object[] parameters) :
			base("org.vpc.neormf.commons.exceptions.RemoveDataException.Message", parameters)
		{
		}

		public RemoveDataException(Exception throwable)
			: base("org.vpc.neormf.commons.exceptions.RemoveDataException.Message", null, throwable)
		{
		}

		public RemoveDataException()
			: this((Object[]) null)
		{
			;
		}
	}
}
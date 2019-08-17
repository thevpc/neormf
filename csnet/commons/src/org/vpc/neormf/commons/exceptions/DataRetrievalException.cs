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
	public class DataRetrievalException : AbstractNeormfRuntimeException
	{
		public DataRetrievalException(Exception throwable) : base("org.vpc.neormf.commons.exceptions.DataRetrievalException.Message", null, throwable)
		{
		}

		public DataRetrievalException(Object[] parameters) : base("org.vpc.neormf.commons.exceptions.DataRetrievalException.Message", parameters)
		{
		}

		public DataRetrievalException() : this((Object[]) null)
		{
		}
	}
}
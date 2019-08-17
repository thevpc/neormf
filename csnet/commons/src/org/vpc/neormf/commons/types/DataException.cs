using System;
using org.vpc.neormf.commons.exceptions;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 09:47:09
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.types
{
	public class DataException : AbstractNeormfRuntimeException
	{
		public DataException(String message, Object[] parameters) : base(message, parameters)
		{
		}

		public DataException(String message, Object[] parameters, Exception cause) : base(message, parameters, cause)
		{
		}

	}
}
using System;
/**
 * Created by IntelliJ IDEA.
 * User: taha
 * Date: 14 d�c. 2005
 * Time: 11:45:34
 * To change this template use File | Settings | File Templates.
 */

namespace org.vpc.neormf.commons.exceptions
{
	public class BadFormatException : AbstractNeormfRuntimeException
	{
		public BadFormatException(String message, Object[] parameters) : base(message, parameters)
		{
		}
	}
}
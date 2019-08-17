using System;
/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 15 avr. 2004 Time: 17:16:22
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons
{
	public class CommunicationException : Exception 
	{
		private Exception cause;

		public CommunicationException() 
		{
		}

		public CommunicationException(String message):base(message) 
		{
		}

		public CommunicationException(Exception cause):base(cause.Message) 
		{
			this.cause=cause;
		}

		public CommunicationException(String message, Exception cause):base(message) 
		{
			this.cause=cause;
		}

		public Exception getCause() 
		{
			return cause;
		}
	}
}
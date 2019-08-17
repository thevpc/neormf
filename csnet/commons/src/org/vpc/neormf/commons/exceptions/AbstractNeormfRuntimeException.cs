using System;
using System.Text;
using org.vpc.neormf.commons.jwrapper;
using org.vpc.neormf.commons.util;
/**
 * Created by IntelliJ IDEA.
 * User: ramzi
 * Date: 21 nov. 2005
 * Time: 23:16:00
 * To change this template use File | Settings | File Templates.
 */
namespace org.vpc.neormf.commons.exceptions
{
	public class AbstractNeormfRuntimeException : Exception,LocalizedObject 
	{
		private Locale locale;
		private Object[] parameters;
		private Exception cause;
		public AbstractNeormfRuntimeException(String message,Object[] parameters):base(message) 
		{
			SetParameters(parameters);
		}

		public AbstractNeormfRuntimeException(String message,Object[] parameters,Exception cause):base(message) 
		{
			this.cause=cause;
			SetParameters(parameters);
		}

		public String GetLocalizedMessage() 
		{
			bool showCause=false;
			if (locale == null) 
			{
				StringBuilder sb=new StringBuilder(base.Message);
				if(showCause && GetCause()!=null)
				{
					sb.Append("\r\n").Append(GetCause().Message);	
				}
				return sb.ToString();
			}
			try 
			{
				StringBuilder sb=new StringBuilder(NeormfMessages.GetString(GetMessage(), parameters==null?new Object[0] :parameters, GetMessage(), locale));
				if(showCause && GetCause()!=null)
				{
					sb.Append("\r\n").Append(GetCause().Message);	
				}
				return sb.ToString();
			} 
			catch (Exception t) 
			{
				Console.WriteLine(t);
				return Message;
			}
		}
	
		public String GetMessage()
		{
			return Message;
		}

		public void SetLocale(Locale locale) 
		{
			this.locale = locale;
		}

		public Object[] GetParameters() 
		{
			return parameters;
		}

		public void SetParameters(Object[] parameters) 
		{
			this.parameters = parameters;
		}
	
		public Exception GetCause()
		{
			return cause;
		}
	}
}
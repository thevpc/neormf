using System;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description r�sum�e de MissingMessageException.
	/// </summary>
	public class MissingMessageException:Exception
	{
		private String msg;
		public MissingMessageException(String msg):base("Missing Message "+msg)
		{
			this.msg=msg;
		}
		
		public String GetMessage(){
			return msg;
		}
	}
}

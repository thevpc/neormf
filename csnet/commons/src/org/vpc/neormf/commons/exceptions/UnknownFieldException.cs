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
	public class UnknownFieldException :FieldException{
										   public UnknownFieldException(String fieldName):base(fieldName,"org.vpc.neormf.commons.exceptions.UnknownFieldException.Message")
	{
		
	}
}
}
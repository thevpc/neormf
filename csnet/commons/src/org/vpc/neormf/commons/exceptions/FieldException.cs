using System;
using org.vpc.neormf.commons.beans;
using org.vpc.neormf.commons.util;
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
	public class FieldException :AbstractNeormfRuntimeException,Dumpable{
		private String fieldName;
		private String fieldTitle;
		private String beanName;

		public FieldException(DataField dataField,String message):base(message,null) 
		{
			
			this.fieldName=dataField.GetFieldName();
			this.fieldTitle=dataField.getFieldTitle();
			this.beanName=dataField.getDataInfo()==null ? null : dataField.getDataInfo().GetBeanName();
			SetParameters(new Object[]{
										  getVerboseFieldName(dataField),
										  fieldName,
										  fieldTitle,
										  beanName
									  });
		}

		public FieldException(String fieldName,String message):base(message,null) 
		{
			this.fieldName=fieldName;
			SetParameters(new Object[]{
										  fieldName,
										  fieldName,
										  fieldTitle,
										  beanName
									  });
		}

		public static String getVerboseFieldName(DataField dataField) 
		{
			if(dataField.getFieldTitle()==null)
			{
				return  "'"+dataField.GetFieldName()+"'";
			}
			//        return "'"+dataField.getFieldTitle()+"' ('"+dataField.getFieldName()+"')";
			return "'"+dataField.getFieldTitle()+"'";
		}

		public String getFieldName() 
		{
			return fieldName;
		}

		public String getFieldTitle() 
		{
			return fieldTitle;
		}

		public String getBeanName() 
		{
			return beanName;
		}


		public String Dump() 
		{
			return GetType()+":"+GetMessage()+": {" +
				"fieldName='" + fieldName + "'" +
				", fieldTitle='" + fieldTitle + "'" +
				", beanName='" + beanName + "'" +
				"}";
		}
	}
}
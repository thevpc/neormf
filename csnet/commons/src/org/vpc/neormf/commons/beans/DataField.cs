using System;
using System.Collections;
using org.vpc.neormf.commons.types;
using org.vpc.neormf.commons.types.converters;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 5 avr. 2004 Time: 14:16:00
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.beans
{
	public class DataField  
	{
		private String fieldName;


		private String columnName;
		private String fieldTitle;
		private DataType fieldType;
		private DataType sqlType;
		private DataInfo dataInfo;
		private DataTypeConverter converter;
		private Hashtable clientProperties;

		public DataField(String fieldName, String fieldTitle, String columnName, DataType fieldType, DataType sqlType, DataTypeConverter converter,int modifiers, RelationRoleIndex[] relationRoleIndices) 
		{
			this.fieldName = fieldName;
			this.columnName = columnName;
			this.converter = converter;
			this.fieldTitle = fieldTitle;
			if (this.fieldTitle == null) 
			{
				this.fieldTitle = this.fieldName;
			}
			this.fieldType = fieldType == null  ? sqlType : fieldType;
			this.sqlType = sqlType==null ? fieldType : sqlType;
		}

		public String GetFieldName() 
		{
			return fieldName;
		}

		public String getFieldTitle() 
		{
			return fieldTitle;
		}

		public DataType getFieldType() 
		{
			return fieldType;
		}

		public String getColumnName() 
		{
			return columnName;
		}

		public DataType getSqlType() 
		{
			return sqlType;
		}

		public DataTypeConverter getConverter() 
		{
			return converter;
		}

		public DataInfo getDataInfo() 
		{
			return dataInfo;
		}

		public void SetDataInfo(DataInfo dataInfo) 
		{
			this.dataInfo = dataInfo;
		}

		public Object getClientProperty(String name) 
		{
			return clientProperties == null ? null : clientProperties[name];
		}

		public void setClientProperty(String name, Object value) 
		{
			if (value == null) 
			{
				if (clientProperties != null) 
				{
					clientProperties.Remove(name);
				}

			} 
			else 
			{
				if (clientProperties == null) 
				{
					clientProperties = new Hashtable();
				}
				clientProperties.Add(name, value);
			}
		}
		public void SetFieldTitle(String newTitle)
		{
			fieldTitle=(newTitle==null)?fieldTitle:newTitle;
		}
	}
}

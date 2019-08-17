using System;
using System.Collections;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 5 avr. 2004 Time: 14:09:37
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.beans
{
	public class DataInfo 
	{
		private DataField titleField;


		private DataField[] keyFields;
		private DataField[] orderFields;
		private bool[] orderFieldsAsc;
		private ArrayList fields = new ArrayList();
		private Hashtable fieldsMap = new Hashtable();
		private String beanName;
		private String dataClassName;
		private String dataKeyClassName;
		private String propertyListClassName;
		private IDictionary properties = new Hashtable();

		public DataInfo(String beanName, DataField[] fields, String[] pkFields, String titleFieldName,String dataClassName, String dataKeyClassName, String propertyFilterClassName, String[] orderedFields, bool[] orderFieldsAsc, IDictionary properties) 
		{
			this.beanName = beanName;
			SetDataClassName(dataClassName);
			SetDataKeyClassName(dataKeyClassName);
			SetPropertyListClassName(propertyFilterClassName);
			if (properties != null) 
			{
				for (IDictionaryEnumerator i = properties.GetEnumerator(); i.MoveNext(); )
				{
					this.properties.Add(i.Key,i.Value);
				}
			}
			for (int i = 0; i < fields.Length; i++) 
			{
				AddField(fields[i]);
			}
			this.keyFields = new DataField[pkFields.Length];
			for (int i = 0; i < keyFields.Length; i++) 
			{
				keyFields[i] = GetField(pkFields[i]);
			}
			if(titleFieldName==null)
			{
				if(this.keyFields.Length>0)
				{
					this.titleField=this.keyFields[0];
				}
				else if(this.fields.Count>0)
				{
					this.titleField=(DataField) this.fields[0];
				}
				else
				{
					//What to do???
				}
			}
			else
			{
				this.titleField = GetField(titleFieldName);
			}

			SetOrder(orderedFields, orderFieldsAsc);
		}

		public DataInfo(String beanName) 
		{
			this.beanName = beanName;
		}

		public virtual String GetDataClassName() 
		{
			return dataClassName;
		}

		public virtual void SetDataClassName(String dataClassName) 
		{
			this.dataClassName = dataClassName;
		}

		public virtual String GetPropertyListClassName() 
		{
			return propertyListClassName;
		}

		public virtual void SetPropertyListClassName(String propertyListClassName) 
		{
			this.propertyListClassName = propertyListClassName;
		}

		public virtual String GetBeanName() 
		{
			return beanName;
		}

		public virtual void SetBeanName(String beanName) 
		{
			this.beanName = beanName;
		}

		public virtual IDictionary GetProperties() 
		{
			return properties;
		}

		public virtual void SetProperties(IDictionary properties) 
		{
			this.properties = properties;
		}

		public virtual DataField GetTitleField() 
		{
			return titleField;
		}

		public virtual void AddField(DataField dataField) 
		{
			fields.Add(dataField);
			fieldsMap.Add(dataField.GetFieldName(), dataField);
			dataField.SetDataInfo(this);
		}

		public virtual DataField[] GetFields() 
		{
			return (DataField[]) fields.ToArray(typeof(DataField));
		}

		public virtual DataField GetField(String fieldName) 
		{
			return (DataField) fieldsMap[fieldName];
		}

		public virtual DataField GetField(int index) 
		{
			return (DataField) fields[index];
		}

		public virtual int Size() 
		{
			return fields.Count;
		}

		public virtual void SetOrder(String[] fields, bool[] asc) 
		{
			if (fields == null) 
			{
				this.orderFields = null;
				this.orderFieldsAsc = null;
				return;
			}
			if (fields.Length != asc.Length) 
			{
				throw new Exception("Fields count must match asc count");
			}
			this.orderFields = new DataField[fields.Length];
			this.orderFieldsAsc = new bool[fields.Length];
			for (int i = 0; i < fields.Length; i++) 
			{
				this.orderFields[i] = GetField(fields[i]);
				this.orderFieldsAsc[i] = asc[i];
			}
		}

		public virtual String GetDataKeyClassName() 
		{
			return dataKeyClassName;
		}

		public virtual void SetDataKeyClassName(String dataKeyClassName) 
		{
			this.dataKeyClassName = dataKeyClassName;
		}

		public virtual DataField[] GetKeyFields() 
		{
			return keyFields;
		}

		public virtual Type GetPropertyListClass() 
		{
				return GetType().Assembly.GetType(propertyListClassName);
		}

		public virtual Type GetDataKeyClass() 
		{

			return GetType().Assembly.GetType(dataKeyClassName);
		}

		public virtual PropertyList CreatePropertyList() 
		{
				return (PropertyList) Activator.CreateInstance(GetType().Assembly.GetType(propertyListClassName));
		}

		public virtual DataContent CreateDataContent() 
		{
			return (DataContent) Activator.CreateInstance(GetType().Assembly.GetType(dataClassName));
		}

		public virtual Type GetDataContentClass() 
		{
			return GetType().Assembly.GetType(dataClassName);
		}
	}
}
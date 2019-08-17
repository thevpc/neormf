using System;
using System.Collections;
using System.Reflection;
using org.vpc.neormf.commons.util;
using org.vpc.neormf.commons.beans;
/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 août 2005
 * Time: 16:11:36
 * To change this template use File | Settings | File Templates.
 */

namespace org.vpc.neormf.commons.types
{
	public class DataContentChoiceType : AbstractChoiceType
	{
		private DataContent dataPrototype;

		public DataContentChoiceType(bool nullable, DataContent dataPrototype) : base(nullable, dataPrototype.Info().GetKeyFields()[0].getFieldType())
		{
			this.dataPrototype = dataPrototype;
		}

		public override IList Elements()
		{
			PropertyList propertyList = dataPrototype.Info().CreatePropertyList();
			DataField[] keyFields = dataPrototype.Info().GetKeyFields();
			if (keyFields.Length > 1)
			{
				throw new UnsupportedOperationException();
			}
			for (int i = 0; i < keyFields.Length; i++)
			{
				propertyList.AddProperty(keyFields[i].GetFieldName());
			}
			propertyList.AddProperty(dataPrototype.Info().GetTitleField().GetFieldName());
			ICollection collection = GetAllDataCollection(propertyList);
			ArrayList list = new ArrayList(collection == null ? 0 : collection.Count);
			if (collection != null)
			{
				for (IEnumerator i = collection.GetEnumerator(); i.MoveNext(); )
				{
					DataContent data = (DataContent) i.Current;
					list.Add(
						new Element(
							data.GetDataKey().KeyPartAt(0),
							data.GetProperty(dataPrototype.Info().GetTitleField().GetFieldName()).ToString()
							)
						);
				}
			}
			return list;
		}

//    protected abstract Collection getAllDataCollection(PropertyList list);
//
//    protected abstract DataContent getData(PropertyList list,Object key);


		protected ICollection GetAllDataCollection(PropertyList list)
		{
			try
			{
				Object client = list.GetType().Assembly.CreateInstance((String) dataPrototype.Info().GetProperties()["ClientConnectorClassName"]);
				return (ICollection) client.GetType().GetMethod("getAll" + dataPrototype.Info().GetBeanName(),
				                                                new Type[]
				                                                	{
				                                                		dataPrototype.Info().GetPropertyListClass(),
				                                                		dataPrototype.Info().GetDataContentClass(),
				                                                		typeof (OrderList)
				                                                	}
					)
					.Invoke(client, new Object[] {list, dataPrototype, new OrderList().Add(dataPrototype.Info().GetTitleField().GetFieldName())});
			}
			catch (Exception e){
				Console.WriteLine(e);
				//e.printStackTrace();
				return new ArrayList();
			}
		}

		protected DataContent GetData(PropertyList list, Object key)
		{
			try
			{
				Object client = list.GetType().Assembly.CreateInstance((String)dataPrototype.Info().GetProperties()[("ClientConnectorClassName")]);
				Object[] keyArray = (key is Object[] ) ? ((Object[]) key) : new Object[] {key};
				ConstructorInfo[] constructors = dataPrototype.Info().GetDataKeyClass().GetConstructors();
				ConstructorInfo constructor = null;
				for (int i = 0; i < constructors.Length; i++)
				{
					if (constructors[i].GetParameters().Length == keyArray.Length)
					{
						constructor = constructors[i];
						break;
					}
				}
				return (DataContent) client.GetType().GetMethod("getData" + dataPrototype.Info().GetBeanName(), new Type[]
					{
						dataPrototype.Info().GetPropertyListClass(),
						dataPrototype.Info().GetDataKeyClass()
					}).Invoke(client, new Object[] {list, constructor.Invoke(keyArray)});
			}
			catch (Exception e)
			{
				Console.WriteLine(e);
				return null;
			}
		}

		public override String GetKeyRenderer(Object key)
		{
			try
			{
				PropertyList propertyList = dataPrototype.Info().CreatePropertyList();
				propertyList.AddProperty(dataPrototype.Info().GetTitleField().GetFieldName());
				DataContent data = GetData(propertyList, key);
				return data == null ? "" : data.GetProperty(dataPrototype.Info().GetTitleField().GetFieldName()).ToString();
			}
			catch (Exception e)
			{
				Console.WriteLine(e);
			}
			return null;
		}

//		protected Object Clone()
//		{
//			try
//			{
//				return base.Clone();
//			}
//			catch (CloneNotSupportedException e)
//			{
//				throw new RuntimeException(e);
//			}
//		}

		public DataContent GetDataPrototype()
		{
			return dataPrototype;
		}

		public void SetDataPrototype(DataContent dataPrototype)
		{
			this.dataPrototype = dataPrototype;
		}
	}
}
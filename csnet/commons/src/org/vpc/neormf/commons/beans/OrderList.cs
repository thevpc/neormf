using System;
using System.Collections;
using org.vpc.neormf.commons.util;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 11:41:49
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.beans
{
	public class OrderList {
		private ArrayList set;

		public OrderList() 
		{
			this.set = new ArrayList();
		}

		public OrderList Add(String fieldName) 
		{
			return Add(new OrderItem(fieldName, true));
		}

		public OrderList Add(String fieldName, bool ascendent) 
		{
			return Add(new OrderItem(fieldName, ascendent));
		}

		public OrderList Add(OrderItem orderItem) 
		{
			if (set.IndexOf(orderItem)<0) 
			{
				set.Add(orderItem);
			}
			return this;
		}

		public OrderList AddAll(String[] fieldNames) 
		{
			for (int i = 0; i < fieldNames.Length; i++) 
			{
				Add(fieldNames[i]);
			}
			return this;
		}

		public OrderList RemoveAll(String[] fieldNames) 
		{
			for (int i = 0; i < fieldNames.Length; i++) 
			{
				set.Remove(new OrderItem(fieldNames[i], true));
			}
			return this;
		}

		public OrderList Insert(String fieldName, int position) 
		{
			return Insert(new OrderItem(fieldName, true), position);
		}

		public OrderList Insert(String fieldName, bool ascendent, int position) 
		{
			return Insert(new OrderItem(fieldName, ascendent), position);
		}

		public OrderList Insert(OrderItem orderItem, int position) 
		{
			if (set.IndexOf(orderItem)<0) 
			{
				set.Insert(position, orderItem);
			}
			return this;
		}

		public OrderList Remove(String fieldName) 
		{
			set.Remove(new OrderItem(fieldName, true));
			return this;
		}

		public bool Contains(String fieldName) 
		{
			return set.IndexOf(new OrderItem(fieldName, true))<0;
		}

		public IEnumerator GetEnumerator() 
		{
			return set.GetEnumerator();
		}

		public override String ToString() 
		{
			return "OrderList{" +
				"set=" + Utils.Dump(set) +
				"}";
		}

		public class OrderItem {
			private String fieldName;
			private bool ascendent;

			public OrderItem(String fieldName, bool ascendent) 
			{
				this.fieldName = fieldName;
				this.ascendent = ascendent;
			}

			public override bool Equals(Object obj) 
			{
				return fieldName.Equals(((OrderItem) obj).fieldName);
			}

			public override int GetHashCode() 
			{
				return fieldName.GetHashCode();
			}

			public String GetFieldName() 
			{
				return fieldName;
			}

			public bool IsAscendent() 
			{
				return ascendent;
			}

			public override String ToString() 
			{
				return "OrderItem{" +
					"fieldName='" + fieldName + "'" +
					", ascendent=" + ascendent +
					"}";
			}

		}

	}
}
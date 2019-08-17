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
 * @creation on Date: 23 mars 2004 Time: 19:22:08
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.beans
{
	public class DataContent
	{
		private Hashtable dataMap;
		private String nullDataNameString;
		private String dataNamePropertyName;
		private bool dataContentValidatable = true;


		public DataContent()
		{
			this.dataMap = new Hashtable();
		}

		public virtual Object GetProperty(String fieldName)
		{
			return this.dataMap[fieldName];
		}

		public virtual void SetProperty(String fieldName, Object value)
		{
			if (dataContentValidatable)
			{
				DataInfo _info = Info();
				if (_info != null)
				{
					DataField dataField = _info.GetField(fieldName);
					if(dataField!=null)
					{
						dataField.getFieldType().ValidateValue(value, dataField.getFieldTitle());
					}
				}
			}
			if(this.dataMap.ContainsKey(fieldName))
			{
				this.dataMap.Remove(fieldName);
			}
			this.dataMap.Add(fieldName, value);
		}

		public virtual ICollection KeySet()
		{
			return this.dataMap.Keys;
		}

		public virtual IDictionaryEnumerator GetEnumerator()
		{
			return this.dataMap.GetEnumerator();
		}

		public virtual bool ContainsProperty(String fieldName)
		{
			return this.dataMap.ContainsKey(fieldName);
		}

		public virtual void UnsetProperty(String fieldName)
		{
			this.dataMap.Remove(fieldName);
		}

		public virtual DataInfo Info()
		{
			//        throw new UnsupportedOperationException();
			return null;
		}

		public virtual String Name()
		{
			String pn = dataNamePropertyName;
			DataInfo _info = Info();
			if (pn == null)
			{
				pn = _info.GetTitleField().GetFieldName();
			}
			Object o = GetProperty(pn);
			if (o == null)
			{
				String nns = GetNullDataNameString();
				if (nns == null)
				{
					nns = "<UNKNOWN " + _info.GetBeanName() + ">";
				}
				return nns;
			}
			return Convert.ToString(o);
		}

		public override String ToString()
		{
			return Name();
		}

		public virtual String GetNullDataNameString()
		{
			return nullDataNameString;
		}

		public virtual void SetNullDataNameString(String nullDataNameString)
		{
			this.nullDataNameString = nullDataNameString;
		}

		public virtual String GetDataNamePropertyName()
		{
			return dataNamePropertyName;
		}

		public virtual void SetDataNamePropertyName(String dataNamePropertyName)
		{
			this.dataNamePropertyName = dataNamePropertyName;
		}

		public virtual DataKey GetDataKey()
		{
			throw new UnsupportedOperationException();
		}

		public virtual void RemoveDataKey()
		{
			DataInfo _info = Info();
			DataField[] fields = _info.GetKeyFields();
			for (int i = 0; i < fields.Length; i++)
			{
				dataMap.Remove(fields[i].GetFieldName());
			}
		}

		public virtual int Size()
		{
			return dataMap.Count;
		}

		public static void SetDataNamePropertyName(String dataNamePropertyName, ICollection dataContentCollection)
		{
			for (IEnumerator i = dataContentCollection.GetEnumerator(); i.MoveNext(); )
			{
				DataContent dataContent = (DataContent) i.Current;
				dataContent.SetDataNamePropertyName(dataNamePropertyName);
			}
		}

		public virtual bool IsDataContentValidatable()
		{
			return dataContentValidatable;
		}

		public virtual void SetDataContentValidatable(bool dataContentValidatable)
		{
			this.dataContentValidatable = dataContentValidatable;
		}

//		public Object clone()
//		{
//			try
//			{
//				DataContent dc = (DataContent) base.clone();
//				dc.dataMap = new HashMap(this.dataMap);
//				return dc;
//			}
//			catch (CloneNotSupportedException e)
//			{
//				throw new RuntimeException(e.getMessage());
//			}
//		}
//
		public virtual DataKey[] GetAllKeys(DataContent[] values)
		{
			DataInfo dataInfo = Info();
			DataKey[] keys = null;
			if (dataInfo == null)
			{
				keys = new DataKey[values.Length];
			}
			else
			{
				keys = (DataKey[]) Array.CreateInstance(dataInfo.GetDataKeyClass(), values.Length);
			}
			for (int i = 0; i < keys.Length; i++)
			{
				keys[i] = values[i].GetDataKey();
			}
			return keys;
		}

		//    public String dump(){
		//        return dump(false);
		//    }

//		public String dump()
//		{
//			String c = GetType().getName();
//			int x = c.lastIndexOf('.');
//			StringBuffer sb = new StringBuffer();
//			if (x > 0)
//			{
//				sb.append(c.substring(x + 1));
//
//			}
//			else
//			{
//				sb.append(c);
//			}
//
//
//			sb.append("[");
//			bool first = true;
//			for (Iterator i = dataMap.entrySet().iterator(); i.hasNext(); )
//			{
//				Map.Entry e = (Map.Entry) i.next();
//				if (first)
//				{
//					first = false;
//				}
//				else
//				{
//					sb.append(",");
//				}
//				sb.append(e.getKey());
//				sb.append("=");
//				sb.append(Utils.dump(e.getValue()));
//			}
//			sb.append("]");
//			return sb.ToString();
//		}

		public void AddAllDeclaredProperties(DataContent other)
		{
			if (other == null)
			{
			}
			DataInfo _info = Info();
			DataField[] fields = _info.GetFields();
			for (int i = 0; i < fields.Length; i++)
			{
				String n = fields[i].GetFieldName();
				if (other.ContainsProperty(n))
				{
					SetProperty(n, other.GetProperty(n));
				}
			}
		}

		public override int GetHashCode()
		{
			int hashCode = 0;
			for (IDictionaryEnumerator i = dataMap.GetEnumerator(); i.MoveNext(); )
			{
				Object k = i.Key;
				Object v = i.Value;
				hashCode += (k == null ? 0 : k.GetHashCode());
				hashCode *= ((v == null ? 0 : v.GetHashCode()));
			}
			return hashCode;
		}


		public override bool Equals(Object o)
		{
			if (o.GetType().IsSubclassOf(GetType()))
			{
				int s = dataMap.Count;
				DataContent d = (DataContent) o;
				if (d.Size() != s)
				{
					return false;
				}
				for (IDictionaryEnumerator i = dataMap.GetEnumerator(); i.MoveNext(); )
				{
					String k = (String) i.Key;
					Object v = i.Value;
					Object v2 = d.dataMap[k];
					if (v2 != null)
					{
						// field exists in Other
						if (!v2.Equals(v))
						{
							return false;
						}
					}
					else
					{
						if (v == null)
						{
							// either field does not exist or is null
							if (!d.dataMap.ContainsKey(k))
							{
								// field doest not exist
								return false;
							}
						}
						else
						{
							// v2 is null but v is not
							return false;
						}
					}
				}
				return true;
			}
			return false;
		}
	}
}
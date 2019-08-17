using System;
using System.Collections;
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
	public class PropertyList 
	{
		private Hashtable mapList;
		private ArrayList setList;

		public PropertyList() 
		{
			this.setList = new ArrayList();
			this.mapList = new Hashtable();
		}

		public PropertyList AddProperty(String fieldName) 
		{
			AddProperty(fieldName,null);
			return this;
		}

		public PropertyList AddProperty(String fieldName,Object constraints) 
		{
			if (!mapList.ContainsKey(fieldName)) 
			{
				mapList.Add(fieldName,constraints);
				setList.Add(fieldName);
			}
			return this;
		}

		public PropertyList AddAllProperties(String[] fieldNames) 
		{
			for (int i = 0; i < fieldNames.Length; i++) 
			{
				AddProperty(fieldNames[i]);
			}
			return this;
		}

		public PropertyList RemoveAllProperties(String[] fieldNames) 
		{
			for (int i = 0; i < fieldNames.Length; i++) 
			{
				RemoveProperty(fieldNames[i]);
			}
			return this;
		}

		public Object GetPropertyConstraints(String propertyName)
		{
			return mapList[propertyName];
		}
    
		//    public PropertyList insertProperty(String fieldName, int position) {
		//        return insertProperty(fieldName, null,position);
		//    }

		//    public PropertyList insertProperty(String fieldName, Object constraints,int position) {
		//        if (!set.containsKey(fieldName)) {
		//            set.put(position, constraints);
		//        }
		//        return this;
		//    }

		public PropertyList RemoveProperty(String fieldName) 
		{
			mapList.Remove(fieldName);
			setList.Remove(fieldName);
			return this;
		}

		public bool ContainsProperty(String fieldName) 
		{
			return mapList.ContainsKey(fieldName);
		}

		public IEnumerator GetEnumerator() 
		{
			return setList.GetEnumerator();
		}

		public int Size() 
		{
			return setList.Count;
		}

		public ICollection KeySet() 
		{
			return (ICollection)setList.Clone();
		}

		public void Clear() 
		{
			setList.Clear();
			mapList.Clear();
		}

		public String[] ToArray() 
		{
			return (String[]) setList.ToArray(typeof(String));
		}

		public override String ToString() 
		{
			return "PropertyList{" +
				"set=" + setList +
				"}";
		}

		public virtual void AddAllProperties() 
		{
		}

		public virtual DataInfo Info()
		{
			//        throw new UnsupportedOperationException();
			return null;
		}

	}
}

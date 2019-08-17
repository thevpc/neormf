using System;
using System.Text;
using org.vpc.neormf.commons.util;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 15:50:39
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.beans
{
	public abstract class DataKey {
        public abstract Object KeyPartAt(int position);

		public abstract int KeySize();

		public override int GetHashCode() 
		{
			int hash = 17;
			for (int i = KeySize() - 1; i >= 0; i--) 
			{
				hash = 31 * hash + KeyPartAt(i).GetHashCode();
			}
			return hash;
		}

		public override bool Equals(Object obj) 
		{
			if (obj == null || GetType() != obj.GetType()) 
			{
				return false;
			}
			for (int i = KeySize() - 1; i >= 0; i--) 
			{
				Object p1 = KeyPartAt(i);
				Object p2 = ((DataKey) obj).KeyPartAt(i);
				if (!p1.Equals(p2)) 
				{
					return false;
				}
			}
			return true;
		}

		public override String ToString()
		{
			StringBuilder sb=new StringBuilder();
			sb.Append(this.GetType().Name);
			sb.Append("(");
			int max=KeySize();
			for (int i = 0; i < max; i++) 
			{
				if(i>0)
				{
					sb.Append(",");
				}
				sb.Append(Utils.Dump(KeyPartAt(i)));
			}
			sb.Append(")");
			return sb.ToString();
		}

		public virtual DataInfo Info()
		{
			//        throw new UnsupportedOperationException();
			return null;
		}

	}
}
using System;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 09:46:05
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.types
{
	public abstract class DataType
	{
		private bool nullable;

		protected DataType(bool nullable)
		{
			this.nullable = nullable;
		}

		public bool IsNullable()
		{
			return nullable;
		}

		public void SetNullable(bool nullable)
		{
			this.nullable = nullable;
		}


		public virtual Object ValidateValue(Object obj, String fieldName)
		{
			if (!nullable && obj == null)
			{
				throw new DataException("org.vpc.neormf.commons.types.DataType.Null.Message", new Object[] {fieldName});
			}
			return obj;
		}

		public abstract Type ToImplType();


		public abstract int GetTypeId();

	}
}
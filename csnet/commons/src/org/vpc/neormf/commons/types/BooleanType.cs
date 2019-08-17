using System;
using org.vpc.neormf.commons.util;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:26:31
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.types
{
	public class BooleanType : DataType
	{
		public static int TYPE = 1;
		public static BooleanType BOOL_NULLABLE = new BooleanType(true);
		public static BooleanType BOOL_NON_NULLABLE = new BooleanType(false);

		public BooleanType(bool nullable) : base(nullable)
		{
		}

		public override Object ValidateValue(Object obj, String fieldName)
		{
			obj = base.ValidateValue(obj, fieldName);
			if (obj == null)
			{
				return null;
			}
			return (Boolean) Utils.CastTo(obj, typeof(bool));
		}

		public override Type ToImplType()
		{
			return typeof(bool);
		}

		public override int GetTypeId()
		{
			return TYPE;
		}

	}
}
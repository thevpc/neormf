using System;
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
	public class AnyType : DataType
	{
		public static int TYPE = -1;
		private Type javaClass;

		public AnyType() : this(typeof (Object), true)
		{
		}

		public AnyType(Type javaClass, bool nullable) : base(nullable)
		{
			this.javaClass = javaClass;
		}

		public override Type ToImplType()
		{
			return javaClass;
		}

		public override int GetTypeId()
		{
			return TYPE;
		}

	}
}
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
	public class IntType : DataType
	{
		public static IntType INT_NULLABLE = new IntType(true);
		public static IntType INT_NON_NULLABLE= new IntType(false);
		public const int TYPE = 2;
		private int minValue = int.MinValue;
		private int maxValue = int.MaxValue;

		public IntType():base(true) 
		{
		}

		public IntType(bool nullable) :base(nullable)
		{
        
		}

		public IntType(bool nullable, int limit, bool isSuperiorLimit) :base(nullable)
		{
			if (isSuperiorLimit) 
			{
				maxValue = limit;
			} 
			else 
			{
				minValue = limit;
			}
		}

		public IntType(bool nullable, int minValue, int maxValue) :base(nullable)
		{
			this.minValue = minValue;
			this.maxValue = maxValue;
		}

		public override Object ValidateValue(Object obj, String fieldName)
		{
			obj = base.ValidateValue(obj, fieldName);
			if (obj == null) 
			{
				return null;
			}
			int i = (int) Utils.CastTo(obj, typeof(int));
			if (i < this.minValue) 
			{
				throw new DataException("org.vpc.neormf.commons.types.IntType.TooLow.Message", new Object[]{fieldName});
			}
			if (i > this.maxValue) 
			{
				throw new DataException("org.vpc.neormf.commons.types.IntType.TooHight.Message", new Object[]{fieldName});
			}
			return i;
		}

		public override Type ToImplType() 
		{
			return typeof(int);
		}

		public override int GetTypeId() 
		{
			return TYPE;
		}

		public int GetMinValue()
		{
			return minValue;
		}
		
		public int GetMaxValue()
		{
			return maxValue;
		}
	}
}

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
	public class DoubleType : DataType
	{
		public static DoubleType DOUBLE_NULLABLE = new DoubleType(true);
		public static DoubleType DOUBLE_NON_NULLABLE = new DoubleType(false);
		public static int TYPE = 3;
		private double minValue = Double.NaN;
		private double maxValue = Double.NaN;

		public DoubleType() : this(true)
		{
		}

		public DoubleType(bool nullable) : base(nullable)
		{
		}

		public DoubleType(bool nullable, double limit, bool isSuperiorLimit) : base(nullable)
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

		public DoubleType(bool nullable, double minValue, double maxValue):base(nullable)
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
			Double dobj = (Double) Utils.CastTo(obj, typeof(Double));
			double d = dobj;
			if (!Double.IsNaN(this.minValue) && d < this.minValue)
			{
				throw new DataException("org.vpc.neormf.commons.types.DoubleType.TooLow.Message", new Object[] {fieldName});
			}
			if (!Double.IsNaN(this.maxValue) && d > this.maxValue)
			{
				throw new DataException("org.vpc.neormf.commons.types.DoubleType.TooHight.Message", new Object[] {fieldName});
			}
			return dobj;
		}

		public override Type ToImplType()
		{
			return typeof(double);
		}

		public override int GetTypeId()
		{
			return TYPE;
		}

		public double GetMinValue()
		{
			return minValue;
		}
		
		public double GetMaxValue()
		{
			return maxValue;
		}

	}
}
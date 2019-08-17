using System;
using org.vpc.neormf.commons.sql;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 14:41:43
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.types.converters
{
	public class IntegerToBoolean : DataTypeConverter 
	{
		public static DataTypeConverter INTEGER_TO_BOOLEAN = new IntegerToBoolean(false);

		private DataType sql = new IntType(true);
		private DataType business = BooleanType.BOOL_NULLABLE;

		private bool nullIsFalse;

		public IntegerToBoolean() :this(false)
		{
			;
		}
    
		public IntegerToBoolean(bool nullIsFalse) 
		{
			this.nullIsFalse=nullIsFalse;
		}

		public override DataType GetSQLDataType() 
		{
			return sql;
		}

		public override DataType GetBusinessDataType() 
		{
			return business;
		}

		public override Object BusinessToSQL(Object businessObject) 
		{
			if(nullIsFalse)
			{
				if(businessObject == null)
				{
					return 0;
				}
				return ((Boolean) businessObject) ? 1 : 0;
			}
			else
			{
				if(businessObject == null)
				{
					return null;
				}
				return  ((Boolean) businessObject) ? 1 : 0;
			}
		}

		public override Object SqlToBusiness(Object sqlObject) 
		{
			if(sqlObject == null || sqlObject is DBNull)
			{
				if(nullIsFalse)
				{
					return false;
				}
				return null;
			}
			if(sqlObject.Equals(1))
			{
				return true;
			}
			if(sqlObject.Equals(0))
			{
				return false;
			}
			throw new NoSuchElementException("Unexpected sql value '"+sqlObject+"' ; possible values are {0,1}");
			//        return sqlObject == null ? null : trueChar.Equals(sqlObject) ? Boolean.TRUE : falseChar.Equals(sqlObject) ? Boolean.FALSE
			//                : null; // throw exception ???
		}
	}
}
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
	public class String1ToBoolean : DataTypeConverter 
	{
		public static DataTypeConverter STRING1_YN_TO_BOOLEAN = new String1ToBoolean('Y', 'N',false);

		private DataType sql = new StringType(true, 0, 1,false);
		private DataType business = BooleanType.BOOL_NULLABLE;

		private String trueChar;
		private String falseChar;
		private bool nullIsFalse;

		public String1ToBoolean(char trueChar, char falseChar) :this(trueChar, falseChar,false)
		{
			;
		}
    
		public String1ToBoolean(char trueChar, char falseChar,bool nullIsFalse) 
		{
			this.trueChar = new String(new char[]{trueChar});
			this.falseChar = new String(new char[]{falseChar});
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
					return falseChar;
				}
				return ((Boolean) businessObject) ? trueChar : falseChar;
			}
			else
			{
				if(businessObject == null)
				{
					return null;
				}
				return businessObject == null ? null : ((Boolean) businessObject) ? trueChar : falseChar;
			}
		}

		public override Object SqlToBusiness(Object sqlObject) 
		{
			if(sqlObject == null)
			{
				if(nullIsFalse)
				{
					return false;
				}
				return null;
			}
			if(trueChar.Equals(sqlObject))
			{
				return true;
			}
			if(falseChar.Equals(sqlObject))
			{
				return false;
			}
			throw new NoSuchElementException("Unexpected sql value '"+sqlObject+"' ; possible values are {'"+trueChar+"','"+falseChar+"'}");
			//        return sqlObject == null ? null : trueChar.Equals(sqlObject) ? Boolean.TRUE : falseChar.Equals(sqlObject) ? Boolean.FALSE
			//                : null; // throw exception ???
		}
	}
}
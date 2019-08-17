using System.Data;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de Types.
	/// </summary>
	public class Types
	{
		private Types()
		{
		}
		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>BIT</code>.
		 */
		public const int BIT 		=  -7;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>TINYINT</code>.
		 */
		public const int TINYINT 	=  -6;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>SMALLINT</code>.
		 */
		public const int SMALLINT	=   5;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>INTEGER</code>.
		 */
		public const int INTEGER 	=   4;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>BIGINT</code>.
		 */
		public const int BIGINT 		=  -5;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>FLOAT</code>.
		 */
		public const int FLOAT 		=   6;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>REAL</code>.
		 */
		public const int REAL 		=   7;


		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>DOUBLE</code>.
		 */
		public const int DOUBLE 		=   8;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>NUMERIC</code>.
		 */
		public const int NUMERIC 	=   2;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>DECIMAL</code>.
		 */
		public const int DECIMAL		=   3;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>CHAR</code>.
		 */
		public const int CHAR		=   1;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>VARCHAR</code>.
		 */
		public const int VARCHAR 	=  12;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>LONGVARCHAR</code>.
		 */
		public const int LONGVARCHAR 	=  -1;


		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>DATE</code>.
		 */
		public const int DATE 		=  91;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>TIME</code>.
		 */
		public const int TIME 		=  92;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>TIMESTAMP</code>.
		 */
		public const int TIMESTAMP 	=  93;


		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>BINARY</code>.
		 */
		public const int BINARY		=  -2;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>VARBINARY</code>.
		 */
		public const int VARBINARY 	=  -3;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>LONGVARBINARY</code>.
		 */
		public const int LONGVARBINARY 	=  -4;

		/**
		 * <P>The constant in the Java programming language, sometimes referred
		 * to as a type code, that identifies the generic SQL type 
		 * <code>NULL</code>.
		 */
		public const int NULL		=   0;

		/**
		 * The constant in the Java programming language that indicates
		 * that the SQL type is database-specific and
		 * gets mapped to a Java object that can be accessed via
		 * the methods <code>getObject</code> and <code>setObject</code>.
		 */
		public const int OTHER		= 1111;

        

		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>JAVA_OBJECT</code>.
		 * @since 1.2
		 */
		public const int JAVA_OBJECT         = 2000;

		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>DISTINCT</code>.
		 * @since 1.2
		 */
		public const int DISTINCT            = 2001;
	
		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>STRUCT</code>.
		 * @since 1.2
		 */
		public const int STRUCT              = 2002;

		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>ARRAY</code>.
		 * @since 1.2
		 */
		public const int ARRAY               = 2003;

		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>BLOB</code>.
		 * @since 1.2
		 */
		public const int BLOB                = 2004;

		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>CLOB</code>.
		 * @since 1.2
		 */
		public const int CLOB                = 2005;

		/**
		 * The constant in the Java programming language, sometimes referred to
		 * as a type code, that identifies the generic SQL type
		 * <code>REF</code>.
		 * @since 1.2
		 */
		public const int REF                 = 2006;
        
		/**
		 * The constant in the Java programming language, somtimes referred to
		 * as a type code, that identifies the generic SQL type <code>DATALINK</code>.
		 *
		 * @since 1.4
		 */
		public const int DATALINK = 70;

		/**
		 * The constant in the Java programming language, somtimes referred to
		 * as a type code, that identifies the generic SQL type <code>BOOLEAN</code>.
		 *
		 * @since 1.4
		 */
		public const int BOOLEAN = 16;

		public static SqlDbType ToSqlDbType(int type)
		{
			switch(type)
			{
				case Types.ARRAY:
					{
						return SqlDbType.NText;
					}
				case Types.BIGINT:
					{
						return SqlDbType.BigInt;
					}
				case Types.BINARY:
					{
						return SqlDbType.Binary;
					}
				case Types.BIT:
					{
						return SqlDbType.Bit;
					}
				case Types.BLOB:
					{
						return SqlDbType.VarBinary;
					}
				case Types.BOOLEAN:
					{
						return SqlDbType.Bit;
					}
				case Types.CHAR:
					{
						return SqlDbType.Char;
					}
				case Types.CLOB:
					{
						return SqlDbType.VarBinary;
					}
				case Types.DATALINK:
					{
						return SqlDbType.VarBinary;
					}
				case Types.DATE:
					{
						return SqlDbType.DateTime;
					}
				case Types.DECIMAL:
					{
						return SqlDbType.Decimal;
					}
				case Types.DISTINCT:
					{
						return SqlDbType.VarBinary;
					}
				case Types.DOUBLE:
					{
						return SqlDbType.Float;
					}
				case Types.FLOAT:
					{
						return SqlDbType.Float;
					}
				case Types.INTEGER:
					{
						return SqlDbType.Int;
					}
				case Types.JAVA_OBJECT:
					{
						return SqlDbType.Variant;
					}
				case Types.LONGVARBINARY:
					{
						return SqlDbType.VarBinary;
					}
				case Types.LONGVARCHAR:
					{
						return SqlDbType.NText;
					}
				case Types.NULL:
					{
						return SqlDbType.Variant;
					}
				case Types.NUMERIC:
					{
						return SqlDbType.Real;
					}
				case Types.OTHER:
					{
						return SqlDbType.Variant;
					}
				case Types.REF:
					{
						return SqlDbType.Variant;
					}
				case Types.SMALLINT:
					{
						return SqlDbType.SmallInt;
					}
				case Types.STRUCT:
					{
						return SqlDbType.Variant;
					}
				case Types.TIME:
					{
						return SqlDbType.DateTime;
					}
				case Types.TIMESTAMP:
					{
						return SqlDbType.Timestamp;
					}
				case Types.TINYINT:
					{
						return SqlDbType.TinyInt;
					}
				case Types.VARBINARY:
					{
						return SqlDbType.VarBinary;
					}
				case Types.VARCHAR:
					{
						return SqlDbType.VarChar;
					}
			}
			return SqlDbType.Variant;
		}
	}
}

using System;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 14:39:14
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.types.converters{
	public abstract class DataTypeConverter 
	{
		public abstract DataType GetSQLDataType();

		public abstract DataType GetBusinessDataType();

		public abstract Object BusinessToSQL(Object businessObject);

		public abstract Object SqlToBusiness(Object sqlObject);
	}
}
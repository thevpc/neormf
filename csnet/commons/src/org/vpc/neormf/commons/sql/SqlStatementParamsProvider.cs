using org.vpc.neormf.commons.jwrapper;
/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Root Optimizer
 * @creation on Date: 29 juil. 2004 Time: 17:06:00
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.sql
{
	public interface SqlStatementParamsProvider 
	{
		/**
		 * populates the statement starting from the given index
		 * @param statement
		 * @param startIndex
		 * @return the number of parameters provided
		 */
		int PopulateStatement(PreparedStatement statement,int startIndex) ;
	}
}
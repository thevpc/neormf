using System;
/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 20 avr. 2004 Time: 16:28:06
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.beans
{
	public class FieldQuery :FieldFormula 
	{
		private String query;
		private DataInfo dataInfo;

		public FieldQuery(String query, DataInfo dataInfo) 
		{
			this.query = query;
			this.dataInfo = dataInfo;
		}

		public Object Compute(DataKey dataKey) 
		{
			return null;//return "SELECT "+pattern+" FROM "+dataInfo.get ;
		}
		
		public String GetQuery(){
			return query;
		}
		
		public DataInfo getDataInfo(){
			return dataInfo;
		}
	}
}
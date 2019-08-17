using System;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 11:45:15
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.beans
{
	public class NamedDataContent : DataContent
	{
		private String dataContentName;

		public NamedDataContent(String dataContentName)
		{
			this.dataContentName = dataContentName;
		}

		public String GetDataContentName()
		{
			return dataContentName;
		}

		public void SetDataContentName(String dataContentName)
		{
			this.dataContentName = dataContentName;
		}
	}
}
using System;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 09:48:18
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.sql
{
	public class DriverNotFoundException : Exception
	{
		public DriverNotFoundException()
		{
		}

		public DriverNotFoundException(String reason) : base(reason)
		{
		}

	}
}
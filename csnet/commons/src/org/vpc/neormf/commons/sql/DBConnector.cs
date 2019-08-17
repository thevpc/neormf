using System;
using System.Data.SqlClient;
/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Root Optimizer
 * @creation on Date: 29 avr. 2004 Time: 15:04:12
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.sql
{
	public class DBConnector 
	{
		private SqlConnection connection;
		private String callerPrincipalName="?";

		public DBConnector() 
		{
		}

		public DBConnector(DBConnector other) 
		{
			if(other!=null)
			{
				SetConnection(other.GetConnection());
				SetCallerPrincipalName(other.GetCallerPrincipalName());
			}
		}

		public SqlConnection GetConnection() 
		{
			return connection;
		}

		public void SetConnection(SqlConnection connection) 
		{
			this.connection = connection;
		}


		public String GetCallerPrincipalName() 
		{
			return callerPrincipalName;
		}

		public void SetCallerPrincipalName(String callerPrincipalName) 
		{
			this.callerPrincipalName = callerPrincipalName;
		}
		//    public void create(DataContent)
		//    public void delete(DataContent)
		//    public void get(DataKey)
		//    public void update(DataContent)
		//    public void getAll(PropertyList,Criteria,Order)
	}
}
using System;
using System.Collections;
using System.Data;
using System.Data.SqlClient;
using System.Text;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description r�sum�e de PreparedStatement.
	/// </summary>
	public class PreparedStatement
	{
		private SqlConnection connection;
		private String query;
		private ArrayList parameters=new ArrayList();
		public PreparedStatement(SqlConnection cnx,String query)
		{
			this.connection=cnx;
			StringBuilder sb=new StringBuilder();
			int index=1;
			for(int i=0;i<query.Length;i++)
			{
				char c=query[i];
				if(c=='?')
				{
					sb.Append(GetParamName(index));
					index++;
				}else
				{
					sb.Append(c);
				}
			}
			this.query=sb.ToString();
			
		}
		
		private String GetParamName(int index)
		{
			return "@p"+index+"_";
		}

		public void SetString(int pos, String value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.VarChar);
			p.Value=(value!=null) ? (Object)value:(Object)DBNull.Value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetDouble(int pos, double value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.Float);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void setShort(int pos, short value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.Decimal);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}
		

		public void SetLong(int pos, long value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.BigInt);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetFloat(int pos, float value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.Float);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}
		
		public void SetInt(int pos, int value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.Int);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetDate(int pos, DateTime value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.DateTime);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void setNull(int pos,int type)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),type);
			p.Value=DBNull.Value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetNull(int pos, SqlDbType type)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),type);
			p.Value=DBNull.Value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetBoolean(int pos, bool value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.Int);
			p.Value=value ? 1 : 0;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetTime(int pos, TimeSpan value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.DateTime);
			p.Value=new DateTime(1900,1,1,value.Hours,value.Minutes,value.Seconds);
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetTimestamp(int pos, DateTime value)
		{
			SqlParameter p = new SqlParameter(GetParamName(pos),SqlDbType.DateTime);
			p.Value=value;
			if(pos>=1 && (pos-1)<parameters.Count)
			{
				parameters.Insert(pos-1, p);
			}
			else
			{
				parameters.Add(p);
			}
		}

		public void SetString(String pos, String value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.VarChar);
			p.Value=(value!=null) ? (Object)value:(Object)DBNull.Value;
			parameters.Add(p);
		}

		public void SetDouble(String pos, double value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.Float);
			p.Value=value;
			parameters.Add(p);
		}

		public void SetShort(String pos, short value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.Decimal);
			p.Value=value;
			parameters.Add(p);
		}

		public void SetLong(String pos, long value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.BigInt);
			p.Value=value;
			parameters.Add(p);
		}

		public void SetFloat(String pos, float value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.Float);
			p.Value=value;
			parameters.Add(p);
		}

		public void SetInt(String pos, int value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.Int);
			p.Value=value;
			parameters.Add(p);
		}

		public void SetBoolean(String pos, bool value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.Int);
			p.Value=value?1:0;
			parameters.Add(p);
		}

		public void SetNull(String pos, int type)
		{
			SqlParameter p = new SqlParameter(pos,type);
			p.Value=DBNull.Value;
			parameters.Add(p);
		}

		public void SetDate(String pos, DateTime value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.DateTime);
			p.Value=value;
			parameters.Add(p);
		}

		public void SetTime(String pos, TimeSpan value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.DateTime);
			p.Value=new DateTime(1900,1,1,value.Hours,value.Minutes,value.Seconds);
			parameters.Add(p);
		}

		public void SetTimestamp(String pos, DateTime value)
		{
			SqlParameter p = new SqlParameter(pos,SqlDbType.DateTime);
			p.Value=value;
			parameters.Add(p);
		}

		public int ExecuteUpdateStoredProcedure()
		{
			//if the provided connection is not open, we will open it
			if (connection.State != ConnectionState.Open)
			{
				connection.Open();
			}

			SqlCommand cmd = new SqlCommand();
			cmd.Connection=connection;
			cmd.CommandText=query;
			cmd.CommandType=CommandType.StoredProcedure;
			foreach (SqlParameter p in parameters)
			{
				//check for derived output value with no value assigned
				if ((p.Direction == ParameterDirection.InputOutput) && (p.Value == null))
				{
					p.Value = DBNull.Value;
				}
				
				cmd.Parameters.Add(p);
			}

			//finally, execute the command.
			int retval = cmd.ExecuteNonQuery();
			
			// detach the SqlParameters from the command object, so they can be used again.
			cmd.Parameters.Clear();
			return retval;
		}

		public int ExecuteUpdate()
		{
			//if the provided connection is not open, we will open it
			if (connection.State != ConnectionState.Open)
			{
				connection.Open();
			}

			SqlCommand cmd = new SqlCommand();
			cmd.Connection=connection;
			cmd.CommandText=query;
			cmd.CommandType=CommandType.Text;
			foreach (SqlParameter p in parameters)
			{
				//check for derived output value with no value assigned
				if ((p.Direction == ParameterDirection.InputOutput) && (p.Value == null))
				{
					p.Value = DBNull.Value;
				}
				
				cmd.Parameters.Add(p);
			}

			//finally, execute the command.
			int retval = cmd.ExecuteNonQuery();
			
			// detach the SqlParameters from the command object, so they can be used again.
			cmd.Parameters.Clear();
			return retval;
		}

		public ResultSet ExecuteQuery()
		{
			//if the provided connection is not open, we will open it
			if (connection.State != ConnectionState.Open)
			{
				connection.Open();
			}

			SqlCommand cmd = new SqlCommand();
			cmd.CommandText=query;
			cmd.Connection=connection;
			cmd.CommandType=CommandType.Text;
			foreach (SqlParameter p in parameters)
			{
				//check for derived output value with no value assigned
				if ((p.Direction == ParameterDirection.InputOutput) && (p.Value == null))
				{
					p.Value = DBNull.Value;
				}
				
				cmd.Parameters.Add(p);
			}
			//Reader ??
			//finally, execute the command.
			//SqlDataReader r = cmd.ExecuteReader();
			//// detach the SqlParameters from the command object, so they can be used again.
			//cmd.Parameters.Clear();
			//return new ResultSetForDataSet(this,r);
			
			//create the DataAdapter & DataSet
			SqlDataAdapter da = new SqlDataAdapter(cmd);
			DataSet ds = new DataSet();
			//fill the DataSet using default values for DataTable names, etc.
			da.Fill(ds);
			// detach the SqlParameters from the command object, so they can be used again.
			cmd.Parameters.Clear();
			return new ResultSetForDataSet(this,ds);
		}

		public ResultSet ExecuteQueryStoredProcedure()
		{
			//if the provided connection is not open, we will open it
			if (connection.State != ConnectionState.Open)
			{
				connection.Open();
			}

			SqlCommand cmd = new SqlCommand();
			cmd.CommandText=query;
			cmd.Connection=connection;
			cmd.CommandType=CommandType.StoredProcedure;
			foreach (SqlParameter p in parameters)
			{
				//check for derived output value with no value assigned
				if ((p.Direction == ParameterDirection.InputOutput) && (p.Value == null))
				{
					p.Value = DBNull.Value;
				}
				
				cmd.Parameters.Add(p);
			}
			//Reader ??
			//finally, execute the command.
			//SqlDataReader r = cmd.ExecuteReader();
			//// detach the SqlParameters from the command object, so they can be used again.
			//cmd.Parameters.Clear();
			//return new ResultSetForDataSet(this,r);
			
			//create the DataAdapter & DataSet
			SqlDataAdapter da = new SqlDataAdapter(cmd);
			DataSet ds = new DataSet();
			//fill the DataSet using default values for DataTable names, etc.
			da.Fill(ds);
			// detach the SqlParameters from the command object, so they can be used again.
			cmd.Parameters.Clear();
			return new ResultSetForDataSet(this,ds);
		}

		public void Close()
		{
			parameters=null;
		}

	}
}

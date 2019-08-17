using System;
using System.Data;
using System.Data.SqlClient;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de ResultSet.
	/// </summary>
	public class ResultSetForDataReader : ResultSet
	{
		private PreparedStatement stmt;
		private SqlDataReader reader;
		private int lastColumn;
		public ResultSetForDataReader(PreparedStatement stmt,SqlDataReader reader)
		{
			this.stmt=stmt;
			this.reader=reader;
		}
		
		public PreparedStatement GetPreparedStatement(){
			return stmt;
		}
		
		public bool MoveNext()
		{
			Console.WriteLine("MoveNext");
			return reader.NextResult();
		}

		public int GetInt(int pos)
		{
			lastColumn=pos;
			return reader.GetInt32(pos);
		}

		public double GetDouble(int pos)
		{
			lastColumn=pos;
			return reader.GetDouble(pos);
		}

		public long GetLong(int pos)
		{
			lastColumn=pos;
			return reader.GetInt64(pos);
		}

		public String GetString(int pos)
		{
			lastColumn=pos;
			return reader.GetString(pos);
		}

		public bool GetBoolean(int pos)
		{
			lastColumn=pos;
			return reader.GetBoolean(pos);
		}

		public DateTime GetDate(int pos)
		{
			lastColumn=pos;
			return reader.GetDateTime(pos);
		}

		public TimeSpan GetTime(int pos)
		{
			lastColumn=pos;
			return reader.GetDateTime(pos).TimeOfDay;
		}

		public DateTime GetTimestamp(int pos)
		{
			lastColumn=pos;
			return reader.GetDateTime(pos);
		}

		public Object GetObject(int pos)
		{
			lastColumn=pos;
			return reader.GetSqlValue(pos);
		}

		public bool WasNull()
		{
			return reader.IsDBNull(lastColumn);
		}

		public void Close()
		{
			reader.Close();
			reader=null;
		}
	}
}
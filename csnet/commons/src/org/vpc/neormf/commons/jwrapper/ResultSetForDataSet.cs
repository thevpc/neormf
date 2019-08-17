using System;
using System.Data;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de ResultSet.
	/// </summary>
	public class ResultSetForDataSet :ResultSet
	{
		PreparedStatement stmt;
		DataSet dataSet;
		int current=-1;
		bool lastWasNull;
		public ResultSetForDataSet(PreparedStatement stmt,DataSet dataSet)
		{
			this.stmt=stmt;
			this.dataSet=dataSet;
		}
		
		public PreparedStatement GetPreparedStatement(){
			return stmt;
		}

		public bool MoveNext()
		{
			current++;
			return current<dataSet.Tables[0].Rows.Count;
		}

		public int GetInt(int pos)
		{
			Object o=GetObject(pos);
			return  lastWasNull?0:Convert.ToInt32(o);
		}

		public long GetLong(int pos)
		{
			Object o=GetObject(pos);
			return  lastWasNull?0:Convert.ToInt64(o);
		}

		public double GetDouble(int pos)
		{
			Object o=GetObject(pos);
			return  lastWasNull?0:Convert.ToDouble(o);
		}

		public String GetString(int pos)
		{
			Object o=GetObject(pos);
			return  Convert.ToString(o);
		}

		public DateTime GetDate(int pos)
		{
			Object o=GetObject(pos);
			return  o==null ? DateTime.MinValue :Convert.ToDateTime(o);
		}

		public bool GetBoolean(int pos)
		{
			Object o=GetObject(pos);
			return  o==null ? false :Convert.ToBoolean(o);
		}

		public TimeSpan GetTime(int pos)
		{
			Object o=GetObject(pos);
			return  o==null ? TimeSpan.MinValue :Convert.ToDateTime(o).TimeOfDay;
		}

		public DateTime GetTimestamp(int pos)
		{
			Object o=GetObject(pos);
			return  o==null ? DateTime.MinValue : Convert.ToDateTime(o);
		}

		public Object GetObject(int pos)
		{
			Object o=dataSet.Tables[0].Rows[current][pos-1];
			lastWasNull=(o is DBNull);
			return lastWasNull ? null : o;
		}

		public bool WasNull()
		{
			return lastWasNull;
		}

		public void Close()
		{
			dataSet=null;
		}

	}
}
using System;
using System.Data;

namespace org.vpc.neormf.commons.jwrapper
{
	/// <summary>
	/// Description résumée de ResultSet.
	/// </summary>
	public interface ResultSet
	{
		bool MoveNext();

		int GetInt(int pos);

		double GetDouble(int pos);

		long GetLong(int pos);

		String GetString(int pos);

		bool GetBoolean(int pos);

		DateTime GetDate(int pos);

		TimeSpan GetTime(int pos);

		DateTime GetTimestamp(int pos);

		Object GetObject(int pos);

		bool WasNull();

		void Close();
	}
}

using System;
using System.Collections;
using System.Data.SqlClient;
using System.Text;
using org.vpc.neormf.commons.jwrapper;
using org.vpc.neormf.commons.sql;
using org.vpc.neormf.commons.util;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 26 mars 2004 Time: 17:18:46
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */

namespace org.vpc.neormf.commons.beans
{
	public class Criteria : SqlStatementParamsProvider
	{
		private String whereClause;

		private String joins;

		private bool distinct;

		private int minRowIndex = -1;

		private int maxRowIndex = -1;

		private Hashtable parameters = new Hashtable();

		public Criteria()
		{
		}

		public Criteria(String whereClause)
		{
			this.whereClause = whereClause;
		}

		public String GetWhereClause()
		{
			return whereClause;
		}

		public Criteria SetWhereClause(String whereClause)
		{
			this.whereClause = whereClause;
			return this;
		}

		public String GetJoins()
		{
			return joins;
		}

		public Criteria SetJoins(String joins)
		{
			this.joins = joins;
			return this;
		}

		public Criteria SetString(int pos, String value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(Types.VARCHAR);
			parameters.Add(pos, p);
			return this;
		}

		public Criteria SetDouble(int pos, double value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(Types.DOUBLE);
			parameters.Add(pos, p);
			return this;
		}

		public Criteria SetInt(int pos, int value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(Types.INTEGER);
			parameters.Add(pos, p);
			return this;
		}

		public Criteria SetDate(int pos, DateTime value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(Types.DATE);
			parameters.Add(pos, p);
			return this;
		}

		public Criteria SetBoolean(int pos, bool value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value ? (1) : (0));
			p.SetSqlType(Types.INTEGER);
			parameters.
				Add((pos), p);
			return this;
		}

		public Criteria SetTime(int pos, TimeSpan value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(Types.TIME);
			parameters.
				Add((pos), p);
			return this;
		}

		public Criteria SetTimestamp(int pos, DateTime value)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(Types.TIMESTAMP);
			parameters.
				Add((pos), p);
			return this;
		}


		public int PopulateStatement(PreparedStatement statement, int startPosition)
		{
			int count = 0;
			for (IDictionaryEnumerator i = parameters.GetEnumerator(); i.MoveNext(); )
			{
				count += ((SqlParam) i.Value).PopulateStatement(statement, startPosition);
			}
			return
				count;
		}

		public SqlParameter[] SqlParameters
		{
			get
			{
				SqlParameter[] all=new SqlParameter[parameters.Count];
				int count = 0;
				for (IDictionaryEnumerator i = parameters.GetEnumerator(); i.MoveNext(); )
				{
					all[count++]=((SqlParam) i.Value).ToSqlParameter();
				}
				return all;
			}
		}

		public bool IsNamesBased()
		{
			if (parameters.Count > 0)
			{
				IDictionaryEnumerator i = parameters.GetEnumerator();
				i.MoveNext();
				Object o = i.Key;
				return o is String;
			}
			else
			{
				return true;
			}
		}


		public int GetMinRowIndex()
		{
			return minRowIndex;
		}

		public void SetMinRowIndex(int minRowIndex)
		{
			this.minRowIndex = minRowIndex;
		}

		public int GetMaxRowIndex()
		{
			return maxRowIndex;
		}

		public void SetMaxRowIndex(int maxRowIndex)
		{
			this.maxRowIndex = maxRowIndex;
		}

		public Criteria SetObject(int pos, Object value, DataField dataField)
		{
			SqlParam p = new SqlParam();
			p.SetPos(pos);
			p.SetValue(value);
			p.SetSqlType(SQLUtils.ToSqlType(dataField.getSqlType()));
			parameters.Add((pos), p);
			return this;
		}

		public int GetParamCount()
		{
			return parameters.Count;
		}

		public Criteria Merge(Criteria other)
		{
			if (other == null)
			{
				return this;
			}
			Criteria newCriteria = new Criteria();

			StringBuilder sb = new StringBuilder();
			if (whereClause != null)
			{
				sb.Append(whereClause);
			}
			if (other.whereClause != null)
			{
				if (sb.Length > 0)
				{
					sb.Append(" AND ");
				}
				sb.Append(other.whereClause);
			}
			newCriteria.whereClause = sb.Length == 0 ? null : sb.ToString();

			sb = new StringBuilder();
			if (joins != null)
			{
				sb.Append(joins);
			}
			if (other.joins != null)
			{
				sb.Append(" ");
				sb.Append(other.joins);
			}
			newCriteria.joins = sb.Length == 0 ? null : sb.ToString();
			int pos = 1;
			for (int i = 0; i < parameters.Count; i++)
			{
				newCriteria.parameters.Add((pos), parameters[i + 1]);
				pos++;
			}
			for (int i = 0; i < other.parameters.Count; i++)
			{
				SqlParam p = (SqlParam) other.parameters[i + 1];
				p.SetPos(pos);
				newCriteria.parameters.Add((pos), p);
				pos++;
			}
			if (minRowIndex >= 0)
			{
				newCriteria.minRowIndex = minRowIndex;
			}
			if (other.minRowIndex >= 0)
			{
				newCriteria.minRowIndex = other.minRowIndex;
			}
			if (maxRowIndex >= 0)
			{
				newCriteria.maxRowIndex = maxRowIndex;
			}
			if (other.maxRowIndex >= 0)
			{
				newCriteria.maxRowIndex = other.maxRowIndex;
			}
			if (distinct || other.IsDistinct())
			{
				newCriteria.distinct = other.distinct;
			}
			return newCriteria;
		}

		public bool IsDistinct()
		{
			return distinct;
		}

		public void SetDistinct(bool distinct)
		{
			this.distinct = distinct;
		}

		public String SqlParametersQuery
		{
			get
			{
				StringBuilder sb=new StringBuilder();
				int index=1;
				for(int i=0;i<whereClause.Length;i++)
				{
					char c=whereClause[i];
					if(c=='?')
					{
						sb.Append("@p"+index);
						index++;
					}
					else
					{
						sb.Append(c);
					}
				}
				return sb.ToString();
			}
		}

		public void PopulateCommand(SqlCommand cmd)
		{
			cmd.CommandText=SqlParametersQuery;
			SqlParameter[] p=SqlParameters;
			for (int i = 0; i < p.Length; i++)
			{
				cmd.Parameters.Add(p[i]);
			}
		}

		public override string ToString()
		{
			return "Criteria{" +
				"whereClause=" + whereClause +
				", joins=" + joins +
				", minRowIndex=" + minRowIndex +
				", maxRowIndex=" + maxRowIndex +
				", params=" + Utils.Dump(parameters) +
				"}";
		}
	}
}
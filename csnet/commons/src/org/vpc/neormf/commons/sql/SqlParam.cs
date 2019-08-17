using System;
using System.Data;
using System.Data.SqlClient;
using org.vpc.neormf.commons.jwrapper;
using org.vpc.neormf.commons.util;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Root Optimizer
 * @creation on Date: 28 juil. 2004 Time: 11:20:37
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.sql
{
	public class SqlParam : SqlStatementParamsProvider 
						  {
    private int pos;
    private String name;
    private Object value;
    private int sqlType;

    public SqlParam() {
    }

    public int GetPos() {
        return pos;
    }

    public void SetPos(int pos) {
        this.pos = pos;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public Object GetValue() {
        return value;
    }

    public void SetValue(Object value) {
        this.value = value;
    }

    public int GetSqlType() {
        return sqlType;
    }

    public void SetSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public int PopulateStatement(PreparedStatement ps,int startPosition){
        int rpos=pos+startPosition-1;
        if (value == null) {
            ps.setNull(rpos, sqlType);
            return 1;
        }
        switch (sqlType) {
            case Types.VARCHAR:
                {
                    ps.SetString(rpos, (String) value);
                    break;
                }
            case Types.INTEGER:
                {
                    ps.SetInt(rpos, ((int) value));
                    break;
                }
            case Types.DOUBLE:
                {
                    ps.SetDouble(rpos, ((double) value));
                    break;
                }
            case Types.SMALLINT:
                {
                    ps.setShort(rpos, ((short) value));
                    break;
                }
            case Types.TIME:
                {
                    ps.SetTime(rpos, ((TimeSpan) value));
                    break;
                }
            case Types.TIMESTAMP:
                {
                    ps.SetTimestamp(rpos, ((DateTime) value));
                    break;
                }
            case Types.BIGINT:
                {
                    ps.SetLong(rpos, ((long) value));
                    break;
                }
            case Types.FLOAT:
                {
                    ps.SetFloat(rpos, ((float) value));
                    break;
                }
            case Types.DATE:
                {
                    ps.SetDate(rpos, ((DateTime) value));
                    break;
                }
            default:
                {
                    throw new Exception("Unhandled sql type " + sqlType);
                }
        }
        return 1;
    }

	public SqlParameter ToSqlParameter()
	{
		String name="@p"+pos;
		SqlDbType sqlDbType=SqlDbType.VarChar;
		switch (sqlType) 
		{
			case Types.VARCHAR:
			{
				sqlDbType=SqlDbType.VarChar;
				break;
			}
			case Types.INTEGER:
			{
				sqlDbType=SqlDbType.Int;
				break;
			}
			case Types.DOUBLE:
			{
				sqlDbType=SqlDbType.Float;
				break;
			}
			case Types.SMALLINT:
			{
				sqlDbType=SqlDbType.SmallInt;
				break;
			}
			case Types.TIME:
			{
				sqlDbType=SqlDbType.Timestamp;
				break;
			}
			case Types.TIMESTAMP:
			{
				sqlDbType=SqlDbType.Timestamp;
				break;
			}
			case Types.BIGINT:
			{
				sqlDbType=SqlDbType.BigInt;
				break;
			}
			case Types.FLOAT:
			{
				sqlDbType=SqlDbType.Float;
				break;
			}
			case Types.DATE:
			{
				sqlDbType=SqlDbType.DateTime;
				break;
			}
			default:
			{
				throw new Exception("Unhandled sql type " + sqlType);
			}
		}
		SqlParameter p=new SqlParameter(name,sqlDbType);
		p.Value=(value == null)?DBNull.Value:value;
		return p;
	}

    public override String ToString() {
        return "Param{" +
                "pos=" + pos +
                (name!=null? (", name=" + Utils.Dump(name)):"") +
                ", value=" + Utils.Dump(value) +
                ", sqlType=" + Types.ToSqlDbType(sqlType).ToString() + "("+sqlType+")"+
                "}";
    }
}
}
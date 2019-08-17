using System;
using System.Collections;
using System.Text;
using org.vpc.neormf.commons.jwrapper;
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
namespace org.vpc.neormf.commons.sql
{
	public class SqlString {
    private String buffer;
    private Hashtable parameters = new Hashtable();

    public SqlString() {
    }

    public SqlString(String buffer) {
        this.buffer = buffer;
    }

    public String GetBuffer() {
        return buffer;
    }

    public SqlString SetString(int pos, String value) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(Types.VARCHAR);
        parameters.Add(pos, p);
        return this;
    }

    public SqlString SetDouble(int pos, double value) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(Types.DOUBLE);
        parameters.Add(pos, p);
        return this;
    }

    public SqlString SetInt(int pos, int value) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(Types.INTEGER);
        parameters.Add(pos, p);
        return this;
    }

    public SqlString SetParam(int pos, Object value,int sqlType) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(sqlType);
        parameters.Add(pos, p);
        return this;
    }

	
    public SqlString SetParam(int pos, Object value,String sqlTypeName) {
		throw new Exception("Not implemented yet");
//        if(pos<=0)
//		{
//            pos=parameters.Count+1;
//        }
//        int sqlType=0;
//        try {
//            sqlType=Types.class.getField(sqlTypeName.toUpperCase()).getInt(null);
//        } catch (Exception e) {
//            throw new RuntimeException(e.ToString());
//        }
//        return setParam(pos, value,sqlType);
    }
	

    public SqlString SetDate(int pos, DateTime value) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(Types.DATE);
        parameters.Add(pos, p);
        return this;
    }

    public SqlString SetTime(int pos, DateTime value) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(Types.TIME);
        parameters.Add(pos, p);
        return this;
    }

    public SqlString SetTimestamp(int pos, DateTime value) {
        SqlParam p = new SqlParam();
        p.SetPos(pos);
        p.SetValue(value);
        p.SetSqlType(Types.TIMESTAMP);
        parameters.Add(pos, p);
        return this;
    }


    public int PopulateStatement(PreparedStatement statement,int startPosition) {
        for (IDictionaryEnumerator i = parameters.GetEnumerator(); i.MoveNext();) {
            startPosition+=((SqlParam) i.Value).PopulateStatement(statement,startPosition);
        }
        return startPosition;
    }

    public bool IsNamesBased() {
		throw new Exception("Not implemented yet");

//        if (parameters.Count > 0) 
//		{
//            Iterator i = parameters.keySet().iterator();
//            i.hasNext();
//            Object o = i.next();
//            return o instanceof String;
//        } else {
//            return true;
//        }
    }


//    public String ToString() {
//        SqlParam[] p=(SqlParam[]) parameters.values().toArray(new SqlParam[parameters.size()]);
//        Arrays.sort(p,new Comparator() {
//            public int compare(Object o, Object o1) {
//                SqlParam p1=(SqlParam) o;
//                SqlParam p2=(SqlParam) o1;
//                int i1=p1.getPos();
//                int i2=p2.getPos();
//                return (i2==i1)?0:(i1>i2)?1:-1;
//            }
//        });
//        return "SqlString{" +
//                "buffer=" + Utils.dump(buffer)  +
//                ", parameters=" + Utils.dump(p) +
//                "}";
//    }

    public int GetParamCount() {
        return parameters.Count;
    }

    public void SetBuffer(String buffer){
        this.buffer=buffer;
    }

    public SqlString Merge(SqlString other) {
        SqlString newSqlString = new SqlString();

        StringBuilder sb = new StringBuilder();
        if (buffer != null) {
            sb.Append(buffer);
        }
        if (other.buffer != null) {
            sb.Append(buffer);
        }
        newSqlString = new SqlString(sb.ToString());
        int pos = 1;
        for (int i = 0; i < parameters.Count; i++) {
            newSqlString.parameters.Add(pos, parameters[i + 1]);
            pos++;
        }
        for (int i = 0; i < other.parameters.Count; i++) {
            SqlParam p=(SqlParam) other.parameters[i + 1];
            p.SetPos(pos);
            newSqlString.parameters.Add(pos, p);
            pos++;
        }
        return newSqlString;
    }

    public SqlParam[] GetParams(){
        int size=parameters.Count;
        SqlParam[] ret=new SqlParam[size];
        for (int i = 0; i < size; i++) {
            ret[i]=(SqlParam) parameters[i+1];
        }
        return ret;
    }

    public void PopulateStatement(PreparedStatement statement){
        int size=parameters.Count;
        for (int i = 0; i < size; i++) {
            int pos=i+1;
            SqlParam param=(SqlParam) parameters[pos];
            //TODO take into consideration null parameters
            switch(param.GetSqlType()){
                case Types.VARCHAR:{
                    statement.SetString(pos,(String) param.GetValue());
                    break;
                }
                case Types.DOUBLE:{
                    statement.SetDouble(pos,Convert.ToDouble(param.GetValue()));
                    break;
                }
                case Types.INTEGER:{
                    statement.SetInt(pos,Convert.ToInt32(param.GetValue()));
                    break;
                }
                case Types.DATE:{
                    statement.SetDate(pos,(DateTime) param.GetValue());
                    break;
                }
                case Types.TIME:{
                    statement.SetTime(pos,(TimeSpan) param.GetValue());
                    break;
                }
                case Types.TIMESTAMP:{
                    statement.SetTimestamp(pos,(DateTime) param.GetValue());
                    break;
                }
                default:{
                    throw new Exception("Unsupported param : "+param);
                }
            }
        }
    }
}
}
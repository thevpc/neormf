package org.vpc.neormf.commons.sql;

import org.vpc.neormf.commons.util.Utils;
import java.io.Serializable;
import java.sql.*;
import java.sql.Date;
import java.util.*;

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
public class SqlString implements Serializable {
    static final long serialVersionUID = -2756473749704119053L;

    private String buffer;
    private HashMap params = new HashMap();

    public SqlString() {
    }

    public SqlString(String buffer) {
        this.buffer = buffer;
    }

    public String getBuffer() {
        return buffer;
    }

    public SqlString setString(int pos, String value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.VARCHAR);
        params.put(new Integer(pos), p);
        return this;
    }

    public SqlString setDouble(int pos, double value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(new Double(value));
        p.setSqlType(Types.DOUBLE);
        params.put(new Integer(pos), p);
        return this;
    }

    public SqlString setInt(int pos, int value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(new Integer(value));
        p.setSqlType(Types.INTEGER);
        params.put(new Integer(pos), p);
        return this;
    }

    public SqlString setParam(int pos, Object value,int sqlType) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(sqlType);
        params.put(new Integer(pos), p);
        return this;
    }

    public SqlString setParam(int pos, Object value,String sqlTypeName) {
        if(pos<=0){
            pos=params.size()+1;
        }
        int sqlType=0;
        try {
            sqlType=Types.class.getField(sqlTypeName.toUpperCase()).getInt(null);
        } catch (Throwable e) {
            throw new RuntimeException(e.toString());
        }
        return setParam(pos, value,sqlType);
    }

    public SqlString setDate(int pos, Date value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.DATE);
        params.put(new Integer(pos), p);
        return this;
    }

    public SqlString setTime(int pos, Time value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.TIME);
        params.put(new Integer(pos), p);
        return this;
    }

    public SqlString setTimestamp(int pos, Timestamp value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.TIMESTAMP);
        params.put(new Integer(pos), p);
        return this;
    }


    public int populateStatement(PreparedStatement statement,int startPosition) throws SQLException {
        for (Iterator i = params.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            startPosition+=((SqlParam) entry.getValue()).populateStatement(statement,startPosition);
        }
        return startPosition;
    }

    public boolean isNamesBased() {
        if (params.size() > 0) {
            Iterator i = params.keySet().iterator();
            i.hasNext();
            Object o = i.next();
            return o instanceof String;
        } else {
            return true;
        }
    }


    public String toString() {
        SqlParam[] p=(SqlParam[]) params.values().toArray(new SqlParam[params.size()]);
        Arrays.sort(p,new Comparator() {
            public int compare(Object o, Object o1) {
                SqlParam p1=(SqlParam) o;
                SqlParam p2=(SqlParam) o1;
                int i1=p1.getPos();
                int i2=p2.getPos();
                return (i2==i1)?0:(i1>i2)?1:-1;
            }
        });
        return "SqlString{" +
                "buffer=" + Utils.dump(buffer)  +
                ", params=" + Utils.dump(p) +
                "}";
    }

    public int getParamCount() {
        return params.size();
    }

    public void setBuffer(String buffer){
        this.buffer=buffer;
    }

    public SqlString merge(SqlString other) {
        SqlString newSqlString = new SqlString();

        StringBuffer sb = new StringBuffer();
        if (buffer != null) {
            sb.append(buffer);
        }
        if (other.buffer != null) {
            sb.append(buffer);
        }
        newSqlString = new SqlString(sb.toString());
        int pos = 1;
        for (int i = 0; i < params.size(); i++) {
            newSqlString.params.put(new Integer(pos), params.get((i + 1)));
            pos++;
        }
        for (int i = 0; i < other.params.size(); i++) {
            SqlParam p=(SqlParam) other.params.get((i + 1));
            p.setPos(pos);
            newSqlString.params.put(new Integer(pos), p);
            pos++;
        }
        return newSqlString;
    }

    public SqlParam[] getParams(){
        int size=params.size();
        SqlParam[] ret=new SqlParam[size];
        for (int i = 0; i < size; i++) {
            ret[i]=(SqlParam) params.get(new Integer(i+1));
        }
        return ret;
    }

    public void populateStatement(PreparedStatement statement) throws SQLException {
        int size=params.size();
        for (int i = 0; i < size; i++) {
            int pos=i+1;
            SqlParam param=(SqlParam) params.get(new Integer(pos));
            //TODO take into consideration null params
            switch(param.getSqlType()){
                case Types.VARCHAR:{
                    statement.setString(pos,(String) param.getValue());
                    break;
                }
                case Types.DOUBLE:{
                    statement.setDouble(pos,((Number) param.getValue()).doubleValue());
                    break;
                }
                case Types.INTEGER:{
                    statement.setInt(pos,((Number) param.getValue()).intValue());
                    break;
                }
                case Types.DATE:{
                    statement.setDate(pos,(Date) param.getValue());
                    break;
                }
                case Types.TIME:{
                    statement.setTime(pos,(Time) param.getValue());
                    break;
                }
                case Types.TIMESTAMP:{
                    statement.setTimestamp(pos,(Timestamp) param.getValue());
                    break;
                }
                default:{
                    throw new IllegalArgumentException("Unsupported param : "+param);
                }
            }
        }
    }
}

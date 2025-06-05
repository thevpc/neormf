package org.vpc.neormf.commons.beans;

import org.vpc.neormf.commons.sql.SqlParam;
import org.vpc.neormf.commons.sql.SqlStatementParamsProvider;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.util.Utils;

import java.io.Serializable;
import java.sql.Date;
import java.sql.*;
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
public class Criteria implements Serializable, SqlStatementParamsProvider {
    static final long serialVersionUID = 7306885846673824612L;

    private String whereClause;
    private String joins;
    private boolean distinct;
    private int minRowIndex = -1;
    private int maxRowIndex = -1;
    private HashMap params = new HashMap();

    public Criteria() {
    }

    public Criteria(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public Criteria setWhereClause(String whereClause) {
        this.whereClause = whereClause;
        return this;
    }

    public String getJoins() {
        return joins;
    }

    public Criteria setJoins(String joins) {
        this.joins = joins;
        return this;
    }

    public Criteria setString(int pos, String value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.VARCHAR);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setDouble(int pos, double value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(new Double(value));
        p.setSqlType(Types.DOUBLE);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setInt(int pos, int value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(new Integer(value));
        p.setSqlType(Types.INTEGER);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setDate(int pos, Date value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.DATE);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setBoolean(int pos, boolean value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value ? new Integer(1) : new Integer(0));
        p.setSqlType(Types.INTEGER);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setTime(int pos, Time value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.TIME);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setTimestamp(int pos, Timestamp value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(Types.TIMESTAMP);
        params.put(new Integer(pos), p);
        return this;
    }

    public Criteria setBlobData(int pos, BlobData value) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(SqlParam.SQL_TYPE_EXTRA_BLOB);
        params.put(new Integer(pos), p);
        return this;
    }


    public int populateStatement(PreparedStatement statement, int startPosition) throws SQLException {
        int count = 0;
        for (Iterator i = params.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            count += ((SqlParam) entry.getValue()).populateStatement(statement, startPosition);
        }
        return count;
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


    public int getMinRowIndex() {
        return minRowIndex;
    }

    public void setMinRowIndex(int minRowIndex) {
        this.minRowIndex = minRowIndex;
    }

    public int getMaxRowIndex() {
        return maxRowIndex;
    }

    public void setMaxRowIndex(int maxRowIndex) {
        this.maxRowIndex = maxRowIndex;
    }

    public String toString() {
        SqlParam[] p = (SqlParam[]) params.values().toArray(new SqlParam[params.size()]);
        Arrays.sort(p, new Comparator() {
            public int compare(Object o, Object o1) {
                SqlParam p1 = (SqlParam) o;
                SqlParam p2 = (SqlParam) o1;
                int i1 = p1.getPos();
                int i2 = p2.getPos();
                return (i2 == i1) ? 0 : (i1 > i2) ? 1 : -1;
            }
        });
        return "Criteria{" +
                "whereClause=" + Utils.dump(whereClause) +
                ", joins=" + Utils.dump(joins) +
                ", minRowIndex=" + minRowIndex +
                ", maxRowIndex=" + maxRowIndex +
                ", params=" + Utils.dump(p) +
                "}";
    }

    public Criteria setObject(int pos, Object value, DTOFieldMetaData dataField) {
        SqlParam p = new SqlParam();
        p.setPos(pos);
        p.setValue(value);
        p.setSqlType(toSqlType(dataField.getSqlType()));
        params.put(new Integer(pos), p);
        return this;
    }

    public int getParamCount() {
        return params.size();
    }

    public Criteria merge(Criteria other) {
        if (other == null) {
            return this;
        }
        Criteria newCriteria = new Criteria();

        StringBuffer sb = new StringBuffer();
        if (whereClause != null) {
            sb.append(whereClause);
        }
        if (other.whereClause != null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append(other.whereClause);
        }
        newCriteria.whereClause = sb.length() == 0 ? null : sb.toString();

        sb = new StringBuffer();
        if (joins != null) {
            sb.append(joins);
        }
        if (other.joins != null) {
            sb.append(" ");
            sb.append(other.joins);
        }
        newCriteria.joins = sb.length() == 0 ? null : sb.toString();
        int pos = 1;
        for (int i = 0; i < params.size(); i++) {
            newCriteria.params.put(new Integer(pos), params.get((i + 1)));
            pos++;
        }
        for (int i = 0; i < other.params.size(); i++) {
            SqlParam p = (SqlParam) other.params.get((i + 1));
            p.setPos(pos);
            newCriteria.params.put(new Integer(pos), p);
            pos++;
        }
        if (minRowIndex >= 0) {
            newCriteria.minRowIndex = minRowIndex;
        }
        if (other.minRowIndex >= 0) {
            newCriteria.minRowIndex = other.minRowIndex;
        }
        if (maxRowIndex >= 0) {
            newCriteria.maxRowIndex = maxRowIndex;
        }
        if (other.maxRowIndex >= 0) {
            newCriteria.maxRowIndex = other.maxRowIndex;
        }
        if (distinct || other.isDistinct()) {
            newCriteria.distinct = other.distinct;
        }
        return newCriteria;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }


    private static int toSqlType(DataType dataType) {
        return toSqlType(dataType.toJavaType());
    }

    private static int toSqlType(Class javaType) {
        if (javaType == String.class) {
            return Types.VARCHAR;
        } else if (javaType == Integer.class) {
            return Types.INTEGER;
        } else if (javaType == Long.class) {
            return Types.BIGINT;
        } else if (javaType == Double.class) {
            return Types.DOUBLE;
        } else if (javaType == Float.class) {
            return Types.FLOAT;
        } else if (javaType == Byte.class) {
            return Types.TINYINT;
        } else if (javaType == java.sql.Date.class) {
            return Types.DATE;
        } else if (javaType == java.sql.Time.class) {
            return Types.TIME;
        } else if (javaType == java.sql.Timestamp.class) {
            return Types.TIMESTAMP;
        } else {
            throw new NoSuchElementException(javaType.getName());
        }
    }

}

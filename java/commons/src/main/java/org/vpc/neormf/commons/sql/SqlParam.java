package org.vpc.neormf.commons.sql;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vpc.neormf.commons.util.Utils;

import java.io.Serializable;
import java.sql.*;
import org.vpc.neormf.commons.beans.BlobData;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 28 juil. 2004 Time: 11:20:37
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class SqlParam implements Serializable, Cloneable, SqlStatementParamsProvider {

    public static final int SQL_TYPE_EXTRA_BLOB = -7770;
    static final long serialVersionUID = 7306885846673824613L;
    private int pos;
    private String name;
    private Object value;
    private int sqlType;

    public SqlParam() {
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public int populateStatement(PreparedStatement ps, int startPosition) throws SQLException {
        int rpos = pos + startPosition - 1;
        if (value == null) {
            ps.setNull(rpos, sqlType);
            return 1;
        }
        switch (sqlType) {
            case Types.VARCHAR: {
                ps.setString(rpos, (String) value);
                break;
            }
            case Types.INTEGER: {
                ps.setInt(rpos, ((Integer) value).intValue());
                break;
            }
            case Types.DOUBLE: {
                ps.setDouble(rpos, ((Double) value).doubleValue());
                break;
            }
            case Types.SMALLINT: {
                ps.setShort(rpos, ((Short) value).shortValue());
                break;
            }
            case Types.TIME: {
                ps.setTime(rpos, ((Time) value));
                break;
            }
            case Types.TIMESTAMP: {
                ps.setTimestamp(rpos, ((Timestamp) value));
                break;
            }
            case Types.BIGINT: {
                ps.setLong(rpos, ((Long) value).longValue());
                break;
            }
            case Types.FLOAT: {
                ps.setFloat(rpos, ((Float) value).floatValue());
                break;
            }
            case Types.DATE: {
                ps.setDate(rpos, ((Date) value));
                break;
            }
            case SQL_TYPE_EXTRA_BLOB: {
                try {
                    SQLUtils.writeBlobData((BlobData) value, ps, pos);
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(SqlParam.class.getName()).log(Level.SEVERE, null, ex);
                    throw new SQLException(ex.toString());
                }
            }

            default: {
                throw new IllegalArgumentException("Unhandled sql type " + sqlType);
            }
        }
        return 1;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String toString() {
        return "Param{" +
                "pos=" + pos +
                (name != null ? (", name=" + Utils.dump(name)) : "") +
                ", value=" + Utils.dump(value) +
                ", sqlType=" //+ SQLUtils.getTypeName(sqlType) 
                + "(" + sqlType + ")" +
                "}";
    }
}

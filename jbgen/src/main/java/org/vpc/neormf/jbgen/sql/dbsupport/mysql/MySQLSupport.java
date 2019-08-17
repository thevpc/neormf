package org.vpc.neormf.jbgen.sql.dbsupport.mysql;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.sql.dbsupport.DefaultDBSupport;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 4 janv. 2007 02:11:58
 */
public class MySQLSupport extends DefaultDBSupport {

    public MySQLSupport() {
    }

    @Override
    public int getType() {
        return AUTO_INCREMENT;
    }


    @Override
    public String getSequenceString(DAOInfo entityInfo) {
        return "SELECT LAST_INSERT_ID()";
    }


    @Override
    public boolean isAutoIdentity(DBColumn dbcolumn, Connection cnx) throws SQLException {
        DatabaseMetaData md = cnx.getMetaData();
        DBTableInfo table = dbcolumn.getTable();
        ResultSet columnsResultSet = null;
        try {
            columnsResultSet = md.getColumns(table == null ? null : table.getTableCat(), table == null ? null : table.getTableSchem(), table == null ? null : table.getTableName(), dbcolumn.getColumnName());
            if (columnsResultSet.next()) {
                boolean b = "YES".equalsIgnoreCase(columnsResultSet.getString("IS_AUTOINCREMENT"));
                return b;
            }
        } finally {
            if (columnsResultSet != null) {
                columnsResultSet.close();
            }
        }
        return false;
    }

    @Override
    public int accept(String name, Connection cnx) throws SQLException {
        return name.indexOf("mysql") >= 0 ? 100 : 0;
    }
}
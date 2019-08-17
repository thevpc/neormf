package org.vpc.neormf.jbgen.sql.dbsupport.derby;

import org.vpc.neormf.jbgen.sql.dbsupport.DefaultDBSupport;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 4 janv. 2007 02:11:58
 */
public class DerbySupport extends DefaultDBSupport {

    public DerbySupport() {
    }

    @Override
    public int getType() {
        return AUTO_INCREMENT;
    }


    @Override
    public String getSequenceString(DAOInfo entityInfo) {
        DBColumn[] keys = entityInfo.getPrimaryKeys();
        DBTableInfo[] tables = entityInfo.getTables();
        return "Select w.AUTOINCREMENTVALUE-1  " +
                " From SYS.SYSCOLUMNS w " +
                " inner join SYS.SYSTABLES t on REFERENCEID=t.TABLEID" +
                " where " +
                " w.COLUMNNAME='"+keys[0].getColumnName()+"'"+
                " and t.TABLENAME='"+tables[0].getTableName()+"'";
    }


    @Override
    public boolean isAutoIdentity(DBColumn dbcolumn, Connection cnx) throws SQLException {
        Connection connection = dbcolumn.getTable().getProjectInfo().getConnection();
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            s = connection.prepareStatement("Select w.AUTOINCREMENTINC\n" +
                    "From SYS.SYSCOLUMNS w \n" +
                    "inner join SYS.SYSTABLES t on REFERENCEID=t.TABLEID\n" +
                    "where " +
                    "w.COLUMNNAME=?" +
                    " and t.TABLENAME=?"
            );
            s.setString(1,dbcolumn.getColumnName());
            s.setString(2,dbcolumn.getTable().getTableName());
            rs = s.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) != 0;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (rs != null) {
                s.close();
            }
        }
        return false;
    }

    @Override
    public int accept(String name, Connection cnx) throws SQLException {
        return name.indexOf("derby") >= 0 ?100:0;
    }
}

package org.vpc.neormf.jbgen.sql.dbsupport.mssqlserver;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.sql.dbsupport.DefaultDBSupport;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 4 janv. 2007 02:11:58
 */
public class MSSQLServerSupport extends DefaultDBSupport{

    public MSSQLServerSupport() {
    }

    @Override
    public int getType() {
        return AUTO_INCREMENT;
    }


    @Override
    public String getSequenceString(DAOInfo entityInfo) {
        return "SELECT @@IDENTITY";
    }


    @Override
    public boolean isAutoIdentity(DBColumn dbcolumn, Connection cnx) {
        return dbcolumn.getTypeName().toLowerCase().endsWith(" identity");
    }

    @Override
    public int accept(String name, Connection cnx) throws SQLException {
        return name.indexOf("microsoft sql server") >= 0 ?100:0;
    }
}

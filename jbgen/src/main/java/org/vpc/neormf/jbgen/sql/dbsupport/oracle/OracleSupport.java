package org.vpc.neormf.jbgen.sql.dbsupport.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import org.vpc.neormf.jbgen.sql.dbsupport.DefaultDBSupport;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 4 janv. 2007 02:11:58
 */
public class OracleSupport extends DefaultDBSupport{

    public OracleSupport() {
    }

    @Override
    public int getType() {
        return SEQUENCE;
    }


    @Override
    public String getSequenceString(DAOInfo entityInfo) {
        return "SELECT "+ entityInfo.getSequenceName() +".NEXTVAL FROM dual";
    }


    @Override
    public boolean isAutoIdentity(DBColumn dbcolumn, Connection cnx) {
        return false;
    }

    public int accept(String name, Connection cnx) throws SQLException {
        return name.indexOf("oracle") >= 0 ?100:0;
    }
    
}

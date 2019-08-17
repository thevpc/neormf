package org.vpc.neormf.jbgen.sql.dbsupport;

import java.sql.Connection;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;

import java.sql.SQLException;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 4 janv. 2007 02:11:43
 */
public interface RDBMSSupport {
    public static final int NONE=0;
    public static final int SEQUENCE=1;
    public static final int AUTO_INCREMENT=2;
    int getType();
    String getSequenceString(DAOInfo entityInfo) throws SQLException;
    boolean isAutoIdentity(DBColumn dbcolumn, Connection cnx) throws SQLException ;
    String coteIdentifier(DAOInfo entityInfo,String id) throws SQLException ;
    int accept(String name,Connection cnx) throws SQLException ;
}

package org.vpc.neormf.jbgen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 21 avr. 2007 17:29:40
 */
public interface JBGenConnectionProvider {
    Connection getConnection(String url, String driverClass, String user, String password, Properties properties) throws SQLException, ClassNotFoundException;
}

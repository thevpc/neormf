/**
 * ====================================================================
 *             DBCLient yet another Jdbc client tool
 *
 * DBClient is a new Open Source Tool for connecting to jdbc
 * compliant relational databases. Specific extensions will take care of
 * each RDBMS implementation.
 *
 * Copyright (C) 2006-2007 Taha BEN SALAH
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */

package org.vpc.neormf.jbgen.sql.urlparser;

import java.util.Properties;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 1 juil. 2007 12:17:48
 */
public class JdbcOdbcDriverUrlParser implements DBCDriverUrlParser {
    private static final String PARAM_DATABASE="DatabaseName";
    public JdbcOdbcDriverUrlParser() {
    }

    public int acceptDriver(String driver) {
        return "sun.jdbc.odbc.JdbcOdbcDriver".equals(driver) ? 10 : -1;
    }

    public Properties parse(String driver, String url) {
        Properties p = new Properties();
        String[] strings = url.split(":");
        if (strings.length >= 2 && strings[0].equals("jdbc") && strings[1].equals("odbc")) {
            p.put(PARAM_DATABASE,strings[2]);
        }
        return p;
    }

    public String format(String driver, Properties properties) {
        String database = properties.getProperty(PARAM_DATABASE);
        return "jdbc:odbc:" +((database==null || database.length()==0)?"NONAME":database);
    }

    public Set<DBCDriverParameter> getParameters() {
        LinkedHashSet<DBCDriverParameter> all = new LinkedHashSet<DBCDriverParameter>();
        all.add(new DBCDriverParameter(PARAM_DATABASE, "JdbcOdbcDriverUrlParser.database", "NONAME", ParameterType.STRING, null));
        return all;
    }

    public String getDatasourceClassName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

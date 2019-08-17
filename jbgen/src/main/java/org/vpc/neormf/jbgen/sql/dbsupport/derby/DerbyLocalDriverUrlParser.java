/**
 * ====================================================================
 *             DBClient yet another Jdbc client tool
 *
 * DBClient is a new Open Source Tool for connecting to jdbc
 * compliant relational databases. Specific extensions will take care of
 * each RDBMS implementation.
 *
 * Copyright (C) 2006-2008 Taha BEN SALAH
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
package org.vpc.neormf.jbgen.sql.dbsupport.derby;

import java.util.Properties;
import java.util.Set;
import java.util.LinkedHashSet;
import org.vpc.neormf.jbgen.sql.urlparser.DBCDriverParameter;
import org.vpc.neormf.jbgen.sql.urlparser.DBCDriverUrlParser;
import org.vpc.neormf.jbgen.util.JBGenUtils;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 1 juil. 2007 12:17:48
 */
public class DerbyLocalDriverUrlParser implements DBCDriverUrlParser {

    public static final String PARAM_DBFOLDER = "DatabaseName";
    private static final String PARAM_ATTRIBUTES = "ConnectionAttributes";

    public DerbyLocalDriverUrlParser() {
    }

    public int acceptDriver(String driver) {
        return driver.equals("org.apache.derby.jdbc.EmbeddedDriver") ? 10 : -1;
    }

    public Properties parse(String driver, String url) {
        Properties p = new Properties();
        String prefix = "jdbc:derby:";
        if (url.startsWith(prefix)) {
            int i = url.indexOf(';', prefix.length());
            p.put(PARAM_DBFOLDER, i >= 0 ? JBGenUtils.substring(url, prefix.length(), i) : JBGenUtils.substring(url, prefix.length(), url.length()));
            String params = i < 0 ? "" : JBGenUtils.substring(url, i, url.length());
            if (params.length() > 0) {
                p.put(PARAM_ATTRIBUTES, params);
            }
        }
        return p;
    }

    public String format(String driver, Properties properties) {
        String folder = properties.getProperty(PARAM_DBFOLDER);
        String create = properties.getProperty(PARAM_ATTRIBUTES);
        folder = ((folder == null || folder.length() == 0) ? "noname" : folder);
        create = ((create == null || create.length() == 0) ? "" : (";" + create));
        return "jdbc:derby:" + folder + create;
    }

    public Set<DBCDriverParameter> getParameters() {
        LinkedHashSet<DBCDriverParameter> all = new LinkedHashSet<DBCDriverParameter>();
        all.add(new DBCDriverParameter(PARAM_DBFOLDER, null, ".", ParameterType.FOLDER, null));
        all.add(new DBCDriverParameter(PARAM_ATTRIBUTES, null, "", ParameterType.STRING, null));
        return all;
    }

    public String getDatasourceClassName() {
        return "org.apache.derby.jdbc.ClientDataSource";
    }
}

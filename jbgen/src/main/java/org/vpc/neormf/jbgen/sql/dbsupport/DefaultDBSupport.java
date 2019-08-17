package org.vpc.neormf.jbgen.sql.dbsupport;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.DAOInfo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 4 janv. 2007 02:11:58
 */
public class DefaultDBSupport implements RDBMSSupport {
    public TreeSet keywords = new TreeSet(
            Arrays.asList(
                    new String[]{
                            "user",
                            "users",
                            "constraint",
                            "constraints",
                            "profile",
                            "agent",
                            "agents",
                            "category",
                            "condition",
                            "access",
                            "office",
                            "order",
                            "rate",
                            "status",
                            "transaction",
                            "statement",
                            "connection",
                            "trace",
                            "table",
                            "view",
                            "select",
                            "update",
                            "delete",
                            "remove",
                            "primary",
                            "key",
                            "id",
                            "foreign",
                            "auto",
                            "increment",
                            "seed",
                            "comment",
                    }
            )
    );

    public DefaultDBSupport() {
    }

    public int getType() {
        return NONE;
    }


    public String getSequenceString(DAOInfo entityInfo) {
        return null;
    }


    public boolean isAutoIdentity(DBColumn dbcolumn, Connection cnx) throws SQLException {
        return false;
    }

    String cote;

    public String coteIdentifier(DAOInfo entityInfo, String id) throws SQLException {
        if (cote == null) {
            DatabaseMetaData metaData = null;
            try {
                metaData = entityInfo.getProjectInfo().getConnection().getMetaData();
            } catch (SQLException e) {
                //
            }
            if (metaData != null) {
                try {
                    cote = metaData.getIdentifierQuoteString();
                } catch (SQLException e) {
                    cote = "";
                }
                try {
                    String kw = metaData.getSQLKeywords();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());

                    }
                } catch (SQLException e) {
                    //
                }
                try {
                    String kw = metaData.getStringFunctions();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());

                    }
                } catch (SQLException e) {
                    //
                }
                try {
                    String kw = metaData.getStringFunctions();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());

                    }
                } catch (SQLException e) {
                    //
                }
                try {
                    String kw = metaData.getNumericFunctions();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());

                    }
                } catch (SQLException e) {
                    //
                }
                try {
                    String kw = metaData.getTimeDateFunctions();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());

                    }
                } catch (SQLException e) {
                    //
                }
                try {
                    String kw = metaData.getStringFunctions();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());

                    }
                } catch (SQLException e) {
                    //
                }
                try {
                    String kw = metaData.getSystemFunctions();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(kw,","); stringTokenizer.hasMoreTokens();) {
                        String s = stringTokenizer.nextToken();
                        keywords.add(s.toLowerCase());
                    }
                } catch (SQLException e) {
                    //
                }
            }
        }
        if (keywords.contains(id.toLowerCase()) || id.indexOf(' ') >= 0) {
            return cote + id + cote;
        } else {
            return id;
        }
    }


    @Override
    public String toString() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf('.')+1);
    }

    public int accept(String name, Connection cnx) throws SQLException {
        return 1;
    }
    
}

package org.vpc.neormf.commons.sql;

/**
 * Created by IntelliJ IDEA.
* User: vpc
* Date: 18 févr. 2009
* Time: 17:56:06
* To change this template use File | Settings | File Templates.
*/
public class DAOFieldSqlQueryImpl extends DAOFieldImpl {
    private String query;

    public DAOFieldSqlQueryImpl(String query) {
        super(DAOFieldImplType.sqlQuery);
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

package org.vpc.neormf.commons.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 29 juil. 2004 Time: 17:06:00
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public interface SqlStatementParamsProvider {
    /**
     * populates the statement starting from the given index
     * @param statement
     * @param startIndex
     * @return the number of params provided
     */
    public int populateStatement(PreparedStatement statement,int startIndex) throws SQLException ;
}

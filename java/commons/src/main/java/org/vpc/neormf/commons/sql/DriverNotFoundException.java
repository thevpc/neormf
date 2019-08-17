package org.vpc.neormf.commons.sql;

import java.sql.SQLException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 09:48:18
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DriverNotFoundException extends SQLException {
    public DriverNotFoundException() {
    }

    public DriverNotFoundException(String reason) {
        super(reason);
    }

    public DriverNotFoundException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public DriverNotFoundException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }
}

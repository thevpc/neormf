package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.exceptions.AbstractNeormfRuntimeException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 09:47:09
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DataException extends AbstractNeormfRuntimeException {
    public DataException(String message, Object[] parameters) {
        super(message, parameters);
    }

    public DataException(String message, Object[] parameters, Throwable cause) {
        super(message, parameters, cause);
    }

}

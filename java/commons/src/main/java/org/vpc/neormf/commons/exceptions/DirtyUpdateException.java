package org.vpc.neormf.commons.exceptions;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 24 mai 2004 Time: 20:16:25
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DirtyUpdateException extends AbstractNeormfRuntimeException{
    public DirtyUpdateException(Object[] parameters) {
        super("org.vpc.neormf.commons.exceptions.DirtyUpdateException.Message",parameters);
    }
    public DirtyUpdateException() {
        this(null);
    }
}

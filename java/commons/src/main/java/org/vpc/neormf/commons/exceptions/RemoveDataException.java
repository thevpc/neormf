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
public class RemoveDataException extends AbstractNeormfRuntimeException{
    public RemoveDataException(Object[] parameters) {
        super("org.vpc.neormf.commons.exceptions.RemoveDataException.Message",parameters);
    }
    public RemoveDataException(Throwable throwable) {
        super("org.vpc.neormf.commons.exceptions.RemoveDataException.Message",null,throwable);
    }
    public RemoveDataException() {
        this((Object[]) null);
    }
}

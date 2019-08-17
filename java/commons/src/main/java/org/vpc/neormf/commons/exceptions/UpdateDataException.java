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
public class UpdateDataException extends AbstractNeormfRuntimeException{
    public UpdateDataException(Throwable error) {
        super("org.vpc.neormf.commons.exceptions.UpdateDataException.Message",null,error);
    }
    public UpdateDataException(String message,Object[] parameters) {
        super(message,parameters);
    }
    
    public UpdateDataException(Object[] parameters) {
        super("org.vpc.neormf.commons.exceptions.UpdateDataException.Message",parameters);
    }
    public UpdateDataException() {
        this((Object[]) null);
    }
}

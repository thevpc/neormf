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
public class CreateDataException extends AbstractNeormfRuntimeException{
    public CreateDataException(String message,Object[] parameters) {
        super(message,parameters);
    }
    
    public CreateDataException(Throwable throwable) {
        super("org.vpc.neormf.commons.exceptions.CreateDataException.Message",null,throwable);
    }
    public CreateDataException(Object[] parameters) {
        super("org.vpc.neormf.commons.exceptions.CreateDataException.Message",parameters);
    }
}

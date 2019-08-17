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
public class IllegalUpdateKeyException extends AbstractNeormfRuntimeException{
    public IllegalUpdateKeyException(Object[] parameters) {
        super("org.vpc.neormf.commons.exceptions.IllegalUpdateKeyException.Message",parameters);
    }
    public IllegalUpdateKeyException() {
        this(null);
    }
}

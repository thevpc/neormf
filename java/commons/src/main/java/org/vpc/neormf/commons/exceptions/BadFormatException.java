package org.vpc.neormf.commons.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: taha
 * Date: 14 dec. 2005
 * Time: 11:45:34
 * To change this template use File | Settings | File Templates.
 */
public class BadFormatException extends AbstractNeormfRuntimeException{
    public BadFormatException(String message,Object[] parameters) {
        super(message,parameters);
    }
}

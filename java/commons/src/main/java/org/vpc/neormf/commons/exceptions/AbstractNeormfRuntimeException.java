package org.vpc.neormf.commons.exceptions;

import org.vpc.neormf.commons.LocalizedObject;
import org.vpc.neormf.commons.util.NeormfMessages;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: ramzi
 * Date: 21 nov. 2005
 * Time: 23:16:00
 * To change this template use File | Settings | File Templates.
 */
public class AbstractNeormfRuntimeException extends RuntimeException implements LocalizedObject {
    private Locale locale;
    private Object[] parameters;

    public AbstractNeormfRuntimeException(String message,Object[] parameters) {
        super(message);
        setParameters(parameters);
    }

    public AbstractNeormfRuntimeException(String message,Object[] parameters,Throwable cause) {
        super(message,cause);
        setParameters(parameters);
    }

    public String getLocalizedMessage() {
        if (locale == null) {
            return super.getLocalizedMessage();
        }
        try {
            return NeormfMessages.getString(getMessage(), parameters==null?new Object[0] :parameters, getMessage(), locale);
        } catch (Throwable t) {
            return super.getLocalizedMessage();
        }
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

}

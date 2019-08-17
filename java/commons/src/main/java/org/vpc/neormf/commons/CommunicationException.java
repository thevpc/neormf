package org.vpc.neormf.commons;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 15 avr. 2004 Time: 17:16:22
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class CommunicationException extends Exception {
    private Throwable cause;

    public CommunicationException() {
    }

    public CommunicationException(String message) {
        super(message);
    }

    public CommunicationException(Throwable cause) {
        super(cause.getMessage());
        this.cause=cause;
    }

    public CommunicationException(String message, Throwable cause) {
        super(message);
        this.cause=cause;
    }

    public Throwable getCause() {
        return cause;
    }
}

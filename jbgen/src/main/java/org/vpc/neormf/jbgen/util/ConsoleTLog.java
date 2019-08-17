package org.vpc.neormf.jbgen.util;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 24 janv. 2007 08:54:26
 */
public class ConsoleTLog extends TLogAdapter{

    public ConsoleTLog() {
    }

    public void logString(String type, String message) {
        if(ERROR.equals(type) || WARNING.equals(type)){
            System.err.println(message);
        }else {
            System.out.println(message);
        }
    }

}

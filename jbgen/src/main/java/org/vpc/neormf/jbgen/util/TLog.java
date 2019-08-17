package org.vpc.neormf.jbgen.util;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 24 janv. 2007 08:50:30
 */
public interface TLog {
    public static final String ERROR="ERROR";
    public static final String DEBUG="DEBUG";
    public static final String WARNING="WARNING";
    public static final String INFO="INFO";
    public void log(String type,Object message);
    public void logString(String type,String message);
    public void debug(Object message);
    public void error(Object message);
    public void info(Object message);
    public void warning(Object message);
    public String format(String type,Object message);
}

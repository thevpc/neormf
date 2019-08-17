package org.vpc.neormf.jbgen.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 24 janv. 2007 08:51:46
 */
public abstract class TLogAdapter implements TLog {
    public static final SimpleDateFormat TIME_FORMATTER=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TLogAdapter() {
    }


    public void log(String type, Object message) {
        logString(type,format(type,message));
    }

    public void warning(Object message) {
        log(WARNING, message);
    }

    public final void debug(Object message) {
        log(DEBUG, message);
    }

    public final void error(Object message) {
        log(ERROR, message);
    }

    public final void info(Object message) {
        log(INFO, message);
    }


    public String format(String type,Object message) {
        return ("[" + TIME_FORMATTER.format(new Date()) + "] ["+type+"] " + messageToString(message));
    }

    public String messageToString(Object message) {
        String msgString = "";
        if (message != null) {
            if (message instanceof Throwable) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(out);
                Throwable th = (Throwable) message;
                th.printStackTrace(ps);
                ps.flush();
                msgString=out.toString();
            }else{
                msgString=String.valueOf(message);
            }
        }
        return msgString;
    }
}

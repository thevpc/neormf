package org.vpc.neormf.jbgen.util;

import javax.swing.*;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 24 janv. 2007 09:14:52
 */
public class JTextAreaTLog extends TLogAdapter{
    JTextArea area;

    public JTextAreaTLog(JTextArea area) {
        this.area = area;
        area.setEditable(false);
    }


    public JTextAreaTLog() {
        this(new JTextArea());
    }

    public JTextArea getArea() {
        return area;
    }

    public void logString(final String type,final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                area.append(message+"\n");
            }
        });
    }
    public void clear(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                area.setText("");
            }
        });
    }
}

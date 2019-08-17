package org.vpc.neormf.idesupport.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.vpc.neormf.jbgen.JBGenMain;

import javax.swing.*;

/**
 * @author Taha Ben Salah (thevpc@walla.com)
 * @creationtime 30 nov. 2006 21:06:41
 */
public class NeormfAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {

        JOptionPane.showMessageDialog(null,"Hello");
        //JBGenMain.main();
    }
}

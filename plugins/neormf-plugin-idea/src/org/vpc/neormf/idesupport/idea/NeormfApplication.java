package org.vpc.neormf.idesupport.idea;

import com.intellij.openapi.components.ApplicationComponent;

import javax.swing.*;

/**
 * @author Taha Ben Salah (thevpc@walla.com)
 * @creationtime 30 nov. 2006 21:52:11
 */
public class NeormfApplication implements ApplicationComponent {
    public NeormfApplication() {
    }

    public void initComponent() {
        JOptionPane.showMessageDialog(null,"NeormfApplication.initComponent");
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        JOptionPane.showMessageDialog(null,"NeormfApplication.disposeComponent");
        // TODO: insert component disposal logic here
    }

    public String getComponentName() {
        return "NeormfApplication";
    }
}

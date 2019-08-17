package org.vpc.neormf.idesupport.idea;

import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.module.Module;

import javax.swing.*;

/**
 * @author Taha Ben Salah (thevpc@walla.com)
 * @creationtime 30 nov. 2006 21:51:10
 */
public class NeormfModule implements ModuleComponent {
    Module module;
    public NeormfModule(Module module) {
//        this.module=module.;
    }

    public void initComponent() {
        //module.
//        JOptionPane.showMessageDialog(null,"NeormfModule.initComponent");

        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
//        JOptionPane.showMessageDialog(null,"NeormfModule.disposeComponent");

        // TODO: insert component disposal logic here
    }

    public String getComponentName() {
        return "NeormfModule";
    }

    public void projectOpened() {
        // called when project is opened
//        JOptionPane.showMessageDialog(null,"NeormfModule.projectOpened");
    }

    public void projectClosed() {
        // called when project is being closed
//        JOptionPane.showMessageDialog(null,"NeormfModule.projectClosed");
    }

    public void moduleAdded() {
        // Invoked when the module corresponding to this component instance has been completely
        // loaded and added to the project.
//        JOptionPane.showMessageDialog(null,"NeormfModule.moduleAdded");
    }
}

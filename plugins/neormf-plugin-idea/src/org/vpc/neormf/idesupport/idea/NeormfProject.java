package org.vpc.neormf.idesupport.idea;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * @author Taha Ben Salah (thevpc@walla.com)
 * @creationtime 30 nov. 2006 21:51:52
 */
public class NeormfProject implements ProjectComponent {
    public NeormfProject(Project project) {
    }

    public void initComponent() {
        JOptionPane.showMessageDialog(null,"NeormfProject.initComponent");
    }

    public void disposeComponent() {
        JOptionPane.showMessageDialog(null,"NeormfProject.disposeComponent");
    }

    public String getComponentName() {
        return "NeormfProject";
    }

    public void projectOpened() {
        JOptionPane.showMessageDialog(null,"NeormfProject.projectOpened");

    }

    public void projectClosed() {
        JOptionPane.showMessageDialog(null,"NeormfProject.projectClosed");
    }
}

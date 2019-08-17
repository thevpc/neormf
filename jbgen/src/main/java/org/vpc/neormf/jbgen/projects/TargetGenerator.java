package org.vpc.neormf.jbgen.projects;

import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 23:22:20
 * To change this template use File | Settings | File Templates.
 */
public class TargetGenerator {
    private String name;
    private String language;
    private JBGenModuleGenerator[] modules;

    public TargetGenerator(String name, String language, JBGenModuleGenerator[] modules) {
        this.name = name;
        this.language = language;
        this.modules = modules;
        for (int i = 0; i < modules.length; i++) {
            modules[i].setProject(this);
        }
    }


    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public JBGenModuleGenerator[] getModules() {
        return modules;
    }
}

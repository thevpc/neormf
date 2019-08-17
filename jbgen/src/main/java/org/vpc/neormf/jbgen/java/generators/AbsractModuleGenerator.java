package org.vpc.neormf.jbgen.java.generators;

import org.vpc.neormf.jbgen.projects.TargetGenerator;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;
import org.vpc.neormf.jbgen.JBGenMain;

public abstract class AbsractModuleGenerator implements JBGenModuleGenerator{
    private TargetGenerator target;
    private JBGenMain jbgen;


    protected AbsractModuleGenerator(JBGenMain jbgen) {
        this.jbgen = jbgen;
    }

    public TargetGenerator getProject() {
        return target;
    }

    public void setProject(TargetGenerator target) {
        this.target = target;
    }

    @Override
    public String toString() {
        String name = getClass().getName();
        if(name.indexOf('.')>0){
            name=name.substring(name.lastIndexOf('.')+1);
        }
        return JBGenUtils.toEnglishSpelling(name, true);
    }

    public TLog getLog() {
        return jbgen.getLog();
    }

    public JBGenMain getJbgen() {
        return jbgen;
    }
}

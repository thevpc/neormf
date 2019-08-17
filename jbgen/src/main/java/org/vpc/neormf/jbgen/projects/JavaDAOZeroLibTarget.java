package org.vpc.neormf.jbgen.projects;

import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
import org.vpc.neormf.jbgen.java.generators.daozerolib.JavaD0ZDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.daozerolib.JavaD0ZDataGenerator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.JBGenMain;


public class JavaDAOZeroLibTarget extends TargetGenerator {
    public static final String NAME = "java-dao-zerolib-target";

    public JavaDAOZeroLibTarget(JBGenMain jbgen) {
        super(NAME, JBGenProjectInfo.LANGUAGE_JAVA, new JBGenModuleGenerator[]{
                new JavaD0ZDataGenerator(jbgen),
                new JavaD0ZDAOGenerator(jbgen),
        });
    }
}

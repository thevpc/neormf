package org.vpc.neormf.jbgen.projects;

import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
import org.vpc.neormf.jbgen.java.generators.dao.JavaDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.dto.DataGenerator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.JBGenMain;

public class JavaDAOTarget extends TargetGenerator {
    public static final String NAME = "java-dao-target";

    public JavaDAOTarget(JBGenMain jbgen) {
        super(NAME, JBGenProjectInfo.LANGUAGE_CSHARP, new JBGenModuleGenerator[]{
                new DataGenerator(jbgen),
                new JavaDAOGenerator(jbgen),
//                new EjbSessionGenerator(),
//                new EjbClientGenerator(),
//                new J2eeDeploymentGenerator(),
//            new DataContentClassNamesListGenerator(),
        });
    }
}

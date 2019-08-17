package org.vpc.neormf.jbgen.projects;

import org.vpc.neormf.jbgen.csharp.generators.data.CSharpDataGenerator;
import org.vpc.neormf.jbgen.csharp.generators.dbc.CSharpDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.JBGenMain;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 23:24:07
 * To change this template use File | Settings | File Templates.
 */
public class CsharpDAOTarget extends TargetGenerator {
    public static final String NAME = "csharp-dao-target";

    public CsharpDAOTarget(JBGenMain jbgen) {
        super(NAME, JBGenProjectInfo.LANGUAGE_CSHARP, new JBGenModuleGenerator[]{
                new CSharpDataGenerator(jbgen),
                new CSharpDAOGenerator(jbgen),
//                new CSharpDataContentClassNamesListGenerator(),
        });
    }
}

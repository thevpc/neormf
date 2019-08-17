package org.vpc.neormf.jbgen.projects;

import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
import org.vpc.neormf.jbgen.java.generators.client.EjbClientGenerator;
import org.vpc.neormf.jbgen.java.generators.dao.JavaDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.dao.JavaJEEDAOGenerator;
import org.vpc.neormf.jbgen.java.generators.dto.DataGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.EjbEntityGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.EjbSessionGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.J2eeDeploymentGenerator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.java.generators.ejb.ejb3.EJB3EntiyGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.ejb3.dao.EJB3DAOGenerator;
import org.vpc.neormf.jbgen.java.generators.ejb.glassfish.SunResourcesXmlGenerator;
import org.vpc.neormf.jbgen.java.generators.web.WebXmlGenerator;

public class J2eeTarget extends TargetGenerator {
    public static final String NAME = "j2ee-target";
    public static final String MODULE_DTO = "dto-module";
    public static final String MODULE_EJB = "ejb-module";
    public static final String MODULE_EJB_BUSINESS_DELEGATE = "ejb-business-delegate-module";
    public static final String MODULE_DAO = "dao-module";
    public static final String MODULE_WEB = "web-module";

    public J2eeTarget(JBGenMain jbgen) {
        super(NAME, JBGenProjectInfo.LANGUAGE_JAVA, new JBGenModuleGenerator[]{
                new DataGenerator(jbgen),
                new EJB3EntiyGenerator(jbgen),
                new EJB3DAOGenerator(jbgen),
                new JavaJEEDAOGenerator(jbgen),
                new EjbEntityGenerator(jbgen),
                new J2eeDeploymentGenerator(jbgen),
                new EjbSessionGenerator(jbgen),
                new EjbClientGenerator(jbgen),
                new WebXmlGenerator(jbgen),
                new SunResourcesXmlGenerator(jbgen),
//            new DataContentClassNamesListGenerator(),
//                new EJB3JspxIceFaceGenerator(jbgen),
        });
    }
}

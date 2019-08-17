package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.java.generators.JBGenCompoundGenerator;
import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
import org.vpc.neormf.jbgen.JBGenMain;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 17:11:33
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class EjbEntityGenerator extends JBGenCompoundGenerator {
    public EjbEntityGenerator(JBGenMain jbgen) {
        super(jbgen,new JBGenModuleGenerator[]{
            new EjbEntityBMPBeanGenerator(jbgen),
            new EjbEntityCMPBeanGenerator(jbgen),
            new EjbEntityHomeGenerator(jbgen),
            new EjbEntityRemoteGenerator(jbgen)
        });
    }

    public String toString() {
        return "Entity Generator";
    }
}

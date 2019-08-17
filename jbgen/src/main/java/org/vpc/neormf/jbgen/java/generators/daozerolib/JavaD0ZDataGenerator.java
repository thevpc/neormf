package org.vpc.neormf.jbgen.java.generators.daozerolib;

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
public class JavaD0ZDataGenerator extends JBGenCompoundGenerator {

    public JavaD0ZDataGenerator(JBGenMain jbgen) {
        super(jbgen,new JBGenModuleGenerator[]{
            new JavaD0ZPrimaryKeyGenerator(jbgen),
            new JavaD0ZDTOGenerator(jbgen),
            new JavaD0ZPropertyListGenerator(jbgen),
            new JavaD0ZFilterGenerator(jbgen)
        });
    }

    public String toString() {
        return "Data Generator 0";
    }

}

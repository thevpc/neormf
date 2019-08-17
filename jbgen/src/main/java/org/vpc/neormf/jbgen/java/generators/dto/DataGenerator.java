/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators.dto;

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
public class DataGenerator extends JBGenCompoundGenerator {

    public DataGenerator(JBGenMain jbgen) {
        super(jbgen,new JBGenModuleGenerator[]{
            new PrimaryKeyGenerator(jbgen),
            new JavaDTOGenerator(jbgen),
            new JavaDTOMetaDataGenerator(jbgen),
            new PropertyListGenerator(jbgen),
            new OrderListGenerator(jbgen),
            new JavaModuleInfoGenerator(jbgen),
        });
    }

    public String toString() {
        return "Data Generator";
    }

}

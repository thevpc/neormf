package org.vpc.neormf.jbgen.java.types;

import org.vpc.neormf.commons.types.AbstractChoiceType;
import org.vpc.neormf.commons.types.DataType;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:26:31
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class JavaAbstractChoiceTypeGen implements JavaDataTypeGen {
    public String toCode(DataType type) {
        AbstractChoiceType him=(AbstractChoiceType) type;
        StringBuilder sb = new StringBuilder();
        sb.append("new ").append(him.getClass().getName()).append("(").append(him.isNullable());
        sb.append(",");
        sb.append(JavaDataTypeGenManager.getDatatypeGenerator(him.getElementType().getClass()).toCode(him.getElementType()));
        sb.append(")");
        return sb.toString();
    }
}

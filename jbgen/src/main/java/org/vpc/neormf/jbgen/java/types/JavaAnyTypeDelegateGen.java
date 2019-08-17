package org.vpc.neormf.jbgen.java.types;

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
public final class JavaAnyTypeDelegateGen implements JavaDataTypeGen {
    public String toCode(DataType type) {
        AnyTypeDelegate him = (AnyTypeDelegate) type;
        return "new AnyType(" + him.getJavaClassName() + ".class," + him.isNullable() + ")";
    }
}

package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.jbgen.java.types.JavaDataTypeGen;
import org.vpc.neormf.jbgen.java.types.AnyTypeDelegate;


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
public final class CSharpAnyTypeDelegateGen implements CSharpDataTypeGen {
    public String toCode(DataType type) {
        AnyTypeDelegate him = (AnyTypeDelegate) type;
        return "new AnyType(" + him.getJavaClassName() + ".class," + him.isNullable() + ")";
    }
}
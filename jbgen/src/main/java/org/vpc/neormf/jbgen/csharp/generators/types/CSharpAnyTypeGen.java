package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.AnyType;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;


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
public final class CSharpAnyTypeGen implements CSharpDataTypeGen {
    public String toCode(DataType type) {
        AnyType him = (AnyType) type;
        return "new " + CSharpUtils.getCSharpClassName(him.getClass()) + "(typeof(" + CSharpUtils.getCSharpClassName(him.toJavaType()) + ")," + him.isNullable() + ")";
    }
}

package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.DateType;

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
public class CSharpDateTypeGen implements CSharpDataTypeGen {
    public String toCode(DataType type) {
        DateType him = (DateType) type;
        if (him.getMin() == 0 && him.getMax() == 0) {
            return !him.isNullable() ? (him.getClass().getName() + ".DATE_NON_NULLABLE") : (him.getClass().getName() + ".DATE_NULLABLE");
        }
        return "new " + him.getClass().getName() + "(" + him.isNullable() +
                "," + (him.getMin() <= 0 ? "null" : "new java.util.Date(" + him.getMin() + "L)") +
                "," + (him.getMax() <= 0 ? "null" : "new java.util.Date(" + him.getMax() + "L)") +
                ")";
    }
}

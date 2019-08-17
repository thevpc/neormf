package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.IntType;
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
public class CSharpIntTypeGen implements CSharpDataTypeGen {
    public String toCode(DataType type) {
        IntType him = (IntType) type;
        int minValue = him.getMinValue();
        int maxValue = him.getMaxValue();
        if(minValue==Integer.MIN_VALUE && maxValue==Integer.MAX_VALUE){
            return !him.isNullable() ? (him.getClass().getName()+".INT_NON_NULLABLE") :(him.getClass().getName()+".INT_NULLABLE");
        }
        return "new " + him.getClass().getName() + "(" + him.isNullable() +
                "," +
                   (
                       (minValue==Integer.MAX_VALUE)?
                            "Int32.MaxValue":
                        (minValue==Integer.MIN_VALUE)?
                            "Int32.MinValue":
                            String.valueOf(minValue)
                    )+
                "," +
                (
                    (maxValue==Integer.MAX_VALUE)?
                         "Int32.MaxValue":
                     (maxValue==Integer.MIN_VALUE)?
                         "Int32.MinValue":
                         String.valueOf(maxValue)
                 )+
                ")";
    }
}

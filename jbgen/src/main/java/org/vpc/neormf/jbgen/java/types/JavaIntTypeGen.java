package org.vpc.neormf.jbgen.java.types;

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
public class JavaIntTypeGen implements JavaDataTypeGen {
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
                            "Integer.MAX_VALUE":
                        (minValue==Integer.MIN_VALUE)?
                            "Integer.MIN_VALUE":
                            String.valueOf(minValue)
                    )+
                "," +
                (
                    (maxValue==Integer.MAX_VALUE)?
                         "Integer.MAX_VALUE":
                     (maxValue==Integer.MIN_VALUE)?
                         "Integer.MIN_VALUE":
                         String.valueOf(maxValue)
                 )+
                ")";
    }
}

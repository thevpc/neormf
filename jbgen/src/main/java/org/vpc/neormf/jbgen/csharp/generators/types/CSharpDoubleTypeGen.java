package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.DoubleType;
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
public class CSharpDoubleTypeGen implements CSharpDataTypeGen {
    public String toCode(DataType type) {
        DoubleType him = (DoubleType) type;
        double minValue = him.getMinValue();
        double maxValue = him.getMaxValue();
        if (Double.isNaN(minValue) && Double.isNaN(maxValue)) {
            return !him.isNullable() ? (him.getClass().getName() + ".DOUBLE_NON_NULLABLE") : (him.getClass().getName() + ".DOUBLE_NULLABLE");
        }
        return "new " + him.getClass().getName() + "(" + him.isNullable() +
                "," +
                (
                        Double.isNaN(minValue) ?
                                "Double.NaN" :
                                minValue == Double.MAX_VALUE ?
                                        "Double.MaxValue" :
                                        minValue == Double.MIN_VALUE ?
                                                "Double.MinValue" :
                                                minValue == Double.NEGATIVE_INFINITY ?
                                                        "Double.NegativeInfinity" :
                                                        minValue == Double.POSITIVE_INFINITY ?
                                                                "Double.PositiveInfinity" :
                                                                String.valueOf(minValue)
                ) +
                "," + (
                Double.isNaN(maxValue) ?
                        "Double.NaN" :
                        maxValue == Double.MAX_VALUE ?
                                "Double.MaxValue" :
                                maxValue == Double.MIN_VALUE ?
                                        "Double.MinValue" :
                                        maxValue == Double.NEGATIVE_INFINITY ?
                                                "Double.NegativeInfinity" :
                                                maxValue == Double.POSITIVE_INFINITY ?
                                                        "Double.PositiveInfinity" :
                                                        String.valueOf(maxValue)
        ) +
                ")";
    }
}

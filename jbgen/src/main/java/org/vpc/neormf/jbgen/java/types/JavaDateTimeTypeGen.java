package org.vpc.neormf.jbgen.java.types;

import org.vpc.neormf.commons.types.DateTimeType;
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
public class JavaDateTimeTypeGen implements JavaDataTypeGen {
    public String toCode(DataType type) {
        DateTimeType him=(DateTimeType) type;
        if(him.getMin()==0 && him.getMax()==0){
            return !type.isNullable() ? (him.getClass().getName()+".DATETIME_NON_NULLABLE") :(him.getClass().getName()+".DATETIME_NULLABLE");
        }
        return "new " + him.getClass().getName() + "(" + type.isNullable() +
                "," + (him.getMin() <= 0 ? "null" : "new java.sql.Timestamp(" + him.getMin() + "L)") +
                "," + (him.getMax() <= 0 ? "null" : "new java.sql.Timestamp(" + him.getMax() + "L)") +
                ")";
    }
}

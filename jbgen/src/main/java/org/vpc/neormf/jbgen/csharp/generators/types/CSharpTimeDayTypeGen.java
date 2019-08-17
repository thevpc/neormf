package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.TimeDayType;
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
public class CSharpTimeDayTypeGen extends CSharpDateTimeTypeGen {
    public String toCode(DataType type) {
        TimeDayType him=(TimeDayType) type;
        if(him.getMin()==0 && him.getMax()==0){
            return !him.isNullable() ? (him.getClass().getName()+".TIMEDAY_NON_NULLABLE") :(him.getClass().getName()+".TIMEDAY_NULLABLE");
        }
        return "new " + him.getClass().getName() + "(" + him.isNullable() +
                "," + (him.getMin() <= 0 ? "null" : "new java.sql.Timestamp(" + him.getMin() + "L)") +
                "," + (him.getMax() <= 0 ? "null" : "new java.sql.Timestamp(" + him.getMax() + "L)") +
                ")";
    }
}

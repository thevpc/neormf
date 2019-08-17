/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.commons.types.converters;

import org.vpc.neormf.commons.types.BooleanType;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.IntType;

import java.util.NoSuchElementException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 14:41:43
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class IntegerToBoolean extends DataTypeConverter {

    private DataType sql = new IntType(true);

    private boolean nullIsFalse;

    public IntegerToBoolean() {
        this(true);
    }
    
    public IntegerToBoolean(boolean nullIsFalse) {
        this.nullIsFalse=nullIsFalse;
    }

    public DataType getSQLDataType() {
        return sql;
    }

    public DataType getBusinessDataType(DataType baseType) {
        return baseType.isNullable()?BooleanType.BOOL_NULLABLE:BooleanType.BOOL_NON_NULLABLE;
    }

    public Object businessToSQL(Object businessObject) {
        if(nullIsFalse){
            return businessObject == null ? new Integer(0) : ((Boolean) businessObject).booleanValue() ? new Integer(1) : new Integer(0);
        }else{
            return businessObject == null ? null : ((Boolean) businessObject).booleanValue() ? new Integer(1) : new Integer(0);
        }
    }

    public Object sqlToBusiness(Object sqlObject) {
        if(sqlObject == null){
            return nullIsFalse?Boolean.FALSE: null;
        }
        if(new Integer(1).equals(sqlObject)){
            return Boolean.TRUE;
        }
        if(new Integer(0).equals(sqlObject)){
            return Boolean.FALSE;
        }
        throw new NoSuchElementException("Unexpected sql value '"+sqlObject+"' ; possible values are {0,1}");
//        return sqlObject == null ? null : trueChar.equals(sqlObject) ? Boolean.TRUE : falseChar.equals(sqlObject) ? Boolean.FALSE
//                : null; // throw exception ???
    }
}

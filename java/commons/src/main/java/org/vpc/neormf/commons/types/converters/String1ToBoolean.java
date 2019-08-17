/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.commons.types.converters;

import org.vpc.neormf.commons.types.BooleanType;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.StringType;

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
public class String1ToBoolean extends DataTypeConverter {

    private DataType sql = new StringType(true, 0, 1,false);

    private String trueChar;
    private String falseChar;
    private boolean nullIsFalse;

    public String1ToBoolean(char trueChar, char falseChar) {
        this(trueChar, falseChar,false);
    }
    
    public String1ToBoolean(char trueChar, char falseChar,boolean nullIsFalse) {
        this.trueChar = new String(new char[]{trueChar});
        this.falseChar = new String(new char[]{falseChar});
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
            return businessObject == null ? falseChar : ((Boolean) businessObject).booleanValue() ? trueChar : falseChar;
        }else{
            return businessObject == null ? null : ((Boolean) businessObject).booleanValue() ? trueChar : falseChar;
        }
    }

    public Object sqlToBusiness(Object sqlObject) {
        if(sqlObject == null){
            return nullIsFalse?Boolean.FALSE: null;
        }
        if(trueChar.equals(sqlObject)){
            return Boolean.TRUE;
        }
        if(falseChar.equals(sqlObject)){
            return Boolean.FALSE;
        }
        throw new NoSuchElementException("Unexpected sql value '"+sqlObject+"' ; possible values are {'"+trueChar+"','"+falseChar+"'}");
//        return sqlObject == null ? null : trueChar.equals(sqlObject) ? Boolean.TRUE : falseChar.equals(sqlObject) ? Boolean.FALSE
//                : null; // throw exception ???
    }
}

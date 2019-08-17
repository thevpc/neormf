/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.util.Utils;


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
public class BooleanType extends DataType {
    public static final int TYPE = 1;
    public static final BooleanType BOOL_NULLABLE = new BooleanType(true);
    public static final BooleanType BOOL_NON_NULLABLE = new BooleanType(false);

    public BooleanType(boolean nullable) {
        super(nullable);
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object == null) {
            return null;
        }
       return  Utils.castTo(object, Boolean.class);
    }

    public Class toJavaType() {
        return Boolean.TYPE;
    }

    public int getTypeId() {
        return TYPE;
    }

}

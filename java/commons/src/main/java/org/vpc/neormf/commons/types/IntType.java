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
public class IntType extends DataType {
    public static final IntType INT_NULLABLE = new IntType(true);
    public static final IntType INT_NON_NULLABLE= new IntType(false);
    public static final int TYPE = 2;
    private int minValue = Integer.MIN_VALUE;
    private int maxValue = Integer.MAX_VALUE;

    public IntType() {
        this(true);
    }
    public IntType(boolean nullable) {
        super(nullable);
    }

    public IntType(boolean nullable, int limit, boolean isSuperiorLimit) {
        super(nullable);
        if (isSuperiorLimit) {
            maxValue = limit;
        } else {
            minValue = limit;
        }
    }

    public IntType(boolean nullable, int minValue, int maxValue) {
        super(nullable);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object == null) {
            return null;
        }
        Integer i = (Integer) Utils.castTo(object, Integer.class);
        if (i.intValue() < this.minValue) {
            throw new DataException("org.vpc.neormf.commons.types.IntType.TooLow.Message", new Object[]{fieldName});
        }
        if (i.intValue() > this.maxValue) {
            throw new DataException("org.vpc.neormf.commons.types.IntType.TooHight.Message", new Object[]{fieldName});
       }
        return i;
    }

    public Class toJavaType() {
        return Integer.TYPE;
    }


    public int getTypeId() {
        return TYPE;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IntType intType = (IntType) o;

        if (maxValue != intType.maxValue) return false;
        if (minValue != intType.minValue) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + minValue;
        result = 31 * result + maxValue;
        return result;
    }
}

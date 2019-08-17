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
public class DoubleType extends DataType {
    public static final DoubleType DOUBLE_NULLABLE = new DoubleType(true);
    public static final DoubleType DOUBLE_NON_NULLABLE= new DoubleType(false);
    public static final int TYPE = 3;
    private double minValue = Double.NaN;
    private double maxValue = Double.NaN;

    public DoubleType() {
        this(true);
    }
    public DoubleType(boolean nullable) {
        super(nullable);
    }

    public DoubleType(boolean nullable, double limit, boolean isSuperiorLimit) {
        super(nullable);
        if (isSuperiorLimit) {
            maxValue = limit;
        } else {
            minValue = limit;
        }
    }

    public DoubleType(boolean nullable, double minValue, double maxValue) {
        super(nullable);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object == null) {
            return null;
        }
        Double dobj = (Double) Utils.castTo(object, Double.class);
        double d = dobj.doubleValue();
        if (!Double.isNaN(this.minValue) && d < this.minValue) {
            throw new DataException("org.vpc.neormf.commons.types.DoubleType.TooLow.Message", new Object[]{fieldName});
        }
        if (!Double.isNaN(this.maxValue) && d > this.maxValue) {
            throw new DataException("org.vpc.neormf.commons.types.DoubleType.TooHight.Message", new Object[]{fieldName});
        }
        return dobj;
    }

    public Class toJavaType() {
        return Double.TYPE;
    }

    public int getTypeId() {
        return TYPE;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DoubleType that = (DoubleType) o;

        if (Double.compare(that.maxValue, maxValue) != 0) return false;
        if (Double.compare(that.minValue, minValue) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = minValue != +0.0d ? Double.doubleToLongBits(minValue) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = maxValue != +0.0d ? Double.doubleToLongBits(maxValue) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

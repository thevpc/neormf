package org.vpc.neormf.commons.types.converters;

import org.vpc.neormf.commons.types.DataType;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 14:39:14
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class DataTypeConverter {
    public abstract DataType getSQLDataType();

    public abstract DataType getBusinessDataType(DataType baseType);

    public abstract Object businessToSQL(Object businessObject);

    public abstract Object sqlToBusiness(Object sqlObject);
}

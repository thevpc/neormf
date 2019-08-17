/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.converters;

import org.vpc.neormf.commons.types.converters.DataTypeConverter;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 17:12:12
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract interface DataTypeConverterFactory {


    public DataTypeConverter getConverter(JBGenProjectInfo project);

    public String getConverterExpression();
}

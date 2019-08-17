/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.converters;

import org.vpc.neormf.commons.types.converters.DataTypeConverter;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaEvaluator;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 17:13:18
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JavaExpressionDataTypeConverterFactory implements DataTypeConverterFactory {
    private String expresion;
    private DataTypeConverter cachedWrapper;
    private TLog tlog;

    public JavaExpressionDataTypeConverterFactory(TLog tlog,String expresion) {
        this.expresion = expresion;
        this.tlog = tlog;
    }

    public String getConverterExpression() {
        return expresion;
    }

    public DataTypeConverter getConverter(JBGenProjectInfo project) {
        if (cachedWrapper != null) {
            return cachedWrapper;
        }
        JavaEvaluator javaEvaluator = new JavaEvaluator(tlog);
        JavaClassSource c = new JavaClassSource();
        c.addImport("org.vpc.neormf.commons.types.*");
        c.addImport("org.vpc.neormf.commons.types.converters.*");
        javaEvaluator.setExtraClass(c);
        cachedWrapper = (DataTypeConverter) JBGenUtils.castTo(javaEvaluator.evaluate(expresion,project.getClasspath()), DataTypeConverter.class);
        return cachedWrapper;
    }

}

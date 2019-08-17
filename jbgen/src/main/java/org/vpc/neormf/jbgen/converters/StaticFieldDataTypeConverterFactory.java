/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.converters;

import org.vpc.neormf.commons.types.converters.DataTypeConverter;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;

import java.lang.reflect.Field;

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
public class StaticFieldDataTypeConverterFactory implements DataTypeConverterFactory {
    private String expresion;
    private DataTypeConverter cachedWrapper;

    public StaticFieldDataTypeConverterFactory(String expresion) {
        this.expresion = expresion;
    }

    public String getConverterExpression() {
        return expresion;
    }

    public DataTypeConverter getConverter(JBGenProjectInfo project) {
        if (cachedWrapper != null) {
            return cachedWrapper;
        }
        try {
            int i = expresion.lastIndexOf('.');
            Class c = Class.forName(expresion.substring(0, i));
            Field f = c.getField(expresion.substring(i + 1));
            cachedWrapper = (DataTypeConverter) JBGenUtils.castTo(f.get(null), DataTypeConverter.class);
            return cachedWrapper;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}

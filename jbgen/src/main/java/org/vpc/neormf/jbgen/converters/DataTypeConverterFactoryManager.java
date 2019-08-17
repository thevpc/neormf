package org.vpc.neormf.jbgen.converters;

import java.lang.reflect.Field;
import org.vpc.neormf.jbgen.JBGenMain;

import java.util.Hashtable;
import org.vpc.neormf.commons.types.converters.Converters;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 24 janv. 2007 11:08:50
 */
public class DataTypeConverterFactoryManager {

    private Hashtable mappings;
    private Hashtable cachedMappings;
    private JBGenMain jbgen;

    public DataTypeConverterFactoryManager(JBGenMain jbgen) {
        this.jbgen = jbgen;
        for (Field field : Converters.class.getClass().getFields()) {
            declareFactory(field.getName(), Converters.class.getName() + "." + field.getName());
        }
    }

    public void declareFactory(String factoryKey, DataTypeConverterFactory factory) {
        if (mappings == null) {
            mappings = new Hashtable();
        }
        mappings.put(factoryKey, factory);
    }

    public void declareFactory(String wrapperKey, String lazyFactory) {
        if (mappings == null) {
            mappings = new Hashtable();
        }
        mappings.put(wrapperKey, lazyFactory);
    }

    public DataTypeConverterFactory getFactory(String factoryKey) {
        Object o = null;
        if (o == null && cachedMappings != null) {
            o = cachedMappings.get(factoryKey);
        }
        if (o == null && mappings != null) {
            o = mappings.get(factoryKey);
        }
        if (o == null) {
            String s = (String) factoryKey;
            s = s.trim();
            DataTypeConverterFactory f = new JavaExpressionDataTypeConverterFactory(jbgen.getLog(), s);
            if (cachedMappings == null) {
                cachedMappings = new Hashtable();
            }
            cachedMappings.put(factoryKey, f);
            return f;

        } else if (o instanceof String) {
            String s = (String) o;
            s = s.trim();
            DataTypeConverterFactory f = new JavaExpressionDataTypeConverterFactory(jbgen.getLog(), s);
            if (cachedMappings == null) {
                cachedMappings = new Hashtable();
            }
            cachedMappings.put(factoryKey, f);
            return f;

        } else {
            return (DataTypeConverterFactory) o;
        }
    }
}

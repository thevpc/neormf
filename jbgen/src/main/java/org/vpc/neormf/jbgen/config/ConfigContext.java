package org.vpc.neormf.jbgen.config;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 19:35:32
 * To change this template use File | Settings | File Templates.
 */
public class ConfigContext {
    Hashtable map = new Hashtable();

    public void put(String key, Object value) {
        if (value == null) {
            map.remove(key);
        } else {
            map.put(key, value);
        }
    }

    public Object get(String key) {
        return map.get(key);
    }
}

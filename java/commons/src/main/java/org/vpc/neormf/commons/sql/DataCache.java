package org.vpc.neormf.commons.sql;

import org.vpc.neormf.commons.beans.DataTransfertObject;
import org.vpc.neormf.commons.beans.DataKey;

import java.util.Hashtable;

/**
 * @author : vpc
 * @creationtime 6 févr. 2006 18:26:05
 */
public class DataCache {
    private long timeout = -1;
    private int max = 100;
    Hashtable map = new Hashtable();

    public DataCache(int max,long timout) {
        this.timeout = timout;
        this.max = max;
    }

    private static class CacheItem {
        private DataTransfertObject value;
        private long date;

        public CacheItem(DataTransfertObject value) {
            this.value = value;
            date = System.currentTimeMillis();
        }
    }

    public synchronized void add(DataTransfertObject d) {
        map.put(d.getDataKey(), new CacheItem(d));
        if(map.size()>max){
            int x=(int) (Math.random()*max);
            map.remove(map.keySet().toArray()[x]);
        }
    }

    public void remove(DataKey key) {
        map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public DataTransfertObject get(DataKey key) {
        CacheItem c = (CacheItem) map.get(key);
        if(c==null){
            return null;
        }
        if(c.date+timeout >System.currentTimeMillis()){
            map.remove(key);
            return null;
        }
        return c.value;
    }

}

package org.vpc.neormf.commons.util;

import org.vpc.neormf.commons.beans.DataTransfertObject;
import org.vpc.neormf.commons.beans.DataKey;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 26 juin 2007 17:01:52
 */
public final class DTOUtils {

    private DTOUtils() {
    }

    public static Collection filter(Collection dtos, DataTransfertObject prototype){
        ArrayList all=new ArrayList();
        for (Iterator i = dtos.iterator(); i.hasNext();) {
            DataTransfertObject d= (DataTransfertObject) i.next();
            boolean ok=true;
            for (Iterator j = prototype.keySet().iterator(); j.hasNext();) {
                String key = (String) j.next();
                Object o1=d.getProperty(key);
                Object o2=prototype.getProperty(key);
                if(!(o1==o2 || (o1!=null && o2!=null && o1.equals(o2)))){
                    ok=false;
                    break;
                }
            }
            if(ok){
               all.add(d);
            }
        }
        return all;
    }

    public static DataTransfertObject findDTO(Collection dtos, DataKey key){
        return findDTO(dtos, key.toDTO());
    }

    public static DataTransfertObject findDTO(Collection dtos, DataTransfertObject prototype){
        for (Iterator i = dtos.iterator(); i.hasNext();) {
            DataTransfertObject d= (DataTransfertObject) i.next();
            boolean ok=true;
            for (Iterator j = prototype.keySet().iterator(); j.hasNext();) {
                String key = (String) j.next();
                Object o1=d.getProperty(key);
                Object o2=prototype.getProperty(key);
                if(!(o1==o2 || (o1!=null && o2!=null && o1.equals(o2)))){
                    ok=false;
                    break;
                }
            }
            if(ok){
               return d;
            }
        }
        return null;
    }
}

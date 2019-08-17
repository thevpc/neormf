package org.vpc.neormf.commons.beans;

import java.io.Serializable;
import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 11:41:49
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class PropertyList implements Serializable {
    private LinkedHashMap set;

    public PropertyList() {
        this.set = new LinkedHashMap();
    }

    public PropertyList addProperty(String fieldName) {
        addProperty(fieldName,null);
        return this;
    }

    public PropertyList addProperty(String fieldName,Object constraints) {
        if (!set.containsKey(fieldName)) {
            set.put(fieldName,constraints);
        }
        return this;
    }

    public PropertyList addAllProperties(String[] fieldNames) {
        for (int i = 0; i < fieldNames.length; i++) {
            addProperty(fieldNames[i]);
        }
        return this;
    }

    public PropertyList removeAllProperties(String[] fieldNames) {
        for (int i = 0; i < fieldNames.length; i++) {
            removeProperty(fieldNames[i]);
        }
        return this;
    }

    public Object getPropertyConstraints(String propertyName){
        return set.get(propertyName);
    }
    
//    public PropertyList insertProperty(String fieldName, int position) {
//        return insertProperty(fieldName, null,position);
//    }

//    public PropertyList insertProperty(String fieldName, Object constraints,int position) {
//        if (!set.containsKey(fieldName)) {
//            set.put(position, constraints);
//        }
//        return this;
//    }

    public PropertyList removeProperty(String fieldName) {
        set.remove(fieldName);
        return this;
    }

    public boolean containsProperty(String fieldName) {
        return set.containsKey(fieldName);
    }

    public Iterator propertiesIterator() {
        return set.keySet().iterator();
    }

    public int size() {
        return set.size();
    }

    public Set propertiesSet() {
        return set.keySet();
//        TreeSet ts=new TreeSet();
//        for (Iterator i = set.iterator(); i.hasNext();) {
//            Property item = (Property) i.next();
//            ts.add(item.name);
//        }
//        return Collections.unmodifiableSet(ts);
    }

    public void clear() {
        set.clear();
    }

    public String[] toArray() {
        Set s=propertiesSet();
        return (String[]) s.toArray(new String[s.size()]);
    }

    public String toString() {
        return "PropertyList{" +
                "set=" + set +
                "}";
    }

    public void addAllProperties() {
    }
    
    public void addAllKeyProperties() {
        addAllProperties(metadata().getKeyFieldNames());
    }

    public DTOMetaData metadata() {
//        throw new UnsupportedOperationException();
        return null;
    }
    
//    private class ItemNameIterator implements Iterator{
//        Iterator base;
//        ItemNameIterator(){
//            base=set.iterator();
//        }
//        public void remove() {
//            base.remove();
//        }
//
//        public boolean hasNext() {
//            return base.hasNext();
//        }
//
//        public Object next() {
//            return ((Property) base.next()).name;
//        }
//    }

//    public static class Property implements Serializable{
//        private String name;
//        private Object constraints;
//
//        public Property(String name, Object constraints) {
//            this.name = name;
//            this.constraints = constraints;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public Object getConstraints() {
//            return constraints;
//        }
//
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            final Property item = (Property) o;
//
//            if (name != null ? !name.equals(item.name) : item.name != null) return false;
//
//            return true;
//        }
//
//        public int hashCode() {
//            return (name != null ? name.hashCode() : 0);
//        }
//
//    }
}

package org.vpc.neormf.commons.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

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
public class OrderList implements Serializable {
    static final long serialVersionUID = -2501669695600987885L;

    private ArrayList set;

    public OrderList() {
        this.set = new ArrayList();
    }

    public OrderList add(String fieldName) {
        return add(new OrderItem(fieldName, true));
    }

    public OrderList add(String fieldName, boolean ascendent) {
        return add(new OrderItem(fieldName, ascendent));
    }

    public OrderList add(OrderItem orderItem) {
        if (!set.contains(orderItem)) {
            set.add(orderItem);
        }
        return this;
    }

    public OrderList addAll(String[] fieldNames) {
        for (int i = 0; i < fieldNames.length; i++) {
            add(fieldNames[i]);
        }
        return this;
    }

    public OrderList removeAll(String[] fieldNames) {
        for (int i = 0; i < fieldNames.length; i++) {
            set.remove(new OrderItem(fieldNames[i], true));
        }
        return this;
    }

    public OrderList insert(String fieldName, int position) {
        return insert(new OrderItem(fieldName, true), position);
    }

    public OrderList insert(String fieldName, boolean ascendent, int position) {
        return insert(new OrderItem(fieldName, ascendent), position);
    }

    public OrderList insert(OrderItem orderItem, int position) {
        if (!set.contains(orderItem)) {
            set.add(position, orderItem);
        }
        return this;
    }

    public OrderList remove(String fieldName) {
        set.remove(new OrderItem(fieldName, true));
        return this;
    }

    public boolean contains(String fieldName) {
        return set.contains(new OrderItem(fieldName, true));
    }

    public Iterator iterator() {
        return set.iterator();
    }

    public String toString() {
        return "OrderList{" +
                "set=" + set +
                "}";
    }

    public static class OrderItem implements Serializable {
        static final long serialVersionUID = -2501669695600987886L;

        private String fieldName;
        private boolean ascendent;

        public OrderItem(String fieldName, boolean ascendent) {
            this.fieldName = fieldName;
            this.ascendent = ascendent;
        }

        public boolean equals(Object obj) {
            return fieldName.equals(((OrderItem) obj).fieldName);
        }

        public int hashCode() {
            return fieldName.hashCode();
        }

        public String getFieldName() {
            return fieldName;
        }

        public boolean isAscendent() {
            return ascendent;
        }

        public String toString() {
            return "OrderItem{" +
                    "fieldName='" + fieldName + "'" +
                    ", ascendent=" + ascendent +
                    "}";
        }

    }

}

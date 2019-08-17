/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.commons.beans;

import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 5 avr. 2004 Time: 14:09:37
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DTOMetaData {
    private DTOFieldMetaData titleField;
    private DTOFieldMetaData[] keyFields;
    private DTOFieldMetaData[] orderFields;
    private boolean[] orderFieldsAsc;
    private ArrayList<DTOFieldMetaData> fields = new ArrayList<DTOFieldMetaData>();
    private Hashtable fieldsMap = new Hashtable();
    private String beanName;
    private String dataClassName;
    private String orderClassName;
    private String dataKeyClassName;
    private String propertyListClassName;
    private Properties properties = new Properties();
    private Comparator<String> populationOrderComparatorByName;

    private class PopulationOrderComparatorByName implements Comparator<String> {
        public int compare(String o1, String o2) {
            DTOFieldMetaData f1=getField(o1);
            DTOFieldMetaData f2=getField(o2);
            return f1.getPopulationOrder()-f2.getPopulationOrder();
        }
    };

    public DTOMetaData(String beanName, DTOFieldMetaData[] fields, String[] pkFields, String titleFieldName,String dataClassName, String dataKeyClassName, String propertyFilterClassName, String orderClassName, String[] orderedFields, boolean[] orderFieldsAsc, Properties properties) {
        this.beanName = beanName;
        setDataClassName(dataClassName);
        setDataKeyClassName(dataKeyClassName);
        setPropertyListClassName(propertyFilterClassName);
        setOrderListClassName(orderClassName);
        if (properties != null) {
            this.properties.putAll(properties);
        }
        for (int i = 0; i < fields.length; i++) {
            addField(fields[i]);
        }
        this.keyFields = new DTOFieldMetaData[pkFields.length];
        for (int i = 0; i < keyFields.length; i++) {
            keyFields[i] = getField(pkFields[i]);
        }
        if(titleFieldName==null){
            if(this.keyFields.length>0){
                this.titleField=this.keyFields[0];
            }else if(this.fields.size()>0){
                this.titleField=(DTOFieldMetaData) this.fields.get(0);
            }else{
                //What to do???
            }
        }else{
            this.titleField = getField(titleFieldName);
        }

        setOrder(orderedFields, orderFieldsAsc);
        populationOrderComparatorByName=new PopulationOrderComparatorByName();
    }

    public DTOMetaData(String beanName) {
        this.beanName = beanName;
    }

    public void setTitleField(String titleField) {
        this.titleField = getField(titleField);
    }

    public String getDataClassName() {
        return dataClassName;
    }

    public void setDataClassName(String dataClassName) {
        this.dataClassName = dataClassName;
    }

    public String getPropertyListClassName() {
        return propertyListClassName;
    }

    public void setPropertyListClassName(String propertyListClassName) {
        this.propertyListClassName = propertyListClassName;
    }

    public void setOrderListClassName(String orderClassName) {
        this.orderClassName = orderClassName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public DTOFieldMetaData getTitleField() {
        return titleField;
    }

    public void addField(DTOFieldMetaData dataField) {
        fields.add(dataField);
        fieldsMap.put(dataField.getFieldName(), dataField);
        dataField.setDTOMetaData(this);
    }

    public DTOFieldMetaData[] getFields() {
        return (DTOFieldMetaData[]) fields.toArray(new DTOFieldMetaData[fields.size()]);
    }

    public DTOFieldMetaData getField(String fieldName) {
        DTOFieldMetaData df=(DTOFieldMetaData) fieldsMap.get(fieldName);
        if(df==null){
            throw new NoSuchElementException(getBeanName()+"."+fieldName+" not found");
        }
        return df;
    }

    public DTOFieldMetaData getField(int index) {
        return (DTOFieldMetaData) fields.get(index);
    }

    public int size() {
        return fields.size();
    }

    public void setOrder(String[] fields, boolean[] asc) {
        if (fields == null) {
            this.orderFields = null;
            this.orderFieldsAsc = null;
            return;
        }
        if (fields.length != asc.length) {
            throw new IllegalArgumentException("Fields count must match asc count");
        }
        this.orderFields = new DTOFieldMetaData[fields.length];
        this.orderFieldsAsc = new boolean[fields.length];
        for (int i = 0; i < fields.length; i++) {
            this.orderFields[i] = getField(fields[i]);
            this.orderFieldsAsc[i] = asc[i];
        }
    }

    public String getDataKeyClassName() {
        return dataKeyClassName;
    }

    public void setDataKeyClassName(String dataKeyClassName) {
        this.dataKeyClassName = dataKeyClassName;
    }

    public DTOFieldMetaData[] getKeyFields() {
        return keyFields;
    }
    
    public DTOFieldMetaData[] getFields(DataFieldFilter filter) {
        if(filter==null){
            return getFields();
        }
        ArrayList<DTOFieldMetaData> all=new ArrayList<DTOFieldMetaData>(fields.size());
        for (DTOFieldMetaData object : fields) {
            if(filter.accept(object)){
                all.add(object);
            }
        }
        return all.toArray(new DTOFieldMetaData[all.size()]);
    }
    
    public DTOFieldMetaData[] getNonKeyFields() {
        TreeSet k=new TreeSet();
        for(int i=0;i<keyFields.length;i++){
            k.add(keyFields[i].getFieldName());
        }
        ArrayList<DTOFieldMetaData> all=new ArrayList<DTOFieldMetaData>();
        for(int i=0;i<fields.size();i++){
            DTOFieldMetaData df=(DTOFieldMetaData)fields.get(i);
            if(!k.contains(df.getFieldName())){
                all.add(df);
            }
        }        
        return (DTOFieldMetaData[])all.toArray(new DTOFieldMetaData[all.size()]);
    }

    public String[] getKeyFieldNames() {
        String[] f=new String[keyFields.length];
        for (int i = 0; i < f.length; i++) {
            f[i]=keyFields[i].getFieldName();
        }
        return f;
    }

    public Class getPropertyListClass() {
        try {
            return Class.forName(propertyListClassName);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public Class getDataKeyClass() {
        try {
            return Class.forName(dataKeyClassName);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public PropertyList createPropertyList() {
        try {
            return (PropertyList) Class.forName(propertyListClassName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public OrderList createOrderList() {
        try {
            return (OrderList) Class.forName(orderClassName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public DataTransfertObject createDTO() {
        try {
            return (DataTransfertObject) Class.forName(dataClassName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public Class getDTOClass() {
        try {
            return Class.forName(dataClassName);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public Class getOrderListClass() {
        try {
            return Class.forName(orderClassName);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public Comparator<String> getPopulationOrderComparator(){
      return populationOrderComparatorByName;
    }
}

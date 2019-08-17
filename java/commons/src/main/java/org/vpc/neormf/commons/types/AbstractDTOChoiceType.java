package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.beans.DataTransfertObject;
import org.vpc.neormf.commons.beans.PropertyList;
import org.vpc.neormf.commons.beans.DTOFieldMetaData;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 aout 2005
 * Time: 16:11:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDTOChoiceType extends AbstractChoiceType implements Cloneable{
    private DataTransfertObject dataPrototype;

    public AbstractDTOChoiceType(boolean nullable, DataTransfertObject dataPrototype) {
        super(nullable, dataPrototype.metadata().getKeyFields()[0].getFieldType());
        this.dataPrototype = dataPrototype;
    }

    public List elements() {
        try {
            PropertyList propertyList = dataPrototype.metadata().createPropertyList();
            DTOFieldMetaData[] keyFields = dataPrototype.metadata().getKeyFields();
            if (keyFields.length > 1) {
                throw new UnsupportedOperationException();
            }
            for (int i = 0; i < keyFields.length; i++) {
                propertyList.addProperty(keyFields[i].getFieldName());
            }
            propertyList.addProperty(dataPrototype.metadata().getTitleField().getFieldName());
            Collection collection = getAllDataCollection(propertyList);
            ArrayList list = new ArrayList(collection == null ? 0 : collection.size());
            if (collection != null) {
                for (Iterator i = collection.iterator(); i.hasNext();) {
                    DataTransfertObject data = (DataTransfertObject) i.next();
                    list.add(
                            new Element(
                                    data.getDataKey().keyPartAt(0),
                                    data.getProperty(dataPrototype.metadata().getTitleField().getFieldName()).toString()
                            )
                    );
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Collection getAllDataCollection(PropertyList list) throws Exception;

    protected abstract DataTransfertObject getData(PropertyList list, Object key) throws Exception;

    public String getKeyRenderer(Object key) {
        try {
            PropertyList propertyList = dataPrototype.metadata().createPropertyList();
            propertyList.addProperty(dataPrototype.metadata().getTitleField().getFieldName());
            DataTransfertObject data = getData(propertyList, key);
            return data == null ? "" : data.getProperty(dataPrototype.metadata().getTitleField().getFieldName()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DataTransfertObject getDataPrototype() {
        return dataPrototype;
    }

    public void setDataPrototype(DataTransfertObject dataPrototype) {
        this.dataPrototype = dataPrototype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractDTOChoiceType that = (AbstractDTOChoiceType) o;

        if (dataPrototype != null ? !dataPrototype.equals(that.dataPrototype) : that.dataPrototype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dataPrototype != null ? dataPrototype.hashCode() : 0);
        return result;
    }
}

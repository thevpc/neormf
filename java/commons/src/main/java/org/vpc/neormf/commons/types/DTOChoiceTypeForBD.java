package org.vpc.neormf.commons.types;

import org.vpc.neormf.commons.beans.DataTransfertObject;
import org.vpc.neormf.commons.beans.PropertyList;
import org.vpc.neormf.commons.beans.OrderList;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 août 2005
 * Time: 16:11:36
 * To change this template use File | Settings | File Templates.
 */
public class DTOChoiceTypeForBD extends AbstractDTOChoiceType implements Cloneable {
    public DTOChoiceTypeForBD(boolean nullable, DataTransfertObject dataPrototype) {
        super(nullable, dataPrototype);
    }

    protected Collection getAllDataCollection(PropertyList list) throws Exception {
        try {
            DataTransfertObject dataPrototype = this.getDataPrototype();
            Object client = Class.forName(dataPrototype.metadata().getProperties().getProperty("BusinessDelegateClassName")).newInstance();
            String prefix="find";
            OrderList ol=(OrderList)dataPrototype.metadata().getOrderListClass().newInstance();
            return (Collection) client.getClass().getMethod(prefix + dataPrototype.metadata().getBeanName(), new Class[]{
                    dataPrototype.metadata().getPropertyListClass(),
                    dataPrototype.metadata().getDTOClass(),
                    dataPrototype.metadata().getOrderListClass()
            }).invoke(client, new Object[]{list, dataPrototype, ol.add(dataPrototype.metadata().getTitleField().getFieldName())});
        } catch (Throwable e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    protected DataTransfertObject getData(PropertyList list, Object key) throws Exception {
        DataTransfertObject dataPrototype = this.getDataPrototype();
        Object client = Class.forName(dataPrototype.metadata().getProperties().getProperty("BusinessDelegateClassName")).newInstance();
        Object[] keyArray = (key instanceof Object[]) ? ((Object[]) key) : new Object[]{key};
        Constructor[] constructors = dataPrototype.metadata().getDataKeyClass().getConstructors();
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            if (constructors[i].getParameterTypes().length == keyArray.length) {
                constructor = constructors[i];
                break;
            }
        }
        return (DataTransfertObject) client.getClass().getMethod("findByKey" + dataPrototype.metadata().getBeanName(), new Class[]{
                dataPrototype.metadata().getPropertyListClass(),
                dataPrototype.metadata().getDataKeyClass()
        }).invoke(client, new Object[]{list, constructor.newInstance(keyArray)});
    }
}

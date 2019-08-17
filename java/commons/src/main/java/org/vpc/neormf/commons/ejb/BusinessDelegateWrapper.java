/**
 * ====================================================================
 *             VPC Pluggable Resources Set Library
 *
 * VPC-PRS si an open sources library that simplifiates Plugging other
 * components such as MessageSets, IconsSets or more Generally Plugins
 *
 * Copyright (C) 2006-2007 Taha BEN SALAH
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package org.vpc.neormf.commons.ejb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import org.vpc.neormf.commons.beans.Criteria;
import org.vpc.neormf.commons.beans.DataKey;
import org.vpc.neormf.commons.beans.DataTransfertObject;
import org.vpc.neormf.commons.beans.OrderList;
import org.vpc.neormf.commons.beans.PropertyList;

/**
 *
 * @author vpc
 */
public class BusinessDelegateWrapper {

    private DataTransfertObject dto;
    private Object bd;
    private Method mupdate;
    private Method mcreate;
    private Method mdelete;
    private Method mfindbykey;
    private Method mfindbyproto;
    private Method mfind;
    private String addMethodPrefix="add";
    private String updateMethodPrefix="update";
    private String findMethodPrefix="find";
    private String findByKeyMethodPrefix="findByKey";
    private String deleteMethodPrefix="delete";

    public BusinessDelegateWrapper(DataTransfertObject dto) {
        this.dto = dto;
    }

    public void create(DataTransfertObject data) {
        Object db = getBusinessDelegate();
        try {
            if (data != null && !dto.metadata().getDTOClass().isInstance(data)) {
                DataTransfertObject d2 = (DataTransfertObject) dto.metadata().getDTOClass().newInstance();
                d2.addAllDeclaredProperties(data);
                data = d2;
            }
            if (mcreate == null) {
                mcreate = db.getClass().getMethod(addMethodPrefix + dto.metadata().getBeanName(), new Class[]{dto.metadata().getDTOClass()});
            }
            mcreate.invoke(db, data);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public void update(DataTransfertObject data) {
        Object db = getBusinessDelegate();
        try {
            if (data != null && !dto.metadata().getDTOClass().isInstance(data)) {
                DataTransfertObject d2 = (DataTransfertObject) dto.metadata().getDTOClass().newInstance();
                d2.addAllDeclaredProperties(data);
                data = d2;
            }
            if (mupdate == null) {
                mupdate = db.getClass().getMethod(updateMethodPrefix + dto.metadata().getBeanName(), new Class[]{dto.metadata().getDTOClass()});
            }
            mupdate.invoke(db, data);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
                throw (RuntimeException) ex.getCause();
            } else {
                throw new RuntimeException(ex.getCause());
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public DataTransfertObject findByKey(PropertyList list, DataKey key) {
        Object db = getBusinessDelegate();
        try {
            if (key != null && !dto.metadata().getDataKeyClass().isInstance(key)) {
                DataTransfertObject d2 = (DataTransfertObject) dto.metadata().getDTOClass().newInstance();
                d2.addAllDeclaredProperties(key.toDTO());
                key = d2.getDataKey();
            }
            if (list != null && !dto.metadata().getPropertyListClass().isInstance(list)) {
                PropertyList d2 = (PropertyList) dto.metadata().getPropertyListClass().newInstance();
                d2.addAllProperties(list.toArray());
                list = d2;
            }
            if (mfindbykey == null) {
                mfindbykey = db.getClass().getMethod(findByKeyMethodPrefix + dto.metadata().getBeanName(), new Class[]{
                    dto.metadata().getPropertyListClass(),
                    dto.metadata().getDataKeyClass()
                });
            }
            return (DataTransfertObject) mfindbykey.invoke(db, list, key);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
                throw (RuntimeException) ex.getCause();
            } else {
                throw new RuntimeException(ex.getCause());
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public void delete(DataKey key) {
        Object db = getBusinessDelegate();
        try {
            if (!dto.metadata().getDataKeyClass().isInstance(key)) {
                DataTransfertObject d2 = (DataTransfertObject) dto.metadata().getDTOClass().newInstance();
                d2.addAllDeclaredProperties(key.toDTO());
                key = d2.getDataKey();
            }
            if (mdelete == null) {
                mdelete = db.getClass().getMethod(deleteMethodPrefix + dto.metadata().getBeanName(), new Class[]{dto.metadata().getDataKeyClass()});
            }
            mdelete.invoke(db, key);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
                throw (RuntimeException) ex.getCause();
            } else {
                throw new RuntimeException(ex.getCause());
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public Collection find(PropertyList list, Criteria criteria, OrderList orderList) {
        Object db = getBusinessDelegate();
        try {
            if (list != null && !dto.metadata().getPropertyListClass().isInstance(list)) {
                PropertyList d2 = (PropertyList) dto.metadata().getPropertyListClass().newInstance();
                d2.addAllProperties(list.toArray());
                list = d2;
            }
            if (orderList != null && !dto.metadata().getOrderListClass().isInstance(orderList)) {
                OrderList d2 = (OrderList) dto.metadata().getOrderListClass().newInstance();
                for (Iterator i = orderList.iterator(); i.hasNext();) {
                    OrderList.OrderItem oi = (OrderList.OrderItem) i.next();
                    d2.add(oi);
                }
                orderList = d2;
            }
            if (mfind == null) {
                mfind = db.getClass().getMethod(findMethodPrefix + dto.metadata().getBeanName(), new Class[]{
                    dto.metadata().getPropertyListClass(),
                    Criteria.class,
                    dto.metadata().getOrderListClass()
                });
            }
            return (Collection) mfind.invoke(db, list, criteria, orderList);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
                throw (RuntimeException) ex.getCause();
            } else {
                throw new RuntimeException(ex.getCause());
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public Collection find(PropertyList list, DataTransfertObject prototype, OrderList orderList) {
        Object db = getBusinessDelegate();
        try {
            if (list != null && !dto.metadata().getPropertyListClass().isInstance(list)) {
                PropertyList d2 = (PropertyList) dto.metadata().getPropertyListClass().newInstance();
                d2.addAllProperties(list.toArray());
                list = d2;
            }
            if (prototype != null && !dto.metadata().getDTOClass().isInstance(prototype)) {
                DataTransfertObject d2 = (DataTransfertObject) dto.metadata().getDTOClass().newInstance();
                d2.addAllDeclaredProperties(prototype);
                prototype = d2;
            }
            if (orderList != null && !dto.metadata().getOrderListClass().isInstance(orderList)) {
                OrderList d2 = (OrderList) dto.metadata().getOrderListClass().newInstance();
                for (Iterator i = orderList.iterator(); i.hasNext();) {
                    OrderList.OrderItem oi = (OrderList.OrderItem) i.next();
                    d2.add(oi);
                }
                orderList = d2;
            }
            if (mfindbyproto == null) {
                mfindbyproto = db.getClass().getMethod(findMethodPrefix + dto.metadata().getBeanName(), new Class[]{
                    dto.metadata().getPropertyListClass(),
                    dto.metadata().getDTOClass(),
                    dto.metadata().getOrderListClass()
                });
            }
            return (Collection) mfindbyproto.invoke(db, list, prototype, orderList);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
                throw (RuntimeException) ex.getCause();
            } else {
                throw new RuntimeException(ex.getCause());
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private Object getBusinessDelegate() {
        if (bd == null) {
            try {
                String s = dto.metadata().getProperties().getProperty("BusinessDelegateClassName");
                bd = Class.forName(s).newInstance();
            } catch (Exception ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        return bd;
    }

    public String getAddMethodPrefix() {
        return addMethodPrefix;
    }

    public void setAddMethodPrefix(String addMethodPrefix) {
        this.addMethodPrefix = addMethodPrefix;
    }

    public String getDeleteMethodPrefix() {
        return deleteMethodPrefix;
    }

    public void setDeleteMethodPrefix(String deleteMethodPrefix) {
        this.deleteMethodPrefix = deleteMethodPrefix;
    }

    public String getFindByKeyMethodPrefix() {
        return findByKeyMethodPrefix;
    }

    public void setFindByKeyMethodPrefix(String findByKeyMethodPrefix) {
        this.findByKeyMethodPrefix = findByKeyMethodPrefix;
    }

    public String getFindMethodPrefix() {
        return findMethodPrefix;
    }

    public void setFindMethodPrefix(String findMethodPrefix) {
        this.findMethodPrefix = findMethodPrefix;
    }

    public String getUpdateMethodPrefix() {
        return updateMethodPrefix;
    }

    public void setUpdateMethodPrefix(String updateMethodPrefix) {
        this.updateMethodPrefix = updateMethodPrefix;
    }
    
}

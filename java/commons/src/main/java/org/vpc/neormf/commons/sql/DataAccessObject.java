package org.vpc.neormf.commons.sql;

import org.vpc.neormf.commons.beans.*;
import org.vpc.neormf.commons.exceptions.FieldException;
import org.vpc.neormf.commons.exceptions.DataNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 29 avr. 2004 Time: 15:04:12
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class DataAccessObject {
    private Connection connection;
    private String callerPrincipalName="?";

    public DataAccessObject() {
    }

    public DataAccessObject(DataAccessObject other) {
        if(other!=null){
            setConnection(other.getConnection());
            setCallerPrincipalName(other.getCallerPrincipalName());
        }
    }

    public DataAccessObject(Connection cnx,String principal) {
        setConnection(cnx);
        setCallerPrincipalName(principal);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public String getCallerPrincipalName() {
        return callerPrincipalName;
    }

    public void setCallerPrincipalName(String callerPrincipalName) {
        this.callerPrincipalName = callerPrincipalName;
    }

    public boolean defaultExists(DataKey data) throws SQLException{
        try {
            PropertyList d = data.metadata().createPropertyList();
            d.addProperty(data.metadata().getKeyFields()[0].getFieldName());
            defaultFindByKey(d,data);
        } catch (DataNotFoundException e) {
            return false;
        }
        return true;
    }

    public DataKey defaultInsert(DataTransfertObject data) throws SQLException, FieldException{
        throw new AbstractMethodError("Not Supported");
    }

    public void defaultUpdate(DataTransfertObject data) throws SQLException{
        throw new AbstractMethodError("Not Supported");
    }

    public void defaultDelete(DataKey data) throws SQLException{
        throw new AbstractMethodError("Not Supported");
    }

    public DataTransfertObject defaultFindByKey(PropertyList propeties,DataKey dataKey) throws SQLException, FieldException{
        throw new AbstractMethodError("Not Supported");
    }

    public List defaultFind(PropertyList propeties, Criteria criteria, OrderList list) throws SQLException, FieldException{
        throw new AbstractMethodError("Not Supported");
    }
}

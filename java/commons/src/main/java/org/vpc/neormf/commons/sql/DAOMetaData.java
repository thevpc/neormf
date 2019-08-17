package org.vpc.neormf.commons.sql;

import org.vpc.neormf.commons.beans.DTOMetaData;

import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 18 févr. 2009
 * Time: 17:42:02
 * To change this template use File | Settings | File Templates.
 */
public class DAOMetaData {
    private DTOMetaData dataInfo;
    private LinkedHashMap fields =new LinkedHashMap();
    private LinkedHashMap criterias =new LinkedHashMap();

    public DAOMetaData(DTOMetaData dataInfo,DAOFieldMetaData[] fields) {
        this.dataInfo=dataInfo;
        for (int i = 0; i < fields.length; i++) {
            DAOFieldMetaData field = fields[i];
            this.fields.put(field.getName(),field);
        }
    }

    public DTOMetaData getDTO() {
        return dataInfo;
    }

    public DAOFieldMetaData getField(String name){
        return (DAOFieldMetaData) fields.get(name);
    }

}

package org.vpc.neormf.commons.exceptions;

import org.vpc.neormf.commons.beans.DTOFieldMetaData;
import org.vpc.neormf.commons.util.Dumpable;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 24 mai 2004 Time: 20:16:25
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class FieldException extends AbstractNeormfRuntimeException implements Dumpable{
    private String fieldName;
    private String fieldTitle;
    private String beanName;

    public FieldException(DTOFieldMetaData dataField,String message) {
        super(message,null);
        this.fieldName=dataField.getFieldName();
        this.fieldTitle=dataField.getFieldTitle();
        this.beanName=dataField.getDTOMetaData()==null ? null : dataField.getDTOMetaData().getBeanName();
        setParameters(new Object[]{
                getVerboseFieldName(dataField),
                fieldName,
                fieldTitle,
                beanName
        });
    }

    public FieldException(String fieldName,String message) {
        super(message,null);
        this.fieldName=fieldName;
        setParameters(new Object[]{
                fieldName,
                fieldName,
                fieldTitle,
                beanName
        });
    }

    public static String getVerboseFieldName(DTOFieldMetaData dataField) {
        if(dataField.getFieldTitle()==null){
            return  "'"+dataField.getFieldName()+"'";
        }
//        return "'"+dataField.getFieldTitle()+"' ('"+dataField.getFieldName()+"')";
        return "'"+dataField.getFieldTitle()+"'";
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldTitle() {
        return fieldTitle;
    }

    public String getBeanName() {
        return beanName;
    }


    public String dump() {
        return getClass()+":"+getMessage()+": {" +
                "fieldName='" + fieldName + "'" +
                ", fieldTitle='" + fieldTitle + "'" +
                ", beanName='" + beanName + "'" +
                "}";
    }
}

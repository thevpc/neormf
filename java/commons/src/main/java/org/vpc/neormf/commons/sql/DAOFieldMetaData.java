package org.vpc.neormf.commons.sql;

import org.vpc.neormf.commons.beans.DTOFieldMetaData;

public class DAOFieldMetaData {


    private DTOFieldMetaData fieldInfo;
    private DAOFieldKind kind;
    private DAOFieldImpl loadImplementation;
    private DAOFieldImpl storeImplementation;

    public DAOFieldMetaData(DTOFieldMetaData fieldInfo, DAOFieldKind kind,DAOFieldImpl implementation) {
        this(fieldInfo,kind,implementation,implementation);
    }
    
    public DAOFieldMetaData(DTOFieldMetaData fieldInfo, DAOFieldKind kind,DAOFieldImpl loadImplementation, DAOFieldImpl storeImplementation) {
        this.fieldInfo = fieldInfo;
        this.kind = kind;
        this.loadImplementation = loadImplementation;
        this.storeImplementation = storeImplementation;
    }

    public DAOFieldKind getKind() {
        return kind;
    }

    public String getName(){
        return fieldInfo.getFieldName();
    }

    public DTOFieldMetaData getDTOField() {
        return fieldInfo;
    }

    public DAOFieldImpl getLoadImplementation() {
        return loadImplementation;
    }

    public DAOFieldImpl getStoreImplementation() {
        return storeImplementation;
    }
}

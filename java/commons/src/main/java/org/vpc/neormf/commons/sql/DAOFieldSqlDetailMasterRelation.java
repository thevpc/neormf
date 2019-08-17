package org.vpc.neormf.commons.sql;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 18 févr. 2009
 * Time: 17:56:06
 * To change this template use File | Settings | File Templates.
 */
public class DAOFieldSqlDetailMasterRelation extends DAOFieldImpl {
    private String masterEntity;
    private String[] masterFields;
    private String[] localFields;

    public DAOFieldSqlDetailMasterRelation(String masterEntity, String[] masterFields, String[] localFields) {
        super(DAOFieldImplType.sqlDetailMaster);
        this.masterEntity = masterEntity;
        this.masterFields = masterFields;
        this.localFields = localFields;
    }

    public String[] getLocalFields() {
        return localFields;
    }

    public String getMasterEntity() {
        return masterEntity;
    }

    public String[] getMasterFields() {
        return masterFields;
    }
}
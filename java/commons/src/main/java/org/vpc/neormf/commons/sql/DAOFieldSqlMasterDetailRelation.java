package org.vpc.neormf.commons.sql;

/**
 * Created by IntelliJ IDEA.
* User: vpc
* Date: 18 févr. 2009
* Time: 17:56:06
* To change this template use File | Settings | File Templates.
*/
public class DAOFieldSqlMasterDetailRelation extends DAOFieldImpl {
    private String detailEntity;
    private String[] detailFields;
    private String[] localFields;

    public DAOFieldSqlMasterDetailRelation(String detailEntity, String[] detailFields, String[] localFields) {
        super(DAOFieldImplType.sqlMasterDetail);
        this.detailEntity = detailEntity;
        this.detailFields = detailFields;
        this.localFields = localFields;
    }

    public String[] getLocalFields() {
        return localFields;
    }

    public String getDetailEntity() {
        return detailEntity;
    }

    public String[] getDetailFields() {
        return detailFields;
    }
}
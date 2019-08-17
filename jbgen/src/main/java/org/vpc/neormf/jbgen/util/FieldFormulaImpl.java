package org.vpc.neormf.jbgen.util;

import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.commons.sql.CodeLanguage;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 30 juin 2004 Time: 17:30:58
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class FieldFormulaImpl {
    private ConfigNode body;
    private FieldFormulaType type;
    private DBColumn column;

    public FieldFormulaImpl(FieldFormulaType type,ConfigNode body,DBColumn column) throws IllegalArgumentException {
        this.type=type;
        this.body=body;
        this.column=column;
    }
    
    public FieldFormulaImpl(ConfigNode expression,DBColumn column) throws IllegalArgumentException {
        body=expression;
        this.column=column;
        if (expression == null) {
            type = FieldFormulaType.none;
        } else {
            type = configToFieldFormulaType(expression);
        }
    }

    public static FieldFormulaType configToFieldFormulaType(ConfigNode node) {
        String value=node==null?null:node.getTag();
        if (value == null) {
            return FieldFormulaType.none;
        }
        return FieldFormulaType.valueOf(value);
    }

    public ConfigNode getBody() {
        return body;
    }

    public FieldFormulaType getType() {
        return type;
    }

    public String getTypeName() {
        return type.toString();
    }

    public String toUserCode(CodeLanguage language){
        switch (language){
            case JAVA:{
                switch (type){
                    case none:{
                        return "DAOFieldNoneImpl.NONE_IMPL";
                    }
                    case sqlQuery:{
                        return "new DAOFieldSqlQueryImpl(\""+ JavaUtils.toStringLiteral(JBGenUtils.getSearchSQL(column,true).getQuery())+"\")";
                    }
                    case sqlMasterDetail:{
                        final RelationDesc relationDesc = new RelationDesc(this.getBody(), column, column.getDAOInfo());
                        return "new DAOFieldSqlMasterDetailRelation("+
                                //JavaUtils.toStringLiteral(relationDesc.getDetailTable().getTableName())
                                JavaUtils.toStringLiteral(column.getDAOInfo().getProjectInfo().getDAOInfoByTableName(relationDesc.getDetailTable().getTableName(), true).getBeanName())
                                +",new String[]{"
                                +new ListUserCode<String>(relationDesc.getDetailPrimaryFields()){
                                    public String stringify(String o) {
                                        DAOInfo dao = column.getDAOInfo().getProjectInfo().getDAOInfoByTableName(relationDesc.getDetailTable().getTableName(), true);
                                        return dao.getColumnByFieldName(o,true).getFullBeanFieldConstant2();
                                    }
                                }
                                +"},new String[]{"
                                +new ListUserCode<String>(relationDesc.getForeignFields()){
                                    public String stringify(String o) {
                                        DAOInfo dao = column.getDAOInfo();
                                        return dao.getColumnByFieldName(o,true).getFullBeanFieldConstant();
                                    }
                                }
                                +"})";
                    }
                    case sqlDetailMaster:{
                        final RelationDesc relationDesc = new RelationDesc(this.getBody(), column, column.getDAOInfo());
                        return "new DAOFieldSqlDetailMasterRelation("+
                                //JavaUtils.toStringLiteral(relationDesc.getDetailTable().getTableName())
                                JavaUtils.toStringLiteral(column.getDAOInfo().getProjectInfo().getDAOInfoByTableName(relationDesc.getDetailTable().getTableName(), true).getBeanName())
                                +",new String[]{"
                                +new ListUserCode<String>(relationDesc.getDetailPrimaryFields()){
                                    public String stringify(String o) {
                                        DAOInfo dao = column.getDAOInfo().getProjectInfo().getDAOInfoByTableName(relationDesc.getDetailTable().getTableName(), true);
                                        return dao.getColumnByFieldName(o,true).getFullBeanFieldConstant2();
                                    }
                                }
                                +"},new String[]{"
                                +new ListUserCode<String>(relationDesc.getForeignFields()){
                                    public String stringify(String o) {
                                        DAOInfo dao = column.getDAOInfo();
                                        return dao.getColumnByFieldName(o,true).getFullBeanFieldConstant();
                                    }
                                }
                                +"})";
                    }
                }
            }
        }
        throw new IllegalArgumentException("unsupported "+language+" / "+type);
    }
}

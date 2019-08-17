package org.vpc.neormf.jbgen.util;

import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.config.ConfigNode;

import java.util.Arrays;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 27 mai 2004 Time: 18:51:06
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class RelationDesc {
    String[] foreignFields = null;
    String[] detailPrimaryFields = null;
    DBTableInfo detailTable = null;
    String description;

    public RelationDesc(ConfigNode description, DBColumn column, DAOInfo entityInfo) {
        /**
         * <master-table></master-table>
         * <master-primary-key-list></master-foreign-key-list>
         * <slave-foreign-key-list></slave-foreign-key-list>
         */


        try {
            foreignFields = JBGenUtils.getStringArray(description.getAttribute("foreignFields",null,true,null,true,null), ",", true);
            detailTable = entityInfo.getProjectInfo().getDBTableByName(description.getAttribute("detailTable",null,true,null,true,null), true);
            detailPrimaryFields = JBGenUtils.getStringArray(description.getAttribute("detailPrimaryFields",null,true,null,true,null), ",", true);
            if (foreignFields.length != detailPrimaryFields.length) {
                throw new IllegalArgumentException("Bad Relation mapping " + Arrays.asList(foreignFields) + " -> " + Arrays.asList(detailPrimaryFields));
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad pattern for " + entityInfo.getClassName() + "." + column.getBeanFieldName() + " : Expected 'FKfield1,FKfield2->MasterFieldName:PKfield1,PKfield2' : " + e.getMessage());
        }

    }

    public String[] getForeignFields() {
        return foreignFields;
    }

    public String[] getDetailPrimaryFields() {
        return detailPrimaryFields;
    }

    public DBTableInfo getDetailTable() {
        return detailTable;
    }
}

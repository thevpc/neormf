/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpc.neormf.jbgen.dbsupport;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.vpc.neormf.commons.beans.RelationInfo.Cardinality;
import org.vpc.neormf.commons.beans.RelationInfo.Role;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;

/**
 *
 * @author vpc
 */
public class DBRelation {
    private JBGenProjectInfo project;
    private DBTableInfo fkTable;
    private DBTableInfo pkTable;
    private ArrayList<DBColumn> fkColumns=new ArrayList<DBColumn>();
    private ArrayList<DBColumn> pkColumns=new ArrayList<DBColumn>();
    private int updateRule;
    private int deleteRule;
    private int differability;
    private String pkName;
    private String fkName;
    private String name;
    public DBRelation(JBGenProjectInfo project,DBTableInfo fkTable,DBTableInfo pkTable,int updateRule,int deleteRule,int differability,String fkName,String pkName) throws SQLException, IOException {
        this.project=project;
        this.fkTable=fkTable;
        this.pkTable=pkTable;
        this.updateRule=updateRule;
        this.deleteRule=deleteRule;
        this.differability=differability;
        this.fkName=fkName;
        this.pkName=pkName;
    }

    public void addFkColumn(DBColumn c){
        fkColumns.add(c);
    }
    public void addPkColumn(DBColumn c){
        pkColumns.add(c);
    }

    public String getFkName() {
        return fkName;
    }

    public DBTableInfo getFkTable() {
        return fkTable;
    }

    public String getPkName() {
        return pkName;
    }

    public DBTableInfo getPkTable() {
        return pkTable;
    }

    public List<DBColumn> getFkColumns() {
        return Collections.unmodifiableList(fkColumns);
    }

    public List<DBColumn> getPkColumns() {
        return Collections.unmodifiableList(pkColumns);
    }
    
    public int size(){
        return fkColumns.size();
    }

    public String getName() {
        if(name==null){
            name=fkTable.getDAOInfo().getBeanName()+"_"+pkTable.getDAOInfo().getBeanName();
            for (DBColumn dBColumn : fkColumns) {
                name+=("_"+dBColumn.getBeanFieldVar());
            }
            for (DBColumn dBColumn : pkColumns) {
                name+=("_"+dBColumn.getBeanFieldVar());
            }
        }
        return name;
    }
    
    
    
}

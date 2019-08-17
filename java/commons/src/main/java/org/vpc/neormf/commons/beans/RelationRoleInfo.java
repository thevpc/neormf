/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vpc.neormf.commons.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.vpc.neormf.commons.beans.RelationInfo.Cardinality;
import org.vpc.neormf.commons.beans.RelationInfo.Role;

/**
 *
 * @author vpc
 */
public class RelationRoleInfo {
    private RelationInfo relation;
    private String roleName;
    private RelationInfo.Cardinality cardinality;
    private RelationInfo.Role role;
    private String dtoName;
    private String[] fieldNames;
    private String referencedDTOName;
    private List<RelationRoleIndex> items=new ArrayList<RelationRoleIndex>();

    public RelationRoleInfo(RelationInfo relation, String roleName,RelationInfo.Role role,Cardinality cardinality, String dtoName, String[] fieldNames, String referencedDTOName) {
        this.relation = relation;
        this.roleName = roleName;
        this.cardinality = cardinality;
        this.dtoName = dtoName;
        this.fieldNames = fieldNames;
        this.referencedDTOName = referencedDTOName;
        this.role = role;
    }

    void add(RelationRoleIndex index){
        items.add(index);
    }

    public List<RelationRoleIndex> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public Cardinality getCardinality() {
        return cardinality;
    }

    public String getDtoName() {
        return dtoName;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public String getReferencedDTOName() {
        return referencedDTOName;
    }

    public RelationInfo getRelation() {
        return relation;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role getRole() {
        return role;
    }


}

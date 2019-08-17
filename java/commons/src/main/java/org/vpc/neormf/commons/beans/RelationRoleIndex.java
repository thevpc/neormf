/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vpc.neormf.commons.beans;

import java.io.Serializable;

/**
 *
 * @author vpc
 */
public class RelationRoleIndex implements Serializable{
    public static final RelationRoleIndex[] NO_ROLES={};
    private int index;
    private RelationRoleInfo relationRole;
    private DTOFieldMetaData field;

    public RelationRoleIndex(RelationRoleInfo relationRole,int index) {
        this.index = index;
        this.relationRole = relationRole;
        relationRole.add(this);
    }

    public int getIndex() {
        return index;
    }

    public RelationRoleInfo getRelationRole() {
        return relationRole;
    }

    public DTOFieldMetaData getField() {
        return field;
    }

    public void setField(DTOFieldMetaData field) {
        this.field = field;
    }
    
    
    
}

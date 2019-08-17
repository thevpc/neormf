/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vpc.neormf.commons.beans;

/**
 *
 * @author vpc
 */
public class RelationInfo {
    public static enum Type{
        COMPOSITION,AGGREGATION
    }
    public static enum Cardinality{
        ONE_TO_MANY,MANY_TO_ONE,ONE_TO_ONE,MANY_TO_MANY
    }
    public static enum Role{
        Primary,Foreign
    }
    private String name;
    private String[] foreignFieldNames;
    private String[] primaryFieldNames;
    private String foreignRoleName;
    private String primaryRoleName;
    private Type type;
    private Cardinality cardinality;
    private String foreignDTOName;
    private String primaryDTOName;
    private RelationRoleInfo foreignRole;
    private RelationRoleInfo primaryRole;

    public RelationInfo(String name, String[] foreignFieldNames, String[] primaryFieldNames, String foreignRoleName, String primaryRoleName, Type type, Cardinality cardinality, String foreignDTOName, String primaryDTOName) {
        this.name = name;
        this.foreignFieldNames = foreignFieldNames;
        this.primaryFieldNames = primaryFieldNames;
        this.foreignRoleName = foreignRoleName;
        this.primaryRoleName = primaryRoleName;
        this.type = type;
        this.cardinality = cardinality;
        this.foreignDTOName = foreignDTOName;
        this.primaryDTOName = primaryDTOName;
        this.foreignRole = new RelationRoleInfo(this, foreignRoleName,Role.Foreign, cardinality, foreignDTOName, foreignFieldNames, primaryDTOName);
        Cardinality revCard=RelationInfo.Cardinality.MANY_TO_MANY;
        switch(cardinality){
            case MANY_TO_MANY:{
                revCard=RelationInfo.Cardinality.MANY_TO_MANY;
                break;
            }
            case MANY_TO_ONE:{
                revCard=RelationInfo.Cardinality.ONE_TO_MANY;
                break;
            }
            case ONE_TO_MANY:{
                revCard=RelationInfo.Cardinality.MANY_TO_ONE;
                break;
            }
            case ONE_TO_ONE:{
                revCard=RelationInfo.Cardinality.ONE_TO_ONE;
                break;
            }
        }
        this.primaryRole = new RelationRoleInfo(this, primaryRoleName,Role.Primary, revCard, primaryDTOName, primaryFieldNames, foreignDTOName);
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public String getForeignDTOName() {
        return foreignDTOName;
    }

    public String[] getForeignFieldNames() {
        return foreignFieldNames;
    }

    public RelationRoleInfo getForeignRole() {
        return foreignRole;
    }

    public String getForeignRoleName() {
        return foreignRoleName;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryDTOName() {
        return primaryDTOName;
    }

    public String[] getPrimaryFieldNames() {
        return primaryFieldNames;
    }

    public RelationRoleInfo getPrimaryRole() {
        return primaryRole;
    }

    public String getPrimaryRoleName() {
        return primaryRoleName;
    }

    public Type getType() {
        return type;
    }
    

    
    
    
}

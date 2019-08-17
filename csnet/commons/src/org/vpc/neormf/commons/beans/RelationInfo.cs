// RelationInfo.cs created with MonoDevelop
// User: vpc at 00:44 10/02/2009
//
// To change standard headers go to Edit->Preferences->Coding->Standard Headers
//

using System;

namespace org.vpc.neormf.commons.beans
{
	
	
	public class RelationInfo
	{
		public enum Type{
	        COMPOSITION,AGGREGATION
	    }
	    public  enum Cardinality{
	        ONE_TO_MANY,MANY_TO_ONE,ONE_TO_ONE,MANY_TO_MANY
	    }
	    public enum Role{
	        Primary,Foreign
	    }
		
		public RelationInfo()
		{
		}
    private String name;
    private String foreignFieldName;
    private String primaryFieldName;
    private String foreignRoleName;
    private String primaryRoleName;
    private Type type;
    private Cardinality cardinality;
    private String foreignDTOName;
    private String primaryDTOName;
    private RelationRoleInfo foreignRole;
    private RelationRoleInfo primaryRole;

    public RelationInfo(String name, String foreignFieldName, String primaryFieldName, String foreignRoleName, String primaryRoleName, Type type, Cardinality cardinality, String foreignDTOName, String primaryDTOName) {
        this.name = name;
        this.foreignFieldName = foreignFieldName;
        this.primaryFieldName = primaryFieldName;
        this.foreignRoleName = foreignRoleName;
        this.primaryRoleName = primaryRoleName;
        this.type = type;
        this.cardinality = cardinality;
        this.foreignDTOName = foreignDTOName;
        this.primaryDTOName = primaryDTOName;
        this.foreignRole = new RelationRoleInfo(this, foreignRoleName,Role.Foreign, cardinality, foreignDTOName, foreignFieldName, primaryDTOName);
        Cardinality revCard=RelationInfo.Cardinality.MANY_TO_MANY;
        switch(cardinality){
            case Cardinality.MANY_TO_MANY:{
                revCard=RelationInfo.Cardinality.MANY_TO_MANY;
                break;
            }
            case Cardinality.MANY_TO_ONE:{
                revCard=RelationInfo.Cardinality.ONE_TO_MANY;
                break;
            }
            case Cardinality.ONE_TO_MANY:{
                revCard=RelationInfo.Cardinality.MANY_TO_ONE;
                break;
            }
            case Cardinality.ONE_TO_ONE:{
                revCard=RelationInfo.Cardinality.ONE_TO_ONE;
                break;
            }
        }
        this.primaryRole = new RelationRoleInfo(this, primaryRoleName,Role.Primary, revCard, primaryDTOName, primaryFieldName, foreignDTOName);
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public String getForeignDTOName() {
        return foreignDTOName;
    }

    public String getForeignFieldName() {
        return foreignFieldName;
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

    public String getPrimaryFieldName() {
        return primaryFieldName;
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
	
	
}

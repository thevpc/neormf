// RelationRoleInfo.cs created with MonoDevelop
// User: vpc at 00:45 10/02/2009
//
// To change standard headers go to Edit->Preferences->Coding->Standard Headers
//

using System;
using System.Collections.Generic;
//using System.Collections.ObjectModel;
namespace org.vpc.neormf.commons.beans
{
	
	
	public class RelationRoleInfo
	{
		
		
		private RelationInfo relation;
    private String roleName;
    private RelationInfo.Cardinality cardinality;
    private RelationInfo.Role role;
    private String dtoName;
    private String fieldName;
    private String referencedDTOName;
    private IList<RelationRoleIndex> items=new ArrayList<RelationRoleIndex>();

    public RelationRoleInfo(RelationInfo relation, String roleName,RelationInfo.Role role,RelationInfo.Cardinality cardinality, String dtoName, String fieldName, String referencedDTOName) {
        this.relation = relation;
        this.roleName = roleName;
        this.cardinality = cardinality;
        this.dtoName = dtoName;
        this.fieldName = fieldName;
        this.referencedDTOName = referencedDTOName;
        this.role = role;
    }

    public void add(RelationRoleIndex index){
        items.add(index);
    }

    public IList<RelationRoleIndex> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public RelationInfo.Cardinality getCardinality() {
        return cardinality;
    }

    public String getDtoName() {
        return dtoName;
    }

    public String getFieldName() {
        return fieldName;
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

    public RelationInfo.Role getRole() {
        return role;
    }
	}
}

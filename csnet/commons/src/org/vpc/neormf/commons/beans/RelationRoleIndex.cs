// RelationRoleIndex.cs created with MonoDevelop
// User: vpc at 00:46 10/02/2009
//
// To change standard headers go to Edit->Preferences->Coding->Standard Headers
//

using System;

namespace org.vpc.neormf.commons.beans
{
	
	
	public class RelationRoleIndex
	{
		
		private int index;
    private RelationRoleInfo relationRole;
    private DataField field;

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

    public DataField getField() {
        return field;
    }

    public void setField(DataField field) {
        this.field = field;
    }
	}
}

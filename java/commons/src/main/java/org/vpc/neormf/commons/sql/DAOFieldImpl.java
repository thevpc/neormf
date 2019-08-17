package org.vpc.neormf.commons.sql;

/**
 * Created by IntelliJ IDEA.
* User: vpc
* Date: 18 févr. 2009
* Time: 17:55:46
* To change this template use File | Settings | File Templates.
*/
public abstract class DAOFieldImpl {
  private DAOFieldImplType type;

    DAOFieldImpl(DAOFieldImplType type) {
        this.type = type;
    }

    public DAOFieldImplType getType() {
        return type;
    }
}

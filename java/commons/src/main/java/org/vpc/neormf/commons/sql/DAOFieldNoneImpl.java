package org.vpc.neormf.commons.sql;

/**
 * Created by IntelliJ IDEA.
* User: vpc
* Date: 18 févr. 2009
* Time: 18:11:54
* To change this template use File | Settings | File Templates.
*/
public class DAOFieldNoneImpl extends DAOFieldImpl {
    public static DAOFieldImpl NONE_IMPL=new DAOFieldNoneImpl();
    private DAOFieldNoneImpl() {
        super(DAOFieldImplType.none);
    }
}

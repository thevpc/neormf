package org.vpc.neormf.jbgen.java.generators.dao;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.info.DAOInfo;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 9 févr. 2009
 * Time: 21:06:02
 * To change this template use File | Settings | File Templates.
 */
public class JavaJEEDAOGenerator extends JavaDAOGenerator{
    public JavaJEEDAOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }
    public boolean accept(Connection connection, DAOInfo entityInfo) {
        if(entityInfo.getProjectInfo().getTargetEjbVersion().compareTo("3.0") >= 0){
            return false;
        }
        return super.accept(connection, entityInfo);
    }
}

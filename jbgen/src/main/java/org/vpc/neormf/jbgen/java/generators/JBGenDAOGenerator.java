/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators;

import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 15:05:22
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class JBGenDAOGenerator extends AbsractModuleGenerator{

    protected JBGenDAOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo moduleCodeStyle) throws SQLException, IOException {
        DAOInfo[] entityInfo = moduleCodeStyle.getAllEntities();
        boolean ok = true;
        for (int g = 0; g < entityInfo.length; g++) {
            if (accept(connection, entityInfo[g])) {
                if (ok) {
                    getLog().info(" --------- " + this + " ...");
                    ok = false;
                }
                generate(connection, entityInfo[g]);
                performExtraChecks(entityInfo[g]);
            }
        }
    }

    public abstract void generate(Connection connection, DAOInfo entityInfo) throws SQLException, IOException;

    public abstract boolean accept(Connection connection, DAOInfo entityInfo);

    public abstract void performExtraChecks(DAOInfo entityInfo) throws NoSuchElementException;
}

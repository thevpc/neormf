/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators;

import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
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
public abstract class JBGenBOGenerator extends AbsractModuleGenerator{

    protected JBGenBOGenerator(JBGenMain jbgen) {
        super(jbgen);
    }

    public void generate(Connection connection, JBGenProjectInfo project) throws SQLException, IOException {
        BOInfo[] sessionInfos = project.getAllBOs();
        boolean ok = true;
        for (int g = 0; g < sessionInfos.length; g++) {
            if (accept(connection, sessionInfos[g])) {
                if (ok) {
                    getLog().info(" --------- " + this + " ...");
                    ok = false;
                }
                generate(connection, sessionInfos[g]);
                performExtraChecks(sessionInfos[g]);
            }
        }
    }

    public abstract void generate(Connection connection, BOInfo entityInfo) throws SQLException, IOException;

    public abstract boolean accept(Connection connection, BOInfo entityInfo);

    public abstract void performExtraChecks(BOInfo entityInfo) throws NoSuchElementException;
}

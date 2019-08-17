/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.generators;

import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.projects.TargetGenerator;
import org.vpc.neormf.jbgen.util.TLog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
public interface JBGenModuleGenerator {
    public void setProject(TargetGenerator target);
    public void generate(Connection connection, JBGenProjectInfo moduleCodeStyle) throws SQLException, IOException;

    TargetGenerator getProject();
    TLog getLog();
}

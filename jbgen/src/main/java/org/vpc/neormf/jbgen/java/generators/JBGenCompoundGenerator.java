package org.vpc.neormf.jbgen.java.generators;

import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.projects.TargetGenerator;
import org.vpc.neormf.jbgen.JBGenMain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 5 mai 2004 Time: 17:32:22
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JBGenCompoundGenerator extends AbsractModuleGenerator{

    private JBGenModuleGenerator[] generators;

    public JBGenCompoundGenerator(JBGenMain jbgen, JBGenModuleGenerator[] generators) {
        super(jbgen);
        this.generators = generators;
    }

    @Override
    public void setProject(TargetGenerator target) {
        for (int i = 0; i < generators.length; i++) {
            generators[i].setProject(target);
        }
    }

    public void generate(Connection connection, JBGenProjectInfo project) throws SQLException, IOException {
        for (int i = 0; i < generators.length; i++) {
            generators[i].generate(connection, project);
        }
    }
//    public void generate(Connection connection, EntityInfo entityInfo) throws SQLException, IOException {
//        for (int i = 0; i < generators.length; i++) {
//            if (generators[i].accept(connection, entityInfo)) {
//                generators[i].generate(connection, entityInfo);
//            }
//        }
//    }
//
//    public boolean accept(Connection connection, EntityInfo entityInfo) {
//        for (int i = 0; i < generators.length; i++) {
//            if (generators[i].accept(connection, entityInfo)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void performExtraChecks(EntityInfo entityInfo) throws NoSuchElementException {
//        for (int i = 0; i < generators.length; i++) {
//            generators[i].performExtraChecks(entityInfo);
//        }
//    }
//
//    public JBGenEntityGenerator[] getGenerators() {
//        return generators;
//    }
}

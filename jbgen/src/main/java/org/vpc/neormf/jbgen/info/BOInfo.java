package org.vpc.neormf.jbgen.info;

import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.projects.J2eeTarget;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 20 avr. 2004 Time: 20:07:58
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class BOInfo extends AbstractInfo {
    private ArrayList daoInfos = new ArrayList();
    private String sessionName;

    private File file;

    public BOInfo(String sessionName, JBGenProjectInfo projectInfo) throws IOException {
        super(projectInfo, "session." + sessionName);
        this.sessionName = sessionName;
        if (sessionName.endsWith("Session")) {
            addVar("BeanName", sessionName.substring(0, sessionName.length() - "Session".length()));
        } else {
            addVar("BeanName", sessionName);
        }
        addVar("SessionName", sessionName);

        String fileName = getBeanName() + ".bo.xml";
        File[] possibleFiles = JBGenUtils.searchFilesByDosFilterName(fileName, projectInfo.getFolder());
        if (possibleFiles.length > 1) {
            getLog().warning("ambigous name for " + fileName);
        } else if (possibleFiles.length == 0) {
            this.file = new File(projectInfo.getFolder(), fileName);
        } else {
            this.file = possibleFiles[0];
        }

//        this.file = new File(moduleCodeStyle.getFolder(), getBeanName() + ".session");

        getConfig().setFile(file);
        if (file.exists()) {
            if (isJBGenLogOptionEnabled("info-if-found-bo-file", true)) {
                getLog().info("Loading SessionInfo " + file.getPath());
            }
            getConfig().load(file);
        }else{
            if (isJBGenLogOptionEnabled("warn-if-missing-bo-file", false)) {
                getLog().warning("Not Particular Configuration For "+sessionName+" as File Not found : " + projectInfo.getFolder().getCanonicalPath()+"/**/"+file.getName());
            }
        }
    }

    public void add(DAOInfo entityInfo) {
        daoInfos.add(entityInfo);
    }

    public DAOInfo[] getDAOInfos() {
        return (DAOInfo[]) daoInfos.toArray(new DAOInfo[daoInfos.size()]);
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getBeanName() {
        try {
            return getCodeStyleObjectName("session", "{SessionName:class}");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getSessionBeanName() {
        try {
            return getCodeStyleObjectName("session-bean", "{SessionName:class}Bean");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getSessionRemoteNameVarName() {
        return "_"+getSessionRemoteName()+"_";
    }

    public String getSessionRemoteName() {
        try {
            return getCodeStyleObjectName("session-remote", "{SessionName:class}Remote");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getSessionHomeNameVarName() {
        return "_"+getSessionHomeName()+"_";
    }
    
    public String getSessionHomeName() {
        try {
            return getCodeStyleObjectName("session-home", "{SessionName:class}Home");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getEjbClientName() {
        try {
            return getCodeStyleObjectName("ejb-business-delegate", "{SessionName:class}BD");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getFullConnectorName() {
        return getEjbClientPackage() + "." + getEjbClientName();
    }

    public String getSessionLocalHomeName() {
        try {
            return getCodeStyleObjectName("session-local-home", "{SessionName:class}LocalHome");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getSessionLocalName() {
        return getCodeStyleObjectName("session-local", "{SessionName:class}Local");
    }

    public String getFullSessionHomeName() {
        return getSessionPackage() + "." + getSessionHomeName();
    }

    public String getFullSessionRemoteName() {
        return getSessionPackage() + "." + getSessionRemoteName();
    }

    public String getFullSessionBeanName() {
        return getSessionPackage() + "." + getSessionBeanName();
    }

    public String getSessionPackage() {
        return getCodeStyleObjectName("session-package", "{ModulePackage}.server.ejb.{SessionName:package}");
    }

    public String getEjbClientPackage() {
            return getCodeStyleObjectName("ejb-business-delegate-package", "{ModulePackage}.bd.{SessionName:package}");
    }

    public void checkGenerateFilter(String generationKeyName) {
//        String f = getConfig().getString("generate." + generationKeyName, null, true, "Table names filter to generate '" + generationKeyName + "', use +* for all, example +TAB* -TABSAMPPE", true, getVars());
//        try {
//            JBGenUtils.parseBoolean(f);
//        } catch (IllegalArgumentException e) {
//            StringListFilter filter = new StringListFilter(f);
//            HashSet possibleValues = new HashSet();
//            SessionInfo[] si = getModuleInfo().getAllSessions();
//            for (int i = 0; i < si.length; i++) {
//                possibleValues.add(si[i].getBeanName());
//            }
//            try {
//                filter.checkValuesIn(possibleValues);
//            } catch (NoSuchElementException e2) {
//                throw new NoSuchElementException("Bad Filter 'generate." + generationKeyName + "' : " + e2.getMessage());
//            }
//        }
    }

    public boolean doGenerateRemoteSession() {
        return doGenerateBean(J2eeTarget.MODULE_EJB+".session-remote");
    }

    public boolean doGenerateLocalSession() {
        return doGenerateBean(J2eeTarget.MODULE_EJB+".session-local");
    }

    public boolean doGenerateBusinessDelegate() {
        return doGenerateBean(J2eeTarget.MODULE_EJB_BUSINESS_DELEGATE);
    }

    public boolean doGenerateSession() {
        return doGenerateLocalSession() || doGenerateRemoteSession();
    }

    //TODO incorerct
    public String getLogCatagory() {
        return getLogOption("session-category");
//        return getString("log.category.session", "{SessionName}", true);
    }

    public String[] getEjbReferences() {
        return getProjectInfo().getEjbReferences(getBeanName());
    }
}

package org.vpc.neormf.jbgen.info;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 19:30:17
 * To change this template use File | Settings | File Templates.
 */
public class SessionDeployInfo {
    String description;
    String displayName;
    String ejbName;
    String home;
    String remote;
    String ejbClass;
    String sessionType;
    String transactionType;

    public SessionDeployInfo(String description, String displayName, String ejbName, String home, String remote, String ejbClass, String sessionType, String transactionType) {
        this.description = description;
        this.displayName = displayName;
        this.ejbName = ejbName;
        this.home = home;
        this.remote = remote;
        this.ejbClass = ejbClass;
        this.sessionType = sessionType;
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEjbName() {
        return ejbName;
    }

    public String getHome() {
        return home;
    }

    public String getRemote() {
        return remote;
    }

    public String getEjbClass() {
        return ejbClass;
    }

    public String getSessionType() {
        return sessionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEjbName(String ejbName) {
        this.ejbName = ejbName;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public void setEjbClass(String ejbClass) {
        this.ejbClass = ejbClass;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

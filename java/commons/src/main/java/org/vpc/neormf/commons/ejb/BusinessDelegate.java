package org.vpc.neormf.commons.ejb;

import java.util.Properties;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Azure Systems
 * @project Route Optimizer
 * @creation on Date: 21 avr. 2004 Time: 19:37:14
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class BusinessDelegate {
    private String serverAddress = "localhost";
    private int serverPort = -1;
    private String userLogin;
    private String userPassword;
    private Properties connectionProperties;
    private static BusinessDelegate prototype;

    public BusinessDelegate() {
        if(prototype!=null){
            serverAddress=prototype.serverAddress;
            serverPort=prototype.serverPort;
            userLogin=prototype.userLogin;
            userPassword=prototype.userPassword;
        }
    }

    public static final void setClientPrototype(BusinessDelegate businessDelegate){
        prototype= businessDelegate;
    }

    public static final BusinessDelegate getClientPrototype(){
        return prototype;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }


    public BusinessDelegate(String serverAddress, int serverPort) {
        this();
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public BusinessDelegate(String serverAddress) {
        this();
        this.serverAddress = serverAddress;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setConnectionProperties(Properties connectionProperties){
        this.connectionProperties=connectionProperties;
    }

    public Properties  getConnectionProperties(){
        return connectionProperties;
    }
}

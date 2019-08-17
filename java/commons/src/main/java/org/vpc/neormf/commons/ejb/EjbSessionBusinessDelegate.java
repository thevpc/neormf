package org.vpc.neormf.commons.ejb;


import org.vpc.neormf.commons.CommunicationException;
import org.vpc.neormf.commons.exceptions.CreateDataException;
import org.vpc.neormf.commons.exceptions.DataNotFoundException;
import org.vpc.neormf.commons.exceptions.RemoveDataException;

import javax.ejb.CreateException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Azure Systems
 * @project Neormf
 * @creation on Date: 5 mai 2004 Time: 18:23:38
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class EjbSessionBusinessDelegate extends BusinessDelegate {
    private SimpleEJBClient delegate = null;
    private String applicationServerName;
    private String moduleName;


    public EjbSessionBusinessDelegate() {
        init();
    }

    private void init() {
        BusinessDelegate prototype = getClientPrototype();
        if (prototype != null) {
            setServerAddress(prototype.getServerAddress());
            setUserLogin(prototype.getUserLogin());
            setUserPassword(prototype.getUserPassword());
            setServerPort(prototype.getServerPort());
            if (prototype instanceof EjbSessionBusinessDelegate) {
                EjbSessionBusinessDelegate ejbSessionBusinessDelegate = (EjbSessionBusinessDelegate) prototype;
                applicationServerName = ejbSessionBusinessDelegate.applicationServerName;
                moduleName = ejbSessionBusinessDelegate.moduleName;
            }
        }
    }

    public EjbSessionBusinessDelegate(String serverAddress, int serverPort) {
        super(serverAddress, serverPort);
        init();
    }

    public EjbSessionBusinessDelegate(String serverAddress) {
        super(serverAddress);
        init();
    }

    public String getApplicationServerName() {
        return applicationServerName;
    }

    public void setApplicationServerName(String applicationServerName) {
        this.applicationServerName = applicationServerName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public SimpleEJBClient getDelegate() throws NamingException {
        if (delegate == null) {
            delegate = new SimpleEJBClient();
            delegate.setServerMode(false);
            delegate.connect(getServerAddress(), getServerPort(), getUserLogin(), getUserPassword(), getModuleName(), getApplicationServerName(), getConnectionProperties());
        }
        return delegate;
    }

    public CommunicationException createCommunicationException(Throwable throwable) {
        while((throwable instanceof RemoteException) && ((RemoteException) throwable).detail != null){
            throwable=((RemoteException) throwable).detail;
        }
//        if (throwable instanceof RemoteException) {
////            Throwable cause=((RemoteException)throwable).getCause();
//            Throwable cause = ((RemoteException) throwable).detail;
//            if (cause instanceof ObjectNotFoundException) {
//                cause = new DataNotFoundException(cause.getMessage());
//            } else if (cause == null) {
//                cause = throwable;
//            }
//            return new CommunicationException(cause);
//        }
        if (throwable instanceof ObjectNotFoundException) {
            throwable = new DataNotFoundException(throwable);
        } else if (throwable instanceof CreateException) {
            throwable = new CreateDataException(throwable);
        } else if (throwable instanceof RemoveException) {
            throwable = new RemoveDataException(throwable);
        }
        return new CommunicationException(throwable);
    }
}

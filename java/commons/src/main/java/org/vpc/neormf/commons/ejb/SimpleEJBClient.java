package org.vpc.neormf.commons.ejb;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 8 août 2005
 * Time: 17:09:09
 * To change this template use File | Settings | File Templates.
 */

/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
import org.vpc.neormf.commons.util.Utils;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

//import com.sun.naming.internal.ResourceManager;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Azure Systems
 * @project Route Optimizer
 * @creation on Date: 2 avr. 2004 Time: 14:50:31
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class SimpleEJBClient {

    private String server;
    private String protocol;
    private int port;
    private boolean serverMode = true;
    private String url;
    private String user;
    private String password;
    private String contextFactory;
    private String appServerName;
    private Properties extraProperties;
    private InitialContext initialContext;
    public static final String WEBLOGIC8 = "weblogic8";
    public static final String O9IAS = "O9iAS";
    public static final String JBOSS4 = "jboss4";
    public static final String GLASSFISH2 = "glassfish2";
    public static final Set SUPPORTED_SERVERS = Collections.unmodifiableSet(new TreeSet(Arrays.asList(new Object[]{WEBLOGIC8, O9IAS, JBOSS4, GLASSFISH2})));

    public SimpleEJBClient() {
    }

    public void connect(String server, int port, String user, String password, String moduleName, String appServerName) throws NamingException {
        connect(server, port, user, password, moduleName, appServerName, null);
    }

    public void connect(String server, int port, String user, String password, String moduleName, String appServerName, Properties connectionProperties) throws NamingException {
        String url = null;
        String contextFactory = null;
        if (WEBLOGIC8.equalsIgnoreCase(appServerName)) {
            url = "t3://" +
                    (server == null ? "localhost" : server) +
                    ((port < 0) ? "" : (":" + port));
            contextFactory = "weblogic.jndi.WLInitialContextFactory";
            protocol = "t3";
        } else if (O9IAS.equalsIgnoreCase(appServerName)) {
            url = "ormi://" +
                    (server == null ? "localhost" : server) +
                    ((port < 0) ? "" : (":" + port)) +
                    "/" + moduleName;
            contextFactory = "com.evermind.server.rmi.RMIInitialContextFactory";
            protocol = "ormi";
            if (!isServerMode()) {
                if (connectionProperties == null) {
                    connectionProperties = new Properties();
                }
                connectionProperties.put("dedicated.connection", "true");
            }
        } else if (JBOSS4.equalsIgnoreCase(appServerName)) {
            url = (server == null ? "localhost" : server) +
                    ((port < 0) ? "" : (":" + port));
            contextFactory = "org.jnp.interfaces.NamingContextFactory";
            protocol = null;
            connectionProperties = new Properties();
            connectionProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        } else if (GLASSFISH2.equalsIgnoreCase(appServerName)) {
            url = (server == null ? "localhost" : server) +
                    ((port < 0) ? "" : (":" + port));
            if (!url.equals("localhost")) {
                throw new NoSuchElementException("Unsupported Remote Application Server " + appServerName + ". Use localhost");
            }
            contextFactory = null;
            protocol = null;
            connectionProperties = new Properties();
        } else {
            throw new NoSuchElementException("Unsupported Application Server " + appServerName + ". Use one of the following : " + SUPPORTED_SERVERS);
        }
        this.server = server;
        this.port = port;
        this.appServerName = appServerName;
        connect(url, user, password, contextFactory, connectionProperties);
    }

    public void connect(String url, String user, String password, String contextFactory, Properties extraProperties) throws NamingException {
        this.url = url;
        this.user = user;
        this.password = password == null ? "" : password;
        this.contextFactory = contextFactory;
        this.extraProperties = extraProperties;
        Properties properties = null;
        try {
            properties = new Properties();
            if (this.contextFactory != null) {
                properties.put(Context.INITIAL_CONTEXT_FACTORY, this.contextFactory);
            }
            if (this.url != null) {
                properties.put(Context.PROVIDER_URL, this.url);
            }
            if (user != null) {
                properties.put(Context.SECURITY_PRINCIPAL, this.user);
                properties.put(Context.SECURITY_CREDENTIALS, this.password);
            }
            if (extraProperties != null) {
                properties.putAll(extraProperties);
            }
//            Hashtable initialEnvironment = new Hashtable();//ResourceManager.getInitialEnvironment(null);
//            if (initialEnvironment.get(Context.INITIAL_CONTEXT_FACTORY) != null) {
//                initialContext = new InitialContext();
//            } else {
//            }
            initialContext = new InitialContext(properties);
        } catch (NamingException e) {
            System.out.println("Unable to connect to " + appServerName + " server at " + url);
            System.out.println("Please make sure that the server is running.");
            throw e;
        }
    }

    public EJBObject createSession(Class sessionHomeClass) throws NamingException {
        EJBHome home = getHome(sessionHomeClass);
        try {
            Method method = home.getClass().getMethod("create", new Class[0]);
            return (EJBObject) method.invoke(home, new Object[0]);
        } catch (NoSuchMethodException e) {
            throw new NamingException("NoSuchMethodException : " + e);
        } catch (IllegalAccessException e) {
            throw new NamingException("IllegalAccessException : " + e);
        } catch (InvocationTargetException e) {
            throw new NamingException("InvocationTargetException : " + e);
        }
    }

    public EJBHome getHome(Class homeClass) throws NamingException {

        //look up jndi name
        String jndiName=null;
        String defaultJndiName=Utils.removeTail(Utils.getClassName(homeClass), "Home".length());
        if(GLASSFISH2.equals(appServerName)){
            jndiName="java:comp/env/"+defaultJndiName;
        }else{
            jndiName=defaultJndiName;
        }
        Object ref = initialContext.lookup(jndiName);
        //look up jndi name and cast to Home interface
        return (EJBHome) Utils.castTo(PortableRemoteObject.narrow(ref, homeClass),
                homeClass);
    }

    public String getServer() {
        return server;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getContextFactory() {
        return contextFactory;
    }

    public String getAppServerName() {
        return appServerName;
    }

    public InitialContext getInitialContext() {
        return initialContext;
    }

    public Properties getExtraProperties() {
        return extraProperties;
    }

    public boolean isServerMode() {
        return serverMode;
    }

    public void setServerMode(boolean serverMode) {
        this.serverMode = serverMode;
    }
}

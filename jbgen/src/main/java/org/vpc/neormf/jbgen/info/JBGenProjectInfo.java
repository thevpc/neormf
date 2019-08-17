/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.info;

import org.vpc.neormf.jbgen.JBGenMain;
import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.config.JBGenConfig;
import org.vpc.neormf.jbgen.config.XmlConfig;
import org.vpc.neormf.jbgen.csharp.util.CSharpUtils;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBRelation;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.projects.TargetGenerator;
import org.vpc.neormf.jbgen.sql.dbsupport.DefaultDBSupport;
import org.vpc.neormf.jbgen.sql.dbsupport.RDBMSSupport;
import org.vpc.neormf.jbgen.sql.dbsupport.derby.DerbyLocalDriverUrlParser;
import org.vpc.neormf.jbgen.sql.dbsupport.derby.DerbyNetworkDriverUrlParser;
import org.vpc.neormf.jbgen.sql.dbsupport.derby.DerbySupport;
import org.vpc.neormf.jbgen.sql.dbsupport.mssqlserver.MSSQLServerSupport;
import org.vpc.neormf.jbgen.sql.dbsupport.mysql.MySQLSupport;
import org.vpc.neormf.jbgen.sql.dbsupport.oracle.OracleSupport;
import org.vpc.neormf.jbgen.sql.urlparser.DBCDriverUrlParser;
import org.vpc.neormf.jbgen.sql.urlparser.JdbcOdbcDriverUrlParser;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.StringListFilter;
import org.vpc.neormf.jbgen.util.TLog;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * Code styles for generated classes naming and misc config.
 * Also handles user preferences for generation target dir and source database
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 29 mars 2004 Time: 19:36:07
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JBGenProjectInfo extends JBGenInfo {

    public static final Set<String> EJB_VERSIONS = new TreeSet<String>(Arrays.asList(new String[]{"1.1", "2.0", "2.1", "3.0"}));
    public static final String WEBLOGIC8 = "weblogic8";
    public static final String O9IAS = "O9iAS";
    public static final String OC4J = "OC4J";
    public static final String JBOSS4 = "jboss4";
    public static final String GLASSFISH2 = "glassfish2";
    public static String LANGUAGE_JAVA = "java";
    public static String LANGUAGE_CSHARP = "csharp";
    public static final Set<String> SUPPORTED_SERVERS = Collections.unmodifiableSet(new TreeSet<String>(Arrays.asList(new String[]{WEBLOGIC8, O9IAS, JBOSS4, GLASSFISH2, OC4J})));
    public static final Set<String> SUPPORTED_LOG_API_JAVA = Collections.unmodifiableSet(new TreeSet<String>(Arrays.asList(new String[]{"none", "log4j", "stdout"})));
    public static final Set<String> SUPPORTED_LOG_API_CSHARP = Collections.unmodifiableSet(new TreeSet<String>(Arrays.asList(new String[]{"none", "log4net", "stdout"})));
    private File folder;
    private File referenceFolder;
    private File file;
    private Hashtable<String, DAOInfo> doInfos = new Hashtable<String, DAOInfo>();
    private Hashtable<String, BOInfo> boInfos = new Hashtable<String, BOInfo>();
    private LinkedHashMap<String, DBTableInfo> dbTables = new LinkedHashMap<String, DBTableInfo>();
    private HashMap userProperties = new HashMap();
    private TargetGenerator target;
    private Hashtable cache = new Hashtable();
    private TreeSet<File> generatedFiles = new TreeSet<File>();
    private TreeSet<File> oldGeneratedFiles = new TreeSet<File>();
    private RDBMSSupport rdbmsSupport;
    private JBGenMain jbgen;
    private String classpath;
    private DBCDriverUrlParser driverUrlParser;
    private Vector<DBCDriverUrlParser> driverUrlParserList = new Vector<DBCDriverUrlParser>();
    private Vector<RDBMSSupport> rdbmsSupportList = new Vector<RDBMSSupport>();
    private Vector<DBRelation> relations = new Vector<DBRelation>();

    public JBGenProjectInfo(JBGenMain jbgen, File folder) throws IOException {
        this.folder = folder;
        this.jbgen = jbgen;
        
        addRDBMSSupport(new DefaultDBSupport());
        addRDBMSSupport(new DerbySupport());
        addRDBMSSupport(new OracleSupport());
        addRDBMSSupport(new MSSQLServerSupport());
        addRDBMSSupport(new MySQLSupport());

        addDriverUrlParser(new JdbcOdbcDriverUrlParser());
        addDriverUrlParser(new DerbyLocalDriverUrlParser());
        addDriverUrlParser(new DerbyNetworkDriverUrlParser());
        
        JBGenConfig defaults = null;
        File defaultsFile = new File(folder, "jbgen-defaults.xml");
        if (defaultsFile.exists()) {
            defaults = createConfig(null, null);
            defaults.setFile(defaultsFile);
            getLog().info("Loading " + defaultsFile.getCanonicalPath());
            defaults.load(defaultsFile);
        }
        file = new File(folder, "jbgen-config.xml");
        this.config = createConfig(defaults, null);
        File jbgen_output_file = new File(folder, "jbgen-output.xml");
        if (jbgen_output_file.exists()) {
            ConfigNode jbgen_output_node = ConfigNode.load(new File(folder, "jbgen-output.xml"));
            ConfigNode file_elements = jbgen_output_node.getChild("file-elements", false);
            if (file_elements != null) {
                for (ConfigNode fileNode : file_elements.getChildren()) {
                    File f = new File(fileNode.getAttribute("path"));
                    oldGeneratedFiles.add(f);
                }
            }
        }
        // file must exists
        getLog().info("Loading " + file.getCanonicalPath());
        config.setFile(file);
        config.load(file);
        getLog().info("");
    }

    public JBGenConfig createConfig(JBGenConfig base, String prefix) {
        return new XmlConfig(base, prefix, jbgen);
    }

    public String getModuleName() {
        return getString("target." + target.getName(), "name", "NeormfApplication", true);
    }

    public String getDataSourceName() {
        return getString("target." + target.getName() + ".dataSource", "name", "MyDataSource", true);
    }

    //TODO a koa ca sert?
    public boolean generateLog(String logKey) {
        return config.getBoolean("generate.log." + logKey, true);
    }

    public String getDataSourceSchemaName() {
        return getString("dataSource.schemaName", null, false);
    }

    public String getDataSourceTableNamePattern() {
        return getString("dataSource.tableNamePattern", null, false);
    }

    //TODO a koa ca sert?
    public StringListFilter getGeneratorTableNamePattern(String generationKeyName) {
        return new StringListFilter(getString("generate." + generationKeyName, null, true));
    }

    public String getModulePackage() {
        return getString("target." + getValidTarget().getName() + ".package", "com", true);
    }

    public String getProjectFolder() {
        String projectPath = getString("target." + getValidTarget().getName() + ".root-path", null, false);
        return JBGenUtils.getPath(getReferenceFolder().getAbsolutePath(), projectPath);
    }

    public String getModuleFolder(String moduleName) {
        String modulePath = getString("target." + getValidTarget().getName() + "." + moduleName + ".root-path", null, false);
        return JBGenUtils.getPath(getProjectFolder(), modulePath);
    }

    public String getSubModuleFolder(String moduleName, String submoduleName) {
        String path = getString("target." + getValidTarget().getName() + "." + moduleName + "." + submoduleName + ".root-path", null, false);
        return JBGenUtils.getPath(getModuleFolder(moduleName), path);
    }

    public String getTargetMetaInfFolder() {
        String moduleName = J2eeTarget.MODULE_EJB;
        String submoduleName = "deployment";
        String path = getString("target." + getValidTarget().getName() + "." + moduleName + "." + submoduleName + ".meta-inf-path", null, false);
        if (path != null) {
            return JBGenUtils.getPath(getModuleFolder(moduleName), path);
        }

        return getSubModuleFolder(J2eeTarget.MODULE_EJB, "deployment");
//        String metainf = (String) cache.get("getTargetMetaInfFolder");
//        if(metainf==null){
//            metainf = getString("target." + getTarget().getName() + "."+J2eeTarget.MODULE_EJB+".deployment.META-INF", "META-INF", false);
//            cache.put("getTargetMetaInfFolder",metainf);
//        }
//        String folder1 = getTargetSrcFolder(J2eeTarget.MODULE_WEB);
//        return folder1+File.separator+(metainf==null?".":metainf);
    }

    public String getTargetSetupFolder() {
        String moduleName = J2eeTarget.MODULE_EJB;
        String submoduleName = "deployment";
        String path = getString("target." + getValidTarget().getName() + "." + moduleName + "." + submoduleName + ".setup-path", null, false);
        return JBGenUtils.getPath(getModuleFolder(moduleName), path);
    }

    public String getTargetWebInfFolder() {
        String moduleName = J2eeTarget.MODULE_WEB;
        String submoduleName = "deployment";
        String path = getString("target." + getValidTarget().getName() + "." + moduleName + "." + submoduleName + ".web-inf-path", null, false);
        if (path != null) {
            return JBGenUtils.getPath(getModuleFolder(moduleName), path);
        }
        return getSubModuleFolder(J2eeTarget.MODULE_WEB, ".deployment");
//        String info = (String) cache.get("getTargetWebInfFolder");
//        if (info==null) {
//            info=getString("target." + getTarget().getName() + "."+J2eeTarget.MODULE_WEB+".deployment.WEB-INF", "WEB-INF", false);
//            cache.put("getTargetWebInfFolder",info);
//        }
//        String folder1 = getTargetSrcFolder(J2eeTarget.MODULE_WEB);
//        return folder1+File.separator+(info==null?".":info);
    }

    @Override
    public JBGenConfig getConfig() {
        return config;
    }

    @Override
    public Properties getVars() {
        return null;
    }

    public File getFolder() {
        return folder;
    }

    public File getFile() {
        return file;
    }

    public DAOInfo getDAOInfoForTable(String tableName, boolean autoCreate) throws IOException {
        return getDBTableByName(tableName, true).getDAOInfo(autoCreate);
//        ConfigNode[] mapping = getConfig().getNodes("object-mappings.data-objects.do", false);
//        DBTableInfo[] tablesArray = getAllTables();
//        HashSet<String> validTables = new HashSet<String>();
//        HashSet<String> validTablesU = new HashSet<String>();
//        for (int i = 0; i < tablesArray.length; i++) {
//            validTables.add(tablesArray[i].getTableName());
//            validTablesU.add(tablesArray[i].getTableName().toUpperCase());
//        }
//        String entityName = null;
//        ForLabel:
//        for (int i = 0; i < mapping.length; i++) {
//            ConfigNode configNode = mapping[i];
//            String attribute = configNode.getAttribute("table-list", null, true, null, true, null);
//            List collection = JBGenUtils.filterList(validTables, attribute, true);
//            if (collection.size() > 0) {
//                if (collection.contains(tableName)) {
//                    entityName = configNode.getName();
//                }
//            } else {
//                throw new IllegalArgumentException("Unknown " + attribute + " is empty. please use one of :\n" + validTables);
//            }
//        }
//        if (entityName == null) {
//            entityName = tableName;
//        }
//
//        DAOInfo n = doInfos.get(entityName);
//        if (n == null && autoCreate) {
//            n = new DAOInfo(entityName, this);
//            doInfos.put(entityName, n);
//        }
//
//        return n;
    }

//    public DAOInfo getDAOInfoForTable(String tableName, boolean autoCreate) throws IOException {
//        ConfigNode[] mapping = getConfig().getNodes("object-mappings.data-objects.do", false);
//        DBTableInfo[] tablesArray = getAllTables();
//        HashSet<String> validTables = new HashSet<String>();
//        HashSet<String> validTablesU = new HashSet<String>();
//        for (int i = 0; i < tablesArray.length; i++) {
//            validTables.add(tablesArray[i].getTableName());
//            validTablesU.add(tablesArray[i].getTableName().toUpperCase());
//        }
//        String entityName = null;
//        ForLabel:
//        for (int i = 0; i < mapping.length; i++) {
//            ConfigNode configNode = mapping[i];
//            String attribute = configNode.getAttribute("table-list", null, true, null, true, null);
//            List collection = JBGenUtils.filterList(validTables, attribute, true);
//            if (collection.size() > 0) {
//                if (collection.contains(tableName)) {
//                    entityName = configNode.getName();
//                }
//            } else {
//                throw new IllegalArgumentException("Unknown " + attribute + " is empty. please use one of :\n" + validTables);
//            }
//        }
//        if (entityName == null) {
//            entityName = tableName;
//        }
//
//        DAOInfo n = doInfos.get(entityName);
//        if (n == null && autoCreate) {
//            n = new DAOInfo(entityName, this);
//            doInfos.put(entityName, n);
//        }
//
//        return n;
//    }
//
    public void performExtraChecks() throws NoSuchElementException {
        // entities extra checking
//        MethodDescList methodDescList = getEntitiesMethodDescList();
//        MethodDescList.MethodDesc[] methods = methodDescList.getMethods();
//        DBTable[] tables = getAllTables();
//        HashSet set = new HashSet();
//        for (int i = 0; i < tables.length; i++) {
//            set.add(tables[i].getTableName());
//        }
//
//        try {
//            for (int i = 0; i < methods.length; i++) {
//                String[] params = methods[i].getParams();
//                for (int j = 0; j < params.length; j++) {
//                    JBGenUtils.checkInPossibleValues(params[j], set);
//                }
//            }
//        } catch (NoSuchElementException e) {
//            throw new NoSuchElementException("Entity Pattern (module.entities) is incorrect. You should use valid Table Names : " + e.getMessage());
//        }

        // sessions extra checking
//        methodDescList = getSessionsMethodDescList();
//        methods = methodDescList.getMethods();
//        EntityInfo[] infos = getAllEntities();
//        set = new HashSet();
//        for (int i = 0; i < infos.length; i++) {
//            set.add(infos[i].getBeanName());
//        }
//        try {
//            for (int i = 0; i < methods.length; i++) {
//                String[] params = methods[i].getParams();
//                for (int j = 0; j < params.length; j++) {
//                    JBGenUtils.checkInPossibleValues(params[j], set);
//                }
//            }
//        } catch (NoSuchElementException e) {
//            throw new NoSuchElementException("Session Pattern (module.sessions) is incorrect. You should use valid Entity names : " + e.getMessage());
//        }
    }

    public DAOInfo[] getAllEjbEntities() {
        ArrayList<DAOInfo> list = new ArrayList<DAOInfo>();
        for (Iterator i = doInfos.values().iterator(); i.hasNext();) {
            DAOInfo entityInfo = (DAOInfo) i.next();
            boolean isCMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-cmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-cmp-local");
            boolean isBMP = entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-bmp-remote") || entityInfo.doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-bmp-local");
            if (isBMP || isCMP) {
                list.add(entityInfo);
            }
        }
        return list.toArray(new DAOInfo[list.size()]);
    }

    public DAOInfo[] getAllEntities() {
        Collection<DAOInfo> c = doInfos.values();
        return c.toArray(new DAOInfo[c.size()]);
    }

    public BOInfo getBOInfo(String name) throws NoSuchElementException {
        return getBOInfo(name, false);
    }

    public BOInfo getBOInfo(String name, boolean autoCreate) throws NoSuchElementException {
        Collection c = boInfos.values();
        for (Iterator i = c.iterator(); i.hasNext();) {
            BOInfo sessionInfo = (BOInfo) i.next();
            if (name.equals(sessionInfo.getBeanName())) {
                return sessionInfo;
            }
        }
        if (autoCreate) {
            BOInfo n = null;
            try {
                n = new BOInfo(name, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boInfos.put(name, n);
            return n;
        }
        throw new NoSuchElementException("No session named " + name);
    }

    public DAOInfo getDAOInfo(String name) throws NoSuchElementException, IOException {
        return getDAOInfo(name, false);
    }

    public DAOInfo getDAOInfoByTableName(String tableName,boolean required){
        for (DAOInfo daoInfo : doInfos.values()) {
            for (DBTableInfo dbTableInfo : daoInfo.getTables()) {
                if(dbTableInfo.getTableName().equals(tableName)){
                    return daoInfo;
                }
            }
        }
        if (required) {
            throw new NoSuchElementException("No session named " + tableName);
        }
        return null;
    }

    public DBTableInfo getTableInfoByTableName(String tableName,boolean required){
        for (DAOInfo daoInfo : doInfos.values()) {
            for (DBTableInfo dbTableInfo : daoInfo.getTables()) {
                if(dbTableInfo.getTableName().equals(tableName)){
                    return dbTableInfo;
                }
            }
        }
        if (required) {
            throw new NoSuchElementException("No session named " + tableName);
        }
        return null;
    }

    public DAOInfo getDAOInfo(String name, boolean autoCreate) throws NoSuchElementException, IOException {
        Collection c = doInfos.values();
        for (Iterator i = c.iterator(); i.hasNext();) {
            DAOInfo entityInfo = (DAOInfo) i.next();
            if (name.equals(entityInfo.getBeanName())) {
                return entityInfo;
            }
        }
        if (autoCreate) {
            DAOInfo n = new DAOInfo(name, this);
            doInfos.put(name, n);
            return n;
        }
        throw new NoSuchElementException("No entity named " + name);
    }

    public BOInfo[] getAllBOs() {
        ArrayList<BOInfo> list = new ArrayList<BOInfo>();
        for (Iterator i = boInfos.values().iterator(); i.hasNext();) {
            BOInfo sessionInfo = (BOInfo) i.next();
            if (sessionInfo.doGenerateSession()) {
                list.add(sessionInfo);
            }
        }
        return list.toArray(new BOInfo[list.size()]);
    }

    //TODO ???????????
    public BOInfo[] getAllBDLGs() {
        ArrayList<BOInfo> list = new ArrayList<BOInfo>();
        for (Iterator i = boInfos.values().iterator(); i.hasNext();) {
            BOInfo sessionInfo = (BOInfo) i.next();
            sessionInfo.getGeneratedClass("BusinessDelegate");
            if (sessionInfo.doGenerateBusinessDelegate()) {
                list.add(sessionInfo);
            }
        }
        return list.toArray(new BOInfo[list.size()]);
    }

    public void addTable(DBTableInfo dbTable) {
        String k = dbTable.getTableName().toLowerCase();
        if (dbTables.containsKey(k)) {
            throw new IllegalArgumentException("Table " + dbTable.getTableName() + " already declared");
        }
        dbTables.put(k, dbTable);
    //just to initialize it
    //dbTable.getDAOInfo();
    }

    public DBTableInfo[] getAllTables() {
        return dbTables.values().toArray(new DBTableInfo[dbTables.size()]);
    }

    public DBTableInfo getDBTableByName(String tableName, boolean required) {
        for (Iterator i = dbTables.values().iterator(); i.hasNext();) {
            DBTableInfo dbTable = (DBTableInfo) i.next();
            if (dbTable.getTableName().equals(tableName)) {
                return dbTable;
            }
        }
        if (required) {
            //fails
            HashSet<String> list = new HashSet<String>();
            for (Iterator i = dbTables.values().iterator(); i.hasNext();) {
                DBTableInfo dbTable = (DBTableInfo) i.next();
                list.add(dbTable.getTableName());
            }
            JBGenUtils.checkInPossibleValues(tableName, list);
        }
        return null;
    }

    public String getTargetApplicationServer() {
        ConfigNode ejbNode = config.getNode("target." + getValidTarget().getName(), true);
        String s = ejbNode.getAttribute("application-server", null, true, "application server " + SUPPORTED_SERVERS, true, null);
        if (SUPPORTED_SERVERS.contains(s)) {
            return s;
        } else {
            throw new NoSuchElementException("Expected an Application Server from the following list " + SUPPORTED_SERVERS);
        }
    }

    public String getTargetEjbVersion() {
        ConfigNode ejbNode = config.getNode("target." + getValidTarget().getName(), true);
        String s = ejbNode.getAttribute("ejb-version", null, true, "ejb version " + EJB_VERSIONS, true, null);
        if (EJB_VERSIONS.contains(s.toLowerCase())) {
            return s.toLowerCase();
        } else {
            throw new NoSuchElementException("Expected an EJB version from the following list " + EJB_VERSIONS);
        }
    }

    public Map getUserProperties() {
        return userProperties;
    }

    public SessionDeployInfo getExtraSessionsByName(String name) throws NoSuchElementException {
        SessionDeployInfo[] x = getExtraSessions();
        for (int i = 0; i < x.length; i++) {
            SessionDeployInfo sessionDeployInfo = x[i];
            if (name.equals(sessionDeployInfo.getEjbName())) {
                return sessionDeployInfo;
            }
        }
        throw new NoSuchElementException("No Extra Session named " + name);
    }

    public SessionDeployInfo[] getExtraSessions() {
        SessionDeployInfo[] cached_getExtraSessions = (SessionDeployInfo[]) cache.get("getExtraSessions");
        if (cached_getExtraSessions == null) {
            ConfigNode[] children = config.getNodes("target." + getValidTarget().getName() + ".deployment.ejb-jar.user-session", false);
            SessionDeployInfo[] s = new SessionDeployInfo[children.length];
            for (int i = 0; i < s.length; i++) {
                ConfigNode ss = children[i];
                s[i] = new SessionDeployInfo(ss.getAttribute("description", null, false, null, true, getVars()),
                        ss.getAttribute("display-name", null, false, null, true, getVars()),
                        ss.getAttribute("ejb-name", children[i].getName(), true, null, true, getVars()),
                        ss.getAttribute("home", null, true, null, true, getVars()),
                        ss.getAttribute("remote", null, true, null, true, getVars()),
                        ss.getAttribute("ejb-class", null, true, null, true, getVars()),
                        ss.getAttribute("session-type", "Stateless", true, null, true, getVars()),
                        ss.getAttribute("transaction-type", "Container", true, null, true, getVars()));
                if (s[i].getDescription() == null) {
                    s[i].setDescription(s[i].getEjbName());
                }
                if (s[i].getDisplayName() == null) {
                    s[i].setDisplayName(s[i].getEjbName());
                }
            }
            cached_getExtraSessions = s;
            cache.put("getExtraSessions", cached_getExtraSessions);
        }
        return cached_getExtraSessions;
    }

    public Connection createConnection() throws ClassNotFoundException, SQLException {
        if (jbgen.getConnectionProvider() == null) {
            ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
            try {
                Class.forName(node.getChild("driver", true).getValue());
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("Driver Not Found : " + e.toString());
            }
            return DriverManager.getConnection(
                    node.getChild("url", true).getValue(),
                    node.getChild("user", true).getValue(),
                    node.getChild("password", true).getValue());
        } else {
            ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
            return jbgen.getConnectionProvider().getConnection(
                    node.getChild("url", true).getValue(),
                    node.getChild("driver", true).getValue(),
                    node.getChild("user", true).getValue(),
                    node.getChild("password", true).getValue(),
                    null);
        }
    }

    public String getConnectionDriver() {
        ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
        return node.getChild("driver", true).getValue();
    }

    public String getConnectionUser() {
        ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
        return node.getChild("user", true).getValue();
    }

    public String getConnectionDataSource() {
        ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
        ConfigNode n = node.getChild("datasource", false);
        if (n == null) {
            return getDriverUrlParser().getDatasourceClassName();
        } else {
            return n.getValue();
        }
    }

    public String getConnectionPassword() {
        ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
        return node.getChild("password", true).getValue();
    }

    public String getConnectionURL() {
        ConfigNode node = getConfig().getNode("source.jdbc-connection", true);
        return node.getChild("url", true).getValue();
    }
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = createConnection();
            } catch (ClassNotFoundException e) {
                throw new SQLException(e.toString());
            }
        }
        return connection;
    }

    public boolean generateStandardOutputLog() {
        return config.getBoolean("generate.log-stdout", true);
    }

    public boolean generateLog4JLog() {
        return config.getBoolean("generate.log-log4j", true);
    }

    public String[] getEjbReferences(String caller) {
        ConfigNode[] nodes = config.getNodes("target." + getValidTarget().getName() + ".deployment.ejb-jar.ejb-ref<caller=\"" + caller + "\">", false);
        String[] p = new String[nodes.length];
        for (int i = 0; i < p.length; i++) {
            p[i] = nodes[i].getName();
        }
        return p;
    }

    public EjbRoleInfo[] getEjbRoles() {
        ConfigNode[] securityRoles = config.getNodes("target." + getValidTarget().getName() + ".deployment.ejb-jar.security-role", false);
        EjbRoleInfo[] p = new EjbRoleInfo[securityRoles.length];
        for (int i = 0; i < p.length; i++) {
            p[i] = new EjbRoleInfo(securityRoles[i].getName(), securityRoles[i].getValue());
        }
        return p;
    }

    public String[] getSourcePaths() {
        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.add(getModuleFolder(J2eeTarget.MODULE_EJB));
        treeSet.add(getModuleFolder(J2eeTarget.MODULE_DTO));
        //TODO :::::::::::::
        treeSet.add(getModuleFolder(J2eeTarget.MODULE_EJB_BUSINESS_DELEGATE));
        treeSet.add(getModuleFolder(J2eeTarget.MODULE_DAO));
        return treeSet.toArray(new String[treeSet.size()]);
    }

    public String getSecurityDomain() {
        return getString("target." + getValidTarget().getName() + "." + J2eeTarget.MODULE_EJB + ".deployment.ejb-jar.security-domain", null, false);
    }

    public TargetGenerator getTarget() {
        return target;
    }

    public TargetGenerator getValidTarget() {
        if (target == null) {
            throw new NoSuchElementException("No Target has been specified");
        }
        return target;
    }

    public void setTarget(TargetGenerator target) {
        this.target = target;
        cache.clear();
    }

    public DBCDriverUrlParser getDriverUrlParser() {
        if (driverUrlParser == null) {
            String driver = getConnectionDriver();
            int bestValue = -1;
            DBCDriverUrlParser bestParser = null;
            for (DBCDriverUrlParser parser : driverUrlParserList) {
                if (driver != null) {
                    int v = parser.acceptDriver(driver);
                    if (v > 0) {
                        if (bestValue < 0 || bestParser == null || v > bestValue) {
                            bestValue = v;
                            bestParser = parser;
                        }
                    }
                }
            }
        if (bestParser == null) {
            throw new NoSuchElementException("No Parser for " + driver);
        }
            driverUrlParser = bestParser;
        }
        return driverUrlParser;
    }

    public void addDriverUrlParser(DBCDriverUrlParser parser) {
        driverUrlParserList.add(parser);
    }

    public void removeDriverUrlParser(DBCDriverUrlParser parser) {
        driverUrlParserList.remove(parser);
    }

    public void addRDBMSSupport(RDBMSSupport rdbms) {
        rdbmsSupportList.add(rdbms);
    }

    public void removeRDBMSSupport(RDBMSSupport rdbms) {
        rdbmsSupportList.remove(rdbms);
    }

    public RDBMSSupport getRdbmsSupport() throws SQLException {
        if (rdbmsSupport == null) {
            int bestValue = -1;
            String dbserver = getConnection().getMetaData().getDatabaseProductName().toLowerCase();
            RDBMSSupport bestSupport = null;
            for (RDBMSSupport rdbms : rdbmsSupportList) {
                int v = rdbms.accept(dbserver, getConnection());
                if (v > 0) {
                    if (bestValue < 0 || bestSupport == null || v > bestValue) {
                        bestValue = v;
                        bestSupport = rdbms;
                    }
                }
            }
            rdbmsSupport =bestSupport;
            getLog().info("Using RDBMS Adapter " + rdbmsSupport);
        }
        return rdbmsSupport;
    }

    public TLog getLog() {
        return jbgen.getLog();
    }

    public JBGenMain getJbgen() {
        return jbgen;
    }

    public void addGeneratedFile(File file) {
        generatedFiles.add(myCononize(file));
    }

    public Collection<File> getGeneratedFiles() {
        return Collections.unmodifiableCollection(generatedFiles);
    }

    public Collection<File> getLastGeneratedFiles() {
        return Collections.unmodifiableCollection(oldGeneratedFiles);
    }

    public String getClasspath() {
        String path_separator = System.getProperty("path.separator");
        StringBuilder s = new StringBuilder();
        if (classpath != null && classpath.length() > 0) {
            if (s.length() > 0) {
                s.append(path_separator);
            }
            s.append(classpath);
        }
        if (s.length() > 0) {
            s.append(path_separator);
        }
        s.append(jbgen.getClasspath());
        return s.toString();
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getConvertJavaToSQLExpression(DBColumn c, String expression) {
        return JavaUtils.getConvertJavaToSQLExpression(c, expression, this);
    }

    public String getConvertJavaToSQLExpression(DBColumn c, String expression, String converterName) {
        return JavaUtils.getConvertJavaToSQLExpression(c, expression, converterName, this);
    }

    public String getConvertSQLToJavaExpression(DBColumn c, String expression) {
        return JavaUtils.getConvertSQLToJavaExpression(c, expression, null, this);
    }

    public String getConvertSQLToJavaExpression(DBColumn c, String expression, String converterName) {
        return JavaUtils.getConvertSQLToJavaExpression(c, expression, converterName, this);
    }

    public String getConvertCSharpToSQLExpression(DBColumn c, String expression) {
        return CSharpUtils.getConvertCSharpToSQLExpression(c, expression, this);
    }

    public String getConvertCSharpToSQLExpression(DBColumn c, String expression, String converterName) {
        return CSharpUtils.getConvertCSharpToSQLExpression(c, expression, converterName, this);
    }

    public String getConvertSQLToCSharpExpression(DBColumn c, String expression) {
        return CSharpUtils.getConvertSQLToCSharpExpression(c, expression, this);
    }

    public String getConvertSQLToCSharpExpression(DBColumn c, String expression, String converterName) {
        return CSharpUtils.getConvertSQLToCSharpExpression(c, expression, converterName, this);
    }

    public File getReferenceFolder() {
        if (referenceFolder == null) {
            ConfigNode option_reference_folder = config.getNode("options.option-reference-folder", false);
            if (option_reference_folder != null && option_reference_folder.getValue() != null && option_reference_folder.getValue().trim().length() > 0) {
                File f = new File(option_reference_folder.getValue());
                if (f.isAbsolute()) {
                    referenceFolder = f;
                } else {
                    referenceFolder = new File(folder, f.getPath());
                }
            } else {
                referenceFolder = folder;
            }
            try {
                referenceFolder = referenceFolder.getCanonicalFile();
            } catch (IOException e) {
                referenceFolder = referenceFolder.getAbsoluteFile();
            }
        }
        return referenceFolder;
    }

    public File myCononize(File f) {
        if (f == null) {
            return f;
        }
        try {
            f = f.getCanonicalFile();
        } catch (IOException e) {
            f = f.getAbsoluteFile();
        }
        String path = JBGenUtils.getRelativePath(getReferenceFolder(), f);
        return path == null ? f : new File(path);
    }

    public Vector<DBRelation> getRelations() {
        return relations;
    }
}
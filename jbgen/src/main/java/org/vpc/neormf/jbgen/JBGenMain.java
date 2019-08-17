/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen;

import org.vpc.neormf.commons.sql.SQLUtils;
import org.vpc.neormf.commons.util.Utils;
import org.vpc.neormf.jbgen.config.ConfigFilter;
import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactoryManager;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.info.BOInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.java.generators.JBGenModuleGenerator;
import org.vpc.neormf.jbgen.projects.TargetGenerator;
import org.vpc.neormf.jbgen.util.ConsoleTLog;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.vpc.neormf.commons.beans.RelationInfo.Cardinality;
import org.vpc.neormf.commons.beans.RelationInfo.Role;
import org.vpc.neormf.jbgen.dbsupport.DBRelation;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project vpcfwk
 * @creation on Date: 23 mars 2004 Time: 19:25:29
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JBGenMain {

    private TLog log = new ConsoleTLog();
    private DataTypeConverterFactoryManager dataTypeConverterFactoryManager;
    private JBGenConnectionProvider connectionProvider;
    private String classpath;

    public static void build(JBGenOptions options) throws SQLException, IOException, ClassNotFoundException {
        JBGenMain m = new JBGenMain();
        if (options.isWindowMode()) {
            JBGenWindowTool tool = new JBGenWindowTool(m);
            tool.showWindow();
            for (int i = 0; i < options.getFiles().length; i++) {
                tool.generate(options.getFiles()[i]);
            }
        } else {
            for (int i = 0; i < options.getFiles().length; i++) {
                m.generate(options.getFiles()[i]);
            }
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            build(new JBGenOptions(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JBGenMain() {
    }

    public String getHeaderCartridge() {
        String filePath;
        try {
            filePath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            filePath = new File(".").getAbsolutePath();
        }
        return ("\n") + ("**************************************************************************\n") + ("*   JBGen v " + JBGenVersion.getVersion()+ "\n") + ("*   JBGen Object Relational Mapping Generation Tool\n") + ("*   Part of neormf Porject\n") + ("*   @copyrights Vpc 2005-2007\n") + ("*   @author Taha BEN SALAH\n") + ("*   @currentfolder " + filePath + "\n") + ("**************************************************************************\n") + ("");

    }

    public void generate(File folder) throws IOException, SQLException, ClassNotFoundException {
        getLog().info(getHeaderCartridge());
        getLog().info("Running " + folder.getPath() + "...");
        long startTime = System.currentTimeMillis();
        JBGenProjectInfo project = new JBGenProjectInfo(this, folder);
        ConfigNode[] allProjects = project.getConfig().getNodes("target.*", false);
        GenManager genManager = new GenManager(this);
        TargetGenerator defaultTarget=null;
        for (int i = 0; i < allProjects.length; i++) {
            if (allProjects[i].isEnabledPath()) {
                getLog().info(" --- JBGen : Target '" + allProjects[i].getTag() + "'");
                defaultTarget = genManager.getTarget(allProjects[i].getTag());
                break;
            }
        }
        if(defaultTarget==null){
            getLog().error(" --- JBGen : No Target found");
            return;
        }
        project.setTarget(defaultTarget);
        //TODO how to handle log???
//        String[] p = moduleInfo.getConfig().getPropertiesStartingWith("log.");
//        for (int i = 0; i < p.length; i++) {
//            System.setProperty(p[i], moduleInfo.getConfig().getString(p[i]));
//        }
        ConfigNode[] clean = project.getConfig().getNodes("options.clear-folder", false);
        if (clean != null) {
            for (int i = 0; i < clean.length; i++) {
                String value = clean[i].getValue();
                if (value != null && value.trim().length() > 0) {
                    File f = new File(value.trim());
                    if (JBGenUtils.deleteFile(f)) {
                        getLog().info("deleted " + f);
                    } else {
                        getLog().error("could not clean non existing file/folder '" + f + "'");
                    }
                }
            }
        }
        Connection connection = project.getConnection();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tablesResultSet = null;
        try {
            long startDate = System.currentTimeMillis();
            getLog().info("Loading Database schema...");
            ConfigNode sourceJdbc = project.getConfig().getNode("source.jdbc-connection", false);
            if (sourceJdbc != null) {
                ConfigNode schema = sourceJdbc.getChild("schema", false);
                tablesResultSet = databaseMetaData.getTables(connection.getCatalog(),
                        schema == null ? null : schema.getValue(),
                        null, null/*new String[]{"TABLE"}*/);
                int allObjectsCounter = 0;
                int usefulObjectsCounter = 0;
                while (tablesResultSet.next()) {
                    allObjectsCounter++;
                    DBTableInfo table = new DBTableInfo(tablesResultSet, project);
                    ConfigNode[] includes = sourceJdbc.getChildren("include");
                    boolean included = includes.length == 0;
                    String attribs="name=\"" + table.getTableName().toUpperCase() + "\",type=\"" + table.getTableType().toUpperCase() + "\"";
                    for (int i = 0; i < includes.length; i++) {
                        ConfigNode include = includes[i];
                        if (include.accept(ConfigFilter.valueOf("include<"+attribs+">")[0])) {
                            included = true;
                            break;
                        }
                    }
                    ConfigNode[] excludes = sourceJdbc.getChildren("exclude");
                    boolean excluded = false;
                    for (int i = 0; i < excludes.length; i++) {
                        ConfigNode exclude = excludes[i];
                        if (exclude.accept(ConfigFilter.valueOf("exclude<"+attribs+">")[0])) {
                            excluded = true;
                            break;
                        }
                        if(!excluded && table.getTableName().contains("BIN$")){
                            exclude.accept(ConfigFilter.valueOf("exclude<"+attribs+">")[0]);
                        }
                    }
                    if (included && !excluded) {
                        usefulObjectsCounter++;
                        table.init();
                        project.addTable(table);
                    }
                }
                tablesResultSet.close();
                tablesResultSet = null;
                long endDate = System.currentTimeMillis();
                getLog().info(usefulObjectsCounter + "/" + allObjectsCounter + " Database objects loaded successfully  in " + Utils.getPeriodDescription(endDate - startDate) + " ...");
                if (allObjectsCounter == 0) {
                    //
                    getLog().error("No objects, schema '" + (schema == null ? null : schema.getValue()) + "' is incorrect??try one of the following :");
                    tablesResultSet = databaseMetaData.getSchemas();
                    while (tablesResultSet.next()) {
                        getLog().error(" " + tablesResultSet.getString("TABLE_SCHEM") + ", ");
                    }
                    System.err.print(" ...");
                    tablesResultSet.close();
                    tablesResultSet = null;
                } else if (usefulObjectsCounter == 0) {
                    getLog().error("No valid objects in schema '" + (schema == null ? null : schema.getValue()) + "' ?? filters did eliminate the following :");
                    tablesResultSet = databaseMetaData.getTables(connection.getCatalog(),
                            schema == null ? null : schema.getValue(),
                            null, null/*new String[]{"TABLE"}*/);
                    while (tablesResultSet.next()) {
                        String tcat = tablesResultSet.getString("TABLE_CAT");
                        String tschem = tablesResultSet.getString("TABLE_SCHEM");
                        String tn = tablesResultSet.getString("TABLE_NAME");
                        String tt = tablesResultSet.getString("TABLE_TYPE");
                        getLog().error(tcat + "." + tschem + "." + tn + " [" + tt + "]");
                    }
                    tablesResultSet.close();
                    tablesResultSet = null;
                }
            }
            for (DBTableInfo dBTable : project.getAllTables()) {
                ResultSet rs = databaseMetaData.getImportedKeys(dBTable.getTableCat(), dBTable.getTableSchem(), dBTable.getTableName());
                int lastKeySEQ = -1;
                DBRelation lastRelation = null;
                while (rs.next()) {
                    int KEY_SEQ = rs.getInt("KEY_SEQ");
                    String PKTABLE_CAT = rs.getString("PKTABLE_CAT");
                    String PKTABLE_SCHEM = rs.getString("PKTABLE_SCHEM");
                    String PKTABLE_NAME = rs.getString("PKTABLE_NAME");
                    String PKCOLUMN_NAME = rs.getString("PKCOLUMN_NAME");
                    String FKCOLUMN_NAME = rs.getString("FKCOLUMN_NAME");
                    String FKTABLE_CAT = rs.getString("FKTABLE_CAT");
                    String FKTABLE_SCHEM = rs.getString("FKTABLE_SCHEM");
                    String FKTABLE_NAME = rs.getString("FKTABLE_NAME");
                    DBTableInfo actual = project.getDBTableByName(FKTABLE_NAME, true);
                    DBTableInfo referenced = project.getDBTableByName(PKTABLE_NAME, true);
                    if (lastRelation == null || (lastKeySEQ > 0 && KEY_SEQ <= lastKeySEQ)) {
                        int UPDATE_RULE = rs.getInt("UPDATE_RULE");
                        int DELETE_RULE = rs.getInt("DELETE_RULE");
                        String FK_NAME = rs.getString("FK_NAME");
                        String PK_NAME = rs.getString("PK_NAME");
                        int DEFERRABILITY = rs.getInt("DEFERRABILITY");
                        lastRelation = new DBRelation(project, actual, referenced, UPDATE_RULE, DELETE_RULE, DEFERRABILITY, FK_NAME, PK_NAME);
                        project.getRelations().add(lastRelation);
                        actual.addImportRelation(lastRelation);
                        referenced.addExportRelation(lastRelation);
                    }

                    DBColumn fkc=actual.getColumn(FKCOLUMN_NAME, true);
                    DBColumn pkc=referenced.getColumn(PKCOLUMN_NAME, true);
                    lastRelation.addFkColumn(fkc);
                    lastRelation.addPkColumn(pkc);
                    fkc.addRelationLink(new DBColumn.RelationLink(lastRelation, KEY_SEQ,Role.Foreign,Cardinality.MANY_TO_ONE));
                    pkc.addRelationLink(new DBColumn.RelationLink(lastRelation, KEY_SEQ,Role.Primary,Cardinality.ONE_TO_MANY));
                    lastKeySEQ = KEY_SEQ;
                }
            }

            ConfigNode sourceVirtual = project.getConfig().getNode("source.user-objects", false);
            if (sourceVirtual != null) {
                ConfigNode[] configNodes = sourceVirtual.getChildren("do");
                for (ConfigNode configNode : configNodes) {
                    DBTableInfo table = new DBTableInfo(configNode.getName(), null, null, DBTableInfo.VIRTUAL_TABLE_TYPE, configNode.getAttribute("description"), project);
                    project.addTable(table);
                }
            }
            for (DBTableInfo dbTable : project.getAllTables()) {
                dbTable.getDAOInfo();
            }
            for (DAOInfo daoInfo : project.getAllEntities()) {
                daoInfo.getBOInfo();
            }
            project.performExtraChecks();
            for (int i = 0; i < allProjects.length; i++) {
                if (allProjects[i].isEnabledPath()) {
                    getLog().info(" --- JBGen : Target '" + allProjects[i].getTag() + "'");
                    TargetGenerator prj = genManager.getTarget(allProjects[i].getTag());
                    project.setTarget(prj);
                    project.getUserProperties().clear();
                    JBGenModuleGenerator[] moduleGenerator = prj.getModules();
                    for (int j = 0; j < moduleGenerator.length; j++) {
                        getLog().info(" ------ " + moduleGenerator[j]);
                        moduleGenerator[j].generate(connection, project);
                    }
                } else {
                    getLog().info(" --- JBGen : Ingnored Target '" + allProjects[i].getTag() + "'");
                }
            }
            checkNoMoreGeneratedFiles(project);
            generateOutput(project);
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            getLog().info(" --- JBGen : generation succeeded in " + Utils.getPeriodDescription(time) + " .");
        } finally {
            if (tablesResultSet != null) {
                tablesResultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void checkNoMoreGeneratedFiles(JBGenProjectInfo moduleInfo) throws FileNotFoundException {
        Collection<File> oldf = new ArrayList<File>(moduleInfo.getLastGeneratedFiles());
        Collection<File> newf = moduleInfo.getGeneratedFiles();
        oldf.removeAll(newf);
        for (File f : oldf) {
            System.out.println("File " + f + " no more used, should be deleted?");
        }
    }

    public static void generateOutput(JBGenProjectInfo moduleInfo) throws FileNotFoundException {
        ConfigNode result = new ConfigNode("jbgen-output");
        for (DBTableInfo elem : moduleInfo.getAllTables()) {
            ConfigNode tables = result.getChildOrCreateIt("table-elements");
            ConfigNode t = new ConfigNode("table");
            t.setName(elem.getTableName());
            DAOInfo entityInfo = elem.getDAOInfo(false);
            if (entityInfo != null) {
                t.setAttribute("do", entityInfo.getBeanName());
            }
            tables.add(t);
        }
        for (DAOInfo elem : moduleInfo.getAllEntities()) {
            ConfigNode tables = result.getChildOrCreateIt("do-elements");
            ConfigNode t = new ConfigNode("do");
            t.setName(elem.getBeanName());
            BOInfo entityInfo = elem.getBOInfo(false);
            if (entityInfo != null) {
                t.setAttribute("bo", entityInfo.getBeanName());
            }
            DBColumn[] columns = elem.getColumns(true, true, true);
            for (int i = 0; i < columns.length; i++) {
                DBColumn dbColumn = columns[i];
                ConfigNode f = new ConfigNode("field");
                f.setName(dbColumn.getBeanFieldName());
                f.setAttribute("title", dbColumn.getTitle());
                f.setAttribute("columnName", dbColumn.getColumnName());
                f.setAttribute("columnSize", String.valueOf(dbColumn.getColumnSize()));
                f.setAttribute("columnDecimalDigits", String.valueOf(dbColumn.getColumnDecimalDigits()));
                f.setAttribute("columnBufferLength", String.valueOf(dbColumn.getColumnBufferLength()));
                f.setAttribute("columnNumRecRadix", String.valueOf(dbColumn.getColumnNumRecRadix()));
                f.setAttribute("columnPkKeySeq", String.valueOf(dbColumn.getColumnPkKeySeq()));
                f.setAttribute("sqlType", SQLUtils.getTypeName(dbColumn.getSqlType()));
                f.setAttribute("sqlTypeName", String.valueOf(dbColumn.getTypeName()));
                f.setAttribute("tableName", dbColumn.getTableName());
                t.add(f);
            }
            tables.add(t);
        }
        for (BOInfo elem : moduleInfo.getAllBOs()) {
            ConfigNode tables = result.getChildOrCreateIt("bo-elements");
            ConfigNode t = new ConfigNode("bo");
            t.setName(elem.getBeanName());
            tables.add(t);
        }
        for (BOInfo elem : moduleInfo.getAllBDLGs()) {
            ConfigNode tables = result.getChildOrCreateIt("bdlg-elements");
            ConfigNode t = new ConfigNode("bdlg");
            t.setName(elem.getBeanName());
            tables.add(t);
        }
        for (Object elem : moduleInfo.getGeneratedFiles()) {
            File file = (File) elem;
            ConfigNode files = result.getChildOrCreateIt("file-elements");
            ConfigNode t = new ConfigNode("file");
            t.setName(file.getName());
            t.setAttribute("path", file.getPath());
            files.add(t);
        }
        result.store(new File(moduleInfo.getFolder(), "jbgen-output.xml"));
    }

    public static String comments() {
        return "DO NOT EDIT MANUALLY\n" + "GENERATED AUTOMATICALLY BY JBGen (" + JBGenVersion.getMajorVersion() + ")\n" + "@author Taha BEN SALAH (taha.bensalah@gmail.com)\n" + "@framework neormf (license GPL)\n" //                +"@modification on " + new java.util.Date() + " by " + System.getProperty("user.name")
                ;
    }

    public TLog getLog() {
        return log;
    }

    public void setLog(TLog log) {
        this.log = log;
    }

    public DataTypeConverterFactoryManager getDataTypeConverterFactoryManager() {
        if (dataTypeConverterFactoryManager == null) {
            dataTypeConverterFactoryManager = new DataTypeConverterFactoryManager(this);
        }
        return dataTypeConverterFactoryManager;
    }

    public static boolean isValidProjectFolder(File folder) {
        return folder != null && new File(folder, "jbgen-config.xml").exists();
    }

    public static File getProjectFile(File folder) {
        return new File(folder, "jbgen-config.xml");
    }

    public JBGenConnectionProvider getConnectionProvider() {
        return connectionProvider;
    }

    public void setConnectionProvider(JBGenConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public static File findDOConfigFile(String name, File folder) {
        String fileName = name + ".do.xml";
        File[] possibleFiles = JBGenUtils.searchFilesByDosFilterName(fileName, folder);
        if (possibleFiles.length == 0) {
            return new File(folder, fileName);
        } else {
            return possibleFiles[0];
        }
    }

    public static File findBOConfigFile(String name, File folder) {
        String fileName = name + ".bo.xml";
        File[] possibleFiles = JBGenUtils.searchFilesByDosFilterName(fileName, folder);
        if (possibleFiles.length == 0) {
            return new File(folder, fileName);
        } else {
            return possibleFiles[0];
        }
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
        s.append(System.getProperty("java.class.path"));
        return s.toString();
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }
}

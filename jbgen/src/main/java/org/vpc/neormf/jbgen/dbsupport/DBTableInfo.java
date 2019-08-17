package org.vpc.neormf.jbgen.dbsupport;

import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.info.AbstractInfo;
import org.vpc.neormf.jbgen.info.DAOInfo;
import org.vpc.neormf.jbgen.info.JBGenProjectInfo;
import org.vpc.neormf.jbgen.sql.dbsupport.RDBMSSupport;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.io.File;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 15:17:24
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DBTableInfo extends AbstractInfo {

    public static String VIRTUAL_TABLE_TYPE = "$NEORMF_VIRTUAL$";
    private String tableType;
    private String tableSchem;
    private String tableCat;
    private String tableName;
    private String tableRemarks;
    private LinkedHashMap<String, DBColumn> columns;
    private DAOInfo entityInfo;
    private JBGenProjectInfo project;
    private boolean autoIdentity;
    private String sequence;
    private File file;
    private ArrayList<DBRelation> importRelations = new ArrayList<DBRelation>();
    private ArrayList<DBRelation> exportRelations = new ArrayList<DBRelation>();

    public DBTableInfo(ResultSet tablesResultSet, JBGenProjectInfo project) throws SQLException, IOException {
        super(project, "table." + tablesResultSet.getString("TABLE_NAME"));
        this.tableType = tablesResultSet.getString("TABLE_TYPE");
        this.tableSchem = tablesResultSet.getString("TABLE_SCHEM");
        this.tableCat = tablesResultSet.getString("TABLE_CAT");
        this.tableName = tablesResultSet.getString("TABLE_NAME");
        try {
            this.tableRemarks = tablesResultSet.getString("TABLE_REMARKS");
        } catch (SQLException e) {
            this.tableRemarks = tablesResultSet.getString("REMARKS");
        }
        this.project = project;
    }

    public String getLogCatagory() {
        return null;
    }

    public void addImportRelation(DBRelation r) {
        importRelations.add(r);
    }

    public void addExportRelation(DBRelation r) {
        exportRelations.add(r);
    }


    public Collection<DBRelation> getImportRelations() {
        return Collections.unmodifiableCollection(importRelations);
    }

    public Collection<DBRelation> getExportRelations() {
        return Collections.unmodifiableCollection(exportRelations);
    }

    public void init() throws IOException {
        String fileName = getTableName() + ".table.xml";
        File[] possibleFiles = JBGenUtils.searchFilesByDosFilterName(fileName, project.getFolder());
        if (possibleFiles.length > 1) {
            getLog().warning("ambigous name for " + fileName);
        } else if (possibleFiles.length == 0) {
            this.file = new File(project.getFolder(), fileName);
        } else {
            this.file = possibleFiles[0];
        }


        getConfig().setFile(file);
        if (file.exists()) {
            if (isJBGenLogOptionEnabled("info-if-found-table-file", true)) {
                getLog().info("Loading TableInfo " + file.getPath());
            }
            getConfig().load(file);
        } else {
            if (isJBGenLogOptionEnabled("warn-if-missing-table-file", false)) {
                getLog().warning("Not Particular Configuration For " + tableName + " as File Not found : " + project.getFolder().getCanonicalPath() + "/**/" + file.getName());
            }
        }
        ConfigNode children = getConfig().getNode("object-definition.table<name=\"" + tableName + "\">", false);
        if (children != null) {
            setSequence(children.getAttribute("sequence"));
        }
    }

    public DBTableInfo(String name, String schema, String cat, String type, String remarks, JBGenProjectInfo moduleInfo) throws SQLException, IOException {
        super(moduleInfo, "table." + name);
        this.tableType = type;
        this.tableSchem = schema;
        this.tableCat = cat;
        this.tableName = name;
        this.tableRemarks = remarks;
        this.project = moduleInfo;
        addVar("TableName", tableName);
        addVar("TableType", tableType);
        if (tableSchem != null) {
            addVar("Schema", tableSchem);
        }
        if (tableCat != null) {
            addVar("Catalog", tableCat);
        }
        init();
    }

    private void loadColumns() {
        try {
            if (columns == null) {
                DatabaseMetaData md = project.getConnection().getMetaData();
                ResultSet columnsResultSet = null;
                try {
                    columnsResultSet = md.getColumns(tableCat, tableSchem, tableName, null);
                    columns = new LinkedHashMap<String, DBColumn>();
                    while (columnsResultSet.next()) {
                        DBColumn dbColumn = new DBColumn(columnsResultSet, this);
                        addColumn(dbColumn);
                    }
                } finally {
                    columnsResultSet.close();
                }
                ResultSet pkResultSet = null;
                try {
                    pkResultSet = md.getPrimaryKeys(tableCat, tableSchem, tableName);
                    int pk = 0;
                    RDBMSSupport rdbmsSupport = project.getRdbmsSupport();
                    while (pkResultSet.next()) {
                        String pkColumnName = pkResultSet.getString("COLUMN_NAME");
                        int keySeq = pkResultSet.getInt("KEY_SEQ");
                        String pkName = pkResultSet.getString("PK_NAME");
                        DBColumn pki = getColumn(pkColumnName);
                        pki.setPkPosition(pk++);
                        if (getSequence() != null || rdbmsSupport.isAutoIdentity(pki, project.getConnection())) {
                            setAutoIdentity(true);
                            pki.setAutoIdentity(true);
                            pki.setForbiddenOnInsert(true);
                            pki.setRequiredOnInsert(false);
                        }
//                pki.setForbiddenOnUpdate(true);
                        pki.setPkKeySeq(keySeq);
                        pki.setPkName(pkName);
                    }
                    //TODO
//                    if(oracle){
//                        PreparedStatement ps = moduleInfo.getConnection().prepareStatement("Select 1 from Sequences Where SEQ_NAME=?");
//                        ps.setString(1,entityInfo.getSequenceName());
//                        ResultSet rs = ps.executeQuery();
//                        if(rs.next()){
//                            setAutoIdentity(true);
//                            setSequence(rs.getString(1));
//                        }
//                    }
                } finally {
                    pkResultSet.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(DBColumn dbColumn) {
        String k = dbColumn.getColumnName().toUpperCase();
        if (columns.containsKey(k)) {
            throw new IllegalArgumentException("Column " + getTableName() + "." + dbColumn.getColumnName() + " alreadey declared");
        }
        columns.put(k, dbColumn);
    }

    public DBColumn getColumn(String name, boolean required) {
        loadColumns();
        for (DBColumn dbColumn : columns.values()) {
            if (dbColumn.getColumnName().equals(name)) {
                return dbColumn;
            }
        }
        if (required) {
            throw new IllegalArgumentException("Column " + getTableName() + "." + name + " not found");
        }
        return null;
    }

    public DBColumn getColumn(String name) {
        return getColumn(name, false);
    }

    /**
     * pk columns then others
     *
     * @return pk columns then others
     */
    public DBColumn[] getOrderedColumns() {
        loadColumns();
        ArrayList<DBColumn> colspk = new ArrayList<DBColumn>();
        ArrayList<DBColumn> colsu = new ArrayList<DBColumn>();
        for (DBColumn dbColumn1 : columns.values()) {
            if (dbColumn1.getPkPosition() >= 0) {
                colspk.add(dbColumn1);
            } else {
                colsu.add(dbColumn1);
            }
        }
        colspk.addAll(colsu);
        return colspk.toArray(new DBColumn[colspk.size()]);
    }

    public DBColumn[] getColumns() {
        loadColumns();
        return columns.values().toArray(new DBColumn[columns.size()]);
    }

    public DBColumn[] getPkColumns() {
        loadColumns();
        Hashtable<Integer, DBColumn> ht = new Hashtable<Integer, DBColumn>();
        for (DBColumn dbColumn1 : columns.values()) {
            if (dbColumn1.getPkPosition() >= 0) {
                ht.put(dbColumn1.getPkPosition(), dbColumn1);
            }
        }
        int size = ht.size();
        ArrayList<DBColumn> al = new ArrayList<DBColumn>();
        for (int i = 0; i < size; i++) {
            al.add(ht.get(new Integer(i)));
        }
        return al.toArray(new DBColumn[al.size()]);
    }

    public DBColumn[] getUpdatableColumns() {
        loadColumns();
        ArrayList<DBColumn> al = new ArrayList<DBColumn>();
        for (Iterator i = columns.values().iterator(); i.hasNext();) {
            DBColumn dbColumn = (DBColumn) i.next();
            if (dbColumn.getPkPosition() < 0) {
                al.add(dbColumn);
            }
        }
        return al.toArray(new DBColumn[al.size()]);
    }

//    public DBColumn getColumnByBeanFieldName(String name){
//        for(int i=0;i<columns.length;i++){
//            if(columns[i].beanFieldName.equals(name)){
//                return columns[i];
//            }
//        }
//        return null;

    //    }

    @Override
    public String toString() {
        return tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public String getTableSchem() {
        return tableSchem;
    }

    public String getTableCat() {
        return tableCat;
    }

    //TODO add Cotes
    public String getTableName() {
        return tableName;
    }

    public String getTableNameCoted() {
        try {
            return entityInfo.getProjectInfo().getRdbmsSupport().coteIdentifier(entityInfo, getTableName());
        } catch (SQLException e) {
            return getTableName();
        }
    }

    public String getTableName2() {
        return entityInfo.getTablePrefix() + JBGenUtils.replaceString(getTableNameCoted(), "\"", "\\\"");
    }

    public String getTableNameQ() {
        return entityInfo.getTablePrefix() + getTableNameCoted();
    }

    public String getTableRemarks() {
        return tableRemarks;
    }

    public DAOInfo getDAOInfo(boolean autoCreate) {
        if (entityInfo == null && autoCreate) {
            try {
                String doName = null;
                ConfigNode[] tabConfig = getConfig().getNodes("object-mappings.data-objects.table<name=\"" + tableName + "\"", false, true);
                if (tabConfig.length > 0) {
                    doName = tabConfig[0].getAttribute("do");
                }
                if (doName == null) {
                    ConfigNode[] mapping = getConfig().getNodes("object-mappings.data-objects.do", false);
                    ForLabel:
                    for (int i = 0; i < mapping.length; i++) {
                        ConfigNode configNode = mapping[i];
                        String attribute = configNode.getAttribute("table-list", null, true, null, true, null);
                        if (attribute != null && tableName.matches(attribute)) {
                            doName = configNode.getName();
                            if (doName != null) {
                                break;
                            }
                        }
                    }
                }
                if (doName == null) {
                    doName = getCodeStyleObjectName("do", "{TableName:class}");
                }
                entityInfo = project.getDAOInfo(doName, true);
                entityInfo.addTable(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entityInfo;
    }

    public DAOInfo getDAOInfo() {
        return getDAOInfo(true);
    }

    public boolean isAutoIdentity() {
        return autoIdentity;
    }

    public void setAutoIdentity(boolean autoIdentity) {
        this.autoIdentity = autoIdentity;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Deprecated
    public String getBeanName() {
        return tableName;
    }

    @Override
    public Properties getVars() {
        Properties vars1 = super.getVars();
        addVar("TableName", tableName);
        addVar("TableType", tableType);
        return vars1;
    }

//    public void setEntityInfo(EntityInfo entityInfo) {
//        this.entityInfo = entityInfo;
//    }
//
}


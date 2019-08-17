package org.vpc.neormf.jbgen.info;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.jbgen.config.ConfigNode;
import org.vpc.neormf.jbgen.converters.DataTypeConverterFactory;
import org.vpc.neormf.jbgen.csharp.model.csharpclass.CSharpParam;
import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.dbsupport.DBTableInfo;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaEvaluator;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaField;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;
import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.projects.J2eeTarget;
import org.vpc.neormf.jbgen.util.FieldFormulaImpl;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.FieldFormulaType;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import org.vpc.neormf.commons.beans.RelationInfo.Cardinality;
import org.vpc.neormf.commons.sql.DAOFieldKind;
import org.vpc.neormf.jbgen.dbsupport.DBRelation;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 15 avr. 2004 Time: 18:08:25
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class DAOInfo extends AbstractInfo {

    private File file;
    private ArrayList<DBTableInfo> dbtables = new ArrayList<DBTableInfo>();
    private ArrayList<DBColumn> extraColumns;
    //    private ArrayList columns2;
    private ArrayList updatableColumns;
    private String entityName;
    private BOInfo sessionInfo;
    private DBColumn[] pkColumns;
    private Hashtable cache = new Hashtable();

//    public static final int CMP_LOCAL_BEAN=1;
//    public static final int CMP_REMOTE_BEAN=2;
//    public static final int BMP_LOCAL_BEAN=3;
//    public static final int BMP_REMOTE_BEAN=4;
//    public static final int DBC_BEAN=5;
//    private int genType=BMP_REMOTE_BEAN;
    public DAOInfo(String entityName, JBGenProjectInfo moduleInfo) throws IOException {
        super(moduleInfo, "entity." + entityName);
        this.entityName = entityName;

//        this.file=new File(moduleInfo.getFolder(),JBGenUtils.getJavaClass(this.entityInfo.getEntityName())+".bean");

        String fileName = getBeanName() + ".do.xml";
        File[] possibleFiles = JBGenUtils.searchFilesByDosFilterName(fileName, moduleInfo.getFolder());
        if (possibleFiles.length > 1) {
            getLog().warning("ambigous name for " + fileName);
        } else if (possibleFiles.length == 0) {
            this.file = new File(moduleInfo.getFolder(), fileName);
        } else {
            this.file = possibleFiles[0];
        }


        getConfig().setFile(file);
        if (file.exists()) {
            if (isJBGenLogOptionEnabled("info-if-found-do-file", true)) {
                getLog().info("Loading EntityInfo " + file.getPath());
            }
            getConfig().load(file);
        } else {
            if (isJBGenLogOptionEnabled("warn-if-missing-do-file", false)) {
                getLog().warning("Not Particular Configuration For " + entityName + " as File Not found : " + moduleInfo.getFolder().getCanonicalPath() + "/**/" + file.getName());
            }
        }

        FieldDefinition[] extraFields = getExtraFieldDefinitions();
        extraColumns = new ArrayList(extraFields.length);
        for (int i = 0; i < extraFields.length; i++) {
            DBColumn newColumn = new DBColumn(extraFields[i].isNullable(),
                    "@" + entityName + "@",
                    extraFields[i].getColumnName(),
                    extraFields[i].getType(),
                    extraFields[i].getTypeName(),
                    extraFields[i].getColumnsSize(),
                    extraFields[i].getBufferLength(),
                    extraFields[i].getDecimalDigits(),
                    extraFields[i].getNumPrecRadix());
            newColumn.setFieldKind(extraFields[i].getKind());
            DBColumn existingColumn = getColumnByFieldName(newColumn.getBeanFieldName(), false);
            if (existingColumn != null) {
                throw new IllegalArgumentException("Field " + getBeanName() + "." + newColumn.getBeanFieldName() + " already exists");
            } else {
                newColumn.setDao(this);
                newColumn.setGetterImpl(getGetterImpl(newColumn));
                newColumn.setSetterImpl(getSetterImpl(newColumn));
                newColumn.setCreatorImpl(getCreatorImpl(newColumn));
                extraColumns.add(newColumn);
            }
        }
    }

    public void addTable(DBTableInfo dbTable) {
        if (this.dbtables.size() > 0) {
            // some extra checking
            DBTableInfo leadingTbl = this.dbtables.get(0);
            DBColumn[] leadingPk = leadingTbl.getPkColumns();
            DBColumn[] newPk = dbTable.getPkColumns();
            if (leadingPk.length != newPk.length) {
                throw new IllegalArgumentException("Table Groups must include tables with same primary keys definition");
            }
            for (int i = 0; i < leadingPk.length; i++) {
                if ( //!leadingPk[i].columnName.equals(newPk[i].columnName) ||
                        !leadingPk[i].getSqlDataType().toJavaType().equals(newPk[i].getSqlDataType().toJavaType())) {
                    StringBuilder error = new StringBuilder("Table Group " + this + " must include tables with same primary keys definition)");
                    error.append("\nclashes for TAB1 =").append(leadingTbl).append("\n");
                    for (int k = 0; k < leadingPk.length; k++) {
                        error.append("\t").append(leadingPk[k].getColumnName()).append("\t").append(leadingPk[k].getSqlDataType()).append("\n");
                    }
                    error.append("\nand for TAB2 =").append(dbTable).append("\n");
                    for (int k = 0; k < newPk.length; k++) {
                        error.append("\t").append(newPk[k].getColumnName()).append("\t").append(newPk[k].getSqlDataType()).append("\n");
                    }
                    throw new IllegalArgumentException(error.toString());
                }
            }
        }
        if (entityName == null) {
            entityName = dbTable.getTableName();
        }
        this.dbtables.add(dbTable);
        this.updatableColumns = null;
        TreeSet<String> cols = new TreeSet<String>();
        for (DBColumn dBColumn : dbTable.getColumns()) {
            dBColumn.setDao(this);
            dBColumn.setGetterImpl(getGetterImpl(dBColumn));
            dBColumn.setSetterImpl(getGetterImpl(dBColumn));
            dBColumn.setCreatorImpl(getGetterImpl(dBColumn));
        }

        for (DBRelation rr : dbTable.getExportRelations()) {//this is master
            String n=rr.getFkTable().getCodeStyleObjectName("do", "{TableName:class}");
            n=JBGenUtils.decapitalize(n);

            //verifier si on a une relation 1:1
            //une relation est 1:1 si TOUTES les clef du detail sont contenues dans la clef du Master

            boolean pkUnique=true;
            TreeSet<DBColumn> relationDetailfields=new TreeSet<DBColumn>(rr.getFkColumns());
            for (DBColumn detailPK : rr.getFkTable().getPkColumns()) {
                if(!relationDetailfields.contains(detailPK)){
                    pkUnique=false;
                    break;
                }
            }

            Cardinality card=pkUnique? Cardinality.ONE_TO_ONE:Cardinality.ONE_TO_MANY;
            String cn = null;
            String ttype = null;
            switch(card){
                case ONE_TO_MANY:{
                    cn = n+"List";
                    ttype="List";
                    break;
                }
                case ONE_TO_ONE:{
                    cn = n;
                    ttype=rr.getFkTable().getDAOInfo().getFullDTOName();
                    break;
                }
            }
            String cn2 = cn;
            int i = 1;
            while (cols.contains(cn2)) {
                DBColumn existingColumn = getColumnByFieldName(cn2, false);
                if (existingColumn == null) {
                    break;
                }
                i++;
                cn2 = cn + i;
            }
            DBColumn newColumn = new DBColumn(true,
                    "@" + entityName + "@",
                    cn,
                    Types.JAVA_OBJECT,
                    ttype,
                    0,
                    0,
                    9,
                    0);
            newColumn.setCardinality(card);
            //TODO see here is it is one to one or one to many !!!
            StringBuilder fks = new StringBuilder();
            for (DBColumn dBColumn : rr.getFkColumns()) {
                if (fks.length() > 0) {
                    fks.append(",");
                }
                fks.append(dBColumn.getBeanFieldName());
            }

            StringBuilder pks = new StringBuilder();
            for (DBColumn dBColumn : rr.getPkColumns()) {
                if (pks.length() > 0) {
                    pks.append(",");
                }
                pks.append(dBColumn.getBeanFieldName());
            }
            ConfigNode implNode=new ConfigNode("sqlMasterDetailFieldExpression");
            implNode.setAttribute("detailPrimaryFields", fks.toString());
            implNode.setAttribute("detailTable", rr.getFkTable().getTableName());
            implNode.setAttribute("foreignFields", pks.toString());
            FieldFormulaImpl impl=new FieldFormulaImpl(FieldFormulaType.sqlMasterDetail, implNode,newColumn);
            newColumn.setDao(this);
            newColumn.setCreatorImpl(impl);
            newColumn.setSetterImpl(impl);
            newColumn.setGetterImpl(impl);
            extraColumns.add(newColumn);
            newColumn.setRelation(rr);
        }

        for (DBRelation rr : dbTable.getImportRelations()) {//this is detail
            String n=rr.getPkTable().getCodeStyleObjectName("do", "{TableName:class}");
            n=JBGenUtils.decapitalize(n);

            //verifier si on a une relation 1:1
            //une relation est 1:1 si TOUTES les clef du detail sont contenues dans la clef du Master

            boolean pkUnique=true;
            TreeSet<DBColumn> relationDetailfields=new TreeSet<DBColumn>(rr.getFkColumns());
            for (DBColumn detailPK : rr.getFkTable().getPkColumns()) {
                if(!relationDetailfields.contains(detailPK)){
                    pkUnique=false;
                    break;
                }
            }

            Cardinality card=Cardinality.MANY_TO_ONE;
            String cn = n;
            String ttype = rr.getPkTable().getDAOInfo().getFullDTOName();
            String cn2 = cn;
            int i = 1;
            while (cols.contains(cn2)) {
                DBColumn existingColumn = getColumnByFieldName(cn2, false);
                if (existingColumn == null) {
                    break;
                }
                i++;
                cn2 = cn + i;
            }
            DBColumn newColumn = new DBColumn(true,
                    "@" + entityName + "@",
                    cn,
                    Types.JAVA_OBJECT,
                    ttype,
                    0,
                    0,
                    9,
                    0);
            newColumn.setPopulationOrder(10);


            newColumn.setCardinality(card);
            //TODO see here is it is one to one or one to many !!!
            StringBuilder fks = new StringBuilder();
            ArrayList<String> requiredFields=new ArrayList<String>(rr.getFkColumns().size());
            for (DBColumn dBColumn : rr.getFkColumns()) {
                if (fks.length() > 0) {
                    fks.append(",");
                }
                fks.append(dBColumn.getBeanFieldName());
                requiredFields.add(dBColumn.getBeanFieldName());
            }
            newColumn.setRequiredFields(requiredFields.toArray(new String[requiredFields.size()]));

            StringBuilder pks = new StringBuilder();
            for (DBColumn dBColumn : rr.getPkColumns()) {
                if (pks.length() > 0) {
                    pks.append(",");
                }
                pks.append(dBColumn.getBeanFieldName());
            }
            ConfigNode implNode=new ConfigNode("sqlMasterDetailFieldExpression");
            implNode.setAttribute("detailPrimaryFields", pks.toString());
            implNode.setAttribute("detailTable", rr.getPkTable().getTableName());
            implNode.setAttribute("foreignFields", fks.toString());
            FieldFormulaImpl impl=new FieldFormulaImpl(FieldFormulaType.sqlDetailMaster, implNode,newColumn);
            newColumn.setDao(this);
            newColumn.setCreatorImpl(impl);
            newColumn.setSetterImpl(impl);
            newColumn.setGetterImpl(impl);
            extraColumns.add(newColumn);
            newColumn.setRelation(rr);
        }
    }

    public DBTableInfo[] getTables() {
        return dbtables.toArray(new DBTableInfo[dbtables.size()]);
    }

    public boolean isRealRegularColumn(String name) {
        for (DBTableInfo dbtable : dbtables) {
            for (DBColumn dbColumn : dbtable.getColumns()) {
                if (dbColumn.getColumnName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public DBColumn[] getColumns(boolean pk, boolean updatable, boolean extra) {
        return getColumns(pk, updatable, extra,false);
    }
    
    public DBColumn[] getColumns(boolean pk, boolean updatable, boolean extra,boolean populationOrder) {
        ArrayList<DBColumn> columns = new ArrayList<DBColumn>();
        DBTableInfo dbTable;
        DBColumn[] c;
        TreeSet<String> baseKnown = new TreeSet<String>();
        for (Iterator i1 = dbtables.iterator(); i1.hasNext();) {
            dbTable = (DBTableInfo) i1.next();
            DBColumn[] columns1 = dbTable.getColumns();
            for (int i = 0; i < columns1.length; i++) {
                DBColumn dbColumn = columns1[i];
                if (!baseKnown.add(dbColumn.getBeanFieldName())) {
                    throw new IllegalArgumentException("Field " + dbColumn.getBeanFieldName() + " already exists");
                }
            }
        }
        if (dbtables.size() > 0) {
            dbTable = (DBTableInfo) dbtables.get(0);
            if (pk) {
                c = dbTable.getPkColumns();
                for (int i2 = 0; i2 < c.length; i2++) {
                    if (c[i2] == null) {
                        getLog().error("impossible 1");
                    }
                    columns.add(c[i2]);
                }
            }
        }
        if (updatable) {
            for (Iterator i1 = dbtables.iterator(); i1.hasNext();) {
                dbTable = (DBTableInfo) i1.next();
                c = dbTable.getUpdatableColumns();
                for (int i2 = 0; i2 < c.length; i2++) {
                    if (c[i2] == null) {
                        getLog().error("impossible");
                    }
                    columns.add(c[i2]);
                }
            }
        }
        if (extra) {
            c = getExtraColumns();
            for (int i2 = 0; i2 < c.length; i2++) {
                if (c[i2] == null) {
                    getLog().error("impossible");
                }
                if (baseKnown.add(c[i2].getBeanFieldName())) {
                    columns.add(c[i2]);
                }
            }
        }
        DBColumn[] dbColumns = (DBColumn[]) columns.toArray(new DBColumn[columns.size()]);
        if(populationOrder){
            Arrays.sort(dbColumns,DBColumn.POPULATION_COMPARATOR);
        }
        return dbColumns;
    }

//    public DBColumn[] getColumns(){
//        if(columns==null){
//            columns=new ArrayList();
//            DBTable dbTable = (DBTable) dbtables.get(0);
//            DBColumn[] c=dbTable.getPkColumns();
//            for(int i2=0;i2<c.length;i2++){
//               columns.add(c[i2]);
//            }
//            for (Iterator i1 = dbtables.iterator(); i1.hasNext();) {
//                dbTable = (DBTable) i1.next();
//                c=dbTable.getUpdatableColumns();
//                for(int i2=0;i2<c.length;i2++){
//                   columns.add(c[i2]);
//                }
//            }
//        }
//        return (DBColumn[]) columns.toArray(new DBColumn[columns.size()]);
//    }
    public boolean isView() {
        DBTableInfo[] tables = getTables();
        for (int i = 0; i < tables.length; i++) {
            DBTableInfo table = tables[i];
            if (!"view".equalsIgnoreCase(table.getTableType())) {
                return false;
            }
        }
        return true;
    }

    public String getBOName() {
        String bo = getString("object-definition.do<name=\"" + getBeanName() + "\">", "bo", null, false);
        if (bo != null) {
            return bo;
        }
        ConfigNode[] bos = getNodes("object-definition.bo");
        for (ConfigNode boNode : bos) {
            ConfigNode child = boNode.getChild("do-list", false);
            if (child != null) {
                String value = child.getValue();
                if (value != null) {
                    if (getBeanName().matches(value)) {
                        return boNode.getName();
                    }
                }
            }
        }
        return getCodeStyleObjectName("bo", "{BeanName}BO");
    }

    public DBColumn[] getPrimaryKeys() {
        if (pkColumns != null) {
            return pkColumns;
        }
        DBColumn[] c = dbtables.size() == 0 ? new DBColumn[0] : ((DBTableInfo) dbtables.get(0)).getPkColumns();
        String primary = getString("object-definition.do<name=\"" + getBeanName() + "\">", "keys", null, false);
        if (primary != null) {
            if (c.length != 0) {
                getLog().warning(entityName + " : Primary Fields overridden.");
            }
            for (Iterator i = dbtables.iterator(); i.hasNext();) {
                DBTableInfo dbTable = (DBTableInfo) i.next();
                DBColumn[] cols = dbTable.getColumns();
                for (int j = 0; j < cols.length; j++) {
                    DBColumn col = cols[j];
                    col.setPkPosition(-1);
                }
            }
            ArrayList list = new ArrayList();
            int pos = 0;
            for (StringTokenizer stringTokenizer = new StringTokenizer(primary, " ,;:"); stringTokenizer.hasMoreTokens();) {
                String s = stringTokenizer.nextToken();
                DBColumn col = getColumnByFieldName(s, true);
                col.setPkPosition(pos++);
                list.add(col);
            }
            pkColumns = (DBColumn[]) list.toArray(new DBColumn[list.size()]);
        } else {
            pkColumns = c;
        }
        return pkColumns;
    }

    public DBColumn[] getUpdatableColumns() {
        if (updatableColumns == null) {
            updatableColumns = new ArrayList();
            for (Iterator i1 = dbtables.iterator(); i1.hasNext();) {
                DBTableInfo dbTable = (DBTableInfo) i1.next();
                DBColumn[] c = dbTable.getUpdatableColumns();
                for (int i2 = 0; i2 < c.length; i2++) {
                    updatableColumns.add(c[i2]);
                }
            }
        }
        return (DBColumn[]) updatableColumns.toArray(new DBColumn[updatableColumns.size()]);
    }

    public DBColumn[] getExtraColumns() {
        if (extraColumns == null) {
            return new DBColumn[0];
        }
        return (DBColumn[]) extraColumns.toArray(new DBColumn[extraColumns.size()]);
    }

    public int size() {
        return dbtables.size();
    }

    public String getEntityName() {
        return entityName;
    }

    public String toString() {
        if (dbtables.size() > 1 || (dbtables.size() == 1 && dbtables.size() > 0 && !entityName.equals(((DBTableInfo) dbtables.get(0)).getTableName()))) {
            return entityName + "(" + dbtables + ")";
        }
        return entityName;
    }

    public String getBeanName() {
        String c = (String) cache.get("getBeanName");
        if (c == null) {
            c = getCodeStyleObjectName("do", "{TableName:class}");
            cache.put("getBeanName", c);
        }
        return c;
    }

    public String getFullDTOName() {
        return getDataPackage() + "." + getDTOName();
    }

    public String getFullPropertyListName() {
        return getDataPackage() + "." + getPropertyListName();
    }

    public String getFullDataKeyName() {
        return getDataPackage() + "." + getDataKeyName();
    }

    public String getFullDataFilterName() {
        return getDAOPackage() + "." + getDataFilterName();
    }

    public String getDTOMetaDataName() {
        return getDTOName()+"MetaData";
    }
    
    public String getDTOName() {
        String c = (String) cache.get("getDataContentName");
        if (c == null) {
            try {
                c = getCodeStyleObjectName("dto-data", "{TableName:class}");
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
            }
            cache.put("getDataContentName", c);
        }
        return c;
    }

    public String getPropertyListName() {
        String c = (String) cache.get("getPropertyListName");
        if (c == null) {
            try {
                c = getCodeStyleObjectName("dto-property-list", "{TableName:class}PropertyList");
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
            }
            cache.put("getPropertyListName", c);
        }
        return c;
    }

    public String getFullOrderListName() {
        return getDataPackage() + "." + getOrderListName();
    }

    public String getOrderListName() {
        String c = (String) cache.get("getOrderListName");
        if (c == null) {
            try {
                c = getCodeStyleObjectName("dto-order-list", "{TableName:class}OrderList");
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
            }
            cache.put("getOrderListName", c);
        }
        return c;
    }

    public String getDataKeyName() {
        String c = (String) cache.get("getDataKeyName");
        if (c == null) {
            try {
                c = getCodeStyleObjectName("dto-key", "{TableName:class}Key");
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
            }
            cache.put("getDataKeyName", c);
        }
        return c;
    }

    public String getDataFilterName() {
        String c = (String) cache.get("getDataFilterName");
        if (c == null) {
            try {
                c = getCodeStyleObjectName("dto-filter", "{TableName:class}Filter");
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
            }
            cache.put("getDataFilterName", c);
        }
        return c;
    }

    public String getDOBeanName() {
        String c = (String) cache.get("getDOBeanName");
        if (c == null) {
            try {
                c = getCodeStyleObjectName("entity-bean", "{TableName:class}Bean");
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
            }
            cache.put("getDOBeanName", c);
        }
        return c;
    }

    public String getDAONameVarName() {
        return "_"+getDAOName()+"_";
    }

    public String getDAOName() {
        try {
            return getCodeStyleObjectName("dao", "{TableName:class}DAO");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getEntityRemoteName() {
        try {
            return getCodeStyleObjectName("entity-remote", "{TableName:class}Remote");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getEntityHomeNameVarName() {
       return "_"+getEntityHomeName()+"_";
    }

    public String getEntityHomeName() {
        try {
            return getCodeStyleObjectName("entity-home", "{TableName:class}Home");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getEntityLocalHomeName() {
        try {
            return getCodeStyleObjectName("entity-local-home", "{TableName:class}LocalHome");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getEntityLocalName() {
        try {
            return getCodeStyleObjectName("entity-local", "{TableName:class}Local");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getFullEntityHomeName() {
        return getEntityPackage() + "." + getEntityHomeName();
    }

    public String getFullDAOName() {
        return getDAOPackage() + "." + getDAOName();
    }

    public String getFullEntityRemoteName() {
        return getEntityPackage() + "." + getEntityRemoteName();
    }

    public String getFullEntityBeanName() {
        return getEntityPackage() + "." + getDOBeanName();
    }

    public String getEntityPackage() {
        try {
            return getCodeStyleObjectName("entity-package", "{ModulePackage}.server.ejb.{TableName:package}");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getDataPackage() {
        try {
            return getCodeStyleObjectName("dto-package", "{ModulePackage}.data.{TableName:package}");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getDAOPackage() {
        try {
            return getCodeStyleObjectName("dao-package", "{ModulePackage}.dao.{TableName:package}");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getTablePrefix() {
        try {
            return getCodeStyleObjectName("table-prefix", "");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(getBeanName() + " : " + e.getMessage());
        }
    }

    public String getTitleFieldName() {
        String t = (String) cache.get("getTitleFieldName");
        if (t == null) {
            t = getPrimaryKeys().length == 0 ? null : getString("object-definition.do<name=\"" + getBeanName() + "\">", "titleField", getPrimaryKeys()[0].getBeanFieldName(), false);
            cache.put("getTitleFieldName", t == null ? "" : t);
        } else {
            if (t.length() == 0) {
                return null;
            }
        }
        return t;
    }

    public OrdreInfo[] getOrder() {
        OrdreInfo[] t = (OrdreInfo[]) cache.get("getOrder");
        if (t == null) {
            String f = getString("object-definition.do<name=\"" + getBeanName() + "\">", "order", null, false);
            if (f == null) {
                t = new OrdreInfo[0];
            } else {
                ArrayList v = new ArrayList();
                for (StringTokenizer tk = new StringTokenizer(f, ","); tk.hasMoreTokens();) {
                    String s = tk.nextToken();
                    StringTokenizer tk2 = new StringTokenizer(s.trim(), " ");
                    tk2.hasMoreTokens();
                    String fn = tk2.nextToken();
                    boolean b = true;
                    if (tk2.hasMoreTokens()) {
                        b = "asc".equalsIgnoreCase(tk2.nextToken());
                    }
                    v.add(new OrdreInfo(fn, b));
                }
                t = (OrdreInfo[]) v.toArray(new OrdreInfo[v.size()]);
            }
            cache.put("getOrder", t);
        }
        return t.length == 0 ? null : t;
    }

    public DataTypeConverterFactory getFieldConverterFactory(DBColumn dbColumn) {
        String f = getFieldPatternValue(dbColumn, "converter", null);
        if (f == null) {
            return null;
        }
        return dbColumn.getDAOInfo().getProjectInfo().getJbgen().getDataTypeConverterFactoryManager().getFactory(f);
    }

    public DataType getFieldDataType(DBColumn dbColumn, DataType sqlType) {
        String f = getFieldPatternValue(dbColumn, "datatype", null);
        if (f == null) {
            return null;
        }
        JavaEvaluator javaEvaluator = new JavaEvaluator(getLog());
        JavaClassSource c = new JavaClassSource();
        c.addImport("org.vpc.neormf.commons.types.*");
        c.addImport("org.vpc.neormf.commons.types.converters.*");

        if (sqlType != null) {
            c.addField(new JavaField("SQL_TYPE", sqlType.getClass().getName(), null, "public static final", JavaUtils.getImportedCode(sqlType)));
        }
        javaEvaluator.setExtraClass(c);

        return (DataType) JBGenUtils.castTo(javaEvaluator.evaluate(f, getProjectInfo().getClasspath()), DataType.class);
    }

    public DataType getFieldSqlDataType(DBColumn dbColumn, DataType defaultType) {
        ConfigNode children = getConfig().getNode("object-definition.do<name=\"" + getBeanName() + "\">.field<name=\"" + dbColumn.getBeanFieldName() + "\">.definition", false);
        if (children == null) {
            return defaultType;
        } else {
            ConfigNode col = children;
            return dbColumn.getDataTypeForSQLType(
                    col.getAttribute("nullable", "").length() > 0 ? new Boolean(col.getAttribute("nullable")).booleanValue() : dbColumn.isNullable(),
                    col.getAttribute("typename", "").length() > 0 ? (col.getAttribute("typename").startsWith("OBJECT:") ? Types.JAVA_OBJECT : JBGenUtils.toSQLType(col.getAttribute("typename"))) : dbColumn.getSqlType(),
                    col.getAttribute("typename", "").length() > 0 ? (col.getAttribute("typename").startsWith("OBJECT:") ? col.getAttribute("typename").substring("OBJECT:".length()) : col.getAttribute("typename")) : dbColumn.getTypeName(),
                    col.getAttribute("size", "").length() > 0 ? Integer.parseInt(col.getAttribute("size")) : dbColumn.getColumnSize(),
                    col.getAttribute("bufferLength", "").length() > 0 ? Integer.parseInt(col.getAttribute("bufferLength")) : dbColumn.getColumnBufferLength(),
                    col.getAttribute("decimalDigits", "").length() > 0 ? Integer.parseInt(col.getAttribute("decimalDigits")) : dbColumn.getColumnDecimalDigits(),
                    col.getAttribute("numPrecRadix", "").length() > 0 ? Integer.parseInt(col.getAttribute("numPrecRadix")) : dbColumn.getColumnNumRecRadix());
        }
    }

    public void checkGenerateFilter(String generationKeyName) throws NoSuchElementException {
//        String prj = getModuleInfo().getProject().getName();
//        ConfigNode[] includes = getConfig().getNodes("target." + prj + "." + generationKeyName + ".include", false);
//        ConfigNode[] excludes = getConfig().getNodes("target." + prj + "." + generationKeyName + ".include", false);
//        boolean included=includes.length==0;
//        for (int i = 0; i < includes.length; i++) {
//            ConfigNode include = includes[i];
//            if(include.accept(ConfigFilter.valueOf("include<name=\""+table.getTableName().toUpperCase()+"\",type=\""+table.getTableType().toUpperCase()+"\">",false)[0])){
//              included=true;
//                break;
//            }
//        }
//        boolean excluded=false;
//        for (int i = 0; i < excludes.length; i++) {
//            ConfigNode exclude = excludes[i];
//            if(exclude.accept(ConfigFilter.valueOf("exclude<name=\""+table.getTableName().toUpperCase()+"\",type=\""+table.getTableType().toUpperCase()+"\">",false)[0])){
//              excluded=true;
//                break;
//            }
//        }
//        if(included && !excluded){
//            moduleCodeStyle.addTable(table);
//        }else{
//            System.out.println(table.getTableName()+" skipped : included="+included+" ; excluded="+excluded);
//        }
//
//        String f = getConfig().getString("target."+ prj +"."+generationKeyName, null, true, "Table names filter to generate '" + generationKeyName + "', use +* for all, example +TAB* -TABSAMPPE", true, getVars());
//        try {
//            JBGenUtils.parseBoolean(f);
//        } catch (IllegalArgumentException e) {
//            StringListFilter filter = new StringListFilter(f);
//            HashSet possibleValues = new HashSet();
//            EntityInfo[] ei = getModuleInfo().getAllEntities();
//            for (int i = 0; i < ei.length; i++) {
//                possibleValues.add(ei[i].getBeanName());
//            }
//            try {
//                filter.checkValuesIn(possibleValues);
//            } catch (NoSuchElementException e2) {
//                throw new NoSuchElementException("Bad Filter 'generate." + generationKeyName + "' : " + e2.getMessage());
//            }
//        }
    }

    public static class OrdreInfo {

        public String fieldName;
        public boolean isAsc;

        public OrdreInfo(String fieldName, boolean asc) {
            this.fieldName = fieldName;
            isAsc = asc;
        }
    }

    public ConfigNode[] getFieldConfig(String name) {
        return getConfig().getNodes("object-definition.do<name=\"" + getBeanName() + "\">.field<name=\"" + name + "\">", false);
    }

    public FieldDefinition[] getExtraFieldDefinitions() {
        ConfigNode[] children = getConfig().getNodes("object-definition.do<name=\"" + getBeanName() + "\">.field", false);
        ArrayList<FieldDefinition> al = new ArrayList<FieldDefinition>();
        for (int i = 0; i < children.length; i++) {
            ConfigNode col = children[i];
            FieldDefinition definition = new FieldDefinition(col, getVars());
            if (!isRealRegularColumn(definition.getColumnName())) {
                if (definition.getKind().equals(DAOFieldKind.REGULAR)) {
                    definition.setKind(DAOFieldKind.LIVE_FORMULA);
                }
                al.add(definition);
            } else {
                //existing column
                switch (definition.getKind()) {
                    case LIVE_FORMULA: {
                        throw new IllegalArgumentException("Persistent Column " + definition.getColumnName() + " is declared as live liveFormula");
                    }
                }
            }
        }
        return al.toArray(new FieldDefinition[al.size()]);
    }

    public static class FieldDefinition {

        String columnName;
        DAOFieldKind kind;
        int type;
        String typeName;
        int columnsSize;
        int bufferLength;
        int decimalDigits;
        int numPrecRadix;
        boolean nullable;

        public FieldDefinition(ConfigNode col, Properties vars) {
            this(
                    col.getAttribute("name", null, true, "column name", true, vars),
                    DAOFieldKind.valueOf(col.getAttribute("kind", "REGULAR")),
                    col.getAttribute("typename", "").length() > 0 ? (col.getAttribute("typename").startsWith("OBJECT:") ? Types.JAVA_OBJECT : JBGenUtils.toSQLType(col.getAttribute("typename"))) : Types.JAVA_OBJECT,
                    col.getAttribute("nullable", "").length() > 0 ? new Boolean(col.getAttribute("nullable")).booleanValue() : true,
                    col.getAttribute("size", "").length() > 0 ? Integer.parseInt(col.getAttribute("size")) : -1,
                    col.getAttribute("typename", "").length() > 0 ? (col.getAttribute("typename").startsWith("OBJECT:") ? col.getAttribute("typename").substring("OBJECT:".length()) : col.getAttribute("typename")) : null,
                    col.getAttribute("bufferLength", "").length() > 0 ? Integer.parseInt(col.getAttribute("bufferLength")) : -1,
                    col.getAttribute("decimalDigits", "").length() > 0 ? Integer.parseInt(col.getAttribute("decimalDigits")) : -1,
                    col.getAttribute("numPrecRadix", "").length() > 0 ? Integer.parseInt(col.getAttribute("numPrecRadix")) : -1);
        }

        public FieldDefinition(String columnName, DAOFieldKind kind, int type, boolean nullable, int columnsSize, String typeName, int bufferLength, int decimalDigits, int numPrecRadix) {
            this.columnName = columnName;
            this.type = type;
            this.kind = kind;
            this.typeName = typeName;
            this.columnsSize = columnsSize;
            this.bufferLength = bufferLength;
            this.decimalDigits = decimalDigits;
            this.numPrecRadix = numPrecRadix;
            this.nullable = nullable;
            switch (kind) {
                case LIVE_FORMULA:
                case REGULAR: {
                    break;
                }
                default: {
                    throw new IllegalArgumentException("kind not yet supported");
                }
            }
        }

        public DAOFieldKind getKind() {
            return kind;
        }

        public void setKind(DAOFieldKind kind) {
            this.kind = kind;
        }

        public boolean isNullable() {
            return nullable;
        }

        public String getColumnName() {
            return columnName;
        }

        public int getType() {
            return type;
        }

        public String getTypeName() {
            return typeName;
        }

        public int getColumnsSize() {
            return columnsSize;
        }

        public int getBufferLength() {
            return bufferLength;
        }

        public int getDecimalDigits() {
            return decimalDigits;
        }

        public int getNumPrecRadix() {
            return numPrecRadix;
        }
    }

    public FieldFormulaImpl getFieldImpl(DBColumn column, String type) {
        ConfigNode[] fieldNodes = getFieldNodes(column, "*-expression<when=\"" + type + "|always\">");
        if (fieldNodes.length > 2) {
            throw new IllegalArgumentException("too many expressions");
        }
        try {
            ConfigNode configNode = fieldNodes.length == 0 ? null : fieldNodes[0];
            if (column.getTable() == null && configNode == null) {
                configNode = new ConfigNode(FieldFormulaType.code.toString());
                configNode.setValue("return null;");
            }
            return new FieldFormulaImpl(configNode,column);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(getBeanName() + "." + column.getBeanFieldName() + " : " + e.getMessage());
        }
    }

    public FieldFormulaImpl getGetterImpl(DBColumn column) {
        return getFieldImpl(column, "get");
    }

    public FieldFormulaImpl getCreatorImpl(DBColumn column) {
        return getFieldImpl(column, "create");
    }

    public FieldFormulaImpl getSetterImpl(DBColumn column) {
        return getFieldImpl(column, "set");
    }

    public Properties getVars() {
        addVar("TableName", getEntityName());
//        addVar("DataSourceName", getModuleInfo().getDataSourceName());
        if (size() > 0) {
            addVar("TableType", getTables()[0].getTableType());
        }
        if (cache.get("getBeanName") != null) {
            addVar("BeanName", getBeanName());
        }
        if (getProjectInfo().getTarget() != null) {
            addVar("ModulePackage", getProjectInfo().getModulePackage());
            addVar("TargetMetaInfFolder", getProjectInfo().getTargetMetaInfFolder());
//            addVar("TargetMetaInfFolder_data", getModuleInfo().getTargetSrcFolder(J2eeTarget.MODULE_DTO));
//            addVar("TargetMetaInfFolder_key", getModuleInfo().getTargetSrcFolder("key"));
        }

        return super.getVars();
    }

    public DBColumn getColumnByFieldName(String name, boolean required) {
        DBColumn[] cols = getColumns(true, true, true,false);
        for (int i = 0; i < cols.length; i++) {
            if (cols[i].getBeanFieldName().equals(name)) {
                return cols[i];
            }
        }
        if (required) {
            StringBuilder sb = new StringBuilder("Field ").append(name).append(" not found in ").append(getBeanName()).append(". Try one of the following \n");
            for (int i = 0; i < cols.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(cols[i].getBeanFieldName());
            }
            throw new NoSuchElementException(sb.toString());
        }
        return null;
    }

    public DBColumn getColumnByFieldVar(String name, boolean required) {
        DBColumn[] cols = getColumns(true, true, true,false);
        for (int i = 0; i < cols.length; i++) {
            if (cols[i].getBeanFieldVar().equals(name)) {
                return cols[i];
            }
        }
        if (required) {
            StringBuilder sb = new StringBuilder("Field ").append(name).append(" not found in ").append(getBeanName()).append(". Try one of the following \n");
            for (int i = 0; i < cols.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(cols[i].getBeanFieldVar());
            }
            throw new NoSuchElementException(sb.toString());
        }
        return null;
    }

    public String getMethodGetDataExtraParamNamesString(boolean addStartupComma) {
        JavaParam[] p = getMethodGetDataExtraParams();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length; i++) {
            if (i > 0 || addStartupComma) {
                sb.append(", ");
            }
            sb.append(p[i].getName());
        }
        return sb.toString();
    }

    public JavaParam[] getMethodGetDataExtraParams() {
        String extra = getString("object-definition.do<name=\"" + getBeanName() + "\">.method<name=getData>", "extra-params", null, false);
        ArrayList list = new ArrayList();
        if (extra != null) {
            StringTokenizer st = new StringTokenizer(extra, ",");
            while (st.hasMoreTokens()) {
                String paramString = st.nextToken().trim();
                StringTokenizer st2 = new StringTokenizer(paramString);
                st2.hasMoreTokens();
                String type = st2.nextToken();
                st2.hasMoreTokens();
                String name = st2.nextToken();
                list.add(new JavaParam(name, type, null, true));
            }
        }
        return (JavaParam[]) list.toArray(new JavaParam[list.size()]);
    }

    public CSharpParam[] getMethodGetDataExtraCSharpParams() {
        String extra = getString("object-definition.do<name=\"" + getBeanName() + "\">.method<name=getData>", "extra-params", null, false);
        ArrayList list = new ArrayList();
        if (extra != null) {
            StringTokenizer st = new StringTokenizer(extra, ",");
            while (st.hasMoreTokens()) {
                String paramString = st.nextToken().trim();
                StringTokenizer st2 = new StringTokenizer(paramString);
                st2.hasMoreTokens();
                String type = st2.nextToken();
                st2.hasMoreTokens();
                String name = st2.nextToken();
                list.add(new CSharpParam(name, type, null, true));
            }
        }
        return (CSharpParam[]) list.toArray(new CSharpParam[list.size()]);
    }

    public String getMethodFindAllExtraParamNamesString(boolean addTrailingComma) {
        JavaParam[] p = getMethodFindExtraParams();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length; i++) {
            if (i > 0 || addTrailingComma) {
                sb.append(", ");
            }
            sb.append(p[i].getName());
        }
        return sb.toString();
    }

    public JavaParam[] getMethodFindDataExtraParams() {
        JavaParam[] p1 = getMethodFindExtraParams();
        JavaParam[] p2 = getMethodGetDataExtraParams();
        ArrayList list = new ArrayList(p1.length + p2.length);
        for (int i = 0; i < p1.length; i++) {
            list.add(p1[i]);
        }
        for (int i = 0; i < p2.length; i++) {
            if (!list.contains(p2[i])) {
                list.add(p2[i]);
            }
        }
        return (JavaParam[]) list.toArray(new JavaParam[list.size()]);
    }

    public JavaParam[] getMethodFindExtraParams() {
        String extra = getString("object-definition.do<name=\"" + getBeanName() + "\">.method<name=findAll>", "extra-params", null, false);
        ArrayList list = new ArrayList();
        if (extra != null) {
            StringTokenizer st = new StringTokenizer(extra, ",");
            while (st.hasMoreTokens()) {
                String paramString = st.nextToken().trim();
                StringTokenizer st2 = new StringTokenizer(paramString);
                st2.hasMoreTokens();
                String type = st2.nextToken();
                st2.hasMoreTokens();
                String name = st2.nextToken();
                list.add(new JavaParam(name, type, null, true));
            }
        }
        return (JavaParam[]) list.toArray(new JavaParam[list.size()]);
    }

    public JavaParam[] getMethodCreateDataExtraParams() {
        String extra = getString("object-definition.do<name=\"" + getBeanName() + "\">.method<name=createData>", "extra-params", null, false);
        ArrayList list = new ArrayList();
        if (extra != null) {
            StringTokenizer st = new StringTokenizer(extra, ",");
            while (st.hasMoreTokens()) {
                String paramString = st.nextToken().trim();
                StringTokenizer st2 = new StringTokenizer(paramString);
                st2.hasMoreTokens();
                String type = st2.nextToken();
                st2.hasMoreTokens();
                String name = st2.nextToken();
                list.add(new JavaParam(name, type, null, true));
            }
        }
        return (JavaParam[]) list.toArray(new JavaParam[list.size()]);
    }

    public boolean hasKey() {
        return getColumns(true, false, false,false).length > 0;
    }

    public boolean isUpdatable() {
        DBTableInfo[] tables = getTables();
        for (int i = 0; i < tables.length; i++) {
            if (!tables[i].getTableType().equalsIgnoreCase("TABLE") || tables[i].getPkColumns().length==0) {
                return false;
            }
        }
        return true;
    }

    public boolean doGenerateData() {
        return doGenerateBean(J2eeTarget.MODULE_DTO);
    }

    public boolean doGenerateCmpLocalEntity() {
        return doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-cmp-local");
    }

    public boolean doGenerateCmpRemoteEntity() {
        return doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-cmp-remote");
    }

    public boolean doGenerateBmpEntity() {
        return doGenerateBmpLocalEntity() || doGenerateBmpRemoteEntity();
    }

    public boolean doGenerateCmpEntity() {
        return doGenerateCmpLocalEntity() || doGenerateCmpRemoteEntity();
    }

    public boolean doGenerateBmpLocalEntity() {
        return doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-bmp-local");
    }

    public boolean doGenerateBmpRemoteEntity() {
        return doGenerateBean(J2eeTarget.MODULE_EJB + ".entity-bmp-remote");
    }

    public boolean doGenerateDAO() {
        return doGenerateBean(J2eeTarget.MODULE_DAO);
    }

    public boolean doGenerateEntity() {
        return doGenerateBmpLocalEntity() || doGenerateBmpRemoteEntity() || doGenerateCmpLocalEntity() || doGenerateCmpRemoteEntity();
    }

    public BOInfo getBOInfo(boolean autoCreate) {
        if (sessionInfo == null && autoCreate) {
            sessionInfo = getProjectInfo().getBOInfo(getBOName(), autoCreate);
            sessionInfo.add(this);
        }
        return sessionInfo;
    }

    public BOInfo getBOInfo() {
        return getBOInfo(true);
    }

//    public String getPreUpdateUserCode(){
//        return getConfig().getProperty("data.preUpdate.userCode");
//    }
//
//    public String getPostUpdateUserCode(){
//        return getConfig().getProperty("data.postUpdate.userCode");
//    }
//
//
//    public String getPreInsertUserCode(){
//        return getConfig().getProperty("data.preInsert.userCode");
//    }
//
//
//    public String getPostInsertUserCode(){
//        return getConfig().getProperty("data.postInsert.userCode");
//    }
//
//    public String getPreUpdateFieldUserCode(DBColumn dbColumn){
//        return getConfig().getProperty("field."+dbColumn.getBeanFieldName()+".preUpdate.userCode");
//    }
//
//    public String getPostUpdateFieldUserCode(DBColumn dbColumn){
//        return getConfig().getProperty("field."+dbColumn.getBeanFieldName()+".postUpdate.userCode");
//    }
//
//    public String getPreSelectUserCode(){
//        return getConfig().getProperty("data.preSelect.userCode");
//    }
//
//    public String getPostSelectUserCode(){
//        return getConfig().getProperty("data.postSelect.userCode");
//    }
    public String getDefautlSequenceName() {
        return getCodeStyleObjectName("table-sequence", "{TableName:upper}_SEQ");
    }

    public String getTableSequenceName() {
        return getString("object-definition.table<name=\"" + getTables() + "\">", "sequence", null, false);
    }

    public String getSequenceName() {
        return getString("object-definition.do<name=\"" + getBeanName() + "\">", "sequence", null, false);
    }

    public boolean isAutoDentity() throws SQLException {
        String seq = getSequenceName();
        if (seq != null) {
            return true;
        }
        for (Iterator i = dbtables.iterator(); i.hasNext();) {
            DBTableInfo dbTable = (DBTableInfo) i.next();
            if (dbTable.isAutoIdentity()) {
                return true;
            }
        }
        return false;
//        if (getModuleInfo().isDBOracle()) {
//            //TODO
//            throw new RuntimeException("Not Implemented");
//        }else if(getModuleInfo().isDBSqlServer()){
//            return false;
//        }
//        return false;
    }

    public boolean isRequiredOnInsert(DBColumn dbColumn, boolean defaultValue) {
        return JBGenUtils.getBoolean((String) getFieldPatternAttribute(dbColumn, null, "isRequiredOnInsert", null), defaultValue);
    }

    public boolean isForbiddenOnInsert(DBColumn dbColumn, boolean defaultValue) {
        return JBGenUtils.getBoolean((String) getFieldPatternAttribute(dbColumn, null, "isForbiddenOnInsert", null), defaultValue);
    }

    public boolean isForbiddenOnUpdate(DBColumn dbColumn, boolean defaultValue) {
        return JBGenUtils.getBoolean((String) getFieldPatternAttribute(dbColumn, null, "isForbiddenOnUpdate", null), defaultValue);
    }

    public boolean isForbiddenOnSearch(DBColumn dbColumn, boolean defaultValue) {
        return JBGenUtils.getBoolean((String) getFieldPatternAttribute(dbColumn, null, "isForbiddenOnSearch", null), defaultValue);
    }

    public String getFieldTitle(DBColumn dbColumn) {
        ConfigNode[] nodes = getFieldNodes(dbColumn, null);
        String d = JBGenUtils.toEnglishSpelling(dbColumn.getBeanFieldName(), true);
        if (nodes.length == 0) {
            return d;
        }
        return nodes[0].getAttribute("title", d, false, null, true, null);
    }

    public String getFieldInitialValue(DBColumn dbColumn) {
        Class c = dbColumn.getBusinessDataType().toJavaType();
        return (String) getFieldPatternValue(dbColumn, "initialValue",
                c.equals(Integer.TYPE) ? "0" : c.equals(Long.TYPE) ? "0L" : c.equals(Double.TYPE) ? "0.0" : c.equals(Float.TYPE) ? "0F" : c.equals(Boolean.TYPE) ? "false" : "null");
    }

    private ConfigNode[] getFieldNodes(DBColumn dbColumn, String suffix) {
        String p =
                "<" + "name=" + dbColumn.getBeanFieldName() + ",upperName=" + dbColumn.getBeanFieldName().toUpperCase() + ",cstName=" + dbColumn.getBeanFieldConstant() + ",type=" + JBGenUtils.getClassName(dbColumn.getBusinessDataType().toJavaType()) + ",sqlType=" + JBGenUtils.getClassName(dbColumn.getSqlDataType().toJavaType()) + ",bean=" + dbColumn.getDAOInfo().getBeanName() + ",table=" + ("\"" + ((dbColumn.getTable() == null) ? "" : dbColumn.getTable().getTableName()) + "\"") + ">";
        String pattern = "object-definition.do<name=\"" + getBeanName() + "\">.field" + p + (suffix == null ? "" : ("." + suffix));
        ConfigNode[] c = getConfig().getNodes(pattern, true);
        return c;

    }

    private String getFieldPatternAttribute(DBColumn dbColumn, String suffix, String attribute, String defaultValue) {
        ConfigNode[] c = getFieldNodes(dbColumn, suffix);
        if (c.length == 0) {
            return defaultValue;
        }
        return c[0].getAttribute(attribute, defaultValue);
    }

    private String getFieldPatternValue(DBColumn dbColumn, String suffix, String defaultValue) {
        ConfigNode[] c = getFieldNodes(dbColumn, suffix);
        if (c.length == 0) {
            return defaultValue;
        }
        return c[0].getValue();
    }

    public static class BeanCache {

        private String timeoutExpression;
        private String maxExpression;

        public BeanCache(String timeoutExpression, String maxExpresion) {
            this.timeoutExpression = timeoutExpression;
            this.maxExpression = maxExpresion;
        }

        public String getTimeoutExpression() {
            return timeoutExpression;
        }

        public String getMaxExpression() {
            return maxExpression;
        }
    }

    //TODO add in frontend
    public BeanCache getDAOCache() {
        ConfigNode[] configNodes = getPattern(null, "cache");
        if (configNodes.length == 0) {
            return null;
        }
        return new BeanCache(
                configNodes[0].getAttribute("timeout", null, true, null, true, null),
                configNodes[0].getAttribute("max", null, true, null, true, null));
//        String cache = (String) getPatternValue(null, "cache", null);
//        boolean isCached = (cache != null && cache.length() > 0);
//        ParamsList cacheParams = new ParamsList(cache);
//        cacheParams.setAcceptMethod(false);
//        cacheParams.setSeparators(",;");
//        if (isCached && (cacheParams.length() != 2 || cacheParams.getParam("max") == null || cacheParams.getParam("timeout") == null)) {
//            throw new IllegalArgumentException("Expected enity." + getBeanName() + ".dao.cache=timeout=1000;max=100");
//        }
//        return !isCached ? null : new BeanCache(cacheParams.getParam("timeout").getValue(), cacheParams.getParam("max").getValue());
    }

    private String getPatternValue(String prefix, String suffix, String defaultValue) {
        String p = "entity." + ((prefix != null && prefix.length() > 0) ? (prefix + ".") : "") +
                "<" + "name=" + this.getBeanName() + ",upperName=" + this.getBeanName().toUpperCase() + ">" +
                ((suffix != null && suffix.length() > 0) ? ("." + suffix) : "");

        ConfigNode[] c = getConfig().getNodes(p, true);
        if (c.length == 0) {
            return defaultValue;
        }
        return c[0].getValue();
    }

    private ConfigNode[] getPattern(String prefix, String suffix) {
        String p = "object-definition." + ((prefix != null && prefix.length() > 0) ? (prefix + ".") : "") +
                "do<" + "name=" + this.getBeanName() + ",upperName=" + this.getBeanName().toUpperCase() + ">" +
                ((suffix != null && suffix.length() > 0) ? ("." + suffix) : "");

        ConfigNode[] c = getConfig().getNodes(p, true);
        return c;
    }

    public String getSearchCriteriaPattern(DBColumn dbColumn) {
        return getFieldPatternValue(dbColumn, "search", "{columnName} = {columnValue}");
    }

    public String getLogCatagory() {
        return getLogOption("entity-category");
    }

    public boolean doOptimizeGetAll() {
        return isJBGenOptionEnabled("option-entity-optimize-getAll", false);
    }

    public boolean removeOrderListForGetAllQBE() {
        //TODO no more supported
        return getConfig().getBoolean("entity.removeOrderListForGetAllQBE", true);
    }

    public String getUserCodeForDO(String key) {
        return getJavaUserCode(null, null, "object-definition.do<name=" + getBeanName() + ">.userCode." + key);
    }

    public String getUserCodeForField(String field, String key) {
        return getJavaUserCode(null, null, "object-definition.do<name=" + getBeanName() + ">.field<name=" + field + ">.userCode." + key);
    }

    public boolean isCheckQueries() {
        return isJBGenOptionEnabled("option-check-queries", false);
    }

    public boolean isAcceptColumnNamesInOrderClause() {
        return isJBGenOptionEnabled("option-accept-column-names", false);
    }

    public String[] getEntityReferences() {
        return JBGenUtils.split(getString("entity.references", null, false), ",", true);
    }

    public String[] getEjbReferences() {
        return getProjectInfo().getEjbReferences(getBeanName());
    }
//
//    public boolean doGenerateTables(String generationKeyName) {
//        String f = null;
//        try {
//            f = getConfig().getString("generate." + generationKeyName, null, true, "Table names filter to generate '" + generationKeyName + "', use +* for all, example +TAB* -TABSAMPPE", true, getVars());
//        } catch (NoSuchElementException e) {
//            throw new IllegalArgumentException(getBeanName() + " : " + e.getMessage());
//        }
//        try {
//            return JBGenUtils.parseBoolean(f);
//        } catch (IllegalArgumentException e) {
//            StringListFilter filter = null;
//            try {
//                filter = new StringListFilter(f);
//            } catch (Exception e1) {
//                throw new IllegalArgumentException("generate." + generationKeyName + " is incorrect for " + getBeanName() + " : " + e1.getMessage());
//            }
//            for (int i = 0; i < getTables().length; i++) {
//                DBTable dbTable = getTables()[i];
//                if (filter.accept(dbTable.getTableName())) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
}

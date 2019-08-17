package org.vpc.neormf.jbgen.java.generators.ejb;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;
import org.vpc.neormf.jbgen.util.JBGenUtils;

import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 5 mai 2004 Time: 20:11:02
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class JavaSourceCodeFieldsSwitcher {
    private static final String PRIVATE_COL_TYPE_FIELD = "field";
    private static final String PRIVATE_COL_TYPE_COLUMN = "column";

    public static final String ITERATE_COLLECTION =
            "String selectedFieldName=(String)i.next();"
            ;
    public static final String ITERATE_MAP =
            "String selectedFieldName=(String)selectedEntry.getKey();" +
            "Object selectedFieldValue=(Object)selectedEntry.getValue();"
            ;
    private String iteratorExpression;
    private boolean autoBreak = true;
    private boolean acceptFieldColumnNames = false;
    private String iterateTypeCode;
    private String switchDefaultCode;
    private DBColumn[] columns;

    protected JavaSourceCodeFieldsSwitcher(String collectionExpression, String iterateTypeCode, boolean autoBreak, DBColumn[] columns) {
        this.iteratorExpression = collectionExpression;
        this.iterateTypeCode = iterateTypeCode;
        this.autoBreak = autoBreak;
        this.columns = columns;
    }

    public abstract String getFieldNameCode(DBColumn dbColumn);

    public String getFieldColumnCode(DBColumn dbColumn) {
        return null;
    }

    public String getCode(int indent) {
        return JBGenUtils.indent(getCode(), indent, false, true);
    }

    public void setIterateTypeCode(String iterateTypeCode) {
        this.iterateTypeCode = iterateTypeCode;
    }

    public String getFieldNameMapEntryCastCode() {
        return iterateTypeCode;
    }

    private class HashEntry {
        private ArrayList columns = new ArrayList();
        private ArrayList names = new ArrayList();
        private ArrayList types = new ArrayList();

        public HashEntry() {
        }

        public void addColumn(String hashType, String hashName, DBColumn column) {
//            System.out.println(column.getTableName()+"."+column.getColumnName());
            if (!columns.contains(column)) {
                columns.add(column);
            }
            if (!names.contains(hashName)) {
                names.add(hashName);
                types.add(hashType);
            } else {
                StringBuilder sb = new StringBuilder("Name ").append(hashName)
                        .append("clashed with previous names : ");
                int index = 0;
                for (Iterator i = names.iterator(); i.hasNext(); index++) {
                    String s = (String) i.next();
                    if (index >= 0) {
                        sb.append(",");
                    }
                    sb.append(s).append("(").append(columns.get(index)).append(")");
                }
                throw new IllegalArgumentException("Name clashed with previous names : " + hashName + "(" + hashType + ") from " + names);
            }
        }
    }

    private Hashtable getHashCodeMap() {
        Hashtable map = new Hashtable();
        HashSet hashNamesSet = new HashSet();
        for (int i = 0; i < columns.length; i++) {
            String hashName = columns[i].getBeanFieldName();
            Integer hash = new Integer(hashName.hashCode());
            HashEntry hashEntry = (HashEntry) map.get(hash);
            if (hashEntry == null) {
                hashEntry = new HashEntry();
                map.put(hash, hashEntry);
            }
            hashEntry.addColumn("field", hashName, columns[i]);
            hashNamesSet.add(hashName);
        }
        if(acceptFieldColumnNames){
            for (int i = 0; i < columns.length; i++) {
                String hashName = columns[i].getColumnName();
                Integer hash = new Integer(hashName.hashCode());
                HashEntry hashEntry = (HashEntry) map.get(hash);
                if (hashEntry == null) {
                    hashEntry = new HashEntry();
                    map.put(hash, hashEntry);
                }
                //TAHA 2005-05-12
                if(!hashNamesSet.contains(hashName)){
                    hashNamesSet.add(hashName);
                    hashEntry.addColumn("column", hashName, columns[i]);
                }
            }
        }
        return map;
    }

    public String getCode() {
        StringBuilder corps = new StringBuilder();
        corps.append("for(Iterator i=").append(iteratorExpression).append(";i.hasNext();){\n");
        corps.append(JBGenUtils.indent(getFieldNameMapEntryCastCode()));
        corps.append("\n");
        corps.append("  int selectedFieldId=selectedFieldName.hashCode();\n");
        corps.append("  switch(selectedFieldId){\n");
        for (Iterator i = getHashCodeMap().entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            int hashCode = ((Integer) entry.getKey()).intValue();
            HashEntry hashEntry = (HashEntry) entry.getValue();
            corps.append("    case ").append(hashCode).append(":{");
            if (hashEntry.columns.size() == 1) {
                DBColumn c = (DBColumn) hashEntry.columns.get(0);
                if (hashEntry.types.get(0).equals(PRIVATE_COL_TYPE_FIELD)) {
                    corps.append("  //field ").append(c.getBeanFieldName()).append("\n");
                } else if (hashEntry.types.get(0).equals(PRIVATE_COL_TYPE_COLUMN)) {
                    corps.append("  //column ").append(c.getColumnName()).append("\n");
                } else {
                    throw new IllegalArgumentException("Impossible");
                }
                corps.append(JBGenUtils.indent(getFieldNameCode(c), 6, false, true));
                corps.append("\n");
            } else {
                ArrayList v = hashEntry.columns;
                for (int j = 0; j < v.size(); j++) {
                    DBColumn c = (DBColumn) v.get(j);
                    String ifEleif = (j == 0) ? "if" : "}else if";
                    corps.append("  ").append(ifEleif).append("(\"");
                    if (hashEntry.types.get(j).equals("name")) {
                        corps.append(c.getBeanFieldName());
                    } else {
                        corps.append(c.getColumnName());
                    }
                    corps.append("\".equals(selectedFieldName)){");
                    if (hashEntry.types.get(j).equals(PRIVATE_COL_TYPE_FIELD)) {
                        corps.append("  //field ").append(c.getBeanFieldName()).append("\n");
                    } else if (hashEntry.types.get(j).equals(PRIVATE_COL_TYPE_COLUMN)) {
                        corps.append("  //column ").append(c.getColumnName()).append("\n");
                    } else {
                        throw new IllegalArgumentException("Impossible");
                    }
                    corps.append(JBGenUtils.indent(getFieldNameCode(c), 6, false, true));
//                    corps.append("\n");
                }
                corps.append("         }else{\n");
                corps.append("            throw new UnknownFieldException(selectedFieldName);");
                corps.append("         }\n");
            }
            if (autoBreak) {
                corps.append("      break;\n");
            }
            corps.append("    }\n");
        }
        corps.append("    default :{\n");//switch
        corps.append(JBGenUtils.indent(getSwitchDefaultCode(), 6, false, true)).append("\n");
        corps.append("    }\n");//default
        corps.append("  }\n");//switch
        corps.append("}\n");//for
        return corps.toString();
    }

    public String getSwitchDefaultCode() {
        return switchDefaultCode != null ? switchDefaultCode : "// default";
    }

    public void setSwitchDefaultCode(String switchDefaultCode) {
        this.switchDefaultCode = switchDefaultCode;
    }

    public boolean isAcceptFieldColumnNames() {
        return acceptFieldColumnNames;
    }

    public void setAcceptFieldColumnNames(boolean acceptFieldColumnNames) {
        this.acceptFieldColumnNames = acceptFieldColumnNames;
    }

}

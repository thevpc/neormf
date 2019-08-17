package org.vpc.neormf.jbgen.util;

import org.vpc.neormf.jbgen.dbsupport.DBColumn;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 27 avr. 2004 Time: 15:03:52
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class SQLPattern {

    public SQLPattern(String inString, DBColumn column, boolean sqlColumns) {
//        String inString="{call ? := Get_Dest_Bnumber_List(?,?)}";
        StringBuilder sb = new StringBuilder();
        StringBuilder currentVar = null;
        int pos = 1;
        params = new ArrayList();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            switch (c) {
                case '{':
                    {
                        if (currentVar != null) {
                            throw new IllegalArgumentException("Unexpected '{'");
                        }
                        currentVar = new StringBuilder();
                        break;
                    }
                case '}':
                    {
                        if (currentVar == null) {
                            throw new IllegalArgumentException("Unexpected '}'");
                        }
                        String[] param = JBGenUtils.getStringArray(currentVar.toString(), null, true);
                        SQLCallParam sqlCallParam = null;
                        switch (param.length) {
                            case 1:
                                {
                                    if (SQLCallParam.isInOutType(param[0]) && (SQLCallParam.getInOutType(param[0]) & SQLCallParam.RETURN) != 0) {
                                        sqlCallParam = new SQLCallParam("$RETURN$", SQLCallParam.getInOutType(param[0]), pos, Types.JAVA_OBJECT);
                                    } else {
                                        sqlCallParam = new SQLCallParam(param[0], SQLCallParam.IN, pos, Types.JAVA_OBJECT);
                                    }
                                    break;
                                }
                            case 2:
                                {
                                    sqlCallParam = new SQLCallParam(param[1], SQLCallParam.getInOutType(param[0]), pos, Types.JAVA_OBJECT);
                                    break;
                                }
                            case 3:
                                {
                                    sqlCallParam = new SQLCallParam(param[1], SQLCallParam.getInOutType(param[0] + " " + param[1]), pos, Types.JAVA_OBJECT);
                                    break;
                                }
                            default:
                                {
                                    throw new IllegalArgumentException("Bad param " + currentVar);
                                }
                        }
                        if (sqlColumns) {
                            DBColumn c2 = column.getDAOInfo().getColumnByFieldName(sqlCallParam.getName(), false);
                            if (c2 == null) {
                                params.add(sqlCallParam);
                                sb.append(" ? ");
                                pos++;
                            } else {
                                sb.append(c2.getTableName()).append(".").append(c2.getColumnName());
                            }
                        } else {
                            params.add(sqlCallParam);
                            sb.append(" ? ");
                            pos++;
                        }
                        currentVar = null;
                        break;
                    }
                default:
                    {
                        if (currentVar != null) {
                            currentVar.append(c);
                        } else {
                            sb.append(c);
                        }
                        break;
                    }
            }
        }
        query = sb.toString();
        pattern = inString;
    }

    public String pattern;
    public String query;
    public ArrayList params;

    public SQLCallParam[] getParams() {
        return (SQLCallParam[]) params.toArray(new SQLCallParam[params.size()]);
    }

    public String getPattern() {
        return pattern;
    }

    public String getQuery() {
        return query;
    }

    public static class SQLCallParam {
        public static final int IN = 1;
        public static final int OUT = 2;
        public static final int RETURN = 4;
        private String name;
        private int inOutType;
        private int pos;
        private int sqlType;

        public SQLCallParam(String name, int inOutType, int pos, int sqlType) {
            this.name = name;
            this.inOutType = inOutType;
            this.pos = pos;
            this.sqlType = sqlType;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(pos);
            sb.append(" ");
            sb.append(name);
            sb.append(" ");
            sb.append(JBGenUtils.getSQLTypeName(sqlType));
            if (((inOutType & IN) != 0)) {
                sb.append(" ").append("IN");
            }
            if (((inOutType & OUT) != 0)) {
                sb.append(" ").append("OUT");
            }
            if (((inOutType & RETURN) != 0)) {
                sb.append(" ").append("RETURN");
            }
            return sb.toString();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getInOutType() {
            return inOutType;
        }

        public void setInOutType(int inOutType) {
            this.inOutType = inOutType;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getSqlType() {
            return sqlType;
        }

        public void setSqlType(int sqlType) {
            this.sqlType = sqlType;
        }

        public boolean isOfInOutType(int type) {
            return (inOutType & type) != 0;
        }

        public boolean isInParam() {
            return (inOutType & IN) != 0;
        }

        public boolean isOutParam() {
            return (inOutType & OUT) != 0;
        }

        public boolean isReturnParam() {
            return (inOutType & RETURN) != 0;
        }

        public static int getInOutType(String name) {
            if ("IN".equalsIgnoreCase(name)) {
                return IN;
            } else if ("OUT".equalsIgnoreCase(name)) {
                return OUT;
            } else if ("RETURN".equalsIgnoreCase(name)) {
                return RETURN;
            } else if ("IN OUT".equalsIgnoreCase(name)) {
                return IN + OUT;
            } else if ("OUT RETURN".equalsIgnoreCase(name)) {
                return OUT + RETURN;
            }
            throw new NoSuchElementException(name);
        }

        public static boolean isInOutType(String name) {
            if ("IN".equalsIgnoreCase(name)) {
                return true;
            } else if ("OUT".equalsIgnoreCase(name)) {
                return true;
            } else if ("RETURN".equalsIgnoreCase(name)) {
                return true;
            } else if ("IN OUT".equalsIgnoreCase(name)) {
                return true;
            } else if ("OUT RETURN".equalsIgnoreCase(name)) {
                return true;
            }
            return false;
        }
    }


    public SQLCallParam indexOfParamByInOutType(int inOutType) {
        for (Iterator i = params.iterator(); i.hasNext();) {
            SQLCallParam sqlCallParam = (SQLCallParam) i.next();
            if (sqlCallParam.isOfInOutType(inOutType)) {
                return sqlCallParam;
            }
        }
        return null;
    }

}

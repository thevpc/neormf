package org.vpc.neormf.commons.util;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:33:25
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public final class Utils {

    private Utils() {
    }

    public static Object castTo(Object o, Class clazz) throws ClassCastException {
        if (o != null && !clazz.isAssignableFrom(o.getClass())) {
            throw new ClassCastException("Expected class " + clazz.getName() + " but intercepted " + o.getClass() + " for object " + o);
        }
        return o;
    }

    public static String removeTail(String str, int count) {
        if (str.length() <= count) {
            return "";
        } else {
            return str.substring(0, str.length() - count);
        }
    }

    public static String getClassName(Class c) {
        String cn = c.getName();
        int x = cn.lastIndexOf('.');
        if (x > 0) {
            return cn.substring(x + 1);
        } else {
            return cn;
        }
    }

    public static String toRegexpPattern(String dosLikePattern) {
        if(dosLikePattern==null){
            dosLikePattern="";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dosLikePattern.length(); i++) {
            char c = dosLikePattern.charAt(i);
            switch (c) {
                case'*': {
                    sb.append(".*");
                    break;
                }
                case'?': {
                    sb.append(".");
                    break;
                }
                case'.':
                case'[':
                case']':
                case'{':
                case'}': 
                case')':
                case'(':
                case'^':
                case'$':
                {
                    sb.append("\\").append(c);
                    break;
                }
                case'\\': {
                    sb.append("\\");
                    i++;
                    c = dosLikePattern.charAt(i);
                    sb.append(c);
                    break;
                }
                default: {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static boolean parseBoolean(String value) {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        } else if ("false".equalsIgnoreCase(value)) {
            return false;
        } else {
            throw new IllegalArgumentException("expected 'true' or 'false' for boolean value");
        }
    }

    public static boolean equals(Object a, Object b) {
        return a == b || (a != null && b != null && a.equals(b));
    }

    public static String dump(int object) {
        return dump(new Integer(object));
    }

    public static String dump(long object) {
        return dump(new Long(object));
    }

    public static String dump(double object) {
        return dump(new Double(object));
    }

    public static String dump(float object) {
        return dump(new Float(object));
    }

    public static String dump(byte object) {
        return dump(new Byte(object));
    }

    public static String dump(char object) {
        return dump(new Character(object));
    }

    public static String dump(boolean object) {
        return dump(object ? Boolean.TRUE : Boolean.FALSE);
    }

    public static String dump(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof Dumpable) {
            return ((Dumpable) object).dump();
        } else if (object instanceof Collection) {
            Collection c = (Collection) object;
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            boolean first = true;
            for (Iterator i = c.iterator(); i.hasNext();) {
                Object o = i.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append(dump(o));
            }
            sb.append("]");
            return sb.toString();

        } else if (object instanceof Map) {
            Map m = (Map) object;
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            boolean first = true;
            for (Iterator i = m.entrySet().iterator(); i.hasNext();) {
                Map.Entry e = (Map.Entry) i.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append(dump(e.getKey()));
                sb.append("=");
                sb.append(dump(e.getValue()));
            }
            sb.append("]");
            return sb.toString();
        } else if (object.getClass().isArray()) {
            StringBuffer sb = new StringBuffer();
            int len = Array.getLength(object);
            sb.append("{");
            for (int i = 0; i < len; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(dump(Array.get(object, i)));
            }
            sb.append("}");
            return sb.toString();
        } else if (object instanceof String) {
            StringBuffer sb = new StringBuffer();
            String s = (String) object;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case'\n': {
                        sb.append("\\n");
                        break;
                    }
                    case'\t': {
                        sb.append("\\t");
                        break;
                    }
                    case'\r': {
                        sb.append("\\r");
                        break;
                    }
                    case'\f': {
                        sb.append("\\f");
                        break;
                    }
                    case'\"': {
                        sb.append("\\\"");
                        break;
                    }
                    case'\'': {
                        sb.append("\\\'");
                        break;
                    }
                    default: {
                        sb.append(c);
                    }
                }
            }
            return "\"" + sb.toString() + "\"";
        } else if (object instanceof java.sql.Date) {
            return DUMP_SQL_DATE_FORMAT.format((Date) object);
        } else if (object instanceof java.sql.Time) {
            return DUMP_SQL_TIME_FORMAT.format((Date) object);
        } else if (object instanceof java.sql.Timestamp) {
            return DUMP_SQL_TIMESTAMP_FORMAT.format((Date) object);
        } else if (object instanceof java.util.Date) {
            return DUMP_UTIL_DATE_FORMAT.format((Date) object);

        } else {
            return String.valueOf(object);
        }
    }

    public static SimpleDateFormat DUMP_SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DUMP_UTIL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DUMP_SQL_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat DUMP_SQL_TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    public static String getPeriodDescription(long period) {
        if (period == 0) {
            return "0";
        }
        boolean neg = (period < 0);
        int ms = (int) (period % 1000);
        int s = (int) ((period % (1000 * 60)) / 1000);
        int mn = (int) ((period % (1000 * 60 * 60)) / (1000 * 60));
        int h = (int) ((period) / (1000 * 60 * 60));
        StringBuffer sb = new StringBuffer();
        if (neg) {
            sb.append("- ");
        }
        if (h != 0) {
            sb.append(h).append("H");
        }
        if (mn != 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(mn).append("mn");
        }
        if (s != 0 || ms != 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(s);
            if (ms != 0) {
                sb.append(".").append(ms);
            }
            sb.append("s");
        }
        return sb.toString();
    }

    public static boolean extendedMatching(String pattern, String value, boolean caseSensitive) {
        if (!caseSensitive) {
            pattern = pattern.toLowerCase();
            value = value.toLowerCase();
        }
        if (pattern.startsWith("regexp://")) {
            return value.matches(pattern.substring("regexp://".length()));
        } else if (pattern.startsWith("exp://")) {
            return value.matches(toRegexpPattern(pattern.substring("exp://".length())));
        } else if (pattern.startsWith("value://")) {
            return value.matches(toRegexpPattern(pattern.substring("value://".length())));
        } else {
            return value.matches(toRegexpPattern(pattern));
        }
    }

}

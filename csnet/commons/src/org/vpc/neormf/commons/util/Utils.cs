using System;
using System.Collections;
using System.Text;
using org.vpc.neormf.commons.beans;
using org.vpc.neormf.commons.jwrapper;
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

namespace org.vpc.neormf.commons.util
{
	public class Utils
	{
		public static void UpdateFromBundle(String bundleName, ModuleInfo info)
		{
			String[] dc = info.GetDTOList();
			foreach (String d in dc)
			{
				Type t = info.GetType().Assembly.GetType(d);
				if (t == null)
				{
					Console.WriteLine("Unable to load DataContent : " + d);
				}
				UpdateFromBundle(bundleName, (DataInfo) t.GetField("INFO").GetValue(null));
			}
		}

		public static void UpdateFromBundle(String bundleName, DataInfo info)
		{
			ResourceBundle r = ResourceBundle.GetBundle(bundleName, Locale.Current);
			String prefix = info.GetDataClassName();
			DataField[] fs = info.GetFields();
			for (int i = 0; i < fs.Length; i++)
			{
				try
				{
					fs[i].SetFieldTitle(r.GetString(prefix + "." + fs[i].GetFieldName()));
				}
				catch (MissingResourceException)
				{
					Console.WriteLine("Missing Resource " + prefix + "." + fs[i].GetFieldName());
				}
				catch (Exception e)
				{
					Console.WriteLine("Resource not found for : " + prefix + "." + fs[i].GetFieldName() + "\n" + e);
				}
			}
		}

		//    public static final Set JAVA_KEYWORDS = Collections.unmodifiableSet(new TreeSet(Arrays.asList(new String[]{
		//        "int",
		//        "long",
		//        "double",
		//        "float",
		//        "byte",
		//        "char",
		//        "bool",
		//        "public",
		//        "static",
		//        "void",
		//        "private",
		//        "final",
		//        "transient",
		//        "class",
		//        "interface",
		//        "switch",
		//    })));
		//
		//    private Utils() {
		//    }
		//
		public static Object CastTo(Object o, Type clazz)
		{
			if (o != null && !clazz.IsAssignableFrom(o.GetType()))
			{
				throw new Exception("Expected class " + clazz.Name + " but intercepted " + o.GetType() + " for obj " + o);
			}
			return o;
		}

		//
		//    public static Class getPrimitiveClass(Class c) {
		//        if (Long.class.Equals(c)) {
		//            return Long.TYPE;
		//
		//        } else if (Integer.class.Equals(c)) {
		//            return Integer.TYPE;
		//
		//        } else if (Double.class.Equals(c)) {
		//            return Double.TYPE;
		//
		//        } else if (Float.class.Equals(c)) {
		//            return Float.TYPE;
		//
		//        } else if (Byte.class.Equals(c)) {
		//            return Byte.TYPE;
		//
		//        } else if (Character.class.Equals(c)) {
		//            return Character.TYPE;
		//
		//        } else if (Boolean.class.Equals(c)) {
		//            return Boolean.TYPE;
		//        } else {
		//            return c;
		//        }
		//    }
		//
		//    public static bool isBaseType(Class c) {
		//        return (Long.class.Equals(c) ||
		//                Integer.class.Equals(c) ||
		//                Double.class.Equals(c) ||
		//                Float.class.Equals(c) ||
		//                Byte.class.Equals(c) ||
		//                Character.class.Equals(c) ||
		//                Boolean.class.Equals(c)
		//                );
		//    }
		//
		//    public static String removeTail(String str, int count) {
		//        if (str.length() <= count) {
		//            return "";
		//        } else {
		//            return str.substring(0, str.length() - count);
		//        }
		//    }
		//
		//    public static String getClassName(Class c) {
		//        String cn = c.getName();
		//        int x = cn.lastIndexOf('.');
		//        if (x > 0) {
		//            return cn.substring(x + 1);
		//        } else {
		//            return cn;
		//        }
		//    }
		//
		//    public static String toRegexpPattern(String dosLikePattern) {
		//        StringBuffer sb = new StringBuffer();
		//        for (int i = 0; i < dosLikePattern.length(); i++) {
		//            char c = dosLikePattern.charAt(i);
		//            switch (c) {
		//                case '*':
		//                    {
		//                        sb.append(".*");
		//                        break;
		//                    }
		//                case '?':
		//                    {
		//                        sb.append(".");
		//                        break;
		//                    }
		//                case '.':
		//                    {
		//                        sb.append("\\.");
		//                        break;
		//                    }
		//                case ')':
		//                    {
		//                        sb.append("\\)");
		//                        break;
		//                    }
		//                case '(':
		//                    {
		//                        sb.append("\\(");
		//                        break;
		//                    }
		//                case '\\':
		//                    {
		//                        sb.append("\\");
		//                        i++;
		//                        c = dosLikePattern.charAt(i);
		//                        sb.append(c);
		//                        break;
		//                    }
		//                default:
		//                    {
		//                        sb.append(c);
		//                        break;
		//                    }
		//            }
		//        }
		//        return sb.ToString();
		//    }
		//
		//    public static bool parseBoolean(String value) {
		//        if ("true".equalsIgnoreCase(value)) {
		//            return true;
		//        } else if ("false".equalsIgnoreCase(value)) {
		//            return false;
		//        } else {
		//            throw new IllegalArgumentException("expected 'true' or 'false' for bool value");
		//        }
		//    }
		//
		//    public static String getJavaConstant(String n) {
		//        if (n.toLowerCase().Equals(n)) {
		//            return n.toUpperCase();
		//        } else if (n.toUpperCase().Equals(n)) {
		//            return n;
		//        } else {
		//            StringBuffer sb = new StringBuffer();
		//            char last = 0;
		//            for (int i = 0; i < n.length(); i++) {
		//                char c = n.charAt(i);
		//                if (i > 0 && Character.isUpperCase(c) && Character.isLowerCase(last)) {
		//                    sb.append("_");
		//                }
		//                sb.append(Character.toUpperCase(c));
		//                last = c;
		//            }
		//            return sb.ToString();
		//        }
		//    }
		//
		//    public static String capitalize(String n) {
		//        StringBuffer sb = new StringBuffer(n);
		//        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		//        return sb.ToString();
		//    }
		//
		//    public static String decapitalize(String n) {
		//        StringBuffer sb = new StringBuffer(n);
		//        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		//        return sb.ToString();
		//    }
		//
		//    public static String toJavaIdentifier(String n, bool capitalize) {
		//        if (n.toLowerCase().Equals(n) || n.toUpperCase().Equals(n)) {
		//            StringBuffer sb = new StringBuffer();
		//            char last = 0;
		//            for (int i = 0; i < n.length(); i++) {
		//                char c = n.charAt(i);
		//                if (c == '_') {
		//                    if (last == '_') {
		//                        sb.append('_');
		//                    }
		//                } else {
		//                    if (last == '_') {
		//                        c = Character.toUpperCase(c);
		//                    } else {
		//                        c = Character.toLowerCase(c);
		//                    }
		//                    sb.append(c);
		//                }
		//                last = c;
		//            }
		//            if (capitalize) {
		//                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		//            }
		//            return sb.ToString();
		//        } else {
		//            if (capitalize) {
		//                StringBuffer sb = new StringBuffer(n);
		//                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		//                return sb.ToString();
		//            } else {
		//                return n;
		//            }
		//        }
		//    }
		//
		//    public static String getJavaMethod(String name) {
		//        return toJavaIdentifier(name, true);
		//    }
		//
		//    public static String getJavaPackage(String name) {
		//        String s = toJavaIdentifier(name, false).toLowerCase();
		//        if (JAVA_KEYWORDS.contains(s)) {
		//            return "the" + s;
		//        }
		//        return s;
		//    }
		//
		//    public static String getJavaBean(String name) {
		//        String s = toJavaIdentifier(name, false);
		//        return s;
		//    }
		//
		//    public static String getJavaClass(String name) {
		//        String s = toJavaIdentifier(name, true);
		//        return s;
		//    }
		//
		//    public static String getJavaVar(String name) {
		//        String s = toJavaIdentifier(name, false);
		//        if (JAVA_KEYWORDS.contains(s)) {
		//            return "the" + capitalize(s);
		//        }
		//        return s;
		//    }
		//
		//    public static Class primitiveToObjectClass(Class clazz) {
		//        if (clazz == Boolean.TYPE) {
		//            return Boolean.class;
		//        } else if (clazz == Integer.TYPE) {
		//            return Integer.class;
		//        } else if (clazz == Long.TYPE) {
		//            return Long.class;
		//        } else if (clazz == Float.TYPE) {
		//            return Float.class;
		//        } else if (clazz == Short.TYPE) {
		//            return Short.class;
		//        } else if (clazz == Double.TYPE) {
		//            return Double.class;
		//        } else if (clazz == Character.TYPE) {
		//            return Character.class;
		//        } else if (clazz == Byte.TYPE) {
		//            return Byte.class;
		//        }
		//        return clazz;
		//    }
		//
		//
		//    public static final int EQUAL = 0;
		//    public static final int CASE_DIFFERENT = 1;
		//    public static final int MISPELT = 2;
		//    public static final int DIFFERENT = 3;
		//
		//    public static int distance(String s1, String s2) {
		//        if (s1.Equals(s2)) {
		//            return EQUAL;
		//        } else if (s1.equalsIgnoreCase(s2)) {
		//            return CASE_DIFFERENT;
		//        } else if (
		//                toJavaIdentifier(s1, false).equalsIgnoreCase(Utils.toJavaIdentifier(s2, false))
		//                ||
		//                getJavaConstant(s1).equalsIgnoreCase(Utils.getJavaConstant(s2))
		//        ) {
		//            return MISPELT;
		//        } else {
		//            return DIFFERENT;
		//        }
		//    }
		//
		//    public static void checkInPossibleValues(String value, Set possibleValues) throws NoSuchElementException {
		//        if (!possibleValues.contains(value)) {
		//            int currentDistance = Utils.DIFFERENT;
		//            String nearestString = null;
		//            for (Iterator iterator = possibleValues.iterator(); iterator.hasNext();) {
		//                String s = (String) iterator.next();
		//                int distance = Utils.distance(s, value);
		//                if (distance == Utils.EQUAL) {
		//                    break;
		//                }
		//                if (currentDistance > distance) {
		//                    currentDistance = distance;
		//                    nearestString = s;
		//                }
		//            }
		//            switch (currentDistance) {
		//                case Utils.CASE_DIFFERENT:
		//                    {
		//                        throw new NoSuchElementException("Element " + value + " is mispelt! Verify char case. You may want " + nearestString);
		//                    }
		//                case Utils.MISPELT:
		//                    {
		//                        throw new NoSuchElementException("Element " + value + " is mispelt! You may want " + nearestString);
		//                    }
		//                case Utils.DIFFERENT:
		//                    {
		//                        throw new NoSuchElementException("Element " + value + " is unknown. Please use one of the following \n" + possibleValues);
		//                    }
		//                default :
		//                    {
		//                        // never
		//                    }
		//            }
		//        }
		//    }
		//
		//    public static String[] getStringArray(String pattern, String separators, bool trim) {
		//        StringTokenizer st = separators == null ? new StringTokenizer(pattern) : new StringTokenizer(pattern, separators);
		//        ArrayList al = new ArrayList();
		//        if (trim) {
		//            while (st.hasMoreTokens()) {
		//                al.add(st.nextToken().trim());
		//            }
		//        } else {
		//            while (st.hasMoreTokens()) {
		//                al.add(st.nextToken());
		//            }
		//        }
		//        return (String[]) al.toArray(new String[al.size()]);
		//    }
		//
		////    public static String indent(String body) {
		////        return indent(body, 2, false,true);
		////    }
		////
		////    public static String removeIndents(String body,int indentAmount) {
		////        int min=-1;
		////        StringTokenizer st = new StringTokenizer(body, "\n", true);
		////        StringBuffer sb=new StringBuffer();
		////
		////        while (st.hasMoreTokens()) {
		////            String line=st.nextToken();
		////            if(line.Equals("\n")){
		////                sb.append(line);
		////            }else{
		////                int max=Math.min(line.length(),indentAmount);
		////                int i=0;
		////                while(i<max && " ".indexOf(line.charAt(i))>=0){
		////                    i++;
		////                }
		////                line=line.substring(i);
		////                sb.append(line);
		////            }
		////        }
		////        return sb.ToString();
		////    }
		////
		////    public static int getIndent(String body){
		////        int min=-1;
		////        StringTokenizer st = new StringTokenizer(body, "\n", false);
		////        while (st.hasMoreTokens()) {
		////            String line=st.nextToken();
		////            int max=line.length();
		////            int i=0;
		////            while(i<max && " ".indexOf(line.charAt(i))>=0){
		////                i++;
		////            }
		////            if(min==-1 || i<min){
		////                min=i;
		////            }
		////        }
		////        return min<0  ? 0 : min;
		////    }
		////
		////    public static String indent(String body, int indent, bool skipFirstLine,bool indentReplace) {
		////        if(indentReplace){
		////            body=removeIndents(body,getIndent(body));
		////        }
		////        StringTokenizer st = new StringTokenizer(body, "\n", true);
		////        StringBuffer sb = new StringBuffer();
		////        bool isFirstline = true;
		////        while (st.hasMoreTokens()) {
		////            String s = st.nextToken();
		////            bool doIndent = true;
		////            if (isFirstline) {
		////                if (skipFirstLine) {
		////                    doIndent = false;
		////                }
		////                isFirstline = false;
		////            }
		////            if (doIndent) {
		////                char[] c = new char[indent];
		////                Arrays.fill(c, ' ');
		////                s = new String(c) + s;
		////            }
		////            sb.append(s);
		////        }
		////        return sb.ToString();
		////    }
		//
		//    public static String toJavaComments(String body) {
		//        StringTokenizer st = new StringTokenizer(body, "\n", true);
		//        StringBuffer sb = new StringBuffer("/**\n* ");
		//        String s = null;
		//        while (st.hasMoreTokens()) {
		//            s = st.nextToken();
		//            if ("\n".Equals(s)) {
		//                char[] c = new char[2];
		//                Arrays.fill(c, ' ');
		//                c[0] = '*';
		//                sb.append("\n").append(new String(c));
		//            } else {
		//                sb.append(s);
		//            }
		//        }
		//        sb.append("\n");
		//        sb.append("*/\n");
		//        return sb.ToString();
		//    }
		//
		//    public static Method[] getMethodsByName(Class classInstance,String methodName){
		//        Method[] m=classInstance.getMethods();
		//        ArrayList list=new ArrayList();
		//        for (int i = 0; i < m.length; i++) {
		//            Method method = m[i];
		//            if(method.getName().Equals(methodName)){
		//                list.add(method);
		//            }
		//        }
		//        return (Method[]) list.toArray(new Method[list.size()]);
		//    }
		//
		//    public static bool Equals(Object a,Object b){
		//        return a==b || (a!=null && b!=null && a.Equals(b));
		//    }
		//
		//		    public static String dump(int obj){
		//		        return dump(new Integer(obj));
		//		    }
		//
		//    public static String dump(long obj){
		//        return dump(new Long(obj));
		//    }
		//
		//    public static String dump(double obj){
		//        return dump(new Double(obj));
		//    }
		//
		//    public static String dump(float obj){
		//        return dump(new Float(obj));
		//    }
		//
		//    public static String dump(byte obj){
		//        return dump(new Byte(obj));
		//    }
		//
		//    public static String dump(char obj){
		//        return dump(new Character(obj));
		//    }
		//
		//    public static String dump(bool obj){
		//        return dump(obj ? Boolean.TRUE:Boolean.FALSE);
		//    }
		//
		public static String Dump(Object obj)
		{
			if (obj == null)
			{
				return "null";
			}
			if (obj is Dumpable)
			{
				return ((Dumpable) obj).Dump();
			}
			else if (obj is IDictionary)
			{
				IDictionary m = (IDictionary) obj;
				StringBuilder sb = new StringBuilder();
				sb.Append("[");
				bool first = true;
				for (IDictionaryEnumerator e = m.GetEnumerator(); e.MoveNext(); )
				{
					if (first)
					{
						first = false;
					}
					else
					{
						sb.Append(",");
					}
					sb.Append(Dump(e.Key));
					sb.Append("=");
					sb.Append(Dump(e.Value));
				}
				sb.Append("]");
				return sb.ToString();
			}
			else if (obj is ICollection)
			{
				ICollection c = (ICollection) obj;
				StringBuilder sb = new StringBuilder();
				sb.Append("[");
				bool first = true;
				foreach (Object o in c)
				{
					if (first)
					{
						first = false;
					}
					else
					{
						sb.Append(",");
					}
					sb.Append(Dump(o));
				}
				sb.Append("]");
				return sb.ToString();

			}
			else if (obj.GetType().IsArray)
			{
				StringBuilder sb = new StringBuilder();
				int len = ((Array) (obj)).GetLength(0);
				sb.Append("{");
				for (int i = 0; i < len; i++)
				{
					if (i > 0)
					{
						sb.Append(",");
					}
					sb.Append(Dump(((Array) (obj)).GetValue(i)));
				}
				sb.Append("}");
				return sb.ToString();
			}
			else if (obj is String)
			{
				StringBuilder sb = new StringBuilder();
				String s = (String) obj;
				for (int i = 0; i < s.Length; i++)
				{
					char c = s[i];
					switch (c)
					{
						case '\n':
							{
								sb.Append("\\n");
								break;
							}
						case '\t':
							{
								sb.Append("\\t");
								break;
							}
						case '\r':
							{
								sb.Append("\\r");
								break;
							}
						case '\f':
							{
								sb.Append("\\f");
								break;
							}
						case '\"':
							{
								sb.Append("\\\"");
								break;
							}
						case '\'':
							{
								sb.Append("\\\'");
								break;
							}
						default:
							{
								sb.Append(c);
								break;
							}
					}
				}
				return "\"" + sb.ToString() + "\"";
			}
			else
			{
				return Convert.ToString(obj);
			}
		}

//		    public static SimpleDateFormat DUMP_SQL_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    public static SimpleDateFormat DUMP_UTIL_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    public static SimpleDateFormat DUMP_SQL_TIME_FORMAT=new SimpleDateFormat("HH:mm:ss");
//		    public static SimpleDateFormat DUMP_SQL_TIMESTAMP_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//		
//		    public static String getPeriodDescription(long period){
//		        if(period==0){
//		            return "0";
//		        }
//		       bool neg=(period<0);
//		       int ms=(int) (period%1000);
//		       int s=(int) ((period%(1000*60))/1000);
//		       int mn=(int) ((period%(1000*60*60))/(1000*60));
//		       int h=(int) ((period)/(1000*60*60));
//		       StringBuffer  sb=new StringBuffer();
//		        if(neg){
//		            sb.append("- ");
//		        }
//		       if(h!=0){
//		           sb.append(h).append("H");
//		       }
//		       if(mn!=0){
//		           if(sb.length()>0){
//		               sb.append(" ");
//		           }
//		           sb.append(mn).append("mn");
//		       }
//		       if(s!=0 || ms!=0){
//		           if(sb.length()>0){
//		               sb.append(" ");
//		           }
//		           sb.append(s);
//		           if(ms!=0){
//		               sb.append(".").append(ms);
//		           }
//		           sb.append("s");
//		       }
//		       return sb.ToString();
//		    }

	}
}
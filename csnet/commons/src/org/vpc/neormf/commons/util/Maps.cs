using System;
using System.Collections;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 29 mars 2004 Time: 17:53:59
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.util
{
	public class Maps 
	{
		//    public static Map removeKeyHeader(String header, Map source) {
		//        Map m = null;
		//        try {
		//            m = (Map) source.GetType().newInstance();
		//            for (Iterator i = source.keySet().iterator(); i.hasNext();) {
		//                String s = (String) i.next();
		//                if (s.startsWith(header + ".")) {
		//                    m.put(s.substring(header.length() + 1), source.get(s));
		//                }
		//            }
		//        } catch (InstantiationException e) {
		//            throw new RuntimeException(e.ToString());
		//        } catch (IllegalAccessException e) {
		//            throw new RuntimeException(e.ToString());
		//        }
		//        return m;
		//    }
		//
		//    public static Map fill(Map map, Object[] keys, Object[] values) {
		//        for (int i = 0; i < keys.length; i++) {
		//            map.put(keys[i], values[i]);
		//        }
		//        return map;
		//    }

		public static IDictionary fill(IDictionary map, Object[] keys, Object[] values) 
		{
			for (int i = 0; i < keys.Length; i++) 
			{
				map.Add(keys[i], values[i]);
			}
			return map;
		}
	}
	

}
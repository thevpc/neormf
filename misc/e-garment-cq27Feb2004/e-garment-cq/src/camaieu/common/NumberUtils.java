package camaieu.common;

/**
 * Classe utilitaire pour la manipulation des nombres
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 20/01/2004
 * @status pour validation
 */
public final class NumberUtils {

    /**
     * constructeur privé
     */
    private NumberUtils() {
    }

    /**
     * transforme n en Integer
     * @param n
     * @return un Integer
     */
    public static Integer toInteger(Number n) {
        return n == null ? null : (n instanceof Integer) ? (Integer) n : new Integer(n.intValue());
    }

    /**
     * transforme n en Double
     * @param n
     * @return un Double
     */
    public static Double toDouble(Number n) {
        return n == null ? null : (n instanceof Double) ? (Double) n : new Double(n.doubleValue());
    }

//    public static int[] toIntArray(String[] str) throws NumberFormatException{
//        int[] ints=new int[str.length];
//        for(int i=0;i<ints.length;i++){
//            ints[i]=Integer.parseInt(str[i]);
//        }
//        return ints;
//    }
//
//    public static Integer[] toIntegerArray(String[] str) throws NumberFormatException{
//        Integer[] ints=new Integer[str.length];
//        for(int i=0;i<ints.length;i++){
//            ints[i]=new Integer(str[i]);
//        }
//        return ints;
//    }
}

package camaieu.webform;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;


/**
 * Classe utilitaire pour la création des FieldConstraints
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public final class FieldConstraintsFactory {

    /**
     * constructeur privé
     */
    private FieldConstraintsFactory() {
    }

    /**
     * aucune contrainte
     */
    public static final FieldConstraints NO_CONSTRAINTS = new FieldConstraints() {
        /**
         *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
         */
        public void checkConstraints(Object value,String fieldId,String fieldName,HttpServletRequest request) throws FieldConstraintsException {
        }
    };

    /**
     * valeur non nulle
     */
    public static final FieldConstraints NON_NULLABLE = new FieldConstraints() {
        /**
         *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
         */
        public void checkConstraints(Object value,String fieldId,String fieldName,HttpServletRequest request) throws FieldConstraintsException {
            if (value == null) {
                throw new FieldConstraintsException("NON_NULLABLE",value,fieldId,fieldName,request);
            }
        }
    };

    /**
     * contraintes sur les chaines
     */
    public static class StringLenghtConstraints implements FieldConstraints{
        /**
         * longueur min au sens large
         */
        private int min;

        /**
         * longueru max au sens large
         */
        private int max;

        /**
         * nullable
         */
        private boolean nullable;

        /**
         * constructeur
         * @param min longueur min au sens large
         * @param max longueur max au sens large
         * @param nullable
         */
        public StringLenghtConstraints(int min, int max, boolean nullable) {
            this.min = min;
            this.max = max;
            this.nullable = nullable;
        }

        /**
         *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
         */
        public void checkConstraints(Object value, String fieldId, String fieldName, HttpServletRequest request) throws FieldConstraintsException {
            if(value==null){
                if(!nullable){
                   throw new FieldConstraintsException("NON_NULLABLE",value,fieldId,fieldName,request);
                }
                return;
            }
            String s=(String) value;
            if(min>=0){
                if(s.length()<min){
                    throw new FieldConstraintsException("STRING.MIN",value,fieldId,fieldName,request);
                }
            }
            if(max>=0){
                if(s.length()>max){
                    throw new FieldConstraintsException("STRING.MAX",value,fieldId,fieldName,request);
                }
            }
        }
    }

    public static class IntegerConstraints implements FieldConstraints{
        /**
         * valeur min au sens large
         */
        private int min;

        /**
         * valeur max au sens large
         */
        private int max;

        /**
         * nullable
         */
        private boolean nullable;

        /**
         * constructeur
         * @param min valeur min au sens large
         * @param max valeur max au sens large
         * @param nullable
         */
        public IntegerConstraints(int min, int max, boolean nullable) {
            this.min = min;
            this.max = max;
            this.nullable = nullable;
        }

        /**
         *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
         */
        public void checkConstraints(Object value, String fieldId, String fieldName, HttpServletRequest request) throws FieldConstraintsException {
            if(value==null){
                if(!nullable){
                   throw new FieldConstraintsException("NON_NULLABLE",value,fieldId,fieldName,request);
                }
                return;
            }
            Number s=(Number) value;
            if(min>=0){
                if(s.intValue()<min){
                    throw new FieldConstraintsException("INTEGER.MIN",value,fieldId,fieldName,request);
                }
            }
            if(max>=0){
                if(s.intValue()>max){
                    throw new FieldConstraintsException("INTEGER.MAX",value,fieldId,fieldName,request);
                }
            }
        }
    }

    public static class DoubleConstraints implements FieldConstraints{
        /**
         * valeur min au sens large
         */
        private double min;

        /**
         * valeur max au sens large
         */
        private double max;

        /**
         * nullable
         */
        private boolean nullable;

        /**
         * constructeur
         * @param min valeur min au sens large
         * @param max valeur max au sens large
         * @param nullable
         */
        public DoubleConstraints(double min, double max, boolean nullable) {
            this.min = min;
            this.max = max;
            this.nullable = nullable;
        }

        /**
         *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
         */
        public void checkConstraints(Object value, String fieldId, String fieldName, HttpServletRequest request) throws FieldConstraintsException {
            if(value==null){
                if(!nullable){
                   throw new FieldConstraintsException("NON_NULLABLE",value,fieldId,fieldName,request);
                }
                return;
            }
            Number s=(Number) value;
            if(min>=0){
                if(s.doubleValue()<min){
                    throw new FieldConstraintsException("DOUBLE.MIN",value,fieldId,fieldName,request);
                }
            }
            if(max>=0){
                if(s.doubleValue()>max){
                    throw new FieldConstraintsException("DOUBLE.MAX",value,fieldId,fieldName,request);
                }
            }
        }
    }

    public static class TimestampConstraints implements FieldConstraints{

        /**
         * valeur min au sens large
         */
        private Timestamp min;

        /**
         * valeur max au sens large
         */
        private Timestamp max;

        /**
         * nullable
         */
        private boolean nullable;

        /**
         * constructeur
         * @param min valeur min au sens large
         * @param max valeur max au sens large
         * @param nullable
         */
        public TimestampConstraints(Timestamp min, Timestamp max, boolean nullable) {
            this.min = min;
            this.max = max;
            this.nullable = nullable;
        }

        /**
         *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
         */
        public void checkConstraints(Object value, String fieldId, String fieldName, HttpServletRequest request) throws FieldConstraintsException {
            if(value==null){
                if(!nullable){
                   throw new FieldConstraintsException("NON_NULLABLE",value,fieldId,fieldName,request);
                }
                return;
            }
            Timestamp s=(Timestamp) value;
            if(min!=null){
                if(s.getTime()<min.getTime()){
                    throw new FieldConstraintsException("TIMESTAMP.MIN",value,fieldId,fieldName,request);
                }
            }
            if(max!=null){
                if(s.getTime()>max.getTime()){
                    throw new FieldConstraintsException("TIMESTAMP.MAX",value,fieldId,fieldName,request);
                }
            }
        }
    }

    // -- TIMESTAMP


    /**
     * timestamp
     * @param nullable
     * @return
     */
    public static TimestampConstraints anyTimestamp(boolean nullable){
        return new TimestampConstraints(null,null,nullable);
    }

    /**
     * timestamp postérieur à min au sens large
     * @param nullable
     * @return
     */
    public static TimestampConstraints timestampGT(Timestamp min,boolean nullable){
        return new TimestampConstraints(min,null,nullable);
    }

    /**
     * timestamp postérieur à min
     * @param min
     * @param strict si vrai la comparaison est stricte
     * @param nullable
     * @return
     */
    public static TimestampConstraints timestampGT(Timestamp min,boolean strict,boolean nullable){
        return new TimestampConstraints((!strict||min==null)?min : new Timestamp(min.getTime()+1),null,nullable);
    }

    /**
     * timestamp antérieur à min
     * @param max
     * @param nullable
     * @return
     */
    public static TimestampConstraints timestampLT(Timestamp max,boolean nullable){
        return new TimestampConstraints(null,max,nullable);
    }

    /**
     * timestamp antérieur à min
     * @param max
     * @param strict si vrai la comparaison est stricte
     * @param nullable
     * @return
     */
    public static TimestampConstraints timestampLT(Timestamp max,boolean strict,boolean nullable){
        return new TimestampConstraints(null,(!strict||max==null)?max : new Timestamp(max.getTime()-1),nullable);
    }

    /**
     * timestamp entre mi et max
     * @param min
     * @param minStrict si vrai la comparaison est stricte pour le min
     * @param max
     * @param maxStrict si vrai la comparaison est stricte pour le max
     * @param nullable
     * @return
     */
    public static TimestampConstraints timestampBetween(Timestamp min,boolean minStrict,Timestamp max,boolean maxStrict,boolean nullable){
        return new TimestampConstraints((!minStrict||min==null)?min : new Timestamp(min.getTime()+1),(!maxStrict||max==null)?max : new Timestamp(max.getTime()-1),nullable);
    }

    /**
     * timestamp entre mi et max au sens large
     * @param min si null aucune contrainte
     * @param max si null aucune contrainte
     * @param nullable
     * @return
     */
    public static TimestampConstraints timestampBetween(Timestamp min,Timestamp max,boolean nullable){
        return new TimestampConstraints(max,min,nullable);
    }

    // -- DOUBLE

    /**
     * double
     * @param nullable
     * @return
     */
    public static DoubleConstraints anyDouble(boolean nullable){
        return new DoubleConstraints(-Double.MAX_VALUE,Double.MAX_VALUE,nullable);
    }

    /**
     * double supérieur à min au sens large
     * @param min
     * @param nullable
     * @return
     */
    public static DoubleConstraints doubleGT(double min,boolean nullable){
        return new DoubleConstraints(min,Double.MAX_VALUE,nullable);
    }

    /**
     * double supérieur à min
     * @param min
     * @param strict si vrai la comparaison est au sens strict
     * @param nullable
     * @return
     */
    public static DoubleConstraints doubleGT(double min,boolean strict,boolean nullable){
        return new DoubleConstraints(!strict?min : (min+Double.MIN_VALUE),Double.MAX_VALUE,nullable);
    }

    /**
     * double inférieur à max au sens large
     * @param max
     * @param nullable
     * @return
     */
    public static DoubleConstraints doubleLT(double max,boolean nullable){
        return new DoubleConstraints(-Double.MAX_VALUE,max,nullable);
    }

    /**
     * double inférieur à max
     * @param max
     * @param strict si vrai la comparaison est au sens strict
     * @param nullable
     * @return
     */
    public static DoubleConstraints doubleLT(double max,boolean strict,boolean nullable){
        return new DoubleConstraints(-Double.MAX_VALUE,!strict?max : (max-Double.MIN_VALUE),nullable);
    }

    /**
     * double entre min et max
     * @param min
     * @param minStrict si vrai la comparaison est au sens strict pour min
     * @param max
     * @param maxStrict si vrai la comparaison est au sens strict pour max
     * @param nullable
     * @return
     */
    public static DoubleConstraints doubleBetween(double min,boolean minStrict,double max,boolean maxStrict,boolean nullable){
        return new DoubleConstraints(!minStrict?min : (min+Double.MIN_VALUE),!maxStrict?max : (min-Double.MIN_VALUE),nullable);
    }

    /**
     * double entre min et max au sens large
     * @param min
     * @param max
     * @param nullable
     * @return
     */
    public static DoubleConstraints doubleBetween(double min,double max,boolean nullable){
        return new DoubleConstraints(max,min,nullable);
    }


    //------------ INTEGER

    /**
     * entier
     * @param nullable
     * @return
     */
    public static IntegerConstraints anyInt(boolean nullable){
        return new IntegerConstraints(Integer.MIN_VALUE,Integer.MAX_VALUE,nullable);
    }

    /**
     * entier supérieur à min au sens large
     * @param min
     * @param nullable
     * @return
     */
    public static IntegerConstraints intGT(int min,boolean nullable){
        return new IntegerConstraints(min,Integer.MAX_VALUE,nullable);
    }

    /**
     * entier inférieur à max au sens large
     * @param max
     * @param nullable
     * @return
     */
    public static IntegerConstraints intLT(int max,boolean nullable){
        return new IntegerConstraints(Integer.MIN_VALUE, max, nullable);
    }

    /**
     * entier entre min et max au sens large
     * @param min
     * @param max
     * @param nullable
     * @return
     */
    public static IntegerConstraints intBetween(int min,int max,boolean nullable){
        return new IntegerConstraints(min, max,nullable);
    }

    //------------- OTHER

    /**
     * null non permis
     * @return
     */
    public static FieldConstraints nonNull(){
        return NON_NULLABLE;
    }

    /**
     * aucune contrainte
     * @return
     */
    public static FieldConstraints any(){
        return NO_CONSTRAINTS;
    }
}

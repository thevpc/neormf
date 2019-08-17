/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vpc.neormf.commons.beans;

/**
 *
 * @author vpc
 */
public class DataFieldFilterFactory {
    public static final DataFieldFilter PK=new KeysFilter();
    public static final DataFieldFilter AUTO_PK=new AutoKeysFilter();
    public static final DataFieldFilter NPK=new NonKeysFilter();
    public static final DataFieldFilter REGULAR_NPK=new RegularNonKeysFilter();
    public static final DataFieldFilter REGULAR=new RegularFilter();
    
    private static final class KeysFilter implements DataFieldFilter{
        public boolean accept(DTOFieldMetaData field) {
            return field.isPK();
        }
    }
    
    private static final class AutoKeysFilter implements DataFieldFilter{
        public boolean accept(DTOFieldMetaData field) {
            return field.isPK() && field.isAllModifiers(DTOFieldMetaData.AUTO_IDENTITY);
        }
    }
    
    private static final class NonKeysFilter implements DataFieldFilter{
        public boolean accept(DTOFieldMetaData field) {
            return !field.isPK();
        }
    }
    
    private static final class RegularNonKeysFilter implements DataFieldFilter{
        public boolean accept(DTOFieldMetaData field) {
            return !field.isPK()  && field.isRegular();
        }
    }
    
    private static final class RegularFilter implements DataFieldFilter{
        public boolean accept(DTOFieldMetaData field) {
            return field.isRegular();
        }
    }
}

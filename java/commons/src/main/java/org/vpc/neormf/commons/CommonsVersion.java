package org.vpc.neormf.commons;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 19 avr. 2007 22:57:16
 */
public class CommonsVersion {
    public static final String PRODUCT_NAME = "neormf-commons";
    public static final String PRODUCT_DESC = "Neormf Commons Library";
    private static final String PRODUCT_VERSION = /**@vpc-tool:inc-version(commons-version,../../../..)**/ "0.1.78" /**@vpc-tool:end**/;
    private static final String PRODUCT_BUILD_DATE = /**@vpc-tool:write-date**/ "2009-07-11" /**@vpc-tool:end**/;
    public static final String AUTHOR_NAME = "Taha BEN SALAH";
    public static final String AUTHOR_MAIL = "taha.bensalah@gmail.com";
    public static final String PRODUCT_URL = "http://neormf.sourceforge.net";
    private static final String PRODUCT_LONG_TITLE = PRODUCT_NAME + " v" + PRODUCT_VERSION;

    private CommonsVersion() {
    }

    public static String getVersion(){
        return PRODUCT_VERSION;
    }

    public static String getBuildDate(){
        return PRODUCT_BUILD_DATE;
    }

    public static String getLongTitle(){
        return PRODUCT_LONG_TITLE;
    }
}

package org.vpc.neormf.jbgen;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 25 févr. 2007 15:54:02
 */
public class JBGenVersion {
    public static final String PRODUCT_NAME = "neormf-jbgen";
    public static final String PRODUCT_DESC = "Neormf JBgen Code Generation Tool";
    private static final String PRODUCT_VERSION = /**@vpc-tool:inc-version(jbgen-version,../../../..)**/ "0.1.185" /**@vpc-tool:end**/;
    private static final String PRODUCT_BUILD_DATE = /**@vpc-tool:write-date**/ "2009-02-24" /**@vpc-tool:end**/;
    public static final String AUTHOR_NAME = "Taha BEN SALAH";
    public static final String AUTHOR_MAIL = "taha.bensalah@gmail.com";
    public static final String PRODUCT_URL = "http://neormf.sourceforge.net";
    private static final String PRODUCT_LONG_TITLE = PRODUCT_NAME + " v" + PRODUCT_VERSION;
    private static final String PRODUCT_MAJOR_VERSION = PRODUCT_VERSION.substring(0,PRODUCT_VERSION.lastIndexOf('.'));

    private JBGenVersion() {
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

    public static String getMajorVersion(){
        return PRODUCT_MAJOR_VERSION;
    }
}

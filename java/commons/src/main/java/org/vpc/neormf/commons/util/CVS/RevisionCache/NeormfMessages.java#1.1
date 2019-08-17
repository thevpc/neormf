package org.vpc.neormf.commons.util;

import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Hashtable;
import java.util.Locale;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: taha
 * Date: 14 dec. 2005
 * Time: 10:45:27
 * To change this template use File | Settings | File Templates.
 */
public class NeormfMessages {
    public static Hashtable mappedBundles=new Hashtable();

    private Locale locale=null;
    private ResourceBundle defaultBundle=null;
    private static String defaultBundleBaseName="org.vpc.neormf.commons.util.neormf-messages";

    private ResourceBundle replaceBundle=null;
    private static String replaceBundleName=null;


    public NeormfMessages(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static void setBundleBaseName(String bundleBaseName) {
        replaceBundleName = bundleBaseName;
        mappedBundles.clear();
    }

    public String getString(String key){
        return getString(key,"<"+key+"$NOT_FOUND>");
    }

    public String getString(String key,Object[] params,String defaultPattern){
        String value = getString(key, defaultPattern);
        return MessageFormat.format(value,params);
    }

    public String getString(String key,String defaultValue){
        if(defaultBundle==null){
            defaultBundle=ResourceBundle.getBundle(defaultBundleBaseName,locale);
        }
        if(replaceBundle==null && replaceBundleName!=null){
            replaceBundle=ResourceBundle.getBundle(replaceBundleName,locale);
        }
        String value=null;
        if(replaceBundle!=null){
            try {
                value=replaceBundle.getString(key);
            } catch (MissingResourceException e) {
                if(defaultBundle!=null){
                    try {
                        value=defaultBundle.getString(key);
                    } catch (MissingResourceException e1) {
                        //
                    }
                }
            }
        }
        if(value==null){
            try {
                value=defaultBundle.getString(key);
            } catch (MissingResourceException e) {
                //
            }
        }
        return (value==null)?defaultValue:value;
    }


    public static String getString(String key, Locale locale){
        NeormfMessages messages = (NeormfMessages) mappedBundles.get(locale);
        if(messages==null){
            messages=new NeormfMessages(locale);
            mappedBundles.put(locale,messages);
        }
        return messages.getString(key);
    }

    public static String getString(String key, String defaultValue,Locale locale){
        NeormfMessages messages = (NeormfMessages) mappedBundles.get(locale);
        if(messages==null){
            messages=new NeormfMessages(locale);
            mappedBundles.put(locale,messages);
        }
        return messages.getString(key,defaultValue);
    }

    public static String getString(String key, Object[] params,Locale locale){
        return getString(key, params,"<"+key+"$NOT_FOUND>",locale);
    }

    public static String getString(String key, Object[] params,String defaultValue,Locale locale){
        NeormfMessages messages = (NeormfMessages) mappedBundles.get(locale);
        if(messages==null){
            messages=new NeormfMessages(locale);
            mappedBundles.put(locale,messages);
        }
        return messages.getString(key,params,defaultValue);
    }

}

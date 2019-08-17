package org.vpc.neormf.wws.common;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 mai 2005
 * Time: 23:26:16
 * To change this template use File | Settings | File Templates.
 */
public final class WMessages {

    static HashMap hshLangues = new HashMap();

    private WMessages() {
    }

    public static Locale configureLanguageFromBrowser(HttpServletRequest request) {
        String prefLang = request.getHeader("accept-language");
        if(prefLang==null || prefLang.length()==0){
            return null;
        }
        if (prefLang.indexOf(",") > 0){
            prefLang = prefLang.substring(0, prefLang.indexOf(","));
        }
        if (prefLang.indexOf("-") > 0){
            prefLang = prefLang.substring(0, prefLang.indexOf("-"));
        }
        if(prefLang.length()>0){
            Locale locale=new Locale(prefLang);
            setLanguage(request, locale);
            return locale;
        }else{
            return null;
        }
    }

    public static void setLanguage(HttpServletRequest request, Locale prefLang) {
        if (prefLang != null){
            request.getSession().setAttribute(WMessages.class.getName(), prefLang);
        }
    }

    public static Locale getLocale(HttpServletRequest request,boolean autoConfigureSession){
        Locale lang = (Locale) WUtils.resolveObjectByName(request,WMessages.class.getName(),null);
        if (lang == null && autoConfigureSession){
            lang=configureLanguageFromBrowser(request);
            if(lang==null){
                return Locale.getDefault();
            }
        }
        return lang;
    }

    public static String getString(String key, HttpServletRequest request) {
        return getString(key, request, getLocale(request,true),"#!" + key + "!#");
    }

    public static String getString(String key, HttpServletRequest request,String defaultValue) {
        return getString(key, request, getLocale(request,true),defaultValue);
    }

    public static String getString(String key, HttpServletRequest request, Locale lang,String defaultValue) {
        if(lang==null){
            lang=Locale.getDefault();
        }
        Vector v = (Vector) hshLangues.get(lang);
        if(v==null){
            v=new Vector();
            hshLangues.put(lang,v);
            try {
                String strName = request.getRequestURI();
                strName = strName.substring(1, strName.indexOf(47, 2));
                ResourceBundle p = ResourceBundle.getBundle("wmessages-"+strName, lang);
                v.add(p);
            } catch (Exception e) {
                //
            }
            try {
                ResourceBundle p = ResourceBundle.getBundle("org.vpc.neormf.commons.util.neormf-messages", lang);
                v.add(p);
            } catch (Exception e) {
                //
            }
        }
        for (Iterator i = v.iterator(); i.hasNext();) {
            ResourceBundle resourceBundle = (ResourceBundle) i.next();
            try {
                return resourceBundle.getString(key);
            } catch (Exception e) {
                //
            }
        }
        return defaultValue;
    }

}
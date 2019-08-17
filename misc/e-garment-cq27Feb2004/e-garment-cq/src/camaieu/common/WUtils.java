package camaieu.common;

import camaieu.tool.web.language.Messages;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe utilitaire d'ordre général
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public final class WUtils {
    public static final SimpleDateFormat COMPACT_TIMESTAMP=new SimpleDateFormat("yyyyMMddHHmmssSSS");
    /**
     * nombre paximum de caractères à afficher.
     * Au del de cette taille, une troncature
     * sera réalisé sur la chaine à afficher en rajoutant '...'
     */
    public static int MAX_TABLE_CELL_STR_LENGTH = 30;
    /**
     * Séparateur par défaut pour le formattage en CSV
     * @see camaieu.common.WUtils#toCsv(String)
     */
    public static char CSV_SEPARATOR = ',';
    /**
     * Cotes par défaut pour le formattage en CSV
     * @see camaieu.common.WUtils#toCsv(String)
     */
    public static char CSV_COTES = '"';

    /**
     * transorme le chemin relatif en chemin par rapport au contexte du webapp.
     *
     * <BR> lorsque le path est nul, la page d'acceuil est retournée
     * @param request de la jsp
     * @param path chemin a transormer, par exemple 'images/Logo/gif'
     * @return le chemin relatif au contexte du webapp, (pour l'exemple donné le résultat est '/qc/images/Logo/gif'
     */
    public static String getPath(HttpServletRequest request, String path) {
        String c = request.getContextPath();
        if (path == null) {
            return c;
        }
        return c + "/" + path;
    }

    /**
     * formatter un objet à afficher
     * @param request de la jsp
     * @param cell l'objet à formatter
     * @return toDisplayable(cell,"-",MAX_TABLE_CELL_STR_LENGTH)
     * @see camaieu.common.WUtils#toDisplayable(HttpServletRequest,Object,String ,int )
     */
    public static String toDisplayable(HttpServletRequest request, Object cell) {
        return toDisplayable(request, cell, "-", MAX_TABLE_CELL_STR_LENGTH);
    }

    /**
     * formatter un objet à afficher
     * @param request de la jsp
     * @param cell l'objet à formatter
     * @return toDisplayable(cell, defaultNullValue, MAX_TABLE_CELL_STR_LENGTH)
     * @see camaieu.common.WUtils#toDisplayable(HttpServletRequest,Object,String ,int )
     */
    public static String toDisplayable(HttpServletRequest request, Object cell, String defaultNullValue) {
        return toDisplayable(request, cell, defaultNullValue, MAX_TABLE_CELL_STR_LENGTH);
    }

    /**
     * formatter un objet à afficher
     * formatter un objet à afficher depuis une clef d'internationalisation
     *
     * @param request de la jsp
     * @param key d'internationalisation
     * @param params parametres du message
     * @return un objet formatté en chaine
     */
    public static String getDisplayable(HttpServletRequest request, String key, Object[] params) {
        return toDisplayable(request, MessageFormat.format(Messages.getString(key,request),params), "Unknown", -1);
    }


    /**
     * formatter un objet à afficher depuis une clef d'internationalisation
     *
     * @param request de la jsp
     * @param key d'internationalisation
     * @return un objet formatté en chaine
     */
    public static String getDisplayable(HttpServletRequest request, String key) {
        return toDisplayable(request, Messages.getString(key,request), "Unknown", -1);
    }

    /**
     * formatter un objet à afficher
     * formatter un objet à afficher depuis une clef d'internationalisation
     *
     * @param request de la jsp
     * @param key d'internationalisation
     * @param maxLength longueur maximale de la chaine résulatnte
     * @return un objet formatté en chaine
     */
    public static String getDisplayable(HttpServletRequest request, String key, int maxLength) {
        return toDisplayable(request, Messages.getString(key,request), "Unknown", maxLength);
    }

    /**
     * formatter un objet à afficher
     * en l'ocuurence, un Java NULL sera remplacée par <code>defaultNullValue</code>,
     * et les chaines plus longues que <code>maxLength</code> seront tronquée et
     * un '...' sera rajouté en fin de chaine.
     *
     * @param request de la jsp
     * @param cell l'objet à formatter
     * @param defaultNullValue valeur si cell est nul
     * @param maxLength longueur maximale de la chaine résulatnte
     * @return un objet formatté en chaine
     */
    public static String toDisplayable(HttpServletRequest request, Object cell, String defaultNullValue, int maxLength) {
        if (cell == null) {
            return defaultNullValue;
        } else if (cell instanceof Date) {
            return getDateFormat(request).format((Date) cell);
//            StringConverter.valueOf((java.sql.Timestamp)cell);
        } else if (cell instanceof Double) {
            return cell.toString();
            // cela pose prblèmle de virgule
//            return StringConverter.valueOf((Double) cell);
        } else if (cell instanceof Integer) {
            return cell.toString();
            // cela pose prblèmle de virgule
//            return StringConverter.valueOf((Integer) cell);
        } else {
            String str = cell.toString();
            if (maxLength > 0 && str.length() >= maxLength) {
                str = str.substring(0, maxLength) + "...";
            }
            return str;
        }
    }

    /**
     * une chaine tronquée si sa longueur dépasse maxLength
     * @param cell
     * @param defaultNullValue
     * @param maxLength
     * @return
     */
    public static String toDisplayable(String cell, String defaultNullValue, int maxLength) {
        if (cell == null) {
            return defaultNullValue;
        } else {
            String str = cell.toString();
            if (maxLength > 0 && str.length() >= maxLength) {
                str = str.substring(0, maxLength) + "...";
            }
            return str;
        }
    }

    /**
     * formatter un objet à afficher en Html
     *
     * @param request de la jsp
     * @param cell object à formatter
     * @param defaultNullValue
     * @param maxLength
     * @return plainTextToHtml(toDisplayable(cell,defaultNullValue,maxLength));
     * @see camaieu.common.WUtils#toDisplayable(javax.servlet.http.HttpServletRequest, java.lang.Object)
     * @see camaieu.common.WUtils#plainTextToHtml(String)
     */
    public static String toHtml(HttpServletRequest request, Object cell, String defaultNullValue, int maxLength) {
        return plainTextToHtml(toDisplayable(request, cell, defaultNullValue, maxLength));
    }

    /**
     * formatter un objet à afficher en Html
     *
     * @param request de la jsp
     * @param cell object à formatter
     * @param defaultNullValue object à formatter
     * @return plainTextToHtml(toDisplayable(cell,defaultNullValue));
     * @see camaieu.common.WUtils#toDisplayable(javax.servlet.http.HttpServletRequest, java.lang.Object, java.lang.String, int)
     * @see camaieu.common.WUtils#plainTextToHtml(String)
     */
    public static String toHtml(HttpServletRequest request, Object cell, String defaultNullValue) {
        return plainTextToHtml(toDisplayable(request, cell, defaultNullValue));
    }

    /**
     * formatter un objet à afficher en Html
     *
     * @param request de la jsp
     * @param cell object à formatter
     * @return plainTextToHtml(toDisplayable(cell));
     * @see camaieu.common.WUtils#toDisplayable(javax.servlet.http.HttpServletRequest, java.lang.Object, java.lang.String, int)
     * @see camaieu.common.WUtils#plainTextToHtml(String)
     */
    public static String toHtml(HttpServletRequest request, Object cell) {
        return plainTextToHtml(toDisplayable(request, cell));
    }

    /**
     * transforme une chaine texte en son équivalent Html
     * @param plainStr text à transformer
     * @return l'équivalent html de plainStr en traitant le cas des caractères spéciaux
     */
    public static String plainTextToHtml(String plainStr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < plainStr.length(); i++) {
            char c = plainStr.charAt(i);
            // do not use Character.isLetterOrDigit(str.charAt(i)) || str.charAt(i)==' '
            if (c == '&') {
                sb.append("&amp;");
            } else if (c == '<') {
                sb.append("&lt;");
            } else if (c == '>') {
                sb.append("&gt;");
            } else if (c == '\"') {
                sb.append("&quot;");
            } else if (c == ' ') {
                sb.append("&nbsp;");
            } else if (c == '\n') {
                sb.append("<BR>");
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                sb.append(plainStr.charAt(i));
            } else {
                sb.append("&#" + (int) plainStr.charAt(i) + ";");
            }
        }
        return sb.toString();
    }

    /**
     * Récupération de chemin du fichier help
     * l'url doit se trouver dans le fichier properties récupéré
     * avec la classe Messages au niveau de la clef CONFIG.HELP_URL
     */
    public static String getHelpPath(HttpServletRequest request) {
        return getHelpPath(request, null);
    }

    /**
     * Récupération de chemin du fichier help pour la section donnée
     * l'url doit se trouver dans le fichier properties récupéré
     * avec la classe Messages au niveau de la clef <B>CONFIG.HELP_URL.<CODE>&lt;helpSectionCode&gt;</CODE></B>
     */
    public static String getHelpPath(HttpServletRequest request, String helpSectionCode) {
        String id = (helpSectionCode == null || helpSectionCode.length() == 0) ? "" : "." + helpSectionCode;
        String h = Messages.getString("CONFIG.HELP_URL" + id, request);
        if ("-err-".equals(h)) {
            if (!(helpSectionCode == null || helpSectionCode.length() == 0)) {
                return getHelpPath(request, null);
            }
            return "help.html";
        }
        return h;
    }

    /**
     * formatter au format CSV
     * @param cell texte à formatter
     * @return toCsv(cell,CSV_SEPARATOR, CSV_COTES);
     */
    public static String toCsv(String cell) {
        return toCsv(cell, CSV_SEPARATOR, CSV_COTES);
    }

    /**
     * formatter au format CSV
     * @param cell texte à formatter
     * @param separator le séparateur
     * @param cotes les cotes " ou '
     * @return texte formatté au format CSV
     */
    public static String toCsv(String cell, char separator, char cotes) {
        if (cell == null || cell.length() == 0) {
            return "";
        } else if (cell.indexOf(separator) >= 0 || cell.indexOf(cotes) >= 0) {
            StringBuffer sb = new StringBuffer();
            sb.append(cotes);
            for (int i = 0; i < cell.length(); i++) {
                char c = cell.charAt(i);
                if (c == cotes) {
                    sb.append(c);
                }
                sb.append(c);
            }
            sb.append(cotes);
            return sb.toString();
        } else {
            return cell;
        }
    }

    /**
     * recupération de la langue
     * cette méthode devrait être déplacée vers la classe Messages
     * (projet cvs WebLangCpnt)
     * @param request
     * @return
     */
    public static String getLanguage(HttpServletRequest request) {
        return request == null ? null : (String) request.getSession().getAttribute("camlang.jar:locale");
    }

    /**
     * recupération de la locale
     * cette méthode devrait être déplacée vers la classe Messages
     * (projet cvs WebLangCpnt)
     * @param request
     * @return
     */
    public static Locale getLocale(HttpServletRequest request) {
        String s = getLanguage(request);
        return s == null ? null : new Locale(s);
    }

    /**
     * recupération du format de la date
     * cette méthode devrait être déplacée vers la classe Messages
     * (projet cvs WebLangCpnt)
     * @param request
     * @return
     */
    public static DateFormat getDateFormat(HttpServletRequest request) {
        String s = getLanguage(request);
        return s == null ? DateFormat.getDateInstance(DateFormat.SHORT) : DateFormat.getDateInstance(DateFormat.SHORT, new Locale(s));
    }

    /**
     * recupération de la date/heure
     * cette méthode devrait être déplacée vers la classe Messages
     * (projet cvs WebLangCpnt)
     * @param request
     * @return
     */
    public static DateFormat getDateTimeFormat(HttpServletRequest request) {
        String s = getLanguage(request);
        return s == null ? DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT) : DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, new Locale(s));
    }

    /**
     * message Html pour un code de message depuis les properties
     * @param request de la jsp
     * @param msgKey code message
     * @return message Html pour un code de message depuis les properties
     */
    public static String getHtml(HttpServletRequest request, String msgKey) {
        return toHtml(request, Messages.getString(msgKey, request), "", -1);
    }


    /**
     * message Html pour un code de message depuis les properties
     * @param request de la jsp
     * @param msgKey code message
     * @param msgParameters paramètres du message
     * @return message Html pour un code de message depuis les properties
     */
    public static String getHtml(HttpServletRequest request, String msgKey, Object[] msgParameters) {
        String pattern = Messages.getString(msgKey, request);
        return toHtml(request, MessageFormat.format(pattern, msgParameters), "", -1);
    }

    /**
     * message CSV pour un code de message depuis les properties
     * @param request de la jsp
     * @param msgKey code message
     * @return message Html pour un code de message depuis les properties
     */
    public static String getCsv(HttpServletRequest request, String msgKey) {
        return toCsv(Messages.getString(msgKey, request));
    }

    /**
     * message CSV pour un code de message depuis les properties
     * @param request de la jsp
     * @param msgKey code message
     * @param msgParameters paramètres du message
     * @return message Html pour un code de message depuis les properties
     */
    public static String getCsv(HttpServletRequest request, String msgKey, Object[] msgParameters) {
        String pattern = Messages.getString(msgKey, request);
        return toCsv(MessageFormat.format(pattern, msgParameters));
    }

    /**
     * revoi (v1==v2 || (v1!=null &&v1.equals(v2))) ? valueWhenEqual : valueWhenDifferent
     * @param v1
     * @param v2
     * @param valueWhenEqual
     * @param valueWhenDifferent
     * @return (v1==v2 || (v1!=null &&v1.equals(v2))) ? valueWhenEqual : valueWhenDifferent
     */
    public static Object valueByComp(Object v1, Object v2, Object valueWhenEqual, Object valueWhenDifferent) {
        return (v1 == v2 || (v1 != null && v1.equals(v2))) ? valueWhenEqual : valueWhenDifferent;
    }

    /**
     * return le chemin absolu de l'image dont la clef dans le fichier ressource est <CODE>imageKey</CODE>
     * @param request
     * @param imageKey
     * @return
     */
    public static String getImageUrl(HttpServletRequest request, String imageKey) {
        String str = Messages.getString("IMAGE." + imageKey, request);
        return getPath(request, str);
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;chemin d'upload&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;upload&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;c:\temp\&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.String&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getWebEnvVar(String key,Object defaultValue){
        try {
            InitialContext context = new InitialContext();
            Object v = context.lookup("java:comp/env/"+key);
            return v==null?defaultValue:v;
        } catch (NamingException e) {
            return defaultValue;
        }
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;chemin d'upload&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;upload&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;c:\temp\&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.String&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getStringWebEnvVar(String key,String defaultValue){
        return (String) getWebEnvVar(key,defaultValue);
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;types de fichiers acceptables&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;fileTypes&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;doc,text,gif&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.String&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static String[] getStringArrayWebEnvVar(String key,String separators, boolean returnNulls,String[] defaultValue){
        String s=(String) getWebEnvVar(key,defaultValue);
        if(s==null){
            return defaultValue;
        }
        return StringUtils.split(s,separators,returnNulls);
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;un nombre&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;nbr&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;15&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.Integer&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static Integer getIntegerWebEnvVar(String key,Integer defaultValue){
        return (Integer) getWebEnvVar(key,defaultValue);
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;un nombre&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;nbr&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;15&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.Integer&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getIntWebEnvVar(String key,int defaultValue){
        return ((Integer) getWebEnvVar(key,new Integer(defaultValue))).intValue();
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;un nombre&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;nbr&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;15.2&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.Double&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static Double getDoubleWebEnvVar(String key,Double defaultValue){
        return (Double) getWebEnvVar(key,defaultValue);
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;un nombre&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;nbr&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;15.2&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.Double&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static double getDoubleWebEnvVar(String key,double defaultValue){
        return ((Double) getWebEnvVar(key,new Double(defaultValue))).doubleValue();
    }

    /**
     * return la valeur d'un paramètre du context web
     * <BR>
     * <code>
     * 	&lt;env-entry&gt;<BR>
	 *  &lt;description&gt;oui/nom&lt;/description&gt;<BR>
	 *  &lt;env-entry-name&gt;yesno&lt;/env-entry-name&gt;<BR>
	 *  &lt;env-entry-value&gt;true&lt;/env-entry-value&gt;<BR>
	 *  &lt;env-entry-type&gt;java.lang.Boolean&lt;/env-entry-type&gt;<BR>
	 *  &lt;/env-entry&gt;
     * </code>
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanWebEnvVar(String key,boolean defaultValue){
        return ((Boolean) getWebEnvVar(key,defaultValue?Boolean.TRUE:Boolean.FALSE)).booleanValue();
    }

    /**
     * le chemin en webapp ou null si le chemin n'est pas sous la racine du webapp
     * @param servletContext
     * @param absoluteFile
     * @return
     */
    public static String getWebPath(HttpServletRequest request, ServletContext servletContext, File absoluteFile){
       return  request.getContextPath()+"/"+IOUtils.getRelativePath(new File(servletContext.getRealPath("/")),absoluteFile);
    }
}

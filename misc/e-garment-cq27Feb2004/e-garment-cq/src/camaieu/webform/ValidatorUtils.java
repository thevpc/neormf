package camaieu.webform;

import camaieu.common.WUtils;
import camaieu.tool.web.language.Messages;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe utilitaire pour la validation des champs
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */

public final class ValidatorUtils {

    /**
     * validation d'un champ de type Timestamp
     * @param field
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Timestamp parseTimestamp(String field, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseTimestamp(field, request.getParameter(field), request, helper,constraints);
    }

    /**
     * validation d'un champ de type Timestamp
     * @param field
     * @param strValue
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Timestamp parseTimestamp(String field, String strValue, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        Timestamp oValue=null;
        if (strValue == null || strValue.length() == 0) {
            oValue=null;
        }else{
            try {
                oValue=new Timestamp(WUtils.getDateFormat(request).parse(strValue).getTime());
            } catch (Throwable e) {
                FieldConstraintsException fce=new FieldConstraintsException("TIMESTAMP.BAD_FORMAT",strValue,field,field,request);
                if (helper != null) {
                    helper.setEditingData(field, strValue);
                    helper.addError(field, fce.getMessage());
                }
                throw fce;
            }
        }
        try {
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;
    }


    /**
     * validation d'un champ de type Date
     * @param field
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Date parseDate(String field, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseDate(field, request.getParameter(field), request, helper,constraints);
    }

    /**
     * validation d'un champ de type Date
     * @param field
     * @param strValue
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Date parseDate(String field, String strValue, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        Date oValue=null;
        if (strValue == null || strValue.length() == 0) {
            oValue=null;
        }else{
            try {
                oValue = WUtils.getDateFormat(request).parse(strValue);
                if(constraints!=null){
                    constraints.checkConstraints(oValue,field,field,request);
                }
            } catch (ParseException e) {
                FieldConstraintsException fce=new FieldConstraintsException("DATE.BAD_FORMAT",strValue,field,field,request);
                if (helper != null) {
                    helper.setEditingData(field, strValue);
                    helper.addError(field, fce.getMessage());
                }
                throw fce;
            }
        }
        try {
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;
    }

    /**
     * validation d'un champ de Integer
     * @param field
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Integer parseInteger(String field, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseInteger(field, request.getParameter(field), request, helper,constraints);
    }

    /**
     * validation d'un champ de type Integer
     * @param field
     * @param strValue
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Integer parseInteger(String field, String strValue, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        Integer oValue=null;
        if (strValue == null || strValue.length() == 0) {
            oValue= null;
        }else{
            try {
                oValue = new Integer(strValue);
                if(constraints!=null){
                    constraints.checkConstraints(oValue,field,field,request);
                }
            } catch (NumberFormatException e) {
                FieldConstraintsException fce=new FieldConstraintsException("INTEGER.BAD_FORMAT",strValue,field,field,request);
                if (helper != null) {
                    helper.setEditingData(field, strValue);
                    helper.addError(field, fce.getMessage());
                }
                throw fce;
            }
        }
        try {
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;

    }


    /**
     * validation d'un champ de type Double
     * @param field
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Double parseDouble(String field, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseDouble(field, request.getParameter(field), request, helper,constraints);
    }

    /**
     * validation d'un champ de type Double
     * @param field
     * @param strValue
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Double parseDouble(String field, String strValue, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        Double oValue=null;
        if (strValue == null || strValue.length() == 0) {
            oValue= null;
        }else{
            try {
               oValue = new Double(strValue);
                if(constraints!=null){
                    constraints.checkConstraints(oValue,field,field,request);
                }
            } catch (NumberFormatException e) {
                FieldConstraintsException fce=new FieldConstraintsException("DOUBLE.BAD_FORMAT",strValue,field,field,request);
                if (helper != null) {
                    helper.setEditingData(field, strValue);
                    helper.addError(field, fce.getMessage());
                }
                throw fce;
            }
        }
        try {
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;
    }

    /**
     * validation d'un champ de type String[]
     * @param field
     * @param separator
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static String[] parseStringArray(String field, String separator, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseStringArray(field, request.getParameter(field), separator, request, helper,constraints);
    }

    /**
     * validation d'un champ de type String[]
     * @param field
     * @param strValue
     * @param separator
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static String[] parseStringArray(String field, String strValue, String separator, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        if (strValue == null || strValue.length() == 0) {
            return new String[0];
        }
        StringTokenizer st = new StringTokenizer(strValue, separator, true);
        boolean lastWasDelemeter = false;
        Vector elements = new Vector();
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (separator.indexOf(t) >= 0) {
                if (lastWasDelemeter) {
                    elements.add(null);
                }
                lastWasDelemeter = true;
            } else {
                lastWasDelemeter = false;
                elements.add(t);
            }
        }
        String[] oValue=(String[]) elements.toArray(new String[elements.size()]);
        try{
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;
    }


    /**
     * validation d'un champ de type String
     * @param field
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static String parseString(String field, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseString(field, request.getParameter(field), request, helper,constraints);
    }

    /**
     * validation d'un champ de type String
     * @param field
     * @param strValue
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static String parseString(String field, String strValue, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        String oValue=null;
        if (strValue == null || strValue.length() == 0) {
            oValue=null;
        }else{
            oValue=strValue;
        }
        try{
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;
    }

    /**
     * validation d'un champ de type Boolean sérialisé en tant que Entier 0 ou 1
     * @param field
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Integer parseBoolInt(String field, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        return parseBoolInt(field, request.getParameter(field), request, helper,constraints);
    }

    /**
     * validation d'un champ de type Boolean sérialisé en tant que Entier 0 ou 1
     * @param field
     * @param strValue
     * @param request
     * @param helper
     * @return
     * @throws camaieu.webform.FieldConstraintsException
     */
    public static Integer parseBoolInt(String field, String strValue, HttpServletRequest request, RecordEditorHelper helper,FieldConstraints constraints) throws FieldConstraintsException {
        if (helper != null) {
            helper.clearColumnErrors(field);
        }
        Integer oValue=null;
        if (strValue == null || strValue.length() == 0) {
            oValue=new Integer(0);
        }else{
            oValue=new Integer(1);
        }
        try{
            if(constraints!=null){
                constraints.checkConstraints(oValue,field,field,request);
            }
        } catch (FieldConstraintsException e) {
            if (helper != null) {
                helper.setEditingData(field, strValue);
                helper.addError(field, e.getMessage());
            }
            throw e;
        }
        return oValue;
    }

    /**
     * renvoie le message d'ereur internationalisé existant dans les fichiers properties sous la forme
     * <BR>
     * <CODE>
     * ERROR.errorKey=un message utilisant éventuellement {0 : strValue}, {1 : field}
     * </CODE>
     * @param errorKey
     * @param field
     * @param strValue
     * @param error
     * @param request
     * @return message d'ereur internationalisé
     */
    public static String getErrorMessage(String errorKey, String field, String strValue, Throwable error, HttpServletRequest request) {
        return MessageFormat.format(
                Messages.getString("ERROR." + errorKey, request)
                , new Object[]{WUtils.toDisplayable(strValue, "", 20), field, error.getMessage()});
    }
}

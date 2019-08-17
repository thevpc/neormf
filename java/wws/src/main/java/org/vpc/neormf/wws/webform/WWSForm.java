package org.vpc.neormf.wws.webform;

import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vpc.neormf.commons.beans.*;
import org.vpc.neormf.commons.util.Utils;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.LocalizedObject;
import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.common.WMessages;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlLabel;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Classe abstraite de base pour gerer des donnees d'un enregistrement
 * encapsulant des donnees en cours d'edition avec des references
 * sur les erreurs eventuelles
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 19/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public class WWSForm implements Serializable {

    private MultipartRequest multipartRequest;
    private HttpServletRequest request;
    private DataTransfertObject dataTransfertObject;
    private String prefix = "";
    /**
     * liste des erreurs
     */
    private ArrayList errors;
    /**
     * mapping des erreurs par champ
     */
    private Hashtable errorsMapper;

    /**
     * Les contraintes e verifier pour par champ
     * <BR>non encore utilisee
     */
//    private Hashtable constraints;
    /**
     * constructer par defaut
     */
    public WWSForm(HttpServletRequest request, DataTransfertObject dataTransfertObject) {
        setRequest(request);
        setDTO(dataTransfertObject);
    }

    public DataTransfertObject fillFromDTO(DataTransfertObject otherDataTransfertObject) {
        this.dataTransfertObject.addAllDeclaredProperties(otherDataTransfertObject);
        return dataTransfertObject;
    }

    public DataTransfertObject fillFromRequest(PropertyList fieldsPropertyList) {
        Utils.castTo(fieldsPropertyList, dataTransfertObject.info().getPropertyListClass());
        DataInfo dataInfo = dataTransfertObject.info();
        String[] fields = fieldsPropertyList.toArray();
        for (int i = 0; i < fields.length; i++) {
            DataField dataField = dataInfo.getField(fields[i]);
            DataType fieldType = dataField.getFieldType();
            DataTypeHtmlSupport support = DataTypeHtmlSupportFactory.getSupport(dataField, request);
            String[] allNames = support.getParameterNames(prefix + fields[i], fieldType, request);
            String[] svalue = new String[allNames.length];
            for (int j = 0; j < allNames.length; j++) {
                svalue[j] = getRequestParameter0(allNames[j]);
            }
            try {

                Object ovalue = support.convertHtmlStringToObject(svalue, fieldType, request);
                dataTransfertObject.setProperty(fields[i], ovalue);
            } catch (Exception e) {
                setEditingData(fields[i], svalue);
                addError(fields[i], e);
            }
        }
        return dataTransfertObject;
    }

    public Object getRequestParameter(String fieldName, DataType fieldType) {
        DataTypeHtmlSupport support = DataTypeHtmlSupportFactory.getSupport(fieldType);
        String[] allNames = support.getParameterNames(prefix + fieldName, fieldType, request);
        String[] svalue = new String[allNames.length];
        for (int j = 0; j < allNames.length; j++) {
            svalue[j] = getRequestParameter0(allNames[j]);
        }
        return support.convertHtmlStringToObject(svalue, fieldType, request);
    }

    public String getRequestParameter(String fieldName) {
        return getRequestParameter0(prefix + fieldName);
    }

    protected String getRequestParameter0(String fieldName) {
        System.out.println(">>>>>>>>> getRequestParameter0(" + fieldName + ")");
        if (isMultipart()) {
            System.out.println("\t multipart");
            if (multipartRequest == null) {
                System.out.println("\t multipartRequest null");
                String multipart_inbox = request.getSession().getServletContext().getRealPath("/multipart-inbox");
                String multipartTempFolder = multipart_inbox;
                new File(multipartTempFolder).mkdirs();
                System.out.println("\t multipartTempFolder : " + multipartTempFolder);
                try {
                    multipartRequest = new MultipartRequest(request, multipartTempFolder);
                } catch (IOException ex) {
                    Logger.getLogger(WWSForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("\t multipart created : ");
            }
            System.out.println("\t multipartRequest != null : " + (multipartRequest != null));
            if (multipartRequest != null) {
                File v = multipartRequest.getFile(fieldName);
                if (v != null) {
                    String mp = "/multipart-inbox/" + v.getName();
                    System.out.println(">>>>>>>>>>  getRequestParameter0/multipart :  " + mp);
                    return mp;
                }
                String param = multipartRequest.getParameter(fieldName);
                System.out.println(">>>>>>>>>>  multipartRequest.getParameter :  " + param);
                if (param != null) {
                    return param;
                }
            }
        }
        String param = request.getParameter(fieldName);
        System.out.println("\t request.getParameter(" + fieldName + ") : " + param);
        return param;
    }

    public String toHiddenValue(String field) {
        return toValue(field) + toHidden(field);
    }

    public String toHiddenValue(DataField field) {
        return toValue(field) + toHidden(field);
    }

    public String toHidden(String field) {
        return toHidden(dataTransfertObject.info().getField(field));
    }

    public String toHidden(DataField _field) {
        StringBuffer sb = new StringBuffer();
        String[] names = getHtmlSupport(_field).getParameterNames(prefix + _field.getFieldName(), _field.getFieldType(), request);
        String[] htmlForcode = generateHtmlForCode(_field.getFieldName());
        for (int i = 0; i < names.length; i++) {
            sb.append("<input name='").append(names[i]).append("' type='hidden' value='").append(htmlForcode[i]).append("'>");
        }
        return sb.toString();
    }

    public DataTypeHtmlSupport getHtmlSupport(DataField field) {
        return DataTypeHtmlSupportFactory.getSupport(field, request);
    }

    public DataTypeHtmlSupport getHtmlSupport(String field) {
        return DataTypeHtmlSupportFactory.getSupport(dataTransfertObject.info().getField(field), request);
    }

    public DataType getFieldType(String field) {
        return dataTransfertObject.info().getField(field).getFieldType();
    }

    public String[] generateHtmlForCode(DataField field) {
        return getHtmlSupport(field).convertObjectToHtmlStringArray(dataTransfertObject.getProperty(field.getFieldName()), field.getFieldType(), request);
    }

    public String[] generateHtmlForCode(String field) {
        return generateHtmlForCode(dataTransfertObject.info().getField(field));
    }

    /**
     * generates a HtmlWidget rendering field editor
     * @param fieldName
     * @return
     */
    public HtmlWidget toEditor(String fieldName) {
        return toEditor(
                fieldName,
                getEditingData(fieldName) == null ? getHtmlSupport(fieldName).convertObjectToHtmlStringArray(dataTransfertObject.getProperty(fieldName), getFieldType(fieldName), request)
                : getEditingData(fieldName),
                isCorrect(fieldName));
    }

    /**
     * generates a HtmlWidget rendering field editor
     * @param fieldName
     * @return
     */
    public HtmlWidget toEditor(DataField fieldName) {
        return toEditor(
                fieldName,
                getEditingData(fieldName) == null ? getHtmlSupport(fieldName).convertObjectToHtmlStringArray(dataTransfertObject.getProperty(fieldName.getFieldName()), fieldName.getFieldType(), request)
                : getEditingData(fieldName),
                isCorrect(fieldName));
    }

    /**
     * generates a HtmlWidget rendering field editor
     * @param field
     * @param value
     * @param isCorrect
     * @return
     */
    public HtmlWidget toEditor(String field, String[] value, boolean isCorrect) {
        return toEditor(dataTransfertObject.info().getField(field), value, isCorrect);
    }

    /**
     * generates a HtmlWidget rendering field editor
     * @param field
     * @param value
     * @param isCorrect
     * @return
     */
    public HtmlWidget toEditor(DataField field, String[] value, boolean isCorrect) {
        return getHtmlSupport(field).generateHtmlEditor(prefix + field.getFieldName(), value, isCorrect, field.getFieldType(), request);
    }

    /**
     * generates a satic html value rendering the field label
     * @param field
     * @return  HtmlLabel
     */
    public HtmlWidget toLabel(String field) {
        return toLabel(dataTransfertObject.info().getField(field));
    }

    /**
     * generates a satic html value rendering the field label
     * @param field
     * @return  HtmlLabel
     */
    public HtmlWidget toLabel(DataField dataField) {
        return new HtmlLabel(WMessages.getString("model."+dataField.getDataInfo().getBeanName()+"."+dataField.getFieldName()+".title", request, dataField.getFieldTitle()));
    }

    /**
     * generates a satic html value rendering the field value
     * @param field
     * @return  HtmlLabel
     */
    public HtmlWidget toValue(String field) {
        return toValue(dataTransfertObject.info().getField(field));
    }

    /**
     * generates a satic html value rendering the field value
     * @param field
     * @return  HtmlLabel
     */
    public HtmlWidget toValue(DataField field) {
        return getHtmlSupport(field).generateHtmlValue(prefix + field, dataTransfertObject.getProperty(field.getFieldName()), field.getFieldType(), request);
    }

    /**
     * new error declaration
     * @param columnName
     * @param error
     */
    public void addError(String columnName, Throwable error) {
        if (error instanceof LocalizedObject) {
            ((LocalizedObject) error).setLocale(WMessages.getLocale(request, true));
        }
        addError(columnName, error.getLocalizedMessage());
    }

    /**
     * new error declaration
     *
     * @param columnName
     * @param errorMessage
     */
    public void addError(String columnName, String errorMessage) {

        if (errors == null) {
            errors = new ArrayList();
        }
        errors.add(new GlobalError(columnName, errorMessage));
        Info c = getColumnCaracteristics(columnName, true);
        if (c.errors == null) {
            c.errors = new ArrayList(1);
        }
        c.errors.add(errorMessage);
    }

    /**
     * new global error declaration
     * @param error
     */
    public void addError(Throwable error) {
        if (error instanceof LocalizedObject) {
            ((LocalizedObject) error).setLocale(WMessages.getLocale(request, true));
        }
        addError(error.getLocalizedMessage());
    }

    /**
     * new global error declaration
     * @param errorMessage
     */
    public void addError(String errorMessage) {

        if (errors == null) {
            errors = new ArrayList();
        }
        errors.add(new GlobalError(null, errorMessage));
    }

    /**
     * definition de la valeur incorrecte saisie par l'utilisateur
     *
     * @param columnName nom de la coonne
     * @param data       donnee incorrecte
     */
    public void setEditingData(String columnName, String[] data) {
        Info e = getColumnCaracteristics(columnName, true);
        e.badValue = data;
    }

    /**
     * recuperation des donnees incrrectes saisies par l'utilisateur
     *
     * @param columnName
     * @return les donnees saisies
     */
    public String[] getEditingData(String columnName) {
        Info e = getColumnCaracteristics(columnName, false);
        return (e == null) ? null : e.badValue;
    }

    /**
     * recuperation des donnees incrrectes saisies par l'utilisateur
     *
     * @param columnName
     * @return les donnees saisies
     */
    public String[] getEditingData(DataField columnName) {
        Info e = getColumnCaracteristics(columnName.getFieldName(), false);
        return (e == null) ? null : e.badValue;
    }

    /**
     * nombre d'erreurs
     *
     * @return nombre d'erreurs
     */
    public int getErrorsCount() {
        return errors == null ? 0 : errors.size();
    }

    /**
     * la colonne de l'erreur e la position i
     *
     * @param i indexe de l'erreur
     * @return la colonne de l'erreur e la position i
     */
    public String getErrorColumnNameAt(int i) {
        return ((GlobalError) errors.get(i)).column;
    }

    /**
     * le message de l'erreur e la position i
     *
     * @param i indexe de l'erreur
     * @return le message de l'erreur e la position i
     */
    public String getErrorMessageAt(int i) {
        return ((GlobalError) errors.get(i)).message;
    }

    /**
     * vrai si au moins une erreur pour la colonne specifiee
     *
     * @param columnName
     * @return vrai si au moins une erreur pour la colonne specifiee
     */
    public boolean isIncorrect(String columnName) {
        return getErrorsCount(columnName) > 0;
    }

    /**
     * vrai si aucune erreur pour la colonne specifiee
     *
     * @param columnName
     * @return vrai si aucune erreur pour la colonne specifiee
     */
    public boolean isCorrect(String columnName) {
        return getErrorsCount(columnName) == 0;
    }

    /**
     * vrai si au moins une erreur pour la colonne specifiee
     *
     * @param columnName
     * @return vrai si au moins une erreur pour la colonne specifiee
     */
    public boolean isIncorrect(DataField columnName) {
        return getErrorsCount(columnName) > 0;
    }

    /**
     * vrai si aucune erreur pour la colonne specifiee
     *
     * @param columnName
     * @return vrai si aucune erreur pour la colonne specifiee
     */
    public boolean isCorrect(DataField columnName) {
        return getErrorsCount(columnName) == 0;
    }

    /**
     * Les erreurs pour le champ specifie
     *
     * @param columnName libelle du champ
     * @return tableau des erreurs
     */
    public String[] getErrors(String columnName) {
        Info e = getColumnCaracteristics(columnName, false);
        return (e == null || e.errors == null) ? new String[0] : (String[]) e.errors.toArray(new String[e.errors.size()]);
    }

    /**
     * Les erreurs pour le champ specifie
     *
     * @param columnName libelle du champ
     * @return tableau des erreurs
     */
    public String[] getErrors(DataField columnName) {
        Info e = getColumnCaracteristics(columnName, false);
        return (e == null || e.errors == null) ? new String[0] : (String[]) e.errors.toArray(new String[e.errors.size()]);
    }

    /**
     * Nbrd'erreurs pour le champ specifie
     *
     * @param columnName libelle du champ
     * @return nombre d'erreurs
     */
    public int getErrorsCount(String columnName) {
        Info e = getColumnCaracteristics(columnName, false);
        return (e == null || e.errors == null) ? 0 : e.errors.size();
    }

    /**
     * Nbrd'erreurs pour le champ specifie
     *
     * @param columnName libelle du champ
     * @return nombre d'erreurs
     */
    public int getErrorsCount(DataField columnName) {
        Info e = getColumnCaracteristics(columnName, false);
        return (e == null || e.errors == null) ? 0 : e.errors.size();
    }

    /**
     * vrai si au moins une erreur
     *
     * @return vrai si au moins une erreur
     */
    public boolean isIncorrect() {
        return errors != null && !errors.isEmpty();
    }

    /**
     * vrai si aucune erreur
     *
     * @return vrai si aucune erreur
     */
    public boolean isCorrect() {
        return errors == null || errors.isEmpty();
    }

    /**
     * remise e zero des erreurs deje declarees pour la colonne specifiee
     *
     * @param columnName libelle du champ
     */
    public void clearColumnErrors(String columnName) {
        if (errors != null && !errors.isEmpty()) {
            if (errorsMapper != null) {
                errorsMapper.remove(columnName);
            }
            for (int i = errors.size() - 1; i >= 0; i--) {
                if (columnName.equals(getErrorColumnNameAt(0))) {
                    errors.remove(i);
                }
            }
        }
    }

    /**
     * remise e zero de toutes les erreurs
     */
    public void clearErrors() {
        errors.clear();
        errors = null;
        errorsMapper.clear();
        errorsMapper = null;
    }

    /**
     * debut de wrapper pour la designation des erreurs dans un formulaire HTML
     *
     * @param columnName libelle du champ
     * @throws java.io.IOException si erreur dans out.print
     */
    public String getStartHtmlEditor(String columnName) throws IOException {
        if (isIncorrect(columnName)) {
            return ("<Table><TR BGCOLOR=\"RED\"><TD>");
        } else {
            return "";
        }
    }

    /**
     * fin de wrapper pour la designation des erreurs dans un formulaire HTML
     *
     * @param columnName libelle du champ
     * @throws java.io.IOException si erreur dans out.print
     */
    public String getEndHtmlEditor(String columnName) throws IOException {
        if (isIncorrect(columnName)) {
            return ("</TD></TR></TABLE>");
        } else {
            return "";
        }
    }

    /**
     * renvoie value si aucune erreur au niveau de champ specifie
     *
     * @param columnName libelle du champ
     * @param value      valeur correcte la plus recente
     */
    public String getHtmlEditingValue(String columnName, Object value) {
        return WUtils.toHtml(request,
                isCorrect(columnName) ? value : getEditingData(columnName),
                "", -1);
    }

    /**
     * alimenter la liste des erreurs avec les erreurs eventuellement retenus
     *
     * @param errorList : la liste des erreurs e alimenter
     */
    public final void fillErrorMessages(ErrorList errorList) {
        if (errors != null && errorList != null) {
            for (int i = 0; i < errors.size(); i++) {
                errorList.addError(getErrorMessageAt(i));
            }
        }
    }

    //-------------------------------------------
    // methodes utilitaire privees
    //-------------------------------------------
    /**
     * recuperation de toutes les infos pour le champ
     * <Br> si la propriete n'a pas de parent, ce dernier sera cree lorsque autoCreate est arme
     *
     * @param columnName libelle du champ
     * @param autoCreate vrai pour creation du parent
     * @return propriete privee
     */
    private Info getColumnCaracteristics(DataField columnName, boolean autoCreate) {
        return getColumnCaracteristics(columnName.getFieldName(), autoCreate);
    }

    private Info getColumnCaracteristics(String columnName, boolean autoCreate) {
        if (errorsMapper == null) {
            if (autoCreate) {
                errorsMapper = new Hashtable();
            } else {
                return null;
            }
        }
        Info m = (Info) errorsMapper.get(columnName);
        if (m == null) {
            if (autoCreate) {
                errorsMapper.put(columnName, m = new Info());
            } else {
                return null;
            }
        }
        return m;
    }

    public DataTransfertObject getDTO() {
        return dataTransfertObject;
    }

    public void setDTO(DataTransfertObject dataTransfertObject) {
        this.dataTransfertObject = dataTransfertObject;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private static class Info {

        ArrayList errors;
        String[] badValue;
    }

    private class GlobalError {

        String column;
        String message;

        public GlobalError(String column, String message) {
            this.column = column;
            this.message = message;
        }
    }

    public Object getProperty(String name) {
        return dataTransfertObject.getProperty(name);
    }

    public void setProperty(String name, Object value) {
        dataTransfertObject.setProperty(name, value);
    }

    public Iterator iterateProperty(String name) {
        return new WWSFormIterator((Collection) dataTransfertObject.getProperty(name), null);
    }

    public Iterator iterate(Collection collection) {
        return new WWSFormIterator(collection, this);
    }

    private class WWSFormIterator implements Iterator {

        private Iterator valuesIterator;
        private WWSForm prototype;

        public WWSFormIterator(Collection collection, WWSForm prototype) {
            valuesIterator = (collection == null ? Collections.EMPTY_LIST : collection).iterator();
            if (prototype == null) {
                prototype = new WWSForm(request, null);
            }
            this.prototype = prototype;
        }

        public void remove() {
            valuesIterator.remove();
        }

        public boolean hasNext() {
            if (valuesIterator.hasNext()) {
                prototype.setDTO((DataTransfertObject) valuesIterator.next());
                return true;
            }
            return false;
        }

        public Object next() {
            return prototype;
        }
    }

    public DataKey getDataKey() {
        return dataTransfertObject.getDataKey();
    }

    /**
     *
     * @return url part
     */
    public String toUrlCode() {
        DataField[] keyFields = dataTransfertObject.info().getKeyFields();
        StringBuffer sb = new StringBuffer();
        DataKey k = getDataKey();
        for (int i = 0; i < keyFields.length; i++) {
            DataField keyField = keyFields[i];
            String field = keyField.getFieldName();
            String[] htmlVals = getHtmlSupport(keyField.getFieldName()).convertObjectToHtmlStringArray(k.keyPartAt(i), getFieldType(keyField.getFieldName()), request);
            String[] names = getHtmlSupport(field).getParameterNames(prefix + field, dataTransfertObject.info().getField(field).getFieldType(), request);
            for (int j = 0; j < names.length; j++) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                //TODO use HttpServletResponse.encodeURL
                sb.append(names[j]).append("=").append(htmlVals[j]);
            }
        }
        return sb.toString();
    }

    public String toErrorList() {
        StringBuffer sb = new StringBuffer();
        if (isIncorrect()) {
            sb.append("<ul>\n");
            for (int i = 0; i < getErrorsCount(); i++) {
                sb.append("<li>").append(getErrorMessageAt(i)).append("</li>\n");
            }
            sb.append("</ul>\n");
        }
        return sb.toString();
    }

    public boolean isMultipart() {
        String type = null;
        String type1 = request.getHeader("Content-Type");
        String type2 = request.getContentType();
        if (type1 == null && type2 != null) {
            type = type2;
        } else if (type2 == null && type1 != null) {
            type = type1;
        } else if (type1 != null && type2 != null) {
            type = type1.length() <= type2.length() ? type2 : type1;
        }
        if (type == null || !type.toLowerCase().startsWith("multipart/form-data")) {
            return false;
        }
        return true;
    }
}

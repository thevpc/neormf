package camaieu.webform;

import camaieu.common.WUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Classe abstraite de base pour gérer des données d'un enregistrement
 * encapsulant des données en cours d'édition avec des références
 * sur les erreurs éventuelles
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 19/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public class RecordEditorHelper implements Serializable {

    /**
     * liste des erreurs
     */
    private Vector errors;

    /**
     * mapping des erreurs par champ
     */
    private Hashtable errorsMapper;

    /**
     * nombre initial d'élement (de colonnes) alloué dans ke Vector et la Map
     */
    private int columnsCount;

    /**
     * Les contraintes à vérifier pour par champ
     * <BR>non encore utilisée
     */
    private Hashtable constraints;

    /**
     * constructer par défaut
     */
    public RecordEditorHelper() {
        this(6);
    }

    /**
     * constructeur lorsqu'on connet le nombre de colonnes
     * @param theColumnsCount nombre initial d'élement (de colonnes) alloué dans ke Vector et la Map
     */
    public RecordEditorHelper(int theColumnsCount) {
        this.columnsCount = theColumnsCount;
    }

    /**
     * declaration d'une nouvelle erreur
     * @param columnName
     * @param error
     */
    public void addError(String columnName, String error) {

        if (errors == null) {
            errors = new Vector(columnsCount);
        }
        errors.add(new String[]{columnName, error});
        Object[] c = getColumnCaracteristics(columnName, true);
        if (c[0] == null) {
            c[0] = new Vector(1);
        }
        ((Vector) c[0]).add(error);
    }

    /**
     * définition de la valeur incorrecte saisie par l'utilisateur
     * @param columnName nom de la coonne
     * @param data donnée incorrecte
     */
    public void setEditingData(String columnName, String data) {
        Object[] c = getColumnCaracteristics(columnName, true);
        c[1] = data;
    }

    /**
     * récupération des données incrrectes saisies par l'utilisateur
     * @param columnName
     * @return les données saisies
     */
    public String getEditingData(String columnName) {
        return (String) getColumnCaracteristics(columnName, 1);
    }

    /**
     * nombre d'erreurs
     * @return nombre d'erreurs
     */
    public int getErrorsCount() {
        return errors == null ? 0 : errors.size();
    }

    /**
     * la colonne de l'erreur à la position i
     * @param i indexe de l'erreur
     * @return la colonne de l'erreur à la position i
     */
    public String getErrorColumnNameAt(int i) {
        return ((String[]) errors.get(i))[0];
    }

    /**
     * le message de l'erreur à la position i
     * @param i indexe de l'erreur
     * @return le message de l'erreur à la position i
     */
    public String getErrorMessageAt(int i) {
        return ((String[]) errors.get(i))[1];
    }

    /**
     * vrai si au moins une erreur pour la colonne spécifiée
     * @param columnName
     * @return vrai si au moins une erreur pour la colonne spécifiée
     */
    public boolean isIncorrect(String columnName) {
        return getErrorsCount(columnName) > 0;
    }

    /**
     * vrai si aucune erreur pour la colonne spécifiée
     * @param columnName
     * @return vrai si aucune erreur pour la colonne spécifiée
     */
    public boolean isCorrect(String columnName) {
        return getErrorsCount(columnName) == 0;
    }

    /**
     * Les erreurs pour le champ spécifié
     * @param columnName libellé du champ
     * @return tableau des erreurs
     */
    public String[] getErrors(String columnName) {
        Vector e = (Vector) getColumnCaracteristics(columnName, 0, false);
        return e == null ? new String[0] : (String[]) e.toArray(new String[e.size()]);
    }

    /**
     * Nbrd'erreurs pour le champ spécifié
     * @param columnName libellé du champ
     * @return nombre d'erreurs
     */
    public int getErrorsCount(String columnName) {
        Vector e = (Vector) getColumnCaracteristics(columnName, 0, false);
        return e == null ? 0 : e.size();
    }

    /**
     * vrai si au moins une erreur
     * @return vrai si au moins une erreur
     */
    public boolean isIncorrect() {
        return errors != null && errors.size() > 0;
    }

    /**
     * vrai si aucune erreur
     * @return vrai si aucune erreur
     */
    public boolean isCorrect() {
        return errors == null || errors.size() == 0;
    }

    /**
     * remise à zéro des erreurs déjà declarées pour la colonne spécifiée
     * @param columnName libellé du champ
     */
    public void clearColumnErrors(String columnName) {
        if (errors != null && errors.size() > 0) {
            if (errorsMapper != null) {
                errorsMapper.remove(columnName);
            }
            for (int i = errors.size() - 1; i >= 0; i--) {
                if (columnName.equals(((String[]) errors.get(i))[0])) {
                    errors.remove(i);
                }
            }
        }
    }

    /**
     * remise à zéro de toutes les erreurs
     */
    public void clearErrors() {
        errors.clear();
        errors = null;
        errorsMapper.clear();
        errorsMapper = null;
    }

    /**
     * début de wrapper pour la désignation des erreurs dans un formulaire HTML
     *
     * @param request de la jsp
     * @param out de la jsp
     * @param columnName libellé du champ
     * @throws java.io.IOException si erreur dans out.print
     */
    public void startHtmlEditor(HttpServletRequest request, JspWriter out, String columnName) throws IOException {
        if (isIncorrect(columnName)) {
            out.print("<Table><TR BGCOLOR=\"RED\"><TD>");
        }
    }

    /**
     * fin de wrapper pour la désignation des erreurs dans un formulaire HTML
     *
     * @param request de la jsp
     * @param out de la jsp
     * @param columnName libellé du champ
     * @throws java.io.IOException si erreur dans out.print
     */
    public void endHtmlEditor(HttpServletRequest request, JspWriter out, String columnName) throws IOException {
        if (isIncorrect(columnName)) {
            out.print("</TD></TR></TABLE>");
        }
    }

    /**
     * renvoie value si aucune erreur au niveau de champ spécifié
     *
     * @param request de la jsp
     * @param columnName libellé du champ
     * @param value valeur correcte la plus récente
     */
    public String getHtmlEditingValue(HttpServletRequest request, String columnName, Object value) {
        return WUtils.toHtml(request,
                isCorrect(columnName) ? value : getEditingData(columnName),
                "", -1);
    }

    /**
     * alimenter la liste des erreurs avec les erreurs eventuellement retenus
     * @param errorList : la liste des erreurs à alimenter
     */
    public final void fillErrorMessages(ErrorList errorList) {
        if (errors != null && errorList != null) {
            for (int i = 0; i < errors.size(); i++) {
                errorList.addError(getErrorMessageAt(i));
            }
        }
    }

    //-------------------------------------------
    // methodes utilitaire privées
    //-------------------------------------------

    /**
     * récupération des infos à la position donnée pour le champ
     * @param columnName liobellé du champ
     * @param position indexe
     * @return propriété privée
     */
    private Object getColumnCaracteristics(String columnName, int position) {
        return getColumnCaracteristics(columnName, position, false);
    }

    /**
     * récupération des infos à la position donnée pour le champ
     * <B> si la propriété n'a pas de parent, ce dernier sera crée lorsque autoCreate est armé
     * @param columnName libellé du champ
     * @param position indexe
     * @param autoCreate vrai pour création du parent
     * @return propriété privée
     */
    private Object getColumnCaracteristics(String columnName, int position, boolean autoCreate) {
        Object[] c = getColumnCaracteristics(columnName, autoCreate);
        return c == null ? null : c[position];
    }

    /**
     * récupération de toutes les infos pour le champ
     * <B> si la propriété n'a pas de parent, ce dernier sera crée lorsque autoCreate est armé
     * @param columnName libellé du champ
     * @param autoCreate vrai pour création du parent
     * @return propriété privée
     */
    private Object[] getColumnCaracteristics(String columnName, boolean autoCreate) {
        if (errorsMapper == null) {
            if (autoCreate) {
                errorsMapper = new Hashtable(columnsCount);
            } else {
                return null;
            }
        }
        Object[] m = (Object[]) errorsMapper.get(columnName);
        if (m == null) {
            if (autoCreate) {
                errorsMapper.put(columnName, m = new Object[2]);
            } else {
                return null;
            }
        }
        return m;
    }

}

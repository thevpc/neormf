package camaieu.webform;

import javax.servlet.http.HttpServletRequest;


/**
 * FieldConstraints pour la combinaison 'ET' des Field Constraints
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */

public class IntersectFieldConstraints implements FieldConstraints {
    /**
     * liste des contraintes
     */
    private FieldConstraints[] constraints;

    /**
     * constructeur
     * @param newConstraints
     */
    public IntersectFieldConstraints(FieldConstraints[] newConstraints) {
        constraints = newConstraints;
    }

    /**
     *@see  FieldConstraints#checkConstraints(java.lang.Object, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
     */
    public void checkConstraints(Object value, String fieldId, String fieldName, HttpServletRequest request) throws FieldConstraintsException {
        for (int i = 0; i < constraints.length; i++) {
            constraints[i].checkConstraints(value, fieldId, fieldName, request);
        }
    }
}

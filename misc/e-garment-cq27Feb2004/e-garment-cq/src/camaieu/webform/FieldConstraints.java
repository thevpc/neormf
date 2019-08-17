package camaieu.webform;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface de validation des champs
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */

public interface FieldConstraints {
    /**
     * vérification des contraintes
     * @param value
     * @throws camaieu.webform.FieldConstraintsException
     */
    public void checkConstraints(Object value,String fieldId,String fieldName,HttpServletRequest request) throws FieldConstraintsException;
}

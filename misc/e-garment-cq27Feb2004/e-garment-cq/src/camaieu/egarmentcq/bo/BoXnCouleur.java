package camaieu.egarmentcq.bo;

import camaieu.common.BoEasyAncestorBean;
import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.dataobject.DoXnCouleur;

/**
 * Business Object pour la manipulation des couleurs
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 20/01/2004
 * @last_modification_date 20/01/2004
 * @status pour validation
 */
public class BoXnCouleur extends BoEasyAncestorBean {
    public BoXnCouleur() {
        super(DoXnCouleur.class, EGCQBusinessConstants.DATASOURCE_NAME);
    }
}

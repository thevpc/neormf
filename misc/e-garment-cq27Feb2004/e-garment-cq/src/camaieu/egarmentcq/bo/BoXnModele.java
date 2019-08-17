package camaieu.egarmentcq.bo;

import camaieu.common.BoEasyAncestorBean;
import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.dataobject.DoXnModele;

/**
 * Business Object pour la manipulation des modèles d'articles
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 20/01/2004
 * @last_modification_date 20/01/2004
 * @status pour validation
 */
public class BoXnModele extends BoEasyAncestorBean {

    /**
     * constructeur par défaut
     */
    public BoXnModele() {
        super(DoXnModele.class, EGCQBusinessConstants.DATASOURCE_NAME);
    }
}

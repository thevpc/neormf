package camaieu.egarmentcq.bo;

import camaieu.common.BoEasyAncestorBean;
import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.dataobject.DoXnFour;

/**
 * Business Object pour la manipulation des fournisseurs.
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 15/01/2004
 * @last_modification_date 22/01/2004
 * @status pour validation
 */
public class BoXnFour extends BoEasyAncestorBean {
    public BoXnFour() {
        super(DoXnFour.class, EGCQBusinessConstants.DATASOURCE_NAME);
    }

//  cette partie n'est plus nécessaire car désormais hérite de BoEasyAncestorBean
// -------------------------------------------------------------------------------
//    /**
//     * récupérer toutes les informations d'un Fournisseur à partir de son code
//     * @param code
//     * @return un DoXnFour contenant toutes les informations d'un Fournisseur
//     * @throws MetierException
//     */
//    public DoXnFour getSupplierByCode(String code) throws MetierException {
//        DoXnFour[] doXnFours = null;
//        try {
//
//// pour fixer un bug (NullPointerException) dans le fwk camaieu
//            if (code == null) {
//                code = "";
//            }
//
//            doXnFours = (DoXnFour[]) retrieve(
//                    EGCQBusinessConstants.DATASOURCE_NAME,
//                    "select * from XN_FOUR where FOU_CODE = ? ",
//                    new Object[]{code},
//                    new Class[]{DoXnFour.class}
//            )[0];
//            if (doXnFours.length > 0) {
//                return doXnFours[0];
//            }
//        } catch (TechniqueException e) {
//            throw new MetierException(e.getMessage());
//        }
//        return null;
//    }

}

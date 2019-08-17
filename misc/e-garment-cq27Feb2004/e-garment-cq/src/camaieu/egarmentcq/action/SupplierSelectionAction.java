package camaieu.egarmentcq.action;

import camaieu.egarmentcq.common.EGCQSessionConstants;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action pour rediriger l'administrateur vers le sous site
 * des fournisseurs
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 14/01/2003
 * @creation_date 16/01/2003
 * @status pour validation
 */
public class SupplierSelectionAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(SupplierSelectionAction.class.getName());

    /**
     * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        String supplierCode = request.getParameter(EGCQSessionConstants.SUPPLIER_CODE);

        // aucune verification de la validité du code soumis comme il a est été précisé
        // dans le document de spécification
        request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_CODE, supplierCode);
        log.info(supplierCode+" ] setting supplier to "+supplierCode);
        return mapping.findForward("ok");
    }

}

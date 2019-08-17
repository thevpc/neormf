package camaieu.egarmentcq.action;

import camaieu.egarmentcq.common.EGCQSessionConstants;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wg4.fwk.context.Profil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe permettant de fermer (invalider) la session courant
 * et de rediriger vers la page "exit".
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 13/01/2003
 * @last_modification_date 13/01/2003
 * @status pour validation
 */
public class LogoutAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(LogoutAction.class.getName());

    /**
     * @see Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {


        Profil lastUser = (Profil) request.getSession().getAttribute(EGCQSessionConstants.PROFILE);
        String supplierCode = (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE);

        request.getSession().setAttribute(EGCQSessionConstants.PROFILE, null);
        request.getSession().setAttribute(EGCQSessionConstants.IS_ADMIN, null);
        request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_CODE, null);
        request.getSession().invalidate();
        log.info("<LogoutAction for " + (lastUser == null ? "?UNKNOWN?" : lastUser.getId()) + " (supplierCode="+supplierCode+")>");
        return mapping.findForward("exit");
    }

}

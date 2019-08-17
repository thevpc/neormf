package camaieu.egarmentcq.action;

import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import camaieu.egarmentcq.bo.BoXnFour;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.dataobject.DoXnFour;
import camaieu.common.WUtils;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action pour préparer les données à afficher dans planning.jsp
 * en l'occurence on retrouvera deux valeurs dans le request correspondant
 * à EGCQRequestConstants.SUPPLIER_DO et EGCQRequestConstants.SUPPLIER_PLANNING.
 * <BR>
 * Pour le mapping avec EGCQRequestConstants.SUPPLIER_DO on trouvera une instance
 * de DoXnFour du prestatire de service connecté.
 * <BR>
 * Pour le mapping avec EGCQRequestConstants.SUPPLIER_PLANNING on trouvera un tableau
 * d'instances de  PlanningAction.CommandeData contenant les information nécessaires
 * à l'affichage.
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 15/01/2003
 * @creation_date 15/01/2003
 * @status pour validation
 */
public class PlanningAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(PlanningAction.class.getName());

    /**
     * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        String supplierCode = (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE);
//        if(supplierCode==null){
//            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
//            return mapping.findForward("standard_error");
//        }

        String exportType = request.getParameter(EGCQSessionConstants.EXPORT_TYPE);
        BoXnFour boXnFour = new BoXnFour();
        DoXnFour doXnFour = new DoXnFour();
        doXnFour.setFouCode(supplierCode == null ? "" : supplierCode);
        doXnFour = (DoXnFour) boXnFour.reload(doXnFour);
        request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_DO, doXnFour);
        if (doXnFour != null) {
            BoDtCmdeAchatTete boDtCmdeAchatTete = new BoDtCmdeAchatTete();
            request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_PLANNING, boDtCmdeAchatTete.getCommandesData(supplierCode));
        } else {
            request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_PLANNING, new BoDtCmdeAchatTete.CommandeData[0]);
        }
        log.info(supplierCode+" ] "+(exportType==null? "load planning" : "export planning"));
        return mapping.findForward(exportType == null ? "ok" : exportType);
    }

}

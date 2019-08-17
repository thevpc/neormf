package camaieu.egarmentcq.action;

import camaieu.egarmentcq.bo.BoDtEnvoiCqSource;
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
 * Action pour préparer les données à afficher dans items_delivery_receipts.jsp
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 19/01/2003
 * @creation_date 20/01/2003
 * @status pour validation
 */
public class ItemsDeliveryReceiptsAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(ItemsDeliveryReceiptsAction.class.getName());

    /**
     * @see Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
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
        DoXnFour doXnFour = new DoXnFour();
        doXnFour.setFouCode(supplierCode == null ? "" : supplierCode);
        BoXnFour boXnFour = new BoXnFour();
        doXnFour = (DoXnFour) boXnFour.reload(doXnFour);
        request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_DO, doXnFour);
        if (doXnFour != null) {
            BoDtEnvoiCqSource boDtEnvoiCqSource = new BoDtEnvoiCqSource();
            request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_ITEM_DELIVERY_RECEIPTS, boDtEnvoiCqSource.getItemDeliveryReceiptsData(supplierCode));
        } else {
            request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_ITEM_DELIVERY_RECEIPTS, new BoDtEnvoiCqSource.ItemDeliveryReceiptData[0]);
        }
        log.info(supplierCode+" ] "+(exportType==null? "load delivery receipts" : "export delivery receipts"));
        return mapping.findForward(exportType == null ? "ok" : exportType);
    }

}

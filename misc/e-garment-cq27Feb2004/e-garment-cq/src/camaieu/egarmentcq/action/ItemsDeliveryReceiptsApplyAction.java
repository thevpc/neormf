package camaieu.egarmentcq.action;

import camaieu.egarmentcq.bo.BoDtEnvoiCqSource;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.dataobject.DoDtEnvoiCqSource;
import camaieu.webform.ErrorList;
import camaieu.webform.FieldConstraintsException;
import camaieu.webform.ValidatorUtils;
import camaieu.common.WUtils;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wg4.fwk.dataobject.DataObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Action pour préparer les données à afficher dans inspection_report.jsp
 * <BR>
 * Elle permet de mettre dans EGCQRequestConstants.C
 * de DoXnFour du prestatire de service connecté.
 * <BR>
 * Pour le mapping avec EGCQRequestConstants.SUPPLIER_PLANNING on trouvera un tableau
 * d'instances de  PlanningAction.CommandeData contenant les information nécessaires
 * à l'affichage.
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 19/01/2003
 * @creation_date 20/01/2003
 * @status pour validation
 */
public class ItemsDeliveryReceiptsApplyAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(ItemsDeliveryReceiptsApplyAction.class.getName());

    /**
     * @see Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        BoDtEnvoiCqSource.ItemDeliveryReceiptData[] receiptData = (BoDtEnvoiCqSource.ItemDeliveryReceiptData[]) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_ITEM_DELIVERY_RECEIPTS);
        DoDtEnvoiCqSource[] cqSources = new DoDtEnvoiCqSource[receiptData.length];
        String[] ecsDtRecepEchan = request.getParameterValues("ecsDtRecepEchan");
        String[] ecsNoColisEchan = request.getParameterValues("ecsNoColisEchan");
        String[] ecsDtRecepBib = request.getParameterValues("ecsDtRecepBib");
        String[] ecsNoColisBib = request.getParameterValues("ecsNoColisBib");
        String[] ecsDtRecepDtech = request.getParameterValues("ecsDtRecepDtech");
        String[] ecsNoColisDtech = request.getParameterValues("ecsNoColisDtech");

        if(receiptData==null){
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
            return mapping.findForward("standard_error");
        }

        Timestamp serverDate = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < receiptData.length; i++) {
            cqSources[i] = receiptData[i].getDoDtEnvoiCqSource();
            cqSources[i].setPersist(DataObject.PERSIST_UPDATE);
            try {
                if (ecsDtRecepEchan != null && ecsDtRecepEchan[i] != null && ecsDtRecepEchan[i].length() > 0) {
                    cqSources[i].setEcsDtRecepEchan(serverDate);
                    receiptData[i].setEcsDtRecepEchan(serverDate);
                }
                cqSources[i].setEcsNoColisEchan(ValidatorUtils.parseString("ecsNoColisEchan", ecsNoColisEchan[i], request, receiptData[i].getHelper(),null));
                receiptData[i].setEcsNoColisEchan(cqSources[i].getEcsNoColisEchan());
            } catch (FieldConstraintsException e) {
            }
            try {
                if (ecsDtRecepBib != null && ecsDtRecepBib[i] != null && ecsDtRecepBib[i].length() > 0) {
                    cqSources[i].setEcsDtRecepBib(serverDate);
                    receiptData[i].setEcsDtRecepBib(serverDate);
                }
                cqSources[i].setEcsNoColisBib(ValidatorUtils.parseString("ecsNoColisBib", ecsNoColisBib[i], request, receiptData[i].getHelper(),null));
                receiptData[i].setEcsNoColisBib(cqSources[i].getEcsNoColisBib());
            } catch (FieldConstraintsException e) {
            }
            try {
                if (ecsDtRecepDtech != null && ecsDtRecepDtech[i] != null && ecsDtRecepDtech[i].length() > 0) {
                    cqSources[i].setEcsDtRecepDtech(serverDate);
                    receiptData[i].setEcsDtRecepDtech(serverDate);
                }
                cqSources[i].setEcsNoColisDtech(ValidatorUtils.parseString("ecsNoColisDtech", ecsNoColisDtech[i], request, receiptData[i].getHelper(),null));
                receiptData[i].setEcsNoColisDtech(cqSources[i].getEcsNoColisDtech());
            } catch (FieldConstraintsException e) {
            }
        }

        ErrorList errorList = new ErrorList(false);
        for (int i = 0; i < receiptData.length; i++) {
            receiptData[i].getHelper().fillErrorMessages(errorList);
        }

        if (errorList.isEmpty()) {
            BoDtEnvoiCqSource boDtEnvoiCqSource = new BoDtEnvoiCqSource();
            boDtEnvoiCqSource.update(cqSources);
            String supplierCode = (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE);
            log.info(supplierCode+" ] update delivery receipts");
            return mapping.findForward("ok");
        } else {
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, errorList.toHtml());
            return mapping.findForward("error");
        }
    }

}

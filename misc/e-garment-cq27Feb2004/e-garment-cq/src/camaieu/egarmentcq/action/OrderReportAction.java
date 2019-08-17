package camaieu.egarmentcq.action;

import camaieu.common.WUtils;
import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.dataobject.DoDtCmdeAchatTete;
import camaieu.egarmentcq.report.OrderReport;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action de génération des rapports (pdf)
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 23/01/2003
 * @creation_date 23/01/2003
 * @status pour validation
 */
public class OrderReportAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(OrderReportAction.class.getName());

    /**
     * @see Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        BoDtCmdeAchatTete.CommandeData[] commandes = (BoDtCmdeAchatTete.CommandeData[]) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_PLANNING);
        if(commandes==null){
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
            return mapping.findForward("standard_error");
        }
        int index = 0;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            Object o = commandes[index]; // juste pour tester l'indice
        } catch (Throwable e) {
            log.error(e.toString(),e);
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
            return mapping.findForward("standard_error");
        }

        DoDtCmdeAchatTete doDtCmdeAchatTete = new DoDtCmdeAchatTete();
        doDtCmdeAchatTete.setCatDepCode(commandes[index].getCatDepCode());
        doDtCmdeAchatTete.setCatDepSocCode(commandes[index].getCatDepSocCode());
        doDtCmdeAchatTete.setCatNoCmde(commandes[index].getCatNoCmde());
        doDtCmdeAchatTete.setCatNoVersion(commandes[index].getCatNoVersion());

        BoDtCmdeAchatTete boDtCmdeAchatTete = new BoDtCmdeAchatTete();
        OrderReport.OrderReportData data = boDtCmdeAchatTete.getOrderReportData(doDtCmdeAchatTete,WUtils.getLanguage(request));
        OrderReport orderReport = new OrderReport();
        orderReport.setData(data);

        request.setAttribute("pdf", orderReport);
        String supplierCode = (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE);
        log.info(supplierCode+" ] build order report");
        orderReport.build(request, response);
//        return mapping.findForward("ok");
        return null;
    }

}

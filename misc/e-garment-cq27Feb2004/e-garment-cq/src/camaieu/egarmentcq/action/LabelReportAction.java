package camaieu.egarmentcq.action;

import camaieu.common.WUtils;
import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import camaieu.egarmentcq.bo.BoXnArt;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.dataobject.DoXnArt;
import camaieu.egarmentcq.report.LabelReport;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action de génération des étiquettes (pdf)
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 23/01/2003
 * @creation_date 23/01/2003
 * @status pour validation
 */
public class LabelReportAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(LabelReportAction.class.getName());

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
        int index = 0;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            Object o = commandes[index]; // juste pour tester l'indice
        } catch (Throwable e) {
            log.error(e.toString(),e);
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
            return mapping.findForward("standard_error");
        }

        DoXnArt doXnArt = new DoXnArt();
        doXnArt.setArtCode(commandes[index].getCamArtCode());
        doXnArt.setArtVar1(commandes[index].getCamArtVar1());
        doXnArt.setArtVar2(commandes[index].getCamArtVar2());
        doXnArt.setArtVar3(commandes[index].getCamArtVar3());

        BoXnArt boXnArt = new BoXnArt();
        LabelReport.LabelReportData data=  boXnArt.getLabelReportData(doXnArt,WUtils.getLanguage(request));
        LabelReport labelReport = new LabelReport();
        labelReport.setData(data);

        String supplierCode = (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE);
        log.info(supplierCode+" ] build label pdf");
        labelReport.build(request, response);
        return null;
    }

}

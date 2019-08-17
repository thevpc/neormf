package camaieu.egarmentcq.action;

import camaieu.common.WUtils;
import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import camaieu.egarmentcq.bo.BoDtRecepCtrlSourceTete;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.common.InspectionReportUploadHelper;
import camaieu.egarmentcq.dataobject.DoDtCmdeAchatTete;
import camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceTete;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class InspectionReportAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(InspectionReportAction.class.getName());

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

        DoDtRecepCtrlSourceTete doDtRecepCtrlSourceTete = new DoDtRecepCtrlSourceTete();
        doDtRecepCtrlSourceTete.setRstCamCatDepCode(commandes[index].getCatDepCode());
        doDtRecepCtrlSourceTete.setRstCamCatDepSocCode(commandes[index].getCatDepSocCode());
        doDtRecepCtrlSourceTete.setRstCamNoCmde(commandes[index].getCatNoCmde());
        doDtRecepCtrlSourceTete.setRstCamCatNoVersion(commandes[index].getCatNoVersion());
        doDtRecepCtrlSourceTete.setRstCamNoLigne(commandes[index].getCamNoLigne());
        doDtRecepCtrlSourceTete.setRstCamArtCode(commandes[index].getCamArtCode());
        doDtRecepCtrlSourceTete.setRstCamArtVar1(commandes[index].getCamArtVar1());
        doDtRecepCtrlSourceTete.setRstCamArtVar2(commandes[index].getCamArtVar2());
        doDtRecepCtrlSourceTete.setRstCamArtVar3(commandes[index].getCamArtVar3());
        doDtRecepCtrlSourceTete.setRstNoControle(commandes[index].getRstNoControle());

        BoDtRecepCtrlSourceTete boDtRecepCtrlSourceTete = new BoDtRecepCtrlSourceTete();
        doDtRecepCtrlSourceTete = boDtRecepCtrlSourceTete.reload(doDtRecepCtrlSourceTete);
        doDtRecepCtrlSourceTete.setExtraArtCode(commandes[index].getCamArtCode());
        doDtRecepCtrlSourceTete.setExtraArtName(commandes[index].getModDes());
        doDtRecepCtrlSourceTete.setExtraColorCode(commandes[index].getCamArtVar1());
        doDtRecepCtrlSourceTete.setExtraColorName(commandes[index].getCouLib());
        doDtRecepCtrlSourceTete.setExtraInspectionType(commandes[index].getRstNoControle());
        request.getSession().setAttribute(EGCQSessionConstants.DO_DT_RECEPT_CTRL_SOURCE_TETE, doDtRecepCtrlSourceTete);


        BoDtCmdeAchatTete boDtCmdeAchatTete=new BoDtCmdeAchatTete();
        DoDtCmdeAchatTete doDtCmdeAchatTete=new DoDtCmdeAchatTete();
        doDtCmdeAchatTete.setCatNoVersion(doDtRecepCtrlSourceTete.getRstCamCatNoVersion());
        doDtCmdeAchatTete.setCatNoCmde(doDtRecepCtrlSourceTete.getRstCamNoCmde());
        doDtCmdeAchatTete.setCatDepCode(doDtRecepCtrlSourceTete.getRstCamCatDepCode());
        doDtCmdeAchatTete.setCatDepSocCode(doDtRecepCtrlSourceTete.getRstCamCatDepSocCode());
        doDtCmdeAchatTete=(DoDtCmdeAchatTete) boDtCmdeAchatTete.reload(doDtCmdeAchatTete);
        InspectionReportUploadHelper fileUploadHelper=new InspectionReportUploadHelper(
                getServlet().getServletContext(),
                (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE),
                doDtCmdeAchatTete.getCatFouCode(),
                doDtRecepCtrlSourceTete.getRstCamArtCode(),
                doDtRecepCtrlSourceTete.getRstCamArtVar1(),
                String.valueOf(doDtRecepCtrlSourceTete.getRstCamNoCmde())
                );
        request.setAttribute(EGCQRequestConstants.UPLOAD_HELPER,fileUploadHelper);
        log.info(supplierCode+" ] load inspection report");
        return mapping.findForward("ok");
    }

}

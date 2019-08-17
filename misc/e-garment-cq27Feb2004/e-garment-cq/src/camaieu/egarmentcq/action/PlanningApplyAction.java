package camaieu.egarmentcq.action;

import camaieu.common.WUtils;
import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import camaieu.egarmentcq.bo.BoDtRecepCtrlSourceTete;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceTete;
import camaieu.webform.ErrorList;
import camaieu.webform.FieldConstraintsException;
import camaieu.webform.ValidatorUtils;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wg4.bean.ancestor.MetierException;
import wg4.fwk.dataobject.DataObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Action pour sauver les modifications sur le planning puis rediriger vers planning.jsp
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 16/01/2003
 * @creation_date 16/01/2003
 * @status pour validation
 */
public class PlanningApplyAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(PlanningApplyAction.class.getName());

    /**
     * @see Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        String[] rstDtCtrlSourceSouhait = request.getParameterValues("rstDtCtrlSourceSouhait");

// dans le cas où le tableau des commandes est vide
        if (rstDtCtrlSourceSouhait == null) {
            return mapping.findForward("ok");
        }
//        DoXnFour doXnFour=(DoXnFour) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_DO);
        BoDtCmdeAchatTete.CommandeData[] commandes = (BoDtCmdeAchatTete.CommandeData[]) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_PLANNING);

        DoDtRecepCtrlSourceTete[] doDtRecepCtrlSourceTetes = new DoDtRecepCtrlSourceTete[rstDtCtrlSourceSouhait.length];
        for (int i = 0; i < doDtRecepCtrlSourceTetes.length; i++) {
            doDtRecepCtrlSourceTetes[i] = new DoDtRecepCtrlSourceTete(DataObject.PERSIST_UPDATE);
//clef primaire
            doDtRecepCtrlSourceTetes[i].setRstCamCatDepCode(commandes[i].getCatDepCode());
            doDtRecepCtrlSourceTetes[i].setRstCamCatDepSocCode(commandes[i].getCatDepSocCode());
            doDtRecepCtrlSourceTetes[i].setRstCamNoCmde(commandes[i].getCatNoCmde());
            doDtRecepCtrlSourceTetes[i].setRstCamCatNoVersion(commandes[i].getCatNoVersion());
            doDtRecepCtrlSourceTetes[i].setRstCamNoLigne(commandes[i].getCamNoLigne());
            doDtRecepCtrlSourceTetes[i].setRstCamArtCode(commandes[i].getCamArtCode());
            doDtRecepCtrlSourceTetes[i].setRstCamArtVar1(commandes[i].getCamArtVar1());
            doDtRecepCtrlSourceTetes[i].setRstCamArtVar2(commandes[i].getCamArtVar2());
            doDtRecepCtrlSourceTetes[i].setRstCamArtVar3(commandes[i].getCamArtVar3());
            doDtRecepCtrlSourceTetes[i].setRstNoControle(commandes[i].getRstNoControle());

            //champs à metre à jour
            try {
                Timestamp dtCtrlSourceSouhait = ValidatorUtils.parseTimestamp("rstDtCtrlSourceSouhait", rstDtCtrlSourceSouhait[i], request, commandes[i].getHelper(),null);
                commandes[i].setRstDtCtrlSourceSouhait(dtCtrlSourceSouhait);
                doDtRecepCtrlSourceTetes[i].setRstDtCtrlSourceSouhait(dtCtrlSourceSouhait);
            } catch (FieldConstraintsException e) {
            }
        }

        ErrorList errorList = new ErrorList(false);
        for (int i = 0; i < commandes.length; i++) {
            commandes[i].getHelper().fillErrorMessages(errorList);
        }

        if (!errorList.isEmpty()) {
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, errorList.toHtml());
            return mapping.findForward("error");
        } else {
            BoDtRecepCtrlSourceTete boDtRecepCtrlSourceTete = new BoDtRecepCtrlSourceTete();
            try {
                int updated = boDtRecepCtrlSourceTete.update(doDtRecepCtrlSourceTetes);
                if (updated > 0) {
                    String supplierCode = (String) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_CODE);
                    log.info(supplierCode+" ] update planning");
                    return mapping.findForward("ok");
                } else {
                    log.error("Could not update RecepCtrlSourceTete");
                    request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.NO_UPDATES",-1));
                    return mapping.findForward("error");
                }
            } catch (MetierException e) {
                request.setAttribute(EGCQRequestConstants.ERROR_MSG, e.getMessage());
                return mapping.findForward("error");
            }

        }
    }

}

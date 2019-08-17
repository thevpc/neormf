package camaieu.egarmentcq.action;

import camaieu.common.StringUtils;
import camaieu.common.WUtils;
import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import camaieu.egarmentcq.bo.BoDtRecepCtrlSourceTete;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.common.InspectionReportUploadHelper;
import camaieu.egarmentcq.dataobject.DoDtCmdeAchatTete;
import camaieu.egarmentcq.dataobject.DoDtRecepCtrlSource;
import camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceColis;
import camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceTete;
import camaieu.upload.IllegalFileUploadedException;
import camaieu.webform.ErrorList;
import camaieu.webform.FieldConstraintsException;
import camaieu.webform.FieldConstraintsFactory;
import camaieu.webform.RecordEditorHelper;
import camaieu.webform.ValidatorUtils;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wg4.fwk.dataobject.DataObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

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
public class InspectionReportApplyAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(InspectionReportApplyAction.class.getName());

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
        DoDtRecepCtrlSourceTete doDtRecepCtrlSourceTete = (DoDtRecepCtrlSourceTete) request.getSession().getAttribute(EGCQSessionConstants.DO_DT_RECEPT_CTRL_SOURCE_TETE);

        if(doDtRecepCtrlSourceTete==null){
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
            return mapping.findForward("standard_error");
        }

        DoDtRecepCtrlSourceColis[] colis = doDtRecepCtrlSourceTete.getDtRecepCtrlSourceColis();
        DoDtRecepCtrlSource[] test = doDtRecepCtrlSourceTete.getDtRecepCtrlSources();
        doDtRecepCtrlSourceTete.resetUpdate();
        RecordEditorHelper helper = doDtRecepCtrlSourceTete.getHelper();
        ErrorList errorList = new ErrorList(false);

        try {
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
            fileUploadHelper.setRequest(request);
            String actionType=fileUploadHelper.getMultipartRequest().getParameter("actionType");
            if(actionType==null){
                actionType="";
            }
            boolean addFile="addFile".equals(actionType);
            int deleteFileIndex=actionType.startsWith("deleteFile:")?Integer.parseInt(actionType.substring("deleteFile:".length())) : -1;

            try {
                doDtRecepCtrlSourceTete.setRstDtCtrlSourceLabo(ValidatorUtils.parseTimestamp("rstDtCtrlSourceLabo",fileUploadHelper.getMultipartRequest().getParameter("rstDtCtrlSourceLabo"), request, helper,null));
            } catch (FieldConstraintsException e) {
            }

            try {
                doDtRecepCtrlSourceTete.setRstDtCtrlSourceReel(ValidatorUtils.parseTimestamp("rstDtCtrlSourceReel",fileUploadHelper.getMultipartRequest().getParameter("rstDtCtrlSourceReel"), request, helper,FieldConstraintsFactory.nonNull()));
            } catch (FieldConstraintsException e) {
            }

            try {
                doDtRecepCtrlSourceTete.setRstQtePresCtrlSource(ValidatorUtils.parseDouble("rstQtePresCtrlSource", fileUploadHelper.getMultipartRequest().getParameter("rstQtePresCtrlSource"),request, helper,FieldConstraintsFactory.nonNull()));
            } catch (FieldConstraintsException e) {
            }

            try {
                doDtRecepCtrlSourceTete.setRstQtePrelCtrlSource(ValidatorUtils.parseDouble("rstQtePrelCtrlSource", fileUploadHelper.getMultipartRequest().getParameter("rstQtePrelCtrlSource"),request, helper,FieldConstraintsFactory.nonNull()));
            } catch (FieldConstraintsException e) {
            }

            try {
                doDtRecepCtrlSourceTete.setRstEchantScelle01(ValidatorUtils.parseBoolInt("rstEchantScelle01", fileUploadHelper.getMultipartRequest().getParameter("rstEchantScelle01"),request, helper,null));
            } catch (FieldConstraintsException e) {
            }


            try {
                doDtRecepCtrlSourceTete.setRstCommentaire(ValidatorUtils.parseString("rstCommentaire", fileUploadHelper.getMultipartRequest().getParameter("rstCommentaire"),request, helper,null));
            } catch (FieldConstraintsException e) {
            }

            try {
                doDtRecepCtrlSourceTete.setRstOkCtrlSource(ValidatorUtils.parseString("rstOkCtrlSource", fileUploadHelper.getMultipartRequest().getParameter("rstOkCtrlSource"),request, helper,null));
                if(doDtRecepCtrlSourceTete.getRstOkCtrlSource()!=null){
                    doDtRecepCtrlSourceTete.setRstDtDecisionPrestataire(new Timestamp(System.currentTimeMillis()));
                }else{
                    doDtRecepCtrlSourceTete.setRstDtDecisionPrestataire(null);
                }
            } catch (FieldConstraintsException e) {
            }

//        try {
//            doDtRecepCtrlSourceTete.setRstFouEnvoiEchan(ValidatorUtils.parseInteger("rstFouEnvoiEchan",request,helper));
//        } catch (NumberFormatException e) {
//            someError = true;
//        }

            try {
                doDtRecepCtrlSourceTete.setRstNoColisEchant(ValidatorUtils.parseString("rstNoColisEchant", fileUploadHelper.getMultipartRequest().getParameter("rstNoColisEchant"),request, helper,null));
            } catch (FieldConstraintsException e) {
            }

//        try {
//            doDtRecepCtrlSourceTete.setRstFouEnvoiEchan(ValidatorUtils.parseBoolInt("rstFouEnvoiEchan",request,helper));
//        } catch (FieldConstraintsException e) {
//            someError = true;
//        }

            try {
                doDtRecepCtrlSourceTete.setRstDtEnvoiEchant(
                        ValidatorUtils.parseTimestamp("rstDtEnvoiEchant", fileUploadHelper.getMultipartRequest().getParameter("rstDtEnvoiEchant"),request, helper,
                                (
                                    (doDtRecepCtrlSourceTete.getRstNoColisEchant()!=null && doDtRecepCtrlSourceTete.getRstNoColisEchant().length()>0)
                                    // TODO à réactiver quand le champ RstFouEnvoiEchan sera rajouté
                                    // || doDtRecepCtrlSourceTete.getRstFouEnvoiEchan()!=0
                                )?FieldConstraintsFactory.nonNull():FieldConstraintsFactory.any()
                    ));
            } catch (FieldConstraintsException e) {
            }

            try {
                doDtRecepCtrlSourceTete.setRstMotifScelle(ValidatorUtils.parseString("rstMotifScelle", fileUploadHelper.getMultipartRequest().getParameter("rstMotifScelle"),request, helper,null));
            } catch (FieldConstraintsException e) {
            }

            String rccNoColis = fileUploadHelper.getMultipartRequest().getParameter("rccNoColis");
            TreeSet rccNoColisIds = rccNoColis==null ? new TreeSet() : new TreeSet(Arrays.asList(StringUtils.split(rccNoColis, ";", false)));
            Vector newColis = new Vector();
            for (int i = 0; i < colis.length; i++) {
                if (rccNoColisIds.contains(colis[i].getRscNoColis())) {
                    newColis.add(colis[i]);
                    rccNoColisIds.remove(colis[i].getRscNoColis());
                }
            }
            for (Iterator iterator = rccNoColisIds.iterator(); iterator.hasNext();) {
                String rccNoColisId = (String) iterator.next();
                DoDtRecepCtrlSourceColis doDtRecepCtrlSourceColis = new DoDtRecepCtrlSourceColis(DataObject.PERSIST_INSERT);
                doDtRecepCtrlSourceColis.setRscNoColis(rccNoColisId);
                newColis.add(doDtRecepCtrlSourceColis);
            }
            colis = (DoDtRecepCtrlSourceColis[]) newColis.toArray(new DoDtRecepCtrlSourceColis[newColis.size()]);
            doDtRecepCtrlSourceTete.setDtRecepCtrlSourceColis(colis);

            //-------------------- tests
            //Select
            String[] rcsCtrlCriteres = fileUploadHelper.getMultipartRequest().getParameterValues("rcsCtrlCritere");
            //Text area
            String[] rcsValeurSaisies = fileUploadHelper.getMultipartRequest().getParameterValues("rcsValeurSaisie");
            for (int i = 0; i < test.length; i++) {
                try {
                    test[i].setRcsCtrlCritere(ValidatorUtils.parseString("rcsCtrlCritere", rcsCtrlCriteres==null?null : rcsCtrlCriteres[i], request, test[i].getHelper(),null));
                } catch (FieldConstraintsException e) {
                }
                try {
                    test[i].setRcsValeurSaisie(ValidatorUtils.parseString("rcsValeurSaisie", rcsValeurSaisies==null?null : rcsValeurSaisies[i], request, test[i].getHelper(),null));
                } catch (FieldConstraintsException e) {
                }
            }
            if(addFile){
                try {
                    if(fileUploadHelper.uploadFiles()==0){
                        errorList.addError(WUtils.getDisplayable(request,"ERROR.NO_FILES_UPLOADED",-1));
                    };
                } catch (IllegalFileUploadedException e) {
                    errorList.addError(e.getMessage());
                } catch (IOException e) {
                    errorList.addError(e.getMessage());
                } catch (IllegalArgumentException e) {
                    errorList.addError(e.getMessage());
                }
            }else if (deleteFileIndex>=0){
                if(!fileUploadHelper.deleteFile(deleteFileIndex)){
                    errorList.addError(WUtils.getDisplayable(request,"ERROR.DELETE_FILE_EXCEPTION",-1));
                }
            }
//        // les fichiers...
//        try {
//            FileUploadHelper fileUploadHelper=new FileUploadHelper(new File("c:/test"),null,"{timestamp}");
//            fileUploadHelper.uploadFiles(request);
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
//        }

            helper.fillErrorMessages(errorList);
            for (int i = 0; i < test.length; i++) {
                test[i].getHelper().fillErrorMessages(errorList);
            }
        } catch (Throwable e) {
            errorList.addError(e.toString());
        }
        if (errorList.isEmpty()) {
            BoDtRecepCtrlSourceTete boDtRecepCtrlSourceTete = new BoDtRecepCtrlSourceTete();
            boDtRecepCtrlSourceTete.update(doDtRecepCtrlSourceTete);
            log.info(supplierCode+" ] update inspection report");
            return mapping.findForward("ok");
        } else {
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, errorList.toHtml());
            return mapping.findForward("error");
        }
    }

}

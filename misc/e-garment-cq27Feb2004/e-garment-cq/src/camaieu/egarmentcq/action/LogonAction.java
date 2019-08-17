package camaieu.egarmentcq.action;

import camaieu.common.WUtils;
import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.common.EGCQRequestConstants;
import camaieu.egarmentcq.common.EGCQSessionConstants;
import camaieu.egarmentcq.common.EGCQUtils;
import camaieu.tool.web.language.Messages;
import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import wg4.fwk.context.Profil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;

/**
 * Classe gérant les requêtes sur les pages d'identification.
 *
 * <BR> elle utilise le Realm spécifique à Camaieu (LDAP+RDBMS).
 * <BR>A l'issue de l'exécution de la méthode exécute deux
 * paramétres sont positionnés dans la session :
 * <UL>
 * <LI><B>EGCQSessionConstants.PROFILE</B> qui contient l'instance du Principal connecté</LI>
 * <LI><B>EGCQSessionConstants.SUPPLIER_CODE</B>qui contient EGCQUtils.ADMIN_SUPPLIER_CODE lorsque c'est un administrateur sinon le code de l'utilisateur </LI>
 * <LI><B>EGCQSessionConstants.IS_ADMIN</B> qui contient Boolean.TRUE ou Boolean.FALSE selon que oui ou non l'utilisateur connecté est un administrateur</LI>
 * </UL>
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 14/01/2003
 * @last_modification_date 16/01/2003
 * @status pour validation
 */
public class LogonAction extends Action {

    /**
     * instance de log
     */
    private static Category log = Category.getInstance(LogonAction.class.getName());

    /**
     * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        Profil p = (Profil) request.getSession().getAttribute(EGCQSessionConstants.PROFILE);


        if (p == null) {
            Principal pr = request.getUserPrincipal();
            if (pr instanceof wg4.fwk.context.Profil) {
                p = (wg4.fwk.context.Profil) pr;
                request.getSession().setAttribute(EGCQSessionConstants.PROFILE, p);
            } else {
                // LDAP realm non mis en place
                log.error("Camaieu LDAP realm not installed correctly. Class Principal's type is " + (pr==null ? "NULL" : pr.getClass().getName()) );
                request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR",-1));
                return mapping.findForward("standard_error");
            }
        }

        // LDAP n'as pas renvoyé d'utilisateurs
        if (p == null) {
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.UNKNOWN_USER",-1));
            return mapping.findForward("error");
        }


        // récupération de la langue préférée
        if (p.getPreferredLanguage() != null && p.getPreferredLanguage().length() > 0) {
            Messages.SetLanguage(request, p.getPreferredLanguage().toLowerCase());
        } else {
            Messages.GetLanguageFromBrowser(request);
        }


        // gestion du code fournisseur :
        // 	- soit il s'agit d'un admin
        // 			-> supplierCode  contient ALL
        // 			-> isAdmin contient Boolean.TRUE
        // 	- soit il s'agit d'un fournisseur
        // 			-> supplierCode  contient le code du fournisseur
        // 			-> isAdmin contient Boolean.FALSE
        String userLogin = p.getId();
        String fouCode = p.getCodeFournisseur();
        if (request.isUserInRole(EGCQBusinessConstants.ROLE_ADMIN)){
            request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_CODE, EGCQUtils.ADMIN_SUPPLIER_CODE);
            request.getSession().setAttribute(EGCQSessionConstants.IS_ADMIN, Boolean.TRUE);
            log.info("<LogonAction as " + userLogin + " / fouCode=" + fouCode + " /  ROLE : ADMIN from "+(p.getRoles() == null ?  "NONE" : Arrays.asList(p.getRoles()).toString())+">");

        } else if (request.isUserInRole(EGCQBusinessConstants.ROLE_SUPPLIER)){
            request.getSession().setAttribute(EGCQSessionConstants.SUPPLIER_CODE, fouCode);
            request.getSession().setAttribute(EGCQSessionConstants.IS_ADMIN, Boolean.FALSE);
            log.info("<LogonAction as " + userLogin + " / fouCode=" + fouCode + " /  ROLE : USER from "+(p.getRoles() == null ?  "NONE" : Arrays.asList(p.getRoles()).toString())+">");

        }else{
            log.info("<LogonAction failed for " + userLogin + " / fouCode=" + fouCode + " / ROLE :"+(p.getRoles() == null ?  "NONE" : Arrays.asList(p.getRoles()).toString()) +">");
            return mapping.findForward("error");
        }
        return mapping.findForward("ok");
    }

}

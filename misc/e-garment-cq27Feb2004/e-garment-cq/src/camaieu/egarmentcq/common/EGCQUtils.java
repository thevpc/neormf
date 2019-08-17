package camaieu.egarmentcq.common;

import camaieu.common.WUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe d'utilité générale.
 *
 * <BR> toutes les méthodes sont statiques
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 14/01/2004
 * @last_modification_date date 16/01/2004
 * @status pour validation
 */
public final class EGCQUtils {
    /**
     * constructeur private pour inhiber l'instantiation
     */
    private EGCQUtils() {
        super();
    }

    /**
     * Code du fournisseur lorsque c'est un administrateur
     */
    public static final String ADMIN_SUPPLIER_CODE = "ALL";


    /**
     * Type d'affichage des tableau en format txt
     */
    public static final String EXPORT_TYPE_PLAIN_TEXT = "text";


    /**
     *
     * @param request de la jsp
     * @param response de la jsp
     * @param gotoPath chemin de la page d'erreur
     * @throws IOException  si la redirection a échoué
     */
    public static void checkIsAdmin(HttpServletRequest request, HttpServletResponse response, String gotoPath) throws IOException {
        Boolean isAdmin = (Boolean) request.getSession().getAttribute(EGCQSessionConstants.IS_ADMIN);
        if (isAdmin == null || !isAdmin.booleanValue()) {
            request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.SECURITY.ADMIN_REQUIRED",-1));
            request.setAttribute(EGCQRequestConstants.ERROR_REDIRECT, null);
            response.sendRedirect(WUtils.getPath(request, gotoPath));
        }
    }

}

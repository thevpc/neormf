package camaieu.egarmentcq.common;

/**
 * Classe utilitaire �num�rant les attributs utilis�s dans l'objet request (HttpServletRequest)
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 14/01/2004
 * @last_modification_date 19/01/2004
 * @status pour validation
 */
public final class EGCQRequestConstants {
    /**
     * constructeur private pour inhiber l'instantiation
     */
    private EGCQRequestConstants() {
    }

    /**
     * Message d'erreur � afficher.
     *
     * <BR>Contexte :
     * <UL>
     * <LI>Setters : </LI>
     * <LI>Getters : standard_error.jsp</LI>
     * </UL>
     * Type : java.lang.String
     */
    public static final String ERROR_MSG = "standard_error_msg";

    /**
     * page vers laquelle il faut rediriger apres affichage de l'erreur.
     * Par d�faut, on revient � login.jsp
     *
     * <BR>Contexte :
     * <UL>
     * <LI>Setters : </LI>
     * <LI>Getters : standard_error.jsp</LI>
     * </UL>
     * Type : java.lang.String
     * Pattern ; url
     */
    public static final String ERROR_REDIRECT = "standard_error_redirect";

    /**
     * upload helper
     */
    public static final String UPLOAD_HELPER="upload_helper";
}

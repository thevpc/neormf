package camaieu.egarmentcq.common;

/**
 * Classe utilitaire contenant les constantes métier
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 20/01/2004
 * @last_modification_date 30/01/2004
 * @status pour validation
 */
public final class EGCQBusinessConstants {
    /**
     * constructeur privé pour inhiber l'instantiation
     */
    private EGCQBusinessConstants() {
    }

    /**
     * nom du prjet / webapp
     */
    public static final String APP_NAME = "e-garment-cq";
    /**
     * nom de la datasource
     */
    public static final String DATASOURCE_NAME = "jdbc/" + APP_NAME;

    /**
     * valeur de INDEX_DELETED pour un enregistrement en suppression logique
     */
    public static final String INDEX_DELETED = "Z";

    /**
     * valeur de INDEX_DELETED pour un enregistrement valide
     */
    public static final String INDEX_VALID = "U";


    /**
     * Valeur VRAI pour les champs Varchar exprimant des valeur booléennes
     */
    public static final Integer BOOLEAN_YES = new Integer(1);

    /**
     * Valeur FAUX pour les champs Varchar exprimant des valeur booléennes
     */
    public static final Integer BOOLEAN_FALSE = new Integer(0);

    /**
     * role administrateur
     */
    public static final String ROLE_ADMIN="admin#"+APP_NAME;

    /**
     * role utilisateur simple
     */
    public static final String ROLE_SUPPLIER="user#"+APP_NAME;
}

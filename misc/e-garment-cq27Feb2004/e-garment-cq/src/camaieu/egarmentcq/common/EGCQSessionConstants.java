package camaieu.egarmentcq.common;

/**
 * Classe utilitaire énumérant les attributs utilisés dans l'objet session (HttpSession)
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 14/01/2004
 * @last_modification_date 19/01/2004
 * @status pour validation
 */
public final class EGCQSessionConstants {
    /**
     * constructeur private pour inhiber l'instantiation
     */
    private EGCQSessionConstants() {
    }

    /**
     * tableau des commandes (planning).
     *
     * <BR>Contexte :
     * <UL>
     * <LI>Setters : PlanningAction</LI>
     * <LI>Getters : supplier_planning.jsp</LI>
     * </UL>
     * Type : camaieu.egarmentcq.bo.BoDtCmdeAchatTete.CommandeData[]
     */
    public static final String SUPPLIER_PLANNING = "supplier_planning";

    /**
     * tableau accusés de réception.
     *
     * <BR>Contexte :
     * <UL>
     * <LI>Setters : ItemDeliveryReceiptAction</LI>
     * <LI>Getters : item_delivery_receipt.jsp</LI>
     * </UL>
     * Type : camaieu.egarmentcq.bo.BoDtEnvoiCqSource.ItemDeliveryReceiptData[]
     */
    public static final String SUPPLIER_ITEM_DELIVERY_RECEIPTS = "item_delivery_receipts";

    /**
     * Données relatives à un fournisseur.
     *
     * <BR>Contexte :
     * <UL>
     * <LI>Setters : PlanningAction</LI>
     * <LI>Getters : supplier_planning.jsp</LI>
     * </UL>
     * Type : camaieu.egarmentcq.dataobject.DoXnFour
     */
    public static final String SUPPLIER_DO = "supplier_do";


    /**
     * le profile connecté à l'application<BR>
     * Type : wg4.fwk.context.Profil
     */
    public static final String PROFILE = "profile";


    /**
     * Code du fournisseur (ou administrateur).
     * Contient le code du fournisseur ou de l'administrateur
     * utilisateur de l'application.<BR>
     * Vaut ADMIN_SUPPLIER_CODE si administrateur
     * tant que ce dernier n'a pas encore selectionné
     * le prestataire à éditer<BR>
     * @see EGCQUtils#ADMIN_SUPPLIER_CODE
     * Type : java.lang.String
     */
    public static final String SUPPLIER_CODE = "supplierCode";


    /**
     * Vrai s'il s'agit d'une session administrateur.
     * Type : java.lang.Boolean
     */
    public static final String IS_ADMIN = "isAdmin";

    /**
     * type d'affichage des tableaux.
     * Type : java.lang.String
     */
    public static final String EXPORT_TYPE = "exportType";

    /**
     * DoDtRecepCtrlSourceTete contenant les informations nécessaires
     * dans inspectionReport.jsp.
     * Type : camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceTete
     */
    public static final String DO_DT_RECEPT_CTRL_SOURCE_TETE = "doDtRecepCtrlSourceTete";
}

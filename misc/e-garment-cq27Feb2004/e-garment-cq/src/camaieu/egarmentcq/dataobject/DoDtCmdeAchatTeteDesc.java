package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtCmdeAchatTete correspondant à la table DT_CMDE_ACHAT_TETE
 */
public class DoDtCmdeAchatTeteDesc implements IDoDescription {
    public static final int CAT_DEP_CODE = 0;
    public static final int CAT_DEP_SOC_CODE = 1;
    public static final int CAT_NO_CMDE = 2;
    public static final int CAT_NO_VERSION = 3;
    public static final int CAT_NO_CMDE_MERE = 4;
    public static final int CAT_NO_VERSION_MERE = 5;
    public static final int CAT_NO_RECEP = 6;
    public static final int CAT_NO_VERSION_RECEP = 7;
    public static final int CAT_DEP_CODE_LIV = 8;
    public static final int CAT_DEP_SOC_CODE_LIV = 9;
    public static final int CAT_DEP_CODE_DEST = 10;
    public static final int CAT_DEP_SOC_CODE_DEST = 11;
    public static final int CAT_DER_NO_VERSION = 12;
    public static final int CAT_DEV_CODE = 13;
    public static final int CAT_TX_DEVISE = 14;
    public static final int CAT_TX_ESC = 15;
    public static final int CAT_CONSIGNATION = 16;
    public static final int CAT_CONTROLE_QUAL = 17;
    public static final int CAT_DT_CMDE = 18;
    public static final int CAT_DT_CONFIRM = 19;
    public static final int CAT_DT_DEPART1 = 20;
    public static final int CAT_DT_DEPART2 = 21;
    public static final int CAT_DT_EDITION1 = 22;
    public static final int CAT_DT_EDITION2 = 23;
    public static final int CAT_DT_EVEN = 24;
    public static final int CAT_DT_PREV_LIVR1 = 25;
    public static final int CAT_DT_PREV_LIVR2 = 26;
    public static final int CAT_DT_RDV = 27;
    public static final int CAT_DT_VALEUR = 28;
    public static final int CAT_ETIQUETTAGE = 29;
    public static final int CAT_EVE_CODE = 30;
    public static final int CAT_FOU_CODE = 31;
    public static final int CAT_INF_FOU_CODE = 32;
    public static final int CAT_INF_FOU_NOM = 33;
    public static final int CAT_INF_FOU_TITRE = 34;
    public static final int CAT_INF_NO_ORDRE = 35;
    public static final int CAT_LAN_CODE = 36;
    public static final int CAT_LIBRE1 = 37;
    public static final int CAT_LIBRE2 = 38;
    public static final int CAT_LIBRE3 = 39;
    public static final int CAT_MLI_CODE = 40;
    public static final int CAT_MOD_CODE = 41;
    public static final int CAT_MTR_CODE = 42;
    public static final int CAT_PAY_CODE = 43;
    public static final int CAT_PIECE_COLIS = 44;
    public static final int CAT_REF_CMDE = 45;
    public static final int CAT_REGIME_TVA = 46;
    public static final int CAT_RELIQUAT_ON = 47;
    public static final int CAT_FOU_CODE_TRANSP = 48;
    public static final int CAT_STA_CODE = 49;
    public static final int CAT_TYPE_ORDRE = 50;
    public static final int CAT_TX_REM_FAC1 = 51;
    public static final int CAT_TX_REM_FAC2 = 52;
    public static final int CAT_TX_REM_FAC3 = 53;
    public static final int CAT_TYD_CODE = 54;
    public static final int CAT_TYPE_CMDE = 55;
    public static final int CAT_TYPE_PORT = 56;
    public static final int CAT_INDEX = 57;
    public static final int CAT_DT_CREAT = 58;
    public static final int CAT_DT_MAJ = 59;
    public static final int CAT_UTIL_MAJ = 60;
    public static final int XTX_ID = 61;
    public static final int Z_STATUS = 62;
    public static final int CAT_VALIDATION = 63;
    public static final int CAT_DT_PREV_LIVR0 = 64;
    public static final int CAT_RETOUR_GP = 65;
    public static final int CAT_ETAT_GP = 66;

    public static final String tableName = "DT_CMDE_ACHAT_TETE";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "CAT_DEP_CODE", "CAT_DEP_SOC_CODE", "CAT_NO_CMDE", "CAT_NO_VERSION", "CAT_NO_CMDE_MERE", "CAT_NO_VERSION_MERE", "CAT_NO_RECEP", "CAT_NO_VERSION_RECEP", "CAT_DEP_CODE_LIV", "CAT_DEP_SOC_CODE_LIV", "CAT_DEP_CODE_DEST", "CAT_DEP_SOC_CODE_DEST", "CAT_DER_NO_VERSION", "CAT_DEV_CODE", "CAT_TX_DEVISE", "CAT_TX_ESC", "CAT_CONSIGNATION", "CAT_CONTROLE_QUAL", "CAT_DT_CMDE", "CAT_DT_CONFIRM", "CAT_DT_DEPART1", "CAT_DT_DEPART2", "CAT_DT_EDITION1", "CAT_DT_EDITION2", "CAT_DT_EVEN", "CAT_DT_PREV_LIVR1", "CAT_DT_PREV_LIVR2", "CAT_DT_RDV", "CAT_DT_VALEUR", "CAT_ETIQUETTAGE", "CAT_EVE_CODE", "CAT_FOU_CODE", "CAT_INF_FOU_CODE", "CAT_INF_FOU_NOM", "CAT_INF_FOU_TITRE", "CAT_INF_NO_ORDRE", "CAT_LAN_CODE", "CAT_LIBRE1", "CAT_LIBRE2", "CAT_LIBRE3", "CAT_MLI_CODE", "CAT_MOD_CODE", "CAT_MTR_CODE", "CAT_PAY_CODE", "CAT_PIECE_COLIS", "CAT_REF_CMDE", "CAT_REGIME_TVA", "CAT_RELIQUAT_ON", "CAT_FOU_CODE_TRANSP", "CAT_STA_CODE", "CAT_TYPE_ORDRE", "CAT_TX_REM_FAC1", "CAT_TX_REM_FAC2", "CAT_TX_REM_FAC3", "CAT_TYD_CODE", "CAT_TYPE_CMDE", "CAT_TYPE_PORT", "CAT_INDEX", "CAT_DT_CREAT", "CAT_DT_MAJ", "CAT_UTIL_MAJ", "XTX_ID", "Z_STATUS", "CAT_VALIDATION", "CAT_DT_PREV_LIVR0", "CAT_RETOUR_GP", "CAT_ETAT_GP"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(67);
        colBase.put("CAT_DEP_CODE", new Integer(CAT_DEP_CODE));
        colBase.put("CAT_DEP_SOC_CODE", new Integer(CAT_DEP_SOC_CODE));
        colBase.put("CAT_NO_CMDE", new Integer(CAT_NO_CMDE));
        colBase.put("CAT_NO_VERSION", new Integer(CAT_NO_VERSION));
        colBase.put("CAT_NO_CMDE_MERE", new Integer(CAT_NO_CMDE_MERE));
        colBase.put("CAT_NO_VERSION_MERE", new Integer(CAT_NO_VERSION_MERE));
        colBase.put("CAT_NO_RECEP", new Integer(CAT_NO_RECEP));
        colBase.put("CAT_NO_VERSION_RECEP", new Integer(CAT_NO_VERSION_RECEP));
        colBase.put("CAT_DEP_CODE_LIV", new Integer(CAT_DEP_CODE_LIV));
        colBase.put("CAT_DEP_SOC_CODE_LIV", new Integer(CAT_DEP_SOC_CODE_LIV));
        colBase.put("CAT_DEP_CODE_DEST", new Integer(CAT_DEP_CODE_DEST));
        colBase.put("CAT_DEP_SOC_CODE_DEST", new Integer(CAT_DEP_SOC_CODE_DEST));
        colBase.put("CAT_DER_NO_VERSION", new Integer(CAT_DER_NO_VERSION));
        colBase.put("CAT_DEV_CODE", new Integer(CAT_DEV_CODE));
        colBase.put("CAT_TX_DEVISE", new Integer(CAT_TX_DEVISE));
        colBase.put("CAT_TX_ESC", new Integer(CAT_TX_ESC));
        colBase.put("CAT_CONSIGNATION", new Integer(CAT_CONSIGNATION));
        colBase.put("CAT_CONTROLE_QUAL", new Integer(CAT_CONTROLE_QUAL));
        colBase.put("CAT_DT_CMDE", new Integer(CAT_DT_CMDE));
        colBase.put("CAT_DT_CONFIRM", new Integer(CAT_DT_CONFIRM));
        colBase.put("CAT_DT_DEPART1", new Integer(CAT_DT_DEPART1));
        colBase.put("CAT_DT_DEPART2", new Integer(CAT_DT_DEPART2));
        colBase.put("CAT_DT_EDITION1", new Integer(CAT_DT_EDITION1));
        colBase.put("CAT_DT_EDITION2", new Integer(CAT_DT_EDITION2));
        colBase.put("CAT_DT_EVEN", new Integer(CAT_DT_EVEN));
        colBase.put("CAT_DT_PREV_LIVR1", new Integer(CAT_DT_PREV_LIVR1));
        colBase.put("CAT_DT_PREV_LIVR2", new Integer(CAT_DT_PREV_LIVR2));
        colBase.put("CAT_DT_RDV", new Integer(CAT_DT_RDV));
        colBase.put("CAT_DT_VALEUR", new Integer(CAT_DT_VALEUR));
        colBase.put("CAT_ETIQUETTAGE", new Integer(CAT_ETIQUETTAGE));
        colBase.put("CAT_EVE_CODE", new Integer(CAT_EVE_CODE));
        colBase.put("CAT_FOU_CODE", new Integer(CAT_FOU_CODE));
        colBase.put("CAT_INF_FOU_CODE", new Integer(CAT_INF_FOU_CODE));
        colBase.put("CAT_INF_FOU_NOM", new Integer(CAT_INF_FOU_NOM));
        colBase.put("CAT_INF_FOU_TITRE", new Integer(CAT_INF_FOU_TITRE));
        colBase.put("CAT_INF_NO_ORDRE", new Integer(CAT_INF_NO_ORDRE));
        colBase.put("CAT_LAN_CODE", new Integer(CAT_LAN_CODE));
        colBase.put("CAT_LIBRE1", new Integer(CAT_LIBRE1));
        colBase.put("CAT_LIBRE2", new Integer(CAT_LIBRE2));
        colBase.put("CAT_LIBRE3", new Integer(CAT_LIBRE3));
        colBase.put("CAT_MLI_CODE", new Integer(CAT_MLI_CODE));
        colBase.put("CAT_MOD_CODE", new Integer(CAT_MOD_CODE));
        colBase.put("CAT_MTR_CODE", new Integer(CAT_MTR_CODE));
        colBase.put("CAT_PAY_CODE", new Integer(CAT_PAY_CODE));
        colBase.put("CAT_PIECE_COLIS", new Integer(CAT_PIECE_COLIS));
        colBase.put("CAT_REF_CMDE", new Integer(CAT_REF_CMDE));
        colBase.put("CAT_REGIME_TVA", new Integer(CAT_REGIME_TVA));
        colBase.put("CAT_RELIQUAT_ON", new Integer(CAT_RELIQUAT_ON));
        colBase.put("CAT_FOU_CODE_TRANSP", new Integer(CAT_FOU_CODE_TRANSP));
        colBase.put("CAT_STA_CODE", new Integer(CAT_STA_CODE));
        colBase.put("CAT_TYPE_ORDRE", new Integer(CAT_TYPE_ORDRE));
        colBase.put("CAT_TX_REM_FAC1", new Integer(CAT_TX_REM_FAC1));
        colBase.put("CAT_TX_REM_FAC2", new Integer(CAT_TX_REM_FAC2));
        colBase.put("CAT_TX_REM_FAC3", new Integer(CAT_TX_REM_FAC3));
        colBase.put("CAT_TYD_CODE", new Integer(CAT_TYD_CODE));
        colBase.put("CAT_TYPE_CMDE", new Integer(CAT_TYPE_CMDE));
        colBase.put("CAT_TYPE_PORT", new Integer(CAT_TYPE_PORT));
        colBase.put("CAT_INDEX", new Integer(CAT_INDEX));
        colBase.put("CAT_DT_CREAT", new Integer(CAT_DT_CREAT));
        colBase.put("CAT_DT_MAJ", new Integer(CAT_DT_MAJ));
        colBase.put("CAT_UTIL_MAJ", new Integer(CAT_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("CAT_VALIDATION", new Integer(CAT_VALIDATION));
        colBase.put("CAT_DT_PREV_LIVR0", new Integer(CAT_DT_PREV_LIVR0));
        colBase.put("CAT_RETOUR_GP", new Integer(CAT_RETOUR_GP));
        colBase.put("CAT_ETAT_GP", new Integer(CAT_ETAT_GP));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "CAT_NO_VERSION", "CAT_NO_CMDE", "CAT_DEP_SOC_CODE", "CAT_DEP_CODE"};

    private static final int[] pkColNum = new int[]{3, 2, 1, 0};

    private static final HashMap fkColName = new HashMap(19);

    static {
        fkColName.put("DT_EVENEMENT", new String[]{
            "CAT_EVE_CODE"
        });
        fkColName.put("DT_STATUT_CMDE", new String[]{
            "CAT_STA_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "CAT_DEP_CODE_LIV"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "CAT_DEP_CODE_DEST"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "CAT_DEP_SOC_CODE_LIV"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "CAT_DEP_SOC_CODE_DEST"
        });
        fkColName.put("XN_DEVISE", new String[]{
            "CAT_DEV_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "CAT_FOU_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "CAT_FOU_CODE_TRANSP"
        });
        fkColName.put("XN_INTERLOC_FOU", new String[]{
            "CAT_INF_NO_ORDRE"
        });
        fkColName.put("XN_INTERLOC_FOU", new String[]{
            "CAT_INF_FOU_CODE"
        });
        fkColName.put("XN_LANGUE", new String[]{
            "CAT_LAN_CODE"
        });
        fkColName.put("XN_MODELE", new String[]{
            "CAT_MOD_CODE"
        });
        fkColName.put("XN_MODE_LIVR", new String[]{
            "CAT_MLI_CODE"
        });
        fkColName.put("XN_MODE_TRANSP", new String[]{
            "CAT_MTR_CODE"
        });
        fkColName.put("XN_PAYS", new String[]{
            "CAT_PAY_CODE"
        });
        fkColName.put("XN_TYPE_DOC", new String[]{
            "CAT_TYD_CODE"
        });
    }

    static {
        fkColName.put("DT_AFFECT_RAR", new String[]{
            "CAT_DEP_CODE", "CAT_DEP_SOC_CODE", "CAT_NO_CMDE", "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_BASE", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_BASE", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_BASE", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_BASE", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_REGL", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_REGL", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_REGL", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_REGL", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_ENVOI_CQ_SOURCE", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_ENVOI_CQ_SOURCE", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_ENVOI_CQ_SOURCE", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_ENVOI_CQ_SOURCE", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_RECEP_ACHAT_TETE", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_TETE", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_TETE", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_RECEP_ACHAT_TETE", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("DT_W_CMDE_ACHAT", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("DT_W_CMDE_ACHAT", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_W_CMDE_ACHAT", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("DT_W_CMDE_ACHAT", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_DEP_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_DEP_SOC_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_NO_CMDE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_NO_VERSION"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAT_NO_VERSION"
        });
    }


    private static final HashMap fkColNum = new HashMap(19);

    static {
        fkColNum.put("DT_EVENEMENT", new int[]{
            30
        });
        fkColNum.put("DT_STATUT_CMDE", new int[]{
            49
        });
        fkColNum.put("XN_DEPOT", new int[]{
            0
        });
        fkColNum.put("XN_DEPOT", new int[]{
            8
        });
        fkColNum.put("XN_DEPOT", new int[]{
            10
        });
        fkColNum.put("XN_DEPOT", new int[]{
            1
        });
        fkColNum.put("XN_DEPOT", new int[]{
            9
        });
        fkColNum.put("XN_DEPOT", new int[]{
            11
        });
        fkColNum.put("XN_DEVISE", new int[]{
            13
        });
        fkColNum.put("XN_FOUR", new int[]{
            31
        });
        fkColNum.put("XN_FOUR", new int[]{
            48
        });
        fkColNum.put("XN_INTERLOC_FOU", new int[]{
            35
        });
        fkColNum.put("XN_INTERLOC_FOU", new int[]{
            32
        });
        fkColNum.put("XN_LANGUE", new int[]{
            36
        });
        fkColNum.put("XN_MODELE", new int[]{
            41
        });
        fkColNum.put("XN_MODE_LIVR", new int[]{
            40
        });
        fkColNum.put("XN_MODE_TRANSP", new int[]{
            42
        });
        fkColNum.put("XN_PAYS", new int[]{
            43
        });
        fkColNum.put("XN_TYPE_DOC", new int[]{
            54
        });
    }

    static {
        fkColNum.put("DT_AFFECT_RAR", new int[]{
            0, 1, 2, 3
        });
        fkColNum.put("DT_CMDE_ACHAT_BASE", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_BASE", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_BASE", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_BASE", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_COMPO", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_COMPO", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_COMPO", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_COMPO", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_EHF", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_EHF", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_EHF", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_EHF", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_ENTR", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_ENTR", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_ENTR", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_ENTR", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_LIGNE", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_LIGNE", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_LIGNE", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_LIGNE", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_MC", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_MC", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_MC", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_MC", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_REGL", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_REGL", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_REGL", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_REGL", new int[]{
            3
        });
        fkColNum.put("DT_ENVOI_CQ_SOURCE", new int[]{
            0
        });
        fkColNum.put("DT_ENVOI_CQ_SOURCE", new int[]{
            1
        });
        fkColNum.put("DT_ENVOI_CQ_SOURCE", new int[]{
            2
        });
        fkColNum.put("DT_ENVOI_CQ_SOURCE", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_TETE", new int[]{
            0
        });
        fkColNum.put("DT_RECEP_ACHAT_TETE", new int[]{
            1
        });
        fkColNum.put("DT_RECEP_ACHAT_TETE", new int[]{
            2
        });
        fkColNum.put("DT_RECEP_ACHAT_TETE", new int[]{
            3
        });
        fkColNum.put("DT_W_CMDE_ACHAT", new int[]{
            0
        });
        fkColNum.put("DT_W_CMDE_ACHAT", new int[]{
            1
        });
        fkColNum.put("DT_W_CMDE_ACHAT", new int[]{
            2
        });
        fkColNum.put("DT_W_CMDE_ACHAT", new int[]{
            3
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            0
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            0
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            1
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            1
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            2
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            2
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            3
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            3
        });
    }

    /**
     * @see IDoDescription.getTableName()
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @see IDoDescription.getDbColName()
     */
    public String[] getDbColName() {
        return dbColName;
    }

    /**
     * @see IDoDescription.getDbColNum()
     */
    public HashMap getDbColNum() {
        return colBase;
    }

    /**
     * @see IDoDescription.getPkColName()
     */
    public String[] getPkColName() {
        return pkColName;
    }

    /**
     * @see IDoDescription.getPkColNum()
     */
    public int[] getPkColNum() {
        return pkColNum;
    }
    /**
     * @see IDoDescription.getPkColNumInsert()
     */
    /**
     * @see IDoDescription.getFkColName()
     */
    public String[] getFkColName(String tableName) {
        return (String[]) fkColName.get(tableName);
    }

    /**
     * @see IDoDescription.getFkColNum()
     */
    public int[] getFkColNum(String tableName) {
        return (int[]) fkColNum.get(tableName);
    }
}

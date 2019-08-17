package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoXnFour correspondant à la table XN_FOUR
 */
public class DoXnFourDesc implements IDoDescription {
    public static final int FOU_RELIQUAT_ON = 0;
    public static final int FOU_TX_ESC = 1;
    public static final int FOU_FOU_CODE_PX = 2;
    public static final int FOU_CODE = 3;
    public static final int FOU_FFO_CODE = 4;
    public static final int FOU_NOM = 5;
    public static final int FOU_ZEC_CODE = 6;
    public static final int FOU_TYC_CODE = 7;
    public static final int FOU_PAY_CODE = 8;
    public static final int FOU_LAN_CODE = 9;
    public static final int FOU_DEV_CODE = 10;
    public static final int FOU_MTR_CODE = 11;
    public static final int FOU_MRG_CODE = 12;
    public static final int FOU_CRG_CODE = 13;
    public static final int FOU_MLI_CODE = 14;
    public static final int FOU_ADR3 = 15;
    public static final int FOU_BANQ = 16;
    public static final int FOU_DT_DER_CMDE = 17;
    public static final int FOU_DT_CREAT = 18;
    public static final int FOU_DELAI_LIV = 19;
    public static final int FOU_CTRLE_FAC = 20;
    public static final int FOU_CPTE_COMPTA = 21;
    public static final int FOU_CODE_LIBRE = 22;
    public static final int FOU_CODE_GUICHET = 23;
    public static final int FOU_CODE_GENCOD = 24;
    public static final int FOU_ADR1 = 25;
    public static final int FOU_VILLE = 26;
    public static final int FOU_UTIL_MAJ = 27;
    public static final int FOU_TYPE_REM_DIFF = 28;
    public static final int FOU_TX_REM_FAC3 = 29;
    public static final int FOU_TX_REM_FAC2 = 30;
    public static final int FOU_TX_REM_FAC1 = 31;
    public static final int FOU_TEL = 32;
    public static final int FOU_ADR3_BANQ = 33;
    public static final int FOU_ADR2_BANQ = 34;
    public static final int FOU_ADR2 = 35;
    public static final int FOU_ADR1_BANQ = 36;
    public static final int FOU_REGIME_TVA = 37;
    public static final int FOU_REF_NOUS = 38;
    public static final int FOU_POSTAL = 39;
    public static final int FOU_MT_MIN_CMDE = 40;
    public static final int FOU_MT_FRANCO_PORT = 41;
    public static final int FOU_GROUPE_ON = 42;
    public static final int FOU_FAX = 43;
    public static final int FOU_CEE_COEF = 44;
    public static final int FOU_CEE_ID = 45;
    public static final int FOU_CLE_CTRLE = 46;
    public static final int FOU_CODE_CPTE_BANQ = 47;
    public static final int FOU_CODE_BANQ = 48;
    public static final int FOU_DT_MAJ = 49;
    public static final int XTX_ID = 50;
    public static final int FOU_FOT_CODE = 51;
    public static final int FOU_TEL2 = 52;
    public static final int FOU_EMAIL = 53;
    public static final int FOU_FOU_CODE = 54;
    public static final int FOU_FOU_CODE_AGENTI = 55;
    public static final int FOU_FOU_CODE_AGENTE = 56;
    public static final int FOU_PAY_CODE_FAB = 57;
    public static final int FOU_ADR1_PAYE = 58;
    public static final int FOU_ADR2_PAYE = 59;
    public static final int FOU_POSTAL_PAYE = 60;
    public static final int FOU_VILLE_PAYE = 61;
    public static final int FOU_PAY_CODE_PAYE = 62;
    public static final int FOU_TEL1_PAYE = 63;
    public static final int FOU_TEL2_PAYE = 64;
    public static final int FOU_FAX_PAYE = 65;
    public static final int FOU_EMAIL_PAYE = 66;
    public static final int FOU_DT_ACCEPT = 67;
    public static final int FOU_DT_FIN_TRAV = 68;
    public static final int FOU_SIGNATAIRE = 69;
    public static final int FOU_QUAL_INDICE = 70;
    public static final int FOU_STAT_ETIQ = 71;
    public static final int FOU_STAT_CQ = 72;
    public static final int FOU_CONSIGNE = 73;
    public static final int Z_STATUS = 74;
    public static final int FOU_ETAT = 75;
    public static final int FOU_OBS = 76;
    public static final int FOU_CDE_COMPOSANTS = 77;
    public static final int FOU_DEP_CODE = 78;
    public static final int FOU_OK_VISITE = 79;
    public static final int FOU_PREST_VISITE = 80;
    public static final int FOU_DOMAINE = 81;
    public static final int FOU_DEP_SOC_CODE = 82;
    public static final int FOU_DEP_CODE_TISSU = 83;
    public static final int FOU_DEP_SOC_CODE_TISSU = 84;

    public static final String tableName = "XN_FOUR";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "FOU_RELIQUAT_ON", "FOU_TX_ESC", "FOU_FOU_CODE_PX", "FOU_CODE", "FOU_FFO_CODE", "FOU_NOM", "FOU_ZEC_CODE", "FOU_TYC_CODE", "FOU_PAY_CODE", "FOU_LAN_CODE", "FOU_DEV_CODE", "FOU_MTR_CODE", "FOU_MRG_CODE", "FOU_CRG_CODE", "FOU_MLI_CODE", "FOU_ADR3", "FOU_BANQ", "FOU_DT_DER_CMDE", "FOU_DT_CREAT", "FOU_DELAI_LIV", "FOU_CTRLE_FAC", "FOU_CPTE_COMPTA", "FOU_CODE_LIBRE", "FOU_CODE_GUICHET", "FOU_CODE_GENCOD", "FOU_ADR1", "FOU_VILLE", "FOU_UTIL_MAJ", "FOU_TYPE_REM_DIFF", "FOU_TX_REM_FAC3", "FOU_TX_REM_FAC2", "FOU_TX_REM_FAC1", "FOU_TEL", "FOU_ADR3_BANQ", "FOU_ADR2_BANQ", "FOU_ADR2", "FOU_ADR1_BANQ", "FOU_REGIME_TVA", "FOU_REF_NOUS", "FOU_POSTAL", "FOU_MT_MIN_CMDE", "FOU_MT_FRANCO_PORT", "FOU_GROUPE_ON", "FOU_FAX", "FOU_CEE_COEF", "FOU_CEE_ID", "FOU_CLE_CTRLE", "FOU_CODE_CPTE_BANQ", "FOU_CODE_BANQ", "FOU_DT_MAJ", "XTX_ID", "FOU_FOT_CODE", "FOU_TEL2", "FOU_EMAIL", "FOU_FOU_CODE", "FOU_FOU_CODE_AGENTI", "FOU_FOU_CODE_AGENTE", "FOU_PAY_CODE_FAB", "FOU_ADR1_PAYE", "FOU_ADR2_PAYE", "FOU_POSTAL_PAYE", "FOU_VILLE_PAYE", "FOU_PAY_CODE_PAYE", "FOU_TEL1_PAYE", "FOU_TEL2_PAYE", "FOU_FAX_PAYE", "FOU_EMAIL_PAYE", "FOU_DT_ACCEPT", "FOU_DT_FIN_TRAV", "FOU_SIGNATAIRE", "FOU_QUAL_INDICE", "FOU_STAT_ETIQ", "FOU_STAT_CQ", "FOU_CONSIGNE", "Z_STATUS", "FOU_ETAT", "FOU_OBS", "FOU_CDE_COMPOSANTS", "FOU_DEP_CODE", "FOU_OK_VISITE", "FOU_PREST_VISITE", "FOU_DOMAINE", "FOU_DEP_SOC_CODE", "FOU_DEP_CODE_TISSU", "FOU_DEP_SOC_CODE_TISSU"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(85);
        colBase.put("FOU_RELIQUAT_ON", new Integer(FOU_RELIQUAT_ON));
        colBase.put("FOU_TX_ESC", new Integer(FOU_TX_ESC));
        colBase.put("FOU_FOU_CODE_PX", new Integer(FOU_FOU_CODE_PX));
        colBase.put("FOU_CODE", new Integer(FOU_CODE));
        colBase.put("FOU_FFO_CODE", new Integer(FOU_FFO_CODE));
        colBase.put("FOU_NOM", new Integer(FOU_NOM));
        colBase.put("FOU_ZEC_CODE", new Integer(FOU_ZEC_CODE));
        colBase.put("FOU_TYC_CODE", new Integer(FOU_TYC_CODE));
        colBase.put("FOU_PAY_CODE", new Integer(FOU_PAY_CODE));
        colBase.put("FOU_LAN_CODE", new Integer(FOU_LAN_CODE));
        colBase.put("FOU_DEV_CODE", new Integer(FOU_DEV_CODE));
        colBase.put("FOU_MTR_CODE", new Integer(FOU_MTR_CODE));
        colBase.put("FOU_MRG_CODE", new Integer(FOU_MRG_CODE));
        colBase.put("FOU_CRG_CODE", new Integer(FOU_CRG_CODE));
        colBase.put("FOU_MLI_CODE", new Integer(FOU_MLI_CODE));
        colBase.put("FOU_ADR3", new Integer(FOU_ADR3));
        colBase.put("FOU_BANQ", new Integer(FOU_BANQ));
        colBase.put("FOU_DT_DER_CMDE", new Integer(FOU_DT_DER_CMDE));
        colBase.put("FOU_DT_CREAT", new Integer(FOU_DT_CREAT));
        colBase.put("FOU_DELAI_LIV", new Integer(FOU_DELAI_LIV));
        colBase.put("FOU_CTRLE_FAC", new Integer(FOU_CTRLE_FAC));
        colBase.put("FOU_CPTE_COMPTA", new Integer(FOU_CPTE_COMPTA));
        colBase.put("FOU_CODE_LIBRE", new Integer(FOU_CODE_LIBRE));
        colBase.put("FOU_CODE_GUICHET", new Integer(FOU_CODE_GUICHET));
        colBase.put("FOU_CODE_GENCOD", new Integer(FOU_CODE_GENCOD));
        colBase.put("FOU_ADR1", new Integer(FOU_ADR1));
        colBase.put("FOU_VILLE", new Integer(FOU_VILLE));
        colBase.put("FOU_UTIL_MAJ", new Integer(FOU_UTIL_MAJ));
        colBase.put("FOU_TYPE_REM_DIFF", new Integer(FOU_TYPE_REM_DIFF));
        colBase.put("FOU_TX_REM_FAC3", new Integer(FOU_TX_REM_FAC3));
        colBase.put("FOU_TX_REM_FAC2", new Integer(FOU_TX_REM_FAC2));
        colBase.put("FOU_TX_REM_FAC1", new Integer(FOU_TX_REM_FAC1));
        colBase.put("FOU_TEL", new Integer(FOU_TEL));
        colBase.put("FOU_ADR3_BANQ", new Integer(FOU_ADR3_BANQ));
        colBase.put("FOU_ADR2_BANQ", new Integer(FOU_ADR2_BANQ));
        colBase.put("FOU_ADR2", new Integer(FOU_ADR2));
        colBase.put("FOU_ADR1_BANQ", new Integer(FOU_ADR1_BANQ));
        colBase.put("FOU_REGIME_TVA", new Integer(FOU_REGIME_TVA));
        colBase.put("FOU_REF_NOUS", new Integer(FOU_REF_NOUS));
        colBase.put("FOU_POSTAL", new Integer(FOU_POSTAL));
        colBase.put("FOU_MT_MIN_CMDE", new Integer(FOU_MT_MIN_CMDE));
        colBase.put("FOU_MT_FRANCO_PORT", new Integer(FOU_MT_FRANCO_PORT));
        colBase.put("FOU_GROUPE_ON", new Integer(FOU_GROUPE_ON));
        colBase.put("FOU_FAX", new Integer(FOU_FAX));
        colBase.put("FOU_CEE_COEF", new Integer(FOU_CEE_COEF));
        colBase.put("FOU_CEE_ID", new Integer(FOU_CEE_ID));
        colBase.put("FOU_CLE_CTRLE", new Integer(FOU_CLE_CTRLE));
        colBase.put("FOU_CODE_CPTE_BANQ", new Integer(FOU_CODE_CPTE_BANQ));
        colBase.put("FOU_CODE_BANQ", new Integer(FOU_CODE_BANQ));
        colBase.put("FOU_DT_MAJ", new Integer(FOU_DT_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("FOU_FOT_CODE", new Integer(FOU_FOT_CODE));
        colBase.put("FOU_TEL2", new Integer(FOU_TEL2));
        colBase.put("FOU_EMAIL", new Integer(FOU_EMAIL));
        colBase.put("FOU_FOU_CODE", new Integer(FOU_FOU_CODE));
        colBase.put("FOU_FOU_CODE_AGENTI", new Integer(FOU_FOU_CODE_AGENTI));
        colBase.put("FOU_FOU_CODE_AGENTE", new Integer(FOU_FOU_CODE_AGENTE));
        colBase.put("FOU_PAY_CODE_FAB", new Integer(FOU_PAY_CODE_FAB));
        colBase.put("FOU_ADR1_PAYE", new Integer(FOU_ADR1_PAYE));
        colBase.put("FOU_ADR2_PAYE", new Integer(FOU_ADR2_PAYE));
        colBase.put("FOU_POSTAL_PAYE", new Integer(FOU_POSTAL_PAYE));
        colBase.put("FOU_VILLE_PAYE", new Integer(FOU_VILLE_PAYE));
        colBase.put("FOU_PAY_CODE_PAYE", new Integer(FOU_PAY_CODE_PAYE));
        colBase.put("FOU_TEL1_PAYE", new Integer(FOU_TEL1_PAYE));
        colBase.put("FOU_TEL2_PAYE", new Integer(FOU_TEL2_PAYE));
        colBase.put("FOU_FAX_PAYE", new Integer(FOU_FAX_PAYE));
        colBase.put("FOU_EMAIL_PAYE", new Integer(FOU_EMAIL_PAYE));
        colBase.put("FOU_DT_ACCEPT", new Integer(FOU_DT_ACCEPT));
        colBase.put("FOU_DT_FIN_TRAV", new Integer(FOU_DT_FIN_TRAV));
        colBase.put("FOU_SIGNATAIRE", new Integer(FOU_SIGNATAIRE));
        colBase.put("FOU_QUAL_INDICE", new Integer(FOU_QUAL_INDICE));
        colBase.put("FOU_STAT_ETIQ", new Integer(FOU_STAT_ETIQ));
        colBase.put("FOU_STAT_CQ", new Integer(FOU_STAT_CQ));
        colBase.put("FOU_CONSIGNE", new Integer(FOU_CONSIGNE));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("FOU_ETAT", new Integer(FOU_ETAT));
        colBase.put("FOU_OBS", new Integer(FOU_OBS));
        colBase.put("FOU_CDE_COMPOSANTS", new Integer(FOU_CDE_COMPOSANTS));
        colBase.put("FOU_DEP_CODE", new Integer(FOU_DEP_CODE));
        colBase.put("FOU_OK_VISITE", new Integer(FOU_OK_VISITE));
        colBase.put("FOU_PREST_VISITE", new Integer(FOU_PREST_VISITE));
        colBase.put("FOU_DOMAINE", new Integer(FOU_DOMAINE));
        colBase.put("FOU_DEP_SOC_CODE", new Integer(FOU_DEP_SOC_CODE));
        colBase.put("FOU_DEP_CODE_TISSU", new Integer(FOU_DEP_CODE_TISSU));
        colBase.put("FOU_DEP_SOC_CODE_TISSU", new Integer(FOU_DEP_SOC_CODE_TISSU));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "FOU_CODE"};

    private static final int[] pkColNum = new int[]{3};

    private static final HashMap fkColName = new HashMap(21);

    static {
        fkColName.put("XN_COND_REG", new String[]{
            "FOU_CRG_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "FOU_DEP_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "FOU_DEP_CODE_TISSU"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "FOU_DEP_SOC_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "FOU_DEP_SOC_CODE_TISSU"
        });
        fkColName.put("XN_DEVISE", new String[]{
            "FOU_DEV_CODE"
        });
        fkColName.put("XN_FAM_FOUR", new String[]{
            "FOU_FFO_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_FOU_CODE_PX"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_FOU_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_FOU_CODE_AGENTI"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_FOU_CODE_AGENTE"
        });
        fkColName.put("XN_FOURNISSEUR_TYPE", new String[]{
            "FOU_FOT_CODE"
        });
        fkColName.put("XN_LANGUE", new String[]{
            "FOU_LAN_CODE"
        });
        fkColName.put("XN_MODE_LIVR", new String[]{
            "FOU_MLI_CODE"
        });
        fkColName.put("XN_MODE_REG", new String[]{
            "FOU_MRG_CODE"
        });
        fkColName.put("XN_MODE_TRANSP", new String[]{
            "FOU_MTR_CODE"
        });
        fkColName.put("XN_PAYS", new String[]{
            "FOU_PAY_CODE"
        });
        fkColName.put("XN_PAYS", new String[]{
            "FOU_PAY_CODE_FAB"
        });
        fkColName.put("XN_PAYS", new String[]{
            "FOU_PAY_CODE_PAYE"
        });
        fkColName.put("XN_TYPE_CONTACT", new String[]{
            "FOU_TYC_CODE"
        });
        fkColName.put("XN_ZONE_ECO", new String[]{
            "FOU_ZEC_CODE"
        });
    }

    static {
        fkColName.put("AR_CICLO_INSPECCION", new String[]{
            "FOU_CODE"
        });
        fkColName.put("AR_CONTROL_ENTREGAS", new String[]{
            "FOU_CODE"
        });
        fkColName.put("AR_INFORMES_RECHAZO", new String[]{
            "FOU_CODE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "FOU_CODE"
        });
        fkColName.put("AR_VAL_PROV", new String[]{
            "FOU_CODE"
        });
        fkColName.put("AR_VER", new String[]{
            "FOU_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("DT_INTEGR_BL_FOUR_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_ART_FOU", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_BL_FOUR_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_CLI", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_POT", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_CONTACT", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_EHF_ACHATS", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_EXCEP_TARIF_CLI", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_EXCEP_TARIF_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FAC_FOUR_TETE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FAM_ART_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FLASH_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FOU_ECHE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_GRILLE_TARIFS", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_HIST_ACHAT", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_HIST_LITIGE", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_INTERLOC_FOU", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_LOT_LIGNES", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_PRIX", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_REGL_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_TARIF_FOUR", new String[]{
            "FOU_CODE"
        });
        fkColName.put("XN_TYPE_DOC_CLI_FOU", new String[]{
            "FOU_CODE"
        });
    }


    private static final HashMap fkColNum = new HashMap(21);

    static {
        fkColNum.put("XN_COND_REG", new int[]{
            13
        });
        fkColNum.put("XN_DEPOT", new int[]{
            78
        });
        fkColNum.put("XN_DEPOT", new int[]{
            83
        });
        fkColNum.put("XN_DEPOT", new int[]{
            82
        });
        fkColNum.put("XN_DEPOT", new int[]{
            84
        });
        fkColNum.put("XN_DEVISE", new int[]{
            10
        });
        fkColNum.put("XN_FAM_FOUR", new int[]{
            4
        });
        fkColNum.put("XN_FOUR", new int[]{
            2
        });
        fkColNum.put("XN_FOUR", new int[]{
            54
        });
        fkColNum.put("XN_FOUR", new int[]{
            55
        });
        fkColNum.put("XN_FOUR", new int[]{
            56
        });
        fkColNum.put("XN_FOURNISSEUR_TYPE", new int[]{
            51
        });
        fkColNum.put("XN_LANGUE", new int[]{
            9
        });
        fkColNum.put("XN_MODE_LIVR", new int[]{
            14
        });
        fkColNum.put("XN_MODE_REG", new int[]{
            12
        });
        fkColNum.put("XN_MODE_TRANSP", new int[]{
            11
        });
        fkColNum.put("XN_PAYS", new int[]{
            8
        });
        fkColNum.put("XN_PAYS", new int[]{
            57
        });
        fkColNum.put("XN_PAYS", new int[]{
            62
        });
        fkColNum.put("XN_TYPE_CONTACT", new int[]{
            7
        });
        fkColNum.put("XN_ZONE_ECO", new int[]{
            6
        });
    }

    static {
        fkColNum.put("AR_CICLO_INSPECCION", new int[]{
            3
        });
        fkColNum.put("AR_CONTROL_ENTREGAS", new int[]{
            3
        });
        fkColNum.put("AR_INFORMES_RECHAZO", new int[]{
            3
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            3
        });
        fkColNum.put("AR_VAL_PROV", new int[]{
            3
        });
        fkColNum.put("AR_VER", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            3
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            3
        });
        fkColNum.put("DT_INTEGR_BL_FOUR_TETE", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_TETE", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_TETE", new int[]{
            3
        });
        fkColNum.put("XN_ART", new int[]{
            3
        });
        fkColNum.put("XN_ART_FOU", new int[]{
            3
        });
        fkColNum.put("XN_BL_FOUR_TETE", new int[]{
            3
        });
        fkColNum.put("XN_CLI", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_POT", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_TETE", new int[]{
            3
        });
        fkColNum.put("XN_CONTACT", new int[]{
            3
        });
        fkColNum.put("XN_DEPOT", new int[]{
            3
        });
        fkColNum.put("XN_EHF_ACHATS", new int[]{
            3
        });
        fkColNum.put("XN_EXCEP_TARIF_CLI", new int[]{
            3
        });
        fkColNum.put("XN_EXCEP_TARIF_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FAC_FOUR_TETE", new int[]{
            3
        });
        fkColNum.put("XN_FAM_ART_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FLASH_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FOU_ECHE", new int[]{
            3
        });
        fkColNum.put("XN_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_GRILLE_TARIFS", new int[]{
            3
        });
        fkColNum.put("XN_HIST_ACHAT", new int[]{
            3
        });
        fkColNum.put("XN_HIST_LITIGE", new int[]{
            3
        });
        fkColNum.put("XN_INTERLOC_FOU", new int[]{
            3
        });
        fkColNum.put("XN_LOT_LIGNES", new int[]{
            3
        });
        fkColNum.put("XN_PRIX", new int[]{
            3
        });
        fkColNum.put("XN_REGL_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_TARIF_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_TYPE_DOC_CLI_FOU", new int[]{
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

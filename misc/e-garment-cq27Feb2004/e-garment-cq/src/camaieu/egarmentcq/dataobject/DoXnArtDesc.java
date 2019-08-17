package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoXnArt correspondant à la table XN_ART
 */
public class DoXnArtDesc implements IDoDescription {
    public static final int ART_CODE = 0;
    public static final int ART_VAR1 = 1;
    public static final int ART_VAR2 = 2;
    public static final int ART_VAR3 = 3;
    public static final int ART_MOD_CODE = 4;
    public static final int ART_MOC_MOD_CODE = 5;
    public static final int ART_MOC_MOD_VAR1 = 6;
    public static final int ART_CAA_CODE = 7;
    public static final int ART_FOU_CODE = 8;
    public static final int ART_SFA_CODE = 9;
    public static final int ART_TXP_CODE = 10;
    public static final int ART_MES_CODE = 11;
    public static final int ART_FAA_CODE = 12;
    public static final int ART_MES_CODE_ACH = 13;
    public static final int ART_MES_CODE_VTE = 14;
    public static final int ART_PX_INIT = 15;
    public static final int ART_TYA_CODE = 16;
    public static final int ART_DES1 = 17;
    public static final int ART_DT_DER_CMDE = 18;
    public static final int ART_TVA_CODE = 19;
    public static final int ART_DECOMPOSE = 20;
    public static final int ART_DEP_SOC_CODE = 21;
    public static final int ART_PAY_CODE = 22;
    public static final int ART_DEP_CODE = 23;
    public static final int ART_ART_CODE = 24;
    public static final int ART_ART_VAR1 = 25;
    public static final int ART_ART_VAR2 = 26;
    public static final int ART_ART_VAR3 = 27;
    public static final int ART_DES2 = 28;
    public static final int ART_DT_CREAT = 29;
    public static final int ART_DT_MAJ = 30;
    public static final int ART_UTIL_MAJ = 31;
    public static final int ART_CAL_COMP_ON = 32;
    public static final int ART_SUB_TYPE = 33;
    public static final int ART_VOL = 34;
    public static final int ART_UNITE_PX_VTE = 35;
    public static final int ART_TYPE_ARRONDI = 36;
    public static final int ART_STOCK = 37;
    public static final int ART_SPCB = 38;
    public static final int ART_SERIE_LOT = 39;
    public static final int ART_REF_EMPLAC = 40;
    public static final int ART_REF_CATAL = 41;
    public static final int ART_QTE_MIN_VTE = 42;
    public static final int ART_DER_PX_ACHAT = 43;
    public static final int ART_CPTE_VTE = 44;
    public static final int ART_CEE_COEF = 45;
    public static final int ART_CEE_NOMEN = 46;
    public static final int ART_COEF_ACH_VTE = 47;
    public static final int ART_COEF_UN_ACH_STK = 48;
    public static final int ART_COEF_UN_STK_VTE = 49;
    public static final int ART_COND_STK = 50;
    public static final int ART_PX_VTE_MP = 51;
    public static final int ART_PX_REVIENT_MP = 52;
    public static final int ART_PX_REVIENT = 53;
    public static final int ART_PX_MINI = 54;
    public static final int ART_PX_MAXI = 55;
    public static final int ART_PX_ACH_MP = 56;
    public static final int ART_PDS = 57;
    public static final int ART_PCB = 58;
    public static final int ART_OBS = 59;
    public static final int ART_NET_ON = 60;
    public static final int ART_GENCOD = 61;
    public static final int ART_ETIQ_ON = 62;
    public static final int ART_ETAT = 63;
    public static final int ART_DT_LIM_CMDE_CLI = 64;
    public static final int ART_DT_DER_ACHAT = 65;
    public static final int ART_DER_PX_VTE = 66;
    public static final int ART_COND_VTE = 67;
    public static final int ART_CPTE_ACH = 68;
    public static final int ART_CPTE_ANAL_ACH = 69;
    public static final int ART_CPTE_ANAL_VTE = 70;
    public static final int XTX_ID = 71;
    public static final int ART_GARANTIE = 72;
    public static final int ART_HAUT = 73;
    public static final int ART_MES_CODE_STK = 74;
    public static final int ART_ARRONDIS_STK = 75;
    public static final int Z_STATUS = 76;
    public static final int ART_CONTRE_MARQUE_ON = 77;
    public static final int ART_DES_COURT = 78;
    public static final int ART_NB_COLIS = 79;
    public static final int ART_MAG_CODE = 80;
    public static final int ART_ROT_CLASS = 81;
    public static final int ART_ROT_JOUR = 82;
    public static final int ART_STE_ON = 83;
    public static final int ART_MES_CODE_STAT = 84;
    public static final int ART_COEF_UN_VTE_STAT = 85;
    public static final int ART_LONG = 86;
    public static final int ART_LARG = 87;
    public static final int ART_PDS_NET = 88;
    public static final int ART_CST_CODE = 89;
    public static final int ART_ZMG_CODE = 90;
    public static final int ART_FRAIS = 91;
    public static final int ART_QTE_TYPE = 92;
    public static final int ART_ARG_PERDIDAS = 93;
    public static final int ART_ARG_CATALOGO = 94;
    public static final int ART_ARG_LOTE_MINIMO = 95;
    public static final int ART_ARG_PCR_CODIGO = 96;
    public static final int ART_ARG_PCR_REVISION = 97;
    public static final int ART_ARG_CONTROL_REC = 98;
    public static final int ART_ARG_MODO_COMPRA = 99;
    public static final int ART_ARG_PLAZO_FABR = 100;
    public static final int ART_ARG_FABRICADO = 101;
    public static final int ART_TYPE_ARTICLE = 102;
    public static final int ART_DT_OK_NOMENCLATURE = 103;
    public static final int ART_ARG_QUALITE = 104;
    public static final int ART_CEN_NIV = 105;
    public static final int ART_PCR_CODIGO_ENTREP = 106;
    public static final int ART_EPAISSEUR_CINTRE = 107;
    public static final int ART_TYPE_ECLATEMENT = 108;

    public static final String tableName = "XN_ART";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "ART_CODE", "ART_VAR1", "ART_VAR2", "ART_VAR3", "ART_MOD_CODE", "ART_MOC_MOD_CODE", "ART_MOC_MOD_VAR1", "ART_CAA_CODE", "ART_FOU_CODE", "ART_SFA_CODE", "ART_TXP_CODE", "ART_MES_CODE", "ART_FAA_CODE", "ART_MES_CODE_ACH", "ART_MES_CODE_VTE", "ART_PX_INIT", "ART_TYA_CODE", "ART_DES1", "ART_DT_DER_CMDE", "ART_TVA_CODE", "ART_DECOMPOSE", "ART_DEP_SOC_CODE", "ART_PAY_CODE", "ART_DEP_CODE", "ART_ART_CODE", "ART_ART_VAR1", "ART_ART_VAR2", "ART_ART_VAR3", "ART_DES2", "ART_DT_CREAT", "ART_DT_MAJ", "ART_UTIL_MAJ", "ART_CAL_COMP_ON", "ART_SUB_TYPE", "ART_VOL", "ART_UNITE_PX_VTE", "ART_TYPE_ARRONDI", "ART_STOCK", "ART_SPCB", "ART_SERIE_LOT", "ART_REF_EMPLAC", "ART_REF_CATAL", "ART_QTE_MIN_VTE", "ART_DER_PX_ACHAT", "ART_CPTE_VTE", "ART_CEE_COEF", "ART_CEE_NOMEN", "ART_COEF_ACH_VTE", "ART_COEF_UN_ACH_STK", "ART_COEF_UN_STK_VTE", "ART_COND_STK", "ART_PX_VTE_MP", "ART_PX_REVIENT_MP", "ART_PX_REVIENT", "ART_PX_MINI", "ART_PX_MAXI", "ART_PX_ACH_MP", "ART_PDS", "ART_PCB", "ART_OBS", "ART_NET_ON", "ART_GENCOD", "ART_ETIQ_ON", "ART_ETAT", "ART_DT_LIM_CMDE_CLI", "ART_DT_DER_ACHAT", "ART_DER_PX_VTE", "ART_COND_VTE", "ART_CPTE_ACH", "ART_CPTE_ANAL_ACH", "ART_CPTE_ANAL_VTE", "XTX_ID", "ART_GARANTIE", "ART_HAUT", "ART_MES_CODE_STK", "ART_ARRONDIS_STK", "Z_STATUS", "ART_CONTRE_MARQUE_ON", "ART_DES_COURT", "ART_NB_COLIS", "ART_MAG_CODE", "ART_ROT_CLASS", "ART_ROT_JOUR", "ART_STE_ON", "ART_MES_CODE_STAT", "ART_COEF_UN_VTE_STAT", "ART_LONG", "ART_LARG", "ART_PDS_NET", "ART_CST_CODE", "ART_ZMG_CODE", "ART_FRAIS", "ART_QTE_TYPE", "ART_ARG_PERDIDAS", "ART_ARG_CATALOGO", "ART_ARG_LOTE_MINIMO", "ART_ARG_PCR_CODIGO", "ART_ARG_PCR_REVISION", "ART_ARG_CONTROL_REC", "ART_ARG_MODO_COMPRA", "ART_ARG_PLAZO_FABR", "ART_ARG_FABRICADO", "ART_TYPE_ARTICLE", "ART_DT_OK_NOMENCLATURE", "ART_ARG_QUALITE", "ART_CEN_NIV", "ART_PCR_CODIGO_ENTREP", "ART_EPAISSEUR_CINTRE", "ART_TYPE_ECLATEMENT"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(109);
        colBase.put("ART_CODE", new Integer(ART_CODE));
        colBase.put("ART_VAR1", new Integer(ART_VAR1));
        colBase.put("ART_VAR2", new Integer(ART_VAR2));
        colBase.put("ART_VAR3", new Integer(ART_VAR3));
        colBase.put("ART_MOD_CODE", new Integer(ART_MOD_CODE));
        colBase.put("ART_MOC_MOD_CODE", new Integer(ART_MOC_MOD_CODE));
        colBase.put("ART_MOC_MOD_VAR1", new Integer(ART_MOC_MOD_VAR1));
        colBase.put("ART_CAA_CODE", new Integer(ART_CAA_CODE));
        colBase.put("ART_FOU_CODE", new Integer(ART_FOU_CODE));
        colBase.put("ART_SFA_CODE", new Integer(ART_SFA_CODE));
        colBase.put("ART_TXP_CODE", new Integer(ART_TXP_CODE));
        colBase.put("ART_MES_CODE", new Integer(ART_MES_CODE));
        colBase.put("ART_FAA_CODE", new Integer(ART_FAA_CODE));
        colBase.put("ART_MES_CODE_ACH", new Integer(ART_MES_CODE_ACH));
        colBase.put("ART_MES_CODE_VTE", new Integer(ART_MES_CODE_VTE));
        colBase.put("ART_PX_INIT", new Integer(ART_PX_INIT));
        colBase.put("ART_TYA_CODE", new Integer(ART_TYA_CODE));
        colBase.put("ART_DES1", new Integer(ART_DES1));
        colBase.put("ART_DT_DER_CMDE", new Integer(ART_DT_DER_CMDE));
        colBase.put("ART_TVA_CODE", new Integer(ART_TVA_CODE));
        colBase.put("ART_DECOMPOSE", new Integer(ART_DECOMPOSE));
        colBase.put("ART_DEP_SOC_CODE", new Integer(ART_DEP_SOC_CODE));
        colBase.put("ART_PAY_CODE", new Integer(ART_PAY_CODE));
        colBase.put("ART_DEP_CODE", new Integer(ART_DEP_CODE));
        colBase.put("ART_ART_CODE", new Integer(ART_ART_CODE));
        colBase.put("ART_ART_VAR1", new Integer(ART_ART_VAR1));
        colBase.put("ART_ART_VAR2", new Integer(ART_ART_VAR2));
        colBase.put("ART_ART_VAR3", new Integer(ART_ART_VAR3));
        colBase.put("ART_DES2", new Integer(ART_DES2));
        colBase.put("ART_DT_CREAT", new Integer(ART_DT_CREAT));
        colBase.put("ART_DT_MAJ", new Integer(ART_DT_MAJ));
        colBase.put("ART_UTIL_MAJ", new Integer(ART_UTIL_MAJ));
        colBase.put("ART_CAL_COMP_ON", new Integer(ART_CAL_COMP_ON));
        colBase.put("ART_SUB_TYPE", new Integer(ART_SUB_TYPE));
        colBase.put("ART_VOL", new Integer(ART_VOL));
        colBase.put("ART_UNITE_PX_VTE", new Integer(ART_UNITE_PX_VTE));
        colBase.put("ART_TYPE_ARRONDI", new Integer(ART_TYPE_ARRONDI));
        colBase.put("ART_STOCK", new Integer(ART_STOCK));
        colBase.put("ART_SPCB", new Integer(ART_SPCB));
        colBase.put("ART_SERIE_LOT", new Integer(ART_SERIE_LOT));
        colBase.put("ART_REF_EMPLAC", new Integer(ART_REF_EMPLAC));
        colBase.put("ART_REF_CATAL", new Integer(ART_REF_CATAL));
        colBase.put("ART_QTE_MIN_VTE", new Integer(ART_QTE_MIN_VTE));
        colBase.put("ART_DER_PX_ACHAT", new Integer(ART_DER_PX_ACHAT));
        colBase.put("ART_CPTE_VTE", new Integer(ART_CPTE_VTE));
        colBase.put("ART_CEE_COEF", new Integer(ART_CEE_COEF));
        colBase.put("ART_CEE_NOMEN", new Integer(ART_CEE_NOMEN));
        colBase.put("ART_COEF_ACH_VTE", new Integer(ART_COEF_ACH_VTE));
        colBase.put("ART_COEF_UN_ACH_STK", new Integer(ART_COEF_UN_ACH_STK));
        colBase.put("ART_COEF_UN_STK_VTE", new Integer(ART_COEF_UN_STK_VTE));
        colBase.put("ART_COND_STK", new Integer(ART_COND_STK));
        colBase.put("ART_PX_VTE_MP", new Integer(ART_PX_VTE_MP));
        colBase.put("ART_PX_REVIENT_MP", new Integer(ART_PX_REVIENT_MP));
        colBase.put("ART_PX_REVIENT", new Integer(ART_PX_REVIENT));
        colBase.put("ART_PX_MINI", new Integer(ART_PX_MINI));
        colBase.put("ART_PX_MAXI", new Integer(ART_PX_MAXI));
        colBase.put("ART_PX_ACH_MP", new Integer(ART_PX_ACH_MP));
        colBase.put("ART_PDS", new Integer(ART_PDS));
        colBase.put("ART_PCB", new Integer(ART_PCB));
        colBase.put("ART_OBS", new Integer(ART_OBS));
        colBase.put("ART_NET_ON", new Integer(ART_NET_ON));
        colBase.put("ART_GENCOD", new Integer(ART_GENCOD));
        colBase.put("ART_ETIQ_ON", new Integer(ART_ETIQ_ON));
        colBase.put("ART_ETAT", new Integer(ART_ETAT));
        colBase.put("ART_DT_LIM_CMDE_CLI", new Integer(ART_DT_LIM_CMDE_CLI));
        colBase.put("ART_DT_DER_ACHAT", new Integer(ART_DT_DER_ACHAT));
        colBase.put("ART_DER_PX_VTE", new Integer(ART_DER_PX_VTE));
        colBase.put("ART_COND_VTE", new Integer(ART_COND_VTE));
        colBase.put("ART_CPTE_ACH", new Integer(ART_CPTE_ACH));
        colBase.put("ART_CPTE_ANAL_ACH", new Integer(ART_CPTE_ANAL_ACH));
        colBase.put("ART_CPTE_ANAL_VTE", new Integer(ART_CPTE_ANAL_VTE));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("ART_GARANTIE", new Integer(ART_GARANTIE));
        colBase.put("ART_HAUT", new Integer(ART_HAUT));
        colBase.put("ART_MES_CODE_STK", new Integer(ART_MES_CODE_STK));
        colBase.put("ART_ARRONDIS_STK", new Integer(ART_ARRONDIS_STK));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("ART_CONTRE_MARQUE_ON", new Integer(ART_CONTRE_MARQUE_ON));
        colBase.put("ART_DES_COURT", new Integer(ART_DES_COURT));
        colBase.put("ART_NB_COLIS", new Integer(ART_NB_COLIS));
        colBase.put("ART_MAG_CODE", new Integer(ART_MAG_CODE));
        colBase.put("ART_ROT_CLASS", new Integer(ART_ROT_CLASS));
        colBase.put("ART_ROT_JOUR", new Integer(ART_ROT_JOUR));
        colBase.put("ART_STE_ON", new Integer(ART_STE_ON));
        colBase.put("ART_MES_CODE_STAT", new Integer(ART_MES_CODE_STAT));
        colBase.put("ART_COEF_UN_VTE_STAT", new Integer(ART_COEF_UN_VTE_STAT));
        colBase.put("ART_LONG", new Integer(ART_LONG));
        colBase.put("ART_LARG", new Integer(ART_LARG));
        colBase.put("ART_PDS_NET", new Integer(ART_PDS_NET));
        colBase.put("ART_CST_CODE", new Integer(ART_CST_CODE));
        colBase.put("ART_ZMG_CODE", new Integer(ART_ZMG_CODE));
        colBase.put("ART_FRAIS", new Integer(ART_FRAIS));
        colBase.put("ART_QTE_TYPE", new Integer(ART_QTE_TYPE));
        colBase.put("ART_ARG_PERDIDAS", new Integer(ART_ARG_PERDIDAS));
        colBase.put("ART_ARG_CATALOGO", new Integer(ART_ARG_CATALOGO));
        colBase.put("ART_ARG_LOTE_MINIMO", new Integer(ART_ARG_LOTE_MINIMO));
        colBase.put("ART_ARG_PCR_CODIGO", new Integer(ART_ARG_PCR_CODIGO));
        colBase.put("ART_ARG_PCR_REVISION", new Integer(ART_ARG_PCR_REVISION));
        colBase.put("ART_ARG_CONTROL_REC", new Integer(ART_ARG_CONTROL_REC));
        colBase.put("ART_ARG_MODO_COMPRA", new Integer(ART_ARG_MODO_COMPRA));
        colBase.put("ART_ARG_PLAZO_FABR", new Integer(ART_ARG_PLAZO_FABR));
        colBase.put("ART_ARG_FABRICADO", new Integer(ART_ARG_FABRICADO));
        colBase.put("ART_TYPE_ARTICLE", new Integer(ART_TYPE_ARTICLE));
        colBase.put("ART_DT_OK_NOMENCLATURE", new Integer(ART_DT_OK_NOMENCLATURE));
        colBase.put("ART_ARG_QUALITE", new Integer(ART_ARG_QUALITE));
        colBase.put("ART_CEN_NIV", new Integer(ART_CEN_NIV));
        colBase.put("ART_PCR_CODIGO_ENTREP", new Integer(ART_PCR_CODIGO_ENTREP));
        colBase.put("ART_EPAISSEUR_CINTRE", new Integer(ART_EPAISSEUR_CINTRE));
        colBase.put("ART_TYPE_ECLATEMENT", new Integer(ART_TYPE_ECLATEMENT));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "ART_VAR3", "ART_VAR2", "ART_VAR1", "ART_CODE"};

    private static final int[] pkColNum = new int[]{3, 2, 1, 0};

    private static final HashMap fkColName = new HashMap(22);

    static {
        fkColName.put("XN_ART", new String[]{
            "ART_ART_CODE", "ART_ART_VAR1", "ART_ART_VAR2", "ART_ART_VAR3"
        });
        fkColName.put("XN_CAT_ART", new String[]{
            "ART_CAA_CODE"
        });
        fkColName.put("XN_COND_STANDARD", new String[]{
            "ART_CST_CODE"
        });
        fkColName.put("XN_COULEUR", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "ART_DEP_CODE"
        });
        fkColName.put("XN_DEPOT", new String[]{
            "ART_DEP_SOC_CODE"
        });
        fkColName.put("XN_FAM_ART", new String[]{
            "ART_FAA_CODE"
        });
        fkColName.put("XN_FOUR", new String[]{
            "ART_FOU_CODE"
        });
        fkColName.put("XN_MESURE", new String[]{
            "ART_MES_CODE"
        });
        fkColName.put("XN_MESURE", new String[]{
            "ART_MES_CODE_VTE"
        });
        fkColName.put("XN_MESURE", new String[]{
            "ART_MES_CODE_ACH"
        });
        fkColName.put("XN_MESURE", new String[]{
            "ART_MES_CODE_STAT"
        });
        fkColName.put("XN_MODELE", new String[]{
            "ART_MOD_CODE"
        });
        fkColName.put("XN_MODELE_COULEUR", new String[]{
            "ART_MOC_MOD_CODE"
        });
        fkColName.put("XN_MODELE_COULEUR", new String[]{
            "ART_MOC_MOD_VAR1"
        });
        fkColName.put("XN_PAYS", new String[]{
            "ART_PAY_CODE"
        });
        fkColName.put("XN_SFAM_ART", new String[]{
            "ART_FAA_CODE"
        });
        fkColName.put("XN_SFAM_ART", new String[]{
            "ART_SFA_CODE"
        });
        fkColName.put("XN_TAXE_PARA", new String[]{
            "ART_TXP_CODE"
        });
        fkColName.put("XN_TVA", new String[]{
            "ART_TVA_CODE"
        });
        fkColName.put("XN_TYPE_ART", new String[]{
            "ART_TYA_CODE"
        });
        fkColName.put("XN_ZONE_MAGASIN", new String[]{
            "ART_ZMG_CODE"
        });
    }

    static {
        fkColName.put("AR_ARC", new String[]{
            "ART_CODE", "ART_VAR1", "ART_VAR2", "ART_VAR3"
        });
        fkColName.put("AR_CICLO_INSPECCION", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_CICLO_INSPECCION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_CICLO_INSPECCION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_CICLO_INSPECCION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_CONTROL_ENTREGAS", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_CONTROL_ENTREGAS", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_CONTROL_ENTREGAS", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_CONTROL_ENTREGAS", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_EES", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_INFORMES_RECHAZO", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_INFORMES_RECHAZO", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_INFORMES_RECHAZO", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_INFORMES_RECHAZO", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_MAQ_EXCEP", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_MAQ_EXCEP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_MAQ_EXCEP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_MAQ_EXCEP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_NEC_BRUTAS", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_NEC_BRUTAS", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_NEC_BRUTAS", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_NEC_BRUTAS", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_NEC_BRUTAS_PREV", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_NEC_BRUTAS_PREV", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_NEC_BRUTAS_PREV", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_NEC_BRUTAS_PREV", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_NEC_NETAS", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_NEC_NETAS", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_NEC_NETAS", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_NEC_NETAS", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_NEC_NETAS_PREV", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_NEC_NETAS_PREV", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_NEC_NETAS_PREV", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_NEC_NETAS_PREV", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_OFS", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_OFS", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_OFS", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_OFS", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_PLAN_GESTION", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_PLAN_GESTION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_PLAN_GESTION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_PLAN_GESTION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_PREVISIONES", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_PREVISIONES", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_PREVISIONES", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_PREVISIONES", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_PREVISIONES_PROD", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_PREVISIONES_PROD", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_PREVISIONES_PROD", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_PREVISIONES_PROD", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_RUTA", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_RUTA", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_RUTA", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_RUTA", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "ART_VAR3"
        });
        fkColName.put("AR_VER", new String[]{
            "ART_CODE"
        });
        fkColName.put("AR_VER", new String[]{
            "ART_VAR1"
        });
        fkColName.put("AR_VER", new String[]{
            "ART_VAR2"
        });
        fkColName.put("AR_VER", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_CMDE_ACHAT_COMPO", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_CMDE_ACHAT_EHF", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_CMDE_ACHAT_ENTR", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_CMDE_ACHAT_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_CMDE_ACHAT_MC", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_INTEGR_BL_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_INTEGR_BL_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_INTEGR_BL_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_INTEGR_BL_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_PARAM_FACTURATION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_RECEP_ACHAT_COMPO", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_COMPO", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_RECEP_ACHAT_COMPO", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_RECEP_ACHAT_COMPO", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_RECEP_ACHAT_EHF", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_EHF", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_RECEP_ACHAT_EHF", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_RECEP_ACHAT_EHF", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_RECEP_ACHAT_ENTR", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_ENTR", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_RECEP_ACHAT_ENTR", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_RECEP_ACHAT_ENTR", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_RECEP_ACHAT_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_RECEP_ACHAT_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_RECEP_ACHAT_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_RECEP_ACHAT_MC", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_RECEP_ACHAT_MC", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_RECEP_ACHAT_MC", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_RECEP_ACHAT_MC", new String[]{
            "ART_VAR3"
        });
        fkColName.put("DT_STOCK_RESIDUEL_ART", new String[]{
            "ART_CODE"
        });
        fkColName.put("DT_STOCK_RESIDUEL_ART", new String[]{
            "ART_VAR1"
        });
        fkColName.put("DT_STOCK_RESIDUEL_ART", new String[]{
            "ART_VAR2"
        });
        fkColName.put("DT_STOCK_RESIDUEL_ART", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ABO_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ABO_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_FAB_ECLATE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_FAB_ECLATE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_FAB_ECLATE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_FAB_ECLATE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_FAB_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_FAB_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_FAB_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_FAB_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_FAB_TETE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_FAB_TETE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_FAB_TETE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_FAB_TETE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_FOU", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_FOU", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_FOU", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_FOU", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_LANGUE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_LANGUE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_LANGUE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_LANGUE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_NOMENCLA", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ART_REMPLACE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BL_FOUR_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_BUDGET_VENTE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_BUDGET_VENTE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_BUDGET_VENTE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_BUDGET_VENTE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_CLI_EHF", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_CLI_EHF", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_CLI_EHF", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_CLI_EHF", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_CLI_LIGNE_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_FOUR_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_CMDE_FOUR_POT", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_CMDE_FOUR_POT", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_CMDE_FOUR_POT", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_CMDE_FOUR_POT", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_COMPOSITION", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_COMPOSITION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_COMPOSITION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_COMPOSITION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_DESIGN_COM", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_DESIGN_COM", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_DESIGN_COM", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_DESIGN_COM", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_DEVIS_CLI_EHF", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_DEVIS_CLI_EHF", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_DEVIS_CLI_EHF", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_DEVIS_CLI_EHF", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_DEVIS_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_DEVIS_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_DEVIS_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_DEVIS_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_DEVIS_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_DEVIS_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_DEVIS_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_DEVIS_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_EMPLAC_PREPA_TETE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_EMPLAC_PREPA_TETE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_EMPLAC_PREPA_TETE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_EMPLAC_PREPA_TETE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_ENTRETIEN", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_ENTRETIEN", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_ENTRETIEN", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_ENTRETIEN", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_EXCEP_TARIF_CLI", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_EXCEP_TARIF_CLI", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_EXCEP_TARIF_CLI", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_EXCEP_TARIF_CLI", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_EXCEP_TARIF_FOUR", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_EXCEP_TARIF_FOUR", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_EXCEP_TARIF_FOUR", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_EXCEP_TARIF_FOUR", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAB_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAB_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAB_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAB_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAB_RETOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAB_RETOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAB_RETOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAB_RETOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAB_RETOUR_TETE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAB_RETOUR_TETE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAB_RETOUR_TETE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAB_RETOUR_TETE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAB_TETE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAB_TETE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAB_TETE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAB_TETE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAC_CLI_DECOMP", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAC_CLI_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FAC_FOUR_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_FLASH_ART", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_FLASH_ART", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_FLASH_ART", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_FLASH_ART", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_HIST_ACHAT", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_HIST_ACHAT", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_HIST_ACHAT", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_HIST_ACHAT", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_HIST_MVT_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_HIST_MVT_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_HIST_MVT_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_HIST_MVT_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_HIST_TARIF_CLI", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_HIST_TARIF_CLI", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_HIST_TARIF_CLI", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_HIST_TARIF_CLI", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_HIST_VENTE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_HIST_VENTE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_HIST_VENTE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_HIST_VENTE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_MES_CLI_ART", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_MES_CLI_ART", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_MES_CLI_ART", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_MES_CLI_ART", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_MVT_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_OPTION", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_PHOTO_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_PHOTO_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_PHOTO_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_PHOTO_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_STOCK", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_STOCK", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_STOCK", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_STOCK", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_STOCK_EMPLAC", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_STOCK_EMPLAC", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_STOCK_EMPLAC", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_STOCK_EMPLAC", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TARIF_CLI", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TARIF_CLI", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TARIF_CLI", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TARIF_CLI", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TARIF_FOUR", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TARIF_FOUR", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TARIF_FOUR", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TARIF_FOUR", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TRANS_ECART_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TRANS_ECART_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TRANS_ECART_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TRANS_ECART_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TRANS_EXPE_EHF", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TRANS_EXPE_EHF", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TRANS_EXPE_EHF", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TRANS_EXPE_EHF", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TRANS_EXPE_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TRANS_EXPE_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TRANS_EXPE_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TRANS_EXPE_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TRANS_RECEP_LIGNE", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TRANS_RECEP_LIGNE", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TRANS_RECEP_LIGNE", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TRANS_RECEP_LIGNE", new String[]{
            "ART_VAR3"
        });
        fkColName.put("XN_TVA_EXCEP", new String[]{
            "ART_CODE"
        });
        fkColName.put("XN_TVA_EXCEP", new String[]{
            "ART_VAR1"
        });
        fkColName.put("XN_TVA_EXCEP", new String[]{
            "ART_VAR2"
        });
        fkColName.put("XN_TVA_EXCEP", new String[]{
            "ART_VAR3"
        });
    }


    private static final HashMap fkColNum = new HashMap(22);

    static {
        fkColNum.put("XN_ART", new int[]{
            24, 25, 26, 27
        });
        fkColNum.put("XN_CAT_ART", new int[]{
            7
        });
        fkColNum.put("XN_COND_STANDARD", new int[]{
            89
        });
        fkColNum.put("XN_COULEUR", new int[]{
            1
        });
        fkColNum.put("XN_DEPOT", new int[]{
            23
        });
        fkColNum.put("XN_DEPOT", new int[]{
            21
        });
        fkColNum.put("XN_FAM_ART", new int[]{
            12
        });
        fkColNum.put("XN_FOUR", new int[]{
            8
        });
        fkColNum.put("XN_MESURE", new int[]{
            11
        });
        fkColNum.put("XN_MESURE", new int[]{
            14
        });
        fkColNum.put("XN_MESURE", new int[]{
            13
        });
        fkColNum.put("XN_MESURE", new int[]{
            84
        });
        fkColNum.put("XN_MODELE", new int[]{
            4
        });
        fkColNum.put("XN_MODELE_COULEUR", new int[]{
            5
        });
        fkColNum.put("XN_MODELE_COULEUR", new int[]{
            6
        });
        fkColNum.put("XN_PAYS", new int[]{
            22
        });
        fkColNum.put("XN_SFAM_ART", new int[]{
            12
        });
        fkColNum.put("XN_SFAM_ART", new int[]{
            9
        });
        fkColNum.put("XN_TAXE_PARA", new int[]{
            10
        });
        fkColNum.put("XN_TVA", new int[]{
            19
        });
        fkColNum.put("XN_TYPE_ART", new int[]{
            16
        });
        fkColNum.put("XN_ZONE_MAGASIN", new int[]{
            90
        });
    }

    static {
        fkColNum.put("AR_ARC", new int[]{
            0, 1, 2, 3
        });
        fkColNum.put("AR_CICLO_INSPECCION", new int[]{
            0
        });
        fkColNum.put("AR_CICLO_INSPECCION", new int[]{
            1
        });
        fkColNum.put("AR_CICLO_INSPECCION", new int[]{
            2
        });
        fkColNum.put("AR_CICLO_INSPECCION", new int[]{
            3
        });
        fkColNum.put("AR_CONTROL_ENTREGAS", new int[]{
            0
        });
        fkColNum.put("AR_CONTROL_ENTREGAS", new int[]{
            1
        });
        fkColNum.put("AR_CONTROL_ENTREGAS", new int[]{
            2
        });
        fkColNum.put("AR_CONTROL_ENTREGAS", new int[]{
            3
        });
        fkColNum.put("AR_EES", new int[]{
            0
        });
        fkColNum.put("AR_EES", new int[]{
            0
        });
        fkColNum.put("AR_EES", new int[]{
            1
        });
        fkColNum.put("AR_EES", new int[]{
            1
        });
        fkColNum.put("AR_EES", new int[]{
            2
        });
        fkColNum.put("AR_EES", new int[]{
            2
        });
        fkColNum.put("AR_EES", new int[]{
            3
        });
        fkColNum.put("AR_EES", new int[]{
            3
        });
        fkColNum.put("AR_INFORMES_RECHAZO", new int[]{
            0
        });
        fkColNum.put("AR_INFORMES_RECHAZO", new int[]{
            1
        });
        fkColNum.put("AR_INFORMES_RECHAZO", new int[]{
            2
        });
        fkColNum.put("AR_INFORMES_RECHAZO", new int[]{
            3
        });
        fkColNum.put("AR_MAQ_EXCEP", new int[]{
            0
        });
        fkColNum.put("AR_MAQ_EXCEP", new int[]{
            1
        });
        fkColNum.put("AR_MAQ_EXCEP", new int[]{
            2
        });
        fkColNum.put("AR_MAQ_EXCEP", new int[]{
            3
        });
        fkColNum.put("AR_NEC_BRUTAS", new int[]{
            0
        });
        fkColNum.put("AR_NEC_BRUTAS", new int[]{
            1
        });
        fkColNum.put("AR_NEC_BRUTAS", new int[]{
            2
        });
        fkColNum.put("AR_NEC_BRUTAS", new int[]{
            3
        });
        fkColNum.put("AR_NEC_BRUTAS_PREV", new int[]{
            0
        });
        fkColNum.put("AR_NEC_BRUTAS_PREV", new int[]{
            1
        });
        fkColNum.put("AR_NEC_BRUTAS_PREV", new int[]{
            2
        });
        fkColNum.put("AR_NEC_BRUTAS_PREV", new int[]{
            3
        });
        fkColNum.put("AR_NEC_NETAS", new int[]{
            0
        });
        fkColNum.put("AR_NEC_NETAS", new int[]{
            1
        });
        fkColNum.put("AR_NEC_NETAS", new int[]{
            2
        });
        fkColNum.put("AR_NEC_NETAS", new int[]{
            3
        });
        fkColNum.put("AR_NEC_NETAS_PREV", new int[]{
            0
        });
        fkColNum.put("AR_NEC_NETAS_PREV", new int[]{
            1
        });
        fkColNum.put("AR_NEC_NETAS_PREV", new int[]{
            2
        });
        fkColNum.put("AR_NEC_NETAS_PREV", new int[]{
            3
        });
        fkColNum.put("AR_OFS", new int[]{
            0
        });
        fkColNum.put("AR_OFS", new int[]{
            1
        });
        fkColNum.put("AR_OFS", new int[]{
            2
        });
        fkColNum.put("AR_OFS", new int[]{
            3
        });
        fkColNum.put("AR_PLAN_GESTION", new int[]{
            0
        });
        fkColNum.put("AR_PLAN_GESTION", new int[]{
            1
        });
        fkColNum.put("AR_PLAN_GESTION", new int[]{
            2
        });
        fkColNum.put("AR_PLAN_GESTION", new int[]{
            3
        });
        fkColNum.put("AR_PREVISIONES", new int[]{
            0
        });
        fkColNum.put("AR_PREVISIONES", new int[]{
            1
        });
        fkColNum.put("AR_PREVISIONES", new int[]{
            2
        });
        fkColNum.put("AR_PREVISIONES", new int[]{
            3
        });
        fkColNum.put("AR_PREVISIONES_PROD", new int[]{
            0
        });
        fkColNum.put("AR_PREVISIONES_PROD", new int[]{
            1
        });
        fkColNum.put("AR_PREVISIONES_PROD", new int[]{
            2
        });
        fkColNum.put("AR_PREVISIONES_PROD", new int[]{
            3
        });
        fkColNum.put("AR_RUTA", new int[]{
            0
        });
        fkColNum.put("AR_RUTA", new int[]{
            1
        });
        fkColNum.put("AR_RUTA", new int[]{
            2
        });
        fkColNum.put("AR_RUTA", new int[]{
            3
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            0
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            1
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            2
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            3
        });
        fkColNum.put("AR_VER", new int[]{
            0
        });
        fkColNum.put("AR_VER", new int[]{
            1
        });
        fkColNum.put("AR_VER", new int[]{
            2
        });
        fkColNum.put("AR_VER", new int[]{
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
        fkColNum.put("DT_INTEGR_BL_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("DT_INTEGR_BL_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("DT_INTEGR_BL_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("DT_INTEGR_BL_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            0
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            0
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            1
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            1
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            2
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            2
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            3
        });
        fkColNum.put("DT_PARAM_FACTURATION", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_COMPO", new int[]{
            0
        });
        fkColNum.put("DT_RECEP_ACHAT_COMPO", new int[]{
            1
        });
        fkColNum.put("DT_RECEP_ACHAT_COMPO", new int[]{
            2
        });
        fkColNum.put("DT_RECEP_ACHAT_COMPO", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_EHF", new int[]{
            0
        });
        fkColNum.put("DT_RECEP_ACHAT_EHF", new int[]{
            1
        });
        fkColNum.put("DT_RECEP_ACHAT_EHF", new int[]{
            2
        });
        fkColNum.put("DT_RECEP_ACHAT_EHF", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_ENTR", new int[]{
            0
        });
        fkColNum.put("DT_RECEP_ACHAT_ENTR", new int[]{
            1
        });
        fkColNum.put("DT_RECEP_ACHAT_ENTR", new int[]{
            2
        });
        fkColNum.put("DT_RECEP_ACHAT_ENTR", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_LIGNE", new int[]{
            0
        });
        fkColNum.put("DT_RECEP_ACHAT_LIGNE", new int[]{
            1
        });
        fkColNum.put("DT_RECEP_ACHAT_LIGNE", new int[]{
            2
        });
        fkColNum.put("DT_RECEP_ACHAT_LIGNE", new int[]{
            3
        });
        fkColNum.put("DT_RECEP_ACHAT_MC", new int[]{
            0
        });
        fkColNum.put("DT_RECEP_ACHAT_MC", new int[]{
            1
        });
        fkColNum.put("DT_RECEP_ACHAT_MC", new int[]{
            2
        });
        fkColNum.put("DT_RECEP_ACHAT_MC", new int[]{
            3
        });
        fkColNum.put("DT_STOCK_RESIDUEL_ART", new int[]{
            0
        });
        fkColNum.put("DT_STOCK_RESIDUEL_ART", new int[]{
            1
        });
        fkColNum.put("DT_STOCK_RESIDUEL_ART", new int[]{
            2
        });
        fkColNum.put("DT_STOCK_RESIDUEL_ART", new int[]{
            3
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_ABO_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_ABO_CLI_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_ART", new int[]{
            0
        });
        fkColNum.put("XN_ART", new int[]{
            1
        });
        fkColNum.put("XN_ART", new int[]{
            2
        });
        fkColNum.put("XN_ART", new int[]{
            3
        });
        fkColNum.put("XN_ART_FAB_ECLATE", new int[]{
            0
        });
        fkColNum.put("XN_ART_FAB_ECLATE", new int[]{
            1
        });
        fkColNum.put("XN_ART_FAB_ECLATE", new int[]{
            2
        });
        fkColNum.put("XN_ART_FAB_ECLATE", new int[]{
            3
        });
        fkColNum.put("XN_ART_FAB_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_ART_FAB_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_ART_FAB_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_ART_FAB_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_ART_FAB_TETE", new int[]{
            0
        });
        fkColNum.put("XN_ART_FAB_TETE", new int[]{
            1
        });
        fkColNum.put("XN_ART_FAB_TETE", new int[]{
            2
        });
        fkColNum.put("XN_ART_FAB_TETE", new int[]{
            3
        });
        fkColNum.put("XN_ART_FOU", new int[]{
            0
        });
        fkColNum.put("XN_ART_FOU", new int[]{
            1
        });
        fkColNum.put("XN_ART_FOU", new int[]{
            2
        });
        fkColNum.put("XN_ART_FOU", new int[]{
            3
        });
        fkColNum.put("XN_ART_LANGUE", new int[]{
            0
        });
        fkColNum.put("XN_ART_LANGUE", new int[]{
            1
        });
        fkColNum.put("XN_ART_LANGUE", new int[]{
            2
        });
        fkColNum.put("XN_ART_LANGUE", new int[]{
            3
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            0
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            0
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            1
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            1
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            2
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            2
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            3
        });
        fkColNum.put("XN_ART_NOMENCLA", new int[]{
            3
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            0
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            0
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            1
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            1
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            2
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            2
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            3
        });
        fkColNum.put("XN_ART_REMPLACE", new int[]{
            3
        });
        fkColNum.put("XN_BL_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_BL_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_BL_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_BL_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_BL_CLI_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_BL_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_BL_FOUR_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_BUDGET_VENTE", new int[]{
            0
        });
        fkColNum.put("XN_BUDGET_VENTE", new int[]{
            1
        });
        fkColNum.put("XN_BUDGET_VENTE", new int[]{
            2
        });
        fkColNum.put("XN_BUDGET_VENTE", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_CLI_EHF", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_CLI_EHF", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_CLI_EHF", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_CLI_EHF", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_CLI_LIGNE_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_CMDE_FOUR_POT", new int[]{
            0
        });
        fkColNum.put("XN_CMDE_FOUR_POT", new int[]{
            1
        });
        fkColNum.put("XN_CMDE_FOUR_POT", new int[]{
            2
        });
        fkColNum.put("XN_CMDE_FOUR_POT", new int[]{
            3
        });
        fkColNum.put("XN_COMPOSITION", new int[]{
            0
        });
        fkColNum.put("XN_COMPOSITION", new int[]{
            1
        });
        fkColNum.put("XN_COMPOSITION", new int[]{
            2
        });
        fkColNum.put("XN_COMPOSITION", new int[]{
            3
        });
        fkColNum.put("XN_DESIGN_COM", new int[]{
            0
        });
        fkColNum.put("XN_DESIGN_COM", new int[]{
            1
        });
        fkColNum.put("XN_DESIGN_COM", new int[]{
            2
        });
        fkColNum.put("XN_DESIGN_COM", new int[]{
            3
        });
        fkColNum.put("XN_DEVIS_CLI_EHF", new int[]{
            0
        });
        fkColNum.put("XN_DEVIS_CLI_EHF", new int[]{
            1
        });
        fkColNum.put("XN_DEVIS_CLI_EHF", new int[]{
            2
        });
        fkColNum.put("XN_DEVIS_CLI_EHF", new int[]{
            3
        });
        fkColNum.put("XN_DEVIS_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_DEVIS_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_DEVIS_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_DEVIS_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_DEVIS_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_DEVIS_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_DEVIS_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_DEVIS_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_EMPLAC_PREPA_TETE", new int[]{
            0
        });
        fkColNum.put("XN_EMPLAC_PREPA_TETE", new int[]{
            1
        });
        fkColNum.put("XN_EMPLAC_PREPA_TETE", new int[]{
            2
        });
        fkColNum.put("XN_EMPLAC_PREPA_TETE", new int[]{
            3
        });
        fkColNum.put("XN_ENTRETIEN", new int[]{
            0
        });
        fkColNum.put("XN_ENTRETIEN", new int[]{
            1
        });
        fkColNum.put("XN_ENTRETIEN", new int[]{
            2
        });
        fkColNum.put("XN_ENTRETIEN", new int[]{
            3
        });
        fkColNum.put("XN_EXCEP_TARIF_CLI", new int[]{
            0
        });
        fkColNum.put("XN_EXCEP_TARIF_CLI", new int[]{
            1
        });
        fkColNum.put("XN_EXCEP_TARIF_CLI", new int[]{
            2
        });
        fkColNum.put("XN_EXCEP_TARIF_CLI", new int[]{
            3
        });
        fkColNum.put("XN_EXCEP_TARIF_FOUR", new int[]{
            0
        });
        fkColNum.put("XN_EXCEP_TARIF_FOUR", new int[]{
            1
        });
        fkColNum.put("XN_EXCEP_TARIF_FOUR", new int[]{
            2
        });
        fkColNum.put("XN_EXCEP_TARIF_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_FAB_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_FAB_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_FAB_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_FAB_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_FAB_RETOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_FAB_RETOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_FAB_RETOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_FAB_RETOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_FAB_RETOUR_TETE", new int[]{
            0
        });
        fkColNum.put("XN_FAB_RETOUR_TETE", new int[]{
            1
        });
        fkColNum.put("XN_FAB_RETOUR_TETE", new int[]{
            2
        });
        fkColNum.put("XN_FAB_RETOUR_TETE", new int[]{
            3
        });
        fkColNum.put("XN_FAB_TETE", new int[]{
            0
        });
        fkColNum.put("XN_FAB_TETE", new int[]{
            1
        });
        fkColNum.put("XN_FAB_TETE", new int[]{
            2
        });
        fkColNum.put("XN_FAB_TETE", new int[]{
            3
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            0
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            1
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            2
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_FAC_CLI_DECOMP", new int[]{
            3
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_FAC_CLI_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_FAC_FOUR_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_FLASH_ART", new int[]{
            0
        });
        fkColNum.put("XN_FLASH_ART", new int[]{
            1
        });
        fkColNum.put("XN_FLASH_ART", new int[]{
            2
        });
        fkColNum.put("XN_FLASH_ART", new int[]{
            3
        });
        fkColNum.put("XN_HIST_ACHAT", new int[]{
            0
        });
        fkColNum.put("XN_HIST_ACHAT", new int[]{
            1
        });
        fkColNum.put("XN_HIST_ACHAT", new int[]{
            2
        });
        fkColNum.put("XN_HIST_ACHAT", new int[]{
            3
        });
        fkColNum.put("XN_HIST_MVT_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_HIST_MVT_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_HIST_MVT_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_HIST_MVT_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_HIST_TARIF_CLI", new int[]{
            0
        });
        fkColNum.put("XN_HIST_TARIF_CLI", new int[]{
            1
        });
        fkColNum.put("XN_HIST_TARIF_CLI", new int[]{
            2
        });
        fkColNum.put("XN_HIST_TARIF_CLI", new int[]{
            3
        });
        fkColNum.put("XN_HIST_VENTE", new int[]{
            0
        });
        fkColNum.put("XN_HIST_VENTE", new int[]{
            1
        });
        fkColNum.put("XN_HIST_VENTE", new int[]{
            2
        });
        fkColNum.put("XN_HIST_VENTE", new int[]{
            3
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            0
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            1
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            2
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            3
        });
        fkColNum.put("XN_MES_CLI_ART", new int[]{
            0
        });
        fkColNum.put("XN_MES_CLI_ART", new int[]{
            1
        });
        fkColNum.put("XN_MES_CLI_ART", new int[]{
            2
        });
        fkColNum.put("XN_MES_CLI_ART", new int[]{
            3
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_MVT_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_OPTION", new int[]{
            0
        });
        fkColNum.put("XN_OPTION", new int[]{
            0
        });
        fkColNum.put("XN_OPTION", new int[]{
            1
        });
        fkColNum.put("XN_OPTION", new int[]{
            1
        });
        fkColNum.put("XN_OPTION", new int[]{
            2
        });
        fkColNum.put("XN_OPTION", new int[]{
            2
        });
        fkColNum.put("XN_OPTION", new int[]{
            3
        });
        fkColNum.put("XN_OPTION", new int[]{
            3
        });
        fkColNum.put("XN_PHOTO_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_PHOTO_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_PHOTO_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_PHOTO_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_STOCK", new int[]{
            0
        });
        fkColNum.put("XN_STOCK", new int[]{
            1
        });
        fkColNum.put("XN_STOCK", new int[]{
            2
        });
        fkColNum.put("XN_STOCK", new int[]{
            3
        });
        fkColNum.put("XN_STOCK_EMPLAC", new int[]{
            0
        });
        fkColNum.put("XN_STOCK_EMPLAC", new int[]{
            1
        });
        fkColNum.put("XN_STOCK_EMPLAC", new int[]{
            2
        });
        fkColNum.put("XN_STOCK_EMPLAC", new int[]{
            3
        });
        fkColNum.put("XN_TARIF_CLI", new int[]{
            0
        });
        fkColNum.put("XN_TARIF_CLI", new int[]{
            1
        });
        fkColNum.put("XN_TARIF_CLI", new int[]{
            2
        });
        fkColNum.put("XN_TARIF_CLI", new int[]{
            3
        });
        fkColNum.put("XN_TARIF_FOUR", new int[]{
            0
        });
        fkColNum.put("XN_TARIF_FOUR", new int[]{
            1
        });
        fkColNum.put("XN_TARIF_FOUR", new int[]{
            2
        });
        fkColNum.put("XN_TARIF_FOUR", new int[]{
            3
        });
        fkColNum.put("XN_TRANS_ECART_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_TRANS_ECART_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_TRANS_ECART_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_TRANS_ECART_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_TRANS_EXPE_EHF", new int[]{
            0
        });
        fkColNum.put("XN_TRANS_EXPE_EHF", new int[]{
            1
        });
        fkColNum.put("XN_TRANS_EXPE_EHF", new int[]{
            2
        });
        fkColNum.put("XN_TRANS_EXPE_EHF", new int[]{
            3
        });
        fkColNum.put("XN_TRANS_EXPE_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_TRANS_EXPE_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_TRANS_EXPE_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_TRANS_EXPE_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_TRANS_RECEP_LIGNE", new int[]{
            0
        });
        fkColNum.put("XN_TRANS_RECEP_LIGNE", new int[]{
            1
        });
        fkColNum.put("XN_TRANS_RECEP_LIGNE", new int[]{
            2
        });
        fkColNum.put("XN_TRANS_RECEP_LIGNE", new int[]{
            3
        });
        fkColNum.put("XN_TVA_EXCEP", new int[]{
            0
        });
        fkColNum.put("XN_TVA_EXCEP", new int[]{
            1
        });
        fkColNum.put("XN_TVA_EXCEP", new int[]{
            2
        });
        fkColNum.put("XN_TVA_EXCEP", new int[]{
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

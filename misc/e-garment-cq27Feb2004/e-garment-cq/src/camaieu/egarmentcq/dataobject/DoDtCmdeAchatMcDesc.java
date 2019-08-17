package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtCmdeAchatMc correspondant à la table DT_CMDE_ACHAT_MC
 */
public class DoDtCmdeAchatMcDesc implements IDoDescription {
    public static final int CAM_CAT_DEP_CODE = 0;
    public static final int CAM_CAT_DEP_SOC_CODE = 1;
    public static final int CAM_CAT_NO_CMDE = 2;
    public static final int CAM_CAT_NO_VERSION = 3;
    public static final int CAM_NO_LIGNE = 4;
    public static final int CAM_ART_CODE = 5;
    public static final int CAM_ART_VAR1 = 6;
    public static final int CAM_ART_VAR2 = 7;
    public static final int CAM_ART_VAR3 = 8;
    public static final int CAM_MT_HT = 9;
    public static final int CAM_NAT_COND = 10;
    public static final int CAM_PCB = 11;
    public static final int CAM_PX_ACH_BRUT = 12;
    public static final int CAM_PX_ACH_NET = 13;
    public static final int CAM_QTE_CMDE = 14;
    public static final int CAM_QTE_RECUE = 15;
    public static final int CAM_QTE_RETOURNEE = 16;
    public static final int CAM_QTE_VALIDEE = 17;
    public static final int CAM_SEMAINE_PREV_LIVR = 18;
    public static final int CAM_DT_PREV_LIVR1 = 19;
    public static final int CAM_SPCB = 20;
    public static final int CAM_TVA_CODE = 21;
    public static final int CAM_TXP_CODE = 22;
    public static final int CAM_TX_REM_LIG1 = 23;
    public static final int CAM_TX_REM_LIG2 = 24;
    public static final int CAM_TX_REM_LIG3 = 25;
    public static final int CAM_TX_REM_LIG4 = 26;
    public static final int CAM_TCA_CODE = 27;
    public static final int CAM_UNITE_PX = 28;
    public static final int CAM_MT_EHF = 29;
    public static final int CAM_MT_EHF_COMP = 30;
    public static final int CAM_INDEX = 31;
    public static final int CAM_DT_CREAT = 32;
    public static final int CAM_DT_MAJ = 33;
    public static final int CAM_UTIL_MAJ = 34;
    public static final int XTX_ID = 35;
    public static final int Z_STATUS = 36;
    public static final int CAM_CREE_EHF = 37;
    public static final int CAM_QTE_OF = 38;
    public static final int CAM_CLT_AN = 39;
    public static final int CAM_CLT_CODE = 40;
    public static final int CAM_CLT_SAI_CODE = 41;
    public static final int CAM_VER_NO_VERSION = 42;
    public static final int CAM_VER_CLIENTE = 43;

    public static final String tableName = "DT_CMDE_ACHAT_MC";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "CAM_CAT_DEP_CODE", "CAM_CAT_DEP_SOC_CODE", "CAM_CAT_NO_CMDE", "CAM_CAT_NO_VERSION", "CAM_NO_LIGNE", "CAM_ART_CODE", "CAM_ART_VAR1", "CAM_ART_VAR2", "CAM_ART_VAR3", "CAM_MT_HT", "CAM_NAT_COND", "CAM_PCB", "CAM_PX_ACH_BRUT", "CAM_PX_ACH_NET", "CAM_QTE_CMDE", "CAM_QTE_RECUE", "CAM_QTE_RETOURNEE", "CAM_QTE_VALIDEE", "CAM_SEMAINE_PREV_LIVR", "CAM_DT_PREV_LIVR1", "CAM_SPCB", "CAM_TVA_CODE", "CAM_TXP_CODE", "CAM_TX_REM_LIG1", "CAM_TX_REM_LIG2", "CAM_TX_REM_LIG3", "CAM_TX_REM_LIG4", "CAM_TCA_CODE", "CAM_UNITE_PX", "CAM_MT_EHF", "CAM_MT_EHF_COMP", "CAM_INDEX", "CAM_DT_CREAT", "CAM_DT_MAJ", "CAM_UTIL_MAJ", "XTX_ID", "Z_STATUS", "CAM_CREE_EHF", "CAM_QTE_OF", "CAM_CLT_AN", "CAM_CLT_CODE", "CAM_CLT_SAI_CODE", "CAM_VER_NO_VERSION", "CAM_VER_CLIENTE"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(44);
        colBase.put("CAM_CAT_DEP_CODE", new Integer(CAM_CAT_DEP_CODE));
        colBase.put("CAM_CAT_DEP_SOC_CODE", new Integer(CAM_CAT_DEP_SOC_CODE));
        colBase.put("CAM_CAT_NO_CMDE", new Integer(CAM_CAT_NO_CMDE));
        colBase.put("CAM_CAT_NO_VERSION", new Integer(CAM_CAT_NO_VERSION));
        colBase.put("CAM_NO_LIGNE", new Integer(CAM_NO_LIGNE));
        colBase.put("CAM_ART_CODE", new Integer(CAM_ART_CODE));
        colBase.put("CAM_ART_VAR1", new Integer(CAM_ART_VAR1));
        colBase.put("CAM_ART_VAR2", new Integer(CAM_ART_VAR2));
        colBase.put("CAM_ART_VAR3", new Integer(CAM_ART_VAR3));
        colBase.put("CAM_MT_HT", new Integer(CAM_MT_HT));
        colBase.put("CAM_NAT_COND", new Integer(CAM_NAT_COND));
        colBase.put("CAM_PCB", new Integer(CAM_PCB));
        colBase.put("CAM_PX_ACH_BRUT", new Integer(CAM_PX_ACH_BRUT));
        colBase.put("CAM_PX_ACH_NET", new Integer(CAM_PX_ACH_NET));
        colBase.put("CAM_QTE_CMDE", new Integer(CAM_QTE_CMDE));
        colBase.put("CAM_QTE_RECUE", new Integer(CAM_QTE_RECUE));
        colBase.put("CAM_QTE_RETOURNEE", new Integer(CAM_QTE_RETOURNEE));
        colBase.put("CAM_QTE_VALIDEE", new Integer(CAM_QTE_VALIDEE));
        colBase.put("CAM_SEMAINE_PREV_LIVR", new Integer(CAM_SEMAINE_PREV_LIVR));
        colBase.put("CAM_DT_PREV_LIVR1", new Integer(CAM_DT_PREV_LIVR1));
        colBase.put("CAM_SPCB", new Integer(CAM_SPCB));
        colBase.put("CAM_TVA_CODE", new Integer(CAM_TVA_CODE));
        colBase.put("CAM_TXP_CODE", new Integer(CAM_TXP_CODE));
        colBase.put("CAM_TX_REM_LIG1", new Integer(CAM_TX_REM_LIG1));
        colBase.put("CAM_TX_REM_LIG2", new Integer(CAM_TX_REM_LIG2));
        colBase.put("CAM_TX_REM_LIG3", new Integer(CAM_TX_REM_LIG3));
        colBase.put("CAM_TX_REM_LIG4", new Integer(CAM_TX_REM_LIG4));
        colBase.put("CAM_TCA_CODE", new Integer(CAM_TCA_CODE));
        colBase.put("CAM_UNITE_PX", new Integer(CAM_UNITE_PX));
        colBase.put("CAM_MT_EHF", new Integer(CAM_MT_EHF));
        colBase.put("CAM_MT_EHF_COMP", new Integer(CAM_MT_EHF_COMP));
        colBase.put("CAM_INDEX", new Integer(CAM_INDEX));
        colBase.put("CAM_DT_CREAT", new Integer(CAM_DT_CREAT));
        colBase.put("CAM_DT_MAJ", new Integer(CAM_DT_MAJ));
        colBase.put("CAM_UTIL_MAJ", new Integer(CAM_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("CAM_CREE_EHF", new Integer(CAM_CREE_EHF));
        colBase.put("CAM_QTE_OF", new Integer(CAM_QTE_OF));
        colBase.put("CAM_CLT_AN", new Integer(CAM_CLT_AN));
        colBase.put("CAM_CLT_CODE", new Integer(CAM_CLT_CODE));
        colBase.put("CAM_CLT_SAI_CODE", new Integer(CAM_CLT_SAI_CODE));
        colBase.put("CAM_VER_NO_VERSION", new Integer(CAM_VER_NO_VERSION));
        colBase.put("CAM_VER_CLIENTE", new Integer(CAM_VER_CLIENTE));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "CAM_NO_LIGNE", "CAM_CAT_NO_VERSION", "CAM_CAT_NO_CMDE", "CAM_CAT_DEP_SOC_CODE", "CAM_CAT_DEP_CODE", "CAM_ART_VAR3", "CAM_ART_VAR2", "CAM_ART_VAR1", "CAM_ART_CODE"};

    private static final int[] pkColNum = new int[]{4, 3, 2, 1, 0, 8, 7, 6, 5};

    private static final HashMap fkColName = new HashMap(15);

    static {
        fkColName.put("AR_VER", new String[]{
            "CAM_ART_CODE", "CAM_ART_VAR1", "CAM_ART_VAR2", "CAM_ART_VAR3", "CAM_VER_CLIENTE", "CAM_VER_NO_VERSION"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "CAM_CAT_DEP_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "CAM_CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "CAM_CAT_NO_CMDE"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "CAM_CAT_NO_VERSION"
        });
        fkColName.put("DT_TYPE_CARTON", new String[]{
            "CAM_TCA_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "CAM_ART_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "CAM_ART_VAR1"
        });
        fkColName.put("XN_ART", new String[]{
            "CAM_ART_VAR2"
        });
        fkColName.put("XN_ART", new String[]{
            "CAM_ART_VAR3"
        });
        fkColName.put("XN_COLLECTION", new String[]{
            "CAM_CLT_AN"
        });
        fkColName.put("XN_COLLECTION", new String[]{
            "CAM_CLT_SAI_CODE"
        });
        fkColName.put("XN_COLLECTION", new String[]{
            "CAM_CLT_CODE"
        });
        fkColName.put("XN_TAXE_PARA", new String[]{
            "CAM_TXP_CODE"
        });
        fkColName.put("XN_TVA", new String[]{
            "CAM_TVA_CODE"
        });
    }

    static {
        fkColName.put("AR_OFS", new String[]{
            "CAM_CAT_DEP_CODE", "CAM_CAT_DEP_SOC_CODE", "CAM_CAT_NO_CMDE", "CAM_CAT_NO_VERSION", "CAM_NO_LIGNE", "CAM_ART_CODE", "CAM_ART_VAR1", "CAM_ART_VAR2", "CAM_ART_VAR3"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_CAT_DEP_CODE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_CAT_DEP_SOC_CODE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_CAT_NO_CMDE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_CAT_NO_VERSION"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_NO_LIGNE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_ART_CODE"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_ART_VAR1"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_ART_VAR2"
        });
        fkColName.put("AR_SOLIC_COMPRA", new String[]{
            "CAM_ART_VAR3"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_CAT_DEP_CODE"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_CAT_DEP_SOC_CODE"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_CAT_NO_CMDE"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_CAT_NO_VERSION"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_NO_LIGNE"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_ART_CODE"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_ART_VAR1"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_ART_VAR2"
        });
        fkColName.put("DT_RAR_VRAC", new String[]{
            "CAM_ART_VAR3"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_CAT_DEP_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_CAT_DEP_SOC_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_CAT_NO_CMDE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_CAT_NO_VERSION"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_NO_LIGNE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_ART_CODE"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_ART_VAR1"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_ART_VAR2"
        });
        fkColName.put("XN_LOT_TETE", new String[]{
            "CAM_ART_VAR3"
        });
    }


    private static final HashMap fkColNum = new HashMap(15);

    static {
        fkColNum.put("AR_VER", new int[]{
            5, 6, 7, 8, 43, 42
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            1
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            2
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            3
        });
        fkColNum.put("DT_TYPE_CARTON", new int[]{
            27
        });
        fkColNum.put("XN_ART", new int[]{
            5
        });
        fkColNum.put("XN_ART", new int[]{
            6
        });
        fkColNum.put("XN_ART", new int[]{
            7
        });
        fkColNum.put("XN_ART", new int[]{
            8
        });
        fkColNum.put("XN_COLLECTION", new int[]{
            39
        });
        fkColNum.put("XN_COLLECTION", new int[]{
            41
        });
        fkColNum.put("XN_COLLECTION", new int[]{
            40
        });
        fkColNum.put("XN_TAXE_PARA", new int[]{
            22
        });
        fkColNum.put("XN_TVA", new int[]{
            21
        });
    }

    static {
        fkColNum.put("AR_OFS", new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8
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
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            4
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            5
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            6
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            7
        });
        fkColNum.put("AR_SOLIC_COMPRA", new int[]{
            8
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            0
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            1
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            2
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            3
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            4
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            5
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            6
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            7
        });
        fkColNum.put("DT_RAR_VRAC", new int[]{
            8
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
        fkColNum.put("XN_LOT_TETE", new int[]{
            4
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            5
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            6
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            7
        });
        fkColNum.put("XN_LOT_TETE", new int[]{
            8
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

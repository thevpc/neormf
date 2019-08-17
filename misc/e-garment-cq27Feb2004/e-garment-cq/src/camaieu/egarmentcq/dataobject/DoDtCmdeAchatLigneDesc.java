package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtCmdeAchatLigne correspondant à la table DT_CMDE_ACHAT_LIGNE
 */
public class DoDtCmdeAchatLigneDesc implements IDoDescription {
    public static final int CAL_CAT_DEP_CODE = 0;
    public static final int CAL_CAT_DEP_SOC_CODE = 1;
    public static final int CAL_CAT_NO_CMDE = 2;
    public static final int CAL_CAT_NO_VERSION = 3;
    public static final int CAL_NO_LIGNE = 4;
    public static final int CAL_ART_CODE = 5;
    public static final int CAL_ART_VAR1 = 6;
    public static final int CAL_ART_VAR2 = 7;
    public static final int CAL_ART_VAR3 = 8;
    public static final int CAL_NO_LIGNE_MC = 9;
    public static final int CAL_PX_ACH_BRUT = 10;
    public static final int CAL_PX_ACH_NEG = 11;
    public static final int CAL_PX_ACH_NET = 12;
    public static final int CAL_PX_NET_ON = 13;
    public static final int CAL_QTE_CMDE = 14;
    public static final int CAL_QTE_RECUE = 15;
    public static final int CAL_QTE_RETOURNEE = 16;
    public static final int CAL_QTE_SOLDE = 17;
    public static final int CAL_QTE_VALIDEE = 18;
    public static final int CAL_SEMAINE_PREV_LIVR = 19;
    public static final int CAL_DT_PREV_LIVR1 = 20;
    public static final int CAL_TVA_CODE = 21;
    public static final int CAL_TXP_CODE = 22;
    public static final int CAL_TX_REM_LIG1 = 23;
    public static final int CAL_TX_REM_LIG2 = 24;
    public static final int CAL_TX_REM_LIG3 = 25;
    public static final int CAL_TX_REM_LIG4 = 26;
    public static final int CAL_UNITE_PX = 27;
    public static final int CAL_PDS = 28;
    public static final int CAL_VOL = 29;
    public static final int CAL_MT_EHF = 30;
    public static final int CAL_MT_EHF_COMP = 31;
    public static final int CAL_SOLDE_ON = 32;
    public static final int CAL_INDEX = 33;
    public static final int CAL_DT_CREAT = 34;
    public static final int CAL_DT_MAJ = 35;
    public static final int CAL_UTIL_MAJ = 36;
    public static final int XTX_ID = 37;
    public static final int Z_STATUS = 38;
    public static final int CAL_ARG_TRATADA01 = 39;

    public static final String tableName = "DT_CMDE_ACHAT_LIGNE";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "CAL_CAT_DEP_CODE", "CAL_CAT_DEP_SOC_CODE", "CAL_CAT_NO_CMDE", "CAL_CAT_NO_VERSION", "CAL_NO_LIGNE", "CAL_ART_CODE", "CAL_ART_VAR1", "CAL_ART_VAR2", "CAL_ART_VAR3", "CAL_NO_LIGNE_MC", "CAL_PX_ACH_BRUT", "CAL_PX_ACH_NEG", "CAL_PX_ACH_NET", "CAL_PX_NET_ON", "CAL_QTE_CMDE", "CAL_QTE_RECUE", "CAL_QTE_RETOURNEE", "CAL_QTE_SOLDE", "CAL_QTE_VALIDEE", "CAL_SEMAINE_PREV_LIVR", "CAL_DT_PREV_LIVR1", "CAL_TVA_CODE", "CAL_TXP_CODE", "CAL_TX_REM_LIG1", "CAL_TX_REM_LIG2", "CAL_TX_REM_LIG3", "CAL_TX_REM_LIG4", "CAL_UNITE_PX", "CAL_PDS", "CAL_VOL", "CAL_MT_EHF", "CAL_MT_EHF_COMP", "CAL_SOLDE_ON", "CAL_INDEX", "CAL_DT_CREAT", "CAL_DT_MAJ", "CAL_UTIL_MAJ", "XTX_ID", "Z_STATUS", "CAL_ARG_TRATADA_01"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(40);
        colBase.put("CAL_CAT_DEP_CODE", new Integer(CAL_CAT_DEP_CODE));
        colBase.put("CAL_CAT_DEP_SOC_CODE", new Integer(CAL_CAT_DEP_SOC_CODE));
        colBase.put("CAL_CAT_NO_CMDE", new Integer(CAL_CAT_NO_CMDE));
        colBase.put("CAL_CAT_NO_VERSION", new Integer(CAL_CAT_NO_VERSION));
        colBase.put("CAL_NO_LIGNE", new Integer(CAL_NO_LIGNE));
        colBase.put("CAL_ART_CODE", new Integer(CAL_ART_CODE));
        colBase.put("CAL_ART_VAR1", new Integer(CAL_ART_VAR1));
        colBase.put("CAL_ART_VAR2", new Integer(CAL_ART_VAR2));
        colBase.put("CAL_ART_VAR3", new Integer(CAL_ART_VAR3));
        colBase.put("CAL_NO_LIGNE_MC", new Integer(CAL_NO_LIGNE_MC));
        colBase.put("CAL_PX_ACH_BRUT", new Integer(CAL_PX_ACH_BRUT));
        colBase.put("CAL_PX_ACH_NEG", new Integer(CAL_PX_ACH_NEG));
        colBase.put("CAL_PX_ACH_NET", new Integer(CAL_PX_ACH_NET));
        colBase.put("CAL_PX_NET_ON", new Integer(CAL_PX_NET_ON));
        colBase.put("CAL_QTE_CMDE", new Integer(CAL_QTE_CMDE));
        colBase.put("CAL_QTE_RECUE", new Integer(CAL_QTE_RECUE));
        colBase.put("CAL_QTE_RETOURNEE", new Integer(CAL_QTE_RETOURNEE));
        colBase.put("CAL_QTE_SOLDE", new Integer(CAL_QTE_SOLDE));
        colBase.put("CAL_QTE_VALIDEE", new Integer(CAL_QTE_VALIDEE));
        colBase.put("CAL_SEMAINE_PREV_LIVR", new Integer(CAL_SEMAINE_PREV_LIVR));
        colBase.put("CAL_DT_PREV_LIVR1", new Integer(CAL_DT_PREV_LIVR1));
        colBase.put("CAL_TVA_CODE", new Integer(CAL_TVA_CODE));
        colBase.put("CAL_TXP_CODE", new Integer(CAL_TXP_CODE));
        colBase.put("CAL_TX_REM_LIG1", new Integer(CAL_TX_REM_LIG1));
        colBase.put("CAL_TX_REM_LIG2", new Integer(CAL_TX_REM_LIG2));
        colBase.put("CAL_TX_REM_LIG3", new Integer(CAL_TX_REM_LIG3));
        colBase.put("CAL_TX_REM_LIG4", new Integer(CAL_TX_REM_LIG4));
        colBase.put("CAL_UNITE_PX", new Integer(CAL_UNITE_PX));
        colBase.put("CAL_PDS", new Integer(CAL_PDS));
        colBase.put("CAL_VOL", new Integer(CAL_VOL));
        colBase.put("CAL_MT_EHF", new Integer(CAL_MT_EHF));
        colBase.put("CAL_MT_EHF_COMP", new Integer(CAL_MT_EHF_COMP));
        colBase.put("CAL_SOLDE_ON", new Integer(CAL_SOLDE_ON));
        colBase.put("CAL_INDEX", new Integer(CAL_INDEX));
        colBase.put("CAL_DT_CREAT", new Integer(CAL_DT_CREAT));
        colBase.put("CAL_DT_MAJ", new Integer(CAL_DT_MAJ));
        colBase.put("CAL_UTIL_MAJ", new Integer(CAL_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("CAL_ARG_TRATADA_01", new Integer(CAL_ARG_TRATADA01));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "CAL_NO_LIGNE", "CAL_CAT_NO_VERSION", "CAL_CAT_NO_CMDE", "CAL_CAT_DEP_SOC_CODE", "CAL_CAT_DEP_CODE"};

    private static final int[] pkColNum = new int[]{4, 3, 2, 1, 0};

    private static final HashMap fkColName = new HashMap(7);

    static {
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "CAL_CAT_DEP_CODE", "CAL_CAT_DEP_SOC_CODE", "CAL_CAT_NO_CMDE", "CAL_CAT_NO_VERSION"
        });
        fkColName.put("XN_ART", new String[]{
            "CAL_ART_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "CAL_ART_VAR1"
        });
        fkColName.put("XN_ART", new String[]{
            "CAL_ART_VAR2"
        });
        fkColName.put("XN_ART", new String[]{
            "CAL_ART_VAR3"
        });
        fkColName.put("XN_TAXE_PARA", new String[]{
            "CAL_TXP_CODE"
        });
        fkColName.put("XN_TVA", new String[]{
            "CAL_TVA_CODE"
        });
    }

    static {
        fkColName.put("AR_POF", new String[]{
            "CAL_CAT_DEP_CODE", "CAL_CAT_DEP_SOC_CODE", "CAL_CAT_NO_CMDE", "CAL_CAT_NO_VERSION", "CAL_NO_LIGNE"
        });
    }


    private static final HashMap fkColNum = new HashMap(7);

    static {
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            0, 1, 2, 3
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
        fkColNum.put("XN_TAXE_PARA", new int[]{
            22
        });
        fkColNum.put("XN_TVA", new int[]{
            21
        });
    }

    static {
        fkColNum.put("AR_POF", new int[]{
            0, 1, 2, 3, 4
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

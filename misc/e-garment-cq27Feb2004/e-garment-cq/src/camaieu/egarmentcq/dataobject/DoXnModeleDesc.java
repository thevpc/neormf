package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoXnModele correspondant à la table XN_MODELE
 */
public class DoXnModeleDesc implements IDoDescription {
    public static final int MOD_CODE = 0;
    public static final int MOD_DES = 1;
    public static final int MOD_LIB = 2;
    public static final int MOD_NAT_COND = 3;
    public static final int MOD_NOMEN = 4;
    public static final int MOD_ART_TEST = 5;
    public static final int MOD_CAT_AMF = 6;
    public static final int MOD_GRT_CODE = 7;
    public static final int MOD_SFA_CODE = 8;
    public static final int MOD_SFA_FAA_CODE = 9;
    public static final int MOD_TVA_CODE = 10;
    public static final int MOD_CEE_COEF = 11;
    public static final int MOD_PX_INIT = 12;
    public static final int MOD_PCB = 13;
    public static final int MOD_SPCB = 14;
    public static final int MOD_COLIS_POIDS = 15;
    public static final int MOD_COLIS_VOL = 16;
    public static final int MOD_NB_VAR = 17;
    public static final int MOD_DT_FIN_VIE = 18;
    public static final int MOD_DT_CREAT = 19;
    public static final int MOD_DT_MAJ = 20;
    public static final int MOD_UTIL_MAJ = 21;
    public static final int XTX_ID = 22;
    public static final int Z_STATUS = 23;
    public static final int MOD_TCA_CODE = 24;
    public static final int MOD_TYPE = 25;
    public static final int MOD_TYPE_ARTICLE = 26;
    public static final int MOD_ARG_PCR_CODIGO = 27;
    public static final int MOD_ARG_QUALITE = 28;
    public static final int MOD_CEN_NIV = 29;
    public static final int MOD_PCR_CODIGO_ENTREP = 30;
    public static final int MOD_EPAISSEUR_CINTRE = 31;
    public static final int MOD_TYPE_ECLATEMENT = 32;

    public static final String tableName = "XN_MODELE";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "MOD_CODE", "MOD_DES", "MOD_LIB", "MOD_NAT_COND", "MOD_NOMEN", "MOD_ART_TEST", "MOD_CAT_AMF", "MOD_GRT_CODE", "MOD_SFA_CODE", "MOD_SFA_FAA_CODE", "MOD_TVA_CODE", "MOD_CEE_COEF", "MOD_PX_INIT", "MOD_PCB", "MOD_SPCB", "MOD_COLIS_POIDS", "MOD_COLIS_VOL", "MOD_NB_VAR", "MOD_DT_FIN_VIE", "MOD_DT_CREAT", "MOD_DT_MAJ", "MOD_UTIL_MAJ", "XTX_ID", "Z_STATUS", "MOD_TCA_CODE", "MOD_TYPE", "MOD_TYPE_ARTICLE", "MOD_ARG_PCR_CODIGO", "MOD_ARG_QUALITE", "MOD_CEN_NIV", "MOD_PCR_CODIGO_ENTREP", "MOD_EPAISSEUR_CINTRE", "MOD_TYPE_ECLATEMENT"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(33);
        colBase.put("MOD_CODE", new Integer(MOD_CODE));
        colBase.put("MOD_DES", new Integer(MOD_DES));
        colBase.put("MOD_LIB", new Integer(MOD_LIB));
        colBase.put("MOD_NAT_COND", new Integer(MOD_NAT_COND));
        colBase.put("MOD_NOMEN", new Integer(MOD_NOMEN));
        colBase.put("MOD_ART_TEST", new Integer(MOD_ART_TEST));
        colBase.put("MOD_CAT_AMF", new Integer(MOD_CAT_AMF));
        colBase.put("MOD_GRT_CODE", new Integer(MOD_GRT_CODE));
        colBase.put("MOD_SFA_CODE", new Integer(MOD_SFA_CODE));
        colBase.put("MOD_SFA_FAA_CODE", new Integer(MOD_SFA_FAA_CODE));
        colBase.put("MOD_TVA_CODE", new Integer(MOD_TVA_CODE));
        colBase.put("MOD_CEE_COEF", new Integer(MOD_CEE_COEF));
        colBase.put("MOD_PX_INIT", new Integer(MOD_PX_INIT));
        colBase.put("MOD_PCB", new Integer(MOD_PCB));
        colBase.put("MOD_SPCB", new Integer(MOD_SPCB));
        colBase.put("MOD_COLIS_POIDS", new Integer(MOD_COLIS_POIDS));
        colBase.put("MOD_COLIS_VOL", new Integer(MOD_COLIS_VOL));
        colBase.put("MOD_NB_VAR", new Integer(MOD_NB_VAR));
        colBase.put("MOD_DT_FIN_VIE", new Integer(MOD_DT_FIN_VIE));
        colBase.put("MOD_DT_CREAT", new Integer(MOD_DT_CREAT));
        colBase.put("MOD_DT_MAJ", new Integer(MOD_DT_MAJ));
        colBase.put("MOD_UTIL_MAJ", new Integer(MOD_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("MOD_TCA_CODE", new Integer(MOD_TCA_CODE));
        colBase.put("MOD_TYPE", new Integer(MOD_TYPE));
        colBase.put("MOD_TYPE_ARTICLE", new Integer(MOD_TYPE_ARTICLE));
        colBase.put("MOD_ARG_PCR_CODIGO", new Integer(MOD_ARG_PCR_CODIGO));
        colBase.put("MOD_ARG_QUALITE", new Integer(MOD_ARG_QUALITE));
        colBase.put("MOD_CEN_NIV", new Integer(MOD_CEN_NIV));
        colBase.put("MOD_PCR_CODIGO_ENTREP", new Integer(MOD_PCR_CODIGO_ENTREP));
        colBase.put("MOD_EPAISSEUR_CINTRE", new Integer(MOD_EPAISSEUR_CINTRE));
        colBase.put("MOD_TYPE_ECLATEMENT", new Integer(MOD_TYPE_ECLATEMENT));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "MOD_CODE"};

    private static final int[] pkColNum = new int[]{0};

    private static final HashMap fkColName = new HashMap(6);

    static {
        fkColName.put("XN_CEE_NOMEN", new String[]{
            "MOD_NOMEN", "MOD_CEN_NIV"
        });
        fkColName.put("XN_FAM_ART", new String[]{
            "MOD_SFA_FAA_CODE"
        });
        fkColName.put("XN_GRILLE_TAILLES", new String[]{
            "MOD_GRT_CODE"
        });
        fkColName.put("XN_SFAM_ART", new String[]{
            "MOD_SFA_FAA_CODE"
        });
        fkColName.put("XN_SFAM_ART", new String[]{
            "MOD_SFA_CODE"
        });
        fkColName.put("XN_TVA", new String[]{
            "MOD_TVA_CODE"
        });
    }

    static {
        fkColName.put("CM_MAJ_PRMP_INTEGRATION", new String[]{
            "MOD_CODE"
        });
        fkColName.put("CM_MII_DDE", new String[]{
            "MOD_CODE"
        });
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "MOD_CODE"
        });
        fkColName.put("DT_W_CMDE_ACHAT", new String[]{
            "MOD_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "MOD_CODE"
        });
        fkColName.put("XN_MODELE_COULEUR", new String[]{
            "MOD_CODE"
        });
        fkColName.put("XN_W_STOCK_CONSULTE", new String[]{
            "MOD_CODE"
        });
    }


    private static final HashMap fkColNum = new HashMap(6);

    static {
        fkColNum.put("XN_CEE_NOMEN", new int[]{
            4, 29
        });
        fkColNum.put("XN_FAM_ART", new int[]{
            9
        });
        fkColNum.put("XN_GRILLE_TAILLES", new int[]{
            7
        });
        fkColNum.put("XN_SFAM_ART", new int[]{
            9
        });
        fkColNum.put("XN_SFAM_ART", new int[]{
            8
        });
        fkColNum.put("XN_TVA", new int[]{
            10
        });
    }

    static {
        fkColNum.put("CM_MAJ_PRMP_INTEGRATION", new int[]{
            0
        });
        fkColNum.put("CM_MII_DDE", new int[]{
            0
        });
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            0
        });
        fkColNum.put("DT_W_CMDE_ACHAT", new int[]{
            0
        });
        fkColNum.put("XN_ART", new int[]{
            0
        });
        fkColNum.put("XN_MODELE_COULEUR", new int[]{
            0
        });
        fkColNum.put("XN_W_STOCK_CONSULTE", new int[]{
            0
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

package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtEnvoiCqSource correspondant à la table DT_ENVOI_CQ_SOURCE
 */
public class DoDtEnvoiCqSourceDesc implements IDoDescription {
    public static final int ECS_ART_CODE = 0;
    public static final int ECS_ART_VAR1 = 1;
    public static final int ECS_ART_VAR2 = 2;
    public static final int ECS_ART_VAR3 = 3;
    public static final int ECS_FOU_CODE = 4;
    public static final int ECS_PREST_FOU_CODE = 5;
    public static final int ECS_FAA_CODE = 6;
    public static final int ECS_DT_ENVOI_DTECH = 7;
    public static final int ECS_NO_COLIS_DTECH = 8;
    public static final int ECS_DT_RECEP_DTECH = 9;
    public static final int ECS_RMQ_DTECH = 10;
    public static final int ECS_DT_ENVOI_ECHAN = 11;
    public static final int ECS_NO_COLIS_ECHAN = 12;
    public static final int ECS_DT_RECEP_ECHAN = 13;
    public static final int ECS_RMQ_ECHAN = 14;
    public static final int ECS_DT_ENVOI_BIB = 15;
    public static final int ECS_NO_COLIS_BIB = 16;
    public static final int ECS_DT_RECEP_BIB = 17;
    public static final int ECS_RMQ_BIB = 18;
    public static final int ECS_CAT_DEP_CODE = 19;
    public static final int ECS_CAT_DEP_SOC_CODE = 20;
    public static final int ECS_CAT_NO_CMDE = 21;
    public static final int ECS_CAT_NO_VERSION = 22;
    public static final int ECS_DT_CREAT = 23;
    public static final int ECS_DT_MAJ = 24;
    public static final int ECS_UTIL_MAJ = 25;
    public static final int XTX_ID = 26;
    public static final int Z_STATUS = 27;
    public static final int ECS_INDEX = 28;

    public static final String tableName = "DT_ENVOI_CQ_SOURCE";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "ECS_ART_CODE", "ECS_ART_VAR1", "ECS_ART_VAR2", "ECS_ART_VAR3", "ECS_FOU_CODE", "ECS_PREST_FOU_CODE", "ECS_FAA_CODE", "ECS_DT_ENVOI_DTECH", "ECS_NO_COLIS_DTECH", "ECS_DT_RECEP_DTECH", "ECS_RMQ_DTECH", "ECS_DT_ENVOI_ECHAN", "ECS_NO_COLIS_ECHAN", "ECS_DT_RECEP_ECHAN", "ECS_RMQ_ECHAN", "ECS_DT_ENVOI_BIB", "ECS_NO_COLIS_BIB", "ECS_DT_RECEP_BIB", "ECS_RMQ_BIB", "ECS_CAT_DEP_CODE", "ECS_CAT_DEP_SOC_CODE", "ECS_CAT_NO_CMDE", "ECS_CAT_NO_VERSION", "ECS_DT_CREAT", "ECS_DT_MAJ", "ECS_UTIL_MAJ", "XTX_ID", "Z_STATUS", "ECS_INDEX"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(29);
        colBase.put("ECS_ART_CODE", new Integer(ECS_ART_CODE));
        colBase.put("ECS_ART_VAR1", new Integer(ECS_ART_VAR1));
        colBase.put("ECS_ART_VAR2", new Integer(ECS_ART_VAR2));
        colBase.put("ECS_ART_VAR3", new Integer(ECS_ART_VAR3));
        colBase.put("ECS_FOU_CODE", new Integer(ECS_FOU_CODE));
        colBase.put("ECS_PREST_FOU_CODE", new Integer(ECS_PREST_FOU_CODE));
        colBase.put("ECS_FAA_CODE", new Integer(ECS_FAA_CODE));
        colBase.put("ECS_DT_ENVOI_DTECH", new Integer(ECS_DT_ENVOI_DTECH));
        colBase.put("ECS_NO_COLIS_DTECH", new Integer(ECS_NO_COLIS_DTECH));
        colBase.put("ECS_DT_RECEP_DTECH", new Integer(ECS_DT_RECEP_DTECH));
        colBase.put("ECS_RMQ_DTECH", new Integer(ECS_RMQ_DTECH));
        colBase.put("ECS_DT_ENVOI_ECHAN", new Integer(ECS_DT_ENVOI_ECHAN));
        colBase.put("ECS_NO_COLIS_ECHAN", new Integer(ECS_NO_COLIS_ECHAN));
        colBase.put("ECS_DT_RECEP_ECHAN", new Integer(ECS_DT_RECEP_ECHAN));
        colBase.put("ECS_RMQ_ECHAN", new Integer(ECS_RMQ_ECHAN));
        colBase.put("ECS_DT_ENVOI_BIB", new Integer(ECS_DT_ENVOI_BIB));
        colBase.put("ECS_NO_COLIS_BIB", new Integer(ECS_NO_COLIS_BIB));
        colBase.put("ECS_DT_RECEP_BIB", new Integer(ECS_DT_RECEP_BIB));
        colBase.put("ECS_RMQ_BIB", new Integer(ECS_RMQ_BIB));
        colBase.put("ECS_CAT_DEP_CODE", new Integer(ECS_CAT_DEP_CODE));
        colBase.put("ECS_CAT_DEP_SOC_CODE", new Integer(ECS_CAT_DEP_SOC_CODE));
        colBase.put("ECS_CAT_NO_CMDE", new Integer(ECS_CAT_NO_CMDE));
        colBase.put("ECS_CAT_NO_VERSION", new Integer(ECS_CAT_NO_VERSION));
        colBase.put("ECS_DT_CREAT", new Integer(ECS_DT_CREAT));
        colBase.put("ECS_DT_MAJ", new Integer(ECS_DT_MAJ));
        colBase.put("ECS_UTIL_MAJ", new Integer(ECS_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("ECS_INDEX", new Integer(ECS_INDEX));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "ECS_PREST_FOU_CODE", "ECS_FOU_CODE", "ECS_ART_VAR3", "ECS_ART_VAR2", "ECS_ART_VAR1", "ECS_ART_CODE"};

    private static final int[] pkColNum = new int[]{5, 4, 3, 2, 1, 0};

    private static final HashMap fkColName = new HashMap(1);

    static {
        fkColName.put("DT_CMDE_ACHAT_TETE", new String[]{
            "ECS_CAT_DEP_CODE", "ECS_CAT_DEP_SOC_CODE", "ECS_CAT_NO_CMDE", "ECS_CAT_NO_VERSION"
        });
    }


    private static final HashMap fkColNum = new HashMap(1);

    static {
        fkColNum.put("DT_CMDE_ACHAT_TETE", new int[]{
            19, 20, 21, 22
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

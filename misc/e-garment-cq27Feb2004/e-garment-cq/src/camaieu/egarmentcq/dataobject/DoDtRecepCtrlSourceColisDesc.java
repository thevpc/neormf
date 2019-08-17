package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtRecepCtrlSourceColis correspondant à la table DT_RECEP_CTRL_SOURCE_COLIS
 */
public class DoDtRecepCtrlSourceColisDesc implements IDoDescription {
    public static final int RSC_CAT_DEP_CODE = 0;
    public static final int RSC_DEP_SOC_CODE = 1;
    public static final int RSC_CAT_NO_CMDE = 2;
    public static final int RSC_CAT_NO_VERSION = 3;
    public static final int RSC_NO_CONTROLE = 4;
    public static final int RSC_NO_COLIS = 5;
    public static final int RSC_INDEX = 6;
    public static final int RSC_DT_CREAT = 7;
    public static final int RSC_DT_MAJ = 8;
    public static final int RSC_UTIL_MAJ = 9;
    public static final int XTX_ID = 10;
    public static final int Z_STATUS = 11;

    public static final String tableName = "DT_RECEP_CTRL_SOURCE_COLIS";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "RSC_CAT_DEP_CODE", "RSC_DEP_SOC_CODE", "RSC_CAT_NO_CMDE", "RSC_CAT_NO_VERSION", "RSC_NO_CONTROLE", "RSC_NO_COLIS", "RSC_INDEX", "RSC_DT_CREAT", "RSC_DT_MAJ", "RSC_UTIL_MAJ", "XTX_ID", "Z_STATUS"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(12);
        colBase.put("RSC_CAT_DEP_CODE", new Integer(RSC_CAT_DEP_CODE));
        colBase.put("RSC_DEP_SOC_CODE", new Integer(RSC_DEP_SOC_CODE));
        colBase.put("RSC_CAT_NO_CMDE", new Integer(RSC_CAT_NO_CMDE));
        colBase.put("RSC_CAT_NO_VERSION", new Integer(RSC_CAT_NO_VERSION));
        colBase.put("RSC_NO_CONTROLE", new Integer(RSC_NO_CONTROLE));
        colBase.put("RSC_NO_COLIS", new Integer(RSC_NO_COLIS));
        colBase.put("RSC_INDEX", new Integer(RSC_INDEX));
        colBase.put("RSC_DT_CREAT", new Integer(RSC_DT_CREAT));
        colBase.put("RSC_DT_MAJ", new Integer(RSC_DT_MAJ));
        colBase.put("RSC_UTIL_MAJ", new Integer(RSC_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "RSC_NO_CONTROLE", "RSC_NO_COLIS", "RSC_DEP_SOC_CODE", "RSC_CAT_NO_VERSION", "RSC_CAT_NO_CMDE", "RSC_CAT_DEP_CODE"};

    private static final int[] pkColNum = new int[]{4, 5, 1, 3, 2, 0};

    private static final HashMap fkColName = new HashMap(0);

    private static final HashMap fkColNum = new HashMap(0);

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

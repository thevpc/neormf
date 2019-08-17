package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtRecepCtrlSourceTete correspondant à la table DT_RECEP_CTRL_SOURCE_TETE
 */
public class DoDtRecepCtrlSourceTeteDesc implements IDoDescription {
    public static final int RST_CAM_CAT_DEP_CODE = 0;
    public static final int RST_CAM_CAT_DEP_SOC_CODE = 1;
    public static final int RST_CAM_NO_CMDE = 2;
    public static final int RST_CAM_CAT_NO_VERSION = 3;
    public static final int RST_CAM_NO_LIGNE = 4;
    public static final int RST_CAM_ART_CODE = 5;
    public static final int RST_CAM_ART_VAR1 = 6;
    public static final int RST_CAM_ART_VAR2 = 7;
    public static final int RST_CAM_ART_VAR3 = 8;
    public static final int RST_NO_CONTROLE = 9;
    public static final int RST_PRESTATAIRE = 10;
    public static final int RST_DT_CTRL_SOURCE_REEL = 11;
    public static final int RST_DT_CTRL_SOURCE_RAPPORT = 12;
    public static final int RST_DT_CTRL_SOURCE_LABO = 13;
    public static final int RST_QTE_PRES_CTRL_SOURCE = 14;
    public static final int RST_QTE_PREL_CTRL_SOURCE = 15;
    public static final int RST_DT_ENVOI_ECHANT = 16;
    public static final int RST_ECHANT_SCELLE01 = 17;
    public static final int RST_MOTIF_SCELLE = 18;
    public static final int RST_NO_COLIS_ECHANT = 19;
    public static final int RST_OK_CTRL_ENTREP = 20;
    public static final int RST_DT_CREAT = 21;
    public static final int RST_DT_MAJ = 22;
    public static final int RST_UTIL_MAJ = 23;
    public static final int XTX_ID = 24;
    public static final int Z_STATUS = 25;
    public static final int RST_DT_CTRL_SOURCE_SOUHAIT = 26;
    public static final int RST_DT_CTRL_SOURCE_PREV = 27;
    public static final int RST_NIVEAU_CTRLE = 28;
    public static final int RST_COMMENTAIRE = 29;
    public static final int RST_OK_CTRL_SOURCE = 30;
    public static final int RST_INDEX = 31;
    public static final int RST_DT_DECISION_PRESTATAIRE = 32;

    public static final String tableName = "DT_RECEP_CTRL_SOURCE_TETE";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "RST_CAM_CAT_DEP_CODE", "RST_CAM_CAT_DEP_SOC_CODE", "RST_CAM_NO_CMDE", "RST_CAM_CAT_NO_VERSION", "RST_CAM_NO_LIGNE", "RST_CAM_ART_CODE", "RST_CAM_ART_VAR1", "RST_CAM_ART_VAR2", "RST_CAM_ART_VAR3", "RST_NO_CONTROLE", "RST_PRESTATAIRE", "RST_DT_CTRL_SOURCE_REEL", "RST_DT_CTRL_SOURCE_RAPPORT", "RST_DT_CTRL_SOURCE_LABO", "RST_QTE_PRES_CTRL_SOURCE", "RST_QTE_PREL_CTRL_SOURCE", "RST_DT_ENVOI_ECHANT", "RST_ECHANT_SCELLE_01", "RST_MOTIF_SCELLE", "RST_NO_COLIS_ECHANT", "RST_OK_CTRL_ENTREP", "RST_DT_CREAT", "RST_DT_MAJ", "RST_UTIL_MAJ", "XTX_ID", "Z_STATUS", "RST_DT_CTRL_SOURCE_SOUHAIT", "RST_DT_CTRL_SOURCE_PREV", "RST_NIVEAU_CTRLE", "RST_COMMENTAIRE", "RST_OK_CTRL_SOURCE", "RST_INDEX", "RST_DT_DECISION_PRESTATAIRE"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(33);
        colBase.put("RST_CAM_CAT_DEP_CODE", new Integer(RST_CAM_CAT_DEP_CODE));
        colBase.put("RST_CAM_CAT_DEP_SOC_CODE", new Integer(RST_CAM_CAT_DEP_SOC_CODE));
        colBase.put("RST_CAM_NO_CMDE", new Integer(RST_CAM_NO_CMDE));
        colBase.put("RST_CAM_CAT_NO_VERSION", new Integer(RST_CAM_CAT_NO_VERSION));
        colBase.put("RST_CAM_NO_LIGNE", new Integer(RST_CAM_NO_LIGNE));
        colBase.put("RST_CAM_ART_CODE", new Integer(RST_CAM_ART_CODE));
        colBase.put("RST_CAM_ART_VAR1", new Integer(RST_CAM_ART_VAR1));
        colBase.put("RST_CAM_ART_VAR2", new Integer(RST_CAM_ART_VAR2));
        colBase.put("RST_CAM_ART_VAR3", new Integer(RST_CAM_ART_VAR3));
        colBase.put("RST_NO_CONTROLE", new Integer(RST_NO_CONTROLE));
        colBase.put("RST_PRESTATAIRE", new Integer(RST_PRESTATAIRE));
        colBase.put("RST_DT_CTRL_SOURCE_REEL", new Integer(RST_DT_CTRL_SOURCE_REEL));
        colBase.put("RST_DT_CTRL_SOURCE_RAPPORT", new Integer(RST_DT_CTRL_SOURCE_RAPPORT));
        colBase.put("RST_DT_CTRL_SOURCE_LABO", new Integer(RST_DT_CTRL_SOURCE_LABO));
        colBase.put("RST_QTE_PRES_CTRL_SOURCE", new Integer(RST_QTE_PRES_CTRL_SOURCE));
        colBase.put("RST_QTE_PREL_CTRL_SOURCE", new Integer(RST_QTE_PREL_CTRL_SOURCE));
        colBase.put("RST_DT_ENVOI_ECHANT", new Integer(RST_DT_ENVOI_ECHANT));
        colBase.put("RST_ECHANT_SCELLE_01", new Integer(RST_ECHANT_SCELLE01));
        colBase.put("RST_MOTIF_SCELLE", new Integer(RST_MOTIF_SCELLE));
        colBase.put("RST_NO_COLIS_ECHANT", new Integer(RST_NO_COLIS_ECHANT));
        colBase.put("RST_OK_CTRL_ENTREP", new Integer(RST_OK_CTRL_ENTREP));
        colBase.put("RST_DT_CREAT", new Integer(RST_DT_CREAT));
        colBase.put("RST_DT_MAJ", new Integer(RST_DT_MAJ));
        colBase.put("RST_UTIL_MAJ", new Integer(RST_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("RST_DT_CTRL_SOURCE_SOUHAIT", new Integer(RST_DT_CTRL_SOURCE_SOUHAIT));
        colBase.put("RST_DT_CTRL_SOURCE_PREV", new Integer(RST_DT_CTRL_SOURCE_PREV));
        colBase.put("RST_NIVEAU_CTRLE", new Integer(RST_NIVEAU_CTRLE));
        colBase.put("RST_COMMENTAIRE", new Integer(RST_COMMENTAIRE));
        colBase.put("RST_OK_CTRL_SOURCE", new Integer(RST_OK_CTRL_SOURCE));
        colBase.put("RST_INDEX", new Integer(RST_INDEX));
        colBase.put("RST_DT_DECISION_PRESTATAIRE", new Integer(RST_DT_DECISION_PRESTATAIRE));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "RST_NO_CONTROLE", "RST_CAM_NO_LIGNE", "RST_CAM_NO_CMDE", "RST_CAM_CAT_NO_VERSION", "RST_CAM_CAT_DEP_SOC_CODE", "RST_CAM_CAT_DEP_CODE", "RST_CAM_ART_VAR3", "RST_CAM_ART_VAR2", "RST_CAM_ART_VAR1", "RST_CAM_ART_CODE"};

    private static final int[] pkColNum = new int[]{9, 4, 2, 3, 1, 0, 8, 7, 6, 5};

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

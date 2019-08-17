package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoDtRecepCtrlSource correspondant à la table DT_RECEP_CTRL_SOURCE
 */
public class DoDtRecepCtrlSourceDesc implements IDoDescription {
    public static final int RCS_CAM_CAT_DEP_CODE = 0;
    public static final int RCS_CAM_CAT_DEP_SOC_CODE = 1;
    public static final int RCS_CAM_CAT_NO_CMDE = 2;
    public static final int RCS_CAM_CAT_NO_VERSION = 3;
    public static final int RCS_CAM_NO_LIGNE = 4;
    public static final int RCS_CAM_ART_CODE = 5;
    public static final int RCS_CAM_ART_VAR1 = 6;
    public static final int RCS_CAM_ART_VAR2 = 7;
    public static final int RCS_CAM_ART_VAR3 = 8;
    public static final int RCS_NO_CONTROLE = 9;
    public static final int RCS_CCC_CODIGO = 10;
    public static final int RCS_VALEUR_SAISIE = 11;
    public static final int RCS_CTRL_CRITERE = 12;
    public static final int RCS_TYPE_LABO = 13;
    public static final int RCS_PRESTATAIRE = 14;
    public static final int RCS_MCR_CODIGO = 15;
    public static final int RCS_NIVEL_INSPECCION = 16;
    public static final int RCS_CTRL01 = 17;
    public static final int RCS_FREC_CONTROL = 18;
    public static final int RCS_FREC_CONTROL_TYPE = 19;
    public static final int RCS_EDITION = 20;
    public static final int RCS_NSU_CODIGO = 21;
    public static final int RCS_DT_CREAT = 22;
    public static final int RCS_DT_MAJ = 23;
    public static final int RCS_UTIL_MAJ = 24;
    public static final int XTX_ID = 25;
    public static final int Z_STATUS = 26;
    public static final int RCS_INDEX = 27;

    public static final String tableName = "DT_RECEP_CTRL_SOURCE";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "RCS_CAM_CAT_DEP_CODE", "RCS_CAM_CAT_DEP_SOC_CODE", "RCS_CAM_CAT_NO_CMDE", "RCS_CAM_CAT_NO_VERSION", "RCS_CAM_NO_LIGNE", "RCS_CAM_ART_CODE", "RCS_CAM_ART_VAR1", "RCS_CAM_ART_VAR2", "RCS_CAM_ART_VAR3", "RCS_NO_CONTROLE", "RCS_CCC_CODIGO", "RCS_VALEUR_SAISIE", "RCS_CTRL_CRITERE", "RCS_TYPE_LABO", "RCS_PRESTATAIRE", "RCS_MCR_CODIGO", "RCS_NIVEL_INSPECCION", "RCS_CTRL_01", "RCS_FREC_CONTROL", "RCS_FREC_CONTROL_TYPE", "RCS_EDITION", "RCS_NSU_CODIGO", "RCS_DT_CREAT", "RCS_DT_MAJ", "RCS_UTIL_MAJ", "XTX_ID", "Z_STATUS", "RCS_INDEX"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(28);
        colBase.put("RCS_CAM_CAT_DEP_CODE", new Integer(RCS_CAM_CAT_DEP_CODE));
        colBase.put("RCS_CAM_CAT_DEP_SOC_CODE", new Integer(RCS_CAM_CAT_DEP_SOC_CODE));
        colBase.put("RCS_CAM_CAT_NO_CMDE", new Integer(RCS_CAM_CAT_NO_CMDE));
        colBase.put("RCS_CAM_CAT_NO_VERSION", new Integer(RCS_CAM_CAT_NO_VERSION));
        colBase.put("RCS_CAM_NO_LIGNE", new Integer(RCS_CAM_NO_LIGNE));
        colBase.put("RCS_CAM_ART_CODE", new Integer(RCS_CAM_ART_CODE));
        colBase.put("RCS_CAM_ART_VAR1", new Integer(RCS_CAM_ART_VAR1));
        colBase.put("RCS_CAM_ART_VAR2", new Integer(RCS_CAM_ART_VAR2));
        colBase.put("RCS_CAM_ART_VAR3", new Integer(RCS_CAM_ART_VAR3));
        colBase.put("RCS_NO_CONTROLE", new Integer(RCS_NO_CONTROLE));
        colBase.put("RCS_CCC_CODIGO", new Integer(RCS_CCC_CODIGO));
        colBase.put("RCS_VALEUR_SAISIE", new Integer(RCS_VALEUR_SAISIE));
        colBase.put("RCS_CTRL_CRITERE", new Integer(RCS_CTRL_CRITERE));
        colBase.put("RCS_TYPE_LABO", new Integer(RCS_TYPE_LABO));
        colBase.put("RCS_PRESTATAIRE", new Integer(RCS_PRESTATAIRE));
        colBase.put("RCS_MCR_CODIGO", new Integer(RCS_MCR_CODIGO));
        colBase.put("RCS_NIVEL_INSPECCION", new Integer(RCS_NIVEL_INSPECCION));
        colBase.put("RCS_CTRL_01", new Integer(RCS_CTRL01));
        colBase.put("RCS_FREC_CONTROL", new Integer(RCS_FREC_CONTROL));
        colBase.put("RCS_FREC_CONTROL_TYPE", new Integer(RCS_FREC_CONTROL_TYPE));
        colBase.put("RCS_EDITION", new Integer(RCS_EDITION));
        colBase.put("RCS_NSU_CODIGO", new Integer(RCS_NSU_CODIGO));
        colBase.put("RCS_DT_CREAT", new Integer(RCS_DT_CREAT));
        colBase.put("RCS_DT_MAJ", new Integer(RCS_DT_MAJ));
        colBase.put("RCS_UTIL_MAJ", new Integer(RCS_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("RCS_INDEX", new Integer(RCS_INDEX));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "RCS_CAM_CAT_DEP_CODE",
        "RCS_CAM_CAT_DEP_SOC_CODE",
        "RCS_CAM_CAT_NO_CMDE",
        "RCS_CAM_CAT_NO_VERSION",
        "RCS_CAM_NO_LIGNE",
        "RCS_CAM_ART_CODE",
        "RCS_CAM_ART_VAR1",
        "RCS_CAM_ART_VAR2",
        "RCS_CAM_ART_VAR3",
        "RCS_NO_CONTROLE",
//        "RCS_CCC_CODIGO",
//        "RCS_PRESTATAIRE"
    };

    private static final int[] pkColNum = new int[]{
        RCS_CAM_CAT_DEP_CODE,
        RCS_CAM_CAT_DEP_SOC_CODE,
        RCS_CAM_CAT_NO_CMDE,
        RCS_CAM_CAT_NO_VERSION,
        RCS_CAM_NO_LIGNE,
        RCS_CAM_ART_CODE,
        RCS_CAM_ART_VAR1,
        RCS_CAM_ART_VAR2,
        RCS_CAM_ART_VAR3,
        RCS_NO_CONTROLE,
        RCS_CCC_CODIGO//,
//        RCS_PRESTATAIRE
    };

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

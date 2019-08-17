package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoXnCouleur correspondant à la table XN_COULEUR
 */
public class DoXnCouleurDesc implements IDoDescription {
    public static final int COU_CODE = 0;
    public static final int COU_LIB = 1;
    public static final int COU_DT_CREAT = 2;
    public static final int COU_DT_MAJ = 3;
    public static final int COU_UTIL_MAJ = 4;
    public static final int XTX_ID = 5;
    public static final int Z_STATUS = 6;

    public static final String tableName = "XN_COULEUR";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "COU_CODE", "COU_LIB", "COU_DT_CREAT", "COU_DT_MAJ", "COU_UTIL_MAJ", "XTX_ID", "Z_STATUS"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(7);
        colBase.put("COU_CODE", new Integer(COU_CODE));
        colBase.put("COU_LIB", new Integer(COU_LIB));
        colBase.put("COU_DT_CREAT", new Integer(COU_DT_CREAT));
        colBase.put("COU_DT_MAJ", new Integer(COU_DT_MAJ));
        colBase.put("COU_UTIL_MAJ", new Integer(COU_UTIL_MAJ));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "COU_CODE"};

    private static final int[] pkColNum = new int[]{0};

    private static final HashMap fkColName = new HashMap(0);

    static {
        fkColName.put("CM_MAJ_PRMP_INTEGRATION", new String[]{
            "COU_CODE"
        });
        fkColName.put("CM_MII_DDE_COUL", new String[]{
            "COU_CODE"
        });
        fkColName.put("DT_W_CMDE_ACHAT", new String[]{
            "COU_CODE"
        });
        fkColName.put("XN_ART", new String[]{
            "COU_CODE"
        });
        fkColName.put("XN_COMPOSITION", new String[]{
            "COU_CODE"
        });
        fkColName.put("XN_MODELE_COULEUR", new String[]{
            "COU_CODE"
        });
    }


    private static final HashMap fkColNum = new HashMap(0);

    static {
        fkColNum.put("CM_MAJ_PRMP_INTEGRATION", new int[]{
            0
        });
        fkColNum.put("CM_MII_DDE_COUL", new int[]{
            0
        });
        fkColNum.put("DT_W_CMDE_ACHAT", new int[]{
            0
        });
        fkColNum.put("XN_ART", new int[]{
            0
        });
        fkColNum.put("XN_COMPOSITION", new int[]{
            0
        });
        fkColNum.put("XN_MODELE_COULEUR", new int[]{
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

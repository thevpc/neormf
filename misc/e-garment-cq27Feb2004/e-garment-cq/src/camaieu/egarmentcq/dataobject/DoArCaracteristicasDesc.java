package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.IDoDescription;

import java.util.HashMap;

/**
 /* Description de l'objetDoArCaracteristicas correspondant à la table AR_CARACTERISTICAS
 */
public class DoArCaracteristicasDesc implements IDoDescription {
    public static final int CCC_DT_CREAT = 0;
    public static final int CCC_UTIL_MAJ = 1;
    public static final int CCC_DT_MAJ = 2;
    public static final int Z_STATUS = 3;
    public static final int CCC_CODIGO = 4;
    public static final int CCC_DESCRIPCION1 = 5;
    public static final int CCC_DESCRIPCION2 = 6;
    public static final int XTX_ID = 7;
    public static final int CCC_MCR_CODIGO = 8;

    public static final String tableName = "AR_CARACTERISTICAS";

    /*
     * Liste des noms de colonnes
     */
    public static final String[] dbColName = new String[]{
        "CCC_DT_CREAT", "CCC_UTIL_MAJ", "CCC_DT_MAJ", "Z_STATUS", "CCC_CODIGO", "CCC_DESCRIPCION1", "CCC_DESCRIPCION2", "XTX_ID", "CCC_MCR_CODIGO"};
    private static final HashMap colBase;

    static {
        colBase = new HashMap(9);
        colBase.put("CCC_DT_CREAT", new Integer(CCC_DT_CREAT));
        colBase.put("CCC_UTIL_MAJ", new Integer(CCC_UTIL_MAJ));
        colBase.put("CCC_DT_MAJ", new Integer(CCC_DT_MAJ));
        colBase.put("Z_STATUS", new Integer(Z_STATUS));
        colBase.put("CCC_CODIGO", new Integer(CCC_CODIGO));
        colBase.put("CCC_DESCRIPCION1", new Integer(CCC_DESCRIPCION1));
        colBase.put("CCC_DESCRIPCION2", new Integer(CCC_DESCRIPCION2));
        colBase.put("XTX_ID", new Integer(XTX_ID));
        colBase.put("CCC_MCR_CODIGO", new Integer(CCC_MCR_CODIGO));
    }

    /*
     * Noms de colonnes de la clé primaire
     */
    private static final String[] pkColName = new String[]{
        "CCC_CODIGO"};

    private static final int[] pkColNum = new int[]{4};

    private static final HashMap fkColName = new HashMap(1);

    static {
        fkColName.put("AR_MEDIOS_CONTROL", new String[]{
            "CCC_MCR_CODIGO"
        });
    }

    static {
        fkColName.put("AR_VALEUR_CRITERE", new String[]{
            "CCC_CODIGO"
        });
    }


    private static final HashMap fkColNum = new HashMap(1);

    static {
        fkColNum.put("AR_MEDIOS_CONTROL", new int[]{
            8
        });
    }

    static {
        fkColNum.put("AR_VALEUR_CRITERE", new int[]{
            4
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

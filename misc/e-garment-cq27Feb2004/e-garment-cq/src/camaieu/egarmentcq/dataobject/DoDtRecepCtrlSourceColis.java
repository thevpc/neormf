package camaieu.egarmentcq.dataobject;

import wg4.fwk.dataobject.DataObject;
import wg4.fwk.dataobject.IDoDescription;
import wg4.fwk.dataobject.ParameterException;
import wg4.fwk.dataobject.SqlArg;
import wg4.fwk.mvc.tool.StrConvertor;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;

public class DoDtRecepCtrlSourceColis implements DataObject {

    private static final IDoDescription description = new DoDtRecepCtrlSourceColisDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[12];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_RECEP_CTRL_SOURCE_COLIS"};
    //variables correspondant à la table DT_RECEP_CTRL_SOURCE_COLIS
    private String rscCatDepCode = null;
    private String rscDepSocCode = null;
    private Integer rscCatNoCmde = null;
    private Integer rscCatNoVersion = null;
    private Integer rscNoControle = null;
    private String rscNoColis = null;
    private String rscIndex = null;
    private Timestamp rscDtCreat = null;
    private Timestamp rscDtMaj = null;
    private String rscUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtRecepCtrlSourceColis() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtRecepCtrlSourceColis(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtRecepCtrlSourceColis(DoDtRecepCtrlSourceColis arg) {
        setRscCatDepCode(arg.rscCatDepCode);
        setRscDepSocCode(arg.rscDepSocCode);
        setRscCatNoCmde(arg.rscCatNoCmde);
        setRscCatNoVersion(arg.rscCatNoVersion);
        setRscNoControle(arg.rscNoControle);
        setRscNoColis(arg.rscNoColis);
        setRscIndex(arg.rscIndex);
        setRscDtCreat(arg.rscDtCreat);
        setRscDtMaj(arg.rscDtMaj);
        setRscUtilMaj(arg.rscUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtRecepCtrlSourceColis(String newSql, Object[] newParam) {
        sql = newSql;
        param = newParam;
    }

    public int getPersist() {
        return persist;
    }

    public void setPersist(int newPersist) {
        persist = newPersist;
    }

    public void resetUpdate() {
        Arrays.fill(updCol, -1);
    }

    public Object[] getParam() {
        return param;
    }

    public String getSQL() {
        return sql;
    }

    public HashSet getColNotInsertable() {
        return null;
    }

    public String getRscCatDepCode() {
        return rscCatDepCode;
    }

    public String getRscDepSocCode() {
        return rscDepSocCode;
    }

    public Integer getRscCatNoCmde() {
        return rscCatNoCmde;
    }

    public Integer getRscCatNoVersion() {
        return rscCatNoVersion;
    }

    public Integer getRscNoControle() {
        return rscNoControle;
    }

    public String getRscNoColis() {
        return rscNoColis;
    }

    public String getRscIndex() {
        return rscIndex;
    }

    public Timestamp getRscDtCreat() {
        return rscDtCreat;
    }

    public Timestamp getRscDtMaj() {
        return rscDtMaj;
    }

    public String getRscUtilMaj() {
        return rscUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public void setRscCatDepCode(String newRscCatDepCode) {
        rscCatDepCode = newRscCatDepCode;
    }

    public void setRscDepSocCode(String newRscDepSocCode) {
        rscDepSocCode = newRscDepSocCode;
    }

    public void setRscCatNoCmde(Integer newRscCatNoCmde) {
        rscCatNoCmde = newRscCatNoCmde;
    }

    public void setRscCatNoVersion(Integer newRscCatNoVersion) {
        rscCatNoVersion = newRscCatNoVersion;
    }

    public void setRscNoControle(Integer newRscNoControle) {
        rscNoControle = newRscNoControle;
    }

    public void setRscNoColis(String newRscNoColis) {
        rscNoColis = newRscNoColis;
    }

    public void setRscIndex(String newRscIndex) {
        rscIndex = newRscIndex;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceColisDesc.RSC_INDEX] = 1;
    }

    public void setRscDtCreat(Timestamp newRscDtCreat) {
        rscDtCreat = newRscDtCreat;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT] = 1;
    }

    public void setRscDtMaj(Timestamp newRscDtMaj) {
        rscDtMaj = newRscDtMaj;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ] = 1;
    }

    public void setRscUtilMaj(String newRscUtilMaj) {
        rscUtilMaj = newRscUtilMaj;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceColisDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceColisDesc.Z_STATUS] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_CAT_DEP_CODE)
            return rscCatDepCode;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_DEP_SOC_CODE)
            return rscDepSocCode;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE)
            return rscCatNoCmde;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION)
            return rscCatNoVersion;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE)
            return rscNoControle;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_NO_COLIS)
            return rscNoColis;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_INDEX)
            return rscIndex;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT)
            return rscDtCreat;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ)
            return rscDtMaj;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ)
            return rscUtilMaj;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtRecepCtrlSourceColisDesc.Z_STATUS)
            return zStatus;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_CAT_DEP_CODE) {
            rscCatDepCode = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_DEP_SOC_CODE) {
            rscDepSocCode = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE) {
            rscCatNoCmde = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION) {
            rscCatNoVersion = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE) {
            rscNoControle = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_NO_COLIS) {
            rscNoColis = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_INDEX) {
            rscIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT) {
            rscDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ) {
            rscDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ) {
            rscUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceColisDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtRecepCtrlSourceColis());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtRecepCtrlSourceColis djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_DEP_CODE] != -1) {
            djo.rscCatDepCode = rs.getString(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_DEP_CODE]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_DEP_SOC_CODE] != -1) {
            djo.rscDepSocCode = rs.getString(val[DoDtRecepCtrlSourceColisDesc.RSC_DEP_SOC_CODE]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE]);
            if (!rs.wasNull())
                djo.rscCatNoCmde = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.rscCatNoVersion = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE]);
            if (!rs.wasNull())
                djo.rscNoControle = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_NO_COLIS] != -1) {
            djo.rscNoColis = rs.getString(val[DoDtRecepCtrlSourceColisDesc.RSC_NO_COLIS]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_INDEX] != -1) {
            djo.rscIndex = rs.getString(val[DoDtRecepCtrlSourceColisDesc.RSC_INDEX]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT] != -1) {
            djo.rscDtCreat = rs.getTimestamp(val[DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ] != -1) {
            djo.rscDtMaj = rs.getTimestamp(val[DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ] != -1) {
            djo.rscUtilMaj = rs.getString(val[DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ]);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceColisDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtRecepCtrlSourceColisDesc.Z_STATUS]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_DEP_CODE], rscCatDepCode);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceColisDesc.RSC_DEP_SOC_CODE], rscDepSocCode);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE] > 0) {
            if (rscCatNoCmde == null)
                stmt.setNull(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_CMDE], rscCatNoCmde.intValue());
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION] > 0) {
            if (rscCatNoVersion == null)
                stmt.setNull(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceColisDesc.RSC_CAT_NO_VERSION], rscCatNoVersion.intValue());
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE] > 0) {
            if (rscNoControle == null)
                stmt.setNull(val[DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceColisDesc.RSC_NO_CONTROLE], rscNoControle.intValue());
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_NO_COLIS] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceColisDesc.RSC_NO_COLIS], rscNoColis);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_INDEX] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceColisDesc.RSC_INDEX], rscIndex);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceColisDesc.RSC_DT_CREAT], rscDtCreat);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceColisDesc.RSC_DT_MAJ], rscDtMaj);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceColisDesc.RSC_UTIL_MAJ], rscUtilMaj);
        }
        if (val[DoDtRecepCtrlSourceColisDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtRecepCtrlSourceColisDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceColisDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtRecepCtrlSourceColisDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceColisDesc.Z_STATUS], zStatus);
        }
    }

    /**
     * Méthode générée automatiquement permettant de remplir un dataobject à partir des valeurs
     * d'une requête http.
     * Les noms des paramètres de la requête doivent être strictement identique aux noms des attributs du DataObject.
     * Cette méthode peut être complétée afin de prendre en compte d'éventuels paramètres se trouvant en session.
     * Pour une regénération éventuelle, faites attention à ne coder qu'entre les tags de début et de fin.
     *  Date de création : (25/05/01 08:03:14)
     * @param request javax.servlet.http.HttpServletRequest
     */
    public DataObject[] setParameters(HttpServletRequest request)
            throws ParameterException, java.text.ParseException {
        String[] params = null;
        String localVal = null;
        int size = 0;
        DoDtRecepCtrlSourceColis[] result = null;
        params = request.getParameterValues("rscCatDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("rscDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("rscCatNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscCatNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rscCatNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rscNoControle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscNoControle((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rscNoColis");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscNoColis(localVal);
            }
        }
        params = request.getParameterValues("rscIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscIndex(localVal);
            }
        }
        params = request.getParameterValues("rscDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rscDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rscUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRscUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setXtxId((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("zStatus");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceColis[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceColis();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setZStatus(localVal);
            }
        }
        /************************ INSTRUCTIONS DEVELOPPEMENT SPECIFIQUE *******************/
        /********************** FIN INSTRUCTIONS DEVELOPPEMENT SPECIFIQUE *****************/
        return result;
    }

    /*
     * @see DataObject#addChild(DataObject)
     */
    public void addChild(DataObject doChild) {
    }

    /*
     * @see DataObject#getDescription()
     */
    public IDoDescription getDescription() {
        return description;
    }

    /*
     * @see DataObject#getUpdateCol()
     */
    public int[] getUpdateCol() {
        return updCol;
    }

}

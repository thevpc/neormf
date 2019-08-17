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

public class DoArCaracteristicas implements DataObject {

    private static final IDoDescription description = new DoArCaracteristicasDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[9];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"AR_CARACTERISTICAS"};
    //variables correspondant à la table AR_CARACTERISTICAS
    private Timestamp cccDtCreat = null;
    private String cccUtilMaj = null;
    private String cccDtMaj = null;
    private String zStatus = null;
    private String cccCodigo = null;
    private String cccDescripcion1 = null;
    private String cccDescripcion2 = null;
    private Integer xtxId = null;
    private String cccMcrCodigo = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoArCaracteristicas() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoArCaracteristicas(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoArCaracteristicas(DoArCaracteristicas arg) {
        setCccDtCreat(arg.cccDtCreat);
        setCccUtilMaj(arg.cccUtilMaj);
        setCccDtMaj(arg.cccDtMaj);
        setZStatus(arg.zStatus);
        setCccCodigo(arg.cccCodigo);
        setCccDescripcion1(arg.cccDescripcion1);
        setCccDescripcion2(arg.cccDescripcion2);
        setXtxId(arg.xtxId);
        setCccMcrCodigo(arg.cccMcrCodigo);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoArCaracteristicas(String newSql, Object[] newParam) {
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

    public Timestamp getCccDtCreat() {
        return cccDtCreat;
    }

    public String getCccUtilMaj() {
        return cccUtilMaj;
    }

    public String getCccDtMaj() {
        return cccDtMaj;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getCccCodigo() {
        return cccCodigo;
    }

    public String getCccDescripcion1() {
        return cccDescripcion1;
    }

    public String getCccDescripcion2() {
        return cccDescripcion2;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getCccMcrCodigo() {
        return cccMcrCodigo;
    }

    public void setCccDtCreat(Timestamp newCccDtCreat) {
        cccDtCreat = newCccDtCreat;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.CCC_DT_CREAT] = 1;
    }

    public void setCccUtilMaj(String newCccUtilMaj) {
        cccUtilMaj = newCccUtilMaj;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.CCC_UTIL_MAJ] = 1;
    }

    public void setCccDtMaj(String newCccDtMaj) {
        cccDtMaj = newCccDtMaj;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.CCC_DT_MAJ] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.Z_STATUS] = 1;
    }

    public void setCccCodigo(String newCccCodigo) {
        cccCodigo = newCccCodigo;
    }

    public void setCccDescripcion1(String newCccDescripcion1) {
        cccDescripcion1 = newCccDescripcion1;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.CCC_DESCRIPCION1] = 1;
    }

    public void setCccDescripcion2(String newCccDescripcion2) {
        cccDescripcion2 = newCccDescripcion2;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.CCC_DESCRIPCION2] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.XTX_ID] = 1;
    }

    public void setCccMcrCodigo(String newCccMcrCodigo) {
        cccMcrCodigo = newCccMcrCodigo;
        if (persist > 0)
            updCol[DoArCaracteristicasDesc.CCC_MCR_CODIGO] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoArCaracteristicasDesc.CCC_DT_CREAT)
            return cccDtCreat;
        else if (numCol == DoArCaracteristicasDesc.CCC_UTIL_MAJ)
            return cccUtilMaj;
        else if (numCol == DoArCaracteristicasDesc.CCC_DT_MAJ)
            return cccDtMaj;
        else if (numCol == DoArCaracteristicasDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoArCaracteristicasDesc.CCC_CODIGO)
            return cccCodigo;
        else if (numCol == DoArCaracteristicasDesc.CCC_DESCRIPCION1)
            return cccDescripcion1;
        else if (numCol == DoArCaracteristicasDesc.CCC_DESCRIPCION2)
            return cccDescripcion2;
        else if (numCol == DoArCaracteristicasDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoArCaracteristicasDesc.CCC_MCR_CODIGO)
            return cccMcrCodigo;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoArCaracteristicasDesc.CCC_DT_CREAT) {
            cccDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.CCC_UTIL_MAJ) {
            cccUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.CCC_DT_MAJ) {
            cccDtMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.CCC_CODIGO) {
            cccCodigo = (String) value;
        }
        if (numCol == DoArCaracteristicasDesc.CCC_DESCRIPCION1) {
            cccDescripcion1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.CCC_DESCRIPCION2) {
            cccDescripcion2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoArCaracteristicasDesc.CCC_MCR_CODIGO) {
            cccMcrCodigo = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoArCaracteristicas());
    }

    private DataObject setProperty(SqlArg sqlArg, DoArCaracteristicas djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoArCaracteristicasDesc.CCC_DT_CREAT] != -1) {
            djo.cccDtCreat = rs.getTimestamp(val[DoArCaracteristicasDesc.CCC_DT_CREAT]);
        }
        if (val[DoArCaracteristicasDesc.CCC_UTIL_MAJ] != -1) {
            djo.cccUtilMaj = rs.getString(val[DoArCaracteristicasDesc.CCC_UTIL_MAJ]);
        }
        if (val[DoArCaracteristicasDesc.CCC_DT_MAJ] != -1) {
            djo.cccDtMaj = rs.getString(val[DoArCaracteristicasDesc.CCC_DT_MAJ]);
        }
        if (val[DoArCaracteristicasDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoArCaracteristicasDesc.Z_STATUS]);
        }
        if (val[DoArCaracteristicasDesc.CCC_CODIGO] != -1) {
            djo.cccCodigo = rs.getString(val[DoArCaracteristicasDesc.CCC_CODIGO]);
        }
        if (val[DoArCaracteristicasDesc.CCC_DESCRIPCION1] != -1) {
            djo.cccDescripcion1 = rs.getString(val[DoArCaracteristicasDesc.CCC_DESCRIPCION1]);
        }
        if (val[DoArCaracteristicasDesc.CCC_DESCRIPCION2] != -1) {
            djo.cccDescripcion2 = rs.getString(val[DoArCaracteristicasDesc.CCC_DESCRIPCION2]);
        }
        if (val[DoArCaracteristicasDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoArCaracteristicasDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoArCaracteristicasDesc.CCC_MCR_CODIGO] != -1) {
            djo.cccMcrCodigo = rs.getString(val[DoArCaracteristicasDesc.CCC_MCR_CODIGO]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoArCaracteristicasDesc.CCC_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoArCaracteristicasDesc.CCC_DT_CREAT], cccDtCreat);
        }
        if (val[DoArCaracteristicasDesc.CCC_UTIL_MAJ] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.CCC_UTIL_MAJ], cccUtilMaj);
        }
        if (val[DoArCaracteristicasDesc.CCC_DT_MAJ] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.CCC_DT_MAJ], cccDtMaj);
        }
        if (val[DoArCaracteristicasDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.Z_STATUS], zStatus);
        }
        if (val[DoArCaracteristicasDesc.CCC_CODIGO] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.CCC_CODIGO], cccCodigo);
        }
        if (val[DoArCaracteristicasDesc.CCC_DESCRIPCION1] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.CCC_DESCRIPCION1], cccDescripcion1);
        }
        if (val[DoArCaracteristicasDesc.CCC_DESCRIPCION2] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.CCC_DESCRIPCION2], cccDescripcion2);
        }
        if (val[DoArCaracteristicasDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoArCaracteristicasDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoArCaracteristicasDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoArCaracteristicasDesc.CCC_MCR_CODIGO] > 0) {
            stmt.setString(val[DoArCaracteristicasDesc.CCC_MCR_CODIGO], cccMcrCodigo);
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
        DoArCaracteristicas[] result = null;
        params = request.getParameterValues("cccDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("cccUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("cccDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccDtMaj(localVal);
            }
        }
        params = request.getParameterValues("zStatus");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
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
        params = request.getParameterValues("cccCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccCodigo(localVal);
            }
        }
        params = request.getParameterValues("cccDescripcion1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccDescripcion1(localVal);
            }
        }
        params = request.getParameterValues("cccDescripcion2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccDescripcion2(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
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
        params = request.getParameterValues("cccMcrCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoArCaracteristicas[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoArCaracteristicas();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCccMcrCodigo(localVal);
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

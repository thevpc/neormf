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

public class DoXnCouleur implements DataObject {

    private static final IDoDescription description = new DoXnCouleurDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[7];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"XN_COULEUR"};
    //variables correspondant à la table XN_COULEUR
    private String couCode = null;
    private String couLib = null;
    private Timestamp couDtCreat = null;
    private Timestamp couDtMaj = null;
    private String couUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoXnCouleur() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnCouleur(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnCouleur(DoXnCouleur arg) {
        setCouCode(arg.couCode);
        setCouLib(arg.couLib);
        setCouDtCreat(arg.couDtCreat);
        setCouDtMaj(arg.couDtMaj);
        setCouUtilMaj(arg.couUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoXnCouleur(String newSql, Object[] newParam) {
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

    public String getCouCode() {
        return couCode;
    }

    public String getCouLib() {
        return couLib;
    }

    public Timestamp getCouDtCreat() {
        return couDtCreat;
    }

    public Timestamp getCouDtMaj() {
        return couDtMaj;
    }

    public String getCouUtilMaj() {
        return couUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public void setCouCode(String newCouCode) {
        couCode = newCouCode;
    }

    public void setCouLib(String newCouLib) {
        couLib = newCouLib;
        if (persist > 0)
            updCol[DoXnCouleurDesc.COU_LIB] = 1;
    }

    public void setCouDtCreat(Timestamp newCouDtCreat) {
        couDtCreat = newCouDtCreat;
        if (persist > 0)
            updCol[DoXnCouleurDesc.COU_DT_CREAT] = 1;
    }

    public void setCouDtMaj(Timestamp newCouDtMaj) {
        couDtMaj = newCouDtMaj;
        if (persist > 0)
            updCol[DoXnCouleurDesc.COU_DT_MAJ] = 1;
    }

    public void setCouUtilMaj(String newCouUtilMaj) {
        couUtilMaj = newCouUtilMaj;
        if (persist > 0)
            updCol[DoXnCouleurDesc.COU_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoXnCouleurDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoXnCouleurDesc.Z_STATUS] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoXnCouleurDesc.COU_CODE)
            return couCode;
        else if (numCol == DoXnCouleurDesc.COU_LIB)
            return couLib;
        else if (numCol == DoXnCouleurDesc.COU_DT_CREAT)
            return couDtCreat;
        else if (numCol == DoXnCouleurDesc.COU_DT_MAJ)
            return couDtMaj;
        else if (numCol == DoXnCouleurDesc.COU_UTIL_MAJ)
            return couUtilMaj;
        else if (numCol == DoXnCouleurDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoXnCouleurDesc.Z_STATUS)
            return zStatus;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoXnCouleurDesc.COU_CODE) {
            couCode = (String) value;
        }
        if (numCol == DoXnCouleurDesc.COU_LIB) {
            couLib = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnCouleurDesc.COU_DT_CREAT) {
            couDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnCouleurDesc.COU_DT_MAJ) {
            couDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnCouleurDesc.COU_UTIL_MAJ) {
            couUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnCouleurDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnCouleurDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoXnCouleur());
    }

    private DataObject setProperty(SqlArg sqlArg, DoXnCouleur djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoXnCouleurDesc.COU_CODE] != -1) {
            djo.couCode = rs.getString(val[DoXnCouleurDesc.COU_CODE]);
        }
        if (val[DoXnCouleurDesc.COU_LIB] != -1) {
            djo.couLib = rs.getString(val[DoXnCouleurDesc.COU_LIB]);
        }
        if (val[DoXnCouleurDesc.COU_DT_CREAT] != -1) {
            djo.couDtCreat = rs.getTimestamp(val[DoXnCouleurDesc.COU_DT_CREAT]);
        }
        if (val[DoXnCouleurDesc.COU_DT_MAJ] != -1) {
            djo.couDtMaj = rs.getTimestamp(val[DoXnCouleurDesc.COU_DT_MAJ]);
        }
        if (val[DoXnCouleurDesc.COU_UTIL_MAJ] != -1) {
            djo.couUtilMaj = rs.getString(val[DoXnCouleurDesc.COU_UTIL_MAJ]);
        }
        if (val[DoXnCouleurDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoXnCouleurDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoXnCouleurDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoXnCouleurDesc.Z_STATUS]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoXnCouleurDesc.COU_CODE] > 0) {
            stmt.setString(val[DoXnCouleurDesc.COU_CODE], couCode);
        }
        if (val[DoXnCouleurDesc.COU_LIB] > 0) {
            stmt.setString(val[DoXnCouleurDesc.COU_LIB], couLib);
        }
        if (val[DoXnCouleurDesc.COU_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoXnCouleurDesc.COU_DT_CREAT], couDtCreat);
        }
        if (val[DoXnCouleurDesc.COU_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoXnCouleurDesc.COU_DT_MAJ], couDtMaj);
        }
        if (val[DoXnCouleurDesc.COU_UTIL_MAJ] > 0) {
            stmt.setString(val[DoXnCouleurDesc.COU_UTIL_MAJ], couUtilMaj);
        }
        if (val[DoXnCouleurDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoXnCouleurDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoXnCouleurDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoXnCouleurDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoXnCouleurDesc.Z_STATUS], zStatus);
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
        DoXnCouleur[] result = null;
        params = request.getParameterValues("couCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCouCode(localVal);
            }
        }
        params = request.getParameterValues("couLib");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCouLib(localVal);
            }
        }
        params = request.getParameterValues("couDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCouDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("couDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCouDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("couUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCouUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
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
                result = new DoXnCouleur[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnCouleur();
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

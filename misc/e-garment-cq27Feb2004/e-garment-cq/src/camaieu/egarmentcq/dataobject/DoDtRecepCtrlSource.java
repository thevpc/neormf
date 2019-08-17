package camaieu.egarmentcq.dataobject;

import camaieu.webform.RecordEditorHelper;
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

public class DoDtRecepCtrlSource implements DataObject {

    private static final IDoDescription description = new DoDtRecepCtrlSourceDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[28];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_RECEP_CTRL_SOURCE"};
    //variables correspondant à la table DT_RECEP_CTRL_SOURCE
    private String rcsCamCatDepCode = null;
    private String rcsCamCatDepSocCode = null;
    private Integer rcsCamCatNoCmde = null;
    private Integer rcsCamCatNoVersion = null;
    private Integer rcsCamNoLigne = null;
    private String rcsCamArtCode = null;
    private String rcsCamArtVar1 = null;
    private String rcsCamArtVar2 = null;
    private String rcsCamArtVar3 = null;
    private Integer rcsNoControle = null;
    private String rcsCccCodigo = null;
    private String rcsValeurSaisie = null;
    private String rcsCtrlCritere = null;
    private String rcsTypeLabo = null;
    private String rcsPrestataire = null;
    private String rcsMcrCodigo = null;
    private String rcsNivelInspeccion = null;
    private String rcsCtrl01 = null;
    private Integer rcsFrecControl = null;
    private String rcsFrecControlType = null;
    private Integer rcsEdition = null;
    private Integer rcsNsuCodigo = null;
    private Timestamp rcsDtCreat = null;
    private Timestamp rcsDtMaj = null;
    private String rcsUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private String rcsIndex = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtRecepCtrlSource() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtRecepCtrlSource(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtRecepCtrlSource(DoDtRecepCtrlSource arg) {
        setRcsCamCatDepCode(arg.rcsCamCatDepCode);
        setRcsCamCatDepSocCode(arg.rcsCamCatDepSocCode);
        setRcsCamCatNoCmde(arg.rcsCamCatNoCmde);
        setRcsCamCatNoVersion(arg.rcsCamCatNoVersion);
        setRcsCamNoLigne(arg.rcsCamNoLigne);
        setRcsCamArtCode(arg.rcsCamArtCode);
        setRcsCamArtVar1(arg.rcsCamArtVar1);
        setRcsCamArtVar2(arg.rcsCamArtVar2);
        setRcsCamArtVar3(arg.rcsCamArtVar3);
        setRcsNoControle(arg.rcsNoControle);
        setRcsCccCodigo(arg.rcsCccCodigo);
        setRcsValeurSaisie(arg.rcsValeurSaisie);
        setRcsCtrlCritere(arg.rcsCtrlCritere);
        setRcsTypeLabo(arg.rcsTypeLabo);
        setRcsPrestataire(arg.rcsPrestataire);
        setRcsMcrCodigo(arg.rcsMcrCodigo);
        setRcsNivelInspeccion(arg.rcsNivelInspeccion);
        setRcsCtrl01(arg.rcsCtrl01);
        setRcsFrecControl(arg.rcsFrecControl);
        setRcsFrecControlType(arg.rcsFrecControlType);
        setRcsEdition(arg.rcsEdition);
        setRcsNsuCodigo(arg.rcsNsuCodigo);
        setRcsDtCreat(arg.rcsDtCreat);
        setRcsDtMaj(arg.rcsDtMaj);
        setRcsUtilMaj(arg.rcsUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setRcsIndex(arg.rcsIndex);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtRecepCtrlSource(String newSql, Object[] newParam) {
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

    public String getRcsCamCatDepCode() {
        return rcsCamCatDepCode;
    }

    public String getRcsCamCatDepSocCode() {
        return rcsCamCatDepSocCode;
    }

    public Integer getRcsCamCatNoCmde() {
        return rcsCamCatNoCmde;
    }

    public Integer getRcsCamCatNoVersion() {
        return rcsCamCatNoVersion;
    }

    public Integer getRcsCamNoLigne() {
        return rcsCamNoLigne;
    }

    public String getRcsCamArtCode() {
        return rcsCamArtCode;
    }

    public String getRcsCamArtVar1() {
        return rcsCamArtVar1;
    }

    public String getRcsCamArtVar2() {
        return rcsCamArtVar2;
    }

    public String getRcsCamArtVar3() {
        return rcsCamArtVar3;
    }

    public Integer getRcsNoControle() {
        return rcsNoControle;
    }

    public String getRcsCccCodigo() {
        return rcsCccCodigo;
    }

    public String getRcsValeurSaisie() {
        return rcsValeurSaisie;
    }

    public String getRcsCtrlCritere() {
        return rcsCtrlCritere;
    }

    public String getRcsTypeLabo() {
        return rcsTypeLabo;
    }

    public String getRcsPrestataire() {
        return rcsPrestataire;
    }

    public String getRcsMcrCodigo() {
        return rcsMcrCodigo;
    }

    public String getRcsNivelInspeccion() {
        return rcsNivelInspeccion;
    }

    public String getRcsCtrl01() {
        return rcsCtrl01;
    }

    public Integer getRcsFrecControl() {
        return rcsFrecControl;
    }

    public String getRcsFrecControlType() {
        return rcsFrecControlType;
    }

    public Integer getRcsEdition() {
        return rcsEdition;
    }

    public Integer getRcsNsuCodigo() {
        return rcsNsuCodigo;
    }

    public Timestamp getRcsDtCreat() {
        return rcsDtCreat;
    }

    public Timestamp getRcsDtMaj() {
        return rcsDtMaj;
    }

    public String getRcsUtilMaj() {
        return rcsUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getRcsIndex() {
        return rcsIndex;
    }

    public void setRcsCamCatDepCode(String newRcsCamCatDepCode) {
        rcsCamCatDepCode = newRcsCamCatDepCode;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE] = 1;
    }

    public void setRcsCamCatDepSocCode(String newRcsCamCatDepSocCode) {
        rcsCamCatDepSocCode = newRcsCamCatDepSocCode;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE] = 1;
    }

    public void setRcsCamCatNoCmde(Integer newRcsCamCatNoCmde) {
        rcsCamCatNoCmde = newRcsCamCatNoCmde;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE] = 1;
    }

    public void setRcsCamCatNoVersion(Integer newRcsCamCatNoVersion) {
        rcsCamCatNoVersion = newRcsCamCatNoVersion;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION] = 1;
    }

    public void setRcsCamNoLigne(Integer newRcsCamNoLigne) {
        rcsCamNoLigne = newRcsCamNoLigne;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE] = 1;
    }

    public void setRcsCamArtCode(String newRcsCamArtCode) {
        rcsCamArtCode = newRcsCamArtCode;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE] = 1;
    }

    public void setRcsCamArtVar1(String newRcsCamArtVar1) {
        rcsCamArtVar1 = newRcsCamArtVar1;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1] = 1;
    }

    public void setRcsCamArtVar2(String newRcsCamArtVar2) {
        rcsCamArtVar2 = newRcsCamArtVar2;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2] = 1;
    }

    public void setRcsCamArtVar3(String newRcsCamArtVar3) {
        rcsCamArtVar3 = newRcsCamArtVar3;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3] = 1;
    }

    public void setRcsNoControle(Integer newRcsNoControle) {
        rcsNoControle = newRcsNoControle;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE] = 1;
    }

    public void setRcsCccCodigo(String newRcsCccCodigo) {
        rcsCccCodigo = newRcsCccCodigo;
//        if (persist > 0)
//            updCol[DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO] = 1;
    }

    public void setRcsValeurSaisie(String newRcsValeurSaisie) {
        rcsValeurSaisie = newRcsValeurSaisie;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE] = 1;
    }

    public void setRcsCtrlCritere(String newRcsCtrlCritere) {
        rcsCtrlCritere = newRcsCtrlCritere;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE] = 1;
    }

    public void setRcsTypeLabo(String newRcsTypeLabo) {
        rcsTypeLabo = newRcsTypeLabo;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO] = 1;
    }

    public void setRcsPrestataire(String newRcsPrestataire) {
        rcsPrestataire = newRcsPrestataire;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE] = 1;
    }

    public void setRcsMcrCodigo(String newRcsMcrCodigo) {
        rcsMcrCodigo = newRcsMcrCodigo;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO] = 1;
    }

    public void setRcsNivelInspeccion(String newRcsNivelInspeccion) {
        rcsNivelInspeccion = newRcsNivelInspeccion;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION] = 1;
    }

    public void setRcsCtrl01(String newRcsCtrl01) {
        rcsCtrl01 = newRcsCtrl01;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_CTRL01] = 1;
    }

    public void setRcsFrecControl(Integer newRcsFrecControl) {
        rcsFrecControl = newRcsFrecControl;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL] = 1;
    }

    public void setRcsFrecControlType(String newRcsFrecControlType) {
        rcsFrecControlType = newRcsFrecControlType;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE] = 1;
    }

    public void setRcsEdition(Integer newRcsEdition) {
        rcsEdition = newRcsEdition;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_EDITION] = 1;
    }

    public void setRcsNsuCodigo(Integer newRcsNsuCodigo) {
        rcsNsuCodigo = newRcsNsuCodigo;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO] = 1;
    }

    public void setRcsDtCreat(Timestamp newRcsDtCreat) {
        rcsDtCreat = newRcsDtCreat;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_DT_CREAT] = 1;
    }

    public void setRcsDtMaj(Timestamp newRcsDtMaj) {
        rcsDtMaj = newRcsDtMaj;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_DT_MAJ] = 1;
    }

    public void setRcsUtilMaj(String newRcsUtilMaj) {
        rcsUtilMaj = newRcsUtilMaj;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.Z_STATUS] = 1;
    }

    public void setRcsIndex(String newRcsIndex) {
        rcsIndex = newRcsIndex;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceDesc.RCS_INDEX] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE)
            return rcsCamCatDepCode;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE)
            return rcsCamCatDepSocCode;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE)
            return rcsCamCatNoCmde;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION)
            return rcsCamCatNoVersion;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE)
            return rcsCamNoLigne;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE)
            return rcsCamArtCode;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1)
            return rcsCamArtVar1;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2)
            return rcsCamArtVar2;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3)
            return rcsCamArtVar3;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE)
            return rcsNoControle;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO)
            return rcsCccCodigo;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE)
            return rcsValeurSaisie;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE)
            return rcsCtrlCritere;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO)
            return rcsTypeLabo;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE)
            return rcsPrestataire;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO)
            return rcsMcrCodigo;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION)
            return rcsNivelInspeccion;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_CTRL01)
            return rcsCtrl01;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL)
            return rcsFrecControl;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE)
            return rcsFrecControlType;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_EDITION)
            return rcsEdition;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO)
            return rcsNsuCodigo;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_DT_CREAT)
            return rcsDtCreat;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_DT_MAJ)
            return rcsDtMaj;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ)
            return rcsUtilMaj;
        else if (numCol == DoDtRecepCtrlSourceDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtRecepCtrlSourceDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoDtRecepCtrlSourceDesc.RCS_INDEX)
            return rcsIndex;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE) {
            rcsCamCatDepCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE) {
            rcsCamCatDepSocCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE) {
            rcsCamCatNoCmde = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION) {
            rcsCamCatNoVersion = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE) {
            rcsCamNoLigne = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE) {
            rcsCamArtCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1) {
            rcsCamArtVar1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2) {
            rcsCamArtVar2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3) {
            rcsCamArtVar3 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE) {
            rcsNoControle = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO) {
            rcsCccCodigo = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE) {
            rcsValeurSaisie = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE) {
            rcsCtrlCritere = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO) {
            rcsTypeLabo = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE) {
            rcsPrestataire = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO) {
            rcsMcrCodigo = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION) {
            rcsNivelInspeccion = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_CTRL01) {
            rcsCtrl01 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL) {
            rcsFrecControl = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE) {
            rcsFrecControlType = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_EDITION) {
            rcsEdition = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO) {
            rcsNsuCodigo = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_DT_CREAT) {
            rcsDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_DT_MAJ) {
            rcsDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ) {
            rcsUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceDesc.RCS_INDEX) {
            rcsIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtRecepCtrlSource());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtRecepCtrlSource djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE] != -1) {
            djo.rcsCamCatDepCode = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE] != -1) {
            djo.rcsCamCatDepSocCode = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE]);
            if (!rs.wasNull())
                djo.rcsCamCatNoCmde = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.rcsCamCatNoVersion = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE]);
            if (!rs.wasNull())
                djo.rcsCamNoLigne = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE] != -1) {
            djo.rcsCamArtCode = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1] != -1) {
            djo.rcsCamArtVar1 = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2] != -1) {
            djo.rcsCamArtVar2 = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3] != -1) {
            djo.rcsCamArtVar3 = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE]);
            if (!rs.wasNull())
                djo.rcsNoControle = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO] != -1) {
            djo.rcsCccCodigo = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE] != -1) {
            djo.rcsValeurSaisie = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE] != -1) {
            djo.rcsCtrlCritere = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO] != -1) {
            djo.rcsTypeLabo = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE] != -1) {
            djo.rcsPrestataire = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO] != -1) {
            djo.rcsMcrCodigo = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION] != -1) {
            djo.rcsNivelInspeccion = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CTRL01] != -1) {
            djo.rcsCtrl01 = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_CTRL01]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL]);
            if (!rs.wasNull())
                djo.rcsFrecControl = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE] != -1) {
            djo.rcsFrecControlType = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_EDITION] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_EDITION]);
            if (!rs.wasNull())
                djo.rcsEdition = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO]);
            if (!rs.wasNull())
                djo.rcsNsuCodigo = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_DT_CREAT] != -1) {
            djo.rcsDtCreat = rs.getTimestamp(val[DoDtRecepCtrlSourceDesc.RCS_DT_CREAT]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_DT_MAJ] != -1) {
            djo.rcsDtMaj = rs.getTimestamp(val[DoDtRecepCtrlSourceDesc.RCS_DT_MAJ]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ] != -1) {
            djo.rcsUtilMaj = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ]);
        }
        if (val[DoDtRecepCtrlSourceDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtRecepCtrlSourceDesc.Z_STATUS]);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_INDEX] != -1) {
            djo.rcsIndex = rs.getString(val[DoDtRecepCtrlSourceDesc.RCS_INDEX]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_CODE], rcsCamCatDepCode);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_DEP_SOC_CODE], rcsCamCatDepSocCode);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE] > 0) {
            if (rcsCamCatNoCmde == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_CMDE], rcsCamCatNoCmde.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION] > 0) {
            if (rcsCamCatNoVersion == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_CAM_CAT_NO_VERSION], rcsCamCatNoVersion.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE] > 0) {
            if (rcsCamNoLigne == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_CAM_NO_LIGNE], rcsCamNoLigne.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_CODE], rcsCamArtCode);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR1], rcsCamArtVar1);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR2], rcsCamArtVar2);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CAM_ART_VAR3], rcsCamArtVar3);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE] > 0) {
            if (rcsNoControle == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_NO_CONTROLE], rcsNoControle.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CCC_CODIGO], rcsCccCodigo);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_VALEUR_SAISIE], rcsValeurSaisie);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CTRL_CRITERE], rcsCtrlCritere);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_TYPE_LABO], rcsTypeLabo);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_PRESTATAIRE], rcsPrestataire);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_MCR_CODIGO], rcsMcrCodigo);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_NIVEL_INSPECCION], rcsNivelInspeccion);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_CTRL01] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_CTRL01], rcsCtrl01);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL] > 0) {
            if (rcsFrecControl == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL], rcsFrecControl.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_FREC_CONTROL_TYPE], rcsFrecControlType);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_EDITION] > 0) {
            if (rcsEdition == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_EDITION], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_EDITION], rcsEdition.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO] > 0) {
            if (rcsNsuCodigo == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.RCS_NSU_CODIGO], rcsNsuCodigo.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceDesc.RCS_DT_CREAT], rcsDtCreat);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceDesc.RCS_DT_MAJ], rcsDtMaj);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_UTIL_MAJ], rcsUtilMaj);
        }
        if (val[DoDtRecepCtrlSourceDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtRecepCtrlSourceDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtRecepCtrlSourceDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.Z_STATUS], zStatus);
        }
        if (val[DoDtRecepCtrlSourceDesc.RCS_INDEX] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceDesc.RCS_INDEX], rcsIndex);
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
        DoDtRecepCtrlSource[] result = null;
        params = request.getParameterValues("rcsCamCatDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("rcsCamCatDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamCatDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("rcsCamCatNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamCatNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsCamCatNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsCamNoLigne");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamNoLigne((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsCamArtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamArtCode(localVal);
            }
        }
        params = request.getParameterValues("rcsCamArtVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamArtVar1(localVal);
            }
        }
        params = request.getParameterValues("rcsCamArtVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamArtVar2(localVal);
            }
        }
        params = request.getParameterValues("rcsCamArtVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCamArtVar3(localVal);
            }
        }
        params = request.getParameterValues("rcsNoControle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsNoControle((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsCccCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCccCodigo(localVal);
            }
        }
        params = request.getParameterValues("rcsValeurSaisie");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsValeurSaisie(localVal);
            }
        }
        params = request.getParameterValues("rcsCtrlCritere");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCtrlCritere(localVal);
            }
        }
        params = request.getParameterValues("rcsTypeLabo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsTypeLabo(localVal);
            }
        }
        params = request.getParameterValues("rcsPrestataire");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsPrestataire(localVal);
            }
        }
        params = request.getParameterValues("rcsMcrCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsMcrCodigo(localVal);
            }
        }
        params = request.getParameterValues("rcsNivelInspeccion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsNivelInspeccion(localVal);
            }
        }
        params = request.getParameterValues("rcsCtrl01");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsCtrl01(localVal);
            }
        }
        params = request.getParameterValues("rcsFrecControl");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsFrecControl((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsFrecControlType");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsFrecControlType(localVal);
            }
        }
        params = request.getParameterValues("rcsEdition");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsEdition((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsNsuCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsNsuCodigo((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rcsDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rcsDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rcsUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
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
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
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
        params = request.getParameterValues("rcsIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRcsIndex(localVal);
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

//--------------------------------------------------

    private String extraCccDescripcion1;

    public String getExtraCccDescripcion1() {
        return extraCccDescripcion1;
    }

    public void setExtraCccDescripcion1(String newCccDescripcion1) {
        this.extraCccDescripcion1 = newCccDescripcion1;
    }

    private String extraRcsPrestataireNom;

    public String getExtraRcsPrestataireNom() {
        return extraRcsPrestataireNom;
    }

    public void setExtraRcsPrestataireNom(String newExtraRcsPrestataireNom) {
        this.extraRcsPrestataireNom = newExtraRcsPrestataireNom;
    }

    private RecordEditorHelper helper;

    public RecordEditorHelper getHelper() {
        if (helper == null) {
            helper = new RecordEditorHelper();
        }
        return helper;
    }
}

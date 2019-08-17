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

public class DoDtEnvoiCqSource implements DataObject {

    private static final IDoDescription description = new DoDtEnvoiCqSourceDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[29];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_ENVOI_CQ_SOURCE"};
    //variables correspondant à la table DT_ENVOI_CQ_SOURCE
    private String ecsArtCode = null;
    private String ecsArtVar1 = null;
    private String ecsArtVar2 = null;
    private String ecsArtVar3 = null;
    private String ecsFouCode = null;
    private String ecsPrestFouCode = null;
    private String ecsFaaCode = null;
    private Timestamp ecsDtEnvoiDtech = null;
    private String ecsNoColisDtech = null;
    private Timestamp ecsDtRecepDtech = null;
    private String ecsRmqDtech = null;
    private Timestamp ecsDtEnvoiEchan = null;
    private String ecsNoColisEchan = null;
    private Timestamp ecsDtRecepEchan = null;
    private String ecsRmqEchan = null;
    private Timestamp ecsDtEnvoiBib = null;
    private String ecsNoColisBib = null;
    private Timestamp ecsDtRecepBib = null;
    private String ecsRmqBib = null;
    private String ecsCatDepCode = null;
    private String ecsCatDepSocCode = null;
    private Integer ecsCatNoCmde = null;
    private Integer ecsCatNoVersion = null;
    private Timestamp ecsDtCreat = null;
    private Timestamp ecsDtMaj = null;
    private String ecsUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private String ecsIndex = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtEnvoiCqSource() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtEnvoiCqSource(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtEnvoiCqSource(DoDtEnvoiCqSource arg) {
        setEcsArtCode(arg.ecsArtCode);
        setEcsArtVar1(arg.ecsArtVar1);
        setEcsArtVar2(arg.ecsArtVar2);
        setEcsArtVar3(arg.ecsArtVar3);
        setEcsFouCode(arg.ecsFouCode);
        setEcsPrestFouCode(arg.ecsPrestFouCode);
        setEcsFaaCode(arg.ecsFaaCode);
        setEcsDtEnvoiDtech(arg.ecsDtEnvoiDtech);
        setEcsNoColisDtech(arg.ecsNoColisDtech);
        setEcsDtRecepDtech(arg.ecsDtRecepDtech);
        setEcsRmqDtech(arg.ecsRmqDtech);
        setEcsDtEnvoiEchan(arg.ecsDtEnvoiEchan);
        setEcsNoColisEchan(arg.ecsNoColisEchan);
        setEcsDtRecepEchan(arg.ecsDtRecepEchan);
        setEcsRmqEchan(arg.ecsRmqEchan);
        setEcsDtEnvoiBib(arg.ecsDtEnvoiBib);
        setEcsNoColisBib(arg.ecsNoColisBib);
        setEcsDtRecepBib(arg.ecsDtRecepBib);
        setEcsRmqBib(arg.ecsRmqBib);
        setEcsCatDepCode(arg.ecsCatDepCode);
        setEcsCatDepSocCode(arg.ecsCatDepSocCode);
        setEcsCatNoCmde(arg.ecsCatNoCmde);
        setEcsCatNoVersion(arg.ecsCatNoVersion);
        setEcsDtCreat(arg.ecsDtCreat);
        setEcsDtMaj(arg.ecsDtMaj);
        setEcsUtilMaj(arg.ecsUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setEcsIndex(arg.ecsIndex);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtEnvoiCqSource(String newSql, Object[] newParam) {
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

    public String getEcsArtCode() {
        return ecsArtCode;
    }

    public String getEcsArtVar1() {
        return ecsArtVar1;
    }

    public String getEcsArtVar2() {
        return ecsArtVar2;
    }

    public String getEcsArtVar3() {
        return ecsArtVar3;
    }

    public String getEcsFouCode() {
        return ecsFouCode;
    }

    public String getEcsPrestFouCode() {
        return ecsPrestFouCode;
    }

    public String getEcsFaaCode() {
        return ecsFaaCode;
    }

    public Timestamp getEcsDtEnvoiDtech() {
        return ecsDtEnvoiDtech;
    }

    public String getEcsNoColisDtech() {
        return ecsNoColisDtech;
    }

    public Timestamp getEcsDtRecepDtech() {
        return ecsDtRecepDtech;
    }

    public String getEcsRmqDtech() {
        return ecsRmqDtech;
    }

    public Timestamp getEcsDtEnvoiEchan() {
        return ecsDtEnvoiEchan;
    }

    public String getEcsNoColisEchan() {
        return ecsNoColisEchan;
    }

    public Timestamp getEcsDtRecepEchan() {
        return ecsDtRecepEchan;
    }

    public String getEcsRmqEchan() {
        return ecsRmqEchan;
    }

    public Timestamp getEcsDtEnvoiBib() {
        return ecsDtEnvoiBib;
    }

    public String getEcsNoColisBib() {
        return ecsNoColisBib;
    }

    public Timestamp getEcsDtRecepBib() {
        return ecsDtRecepBib;
    }

    public String getEcsRmqBib() {
        return ecsRmqBib;
    }

    public String getEcsCatDepCode() {
        return ecsCatDepCode;
    }

    public String getEcsCatDepSocCode() {
        return ecsCatDepSocCode;
    }

    public Integer getEcsCatNoCmde() {
        return ecsCatNoCmde;
    }

    public Integer getEcsCatNoVersion() {
        return ecsCatNoVersion;
    }

    public Timestamp getEcsDtCreat() {
        return ecsDtCreat;
    }

    public Timestamp getEcsDtMaj() {
        return ecsDtMaj;
    }

    public String getEcsUtilMaj() {
        return ecsUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getEcsIndex() {
        return ecsIndex;
    }

    public void setEcsArtCode(String newEcsArtCode) {
        ecsArtCode = newEcsArtCode;
    }

    public void setEcsArtVar1(String newEcsArtVar1) {
        ecsArtVar1 = newEcsArtVar1;
    }

    public void setEcsArtVar2(String newEcsArtVar2) {
        ecsArtVar2 = newEcsArtVar2;
    }

    public void setEcsArtVar3(String newEcsArtVar3) {
        ecsArtVar3 = newEcsArtVar3;
    }

    public void setEcsFouCode(String newEcsFouCode) {
        ecsFouCode = newEcsFouCode;
    }

    public void setEcsPrestFouCode(String newEcsPrestFouCode) {
        ecsPrestFouCode = newEcsPrestFouCode;
    }

    public void setEcsFaaCode(String newEcsFaaCode) {
        ecsFaaCode = newEcsFaaCode;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_FAA_CODE] = 1;
    }

    public void setEcsDtEnvoiDtech(Timestamp newEcsDtEnvoiDtech) {
        ecsDtEnvoiDtech = newEcsDtEnvoiDtech;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH] = 1;
    }

    public void setEcsNoColisDtech(String newEcsNoColisDtech) {
        ecsNoColisDtech = newEcsNoColisDtech;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH] = 1;
    }

    public void setEcsDtRecepDtech(Timestamp newEcsDtRecepDtech) {
        ecsDtRecepDtech = newEcsDtRecepDtech;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH] = 1;
    }

    public void setEcsRmqDtech(String newEcsRmqDtech) {
        ecsRmqDtech = newEcsRmqDtech;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH] = 1;
    }

    public void setEcsDtEnvoiEchan(Timestamp newEcsDtEnvoiEchan) {
        ecsDtEnvoiEchan = newEcsDtEnvoiEchan;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN] = 1;
    }

    public void setEcsNoColisEchan(String newEcsNoColisEchan) {
        ecsNoColisEchan = newEcsNoColisEchan;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN] = 1;
    }

    public void setEcsDtRecepEchan(Timestamp newEcsDtRecepEchan) {
        ecsDtRecepEchan = newEcsDtRecepEchan;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN] = 1;
    }

    public void setEcsRmqEchan(String newEcsRmqEchan) {
        ecsRmqEchan = newEcsRmqEchan;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN] = 1;
    }

    public void setEcsDtEnvoiBib(Timestamp newEcsDtEnvoiBib) {
        ecsDtEnvoiBib = newEcsDtEnvoiBib;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB] = 1;
    }

    public void setEcsNoColisBib(String newEcsNoColisBib) {
        ecsNoColisBib = newEcsNoColisBib;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB] = 1;
    }

    public void setEcsDtRecepBib(Timestamp newEcsDtRecepBib) {
        ecsDtRecepBib = newEcsDtRecepBib;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB] = 1;
    }

    public void setEcsRmqBib(String newEcsRmqBib) {
        ecsRmqBib = newEcsRmqBib;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB] = 1;
    }

    public void setEcsCatDepCode(String newEcsCatDepCode) {
        ecsCatDepCode = newEcsCatDepCode;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE] = 1;
    }

    public void setEcsCatDepSocCode(String newEcsCatDepSocCode) {
        ecsCatDepSocCode = newEcsCatDepSocCode;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE] = 1;
    }

    public void setEcsCatNoCmde(Integer newEcsCatNoCmde) {
        ecsCatNoCmde = newEcsCatNoCmde;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE] = 1;
    }

    public void setEcsCatNoVersion(Integer newEcsCatNoVersion) {
        ecsCatNoVersion = newEcsCatNoVersion;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION] = 1;
    }

    public void setEcsDtCreat(Timestamp newEcsDtCreat) {
        ecsDtCreat = newEcsDtCreat;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_CREAT] = 1;
    }

    public void setEcsDtMaj(Timestamp newEcsDtMaj) {
        ecsDtMaj = newEcsDtMaj;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_DT_MAJ] = 1;
    }

    public void setEcsUtilMaj(String newEcsUtilMaj) {
        ecsUtilMaj = newEcsUtilMaj;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.Z_STATUS] = 1;
    }

    public void setEcsIndex(String newEcsIndex) {
        ecsIndex = newEcsIndex;
        if (persist > 0)
            updCol[DoDtEnvoiCqSourceDesc.ECS_INDEX] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_CODE)
            return ecsArtCode;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_VAR1)
            return ecsArtVar1;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_VAR2)
            return ecsArtVar2;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_VAR3)
            return ecsArtVar3;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_FOU_CODE)
            return ecsFouCode;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_PREST_FOU_CODE)
            return ecsPrestFouCode;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_FAA_CODE)
            return ecsFaaCode;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH)
            return ecsDtEnvoiDtech;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH)
            return ecsNoColisDtech;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH)
            return ecsDtRecepDtech;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH)
            return ecsRmqDtech;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN)
            return ecsDtEnvoiEchan;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN)
            return ecsNoColisEchan;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN)
            return ecsDtRecepEchan;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN)
            return ecsRmqEchan;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB)
            return ecsDtEnvoiBib;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB)
            return ecsNoColisBib;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB)
            return ecsDtRecepBib;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB)
            return ecsRmqBib;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE)
            return ecsCatDepCode;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE)
            return ecsCatDepSocCode;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE)
            return ecsCatNoCmde;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION)
            return ecsCatNoVersion;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_CREAT)
            return ecsDtCreat;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_MAJ)
            return ecsDtMaj;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ)
            return ecsUtilMaj;
        else if (numCol == DoDtEnvoiCqSourceDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtEnvoiCqSourceDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoDtEnvoiCqSourceDesc.ECS_INDEX)
            return ecsIndex;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_CODE) {
            ecsArtCode = (String) value;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_VAR1) {
            ecsArtVar1 = (String) value;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_VAR2) {
            ecsArtVar2 = (String) value;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_ART_VAR3) {
            ecsArtVar3 = (String) value;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_FOU_CODE) {
            ecsFouCode = (String) value;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_PREST_FOU_CODE) {
            ecsPrestFouCode = (String) value;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_FAA_CODE) {
            ecsFaaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH) {
            ecsDtEnvoiDtech = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH) {
            ecsNoColisDtech = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH) {
            ecsDtRecepDtech = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH) {
            ecsRmqDtech = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN) {
            ecsDtEnvoiEchan = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN) {
            ecsNoColisEchan = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN) {
            ecsDtRecepEchan = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN) {
            ecsRmqEchan = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB) {
            ecsDtEnvoiBib = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB) {
            ecsNoColisBib = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB) {
            ecsDtRecepBib = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB) {
            ecsRmqBib = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE) {
            ecsCatDepCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE) {
            ecsCatDepSocCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE) {
            ecsCatNoCmde = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION) {
            ecsCatNoVersion = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_CREAT) {
            ecsDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_DT_MAJ) {
            ecsDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ) {
            ecsUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtEnvoiCqSourceDesc.ECS_INDEX) {
            ecsIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtEnvoiCqSource());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtEnvoiCqSource djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_CODE] != -1) {
            djo.ecsArtCode = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_ART_CODE]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR1] != -1) {
            djo.ecsArtVar1 = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR1]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR2] != -1) {
            djo.ecsArtVar2 = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR2]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR3] != -1) {
            djo.ecsArtVar3 = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR3]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_FOU_CODE] != -1) {
            djo.ecsFouCode = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_FOU_CODE]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_PREST_FOU_CODE] != -1) {
            djo.ecsPrestFouCode = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_PREST_FOU_CODE]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_FAA_CODE] != -1) {
            djo.ecsFaaCode = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_FAA_CODE]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH] != -1) {
            djo.ecsDtEnvoiDtech = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH] != -1) {
            djo.ecsNoColisDtech = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH] != -1) {
            djo.ecsDtRecepDtech = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH] != -1) {
            djo.ecsRmqDtech = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN] != -1) {
            djo.ecsDtEnvoiEchan = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN] != -1) {
            djo.ecsNoColisEchan = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN] != -1) {
            djo.ecsDtRecepEchan = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN] != -1) {
            djo.ecsRmqEchan = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB] != -1) {
            djo.ecsDtEnvoiBib = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB] != -1) {
            djo.ecsNoColisBib = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB] != -1) {
            djo.ecsDtRecepBib = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB] != -1) {
            djo.ecsRmqBib = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE] != -1) {
            djo.ecsCatDepCode = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE] != -1) {
            djo.ecsCatDepSocCode = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE]);
            if (!rs.wasNull())
                djo.ecsCatNoCmde = new Integer(temp);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.ecsCatNoVersion = new Integer(temp);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_CREAT] != -1) {
            djo.ecsDtCreat = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_CREAT]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_MAJ] != -1) {
            djo.ecsDtMaj = rs.getTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_MAJ]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ] != -1) {
            djo.ecsUtilMaj = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ]);
        }
        if (val[DoDtEnvoiCqSourceDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtEnvoiCqSourceDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtEnvoiCqSourceDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtEnvoiCqSourceDesc.Z_STATUS]);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_INDEX] != -1) {
            djo.ecsIndex = rs.getString(val[DoDtEnvoiCqSourceDesc.ECS_INDEX]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_CODE] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_ART_CODE], ecsArtCode);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR1] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR1], ecsArtVar1);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR2] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR2], ecsArtVar2);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR3] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_ART_VAR3], ecsArtVar3);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_FOU_CODE] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_FOU_CODE], ecsFouCode);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_PREST_FOU_CODE] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_PREST_FOU_CODE], ecsPrestFouCode);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_FAA_CODE] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_FAA_CODE], ecsFaaCode);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_DTECH], ecsDtEnvoiDtech);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_DTECH], ecsNoColisDtech);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_DTECH], ecsDtRecepDtech);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_RMQ_DTECH], ecsRmqDtech);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_ECHAN], ecsDtEnvoiEchan);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_ECHAN], ecsNoColisEchan);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_ECHAN], ecsDtRecepEchan);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_RMQ_ECHAN], ecsRmqEchan);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_ENVOI_BIB], ecsDtEnvoiBib);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_NO_COLIS_BIB], ecsNoColisBib);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_RECEP_BIB], ecsDtRecepBib);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_RMQ_BIB], ecsRmqBib);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_CODE], ecsCatDepCode);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_CAT_DEP_SOC_CODE], ecsCatDepSocCode);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE] > 0) {
            if (ecsCatNoCmde == null)
                stmt.setNull(val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_CMDE], ecsCatNoCmde.intValue());
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION] > 0) {
            if (ecsCatNoVersion == null)
                stmt.setNull(val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtEnvoiCqSourceDesc.ECS_CAT_NO_VERSION], ecsCatNoVersion.intValue());
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_CREAT], ecsDtCreat);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtEnvoiCqSourceDesc.ECS_DT_MAJ], ecsDtMaj);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_UTIL_MAJ], ecsUtilMaj);
        }
        if (val[DoDtEnvoiCqSourceDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtEnvoiCqSourceDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtEnvoiCqSourceDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtEnvoiCqSourceDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.Z_STATUS], zStatus);
        }
        if (val[DoDtEnvoiCqSourceDesc.ECS_INDEX] > 0) {
            stmt.setString(val[DoDtEnvoiCqSourceDesc.ECS_INDEX], ecsIndex);
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
        DoDtEnvoiCqSource[] result = null;
        params = request.getParameterValues("ecsArtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsArtCode(localVal);
            }
        }
        params = request.getParameterValues("ecsArtVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsArtVar1(localVal);
            }
        }
        params = request.getParameterValues("ecsArtVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsArtVar2(localVal);
            }
        }
        params = request.getParameterValues("ecsArtVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsArtVar3(localVal);
            }
        }
        params = request.getParameterValues("ecsFouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsFouCode(localVal);
            }
        }
        params = request.getParameterValues("ecsPrestFouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsPrestFouCode(localVal);
            }
        }
        params = request.getParameterValues("ecsFaaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsFaaCode(localVal);
            }
        }
        params = request.getParameterValues("ecsDtEnvoiDtech");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtEnvoiDtech((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsNoColisDtech");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsNoColisDtech(localVal);
            }
        }
        params = request.getParameterValues("ecsDtRecepDtech");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtRecepDtech((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsRmqDtech");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsRmqDtech(localVal);
            }
        }
        params = request.getParameterValues("ecsDtEnvoiEchan");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtEnvoiEchan((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsNoColisEchan");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsNoColisEchan(localVal);
            }
        }
        params = request.getParameterValues("ecsDtRecepEchan");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtRecepEchan((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsRmqEchan");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsRmqEchan(localVal);
            }
        }
        params = request.getParameterValues("ecsDtEnvoiBib");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtEnvoiBib((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsNoColisBib");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsNoColisBib(localVal);
            }
        }
        params = request.getParameterValues("ecsDtRecepBib");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtRecepBib((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsRmqBib");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsRmqBib(localVal);
            }
        }
        params = request.getParameterValues("ecsCatDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("ecsCatDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsCatDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("ecsCatNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsCatNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("ecsCatNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("ecsDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("ecsUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
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
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
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
        params = request.getParameterValues("ecsIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtEnvoiCqSource[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtEnvoiCqSource();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setEcsIndex(localVal);
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

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

public class DoDtCmdeAchatLigne implements DataObject {

    private static final IDoDescription description = new DoDtCmdeAchatLigneDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[40];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_CMDE_ACHAT_LIGNE"};
    //variables correspondant à la table DT_CMDE_ACHAT_LIGNE
    private String calCatDepCode = null;
    private String calCatDepSocCode = null;
    private Integer calCatNoCmde = null;
    private Integer calCatNoVersion = null;
    private Integer calNoLigne = null;
    private String calArtCode = null;
    private String calArtVar1 = null;
    private String calArtVar2 = null;
    private String calArtVar3 = null;
    private Integer calNoLigneMc = null;
    private Double calPxAchBrut = null;
    private Double calPxAchNeg = null;
    private Double calPxAchNet = null;
    private String calPxNetOn = null;
    private Double calQteCmde = null;
    private Double calQteRecue = null;
    private Double calQteRetournee = null;
    private Double calQteSolde = null;
    private Double calQteValidee = null;
    private Integer calSemainePrevLivr = null;
    private Timestamp calDtPrevLivr1 = null;
    private String calTvaCode = null;
    private String calTxpCode = null;
    private Double calTxRemLig1 = null;
    private Double calTxRemLig2 = null;
    private Double calTxRemLig3 = null;
    private Double calTxRemLig4 = null;
    private Integer calUnitePx = null;
    private Double calPds = null;
    private Double calVol = null;
    private Double calMtEhf = null;
    private Double calMtEhfComp = null;
    private String calSoldeOn = null;
    private String calIndex = null;
    private Timestamp calDtCreat = null;
    private Timestamp calDtMaj = null;
    private String calUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private String calArgTratada01 = null;


    //!   fou_nom
    //   dep_nom
    //   dep_postal
    //   dep_ville
    //   cat_no_cmde
    //   cat_no_version
    //   cat_mod_code
    //   mod_des
    //   cam_art_var1
    //   cou_lib
    //   cam_qte_cmde
    //   cat_dt_cmde
    //   mod_des
    //   cam_art_var1
    //   cou_lib
    //   cam_qte_cmde
    //   cat_dt_cmde
    //   cat_dt_depart2
    //   rst_dt_ctrl_source_prev
    //   rst_dt_ctrl_source_souhait
    //   dt_recep_ctrl_source_tete.z_status


    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtCmdeAchatLigne() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtCmdeAchatLigne(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtCmdeAchatLigne(DoDtCmdeAchatLigne arg) {
        setCalCatDepCode(arg.calCatDepCode);
        setCalCatDepSocCode(arg.calCatDepSocCode);
        setCalCatNoCmde(arg.calCatNoCmde);
        setCalCatNoVersion(arg.calCatNoVersion);
        setCalNoLigne(arg.calNoLigne);
        setCalArtCode(arg.calArtCode);
        setCalArtVar1(arg.calArtVar1);
        setCalArtVar2(arg.calArtVar2);
        setCalArtVar3(arg.calArtVar3);
        setCalNoLigneMc(arg.calNoLigneMc);
        setCalPxAchBrut(arg.calPxAchBrut);
        setCalPxAchNeg(arg.calPxAchNeg);
        setCalPxAchNet(arg.calPxAchNet);
        setCalPxNetOn(arg.calPxNetOn);
        setCalQteCmde(arg.calQteCmde);
        setCalQteRecue(arg.calQteRecue);
        setCalQteRetournee(arg.calQteRetournee);
        setCalQteSolde(arg.calQteSolde);
        setCalQteValidee(arg.calQteValidee);
        setCalSemainePrevLivr(arg.calSemainePrevLivr);
        setCalDtPrevLivr1(arg.calDtPrevLivr1);
        setCalTvaCode(arg.calTvaCode);
        setCalTxpCode(arg.calTxpCode);
        setCalTxRemLig1(arg.calTxRemLig1);
        setCalTxRemLig2(arg.calTxRemLig2);
        setCalTxRemLig3(arg.calTxRemLig3);
        setCalTxRemLig4(arg.calTxRemLig4);
        setCalUnitePx(arg.calUnitePx);
        setCalPds(arg.calPds);
        setCalVol(arg.calVol);
        setCalMtEhf(arg.calMtEhf);
        setCalMtEhfComp(arg.calMtEhfComp);
        setCalSoldeOn(arg.calSoldeOn);
        setCalIndex(arg.calIndex);
        setCalDtCreat(arg.calDtCreat);
        setCalDtMaj(arg.calDtMaj);
        setCalUtilMaj(arg.calUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setCalArgTratada01(arg.calArgTratada01);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtCmdeAchatLigne(String newSql, Object[] newParam) {
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

    public String getCalCatDepCode() {
        return calCatDepCode;
    }

    public String getCalCatDepSocCode() {
        return calCatDepSocCode;
    }

    public Integer getCalCatNoCmde() {
        return calCatNoCmde;
    }

    public Integer getCalCatNoVersion() {
        return calCatNoVersion;
    }

    public Integer getCalNoLigne() {
        return calNoLigne;
    }

    public String getCalArtCode() {
        return calArtCode;
    }

    public String getCalArtVar1() {
        return calArtVar1;
    }

    public String getCalArtVar2() {
        return calArtVar2;
    }

    public String getCalArtVar3() {
        return calArtVar3;
    }

    public Integer getCalNoLigneMc() {
        return calNoLigneMc;
    }

    public Double getCalPxAchBrut() {
        return calPxAchBrut;
    }

    public Double getCalPxAchNeg() {
        return calPxAchNeg;
    }

    public Double getCalPxAchNet() {
        return calPxAchNet;
    }

    public String getCalPxNetOn() {
        return calPxNetOn;
    }

    public Double getCalQteCmde() {
        return calQteCmde;
    }

    public Double getCalQteRecue() {
        return calQteRecue;
    }

    public Double getCalQteRetournee() {
        return calQteRetournee;
    }

    public Double getCalQteSolde() {
        return calQteSolde;
    }

    public Double getCalQteValidee() {
        return calQteValidee;
    }

    public Integer getCalSemainePrevLivr() {
        return calSemainePrevLivr;
    }

    public Timestamp getCalDtPrevLivr1() {
        return calDtPrevLivr1;
    }

    public String getCalTvaCode() {
        return calTvaCode;
    }

    public String getCalTxpCode() {
        return calTxpCode;
    }

    public Double getCalTxRemLig1() {
        return calTxRemLig1;
    }

    public Double getCalTxRemLig2() {
        return calTxRemLig2;
    }

    public Double getCalTxRemLig3() {
        return calTxRemLig3;
    }

    public Double getCalTxRemLig4() {
        return calTxRemLig4;
    }

    public Integer getCalUnitePx() {
        return calUnitePx;
    }

    public Double getCalPds() {
        return calPds;
    }

    public Double getCalVol() {
        return calVol;
    }

    public Double getCalMtEhf() {
        return calMtEhf;
    }

    public Double getCalMtEhfComp() {
        return calMtEhfComp;
    }

    public String getCalSoldeOn() {
        return calSoldeOn;
    }

    public String getCalIndex() {
        return calIndex;
    }

    public Timestamp getCalDtCreat() {
        return calDtCreat;
    }

    public Timestamp getCalDtMaj() {
        return calDtMaj;
    }

    public String getCalUtilMaj() {
        return calUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getCalArgTratada01() {
        return calArgTratada01;
    }

    public void setCalCatDepCode(String newCalCatDepCode) {
        calCatDepCode = newCalCatDepCode;
    }

    public void setCalCatDepSocCode(String newCalCatDepSocCode) {
        calCatDepSocCode = newCalCatDepSocCode;
    }

    public void setCalCatNoCmde(Integer newCalCatNoCmde) {
        calCatNoCmde = newCalCatNoCmde;
    }

    public void setCalCatNoVersion(Integer newCalCatNoVersion) {
        calCatNoVersion = newCalCatNoVersion;
    }

    public void setCalNoLigne(Integer newCalNoLigne) {
        calNoLigne = newCalNoLigne;
    }

    public void setCalArtCode(String newCalArtCode) {
        calArtCode = newCalArtCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_ART_CODE] = 1;
    }

    public void setCalArtVar1(String newCalArtVar1) {
        calArtVar1 = newCalArtVar1;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_ART_VAR1] = 1;
    }

    public void setCalArtVar2(String newCalArtVar2) {
        calArtVar2 = newCalArtVar2;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_ART_VAR2] = 1;
    }

    public void setCalArtVar3(String newCalArtVar3) {
        calArtVar3 = newCalArtVar3;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_ART_VAR3] = 1;
    }

    public void setCalNoLigneMc(Integer newCalNoLigneMc) {
        calNoLigneMc = newCalNoLigneMc;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC] = 1;
    }

    public void setCalPxAchBrut(Double newCalPxAchBrut) {
        calPxAchBrut = newCalPxAchBrut;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT] = 1;
    }

    public void setCalPxAchNeg(Double newCalPxAchNeg) {
        calPxAchNeg = newCalPxAchNeg;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG] = 1;
    }

    public void setCalPxAchNet(Double newCalPxAchNet) {
        calPxAchNet = newCalPxAchNet;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET] = 1;
    }

    public void setCalPxNetOn(String newCalPxNetOn) {
        calPxNetOn = newCalPxNetOn;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON] = 1;
    }

    public void setCalQteCmde(Double newCalQteCmde) {
        calQteCmde = newCalQteCmde;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE] = 1;
    }

    public void setCalQteRecue(Double newCalQteRecue) {
        calQteRecue = newCalQteRecue;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE] = 1;
    }

    public void setCalQteRetournee(Double newCalQteRetournee) {
        calQteRetournee = newCalQteRetournee;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE] = 1;
    }

    public void setCalQteSolde(Double newCalQteSolde) {
        calQteSolde = newCalQteSolde;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE] = 1;
    }

    public void setCalQteValidee(Double newCalQteValidee) {
        calQteValidee = newCalQteValidee;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE] = 1;
    }

    public void setCalSemainePrevLivr(Integer newCalSemainePrevLivr) {
        calSemainePrevLivr = newCalSemainePrevLivr;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR] = 1;
    }

    public void setCalDtPrevLivr1(Timestamp newCalDtPrevLivr1) {
        calDtPrevLivr1 = newCalDtPrevLivr1;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1] = 1;
    }

    public void setCalTvaCode(String newCalTvaCode) {
        calTvaCode = newCalTvaCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_TVA_CODE] = 1;
    }

    public void setCalTxpCode(String newCalTxpCode) {
        calTxpCode = newCalTxpCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_TXP_CODE] = 1;
    }

    public void setCalTxRemLig1(Double newCalTxRemLig1) {
        calTxRemLig1 = newCalTxRemLig1;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1] = 1;
    }

    public void setCalTxRemLig2(Double newCalTxRemLig2) {
        calTxRemLig2 = newCalTxRemLig2;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2] = 1;
    }

    public void setCalTxRemLig3(Double newCalTxRemLig3) {
        calTxRemLig3 = newCalTxRemLig3;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3] = 1;
    }

    public void setCalTxRemLig4(Double newCalTxRemLig4) {
        calTxRemLig4 = newCalTxRemLig4;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4] = 1;
    }

    public void setCalUnitePx(Integer newCalUnitePx) {
        calUnitePx = newCalUnitePx;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_UNITE_PX] = 1;
    }

    public void setCalPds(Double newCalPds) {
        calPds = newCalPds;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_PDS] = 1;
    }

    public void setCalVol(Double newCalVol) {
        calVol = newCalVol;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_VOL] = 1;
    }

    public void setCalMtEhf(Double newCalMtEhf) {
        calMtEhf = newCalMtEhf;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_MT_EHF] = 1;
    }

    public void setCalMtEhfComp(Double newCalMtEhfComp) {
        calMtEhfComp = newCalMtEhfComp;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP] = 1;
    }

    public void setCalSoldeOn(String newCalSoldeOn) {
        calSoldeOn = newCalSoldeOn;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON] = 1;
    }

    public void setCalIndex(String newCalIndex) {
        calIndex = newCalIndex;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_INDEX] = 1;
    }

    public void setCalDtCreat(Timestamp newCalDtCreat) {
        calDtCreat = newCalDtCreat;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_DT_CREAT] = 1;
    }

    public void setCalDtMaj(Timestamp newCalDtMaj) {
        calDtMaj = newCalDtMaj;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_DT_MAJ] = 1;
    }

    public void setCalUtilMaj(String newCalUtilMaj) {
        calUtilMaj = newCalUtilMaj;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.Z_STATUS] = 1;
    }

    public void setCalArgTratada01(String newCalArgTratada01) {
        calArgTratada01 = newCalArgTratada01;
        if (persist > 0)
            updCol[DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_CODE)
            return calCatDepCode;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_SOC_CODE)
            return calCatDepSocCode;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE)
            return calCatNoCmde;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION)
            return calCatNoVersion;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE)
            return calNoLigne;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_CODE)
            return calArtCode;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_VAR1)
            return calArtVar1;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_VAR2)
            return calArtVar2;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_VAR3)
            return calArtVar3;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC)
            return calNoLigneMc;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT)
            return calPxAchBrut;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG)
            return calPxAchNeg;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET)
            return calPxAchNet;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON)
            return calPxNetOn;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE)
            return calQteCmde;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE)
            return calQteRecue;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE)
            return calQteRetournee;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE)
            return calQteSolde;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE)
            return calQteValidee;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR)
            return calSemainePrevLivr;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1)
            return calDtPrevLivr1;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_TVA_CODE)
            return calTvaCode;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_TXP_CODE)
            return calTxpCode;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1)
            return calTxRemLig1;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2)
            return calTxRemLig2;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3)
            return calTxRemLig3;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4)
            return calTxRemLig4;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_UNITE_PX)
            return calUnitePx;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_PDS)
            return calPds;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_VOL)
            return calVol;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_MT_EHF)
            return calMtEhf;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP)
            return calMtEhfComp;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON)
            return calSoldeOn;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_INDEX)
            return calIndex;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_DT_CREAT)
            return calDtCreat;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_DT_MAJ)
            return calDtMaj;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ)
            return calUtilMaj;
        else if (numCol == DoDtCmdeAchatLigneDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtCmdeAchatLigneDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01)
            return calArgTratada01;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_CODE) {
            calCatDepCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_SOC_CODE) {
            calCatDepSocCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE) {
            calCatNoCmde = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION) {
            calCatNoVersion = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE) {
            calNoLigne = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_CODE) {
            calArtCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_VAR1) {
            calArtVar1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_VAR2) {
            calArtVar2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_ART_VAR3) {
            calArtVar3 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC) {
            calNoLigneMc = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT) {
            calPxAchBrut = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG) {
            calPxAchNeg = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET) {
            calPxAchNet = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON) {
            calPxNetOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE) {
            calQteCmde = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE) {
            calQteRecue = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE) {
            calQteRetournee = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE) {
            calQteSolde = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE) {
            calQteValidee = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR) {
            calSemainePrevLivr = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1) {
            calDtPrevLivr1 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_TVA_CODE) {
            calTvaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_TXP_CODE) {
            calTxpCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1) {
            calTxRemLig1 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2) {
            calTxRemLig2 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3) {
            calTxRemLig3 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4) {
            calTxRemLig4 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_UNITE_PX) {
            calUnitePx = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_PDS) {
            calPds = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_VOL) {
            calVol = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_MT_EHF) {
            calMtEhf = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP) {
            calMtEhfComp = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON) {
            calSoldeOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_INDEX) {
            calIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_DT_CREAT) {
            calDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_DT_MAJ) {
            calDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ) {
            calUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01) {
            calArgTratada01 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtCmdeAchatLigne());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtCmdeAchatLigne djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_CODE] != -1) {
            djo.calCatDepCode = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_CODE]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_SOC_CODE] != -1) {
            djo.calCatDepSocCode = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_SOC_CODE]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE]);
            if (!rs.wasNull())
                djo.calCatNoCmde = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.calCatNoVersion = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE]);
            if (!rs.wasNull())
                djo.calNoLigne = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_CODE] != -1) {
            djo.calArtCode = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_ART_CODE]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR1] != -1) {
            djo.calArtVar1 = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR1]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR2] != -1) {
            djo.calArtVar2 = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR2]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR3] != -1) {
            djo.calArtVar3 = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR3]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC]);
            if (!rs.wasNull())
                djo.calNoLigneMc = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT]);
            if (!rs.wasNull())
                djo.calPxAchBrut = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG]);
            if (!rs.wasNull())
                djo.calPxAchNeg = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET]);
            if (!rs.wasNull())
                djo.calPxAchNet = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON] != -1) {
            djo.calPxNetOn = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE]);
            if (!rs.wasNull())
                djo.calQteCmde = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE]);
            if (!rs.wasNull())
                djo.calQteRecue = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE]);
            if (!rs.wasNull())
                djo.calQteRetournee = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE]);
            if (!rs.wasNull())
                djo.calQteSolde = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE]);
            if (!rs.wasNull())
                djo.calQteValidee = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR]);
            if (!rs.wasNull())
                djo.calSemainePrevLivr = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1] != -1) {
            djo.calDtPrevLivr1 = rs.getTimestamp(val[DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TVA_CODE] != -1) {
            djo.calTvaCode = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_TVA_CODE]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TXP_CODE] != -1) {
            djo.calTxpCode = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_TXP_CODE]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1]);
            if (!rs.wasNull())
                djo.calTxRemLig1 = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2]);
            if (!rs.wasNull())
                djo.calTxRemLig2 = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3]);
            if (!rs.wasNull())
                djo.calTxRemLig3 = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4]);
            if (!rs.wasNull())
                djo.calTxRemLig4 = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_UNITE_PX] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.CAL_UNITE_PX]);
            if (!rs.wasNull())
                djo.calUnitePx = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PDS] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_PDS]);
            if (!rs.wasNull())
                djo.calPds = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_VOL] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_VOL]);
            if (!rs.wasNull())
                djo.calVol = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF]);
            if (!rs.wasNull())
                djo.calMtEhf = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP]);
            if (!rs.wasNull())
                djo.calMtEhfComp = new Double(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON] != -1) {
            djo.calSoldeOn = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_INDEX] != -1) {
            djo.calIndex = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_INDEX]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_DT_CREAT] != -1) {
            djo.calDtCreat = rs.getTimestamp(val[DoDtCmdeAchatLigneDesc.CAL_DT_CREAT]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_DT_MAJ] != -1) {
            djo.calDtMaj = rs.getTimestamp(val[DoDtCmdeAchatLigneDesc.CAL_DT_MAJ]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ] != -1) {
            djo.calUtilMaj = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ]);
        }
        if (val[DoDtCmdeAchatLigneDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatLigneDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtCmdeAchatLigneDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtCmdeAchatLigneDesc.Z_STATUS]);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01] != -1) {
            djo.calArgTratada01 = rs.getString(val[DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_CODE], calCatDepCode);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_CAT_DEP_SOC_CODE], calCatDepSocCode);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE] > 0) {
            if (calCatNoCmde == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_CMDE], calCatNoCmde.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION] > 0) {
            if (calCatNoVersion == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.CAL_CAT_NO_VERSION], calCatNoVersion.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE] > 0) {
            if (calNoLigne == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE], calNoLigne.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_ART_CODE], calArtCode);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR1] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR1], calArtVar1);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR2] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR2], calArtVar2);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR3] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_ART_VAR3], calArtVar3);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC] > 0) {
            if (calNoLigneMc == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.CAL_NO_LIGNE_MC], calNoLigneMc.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT] > 0) {
            if (calPxAchBrut == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_BRUT], calPxAchBrut.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG] > 0) {
            if (calPxAchNeg == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NEG], calPxAchNeg.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET] > 0) {
            if (calPxAchNet == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_PX_ACH_NET], calPxAchNet.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_PX_NET_ON], calPxNetOn);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE] > 0) {
            if (calQteCmde == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_CMDE], calQteCmde.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE] > 0) {
            if (calQteRecue == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_RECUE], calQteRecue.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE] > 0) {
            if (calQteRetournee == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_RETOURNEE], calQteRetournee.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE] > 0) {
            if (calQteSolde == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_SOLDE], calQteSolde.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE] > 0) {
            if (calQteValidee == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_QTE_VALIDEE], calQteValidee.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR] > 0) {
            if (calSemainePrevLivr == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.CAL_SEMAINE_PREV_LIVR], calSemainePrevLivr.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatLigneDesc.CAL_DT_PREV_LIVR1], calDtPrevLivr1);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TVA_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_TVA_CODE], calTvaCode);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TXP_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_TXP_CODE], calTxpCode);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1] > 0) {
            if (calTxRemLig1 == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG1], calTxRemLig1.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2] > 0) {
            if (calTxRemLig2 == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG2], calTxRemLig2.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3] > 0) {
            if (calTxRemLig3 == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG3], calTxRemLig3.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4] > 0) {
            if (calTxRemLig4 == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_TX_REM_LIG4], calTxRemLig4.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_UNITE_PX] > 0) {
            if (calUnitePx == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_UNITE_PX], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.CAL_UNITE_PX], calUnitePx.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_PDS] > 0) {
            if (calPds == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_PDS], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_PDS], calPds.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_VOL] > 0) {
            if (calVol == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_VOL], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_VOL], calVol.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF] > 0) {
            if (calMtEhf == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF], calMtEhf.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP] > 0) {
            if (calMtEhfComp == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatLigneDesc.CAL_MT_EHF_COMP], calMtEhfComp.doubleValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_SOLDE_ON], calSoldeOn);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_INDEX] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_INDEX], calIndex);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatLigneDesc.CAL_DT_CREAT], calDtCreat);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatLigneDesc.CAL_DT_MAJ], calDtMaj);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_UTIL_MAJ], calUtilMaj);
        }
        if (val[DoDtCmdeAchatLigneDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtCmdeAchatLigneDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatLigneDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtCmdeAchatLigneDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.Z_STATUS], zStatus);
        }
        if (val[DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01] > 0) {
            stmt.setString(val[DoDtCmdeAchatLigneDesc.CAL_ARG_TRATADA01], calArgTratada01);
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
        DoDtCmdeAchatLigne[] result = null;
        params = request.getParameterValues("calCatDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("calCatDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalCatDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("calCatNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalCatNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("calCatNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("calNoLigne");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalNoLigne((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("calArtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalArtCode(localVal);
            }
        }
        params = request.getParameterValues("calArtVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalArtVar1(localVal);
            }
        }
        params = request.getParameterValues("calArtVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalArtVar2(localVal);
            }
        }
        params = request.getParameterValues("calArtVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalArtVar3(localVal);
            }
        }
        params = request.getParameterValues("calNoLigneMc");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalNoLigneMc((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("calPxAchBrut");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalPxAchBrut((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calPxAchNeg");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalPxAchNeg((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calPxAchNet");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalPxAchNet((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calPxNetOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalPxNetOn(localVal);
            }
        }
        params = request.getParameterValues("calQteCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalQteCmde((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calQteRecue");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalQteRecue((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calQteRetournee");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalQteRetournee((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calQteSolde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalQteSolde((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calQteValidee");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalQteValidee((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calSemainePrevLivr");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalSemainePrevLivr((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("calDtPrevLivr1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalDtPrevLivr1((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("calTvaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalTvaCode(localVal);
            }
        }
        params = request.getParameterValues("calTxpCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalTxpCode(localVal);
            }
        }
        params = request.getParameterValues("calTxRemLig1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalTxRemLig1((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calTxRemLig2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalTxRemLig2((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calTxRemLig3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalTxRemLig3((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calTxRemLig4");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalTxRemLig4((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calUnitePx");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalUnitePx((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("calPds");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalPds((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calVol");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalVol((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calMtEhf");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalMtEhf((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calMtEhfComp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalMtEhfComp((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("calSoldeOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalSoldeOn(localVal);
            }
        }
        params = request.getParameterValues("calIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalIndex(localVal);
            }
        }
        params = request.getParameterValues("calDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("calDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("calUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
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
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
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
        params = request.getParameterValues("calArgTratada01");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatLigne[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatLigne();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCalArgTratada01(localVal);
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

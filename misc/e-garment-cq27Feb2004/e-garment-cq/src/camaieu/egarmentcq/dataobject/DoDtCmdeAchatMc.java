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

public class DoDtCmdeAchatMc implements DataObject {

    private static final IDoDescription description = new DoDtCmdeAchatMcDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[44];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_CMDE_ACHAT_MC"};
    //variables correspondant à la table DT_CMDE_ACHAT_MC
    private String camCatDepCode = null;
    private String camCatDepSocCode = null;
    private Integer camCatNoCmde = null;
    private Integer camCatNoVersion = null;
    private Integer camNoLigne = null;
    private String camArtCode = null;
    private String camArtVar1 = null;
    private String camArtVar2 = null;
    private String camArtVar3 = null;
    private Double camMtHt = null;
    private String camNatCond = null;
    private Integer camPcb = null;
    private Double camPxAchBrut = null;
    private Double camPxAchNet = null;
    private Double camQteCmde = null;
    private Double camQteRecue = null;
    private Double camQteRetournee = null;
    private Double camQteValidee = null;
    private Integer camSemainePrevLivr = null;
    private Timestamp camDtPrevLivr1 = null;
    private Integer camSpcb = null;
    private String camTvaCode = null;
    private String camTxpCode = null;
    private Double camTxRemLig1 = null;
    private Double camTxRemLig2 = null;
    private Double camTxRemLig3 = null;
    private Double camTxRemLig4 = null;
    private String camTcaCode = null;
    private Integer camUnitePx = null;
    private Double camMtEhf = null;
    private Double camMtEhfComp = null;
    private String camIndex = null;
    private Timestamp camDtCreat = null;
    private Timestamp camDtMaj = null;
    private String camUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private String camCreeEhf = null;
    private Double camQteOf = null;
    private String camCltAn = null;
    private String camCltCode = null;
    private String camCltSaiCode = null;
    private Integer camVerNoVersion = null;
    private String camVerCliente = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtCmdeAchatMc() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtCmdeAchatMc(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtCmdeAchatMc(DoDtCmdeAchatMc arg) {
        setCamCatDepCode(arg.camCatDepCode);
        setCamCatDepSocCode(arg.camCatDepSocCode);
        setCamCatNoCmde(arg.camCatNoCmde);
        setCamCatNoVersion(arg.camCatNoVersion);
        setCamNoLigne(arg.camNoLigne);
        setCamArtCode(arg.camArtCode);
        setCamArtVar1(arg.camArtVar1);
        setCamArtVar2(arg.camArtVar2);
        setCamArtVar3(arg.camArtVar3);
        setCamMtHt(arg.camMtHt);
        setCamNatCond(arg.camNatCond);
        setCamPcb(arg.camPcb);
        setCamPxAchBrut(arg.camPxAchBrut);
        setCamPxAchNet(arg.camPxAchNet);
        setCamQteCmde(arg.camQteCmde);
        setCamQteRecue(arg.camQteRecue);
        setCamQteRetournee(arg.camQteRetournee);
        setCamQteValidee(arg.camQteValidee);
        setCamSemainePrevLivr(arg.camSemainePrevLivr);
        setCamDtPrevLivr1(arg.camDtPrevLivr1);
        setCamSpcb(arg.camSpcb);
        setCamTvaCode(arg.camTvaCode);
        setCamTxpCode(arg.camTxpCode);
        setCamTxRemLig1(arg.camTxRemLig1);
        setCamTxRemLig2(arg.camTxRemLig2);
        setCamTxRemLig3(arg.camTxRemLig3);
        setCamTxRemLig4(arg.camTxRemLig4);
        setCamTcaCode(arg.camTcaCode);
        setCamUnitePx(arg.camUnitePx);
        setCamMtEhf(arg.camMtEhf);
        setCamMtEhfComp(arg.camMtEhfComp);
        setCamIndex(arg.camIndex);
        setCamDtCreat(arg.camDtCreat);
        setCamDtMaj(arg.camDtMaj);
        setCamUtilMaj(arg.camUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setCamCreeEhf(arg.camCreeEhf);
        setCamQteOf(arg.camQteOf);
        setCamCltAn(arg.camCltAn);
        setCamCltCode(arg.camCltCode);
        setCamCltSaiCode(arg.camCltSaiCode);
        setCamVerNoVersion(arg.camVerNoVersion);
        setCamVerCliente(arg.camVerCliente);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtCmdeAchatMc(String newSql, Object[] newParam) {
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

    public String getCamCatDepCode() {
        return camCatDepCode;
    }

    public String getCamCatDepSocCode() {
        return camCatDepSocCode;
    }

    public Integer getCamCatNoCmde() {
        return camCatNoCmde;
    }

    public Integer getCamCatNoVersion() {
        return camCatNoVersion;
    }

    public Integer getCamNoLigne() {
        return camNoLigne;
    }

    public String getCamArtCode() {
        return camArtCode;
    }

    public String getCamArtVar1() {
        return camArtVar1;
    }

    public String getCamArtVar2() {
        return camArtVar2;
    }

    public String getCamArtVar3() {
        return camArtVar3;
    }

    public Double getCamMtHt() {
        return camMtHt;
    }

    public String getCamNatCond() {
        return camNatCond;
    }

    public Integer getCamPcb() {
        return camPcb;
    }

    public Double getCamPxAchBrut() {
        return camPxAchBrut;
    }

    public Double getCamPxAchNet() {
        return camPxAchNet;
    }

    public Double getCamQteCmde() {
        return camQteCmde;
    }

    public Double getCamQteRecue() {
        return camQteRecue;
    }

    public Double getCamQteRetournee() {
        return camQteRetournee;
    }

    public Double getCamQteValidee() {
        return camQteValidee;
    }

    public Integer getCamSemainePrevLivr() {
        return camSemainePrevLivr;
    }

    public Timestamp getCamDtPrevLivr1() {
        return camDtPrevLivr1;
    }

    public Integer getCamSpcb() {
        return camSpcb;
    }

    public String getCamTvaCode() {
        return camTvaCode;
    }

    public String getCamTxpCode() {
        return camTxpCode;
    }

    public Double getCamTxRemLig1() {
        return camTxRemLig1;
    }

    public Double getCamTxRemLig2() {
        return camTxRemLig2;
    }

    public Double getCamTxRemLig3() {
        return camTxRemLig3;
    }

    public Double getCamTxRemLig4() {
        return camTxRemLig4;
    }

    public String getCamTcaCode() {
        return camTcaCode;
    }

    public Integer getCamUnitePx() {
        return camUnitePx;
    }

    public Double getCamMtEhf() {
        return camMtEhf;
    }

    public Double getCamMtEhfComp() {
        return camMtEhfComp;
    }

    public String getCamIndex() {
        return camIndex;
    }

    public Timestamp getCamDtCreat() {
        return camDtCreat;
    }

    public Timestamp getCamDtMaj() {
        return camDtMaj;
    }

    public String getCamUtilMaj() {
        return camUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getCamCreeEhf() {
        return camCreeEhf;
    }

    public Double getCamQteOf() {
        return camQteOf;
    }

    public String getCamCltAn() {
        return camCltAn;
    }

    public String getCamCltCode() {
        return camCltCode;
    }

    public String getCamCltSaiCode() {
        return camCltSaiCode;
    }

    public Integer getCamVerNoVersion() {
        return camVerNoVersion;
    }

    public String getCamVerCliente() {
        return camVerCliente;
    }

    public void setCamCatDepCode(String newCamCatDepCode) {
        camCatDepCode = newCamCatDepCode;
    }

    public void setCamCatDepSocCode(String newCamCatDepSocCode) {
        camCatDepSocCode = newCamCatDepSocCode;
    }

    public void setCamCatNoCmde(Integer newCamCatNoCmde) {
        camCatNoCmde = newCamCatNoCmde;
    }

    public void setCamCatNoVersion(Integer newCamCatNoVersion) {
        camCatNoVersion = newCamCatNoVersion;
    }

    public void setCamNoLigne(Integer newCamNoLigne) {
        camNoLigne = newCamNoLigne;
    }

    public void setCamArtCode(String newCamArtCode) {
        camArtCode = newCamArtCode;
    }

    public void setCamArtVar1(String newCamArtVar1) {
        camArtVar1 = newCamArtVar1;
    }

    public void setCamArtVar2(String newCamArtVar2) {
        camArtVar2 = newCamArtVar2;
    }

    public void setCamArtVar3(String newCamArtVar3) {
        camArtVar3 = newCamArtVar3;
    }

    public void setCamMtHt(Double newCamMtHt) {
        camMtHt = newCamMtHt;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_MT_HT] = 1;
    }

    public void setCamNatCond(String newCamNatCond) {
        camNatCond = newCamNatCond;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_NAT_COND] = 1;
    }

    public void setCamPcb(Integer newCamPcb) {
        camPcb = newCamPcb;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_PCB] = 1;
    }

    public void setCamPxAchBrut(Double newCamPxAchBrut) {
        camPxAchBrut = newCamPxAchBrut;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT] = 1;
    }

    public void setCamPxAchNet(Double newCamPxAchNet) {
        camPxAchNet = newCamPxAchNet;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET] = 1;
    }

    public void setCamQteCmde(Double newCamQteCmde) {
        camQteCmde = newCamQteCmde;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_QTE_CMDE] = 1;
    }

    public void setCamQteRecue(Double newCamQteRecue) {
        camQteRecue = newCamQteRecue;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_QTE_RECUE] = 1;
    }

    public void setCamQteRetournee(Double newCamQteRetournee) {
        camQteRetournee = newCamQteRetournee;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE] = 1;
    }

    public void setCamQteValidee(Double newCamQteValidee) {
        camQteValidee = newCamQteValidee;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE] = 1;
    }

    public void setCamSemainePrevLivr(Integer newCamSemainePrevLivr) {
        camSemainePrevLivr = newCamSemainePrevLivr;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR] = 1;
    }

    public void setCamDtPrevLivr1(Timestamp newCamDtPrevLivr1) {
        camDtPrevLivr1 = newCamDtPrevLivr1;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1] = 1;
    }

    public void setCamSpcb(Integer newCamSpcb) {
        camSpcb = newCamSpcb;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_SPCB] = 1;
    }

    public void setCamTvaCode(String newCamTvaCode) {
        camTvaCode = newCamTvaCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TVA_CODE] = 1;
    }

    public void setCamTxpCode(String newCamTxpCode) {
        camTxpCode = newCamTxpCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TXP_CODE] = 1;
    }

    public void setCamTxRemLig1(Double newCamTxRemLig1) {
        camTxRemLig1 = newCamTxRemLig1;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1] = 1;
    }

    public void setCamTxRemLig2(Double newCamTxRemLig2) {
        camTxRemLig2 = newCamTxRemLig2;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2] = 1;
    }

    public void setCamTxRemLig3(Double newCamTxRemLig3) {
        camTxRemLig3 = newCamTxRemLig3;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3] = 1;
    }

    public void setCamTxRemLig4(Double newCamTxRemLig4) {
        camTxRemLig4 = newCamTxRemLig4;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4] = 1;
    }

    public void setCamTcaCode(String newCamTcaCode) {
        camTcaCode = newCamTcaCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_TCA_CODE] = 1;
    }

    public void setCamUnitePx(Integer newCamUnitePx) {
        camUnitePx = newCamUnitePx;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_UNITE_PX] = 1;
    }

    public void setCamMtEhf(Double newCamMtEhf) {
        camMtEhf = newCamMtEhf;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_MT_EHF] = 1;
    }

    public void setCamMtEhfComp(Double newCamMtEhfComp) {
        camMtEhfComp = newCamMtEhfComp;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP] = 1;
    }

    public void setCamIndex(String newCamIndex) {
        camIndex = newCamIndex;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_INDEX] = 1;
    }

    public void setCamDtCreat(Timestamp newCamDtCreat) {
        camDtCreat = newCamDtCreat;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_DT_CREAT] = 1;
    }

    public void setCamDtMaj(Timestamp newCamDtMaj) {
        camDtMaj = newCamDtMaj;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_DT_MAJ] = 1;
    }

    public void setCamUtilMaj(String newCamUtilMaj) {
        camUtilMaj = newCamUtilMaj;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.Z_STATUS] = 1;
    }

    public void setCamCreeEhf(String newCamCreeEhf) {
        camCreeEhf = newCamCreeEhf;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_CREE_EHF] = 1;
    }

    public void setCamQteOf(Double newCamQteOf) {
        camQteOf = newCamQteOf;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_QTE_OF] = 1;
    }

    public void setCamCltAn(String newCamCltAn) {
        camCltAn = newCamCltAn;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_CLT_AN] = 1;
    }

    public void setCamCltCode(String newCamCltCode) {
        camCltCode = newCamCltCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_CLT_CODE] = 1;
    }

    public void setCamCltSaiCode(String newCamCltSaiCode) {
        camCltSaiCode = newCamCltSaiCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE] = 1;
    }

    public void setCamVerNoVersion(Integer newCamVerNoVersion) {
        camVerNoVersion = newCamVerNoVersion;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION] = 1;
    }

    public void setCamVerCliente(String newCamVerCliente) {
        camVerCliente = newCamVerCliente;
        if (persist > 0)
            updCol[DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_DEP_CODE)
            return camCatDepCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_DEP_SOC_CODE)
            return camCatDepSocCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE)
            return camCatNoCmde;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION)
            return camCatNoVersion;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_NO_LIGNE)
            return camNoLigne;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_CODE)
            return camArtCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_VAR1)
            return camArtVar1;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_VAR2)
            return camArtVar2;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_VAR3)
            return camArtVar3;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_MT_HT)
            return camMtHt;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_NAT_COND)
            return camNatCond;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_PCB)
            return camPcb;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT)
            return camPxAchBrut;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET)
            return camPxAchNet;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_CMDE)
            return camQteCmde;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_RECUE)
            return camQteRecue;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE)
            return camQteRetournee;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE)
            return camQteValidee;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR)
            return camSemainePrevLivr;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1)
            return camDtPrevLivr1;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_SPCB)
            return camSpcb;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TVA_CODE)
            return camTvaCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TXP_CODE)
            return camTxpCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1)
            return camTxRemLig1;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2)
            return camTxRemLig2;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3)
            return camTxRemLig3;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4)
            return camTxRemLig4;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_TCA_CODE)
            return camTcaCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_UNITE_PX)
            return camUnitePx;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_MT_EHF)
            return camMtEhf;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP)
            return camMtEhfComp;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_INDEX)
            return camIndex;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_DT_CREAT)
            return camDtCreat;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_DT_MAJ)
            return camDtMaj;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ)
            return camUtilMaj;
        else if (numCol == DoDtCmdeAchatMcDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtCmdeAchatMcDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CREE_EHF)
            return camCreeEhf;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_OF)
            return camQteOf;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CLT_AN)
            return camCltAn;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CLT_CODE)
            return camCltCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE)
            return camCltSaiCode;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION)
            return camVerNoVersion;
        else if (numCol == DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE)
            return camVerCliente;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_DEP_CODE) {
            camCatDepCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_DEP_SOC_CODE) {
            camCatDepSocCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE) {
            camCatNoCmde = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION) {
            camCatNoVersion = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_NO_LIGNE) {
            camNoLigne = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_CODE) {
            camArtCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_VAR1) {
            camArtVar1 = (String) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_VAR2) {
            camArtVar2 = (String) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_ART_VAR3) {
            camArtVar3 = (String) value;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_MT_HT) {
            camMtHt = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_NAT_COND) {
            camNatCond = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_PCB) {
            camPcb = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT) {
            camPxAchBrut = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET) {
            camPxAchNet = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_CMDE) {
            camQteCmde = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_RECUE) {
            camQteRecue = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE) {
            camQteRetournee = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE) {
            camQteValidee = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR) {
            camSemainePrevLivr = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1) {
            camDtPrevLivr1 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_SPCB) {
            camSpcb = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TVA_CODE) {
            camTvaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TXP_CODE) {
            camTxpCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1) {
            camTxRemLig1 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2) {
            camTxRemLig2 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3) {
            camTxRemLig3 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4) {
            camTxRemLig4 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_TCA_CODE) {
            camTcaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_UNITE_PX) {
            camUnitePx = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_MT_EHF) {
            camMtEhf = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP) {
            camMtEhfComp = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_INDEX) {
            camIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_DT_CREAT) {
            camDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_DT_MAJ) {
            camDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ) {
            camUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CREE_EHF) {
            camCreeEhf = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_QTE_OF) {
            camQteOf = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CLT_AN) {
            camCltAn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CLT_CODE) {
            camCltCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE) {
            camCltSaiCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION) {
            camVerNoVersion = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE) {
            camVerCliente = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtCmdeAchatMc());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtCmdeAchatMc djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_CODE] != -1) {
            djo.camCatDepCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_SOC_CODE] != -1) {
            djo.camCatDepSocCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_SOC_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE]);
            if (!rs.wasNull())
                djo.camCatNoCmde = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.camCatNoVersion = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_NO_LIGNE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_NO_LIGNE]);
            if (!rs.wasNull())
                djo.camNoLigne = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_CODE] != -1) {
            djo.camArtCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_ART_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_VAR1] != -1) {
            djo.camArtVar1 = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_ART_VAR1]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_VAR2] != -1) {
            djo.camArtVar2 = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_ART_VAR2]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_VAR3] != -1) {
            djo.camArtVar3 = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_ART_VAR3]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_MT_HT] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_MT_HT]);
            if (!rs.wasNull())
                djo.camMtHt = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_NAT_COND] != -1) {
            djo.camNatCond = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_NAT_COND]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_PCB] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_PCB]);
            if (!rs.wasNull())
                djo.camPcb = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT]);
            if (!rs.wasNull())
                djo.camPxAchBrut = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET]);
            if (!rs.wasNull())
                djo.camPxAchNet = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_CMDE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_CMDE]);
            if (!rs.wasNull())
                djo.camQteCmde = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_RECUE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_RECUE]);
            if (!rs.wasNull())
                djo.camQteRecue = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE]);
            if (!rs.wasNull())
                djo.camQteRetournee = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE]);
            if (!rs.wasNull())
                djo.camQteValidee = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR]);
            if (!rs.wasNull())
                djo.camSemainePrevLivr = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1] != -1) {
            djo.camDtPrevLivr1 = rs.getTimestamp(val[DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_SPCB] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_SPCB]);
            if (!rs.wasNull())
                djo.camSpcb = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TVA_CODE] != -1) {
            djo.camTvaCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_TVA_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TXP_CODE] != -1) {
            djo.camTxpCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_TXP_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1]);
            if (!rs.wasNull())
                djo.camTxRemLig1 = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2]);
            if (!rs.wasNull())
                djo.camTxRemLig2 = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3]);
            if (!rs.wasNull())
                djo.camTxRemLig3 = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4]);
            if (!rs.wasNull())
                djo.camTxRemLig4 = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TCA_CODE] != -1) {
            djo.camTcaCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_TCA_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_UNITE_PX] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_UNITE_PX]);
            if (!rs.wasNull())
                djo.camUnitePx = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_MT_EHF] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_MT_EHF]);
            if (!rs.wasNull())
                djo.camMtEhf = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP]);
            if (!rs.wasNull())
                djo.camMtEhfComp = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_INDEX] != -1) {
            djo.camIndex = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_INDEX]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_DT_CREAT] != -1) {
            djo.camDtCreat = rs.getTimestamp(val[DoDtCmdeAchatMcDesc.CAM_DT_CREAT]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_DT_MAJ] != -1) {
            djo.camDtMaj = rs.getTimestamp(val[DoDtCmdeAchatMcDesc.CAM_DT_MAJ]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ] != -1) {
            djo.camUtilMaj = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ]);
        }
        if (val[DoDtCmdeAchatMcDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtCmdeAchatMcDesc.Z_STATUS]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CREE_EHF] != -1) {
            djo.camCreeEhf = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_CREE_EHF]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_OF] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_OF]);
            if (!rs.wasNull())
                djo.camQteOf = new Double(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CLT_AN] != -1) {
            djo.camCltAn = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_CLT_AN]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CLT_CODE] != -1) {
            djo.camCltCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_CLT_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE] != -1) {
            djo.camCltSaiCode = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE]);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION]);
            if (!rs.wasNull())
                djo.camVerNoVersion = new Integer(temp);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE] != -1) {
            djo.camVerCliente = rs.getString(val[DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_CODE], camCatDepCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_CAT_DEP_SOC_CODE], camCatDepSocCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE] > 0) {
            if (camCatNoCmde == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_CMDE], camCatNoCmde.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION] > 0) {
            if (camCatNoVersion == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_CAT_NO_VERSION], camCatNoVersion.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_NO_LIGNE] > 0) {
            if (camNoLigne == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_NO_LIGNE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_NO_LIGNE], camNoLigne.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_ART_CODE], camArtCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_VAR1] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_ART_VAR1], camArtVar1);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_VAR2] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_ART_VAR2], camArtVar2);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_ART_VAR3] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_ART_VAR3], camArtVar3);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_MT_HT] > 0) {
            if (camMtHt == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_MT_HT], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_MT_HT], camMtHt.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_NAT_COND] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_NAT_COND], camNatCond);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_PCB] > 0) {
            if (camPcb == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_PCB], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_PCB], camPcb.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT] > 0) {
            if (camPxAchBrut == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_BRUT], camPxAchBrut.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET] > 0) {
            if (camPxAchNet == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_PX_ACH_NET], camPxAchNet.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_CMDE] > 0) {
            if (camQteCmde == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_QTE_CMDE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_CMDE], camQteCmde.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_RECUE] > 0) {
            if (camQteRecue == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_QTE_RECUE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_RECUE], camQteRecue.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE] > 0) {
            if (camQteRetournee == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_RETOURNEE], camQteRetournee.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE] > 0) {
            if (camQteValidee == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_VALIDEE], camQteValidee.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR] > 0) {
            if (camSemainePrevLivr == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_SEMAINE_PREV_LIVR], camSemainePrevLivr.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatMcDesc.CAM_DT_PREV_LIVR1], camDtPrevLivr1);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_SPCB] > 0) {
            if (camSpcb == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_SPCB], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_SPCB], camSpcb.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TVA_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_TVA_CODE], camTvaCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TXP_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_TXP_CODE], camTxpCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1] > 0) {
            if (camTxRemLig1 == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG1], camTxRemLig1.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2] > 0) {
            if (camTxRemLig2 == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG2], camTxRemLig2.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3] > 0) {
            if (camTxRemLig3 == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG3], camTxRemLig3.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4] > 0) {
            if (camTxRemLig4 == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_TX_REM_LIG4], camTxRemLig4.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_TCA_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_TCA_CODE], camTcaCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_UNITE_PX] > 0) {
            if (camUnitePx == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_UNITE_PX], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_UNITE_PX], camUnitePx.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_MT_EHF] > 0) {
            if (camMtEhf == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_MT_EHF], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_MT_EHF], camMtEhf.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP] > 0) {
            if (camMtEhfComp == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_MT_EHF_COMP], camMtEhfComp.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_INDEX] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_INDEX], camIndex);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatMcDesc.CAM_DT_CREAT], camDtCreat);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatMcDesc.CAM_DT_MAJ], camDtMaj);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_UTIL_MAJ], camUtilMaj);
        }
        if (val[DoDtCmdeAchatMcDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.Z_STATUS], zStatus);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CREE_EHF] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_CREE_EHF], camCreeEhf);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_QTE_OF] > 0) {
            if (camQteOf == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_QTE_OF], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatMcDesc.CAM_QTE_OF], camQteOf.doubleValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CLT_AN] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_CLT_AN], camCltAn);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CLT_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_CLT_CODE], camCltCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_CLT_SAI_CODE], camCltSaiCode);
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION] > 0) {
            if (camVerNoVersion == null)
                stmt.setNull(val[DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatMcDesc.CAM_VER_NO_VERSION], camVerNoVersion.intValue());
        }
        if (val[DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE] > 0) {
            stmt.setString(val[DoDtCmdeAchatMcDesc.CAM_VER_CLIENTE], camVerCliente);
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
        DoDtCmdeAchatMc[] result = null;
        params = request.getParameterValues("camCatDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("camCatDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCatDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("camCatNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCatNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camCatNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camNoLigne");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamNoLigne((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camArtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamArtCode(localVal);
            }
        }
        params = request.getParameterValues("camArtVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamArtVar1(localVal);
            }
        }
        params = request.getParameterValues("camArtVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamArtVar2(localVal);
            }
        }
        params = request.getParameterValues("camArtVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamArtVar3(localVal);
            }
        }
        params = request.getParameterValues("camMtHt");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamMtHt((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camNatCond");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamNatCond(localVal);
            }
        }
        params = request.getParameterValues("camPcb");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamPcb((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camPxAchBrut");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamPxAchBrut((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camPxAchNet");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamPxAchNet((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camQteCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamQteCmde((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camQteRecue");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamQteRecue((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camQteRetournee");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamQteRetournee((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camQteValidee");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamQteValidee((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camSemainePrevLivr");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamSemainePrevLivr((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camDtPrevLivr1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamDtPrevLivr1((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("camSpcb");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamSpcb((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camTvaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTvaCode(localVal);
            }
        }
        params = request.getParameterValues("camTxpCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTxpCode(localVal);
            }
        }
        params = request.getParameterValues("camTxRemLig1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTxRemLig1((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camTxRemLig2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTxRemLig2((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camTxRemLig3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTxRemLig3((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camTxRemLig4");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTxRemLig4((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camTcaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamTcaCode(localVal);
            }
        }
        params = request.getParameterValues("camUnitePx");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamUnitePx((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camMtEhf");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamMtEhf((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camMtEhfComp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamMtEhfComp((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamIndex(localVal);
            }
        }
        params = request.getParameterValues("camDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("camDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("camUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
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
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
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
        params = request.getParameterValues("camCreeEhf");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCreeEhf(localVal);
            }
        }
        params = request.getParameterValues("camQteOf");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamQteOf((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("camCltAn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCltAn(localVal);
            }
        }
        params = request.getParameterValues("camCltCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCltCode(localVal);
            }
        }
        params = request.getParameterValues("camCltSaiCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamCltSaiCode(localVal);
            }
        }
        params = request.getParameterValues("camVerNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamVerNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("camVerCliente");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatMc[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatMc();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCamVerCliente(localVal);
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

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

public class DoXnArt implements DataObject {

    private static final IDoDescription description = new DoXnArtDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[109];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"XN_ART"};
    //variables correspondant à la table XN_ART
    private String artCode = null;
    private String artVar1 = null;
    private String artVar2 = null;
    private String artVar3 = null;
    private String artModCode = null;
    private String artMocModCode = null;
    private String artMocModVar1 = null;
    private String artCaaCode = null;
    private String artFouCode = null;
    private String artSfaCode = null;
    private String artTxpCode = null;
    private String artMesCode = null;
    private String artFaaCode = null;
    private String artMesCodeAch = null;
    private String artMesCodeVte = null;
    private Double artPxInit = null;
    private String artTyaCode = null;
    private String artDes1 = null;
    private Timestamp artDtDerCmde = null;
    private String artTvaCode = null;
    private String artDecompose = null;
    private String artDepSocCode = null;
    private String artPayCode = null;
    private String artDepCode = null;
    private String artArtCode = null;
    private String artArtVar1 = null;
    private String artArtVar2 = null;
    private String artArtVar3 = null;
    private String artDes2 = null;
    private Timestamp artDtCreat = null;
    private Timestamp artDtMaj = null;
    private String artUtilMaj = null;
    private String artCalCompOn = null;
    private String artSubType = null;
    private Double artVol = null;
    private Integer artUnitePxVte = null;
    private Double artTypeArrondi = null;
    private String artStock = null;
    private Integer artSpcb = null;
    private String artSerieLot = null;
    private String artRefEmplac = null;
    private String artRefCatal = null;
    private Double artQteMinVte = null;
    private Double artDerPxAchat = null;
    private String artCpteVte = null;
    private Double artCeeCoef = null;
    private String artCeeNomen = null;
    private Double artCoefAchVte = null;
    private Double artCoefUnAchStk = null;
    private Double artCoefUnStkVte = null;
    private Integer artCondStk = null;
    private Double artPxVteMp = null;
    private Double artPxRevientMp = null;
    private Double artPxRevient = null;
    private Double artPxMini = null;
    private Double artPxMaxi = null;
    private Double artPxAchMp = null;
    private Double artPds = null;
    private Integer artPcb = null;
    private String artObs = null;
    private String artNetOn = null;
    private String artGencod = null;
    private String artEtiqOn = null;
    private String artEtat = null;
    private Timestamp artDtLimCmdeCli = null;
    private Timestamp artDtDerAchat = null;
    private Double artDerPxVte = null;
    private Integer artCondVte = null;
    private String artCpteAch = null;
    private String artCpteAnalAch = null;
    private String artCpteAnalVte = null;
    private Integer xtxId = null;
    private Integer artGarantie = null;
    private Double artHaut = null;
    private String artMesCodeStk = null;
    private Double artArrondisStk = null;
    private String zStatus = null;
    private String artContreMarqueOn = null;
    private String artDesCourt = null;
    private Integer artNbColis = null;
    private String artMagCode = null;
    private String artRotClass = null;
    private Integer artRotJour = null;
    private String artSteOn = null;
    private String artMesCodeStat = null;
    private Double artCoefUnVteStat = null;
    private Double artLong = null;
    private Double artLarg = null;
    private Double artPdsNet = null;
    private String artCstCode = null;
    private String artZmgCode = null;
    private String artFrais = null;
    private String artQteType = null;
    private Double artArgPerdidas = null;
    private String artArgCatalogo = null;
    private Integer artArgLoteMinimo = null;
    private Integer artArgPcrCodigo = null;
    private Integer artArgPcrRevision = null;
    private String artArgControlRec = null;
    private String artArgModoCompra = null;
    private Integer artArgPlazoFabr = null;
    private String artArgFabricado = null;
    private String artTypeArticle = null;
    private Timestamp artDtOkNomenclature = null;
    private Integer artArgQualite = null;
    private Integer artCenNiv = null;
    private Integer artPcrCodigoEntrep = null;
    private Double artEpaisseurCintre = null;
    private String artTypeEclatement = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoXnArt() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnArt(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnArt(DoXnArt arg) {
        setArtCode(arg.artCode);
        setArtVar1(arg.artVar1);
        setArtVar2(arg.artVar2);
        setArtVar3(arg.artVar3);
        setArtModCode(arg.artModCode);
        setArtMocModCode(arg.artMocModCode);
        setArtMocModVar1(arg.artMocModVar1);
        setArtCaaCode(arg.artCaaCode);
        setArtFouCode(arg.artFouCode);
        setArtSfaCode(arg.artSfaCode);
        setArtTxpCode(arg.artTxpCode);
        setArtMesCode(arg.artMesCode);
        setArtFaaCode(arg.artFaaCode);
        setArtMesCodeAch(arg.artMesCodeAch);
        setArtMesCodeVte(arg.artMesCodeVte);
        setArtPxInit(arg.artPxInit);
        setArtTyaCode(arg.artTyaCode);
        setArtDes1(arg.artDes1);
        setArtDtDerCmde(arg.artDtDerCmde);
        setArtTvaCode(arg.artTvaCode);
        setArtDecompose(arg.artDecompose);
        setArtDepSocCode(arg.artDepSocCode);
        setArtPayCode(arg.artPayCode);
        setArtDepCode(arg.artDepCode);
        setArtArtCode(arg.artArtCode);
        setArtArtVar1(arg.artArtVar1);
        setArtArtVar2(arg.artArtVar2);
        setArtArtVar3(arg.artArtVar3);
        setArtDes2(arg.artDes2);
        setArtDtCreat(arg.artDtCreat);
        setArtDtMaj(arg.artDtMaj);
        setArtUtilMaj(arg.artUtilMaj);
        setArtCalCompOn(arg.artCalCompOn);
        setArtSubType(arg.artSubType);
        setArtVol(arg.artVol);
        setArtUnitePxVte(arg.artUnitePxVte);
        setArtTypeArrondi(arg.artTypeArrondi);
        setArtStock(arg.artStock);
        setArtSpcb(arg.artSpcb);
        setArtSerieLot(arg.artSerieLot);
        setArtRefEmplac(arg.artRefEmplac);
        setArtRefCatal(arg.artRefCatal);
        setArtQteMinVte(arg.artQteMinVte);
        setArtDerPxAchat(arg.artDerPxAchat);
        setArtCpteVte(arg.artCpteVte);
        setArtCeeCoef(arg.artCeeCoef);
        setArtCeeNomen(arg.artCeeNomen);
        setArtCoefAchVte(arg.artCoefAchVte);
        setArtCoefUnAchStk(arg.artCoefUnAchStk);
        setArtCoefUnStkVte(arg.artCoefUnStkVte);
        setArtCondStk(arg.artCondStk);
        setArtPxVteMp(arg.artPxVteMp);
        setArtPxRevientMp(arg.artPxRevientMp);
        setArtPxRevient(arg.artPxRevient);
        setArtPxMini(arg.artPxMini);
        setArtPxMaxi(arg.artPxMaxi);
        setArtPxAchMp(arg.artPxAchMp);
        setArtPds(arg.artPds);
        setArtPcb(arg.artPcb);
        setArtObs(arg.artObs);
        setArtNetOn(arg.artNetOn);
        setArtGencod(arg.artGencod);
        setArtEtiqOn(arg.artEtiqOn);
        setArtEtat(arg.artEtat);
        setArtDtLimCmdeCli(arg.artDtLimCmdeCli);
        setArtDtDerAchat(arg.artDtDerAchat);
        setArtDerPxVte(arg.artDerPxVte);
        setArtCondVte(arg.artCondVte);
        setArtCpteAch(arg.artCpteAch);
        setArtCpteAnalAch(arg.artCpteAnalAch);
        setArtCpteAnalVte(arg.artCpteAnalVte);
        setXtxId(arg.xtxId);
        setArtGarantie(arg.artGarantie);
        setArtHaut(arg.artHaut);
        setArtMesCodeStk(arg.artMesCodeStk);
        setArtArrondisStk(arg.artArrondisStk);
        setZStatus(arg.zStatus);
        setArtContreMarqueOn(arg.artContreMarqueOn);
        setArtDesCourt(arg.artDesCourt);
        setArtNbColis(arg.artNbColis);
        setArtMagCode(arg.artMagCode);
        setArtRotClass(arg.artRotClass);
        setArtRotJour(arg.artRotJour);
        setArtSteOn(arg.artSteOn);
        setArtMesCodeStat(arg.artMesCodeStat);
        setArtCoefUnVteStat(arg.artCoefUnVteStat);
        setArtLong(arg.artLong);
        setArtLarg(arg.artLarg);
        setArtPdsNet(arg.artPdsNet);
        setArtCstCode(arg.artCstCode);
        setArtZmgCode(arg.artZmgCode);
        setArtFrais(arg.artFrais);
        setArtQteType(arg.artQteType);
        setArtArgPerdidas(arg.artArgPerdidas);
        setArtArgCatalogo(arg.artArgCatalogo);
        setArtArgLoteMinimo(arg.artArgLoteMinimo);
        setArtArgPcrCodigo(arg.artArgPcrCodigo);
        setArtArgPcrRevision(arg.artArgPcrRevision);
        setArtArgControlRec(arg.artArgControlRec);
        setArtArgModoCompra(arg.artArgModoCompra);
        setArtArgPlazoFabr(arg.artArgPlazoFabr);
        setArtArgFabricado(arg.artArgFabricado);
        setArtTypeArticle(arg.artTypeArticle);
        setArtDtOkNomenclature(arg.artDtOkNomenclature);
        setArtArgQualite(arg.artArgQualite);
        setArtCenNiv(arg.artCenNiv);
        setArtPcrCodigoEntrep(arg.artPcrCodigoEntrep);
        setArtEpaisseurCintre(arg.artEpaisseurCintre);
        setArtTypeEclatement(arg.artTypeEclatement);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoXnArt(String newSql, Object[] newParam) {
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

    public String getArtCode() {
        return artCode;
    }

    public String getArtVar1() {
        return artVar1;
    }

    public String getArtVar2() {
        return artVar2;
    }

    public String getArtVar3() {
        return artVar3;
    }

    public String getArtModCode() {
        return artModCode;
    }

    public String getArtMocModCode() {
        return artMocModCode;
    }

    public String getArtMocModVar1() {
        return artMocModVar1;
    }

    public String getArtCaaCode() {
        return artCaaCode;
    }

    public String getArtFouCode() {
        return artFouCode;
    }

    public String getArtSfaCode() {
        return artSfaCode;
    }

    public String getArtTxpCode() {
        return artTxpCode;
    }

    public String getArtMesCode() {
        return artMesCode;
    }

    public String getArtFaaCode() {
        return artFaaCode;
    }

    public String getArtMesCodeAch() {
        return artMesCodeAch;
    }

    public String getArtMesCodeVte() {
        return artMesCodeVte;
    }

    public Double getArtPxInit() {
        return artPxInit;
    }

    public String getArtTyaCode() {
        return artTyaCode;
    }

    public String getArtDes1() {
        return artDes1;
    }

    public Timestamp getArtDtDerCmde() {
        return artDtDerCmde;
    }

    public String getArtTvaCode() {
        return artTvaCode;
    }

    public String getArtDecompose() {
        return artDecompose;
    }

    public String getArtDepSocCode() {
        return artDepSocCode;
    }

    public String getArtPayCode() {
        return artPayCode;
    }

    public String getArtDepCode() {
        return artDepCode;
    }

    public String getArtArtCode() {
        return artArtCode;
    }

    public String getArtArtVar1() {
        return artArtVar1;
    }

    public String getArtArtVar2() {
        return artArtVar2;
    }

    public String getArtArtVar3() {
        return artArtVar3;
    }

    public String getArtDes2() {
        return artDes2;
    }

    public Timestamp getArtDtCreat() {
        return artDtCreat;
    }

    public Timestamp getArtDtMaj() {
        return artDtMaj;
    }

    public String getArtUtilMaj() {
        return artUtilMaj;
    }

    public String getArtCalCompOn() {
        return artCalCompOn;
    }

    public String getArtSubType() {
        return artSubType;
    }

    public Double getArtVol() {
        return artVol;
    }

    public Integer getArtUnitePxVte() {
        return artUnitePxVte;
    }

    public Double getArtTypeArrondi() {
        return artTypeArrondi;
    }

    public String getArtStock() {
        return artStock;
    }

    public Integer getArtSpcb() {
        return artSpcb;
    }

    public String getArtSerieLot() {
        return artSerieLot;
    }

    public String getArtRefEmplac() {
        return artRefEmplac;
    }

    public String getArtRefCatal() {
        return artRefCatal;
    }

    public Double getArtQteMinVte() {
        return artQteMinVte;
    }

    public Double getArtDerPxAchat() {
        return artDerPxAchat;
    }

    public String getArtCpteVte() {
        return artCpteVte;
    }

    public Double getArtCeeCoef() {
        return artCeeCoef;
    }

    public String getArtCeeNomen() {
        return artCeeNomen;
    }

    public Double getArtCoefAchVte() {
        return artCoefAchVte;
    }

    public Double getArtCoefUnAchStk() {
        return artCoefUnAchStk;
    }

    public Double getArtCoefUnStkVte() {
        return artCoefUnStkVte;
    }

    public Integer getArtCondStk() {
        return artCondStk;
    }

    public Double getArtPxVteMp() {
        return artPxVteMp;
    }

    public Double getArtPxRevientMp() {
        return artPxRevientMp;
    }

    public Double getArtPxRevient() {
        return artPxRevient;
    }

    public Double getArtPxMini() {
        return artPxMini;
    }

    public Double getArtPxMaxi() {
        return artPxMaxi;
    }

    public Double getArtPxAchMp() {
        return artPxAchMp;
    }

    public Double getArtPds() {
        return artPds;
    }

    public Integer getArtPcb() {
        return artPcb;
    }

    public String getArtObs() {
        return artObs;
    }

    public String getArtNetOn() {
        return artNetOn;
    }

    public String getArtGencod() {
        return artGencod;
    }

    public String getArtEtiqOn() {
        return artEtiqOn;
    }

    public String getArtEtat() {
        return artEtat;
    }

    public Timestamp getArtDtLimCmdeCli() {
        return artDtLimCmdeCli;
    }

    public Timestamp getArtDtDerAchat() {
        return artDtDerAchat;
    }

    public Double getArtDerPxVte() {
        return artDerPxVte;
    }

    public Integer getArtCondVte() {
        return artCondVte;
    }

    public String getArtCpteAch() {
        return artCpteAch;
    }

    public String getArtCpteAnalAch() {
        return artCpteAnalAch;
    }

    public String getArtCpteAnalVte() {
        return artCpteAnalVte;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public Integer getArtGarantie() {
        return artGarantie;
    }

    public Double getArtHaut() {
        return artHaut;
    }

    public String getArtMesCodeStk() {
        return artMesCodeStk;
    }

    public Double getArtArrondisStk() {
        return artArrondisStk;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getArtContreMarqueOn() {
        return artContreMarqueOn;
    }

    public String getArtDesCourt() {
        return artDesCourt;
    }

    public Integer getArtNbColis() {
        return artNbColis;
    }

    public String getArtMagCode() {
        return artMagCode;
    }

    public String getArtRotClass() {
        return artRotClass;
    }

    public Integer getArtRotJour() {
        return artRotJour;
    }

    public String getArtSteOn() {
        return artSteOn;
    }

    public String getArtMesCodeStat() {
        return artMesCodeStat;
    }

    public Double getArtCoefUnVteStat() {
        return artCoefUnVteStat;
    }

    public Double getArtLong() {
        return artLong;
    }

    public Double getArtLarg() {
        return artLarg;
    }

    public Double getArtPdsNet() {
        return artPdsNet;
    }

    public String getArtCstCode() {
        return artCstCode;
    }

    public String getArtZmgCode() {
        return artZmgCode;
    }

    public String getArtFrais() {
        return artFrais;
    }

    public String getArtQteType() {
        return artQteType;
    }

    public Double getArtArgPerdidas() {
        return artArgPerdidas;
    }

    public String getArtArgCatalogo() {
        return artArgCatalogo;
    }

    public Integer getArtArgLoteMinimo() {
        return artArgLoteMinimo;
    }

    public Integer getArtArgPcrCodigo() {
        return artArgPcrCodigo;
    }

    public Integer getArtArgPcrRevision() {
        return artArgPcrRevision;
    }

    public String getArtArgControlRec() {
        return artArgControlRec;
    }

    public String getArtArgModoCompra() {
        return artArgModoCompra;
    }

    public Integer getArtArgPlazoFabr() {
        return artArgPlazoFabr;
    }

    public String getArtArgFabricado() {
        return artArgFabricado;
    }

    public String getArtTypeArticle() {
        return artTypeArticle;
    }

    public Timestamp getArtDtOkNomenclature() {
        return artDtOkNomenclature;
    }

    public Integer getArtArgQualite() {
        return artArgQualite;
    }

    public Integer getArtCenNiv() {
        return artCenNiv;
    }

    public Integer getArtPcrCodigoEntrep() {
        return artPcrCodigoEntrep;
    }

    public Double getArtEpaisseurCintre() {
        return artEpaisseurCintre;
    }

    public String getArtTypeEclatement() {
        return artTypeEclatement;
    }

    public void setArtCode(String newArtCode) {
        artCode = newArtCode;
    }

    public void setArtVar1(String newArtVar1) {
        artVar1 = newArtVar1;
    }

    public void setArtVar2(String newArtVar2) {
        artVar2 = newArtVar2;
    }

    public void setArtVar3(String newArtVar3) {
        artVar3 = newArtVar3;
    }

    public void setArtModCode(String newArtModCode) {
        artModCode = newArtModCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MOD_CODE] = 1;
    }

    public void setArtMocModCode(String newArtMocModCode) {
        artMocModCode = newArtMocModCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MOC_MOD_CODE] = 1;
    }

    public void setArtMocModVar1(String newArtMocModVar1) {
        artMocModVar1 = newArtMocModVar1;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MOC_MOD_VAR1] = 1;
    }

    public void setArtCaaCode(String newArtCaaCode) {
        artCaaCode = newArtCaaCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CAA_CODE] = 1;
    }

    public void setArtFouCode(String newArtFouCode) {
        artFouCode = newArtFouCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_FOU_CODE] = 1;
    }

    public void setArtSfaCode(String newArtSfaCode) {
        artSfaCode = newArtSfaCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_SFA_CODE] = 1;
    }

    public void setArtTxpCode(String newArtTxpCode) {
        artTxpCode = newArtTxpCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_TXP_CODE] = 1;
    }

    public void setArtMesCode(String newArtMesCode) {
        artMesCode = newArtMesCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MES_CODE] = 1;
    }

    public void setArtFaaCode(String newArtFaaCode) {
        artFaaCode = newArtFaaCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_FAA_CODE] = 1;
    }

    public void setArtMesCodeAch(String newArtMesCodeAch) {
        artMesCodeAch = newArtMesCodeAch;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MES_CODE_ACH] = 1;
    }

    public void setArtMesCodeVte(String newArtMesCodeVte) {
        artMesCodeVte = newArtMesCodeVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MES_CODE_VTE] = 1;
    }

    public void setArtPxInit(Double newArtPxInit) {
        artPxInit = newArtPxInit;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_INIT] = 1;
    }

    public void setArtTyaCode(String newArtTyaCode) {
        artTyaCode = newArtTyaCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_TYA_CODE] = 1;
    }

    public void setArtDes1(String newArtDes1) {
        artDes1 = newArtDes1;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DES1] = 1;
    }

    public void setArtDtDerCmde(Timestamp newArtDtDerCmde) {
        artDtDerCmde = newArtDtDerCmde;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DT_DER_CMDE] = 1;
    }

    public void setArtTvaCode(String newArtTvaCode) {
        artTvaCode = newArtTvaCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_TVA_CODE] = 1;
    }

    public void setArtDecompose(String newArtDecompose) {
        artDecompose = newArtDecompose;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DECOMPOSE] = 1;
    }

    public void setArtDepSocCode(String newArtDepSocCode) {
        artDepSocCode = newArtDepSocCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DEP_SOC_CODE] = 1;
    }

    public void setArtPayCode(String newArtPayCode) {
        artPayCode = newArtPayCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PAY_CODE] = 1;
    }

    public void setArtDepCode(String newArtDepCode) {
        artDepCode = newArtDepCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DEP_CODE] = 1;
    }

    public void setArtArtCode(String newArtArtCode) {
        artArtCode = newArtArtCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ART_CODE] = 1;
    }

    public void setArtArtVar1(String newArtArtVar1) {
        artArtVar1 = newArtArtVar1;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ART_VAR1] = 1;
    }

    public void setArtArtVar2(String newArtArtVar2) {
        artArtVar2 = newArtArtVar2;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ART_VAR2] = 1;
    }

    public void setArtArtVar3(String newArtArtVar3) {
        artArtVar3 = newArtArtVar3;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ART_VAR3] = 1;
    }

    public void setArtDes2(String newArtDes2) {
        artDes2 = newArtDes2;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DES2] = 1;
    }

    public void setArtDtCreat(Timestamp newArtDtCreat) {
        artDtCreat = newArtDtCreat;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DT_CREAT] = 1;
    }

    public void setArtDtMaj(Timestamp newArtDtMaj) {
        artDtMaj = newArtDtMaj;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DT_MAJ] = 1;
    }

    public void setArtUtilMaj(String newArtUtilMaj) {
        artUtilMaj = newArtUtilMaj;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_UTIL_MAJ] = 1;
    }

    public void setArtCalCompOn(String newArtCalCompOn) {
        artCalCompOn = newArtCalCompOn;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CAL_COMP_ON] = 1;
    }

    public void setArtSubType(String newArtSubType) {
        artSubType = newArtSubType;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_SUB_TYPE] = 1;
    }

    public void setArtVol(Double newArtVol) {
        artVol = newArtVol;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_VOL] = 1;
    }

    public void setArtUnitePxVte(Integer newArtUnitePxVte) {
        artUnitePxVte = newArtUnitePxVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_UNITE_PX_VTE] = 1;
    }

    public void setArtTypeArrondi(Double newArtTypeArrondi) {
        artTypeArrondi = newArtTypeArrondi;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_TYPE_ARRONDI] = 1;
    }

    public void setArtStock(String newArtStock) {
        artStock = newArtStock;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_STOCK] = 1;
    }

    public void setArtSpcb(Integer newArtSpcb) {
        artSpcb = newArtSpcb;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_SPCB] = 1;
    }

    public void setArtSerieLot(String newArtSerieLot) {
        artSerieLot = newArtSerieLot;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_SERIE_LOT] = 1;
    }

    public void setArtRefEmplac(String newArtRefEmplac) {
        artRefEmplac = newArtRefEmplac;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_REF_EMPLAC] = 1;
    }

    public void setArtRefCatal(String newArtRefCatal) {
        artRefCatal = newArtRefCatal;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_REF_CATAL] = 1;
    }

    public void setArtQteMinVte(Double newArtQteMinVte) {
        artQteMinVte = newArtQteMinVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_QTE_MIN_VTE] = 1;
    }

    public void setArtDerPxAchat(Double newArtDerPxAchat) {
        artDerPxAchat = newArtDerPxAchat;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DER_PX_ACHAT] = 1;
    }

    public void setArtCpteVte(String newArtCpteVte) {
        artCpteVte = newArtCpteVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CPTE_VTE] = 1;
    }

    public void setArtCeeCoef(Double newArtCeeCoef) {
        artCeeCoef = newArtCeeCoef;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CEE_COEF] = 1;
    }

    public void setArtCeeNomen(String newArtCeeNomen) {
        artCeeNomen = newArtCeeNomen;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CEE_NOMEN] = 1;
    }

    public void setArtCoefAchVte(Double newArtCoefAchVte) {
        artCoefAchVte = newArtCoefAchVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_COEF_ACH_VTE] = 1;
    }

    public void setArtCoefUnAchStk(Double newArtCoefUnAchStk) {
        artCoefUnAchStk = newArtCoefUnAchStk;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_COEF_UN_ACH_STK] = 1;
    }

    public void setArtCoefUnStkVte(Double newArtCoefUnStkVte) {
        artCoefUnStkVte = newArtCoefUnStkVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_COEF_UN_STK_VTE] = 1;
    }

    public void setArtCondStk(Integer newArtCondStk) {
        artCondStk = newArtCondStk;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_COND_STK] = 1;
    }

    public void setArtPxVteMp(Double newArtPxVteMp) {
        artPxVteMp = newArtPxVteMp;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_VTE_MP] = 1;
    }

    public void setArtPxRevientMp(Double newArtPxRevientMp) {
        artPxRevientMp = newArtPxRevientMp;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_REVIENT_MP] = 1;
    }

    public void setArtPxRevient(Double newArtPxRevient) {
        artPxRevient = newArtPxRevient;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_REVIENT] = 1;
    }

    public void setArtPxMini(Double newArtPxMini) {
        artPxMini = newArtPxMini;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_MINI] = 1;
    }

    public void setArtPxMaxi(Double newArtPxMaxi) {
        artPxMaxi = newArtPxMaxi;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_MAXI] = 1;
    }

    public void setArtPxAchMp(Double newArtPxAchMp) {
        artPxAchMp = newArtPxAchMp;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PX_ACH_MP] = 1;
    }

    public void setArtPds(Double newArtPds) {
        artPds = newArtPds;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PDS] = 1;
    }

    public void setArtPcb(Integer newArtPcb) {
        artPcb = newArtPcb;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PCB] = 1;
    }

    public void setArtObs(String newArtObs) {
        artObs = newArtObs;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_OBS] = 1;
    }

    public void setArtNetOn(String newArtNetOn) {
        artNetOn = newArtNetOn;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_NET_ON] = 1;
    }

    public void setArtGencod(String newArtGencod) {
        artGencod = newArtGencod;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_GENCOD] = 1;
    }

    public void setArtEtiqOn(String newArtEtiqOn) {
        artEtiqOn = newArtEtiqOn;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ETIQ_ON] = 1;
    }

    public void setArtEtat(String newArtEtat) {
        artEtat = newArtEtat;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ETAT] = 1;
    }

    public void setArtDtLimCmdeCli(Timestamp newArtDtLimCmdeCli) {
        artDtLimCmdeCli = newArtDtLimCmdeCli;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DT_LIM_CMDE_CLI] = 1;
    }

    public void setArtDtDerAchat(Timestamp newArtDtDerAchat) {
        artDtDerAchat = newArtDtDerAchat;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DT_DER_ACHAT] = 1;
    }

    public void setArtDerPxVte(Double newArtDerPxVte) {
        artDerPxVte = newArtDerPxVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DER_PX_VTE] = 1;
    }

    public void setArtCondVte(Integer newArtCondVte) {
        artCondVte = newArtCondVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_COND_VTE] = 1;
    }

    public void setArtCpteAch(String newArtCpteAch) {
        artCpteAch = newArtCpteAch;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CPTE_ACH] = 1;
    }

    public void setArtCpteAnalAch(String newArtCpteAnalAch) {
        artCpteAnalAch = newArtCpteAnalAch;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CPTE_ANAL_ACH] = 1;
    }

    public void setArtCpteAnalVte(String newArtCpteAnalVte) {
        artCpteAnalVte = newArtCpteAnalVte;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CPTE_ANAL_VTE] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoXnArtDesc.XTX_ID] = 1;
    }

    public void setArtGarantie(Integer newArtGarantie) {
        artGarantie = newArtGarantie;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_GARANTIE] = 1;
    }

    public void setArtHaut(Double newArtHaut) {
        artHaut = newArtHaut;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_HAUT] = 1;
    }

    public void setArtMesCodeStk(String newArtMesCodeStk) {
        artMesCodeStk = newArtMesCodeStk;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MES_CODE_STK] = 1;
    }

    public void setArtArrondisStk(Double newArtArrondisStk) {
        artArrondisStk = newArtArrondisStk;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARRONDIS_STK] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoXnArtDesc.Z_STATUS] = 1;
    }

    public void setArtContreMarqueOn(String newArtContreMarqueOn) {
        artContreMarqueOn = newArtContreMarqueOn;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CONTRE_MARQUE_ON] = 1;
    }

    public void setArtDesCourt(String newArtDesCourt) {
        artDesCourt = newArtDesCourt;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DES_COURT] = 1;
    }

    public void setArtNbColis(Integer newArtNbColis) {
        artNbColis = newArtNbColis;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_NB_COLIS] = 1;
    }

    public void setArtMagCode(String newArtMagCode) {
        artMagCode = newArtMagCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MAG_CODE] = 1;
    }

    public void setArtRotClass(String newArtRotClass) {
        artRotClass = newArtRotClass;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ROT_CLASS] = 1;
    }

    public void setArtRotJour(Integer newArtRotJour) {
        artRotJour = newArtRotJour;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ROT_JOUR] = 1;
    }

    public void setArtSteOn(String newArtSteOn) {
        artSteOn = newArtSteOn;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_STE_ON] = 1;
    }

    public void setArtMesCodeStat(String newArtMesCodeStat) {
        artMesCodeStat = newArtMesCodeStat;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_MES_CODE_STAT] = 1;
    }

    public void setArtCoefUnVteStat(Double newArtCoefUnVteStat) {
        artCoefUnVteStat = newArtCoefUnVteStat;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_COEF_UN_VTE_STAT] = 1;
    }

    public void setArtLong(Double newArtLong) {
        artLong = newArtLong;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_LONG] = 1;
    }

    public void setArtLarg(Double newArtLarg) {
        artLarg = newArtLarg;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_LARG] = 1;
    }

    public void setArtPdsNet(Double newArtPdsNet) {
        artPdsNet = newArtPdsNet;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PDS_NET] = 1;
    }

    public void setArtCstCode(String newArtCstCode) {
        artCstCode = newArtCstCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CST_CODE] = 1;
    }

    public void setArtZmgCode(String newArtZmgCode) {
        artZmgCode = newArtZmgCode;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ZMG_CODE] = 1;
    }

    public void setArtFrais(String newArtFrais) {
        artFrais = newArtFrais;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_FRAIS] = 1;
    }

    public void setArtQteType(String newArtQteType) {
        artQteType = newArtQteType;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_QTE_TYPE] = 1;
    }

    public void setArtArgPerdidas(Double newArtArgPerdidas) {
        artArgPerdidas = newArtArgPerdidas;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_PERDIDAS] = 1;
    }

    public void setArtArgCatalogo(String newArtArgCatalogo) {
        artArgCatalogo = newArtArgCatalogo;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_CATALOGO] = 1;
    }

    public void setArtArgLoteMinimo(Integer newArtArgLoteMinimo) {
        artArgLoteMinimo = newArtArgLoteMinimo;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_LOTE_MINIMO] = 1;
    }

    public void setArtArgPcrCodigo(Integer newArtArgPcrCodigo) {
        artArgPcrCodigo = newArtArgPcrCodigo;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_PCR_CODIGO] = 1;
    }

    public void setArtArgPcrRevision(Integer newArtArgPcrRevision) {
        artArgPcrRevision = newArtArgPcrRevision;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_PCR_REVISION] = 1;
    }

    public void setArtArgControlRec(String newArtArgControlRec) {
        artArgControlRec = newArtArgControlRec;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_CONTROL_REC] = 1;
    }

    public void setArtArgModoCompra(String newArtArgModoCompra) {
        artArgModoCompra = newArtArgModoCompra;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_MODO_COMPRA] = 1;
    }

    public void setArtArgPlazoFabr(Integer newArtArgPlazoFabr) {
        artArgPlazoFabr = newArtArgPlazoFabr;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_PLAZO_FABR] = 1;
    }

    public void setArtArgFabricado(String newArtArgFabricado) {
        artArgFabricado = newArtArgFabricado;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_FABRICADO] = 1;
    }

    public void setArtTypeArticle(String newArtTypeArticle) {
        artTypeArticle = newArtTypeArticle;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_TYPE_ARTICLE] = 1;
    }

    public void setArtDtOkNomenclature(Timestamp newArtDtOkNomenclature) {
        artDtOkNomenclature = newArtDtOkNomenclature;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_DT_OK_NOMENCLATURE] = 1;
    }

    public void setArtArgQualite(Integer newArtArgQualite) {
        artArgQualite = newArtArgQualite;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_ARG_QUALITE] = 1;
    }

    public void setArtCenNiv(Integer newArtCenNiv) {
        artCenNiv = newArtCenNiv;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_CEN_NIV] = 1;
    }

    public void setArtPcrCodigoEntrep(Integer newArtPcrCodigoEntrep) {
        artPcrCodigoEntrep = newArtPcrCodigoEntrep;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_PCR_CODIGO_ENTREP] = 1;
    }

    public void setArtEpaisseurCintre(Double newArtEpaisseurCintre) {
        artEpaisseurCintre = newArtEpaisseurCintre;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_EPAISSEUR_CINTRE] = 1;
    }

    public void setArtTypeEclatement(String newArtTypeEclatement) {
        artTypeEclatement = newArtTypeEclatement;
        if (persist > 0)
            updCol[DoXnArtDesc.ART_TYPE_ECLATEMENT] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoXnArtDesc.ART_CODE)
            return artCode;
        else if (numCol == DoXnArtDesc.ART_VAR1)
            return artVar1;
        else if (numCol == DoXnArtDesc.ART_VAR2)
            return artVar2;
        else if (numCol == DoXnArtDesc.ART_VAR3)
            return artVar3;
        else if (numCol == DoXnArtDesc.ART_MOD_CODE)
            return artModCode;
        else if (numCol == DoXnArtDesc.ART_MOC_MOD_CODE)
            return artMocModCode;
        else if (numCol == DoXnArtDesc.ART_MOC_MOD_VAR1)
            return artMocModVar1;
        else if (numCol == DoXnArtDesc.ART_CAA_CODE)
            return artCaaCode;
        else if (numCol == DoXnArtDesc.ART_FOU_CODE)
            return artFouCode;
        else if (numCol == DoXnArtDesc.ART_SFA_CODE)
            return artSfaCode;
        else if (numCol == DoXnArtDesc.ART_TXP_CODE)
            return artTxpCode;
        else if (numCol == DoXnArtDesc.ART_MES_CODE)
            return artMesCode;
        else if (numCol == DoXnArtDesc.ART_FAA_CODE)
            return artFaaCode;
        else if (numCol == DoXnArtDesc.ART_MES_CODE_ACH)
            return artMesCodeAch;
        else if (numCol == DoXnArtDesc.ART_MES_CODE_VTE)
            return artMesCodeVte;
        else if (numCol == DoXnArtDesc.ART_PX_INIT)
            return artPxInit;
        else if (numCol == DoXnArtDesc.ART_TYA_CODE)
            return artTyaCode;
        else if (numCol == DoXnArtDesc.ART_DES1)
            return artDes1;
        else if (numCol == DoXnArtDesc.ART_DT_DER_CMDE)
            return artDtDerCmde;
        else if (numCol == DoXnArtDesc.ART_TVA_CODE)
            return artTvaCode;
        else if (numCol == DoXnArtDesc.ART_DECOMPOSE)
            return artDecompose;
        else if (numCol == DoXnArtDesc.ART_DEP_SOC_CODE)
            return artDepSocCode;
        else if (numCol == DoXnArtDesc.ART_PAY_CODE)
            return artPayCode;
        else if (numCol == DoXnArtDesc.ART_DEP_CODE)
            return artDepCode;
        else if (numCol == DoXnArtDesc.ART_ART_CODE)
            return artArtCode;
        else if (numCol == DoXnArtDesc.ART_ART_VAR1)
            return artArtVar1;
        else if (numCol == DoXnArtDesc.ART_ART_VAR2)
            return artArtVar2;
        else if (numCol == DoXnArtDesc.ART_ART_VAR3)
            return artArtVar3;
        else if (numCol == DoXnArtDesc.ART_DES2)
            return artDes2;
        else if (numCol == DoXnArtDesc.ART_DT_CREAT)
            return artDtCreat;
        else if (numCol == DoXnArtDesc.ART_DT_MAJ)
            return artDtMaj;
        else if (numCol == DoXnArtDesc.ART_UTIL_MAJ)
            return artUtilMaj;
        else if (numCol == DoXnArtDesc.ART_CAL_COMP_ON)
            return artCalCompOn;
        else if (numCol == DoXnArtDesc.ART_SUB_TYPE)
            return artSubType;
        else if (numCol == DoXnArtDesc.ART_VOL)
            return artVol;
        else if (numCol == DoXnArtDesc.ART_UNITE_PX_VTE)
            return artUnitePxVte;
        else if (numCol == DoXnArtDesc.ART_TYPE_ARRONDI)
            return artTypeArrondi;
        else if (numCol == DoXnArtDesc.ART_STOCK)
            return artStock;
        else if (numCol == DoXnArtDesc.ART_SPCB)
            return artSpcb;
        else if (numCol == DoXnArtDesc.ART_SERIE_LOT)
            return artSerieLot;
        else if (numCol == DoXnArtDesc.ART_REF_EMPLAC)
            return artRefEmplac;
        else if (numCol == DoXnArtDesc.ART_REF_CATAL)
            return artRefCatal;
        else if (numCol == DoXnArtDesc.ART_QTE_MIN_VTE)
            return artQteMinVte;
        else if (numCol == DoXnArtDesc.ART_DER_PX_ACHAT)
            return artDerPxAchat;
        else if (numCol == DoXnArtDesc.ART_CPTE_VTE)
            return artCpteVte;
        else if (numCol == DoXnArtDesc.ART_CEE_COEF)
            return artCeeCoef;
        else if (numCol == DoXnArtDesc.ART_CEE_NOMEN)
            return artCeeNomen;
        else if (numCol == DoXnArtDesc.ART_COEF_ACH_VTE)
            return artCoefAchVte;
        else if (numCol == DoXnArtDesc.ART_COEF_UN_ACH_STK)
            return artCoefUnAchStk;
        else if (numCol == DoXnArtDesc.ART_COEF_UN_STK_VTE)
            return artCoefUnStkVte;
        else if (numCol == DoXnArtDesc.ART_COND_STK)
            return artCondStk;
        else if (numCol == DoXnArtDesc.ART_PX_VTE_MP)
            return artPxVteMp;
        else if (numCol == DoXnArtDesc.ART_PX_REVIENT_MP)
            return artPxRevientMp;
        else if (numCol == DoXnArtDesc.ART_PX_REVIENT)
            return artPxRevient;
        else if (numCol == DoXnArtDesc.ART_PX_MINI)
            return artPxMini;
        else if (numCol == DoXnArtDesc.ART_PX_MAXI)
            return artPxMaxi;
        else if (numCol == DoXnArtDesc.ART_PX_ACH_MP)
            return artPxAchMp;
        else if (numCol == DoXnArtDesc.ART_PDS)
            return artPds;
        else if (numCol == DoXnArtDesc.ART_PCB)
            return artPcb;
        else if (numCol == DoXnArtDesc.ART_OBS)
            return artObs;
        else if (numCol == DoXnArtDesc.ART_NET_ON)
            return artNetOn;
        else if (numCol == DoXnArtDesc.ART_GENCOD)
            return artGencod;
        else if (numCol == DoXnArtDesc.ART_ETIQ_ON)
            return artEtiqOn;
        else if (numCol == DoXnArtDesc.ART_ETAT)
            return artEtat;
        else if (numCol == DoXnArtDesc.ART_DT_LIM_CMDE_CLI)
            return artDtLimCmdeCli;
        else if (numCol == DoXnArtDesc.ART_DT_DER_ACHAT)
            return artDtDerAchat;
        else if (numCol == DoXnArtDesc.ART_DER_PX_VTE)
            return artDerPxVte;
        else if (numCol == DoXnArtDesc.ART_COND_VTE)
            return artCondVte;
        else if (numCol == DoXnArtDesc.ART_CPTE_ACH)
            return artCpteAch;
        else if (numCol == DoXnArtDesc.ART_CPTE_ANAL_ACH)
            return artCpteAnalAch;
        else if (numCol == DoXnArtDesc.ART_CPTE_ANAL_VTE)
            return artCpteAnalVte;
        else if (numCol == DoXnArtDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoXnArtDesc.ART_GARANTIE)
            return artGarantie;
        else if (numCol == DoXnArtDesc.ART_HAUT)
            return artHaut;
        else if (numCol == DoXnArtDesc.ART_MES_CODE_STK)
            return artMesCodeStk;
        else if (numCol == DoXnArtDesc.ART_ARRONDIS_STK)
            return artArrondisStk;
        else if (numCol == DoXnArtDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoXnArtDesc.ART_CONTRE_MARQUE_ON)
            return artContreMarqueOn;
        else if (numCol == DoXnArtDesc.ART_DES_COURT)
            return artDesCourt;
        else if (numCol == DoXnArtDesc.ART_NB_COLIS)
            return artNbColis;
        else if (numCol == DoXnArtDesc.ART_MAG_CODE)
            return artMagCode;
        else if (numCol == DoXnArtDesc.ART_ROT_CLASS)
            return artRotClass;
        else if (numCol == DoXnArtDesc.ART_ROT_JOUR)
            return artRotJour;
        else if (numCol == DoXnArtDesc.ART_STE_ON)
            return artSteOn;
        else if (numCol == DoXnArtDesc.ART_MES_CODE_STAT)
            return artMesCodeStat;
        else if (numCol == DoXnArtDesc.ART_COEF_UN_VTE_STAT)
            return artCoefUnVteStat;
        else if (numCol == DoXnArtDesc.ART_LONG)
            return artLong;
        else if (numCol == DoXnArtDesc.ART_LARG)
            return artLarg;
        else if (numCol == DoXnArtDesc.ART_PDS_NET)
            return artPdsNet;
        else if (numCol == DoXnArtDesc.ART_CST_CODE)
            return artCstCode;
        else if (numCol == DoXnArtDesc.ART_ZMG_CODE)
            return artZmgCode;
        else if (numCol == DoXnArtDesc.ART_FRAIS)
            return artFrais;
        else if (numCol == DoXnArtDesc.ART_QTE_TYPE)
            return artQteType;
        else if (numCol == DoXnArtDesc.ART_ARG_PERDIDAS)
            return artArgPerdidas;
        else if (numCol == DoXnArtDesc.ART_ARG_CATALOGO)
            return artArgCatalogo;
        else if (numCol == DoXnArtDesc.ART_ARG_LOTE_MINIMO)
            return artArgLoteMinimo;
        else if (numCol == DoXnArtDesc.ART_ARG_PCR_CODIGO)
            return artArgPcrCodigo;
        else if (numCol == DoXnArtDesc.ART_ARG_PCR_REVISION)
            return artArgPcrRevision;
        else if (numCol == DoXnArtDesc.ART_ARG_CONTROL_REC)
            return artArgControlRec;
        else if (numCol == DoXnArtDesc.ART_ARG_MODO_COMPRA)
            return artArgModoCompra;
        else if (numCol == DoXnArtDesc.ART_ARG_PLAZO_FABR)
            return artArgPlazoFabr;
        else if (numCol == DoXnArtDesc.ART_ARG_FABRICADO)
            return artArgFabricado;
        else if (numCol == DoXnArtDesc.ART_TYPE_ARTICLE)
            return artTypeArticle;
        else if (numCol == DoXnArtDesc.ART_DT_OK_NOMENCLATURE)
            return artDtOkNomenclature;
        else if (numCol == DoXnArtDesc.ART_ARG_QUALITE)
            return artArgQualite;
        else if (numCol == DoXnArtDesc.ART_CEN_NIV)
            return artCenNiv;
        else if (numCol == DoXnArtDesc.ART_PCR_CODIGO_ENTREP)
            return artPcrCodigoEntrep;
        else if (numCol == DoXnArtDesc.ART_EPAISSEUR_CINTRE)
            return artEpaisseurCintre;
        else if (numCol == DoXnArtDesc.ART_TYPE_ECLATEMENT)
            return artTypeEclatement;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoXnArtDesc.ART_CODE) {
            artCode = (String) value;
        }
        if (numCol == DoXnArtDesc.ART_VAR1) {
            artVar1 = (String) value;
        }
        if (numCol == DoXnArtDesc.ART_VAR2) {
            artVar2 = (String) value;
        }
        if (numCol == DoXnArtDesc.ART_VAR3) {
            artVar3 = (String) value;
        }
        if (numCol == DoXnArtDesc.ART_MOD_CODE) {
            artModCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MOC_MOD_CODE) {
            artMocModCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MOC_MOD_VAR1) {
            artMocModVar1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CAA_CODE) {
            artCaaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_FOU_CODE) {
            artFouCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_SFA_CODE) {
            artSfaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_TXP_CODE) {
            artTxpCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MES_CODE) {
            artMesCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_FAA_CODE) {
            artFaaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MES_CODE_ACH) {
            artMesCodeAch = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MES_CODE_VTE) {
            artMesCodeVte = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_INIT) {
            artPxInit = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_TYA_CODE) {
            artTyaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DES1) {
            artDes1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DT_DER_CMDE) {
            artDtDerCmde = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_TVA_CODE) {
            artTvaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DECOMPOSE) {
            artDecompose = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DEP_SOC_CODE) {
            artDepSocCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PAY_CODE) {
            artPayCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DEP_CODE) {
            artDepCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ART_CODE) {
            artArtCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ART_VAR1) {
            artArtVar1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ART_VAR2) {
            artArtVar2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ART_VAR3) {
            artArtVar3 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DES2) {
            artDes2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DT_CREAT) {
            artDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DT_MAJ) {
            artDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_UTIL_MAJ) {
            artUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CAL_COMP_ON) {
            artCalCompOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_SUB_TYPE) {
            artSubType = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_VOL) {
            artVol = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_UNITE_PX_VTE) {
            artUnitePxVte = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_TYPE_ARRONDI) {
            artTypeArrondi = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_STOCK) {
            artStock = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_SPCB) {
            artSpcb = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_SERIE_LOT) {
            artSerieLot = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_REF_EMPLAC) {
            artRefEmplac = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_REF_CATAL) {
            artRefCatal = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_QTE_MIN_VTE) {
            artQteMinVte = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DER_PX_ACHAT) {
            artDerPxAchat = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CPTE_VTE) {
            artCpteVte = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CEE_COEF) {
            artCeeCoef = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CEE_NOMEN) {
            artCeeNomen = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_COEF_ACH_VTE) {
            artCoefAchVte = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_COEF_UN_ACH_STK) {
            artCoefUnAchStk = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_COEF_UN_STK_VTE) {
            artCoefUnStkVte = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_COND_STK) {
            artCondStk = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_VTE_MP) {
            artPxVteMp = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_REVIENT_MP) {
            artPxRevientMp = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_REVIENT) {
            artPxRevient = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_MINI) {
            artPxMini = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_MAXI) {
            artPxMaxi = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PX_ACH_MP) {
            artPxAchMp = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PDS) {
            artPds = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PCB) {
            artPcb = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_OBS) {
            artObs = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_NET_ON) {
            artNetOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_GENCOD) {
            artGencod = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ETIQ_ON) {
            artEtiqOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ETAT) {
            artEtat = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DT_LIM_CMDE_CLI) {
            artDtLimCmdeCli = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DT_DER_ACHAT) {
            artDtDerAchat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DER_PX_VTE) {
            artDerPxVte = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_COND_VTE) {
            artCondVte = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CPTE_ACH) {
            artCpteAch = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CPTE_ANAL_ACH) {
            artCpteAnalAch = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CPTE_ANAL_VTE) {
            artCpteAnalVte = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_GARANTIE) {
            artGarantie = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_HAUT) {
            artHaut = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MES_CODE_STK) {
            artMesCodeStk = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARRONDIS_STK) {
            artArrondisStk = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CONTRE_MARQUE_ON) {
            artContreMarqueOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DES_COURT) {
            artDesCourt = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_NB_COLIS) {
            artNbColis = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MAG_CODE) {
            artMagCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ROT_CLASS) {
            artRotClass = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ROT_JOUR) {
            artRotJour = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_STE_ON) {
            artSteOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_MES_CODE_STAT) {
            artMesCodeStat = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_COEF_UN_VTE_STAT) {
            artCoefUnVteStat = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_LONG) {
            artLong = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_LARG) {
            artLarg = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PDS_NET) {
            artPdsNet = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CST_CODE) {
            artCstCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ZMG_CODE) {
            artZmgCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_FRAIS) {
            artFrais = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_QTE_TYPE) {
            artQteType = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_PERDIDAS) {
            artArgPerdidas = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_CATALOGO) {
            artArgCatalogo = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_LOTE_MINIMO) {
            artArgLoteMinimo = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_PCR_CODIGO) {
            artArgPcrCodigo = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_PCR_REVISION) {
            artArgPcrRevision = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_CONTROL_REC) {
            artArgControlRec = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_MODO_COMPRA) {
            artArgModoCompra = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_PLAZO_FABR) {
            artArgPlazoFabr = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_FABRICADO) {
            artArgFabricado = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_TYPE_ARTICLE) {
            artTypeArticle = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_DT_OK_NOMENCLATURE) {
            artDtOkNomenclature = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_ARG_QUALITE) {
            artArgQualite = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_CEN_NIV) {
            artCenNiv = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_PCR_CODIGO_ENTREP) {
            artPcrCodigoEntrep = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_EPAISSEUR_CINTRE) {
            artEpaisseurCintre = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnArtDesc.ART_TYPE_ECLATEMENT) {
            artTypeEclatement = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoXnArt());
    }

    private DataObject setProperty(SqlArg sqlArg, DoXnArt djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoXnArtDesc.ART_CODE] != -1) {
            djo.artCode = rs.getString(val[DoXnArtDesc.ART_CODE]);
        }
        if (val[DoXnArtDesc.ART_VAR1] != -1) {
            djo.artVar1 = rs.getString(val[DoXnArtDesc.ART_VAR1]);
        }
        if (val[DoXnArtDesc.ART_VAR2] != -1) {
            djo.artVar2 = rs.getString(val[DoXnArtDesc.ART_VAR2]);
        }
        if (val[DoXnArtDesc.ART_VAR3] != -1) {
            djo.artVar3 = rs.getString(val[DoXnArtDesc.ART_VAR3]);
        }
        if (val[DoXnArtDesc.ART_MOD_CODE] != -1) {
            djo.artModCode = rs.getString(val[DoXnArtDesc.ART_MOD_CODE]);
        }
        if (val[DoXnArtDesc.ART_MOC_MOD_CODE] != -1) {
            djo.artMocModCode = rs.getString(val[DoXnArtDesc.ART_MOC_MOD_CODE]);
        }
        if (val[DoXnArtDesc.ART_MOC_MOD_VAR1] != -1) {
            djo.artMocModVar1 = rs.getString(val[DoXnArtDesc.ART_MOC_MOD_VAR1]);
        }
        if (val[DoXnArtDesc.ART_CAA_CODE] != -1) {
            djo.artCaaCode = rs.getString(val[DoXnArtDesc.ART_CAA_CODE]);
        }
        if (val[DoXnArtDesc.ART_FOU_CODE] != -1) {
            djo.artFouCode = rs.getString(val[DoXnArtDesc.ART_FOU_CODE]);
        }
        if (val[DoXnArtDesc.ART_SFA_CODE] != -1) {
            djo.artSfaCode = rs.getString(val[DoXnArtDesc.ART_SFA_CODE]);
        }
        if (val[DoXnArtDesc.ART_TXP_CODE] != -1) {
            djo.artTxpCode = rs.getString(val[DoXnArtDesc.ART_TXP_CODE]);
        }
        if (val[DoXnArtDesc.ART_MES_CODE] != -1) {
            djo.artMesCode = rs.getString(val[DoXnArtDesc.ART_MES_CODE]);
        }
        if (val[DoXnArtDesc.ART_FAA_CODE] != -1) {
            djo.artFaaCode = rs.getString(val[DoXnArtDesc.ART_FAA_CODE]);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_ACH] != -1) {
            djo.artMesCodeAch = rs.getString(val[DoXnArtDesc.ART_MES_CODE_ACH]);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_VTE] != -1) {
            djo.artMesCodeVte = rs.getString(val[DoXnArtDesc.ART_MES_CODE_VTE]);
        }
        if (val[DoXnArtDesc.ART_PX_INIT] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_INIT]);
            if (!rs.wasNull())
                djo.artPxInit = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_TYA_CODE] != -1) {
            djo.artTyaCode = rs.getString(val[DoXnArtDesc.ART_TYA_CODE]);
        }
        if (val[DoXnArtDesc.ART_DES1] != -1) {
            djo.artDes1 = rs.getString(val[DoXnArtDesc.ART_DES1]);
        }
        if (val[DoXnArtDesc.ART_DT_DER_CMDE] != -1) {
            djo.artDtDerCmde = rs.getTimestamp(val[DoXnArtDesc.ART_DT_DER_CMDE]);
        }
        if (val[DoXnArtDesc.ART_TVA_CODE] != -1) {
            djo.artTvaCode = rs.getString(val[DoXnArtDesc.ART_TVA_CODE]);
        }
        if (val[DoXnArtDesc.ART_DECOMPOSE] != -1) {
            djo.artDecompose = rs.getString(val[DoXnArtDesc.ART_DECOMPOSE]);
        }
        if (val[DoXnArtDesc.ART_DEP_SOC_CODE] != -1) {
            djo.artDepSocCode = rs.getString(val[DoXnArtDesc.ART_DEP_SOC_CODE]);
        }
        if (val[DoXnArtDesc.ART_PAY_CODE] != -1) {
            djo.artPayCode = rs.getString(val[DoXnArtDesc.ART_PAY_CODE]);
        }
        if (val[DoXnArtDesc.ART_DEP_CODE] != -1) {
            djo.artDepCode = rs.getString(val[DoXnArtDesc.ART_DEP_CODE]);
        }
        if (val[DoXnArtDesc.ART_ART_CODE] != -1) {
            djo.artArtCode = rs.getString(val[DoXnArtDesc.ART_ART_CODE]);
        }
        if (val[DoXnArtDesc.ART_ART_VAR1] != -1) {
            djo.artArtVar1 = rs.getString(val[DoXnArtDesc.ART_ART_VAR1]);
        }
        if (val[DoXnArtDesc.ART_ART_VAR2] != -1) {
            djo.artArtVar2 = rs.getString(val[DoXnArtDesc.ART_ART_VAR2]);
        }
        if (val[DoXnArtDesc.ART_ART_VAR3] != -1) {
            djo.artArtVar3 = rs.getString(val[DoXnArtDesc.ART_ART_VAR3]);
        }
        if (val[DoXnArtDesc.ART_DES2] != -1) {
            djo.artDes2 = rs.getString(val[DoXnArtDesc.ART_DES2]);
        }
        if (val[DoXnArtDesc.ART_DT_CREAT] != -1) {
            djo.artDtCreat = rs.getTimestamp(val[DoXnArtDesc.ART_DT_CREAT]);
        }
        if (val[DoXnArtDesc.ART_DT_MAJ] != -1) {
            djo.artDtMaj = rs.getTimestamp(val[DoXnArtDesc.ART_DT_MAJ]);
        }
        if (val[DoXnArtDesc.ART_UTIL_MAJ] != -1) {
            djo.artUtilMaj = rs.getString(val[DoXnArtDesc.ART_UTIL_MAJ]);
        }
        if (val[DoXnArtDesc.ART_CAL_COMP_ON] != -1) {
            djo.artCalCompOn = rs.getString(val[DoXnArtDesc.ART_CAL_COMP_ON]);
        }
        if (val[DoXnArtDesc.ART_SUB_TYPE] != -1) {
            djo.artSubType = rs.getString(val[DoXnArtDesc.ART_SUB_TYPE]);
        }
        if (val[DoXnArtDesc.ART_VOL] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_VOL]);
            if (!rs.wasNull())
                djo.artVol = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_UNITE_PX_VTE] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_UNITE_PX_VTE]);
            if (!rs.wasNull())
                djo.artUnitePxVte = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_TYPE_ARRONDI] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_TYPE_ARRONDI]);
            if (!rs.wasNull())
                djo.artTypeArrondi = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_STOCK] != -1) {
            djo.artStock = rs.getString(val[DoXnArtDesc.ART_STOCK]);
        }
        if (val[DoXnArtDesc.ART_SPCB] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_SPCB]);
            if (!rs.wasNull())
                djo.artSpcb = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_SERIE_LOT] != -1) {
            djo.artSerieLot = rs.getString(val[DoXnArtDesc.ART_SERIE_LOT]);
        }
        if (val[DoXnArtDesc.ART_REF_EMPLAC] != -1) {
            djo.artRefEmplac = rs.getString(val[DoXnArtDesc.ART_REF_EMPLAC]);
        }
        if (val[DoXnArtDesc.ART_REF_CATAL] != -1) {
            djo.artRefCatal = rs.getString(val[DoXnArtDesc.ART_REF_CATAL]);
        }
        if (val[DoXnArtDesc.ART_QTE_MIN_VTE] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_QTE_MIN_VTE]);
            if (!rs.wasNull())
                djo.artQteMinVte = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_DER_PX_ACHAT] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_DER_PX_ACHAT]);
            if (!rs.wasNull())
                djo.artDerPxAchat = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_CPTE_VTE] != -1) {
            djo.artCpteVte = rs.getString(val[DoXnArtDesc.ART_CPTE_VTE]);
        }
        if (val[DoXnArtDesc.ART_CEE_COEF] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_CEE_COEF]);
            if (!rs.wasNull())
                djo.artCeeCoef = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_CEE_NOMEN] != -1) {
            djo.artCeeNomen = rs.getString(val[DoXnArtDesc.ART_CEE_NOMEN]);
        }
        if (val[DoXnArtDesc.ART_COEF_ACH_VTE] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_COEF_ACH_VTE]);
            if (!rs.wasNull())
                djo.artCoefAchVte = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_COEF_UN_ACH_STK] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_COEF_UN_ACH_STK]);
            if (!rs.wasNull())
                djo.artCoefUnAchStk = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_COEF_UN_STK_VTE] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_COEF_UN_STK_VTE]);
            if (!rs.wasNull())
                djo.artCoefUnStkVte = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_COND_STK] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_COND_STK]);
            if (!rs.wasNull())
                djo.artCondStk = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_PX_VTE_MP] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_VTE_MP]);
            if (!rs.wasNull())
                djo.artPxVteMp = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PX_REVIENT_MP] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_REVIENT_MP]);
            if (!rs.wasNull())
                djo.artPxRevientMp = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PX_REVIENT] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_REVIENT]);
            if (!rs.wasNull())
                djo.artPxRevient = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PX_MINI] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_MINI]);
            if (!rs.wasNull())
                djo.artPxMini = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PX_MAXI] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_MAXI]);
            if (!rs.wasNull())
                djo.artPxMaxi = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PX_ACH_MP] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PX_ACH_MP]);
            if (!rs.wasNull())
                djo.artPxAchMp = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PDS] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PDS]);
            if (!rs.wasNull())
                djo.artPds = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PCB] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_PCB]);
            if (!rs.wasNull())
                djo.artPcb = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_OBS] != -1) {
            djo.artObs = rs.getString(val[DoXnArtDesc.ART_OBS]);
        }
        if (val[DoXnArtDesc.ART_NET_ON] != -1) {
            djo.artNetOn = rs.getString(val[DoXnArtDesc.ART_NET_ON]);
        }
        if (val[DoXnArtDesc.ART_GENCOD] != -1) {
            djo.artGencod = rs.getString(val[DoXnArtDesc.ART_GENCOD]);
        }
        if (val[DoXnArtDesc.ART_ETIQ_ON] != -1) {
            djo.artEtiqOn = rs.getString(val[DoXnArtDesc.ART_ETIQ_ON]);
        }
        if (val[DoXnArtDesc.ART_ETAT] != -1) {
            djo.artEtat = rs.getString(val[DoXnArtDesc.ART_ETAT]);
        }
        if (val[DoXnArtDesc.ART_DT_LIM_CMDE_CLI] != -1) {
            djo.artDtLimCmdeCli = rs.getTimestamp(val[DoXnArtDesc.ART_DT_LIM_CMDE_CLI]);
        }
        if (val[DoXnArtDesc.ART_DT_DER_ACHAT] != -1) {
            djo.artDtDerAchat = rs.getTimestamp(val[DoXnArtDesc.ART_DT_DER_ACHAT]);
        }
        if (val[DoXnArtDesc.ART_DER_PX_VTE] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_DER_PX_VTE]);
            if (!rs.wasNull())
                djo.artDerPxVte = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_COND_VTE] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_COND_VTE]);
            if (!rs.wasNull())
                djo.artCondVte = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_CPTE_ACH] != -1) {
            djo.artCpteAch = rs.getString(val[DoXnArtDesc.ART_CPTE_ACH]);
        }
        if (val[DoXnArtDesc.ART_CPTE_ANAL_ACH] != -1) {
            djo.artCpteAnalAch = rs.getString(val[DoXnArtDesc.ART_CPTE_ANAL_ACH]);
        }
        if (val[DoXnArtDesc.ART_CPTE_ANAL_VTE] != -1) {
            djo.artCpteAnalVte = rs.getString(val[DoXnArtDesc.ART_CPTE_ANAL_VTE]);
        }
        if (val[DoXnArtDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_GARANTIE] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_GARANTIE]);
            if (!rs.wasNull())
                djo.artGarantie = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_HAUT] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_HAUT]);
            if (!rs.wasNull())
                djo.artHaut = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_STK] != -1) {
            djo.artMesCodeStk = rs.getString(val[DoXnArtDesc.ART_MES_CODE_STK]);
        }
        if (val[DoXnArtDesc.ART_ARRONDIS_STK] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_ARRONDIS_STK]);
            if (!rs.wasNull())
                djo.artArrondisStk = new Double(temp);
        }
        if (val[DoXnArtDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoXnArtDesc.Z_STATUS]);
        }
        if (val[DoXnArtDesc.ART_CONTRE_MARQUE_ON] != -1) {
            djo.artContreMarqueOn = rs.getString(val[DoXnArtDesc.ART_CONTRE_MARQUE_ON]);
        }
        if (val[DoXnArtDesc.ART_DES_COURT] != -1) {
            djo.artDesCourt = rs.getString(val[DoXnArtDesc.ART_DES_COURT]);
        }
        if (val[DoXnArtDesc.ART_NB_COLIS] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_NB_COLIS]);
            if (!rs.wasNull())
                djo.artNbColis = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_MAG_CODE] != -1) {
            djo.artMagCode = rs.getString(val[DoXnArtDesc.ART_MAG_CODE]);
        }
        if (val[DoXnArtDesc.ART_ROT_CLASS] != -1) {
            djo.artRotClass = rs.getString(val[DoXnArtDesc.ART_ROT_CLASS]);
        }
        if (val[DoXnArtDesc.ART_ROT_JOUR] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_ROT_JOUR]);
            if (!rs.wasNull())
                djo.artRotJour = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_STE_ON] != -1) {
            djo.artSteOn = rs.getString(val[DoXnArtDesc.ART_STE_ON]);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_STAT] != -1) {
            djo.artMesCodeStat = rs.getString(val[DoXnArtDesc.ART_MES_CODE_STAT]);
        }
        if (val[DoXnArtDesc.ART_COEF_UN_VTE_STAT] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_COEF_UN_VTE_STAT]);
            if (!rs.wasNull())
                djo.artCoefUnVteStat = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_LONG] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_LONG]);
            if (!rs.wasNull())
                djo.artLong = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_LARG] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_LARG]);
            if (!rs.wasNull())
                djo.artLarg = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_PDS_NET] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_PDS_NET]);
            if (!rs.wasNull())
                djo.artPdsNet = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_CST_CODE] != -1) {
            djo.artCstCode = rs.getString(val[DoXnArtDesc.ART_CST_CODE]);
        }
        if (val[DoXnArtDesc.ART_ZMG_CODE] != -1) {
            djo.artZmgCode = rs.getString(val[DoXnArtDesc.ART_ZMG_CODE]);
        }
        if (val[DoXnArtDesc.ART_FRAIS] != -1) {
            djo.artFrais = rs.getString(val[DoXnArtDesc.ART_FRAIS]);
        }
        if (val[DoXnArtDesc.ART_QTE_TYPE] != -1) {
            djo.artQteType = rs.getString(val[DoXnArtDesc.ART_QTE_TYPE]);
        }
        if (val[DoXnArtDesc.ART_ARG_PERDIDAS] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_ARG_PERDIDAS]);
            if (!rs.wasNull())
                djo.artArgPerdidas = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_ARG_CATALOGO] != -1) {
            djo.artArgCatalogo = rs.getString(val[DoXnArtDesc.ART_ARG_CATALOGO]);
        }
        if (val[DoXnArtDesc.ART_ARG_LOTE_MINIMO] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_ARG_LOTE_MINIMO]);
            if (!rs.wasNull())
                djo.artArgLoteMinimo = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_ARG_PCR_CODIGO] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_ARG_PCR_CODIGO]);
            if (!rs.wasNull())
                djo.artArgPcrCodigo = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_ARG_PCR_REVISION] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_ARG_PCR_REVISION]);
            if (!rs.wasNull())
                djo.artArgPcrRevision = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_ARG_CONTROL_REC] != -1) {
            djo.artArgControlRec = rs.getString(val[DoXnArtDesc.ART_ARG_CONTROL_REC]);
        }
        if (val[DoXnArtDesc.ART_ARG_MODO_COMPRA] != -1) {
            djo.artArgModoCompra = rs.getString(val[DoXnArtDesc.ART_ARG_MODO_COMPRA]);
        }
        if (val[DoXnArtDesc.ART_ARG_PLAZO_FABR] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_ARG_PLAZO_FABR]);
            if (!rs.wasNull())
                djo.artArgPlazoFabr = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_ARG_FABRICADO] != -1) {
            djo.artArgFabricado = rs.getString(val[DoXnArtDesc.ART_ARG_FABRICADO]);
        }
        if (val[DoXnArtDesc.ART_TYPE_ARTICLE] != -1) {
            djo.artTypeArticle = rs.getString(val[DoXnArtDesc.ART_TYPE_ARTICLE]);
        }
        if (val[DoXnArtDesc.ART_DT_OK_NOMENCLATURE] != -1) {
            djo.artDtOkNomenclature = rs.getTimestamp(val[DoXnArtDesc.ART_DT_OK_NOMENCLATURE]);
        }
        if (val[DoXnArtDesc.ART_ARG_QUALITE] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_ARG_QUALITE]);
            if (!rs.wasNull())
                djo.artArgQualite = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_CEN_NIV] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_CEN_NIV]);
            if (!rs.wasNull())
                djo.artCenNiv = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_PCR_CODIGO_ENTREP] != -1) {
            int temp = rs.getInt(val[DoXnArtDesc.ART_PCR_CODIGO_ENTREP]);
            if (!rs.wasNull())
                djo.artPcrCodigoEntrep = new Integer(temp);
        }
        if (val[DoXnArtDesc.ART_EPAISSEUR_CINTRE] != -1) {
            double temp = rs.getDouble(val[DoXnArtDesc.ART_EPAISSEUR_CINTRE]);
            if (!rs.wasNull())
                djo.artEpaisseurCintre = new Double(temp);
        }
        if (val[DoXnArtDesc.ART_TYPE_ECLATEMENT] != -1) {
            djo.artTypeEclatement = rs.getString(val[DoXnArtDesc.ART_TYPE_ECLATEMENT]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoXnArtDesc.ART_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CODE], artCode);
        }
        if (val[DoXnArtDesc.ART_VAR1] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_VAR1], artVar1);
        }
        if (val[DoXnArtDesc.ART_VAR2] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_VAR2], artVar2);
        }
        if (val[DoXnArtDesc.ART_VAR3] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_VAR3], artVar3);
        }
        if (val[DoXnArtDesc.ART_MOD_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MOD_CODE], artModCode);
        }
        if (val[DoXnArtDesc.ART_MOC_MOD_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MOC_MOD_CODE], artMocModCode);
        }
        if (val[DoXnArtDesc.ART_MOC_MOD_VAR1] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MOC_MOD_VAR1], artMocModVar1);
        }
        if (val[DoXnArtDesc.ART_CAA_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CAA_CODE], artCaaCode);
        }
        if (val[DoXnArtDesc.ART_FOU_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_FOU_CODE], artFouCode);
        }
        if (val[DoXnArtDesc.ART_SFA_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_SFA_CODE], artSfaCode);
        }
        if (val[DoXnArtDesc.ART_TXP_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_TXP_CODE], artTxpCode);
        }
        if (val[DoXnArtDesc.ART_MES_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MES_CODE], artMesCode);
        }
        if (val[DoXnArtDesc.ART_FAA_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_FAA_CODE], artFaaCode);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_ACH] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MES_CODE_ACH], artMesCodeAch);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_VTE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MES_CODE_VTE], artMesCodeVte);
        }
        if (val[DoXnArtDesc.ART_PX_INIT] > 0) {
            if (artPxInit == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_INIT], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_INIT], artPxInit.doubleValue());
        }
        if (val[DoXnArtDesc.ART_TYA_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_TYA_CODE], artTyaCode);
        }
        if (val[DoXnArtDesc.ART_DES1] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_DES1], artDes1);
        }
        if (val[DoXnArtDesc.ART_DT_DER_CMDE] > 0) {
            stmt.setTimestamp(val[DoXnArtDesc.ART_DT_DER_CMDE], artDtDerCmde);
        }
        if (val[DoXnArtDesc.ART_TVA_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_TVA_CODE], artTvaCode);
        }
        if (val[DoXnArtDesc.ART_DECOMPOSE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_DECOMPOSE], artDecompose);
        }
        if (val[DoXnArtDesc.ART_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_DEP_SOC_CODE], artDepSocCode);
        }
        if (val[DoXnArtDesc.ART_PAY_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_PAY_CODE], artPayCode);
        }
        if (val[DoXnArtDesc.ART_DEP_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_DEP_CODE], artDepCode);
        }
        if (val[DoXnArtDesc.ART_ART_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ART_CODE], artArtCode);
        }
        if (val[DoXnArtDesc.ART_ART_VAR1] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ART_VAR1], artArtVar1);
        }
        if (val[DoXnArtDesc.ART_ART_VAR2] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ART_VAR2], artArtVar2);
        }
        if (val[DoXnArtDesc.ART_ART_VAR3] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ART_VAR3], artArtVar3);
        }
        if (val[DoXnArtDesc.ART_DES2] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_DES2], artDes2);
        }
        if (val[DoXnArtDesc.ART_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoXnArtDesc.ART_DT_CREAT], artDtCreat);
        }
        if (val[DoXnArtDesc.ART_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoXnArtDesc.ART_DT_MAJ], artDtMaj);
        }
        if (val[DoXnArtDesc.ART_UTIL_MAJ] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_UTIL_MAJ], artUtilMaj);
        }
        if (val[DoXnArtDesc.ART_CAL_COMP_ON] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CAL_COMP_ON], artCalCompOn);
        }
        if (val[DoXnArtDesc.ART_SUB_TYPE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_SUB_TYPE], artSubType);
        }
        if (val[DoXnArtDesc.ART_VOL] > 0) {
            if (artVol == null)
                stmt.setNull(val[DoXnArtDesc.ART_VOL], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_VOL], artVol.doubleValue());
        }
        if (val[DoXnArtDesc.ART_UNITE_PX_VTE] > 0) {
            if (artUnitePxVte == null)
                stmt.setNull(val[DoXnArtDesc.ART_UNITE_PX_VTE], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_UNITE_PX_VTE], artUnitePxVte.intValue());
        }
        if (val[DoXnArtDesc.ART_TYPE_ARRONDI] > 0) {
            if (artTypeArrondi == null)
                stmt.setNull(val[DoXnArtDesc.ART_TYPE_ARRONDI], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_TYPE_ARRONDI], artTypeArrondi.doubleValue());
        }
        if (val[DoXnArtDesc.ART_STOCK] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_STOCK], artStock);
        }
        if (val[DoXnArtDesc.ART_SPCB] > 0) {
            if (artSpcb == null)
                stmt.setNull(val[DoXnArtDesc.ART_SPCB], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_SPCB], artSpcb.intValue());
        }
        if (val[DoXnArtDesc.ART_SERIE_LOT] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_SERIE_LOT], artSerieLot);
        }
        if (val[DoXnArtDesc.ART_REF_EMPLAC] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_REF_EMPLAC], artRefEmplac);
        }
        if (val[DoXnArtDesc.ART_REF_CATAL] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_REF_CATAL], artRefCatal);
        }
        if (val[DoXnArtDesc.ART_QTE_MIN_VTE] > 0) {
            if (artQteMinVte == null)
                stmt.setNull(val[DoXnArtDesc.ART_QTE_MIN_VTE], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_QTE_MIN_VTE], artQteMinVte.doubleValue());
        }
        if (val[DoXnArtDesc.ART_DER_PX_ACHAT] > 0) {
            if (artDerPxAchat == null)
                stmt.setNull(val[DoXnArtDesc.ART_DER_PX_ACHAT], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_DER_PX_ACHAT], artDerPxAchat.doubleValue());
        }
        if (val[DoXnArtDesc.ART_CPTE_VTE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CPTE_VTE], artCpteVte);
        }
        if (val[DoXnArtDesc.ART_CEE_COEF] > 0) {
            if (artCeeCoef == null)
                stmt.setNull(val[DoXnArtDesc.ART_CEE_COEF], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_CEE_COEF], artCeeCoef.doubleValue());
        }
        if (val[DoXnArtDesc.ART_CEE_NOMEN] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CEE_NOMEN], artCeeNomen);
        }
        if (val[DoXnArtDesc.ART_COEF_ACH_VTE] > 0) {
            if (artCoefAchVte == null)
                stmt.setNull(val[DoXnArtDesc.ART_COEF_ACH_VTE], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_COEF_ACH_VTE], artCoefAchVte.doubleValue());
        }
        if (val[DoXnArtDesc.ART_COEF_UN_ACH_STK] > 0) {
            if (artCoefUnAchStk == null)
                stmt.setNull(val[DoXnArtDesc.ART_COEF_UN_ACH_STK], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_COEF_UN_ACH_STK], artCoefUnAchStk.doubleValue());
        }
        if (val[DoXnArtDesc.ART_COEF_UN_STK_VTE] > 0) {
            if (artCoefUnStkVte == null)
                stmt.setNull(val[DoXnArtDesc.ART_COEF_UN_STK_VTE], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_COEF_UN_STK_VTE], artCoefUnStkVte.doubleValue());
        }
        if (val[DoXnArtDesc.ART_COND_STK] > 0) {
            if (artCondStk == null)
                stmt.setNull(val[DoXnArtDesc.ART_COND_STK], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_COND_STK], artCondStk.intValue());
        }
        if (val[DoXnArtDesc.ART_PX_VTE_MP] > 0) {
            if (artPxVteMp == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_VTE_MP], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_VTE_MP], artPxVteMp.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PX_REVIENT_MP] > 0) {
            if (artPxRevientMp == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_REVIENT_MP], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_REVIENT_MP], artPxRevientMp.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PX_REVIENT] > 0) {
            if (artPxRevient == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_REVIENT], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_REVIENT], artPxRevient.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PX_MINI] > 0) {
            if (artPxMini == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_MINI], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_MINI], artPxMini.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PX_MAXI] > 0) {
            if (artPxMaxi == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_MAXI], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_MAXI], artPxMaxi.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PX_ACH_MP] > 0) {
            if (artPxAchMp == null)
                stmt.setNull(val[DoXnArtDesc.ART_PX_ACH_MP], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PX_ACH_MP], artPxAchMp.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PDS] > 0) {
            if (artPds == null)
                stmt.setNull(val[DoXnArtDesc.ART_PDS], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PDS], artPds.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PCB] > 0) {
            if (artPcb == null)
                stmt.setNull(val[DoXnArtDesc.ART_PCB], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_PCB], artPcb.intValue());
        }
        if (val[DoXnArtDesc.ART_OBS] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_OBS], artObs);
        }
        if (val[DoXnArtDesc.ART_NET_ON] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_NET_ON], artNetOn);
        }
        if (val[DoXnArtDesc.ART_GENCOD] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_GENCOD], artGencod);
        }
        if (val[DoXnArtDesc.ART_ETIQ_ON] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ETIQ_ON], artEtiqOn);
        }
        if (val[DoXnArtDesc.ART_ETAT] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ETAT], artEtat);
        }
        if (val[DoXnArtDesc.ART_DT_LIM_CMDE_CLI] > 0) {
            stmt.setTimestamp(val[DoXnArtDesc.ART_DT_LIM_CMDE_CLI], artDtLimCmdeCli);
        }
        if (val[DoXnArtDesc.ART_DT_DER_ACHAT] > 0) {
            stmt.setTimestamp(val[DoXnArtDesc.ART_DT_DER_ACHAT], artDtDerAchat);
        }
        if (val[DoXnArtDesc.ART_DER_PX_VTE] > 0) {
            if (artDerPxVte == null)
                stmt.setNull(val[DoXnArtDesc.ART_DER_PX_VTE], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_DER_PX_VTE], artDerPxVte.doubleValue());
        }
        if (val[DoXnArtDesc.ART_COND_VTE] > 0) {
            if (artCondVte == null)
                stmt.setNull(val[DoXnArtDesc.ART_COND_VTE], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_COND_VTE], artCondVte.intValue());
        }
        if (val[DoXnArtDesc.ART_CPTE_ACH] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CPTE_ACH], artCpteAch);
        }
        if (val[DoXnArtDesc.ART_CPTE_ANAL_ACH] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CPTE_ANAL_ACH], artCpteAnalAch);
        }
        if (val[DoXnArtDesc.ART_CPTE_ANAL_VTE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CPTE_ANAL_VTE], artCpteAnalVte);
        }
        if (val[DoXnArtDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoXnArtDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoXnArtDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoXnArtDesc.ART_GARANTIE] > 0) {
            if (artGarantie == null)
                stmt.setNull(val[DoXnArtDesc.ART_GARANTIE], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_GARANTIE], artGarantie.intValue());
        }
        if (val[DoXnArtDesc.ART_HAUT] > 0) {
            if (artHaut == null)
                stmt.setNull(val[DoXnArtDesc.ART_HAUT], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_HAUT], artHaut.doubleValue());
        }
        if (val[DoXnArtDesc.ART_MES_CODE_STK] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MES_CODE_STK], artMesCodeStk);
        }
        if (val[DoXnArtDesc.ART_ARRONDIS_STK] > 0) {
            if (artArrondisStk == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARRONDIS_STK], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_ARRONDIS_STK], artArrondisStk.doubleValue());
        }
        if (val[DoXnArtDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoXnArtDesc.Z_STATUS], zStatus);
        }
        if (val[DoXnArtDesc.ART_CONTRE_MARQUE_ON] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CONTRE_MARQUE_ON], artContreMarqueOn);
        }
        if (val[DoXnArtDesc.ART_DES_COURT] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_DES_COURT], artDesCourt);
        }
        if (val[DoXnArtDesc.ART_NB_COLIS] > 0) {
            if (artNbColis == null)
                stmt.setNull(val[DoXnArtDesc.ART_NB_COLIS], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_NB_COLIS], artNbColis.intValue());
        }
        if (val[DoXnArtDesc.ART_MAG_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MAG_CODE], artMagCode);
        }
        if (val[DoXnArtDesc.ART_ROT_CLASS] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ROT_CLASS], artRotClass);
        }
        if (val[DoXnArtDesc.ART_ROT_JOUR] > 0) {
            if (artRotJour == null)
                stmt.setNull(val[DoXnArtDesc.ART_ROT_JOUR], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_ROT_JOUR], artRotJour.intValue());
        }
        if (val[DoXnArtDesc.ART_STE_ON] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_STE_ON], artSteOn);
        }
        if (val[DoXnArtDesc.ART_MES_CODE_STAT] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_MES_CODE_STAT], artMesCodeStat);
        }
        if (val[DoXnArtDesc.ART_COEF_UN_VTE_STAT] > 0) {
            if (artCoefUnVteStat == null)
                stmt.setNull(val[DoXnArtDesc.ART_COEF_UN_VTE_STAT], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_COEF_UN_VTE_STAT], artCoefUnVteStat.doubleValue());
        }
        if (val[DoXnArtDesc.ART_LONG] > 0) {
            if (artLong == null)
                stmt.setNull(val[DoXnArtDesc.ART_LONG], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_LONG], artLong.doubleValue());
        }
        if (val[DoXnArtDesc.ART_LARG] > 0) {
            if (artLarg == null)
                stmt.setNull(val[DoXnArtDesc.ART_LARG], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_LARG], artLarg.doubleValue());
        }
        if (val[DoXnArtDesc.ART_PDS_NET] > 0) {
            if (artPdsNet == null)
                stmt.setNull(val[DoXnArtDesc.ART_PDS_NET], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_PDS_NET], artPdsNet.doubleValue());
        }
        if (val[DoXnArtDesc.ART_CST_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_CST_CODE], artCstCode);
        }
        if (val[DoXnArtDesc.ART_ZMG_CODE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ZMG_CODE], artZmgCode);
        }
        if (val[DoXnArtDesc.ART_FRAIS] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_FRAIS], artFrais);
        }
        if (val[DoXnArtDesc.ART_QTE_TYPE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_QTE_TYPE], artQteType);
        }
        if (val[DoXnArtDesc.ART_ARG_PERDIDAS] > 0) {
            if (artArgPerdidas == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARG_PERDIDAS], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_ARG_PERDIDAS], artArgPerdidas.doubleValue());
        }
        if (val[DoXnArtDesc.ART_ARG_CATALOGO] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ARG_CATALOGO], artArgCatalogo);
        }
        if (val[DoXnArtDesc.ART_ARG_LOTE_MINIMO] > 0) {
            if (artArgLoteMinimo == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARG_LOTE_MINIMO], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_ARG_LOTE_MINIMO], artArgLoteMinimo.intValue());
        }
        if (val[DoXnArtDesc.ART_ARG_PCR_CODIGO] > 0) {
            if (artArgPcrCodigo == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARG_PCR_CODIGO], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_ARG_PCR_CODIGO], artArgPcrCodigo.intValue());
        }
        if (val[DoXnArtDesc.ART_ARG_PCR_REVISION] > 0) {
            if (artArgPcrRevision == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARG_PCR_REVISION], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_ARG_PCR_REVISION], artArgPcrRevision.intValue());
        }
        if (val[DoXnArtDesc.ART_ARG_CONTROL_REC] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ARG_CONTROL_REC], artArgControlRec);
        }
        if (val[DoXnArtDesc.ART_ARG_MODO_COMPRA] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ARG_MODO_COMPRA], artArgModoCompra);
        }
        if (val[DoXnArtDesc.ART_ARG_PLAZO_FABR] > 0) {
            if (artArgPlazoFabr == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARG_PLAZO_FABR], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_ARG_PLAZO_FABR], artArgPlazoFabr.intValue());
        }
        if (val[DoXnArtDesc.ART_ARG_FABRICADO] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_ARG_FABRICADO], artArgFabricado);
        }
        if (val[DoXnArtDesc.ART_TYPE_ARTICLE] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_TYPE_ARTICLE], artTypeArticle);
        }
        if (val[DoXnArtDesc.ART_DT_OK_NOMENCLATURE] > 0) {
            stmt.setTimestamp(val[DoXnArtDesc.ART_DT_OK_NOMENCLATURE], artDtOkNomenclature);
        }
        if (val[DoXnArtDesc.ART_ARG_QUALITE] > 0) {
            if (artArgQualite == null)
                stmt.setNull(val[DoXnArtDesc.ART_ARG_QUALITE], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_ARG_QUALITE], artArgQualite.intValue());
        }
        if (val[DoXnArtDesc.ART_CEN_NIV] > 0) {
            if (artCenNiv == null)
                stmt.setNull(val[DoXnArtDesc.ART_CEN_NIV], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_CEN_NIV], artCenNiv.intValue());
        }
        if (val[DoXnArtDesc.ART_PCR_CODIGO_ENTREP] > 0) {
            if (artPcrCodigoEntrep == null)
                stmt.setNull(val[DoXnArtDesc.ART_PCR_CODIGO_ENTREP], 3);
            else
                stmt.setInt(val[DoXnArtDesc.ART_PCR_CODIGO_ENTREP], artPcrCodigoEntrep.intValue());
        }
        if (val[DoXnArtDesc.ART_EPAISSEUR_CINTRE] > 0) {
            if (artEpaisseurCintre == null)
                stmt.setNull(val[DoXnArtDesc.ART_EPAISSEUR_CINTRE], 3);
            else
                stmt.setDouble(val[DoXnArtDesc.ART_EPAISSEUR_CINTRE], artEpaisseurCintre.doubleValue());
        }
        if (val[DoXnArtDesc.ART_TYPE_ECLATEMENT] > 0) {
            stmt.setString(val[DoXnArtDesc.ART_TYPE_ECLATEMENT], artTypeEclatement);
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
        DoXnArt[] result = null;
        params = request.getParameterValues("artCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCode(localVal);
            }
        }
        params = request.getParameterValues("artVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtVar1(localVal);
            }
        }
        params = request.getParameterValues("artVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtVar2(localVal);
            }
        }
        params = request.getParameterValues("artVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtVar3(localVal);
            }
        }
        params = request.getParameterValues("artModCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtModCode(localVal);
            }
        }
        params = request.getParameterValues("artMocModCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMocModCode(localVal);
            }
        }
        params = request.getParameterValues("artMocModVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMocModVar1(localVal);
            }
        }
        params = request.getParameterValues("artCaaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCaaCode(localVal);
            }
        }
        params = request.getParameterValues("artFouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtFouCode(localVal);
            }
        }
        params = request.getParameterValues("artSfaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtSfaCode(localVal);
            }
        }
        params = request.getParameterValues("artTxpCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtTxpCode(localVal);
            }
        }
        params = request.getParameterValues("artMesCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMesCode(localVal);
            }
        }
        params = request.getParameterValues("artFaaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtFaaCode(localVal);
            }
        }
        params = request.getParameterValues("artMesCodeAch");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMesCodeAch(localVal);
            }
        }
        params = request.getParameterValues("artMesCodeVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMesCodeVte(localVal);
            }
        }
        params = request.getParameterValues("artPxInit");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxInit((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artTyaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtTyaCode(localVal);
            }
        }
        params = request.getParameterValues("artDes1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDes1(localVal);
            }
        }
        params = request.getParameterValues("artDtDerCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDtDerCmde((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("artTvaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtTvaCode(localVal);
            }
        }
        params = request.getParameterValues("artDecompose");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDecompose(localVal);
            }
        }
        params = request.getParameterValues("artDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("artPayCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPayCode(localVal);
            }
        }
        params = request.getParameterValues("artDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDepCode(localVal);
            }
        }
        params = request.getParameterValues("artArtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArtCode(localVal);
            }
        }
        params = request.getParameterValues("artArtVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArtVar1(localVal);
            }
        }
        params = request.getParameterValues("artArtVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArtVar2(localVal);
            }
        }
        params = request.getParameterValues("artArtVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArtVar3(localVal);
            }
        }
        params = request.getParameterValues("artDes2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDes2(localVal);
            }
        }
        params = request.getParameterValues("artDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("artDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("artUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("artCalCompOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCalCompOn(localVal);
            }
        }
        params = request.getParameterValues("artSubType");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtSubType(localVal);
            }
        }
        params = request.getParameterValues("artVol");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtVol((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artUnitePxVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtUnitePxVte((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artTypeArrondi");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtTypeArrondi((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artStock");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtStock(localVal);
            }
        }
        params = request.getParameterValues("artSpcb");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtSpcb((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artSerieLot");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtSerieLot(localVal);
            }
        }
        params = request.getParameterValues("artRefEmplac");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtRefEmplac(localVal);
            }
        }
        params = request.getParameterValues("artRefCatal");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtRefCatal(localVal);
            }
        }
        params = request.getParameterValues("artQteMinVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtQteMinVte((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artDerPxAchat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDerPxAchat((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCpteVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCpteVte(localVal);
            }
        }
        params = request.getParameterValues("artCeeCoef");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCeeCoef((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCeeNomen");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCeeNomen(localVal);
            }
        }
        params = request.getParameterValues("artCoefAchVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCoefAchVte((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCoefUnAchStk");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCoefUnAchStk((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCoefUnStkVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCoefUnStkVte((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCondStk");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCondStk((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artPxVteMp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxVteMp((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPxRevientMp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxRevientMp((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPxRevient");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxRevient((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPxMini");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxMini((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPxMaxi");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxMaxi((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPxAchMp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPxAchMp((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPds");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPds((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPcb");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPcb((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artObs");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtObs(localVal);
            }
        }
        params = request.getParameterValues("artNetOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtNetOn(localVal);
            }
        }
        params = request.getParameterValues("artGencod");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtGencod(localVal);
            }
        }
        params = request.getParameterValues("artEtiqOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtEtiqOn(localVal);
            }
        }
        params = request.getParameterValues("artEtat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtEtat(localVal);
            }
        }
        params = request.getParameterValues("artDtLimCmdeCli");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDtLimCmdeCli((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("artDtDerAchat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDtDerAchat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("artDerPxVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDerPxVte((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCondVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCondVte((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artCpteAch");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCpteAch(localVal);
            }
        }
        params = request.getParameterValues("artCpteAnalAch");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCpteAnalAch(localVal);
            }
        }
        params = request.getParameterValues("artCpteAnalVte");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCpteAnalVte(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
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
        params = request.getParameterValues("artGarantie");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtGarantie((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artHaut");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtHaut((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artMesCodeStk");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMesCodeStk(localVal);
            }
        }
        params = request.getParameterValues("artArrondisStk");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArrondisStk((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("zStatus");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
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
        params = request.getParameterValues("artContreMarqueOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtContreMarqueOn(localVal);
            }
        }
        params = request.getParameterValues("artDesCourt");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDesCourt(localVal);
            }
        }
        params = request.getParameterValues("artNbColis");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtNbColis((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artMagCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMagCode(localVal);
            }
        }
        params = request.getParameterValues("artRotClass");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtRotClass(localVal);
            }
        }
        params = request.getParameterValues("artRotJour");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtRotJour((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artSteOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtSteOn(localVal);
            }
        }
        params = request.getParameterValues("artMesCodeStat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtMesCodeStat(localVal);
            }
        }
        params = request.getParameterValues("artCoefUnVteStat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCoefUnVteStat((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artLong");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtLong((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artLarg");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtLarg((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artPdsNet");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPdsNet((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artCstCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCstCode(localVal);
            }
        }
        params = request.getParameterValues("artZmgCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtZmgCode(localVal);
            }
        }
        params = request.getParameterValues("artFrais");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtFrais(localVal);
            }
        }
        params = request.getParameterValues("artQteType");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtQteType(localVal);
            }
        }
        params = request.getParameterValues("artArgPerdidas");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgPerdidas((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artArgCatalogo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgCatalogo(localVal);
            }
        }
        params = request.getParameterValues("artArgLoteMinimo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgLoteMinimo((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artArgPcrCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgPcrCodigo((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artArgPcrRevision");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgPcrRevision((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artArgControlRec");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgControlRec(localVal);
            }
        }
        params = request.getParameterValues("artArgModoCompra");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgModoCompra(localVal);
            }
        }
        params = request.getParameterValues("artArgPlazoFabr");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgPlazoFabr((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artArgFabricado");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgFabricado(localVal);
            }
        }
        params = request.getParameterValues("artTypeArticle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtTypeArticle(localVal);
            }
        }
        params = request.getParameterValues("artDtOkNomenclature");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtDtOkNomenclature((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("artArgQualite");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtArgQualite((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artCenNiv");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtCenNiv((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artPcrCodigoEntrep");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtPcrCodigoEntrep((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("artEpaisseurCintre");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtEpaisseurCintre((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("artTypeEclatement");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnArt[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnArt();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setArtTypeEclatement(localVal);
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

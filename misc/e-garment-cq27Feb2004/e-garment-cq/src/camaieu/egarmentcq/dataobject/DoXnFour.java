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

public class DoXnFour implements DataObject {

    private static final IDoDescription description = new DoXnFourDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[85];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"XN_FOUR"};
    //variables correspondant à la table XN_FOUR
    private String fouReliquatOn = null;
    private Double fouTxEsc = null;
    private String fouFouCodePx = null;
    private String fouCode = null;
    private String fouFfoCode = null;
    private String fouNom = null;
    private String fouZecCode = null;
    private String fouTycCode = null;
    private String fouPayCode = null;
    private String fouLanCode = null;
    private String fouDevCode = null;
    private String fouMtrCode = null;
    private String fouMrgCode = null;
    private String fouCrgCode = null;
    private String fouMliCode = null;
    private String fouAdr3 = null;
    private String fouBanq = null;
    private Timestamp fouDtDerCmde = null;
    private Timestamp fouDtCreat = null;
    private Integer fouDelaiLiv = null;
    private String fouCtrleFac = null;
    private String fouCpteCompta = null;
    private String fouCodeLibre = null;
    private String fouCodeGuichet = null;
    private String fouCodeGencod = null;
    private String fouAdr1 = null;
    private String fouVille = null;
    private String fouUtilMaj = null;
    private Integer fouTypeRemDiff = null;
    private Double fouTxRemFac3 = null;
    private Double fouTxRemFac2 = null;
    private Double fouTxRemFac1 = null;
    private String fouTel = null;
    private String fouAdr3Banq = null;
    private String fouAdr2Banq = null;
    private String fouAdr2 = null;
    private String fouAdr1Banq = null;
    private String fouRegimeTva = null;
    private String fouRefNous = null;
    private String fouPostal = null;
    private Double fouMtMinCmde = null;
    private Double fouMtFrancoPort = null;
    private String fouGroupeOn = null;
    private String fouFax = null;
    private Double fouCeeCoef = null;
    private String fouCeeId = null;
    private String fouCleCtrle = null;
    private String fouCodeCpteBanq = null;
    private String fouCodeBanq = null;
    private Timestamp fouDtMaj = null;
    private Integer xtxId = null;
    private String fouFotCode = null;
    private String fouTel2 = null;
    private String fouEmail = null;
    private String fouFouCode = null;
    private String fouFouCodeAgenti = null;
    private String fouFouCodeAgente = null;
    private String fouPayCodeFab = null;
    private String fouAdr1Paye = null;
    private String fouAdr2Paye = null;
    private String fouPostalPaye = null;
    private String fouVillePaye = null;
    private String fouPayCodePaye = null;
    private String fouTel1Paye = null;
    private String fouTel2Paye = null;
    private String fouFaxPaye = null;
    private String fouEmailPaye = null;
    private Timestamp fouDtAccept = null;
    private Timestamp fouDtFinTrav = null;
    private String fouSignataire = null;
    private String fouQualIndice = null;
    private String fouStatEtiq = null;
    private String fouStatCq = null;
    private String fouConsigne = null;
    private String zStatus = null;
    private String fouEtat = null;
    private String fouObs = null;
    private Integer fouCdeComposants = null;
    private String fouDepCode = null;
    private Integer fouOkVisite = null;
    private String fouPrestVisite = null;
    private String fouDomaine = null;
    private String fouDepSocCode = null;
    private String fouDepCodeTissu = null;
    private String fouDepSocCodeTissu = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoXnFour() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnFour(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnFour(DoXnFour arg) {
        setFouReliquatOn(arg.fouReliquatOn);
        setFouTxEsc(arg.fouTxEsc);
        setFouFouCodePx(arg.fouFouCodePx);
        setFouCode(arg.fouCode);
        setFouFfoCode(arg.fouFfoCode);
        setFouNom(arg.fouNom);
        setFouZecCode(arg.fouZecCode);
        setFouTycCode(arg.fouTycCode);
        setFouPayCode(arg.fouPayCode);
        setFouLanCode(arg.fouLanCode);
        setFouDevCode(arg.fouDevCode);
        setFouMtrCode(arg.fouMtrCode);
        setFouMrgCode(arg.fouMrgCode);
        setFouCrgCode(arg.fouCrgCode);
        setFouMliCode(arg.fouMliCode);
        setFouAdr3(arg.fouAdr3);
        setFouBanq(arg.fouBanq);
        setFouDtDerCmde(arg.fouDtDerCmde);
        setFouDtCreat(arg.fouDtCreat);
        setFouDelaiLiv(arg.fouDelaiLiv);
        setFouCtrleFac(arg.fouCtrleFac);
        setFouCpteCompta(arg.fouCpteCompta);
        setFouCodeLibre(arg.fouCodeLibre);
        setFouCodeGuichet(arg.fouCodeGuichet);
        setFouCodeGencod(arg.fouCodeGencod);
        setFouAdr1(arg.fouAdr1);
        setFouVille(arg.fouVille);
        setFouUtilMaj(arg.fouUtilMaj);
        setFouTypeRemDiff(arg.fouTypeRemDiff);
        setFouTxRemFac3(arg.fouTxRemFac3);
        setFouTxRemFac2(arg.fouTxRemFac2);
        setFouTxRemFac1(arg.fouTxRemFac1);
        setFouTel(arg.fouTel);
        setFouAdr3Banq(arg.fouAdr3Banq);
        setFouAdr2Banq(arg.fouAdr2Banq);
        setFouAdr2(arg.fouAdr2);
        setFouAdr1Banq(arg.fouAdr1Banq);
        setFouRegimeTva(arg.fouRegimeTva);
        setFouRefNous(arg.fouRefNous);
        setFouPostal(arg.fouPostal);
        setFouMtMinCmde(arg.fouMtMinCmde);
        setFouMtFrancoPort(arg.fouMtFrancoPort);
        setFouGroupeOn(arg.fouGroupeOn);
        setFouFax(arg.fouFax);
        setFouCeeCoef(arg.fouCeeCoef);
        setFouCeeId(arg.fouCeeId);
        setFouCleCtrle(arg.fouCleCtrle);
        setFouCodeCpteBanq(arg.fouCodeCpteBanq);
        setFouCodeBanq(arg.fouCodeBanq);
        setFouDtMaj(arg.fouDtMaj);
        setXtxId(arg.xtxId);
        setFouFotCode(arg.fouFotCode);
        setFouTel2(arg.fouTel2);
        setFouEmail(arg.fouEmail);
        setFouFouCode(arg.fouFouCode);
        setFouFouCodeAgenti(arg.fouFouCodeAgenti);
        setFouFouCodeAgente(arg.fouFouCodeAgente);
        setFouPayCodeFab(arg.fouPayCodeFab);
        setFouAdr1Paye(arg.fouAdr1Paye);
        setFouAdr2Paye(arg.fouAdr2Paye);
        setFouPostalPaye(arg.fouPostalPaye);
        setFouVillePaye(arg.fouVillePaye);
        setFouPayCodePaye(arg.fouPayCodePaye);
        setFouTel1Paye(arg.fouTel1Paye);
        setFouTel2Paye(arg.fouTel2Paye);
        setFouFaxPaye(arg.fouFaxPaye);
        setFouEmailPaye(arg.fouEmailPaye);
        setFouDtAccept(arg.fouDtAccept);
        setFouDtFinTrav(arg.fouDtFinTrav);
        setFouSignataire(arg.fouSignataire);
        setFouQualIndice(arg.fouQualIndice);
        setFouStatEtiq(arg.fouStatEtiq);
        setFouStatCq(arg.fouStatCq);
        setFouConsigne(arg.fouConsigne);
        setZStatus(arg.zStatus);
        setFouEtat(arg.fouEtat);
        setFouObs(arg.fouObs);
        setFouCdeComposants(arg.fouCdeComposants);
        setFouDepCode(arg.fouDepCode);
        setFouOkVisite(arg.fouOkVisite);
        setFouPrestVisite(arg.fouPrestVisite);
        setFouDomaine(arg.fouDomaine);
        setFouDepSocCode(arg.fouDepSocCode);
        setFouDepCodeTissu(arg.fouDepCodeTissu);
        setFouDepSocCodeTissu(arg.fouDepSocCodeTissu);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoXnFour(String newSql, Object[] newParam) {
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

    public String getFouReliquatOn() {
        return fouReliquatOn;
    }

    public Double getFouTxEsc() {
        return fouTxEsc;
    }

    public String getFouFouCodePx() {
        return fouFouCodePx;
    }

    public String getFouCode() {
        return fouCode;
    }

    public String getFouFfoCode() {
        return fouFfoCode;
    }

    public String getFouNom() {
        return fouNom;
    }

    public String getFouZecCode() {
        return fouZecCode;
    }

    public String getFouTycCode() {
        return fouTycCode;
    }

    public String getFouPayCode() {
        return fouPayCode;
    }

    public String getFouLanCode() {
        return fouLanCode;
    }

    public String getFouDevCode() {
        return fouDevCode;
    }

    public String getFouMtrCode() {
        return fouMtrCode;
    }

    public String getFouMrgCode() {
        return fouMrgCode;
    }

    public String getFouCrgCode() {
        return fouCrgCode;
    }

    public String getFouMliCode() {
        return fouMliCode;
    }

    public String getFouAdr3() {
        return fouAdr3;
    }

    public String getFouBanq() {
        return fouBanq;
    }

    public Timestamp getFouDtDerCmde() {
        return fouDtDerCmde;
    }

    public Timestamp getFouDtCreat() {
        return fouDtCreat;
    }

    public Integer getFouDelaiLiv() {
        return fouDelaiLiv;
    }

    public String getFouCtrleFac() {
        return fouCtrleFac;
    }

    public String getFouCpteCompta() {
        return fouCpteCompta;
    }

    public String getFouCodeLibre() {
        return fouCodeLibre;
    }

    public String getFouCodeGuichet() {
        return fouCodeGuichet;
    }

    public String getFouCodeGencod() {
        return fouCodeGencod;
    }

    public String getFouAdr1() {
        return fouAdr1;
    }

    public String getFouVille() {
        return fouVille;
    }

    public String getFouUtilMaj() {
        return fouUtilMaj;
    }

    public Integer getFouTypeRemDiff() {
        return fouTypeRemDiff;
    }

    public Double getFouTxRemFac3() {
        return fouTxRemFac3;
    }

    public Double getFouTxRemFac2() {
        return fouTxRemFac2;
    }

    public Double getFouTxRemFac1() {
        return fouTxRemFac1;
    }

    public String getFouTel() {
        return fouTel;
    }

    public String getFouAdr3Banq() {
        return fouAdr3Banq;
    }

    public String getFouAdr2Banq() {
        return fouAdr2Banq;
    }

    public String getFouAdr2() {
        return fouAdr2;
    }

    public String getFouAdr1Banq() {
        return fouAdr1Banq;
    }

    public String getFouRegimeTva() {
        return fouRegimeTva;
    }

    public String getFouRefNous() {
        return fouRefNous;
    }

    public String getFouPostal() {
        return fouPostal;
    }

    public Double getFouMtMinCmde() {
        return fouMtMinCmde;
    }

    public Double getFouMtFrancoPort() {
        return fouMtFrancoPort;
    }

    public String getFouGroupeOn() {
        return fouGroupeOn;
    }

    public String getFouFax() {
        return fouFax;
    }

    public Double getFouCeeCoef() {
        return fouCeeCoef;
    }

    public String getFouCeeId() {
        return fouCeeId;
    }

    public String getFouCleCtrle() {
        return fouCleCtrle;
    }

    public String getFouCodeCpteBanq() {
        return fouCodeCpteBanq;
    }

    public String getFouCodeBanq() {
        return fouCodeBanq;
    }

    public Timestamp getFouDtMaj() {
        return fouDtMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getFouFotCode() {
        return fouFotCode;
    }

    public String getFouTel2() {
        return fouTel2;
    }

    public String getFouEmail() {
        return fouEmail;
    }

    public String getFouFouCode() {
        return fouFouCode;
    }

    public String getFouFouCodeAgenti() {
        return fouFouCodeAgenti;
    }

    public String getFouFouCodeAgente() {
        return fouFouCodeAgente;
    }

    public String getFouPayCodeFab() {
        return fouPayCodeFab;
    }

    public String getFouAdr1Paye() {
        return fouAdr1Paye;
    }

    public String getFouAdr2Paye() {
        return fouAdr2Paye;
    }

    public String getFouPostalPaye() {
        return fouPostalPaye;
    }

    public String getFouVillePaye() {
        return fouVillePaye;
    }

    public String getFouPayCodePaye() {
        return fouPayCodePaye;
    }

    public String getFouTel1Paye() {
        return fouTel1Paye;
    }

    public String getFouTel2Paye() {
        return fouTel2Paye;
    }

    public String getFouFaxPaye() {
        return fouFaxPaye;
    }

    public String getFouEmailPaye() {
        return fouEmailPaye;
    }

    public Timestamp getFouDtAccept() {
        return fouDtAccept;
    }

    public Timestamp getFouDtFinTrav() {
        return fouDtFinTrav;
    }

    public String getFouSignataire() {
        return fouSignataire;
    }

    public String getFouQualIndice() {
        return fouQualIndice;
    }

    public String getFouStatEtiq() {
        return fouStatEtiq;
    }

    public String getFouStatCq() {
        return fouStatCq;
    }

    public String getFouConsigne() {
        return fouConsigne;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getFouEtat() {
        return fouEtat;
    }

    public String getFouObs() {
        return fouObs;
    }

    public Integer getFouCdeComposants() {
        return fouCdeComposants;
    }

    public String getFouDepCode() {
        return fouDepCode;
    }

    public Integer getFouOkVisite() {
        return fouOkVisite;
    }

    public String getFouPrestVisite() {
        return fouPrestVisite;
    }

    public String getFouDomaine() {
        return fouDomaine;
    }

    public String getFouDepSocCode() {
        return fouDepSocCode;
    }

    public String getFouDepCodeTissu() {
        return fouDepCodeTissu;
    }

    public String getFouDepSocCodeTissu() {
        return fouDepSocCodeTissu;
    }

    public void setFouReliquatOn(String newFouReliquatOn) {
        fouReliquatOn = newFouReliquatOn;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_RELIQUAT_ON] = 1;
    }

    public void setFouTxEsc(Double newFouTxEsc) {
        fouTxEsc = newFouTxEsc;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TX_ESC] = 1;
    }

    public void setFouFouCodePx(String newFouFouCodePx) {
        fouFouCodePx = newFouFouCodePx;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FOU_CODE_PX] = 1;
    }

    public void setFouCode(String newFouCode) {
        fouCode = newFouCode;
    }

    public void setFouFfoCode(String newFouFfoCode) {
        fouFfoCode = newFouFfoCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FFO_CODE] = 1;
    }

    public void setFouNom(String newFouNom) {
        fouNom = newFouNom;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_NOM] = 1;
    }

    public void setFouZecCode(String newFouZecCode) {
        fouZecCode = newFouZecCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ZEC_CODE] = 1;
    }

    public void setFouTycCode(String newFouTycCode) {
        fouTycCode = newFouTycCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TYC_CODE] = 1;
    }

    public void setFouPayCode(String newFouPayCode) {
        fouPayCode = newFouPayCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_PAY_CODE] = 1;
    }

    public void setFouLanCode(String newFouLanCode) {
        fouLanCode = newFouLanCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_LAN_CODE] = 1;
    }

    public void setFouDevCode(String newFouDevCode) {
        fouDevCode = newFouDevCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DEV_CODE] = 1;
    }

    public void setFouMtrCode(String newFouMtrCode) {
        fouMtrCode = newFouMtrCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_MTR_CODE] = 1;
    }

    public void setFouMrgCode(String newFouMrgCode) {
        fouMrgCode = newFouMrgCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_MRG_CODE] = 1;
    }

    public void setFouCrgCode(String newFouCrgCode) {
        fouCrgCode = newFouCrgCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CRG_CODE] = 1;
    }

    public void setFouMliCode(String newFouMliCode) {
        fouMliCode = newFouMliCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_MLI_CODE] = 1;
    }

    public void setFouAdr3(String newFouAdr3) {
        fouAdr3 = newFouAdr3;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR3] = 1;
    }

    public void setFouBanq(String newFouBanq) {
        fouBanq = newFouBanq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_BANQ] = 1;
    }

    public void setFouDtDerCmde(Timestamp newFouDtDerCmde) {
        fouDtDerCmde = newFouDtDerCmde;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DT_DER_CMDE] = 1;
    }

    public void setFouDtCreat(Timestamp newFouDtCreat) {
        fouDtCreat = newFouDtCreat;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DT_CREAT] = 1;
    }

    public void setFouDelaiLiv(Integer newFouDelaiLiv) {
        fouDelaiLiv = newFouDelaiLiv;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DELAI_LIV] = 1;
    }

    public void setFouCtrleFac(String newFouCtrleFac) {
        fouCtrleFac = newFouCtrleFac;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CTRLE_FAC] = 1;
    }

    public void setFouCpteCompta(String newFouCpteCompta) {
        fouCpteCompta = newFouCpteCompta;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CPTE_COMPTA] = 1;
    }

    public void setFouCodeLibre(String newFouCodeLibre) {
        fouCodeLibre = newFouCodeLibre;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CODE_LIBRE] = 1;
    }

    public void setFouCodeGuichet(String newFouCodeGuichet) {
        fouCodeGuichet = newFouCodeGuichet;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CODE_GUICHET] = 1;
    }

    public void setFouCodeGencod(String newFouCodeGencod) {
        fouCodeGencod = newFouCodeGencod;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CODE_GENCOD] = 1;
    }

    public void setFouAdr1(String newFouAdr1) {
        fouAdr1 = newFouAdr1;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR1] = 1;
    }

    public void setFouVille(String newFouVille) {
        fouVille = newFouVille;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_VILLE] = 1;
    }

    public void setFouUtilMaj(String newFouUtilMaj) {
        fouUtilMaj = newFouUtilMaj;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_UTIL_MAJ] = 1;
    }

    public void setFouTypeRemDiff(Integer newFouTypeRemDiff) {
        fouTypeRemDiff = newFouTypeRemDiff;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TYPE_REM_DIFF] = 1;
    }

    public void setFouTxRemFac3(Double newFouTxRemFac3) {
        fouTxRemFac3 = newFouTxRemFac3;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TX_REM_FAC3] = 1;
    }

    public void setFouTxRemFac2(Double newFouTxRemFac2) {
        fouTxRemFac2 = newFouTxRemFac2;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TX_REM_FAC2] = 1;
    }

    public void setFouTxRemFac1(Double newFouTxRemFac1) {
        fouTxRemFac1 = newFouTxRemFac1;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TX_REM_FAC1] = 1;
    }

    public void setFouTel(String newFouTel) {
        fouTel = newFouTel;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TEL] = 1;
    }

    public void setFouAdr3Banq(String newFouAdr3Banq) {
        fouAdr3Banq = newFouAdr3Banq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR3_BANQ] = 1;
    }

    public void setFouAdr2Banq(String newFouAdr2Banq) {
        fouAdr2Banq = newFouAdr2Banq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR2_BANQ] = 1;
    }

    public void setFouAdr2(String newFouAdr2) {
        fouAdr2 = newFouAdr2;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR2] = 1;
    }

    public void setFouAdr1Banq(String newFouAdr1Banq) {
        fouAdr1Banq = newFouAdr1Banq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR1_BANQ] = 1;
    }

    public void setFouRegimeTva(String newFouRegimeTva) {
        fouRegimeTva = newFouRegimeTva;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_REGIME_TVA] = 1;
    }

    public void setFouRefNous(String newFouRefNous) {
        fouRefNous = newFouRefNous;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_REF_NOUS] = 1;
    }

    public void setFouPostal(String newFouPostal) {
        fouPostal = newFouPostal;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_POSTAL] = 1;
    }

    public void setFouMtMinCmde(Double newFouMtMinCmde) {
        fouMtMinCmde = newFouMtMinCmde;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_MT_MIN_CMDE] = 1;
    }

    public void setFouMtFrancoPort(Double newFouMtFrancoPort) {
        fouMtFrancoPort = newFouMtFrancoPort;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_MT_FRANCO_PORT] = 1;
    }

    public void setFouGroupeOn(String newFouGroupeOn) {
        fouGroupeOn = newFouGroupeOn;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_GROUPE_ON] = 1;
    }

    public void setFouFax(String newFouFax) {
        fouFax = newFouFax;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FAX] = 1;
    }

    public void setFouCeeCoef(Double newFouCeeCoef) {
        fouCeeCoef = newFouCeeCoef;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CEE_COEF] = 1;
    }

    public void setFouCeeId(String newFouCeeId) {
        fouCeeId = newFouCeeId;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CEE_ID] = 1;
    }

    public void setFouCleCtrle(String newFouCleCtrle) {
        fouCleCtrle = newFouCleCtrle;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CLE_CTRLE] = 1;
    }

    public void setFouCodeCpteBanq(String newFouCodeCpteBanq) {
        fouCodeCpteBanq = newFouCodeCpteBanq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CODE_CPTE_BANQ] = 1;
    }

    public void setFouCodeBanq(String newFouCodeBanq) {
        fouCodeBanq = newFouCodeBanq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CODE_BANQ] = 1;
    }

    public void setFouDtMaj(Timestamp newFouDtMaj) {
        fouDtMaj = newFouDtMaj;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DT_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoXnFourDesc.XTX_ID] = 1;
    }

    public void setFouFotCode(String newFouFotCode) {
        fouFotCode = newFouFotCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FOT_CODE] = 1;
    }

    public void setFouTel2(String newFouTel2) {
        fouTel2 = newFouTel2;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TEL2] = 1;
    }

    public void setFouEmail(String newFouEmail) {
        fouEmail = newFouEmail;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_EMAIL] = 1;
    }

    public void setFouFouCode(String newFouFouCode) {
        fouFouCode = newFouFouCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FOU_CODE] = 1;
    }

    public void setFouFouCodeAgenti(String newFouFouCodeAgenti) {
        fouFouCodeAgenti = newFouFouCodeAgenti;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FOU_CODE_AGENTI] = 1;
    }

    public void setFouFouCodeAgente(String newFouFouCodeAgente) {
        fouFouCodeAgente = newFouFouCodeAgente;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FOU_CODE_AGENTE] = 1;
    }

    public void setFouPayCodeFab(String newFouPayCodeFab) {
        fouPayCodeFab = newFouPayCodeFab;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_PAY_CODE_FAB] = 1;
    }

    public void setFouAdr1Paye(String newFouAdr1Paye) {
        fouAdr1Paye = newFouAdr1Paye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR1_PAYE] = 1;
    }

    public void setFouAdr2Paye(String newFouAdr2Paye) {
        fouAdr2Paye = newFouAdr2Paye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ADR2_PAYE] = 1;
    }

    public void setFouPostalPaye(String newFouPostalPaye) {
        fouPostalPaye = newFouPostalPaye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_POSTAL_PAYE] = 1;
    }

    public void setFouVillePaye(String newFouVillePaye) {
        fouVillePaye = newFouVillePaye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_VILLE_PAYE] = 1;
    }

    public void setFouPayCodePaye(String newFouPayCodePaye) {
        fouPayCodePaye = newFouPayCodePaye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_PAY_CODE_PAYE] = 1;
    }

    public void setFouTel1Paye(String newFouTel1Paye) {
        fouTel1Paye = newFouTel1Paye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TEL1_PAYE] = 1;
    }

    public void setFouTel2Paye(String newFouTel2Paye) {
        fouTel2Paye = newFouTel2Paye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_TEL2_PAYE] = 1;
    }

    public void setFouFaxPaye(String newFouFaxPaye) {
        fouFaxPaye = newFouFaxPaye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_FAX_PAYE] = 1;
    }

    public void setFouEmailPaye(String newFouEmailPaye) {
        fouEmailPaye = newFouEmailPaye;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_EMAIL_PAYE] = 1;
    }

    public void setFouDtAccept(Timestamp newFouDtAccept) {
        fouDtAccept = newFouDtAccept;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DT_ACCEPT] = 1;
    }

    public void setFouDtFinTrav(Timestamp newFouDtFinTrav) {
        fouDtFinTrav = newFouDtFinTrav;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DT_FIN_TRAV] = 1;
    }

    public void setFouSignataire(String newFouSignataire) {
        fouSignataire = newFouSignataire;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_SIGNATAIRE] = 1;
    }

    public void setFouQualIndice(String newFouQualIndice) {
        fouQualIndice = newFouQualIndice;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_QUAL_INDICE] = 1;
    }

    public void setFouStatEtiq(String newFouStatEtiq) {
        fouStatEtiq = newFouStatEtiq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_STAT_ETIQ] = 1;
    }

    public void setFouStatCq(String newFouStatCq) {
        fouStatCq = newFouStatCq;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_STAT_CQ] = 1;
    }

    public void setFouConsigne(String newFouConsigne) {
        fouConsigne = newFouConsigne;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CONSIGNE] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoXnFourDesc.Z_STATUS] = 1;
    }

    public void setFouEtat(String newFouEtat) {
        fouEtat = newFouEtat;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_ETAT] = 1;
    }

    public void setFouObs(String newFouObs) {
        fouObs = newFouObs;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_OBS] = 1;
    }

    public void setFouCdeComposants(Integer newFouCdeComposants) {
        fouCdeComposants = newFouCdeComposants;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_CDE_COMPOSANTS] = 1;
    }

    public void setFouDepCode(String newFouDepCode) {
        fouDepCode = newFouDepCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DEP_CODE] = 1;
    }

    public void setFouOkVisite(Integer newFouOkVisite) {
        fouOkVisite = newFouOkVisite;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_OK_VISITE] = 1;
    }

    public void setFouPrestVisite(String newFouPrestVisite) {
        fouPrestVisite = newFouPrestVisite;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_PREST_VISITE] = 1;
    }

    public void setFouDomaine(String newFouDomaine) {
        fouDomaine = newFouDomaine;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DOMAINE] = 1;
    }

    public void setFouDepSocCode(String newFouDepSocCode) {
        fouDepSocCode = newFouDepSocCode;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DEP_SOC_CODE] = 1;
    }

    public void setFouDepCodeTissu(String newFouDepCodeTissu) {
        fouDepCodeTissu = newFouDepCodeTissu;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DEP_CODE_TISSU] = 1;
    }

    public void setFouDepSocCodeTissu(String newFouDepSocCodeTissu) {
        fouDepSocCodeTissu = newFouDepSocCodeTissu;
        if (persist > 0)
            updCol[DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoXnFourDesc.FOU_RELIQUAT_ON)
            return fouReliquatOn;
        else if (numCol == DoXnFourDesc.FOU_TX_ESC)
            return fouTxEsc;
        else if (numCol == DoXnFourDesc.FOU_FOU_CODE_PX)
            return fouFouCodePx;
        else if (numCol == DoXnFourDesc.FOU_CODE)
            return fouCode;
        else if (numCol == DoXnFourDesc.FOU_FFO_CODE)
            return fouFfoCode;
        else if (numCol == DoXnFourDesc.FOU_NOM)
            return fouNom;
        else if (numCol == DoXnFourDesc.FOU_ZEC_CODE)
            return fouZecCode;
        else if (numCol == DoXnFourDesc.FOU_TYC_CODE)
            return fouTycCode;
        else if (numCol == DoXnFourDesc.FOU_PAY_CODE)
            return fouPayCode;
        else if (numCol == DoXnFourDesc.FOU_LAN_CODE)
            return fouLanCode;
        else if (numCol == DoXnFourDesc.FOU_DEV_CODE)
            return fouDevCode;
        else if (numCol == DoXnFourDesc.FOU_MTR_CODE)
            return fouMtrCode;
        else if (numCol == DoXnFourDesc.FOU_MRG_CODE)
            return fouMrgCode;
        else if (numCol == DoXnFourDesc.FOU_CRG_CODE)
            return fouCrgCode;
        else if (numCol == DoXnFourDesc.FOU_MLI_CODE)
            return fouMliCode;
        else if (numCol == DoXnFourDesc.FOU_ADR3)
            return fouAdr3;
        else if (numCol == DoXnFourDesc.FOU_BANQ)
            return fouBanq;
        else if (numCol == DoXnFourDesc.FOU_DT_DER_CMDE)
            return fouDtDerCmde;
        else if (numCol == DoXnFourDesc.FOU_DT_CREAT)
            return fouDtCreat;
        else if (numCol == DoXnFourDesc.FOU_DELAI_LIV)
            return fouDelaiLiv;
        else if (numCol == DoXnFourDesc.FOU_CTRLE_FAC)
            return fouCtrleFac;
        else if (numCol == DoXnFourDesc.FOU_CPTE_COMPTA)
            return fouCpteCompta;
        else if (numCol == DoXnFourDesc.FOU_CODE_LIBRE)
            return fouCodeLibre;
        else if (numCol == DoXnFourDesc.FOU_CODE_GUICHET)
            return fouCodeGuichet;
        else if (numCol == DoXnFourDesc.FOU_CODE_GENCOD)
            return fouCodeGencod;
        else if (numCol == DoXnFourDesc.FOU_ADR1)
            return fouAdr1;
        else if (numCol == DoXnFourDesc.FOU_VILLE)
            return fouVille;
        else if (numCol == DoXnFourDesc.FOU_UTIL_MAJ)
            return fouUtilMaj;
        else if (numCol == DoXnFourDesc.FOU_TYPE_REM_DIFF)
            return fouTypeRemDiff;
        else if (numCol == DoXnFourDesc.FOU_TX_REM_FAC3)
            return fouTxRemFac3;
        else if (numCol == DoXnFourDesc.FOU_TX_REM_FAC2)
            return fouTxRemFac2;
        else if (numCol == DoXnFourDesc.FOU_TX_REM_FAC1)
            return fouTxRemFac1;
        else if (numCol == DoXnFourDesc.FOU_TEL)
            return fouTel;
        else if (numCol == DoXnFourDesc.FOU_ADR3_BANQ)
            return fouAdr3Banq;
        else if (numCol == DoXnFourDesc.FOU_ADR2_BANQ)
            return fouAdr2Banq;
        else if (numCol == DoXnFourDesc.FOU_ADR2)
            return fouAdr2;
        else if (numCol == DoXnFourDesc.FOU_ADR1_BANQ)
            return fouAdr1Banq;
        else if (numCol == DoXnFourDesc.FOU_REGIME_TVA)
            return fouRegimeTva;
        else if (numCol == DoXnFourDesc.FOU_REF_NOUS)
            return fouRefNous;
        else if (numCol == DoXnFourDesc.FOU_POSTAL)
            return fouPostal;
        else if (numCol == DoXnFourDesc.FOU_MT_MIN_CMDE)
            return fouMtMinCmde;
        else if (numCol == DoXnFourDesc.FOU_MT_FRANCO_PORT)
            return fouMtFrancoPort;
        else if (numCol == DoXnFourDesc.FOU_GROUPE_ON)
            return fouGroupeOn;
        else if (numCol == DoXnFourDesc.FOU_FAX)
            return fouFax;
        else if (numCol == DoXnFourDesc.FOU_CEE_COEF)
            return fouCeeCoef;
        else if (numCol == DoXnFourDesc.FOU_CEE_ID)
            return fouCeeId;
        else if (numCol == DoXnFourDesc.FOU_CLE_CTRLE)
            return fouCleCtrle;
        else if (numCol == DoXnFourDesc.FOU_CODE_CPTE_BANQ)
            return fouCodeCpteBanq;
        else if (numCol == DoXnFourDesc.FOU_CODE_BANQ)
            return fouCodeBanq;
        else if (numCol == DoXnFourDesc.FOU_DT_MAJ)
            return fouDtMaj;
        else if (numCol == DoXnFourDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoXnFourDesc.FOU_FOT_CODE)
            return fouFotCode;
        else if (numCol == DoXnFourDesc.FOU_TEL2)
            return fouTel2;
        else if (numCol == DoXnFourDesc.FOU_EMAIL)
            return fouEmail;
        else if (numCol == DoXnFourDesc.FOU_FOU_CODE)
            return fouFouCode;
        else if (numCol == DoXnFourDesc.FOU_FOU_CODE_AGENTI)
            return fouFouCodeAgenti;
        else if (numCol == DoXnFourDesc.FOU_FOU_CODE_AGENTE)
            return fouFouCodeAgente;
        else if (numCol == DoXnFourDesc.FOU_PAY_CODE_FAB)
            return fouPayCodeFab;
        else if (numCol == DoXnFourDesc.FOU_ADR1_PAYE)
            return fouAdr1Paye;
        else if (numCol == DoXnFourDesc.FOU_ADR2_PAYE)
            return fouAdr2Paye;
        else if (numCol == DoXnFourDesc.FOU_POSTAL_PAYE)
            return fouPostalPaye;
        else if (numCol == DoXnFourDesc.FOU_VILLE_PAYE)
            return fouVillePaye;
        else if (numCol == DoXnFourDesc.FOU_PAY_CODE_PAYE)
            return fouPayCodePaye;
        else if (numCol == DoXnFourDesc.FOU_TEL1_PAYE)
            return fouTel1Paye;
        else if (numCol == DoXnFourDesc.FOU_TEL2_PAYE)
            return fouTel2Paye;
        else if (numCol == DoXnFourDesc.FOU_FAX_PAYE)
            return fouFaxPaye;
        else if (numCol == DoXnFourDesc.FOU_EMAIL_PAYE)
            return fouEmailPaye;
        else if (numCol == DoXnFourDesc.FOU_DT_ACCEPT)
            return fouDtAccept;
        else if (numCol == DoXnFourDesc.FOU_DT_FIN_TRAV)
            return fouDtFinTrav;
        else if (numCol == DoXnFourDesc.FOU_SIGNATAIRE)
            return fouSignataire;
        else if (numCol == DoXnFourDesc.FOU_QUAL_INDICE)
            return fouQualIndice;
        else if (numCol == DoXnFourDesc.FOU_STAT_ETIQ)
            return fouStatEtiq;
        else if (numCol == DoXnFourDesc.FOU_STAT_CQ)
            return fouStatCq;
        else if (numCol == DoXnFourDesc.FOU_CONSIGNE)
            return fouConsigne;
        else if (numCol == DoXnFourDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoXnFourDesc.FOU_ETAT)
            return fouEtat;
        else if (numCol == DoXnFourDesc.FOU_OBS)
            return fouObs;
        else if (numCol == DoXnFourDesc.FOU_CDE_COMPOSANTS)
            return fouCdeComposants;
        else if (numCol == DoXnFourDesc.FOU_DEP_CODE)
            return fouDepCode;
        else if (numCol == DoXnFourDesc.FOU_OK_VISITE)
            return fouOkVisite;
        else if (numCol == DoXnFourDesc.FOU_PREST_VISITE)
            return fouPrestVisite;
        else if (numCol == DoXnFourDesc.FOU_DOMAINE)
            return fouDomaine;
        else if (numCol == DoXnFourDesc.FOU_DEP_SOC_CODE)
            return fouDepSocCode;
        else if (numCol == DoXnFourDesc.FOU_DEP_CODE_TISSU)
            return fouDepCodeTissu;
        else if (numCol == DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU)
            return fouDepSocCodeTissu;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoXnFourDesc.FOU_RELIQUAT_ON) {
            fouReliquatOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TX_ESC) {
            fouTxEsc = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FOU_CODE_PX) {
            fouFouCodePx = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CODE) {
            fouCode = (String) value;
        }
        if (numCol == DoXnFourDesc.FOU_FFO_CODE) {
            fouFfoCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_NOM) {
            fouNom = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ZEC_CODE) {
            fouZecCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TYC_CODE) {
            fouTycCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_PAY_CODE) {
            fouPayCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_LAN_CODE) {
            fouLanCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DEV_CODE) {
            fouDevCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_MTR_CODE) {
            fouMtrCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_MRG_CODE) {
            fouMrgCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CRG_CODE) {
            fouCrgCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_MLI_CODE) {
            fouMliCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR3) {
            fouAdr3 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_BANQ) {
            fouBanq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DT_DER_CMDE) {
            fouDtDerCmde = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DT_CREAT) {
            fouDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DELAI_LIV) {
            fouDelaiLiv = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CTRLE_FAC) {
            fouCtrleFac = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CPTE_COMPTA) {
            fouCpteCompta = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CODE_LIBRE) {
            fouCodeLibre = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CODE_GUICHET) {
            fouCodeGuichet = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CODE_GENCOD) {
            fouCodeGencod = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR1) {
            fouAdr1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_VILLE) {
            fouVille = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_UTIL_MAJ) {
            fouUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TYPE_REM_DIFF) {
            fouTypeRemDiff = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TX_REM_FAC3) {
            fouTxRemFac3 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TX_REM_FAC2) {
            fouTxRemFac2 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TX_REM_FAC1) {
            fouTxRemFac1 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TEL) {
            fouTel = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR3_BANQ) {
            fouAdr3Banq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR2_BANQ) {
            fouAdr2Banq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR2) {
            fouAdr2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR1_BANQ) {
            fouAdr1Banq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_REGIME_TVA) {
            fouRegimeTva = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_REF_NOUS) {
            fouRefNous = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_POSTAL) {
            fouPostal = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_MT_MIN_CMDE) {
            fouMtMinCmde = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_MT_FRANCO_PORT) {
            fouMtFrancoPort = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_GROUPE_ON) {
            fouGroupeOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FAX) {
            fouFax = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CEE_COEF) {
            fouCeeCoef = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CEE_ID) {
            fouCeeId = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CLE_CTRLE) {
            fouCleCtrle = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CODE_CPTE_BANQ) {
            fouCodeCpteBanq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CODE_BANQ) {
            fouCodeBanq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DT_MAJ) {
            fouDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FOT_CODE) {
            fouFotCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TEL2) {
            fouTel2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_EMAIL) {
            fouEmail = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FOU_CODE) {
            fouFouCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FOU_CODE_AGENTI) {
            fouFouCodeAgenti = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FOU_CODE_AGENTE) {
            fouFouCodeAgente = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_PAY_CODE_FAB) {
            fouPayCodeFab = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR1_PAYE) {
            fouAdr1Paye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ADR2_PAYE) {
            fouAdr2Paye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_POSTAL_PAYE) {
            fouPostalPaye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_VILLE_PAYE) {
            fouVillePaye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_PAY_CODE_PAYE) {
            fouPayCodePaye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TEL1_PAYE) {
            fouTel1Paye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_TEL2_PAYE) {
            fouTel2Paye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_FAX_PAYE) {
            fouFaxPaye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_EMAIL_PAYE) {
            fouEmailPaye = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DT_ACCEPT) {
            fouDtAccept = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DT_FIN_TRAV) {
            fouDtFinTrav = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_SIGNATAIRE) {
            fouSignataire = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_QUAL_INDICE) {
            fouQualIndice = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_STAT_ETIQ) {
            fouStatEtiq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_STAT_CQ) {
            fouStatCq = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CONSIGNE) {
            fouConsigne = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_ETAT) {
            fouEtat = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_OBS) {
            fouObs = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_CDE_COMPOSANTS) {
            fouCdeComposants = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DEP_CODE) {
            fouDepCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_OK_VISITE) {
            fouOkVisite = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_PREST_VISITE) {
            fouPrestVisite = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DOMAINE) {
            fouDomaine = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DEP_SOC_CODE) {
            fouDepSocCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DEP_CODE_TISSU) {
            fouDepCodeTissu = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU) {
            fouDepSocCodeTissu = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoXnFour());
    }

    private DataObject setProperty(SqlArg sqlArg, DoXnFour djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoXnFourDesc.FOU_RELIQUAT_ON] != -1) {
            djo.fouReliquatOn = rs.getString(val[DoXnFourDesc.FOU_RELIQUAT_ON]);
        }
        if (val[DoXnFourDesc.FOU_TX_ESC] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_TX_ESC]);
            if (!rs.wasNull())
                djo.fouTxEsc = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE_PX] != -1) {
            djo.fouFouCodePx = rs.getString(val[DoXnFourDesc.FOU_FOU_CODE_PX]);
        }
        if (val[DoXnFourDesc.FOU_CODE] != -1) {
            djo.fouCode = rs.getString(val[DoXnFourDesc.FOU_CODE]);
        }
        if (val[DoXnFourDesc.FOU_FFO_CODE] != -1) {
            djo.fouFfoCode = rs.getString(val[DoXnFourDesc.FOU_FFO_CODE]);
        }
        if (val[DoXnFourDesc.FOU_NOM] != -1) {
            djo.fouNom = rs.getString(val[DoXnFourDesc.FOU_NOM]);
        }
        if (val[DoXnFourDesc.FOU_ZEC_CODE] != -1) {
            djo.fouZecCode = rs.getString(val[DoXnFourDesc.FOU_ZEC_CODE]);
        }
        if (val[DoXnFourDesc.FOU_TYC_CODE] != -1) {
            djo.fouTycCode = rs.getString(val[DoXnFourDesc.FOU_TYC_CODE]);
        }
        if (val[DoXnFourDesc.FOU_PAY_CODE] != -1) {
            djo.fouPayCode = rs.getString(val[DoXnFourDesc.FOU_PAY_CODE]);
        }
        if (val[DoXnFourDesc.FOU_LAN_CODE] != -1) {
            djo.fouLanCode = rs.getString(val[DoXnFourDesc.FOU_LAN_CODE]);
        }
        if (val[DoXnFourDesc.FOU_DEV_CODE] != -1) {
            djo.fouDevCode = rs.getString(val[DoXnFourDesc.FOU_DEV_CODE]);
        }
        if (val[DoXnFourDesc.FOU_MTR_CODE] != -1) {
            djo.fouMtrCode = rs.getString(val[DoXnFourDesc.FOU_MTR_CODE]);
        }
        if (val[DoXnFourDesc.FOU_MRG_CODE] != -1) {
            djo.fouMrgCode = rs.getString(val[DoXnFourDesc.FOU_MRG_CODE]);
        }
        if (val[DoXnFourDesc.FOU_CRG_CODE] != -1) {
            djo.fouCrgCode = rs.getString(val[DoXnFourDesc.FOU_CRG_CODE]);
        }
        if (val[DoXnFourDesc.FOU_MLI_CODE] != -1) {
            djo.fouMliCode = rs.getString(val[DoXnFourDesc.FOU_MLI_CODE]);
        }
        if (val[DoXnFourDesc.FOU_ADR3] != -1) {
            djo.fouAdr3 = rs.getString(val[DoXnFourDesc.FOU_ADR3]);
        }
        if (val[DoXnFourDesc.FOU_BANQ] != -1) {
            djo.fouBanq = rs.getString(val[DoXnFourDesc.FOU_BANQ]);
        }
        if (val[DoXnFourDesc.FOU_DT_DER_CMDE] != -1) {
            djo.fouDtDerCmde = rs.getTimestamp(val[DoXnFourDesc.FOU_DT_DER_CMDE]);
        }
        if (val[DoXnFourDesc.FOU_DT_CREAT] != -1) {
            djo.fouDtCreat = rs.getTimestamp(val[DoXnFourDesc.FOU_DT_CREAT]);
        }
        if (val[DoXnFourDesc.FOU_DELAI_LIV] != -1) {
            int temp = rs.getInt(val[DoXnFourDesc.FOU_DELAI_LIV]);
            if (!rs.wasNull())
                djo.fouDelaiLiv = new Integer(temp);
        }
        if (val[DoXnFourDesc.FOU_CTRLE_FAC] != -1) {
            djo.fouCtrleFac = rs.getString(val[DoXnFourDesc.FOU_CTRLE_FAC]);
        }
        if (val[DoXnFourDesc.FOU_CPTE_COMPTA] != -1) {
            djo.fouCpteCompta = rs.getString(val[DoXnFourDesc.FOU_CPTE_COMPTA]);
        }
        if (val[DoXnFourDesc.FOU_CODE_LIBRE] != -1) {
            djo.fouCodeLibre = rs.getString(val[DoXnFourDesc.FOU_CODE_LIBRE]);
        }
        if (val[DoXnFourDesc.FOU_CODE_GUICHET] != -1) {
            djo.fouCodeGuichet = rs.getString(val[DoXnFourDesc.FOU_CODE_GUICHET]);
        }
        if (val[DoXnFourDesc.FOU_CODE_GENCOD] != -1) {
            djo.fouCodeGencod = rs.getString(val[DoXnFourDesc.FOU_CODE_GENCOD]);
        }
        if (val[DoXnFourDesc.FOU_ADR1] != -1) {
            djo.fouAdr1 = rs.getString(val[DoXnFourDesc.FOU_ADR1]);
        }
        if (val[DoXnFourDesc.FOU_VILLE] != -1) {
            djo.fouVille = rs.getString(val[DoXnFourDesc.FOU_VILLE]);
        }
        if (val[DoXnFourDesc.FOU_UTIL_MAJ] != -1) {
            djo.fouUtilMaj = rs.getString(val[DoXnFourDesc.FOU_UTIL_MAJ]);
        }
        if (val[DoXnFourDesc.FOU_TYPE_REM_DIFF] != -1) {
            int temp = rs.getInt(val[DoXnFourDesc.FOU_TYPE_REM_DIFF]);
            if (!rs.wasNull())
                djo.fouTypeRemDiff = new Integer(temp);
        }
        if (val[DoXnFourDesc.FOU_TX_REM_FAC3] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_TX_REM_FAC3]);
            if (!rs.wasNull())
                djo.fouTxRemFac3 = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_TX_REM_FAC2] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_TX_REM_FAC2]);
            if (!rs.wasNull())
                djo.fouTxRemFac2 = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_TX_REM_FAC1] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_TX_REM_FAC1]);
            if (!rs.wasNull())
                djo.fouTxRemFac1 = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_TEL] != -1) {
            djo.fouTel = rs.getString(val[DoXnFourDesc.FOU_TEL]);
        }
        if (val[DoXnFourDesc.FOU_ADR3_BANQ] != -1) {
            djo.fouAdr3Banq = rs.getString(val[DoXnFourDesc.FOU_ADR3_BANQ]);
        }
        if (val[DoXnFourDesc.FOU_ADR2_BANQ] != -1) {
            djo.fouAdr2Banq = rs.getString(val[DoXnFourDesc.FOU_ADR2_BANQ]);
        }
        if (val[DoXnFourDesc.FOU_ADR2] != -1) {
            djo.fouAdr2 = rs.getString(val[DoXnFourDesc.FOU_ADR2]);
        }
        if (val[DoXnFourDesc.FOU_ADR1_BANQ] != -1) {
            djo.fouAdr1Banq = rs.getString(val[DoXnFourDesc.FOU_ADR1_BANQ]);
        }
        if (val[DoXnFourDesc.FOU_REGIME_TVA] != -1) {
            djo.fouRegimeTva = rs.getString(val[DoXnFourDesc.FOU_REGIME_TVA]);
        }
        if (val[DoXnFourDesc.FOU_REF_NOUS] != -1) {
            djo.fouRefNous = rs.getString(val[DoXnFourDesc.FOU_REF_NOUS]);
        }
        if (val[DoXnFourDesc.FOU_POSTAL] != -1) {
            djo.fouPostal = rs.getString(val[DoXnFourDesc.FOU_POSTAL]);
        }
        if (val[DoXnFourDesc.FOU_MT_MIN_CMDE] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_MT_MIN_CMDE]);
            if (!rs.wasNull())
                djo.fouMtMinCmde = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_MT_FRANCO_PORT] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_MT_FRANCO_PORT]);
            if (!rs.wasNull())
                djo.fouMtFrancoPort = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_GROUPE_ON] != -1) {
            djo.fouGroupeOn = rs.getString(val[DoXnFourDesc.FOU_GROUPE_ON]);
        }
        if (val[DoXnFourDesc.FOU_FAX] != -1) {
            djo.fouFax = rs.getString(val[DoXnFourDesc.FOU_FAX]);
        }
        if (val[DoXnFourDesc.FOU_CEE_COEF] != -1) {
            double temp = rs.getDouble(val[DoXnFourDesc.FOU_CEE_COEF]);
            if (!rs.wasNull())
                djo.fouCeeCoef = new Double(temp);
        }
        if (val[DoXnFourDesc.FOU_CEE_ID] != -1) {
            djo.fouCeeId = rs.getString(val[DoXnFourDesc.FOU_CEE_ID]);
        }
        if (val[DoXnFourDesc.FOU_CLE_CTRLE] != -1) {
            djo.fouCleCtrle = rs.getString(val[DoXnFourDesc.FOU_CLE_CTRLE]);
        }
        if (val[DoXnFourDesc.FOU_CODE_CPTE_BANQ] != -1) {
            djo.fouCodeCpteBanq = rs.getString(val[DoXnFourDesc.FOU_CODE_CPTE_BANQ]);
        }
        if (val[DoXnFourDesc.FOU_CODE_BANQ] != -1) {
            djo.fouCodeBanq = rs.getString(val[DoXnFourDesc.FOU_CODE_BANQ]);
        }
        if (val[DoXnFourDesc.FOU_DT_MAJ] != -1) {
            djo.fouDtMaj = rs.getTimestamp(val[DoXnFourDesc.FOU_DT_MAJ]);
        }
        if (val[DoXnFourDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoXnFourDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoXnFourDesc.FOU_FOT_CODE] != -1) {
            djo.fouFotCode = rs.getString(val[DoXnFourDesc.FOU_FOT_CODE]);
        }
        if (val[DoXnFourDesc.FOU_TEL2] != -1) {
            djo.fouTel2 = rs.getString(val[DoXnFourDesc.FOU_TEL2]);
        }
        if (val[DoXnFourDesc.FOU_EMAIL] != -1) {
            djo.fouEmail = rs.getString(val[DoXnFourDesc.FOU_EMAIL]);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE] != -1) {
            djo.fouFouCode = rs.getString(val[DoXnFourDesc.FOU_FOU_CODE]);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE_AGENTI] != -1) {
            djo.fouFouCodeAgenti = rs.getString(val[DoXnFourDesc.FOU_FOU_CODE_AGENTI]);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE_AGENTE] != -1) {
            djo.fouFouCodeAgente = rs.getString(val[DoXnFourDesc.FOU_FOU_CODE_AGENTE]);
        }
        if (val[DoXnFourDesc.FOU_PAY_CODE_FAB] != -1) {
            djo.fouPayCodeFab = rs.getString(val[DoXnFourDesc.FOU_PAY_CODE_FAB]);
        }
        if (val[DoXnFourDesc.FOU_ADR1_PAYE] != -1) {
            djo.fouAdr1Paye = rs.getString(val[DoXnFourDesc.FOU_ADR1_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_ADR2_PAYE] != -1) {
            djo.fouAdr2Paye = rs.getString(val[DoXnFourDesc.FOU_ADR2_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_POSTAL_PAYE] != -1) {
            djo.fouPostalPaye = rs.getString(val[DoXnFourDesc.FOU_POSTAL_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_VILLE_PAYE] != -1) {
            djo.fouVillePaye = rs.getString(val[DoXnFourDesc.FOU_VILLE_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_PAY_CODE_PAYE] != -1) {
            djo.fouPayCodePaye = rs.getString(val[DoXnFourDesc.FOU_PAY_CODE_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_TEL1_PAYE] != -1) {
            djo.fouTel1Paye = rs.getString(val[DoXnFourDesc.FOU_TEL1_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_TEL2_PAYE] != -1) {
            djo.fouTel2Paye = rs.getString(val[DoXnFourDesc.FOU_TEL2_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_FAX_PAYE] != -1) {
            djo.fouFaxPaye = rs.getString(val[DoXnFourDesc.FOU_FAX_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_EMAIL_PAYE] != -1) {
            djo.fouEmailPaye = rs.getString(val[DoXnFourDesc.FOU_EMAIL_PAYE]);
        }
        if (val[DoXnFourDesc.FOU_DT_ACCEPT] != -1) {
            djo.fouDtAccept = rs.getTimestamp(val[DoXnFourDesc.FOU_DT_ACCEPT]);
        }
        if (val[DoXnFourDesc.FOU_DT_FIN_TRAV] != -1) {
            djo.fouDtFinTrav = rs.getTimestamp(val[DoXnFourDesc.FOU_DT_FIN_TRAV]);
        }
        if (val[DoXnFourDesc.FOU_SIGNATAIRE] != -1) {
            djo.fouSignataire = rs.getString(val[DoXnFourDesc.FOU_SIGNATAIRE]);
        }
        if (val[DoXnFourDesc.FOU_QUAL_INDICE] != -1) {
            djo.fouQualIndice = rs.getString(val[DoXnFourDesc.FOU_QUAL_INDICE]);
        }
        if (val[DoXnFourDesc.FOU_STAT_ETIQ] != -1) {
            djo.fouStatEtiq = rs.getString(val[DoXnFourDesc.FOU_STAT_ETIQ]);
        }
        if (val[DoXnFourDesc.FOU_STAT_CQ] != -1) {
            djo.fouStatCq = rs.getString(val[DoXnFourDesc.FOU_STAT_CQ]);
        }
        if (val[DoXnFourDesc.FOU_CONSIGNE] != -1) {
            djo.fouConsigne = rs.getString(val[DoXnFourDesc.FOU_CONSIGNE]);
        }
        if (val[DoXnFourDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoXnFourDesc.Z_STATUS]);
        }
        if (val[DoXnFourDesc.FOU_ETAT] != -1) {
            djo.fouEtat = rs.getString(val[DoXnFourDesc.FOU_ETAT]);
        }
        if (val[DoXnFourDesc.FOU_OBS] != -1) {
            djo.fouObs = rs.getString(val[DoXnFourDesc.FOU_OBS]);
        }
        if (val[DoXnFourDesc.FOU_CDE_COMPOSANTS] != -1) {
            int temp = rs.getInt(val[DoXnFourDesc.FOU_CDE_COMPOSANTS]);
            if (!rs.wasNull())
                djo.fouCdeComposants = new Integer(temp);
        }
        if (val[DoXnFourDesc.FOU_DEP_CODE] != -1) {
            djo.fouDepCode = rs.getString(val[DoXnFourDesc.FOU_DEP_CODE]);
        }
        if (val[DoXnFourDesc.FOU_OK_VISITE] != -1) {
            int temp = rs.getInt(val[DoXnFourDesc.FOU_OK_VISITE]);
            if (!rs.wasNull())
                djo.fouOkVisite = new Integer(temp);
        }
        if (val[DoXnFourDesc.FOU_PREST_VISITE] != -1) {
            djo.fouPrestVisite = rs.getString(val[DoXnFourDesc.FOU_PREST_VISITE]);
        }
        if (val[DoXnFourDesc.FOU_DOMAINE] != -1) {
            djo.fouDomaine = rs.getString(val[DoXnFourDesc.FOU_DOMAINE]);
        }
        if (val[DoXnFourDesc.FOU_DEP_SOC_CODE] != -1) {
            djo.fouDepSocCode = rs.getString(val[DoXnFourDesc.FOU_DEP_SOC_CODE]);
        }
        if (val[DoXnFourDesc.FOU_DEP_CODE_TISSU] != -1) {
            djo.fouDepCodeTissu = rs.getString(val[DoXnFourDesc.FOU_DEP_CODE_TISSU]);
        }
        if (val[DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU] != -1) {
            djo.fouDepSocCodeTissu = rs.getString(val[DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoXnFourDesc.FOU_RELIQUAT_ON] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_RELIQUAT_ON], fouReliquatOn);
        }
        if (val[DoXnFourDesc.FOU_TX_ESC] > 0) {
            if (fouTxEsc == null)
                stmt.setNull(val[DoXnFourDesc.FOU_TX_ESC], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_TX_ESC], fouTxEsc.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE_PX] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FOU_CODE_PX], fouFouCodePx);
        }
        if (val[DoXnFourDesc.FOU_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CODE], fouCode);
        }
        if (val[DoXnFourDesc.FOU_FFO_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FFO_CODE], fouFfoCode);
        }
        if (val[DoXnFourDesc.FOU_NOM] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_NOM], fouNom);
        }
        if (val[DoXnFourDesc.FOU_ZEC_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ZEC_CODE], fouZecCode);
        }
        if (val[DoXnFourDesc.FOU_TYC_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_TYC_CODE], fouTycCode);
        }
        if (val[DoXnFourDesc.FOU_PAY_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_PAY_CODE], fouPayCode);
        }
        if (val[DoXnFourDesc.FOU_LAN_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_LAN_CODE], fouLanCode);
        }
        if (val[DoXnFourDesc.FOU_DEV_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_DEV_CODE], fouDevCode);
        }
        if (val[DoXnFourDesc.FOU_MTR_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_MTR_CODE], fouMtrCode);
        }
        if (val[DoXnFourDesc.FOU_MRG_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_MRG_CODE], fouMrgCode);
        }
        if (val[DoXnFourDesc.FOU_CRG_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CRG_CODE], fouCrgCode);
        }
        if (val[DoXnFourDesc.FOU_MLI_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_MLI_CODE], fouMliCode);
        }
        if (val[DoXnFourDesc.FOU_ADR3] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR3], fouAdr3);
        }
        if (val[DoXnFourDesc.FOU_BANQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_BANQ], fouBanq);
        }
        if (val[DoXnFourDesc.FOU_DT_DER_CMDE] > 0) {
            stmt.setTimestamp(val[DoXnFourDesc.FOU_DT_DER_CMDE], fouDtDerCmde);
        }
        if (val[DoXnFourDesc.FOU_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoXnFourDesc.FOU_DT_CREAT], fouDtCreat);
        }
        if (val[DoXnFourDesc.FOU_DELAI_LIV] > 0) {
            if (fouDelaiLiv == null)
                stmt.setNull(val[DoXnFourDesc.FOU_DELAI_LIV], 3);
            else
                stmt.setInt(val[DoXnFourDesc.FOU_DELAI_LIV], fouDelaiLiv.intValue());
        }
        if (val[DoXnFourDesc.FOU_CTRLE_FAC] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CTRLE_FAC], fouCtrleFac);
        }
        if (val[DoXnFourDesc.FOU_CPTE_COMPTA] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CPTE_COMPTA], fouCpteCompta);
        }
        if (val[DoXnFourDesc.FOU_CODE_LIBRE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CODE_LIBRE], fouCodeLibre);
        }
        if (val[DoXnFourDesc.FOU_CODE_GUICHET] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CODE_GUICHET], fouCodeGuichet);
        }
        if (val[DoXnFourDesc.FOU_CODE_GENCOD] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CODE_GENCOD], fouCodeGencod);
        }
        if (val[DoXnFourDesc.FOU_ADR1] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR1], fouAdr1);
        }
        if (val[DoXnFourDesc.FOU_VILLE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_VILLE], fouVille);
        }
        if (val[DoXnFourDesc.FOU_UTIL_MAJ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_UTIL_MAJ], fouUtilMaj);
        }
        if (val[DoXnFourDesc.FOU_TYPE_REM_DIFF] > 0) {
            if (fouTypeRemDiff == null)
                stmt.setNull(val[DoXnFourDesc.FOU_TYPE_REM_DIFF], 3);
            else
                stmt.setInt(val[DoXnFourDesc.FOU_TYPE_REM_DIFF], fouTypeRemDiff.intValue());
        }
        if (val[DoXnFourDesc.FOU_TX_REM_FAC3] > 0) {
            if (fouTxRemFac3 == null)
                stmt.setNull(val[DoXnFourDesc.FOU_TX_REM_FAC3], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_TX_REM_FAC3], fouTxRemFac3.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_TX_REM_FAC2] > 0) {
            if (fouTxRemFac2 == null)
                stmt.setNull(val[DoXnFourDesc.FOU_TX_REM_FAC2], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_TX_REM_FAC2], fouTxRemFac2.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_TX_REM_FAC1] > 0) {
            if (fouTxRemFac1 == null)
                stmt.setNull(val[DoXnFourDesc.FOU_TX_REM_FAC1], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_TX_REM_FAC1], fouTxRemFac1.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_TEL] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_TEL], fouTel);
        }
        if (val[DoXnFourDesc.FOU_ADR3_BANQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR3_BANQ], fouAdr3Banq);
        }
        if (val[DoXnFourDesc.FOU_ADR2_BANQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR2_BANQ], fouAdr2Banq);
        }
        if (val[DoXnFourDesc.FOU_ADR2] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR2], fouAdr2);
        }
        if (val[DoXnFourDesc.FOU_ADR1_BANQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR1_BANQ], fouAdr1Banq);
        }
        if (val[DoXnFourDesc.FOU_REGIME_TVA] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_REGIME_TVA], fouRegimeTva);
        }
        if (val[DoXnFourDesc.FOU_REF_NOUS] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_REF_NOUS], fouRefNous);
        }
        if (val[DoXnFourDesc.FOU_POSTAL] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_POSTAL], fouPostal);
        }
        if (val[DoXnFourDesc.FOU_MT_MIN_CMDE] > 0) {
            if (fouMtMinCmde == null)
                stmt.setNull(val[DoXnFourDesc.FOU_MT_MIN_CMDE], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_MT_MIN_CMDE], fouMtMinCmde.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_MT_FRANCO_PORT] > 0) {
            if (fouMtFrancoPort == null)
                stmt.setNull(val[DoXnFourDesc.FOU_MT_FRANCO_PORT], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_MT_FRANCO_PORT], fouMtFrancoPort.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_GROUPE_ON] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_GROUPE_ON], fouGroupeOn);
        }
        if (val[DoXnFourDesc.FOU_FAX] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FAX], fouFax);
        }
        if (val[DoXnFourDesc.FOU_CEE_COEF] > 0) {
            if (fouCeeCoef == null)
                stmt.setNull(val[DoXnFourDesc.FOU_CEE_COEF], 3);
            else
                stmt.setDouble(val[DoXnFourDesc.FOU_CEE_COEF], fouCeeCoef.doubleValue());
        }
        if (val[DoXnFourDesc.FOU_CEE_ID] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CEE_ID], fouCeeId);
        }
        if (val[DoXnFourDesc.FOU_CLE_CTRLE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CLE_CTRLE], fouCleCtrle);
        }
        if (val[DoXnFourDesc.FOU_CODE_CPTE_BANQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CODE_CPTE_BANQ], fouCodeCpteBanq);
        }
        if (val[DoXnFourDesc.FOU_CODE_BANQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CODE_BANQ], fouCodeBanq);
        }
        if (val[DoXnFourDesc.FOU_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoXnFourDesc.FOU_DT_MAJ], fouDtMaj);
        }
        if (val[DoXnFourDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoXnFourDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoXnFourDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoXnFourDesc.FOU_FOT_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FOT_CODE], fouFotCode);
        }
        if (val[DoXnFourDesc.FOU_TEL2] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_TEL2], fouTel2);
        }
        if (val[DoXnFourDesc.FOU_EMAIL] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_EMAIL], fouEmail);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FOU_CODE], fouFouCode);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE_AGENTI] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FOU_CODE_AGENTI], fouFouCodeAgenti);
        }
        if (val[DoXnFourDesc.FOU_FOU_CODE_AGENTE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FOU_CODE_AGENTE], fouFouCodeAgente);
        }
        if (val[DoXnFourDesc.FOU_PAY_CODE_FAB] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_PAY_CODE_FAB], fouPayCodeFab);
        }
        if (val[DoXnFourDesc.FOU_ADR1_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR1_PAYE], fouAdr1Paye);
        }
        if (val[DoXnFourDesc.FOU_ADR2_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ADR2_PAYE], fouAdr2Paye);
        }
        if (val[DoXnFourDesc.FOU_POSTAL_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_POSTAL_PAYE], fouPostalPaye);
        }
        if (val[DoXnFourDesc.FOU_VILLE_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_VILLE_PAYE], fouVillePaye);
        }
        if (val[DoXnFourDesc.FOU_PAY_CODE_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_PAY_CODE_PAYE], fouPayCodePaye);
        }
        if (val[DoXnFourDesc.FOU_TEL1_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_TEL1_PAYE], fouTel1Paye);
        }
        if (val[DoXnFourDesc.FOU_TEL2_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_TEL2_PAYE], fouTel2Paye);
        }
        if (val[DoXnFourDesc.FOU_FAX_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_FAX_PAYE], fouFaxPaye);
        }
        if (val[DoXnFourDesc.FOU_EMAIL_PAYE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_EMAIL_PAYE], fouEmailPaye);
        }
        if (val[DoXnFourDesc.FOU_DT_ACCEPT] > 0) {
            stmt.setTimestamp(val[DoXnFourDesc.FOU_DT_ACCEPT], fouDtAccept);
        }
        if (val[DoXnFourDesc.FOU_DT_FIN_TRAV] > 0) {
            stmt.setTimestamp(val[DoXnFourDesc.FOU_DT_FIN_TRAV], fouDtFinTrav);
        }
        if (val[DoXnFourDesc.FOU_SIGNATAIRE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_SIGNATAIRE], fouSignataire);
        }
        if (val[DoXnFourDesc.FOU_QUAL_INDICE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_QUAL_INDICE], fouQualIndice);
        }
        if (val[DoXnFourDesc.FOU_STAT_ETIQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_STAT_ETIQ], fouStatEtiq);
        }
        if (val[DoXnFourDesc.FOU_STAT_CQ] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_STAT_CQ], fouStatCq);
        }
        if (val[DoXnFourDesc.FOU_CONSIGNE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_CONSIGNE], fouConsigne);
        }
        if (val[DoXnFourDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoXnFourDesc.Z_STATUS], zStatus);
        }
        if (val[DoXnFourDesc.FOU_ETAT] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_ETAT], fouEtat);
        }
        if (val[DoXnFourDesc.FOU_OBS] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_OBS], fouObs);
        }
        if (val[DoXnFourDesc.FOU_CDE_COMPOSANTS] > 0) {
            if (fouCdeComposants == null)
                stmt.setNull(val[DoXnFourDesc.FOU_CDE_COMPOSANTS], 3);
            else
                stmt.setInt(val[DoXnFourDesc.FOU_CDE_COMPOSANTS], fouCdeComposants.intValue());
        }
        if (val[DoXnFourDesc.FOU_DEP_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_DEP_CODE], fouDepCode);
        }
        if (val[DoXnFourDesc.FOU_OK_VISITE] > 0) {
            if (fouOkVisite == null)
                stmt.setNull(val[DoXnFourDesc.FOU_OK_VISITE], 3);
            else
                stmt.setInt(val[DoXnFourDesc.FOU_OK_VISITE], fouOkVisite.intValue());
        }
        if (val[DoXnFourDesc.FOU_PREST_VISITE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_PREST_VISITE], fouPrestVisite);
        }
        if (val[DoXnFourDesc.FOU_DOMAINE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_DOMAINE], fouDomaine);
        }
        if (val[DoXnFourDesc.FOU_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_DEP_SOC_CODE], fouDepSocCode);
        }
        if (val[DoXnFourDesc.FOU_DEP_CODE_TISSU] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_DEP_CODE_TISSU], fouDepCodeTissu);
        }
        if (val[DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU] > 0) {
            stmt.setString(val[DoXnFourDesc.FOU_DEP_SOC_CODE_TISSU], fouDepSocCodeTissu);
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
        DoXnFour[] result = null;
        params = request.getParameterValues("fouReliquatOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouReliquatOn(localVal);
            }
        }
        params = request.getParameterValues("fouTxEsc");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTxEsc((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouFouCodePx");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFouCodePx(localVal);
            }
        }
        params = request.getParameterValues("fouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCode(localVal);
            }
        }
        params = request.getParameterValues("fouFfoCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFfoCode(localVal);
            }
        }
        params = request.getParameterValues("fouNom");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouNom(localVal);
            }
        }
        params = request.getParameterValues("fouZecCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouZecCode(localVal);
            }
        }
        params = request.getParameterValues("fouTycCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTycCode(localVal);
            }
        }
        params = request.getParameterValues("fouPayCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouPayCode(localVal);
            }
        }
        params = request.getParameterValues("fouLanCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouLanCode(localVal);
            }
        }
        params = request.getParameterValues("fouDevCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDevCode(localVal);
            }
        }
        params = request.getParameterValues("fouMtrCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouMtrCode(localVal);
            }
        }
        params = request.getParameterValues("fouMrgCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouMrgCode(localVal);
            }
        }
        params = request.getParameterValues("fouCrgCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCrgCode(localVal);
            }
        }
        params = request.getParameterValues("fouMliCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouMliCode(localVal);
            }
        }
        params = request.getParameterValues("fouAdr3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr3(localVal);
            }
        }
        params = request.getParameterValues("fouBanq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouBanq(localVal);
            }
        }
        params = request.getParameterValues("fouDtDerCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDtDerCmde((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("fouDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("fouDelaiLiv");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDelaiLiv((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("fouCtrleFac");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCtrleFac(localVal);
            }
        }
        params = request.getParameterValues("fouCpteCompta");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCpteCompta(localVal);
            }
        }
        params = request.getParameterValues("fouCodeLibre");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCodeLibre(localVal);
            }
        }
        params = request.getParameterValues("fouCodeGuichet");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCodeGuichet(localVal);
            }
        }
        params = request.getParameterValues("fouCodeGencod");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCodeGencod(localVal);
            }
        }
        params = request.getParameterValues("fouAdr1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr1(localVal);
            }
        }
        params = request.getParameterValues("fouVille");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouVille(localVal);
            }
        }
        params = request.getParameterValues("fouUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("fouTypeRemDiff");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTypeRemDiff((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("fouTxRemFac3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTxRemFac3((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouTxRemFac2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTxRemFac2((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouTxRemFac1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTxRemFac1((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouTel");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTel(localVal);
            }
        }
        params = request.getParameterValues("fouAdr3Banq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr3Banq(localVal);
            }
        }
        params = request.getParameterValues("fouAdr2Banq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr2Banq(localVal);
            }
        }
        params = request.getParameterValues("fouAdr2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr2(localVal);
            }
        }
        params = request.getParameterValues("fouAdr1Banq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr1Banq(localVal);
            }
        }
        params = request.getParameterValues("fouRegimeTva");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouRegimeTva(localVal);
            }
        }
        params = request.getParameterValues("fouRefNous");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouRefNous(localVal);
            }
        }
        params = request.getParameterValues("fouPostal");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouPostal(localVal);
            }
        }
        params = request.getParameterValues("fouMtMinCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouMtMinCmde((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouMtFrancoPort");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouMtFrancoPort((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouGroupeOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouGroupeOn(localVal);
            }
        }
        params = request.getParameterValues("fouFax");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFax(localVal);
            }
        }
        params = request.getParameterValues("fouCeeCoef");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCeeCoef((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("fouCeeId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCeeId(localVal);
            }
        }
        params = request.getParameterValues("fouCleCtrle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCleCtrle(localVal);
            }
        }
        params = request.getParameterValues("fouCodeCpteBanq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCodeCpteBanq(localVal);
            }
        }
        params = request.getParameterValues("fouCodeBanq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCodeBanq(localVal);
            }
        }
        params = request.getParameterValues("fouDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
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
        params = request.getParameterValues("fouFotCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFotCode(localVal);
            }
        }
        params = request.getParameterValues("fouTel2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTel2(localVal);
            }
        }
        params = request.getParameterValues("fouEmail");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouEmail(localVal);
            }
        }
        params = request.getParameterValues("fouFouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFouCode(localVal);
            }
        }
        params = request.getParameterValues("fouFouCodeAgenti");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFouCodeAgenti(localVal);
            }
        }
        params = request.getParameterValues("fouFouCodeAgente");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFouCodeAgente(localVal);
            }
        }
        params = request.getParameterValues("fouPayCodeFab");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouPayCodeFab(localVal);
            }
        }
        params = request.getParameterValues("fouAdr1Paye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr1Paye(localVal);
            }
        }
        params = request.getParameterValues("fouAdr2Paye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouAdr2Paye(localVal);
            }
        }
        params = request.getParameterValues("fouPostalPaye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouPostalPaye(localVal);
            }
        }
        params = request.getParameterValues("fouVillePaye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouVillePaye(localVal);
            }
        }
        params = request.getParameterValues("fouPayCodePaye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouPayCodePaye(localVal);
            }
        }
        params = request.getParameterValues("fouTel1Paye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTel1Paye(localVal);
            }
        }
        params = request.getParameterValues("fouTel2Paye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouTel2Paye(localVal);
            }
        }
        params = request.getParameterValues("fouFaxPaye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouFaxPaye(localVal);
            }
        }
        params = request.getParameterValues("fouEmailPaye");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouEmailPaye(localVal);
            }
        }
        params = request.getParameterValues("fouDtAccept");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDtAccept((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("fouDtFinTrav");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDtFinTrav((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("fouSignataire");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouSignataire(localVal);
            }
        }
        params = request.getParameterValues("fouQualIndice");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouQualIndice(localVal);
            }
        }
        params = request.getParameterValues("fouStatEtiq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouStatEtiq(localVal);
            }
        }
        params = request.getParameterValues("fouStatCq");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouStatCq(localVal);
            }
        }
        params = request.getParameterValues("fouConsigne");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouConsigne(localVal);
            }
        }
        params = request.getParameterValues("zStatus");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
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
        params = request.getParameterValues("fouEtat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouEtat(localVal);
            }
        }
        params = request.getParameterValues("fouObs");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouObs(localVal);
            }
        }
        params = request.getParameterValues("fouCdeComposants");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouCdeComposants((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("fouDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDepCode(localVal);
            }
        }
        params = request.getParameterValues("fouOkVisite");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouOkVisite((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("fouPrestVisite");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouPrestVisite(localVal);
            }
        }
        params = request.getParameterValues("fouDomaine");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDomaine(localVal);
            }
        }
        params = request.getParameterValues("fouDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("fouDepCodeTissu");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDepCodeTissu(localVal);
            }
        }
        params = request.getParameterValues("fouDepSocCodeTissu");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnFour[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnFour();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setFouDepSocCodeTissu(localVal);
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

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

public class DoDtCmdeAchatTete implements DataObject {

    private static final IDoDescription description = new DoDtCmdeAchatTeteDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[67];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_CMDE_ACHAT_TETE"};
    //variables correspondant à la table DT_CMDE_ACHAT_TETE
    private String catDepCode = null;
    private String catDepSocCode = null;
    private Integer catNoCmde = null;
    private Integer catNoVersion = null;
    private Integer catNoCmdeMere = null;
    private Integer catNoVersionMere = null;
    private Integer catNoRecep = null;
    private Integer catNoVersionRecep = null;
    private String catDepCodeLiv = null;
    private String catDepSocCodeLiv = null;
    private String catDepCodeDest = null;
    private String catDepSocCodeDest = null;
    private Integer catDerNoVersion = null;
    private String catDevCode = null;
    private Double catTxDevise = null;
    private Double catTxEsc = null;
    private String catConsignation = null;
    private String catControleQual = null;
    private Timestamp catDtCmde = null;
    private Timestamp catDtConfirm = null;
    private Timestamp catDtDepart1 = null;
    private Timestamp catDtDepart2 = null;
    private Timestamp catDtEdition1 = null;
    private Timestamp catDtEdition2 = null;
    private Timestamp catDtEven = null;
    private Timestamp catDtPrevLivr1 = null;
    private Timestamp catDtPrevLivr2 = null;
    private Timestamp catDtRdv = null;
    private Timestamp catDtValeur = null;
    private String catEtiquettage = null;
    private String catEveCode = null;
    private String catFouCode = null;
    private String catInfFouCode = null;
    private String catInfFouNom = null;
    private String catInfFouTitre = null;
    private Integer catInfNoOrdre = null;
    private String catLanCode = null;
    private String catLibre1 = null;
    private String catLibre2 = null;
    private String catLibre3 = null;
    private String catMliCode = null;
    private String catModCode = null;
    private String catMtrCode = null;
    private String catPayCode = null;
    private String catPieceColis = null;
    private String catRefCmde = null;
    private String catRegimeTva = null;
    private String catReliquatOn = null;
    private String catFouCodeTransp = null;
    private String catStaCode = null;
    private String catTypeOrdre = null;
    private Double catTxRemFac1 = null;
    private Double catTxRemFac2 = null;
    private Double catTxRemFac3 = null;
    private String catTydCode = null;
    private String catTypeCmde = null;
    private String catTypePort = null;
    private String catIndex = null;
    private Timestamp catDtCreat = null;
    private Timestamp catDtMaj = null;
    private String catUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private String catValidation = null;
    private Timestamp catDtPrevLivr0 = null;
    private String catRetourGp = null;
    private String catEtatGp = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtCmdeAchatTete() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtCmdeAchatTete(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtCmdeAchatTete(DoDtCmdeAchatTete arg) {
        setCatDepCode(arg.catDepCode);
        setCatDepSocCode(arg.catDepSocCode);
        setCatNoCmde(arg.catNoCmde);
        setCatNoVersion(arg.catNoVersion);
        setCatNoCmdeMere(arg.catNoCmdeMere);
        setCatNoVersionMere(arg.catNoVersionMere);
        setCatNoRecep(arg.catNoRecep);
        setCatNoVersionRecep(arg.catNoVersionRecep);
        setCatDepCodeLiv(arg.catDepCodeLiv);
        setCatDepSocCodeLiv(arg.catDepSocCodeLiv);
        setCatDepCodeDest(arg.catDepCodeDest);
        setCatDepSocCodeDest(arg.catDepSocCodeDest);
        setCatDerNoVersion(arg.catDerNoVersion);
        setCatDevCode(arg.catDevCode);
        setCatTxDevise(arg.catTxDevise);
        setCatTxEsc(arg.catTxEsc);
        setCatConsignation(arg.catConsignation);
        setCatControleQual(arg.catControleQual);
        setCatDtCmde(arg.catDtCmde);
        setCatDtConfirm(arg.catDtConfirm);
        setCatDtDepart1(arg.catDtDepart1);
        setCatDtDepart2(arg.catDtDepart2);
        setCatDtEdition1(arg.catDtEdition1);
        setCatDtEdition2(arg.catDtEdition2);
        setCatDtEven(arg.catDtEven);
        setCatDtPrevLivr1(arg.catDtPrevLivr1);
        setCatDtPrevLivr2(arg.catDtPrevLivr2);
        setCatDtRdv(arg.catDtRdv);
        setCatDtValeur(arg.catDtValeur);
        setCatEtiquettage(arg.catEtiquettage);
        setCatEveCode(arg.catEveCode);
        setCatFouCode(arg.catFouCode);
        setCatInfFouCode(arg.catInfFouCode);
        setCatInfFouNom(arg.catInfFouNom);
        setCatInfFouTitre(arg.catInfFouTitre);
        setCatInfNoOrdre(arg.catInfNoOrdre);
        setCatLanCode(arg.catLanCode);
        setCatLibre1(arg.catLibre1);
        setCatLibre2(arg.catLibre2);
        setCatLibre3(arg.catLibre3);
        setCatMliCode(arg.catMliCode);
        setCatModCode(arg.catModCode);
        setCatMtrCode(arg.catMtrCode);
        setCatPayCode(arg.catPayCode);
        setCatPieceColis(arg.catPieceColis);
        setCatRefCmde(arg.catRefCmde);
        setCatRegimeTva(arg.catRegimeTva);
        setCatReliquatOn(arg.catReliquatOn);
        setCatFouCodeTransp(arg.catFouCodeTransp);
        setCatStaCode(arg.catStaCode);
        setCatTypeOrdre(arg.catTypeOrdre);
        setCatTxRemFac1(arg.catTxRemFac1);
        setCatTxRemFac2(arg.catTxRemFac2);
        setCatTxRemFac3(arg.catTxRemFac3);
        setCatTydCode(arg.catTydCode);
        setCatTypeCmde(arg.catTypeCmde);
        setCatTypePort(arg.catTypePort);
        setCatIndex(arg.catIndex);
        setCatDtCreat(arg.catDtCreat);
        setCatDtMaj(arg.catDtMaj);
        setCatUtilMaj(arg.catUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setCatValidation(arg.catValidation);
        setCatDtPrevLivr0(arg.catDtPrevLivr0);
        setCatRetourGp(arg.catRetourGp);
        setCatEtatGp(arg.catEtatGp);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtCmdeAchatTete(String newSql, Object[] newParam) {
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

    public String getCatDepCode() {
        return catDepCode;
    }

    public String getCatDepSocCode() {
        return catDepSocCode;
    }

    public Integer getCatNoCmde() {
        return catNoCmde;
    }

    public Integer getCatNoVersion() {
        return catNoVersion;
    }

    public Integer getCatNoCmdeMere() {
        return catNoCmdeMere;
    }

    public Integer getCatNoVersionMere() {
        return catNoVersionMere;
    }

    public Integer getCatNoRecep() {
        return catNoRecep;
    }

    public Integer getCatNoVersionRecep() {
        return catNoVersionRecep;
    }

    public String getCatDepCodeLiv() {
        return catDepCodeLiv;
    }

    public String getCatDepSocCodeLiv() {
        return catDepSocCodeLiv;
    }

    public String getCatDepCodeDest() {
        return catDepCodeDest;
    }

    public String getCatDepSocCodeDest() {
        return catDepSocCodeDest;
    }

    public Integer getCatDerNoVersion() {
        return catDerNoVersion;
    }

    public String getCatDevCode() {
        return catDevCode;
    }

    public Double getCatTxDevise() {
        return catTxDevise;
    }

    public Double getCatTxEsc() {
        return catTxEsc;
    }

    public String getCatConsignation() {
        return catConsignation;
    }

    public String getCatControleQual() {
        return catControleQual;
    }

    public Timestamp getCatDtCmde() {
        return catDtCmde;
    }

    public Timestamp getCatDtConfirm() {
        return catDtConfirm;
    }

    public Timestamp getCatDtDepart1() {
        return catDtDepart1;
    }

    public Timestamp getCatDtDepart2() {
        return catDtDepart2;
    }

    public Timestamp getCatDtEdition1() {
        return catDtEdition1;
    }

    public Timestamp getCatDtEdition2() {
        return catDtEdition2;
    }

    public Timestamp getCatDtEven() {
        return catDtEven;
    }

    public Timestamp getCatDtPrevLivr1() {
        return catDtPrevLivr1;
    }

    public Timestamp getCatDtPrevLivr2() {
        return catDtPrevLivr2;
    }

    public Timestamp getCatDtRdv() {
        return catDtRdv;
    }

    public Timestamp getCatDtValeur() {
        return catDtValeur;
    }

    public String getCatEtiquettage() {
        return catEtiquettage;
    }

    public String getCatEveCode() {
        return catEveCode;
    }

    public String getCatFouCode() {
        return catFouCode;
    }

    public String getCatInfFouCode() {
        return catInfFouCode;
    }

    public String getCatInfFouNom() {
        return catInfFouNom;
    }

    public String getCatInfFouTitre() {
        return catInfFouTitre;
    }

    public Integer getCatInfNoOrdre() {
        return catInfNoOrdre;
    }

    public String getCatLanCode() {
        return catLanCode;
    }

    public String getCatLibre1() {
        return catLibre1;
    }

    public String getCatLibre2() {
        return catLibre2;
    }

    public String getCatLibre3() {
        return catLibre3;
    }

    public String getCatMliCode() {
        return catMliCode;
    }

    public String getCatModCode() {
        return catModCode;
    }

    public String getCatMtrCode() {
        return catMtrCode;
    }

    public String getCatPayCode() {
        return catPayCode;
    }

    public String getCatPieceColis() {
        return catPieceColis;
    }

    public String getCatRefCmde() {
        return catRefCmde;
    }

    public String getCatRegimeTva() {
        return catRegimeTva;
    }

    public String getCatReliquatOn() {
        return catReliquatOn;
    }

    public String getCatFouCodeTransp() {
        return catFouCodeTransp;
    }

    public String getCatStaCode() {
        return catStaCode;
    }

    public String getCatTypeOrdre() {
        return catTypeOrdre;
    }

    public Double getCatTxRemFac1() {
        return catTxRemFac1;
    }

    public Double getCatTxRemFac2() {
        return catTxRemFac2;
    }

    public Double getCatTxRemFac3() {
        return catTxRemFac3;
    }

    public String getCatTydCode() {
        return catTydCode;
    }

    public String getCatTypeCmde() {
        return catTypeCmde;
    }

    public String getCatTypePort() {
        return catTypePort;
    }

    public String getCatIndex() {
        return catIndex;
    }

    public Timestamp getCatDtCreat() {
        return catDtCreat;
    }

    public Timestamp getCatDtMaj() {
        return catDtMaj;
    }

    public String getCatUtilMaj() {
        return catUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getCatValidation() {
        return catValidation;
    }

    public Timestamp getCatDtPrevLivr0() {
        return catDtPrevLivr0;
    }

    public String getCatRetourGp() {
        return catRetourGp;
    }

    public String getCatEtatGp() {
        return catEtatGp;
    }

    public void setCatDepCode(String newCatDepCode) {
        catDepCode = newCatDepCode;
    }

    public void setCatDepSocCode(String newCatDepSocCode) {
        catDepSocCode = newCatDepSocCode;
    }

    public void setCatNoCmde(Integer newCatNoCmde) {
        catNoCmde = newCatNoCmde;
    }

    public void setCatNoVersion(Integer newCatNoVersion) {
        catNoVersion = newCatNoVersion;
    }

    public void setCatNoCmdeMere(Integer newCatNoCmdeMere) {
        catNoCmdeMere = newCatNoCmdeMere;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE] = 1;
    }

    public void setCatNoVersionMere(Integer newCatNoVersionMere) {
        catNoVersionMere = newCatNoVersionMere;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE] = 1;
    }

    public void setCatNoRecep(Integer newCatNoRecep) {
        catNoRecep = newCatNoRecep;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_NO_RECEP] = 1;
    }

    public void setCatNoVersionRecep(Integer newCatNoVersionRecep) {
        catNoVersionRecep = newCatNoVersionRecep;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP] = 1;
    }

    public void setCatDepCodeLiv(String newCatDepCodeLiv) {
        catDepCodeLiv = newCatDepCodeLiv;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV] = 1;
    }

    public void setCatDepSocCodeLiv(String newCatDepSocCodeLiv) {
        catDepSocCodeLiv = newCatDepSocCodeLiv;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV] = 1;
    }

    public void setCatDepCodeDest(String newCatDepCodeDest) {
        catDepCodeDest = newCatDepCodeDest;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST] = 1;
    }

    public void setCatDepSocCodeDest(String newCatDepSocCodeDest) {
        catDepSocCodeDest = newCatDepSocCodeDest;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST] = 1;
    }

    public void setCatDerNoVersion(Integer newCatDerNoVersion) {
        catDerNoVersion = newCatDerNoVersion;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION] = 1;
    }

    public void setCatDevCode(String newCatDevCode) {
        catDevCode = newCatDevCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DEV_CODE] = 1;
    }

    public void setCatTxDevise(Double newCatTxDevise) {
        catTxDevise = newCatTxDevise;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE] = 1;
    }

    public void setCatTxEsc(Double newCatTxEsc) {
        catTxEsc = newCatTxEsc;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TX_ESC] = 1;
    }

    public void setCatConsignation(String newCatConsignation) {
        catConsignation = newCatConsignation;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION] = 1;
    }

    public void setCatControleQual(String newCatControleQual) {
        catControleQual = newCatControleQual;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL] = 1;
    }

    public void setCatDtCmde(Timestamp newCatDtCmde) {
        catDtCmde = newCatDtCmde;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_CMDE] = 1;
    }

    public void setCatDtConfirm(Timestamp newCatDtConfirm) {
        catDtConfirm = newCatDtConfirm;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM] = 1;
    }

    public void setCatDtDepart1(Timestamp newCatDtDepart1) {
        catDtDepart1 = newCatDtDepart1;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1] = 1;
    }

    public void setCatDtDepart2(Timestamp newCatDtDepart2) {
        catDtDepart2 = newCatDtDepart2;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2] = 1;
    }

    public void setCatDtEdition1(Timestamp newCatDtEdition1) {
        catDtEdition1 = newCatDtEdition1;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1] = 1;
    }

    public void setCatDtEdition2(Timestamp newCatDtEdition2) {
        catDtEdition2 = newCatDtEdition2;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2] = 1;
    }

    public void setCatDtEven(Timestamp newCatDtEven) {
        catDtEven = newCatDtEven;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_EVEN] = 1;
    }

    public void setCatDtPrevLivr1(Timestamp newCatDtPrevLivr1) {
        catDtPrevLivr1 = newCatDtPrevLivr1;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1] = 1;
    }

    public void setCatDtPrevLivr2(Timestamp newCatDtPrevLivr2) {
        catDtPrevLivr2 = newCatDtPrevLivr2;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2] = 1;
    }

    public void setCatDtRdv(Timestamp newCatDtRdv) {
        catDtRdv = newCatDtRdv;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_RDV] = 1;
    }

    public void setCatDtValeur(Timestamp newCatDtValeur) {
        catDtValeur = newCatDtValeur;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR] = 1;
    }

    public void setCatEtiquettage(String newCatEtiquettage) {
        catEtiquettage = newCatEtiquettage;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE] = 1;
    }

    public void setCatEveCode(String newCatEveCode) {
        catEveCode = newCatEveCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_EVE_CODE] = 1;
    }

    public void setCatFouCode(String newCatFouCode) {
        catFouCode = newCatFouCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE] = 1;
    }

    public void setCatInfFouCode(String newCatInfFouCode) {
        catInfFouCode = newCatInfFouCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE] = 1;
    }

    public void setCatInfFouNom(String newCatInfFouNom) {
        catInfFouNom = newCatInfFouNom;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM] = 1;
    }

    public void setCatInfFouTitre(String newCatInfFouTitre) {
        catInfFouTitre = newCatInfFouTitre;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE] = 1;
    }

    public void setCatInfNoOrdre(Integer newCatInfNoOrdre) {
        catInfNoOrdre = newCatInfNoOrdre;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE] = 1;
    }

    public void setCatLanCode(String newCatLanCode) {
        catLanCode = newCatLanCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_LAN_CODE] = 1;
    }

    public void setCatLibre1(String newCatLibre1) {
        catLibre1 = newCatLibre1;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_LIBRE1] = 1;
    }

    public void setCatLibre2(String newCatLibre2) {
        catLibre2 = newCatLibre2;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_LIBRE2] = 1;
    }

    public void setCatLibre3(String newCatLibre3) {
        catLibre3 = newCatLibre3;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_LIBRE3] = 1;
    }

    public void setCatMliCode(String newCatMliCode) {
        catMliCode = newCatMliCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_MLI_CODE] = 1;
    }

    public void setCatModCode(String newCatModCode) {
        catModCode = newCatModCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_MOD_CODE] = 1;
    }

    public void setCatMtrCode(String newCatMtrCode) {
        catMtrCode = newCatMtrCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_MTR_CODE] = 1;
    }

    public void setCatPayCode(String newCatPayCode) {
        catPayCode = newCatPayCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_PAY_CODE] = 1;
    }

    public void setCatPieceColis(String newCatPieceColis) {
        catPieceColis = newCatPieceColis;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS] = 1;
    }

    public void setCatRefCmde(String newCatRefCmde) {
        catRefCmde = newCatRefCmde;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_REF_CMDE] = 1;
    }

    public void setCatRegimeTva(String newCatRegimeTva) {
        catRegimeTva = newCatRegimeTva;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA] = 1;
    }

    public void setCatReliquatOn(String newCatReliquatOn) {
        catReliquatOn = newCatReliquatOn;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON] = 1;
    }

    public void setCatFouCodeTransp(String newCatFouCodeTransp) {
        catFouCodeTransp = newCatFouCodeTransp;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP] = 1;
    }

    public void setCatStaCode(String newCatStaCode) {
        catStaCode = newCatStaCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_STA_CODE] = 1;
    }

    public void setCatTypeOrdre(String newCatTypeOrdre) {
        catTypeOrdre = newCatTypeOrdre;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE] = 1;
    }

    public void setCatTxRemFac1(Double newCatTxRemFac1) {
        catTxRemFac1 = newCatTxRemFac1;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1] = 1;
    }

    public void setCatTxRemFac2(Double newCatTxRemFac2) {
        catTxRemFac2 = newCatTxRemFac2;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2] = 1;
    }

    public void setCatTxRemFac3(Double newCatTxRemFac3) {
        catTxRemFac3 = newCatTxRemFac3;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3] = 1;
    }

    public void setCatTydCode(String newCatTydCode) {
        catTydCode = newCatTydCode;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TYD_CODE] = 1;
    }

    public void setCatTypeCmde(String newCatTypeCmde) {
        catTypeCmde = newCatTypeCmde;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE] = 1;
    }

    public void setCatTypePort(String newCatTypePort) {
        catTypePort = newCatTypePort;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT] = 1;
    }

    public void setCatIndex(String newCatIndex) {
        catIndex = newCatIndex;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_INDEX] = 1;
    }

    public void setCatDtCreat(Timestamp newCatDtCreat) {
        catDtCreat = newCatDtCreat;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_CREAT] = 1;
    }

    public void setCatDtMaj(Timestamp newCatDtMaj) {
        catDtMaj = newCatDtMaj;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_MAJ] = 1;
    }

    public void setCatUtilMaj(String newCatUtilMaj) {
        catUtilMaj = newCatUtilMaj;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.Z_STATUS] = 1;
    }

    public void setCatValidation(String newCatValidation) {
        catValidation = newCatValidation;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_VALIDATION] = 1;
    }

    public void setCatDtPrevLivr0(Timestamp newCatDtPrevLivr0) {
        catDtPrevLivr0 = newCatDtPrevLivr0;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0] = 1;
    }

    public void setCatRetourGp(String newCatRetourGp) {
        catRetourGp = newCatRetourGp;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP] = 1;
    }

    public void setCatEtatGp(String newCatEtatGp) {
        catEtatGp = newCatEtatGp;
        if (persist > 0)
            updCol[DoDtCmdeAchatTeteDesc.CAT_ETAT_GP] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_CODE)
            return catDepCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE)
            return catDepSocCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_CMDE)
            return catNoCmde;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_VERSION)
            return catNoVersion;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE)
            return catNoCmdeMere;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE)
            return catNoVersionMere;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_RECEP)
            return catNoRecep;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP)
            return catNoVersionRecep;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV)
            return catDepCodeLiv;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV)
            return catDepSocCodeLiv;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST)
            return catDepCodeDest;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST)
            return catDepSocCodeDest;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION)
            return catDerNoVersion;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEV_CODE)
            return catDevCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE)
            return catTxDevise;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_ESC)
            return catTxEsc;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION)
            return catConsignation;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL)
            return catControleQual;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_CMDE)
            return catDtCmde;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM)
            return catDtConfirm;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1)
            return catDtDepart1;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2)
            return catDtDepart2;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1)
            return catDtEdition1;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2)
            return catDtEdition2;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_EVEN)
            return catDtEven;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1)
            return catDtPrevLivr1;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2)
            return catDtPrevLivr2;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_RDV)
            return catDtRdv;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR)
            return catDtValeur;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE)
            return catEtiquettage;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_EVE_CODE)
            return catEveCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_FOU_CODE)
            return catFouCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE)
            return catInfFouCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM)
            return catInfFouNom;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE)
            return catInfFouTitre;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE)
            return catInfNoOrdre;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_LAN_CODE)
            return catLanCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_LIBRE1)
            return catLibre1;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_LIBRE2)
            return catLibre2;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_LIBRE3)
            return catLibre3;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_MLI_CODE)
            return catMliCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_MOD_CODE)
            return catModCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_MTR_CODE)
            return catMtrCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_PAY_CODE)
            return catPayCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS)
            return catPieceColis;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_REF_CMDE)
            return catRefCmde;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA)
            return catRegimeTva;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON)
            return catReliquatOn;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP)
            return catFouCodeTransp;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_STA_CODE)
            return catStaCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE)
            return catTypeOrdre;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1)
            return catTxRemFac1;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2)
            return catTxRemFac2;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3)
            return catTxRemFac3;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYD_CODE)
            return catTydCode;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE)
            return catTypeCmde;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT)
            return catTypePort;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_INDEX)
            return catIndex;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_CREAT)
            return catDtCreat;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_MAJ)
            return catDtMaj;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ)
            return catUtilMaj;
        else if (numCol == DoDtCmdeAchatTeteDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtCmdeAchatTeteDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_VALIDATION)
            return catValidation;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0)
            return catDtPrevLivr0;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP)
            return catRetourGp;
        else if (numCol == DoDtCmdeAchatTeteDesc.CAT_ETAT_GP)
            return catEtatGp;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_CODE) {
            catDepCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE) {
            catDepSocCode = (String) value;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_CMDE) {
            catNoCmde = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_VERSION) {
            catNoVersion = (Integer) value;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE) {
            catNoCmdeMere = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE) {
            catNoVersionMere = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_RECEP) {
            catNoRecep = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP) {
            catNoVersionRecep = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV) {
            catDepCodeLiv = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV) {
            catDepSocCodeLiv = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST) {
            catDepCodeDest = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST) {
            catDepSocCodeDest = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION) {
            catDerNoVersion = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DEV_CODE) {
            catDevCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE) {
            catTxDevise = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_ESC) {
            catTxEsc = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION) {
            catConsignation = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL) {
            catControleQual = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_CMDE) {
            catDtCmde = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM) {
            catDtConfirm = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1) {
            catDtDepart1 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2) {
            catDtDepart2 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1) {
            catDtEdition1 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2) {
            catDtEdition2 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_EVEN) {
            catDtEven = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1) {
            catDtPrevLivr1 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2) {
            catDtPrevLivr2 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_RDV) {
            catDtRdv = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR) {
            catDtValeur = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE) {
            catEtiquettage = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_EVE_CODE) {
            catEveCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_FOU_CODE) {
            catFouCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE) {
            catInfFouCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM) {
            catInfFouNom = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE) {
            catInfFouTitre = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE) {
            catInfNoOrdre = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_LAN_CODE) {
            catLanCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_LIBRE1) {
            catLibre1 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_LIBRE2) {
            catLibre2 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_LIBRE3) {
            catLibre3 = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_MLI_CODE) {
            catMliCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_MOD_CODE) {
            catModCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_MTR_CODE) {
            catMtrCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_PAY_CODE) {
            catPayCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS) {
            catPieceColis = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_REF_CMDE) {
            catRefCmde = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA) {
            catRegimeTva = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON) {
            catReliquatOn = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP) {
            catFouCodeTransp = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_STA_CODE) {
            catStaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE) {
            catTypeOrdre = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1) {
            catTxRemFac1 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2) {
            catTxRemFac2 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3) {
            catTxRemFac3 = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYD_CODE) {
            catTydCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE) {
            catTypeCmde = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT) {
            catTypePort = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_INDEX) {
            catIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_CREAT) {
            catDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_MAJ) {
            catDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ) {
            catUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_VALIDATION) {
            catValidation = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0) {
            catDtPrevLivr0 = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP) {
            catRetourGp = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtCmdeAchatTeteDesc.CAT_ETAT_GP) {
            catEtatGp = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtCmdeAchatTete());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtCmdeAchatTete djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE] != -1) {
            djo.catDepCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE] != -1) {
            djo.catDepSocCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE]);
            if (!rs.wasNull())
                djo.catNoCmde = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.catNoVersion = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE]);
            if (!rs.wasNull())
                djo.catNoCmdeMere = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE]);
            if (!rs.wasNull())
                djo.catNoVersionMere = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_RECEP] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_RECEP]);
            if (!rs.wasNull())
                djo.catNoRecep = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP]);
            if (!rs.wasNull())
                djo.catNoVersionRecep = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV] != -1) {
            djo.catDepCodeLiv = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV] != -1) {
            djo.catDepSocCodeLiv = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST] != -1) {
            djo.catDepCodeDest = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST] != -1) {
            djo.catDepSocCodeDest = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION]);
            if (!rs.wasNull())
                djo.catDerNoVersion = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEV_CODE] != -1) {
            djo.catDevCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_DEV_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE]);
            if (!rs.wasNull())
                djo.catTxDevise = new Double(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_ESC] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_ESC]);
            if (!rs.wasNull())
                djo.catTxEsc = new Double(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION] != -1) {
            djo.catConsignation = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL] != -1) {
            djo.catControleQual = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_CMDE] != -1) {
            djo.catDtCmde = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_CMDE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM] != -1) {
            djo.catDtConfirm = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1] != -1) {
            djo.catDtDepart1 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2] != -1) {
            djo.catDtDepart2 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1] != -1) {
            djo.catDtEdition1 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2] != -1) {
            djo.catDtEdition2 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_EVEN] != -1) {
            djo.catDtEven = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_EVEN]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1] != -1) {
            djo.catDtPrevLivr1 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2] != -1) {
            djo.catDtPrevLivr2 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_RDV] != -1) {
            djo.catDtRdv = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_RDV]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR] != -1) {
            djo.catDtValeur = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE] != -1) {
            djo.catEtiquettage = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_EVE_CODE] != -1) {
            djo.catEveCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_EVE_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE] != -1) {
            djo.catFouCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE] != -1) {
            djo.catInfFouCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM] != -1) {
            djo.catInfFouNom = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE] != -1) {
            djo.catInfFouTitre = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE]);
            if (!rs.wasNull())
                djo.catInfNoOrdre = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LAN_CODE] != -1) {
            djo.catLanCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_LAN_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LIBRE1] != -1) {
            djo.catLibre1 = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_LIBRE1]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LIBRE2] != -1) {
            djo.catLibre2 = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_LIBRE2]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LIBRE3] != -1) {
            djo.catLibre3 = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_LIBRE3]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_MLI_CODE] != -1) {
            djo.catMliCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_MLI_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_MOD_CODE] != -1) {
            djo.catModCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_MOD_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_MTR_CODE] != -1) {
            djo.catMtrCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_MTR_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_PAY_CODE] != -1) {
            djo.catPayCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_PAY_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS] != -1) {
            djo.catPieceColis = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_REF_CMDE] != -1) {
            djo.catRefCmde = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_REF_CMDE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA] != -1) {
            djo.catRegimeTva = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON] != -1) {
            djo.catReliquatOn = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP] != -1) {
            djo.catFouCodeTransp = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_STA_CODE] != -1) {
            djo.catStaCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_STA_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE] != -1) {
            djo.catTypeOrdre = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1]);
            if (!rs.wasNull())
                djo.catTxRemFac1 = new Double(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2]);
            if (!rs.wasNull())
                djo.catTxRemFac2 = new Double(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3] != -1) {
            double temp = rs.getDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3]);
            if (!rs.wasNull())
                djo.catTxRemFac3 = new Double(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYD_CODE] != -1) {
            djo.catTydCode = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_TYD_CODE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE] != -1) {
            djo.catTypeCmde = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT] != -1) {
            djo.catTypePort = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INDEX] != -1) {
            djo.catIndex = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_INDEX]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_CREAT] != -1) {
            djo.catDtCreat = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_CREAT]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_MAJ] != -1) {
            djo.catDtMaj = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_MAJ]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ] != -1) {
            djo.catUtilMaj = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ]);
        }
        if (val[DoDtCmdeAchatTeteDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtCmdeAchatTeteDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtCmdeAchatTeteDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtCmdeAchatTeteDesc.Z_STATUS]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_VALIDATION] != -1) {
            djo.catValidation = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_VALIDATION]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0] != -1) {
            djo.catDtPrevLivr0 = rs.getTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP] != -1) {
            djo.catRetourGp = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP]);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_ETAT_GP] != -1) {
            djo.catEtatGp = rs.getString(val[DoDtCmdeAchatTeteDesc.CAT_ETAT_GP]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE], catDepCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE], catDepSocCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE] > 0) {
            if (catNoCmde == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE], catNoCmde.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION] > 0) {
            if (catNoVersion == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION], catNoVersion.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE] > 0) {
            if (catNoCmdeMere == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_CMDE_MERE], catNoCmdeMere.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE] > 0) {
            if (catNoVersionMere == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_MERE], catNoVersionMere.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_RECEP] > 0) {
            if (catNoRecep == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_NO_RECEP], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_RECEP], catNoRecep.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP] > 0) {
            if (catNoVersionRecep == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_NO_VERSION_RECEP], catNoVersionRecep.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_LIV], catDepCodeLiv);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_LIV], catDepSocCodeLiv);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_CODE_DEST], catDepCodeDest);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEP_SOC_CODE_DEST], catDepSocCodeDest);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION] > 0) {
            if (catDerNoVersion == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_DER_NO_VERSION], catDerNoVersion.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DEV_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_DEV_CODE], catDevCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE] > 0) {
            if (catTxDevise == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_DEVISE], catTxDevise.doubleValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_ESC] > 0) {
            if (catTxEsc == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_TX_ESC], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_ESC], catTxEsc.doubleValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_CONSIGNATION], catConsignation);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_CONTROLE_QUAL], catControleQual);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_CMDE] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_CMDE], catDtCmde);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_CONFIRM], catDtConfirm);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART1], catDtDepart1);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_DEPART2], catDtDepart2);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION1], catDtEdition1);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_EDITION2], catDtEdition2);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_EVEN] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_EVEN], catDtEven);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR1], catDtPrevLivr1);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR2], catDtPrevLivr2);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_RDV] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_RDV], catDtRdv);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_VALEUR], catDtValeur);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_ETIQUETTAGE], catEtiquettage);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_EVE_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_EVE_CODE], catEveCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE], catFouCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_CODE], catInfFouCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_NOM], catInfFouNom);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_INF_FOU_TITRE], catInfFouTitre);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE] > 0) {
            if (catInfNoOrdre == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.CAT_INF_NO_ORDRE], catInfNoOrdre.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LAN_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_LAN_CODE], catLanCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LIBRE1] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_LIBRE1], catLibre1);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LIBRE2] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_LIBRE2], catLibre2);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_LIBRE3] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_LIBRE3], catLibre3);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_MLI_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_MLI_CODE], catMliCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_MOD_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_MOD_CODE], catModCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_MTR_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_MTR_CODE], catMtrCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_PAY_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_PAY_CODE], catPayCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_PIECE_COLIS], catPieceColis);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_REF_CMDE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_REF_CMDE], catRefCmde);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_REGIME_TVA], catRegimeTva);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_RELIQUAT_ON], catReliquatOn);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_FOU_CODE_TRANSP], catFouCodeTransp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_STA_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_STA_CODE], catStaCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_TYPE_ORDRE], catTypeOrdre);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1] > 0) {
            if (catTxRemFac1 == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC1], catTxRemFac1.doubleValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2] > 0) {
            if (catTxRemFac2 == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC2], catTxRemFac2.doubleValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3] > 0) {
            if (catTxRemFac3 == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3], 3);
            else
                stmt.setDouble(val[DoDtCmdeAchatTeteDesc.CAT_TX_REM_FAC3], catTxRemFac3.doubleValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYD_CODE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_TYD_CODE], catTydCode);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_TYPE_CMDE], catTypeCmde);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_TYPE_PORT], catTypePort);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_INDEX] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_INDEX], catIndex);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_CREAT], catDtCreat);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_MAJ], catDtMaj);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_UTIL_MAJ], catUtilMaj);
        }
        if (val[DoDtCmdeAchatTeteDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtCmdeAchatTeteDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtCmdeAchatTeteDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtCmdeAchatTeteDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.Z_STATUS], zStatus);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_VALIDATION] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_VALIDATION], catValidation);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0] > 0) {
            stmt.setTimestamp(val[DoDtCmdeAchatTeteDesc.CAT_DT_PREV_LIVR0], catDtPrevLivr0);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_RETOUR_GP], catRetourGp);
        }
        if (val[DoDtCmdeAchatTeteDesc.CAT_ETAT_GP] > 0) {
            stmt.setString(val[DoDtCmdeAchatTeteDesc.CAT_ETAT_GP], catEtatGp);
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
        DoDtCmdeAchatTete[] result = null;
        params = request.getParameterValues("catDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("catDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("catNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catNoCmdeMere");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatNoCmdeMere((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catNoVersionMere");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatNoVersionMere((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catNoRecep");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatNoRecep((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catNoVersionRecep");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatNoVersionRecep((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catDepCodeLiv");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDepCodeLiv(localVal);
            }
        }
        params = request.getParameterValues("catDepSocCodeLiv");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDepSocCodeLiv(localVal);
            }
        }
        params = request.getParameterValues("catDepCodeDest");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDepCodeDest(localVal);
            }
        }
        params = request.getParameterValues("catDepSocCodeDest");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDepSocCodeDest(localVal);
            }
        }
        params = request.getParameterValues("catDerNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDerNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catDevCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDevCode(localVal);
            }
        }
        params = request.getParameterValues("catTxDevise");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTxDevise((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("catTxEsc");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTxEsc((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("catConsignation");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatConsignation(localVal);
            }
        }
        params = request.getParameterValues("catControleQual");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatControleQual(localVal);
            }
        }
        params = request.getParameterValues("catDtCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtCmde((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtConfirm");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtConfirm((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtDepart1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtDepart1((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtDepart2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtDepart2((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtEdition1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtEdition1((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtEdition2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtEdition2((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtEven");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtEven((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtPrevLivr1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtPrevLivr1((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtPrevLivr2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtPrevLivr2((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtRdv");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtRdv((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtValeur");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtValeur((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catEtiquettage");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatEtiquettage(localVal);
            }
        }
        params = request.getParameterValues("catEveCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatEveCode(localVal);
            }
        }
        params = request.getParameterValues("catFouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatFouCode(localVal);
            }
        }
        params = request.getParameterValues("catInfFouCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatInfFouCode(localVal);
            }
        }
        params = request.getParameterValues("catInfFouNom");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatInfFouNom(localVal);
            }
        }
        params = request.getParameterValues("catInfFouTitre");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatInfFouTitre(localVal);
            }
        }
        params = request.getParameterValues("catInfNoOrdre");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatInfNoOrdre((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("catLanCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatLanCode(localVal);
            }
        }
        params = request.getParameterValues("catLibre1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatLibre1(localVal);
            }
        }
        params = request.getParameterValues("catLibre2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatLibre2(localVal);
            }
        }
        params = request.getParameterValues("catLibre3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatLibre3(localVal);
            }
        }
        params = request.getParameterValues("catMliCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatMliCode(localVal);
            }
        }
        params = request.getParameterValues("catModCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatModCode(localVal);
            }
        }
        params = request.getParameterValues("catMtrCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatMtrCode(localVal);
            }
        }
        params = request.getParameterValues("catPayCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatPayCode(localVal);
            }
        }
        params = request.getParameterValues("catPieceColis");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatPieceColis(localVal);
            }
        }
        params = request.getParameterValues("catRefCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatRefCmde(localVal);
            }
        }
        params = request.getParameterValues("catRegimeTva");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatRegimeTva(localVal);
            }
        }
        params = request.getParameterValues("catReliquatOn");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatReliquatOn(localVal);
            }
        }
        params = request.getParameterValues("catFouCodeTransp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatFouCodeTransp(localVal);
            }
        }
        params = request.getParameterValues("catStaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatStaCode(localVal);
            }
        }
        params = request.getParameterValues("catTypeOrdre");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTypeOrdre(localVal);
            }
        }
        params = request.getParameterValues("catTxRemFac1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTxRemFac1((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("catTxRemFac2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTxRemFac2((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("catTxRemFac3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTxRemFac3((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("catTydCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTydCode(localVal);
            }
        }
        params = request.getParameterValues("catTypeCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTypeCmde(localVal);
            }
        }
        params = request.getParameterValues("catTypePort");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatTypePort(localVal);
            }
        }
        params = request.getParameterValues("catIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatIndex(localVal);
            }
        }
        params = request.getParameterValues("catDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
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
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
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
        params = request.getParameterValues("catValidation");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatValidation(localVal);
            }
        }
        params = request.getParameterValues("catDtPrevLivr0");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatDtPrevLivr0((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("catRetourGp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatRetourGp(localVal);
            }
        }
        params = request.getParameterValues("catEtatGp");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtCmdeAchatTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtCmdeAchatTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setCatEtatGp(localVal);
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

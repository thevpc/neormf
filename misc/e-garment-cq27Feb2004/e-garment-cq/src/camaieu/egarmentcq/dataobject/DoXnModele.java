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

public class DoXnModele implements DataObject {

    private static final IDoDescription description = new DoXnModeleDesc();
    private transient int persist = PERSIST_UPDATE_INSERT;
    private transient int[] updCol = new int[33];
    private transient String sql;
    private transient Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"XN_MODELE"};
    //variables correspondant à la table XN_MODELE
    private String modCode = null;
    private String modDes = null;
    private String modLib = null;
    private String modNatCond = null;
    private String modNomen = null;
    private String modArtTest = null;
    private String modCatAmf = null;
    private String modGrtCode = null;
    private String modSfaCode = null;
    private String modSfaFaaCode = null;
    private String modTvaCode = null;
    private Double modCeeCoef = null;
    private Double modPxInit = null;
    private Integer modPcb = null;
    private Integer modSpcb = null;
    private Double modColisPoids = null;
    private Double modColisVol = null;
    private Integer modNbVar = null;
    private Timestamp modDtFinVie = null;
    private Timestamp modDtCreat = null;
    private Timestamp modDtMaj = null;
    private String modUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private String modTcaCode = null;
    private String modType = null;
    private String modTypeArticle = null;
    private Integer modArgPcrCodigo = null;
    private Integer modArgQualite = null;
    private Integer modCenNiv = null;
    private Integer modPcrCodigoEntrep = null;
    private Double modEpaisseurCintre = null;
    private String modTypeEclatement = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoXnModele() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnModele(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoXnModele(DoXnModele arg) {
        setModCode(arg.modCode);
        setModDes(arg.modDes);
        setModLib(arg.modLib);
        setModNatCond(arg.modNatCond);
        setModNomen(arg.modNomen);
        setModArtTest(arg.modArtTest);
        setModCatAmf(arg.modCatAmf);
        setModGrtCode(arg.modGrtCode);
        setModSfaCode(arg.modSfaCode);
        setModSfaFaaCode(arg.modSfaFaaCode);
        setModTvaCode(arg.modTvaCode);
        setModCeeCoef(arg.modCeeCoef);
        setModPxInit(arg.modPxInit);
        setModPcb(arg.modPcb);
        setModSpcb(arg.modSpcb);
        setModColisPoids(arg.modColisPoids);
        setModColisVol(arg.modColisVol);
        setModNbVar(arg.modNbVar);
        setModDtFinVie(arg.modDtFinVie);
        setModDtCreat(arg.modDtCreat);
        setModDtMaj(arg.modDtMaj);
        setModUtilMaj(arg.modUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setModTcaCode(arg.modTcaCode);
        setModType(arg.modType);
        setModTypeArticle(arg.modTypeArticle);
        setModArgPcrCodigo(arg.modArgPcrCodigo);
        setModArgQualite(arg.modArgQualite);
        setModCenNiv(arg.modCenNiv);
        setModPcrCodigoEntrep(arg.modPcrCodigoEntrep);
        setModEpaisseurCintre(arg.modEpaisseurCintre);
        setModTypeEclatement(arg.modTypeEclatement);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoXnModele(String newSql, Object[] newParam) {
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

    public String getModCode() {
        return modCode;
    }

    public String getModDes() {
        return modDes;
    }

    public String getModLib() {
        return modLib;
    }

    public String getModNatCond() {
        return modNatCond;
    }

    public String getModNomen() {
        return modNomen;
    }

    public String getModArtTest() {
        return modArtTest;
    }

    public String getModCatAmf() {
        return modCatAmf;
    }

    public String getModGrtCode() {
        return modGrtCode;
    }

    public String getModSfaCode() {
        return modSfaCode;
    }

    public String getModSfaFaaCode() {
        return modSfaFaaCode;
    }

    public String getModTvaCode() {
        return modTvaCode;
    }

    public Double getModCeeCoef() {
        return modCeeCoef;
    }

    public Double getModPxInit() {
        return modPxInit;
    }

    public Integer getModPcb() {
        return modPcb;
    }

    public Integer getModSpcb() {
        return modSpcb;
    }

    public Double getModColisPoids() {
        return modColisPoids;
    }

    public Double getModColisVol() {
        return modColisVol;
    }

    public Integer getModNbVar() {
        return modNbVar;
    }

    public Timestamp getModDtFinVie() {
        return modDtFinVie;
    }

    public Timestamp getModDtCreat() {
        return modDtCreat;
    }

    public Timestamp getModDtMaj() {
        return modDtMaj;
    }

    public String getModUtilMaj() {
        return modUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public String getModTcaCode() {
        return modTcaCode;
    }

    public String getModType() {
        return modType;
    }

    public String getModTypeArticle() {
        return modTypeArticle;
    }

    public Integer getModArgPcrCodigo() {
        return modArgPcrCodigo;
    }

    public Integer getModArgQualite() {
        return modArgQualite;
    }

    public Integer getModCenNiv() {
        return modCenNiv;
    }

    public Integer getModPcrCodigoEntrep() {
        return modPcrCodigoEntrep;
    }

    public Double getModEpaisseurCintre() {
        return modEpaisseurCintre;
    }

    public String getModTypeEclatement() {
        return modTypeEclatement;
    }

    public void setModCode(String newModCode) {
        modCode = newModCode;
    }

    public void setModDes(String newModDes) {
        modDes = newModDes;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_DES] = 1;
    }

    public void setModLib(String newModLib) {
        modLib = newModLib;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_LIB] = 1;
    }

    public void setModNatCond(String newModNatCond) {
        modNatCond = newModNatCond;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_NAT_COND] = 1;
    }

    public void setModNomen(String newModNomen) {
        modNomen = newModNomen;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_NOMEN] = 1;
    }

    public void setModArtTest(String newModArtTest) {
        modArtTest = newModArtTest;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_ART_TEST] = 1;
    }

    public void setModCatAmf(String newModCatAmf) {
        modCatAmf = newModCatAmf;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_CAT_AMF] = 1;
    }

    public void setModGrtCode(String newModGrtCode) {
        modGrtCode = newModGrtCode;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_GRT_CODE] = 1;
    }

    public void setModSfaCode(String newModSfaCode) {
        modSfaCode = newModSfaCode;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_SFA_CODE] = 1;
    }

    public void setModSfaFaaCode(String newModSfaFaaCode) {
        modSfaFaaCode = newModSfaFaaCode;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_SFA_FAA_CODE] = 1;
    }

    public void setModTvaCode(String newModTvaCode) {
        modTvaCode = newModTvaCode;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_TVA_CODE] = 1;
    }

    public void setModCeeCoef(Double newModCeeCoef) {
        modCeeCoef = newModCeeCoef;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_CEE_COEF] = 1;
    }

    public void setModPxInit(Double newModPxInit) {
        modPxInit = newModPxInit;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_PX_INIT] = 1;
    }

    public void setModPcb(Integer newModPcb) {
        modPcb = newModPcb;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_PCB] = 1;
    }

    public void setModSpcb(Integer newModSpcb) {
        modSpcb = newModSpcb;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_SPCB] = 1;
    }

    public void setModColisPoids(Double newModColisPoids) {
        modColisPoids = newModColisPoids;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_COLIS_POIDS] = 1;
    }

    public void setModColisVol(Double newModColisVol) {
        modColisVol = newModColisVol;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_COLIS_VOL] = 1;
    }

    public void setModNbVar(Integer newModNbVar) {
        modNbVar = newModNbVar;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_NB_VAR] = 1;
    }

    public void setModDtFinVie(Timestamp newModDtFinVie) {
        modDtFinVie = newModDtFinVie;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_DT_FIN_VIE] = 1;
    }

    public void setModDtCreat(Timestamp newModDtCreat) {
        modDtCreat = newModDtCreat;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_DT_CREAT] = 1;
    }

    public void setModDtMaj(Timestamp newModDtMaj) {
        modDtMaj = newModDtMaj;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_DT_MAJ] = 1;
    }

    public void setModUtilMaj(String newModUtilMaj) {
        modUtilMaj = newModUtilMaj;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoXnModeleDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoXnModeleDesc.Z_STATUS] = 1;
    }

    public void setModTcaCode(String newModTcaCode) {
        modTcaCode = newModTcaCode;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_TCA_CODE] = 1;
    }

    public void setModType(String newModType) {
        modType = newModType;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_TYPE] = 1;
    }

    public void setModTypeArticle(String newModTypeArticle) {
        modTypeArticle = newModTypeArticle;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_TYPE_ARTICLE] = 1;
    }

    public void setModArgPcrCodigo(Integer newModArgPcrCodigo) {
        modArgPcrCodigo = newModArgPcrCodigo;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_ARG_PCR_CODIGO] = 1;
    }

    public void setModArgQualite(Integer newModArgQualite) {
        modArgQualite = newModArgQualite;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_ARG_QUALITE] = 1;
    }

    public void setModCenNiv(Integer newModCenNiv) {
        modCenNiv = newModCenNiv;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_CEN_NIV] = 1;
    }

    public void setModPcrCodigoEntrep(Integer newModPcrCodigoEntrep) {
        modPcrCodigoEntrep = newModPcrCodigoEntrep;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP] = 1;
    }

    public void setModEpaisseurCintre(Double newModEpaisseurCintre) {
        modEpaisseurCintre = newModEpaisseurCintre;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_EPAISSEUR_CINTRE] = 1;
    }

    public void setModTypeEclatement(String newModTypeEclatement) {
        modTypeEclatement = newModTypeEclatement;
        if (persist > 0)
            updCol[DoXnModeleDesc.MOD_TYPE_ECLATEMENT] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoXnModeleDesc.MOD_CODE)
            return modCode;
        else if (numCol == DoXnModeleDesc.MOD_DES)
            return modDes;
        else if (numCol == DoXnModeleDesc.MOD_LIB)
            return modLib;
        else if (numCol == DoXnModeleDesc.MOD_NAT_COND)
            return modNatCond;
        else if (numCol == DoXnModeleDesc.MOD_NOMEN)
            return modNomen;
        else if (numCol == DoXnModeleDesc.MOD_ART_TEST)
            return modArtTest;
        else if (numCol == DoXnModeleDesc.MOD_CAT_AMF)
            return modCatAmf;
        else if (numCol == DoXnModeleDesc.MOD_GRT_CODE)
            return modGrtCode;
        else if (numCol == DoXnModeleDesc.MOD_SFA_CODE)
            return modSfaCode;
        else if (numCol == DoXnModeleDesc.MOD_SFA_FAA_CODE)
            return modSfaFaaCode;
        else if (numCol == DoXnModeleDesc.MOD_TVA_CODE)
            return modTvaCode;
        else if (numCol == DoXnModeleDesc.MOD_CEE_COEF)
            return modCeeCoef;
        else if (numCol == DoXnModeleDesc.MOD_PX_INIT)
            return modPxInit;
        else if (numCol == DoXnModeleDesc.MOD_PCB)
            return modPcb;
        else if (numCol == DoXnModeleDesc.MOD_SPCB)
            return modSpcb;
        else if (numCol == DoXnModeleDesc.MOD_COLIS_POIDS)
            return modColisPoids;
        else if (numCol == DoXnModeleDesc.MOD_COLIS_VOL)
            return modColisVol;
        else if (numCol == DoXnModeleDesc.MOD_NB_VAR)
            return modNbVar;
        else if (numCol == DoXnModeleDesc.MOD_DT_FIN_VIE)
            return modDtFinVie;
        else if (numCol == DoXnModeleDesc.MOD_DT_CREAT)
            return modDtCreat;
        else if (numCol == DoXnModeleDesc.MOD_DT_MAJ)
            return modDtMaj;
        else if (numCol == DoXnModeleDesc.MOD_UTIL_MAJ)
            return modUtilMaj;
        else if (numCol == DoXnModeleDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoXnModeleDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoXnModeleDesc.MOD_TCA_CODE)
            return modTcaCode;
        else if (numCol == DoXnModeleDesc.MOD_TYPE)
            return modType;
        else if (numCol == DoXnModeleDesc.MOD_TYPE_ARTICLE)
            return modTypeArticle;
        else if (numCol == DoXnModeleDesc.MOD_ARG_PCR_CODIGO)
            return modArgPcrCodigo;
        else if (numCol == DoXnModeleDesc.MOD_ARG_QUALITE)
            return modArgQualite;
        else if (numCol == DoXnModeleDesc.MOD_CEN_NIV)
            return modCenNiv;
        else if (numCol == DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP)
            return modPcrCodigoEntrep;
        else if (numCol == DoXnModeleDesc.MOD_EPAISSEUR_CINTRE)
            return modEpaisseurCintre;
        else if (numCol == DoXnModeleDesc.MOD_TYPE_ECLATEMENT)
            return modTypeEclatement;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoXnModeleDesc.MOD_CODE) {
            modCode = (String) value;
        }
        if (numCol == DoXnModeleDesc.MOD_DES) {
            modDes = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_LIB) {
            modLib = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_NAT_COND) {
            modNatCond = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_NOMEN) {
            modNomen = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_ART_TEST) {
            modArtTest = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_CAT_AMF) {
            modCatAmf = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_GRT_CODE) {
            modGrtCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_SFA_CODE) {
            modSfaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_SFA_FAA_CODE) {
            modSfaFaaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_TVA_CODE) {
            modTvaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_CEE_COEF) {
            modCeeCoef = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_PX_INIT) {
            modPxInit = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_PCB) {
            modPcb = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_SPCB) {
            modSpcb = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_COLIS_POIDS) {
            modColisPoids = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_COLIS_VOL) {
            modColisVol = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_NB_VAR) {
            modNbVar = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_DT_FIN_VIE) {
            modDtFinVie = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_DT_CREAT) {
            modDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_DT_MAJ) {
            modDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_UTIL_MAJ) {
            modUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_TCA_CODE) {
            modTcaCode = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_TYPE) {
            modType = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_TYPE_ARTICLE) {
            modTypeArticle = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_ARG_PCR_CODIGO) {
            modArgPcrCodigo = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_ARG_QUALITE) {
            modArgQualite = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_CEN_NIV) {
            modCenNiv = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP) {
            modPcrCodigoEntrep = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_EPAISSEUR_CINTRE) {
            modEpaisseurCintre = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoXnModeleDesc.MOD_TYPE_ECLATEMENT) {
            modTypeEclatement = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoXnModele());
    }

    private DataObject setProperty(SqlArg sqlArg, DoXnModele djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoXnModeleDesc.MOD_CODE] != -1) {
            djo.modCode = rs.getString(val[DoXnModeleDesc.MOD_CODE]);
        }
        if (val[DoXnModeleDesc.MOD_DES] != -1) {
            djo.modDes = rs.getString(val[DoXnModeleDesc.MOD_DES]);
        }
        if (val[DoXnModeleDesc.MOD_LIB] != -1) {
            djo.modLib = rs.getString(val[DoXnModeleDesc.MOD_LIB]);
        }
        if (val[DoXnModeleDesc.MOD_NAT_COND] != -1) {
            djo.modNatCond = rs.getString(val[DoXnModeleDesc.MOD_NAT_COND]);
        }
        if (val[DoXnModeleDesc.MOD_NOMEN] != -1) {
            djo.modNomen = rs.getString(val[DoXnModeleDesc.MOD_NOMEN]);
        }
        if (val[DoXnModeleDesc.MOD_ART_TEST] != -1) {
            djo.modArtTest = rs.getString(val[DoXnModeleDesc.MOD_ART_TEST]);
        }
        if (val[DoXnModeleDesc.MOD_CAT_AMF] != -1) {
            djo.modCatAmf = rs.getString(val[DoXnModeleDesc.MOD_CAT_AMF]);
        }
        if (val[DoXnModeleDesc.MOD_GRT_CODE] != -1) {
            djo.modGrtCode = rs.getString(val[DoXnModeleDesc.MOD_GRT_CODE]);
        }
        if (val[DoXnModeleDesc.MOD_SFA_CODE] != -1) {
            djo.modSfaCode = rs.getString(val[DoXnModeleDesc.MOD_SFA_CODE]);
        }
        if (val[DoXnModeleDesc.MOD_SFA_FAA_CODE] != -1) {
            djo.modSfaFaaCode = rs.getString(val[DoXnModeleDesc.MOD_SFA_FAA_CODE]);
        }
        if (val[DoXnModeleDesc.MOD_TVA_CODE] != -1) {
            djo.modTvaCode = rs.getString(val[DoXnModeleDesc.MOD_TVA_CODE]);
        }
        if (val[DoXnModeleDesc.MOD_CEE_COEF] != -1) {
            double temp = rs.getDouble(val[DoXnModeleDesc.MOD_CEE_COEF]);
            if (!rs.wasNull())
                djo.modCeeCoef = new Double(temp);
        }
        if (val[DoXnModeleDesc.MOD_PX_INIT] != -1) {
            double temp = rs.getDouble(val[DoXnModeleDesc.MOD_PX_INIT]);
            if (!rs.wasNull())
                djo.modPxInit = new Double(temp);
        }
        if (val[DoXnModeleDesc.MOD_PCB] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_PCB]);
            if (!rs.wasNull())
                djo.modPcb = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_SPCB] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_SPCB]);
            if (!rs.wasNull())
                djo.modSpcb = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_COLIS_POIDS] != -1) {
            double temp = rs.getDouble(val[DoXnModeleDesc.MOD_COLIS_POIDS]);
            if (!rs.wasNull())
                djo.modColisPoids = new Double(temp);
        }
        if (val[DoXnModeleDesc.MOD_COLIS_VOL] != -1) {
            double temp = rs.getDouble(val[DoXnModeleDesc.MOD_COLIS_VOL]);
            if (!rs.wasNull())
                djo.modColisVol = new Double(temp);
        }
        if (val[DoXnModeleDesc.MOD_NB_VAR] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_NB_VAR]);
            if (!rs.wasNull())
                djo.modNbVar = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_DT_FIN_VIE] != -1) {
            djo.modDtFinVie = rs.getTimestamp(val[DoXnModeleDesc.MOD_DT_FIN_VIE]);
        }
        if (val[DoXnModeleDesc.MOD_DT_CREAT] != -1) {
            djo.modDtCreat = rs.getTimestamp(val[DoXnModeleDesc.MOD_DT_CREAT]);
        }
        if (val[DoXnModeleDesc.MOD_DT_MAJ] != -1) {
            djo.modDtMaj = rs.getTimestamp(val[DoXnModeleDesc.MOD_DT_MAJ]);
        }
        if (val[DoXnModeleDesc.MOD_UTIL_MAJ] != -1) {
            djo.modUtilMaj = rs.getString(val[DoXnModeleDesc.MOD_UTIL_MAJ]);
        }
        if (val[DoXnModeleDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoXnModeleDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoXnModeleDesc.Z_STATUS]);
        }
        if (val[DoXnModeleDesc.MOD_TCA_CODE] != -1) {
            djo.modTcaCode = rs.getString(val[DoXnModeleDesc.MOD_TCA_CODE]);
        }
        if (val[DoXnModeleDesc.MOD_TYPE] != -1) {
            djo.modType = rs.getString(val[DoXnModeleDesc.MOD_TYPE]);
        }
        if (val[DoXnModeleDesc.MOD_TYPE_ARTICLE] != -1) {
            djo.modTypeArticle = rs.getString(val[DoXnModeleDesc.MOD_TYPE_ARTICLE]);
        }
        if (val[DoXnModeleDesc.MOD_ARG_PCR_CODIGO] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_ARG_PCR_CODIGO]);
            if (!rs.wasNull())
                djo.modArgPcrCodigo = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_ARG_QUALITE] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_ARG_QUALITE]);
            if (!rs.wasNull())
                djo.modArgQualite = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_CEN_NIV] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_CEN_NIV]);
            if (!rs.wasNull())
                djo.modCenNiv = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP] != -1) {
            int temp = rs.getInt(val[DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP]);
            if (!rs.wasNull())
                djo.modPcrCodigoEntrep = new Integer(temp);
        }
        if (val[DoXnModeleDesc.MOD_EPAISSEUR_CINTRE] != -1) {
            double temp = rs.getDouble(val[DoXnModeleDesc.MOD_EPAISSEUR_CINTRE]);
            if (!rs.wasNull())
                djo.modEpaisseurCintre = new Double(temp);
        }
        if (val[DoXnModeleDesc.MOD_TYPE_ECLATEMENT] != -1) {
            djo.modTypeEclatement = rs.getString(val[DoXnModeleDesc.MOD_TYPE_ECLATEMENT]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoXnModeleDesc.MOD_CODE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_CODE], modCode);
        }
        if (val[DoXnModeleDesc.MOD_DES] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_DES], modDes);
        }
        if (val[DoXnModeleDesc.MOD_LIB] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_LIB], modLib);
        }
        if (val[DoXnModeleDesc.MOD_NAT_COND] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_NAT_COND], modNatCond);
        }
        if (val[DoXnModeleDesc.MOD_NOMEN] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_NOMEN], modNomen);
        }
        if (val[DoXnModeleDesc.MOD_ART_TEST] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_ART_TEST], modArtTest);
        }
        if (val[DoXnModeleDesc.MOD_CAT_AMF] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_CAT_AMF], modCatAmf);
        }
        if (val[DoXnModeleDesc.MOD_GRT_CODE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_GRT_CODE], modGrtCode);
        }
        if (val[DoXnModeleDesc.MOD_SFA_CODE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_SFA_CODE], modSfaCode);
        }
        if (val[DoXnModeleDesc.MOD_SFA_FAA_CODE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_SFA_FAA_CODE], modSfaFaaCode);
        }
        if (val[DoXnModeleDesc.MOD_TVA_CODE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_TVA_CODE], modTvaCode);
        }
        if (val[DoXnModeleDesc.MOD_CEE_COEF] > 0) {
            if (modCeeCoef == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_CEE_COEF], 3);
            else
                stmt.setDouble(val[DoXnModeleDesc.MOD_CEE_COEF], modCeeCoef.doubleValue());
        }
        if (val[DoXnModeleDesc.MOD_PX_INIT] > 0) {
            if (modPxInit == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_PX_INIT], 3);
            else
                stmt.setDouble(val[DoXnModeleDesc.MOD_PX_INIT], modPxInit.doubleValue());
        }
        if (val[DoXnModeleDesc.MOD_PCB] > 0) {
            if (modPcb == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_PCB], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_PCB], modPcb.intValue());
        }
        if (val[DoXnModeleDesc.MOD_SPCB] > 0) {
            if (modSpcb == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_SPCB], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_SPCB], modSpcb.intValue());
        }
        if (val[DoXnModeleDesc.MOD_COLIS_POIDS] > 0) {
            if (modColisPoids == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_COLIS_POIDS], 3);
            else
                stmt.setDouble(val[DoXnModeleDesc.MOD_COLIS_POIDS], modColisPoids.doubleValue());
        }
        if (val[DoXnModeleDesc.MOD_COLIS_VOL] > 0) {
            if (modColisVol == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_COLIS_VOL], 3);
            else
                stmt.setDouble(val[DoXnModeleDesc.MOD_COLIS_VOL], modColisVol.doubleValue());
        }
        if (val[DoXnModeleDesc.MOD_NB_VAR] > 0) {
            if (modNbVar == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_NB_VAR], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_NB_VAR], modNbVar.intValue());
        }
        if (val[DoXnModeleDesc.MOD_DT_FIN_VIE] > 0) {
            stmt.setTimestamp(val[DoXnModeleDesc.MOD_DT_FIN_VIE], modDtFinVie);
        }
        if (val[DoXnModeleDesc.MOD_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoXnModeleDesc.MOD_DT_CREAT], modDtCreat);
        }
        if (val[DoXnModeleDesc.MOD_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoXnModeleDesc.MOD_DT_MAJ], modDtMaj);
        }
        if (val[DoXnModeleDesc.MOD_UTIL_MAJ] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_UTIL_MAJ], modUtilMaj);
        }
        if (val[DoXnModeleDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoXnModeleDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoXnModeleDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoXnModeleDesc.Z_STATUS], zStatus);
        }
        if (val[DoXnModeleDesc.MOD_TCA_CODE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_TCA_CODE], modTcaCode);
        }
        if (val[DoXnModeleDesc.MOD_TYPE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_TYPE], modType);
        }
        if (val[DoXnModeleDesc.MOD_TYPE_ARTICLE] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_TYPE_ARTICLE], modTypeArticle);
        }
        if (val[DoXnModeleDesc.MOD_ARG_PCR_CODIGO] > 0) {
            if (modArgPcrCodigo == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_ARG_PCR_CODIGO], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_ARG_PCR_CODIGO], modArgPcrCodigo.intValue());
        }
        if (val[DoXnModeleDesc.MOD_ARG_QUALITE] > 0) {
            if (modArgQualite == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_ARG_QUALITE], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_ARG_QUALITE], modArgQualite.intValue());
        }
        if (val[DoXnModeleDesc.MOD_CEN_NIV] > 0) {
            if (modCenNiv == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_CEN_NIV], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_CEN_NIV], modCenNiv.intValue());
        }
        if (val[DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP] > 0) {
            if (modPcrCodigoEntrep == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP], 3);
            else
                stmt.setInt(val[DoXnModeleDesc.MOD_PCR_CODIGO_ENTREP], modPcrCodigoEntrep.intValue());
        }
        if (val[DoXnModeleDesc.MOD_EPAISSEUR_CINTRE] > 0) {
            if (modEpaisseurCintre == null)
                stmt.setNull(val[DoXnModeleDesc.MOD_EPAISSEUR_CINTRE], 3);
            else
                stmt.setDouble(val[DoXnModeleDesc.MOD_EPAISSEUR_CINTRE], modEpaisseurCintre.doubleValue());
        }
        if (val[DoXnModeleDesc.MOD_TYPE_ECLATEMENT] > 0) {
            stmt.setString(val[DoXnModeleDesc.MOD_TYPE_ECLATEMENT], modTypeEclatement);
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
        DoXnModele[] result = null;
        params = request.getParameterValues("modCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModCode(localVal);
            }
        }
        params = request.getParameterValues("modDes");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModDes(localVal);
            }
        }
        params = request.getParameterValues("modLib");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModLib(localVal);
            }
        }
        params = request.getParameterValues("modNatCond");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModNatCond(localVal);
            }
        }
        params = request.getParameterValues("modNomen");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModNomen(localVal);
            }
        }
        params = request.getParameterValues("modArtTest");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModArtTest(localVal);
            }
        }
        params = request.getParameterValues("modCatAmf");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModCatAmf(localVal);
            }
        }
        params = request.getParameterValues("modGrtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModGrtCode(localVal);
            }
        }
        params = request.getParameterValues("modSfaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModSfaCode(localVal);
            }
        }
        params = request.getParameterValues("modSfaFaaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModSfaFaaCode(localVal);
            }
        }
        params = request.getParameterValues("modTvaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModTvaCode(localVal);
            }
        }
        params = request.getParameterValues("modCeeCoef");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModCeeCoef((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("modPxInit");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModPxInit((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("modPcb");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModPcb((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modSpcb");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModSpcb((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modColisPoids");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModColisPoids((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("modColisVol");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModColisVol((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("modNbVar");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModNbVar((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modDtFinVie");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModDtFinVie((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("modDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("modDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("modUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
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
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
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
        params = request.getParameterValues("modTcaCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModTcaCode(localVal);
            }
        }
        params = request.getParameterValues("modType");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModType(localVal);
            }
        }
        params = request.getParameterValues("modTypeArticle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModTypeArticle(localVal);
            }
        }
        params = request.getParameterValues("modArgPcrCodigo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModArgPcrCodigo((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modArgQualite");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModArgQualite((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modCenNiv");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModCenNiv((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modPcrCodigoEntrep");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModPcrCodigoEntrep((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("modEpaisseurCintre");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModEpaisseurCintre((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("modTypeEclatement");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoXnModele[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoXnModele();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setModTypeEclatement(localVal);
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

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

public class DoDtRecepCtrlSourceTete implements DataObject {

    private static final IDoDescription description = new DoDtRecepCtrlSourceTeteDesc();
    private /*transient*/ int persist = PERSIST_UPDATE_INSERT;
    private /*transient*/ int[] updCol = new int[33];
    private /*transient*/ String sql;
    private /*transient*/ Object[] param;

//tables correspondantes
    private static final String[] tableNames = new String[]{"DT_RECEP_CTRL_SOURCE_TETE"};
    //variables correspondant à la table DT_RECEP_CTRL_SOURCE_TETE
    private String rstCamCatDepCode = null;
    private String rstCamCatDepSocCode = null;
    private Integer rstCamNoCmde = null;
    private Integer rstCamCatNoVersion = null;
    private Integer rstCamNoLigne = null;
    private String rstCamArtCode = null;
    private String rstCamArtVar1 = null;
    private String rstCamArtVar2 = null;
    private String rstCamArtVar3 = null;
    private Integer rstNoControle = null;
    private String rstPrestataire = null;
    private Timestamp rstDtCtrlSourceReel = null;
    private Timestamp rstDtCtrlSourceRapport = null;
    private Timestamp rstDtCtrlSourceLabo = null;
    private Double rstQtePresCtrlSource = null;
    private Double rstQtePrelCtrlSource = null;
    private Timestamp rstDtEnvoiEchant = null;
    private Integer rstEchantScelle01 = null;
    private String rstMotifScelle = null;
    private String rstNoColisEchant = null;
    private String rstOkCtrlEntrep = null;
    private Timestamp rstDtCreat = null;
    private Timestamp rstDtMaj = null;
    private String rstUtilMaj = null;
    private Integer xtxId = null;
    private String zStatus = null;
    private Timestamp rstDtCtrlSourceSouhait = null;
    private Timestamp rstDtCtrlSourcePrev = null;
    private Integer rstNiveauCtrle = null;
    private String rstCommentaire = null;
    private String rstOkCtrlSource = null;
    private String rstIndex = null;
    private Timestamp rstDtDecisionPrestataire = null;

    /**
     * Constructeur utilisé par la méthode setPropertie
     */
    public DoDtRecepCtrlSourceTete() {
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtRecepCtrlSourceTete(int persistTyp) {
        persist = persistTyp;
    }

    /**
     * Constructeur permettant d'initialiser le type de persistance
     */
    public DoDtRecepCtrlSourceTete(DoDtRecepCtrlSourceTete arg) {
        setRstCamCatDepCode(arg.rstCamCatDepCode);
        setRstCamCatDepSocCode(arg.rstCamCatDepSocCode);
        setRstCamNoCmde(arg.rstCamNoCmde);
        setRstCamCatNoVersion(arg.rstCamCatNoVersion);
        setRstCamNoLigne(arg.rstCamNoLigne);
        setRstCamArtCode(arg.rstCamArtCode);
        setRstCamArtVar1(arg.rstCamArtVar1);
        setRstCamArtVar2(arg.rstCamArtVar2);
        setRstCamArtVar3(arg.rstCamArtVar3);
        setRstNoControle(arg.rstNoControle);
        setRstPrestataire(arg.rstPrestataire);
        setRstDtCtrlSourceReel(arg.rstDtCtrlSourceReel);
        setRstDtCtrlSourceRapport(arg.rstDtCtrlSourceRapport);
        setRstDtCtrlSourceLabo(arg.rstDtCtrlSourceLabo);
        setRstQtePresCtrlSource(arg.rstQtePresCtrlSource);
        setRstQtePrelCtrlSource(arg.rstQtePrelCtrlSource);
        setRstDtEnvoiEchant(arg.rstDtEnvoiEchant);
        setRstEchantScelle01(arg.rstEchantScelle01);
        setRstMotifScelle(arg.rstMotifScelle);
        setRstNoColisEchant(arg.rstNoColisEchant);
        setRstOkCtrlEntrep(arg.rstOkCtrlEntrep);
        setRstDtCreat(arg.rstDtCreat);
        setRstDtMaj(arg.rstDtMaj);
        setRstUtilMaj(arg.rstUtilMaj);
        setXtxId(arg.xtxId);
        setZStatus(arg.zStatus);
        setRstDtCtrlSourceSouhait(arg.rstDtCtrlSourceSouhait);
        setRstDtCtrlSourcePrev(arg.rstDtCtrlSourcePrev);
        setRstNiveauCtrle(arg.rstNiveauCtrle);
        setRstCommentaire(arg.rstCommentaire);
        setRstOkCtrlSource(arg.rstOkCtrlSource);
        setRstIndex(arg.rstIndex);
        setRstDtDecisionPrestataire(arg.rstDtDecisionPrestataire);
    }

    /**
     * Constructeur utilisé par la méthode retrieve
     */
    public DoDtRecepCtrlSourceTete(String newSql, Object[] newParam) {
        sql = newSql;
        param = newParam;
    }

    public int getPersist() {
        return persist;
    }

    public void setPersist(int newPersist) {
        persist = newPersist;
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

    public String getRstCamCatDepCode() {
        return rstCamCatDepCode;
    }

    public String getRstCamCatDepSocCode() {
        return rstCamCatDepSocCode;
    }

    public Integer getRstCamNoCmde() {
        return rstCamNoCmde;
    }

    public Integer getRstCamCatNoVersion() {
        return rstCamCatNoVersion;
    }

    public Integer getRstCamNoLigne() {
        return rstCamNoLigne;
    }

    public String getRstCamArtCode() {
        return rstCamArtCode;
    }

    public String getRstCamArtVar1() {
        return rstCamArtVar1;
    }

    public String getRstCamArtVar2() {
        return rstCamArtVar2;
    }

    public String getRstCamArtVar3() {
        return rstCamArtVar3;
    }

    public Integer getRstNoControle() {
        return rstNoControle;
    }

    public String getRstPrestataire() {
        return rstPrestataire;
    }

    public Timestamp getRstDtCtrlSourceReel() {
        return rstDtCtrlSourceReel;
    }

    public Timestamp getRstDtCtrlSourceRapport() {
        return rstDtCtrlSourceRapport;
    }

    public Timestamp getRstDtCtrlSourceLabo() {
        return rstDtCtrlSourceLabo;
    }

    public Double getRstQtePresCtrlSource() {
        return rstQtePresCtrlSource;
    }

    public Double getRstQtePrelCtrlSource() {
        return rstQtePrelCtrlSource;
    }

    public Timestamp getRstDtEnvoiEchant() {
        return rstDtEnvoiEchant;
    }

    public Integer getRstEchantScelle01() {
        return rstEchantScelle01;
    }

    public String getRstMotifScelle() {
        return rstMotifScelle;
    }

    public String getRstNoColisEchant() {
        return rstNoColisEchant;
    }

    public String getRstOkCtrlEntrep() {
        return rstOkCtrlEntrep;
    }

    public Timestamp getRstDtCreat() {
        return rstDtCreat;
    }

    public Timestamp getRstDtMaj() {
        return rstDtMaj;
    }

    public String getRstUtilMaj() {
        return rstUtilMaj;
    }

    public Integer getXtxId() {
        return xtxId;
    }

    public String getZStatus() {
        return zStatus;
    }

    public Timestamp getRstDtCtrlSourceSouhait() {
        return rstDtCtrlSourceSouhait;
    }

    public Timestamp getRstDtCtrlSourcePrev() {
        return rstDtCtrlSourcePrev;
    }

    public Integer getRstNiveauCtrle() {
        return rstNiveauCtrle;
    }

    public String getRstCommentaire() {
        return rstCommentaire;
    }

    public String getRstOkCtrlSource() {
        return rstOkCtrlSource;
    }

    public String getRstIndex() {
        return rstIndex;
    }

    public Timestamp getRstDtDecisionPrestataire() {
        return rstDtDecisionPrestataire;
    }

    public void setRstCamCatDepCode(String newRstCamCatDepCode) {
        rstCamCatDepCode = newRstCamCatDepCode;
    }

    public void setRstCamCatDepSocCode(String newRstCamCatDepSocCode) {
        rstCamCatDepSocCode = newRstCamCatDepSocCode;
    }

    public void setRstCamNoCmde(Integer newRstCamNoCmde) {
        rstCamNoCmde = newRstCamNoCmde;
    }

    public void setRstCamCatNoVersion(Integer newRstCamCatNoVersion) {
        rstCamCatNoVersion = newRstCamCatNoVersion;
    }

    public void setRstCamNoLigne(Integer newRstCamNoLigne) {
        rstCamNoLigne = newRstCamNoLigne;
    }

    public void setRstCamArtCode(String newRstCamArtCode) {
        rstCamArtCode = newRstCamArtCode;
    }

    public void setRstCamArtVar1(String newRstCamArtVar1) {
        rstCamArtVar1 = newRstCamArtVar1;
    }

    public void setRstCamArtVar2(String newRstCamArtVar2) {
        rstCamArtVar2 = newRstCamArtVar2;
    }

    public void setRstCamArtVar3(String newRstCamArtVar3) {
        rstCamArtVar3 = newRstCamArtVar3;
    }

    public void setRstNoControle(Integer newRstNoControle) {
        rstNoControle = newRstNoControle;
    }

    public void setRstPrestataire(String newRstPrestataire) {
        rstPrestataire = newRstPrestataire;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE] = 1;
    }

    public void setRstDtCtrlSourceReel(Timestamp newRstDtCtrlSourceReel) {
        rstDtCtrlSourceReel = newRstDtCtrlSourceReel;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL] = 1;
    }

    public void setRstDtCtrlSourceRapport(Timestamp newRstDtCtrlSourceRapport) {
        rstDtCtrlSourceRapport = newRstDtCtrlSourceRapport;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT] = 1;
    }

    public void setRstDtCtrlSourceLabo(Timestamp newRstDtCtrlSourceLabo) {
        rstDtCtrlSourceLabo = newRstDtCtrlSourceLabo;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO] = 1;
    }

    public void setRstQtePresCtrlSource(Double newRstQtePresCtrlSource) {
        rstQtePresCtrlSource = newRstQtePresCtrlSource;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE] = 1;
    }

    public void setRstQtePrelCtrlSource(Double newRstQtePrelCtrlSource) {
        rstQtePrelCtrlSource = newRstQtePrelCtrlSource;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE] = 1;
    }

    public void setRstDtEnvoiEchant(Timestamp newRstDtEnvoiEchant) {
        rstDtEnvoiEchant = newRstDtEnvoiEchant;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT] = 1;
    }

    public void setRstEchantScelle01(Integer newRstEchantScelle01) {
        rstEchantScelle01 = newRstEchantScelle01;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01] = 1;
    }

    public void setRstMotifScelle(String newRstMotifScelle) {
        rstMotifScelle = newRstMotifScelle;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE] = 1;
    }

    public void setRstNoColisEchant(String newRstNoColisEchant) {
        rstNoColisEchant = newRstNoColisEchant;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT] = 1;
    }

    public void setRstOkCtrlEntrep(String newRstOkCtrlEntrep) {
        rstOkCtrlEntrep = newRstOkCtrlEntrep;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP] = 1;
    }

    public void setRstDtCreat(Timestamp newRstDtCreat) {
        rstDtCreat = newRstDtCreat;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT] = 1;
    }

    public void setRstDtMaj(Timestamp newRstDtMaj) {
        rstDtMaj = newRstDtMaj;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ] = 1;
    }

    public void setRstUtilMaj(String newRstUtilMaj) {
        rstUtilMaj = newRstUtilMaj;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ] = 1;
    }

    public void setXtxId(Integer newXtxId) {
        xtxId = newXtxId;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.XTX_ID] = 1;
    }

    public void setZStatus(String newZStatus) {
        zStatus = newZStatus;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.Z_STATUS] = 1;
    }

    public void setRstDtCtrlSourceSouhait(Timestamp newRstDtCtrlSourceSouhait) {
        rstDtCtrlSourceSouhait = newRstDtCtrlSourceSouhait;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT] = 1;
    }

    public void setRstDtCtrlSourcePrev(Timestamp newRstDtCtrlSourcePrev) {
        rstDtCtrlSourcePrev = newRstDtCtrlSourcePrev;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV] = 1;
    }

    public void setRstNiveauCtrle(Integer newRstNiveauCtrle) {
        rstNiveauCtrle = newRstNiveauCtrle;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE] = 1;
    }

    public void setRstCommentaire(String newRstCommentaire) {
        rstCommentaire = newRstCommentaire;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE] = 1;
    }

    public void setRstOkCtrlSource(String newRstOkCtrlSource) {
        rstOkCtrlSource = newRstOkCtrlSource;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE] = 1;
    }

    public void setRstIndex(String newRstIndex) {
        rstIndex = newRstIndex;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_INDEX] = 1;
    }

    public void setRstDtDecisionPrestataire(Timestamp newRstDtDecisionPrestataire) {
        rstDtDecisionPrestataire = newRstDtDecisionPrestataire;
        if (persist > 0)
            updCol[DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE] = 1;
    }

    public Object get(int numCol) {
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_CODE)
            return rstCamCatDepCode;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_SOC_CODE)
            return rstCamCatDepSocCode;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE)
            return rstCamNoCmde;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION)
            return rstCamCatNoVersion;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE)
            return rstCamNoLigne;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_CODE)
            return rstCamArtCode;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR1)
            return rstCamArtVar1;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR2)
            return rstCamArtVar2;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR3)
            return rstCamArtVar3;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE)
            return rstNoControle;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE)
            return rstPrestataire;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL)
            return rstDtCtrlSourceReel;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT)
            return rstDtCtrlSourceRapport;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO)
            return rstDtCtrlSourceLabo;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE)
            return rstQtePresCtrlSource;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE)
            return rstQtePrelCtrlSource;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT)
            return rstDtEnvoiEchant;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01)
            return rstEchantScelle01;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE)
            return rstMotifScelle;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT)
            return rstNoColisEchant;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP)
            return rstOkCtrlEntrep;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT)
            return rstDtCreat;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ)
            return rstDtMaj;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ)
            return rstUtilMaj;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.XTX_ID)
            return xtxId;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.Z_STATUS)
            return zStatus;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT)
            return rstDtCtrlSourceSouhait;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV)
            return rstDtCtrlSourcePrev;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE)
            return rstNiveauCtrle;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE)
            return rstCommentaire;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE)
            return rstOkCtrlSource;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_INDEX)
            return rstIndex;
        else if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE)
            return rstDtDecisionPrestataire;
        return null;
    }

    public void set(int numCol, Object value) {
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_CODE) {
            rstCamCatDepCode = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_SOC_CODE) {
            rstCamCatDepSocCode = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE) {
            rstCamNoCmde = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION) {
            rstCamCatNoVersion = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE) {
            rstCamNoLigne = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_CODE) {
            rstCamArtCode = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR1) {
            rstCamArtVar1 = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR2) {
            rstCamArtVar2 = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR3) {
            rstCamArtVar3 = (String) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE) {
            rstNoControle = (Integer) value;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE) {
            rstPrestataire = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL) {
            rstDtCtrlSourceReel = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT) {
            rstDtCtrlSourceRapport = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO) {
            rstDtCtrlSourceLabo = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE) {
            rstQtePresCtrlSource = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE) {
            rstQtePrelCtrlSource = (Double) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT) {
            rstDtEnvoiEchant = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01) {
            rstEchantScelle01 = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE) {
            rstMotifScelle = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT) {
            rstNoColisEchant = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP) {
            rstOkCtrlEntrep = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT) {
            rstDtCreat = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ) {
            rstDtMaj = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ) {
            rstUtilMaj = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.XTX_ID) {
            xtxId = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.Z_STATUS) {
            zStatus = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT) {
            rstDtCtrlSourceSouhait = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV) {
            rstDtCtrlSourcePrev = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE) {
            rstNiveauCtrle = (Integer) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE) {
            rstCommentaire = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE) {
            rstOkCtrlSource = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_INDEX) {
            rstIndex = (String) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
        if (numCol == DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE) {
            rstDtDecisionPrestataire = (Timestamp) value;
            if (persist > 0)
                updCol[numCol] = 1;
        }
    }

    public DataObject setProperty(SqlArg sqlArg) throws SQLException {
        return setProperty(sqlArg, new DoDtRecepCtrlSourceTete());
    }

    private DataObject setProperty(SqlArg sqlArg, DoDtRecepCtrlSourceTete djo) throws SQLException {
        ResultSet rs = sqlArg.getResultSet();
        int[] val = sqlArg.getVal();
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_CODE] != -1) {
            djo.rstCamCatDepCode = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_CODE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_SOC_CODE] != -1) {
            djo.rstCamCatDepSocCode = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_SOC_CODE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE]);
            if (!rs.wasNull())
                djo.rstCamNoCmde = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION]);
            if (!rs.wasNull())
                djo.rstCamCatNoVersion = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE]);
            if (!rs.wasNull())
                djo.rstCamNoLigne = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_CODE] != -1) {
            djo.rstCamArtCode = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_CODE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR1] != -1) {
            djo.rstCamArtVar1 = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR1]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR2] != -1) {
            djo.rstCamArtVar2 = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR2]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR3] != -1) {
            djo.rstCamArtVar3 = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR3]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE]);
            if (!rs.wasNull())
                djo.rstNoControle = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE] != -1) {
            djo.rstPrestataire = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL] != -1) {
            djo.rstDtCtrlSourceReel = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT] != -1) {
            djo.rstDtCtrlSourceRapport = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO] != -1) {
            djo.rstDtCtrlSourceLabo = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE] != -1) {
            double temp = rs.getDouble(val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE]);
            if (!rs.wasNull())
                djo.rstQtePresCtrlSource = new Double(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE] != -1) {
            double temp = rs.getDouble(val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE]);
            if (!rs.wasNull())
                djo.rstQtePrelCtrlSource = new Double(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT] != -1) {
            djo.rstDtEnvoiEchant = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01]);
            if (!rs.wasNull())
                djo.rstEchantScelle01 = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE] != -1) {
            djo.rstMotifScelle = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT] != -1) {
            djo.rstNoColisEchant = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP] != -1) {
            djo.rstOkCtrlEntrep = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT] != -1) {
            djo.rstDtCreat = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ] != -1) {
            djo.rstDtMaj = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ] != -1) {
            djo.rstUtilMaj = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.XTX_ID] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.XTX_ID]);
            if (!rs.wasNull())
                djo.xtxId = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.Z_STATUS] != -1) {
            djo.zStatus = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.Z_STATUS]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT] != -1) {
            djo.rstDtCtrlSourceSouhait = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV] != -1) {
            djo.rstDtCtrlSourcePrev = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE] != -1) {
            int temp = rs.getInt(val[DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE]);
            if (!rs.wasNull())
                djo.rstNiveauCtrle = new Integer(temp);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE] != -1) {
            djo.rstCommentaire = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE] != -1) {
            djo.rstOkCtrlSource = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_INDEX] != -1) {
            djo.rstIndex = rs.getString(val[DoDtRecepCtrlSourceTeteDesc.RST_INDEX]);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE] != -1) {
            djo.rstDtDecisionPrestataire = rs.getTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE]);
        }
        return djo;
    }

    public void getProperty(SqlArg sqlArg) throws SQLException {
        PreparedStatement stmt = sqlArg.getStmt();
        int[] val = sqlArg.getVal();
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_CODE], rstCamCatDepCode);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_SOC_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_DEP_SOC_CODE], rstCamCatDepSocCode);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE] > 0) {
            if (rstCamNoCmde == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_CMDE], rstCamNoCmde.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION] > 0) {
            if (rstCamCatNoVersion == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_CAT_NO_VERSION], rstCamCatNoVersion.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE] > 0) {
            if (rstCamNoLigne == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_NO_LIGNE], rstCamNoLigne.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_CODE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_CODE], rstCamArtCode);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR1] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR1], rstCamArtVar1);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR2] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR2], rstCamArtVar2);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR3] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_CAM_ART_VAR3], rstCamArtVar3);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE] > 0) {
            if (rstNoControle == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.RST_NO_CONTROLE], rstNoControle.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_PRESTATAIRE], rstPrestataire);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_REEL], rstDtCtrlSourceReel);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_RAPPORT], rstDtCtrlSourceRapport);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_LABO], rstDtCtrlSourceLabo);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE] > 0) {
            if (rstQtePresCtrlSource == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE], 3);
            else
                stmt.setDouble(val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PRES_CTRL_SOURCE], rstQtePresCtrlSource.doubleValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE] > 0) {
            if (rstQtePrelCtrlSource == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE], 3);
            else
                stmt.setDouble(val[DoDtRecepCtrlSourceTeteDesc.RST_QTE_PREL_CTRL_SOURCE], rstQtePrelCtrlSource.doubleValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_ENVOI_ECHANT], rstDtEnvoiEchant);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01] > 0) {
            if (rstEchantScelle01 == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.RST_ECHANT_SCELLE01], rstEchantScelle01.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_MOTIF_SCELLE], rstMotifScelle);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_NO_COLIS_ECHANT], rstNoColisEchant);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_ENTREP], rstOkCtrlEntrep);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CREAT], rstDtCreat);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_MAJ], rstDtMaj);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_UTIL_MAJ], rstUtilMaj);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.XTX_ID] > 0) {
            if (xtxId == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.XTX_ID], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.XTX_ID], xtxId.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.Z_STATUS] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.Z_STATUS], zStatus);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_SOUHAIT], rstDtCtrlSourceSouhait);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_CTRL_SOURCE_PREV], rstDtCtrlSourcePrev);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE] > 0) {
            if (rstNiveauCtrle == null)
                stmt.setNull(val[DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE], 3);
            else
                stmt.setInt(val[DoDtRecepCtrlSourceTeteDesc.RST_NIVEAU_CTRLE], rstNiveauCtrle.intValue());
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_COMMENTAIRE], rstCommentaire);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_OK_CTRL_SOURCE], rstOkCtrlSource);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_INDEX] > 0) {
            stmt.setString(val[DoDtRecepCtrlSourceTeteDesc.RST_INDEX], rstIndex);
        }
        if (val[DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE] > 0) {
            stmt.setTimestamp(val[DoDtRecepCtrlSourceTeteDesc.RST_DT_DECISION_PRESTATAIRE], rstDtDecisionPrestataire);
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
        DoDtRecepCtrlSourceTete[] result = null;
        params = request.getParameterValues("rstCamCatDepCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamCatDepCode(localVal);
            }
        }
        params = request.getParameterValues("rstCamCatDepSocCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamCatDepSocCode(localVal);
            }
        }
        params = request.getParameterValues("rstCamNoCmde");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamNoCmde((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rstCamCatNoVersion");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamCatNoVersion((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rstCamNoLigne");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamNoLigne((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rstCamArtCode");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamArtCode(localVal);
            }
        }
        params = request.getParameterValues("rstCamArtVar1");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamArtVar1(localVal);
            }
        }
        params = request.getParameterValues("rstCamArtVar2");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamArtVar2(localVal);
            }
        }
        params = request.getParameterValues("rstCamArtVar3");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCamArtVar3(localVal);
            }
        }
        params = request.getParameterValues("rstNoControle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstNoControle((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rstPrestataire");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstPrestataire(localVal);
            }
        }
        params = request.getParameterValues("rstDtCtrlSourceReel");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtCtrlSourceReel((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstDtCtrlSourceRapport");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtCtrlSourceRapport((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstDtCtrlSourceLabo");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtCtrlSourceLabo((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstQtePresCtrlSource");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstQtePresCtrlSource((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("rstQtePrelCtrlSource");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstQtePrelCtrlSource((Double) StrConvertor.convert(localVal, Double.class));
            }
        }
        params = request.getParameterValues("rstDtEnvoiEchant");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtEnvoiEchant((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstEchantScelle01");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstEchantScelle01((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rstMotifScelle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstMotifScelle(localVal);
            }
        }
        params = request.getParameterValues("rstNoColisEchant");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstNoColisEchant(localVal);
            }
        }
        params = request.getParameterValues("rstOkCtrlEntrep");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstOkCtrlEntrep(localVal);
            }
        }
        params = request.getParameterValues("rstDtCreat");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtCreat((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstDtMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtMaj((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstUtilMaj");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstUtilMaj(localVal);
            }
        }
        params = request.getParameterValues("xtxId");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
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
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
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
        params = request.getParameterValues("rstDtCtrlSourceSouhait");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtCtrlSourceSouhait((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstDtCtrlSourcePrev");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtCtrlSourcePrev((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
            }
        }
        params = request.getParameterValues("rstNiveauCtrle");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstNiveauCtrle((Integer) StrConvertor.convert(localVal, Integer.class));
            }
        }
        params = request.getParameterValues("rstCommentaire");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstCommentaire(localVal);
            }
        }
        params = request.getParameterValues("rstOkCtrlSource");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstOkCtrlSource(localVal);
            }
        }
        params = request.getParameterValues("rstIndex");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstIndex(localVal);
            }
        }
        params = request.getParameterValues("rstDtDecisionPrestataire");
        if (params != null) {
            if (result == null) {
                size = params.length;
                result = new DoDtRecepCtrlSourceTete[size];
                for (int i = 0; i < size; i++) {
                    result[i] = new DoDtRecepCtrlSourceTete();
                }
            }
            for (int i = 0; i < size; i++) {
                if (params.length == 1)
                    localVal = params[0];
                else
                    localVal = params[i];
                result[i].setRstDtDecisionPrestataire((Timestamp) StrConvertor.convert(localVal, Timestamp.class));
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



    /****************************************************************/
    //

    private String extraArtCode;
    private String extraArtName;
    private String extraColorCode;
    private String extraColorName;
    private Integer extraInspectionType;
    private DoDtRecepCtrlSource[] doDtRecepCtrlSources;
    private DoDtRecepCtrlSourceColis[] doDtRecepCtrlSourceColis;

    public String getExtraArtCode() {
        return extraArtCode;
    }

    public void setExtraArtCode(String extraArtCode) {
        this.extraArtCode = extraArtCode;
    }

    public String getExtraArtName() {
        return extraArtName;
    }

    public void setExtraArtName(String extraArtName) {
        this.extraArtName = extraArtName;
    }

    public String getExtraColorCode() {
        return extraColorCode;
    }

    public void setExtraColorCode(String extraColorCode) {
        this.extraColorCode = extraColorCode;
    }

    public String getExtraColorName() {
        return extraColorName;
    }

    public void setExtraColorName(String extraColorName) {
        this.extraColorName = extraColorName;
    }

    public Integer getExtraInspectionType() {
        return extraInspectionType;
    }

    public void setExtraInspectionType(Integer extraInspectionType) {
        this.extraInspectionType = extraInspectionType;
    }

    public void setDtRecepCtrlSources(DoDtRecepCtrlSource[] lines) {
        this.doDtRecepCtrlSources = lines;
    }

    public DoDtRecepCtrlSource[] getDtRecepCtrlSources() {
        return this.doDtRecepCtrlSources;
    }

    public void setDtRecepCtrlSourceColis(DoDtRecepCtrlSourceColis[] lines) {
        this.doDtRecepCtrlSourceColis = lines;
    }

    public DoDtRecepCtrlSourceColis[] getDtRecepCtrlSourceColis() {
        return this.doDtRecepCtrlSourceColis;
    }

    private RecordEditorHelper helper;

    public RecordEditorHelper getHelper() {
        if (helper == null) {
            helper = new RecordEditorHelper();
        }
        return helper;
    }

    public void resetUpdate() {
        Arrays.fill(updCol, -1);
        if (doDtRecepCtrlSourceColis != null) {
            for (int i = 0; i < doDtRecepCtrlSourceColis.length; i++) {
                doDtRecepCtrlSourceColis[i].resetUpdate();
            }
        }
        if (doDtRecepCtrlSources != null) {
            for (int i = 0; i < doDtRecepCtrlSources.length; i++) {
                doDtRecepCtrlSources[i].resetUpdate();
            }
        }
    }
}

package camaieu.egarmentcq.bo;

import camaieu.common.BoEasyAncestorBean;
import camaieu.common.NumberUtils;
import camaieu.common.Struct;
import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.dataobject.DoDtCmdeAchatTete;
import camaieu.egarmentcq.dataobject.DoXnArt;
import camaieu.egarmentcq.report.OrderReport;
import camaieu.webform.AbstractHeteroData;
import wg4.bean.ancestor.MetierException;
import wg4.bean.ancestor.TechniqueException;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Business Object pour la manipulation des Commandes d'achat.
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 15/01/2004
 * @last_modification_date 16/01/2004
 * @status pour validation
 */
public class BoDtCmdeAchatTete extends BoEasyAncestorBean {
    public BoDtCmdeAchatTete() {
        super(DoDtCmdeAchatTete.class, EGCQBusinessConstants.DATASOURCE_NAME);
    }

    /**
     * liste des commandes.
     * <BR>
     * La requ�te utilis�e est
     * <BR>
     * <code>
     * select
     * 	fou_nom, dep_nom, dep_postal, dep_ville, , cat_no_cmde,
     * cat_no_version, cat_mod_code,
     * 	mod_des, cam_art_var1, cou_lib, cam_qte_cmde, cat_dt_cmde,
     * cat_dt_depart2,
     * 	rst_dt_ctrl_source_prev, rst_dt_ctrl_source_souhait,
     * dt_recep_ctrl_source_tete.z_status
     * from
     * 	dt_recep_ctrl_source_tete, dt_cmde_achat_mc, dt_cmde_achat_tete,
     * xn_modele, xn_art,
     * 	xn_couleur, xn_four, xn_depot
     * where
     * 	-- liaison entre DT_RECEP_CTRL_SOURCE_TETE et DT_CMDE_ACHAT_MC
     * 	rst_cam_cat_dep_code = cam_cat_dep_code and
     * 	rst_cam_cat_dep_soc_code = cam_cat_dep_soc_code and
     * 	rst_cam_no_cmde = cam_cat_no_cmde and
     * 	rst_cam_cat_no_version = cam_cat_no_version and
     * 	rst_cam_no_ligne = cam_no_ligne and
     * 	rst_cam_art_code = cam_art_code and
     * 	rst_cam_art_var1 = cam_art_var1 and
     * 	rst_cam_art_var2 = cam_art_var2 and
     * 	rst_cam_art_var3 = cam_art_var3 and
     * 	-- liaison entre DT_CMDE_ACHAT_TETE et DT_CMDE_ACHAT_MC
     * 	cam_cat_dep_code = cat_dep_code and
     * 	cam_cat_dep_soc_code = cat_dep_soc_code and
     * 	cam_cat_no_cmde = cat_no_cmde and
     * 	cam_cat_no_version = cat_no_version and
     * 	art_code = cam_art_code and
     * 	art_var1 = cam_art_var1 and
     * 	art_var2 = cam_art_var2 and
     * 	art_var3 = cam_art_var3 and
     * 	-- liaison entre XN_MODELE et XN_ART
     * 	mod_code = art_mod_code and
     * 	-- liaison entre XN_COULEUR et DT_CMDE_ACHAT_MC
     * 	cam_art_var1 = cou_code and
     * 	-- liaison entre XN_FOUR et DT_CMDE_ACHAT_MC
     * 	cat_fou_code = fou_code and
     * 	-- liaison entre XN_DEPOT et DT_CMDE_ACHAT_TETE
     * 	cat_dep_code_ctrle_source = dep_code and
     * 	art_var1<>'-' and -- ???
     * 	cat_decision_finale = 'NE' and -- decision cama�eu non prise
     * 	rst_prestataire = 3000 -- code du prestataire de contr�le
     * order by
     * cat_dt_ctrl_source_prevu
     * </code>
     * <BR>
     * pour la r�cup�ration des donn�es, on utilise
     * @see wg4.bean.ancestor.BoAncestorBean#retrieve(java.lang.String , java.lang.String , java.lang.Object[])
     * @param supplicerCode
     * @return
     * @throws MetierException
     */
    public CommandeData[] getCommandesData(String supplicerCode) throws MetierException {
        try {
            Object[][] lines = retrieve(
                    EGCQBusinessConstants.DATASOURCE_NAME,

                    //requ�te
                    "select " +
                    " fou_nom, dep_nom, dep_postal, dep_ville, cat_no_cmde, " +
                    " cat_no_version, cat_mod_code, " +
                    " cam_art_var1, cou_lib, cam_qte_cmde, cat_dt_cmde, mod_des, " +
                    " cat_dt_depart2, " +
                    " rst_dt_ctrl_source_prev, rst_dt_ctrl_source_souhait, " +
                    " dt_recep_ctrl_source_tete.rst_index" +
                    //lignes rajout�es par taha (tbensalah), ADD'IT
                    " ,cam_art_code, cam_art_var2, cam_art_var3, cam_no_ligne, rst_no_controle" +
                    ",cat_dep_code,cat_dep_soc_code,fou_code,cat_dep_code_ctrle_source " +
                    // fin taha
                    "from " +
                    " dt_recep_ctrl_source_tete, dt_cmde_achat_mc, dt_cmde_achat_tete, " +
                    " xn_modele, xn_art, " +
                    " xn_couleur, xn_four, xn_depot " +
                    "where " +
                    // liaison entre DT_RECEP_CTRL_SOURCE_TETE et DT_CMDE_ACHAT_MC
                    " rst_cam_cat_dep_code = cam_cat_dep_code and " +
                    " rst_cam_cat_dep_soc_code = cam_cat_dep_soc_code and " +
                    " rst_cam_no_cmde = cam_cat_no_cmde and " +
                    " rst_cam_cat_no_version = cam_cat_no_version and " +
                    " rst_cam_no_ligne = cam_no_ligne and " +
                    " rst_cam_art_code = cam_art_code and " +
                    " rst_cam_art_var1 = cam_art_var1 and " +
                    " rst_cam_art_var2 = cam_art_var2 and " +
                    " rst_cam_art_var3 = cam_art_var3 and " +
                    // liaison entre DT_CMDE_ACHAT_TETE et DT_CMDE_ACHAT_MC
                    " cam_cat_dep_code = cat_dep_code and " +
                    " cam_cat_dep_soc_code = cat_dep_soc_code and " +
                    " cam_cat_no_cmde = cat_no_cmde and " +
                    " cam_cat_no_version = cat_no_version and " +
                    " art_code = cam_art_code and " +
                    " art_var1 = cam_art_var1 and " +
                    " art_var2 = cam_art_var2 and " +
                    " art_var3 = cam_art_var3 and " +
                    // liaison entre XN_MODELE et XN_ART
                    " mod_code = art_mod_code and " +
                    // liaison entre XN_COULEUR et DT_CMDE_ACHAT_MC
                    " cam_art_var1 = cou_code and " +
                    // liaison entre XN_FOUR et DT_CMDE_ACHAT_MC
                    " cat_fou_code = fou_code and " +
                    // liaison entre XN_DEPOT et DT_CMDE_ACHAT_TETE
                    " cat_dep_code_ctrle_source = dep_code and " +
                    " art_var1<>'-' and " + // -- ???
                    " cat_decision_finale = 'NE' and " + //-- decision cama�eu non prise
                    " rst_prestataire = ? " + //-- code du prestataire de contr�le
                    "order by " +
                    "dt_recep_ctrl_source_tete.rst_index DESC, cat_dt_ctrl_source_prevue ASC",

                    new Object[]{supplicerCode}
            );
//            System.out.println(">>>>>>>>>>  requete = " + query);
            CommandeData[] commandes = new CommandeData[lines.length];
            for (int i = 0; i < commandes.length; i++) {
                commandes[i] = new CommandeData(
                        (String) lines[i][0],
                        (String) lines[i][1],
                        (String) lines[i][2],
                        (String) lines[i][3],
                        NumberUtils.toInteger((Number) lines[i][4]),
                        NumberUtils.toInteger((Number) lines[i][5]),
                        (String) lines[i][6],
                        (String) lines[i][7],
                        (String) lines[i][8],
                        NumberUtils.toInteger((Number) lines[i][9]),
                        (Date) lines[i][10],
                        (String) lines[i][11],
                        (Date) lines[i][12],
                        (Date) lines[i][13],
                        (Date) lines[i][14],
                        (String) lines[i][15],
                        (String) lines[i][16],
                        (String) lines[i][17],
                        (String) lines[i][18],
                        NumberUtils.toInteger((Number) lines[i][19]),
                        NumberUtils.toInteger((Number) lines[i][20]),
                        (String) lines[i][21],
                        (String) lines[i][22],
                        (String) lines[i][23],
                        (String) lines[i][24]
                );
            }


            return commandes;
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    /**
     * les donn�es n�cessaires � la g�n�ration du rapport pdf
     * @param doDtCmdeAchatTete instance de DoDtCmdeAchatTete contenant la clef
     * @return les donn�es n�cessaires � la g�n�ration du rapport pdf
     * @throws MetierException
     */
    public OrderReport.OrderReportData getOrderReportData(DoDtCmdeAchatTete doDtCmdeAchatTete,String lang) throws MetierException {
        OrderReport.OrderReportData data = new OrderReport.OrderReportData();
        try {
            Object[][] lines = retrieve(
                    EGCQBusinessConstants.DATASOURCE_NAME,
                    //requ�te
                    "select " +
                    "cat_fou_code, fou_nom, fou_adr1, fou_adr2, fou_postal, fou_ville, pay_nom, " +
                    "mod_sfa_code, sfa_lib, faa_lib, mod_des, mod_code, cam_qte_cmde, cam_px_ach_net, " +
                    "mrg_des, crl_lib, mli_code, mli_lib, natcond.rv_meaning natcond, " +
                    "etiquettage.rv_meaning etiq, controlequal.rv_meaning controle, " +
                    "CAT_NO_CMDE, CAT_NO_VERSION, mtr_code, mtr_lib, " +
                    "(soc_nom || chr(10) || dep_nom || chr(10) || dep_adr1 || chr(10) || DEP_POSTAL || ' ' || DEP_VILLE) adresse, " +
                    "cat_dt_cmde, CAT_DT_DEPART1, CAT_DT_PREV_LIVR1, cou_code, cou_lib, cam_art_var1, cam_art_var2, cam_art_var3 " +
                    "from " +
                    "dt_cmde_achat_tete, xn_four, xn_pays, xn_modele, dt_cmde_achat_mc, xn_sfam_art, xn_fam_art, " +
                    "dt_cmde_achat_regl, xn_mode_reg, xn_cond_reg_langue, xn_mode_livr, xn_mode_transp, " +
                    "cg_ref_codes etiquettage, cg_ref_codes controlequal, cg_ref_codes natcond, " +
                    "xn_depot,xn_societe,xn_couleur " +

                    "where " +
                    "cat_fou_code = fou_code and " +
                    "fou_pay_code = pay_code and " +
                    "cam_cat_no_cmde = cat_no_cmde and " +
                    "cam_cat_no_version = cat_no_version and " +
                    "cam_cat_dep_soc_code = cat_dep_soc_code and " +
                    "cam_cat_dep_code = cat_dep_code and " +
                    "cam_art_var1<>'-' and " +
                    "cou_code = cam_art_var1 and "+
                    "cam_art_code = mod_code and " +
                    "mod_sfa_code = sfa_code and " +
                    "sfa_faa_code = mod_sfa_faa_code and " +
                    "faa_code = sfa_faa_code and " +
                    "car_cat_no_cmde = cat_no_cmde and " +
                    "car_cat_no_version = cat_no_version and " +
                    "crl_crg_code = car_crg_code and " +
                    "crl_lan_code = 'FR' and " +
                    "mrg_code = car_mrg_code and " +
                    "cat_mli_code = mli_code and " +
                    "mtr_code = cat_mtr_code and " +
                    "etiquettage.rv_domain = 'DT_ETIQ' and " +
                    "etiquettage.rv_low_value = cat_etiquettage and " +
                    "controlequal.rv_domain = 'DT_QUAL' and " +
                    "controlequal.rv_low_value = cat_controle_qual and " +
                    "natcond.rv_domain = 'NAT_COND' and " +
                    "natcond.rv_low_value = mod_nat_cond and " +
                    "cat_dep_code = dep_code and " +
                    "cat_dep_soc_code = dep_soc_code and " +
                    "dep_soc_code = soc_code and " +
                    "cat_no_cmde = ? and " +
                    "cat_no_version = ?",
                    new Object[]{
                        doDtCmdeAchatTete.getCatNoCmde(),
                        doDtCmdeAchatTete.getCatNoVersion(),
                    }
            );
            if (lines.length == 0) {
                return null;
            }

//            cat_fou_code
            data.setFouCode((String) lines[0][0]);

//            fou_nom
            data.setFouNom((String) lines[0][1]);

//            fou_adr1
            data.setFouAdr1((String) lines[0][2]);

//            fou_adr2
            data.setFouAdr2((String) lines[0][3]);

//            fou_postal
            data.setFouPostal((String) lines[0][4]);

//            fou_ville
            data.setFouVille((String) lines[0][5]);

//            pay_nom
            data.setFouPays((String) lines[0][6]);

//            mod_sfa_code
            data.setFaaCode((String) lines[0][7]);

            //(String) lines[0][8]

//            sfa_lib
//            faa_lib
            data.setFaaLib(lines[0][8] + " " +lines[0][9]);

//            mod_des
            data.setArtLib((String) lines[0][10]);

//            mod_code
            data.setArtCode((String) lines[0][11]);

//            cam_qte_cmde
            data.setQteCmde(NumberUtils.toInteger((Number) lines[0][12]));

//            cam_px_ach_net
            data.setPrixAUHT(NumberUtils.toDouble((Number) lines[0][13]));

//            mrg_des
//            crl_lib
            data.setCondPaiement(lines[0][14] + " " + lines[0][15]);

//            mli_code
//            mli_lib
            data.setCondLivraison(lines[0][16] + " " + lines[0][17]);

//            natcond.rv_meaning natcond
            data.setConditionnement((String) lines[0][18]);

//            etiquettage.rv_meaning etiq
            data.setEtiqFournisseur((String) lines[0][19]);

//            controlequal.rv_meaning controle
            data.setCtrlQualiteFournisseur((String) lines[0][20]);

            //CAT_NO_CMDE
            data.setNoCmde(NumberUtils.toInteger((Number) lines[0][21]));

            //CAT_NO_VERSION
            data.setNoVersion(NumberUtils.toInteger((Number) lines[0][22]));

            //mtr_code
            //mtr_lib
            data.setTransport(lines[0][23] + " " + lines[0][24]);

            // adresse
//            data.setAddresse((String) lines[0][25]);

            //cat_dt_cmde
            data.setDateCmde((Timestamp) lines[0][26]);

            //CAT_DT_DEPART1
            data.setDateDepart((Timestamp) lines[0][27]);

            //CAT_DT_PREV_LIV1
            data.setDateRendu((Timestamp) lines[0][28]);

            //cou_code
            //cou_lib
            data.setCouCode((String) lines[0][29]);
            data.setCouLib((String) lines[0][30]);

            String artVar1=(String) lines[0][31];
            String artVar2=(String) lines[0][32];
            String artVar3=(String) lines[0][33];

            data.setEntretien(null);
            data.setColoris(getTailleQteCmdStructsByCmde(doDtCmdeAchatTete));

            ////////////// INCONNUS

            BoXnArt boXnArt=new BoXnArt();
            DoXnArt doXnArt = new DoXnArt();
            doXnArt.setArtCode(data.getArtCode());
            doXnArt.setArtVar1(artVar1);
            doXnArt.setArtVar2(artVar2);
            doXnArt.setArtVar3(artVar3);

            data.setComposition(boXnArt.getComposition(doXnArt,lang));
            data.setEntretien(boXnArt.getEntretien(doXnArt,lang));

        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
        return data;
    }

    /**
     * les donn�es n�cessaires � la g�n�ration des �tiquettes
     * @param doDtCmdeAchatTete instance de DoDtCmdeAchatTete contenant la clef
     * @return les donn�es n�cessaires � la g�n�ration des �tiquettes
     * @throws MetierException
     */
    public TailleQteCmdStruct[] getTailleQteCmdStructsByCmde(DoDtCmdeAchatTete doDtCmdeAchatTete) throws MetierException {
        try {
            Object[][] lines = retrieve(
                    EGCQBusinessConstants.DATASOURCE_NAME,
                    //requ�te
                    "select " +
                    "tai_lib, cal_qte_cmde " +
                    "from " +
                    "dt_cmde_achat_tete, dt_cmde_achat_ligne, xn_modele, xn_taille " +
                    "where " +
                    "cat_no_cmde = cal_cat_no_cmde and " +
                    "cat_no_version = cal_cat_no_version and " +
                    "mod_code = cal_art_code and " +
                    "mod_grt_code = tai_grt_code and " +
                    "cal_art_var2=tai_indice and " +
                    "cat_no_cmde = ? and " +
                    "cat_no_version = ?",
                    new Object[]{
                        doDtCmdeAchatTete.getCatNoCmde(),
                        doDtCmdeAchatTete.getCatNoVersion(),
                    }
            );
            TailleQteCmdStruct[] struct=new TailleQteCmdStruct[lines.length];
            for(int i=0;i<lines.length;i++){
                struct[i]=new TailleQteCmdStruct(
                        (String)lines[i][0],
                        NumberUtils.toInteger((Number)lines[i][1])
                );
            }
            return struct;
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    /**
     * structure contenant les paires taille+qte
     */
    public final static class TailleQteCmdStruct extends Struct {
        /**
         * taille
         */
        private String taille;

        /**
         * qte
         */
        private Integer qte;

        /**
         * constructeur
         * @param taille
         * @param qte
         */
        public TailleQteCmdStruct(String taille, Integer qte) {
            this.taille = taille;
            this.qte = qte;
        }

        /**
         * taille
         * @return taille
         */
        public String getTaille() {
            return taille;
        }

        /**
         * qte
         * @return qte
         */
        public Integer getQte() {
            return qte;
        }
    }

    /**
     * classe interne utlis�e pour renvoyer des informations relatives �
     * plusieurs tables en m�me temps vu que les DataObjects standards ne
     * le permettaient pas.
     * <BR>Le nom des champs correspond aux noms dans la base de donn�e selon
     * la requ�te exprim�e dans getCommandesData(...) en respectant la nomenclature
     * emprunt�e aux DataObjects.
     */
    public static class CommandeData extends AbstractHeteroData {
        private String fouNom;
        private String depNom;
        private String depPostal;
        private String depVille;
        private Integer catNoCmde;
        private Integer catNoVersion;
        private String catModCode;
        private String camArtVar1;
        private String couLib;
        private Integer camQteCmde;
        private Date catDtCmde;
        private String modDes;
        private Date catDtDepart2;
        private Date rstDtCtrlSourcePrev;
        private Date rstDtCtrlSourceSouhait;
        private String rstIndex;
        private String camArtCode;
        private String camArtVar2;
        private String camArtVar3;
        private Integer rstNoControle;
        private Integer camNoLigne;
        private String catDepCode;
        private String catDepCodeCtrleSource;
        private String catDepSocCode;
        private String fouCode;

        public CommandeData(
                String newFouNom, String newDepNom, String newDepPostal, String newDepVille,
                Integer newCatNoCmde, Integer newCatNoVersion, String newCatModCode,
                String newCamArtVar1, String newCouLib, Integer newCamQteCmde, Date newCatDtCmde,
                String newModDes, Date newCatDtDepart2, Date newRstDtCtrlSourcePrev,
                Date newRstDtCtrlSourceSouhait, String newRstIndex
                , String newCamArtCode, String newCamArtVar2, String newCamArtVar3
                , Integer newCamNoLigne, Integer newRstNoControle
                , String newCatDepCode, String newCatDepSocCode, String newFouCode, String newCatDepCodeCtrleSource
                ) {
            this.fouNom = newFouNom;
            this.depNom = newDepNom;
            this.depPostal = newDepPostal;
            this.depVille = newDepVille;
            this.catNoCmde = newCatNoCmde;
            this.catNoVersion = newCatNoVersion;
            this.catModCode = newCatModCode;
            this.camArtVar1 = newCamArtVar1;
            this.couLib = newCouLib;
            this.camQteCmde = newCamQteCmde;
            this.catDtCmde = newCatDtCmde;
            this.modDes = newModDes;
            this.catDtDepart2 = newCatDtDepart2;
            this.rstDtCtrlSourcePrev = newRstDtCtrlSourcePrev;
            this.rstDtCtrlSourceSouhait = newRstDtCtrlSourceSouhait;
            this.rstIndex = newRstIndex;
            this.camArtCode = newCamArtCode;

            this.camArtVar2 = newCamArtVar2;
            this.camArtVar3 = newCamArtVar3;
            this.rstNoControle = newRstNoControle;
            this.camNoLigne = newCamNoLigne;
            this.catDepCode = newCatDepCode;
            this.catDepSocCode = newCatDepSocCode;
            this.fouCode = newFouCode;
            this.catDepCodeCtrleSource = newCatDepCodeCtrleSource;
        }

        public String getFouNom() {
            return fouNom;
        }

        public String getDepNom() {
            return depNom;
        }

        public String getDepPostal() {
            return depPostal;
        }

        public String getDepVille() {
            return depVille;
        }

        public Integer getCatNoCmde() {
            return catNoCmde;
        }

        public Integer getCatNoVersion() {
            return catNoVersion;
        }

        public String getCatModCode() {
            return catModCode;
        }

        public String getCamArtVar1() {
            return camArtVar1;
        }

        public String getCouLib() {
            return couLib;
        }

        public Integer getCamQteCmde() {
            return camQteCmde;
        }

        public Date getCatDtCmde() {
            return catDtCmde;
        }

        public String getModDes() {
            return modDes;
        }

        public Date getCatDtDepart2() {
            return catDtDepart2;
        }

        public Date getRstDtCtrlSourcePrev() {
            return rstDtCtrlSourcePrev;
        }

        public Date getRstDtCtrlSourceSouhait() {
            return rstDtCtrlSourceSouhait;
        }

        public void setRstDtCtrlSourceSouhait(Date newRstDtCtrlSourceSouhait) {
            rstDtCtrlSourceSouhait = newRstDtCtrlSourceSouhait;
        }

        public String getRstIndex() {
            return rstIndex;
        }

        public String getCamArtCode() {
            return camArtCode;
        }

        public String getCamArtVar2() {
            return camArtVar2;
        }

        public String getCamArtVar3() {
            return camArtVar3;
        }

        public Integer getRstNoControle() {
            return rstNoControle;
        }

        public Integer getCamNoLigne() {
            return camNoLigne;
        }

        public String getCatDepCode() {
            return catDepCode;
        }

        public String getCatDepCodeCtrleSource() {
            return catDepCodeCtrleSource;
        }

        public String getCatDepSocCode() {
            return catDepSocCode;
        }

        public String getFouCode() {
            return fouCode;
        }
    }

}

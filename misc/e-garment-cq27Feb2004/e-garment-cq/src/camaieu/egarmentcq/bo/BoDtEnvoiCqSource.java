package camaieu.egarmentcq.bo;

import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.dataobject.DoDtEnvoiCqSource;
import camaieu.webform.AbstractHeteroData;
import wg4.bean.ancestor.BoAncestorBean;
import wg4.bean.ancestor.MetierException;
import wg4.bean.ancestor.TechniqueException;
import wg4.fwk.dataobject.DataObject;

import java.sql.Timestamp;

/**
 * Business Object pour la manipulation des accusés de receptions.
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 21/01/2004
 * @last_modification_date 21/01/2004
 * @status pour validation
 */
public class BoDtEnvoiCqSource extends BoAncestorBean {

    /**
     * construteur
     */
    public BoDtEnvoiCqSource() {
    }

    /**
     * charge les données des accusés de reception pour un supplier donné
     * @param supplicerCode le code du fournisseur
     * @return les données des accusés de reception pour un supplier donné
     * @throws MetierException
     */
    public ItemDeliveryReceiptData[] getItemDeliveryReceiptsData(String supplicerCode) throws MetierException {
        try {
            Object[][] lines = retrieve(
                    EGCQBusinessConstants.DATASOURCE_NAME,

                    //requête
                    "select " +
                    "fou_nom, ecs_faa_code, faa_lib, ecs_art_code, mod_des, ecs_art_var1, " +
                    "cou_lib, x.tai_lib as tai_min, y.tai_lib as tai_max, " +
                    "ecs_dt_envoi_echan, ecs_dt_recep_echan,	ecs_no_colis_echan, " +
                    "ecs_dt_envoi_bib, ecs_dt_recep_bib, ecs_no_colis_bib, " +
                    "ecs_dt_envoi_dtech, ecs_dt_recep_dtech, ecs_no_colis_dtech, " +
                    "ecs_prest_fou_code, ecs_fou_code, ecs_art_var2, ecs_art_var3 " +
                    "from " +
                    "dt_envoi_cq_source, xn_four, xn_fam_art, xn_art, xn_modele, " +
                    "xn_couleur,	xn_taille x, xn_taille y " +
                    "where " +
//-- liaison entre XN_FOUR et DT_ENVOI_CQ_SOURCE
                    "fou_code = ecs_fou_code and " +
//-- liaison entre XN_FAM_ART et DT_ENVOI_CQ_SOURCE
                    "faa_code = ecs_faa_code and " +
//-- liaison entre XN_ART et DT_ENVOI_CQ_SOURCE
                    "art_code = ecs_art_code and " +
                    "art_var1 = ecs_art_var1 and " +
                    "art_var2 = ecs_art_var2 and " +
                    "art_var3 = ecs_art_var3 and " +
//-- liaison entre XN_MODELE et XN_ART
                    "mod_code = art_mod_code and " +
//-- liaison entre XN_COULEUR et XN_ART
                    "art_var1 = cou_code and " +
//-- liaison entre et XN_TAILLE et DT_ENVOI_CQ_SOURCE
                    "x.tai_grt_code = mod_grt_code and " +
                    "y.tai_grt_code = x.tai_grt_code and " +
                    "x.tai_indice = (select MIN(tai_indice) from xn_taille where tai_grt_code=x.tai_grt_code) and " +
                    "y.tai_indice = (select MAX(tai_indice) from xn_taille where tai_grt_code=y.tai_grt_code) and " +
//	-- conditions d'affichage
                    "(ecs_dt_recep_dtech is NULL or " +
                    "ecs_dt_recep_bib is NULL or " +
                    "ecs_dt_recep_echan is NULL) and " +
//-- conditions générales
                    "ecs_prest_fou_code = ? " + //  -- code du prestataire de contrôle
                    "order by " +
                    "ecs_dt_envoi_bib "
                    ,

                    new Object[]{supplicerCode}
            );
//            System.out.println(">>>>>>>>>>  requete = " + query);
            ItemDeliveryReceiptData[] receiptData = new ItemDeliveryReceiptData[lines.length];
            for (int i = 0; i < receiptData.length; i++) {
                receiptData[i] = new ItemDeliveryReceiptData(
                        (String) lines[i][0],
                        (String) lines[i][1],
                        (String) lines[i][2],
                        (String) lines[i][3],
                        (String) lines[i][4],
                        (String) lines[i][5],
                        (String) lines[i][6],
                        (String) lines[i][7],
                        (String) lines[i][8],
                        (Timestamp) lines[i][9],
                        (Timestamp) lines[i][10],
                        (String) lines[i][11],
                        (Timestamp) lines[i][12],
                        (Timestamp) lines[i][13],
                        (String) lines[i][14],
                        (Timestamp) lines[i][15],
                        (Timestamp) lines[i][16],
                        (String) lines[i][17],
                        (String) lines[i][18],
                        (String) lines[i][19],
                        (String) lines[i][20],
                        (String) lines[i][21]
                );
            }


            return receiptData;
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    /**
     * mettre à jour toutes les instances
     * @param elements les instance à mettre à jour
     * @return nombre de liges affectées
     * @throws MetierException
     */
    public int update(DoDtEnvoiCqSource[] elements) throws MetierException {
        for (int i = 0; i < elements.length; i++) {
            elements[i].setPersist(DataObject.PERSIST_UPDATE);
        }
        try {
            return persist(EGCQBusinessConstants.DATASOURCE_NAME, elements, true);
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    /**
     * informations sur les accusés de réception
     */
    public static class ItemDeliveryReceiptData extends AbstractHeteroData {
        private String fouNom;
        private String ecsFaaCode;
        private String faaLib;
        private String ecsArtCode;
        private String modDes;
        private String ecsArtVar1;
        private String couLib;
        private String taiMin;
        private String taiMax;
        private Timestamp ecsDtEnvoiEchan;
        private Timestamp ecsDtRecepEchan;
        private String ecsNoColisEchan;
        private Timestamp ecsDtEnvoiBib;
        private Timestamp ecsDtRecepBib;
        private String ecsNoColisBib;
        private Timestamp ecsDtEnvoiDtech;
        private Timestamp ecsDtRecepDtech;
        private String ecsNoColisDtech;
        private String ecsPrestFouCode;
        private String ecsFouCode;
        private String ecsArtVar2;
        private String ecsArtVar3;

        public ItemDeliveryReceiptData(String newFouNom, String newEcsFaaCode, String newFaaLib, String newEcsArtCode, String newModDes, String newEcsArtVar1, String newCouLib, String newTaiMin, String newTaiMax, Timestamp newEcsDtEnvoiEchan, Timestamp newEcsDtRecepEchan, String newEcsNoColisEchan, Timestamp newEcsDtEnvoiBib, Timestamp newEcsDtRecepBib, String newEcsNoColisBib, Timestamp newEcsDtEnvoiDtech, Timestamp newEcsDtRecepDtech, String newEcsNoColisDtech, String newEcsPrestFouCode, String newEcsFouCode, String newEcsArtVar2, String newEcsArtVar3) {
            this.fouNom = newFouNom;
            this.ecsFaaCode = newEcsFaaCode;
            this.faaLib = newFaaLib;
            this.ecsArtCode = newEcsArtCode;
            this.modDes = newModDes;
            this.ecsArtVar1 = newEcsArtVar1;
            this.couLib = newCouLib;
            this.taiMin = newTaiMin;
            this.taiMax = newTaiMax;
            this.ecsDtEnvoiEchan = newEcsDtEnvoiEchan;
            this.ecsDtRecepEchan = newEcsDtRecepEchan;
            this.ecsNoColisEchan = newEcsNoColisEchan;
            this.ecsDtEnvoiBib = newEcsDtEnvoiBib;
            this.ecsDtRecepBib = newEcsDtRecepBib;
            this.ecsNoColisBib = newEcsNoColisBib;
            this.ecsDtEnvoiDtech = newEcsDtEnvoiDtech;
            this.ecsDtRecepDtech = newEcsDtRecepDtech;
            this.ecsNoColisDtech = newEcsNoColisDtech;
            this.ecsPrestFouCode = newEcsPrestFouCode;
            this.ecsFouCode = newEcsFouCode;
            this.ecsArtVar2 = newEcsArtVar2;
            this.ecsArtVar3 = newEcsArtVar3;
        }

        public String getFouNom() {
            return fouNom;
        }

        public String getEcsFaaCode() {
            return ecsFaaCode;
        }

        public String getFaaLib() {
            return faaLib;
        }

        public String getEcsArtCode() {
            return ecsArtCode;
        }

        public String getModDes() {
            return modDes;
        }

        public String getEcsArtVar1() {
            return ecsArtVar1;
        }

        public String getCouLib() {
            return couLib;
        }

        public String getTaiMin() {
            return taiMin;
        }

        public String getTaiMax() {
            return taiMax;
        }

        public Timestamp getEcsDtEnvoiEchan() {
            return ecsDtEnvoiEchan;
        }

        public Timestamp getEcsDtRecepEchan() {
            return ecsDtRecepEchan;
        }

        public String getEcsNoColisEchan() {
            return ecsNoColisEchan;
        }

        public Timestamp getEcsDtEnvoiBib() {
            return ecsDtEnvoiBib;
        }

        public Timestamp getEcsDtRecepBib() {
            return ecsDtRecepBib;
        }

        public String getEcsNoColisBib() {
            return ecsNoColisBib;
        }

        public Timestamp getEcsDtEnvoiDtech() {
            return ecsDtEnvoiDtech;
        }

        public Timestamp getEcsDtRecepDtech() {
            return ecsDtRecepDtech;
        }

        public String getEcsNoColisDtech() {
            return ecsNoColisDtech;
        }

        public String getEcsPrestFouCode() {
            return ecsPrestFouCode;
        }

        public String getEcsFouCode() {
            return ecsFouCode;
        }

        public String getEcsArtVar2() {
            return ecsArtVar2;
        }

        public String getEcsArtVar3() {
            return ecsArtVar3;
        }

        public DoDtEnvoiCqSource getDoDtEnvoiCqSource() {
            DoDtEnvoiCqSource cqSource = new DoDtEnvoiCqSource();
            //ECS_PREST_FOU_CODE","ECS_FOU_CODE","ECS_ART_VAR3","ECS_ART_VAR2","ECS_ART_VAR1","ECS_ART_CODE
            cqSource.setEcsPrestFouCode(getEcsPrestFouCode());
            cqSource.setEcsFouCode(getEcsFouCode());
            cqSource.setEcsArtCode(getEcsArtCode());
            cqSource.setEcsArtVar1(getEcsArtVar1());
            cqSource.setEcsArtVar2(getEcsArtVar2());
            cqSource.setEcsArtVar3(getEcsArtVar3());
            return cqSource;
        }

        public void setEcsDtEnvoiEchan(Timestamp ecsDtEnvoiEchan) {
            this.ecsDtEnvoiEchan = ecsDtEnvoiEchan;
        }

        public void setEcsDtRecepEchan(Timestamp ecsDtRecepEchan) {
            this.ecsDtRecepEchan = ecsDtRecepEchan;
        }

        public void setEcsNoColisEchan(String ecsNoColisEchan) {
            this.ecsNoColisEchan = ecsNoColisEchan;
        }

        public void setEcsDtEnvoiBib(Timestamp ecsDtEnvoiBib) {
            this.ecsDtEnvoiBib = ecsDtEnvoiBib;
        }

        public void setEcsDtRecepBib(Timestamp ecsDtRecepBib) {
            this.ecsDtRecepBib = ecsDtRecepBib;
        }

        public void setEcsNoColisBib(String ecsNoColisBib) {
            this.ecsNoColisBib = ecsNoColisBib;
        }

        public void setEcsDtEnvoiDtech(Timestamp ecsDtEnvoiDtech) {
            this.ecsDtEnvoiDtech = ecsDtEnvoiDtech;
        }

        public void setEcsDtRecepDtech(Timestamp ecsDtRecepDtech) {
            this.ecsDtRecepDtech = ecsDtRecepDtech;
        }

        public void setEcsNoColisDtech(String ecsNoColisDtech) {
            this.ecsNoColisDtech = ecsNoColisDtech;
        }
    }
}

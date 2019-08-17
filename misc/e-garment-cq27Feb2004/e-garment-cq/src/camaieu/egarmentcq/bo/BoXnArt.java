package camaieu.egarmentcq.bo;

import camaieu.common.BoEasyAncestorBean;
import camaieu.common.NumberUtils;
import camaieu.egarmentcq.common.EGCQBusinessConstants;
import camaieu.egarmentcq.dataobject.DoXnArt;
import camaieu.egarmentcq.report.LabelReport;
import org.apache.log4j.Category;
import wg4.bean.ancestor.MetierException;
import wg4.bean.ancestor.TechniqueException;
import wg4.fwk.dataobject.DataObject;

/**
 * Business Object pour la manipulation des Articles
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 20/01/2004
 * @last_modification_date 20/01/2004
 * @status pour validation
 */

public class BoXnArt extends BoEasyAncestorBean {
    /**
     * instance de log
     */
    private static Category log = Category.getInstance(BoXnArt.class.getName());

    /**
     * constructeur par défaut
     */
    public BoXnArt() {
        super(DoXnArt.class, EGCQBusinessConstants.DATASOURCE_NAME);
    }

    public DoXnArt reloadSampleArticle(DoXnArt art) throws MetierException{
        try {
            if(art.getArtVar2()==null || "-".equals(art.getArtVar2())){
                DataObject[][] lines = retrieve(
                        EGCQBusinessConstants.DATASOURCE_NAME,
                        //requête
                        "select * " +
                        "from xn_art " +
                        "where " +
                        "art_code=? and art_var1=? and art_var2 <> '-' and art_var3 = '-'",
                        new Object[]{
                            art.getArtCode(),art.getArtVar1()
                        },new Class[]{DoXnArt.class}
                );
                return (lines==null || lines.length==0 || lines[0].length==0) ? null : (DoXnArt) lines[0][0];
            }else{
                return (DoXnArt) reload(art);
            }
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

//    public Map getExtraProperties(DoXnArt art) throws MetierException {
//        try {
//            Object[][] lines = retrieve(
//                    EGCQBusinessConstants.DATASOURCE_NAME,
//                    //requête
//                    "select " +
//                    "art_gencod, tai_lib " +
//                    "from " +
//                    "xn_art, xn_modele, xn_taille " +
//                    "where " +
//                    "mod_code = art_code and " +
//                    "mod_grt_code = tai_grt_code and " +
//                    "art_var2=tai_indice and " +
//                    "art_code=? and art_var1=? and art_var2 <> '-' and art_var3 = '-'",
////                    "art_code=? and "+
////                    "art_var1=? and "+
////                    "art_var2=? and "+
////                    "art_var3=? ",
//                    new Object[]{
//                        art.getArtCode(),art.getArtVar1()//,art.getArtVar2(),art.getArtVar3()
//                    }
//            );
//            if(){
//                return null;
//            }
//            HashMap hashMap=new HashMap();
//            hashMap.put("art_gencod",(String) lines[0][0]);
//            hashMap.put("tai_lib",(String) lines[0][2]);
//            return hashMap;
//        } catch (TechniqueException e) {
//            throw new MetierException(e.getMessage());
//        }
//    }
//    /**
//     * code à barre
//     * @param art
//     * @return
//     * @throws MetierException
//     */
//    public String getEANcode(DoXnArt art) throws MetierException {
//        try {
//            Object[][] lines = null;
//            lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
//                    "select art_gencod from xn_art where art_code = ? and art_var1 = ? and art_var2 <> '-' and art_var3 = '-'",
//                    new Object[]{art.getArtCode(), art.getArtVar1()}
//            );
//            return (lines.length == 0 || lines[0].length == 0) ? "" : (String) lines[0][0];
//        } catch (TechniqueException e) {
//            throw new MetierException(e.getMessage());
//        }
//    }

    /**
     * taille de l'article
     * @param art
     * @return
     * @throws MetierException
     */
    public String getTailleLib(DoXnArt art) throws MetierException {
        try {
            Object[][] lines = retrieve(
                    EGCQBusinessConstants.DATASOURCE_NAME,
                    //requête
                    "select " +
                    "tai_lib " +
                    "from " +
                    "xn_art, xn_modele, xn_taille " +
                    "where " +
                    "mod_code = art_code and " +
                    "mod_grt_code = tai_grt_code and " +
                    "art_var2=tai_indice and " +
                    "art_code=? and art_var1=? and art_var2=? and art_var3 = ?",
//                    "art_code=? and "+
//                    "art_var1=? and "+
//                    "art_var2=? and "+
//                    "art_var3=? ",
                    new Object[]{
                        art.getArtCode(),art.getArtVar1(),art.getArtVar2(),art.getArtVar3()
                    }
            );
            return (lines == null || lines.length == 0 || lines[0].length == 0) ? null : (String) lines[0][0];
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }

    }

    /**
     * les valeurs des entretiens en tableau 2d
     * <BR> la 1ere colonne étant eru_lib
     * <BR> la 2eme colonne étant erv_lib
     * @param art dataObject contenant la clef
     * @return
     * @throws MetierException
     */
    public String[][] getEntretien(DoXnArt art, String lang) throws MetierException {
        try {
            Object[][] lines = null;
            if ("fr".equalsIgnoreCase(lang)) {
                lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                        "select eru_lib , erv_lib " +
                        "from " +
                        "xn_entretien, xn_entretien_rubrique ,xn_entretien_rub_valeur " +
                        "where " +
                        "ent_art_code = ? and " +
                        "erv_eru_code = ent_eru_code and " +
                        "ent_erv_val = erv_val and " +
                        "eru_code = ent_eru_code",
                        new Object[]{art.getArtCode()}
                );
            } else {
                //english
                lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                        "select erl_lib || ' ' || evl_lib from " +
                        "xn_entretien, xn_entretien_rub_langue, xn_entretien_rub_val_langue " +
                        "where " +
                        "ent_art_code = ? and " +
                        "erl_eru_code = ent_eru_code and " +
                        "erl_lan_code = 'GB' and " +
                        "evl_erv_eru_code = ent_eru_code and " +
                        "evl_erv_val = ent_erv_val and " +
                        "evl_lan_code = 'GB' ",
                        new Object[]{art.getArtCode()}
                );
            }
            String[][] ent = new String[lines.length][2];
            for (int i = 0; i < ent.length; i++) {
                ent[i][0] = (String) lines[i][0];
                ent[i][1] = (String) lines[i][1];
            }
            return ent;
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    /**
     * les valeurs des compositions en tableau 2d
     * <BR> la 1ere colonne étant com_pourcent
     * <BR> la 2eme colonne étant mat_lib
     * @param art dataObject contenant la clef
     * @return
     * @throws MetierException
     */
    public String[][] getComposition(DoXnArt art, String lang) throws MetierException {
        String eanCode = art.getArtGencod() ;
        try {
            if (eanCode == null) {
                art = reloadSampleArticle(art);
                eanCode = art.getArtGencod();
            }
            String[][] zone6 = new String[6][2];
            if (eanCode == null) {
                for (int i = 0; i < zone6.length; i++) {
                    zone6[i][0] = "";
                    zone6[i][1] = "";
                }
            } else {
                for (int i = 0; i < zone6.length; i++) {
                    Object[][] lines = null;
                    if ("fr".equalsIgnoreCase(lang)) {
                        lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                                "Select " +
                                "com_pourcent," +
                                "mat_lib " +
                                "from " +
                                "xn_art, " +
                                "xn_composition," +
                                "xn_matiere " +
                                "where " +
                                "com_art_code = art_code and " +
                                "(" +
                                "com_art_var1 = art_var1 or " +
                                "(" +
                                "com_art_var1 = '-' and " +
                                "not exists (" +
                                "select 1 from xn_composition where com_art_code = art_code and com_art_var1 = art_var1" +
                                ")" +
                                ")" +
                                ") and " +
                                "com_no = ? and com_mat_code = mat_code and art_gencod = ?",
                                new Object[]{new Integer(i + 1), eanCode}
                        );
                    } else {
                        //english
                        String baseLang = "GB";
                        lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                                "Select " +
                                "com_pourcent, mal_lib " +
                                "from " +
                                "xn_art, xn_composition,xn_matiere_langue " +
                                "where " +
                                "com_art_code  = art_code and " +
                                "(com_art_var1 = art_var1 or " +
                                " (com_art_var1 = '-' and not exists " +
                                "  (select 1 from xn_composition where com_art_code = art_code and com_art_var1 = art_var1) " +
                                " ) " +
                                ") and " +
                                "com_mat_code = mal_mat_code and " +
                                "com_no = ? and " +
                                "mal_lan_code = ? and " +
                                "art_code = ? and " +
                                "art_var1=  ? and " +
                                "art_var2 = ? and " +
                                "art_var3 = ?"
                                ,
                                new Object[]{new Integer(i + 1), baseLang, art.getArtCode(), art.getArtVar1(), art.getArtVar2(), art.getArtVar3()}
                        );
                    }
                    if (lines.length == 0) {
                        zone6[i][0] = "";
                        zone6[i][1] = "";
                    } else {
                        zone6[i][0] = String.valueOf(lines[0][0])+"%";
                        zone6[i][1] = (String) lines[0][1];
                    }
                }
            }
            return zone6;
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    public LabelReport.Price getTacPxActuelFRF(DoXnArt art) throws MetierException {
        return getTacPxActuel(art,"91");
    }

    public LabelReport.Price getTacPxActuelEuro(DoXnArt art) throws MetierException {
        return getTacPxActuel(art,"01");
    }

    /**
     * le TAC_PX_ACTUEL de l'article
     * @param art dataObject contenant la clef
     * @return TAC_PX_ACTUEL
     * @throws MetierException
     */
    public LabelReport.Price getTacPxActuel(DoXnArt art,String moneyCode) throws MetierException {
        String eanCode = art.getArtGencod();
        try {
            if (eanCode == null) {
                art = reloadSampleArticle(art);
                eanCode = art.getArtGencod();
            }
            if (eanCode == null) {
                return null;
            } else {
                Object[][] lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                        "select " +
                        "TAC_PX_ACTUEL, NTC_DEV_CODE " +
                        "from " +
                        "XN_TARIF_CLI, " +
                        "XN_ART, " +
                        "XN_NAT_TARIF_CLI " +
                        "where  " +
                        "tac_art_code = art_code and " +
                        "tac_art_var1 = art_var1 and " +
                        "tac_art_var2 = '-' and " +
                        "tac_art_var3 = '-' and " +
                        "tac_ntc_code = ntc_code and " +
                        "art_gencod = ? and " +
                        "tac_ntc_code = ? " +
                        "union " +
                        "select " +
                        "TAC_PX_ACTUEL, NTC_DEV_CODE  " +
                        "from " +
                        "XN_TARIF_CLI,   " +
                        "XN_ART, " +
                        "XN_NAT_TARIF_CLI " +
                        "where " +
                        "tac_art_code = art_code and " +
                        "tac_art_var1 = '-' and " +
                        "tac_art_var2 = '-' and	 " +
                        "tac_art_var3 = '-' and " +
                        "tac_ntc_code = ntc_code and " +
                        "art_gencod = ? and " +
                        "tac_ntc_code	= ? and " +
                        "not exists " +
                        "(select " +
                        "1 " +
                        "from " +
                        "XN_TARIF_CLI," +
                        "XN_ART " +
                        "where " +
                        "tac_art_code = art_code   and " +
                        "tac_art_var1 = art_var1 " +
                        "and tac_art_var2 = '-' and " +
                        "tac_art_var3 = '-'  and " +
                        "tac_ntc_code = ntc_code and " +
                        "art_gencod = ? and " +
                        "tac_ntc_code = ?" +
                        ")", new Object[]{eanCode, moneyCode, eanCode, moneyCode, eanCode, moneyCode});
                if(lines.length == 0 || lines[0].length==0){
                    return new LabelReport.Price(null,moneyCode,moneyCode);
                }
                return new LabelReport.Price(NumberUtils.toDouble((Number) lines[0][0]),moneyCode, (String) lines[0][1]);
            }
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }

    /**
     * les informations des étiquettes
     * @param art le dataObject contenant la clef
     * @return les informations des étiquettes
     * @throws MetierException
     */
    public LabelReport.LabelReportData getLabelReportData(DoXnArt art, String lang) throws MetierException {
        LabelReport.LabelReportData data = new LabelReport.LabelReportData();
        try {
            art = reloadSampleArticle(art);
            String eanCode = art.getArtGencod();

            if (eanCode == null) {
                log.debug("Problem : no EAN code was found for art " + art.getArtCode());
            } else {
                Object[][] lines = null;
                data.setBarCode(eanCode);
                data.setZone1(getTacPxActuelEuro(art));
                data.setZone1fr(getTacPxActuelFRF(art));
                lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                        "Select mod_lib from XN_ART,XN_MODELE where  art_code = mod_code and art_gencod = ?"
                        , new Object[]{eanCode});
                data.setZone2(lines.length == 0 ? null : (String) lines[0][0]);

                lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                        "Select substr(annee_clt,3,2)||collection from XN_ART,CM_MODELE_COULEUR where art_code = modele and art_var1 = couleur and art_gencod = ?"
                        , new Object[]{eanCode});
                data.setZone3(lines.length == 0 ? null : (String) lines[0][0]);


                lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                        "Select " +
                        "art_code ||" +
                        "'/'||" +
                        "art_var1||" +
                        "'/'||" +
                        "mod_sfa_faa_code||" +
                        "substr(c1.cri_crv_valeur,3,4)||" +
                        "' '||" +
                        "c2.cri_crv_valeur||" +
                        "'-'||" +
                        "c3.cri_crv_valeur ZA5 " +

                        "from " +
                        "XN_ART," +
                        "XN_MODELE," +
                        "XN_MODELE_COULEUR moc," +
                        "st_critere c1," +
                        "st_critere c2," +
                        "st_critere c3 " +

                        "where " +
                        "art_code = mod_code and " +
                        "art_code = moc_mod_code and " +
                        "art_var1= moc_mod_var1 and " +
                        "moc.xtx_id = c1.cri_xtx_id and " +
                        "c1.cri_crv_crn_code= 'SF'and " +
                        "moc.xtx_id = c2.cri_xtx_id and	" +
                        "c2.cri_crv_crn_code = 'PO'and " +
                        "moc.xtx_id = c3.cri_xtx_id and	" +
                        "c3.cri_crv_crn_code = 'ST'and art_gencod = ?"
                        , new Object[]{eanCode});
                data.setZone4(lines.length == 0 ? null : (String) lines[0][0]);

                Object[] val5 = new Object[]{
                    new Integer(1), "CHLOR",
                    new Integer(2), "LAVAG",
                    new Integer(3), "NETTO",
                    new Integer(4), "REPAS",
                    new Integer(5), "SECHE",
                };

                StringBuffer sb = new StringBuffer("");
                for (int i = 0; i < val5.length; i = i + 2) {
                    Object p_ent_no = val5[i];
                    Object p_ent_eru_code = val5[i + 1];

                    lines = retrieve(EGCQBusinessConstants.DATASOURCE_NAME,
                            "select " +
                            "cmsee_code_ascii " +
                            "from " +
                            "xn_entretien, " +
                            "xn_art, " +
                            "cm_symboles_entretien_eti " +
                            "where  " +
                            "ent_art_code = art_code and " +
                            "ENT_ART_VAR1 = art_var1 and " +
                            "ENT_ART_VAR2	= '-' and " +
                            "ENT_ART_VAR3 = '-' and " +
                            "ent_eru_code = cmsee_code_entretien and " +
                            "ent_erv_val = cmsee_valeur_entretien and " +
                            "ent_no = ? and " +
                            "ent_eru_code = ? and " +
                            "art_gencod = ? and " +
                            "cmsee_type_imp = 'SATO' " +

                            "union " +

                            "select " +
                            "cmsee_code_ascii " +
                            "from " +
                            "xn_entretien, " +
                            "xn_art, " +
                            "cm_symboles_entretien_eti " +
                            "where " +
                            "ent_art_code = art_code and " +
                            "ENT_ART_VAR1 = '-' and " +
                            "ENT_ART_Var2 = '-' and " +
                            "ENT_ART_VAR3	= '-' and " +
                            "ent_eru_code = cmsee_code_entretien and " +
                            "ent_erv_val = cmsee_valeur_entretien and " +
                            "ent_no = ? and " +
                            "ent_eru_code = ? and " +
                            "art_gencod = ? and " +
                            "cmsee_type_imp = 'SATO' and " +
                            "not exists (" +
                            "select " +
                            "1 " +
                            "from " +
                            "xn_entretien, " +
                            "xn_art, " +
                            "cm_symboles_entretien_eti " +
                            "where " +
                            "ent_art_code = art_code and " +
                            "ENT_ART_VAR1 = art_var1 and " +
                            "ENT_ART_VAR2 = '-' and " +
                            "ENT_ART_VAR3 = '-' and " +
                            "ent_eru_code = cmsee_code_entretien and " +
                            "ent_erv_val = cmsee_valeur_entretien and " +
                            "ent_no = ? and " +
                            "ent_eru_code = ? and " +
                            "art_gencod = ? and " +
                            "cmsee_type_imp = 'SATO'" +
                            ")",
                            new Object[]{
                                p_ent_no, p_ent_eru_code, eanCode,
                                p_ent_no, p_ent_eru_code, eanCode,
                                p_ent_no, p_ent_eru_code, eanCode
                            }
                    );
                    if (lines.length == 0) {
                        sb.append(" ");
                    } else {
                        sb.append((String) lines[0][0]);
                    }
                }
                data.setZone5(sb.toString());
                data.setZone6(getComposition(art, lang));
                data.setTaillCode(getTailleLib(art));
            }

            return data;
        } catch (TechniqueException e) {
            throw new MetierException(e.getMessage());
        }
    }
}

package camaieu.egarmentcq.report;

import camaieu.common.HttpPdfDocument;
import camaieu.common.ITextUtils;
import camaieu.common.StringUtils;
import camaieu.common.WUtils;
import camaieu.egarmentcq.bo.BoDtCmdeAchatTete;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.StringTokenizer;

/**
 * HttpPdfDocument pour la génération des commandes
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 22/01/2003
 * @creation_date 26/01/2003
 * @status pour validation
 */
public final class OrderReport extends HttpPdfDocument {

    /**
     * police
     */
    private static final Font FONT_NORMAL_BIG = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);

    /**
     * police
     */
    private static final Font FONT_BOLD_BIG = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);

    /**
     * police
     */
    private static final Font FONT_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);

    /**
     * police
     */
    private static final Font FONT_BOLD = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);

    /**
     * police
     */
    private static final Font FONT_BOLD_UNDERLINE = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);

    /**
     * espacement simple
     */
    private static final Chunk CHUNK_SPACE = new Chunk(" ", FONT_NORMAL);

    /**
     * tablulation
     */
    private static final Chunk CHUNK_TAB = new Chunk("      ", FONT_NORMAL);

    /**
     * données du rapport
     */
    private OrderReportData data;

    /**
     * constructeur par défaut
     */
    public OrderReport() {
    }

    /**
     * modifie les données du rapport
     * @param data
     */
    public void setData(OrderReportData data) {
        this.data = data;
    }

    /**
     * @see HttpPdfDocument#writeDocument();
     */
    public void writeDocument() throws IOException, DocumentException {
        HttpServletRequest request = getRequest();
        Document doc = getDocument();
        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;
        doc.open();

        PdfPTable page = new PdfPTable(1);
        page.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        //--------------------- TABLE 1 : Header
        //--------------------------------------------------------------------------------

        table = new PdfPTable(2);
        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ORDER",-1)
                , FONT_BOLD_BIG));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ORDER_NBR",-1),
                FONT_NORMAL_BIG));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getNoCmde(),"",-1), FONT_BOLD_BIG));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getNoVersion(),"",-1), FONT_BOLD_BIG));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ORDER_DATE",-1),
                FONT_NORMAL));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getDateCmde(),"",-1), FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);


        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.DEPARTURE_DATE",-1),
                FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getDateDepart(),"",-1), FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ARRIVAL_DATE",-1),
                FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getDateRendu(),"",-1), FONT_BOLD));
        paragraph.add(Chunk.NEWLINE);
        String adresse = data.getAddresse();
        if (adresse != null) {
            StringTokenizer st = new StringTokenizer(data.getAddresse(), "\n", true);
            while (st.hasMoreTokens()) {
                paragraph.add(CHUNK_TAB);
                paragraph.add(new Chunk(st.nextToken(), FONT_BOLD));
            }
        }
        table.addCell(ITextUtils.createBoxedPdfPCell(paragraph));

        table.addCell(ITextUtils.createEmptyPdfPCell(2, 3));

        table.addCell(ITextUtils.createEmptyPdfPCell(1, 3));

        paragraph = new Paragraph();
        paragraph.add(CHUNK_TAB);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouNom(),"",-1), FONT_BOLD));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(CHUNK_TAB);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouAdr1(),"",-1), FONT_BOLD));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(CHUNK_TAB);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouAdr2(),"",-1), FONT_BOLD));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(CHUNK_TAB);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouPostal(),"",-1), FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouVille(),"",-1), FONT_BOLD));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(CHUNK_TAB);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouPays(),"",-1), FONT_BOLD));
        table.addCell(ITextUtils.createBoxedPdfPCell(paragraph));

//            cell=new Cell(table);cell.setBorder(Rectangle.NO_BORDER);cell.setBorderColor(Color.blue);
//            page.addCell(cell);
//            page.insertTable(table);
        page.addCell(table);

        //--------------------- TABLE 2 : Modlele
        //--------------------------------------------------------------------------------

        page.addCell(ITextUtils.createEmptyPdfPCell(1, 5));
        table = new PdfPTable(2);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.MODEL",-1),
                FONT_BOLD_UNDERLINE));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFaaCode(),"",-1), FONT_NORMAL));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFaaLib(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.SUPPLIER",-1),
                FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getFouCode(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.REF_ITEM_SUPPLIER",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        table.addCell(ITextUtils.createEmptyPdfPCell(1, 3));

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.REF_ITEM_CAMAIEU",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getArtCode(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ITEM_DESC",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getArtLib(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ENTRETIEN",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(StringUtils.concat(data.getEntretien()," ",true," / ",true), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.COMPOSITION",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(StringUtils.concat(data.getComposition()," ",true," / ",true), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        table.addCell(cell);

        page.addCell(ITextUtils.createBoxedPdfPCell(table));

        // ------------------ Table 3 - MODELE COULEUR
        //--------------------------------------------------------------------------------

        page.addCell(ITextUtils.createEmptyPdfPCell(1, 5));
        table = new PdfPTable(1);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.MODEL_COLOR",-1),
                FONT_BOLD_UNDERLINE));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.CODE_COLORIS_CAMAIEU",-1),
                FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getCouCode(),"",-1), FONT_NORMAL));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getCouLib(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
        table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.COLORIS_SUPPLIER",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
        table.addCell(cell);

        // TABLEAU INTERNE
        BoDtCmdeAchatTete.TailleQteCmdStruct[] colorisData = data.getColoris();
        if (colorisData != null && colorisData.length > 0) {
            PdfPTable colorisTable = new PdfPTable(colorisData.length+2);
            colorisTable.setWidthPercentage(80);
            colorisTable.addCell(ITextUtils.createEmptyPdfPCell(1,10));
            for (int i = 0; i < colorisData.length; i++) {
                paragraph = new Paragraph();
                paragraph.add(new Chunk(WUtils.toDisplayable(request, colorisData[i].getTaille(),"",-1), FONT_BOLD));
                cell = new PdfPCell(paragraph);
                cell.setBorder(Rectangle.BOX);
                cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
                colorisTable.addCell(cell);
            }
            colorisTable.addCell(ITextUtils.createEmptyPdfPCell(1,10));

            colorisTable.addCell(ITextUtils.createEmptyPdfPCell(1,10));
            for (int i = 0; i < colorisData.length; i++) {
                paragraph = new Paragraph();
                paragraph.add(new Chunk(WUtils.toDisplayable(request, colorisData[i].getQte(),"",-1), FONT_NORMAL));
                cell = new PdfPCell(paragraph);
                cell.setBorder(Rectangle.BOX);
                cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
                colorisTable.addCell(cell);
            }
            colorisTable.addCell(ITextUtils.createEmptyPdfPCell(1,10));

            cell = new PdfPCell(colorisTable);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
        } else {
            table.addCell(ITextUtils.createEmptyPdfPCell(1, 1));
        }

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.TOTAL_QTY",-1),
                FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getQteCmde(),"",-1), FONT_NORMAL));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.PRICE_UHT",-1),
                FONT_BOLD));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getPrixAUHT(),"",-1), FONT_NORMAL));
        paragraph.add(CHUNK_SPACE);
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.EURO",-1),
                FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
        table.addCell(cell);

        page.addCell(ITextUtils.createBoxedPdfPCell(table));

        // -------------------- Table 4
        //--------------------------------------------------------------------------------

        page.addCell(ITextUtils.createEmptyPdfPCell(1, 5));
        table = new PdfPTable(2);
        table.setWidths(new int[]{2, 1});

        PdfPTable sous_table = new PdfPTable(2);
        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ORDER_COND",-1),
                FONT_BOLD_UNDERLINE));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.PAYE_COND",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getCondPaiement(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);


        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.LIVR_COND",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getCondLivraison(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);


        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.TRANSPORT",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getTransport(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);


        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.CONDITIONNING",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getConditionnement(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);


        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.ETIQ_SUPPLIER",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getEtiqFournisseur(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);


        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.QC_SUPPLIER",-1),
                FONT_BOLD));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        paragraph = new Paragraph();
        paragraph.add(new Chunk(WUtils.toDisplayable(request, data.getCtrlQualiteFournisseur(),"",-1), FONT_NORMAL));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        sous_table.addCell(cell);

        table.addCell(ITextUtils.createBoxedPdfPCell(sous_table));

        sous_table = new PdfPTable(1);
        paragraph = new Paragraph();
        paragraph.add(new Chunk(
                WUtils.getDisplayable(getRequest(),"ORDER_REPORT.SIGNATURE",-1),
                FONT_BOLD_UNDERLINE));
        cell = new PdfPCell(paragraph);
        cell.setBorder(Cell.NO_BORDER);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        sous_table.addCell(cell);

        table.addCell(ITextUtils.createBoxedPdfPCell(sous_table));

        cell = new PdfPCell(table);
        cell.setBorder(Rectangle.NO_BORDER);
        page.addCell(cell);

        doc.add(page);
    }

    /**
     * données du rapport
     */
    public static class OrderReportData {
        private Integer noCmde = new Integer(1456);
        private Integer noVersion = new Integer(1);
        private Timestamp dateCmde = new Timestamp(System.currentTimeMillis());
        private Timestamp dateDepart = new Timestamp(System.currentTimeMillis());
        private Timestamp dateRendu = new Timestamp(System.currentTimeMillis());
        private String addresse = "CAMAÏEU ENTREPOT,\n211 AVENUE BRAME\nBP229\n\n59100 ROUBAIX CEDEX 1";
        private String fouCode = "fouCode";
        private String fouNom = "fouNom";
        private String fouAdr1 = "fouAdr1";
        private String fouAdr2 = "fouAdr2";
        private String fouPostal = "fouPostal";
        private String fouVille = "fouVille";
        private String fouPays = "fouPays";
        private String faaCode = "faaCode";
        private String faaLib = "faaLib";
        private String artCode = "artCode";
        private String artLib = "artLib";
        private String[][] entretien = new String[][]{{"50","COTON"},{"50","POLY"}};
        private String[][] composition = new String[][]{{"50","COTON"},{"50","POLY"}};
        private String couCode = "couCode";
        private String couLib = "couLib";
        private BoDtCmdeAchatTete.TailleQteCmdStruct[] coloris = new BoDtCmdeAchatTete.TailleQteCmdStruct[]{
            new BoDtCmdeAchatTete.TailleQteCmdStruct("32",new Integer(12)),
            new BoDtCmdeAchatTete.TailleQteCmdStruct("34",new Integer(36)),
            new BoDtCmdeAchatTete.TailleQteCmdStruct("36",new Integer(12)),
            new BoDtCmdeAchatTete.TailleQteCmdStruct("40",new Integer(6))
        };

        private Integer qteCmde = new Integer(5);
        private Double prixAUHT = new Double(5);
        private String condPaiement = "";
        private String condLivraison = "";
        private String livraison = "";
        private String conditionnement = "";
        private String etiqFournisseur = "";
        private String ctrlQualiteFournisseur = "";
        private String transport = "";

        public Integer getNoCmde() {
            return noCmde;
        }

        public void setNoCmde(Integer noCmde) {
            this.noCmde = noCmde;
        }

        public Integer getNoVersion() {
            return noVersion;
        }

        public void setNoVersion(Integer noVersion) {
            this.noVersion = noVersion;
        }

        public Timestamp getDateCmde() {
            return dateCmde;
        }

        public void setDateCmde(Timestamp dateCmde) {
            this.dateCmde = dateCmde;
        }

        public Timestamp getDateDepart() {
            return dateDepart;
        }

        public void setDateDepart(Timestamp dateDepart) {
            this.dateDepart = dateDepart;
        }

        public Timestamp getDateRendu() {
            return dateRendu;
        }

        public void setDateRendu(Timestamp dateRendu) {
            this.dateRendu = dateRendu;
        }

        public String getAddresse() {
            return addresse;
        }

        public void setAddresse(String addresse) {
            this.addresse = addresse;
        }

        public String getFouNom() {
            return fouNom;
        }

        public void setFouNom(String fouNom) {
            this.fouNom = fouNom;
        }

        public String getFouAdr1() {
            return fouAdr1;
        }

        public void setFouAdr1(String fouAdr1) {
            this.fouAdr1 = fouAdr1;
        }

        public String getFouAdr2() {
            return fouAdr2;
        }

        public void setFouAdr2(String fouAdr2) {
            this.fouAdr2 = fouAdr2;
        }

        public String getFouPostal() {
            return fouPostal;
        }

        public void setFouPostal(String fouPostal) {
            this.fouPostal = fouPostal;
        }

        public String getFouVille() {
            return fouVille;
        }

        public void setFouVille(String fouVille) {
            this.fouVille = fouVille;
        }

        public String getFouPays() {
            return fouPays;
        }

        public void setFouPays(String fouPays) {
            this.fouPays = fouPays;
        }

        public String getFaaCode() {
            return faaCode;
        }

        public void setFaaCode(String faaCode) {
            this.faaCode = faaCode;
        }

        public String getFaaLib() {
            return faaLib;
        }

        public void setFaaLib(String faaLib) {
            this.faaLib = faaLib;
        }

        public String getArtCode() {
            return artCode;
        }

        public void setArtCode(String artCode) {
            this.artCode = artCode;
        }

        public String getArtLib() {
            return artLib;
        }

        public void setArtLib(String artLib) {
            this.artLib = artLib;
        }

        public String[][] getEntretien() {
            return entretien;
        }

        public void setEntretien(String[][] entretien) {
            this.entretien = entretien;
        }

        public String[][] getComposition() {
            return composition;
        }

        public void setComposition(String[][] composition) {
            this.composition = composition;
        }

        public String getFouCode() {
            return fouCode;
        }

        public void setFouCode(String fouCode) {
            this.fouCode = fouCode;
        }

        public String getCouCode() {
            return couCode;
        }

        public void setCouCode(String couCode) {
            this.couCode = couCode;
        }

        public String getCouLib() {
            return couLib;
        }

        public void setCouLib(String couLib) {
            this.couLib = couLib;
        }

        public BoDtCmdeAchatTete.TailleQteCmdStruct[] getColoris() {
            return coloris;
        }

        public void setColoris(BoDtCmdeAchatTete.TailleQteCmdStruct[] coloris) {
            this.coloris = coloris;
        }

        public Integer getQteCmde() {
            return qteCmde;
        }

        public void setQteCmde(Integer qteCmde) {
            this.qteCmde = qteCmde;
        }

        public Double getPrixAUHT() {
            return prixAUHT;
        }

        public void setPrixAUHT(Double prixAUHT) {
            this.prixAUHT = prixAUHT;
        }

        public String getCondPaiement() {
            return condPaiement;
        }

        public void setCondPaiement(String condPaiement) {
            this.condPaiement = condPaiement;
        }

        public String getCondLivraison() {
            return condLivraison;
        }

        public void setCondLivraison(String condLivraison) {
            this.condLivraison = condLivraison;
        }

        public String getLivraison() {
            return livraison;
        }

        public void setLivraison(String livraison) {
            this.livraison = livraison;
        }

        public String getConditionnement() {
            return conditionnement;
        }

        public void setConditionnement(String conditionnement) {
            this.conditionnement = conditionnement;
        }

        public String getEtiqFournisseur() {
            return etiqFournisseur;
        }

        public void setEtiqFournisseur(String etiqFournisseur) {
            this.etiqFournisseur = etiqFournisseur;
        }

        public String getCtrlQualiteFournisseur() {
            return ctrlQualiteFournisseur;
        }

        public void setCtrlQualiteFournisseur(String ctrlQualiteFournisseur) {
            this.ctrlQualiteFournisseur = ctrlQualiteFournisseur;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }
    }
}

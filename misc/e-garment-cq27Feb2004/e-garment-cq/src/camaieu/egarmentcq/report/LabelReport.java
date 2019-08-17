package camaieu.egarmentcq.report;

import camaieu.common.HttpPdfDocument;
import camaieu.common.ITextUtils;
import camaieu.common.WUtils;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * HttpPdfDocument pour la génération des étiquettes
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date 22/01/2003
 * @creation_date 26/01/2003
 * @status pour validation
 */
public class LabelReport extends HttpPdfDocument {

    /**
     * police
     */
    private static final Font FONT_1_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);

    /**
     * police
     */
    private static final Font FONT_1_BOLD = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD);

    /**
     * police
     */
    private static final Font FONT_2_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);

    /**
     * police
     */
    private static final Font FONT_2_BOLD = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);

    /**
     * police
     */
    private static final Font FONT_3_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL);

    /**
     * police
     */
    private static final Font FONT_3_BOLD = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);

    /**
     * données du rapport
     */
    private LabelReportData data;

    /**
     * constructeur par défaut
     */
    public LabelReport() {
    }

    /**
     * @see HttpPdfDocument#writeDocument()
     */
    protected void writeDocument() throws IOException, DocumentException {
        HttpServletRequest request = getRequest();
//        HttpServletResponse  response=getResponse();
        LabelReportData data = getData();
        Document doc = getDocument();
        PdfPTable table = null;
        PdfPCell cell = null;
        doc.open();

        PdfPTable page = new PdfPTable(1);
        page.setWidthPercentage(30);
        page.getDefaultCell().setBorder(Rectangle.BOX);
        page.getDefaultCell().setBorderWidth(2);

        table = new PdfPTable(2);
        cell = table.getDefaultCell();
        cell.setBorder(Rectangle.NO_BORDER);


        cell.setColspan(2);
        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        table.addCell(new Paragraph(data.getTitle(), FONT_2_BOLD));

        table.addCell(ITextUtils.createEmptyPdfPCell(2, 3));

        String[][] zone6 = data.getZone6();
        cell.setColspan(1);
        for (int i = 0; i < zone6.length; i++) {
            cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            table.addCell((new Paragraph(zone6[i][0], FONT_1_NORMAL)));

            cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            table.addCell(new Paragraph(zone6[i][1], FONT_1_NORMAL));
        }

        for (int i = zone6.length; i < 10; i++) {
            table.addCell(ITextUtils.createEmptyPdfPCell(2, 3));
        }

        table.addCell(ITextUtils.createEmptyPdfPCell(2, 3));

        cell = new PdfPCell(table);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderWidth(2);
        page.addCell(cell);

        StringBuffer smatchedZone5 = new StringBuffer();
        String zone5 = data.getZone5();
        for (int i = 0; i < zone5.length(); i++) {
            if (i > 0) {
                smatchedZone5.append("  ");
            }
            smatchedZone5.append(zone5.charAt(i));
        }
        cell = new PdfPCell(new Paragraph(smatchedZone5.toString(), FONT_2_NORMAL));
        cell.setPadding(10);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderWidth(2);
        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        page.addCell(cell);

        table = new PdfPTable(2);
        cell = table.getDefaultCell();
        cell.setBorder(Rectangle.NO_BORDER);

        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        table.addCell(new Paragraph(data.getZone2(), FONT_2_NORMAL));

        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        table.addCell(new Paragraph(data.getZone3(), FONT_2_NORMAL));

        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
        cell.setColspan(2);
        table.addCell(new Paragraph(data.getZone4(), FONT_1_NORMAL));

        cell = new PdfPCell(table);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderWidth(2);
        cell.setPadding(5);
        page.addCell(cell);

        // -------------------- TABLE 3
        table = new PdfPTable(2);
        cell = table.getDefaultCell();
        cell.setBorder(Rectangle.NO_BORDER);

        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        table.addCell(new Paragraph(data.getTaillLib(), FONT_3_NORMAL));

        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        table.addCell(new Paragraph(data.getTaillCode(), FONT_3_BOLD));

        cell = new PdfPCell(table);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderWidth(2);
        cell.setPadding(10);
        page.addCell(cell);

        // -------------------- TABLE 3
        table = new PdfPTable(4);
        table.setWidths(new int[]{1, 3, 2, 1});
        cell = table.getDefaultCell();
        cell.setBorder(Rectangle.NO_BORDER);

        BarcodeEAN b = new BarcodeEAN();
        b.setCode(data.getBarCode());
        cell = new PdfPCell(ITextUtils.createBarcodeImage(b, getPdfWriter()));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        table.addCell(cell);
        table.addCell(ITextUtils.createEmptyPdfPCell(4, 3));
        cell = table.getDefaultCell();
        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
        table.addCell(ITextUtils.createEmptyPdfPCell(1, 5));
        table.addCell(new Paragraph(
                data.getZone1().getFormattedValue(),
//                WUtils.toDisplayable(request, data.getZone1()),
                FONT_3_BOLD));
        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
//        table.addCell(new Paragraph("€", FONT_1_BOLD));
        table.addCell(new Paragraph(data.getZone1().getMoneyName(), FONT_1_BOLD));
        table.addCell(ITextUtils.createEmptyPdfPCell(1, 5));

        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
        table.addCell(ITextUtils.createEmptyPdfPCell(1, 5));
        table.addCell(new Paragraph(
                data.getZone1fr().getFormattedValue(),
//                WUtils.toDisplayable(request, data.getZone1fr()),
                FONT_2_BOLD));
        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
        table.addCell(new Paragraph(data.getZone1fr().getMoneyName(), FONT_1_BOLD));
        table.addCell(ITextUtils.createEmptyPdfPCell(1, 5));

        cell = new PdfPCell(table);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderWidth(2);
        cell.setPadding(10);
        page.addCell(cell);

        doc.add(page);
    }

    /**
     * recupère les données du rapport
     * @return
     */
    public LabelReportData getData() {
        return data;
    }

    /**
     * modifie les données du rapport
     * @param data
     */
    public void setData(LabelReportData data) {
        this.data = data;
    }

    public static class Price implements Serializable{
        private Double value;
        private String moneyCode;
        private String moneyName;

        public Price(Double newValue, String newMoneyCode, String newMoneyName) {
            value = newValue;
            moneyCode = newMoneyCode;
            moneyName = newMoneyName;
        }

        public Double getValue() {
            return value;
        }

        public String getMoneyCode() {
            return moneyCode;
        }

        public String getMoneyName() {
            return moneyName;
        }

        public void setValue(Double newValue) {
            value = newValue;
        }

        public void setMoneyCode(String newMoneyCode) {
            moneyCode = newMoneyCode;
        }

        public void setMoneyName(String newMoneyName) {
            moneyName = newMoneyName;
        }
        public String getFormattedValue(){
            return value==null?"": new DecimalFormat("##0.00").format(value.doubleValue());
        }
    }
    /**
     * données du rapport des étiquettes
     */
    public static class LabelReportData implements Serializable{
        private String title = "Camaieu International";
        private Price zone1 = new Price(new Double(52.90),"01","Euro");
        private Price zone1fr = new Price(new Double(347.00),"91","FRF");
        private String zone2 = "ISTANBUL";
        private String zone3 = "03H2";
        private String zone4 = "411590/	0804/	F50";
        private String zone5 = "QCHAB";
        private String[][] zone6 = new String[][]{
            {"65%", "COTON"},
            {"35%", "POLYAMIDE"}
        };
        private String line1;
        private String barCode = "1044800428410";
//        private String barCode="2500011244328";
        private String taillLib = "Taille";
        private String taillCode = "36";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Price getZone1() {
            return zone1;
        }

        public void setZone1(Price zone1) {
            this.zone1 = zone1;
        }

        public Price getZone1fr() {
            return zone1fr;
        }

        public void setZone1fr(Price zone1fr) {
            this.zone1fr = zone1fr;
        }

        public String getZone2() {
            return zone2;
        }

        public void setZone2(String zone2) {
            this.zone2 = zone2;
        }

        public String getZone3() {
            return zone3;
        }

        public void setZone3(String zone3) {
            this.zone3 = zone3;
        }

        public String getZone4() {
            return zone4;
        }

        public void setZone4(String zone4) {
            this.zone4 = zone4;
        }

        public String getZone5() {
            return zone5;
        }

        public void setZone5(String zone5) {
            this.zone5 = zone5;
        }

        public String getLine1() {
            return line1;
        }

        public void setLine1(String line1) {
            this.line1 = line1;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getTaillLib() {
            return taillLib;
        }

        public void setTaillLib(String taillLib) {
            this.taillLib = taillLib;
        }

        public String getTaillCode() {
            return taillCode;
        }

        public void setTaillCode(String taillCode) {
            this.taillCode = taillCode;
        }

        public String[][] getZone6() {
            return zone6;
        }

        public void setZone6(String[][] zone6) {
            this.zone6 = zone6;
        }
    }
}

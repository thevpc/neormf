package camaieu.common;

import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;

/**
 * Classe utilitaire pour la manipulation des objets de la librairie
 * itext
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 22/01/2004
 * @last_modification_date date 26/01/2004
 * @status pour validation
 */
public final class ITextUtils {

    /**
     * constructeur privé
     */
    private ITextUtils() {
    }

    /**
     * crée une cellule  avec bordure
     * @param e contenu de la cellule
     * @return toBoxedPdfPCell(e, Rectangle.BOX, 2, 2, 2);
     */
    public static PdfPCell createBoxedPdfPCell(Object e) {
        return toBoxedPdfPCell(e, Rectangle.BOX, 2, 2, 2);
    }

    /**
     * crée une cellule  avec bordure
     * @param e cellule de type PdfPCell, Image, PdfPTable, Phrase
     * sinon elle sera transformée en new PdfPCell(new Phrase(String.valueOf(e)))
     * @param boxBorder type de Bord (Rectangle.NO_BORDER,Rectangle.BOX, ...)
     * @param boxWidth tageur du bord
     * @param internalPadding espacement intérieur
     * @param externalPadding espacement extérieur
     * @return une cellule avec bordure
     */
    public static PdfPCell toBoxedPdfPCell(Object e, int boxBorder, int boxWidth, int internalPadding, int externalPadding) {
        PdfPCell c =
                (e instanceof PdfPCell) ? new PdfPCell((PdfPCell) e) :
                (e instanceof Image) ? new PdfPCell((Image) e) :
                (e instanceof PdfPTable) ? new PdfPCell((PdfPTable) e) :
                (e instanceof Phrase) ? new PdfPCell((Phrase) e) :
                new PdfPCell(new Phrase(String.valueOf(e)));

        c.setPadding(internalPadding);
        c.setBorder(boxBorder);
        c.setBorderWidth(boxWidth);

        PdfPTable p = new PdfPTable(1);
        p.addCell(c);

        PdfPCell c2 = new PdfPCell(p);
        c2.setPadding(externalPadding);
        c2.setBorder(Rectangle.NO_BORDER);
        return c2;
    }

    /**
     * crée une celule vide sans bordure
     * @param colSpan nbr de colonnes
     * @param extraPadding espacement
     * @return une celule vide sans bordure
     */
    public static PdfPCell createEmptyPdfPCell(int colSpan, float extraPadding) {
        PdfPCell cell = new PdfPCell(new Paragraph(""));
        cell.setPadding(extraPadding);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(colSpan);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    /**
     * crée une image Barcode
     * @param barcodeEAN le barcode
     * @param pdfWriter le writer du Document
     * @return une image code à barre
     */
    public static Image createBarcodeImage(BarcodeEAN barcodeEAN, PdfWriter pdfWriter) {
        return createBarcodeImage(barcodeEAN, pdfWriter, Color.BLACK, Color.BLACK);
    }

    /**
     * crée une image Barcode
     * @param barcodeEAN le barcode
     * @param pdfWriter le writer du Document
     * @param barColor couleur des barres
     * @param textColor couleur du texte
     * @return une image du code à barre
     */
    public static Image createBarcodeImage(BarcodeEAN barcodeEAN, PdfWriter pdfWriter, Color barColor, Color textColor) {
        PdfContentByte pdfContentByte = new PdfContentByte(pdfWriter);
        barcodeEAN.placeBarcode(pdfContentByte, barColor, textColor);
        return barcodeEAN.createImageWithBarcode(pdfContentByte, barColor, textColor);
    }

    /**
     * crée un tableau contenant les cellules <code>values</code>
     * @param values les cellules du tableau
     * @param defaultCell cellule par défaut du tableau
     * @return un tableau contenant les cellules spécifiées
     */
    public static PdfPTable createTable(Object[][] values,PdfPCell defaultCell){
        if(values==null || values.length==1){
            PdfPTable pTable=new PdfPTable(1);
            pTable.addCell(createEmptyPdfPCell(1,1));
            return pTable;
        }
        PdfPTable pTable=new PdfPTable(values[0].length);
        if(defaultCell!=null){
            for(int i=0;i<values.length;i++){
                for(int j=0;j<values[i].length;j++){
                        defaultCell.setPhrase(new Phrase(String.valueOf(values[i])));
                        pTable.addCell(defaultCell);
                        defaultCell.setPhrase(null);
                }
            }
        }else{
            for(int i=0;i<values.length;i++){
                for(int j=0;j<values[i].length;j++){
                    pTable.addCell(new Phrase(String.valueOf(values[i])));
                }
            }
        }
        return pTable;
    }
}

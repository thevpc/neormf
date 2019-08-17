package org.vpc.neormf.wws.common;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.log4j.Category;
import org.vpc.neormf.commons.util.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Classe de base pour la generation des documents pdf
 * sur un flux HTTP
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 26/01/2004
 * @last_modification_date date 26/01/2004
 * @status pour validation
 */
public abstract class HttpPdfDocument {
    private static Category log = Category.getInstance(HttpPdfDocument.class.getName());

    /**
     * flux de sortie
     */
    private OutputStream out = null;

    /**
     * document pdf
     */
    private Document document = null;

    /**
     * writer pdf
     */
    private PdfWriter pdfWriter;

    /**
     * vrai si le flux de sortie doit etre ferme e la fin
     */
    private boolean autocloseStream;

    /**
     * request de la servlet
     */
    private HttpServletRequest request;

    /**
     * response de la servlet
     */
    private HttpServletResponse response;

    /**
     * constructeur par defaut
     */
    public HttpPdfDocument() {
    }

//    public HttpPdfDocument(OutputStream out,boolean autocloseStream) throws IOException,DocumentException{
//        this.out = out;
//        this.autocloseStream=autocloseStream;
//        this.document = new Document();
//        //Creates a Writer that listens to this document and writes the document to the OutputStream of your choice:
//        this.pdfWriter=PdfWriter.getInstance(document, out);
//    }


    /**
     * methode e definir pour le contenu du document.
     * par exemple
     * <code>
     *  protected void writeDocument() throws IOException, DocumentException {
     *     HttpServletRequest request = getRequest();
     *     HttpServletResponse  response=getResponse();
     *     LabelReportData data = getData();
     *     Document doc = getDocument();
     *     doc.open();
     *     PdfPTable page = new PdfPTable(1);
     *     page.addCell("Hello");
     *     doc.add(page);
     *  }
     * </code>
     * @throws IOException
     * @throws DocumentException
     */
    protected abstract void writeDocument() throws IOException, DocumentException;

    /**
     * methode e invoquer pour creer le document dans un fichier
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void build(String file) throws IOException, DocumentException {
        build(new File(file));
    }

    /**
     * methode e invoquer pour creer le document dans un fichier
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void build(File file) throws IOException, DocumentException {
        this.request = null;
        this.response = null;
        this.out = new FileOutputStream(file);
        this.autocloseStream = true;
        this.document = new Document();
        //Creates a Writer that listens to this document and writes the document to the OutputStream of your choice:
        this.pdfWriter = PdfWriter.getInstance(document, out);
        try {
            this.autocloseStream = true;
            writeDocument();
        } finally {
            close();
        }
    }

    /**
     * methode e invoquer pour creer le document directement sur le flux http
     * @param request de la servlet
     * @param response de la servlet
     * @throws IOException
     * @throws DocumentException
     */
    public void build(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        this.request = request;
        this.response = response;
        if (this.response != null) {
            this.response.setContentType("application/pdf");
            this.response.flushBuffer();
            this.out = response.getOutputStream();
        }
        this.autocloseStream = false;
        this.document = new Document();


        // j'ai un probleme si j'ecris directement sur le response.getOutputStream()
        // pour contourner ce 'bug?' je cree un fichier temporaire puis je l'efface
        FileOutputStream fos = null;
        File file = null;
        try {
            file = File.createTempFile("htd-", ".pdf");
            fos = new FileOutputStream(file);
            out = fos;
            this.autocloseStream = true;
            //Creates a Writer that listens to this document and writes the document to the OutputStream of your choice:
            this.pdfWriter = PdfWriter.getInstance(document, out);
            writeDocument();
        } finally {
            close();
        }
        IOUtils.copy(file,response.getOutputStream());
//        java.io.FileInputStream fis = null;
//        try {
//            fis = new java.io.FileInputStream(file);
//            final int buffer = 1024;
//            byte[] b = new byte[buffer];
//            int x = 0;
//            while ((x = fis.read(b)) > 0) {
//                response.getOutputStream().write(b, 0, x);
//            }
//        } finally {
//            if (fis != null) {
//                fis.close();
//            }
//        }
        if (file != null) {
            if (!file.delete()) {
                log.error("impossible d'effacer " + file.getCanonicalPath());
            }
        }
    }

//    public void closeSilently() {
//        try {
//            close();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * liberation des ressources
     * @throws IOException
     */
    public void close() throws IOException{
        if (document != null) {
            document.close();
        }
        out.flush();
        if (autocloseStream) {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * le document pdf
     * @return le document pdf
     */
    public Document getDocument() {
        return document;
    }

    /**
     * le PdfWriter
     * @return le PdfWriter
     */
    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }

    /**
     * la request de la servlet
     * @return la request de la servlet
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * la response de la servlet
     * @return HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        return response;
    }
}

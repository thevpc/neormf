package camaieu.egarmentcq.common;

import camaieu.common.IOUtils;
import camaieu.common.WUtils;
import camaieu.upload.FileNameGenerator;
import camaieu.upload.FileUploadHelper;
import camaieu.upload.SimpleUploadableFileFilter;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FilenameFilter;

/**
 * Classe utilitaire pour l'upload des fichiers
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 27/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class InspectionReportUploadHelper extends FileUploadHelper{
    /**
     * prestCode
     */
    private String prestCode;

    /**
     * fouCode
     */
    private String fouCode;

    /**
     * artCode
     */
    private String artCode;

    /**
     * couCode
     */
    private String couCode;

    /**
     * cmdCode
     */
    private String cmdCode;

    /**
     * unique constructeur
     * @param prestCode
     * @param fouCode
     * @param artCode
     * @param couCode
     * @param cmdCode
     */
    public InspectionReportUploadHelper(
                                        ServletContext servletContext,
                                        String prestCode,
                                        String fouCode,
                                        String artCode,
                                        String couCode,
                                        String cmdCode) {
        super(
                WUtils.getStringWebEnvVar("uploadWebRoot",null)==null ?
                    new File(WUtils.getStringWebEnvVar("uploadRoot",null))
                    :
                    new File(servletContext.getRealPath(WUtils.getStringWebEnvVar("uploadWebRoot",null))),
                new File(WUtils.getStringWebEnvVar("uploadTempRoot",null))
        );
        this.prestCode=prestCode;
        this.fouCode=fouCode;
        this.artCode=artCode;
        this.couCode=couCode;
        this.cmdCode=cmdCode;
        setFileNameGenerator(new FileNameGenerator() {
            public File rename(FileUploadHelper helper, File tempFile) {
                for(int i=1;i<10*WUtils.getIntWebEnvVar("uploadFileMaxCount",7);i++){
                    File f=new File(helper.getRoot(),
                    InspectionReportUploadHelper.this.prestCode+"_"+
                    InspectionReportUploadHelper.this.fouCode+"_"+
                    InspectionReportUploadHelper.this.artCode+"_"+
                    InspectionReportUploadHelper.this.couCode+"_"+
                    InspectionReportUploadHelper.this.cmdCode+"_"+
                            i+
                            "."+IOUtils.getFileExtension(tempFile)
                    );
                    if(!f.exists()){
                        return f;
                    }
                }
                throw new IllegalArgumentException("infinite loop");
            }
        });
        setFilenameFilter(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return getRoot().equals(dir) && name.startsWith(
                    InspectionReportUploadHelper.this.prestCode+"_"+
                    InspectionReportUploadHelper.this.fouCode+"_"+
                    InspectionReportUploadHelper.this.artCode+"_"+
                    InspectionReportUploadHelper.this.couCode+"_"+
                    InspectionReportUploadHelper.this.cmdCode+"_"
                );
            }
        });
        setUploadableFileFilter(new SimpleUploadableFileFilter(
                WUtils.getIntWebEnvVar("uploadFileMaxSize",700)*1024,
                WUtils.getStringArrayWebEnvVar(
                    "uploadFileTypes",",; ",false,
                        new String[]{"doc","pdf","jpg","gif", "tif","zip","txt"}
                )
                ));
        setMaxFileCount(WUtils.getIntWebEnvVar("uploadFileMaxCount",7));
    }

    public String getFouCode() {
        return fouCode;
    }

    public void setFouCode(String fouCode) {
        this.fouCode = fouCode;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public String getCouCode() {
        return couCode;
    }

    public void setCouCode(String couCode) {
        this.couCode = couCode;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public String getPrestCode() {
        return prestCode;
    }

    public void setPrestCode(String prestCode) {
        this.prestCode = prestCode;
    }
}

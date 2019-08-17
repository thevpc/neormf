package camaieu.upload;

import camaieu.common.WUtils;

import java.io.File;

/**
 * exception declanchée si le fichier uploadé n'est pas acceptable
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class IllegalFileUploadedException extends Exception{
    /**
     * fichier non acceptable
     */
    private File file;

    /**
     * constructeur
     * @param helper
     * @param file
     */
    public IllegalFileUploadedException(FileUploadHelper helper,File file) {
        this(helper,file,WUtils.getDisplayable(helper.getRequest(),"ERROR.ILLEGAL_FILE_UPLOADED"));
    }

    /**
     * constructeur
     * @param helper
     * @param file
     * @param message
     */
    public IllegalFileUploadedException(FileUploadHelper helper,File file,String message) {
        super(message);
        this.file=file;
    }

    /**
     * fichier non acceptable
     * @return
     */
    public File getFile() {
        return file;
    }
}

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
public class IllegalFileUploadedCountException extends IllegalFileUploadedException{
    /**
     * nombre max de fichier à uploader
     */
    private int maxFileCount;
    /**
     * constructeur
     * @param helper
     * @param file
     */
    public IllegalFileUploadedCountException(int maxFileCount, FileUploadHelper helper,File file) {
        this(maxFileCount,helper,file,WUtils.getDisplayable(
                helper.getRequest(),
                "ERROR.ILLEGAL_FILE_UPLOADED.BAD_COUNT",
                new Object[]{new Integer(maxFileCount)}
        ));
    }

    /**
     * constructeur
     * @param helper
     * @param file
     * @param message
     */
    public IllegalFileUploadedCountException(int maxFileCount, FileUploadHelper helper,File file, String message) {
        super(helper,file, message);
        this.maxFileCount=maxFileCount;
    }

    /**
     * nbr max de fichiers à uploader
     * @return
     */
    public int getMaxFileCount() {
        return maxFileCount;
    }

}

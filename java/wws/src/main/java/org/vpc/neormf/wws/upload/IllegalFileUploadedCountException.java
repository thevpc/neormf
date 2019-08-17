package org.vpc.neormf.wws.upload;

import org.vpc.neormf.wws.common.WUtils;

import java.io.File;

/**
 * exception declanchee si le fichier uploade n'est pas acceptable
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class IllegalFileUploadedCountException extends IllegalFileUploadedException{
    /**
     * nombre max de fichier e uploader
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
     * nbr max de fichiers e uploader
     * @return
     */
    public int getMaxFileCount() {
        return maxFileCount;
    }

}

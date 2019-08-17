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
public class IllegalFileUploadedSizeException extends IllegalFileUploadedException{
    /**
     * taille e refuser
     */
    private long badSize;

    /**
     * taille maximale
     */
    private long maxSize;

    /**
     * constructeur
     * @param helper
     * @param file
     */
    public IllegalFileUploadedSizeException(long badSize,long maxSize,FileUploadHelper helper,File file) {
        this(
                badSize,maxSize,
                helper,file,WUtils.getDisplayable(helper.getRequest(),"ERROR.ILLEGAL_FILE_UPLOADED.BAD_SIZE",
                new Object[]{
                    new Long( badSize),
                    new Long(maxSize)
                }));
    }

    /**
     * constructeur
     * @param helper
     * @param file
     * @param message
     */
    public IllegalFileUploadedSizeException(long badSize,long maxSize,FileUploadHelper helper,File file, String message) {
        super(helper,file, message);
        this.badSize=badSize;
        this.maxSize=maxSize;
    }

    /**
     * taille e refuser
     */
    public long getBadSize() {
        return badSize;
    }

    /**
     * taille maximale
     */
    public long getMaxSize() {
        return maxSize;
    }
}

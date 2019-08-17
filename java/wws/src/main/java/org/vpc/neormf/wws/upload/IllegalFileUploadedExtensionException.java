package org.vpc.neormf.wws.upload;

import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.commons.util.StringUtils;

import java.io.File;

/**
 * exception declanchee si le fichier uploade n'est pas acceptable
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class IllegalFileUploadedExtensionException extends IllegalFileUploadedException{

    /**
     * extension du fichier e rejeter
     */
    private String badExtension;

    /**
     * les extensions imposees
     */
    private String[] requiredExtensions;

    /**
     * constructeur
     * @param helper
     * @param file
     */
    public IllegalFileUploadedExtensionException(String badExtension, String[] requiredExtensions,FileUploadHelper helper,File file) {
        this(badExtension, requiredExtensions,helper,file,WUtils.getDisplayable(helper.getRequest(),"ERROR.ILLEGAL_FILE_UPLOADED.BAD_EXTENSION",
                new Object[]{badExtension,StringUtils.concat(requiredExtensions,",",true)}));
    }

    /**
     * constructeur
     * @param helper
     * @param file
     * @param message
     */
    public IllegalFileUploadedExtensionException(String badExtension, String[] requiredExtensions,FileUploadHelper helper,File file, String message) {
        super(helper,file, message);
        this.badExtension=badExtension;
        this.requiredExtensions=requiredExtensions;
    }

    /**
     * extension du fichier e rejeter
     * @return
     */
    public String getBadExtension() {
        return badExtension;
    }

    /**
     * les extensions imposees
     * @return
     */
    public String[] getRequiredExtensions() {
        return requiredExtensions;
    }
}

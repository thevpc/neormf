package camaieu.upload;

import camaieu.common.StringUtils;
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
public class IllegalFileUploadedExtensionException extends IllegalFileUploadedException{

    /**
     * extension du fichier à rejeter
     */
    private String badExtension;

    /**
     * les extensions imposées
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
     * extension du fichier à rejeter
     * @return
     */
    public String getBadExtension() {
        return badExtension;
    }

    /**
     * les extensions imposées
     * @return
     */
    public String[] getRequiredExtensions() {
        return requiredExtensions;
    }
}

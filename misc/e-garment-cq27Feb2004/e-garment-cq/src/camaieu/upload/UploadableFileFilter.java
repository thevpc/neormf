package camaieu.upload;

import java.io.File;

/**
 * filtre pour les fichier uploadés
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public interface UploadableFileFilter{

    /**
     * vérifie si le fichier est accepté ou non
     * @param helper instance du FileUploadHelper
     * @param file fichier à vérifier
     * @throws IllegalFileUploadedException si le fichier n'est pas accepté
     */
    public void checkFile(FileUploadHelper helper, File file) throws IllegalFileUploadedException;
}

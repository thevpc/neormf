package camaieu.upload;

import java.io.File;

/**
 * filtre pour les fichier upload�s
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public interface UploadableFileFilter{

    /**
     * v�rifie si le fichier est accept� ou non
     * @param helper instance du FileUploadHelper
     * @param file fichier � v�rifier
     * @throws IllegalFileUploadedException si le fichier n'est pas accept�
     */
    public void checkFile(FileUploadHelper helper, File file) throws IllegalFileUploadedException;
}

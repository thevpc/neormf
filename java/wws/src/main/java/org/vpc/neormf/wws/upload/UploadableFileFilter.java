package org.vpc.neormf.wws.upload;

import java.io.File;

/**
 * filtre pour les fichier uploades
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public interface UploadableFileFilter{

    /**
     * verifie si le fichier est accepte ou non
     * @param helper instance du FileUploadHelper
     * @param file fichier e verifier
     * @throws IllegalFileUploadedException si le fichier n'est pas accepte
     */
    public void checkFile(FileUploadHelper helper, File file) throws IllegalFileUploadedException;
}

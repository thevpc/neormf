package org.vpc.neormf.wws.upload;

import java.io.File;

/**
 * interface pour le renommage des fichier temp en fichier dest
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public interface FileNameGenerator{
    /**
     * renommer (copier) le fichier temp en dest
     * @param helper
     * @param tempFile
     * @return
     */
    public File rename(FileUploadHelper helper, File tempFile);
}

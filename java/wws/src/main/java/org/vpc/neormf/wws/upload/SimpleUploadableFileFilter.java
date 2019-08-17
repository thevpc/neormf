package org.vpc.neormf.wws.upload;

import org.vpc.neormf.commons.util.IOUtils;

import java.io.File;
import java.util.HashSet;

/**
 * filtre des fichiers acceptes par defaut
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class SimpleUploadableFileFilter implements UploadableFileFilter{
    /**
     * taille max des fichiers
     */
    private long maxSize;

    /**
     * les types acceptes pour les fichiers
     */
    private HashSet acceptedExtensions;

    /**
     * unique constructeur
     * @param newMaxSize taille max des fichiers (&lt;0 si aucun critere sur la taille)
     * @param newAcceptedExtensions es types acceptes pour les fichiers (null si tous acceptes)
     */
    public SimpleUploadableFileFilter(long newMaxSize, String[] newAcceptedExtensions) {
        this.maxSize = newMaxSize;
        if(newAcceptedExtensions!=null && newAcceptedExtensions.length!=0){
            this.acceptedExtensions=new HashSet(newAcceptedExtensions.length);
            for(int i=0;i<newAcceptedExtensions.length;i++){
                this.acceptedExtensions.add(newAcceptedExtensions[i].toLowerCase());
            }
        }
    }

    /**
     *
     * @see UploadableFileFilter#checkFile(org.vpc.neormf.wws.upload.FileUploadHelper, java.io.File)
     */
    public void checkFile(FileUploadHelper helper, File file) throws IllegalFileUploadedException {
        if(!(acceptedExtensions==null || acceptedExtensions.size()==0 ||  acceptedExtensions.contains(IOUtils.getFileExtension(file)))){
            throw new IllegalFileUploadedExtensionException(
                    IOUtils.getFileExtension(file),
                    (String[]) acceptedExtensions.toArray(new String[acceptedExtensions.size()]),
                    helper,
                    file);
        }
        if(maxSize>0 && file.length()>maxSize){
            throw new IllegalFileUploadedSizeException(
                    file.length(),
                    maxSize,
                    helper,
                    file
            );
        }
    }

}

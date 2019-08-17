package camaieu.upload;

import camaieu.common.IOUtils;

import java.io.File;
import java.util.HashSet;

/**
 * filtre des fichiers acceptés par défaut
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
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
     * les types acceptés pour les fichiers
     */
    private HashSet acceptedExtensions;

    /**
     * unique constructeur
     * @param newMaxSize taille max des fichiers (&lt;0 si aucun critère sur la taille)
     * @param newAcceptedExtensions es types acceptés pour les fichiers (null si tous acceptés)
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
     * @see UploadableFileFilter#checkFile(camaieu.upload.FileUploadHelper, java.io.File)
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

package camaieu.upload;

import camaieu.common.IOUtils;
import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;

/**
 * Classe utilitaire pour le t�l�versement de fichiers
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 27/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class FileUploadHelper {
    /**
     * comparateur de fichiers par d�faut
     */
    public static final Comparator FILE_NAME_COMPARATOR=new Comparator() {

        /**
         * @see Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            File f1=(File) o1;
            File f2=(File) o2;
            return f1.getName().compareTo(f2.getName());
        }

        /**
         * @see Object#hashCode()
         */
        public int hashCode() {
            return 125;
        }

        /**
         * @see Object#equals(java.lang.Object)
         */
        public boolean equals(Object obj) {
            return FILE_NAME_COMPARATOR==obj;
        }
    };

    private File root;
    private File tempRoot;
    private FilenameFilter filenameFilter;
    private MultipartRequest multipartRequest;
    private HttpServletRequest request;
    private FileNameGenerator fileNameGenerator;
    private UploadableFileFilter uploadableFileFilter;
    private Comparator fileComparator;
    private boolean autoDeleteTempFiles=true;
    private int maxFileCount=-1;

    public FileUploadHelper(File root,File tempRoot) {
        this(root,tempRoot,null);
    }

    public FileUploadHelper(File root,File tempRoot, FilenameFilter filenameFilter) {
        this.root = root;
        this.filenameFilter = filenameFilter;
        this.tempRoot=tempRoot;
        if(!root.exists()){
            if(!root.mkdirs()){
                throw new IllegalArgumentException("folder "+root+" does not exist and could not be created");
            }
        }
        if(!tempRoot.exists()){
            if(!tempRoot.mkdirs()){
                throw new IllegalArgumentException("folder "+tempRoot+" does not exist and could not be created");
            }
        }
    }

    /**
     * nombre de fichiers v�rifiant le filenameFilter
     * @return
     */
    public int getFilesCount(){
        return root.listFiles(filenameFilter).length;
    }

    /**
     * fichier � l'index donn�
     * <BR> getFiles()[index]
     * @param index
     * @return
     */
    public File getFile(int index){
        return getFiles()[index];
    }

    /**
     * tous les fichiers t�l�charg� v�rifiant filenameFilter
     * et ordonn�s selon fileComparator
     * @return
     */
    public File[] getFiles(){
        File[] f=root.listFiles(filenameFilter);
        Arrays.sort(f,fileComparator==null ? FILE_NAME_COMPARATOR : fileComparator);
        return f;
    }

    /**
     * effacer le fichier � la position index
     * @param index
     * @return
     */
    public boolean deleteFile(int index){
        return getFiles()[index].delete();
    }

    /**
     * t�l�verse touts les fichiers de le formulaire
     * @return
     * @throws IllegalFileUploadedException si le fichier n'est pas accept� (type incorrecte par exemple)
     * @throws IOException si erreur lors de la copie du fichier
     * @throws IllegalArgumentException si fileNameGenerator est null
     */
    public int uploadFiles() throws IllegalFileUploadedException,IOException,IllegalArgumentException{
        int count=0;
        for (Enumeration enu= multipartRequest.getFileNames();enu.hasMoreElements();) {
            String fName = (String) enu.nextElement();
            if(uploadFile(fName)){
                count++;
            }
        }
        return count;
    }

    /**
     * t�l�verse le fichier nomm� <code>name</code> dans le formulaire
     * @param name
     * @return
     * @throws IllegalFileUploadedException si le fichier n'est pas accept� (type incorrecte par exemple)
     * @throws IOException si erreur lors de la copie du fichier
     * @throws IllegalArgumentException si fileNameGenerator est null
     */
    public boolean uploadFile(String name) throws IllegalFileUploadedException,IOException,IllegalArgumentException{
        int initialCount=getFilesCount();
        if(fileNameGenerator!=null){
            File file = multipartRequest.getFile(name);
            if(file!=null){
                try {
                    if(maxFileCount>0 && initialCount>=maxFileCount){
                        throw new IllegalFileUploadedCountException(maxFileCount,this,file);
                    }
                    if(uploadableFileFilter!=null){
                        uploadableFileFilter.checkFile(this,file);
                    }
                    File newFile=fileNameGenerator.rename(this,file);
                    IOUtils.copy(file,newFile);
                    return true;
                } finally {
                    if(autoDeleteTempFiles){
                        if(!file.delete()){
                            System.out.println("could not delete temp file");
                            // log we could not delete temp file ?
                        }
                    }
                }
            }else{
                return false;
            }
        }else{
            throw new IllegalArgumentException("You have to specify a fileNameGenerator");
        }
    }

    /**
     * racine des fichiers upload�s
     * @return
     */
    public File getRoot() {
        return root;
    }

    /**
     * filtre des fichiers t�l�vers� � concid�rer
     * @return
     */
    public FilenameFilter getFilenameFilter() {
        return filenameFilter;
    }

    /**
     * les MultipartRequest associ�
     * @return
     */
    public MultipartRequest getMultipartRequest() {
        return multipartRequest;
    }

    /**
     * le request associ�
     * @return
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * le r�pertoire temporaire de t�l�versement
     * @return
     */
    public File getTempRoot() {
        return tempRoot;
    }

    /**
     * le g�n�rateur de nom pour copie du fichier t�l�vers� depuis le tempRoot vers le root
     * @return
     */
    public FileNameGenerator getFileNameGenerator() {
        return fileNameGenerator;
    }

    /**
     * modifie le g�n�rateur de nom pour copie du fichier t�l�vers� depuis le tempRoot vers le root
     * @param fileNameGenerator
     */
    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
    }

    /**
     * modifie le filtre des fichiers t�l�vers� � concid�rer
     * @param filenameFilter
     */
    public void setFilenameFilter(FilenameFilter filenameFilter) {
        this.filenameFilter = filenameFilter;
    }

    /**
     * modifie le request (et par suite le multipartRequest)
     * @param request
     */
    public void setRequest(HttpServletRequest request) {
        setRequest(request,Integer.MAX_VALUE);
    }
    /**
     * modifie le request (et par suite le multipartRequest)
     * @param request
     */
    public void setRequest(HttpServletRequest request,int maxPostSize) {
        this.request = request;
        try {
            multipartRequest = new MultipartRequest(request,tempRoot.getCanonicalPath(),maxPostSize);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * le filtre des fichiers uploadable (qui v�rifie certaines contraintes)
     * @return
     */
    public UploadableFileFilter getUploadableFileFilter() {
        return uploadableFileFilter;
    }

    /**
     * modifie le filtre des fichiers uploadable (qui v�rifie certaines contraintes)
     * @param uploadableFileFilter
     */
    public void setUploadableFileFilter(UploadableFileFilter uploadableFileFilter) {
        this.uploadableFileFilter = uploadableFileFilter;
    }

    /**
     * l'ordre de tri des fichiers t�l�vers�s
     * @return
     */
    public Comparator getFileComparator() {
        return fileComparator;
    }

    /**
     * modifie l'ordre de tri des fichiers t�l�vers�s
     * @param fileComparator
     */
    public void setFileComparator(Comparator fileComparator) {
        this.fileComparator = fileComparator;
    }

    /**
     * vrai si les fichiers temp doivent �tre effac�s apres copie vers le root
     * @return
     */
    public boolean isAutoDeleteTempFiles() {
        return autoDeleteTempFiles;
    }

    /**
     * si vrai, les fichiers temp doivent �tre effac�s apres copie vers le root
     * @param autoDeleteTempFiles
     */
    public void setAutoDeleteTempFiles(boolean autoDeleteTempFiles) {
        this.autoDeleteTempFiles = autoDeleteTempFiles;
    }

    public int getMaxFileCount() {
        return maxFileCount;
    }

    public void setMaxFileCount(int maxFileCount) {
        this.maxFileCount = maxFileCount;
    }

}

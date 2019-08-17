package camaieu.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Classe utilitaire pour la manipulation des E/S
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 27/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public final class IOUtils {
    /**
     * taille par défaut du buffer de transfert
     */
    public static final int DEFAULT_BUFFER_SIZE=1024;

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(InputStream in,OutputStream out) throws IOException{
        copy(in,out,DEFAULT_BUFFER_SIZE);
    }

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(InputStream in,OutputStream out,int bufferSize) throws IOException{
        byte[] buffer=new byte[bufferSize];
        int len=0;
        while((len=in.read(buffer))>0){
            out.write(buffer,0,len);
        }
    }

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(File in,File out) throws IOException{
        copy(in,out,DEFAULT_BUFFER_SIZE);
    }

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(File in,File out,int bufferSize) throws IOException{
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(in);
            fos=new FileOutputStream(out);
            copy(fis,fos,bufferSize);
        } finally {
            if(fis!=null){
                fis.close();
            }
            if(fos!=null){
                fos.close();
            }
        }
    }


    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(File in,OutputStream out) throws IOException{
        copy(in,out,DEFAULT_BUFFER_SIZE);
    }

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(File in,OutputStream out,int bufferSize) throws IOException{
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(in);
            copy(fis,out,bufferSize);
        } finally {
            if(fis!=null){
                fis.close();
            }
        }
    }

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(InputStream in,File out) throws IOException{
        copy(in,out,DEFAULT_BUFFER_SIZE);
    }

    /**
     * copy le flux d'entrée dans le lux de sortie
     * @param in entrée
     * @param out sortie
     * @throws IOException
     */
    public static void copy(InputStream in,File out,int bufferSize) throws IOException{
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(out);
            copy(in,fos,bufferSize);
        } finally {
            if(fos!=null){
                fos.close();
            }
        }
    }

    /**
     * retourne le nom du fichier (sans l'extension)
     * @param f fichier
     * @return
     */
    public static String getFileName(File f){
        String s=f.getName();
        int i=s.lastIndexOf('.');
        if(i==0){
            return "";
        }else if(i>0){
            return s.substring(0,i);
        }else{
            return s;
        }
    }

    /**
     * retourne l'extension d'un fichier
     * @param f fichier
     * @return
     */
    public static String getFileExtension(File f){
        String s=f.getName();
        int i=s.lastIndexOf('.');
        if(i==0){
            return s.substring(1);
        }else if(i>0){
            if(i<(s.length()-1)){
                return s.substring(i+1);
            }else{
                return "";
            }
        }else{
            return "";
        }
    }

    /**
     * retourne le path relatif
     * @param parent
     * @param son
     * @return
     */
    public static String getRelativePath(File parent,File son){
        String parentPath=null;
        String sonPath=null;
        try {
            parentPath=parent.getCanonicalPath();
            sonPath=son.getCanonicalPath();
        } catch (IOException e) {
            parentPath=parent.getAbsolutePath();
            sonPath=son.getAbsolutePath();
        }
        if(sonPath.startsWith(parentPath)){
            String p=sonPath.substring(parentPath.length());
            if(p.startsWith("/") || p.startsWith("\\")){
                p=p.substring(1);
            }
            return p;
        }
        return null;
    }
}

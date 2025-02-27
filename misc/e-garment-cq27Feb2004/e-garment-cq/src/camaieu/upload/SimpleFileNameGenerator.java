package camaieu.upload;

import camaieu.common.WUtils;

import java.io.File;
import java.util.Date;

/**
 * g�n�rateur de nom par d�faut
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 28/01/2004
 * @last_modification_date date 28/01/2004
 * @status pour validation
 */
public class SimpleFileNameGenerator implements FileNameGenerator{
    /**
     * modele de g�n�ration, {timestamp} sera remplac� par la datedu jour au format yyyyMMddHHmmssSSS
     */
    private String pattern;

    /**
     * constructeur
     * @param pattern mod�le du nom de fichier
     */
    public SimpleFileNameGenerator(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @see FileNameGenerator#rename(camaieu.upload.FileUploadHelper, java.io.File)
     */
    public File rename(FileUploadHelper helper, File tempFile) {
        File nf=null;
        int count=0;
        while(true){
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<pattern.length();i++){
                char c=pattern.charAt(i);
                switch(c){
                    case '{' : {
                        if(pattern.substring(i).startsWith("{timestamp}")){
                            i+="{timestamp}".length()-1;
                            sb.append(WUtils.COMPACT_TIMESTAMP.format(new Date()));
                        }else{
                            sb.append(c);
                        }
                        break;
                    }
                    default :{
                        sb.append(c);
                    }
                }
            }
            nf=new File(helper.getRoot(),sb.toString());
            if(!nf.exists()){
                return nf;
            }
            count++;
            if(count>100){
                throw new IllegalArgumentException("infinite loop");
            }
        }
    }
}

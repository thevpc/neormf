package org.vpc.neormf.jbgen;

import javax.swing.*;
import java.util.ArrayList;
import java.io.File;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 19 avr. 2007 23:09:48
 */
public class JBGenOptions {
    private File[] files=null;
    boolean windowMode=false;

    public JBGenOptions(File[] files,boolean windowMode) {
        this.windowMode=windowMode;
        this.files=files;
    }


    public JBGenOptions(String[] args) {
        ArrayList filesList=new ArrayList();
        for (int i = 0; i < args.length; i++) {
            if(args[i].startsWith("-")){
               if(args[i].equals("-window")){
                   windowMode=true;
               }else{
                   if(windowMode){
                       JOptionPane.showMessageDialog(null,"Unknown option "+args[i]);
                   }else{
                       System.err.println("Unknown option "+args[i]);
                   }
               }
            }else{
                filesList.add(new File(args[i]));
            }
        }

        if(filesList.size()==0){
            windowMode=true;
        }
        files= (File[]) filesList.toArray(new File[filesList.size()]);
    }


    public File[] getFiles() {
        return files;
    }

    public boolean isWindowMode() {
        return windowMode;
    }
}

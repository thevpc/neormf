package org.vpc.neormf.jbgen.java.model.javaclass.parser.sun15;

import org.vpc.neormf.jbgen.java.model.javaclass.JavaCompiler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import com.sun.tools.javac.Main;
import org.vpc.neormf.jbgen.util.JBGenUtils;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 19 avr. 2007 21:47:38
 */
public class JavaCompilerImpl implements JavaCompiler {
    public Class compile(File file, String classpath) throws IOException {
        File root = file.getParentFile();
        //System.getProperty("java.class.path")
        Main.compile(new String[]{"-classpath",classpath,"-d", root.getPath(), /*"-target","1.4", */file.getPath()});
        String[] strings = classpath.split(System.getProperty("path.separator"));
        URL[] all=new URL[strings.length+1];
        all[0]=root.toURL();
        for (int i = 0; i < strings.length; i++) {
            all[i+1]=new File(strings[i]).toURL();
        }
        URLClassLoader urlcl=new URLClassLoader(all,getClass().getClassLoader());
        try {
            return urlcl.loadClass(JBGenUtils.getFileName(file));
        } catch (ClassNotFoundException e) {
            throw new IOException(e.toString());
        }
    }
}

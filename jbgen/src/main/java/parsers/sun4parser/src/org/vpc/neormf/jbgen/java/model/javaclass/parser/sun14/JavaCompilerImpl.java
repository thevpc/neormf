package org.vpc.neormf.jbgen.java.model.javaclass.parser.sun14;

import org.vpc.neormf.jbgen.java.model.javaclass.JavaCompiler;

import java.io.File;
import java.io.IOException;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 19 avr. 2007 21:47:38
 */
public class JavaCompilerImpl implements JavaCompiler {
    public Class compile(File file) throws IOException{
        com.sun.tools.javac.v8.Main m = new com.sun.tools.javac.v8.Main("taha");
        File root = file.getParentFile();
        m.compile(new String[]{"-classpath",System.getProperty("java.class.path"),"-d", root.getPath(), /*"-target","1.4", */file.getPath()});
        ClassLoader c = new sun.rmi.rmic.iiop.DirectoryLoader(root);
        try {
            return c.loadClass(file.getName());
        } catch (ClassNotFoundException e) {
            throw new IOException(e.toString());
        }
    }
}

package org.vpc.neormf.jbgen.java.model.javaclass;

import java.io.File;
import java.io.IOException;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 19 avr. 2007 21:46:46
 */
public interface JavaCompiler {
    public Class compile(File file, String classpath) throws IOException;
}

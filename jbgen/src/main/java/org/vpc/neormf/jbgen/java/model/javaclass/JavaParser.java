package org.vpc.neormf.jbgen.java.model.javaclass;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 19 avr. 2007 21:43:33
 */
public interface JavaParser {
    public JavaClassSource parse(InputStream inputstream) throws IOException;
}

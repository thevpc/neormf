package org.vpc.neormf.jbgen.java.model.javaclass.parser.sun15;

import org.vpc.neormf.jbgen.java.model.javaclass.JavaParser;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;

import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.sun.tools.javac.parser.Scanner;
import com.sun.tools.javac.parser.Parser;
import com.sun.tools.javac.tree.Tree;
import com.sun.tools.javac.util.Context;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 27 janv. 2007 10:34:46
 */
public class JavaParserImpl implements JavaParser {
    public JavaClassSource parse(InputStream inputstream) throws IOException {
        Context context = new Context();
        Scanner.Factory factory = Scanner.Factory.instance(context);
        Scanner scanner = factory.newScanner(inputstream, null);
        Parser parser = Parser.Factory.instance(context).newParser(scanner, true);
        Tree.TopLevel topLevel = parser.compilationUnit();
        JavaClassVisitor classVisitor = new JavaClassVisitor(null, false);
        topLevel.accept(classVisitor);
        inputstream.close();
        return classVisitor.getJbgenJavaClassSource();
    }
}

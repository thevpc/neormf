package org.vpc.neormf.jbgen.java.model.javaclass.parser.sun14;

import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParser;
import org.vpc.neormf.jbgen.java.model.javaclass.parser.sun14.JavaClassVisitor;

import java.io.InputStream;
import java.io.IOException;

import com.sun.tools.javac.v8.util.Context;
import com.sun.tools.javac.v8.parser.Scanner;
import com.sun.tools.javac.v8.parser.Parser;
import com.sun.tools.javac.v8.tree.Tree;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 27 janv. 2007 10:34:46
 */
public class JavaParserImpl implements JavaParser {
    public JavaClassSource parse(InputStream inputstream) throws IOException {
        Context context = new Context();
        Scanner scanner = new Scanner(context, inputstream, null);
        inputstream.close();
        Parser parser = new Parser(context, scanner, true, true);
        Tree.TopLevel topLevel = parser.compilationUnit();
//        PrintWriter printwriter = new PrintWriter(System.out);
        JavaClassVisitor classVisitor = new JavaClassVisitor(null, false);
        topLevel.accept(classVisitor);
        return classVisitor.getJbgenJavaClassSource();
    }
}

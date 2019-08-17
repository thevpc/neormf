/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.java.model.javaclass;

import org.vpc.neormf.jbgen.util.TLog;
import org.vpc.neormf.commons.util.IOUtils;

import java.io.File;
import java.util.Iterator;

/**
 * Java Expression Evaluator Utility class
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 2 avr. 2004 Time: 18:59:46
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JavaEvaluator {

    public JavaClassSource getExtraClass() {
        return extraClass;
    }

    public void setExtraClass(JavaClassSource extraClass) {
        this.extraClass = extraClass;
    }

    private JavaClassSource extraClass;
    private TLog tlog;

    public JavaEvaluator(TLog tlog) {
        this.tlog = tlog;
    }

    public Object evaluate(String expression,String classpath) {
        try {
            File file = File.createTempFile("_exp_",".java");
            JavaClassSource aClass = createClass(IOUtils.getFileName(file), expression);
            aClass.write(file.getParentFile(), false, true,tlog);
            Class cls = JavaClassSource.getJavaCompiler().compile(file, classpath);
//            if (!f.delete()) {
//                System.err.println("JavaEvaluator : failed to delete src file " + f.getPath());
//            }
//            File b = new File(root, aClass.getName() + ".class");
//            if (!b.delete()) {
//                System.err.println("JavaEvaluator : failed to delete bytes file " + f.getPath());
//            }
            return cls.getField("VALUE").get(null);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to evaluate : " + expression, e);
        }
    }

    protected JavaClassSource createClass(String name,String expression){
        JavaClassSource aClass = new JavaClassSource();
        aClass.setModifiers("public");
        aClass.setName(name);

        aClass.addAllImports(extraClass.getImports());

        for (Iterator i = extraClass.getFields().iterator(); i.hasNext();) {
            aClass.addField((JavaField) i.next());
        }

        for (Iterator i = extraClass.getMethods().iterator(); i.hasNext();) {
            aClass.addMethod((JavaMethod) i.next());
        }

        aClass.addField(new JavaField("VALUE", "Object", null, "public static final", expression));
        return aClass;
    }
}

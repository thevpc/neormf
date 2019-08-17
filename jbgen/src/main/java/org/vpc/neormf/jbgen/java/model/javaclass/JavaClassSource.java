package org.vpc.neormf.jbgen.java.model.javaclass;

import org.vpc.neormf.jbgen.java.util.JavaUtils;
import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;

import java.io.*;
import java.util.*;

/**
 * Java Class Source Utility class
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creationtime on Date: 24 mars 2004 Time: 14:22:50
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JavaClassSource implements Cloneable {
    public static final String CLASS_TYPE_CLASS = "class";
    public static final String CLASS_TYPE_INTERFACE = "interface";
    private String pkg;
    private Vector imports = new Vector();
    private String comments;
    private String topLevelComments;
    private String modifiers;
    private String classType = CLASS_TYPE_CLASS;
    private String superClass;
    private Vector interfaces = new Vector();
    private String name;
    private Vector fields = new Vector();
    private Vector methods = new Vector();
    private Vector subClasses = new Vector();
    private Vector userCode = new Vector();
    private File file = null;

    public JavaClassSource() {
    }

    public static JavaClassSource toJavaClassSource(String file) throws IOException {
        ByteArrayInputStream inputstream = null;
        try {
            inputstream = new ByteArrayInputStream(file.getBytes());
            return toJavaClassSource(inputstream);
        } finally {
            if (inputstream != null) {
                inputstream.close();
            }
        }
    }

    private static JavaClassSource toJavaClassSource(File file) throws IOException {
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(file);
            return toJavaClassSource(inputstream);
        } finally {
            if (inputstream != null) {
                inputstream.close();
            }
        }
    }

    private static JavaClassSource toJavaClassSource(InputStream inputstream) throws IOException {
        return getJavaParser().parse(inputstream);
    }

    public static JavaCompiler getJavaCompiler() {
        String s = System.getProperty("JavaCompiler");
        Class c = null;
        if (s != null) {
            try {
                c = Class.forName(s);
            } catch (Throwable e) {
                //
            }
        }
        if (c == null) {
            try {
                c = Class.forName("org.vpc.neormf.jbgen.java.model.javaclass.parser.sun15.JavaCompilerImpl");
            } catch (Throwable e) {
                //
            }
        }

        if (c == null) {
            try {
                c = Class.forName("org.vpc.neormf.jbgen.java.model.javaclass.parser.sun14.JavaCompilerImpl");
            } catch (Throwable e) {
                //
            }
        }
        try {
            return (JavaCompiler) c.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static JavaParser getJavaParser() {
        String s = System.getProperty("JavaParser");
        Class c = null;
        try {
            c = Class.forName(s);
        } catch (Throwable e) {
            //
        }
        if (c == null) {
            try {
                c = Class.forName("org.vpc.neormf.jbgen.java.model.javaclass.parser.sun15.JavaParserImpl");
            } catch (Throwable e) {
                //
            }
        }

        if (c == null) {
            try {
                c = Class.forName("org.vpc.neormf.jbgen.java.model.javaclass.parser.sun14.JavaParserImpl");
            } catch (Throwable e) {
                //
            }
        }
        try {
            return (JavaParser) c.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


    public JavaClassSource(File file) throws IOException {
        this(toJavaClassSource(file));
    }

//    public JavaClassSource(String file) throws IOException {
//        this(toJavaClassSource(file));
//    }

    public JavaClassSource(InputStream inputStream) throws IOException {
        this(toJavaClassSource(inputStream));
    }

    public void reload(JavaClassSource other) {
        pkg = other.pkg;
        imports.addAll(other.imports);
        comments = other.comments;
        topLevelComments = other.topLevelComments;
        modifiers = other.modifiers;
        classType = other.classType;
        superClass = other.superClass;
        interfaces.addAll(other.interfaces);
        name = other.name;
        fields = new Vector();
        for (Iterator i = other.fields.iterator(); i.hasNext();) {
            JavaField javaField = (JavaField) (((JavaField) i.next()).clone());
            fields.add(javaField);
        }
        methods = new Vector();
        for (Iterator i = other.methods.iterator(); i.hasNext();) {
            JavaMethod javaMethod = (JavaMethod) (((JavaMethod) i.next()).clone());
            methods.add(javaMethod);
        }
        subClasses = new Vector();
        for (Iterator i = other.subClasses.iterator(); i.hasNext();) {
            JavaClassSource javaClassSource = (JavaClassSource) (((JavaClassSource) i.next()).clone());
            subClasses.add(javaClassSource);
        }
//        subClasses = new Vector();
        userCode = new Vector();
    }

    public JavaClassSource(JavaClassSource other) {
        reload(other);
    }

    public String getSourceCode() {
        StringBuilder out = new StringBuilder();
        if (topLevelComments != null) {
            out.append(JavaUtils.toJavaComments(topLevelComments));
        }
        if (pkg != null) {
            out.append("package ").append(pkg).append(";");
            out.append("\n");
        }
        if (imports.size() > 0) {
            for (int i = 0; i < imports.size(); i++) {
                out.append("import ").append(imports.get(i)).append(";");
                out.append("\n");
            }
            out.append("\n");
        }
        if (userCode.size() > 0) {
            boolean added = false;
            for (int i = 0; i < userCode.size(); i++) {
                String code = (String) userCode.get(i);
                StringTokenizer st = new StringTokenizer(code);
                if (st.hasMoreTokens()) {
                    if (!st.nextToken().equals("import")) {
                        continue;
                    }
                }
                if (!added) {
                    out.append(JBGenUtils.indent("/*@user_code:begin*/"));
                    out.append("\n");
                    added = true;
                }
                out.append(JBGenUtils.indent(code));
                out.append("\n");
            }
            if (added) {
                out.append(JBGenUtils.indent("/*@user_code:end*/"));
                out.append("\n");
            }
        }

        if (comments != null) {
            out.append(JavaUtils.toJavaComments(comments));
        }
        if (modifiers != null) {
            out.append(modifiers);
            out.append(" ");
        }
        out.append(classType);
        out.append(" ");
        out.append(name);
        if (superClass != null) {
            out.append(" extends ");
            out.append(superClass);
        }
        if (interfaces != null && interfaces.size() > 0) {
            out.append(" implements ");
            for (int i = 0; i < interfaces.size(); i++) {
                if (i > 0) {
                    out.append(", ");
                }
                out.append(interfaces.get(i));
            }
        }
        out.append("{\n");
        for (int i = 0; i < fields.size(); i++) {
            out.append(JBGenUtils.indent(((JavaField) fields.get(i)).getSourceCode()));
            out.append("\n");
        }
        for (int i = 0; i < methods.size(); i++) {
            out.append(JBGenUtils.indent(((JavaMethod) methods.get(i)).getSourceCode()));
            out.append("\n");
        }
        for (int i = 0; i < subClasses.size(); i++) {
            out.append(JBGenUtils.indent(((JavaClassSource) subClasses.get(i)).getSourceCode()));
            out.append("\n");
        }
        if (userCode.size() > 0) {
            boolean added = false;
            for (int i = 0; i < userCode.size(); i++) {
                String code = (String) userCode.get(i);
                StringTokenizer st = new StringTokenizer(code);
                if (st.hasMoreTokens()) {
                    if (st.nextToken().equals("import")) {
                        continue;
                    }
                }
                if (!added) {
                    out.append(JBGenUtils.indent("/*@user_code:begin*/"));
                    out.append("\n");
                    added = true;
                }
                out.append(JBGenUtils.indent(code));
                out.append("\n");
            }
            if (added) {
                out.append(JBGenUtils.indent("/*@user_code:end*/"));
                out.append("\n");
            }
        }
        out.append("}");
        return out.toString();
    }

    public File getFile() {
        return file;
    }

    public File getFile(File folder) {
        File f = new File(folder,
                pkg == null ? (
                        name + ".java"
                ) :
                        (
                                pkg.replace('.', '/') + '/' + name + ".java"
                        ));
        return f;
    }

    public void loadUserCode(File folder) throws IOException {
        BufferedReader fr = null;
        try {
            File f = getFile(folder);
            file = f;
            if (f.exists()) {
                fr = new BufferedReader(new FileReader(f));
                String line = null;
                StringBuilder code = null;
                while ((line = fr.readLine()) != null) {
                    if ("/*@user_code:begin*/".equals(line.trim())) {
                        if (code != null) {
                            throw new IllegalArgumentException(f.getCanonicalPath() + " : Unexpected token " + line.trim());
                        }
                        code = new StringBuilder();
                    } else if ("/*@user_code:end*/".equals(line.trim())) {
                        if (code == null) {
                            throw new IllegalArgumentException(f.getCanonicalPath() + " : Unexpected token " + line.trim() + ". no /*@user_code:begin*/ encountred");
                        }
                        userCode.add(JBGenUtils.removeIndents(code.toString()));
                        code = null;
                    } else if (code == null) {
                        // nothing
                    } else if (code != null) {//superflux
                        if (code.length() > 0) {
                            code.append("\n");
                        }
                        code.append(line);
                    }
                }
            }
        } finally {
            if (fr != null) {
                fr.close();
            }
        }
    }

    public boolean rewrite(File folder, TLog tlog) throws IOException {
        return write(folder, true, false, tlog);
    }

    public boolean write(File folder, boolean loadUserCode, boolean forceGenerate, TLog tlog) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            loadUserCode(folder);
            File f = getFile(folder);
            setFile(f);
            if (!forceGenerate) {
                if (JBGenUtils.hasContent(f, getSourceCode(), tlog)) {
                    return false;
                }
            }
            f.getParentFile().mkdirs();
            fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(getSourceCode().getBytes());
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return true;
    }

    public void addMethod(JavaMethod method) {
        for (Iterator i = methods.iterator(); i.hasNext();) {
            JavaMethod javaMethod = (JavaMethod) i.next();
            if (javaMethod.getName().equals(method.getName())) {
                JavaParam[] p1 = javaMethod.getParams();
                JavaParam[] p2 = method.getParams();
                if (p1.length == p2.length) {
                    boolean areTheSame = true;
                    for (int j = 0; j < p2.length; j++) {
                        if (!p2[j].getType().equals(p1[j].getType())) {
                            areTheSame = false;
                            break;
                        }
                    }
                    if (areTheSame) {
                        throw new IllegalArgumentException("Method " + method + " already exists in " + getName());
                    }
                }

            }
        }
        methods.add(method);
    }

    public JavaField getField(String fieldName) {
        for (Iterator i = fields.iterator(); i.hasNext();) {
            JavaField javaField = (JavaField) i.next();
            if (javaField.getName().equals(fieldName)) {
                return javaField;
            }
        }
        return null;
    }

    public void addField(JavaField field) {
        fields.add(field);
    }

    public void addProperty(String name, String type, String defaultValue) {
        JavaField f = new JavaField(name, type, null, "private", defaultValue);
        addField(f);
        addMethod(new JavaMethod(JavaUtils.businessGetterName(name, type), type, null, "public", null, null, "return " + name + ";"));
        addMethod(new JavaMethod(JavaUtils.businessSetterName(name), "void", new JavaParam[]{new JavaParam("value", type, null)}, "public", null, null, "this." + name + "=value;"));
    }

    public void addSubClass(JavaClassSource clazz) {
        subClasses.add(clazz);
    }

    public Collection getFields() {
        return (Collection) fields.clone();
    }

    public JavaField getField(int i) {
        return (JavaField) fields.get(i);
    }

    public JavaMethod getMethod(int i) {
        return (JavaMethod) methods.get(i);
    }

    public Collection getMethods() {
        return (Collection) methods.clone();
    }

    public Collection getSubClasses() {
        return (Collection) subClasses.clone();
    }

    public void addImport(String importedPkg) {
        if (!imports.contains(importedPkg)) {
            imports.add(importedPkg);
        }
    }

    public void addAllImports(String[] imports) {
        this.imports.addAll(Arrays.asList(imports));
    }

    public String[] getImports() {
        return (String[]) imports.toArray(new String[imports.size()]);
    }

    public String getPackage() {
        return pkg;
    }

    public void setPackage(String pkg) {
        this.pkg = pkg;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
        if (this.modifiers != null) {
            this.modifiers = this.modifiers.trim();
        }
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String[] getInterfaces() {
        return (String[]) interfaces.toArray(new String[interfaces.size()]);
    }

    public void addInterface(String interfaceName) {
        if (this.interfaces == null) {
            this.interfaces = new Vector();
        }
        this.interfaces.add(interfaceName);
    }

    public void setInterfaces(String[] interfaces) {
        if (interfaces == null) {
            interfaces = new String[0];
        }
        if (this.interfaces == null) {
            this.interfaces = new Vector();
        }
        this.interfaces.clear();
        this.interfaces.addAll(Arrays.asList(interfaces));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector getUserCode() {
        return userCode;
    }

    public void setUserCode(Vector userCode) {
        this.userCode = userCode;
    }

    public String getTopLevelComments() {
        return topLevelComments;
    }

    public void setTopLevelComments(String topLevelComments) {
        this.topLevelComments = topLevelComments;
    }

    public Object clone() {
        return new JavaClassSource(this);
    }

    public void setFile(File file) {
        this.file = file;
    }
}

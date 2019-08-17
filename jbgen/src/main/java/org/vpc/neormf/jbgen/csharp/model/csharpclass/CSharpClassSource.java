package org.vpc.neormf.jbgen.csharp.model.csharpclass;

import org.vpc.neormf.jbgen.util.JBGenUtils;
import org.vpc.neormf.jbgen.util.TLog;
import org.vpc.neormf.jbgen.java.util.JavaUtils;

import java.io.*;
import java.util.*;

/**
 * Csharp Class Source Utility class
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 14:22:50
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class CSharpClassSource implements Cloneable {
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
    private Vector attributes = new Vector();
    private Vector subClasses = new Vector();
    private Vector userCode = new Vector();
    private File file = null;

    public CSharpClassSource() {
    }

//    private static CSharpClassSource toCSharpClassSource(String file) throws IOException {
//        return toCSharpClassSource(new FileInputStream(file));
//    }
//
//    private static CSharpClassSource toCSharpClassSource(File file) throws IOException {
//        return toCSharpClassSource(new FileInputStream(file));
//    }
//
//    private static CSharpClassSource toCSharpClassSource(InputStream inputstream) throws IOException {
//        Context context = new Context();
//        Scanner scanner = new Scanner(context, inputstream, null);
//        inputstream.close();
//        Parser parser = new Parser(context, scanner, true, true);
//        Tree.TopLevel topLevel = parser.compilationUnit();
//        PrintWriter printwriter = new PrintWriter(System.out);
//        JavaClassVisitor classVisitor = new JavaClassVisitor(null, false);
//        topLevel.accept(classVisitor);
//        return classVisitor.getJbgenJavaClassSource();
//    }
//
//    public CSharpClassSource(File file) throws IOException {
//        this(toJavaClassSource(file));
//    }
//
//    public CSharpClassSource(String file) throws IOException {
//        this(toJavaClassSource(file));
//    }
//
//    public CSharpClassSource(InputStream inputStream) throws IOException {
//        this(toJavaClassSource(inputStream));
//    }

    public void reload(CSharpClassSource other) {
        pkg = other.pkg;
        imports.addAll(other.imports);
        comments = other.comments;
        topLevelComments = other.topLevelComments;
        modifiers = other.modifiers;
        classType = other.classType;
        superClass = other.superClass;
        interfaces.addAll(other.interfaces);
        name = other.name;
        fields=new Vector();
        for (Iterator i = other.fields.iterator(); i.hasNext();) {
            CSharpField CSharpField = (CSharpField) (((CSharpField) i.next()).clone());
            fields.add(CSharpField);
        }
        methods=new Vector();
        for (Iterator i = other.methods.iterator(); i.hasNext();) {
            CSharpMethod javaMethod = (CSharpMethod) (((CSharpMethod) i.next()).clone());
            methods.add(javaMethod);
        }
        attributes=new Vector();
        for (Iterator i = other.attributes.iterator(); i.hasNext();) {
            CSharpAttribute javaMethod = (CSharpAttribute) (((CSharpAttribute) i.next()).clone());
            attributes.add(javaMethod);
        }
        subClasses=new Vector();
        for (Iterator i = other.subClasses.iterator(); i.hasNext();) {
            CSharpClassSource javaClassSource = (CSharpClassSource) (((CSharpClassSource) i.next()).clone());
            subClasses.add(javaClassSource);
        }
//        subClasses = new Vector();
        userCode = new Vector();
    }

    public CSharpClassSource(CSharpClassSource other) {
        reload(other);
    }

    public String getSourceCode() {
        StringBuilder out = new StringBuilder();
        if (topLevelComments != null) {
            out.append(JavaUtils.toJavaComments(topLevelComments));
        }
        if (imports.size() > 0) {
            for (int i = 0; i < imports.size(); i++) {
                out.append("using ").append(imports.get(i)).append(";");
                out.append("\n");
            }
            out.append("\n");
        }
        if (pkg != null) {
            out.append("namespace ").append(pkg).append("{");
            out.append("\n");
        }
        if (userCode.size() > 0) {
            boolean added = false;
            for (int i = 0; i < userCode.size(); i++) {
                String code = (String) userCode.get(i);
                StringTokenizer st = new StringTokenizer(code);
                if (st.hasMoreTokens()) {
                    if (!st.nextToken().equals("using")) {
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
            out.append(" : ");
            out.append(superClass);
        }
        if (interfaces != null && interfaces.size() > 0) {
            if(superClass != null){
                out.append(" , ");
            }else{
                out.append(" : ");
            }
            for (int i = 0; i < interfaces.size(); i++) {
                if (i > 0) {
                    out.append(", ");
                }
                out.append(interfaces.get(i));
            }
        }
        out.append("{\n");
        for (int i = 0; i < fields.size(); i++) {
            out.append(JBGenUtils.indent(((CSharpField) fields.get(i)).getSourceCode()));
            out.append("\n");
        }
        for (int i = 0; i < attributes.size(); i++) {
            out.append(JBGenUtils.indent(((CSharpAttribute) attributes.get(i)).getSourceCode()));
            out.append("\n");
        }
        for (int i = 0; i < methods.size(); i++) {
            out.append(JBGenUtils.indent(((CSharpMethod) methods.get(i)).getSourceCode()));
            out.append("\n");
        }
        for (int i = 0; i < subClasses.size(); i++) {
            out.append(JBGenUtils.indent(((CSharpClassSource) subClasses.get(i)).getSourceCode()));
            out.append("\n");
        }
        if (userCode.size() > 0) {
            boolean added = false;
            for (int i = 0; i < userCode.size(); i++) {
                String code = (String) userCode.get(i);
                StringTokenizer st = new StringTokenizer(code);
                if (st.hasMoreTokens()) {
                    if (st.nextToken().equals("using")) {
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
        if (pkg != null) {
            out.append("\n}");
            out.append("\n");
        }
        return out.toString();
    }

    public File getFile() {
        return file;
    }

    public File getFile(File folder) {
        File f = new File(folder,
                pkg == null ? (
                name + ".cs"
                ) :
                (
                pkg.replace('.', '/') + '/' + name + ".cs"
                ));
        return f;
    }

    public void loadUserCode(File folder) throws IOException {
        BufferedReader fr = null;
        try {
            File f = getFile(folder);
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

    public boolean rewrite(File folder,TLog tlog) throws IOException {
        return write(folder, true, false,tlog);
    }

    public boolean write(File folder, boolean loadUserCode, boolean forceGenerate, TLog tlog) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            loadUserCode(folder);
            File f = getFile(folder);
            setFile(f);
            if (!forceGenerate) {
                if (JBGenUtils.hasContent(f, getSourceCode(),tlog)) {
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

    public void addMethod(CSharpMethod method) {
        for (Iterator i = methods.iterator(); i.hasNext();) {
            CSharpMethod javaMethod = (CSharpMethod) i.next();
            if (javaMethod.getName().equals(method.getName())) {
                CSharpParam[] p1 = javaMethod.getParams();
                CSharpParam[] p2 = method.getParams();
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

    public void addAttribute(CSharpAttribute attribute) {
        for (Iterator i = attributes.iterator(); i.hasNext();) {
            CSharpAttribute javaMethod = (CSharpAttribute) i.next();
            if (javaMethod.getName().equals(attribute.getName())) {
                        throw new IllegalArgumentException("Attribute " + attribute.getName() + " already exists in " + getName());
            }
        }
        attributes.add(attribute);
    }

    public CSharpField getField(String fieldName) {
        for (Iterator i = fields.iterator(); i.hasNext();) {
            CSharpField CSharpField = (CSharpField) i.next();
            if (CSharpField.getName().equals(fieldName)) {
                return CSharpField;
            }
        }
        return null;
    }

    public void addField(CSharpField field) {
        fields.add(field);
    }

    public void addSubClass(CSharpClassSource clazz) {
        subClasses.add(clazz);
    }

    public Collection getFields() {
        return (Collection) fields.clone();
    }

    public CSharpField getField(int i) {
        return (CSharpField) fields.get(i);
    }

    public CSharpMethod getMethod(int i) {
        return (CSharpMethod) methods.get(i);
    }

    public Collection getMethods() {
        return (Collection) methods.clone();
    }

    public CSharpAttribute getAttribute(int i) {
        return (CSharpAttribute) attributes.get(i);
    }

    public Collection getAttributes() {
        return (Collection) attributes.clone();
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

    public String getFQName() {
        String p=getPackage();
        return (p==null || p.length()==0)?getName():(p+"."+getName());
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
        return new CSharpClassSource(this);
    }

    public void setFile(File file) {
        this.file = file;
    }
}

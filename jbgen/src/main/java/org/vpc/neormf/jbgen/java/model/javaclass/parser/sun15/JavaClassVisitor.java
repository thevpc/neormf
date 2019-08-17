package org.vpc.neormf.jbgen.java.model.javaclass.parser.sun15;

import com.sun.tools.javac.tree.Tree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaClassSource;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaMethod;
import org.vpc.neormf.jbgen.java.model.javaclass.JavaParam;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 7 juil. 2004 Time: 10:39:14
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JavaClassVisitor extends Tree.Visitor {
    private JavaClassSource jbgenJavaClassSource = new JavaClassSource();
    private final boolean sourceOutput;
    PrintWriter out;
    public int width;
    int lmargin, prec;
    Name enclClassName;
    Map docComments;

    void align() {
        for (int i = 0; i < lmargin; i++) {
            if (out != null) {
                out.print(" ");
            }
        }

    }

    void indent() {
        lmargin = lmargin + width;
    }

    public void println() {
        if (out != null) {
            out.println();
        }
    }

    void undent() {
        lmargin = lmargin - width;
    }

    void close(int i, int j) {
        if (j < i) {
            if (out != null) {
                out.print(")");
            }
        }
    }

    void open(int i, int j) {
        if (j < i) {
            if (out != null) {
                out.print("(");
            }
        }
    }

    public void printFlags(long l) {
        if ((l & 65536L) != 0L) {
            print("/*synthetic*/ ");
        }
        print(TreeInfo.flagNames(l));
        if ((l & 4095L) != 0L) {
            print(" ");
        }
    }

    public String getPrintFlags(long l) {
        StringBuilder sb = new StringBuilder();
        if ((l & 65536L) != 0L) {
            sb.append(getPrint("/*synthetic*/ "));
        }
        sb.append(TreeInfo.flagNames(l));
        if ((l & 4095L) != 0L) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public void printDocComment(Tree tree) {
        if (docComments != null) {
            String s = (String) docComments.get(tree);
            if (s != null) {
                print("/**");
                println();
                int i = 0;
                for (int j = JavaClassVisitor.lineEndPos(s, i); i < s.length(); j = JavaClassVisitor.lineEndPos(s, i)) {
                    align();
                    print(" *");
                    if (i < s.length() && s.charAt(i) > ' ') {
                        print(" ");
                    }
                    print(s.substring(i, j));
                    println();
                    i = j + 1;
                }

                align();
                print(" */");
                println();
                align();
            }
        }
    }

    public void printExpr(Tree tree) {
        printExpr(tree, 0);
    }

    public void printStat(Tree tree) {
        printExpr(tree, -1);
    }

    public void visitTree(Tree tree) {
        print("(UNKNOWN: " + tree + ")");
        println();
    }

    public void printExpr(Tree tree, int i) {
        int j = prec;
        prec = i;
        if (tree == null) {
            print("/*missing*/");
        } else {
            tree.accept(this);
        }
        prec = j;
//        break MISSING_BLOCK_LABEL_46;
//        Exception exception;
//        exception;
//        prec = j;
//        throw exception;
    }

    public void visitApply(Tree.Apply apply) {
        printExpr(apply.meth);
        print("(");
        printExprs(apply.args);
        print(")");
    }

    public void visitAssert(Tree.Assert assert00) {
        print("assert ");
        printExpr(assert00.cond);
        if (assert00.detail != null) {
            print(" : ");
            printExpr(assert00.detail);
        }
        print(";");
    }

    public void visitAssign(Tree.Assign assign) {
        open(prec, 1);
        printExpr(assign.lhs, 2);
        print(" = ");
        printExpr(assign.rhs, 1);
        close(prec, 1);
    }

    public void visitAssignop(Tree.Assignop assignop) {
        open(prec, 2);
        printExpr(assignop.lhs, 3);
        print(" " + operatorName(assignop.tag - 17) + "= ");
        printExpr(assignop.rhs, 2);
        close(prec, 2);
    }

    public void visitBinary(Tree.Binary binary) {
        int i = TreeInfo.opPrec(binary.tag);
        String s = operatorName(binary.tag).toString();
        open(prec, i);
        printExpr(binary.lhs, i);
        print(" " + s + " ");
        printExpr(binary.rhs, i + 1);
        close(prec, i);
    }

    public void visitBlock(Tree.Block block) {
        printFlags(block.flags);
        printBlock(block.stats);
    }

    public void visitBreak(Tree.Break break1) {
        print("break");
        if (break1.label != null) {
            print(" " + break1.label);
        }
        print(";");
    }

    public void visitCase(Tree.Case case1) {
        if (case1.pat == null) {
            print("default");
        } else {
            print("case ");
            printExpr(case1.pat);
        }
        print(": ");
        println();
        indent();
        printStats(case1.stats);
        undent();
        align();
    }

    public void visitCatch(Tree.Catch catch1) {
        print(" catch (");
        printExpr(catch1.param);
        print(") ");
        printStat(catch1.body);
    }

    public void visitClassDef(Tree.ClassDef classdef) {
        println();
        align();
        jbgenJavaClassSource.setComments((String) docComments.get(classdef));
        printDocComment(classdef);
        printFlags(classdef.mods.flags & -513L);
        jbgenJavaClassSource.setModifiers(getPrintFlags(classdef.mods.flags & -513L));
        Name name = enclClassName;
        enclClassName = classdef.name;
        if ((classdef.mods.flags & 512L) != 0L) {
            print("interface " + classdef.name);
            if (classdef.implementing.nonEmpty()) {
                print(" extends ");
                printExprs(classdef.implementing);
            }
        } else {
            print("class " + classdef.name);
            /*-JBGEN-*/
            jbgenJavaClassSource.setName(classdef.name.toString());
            if (classdef.extending != null) {
                print(" extends ");
                printExpr(classdef.extending);
                /*-JBGEN-*/
                jbgenJavaClassSource.setSuperClass(classdef.extending.toString());
            }
            if (classdef.implementing.nonEmpty()) {
                print(" implements ");

                /*-JBGEN-*/
                List list = classdef.implementing;
                if (list.nonEmpty()) {
                    jbgenJavaClassSource.addInterface(list.head.toString());
                    for (List list1 = list.tail; list1.nonEmpty(); list1 = list1.tail) {
                        jbgenJavaClassSource.addInterface(list1.head.toString());
                    }
                }
                /*-JBGEN-*/
                printExprs(classdef.implementing);
            }
        }
        print(" ");
        printBlock(classdef.defs);
        enclClassName = name;
    }

    public void visitConditional(Tree.Conditional conditional) {
        open(prec, 3);
        printExpr(conditional.cond, 3);
        print(" ? ");
        printExpr(conditional.truepart, 3);
        print(" : ");
        printExpr(conditional.falsepart, 3);
        close(prec, 3);
    }

    public void visitContinue(Tree.Continue continue1) {
        print("continue");
        if (continue1.label != null) {
            print(" " + continue1.label);
        }
        print(";");
    }

    public void visitDoLoop(Tree.DoLoop doloop) {
        print("do ");
        printStat(doloop.body);
        align();
        print(" while ");
        if (doloop.cond.tag == 28) {
            printExpr(doloop.cond);
        } else {
            print("(");
            printExpr(doloop.cond);
            print(")");
        }
        print(";");
    }

    public void visitErroneous(Tree.Erroneous erroneous) {
        print("(ERROR)");
    }

    public void visitExec(Tree.Exec exec) {
        printExpr(exec.expr);
        if (prec == -1) {
            print(";");
        }
    }

    public void visitForLoop(Tree.ForLoop forloop) {
        print("for (");
        if (forloop.init.nonEmpty()) {
            if (((Tree) forloop.init.head).tag == 5) {
                printExpr((Tree) forloop.init.head);
                for (List list = forloop.init.tail; list.nonEmpty(); list = list.tail) {
                    Tree.VarDef vardef = (Tree.VarDef) list.head;
                    print(", " + vardef.name + " = ");
                    printExpr(vardef.init);
                }

            } else {
                printExprs(forloop.init);
            }
        }
        print("; ");
        if (forloop.cond != null) {
            printExpr(forloop.cond);
        }
        print("; ");
        printExprs(forloop.step);
        print(") ");
        printStat(forloop.body);
    }

    public void visitIdent(Tree.Ident ident) {
        print(ident.name.toString());
    }

    public void visitIf(Tree.If if1) {
        print("if ");
        if (if1.cond.tag == 28) {
            printExpr(if1.cond);
        } else {
            print("(");
            printExpr(if1.cond);
            print(")");
        }
        print(" ");
        printStat(if1.thenpart);
        if (if1.elsepart != null) {
            print(" else ");
            printStat(if1.elsepart);
        }
    }

    public void visitImport(Tree.Import import1) {
        jbgenJavaClassSource.addImport(import1.qualid.toString());
        print("import ");
        printExpr(import1.qualid);
        print(";");
        println();
    }

    public void visitIndexed(Tree.Indexed indexed) {
        printExpr(indexed.indexed, 15);
        print("[");
        printExpr(indexed.index);
        print("]");
    }

    public void visitLabelled(Tree.Labelled labelled) {
        print(labelled.label + ": ");
        printStat(labelled.body);
    }

    public void visitLiteral(Tree.Literal literal) {
        switch (literal.typetag) {
            case 4: // '\004'
                print(literal.value.toString());
                break;

            case 5: // '\005'
                print(literal.value.toString() + "L");
                break;

            case 6: // '\006'
                print(literal.value.toString() + "F");
                break;

            case 7: // '\007'
                print(literal.value.toString());
                break;

            case 2: // '\002'
                print("'" + Convert.quote(String.valueOf((char) ((Number) literal.value).intValue())) + "'");
                break;

            case 10: // '\n'
                print("\"" + Convert.quote((String) literal.value) + "\"");
                break;

            case 3: // '\003'
            case 8: // '\b'
            case 9: // '\t'
            default:
                print(literal.value.toString());
                break;
        }
    }

    public void visitMethodDef(Tree.MethodDef methoddef) {
        if (methoddef.name == methoddef.name.table.init && enclClassName == null && sourceOutput) {
            return;
        }
        println();
        align();
        /*-JBGEN-*/
        JavaMethod jbgenJavaMethod = new JavaMethod();
        jbgenJavaClassSource.addMethod(jbgenJavaMethod);
        /*-JBGEN-*/
        jbgenJavaMethod.setComments((String) docComments.get(methoddef));
        printDocComment(methoddef);
        jbgenJavaMethod.setModifiers(getPrintFlags(methoddef.mods.flags));
        printFlags(methoddef.mods.flags);
        if (methoddef.name == methoddef.name.table.init) {
            /*-JBGEN-*/
            jbgenJavaMethod.setName(enclClassName == null ? methoddef.name.toString() : enclClassName.toString());
            print(enclClassName == null ? methoddef.name.toString() : enclClassName.toString());
        } else {
            /*-JBGEN-*/
            jbgenJavaMethod.setType(methoddef.restype.toString());
            /*-JBGEN-*/
            jbgenJavaMethod.setName(methoddef.name.toString());
            printExpr(methoddef.restype);
            print(" " + methoddef.name);
        }
        print("(");
        Object[] varsArray = toArray(methoddef.params);
        for (int i = 0; i < varsArray.length; i++) {
            Tree.VarDef varDef = (Tree.VarDef) varsArray[i];
            jbgenJavaMethod.addParam(new JavaParam(varDef.name.toString(),
                    varDef.vartype.toString(),
                    (String) docComments.get(varDef)));
        }

        printExprs(methoddef.params);
        print(")");
        if (methoddef.thrown.nonEmpty()) {
            print(" throws ");
            varsArray = toArray(methoddef.thrown);
            for (int i = 0; i < varsArray.length; i++) {
                jbgenJavaMethod.addException(varsArray[i].toString());
            }
            printExprs(methoddef.thrown);
        }
        if (methoddef.body != null) {
            print(" ");

            StringBuilder corps = new StringBuilder();
            corps.append(methoddef.body.toString());
            corps.deleteCharAt(0);
            corps.deleteCharAt(corps.length() - 1);
            jbgenJavaMethod.setBody(corps.toString());
            printStat(methoddef.body);
        } else {
            print(";");
        }
    }

    private Object[] toArray(List list) {
        java.util.ArrayList arrayList = new ArrayList();
        if (list.nonEmpty()) {
            arrayList.add(list.head);
            for (List list1 = list.tail; list1.nonEmpty(); list1 = list1.tail) {
                arrayList.add(list1.head);
            }
        }
        return arrayList.toArray();
    }

    public void visitNewArray(Tree.NewArray newarray) {
        if (newarray.elemtype != null) {
            print("new ");
            int i = 0;
            Tree tree;
            for (tree = newarray.elemtype; tree.tag == 37; tree = ((Tree.TypeArray) tree).elemtype) {
                i++;
            }

            printExpr(tree);
            for (List list = newarray.dims; list.nonEmpty(); list = list.tail) {
                print("[");
                printExpr((Tree) list.head);
                print("]");
            }

            for (int j = 0; j < i; j++) {
                print("[]");
            }

            if (newarray.elems != null) {
                print("[]");
            }
        }
        if (newarray.elems != null) {
            print("{");
            printExprs(newarray.elems);
            print("}");
        }
    }

    public void visitNewClass(Tree.NewClass newclass) {
        if (newclass.encl != null) {
            printExpr(newclass.encl);
            print(".");
        }
        print("new ");
        printExpr(newclass.clazz);
        print("(");
        printExprs(newclass.args);
        print(")");
        if (newclass.def != null) {
            Name name = enclClassName;
            enclClassName = null;
            printBlock(newclass.def.defs);
            enclClassName = name;
        }
    }

    public void visitParens(Tree.Parens parens) {
        print("(");
        printExpr(parens.expr);
        print(")");
    }

    public void visitReturn(Tree.Return return1) {
        print("return");
        if (return1.expr != null) {
            print(" ");
            printExpr(return1.expr);
        }
        print(";");
    }

    public void visitSelect(Tree.Select select) {
        printExpr(select.selected, 15);
        print("." + select.name);
    }

    public void visitSkip(Tree.Skip skip) {
        print(";");
    }

    public void visitSwitch(Tree.Switch switch1) {
        print("switch ");
        if (switch1.selector.tag == 28) {
            printExpr(switch1.selector);
        } else {
            print("(");
            printExpr(switch1.selector);
            print(")");
        }
        print(" {");
        println();
        printStats(switch1.cases);
        align();
        print("}");
    }

    public void visitSynchronized(Tree.Synchronized synchronized1) {
        print("synchronized ");
        if (synchronized1.lock.tag == 28) {
            printExpr(synchronized1.lock);
        } else {
            print("(");
            printExpr(synchronized1.lock);
            print(")");
        }
        print(" ");
        printStat(synchronized1.body);
    }

    public void visitThrow(Tree.Throw throw1) {
        print("throw ");
        printExpr(throw1.expr);
        print(";");
    }

    public void visitTopLevel(Tree.TopLevel toplevel) {
        printUnit(toplevel, null);
    }

    public void visitTry(Tree.Try try1) {
        print("try ");
        printStat(try1.body);
        for (List list = try1.catchers; list.nonEmpty(); list = list.tail) {
            printStat((Tree) list.head);
        }

        if (try1.finalizer != null) {
            print(" finally ");
            printStat(try1.finalizer);
        }
    }

    public void visitTypeArray(Tree.TypeArray typearray) {
        printExpr(typearray.elemtype);
        print("[]");
    }

    public void visitTypeCast(Tree.TypeCast typecast) {
        open(prec, 14);
        print("(");
        printExpr(typecast.clazz);
        print(")");
        printExpr(typecast.expr, 14);
        close(prec, 14);
    }

    public void visitTypeIdent(Tree.TypeIdent typeident) {
        switch (typeident.typetag) {
            case 1: // '\001'
                print("byte");
                break;

            case 2: // '\002'
                print("char");
                break;

            case 3: // '\003'
                print("short");
                break;

            case 4: // '\004'
                print("int");
                break;

            case 5: // '\005'
                print("long");
                break;

            case 6: // '\006'
                print("float");
                break;

            case 7: // '\007'
                print("double");
                break;

            case 8: // '\b'
                print("boolean");
                break;

            case 9: // '\t'
                print("void");
                break;

            default:
                print("error");
                break;
        }
    }

    public void visitTypeTest(Tree.TypeTest typetest) {
        open(prec, 10);
        printExpr(typetest.expr, 10);
        print(" instanceof ");
        printExpr(typetest.clazz, 11);
        close(prec, 10);
    }

    public void visitUnary(Tree.Unary unary) {
        int i = TreeInfo.opPrec(unary.tag);
        String s = operatorName(unary.tag).toString();
        open(prec, i);
        if (unary.tag <= 46) {
            print(s);
            printExpr(unary.arg, i);
        } else {
            printExpr(unary.arg, i);
            print(s);
        }
        close(prec, i);
    }

    public void visitVarDef(Tree.VarDef vardef) {
        if (docComments != null && docComments.get(vardef) != null) {
            println();
            align();
        }
        printDocComment(vardef);
        printFlags(vardef.mods.flags);
        printExpr(vardef.vartype);
        print(" " + vardef.name);
        if (vardef.init != null) {
            print(" = ");
            printExpr(vardef.init);
        }
        if (prec == -1) {
            print(";");
        }
    }

    public void visitWhileLoop(Tree.WhileLoop whileloop) {
        print("while ");
        if (whileloop.cond.tag == 28) {
            printExpr(whileloop.cond);
        } else {
            print("(");
            printExpr(whileloop.cond);
            print(")");
        }
        print(" ");
        printStat(whileloop.body);
    }

    public void printBlock(List list) {
        print("{");
        println();
        indent();
        printStats(list);
        undent();
        align();
        print("}");
    }

    public void printExprs(List list) {
        printExprs(list, ", ");
    }

    public void printStats(List list) {
        for (List list1 = list; list1.nonEmpty(); list1 = list1.tail) {
            align();
            printStat((Tree) list1.head);
            println();
        }

    }

    public String getPrintExprs(List list) {
        return getPrintExprs(list, ", ");
    }

    public String getPrintExprs(List list, String s) {
        StringBuilder sb = new StringBuilder();
        if (list.nonEmpty()) {
            sb.append(((Tree) list.head).toString());
            for (List list1 = list.tail; list1.nonEmpty(); list1 = list1.tail) {
                sb.append(s);
                sb.append((Tree) list1.head);
            }
        }
        return sb.toString();
    }

    public JavaClassVisitor(PrintWriter printwriter, boolean flag) {
        width = 4;
        lmargin = 0;
        docComments = null;
        out = printwriter;
        sourceOutput = flag;
    }

    public String operatorName(int i) {
        switch (i) {
            case Tree.POS: // ')'
                return "+";

            case Tree.NEG: // '*'
                return "-";

            case Tree.NOT: // '+'
                return "!";

            case Tree.COMPL: // ','
                return "~";

            case Tree.PREINC: // '-'
                return "++";

            case Tree.PREDEC: // '.'
                return "--";

            case Tree.POSTINC: // '/'
                return "++";

            case Tree.POSTDEC: // '0'
                return "--";

            case Tree.NULLCHK: // '1'
                return "<*nullchk*>";

            case Tree.OR: // '2'
                return "||";

            case Tree.AND: // '3'
                return "&&";

            case Tree.EQ: // '7'
                return "==";

            case Tree.NE: // '8'
                return "!=";

            case Tree.LT: // '9'
                return "<";

            case Tree.GT: // ':'
                return ">";

            case Tree.LE: // ';'
                return "<=";

            case Tree.GE: // '<'
                return ">=";

            case Tree.BITOR: // '4'
                return "|";

            case Tree.BITXOR: // '5'
                return "^";

            case Tree.BITAND: // '6'
                return "&";

            case Tree.SL: // '='
                return "<<";

            case Tree.SR: // '>'
                return ">>";

            case Tree.USR: // '?'
                return ">>>";

            case Tree.PLUS: // '@'
                return "+";

            case Tree.MINUS: // 'A'
                return "-";

            case Tree.MUL: // 'B'
                return "*";

            case Tree.DIV: // 'C'
                return "/";

            case Tree.MOD: // 'D'
                return "%";
        }
        throw new Error();
    }

    public void print(String s) {
        if (out != null) {
            out.print(Convert.escapeUnicode(s));
        }
    }

    public String getPrint(String s) {
        return Convert.escapeUnicode(s);
    }

    static int lineEndPos(String s, int i) {
        int j = s.indexOf('\n', i);
        if (j < 0) {
            j = s.length();
        }
        return j;
    }

    public void printUnit(Tree.TopLevel toplevel, Tree.ClassDef classdef) {
        docComments = toplevel.docComments;
        jbgenJavaClassSource.setComments((String) docComments.get(toplevel));
        printDocComment(toplevel);
        if (toplevel.pid != null) {
            jbgenJavaClassSource.setPackage(toplevel.pid.toString());
            print("package ");
            printExpr(toplevel.pid);
            print(";");
            println();
        }
        for (List list = toplevel.defs; list.nonEmpty() && (classdef == null || ((Tree) list.head).tag == 2); list = list.tail)
        {
            printStat((Tree) list.head);
            println();
        }

        if (classdef != null) {
            printStat(classdef);
            println();
        }
    }

    public void printExprs(List list, String s) {
        if (list.nonEmpty()) {
            printExpr((Tree) list.head);
            for (List list1 = list.tail; list1.nonEmpty(); list1 = list1.tail) {
                print(s);
                printExpr((Tree) list1.head);
            }

        }
    }

    public JavaClassSource getJbgenJavaClassSource() {
        return jbgenJavaClassSource;
    }

}

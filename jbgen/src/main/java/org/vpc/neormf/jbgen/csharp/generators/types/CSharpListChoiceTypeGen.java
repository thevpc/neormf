package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.ListChoiceType;
import org.vpc.neormf.commons.types.AbstractChoiceType;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 août 2005
 * Time: 14:55:47
 * To change this template use File | Settings | File Templates.
 */
public class CSharpListChoiceTypeGen extends CSharpAbstractChoiceTypeGen {
    public String toCode(DataType type) {
        ListChoiceType him = (ListChoiceType) type;
        StringBuilder sb = new StringBuilder();
        sb.append("new ").append(him.getClass().getName()).append("(").append(him.isNullable());
        sb.append(",new Object[]{");
        boolean first = true;
        for (Iterator iterator = him.elements().iterator(); iterator.hasNext();) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            AbstractChoiceType.Element elem = (AbstractChoiceType.Element) iterator.next();
            Object o = elem.getKey();
            if (o == null) {
                sb.append("null");
            } else if (o instanceof String) {
                sb.append("\"");
                sb.append(o);
                sb.append("\"");
            } else if (o instanceof java.util.Date) {
                sb.append("new java.util.Date(");
                sb.append(((java.util.Date) o).getTime()).append("L");
                sb.append(")");
            } else if (o instanceof Integer) {
                sb.append("new Integer(");
                sb.append(o);
                sb.append(")");
            } else if (o instanceof Long) {
                sb.append("new Long(");
                sb.append(o).append("L");
                sb.append(")");
            } else if (o instanceof Double) {
                sb.append("new Double(");
                sb.append(o);
                sb.append(")");
            } else if (o instanceof Float) {
                sb.append("new Float(");
                sb.append(o).append("F");
                sb.append(")");
            } else {
                throw new IllegalArgumentException("Unknown Type " + o.getClass());
            }
        }
        sb.append("},new Object[]{");
        first = true;
        for (Iterator iterator = him.elements().iterator(); iterator.hasNext();) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            AbstractChoiceType.Element elem = (AbstractChoiceType.Element) iterator.next();
            Object o = elem.getRenderer();
            if (o == null) {
                sb.append("null");
            } else if (o instanceof String) {
                sb.append("\"");
                sb.append(o);
                sb.append("\"");
            } else if (o instanceof java.util.Date) {
                sb.append("new java.util.Date(");
                sb.append(((java.util.Date) o).getTime()).append("L");
                sb.append(")");
            } else if (o instanceof Integer) {
                sb.append("new Integer(");
                sb.append(o);
                sb.append(")");
            } else if (o instanceof Long) {
                sb.append("new Long(");
                sb.append(o).append("L");
                sb.append(")");
            } else if (o instanceof Double) {
                sb.append("new Double(");
                sb.append(o);
                sb.append(")");
            } else if (o instanceof Float) {
                sb.append("new Float(");
                sb.append(o).append("F");
                sb.append(")");
            } else {
                throw new IllegalArgumentException("Unknown Type " + o.getClass());
            }
        }
        sb.append("},");
        sb.append(CSharpDataTypeGenManager.getDatatypeGenerator(him.getElementType().getClass()).toCode(him.getElementType()));
        sb.append(")");
        return sb.toString();
    }
}

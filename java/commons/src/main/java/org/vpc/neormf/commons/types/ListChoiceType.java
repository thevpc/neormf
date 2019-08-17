package org.vpc.neormf.commons.types;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 août 2005
 * Time: 14:55:47
 * To change this template use File | Settings | File Templates.
 */
public class ListChoiceType extends AbstractChoiceType {
    private List elements;
    private List renderers;
    private List mappedElements;
    public ListChoiceType(boolean nullable, Object[] elements, Object[] renderers,DataType elementType) {
        this(nullable, Arrays.asList(elements), Arrays.asList(renderers), elementType);
    }

    public ListChoiceType(boolean nullable, Object[] elements, DataType elementType) {
        this(nullable, Arrays.asList(elements), Arrays.asList(elements), elementType);
    }

    public ListChoiceType(boolean nullable, List elements, DataType elementType) {
        this(nullable, elements,elements,elementType);
    }
    
    public ListChoiceType(boolean nullable, List elements, List renderers, DataType elementType) {
        super(nullable,elementType);
        this.elements = elements;
        this.renderers = renderers;
        mappedElements=new ArrayList();
        for (int i = 0; i < elements.size(); i++) {
            Object o = elements.get(i);
            Element e=new Element(o,String.valueOf(this.renderers.get(i)));
            mappedElements.add(e);
        }
    }

    public Object validateValue(Object object, String fieldName) throws DataException {
        object = super.validateValue(object, fieldName);
        if (object != null) {
            if (!elements.contains(object)) {
                object = null;
            }
        }
        return object;
    }

    public String getKeyRenderer(Object value) {
        return null;
    }

    public List elements() {
        return mappedElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ListChoiceType that = (ListChoiceType) o;

        if (elements != null ? !elements.equals(that.elements) : that.elements != null) return false;
        if (mappedElements != null ? !mappedElements.equals(that.mappedElements) : that.mappedElements != null)
            return false;
        if (renderers != null ? !renderers.equals(that.renderers) : that.renderers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        result = 31 * result + (renderers != null ? renderers.hashCode() : 0);
        result = 31 * result + (mappedElements != null ? mappedElements.hashCode() : 0);
        return result;
    }
}

package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.wws.webform.DataTypeHtmlSupport;
import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlLabel;
import org.vpc.neormf.wws.html.HtmlErrorDecoration;

import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 18:20:49
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultDataTypeHtmlSupport implements DataTypeHtmlSupport {
    private Hashtable extraProperties;


    public String[] getParameterNames(String basicName, DataType type, HttpServletRequest request) {
        return new String[]{basicName};
    }

    public String[] convertObjectToHtmlStringArray(Object value, DataType type, HttpServletRequest request) {
        return new String[]{convertObjectToHtmlString(value, type, request)};
    }

    protected abstract HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request);

    public HtmlWidget generateHtmlValue(String name, Object value, DataType type, HttpServletRequest request) {
        return new HtmlLabel(convertObjectToHtmlString(value, type, request));
    }

    public HtmlWidget generateHtmlEditor(String name, String[] value, boolean isCorrect, DataType type, HttpServletRequest request) {
        HtmlWidget v = generateHtmlEditor(name, value, type, request);
        if (isCorrect) {
            return v;
        } else {
            return new HtmlErrorDecoration(v);
        }
    }

    public void setExtraProperty(String property, Object value, HttpServletRequest request) {
        if (value == null) {
            if (request != null) {
                Hashtable map = (Hashtable) request.getAttribute(getClass().getName() + ".ExtraProperties");
                if (map != null) {
                    map.put(property, value);
                }
            } else {
                if (extraProperties != null) {
                    extraProperties.remove(property);
                }
            }
        } else {
            if (request != null) {
                Hashtable map = (Hashtable) request.getAttribute(getClass().getName() + ".ExtraProperties");
                if (map == null) {
                    map = new Hashtable();
                    request.setAttribute(getClass().getName() + ".ExtraProperties", map);
                }
                map.put(property, value);
            } else {
                if (extraProperties == null) {
                    extraProperties = new Hashtable();
                }
                extraProperties.put(property, value);
            }
        }
    }

    public Object getExtraProperty(String property, HttpServletRequest request) {
        if (request != null) {
            Hashtable map = (Hashtable) request.getAttribute(getClass().getName() + ".ExtraProperties");
            if (map != null) {
                Object o=map.get(property);
                if(o!=null){
                    return o;
                }
            }
            return WUtils.resolveObjectByName(request,getClass().getName() + ".ExtraProperties."+property,null);
        } else if (extraProperties != null) {
            return extraProperties.get(property);
        }
        return null;
    }

    public Hashtable getExtraProperties(HttpServletRequest request) {
        Hashtable props = new Hashtable();
        if (extraProperties != null) {
            props.putAll(extraProperties);
        }
        if (request != null) {
            Hashtable map = (Hashtable) request.getAttribute(getClass().getName() + ".ExtraProperties");
            if (map != null) {
                props.putAll(map);
            }
        }
        return props;
    }
}

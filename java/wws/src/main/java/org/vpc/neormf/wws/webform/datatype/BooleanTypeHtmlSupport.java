package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlCheckbox;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class BooleanTypeHtmlSupport extends DefaultDataTypeHtmlSupport {
    public String convertObjectToHtmlString(Object v, DataType type, HttpServletRequest request) {
        return Boolean.TRUE.equals(v)?"on" : "";
    }

    public Object convertHtmlStringToObject(String[] s, DataType type, HttpServletRequest request) {
        return s[0] == null ? null : "on".equals(s[0]) ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean convertHtmlStringToBolean(String[] s, DataType type, HttpServletRequest request,boolean defaultValue) {
        Boolean aBoolean = ((Boolean) convertHtmlStringToObject(s, type, request));
        return aBoolean==null?defaultValue:aBoolean.booleanValue();
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        HtmlCheckbox htmlCheckbox=new HtmlCheckbox();
        htmlCheckbox.setName(name);
        htmlCheckbox.setChecked((value[0] != null && "on".equals(value[0])));
        htmlCheckbox.setDisabled(false);
        return htmlCheckbox;
    }

    public HtmlWidget generateHtmlValue(String name, Object value, DataType type, HttpServletRequest request) {
        HtmlCheckbox htmlCheckbox=new HtmlCheckbox();
        htmlCheckbox.setName(name);
        htmlCheckbox.setChecked((value != null && value.equals("on")));
        htmlCheckbox.setDisabled(true);
        return htmlCheckbox;
    }
}

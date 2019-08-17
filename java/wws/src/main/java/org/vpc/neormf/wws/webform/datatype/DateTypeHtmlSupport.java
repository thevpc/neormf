package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.DateType;
import org.vpc.neormf.commons.types.DataException;
import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlText;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;


/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class DateTypeHtmlSupport extends DefaultDataTypeHtmlSupport {
    public String convertObjectToHtmlString(Object v, DataType type, HttpServletRequest request) {
        return v==null?"":WUtils.getDateFormat(request).format((java.util.Date)v);
    }

    public Object convertHtmlStringToObject(String[] s, DataType type, HttpServletRequest request) {
        try {
            return (s[0]==null || s[0].length()==0) ? null :
                    ((java.util.Date)(DateType.DATE_NULLABLE.validateValue(new java.sql.Date(WUtils.getDateFormat(request).parse(s[0]).getTime()),null)))
            ;
        } catch (ParseException e) {
            throw new DataException("org.vpc.neormf.commons.types.DateType.BadFormat.Message",new Object[]{s[0]});
        }
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        HtmlText htmlText = new HtmlText();
        htmlText.setName(name);
        htmlText.setValue(value[0]);
        try {
            htmlText.setSize(Integer.parseInt((String) getExtraProperty("text.size", request)));
        } catch (Exception e) {
        }
        try {
            htmlText.setMaxLength(Integer.parseInt((String) getExtraProperty("text.maxlength", request)));
        } catch (Exception e) {
        }
        
        return htmlText;
//        String cols = (String) getExtraProperty("text.size", request);
//        if (cols != null) {
//            cols = "size=" + cols;
//        } else {
//            cols = "";
//        }
//        return "<input name='"+name+"' "+cols+" type='text' value=" +  WUtils.addHtmlCotes(WUtils.toHtml(request,value))+ ">";
    }
}

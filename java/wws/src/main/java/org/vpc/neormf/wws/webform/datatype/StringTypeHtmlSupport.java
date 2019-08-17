package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.StringType;
import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlText;
import org.vpc.neormf.wws.html.HtmlTextArea;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class StringTypeHtmlSupport extends DefaultDataTypeHtmlSupport {
    public StringTypeHtmlSupport() {
    }

    public String convertObjectToHtmlString(Object v, DataType type, HttpServletRequest request) {
        return v == null ? "" : v.toString();
    }

    public Object convertHtmlStringToObject(String[] s, DataType type, HttpServletRequest request) {
        return (s[0]==null || s[0].length()==0)?null:s[0];
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        if (((StringType) type).isMultiline()) {
            HtmlTextArea htmlText = new HtmlTextArea();
            htmlText.setName(name);
            htmlText.setValue(WUtils.plainTextToHtml0(value[0]));
            htmlText.setMaxLength(((StringType) type).getMaxLength());
            String cols = (String) getExtraProperty("textarea.cols", request);
            if (cols != null) {
                try {
                    htmlText.setColumns(Integer.parseInt(cols));
                } catch (Exception e) {
                }
            }
            return htmlText;

        } else {
            HtmlText htmlText = new HtmlText();
            htmlText.setName(name);
            htmlText.setValue(value[0]);
            htmlText.setMaxLength(((StringType) type).getMaxLength());
            try {
                htmlText.setSize(Integer.parseInt((String) getExtraProperty("text.size", request)));
            } catch (Exception e) {
                //
            }
            return htmlText;
        }
    }
}

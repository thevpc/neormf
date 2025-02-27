package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.StringType;
import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlText;
import org.vpc.neormf.wws.html.HtmlTextArea;
import org.vpc.neormf.wws.html.HtmlPassword;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 aout 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class StringPasswordTypeHtmlSupport extends StringTypeHtmlSupport{
    public StringPasswordTypeHtmlSupport() {
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        HtmlPassword htmlText = new HtmlPassword();
        htmlText.setName(name);
        htmlText.setValue(WUtils.toHtml(request,value[0]));
        htmlText.setMaxLength(((StringType) type).getMaxLength());
        String preferredSize = (String) getExtraProperty("text.size", request);
        if (preferredSize != null) {
            try {
                htmlText.setSize(Integer.parseInt(preferredSize));
            } catch (Exception e) {
            }
        }
        return htmlText;
    }
}

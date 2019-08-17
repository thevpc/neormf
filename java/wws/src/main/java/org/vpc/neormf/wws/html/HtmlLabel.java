package org.vpc.neormf.wws.html;

import org.vpc.neormf.wws.common.WUtils;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 29 sept. 2005
 * Time: 19:33:40
 * To change this template use File | Settings | File Templates.
 */
public class HtmlLabel extends HtmlWidget{
    private String text;

    public HtmlLabel(String text) {
        this.text = text;
    }

    public String toHtml() {
        return WUtils.plainTextToHtml(text==null?"":text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package org.vpc.neormf.wws.html;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 29 sept. 2005
 * Time: 19:48:10
 * To change this template use File | Settings | File Templates.
 */
public class HtmlErrorDecoration extends HtmlWidget{
    private HtmlWidget htmlWidget;

    public HtmlErrorDecoration(HtmlWidget htmlWidget) {
        this.htmlWidget = htmlWidget;
    }

    public String toHtml() {
        return "<Table><TR BGCOLOR='RED'><TD>" + htmlWidget.toHtml() + "</TD></TR></TABLE>";
    }
}

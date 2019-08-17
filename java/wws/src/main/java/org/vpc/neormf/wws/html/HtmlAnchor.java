package org.vpc.neormf.wws.html;

import org.vpc.neormf.wws.common.WUtils;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 29 sept. 2005
 * Time: 19:30:23
 * To change this template use File | Settings | File Templates.
 */
public class HtmlAnchor extends HtmlWidget{
    private String href;
    private String label;

    public HtmlAnchor() {
    }

    public HtmlAnchor(String href, String label) {
        this.href = href;
        this.label = label;
    }

    
    
    public String toHtml() {
        return "<a href="+WUtils.plainTextToCotedHtml(href)+" >"
                +WUtils.plainTextToHtml(label==null?"":label)
                +"</a>";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

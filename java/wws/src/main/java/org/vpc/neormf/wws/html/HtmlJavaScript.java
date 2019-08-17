package org.vpc.neormf.wws.html;

/**
 * Created by IntelliJ IDEA.
 * User: taha
 * Date: 15 mai 2006
 * Time: 19:25:31
 * To change this template use File | Settings | File Templates.
 */
public class HtmlJavaScript extends HtmlWidget{
    private String scriptCode;

    public HtmlJavaScript(String value) {
        this.scriptCode = value;
    }

    public String toHtml() {
        return "<script language = \"javascript\">"+scriptCode +"</script>";
    }

    public String getScriptCode() {
        return scriptCode;
    }

}

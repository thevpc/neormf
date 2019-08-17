package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.util.IOUtils;
import org.vpc.neormf.wws.html.HtmlChoice;
import org.vpc.neormf.wws.html.HtmlText;
import org.vpc.neormf.wws.html.HtmlJavaScript;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: taha
 * Date: 11 mai 2006
 * Time: 21:51:24
 * To change this template use File | Settings | File Templates.
 */
public class EditableChoiceTypeHtmlSupport extends ChoiceTypeHtmlSupport{
    private String pattern;

    public EditableChoiceTypeHtmlSupport() {
    }

    public EditableChoiceTypeHtmlSupport(DataType staticDatatype) {
        super(staticDatatype);
    }

    public String getPattern() {
        if(pattern==null){
            try {
                pattern=new String(IOUtils.loadStreamAsByteArray(EditableChoiceTypeHtmlSupport.class.getResource("EditableChoiceTypeHtmlSupportScript.js")));
            } catch (IOException e) {
                pattern=e.toString();
                throw new RuntimeException(e);
            }
        }
        return pattern;
    }

    protected HtmlChoice createChoice() {
        return new HtmlChoice(){
            public String toHtml() {
                StringBuffer sb=new StringBuffer(super.toHtml());
                HtmlText text=new HtmlText();
                text.setName(getName()+"_InputTextFilter");
                text.setSize(4);
                sb.append(text.toHtml());
                HtmlJavaScript script =new HtmlJavaScript(getPattern().replaceAll("\\$\\$NAME\\$\\$",getName()));
                //sb.append("<script language = \"javascript\">EditableChoiceTypeHtmlSupportScript_InitList").append(getName()).append("(); EditableChoiceTypeHtmlSupportScript_CheckList").append(getName()).append("();</script>");
                sb.append(script.toHtml());
                return sb.toString();
            }
        };
    }
}

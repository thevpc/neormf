package org.vpc.neormf.wws.html;

import org.vpc.neormf.wws.common.WUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 29 sept. 2005
 * Time: 19:30:23
 * To change this template use File | Settings | File Templates.
 */
public class HtmlChoice extends HtmlWidget{
    private String name;
    private boolean disabled;
    private String value=null;
    private String onChange=null;
    private ArrayList options=new ArrayList();

    public String toHtml() {
        StringBuffer sb=new StringBuffer("<Select name=\"").append(name).append("\"");
        sb.append(disabled?" disabled ":"");
        sb.append((onChange!=null)?(" onchange="+onChange):"");
        sb.append(">");
        for (Iterator i = options.iterator(); i.hasNext();) {
            Option option = (Option) i.next();
            String s1=value==null?"":value;
            String s2=option.value ==null?"":option.value;
            boolean selected=s1.equals(s2);
            sb.append("<option value=").append("\"").append(s2).append("\"").append(selected?" selected ":"").append(">").append(WUtils.plainTextToHtml(option.label)).append("</option>");
        }
        sb.append("</Select>");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addOption(String name, String value){
        options.add(new Option(name,value));
    }

    public static class Option{
        private String value;
        private String label;

        public Option(String name, String value) {
            this.value = name;
            this.label = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }
}

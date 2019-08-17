package org.vpc.neormf.wws.html;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 29 sept. 2005
 * Time: 19:30:23
 * To change this template use File | Settings | File Templates.
 */
public class HtmlTextArea extends HtmlWidget{
    private String name;
    private boolean disabled;
    private int columns =Integer.MAX_VALUE;
    private int maxLength=Integer.MAX_VALUE;
    private String value=null;

    public String toHtml() {
        return "<textarea name='"+name+"' type='text' "
                + (disabled ? "disabled " : " ")
                + (columns !=Integer.MAX_VALUE ? ("cols="+columns +" " ) : " ")
                + (maxLength!=Integer.MAX_VALUE ? ("maxlength="+maxLength+" " ) : " ")
                +">"+(value==null?"":value)+"</textarea>";
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

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}

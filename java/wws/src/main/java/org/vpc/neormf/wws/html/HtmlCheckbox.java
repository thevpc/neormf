package org.vpc.neormf.wws.html;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 29 sept. 2005
 * Time: 19:30:23
 * To change this template use File | Settings | File Templates.
 */
public class HtmlCheckbox extends HtmlWidget{
    private String name;
    private String onClick;
    private boolean checked;
    private boolean disabled;

    public String toHtml() {
        return "<input name='"+name+"' type='checkbox' "
                + (checked ? "checked " : " ")
                + (disabled ? "disabled " : " ")
                + (onClick!=null ? "onclick=\""+onClick+"\" " : " ")
                +">";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}

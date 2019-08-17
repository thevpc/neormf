package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.AbstractChoiceType;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.wws.webform.DataTypeHtmlSupportFactory;
import org.vpc.neormf.wws.common.WMessages;
import org.vpc.neormf.wws.html.HtmlWidget;
import org.vpc.neormf.wws.html.HtmlLabel;
import org.vpc.neormf.wws.html.HtmlChoice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Iterator;


/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class ChoiceTypeHtmlSupport extends DefaultDataTypeHtmlSupport {
    private DataType staticDatatype;

    public ChoiceTypeHtmlSupport() {
    }

    public ChoiceTypeHtmlSupport(DataType staticDatatype) {
        this.staticDatatype = staticDatatype;
    }

    private DataType getDereferencedValidDataType(DataType type){
        return staticDatatype==null?type : staticDatatype;
    }

    public String[] convertObjectToHtmlStringArray(Object v, DataType type, HttpServletRequest request) {
        DataType etype = getDereferencedValidDataType(type);
        if (etype instanceof AbstractChoiceType) {
            etype = ((AbstractChoiceType) etype).getElementType();
        }
        return DataTypeHtmlSupportFactory.getSupport(etype.getClass()).convertObjectToHtmlStringArray(v, etype, request);
    }

    public String convertObjectToHtmlString(Object v, DataType type, HttpServletRequest request) {
        DataType etype = getDereferencedValidDataType(type);
        if (etype instanceof AbstractChoiceType) {
            etype = ((AbstractChoiceType) etype).getElementType();
        }
        return DataTypeHtmlSupportFactory.getSupport(etype.getClass()).convertObjectToHtmlString(v, etype, request);
    }

    public Object convertHtmlStringToObject(String[] s, DataType type, HttpServletRequest request) {
        DataType etype = type;
        if (etype instanceof AbstractChoiceType) {
            etype = ((AbstractChoiceType) etype).getElementType();
        }
        return DataTypeHtmlSupportFactory.getSupport(etype.getClass()).convertHtmlStringToObject(s, etype, request);
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        HtmlChoice choice=createChoice();
        choice.setName(name);
        type=getDereferencedValidDataType(type);
        if(value.length!=1){
            throw new RuntimeException("Not supported composed values");
        }
        choice.setValue(value[0]);
        if (type instanceof AbstractChoiceType) {
            List list = ((AbstractChoiceType) type).elements();
            for (Iterator i = list.iterator(); i.hasNext();) {
                AbstractChoiceType.Element elem=(AbstractChoiceType.Element) i.next();
                Object key = elem.getKey();
                String s=convertObjectToHtmlString(key,type,request);
                String renderer=elem.getRenderer();
                if(renderer==null){
                    renderer=(WMessages.getString("field."+name+".value."+value,request,convertObjectToHtmlString(value,type, request)));
                }
                choice.addOption(s,renderer);
            }
        }else{
            String s=convertObjectToHtmlString(Boolean.TRUE,type,request);
            choice.addOption(s,(WMessages.getString("field."+name+".value.true",request,convertObjectToHtmlString(value,type, request))));
            s=convertObjectToHtmlString(Boolean.FALSE,type,request);
            choice.addOption(s,(WMessages.getString("field."+name+".value.false",request,convertObjectToHtmlString(value,type, request))));
        }
        return choice;
    }

    protected HtmlChoice createChoice(){
      return new HtmlChoice();
    }

    public HtmlWidget generateHtmlValue(String name, Object value, DataType type, HttpServletRequest request) {
        type=getDereferencedValidDataType(type);
        String renderer=(type instanceof AbstractChoiceType) ? ((AbstractChoiceType) type).getKeyRenderer(value) : null;
        if(renderer==null){
            renderer=WMessages.getString("field."+name+".value."+value,request,convertObjectToHtmlString(value,type, request));
        }
        return new HtmlLabel(renderer);
    }
}

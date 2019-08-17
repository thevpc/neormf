package org.vpc.neormf.wws.webform.datatype;

import org.vpc.neormf.commons.types.DataException;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.commons.types.TimeDayType;
import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.html.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;


/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 aout 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class TimeDayTypeHtmlSupport extends DefaultDataTypeHtmlSupport {
    public String[] getParameterNames(String basicName, DataType type, HttpServletRequest request) {
        return new String[]{basicName, basicName + "Index"};
    }

    public String[] convertObjectToHtmlStringArray(Object value, DataType type, HttpServletRequest request) {
        if (value == null) {
            return new String[]{"", ""};
        }
        Timestamp ts = (Timestamp) value;
        String s = WUtils.getTimeFormat(request).format((java.util.Date) value);
        int day = ((TimeDayType) type).getDay(ts.getTime());
        return new String[]{s, day > 1 ? "on" : ""};
    }

    public String convertObjectToHtmlString(Object v, DataType type, HttpServletRequest request) {
        if (v == null) {
            return "";
        }
        Timestamp ts = (Timestamp) v;
        String s = WUtils.getTimeFormat(request).format((java.util.Date) v);
        int day = ((TimeDayType) type).getDay(ts.getTime());
        switch (day) {
            case 1: {
                return s;
            }
            case 2: {
                return s + " (du lendemain)";
            }
            case 3: {
                return s + " (du surlendemain)";
            }
            default: {
                return s + " (apres " + (day - 1) + " jours)";
            }
        }
    }

    public Object convertHtmlStringToObject(String[] s, DataType type, HttpServletRequest request) {
        try {
            if (s[0] == null || s[0].length() == 0) {
                return null;
            }
            return TimeDayType.TIMEDAY_NULLABLE.getTimestamp(new Time(WUtils.getTimeFormat(request).parse(s[0]).getTime()), "on".equals(s[1]) ? 2 : 1);
        } catch (ParseException e) {
            throw new DataException("org.vpc.neormf.commons.types.TimeDayType.BadFormat.Message", new Object[]{s[0]});
        }
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        String[] parameterNames = getParameterNames(name, type, request);
        HtmlContainer c = new HtmlContainer();
        HtmlText htmlText = new HtmlText();
        htmlText.setName(parameterNames[0]);
        htmlText.setValue(value[0]);
        try {
            htmlText.setSize(Integer.parseInt((String) getExtraProperty("text.size", request)));
        } catch (Exception e) {
        }
        try {
            htmlText.setMaxLength(Integer.parseInt((String) getExtraProperty("text.maxlength", request)));
        } catch (Exception e) {
        }
        c.addElement(htmlText);
        c.addElement(new HtmlLabel("("));
        HtmlCheckbox htmlCheckbox = new HtmlCheckbox();
        htmlCheckbox.setName(parameterNames[1]);
        htmlCheckbox.setChecked("on".equals(value[1]));
        c.addElement(htmlCheckbox);
        c.addElement(new HtmlLabel(" du lendemain)"));
        return c;
    }
}

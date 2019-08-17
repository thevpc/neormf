package org.vpc.neormf.jbgen.util;

import java.util.List;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 18 févr. 2009
 * Time: 18:53:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class ListUserCode<T> {
    private List<T> list;
    private String separator;

    protected ListUserCode(T[] list) {
        this(Arrays.asList(list));
    }
    
    protected ListUserCode(List<T> list) {
        this(",",list);
    }

    protected ListUserCode(String separator,T[] list) {
        this(separator, Arrays.asList(list));
    }

    protected ListUserCode(String separator,List<T> list) {
        this.list = list;
        this.separator = separator;
    }
    
    public boolean accept(T o){
        return true;
    }

    public abstract String stringify(T o);

    public String toString(){
        StringBuilder sb=new StringBuilder();
        boolean first=true;
        for (T t : list) {
            if (accept(t)) {
                if(first){
                    first = false;
                }else{
                    sb.append(separator);
                }
                sb.append(stringify(t));
            }
        }
        return sb.toString();
    }
}

package org.vpc.neormf.jbgen.config;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 03:55:34
 * To change this template use File | Settings | File Templates.
 */
public class ConfigHandler extends DefaultHandler {
    Stack stack=new Stack();
    ConfigNode root;

    public ConfigHandler() {
    }

    public void startDocument() throws SAXException {
        super.startDocument();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        ConfigNode item=new ConfigNode(qName);
        int max=attributes.getLength();
        for(int i=0;i<max;i++){
            item.setAttribute(attributes.getQName(i),attributes.getValue(i));
        }
        if(!stack.isEmpty()){
            ConfigNode parent = (ConfigNode) stack.peek();
            parent.add(item);
        }else{
            root=item;
        }
        stack.push(item);
    }


    public void endElement(String uri, String localName, String qName) throws SAXException {
        stack.pop();
    }


    public void characters(char ch[], int start, int length) throws SAXException {
        String s=new String(ch,start,length).trim();
        if(s.length()>0){
            ConfigNode parent = (ConfigNode) stack.peek();
            String v=parent.getValue();
            if(v==null){
                v="";
            }
            parent.setValue(v+s);
        }
    }


    public ConfigNode getRoot() {
        return root;
    }
}

package org.vpc.neormf.jbgen.xml;

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
public class XmlHandler extends DefaultHandler {
    private Stack stack=new Stack();
    private XmlNode root;

    public XmlHandler() {
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        XmlNode item=new XmlNode(qName);
        int max=attributes.getLength();
        for(int i=0;i<max;i++){
            item.setAttribute(attributes.getQName(i),attributes.getValue(i));
        }
        if(!stack.isEmpty()){
            XmlNode parent = (XmlNode) stack.peek();
            parent.add(item);
        }else{
            root=item;
        }
        stack.push(item);
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        stack.pop();
    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String s=new String(ch,start,length).trim();
        if(s.length()>0){
            XmlNode parent = (XmlNode) stack.peek();
            String v=parent.getText();
            if(v==null){
                v="";
            }
            parent.setText(v+s);
        }
    }


    public XmlNode getRoot() {
        return root;
    }
}

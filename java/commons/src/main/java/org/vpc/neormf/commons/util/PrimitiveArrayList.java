package org.vpc.neormf.commons.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 1 juil. 2004 Time: 17:05:04
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class PrimitiveArrayList extends ArrayList{
    public PrimitiveArrayList(int i) {
        super(i);
    }

    public PrimitiveArrayList() {
    }

    public PrimitiveArrayList(Collection collection) {
        super(collection);
    }

    public void add(int element){
        super.add(new Integer(element));
    }
    public void add(int pos,int element){
        super.add(pos,new Integer(element));
    }
    public void addInt(int element){
        super.add(new Integer(element));
    }
    public void addInt(int pos,int element){
        super.add(pos,new Integer(element));
    }
    public void add(long element){
        super.add(new Long(element));
    }
    public void add(int pos,long element){
        super.add(pos,new Long(element));
    }
    public void addLong(long element){
        super.add(new Long(element));
    }
    public void addLong(int pos,long element){
        super.add(pos,new Long(element));
    }
    public void add(double element){
        super.add(new Double(element));
    }
    public void add(int pos,double element){
        super.add(pos,new Double(element));
    }
    public void addDouble(double element){
        super.add(new Double(element));
    }
    public void addDouble(int pos,double element){
        super.add(pos,new Double(element));
    }
    public void add(float element){
        super.add(new Float(element));
    }
    public void add(int pos,float element){
        super.add(pos,new Float(element));
    }
    public void addFloat(float element){
        super.add(new Float(element));
    }
    public void addFloat(int pos,float element){
        super.add(pos,new Float(element));
    }
    public void add(byte element){
        super.add(new Byte(element));
    }
    public void add(int pos,byte element){
        super.add(pos,new Byte(element));
    }
    public void addByte(byte element){
        super.add(new Byte(element));
    }
    public void addByte(int pos,byte element){
        super.add(pos,new Byte(element));
    }
    public void add(char element){
        super.add(new Character(element));
    }
    public void add(int pos,char element){
        super.add(pos,new Character(element));
    }
    public void addChar(char element){
        super.add(new Character(element));
    }
    public void addChar(int pos,char element){
        super.add(pos,new Character(element));
    }

    public void add(boolean element){
        super.add(element?Boolean.TRUE:Boolean.FALSE);
    }
    public void add(int pos,boolean element){
        super.add(pos,element?Boolean.TRUE:Boolean.FALSE);
    }
    public void addBooolean(boolean element){
        super.add(element?Boolean.TRUE:Boolean.FALSE);
    }
    public void addBoolean(int pos,boolean element){
        super.add(pos,element?Boolean.TRUE:Boolean.FALSE);
    }
}

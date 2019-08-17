package org.vpc.neormf.commons.util;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 18 juin 2006
 * Time: 16:48:12
 * To change this template use File | Settings | File Templates.
 */
public class SerializationUtils {
    public static Object getObjectFromSerializedForm(byte[] bytes) throws ClassNotFoundException {
        if(bytes==null || bytes.length==0){
            return null;
        }
        ObjectInputStream b = null;
        try {
            b = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return b.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        } finally {
            try {
                if (b != null) {
                    b.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static byte[] getSerializedFormOf(Object object) {
        if(object==null){
            return null;
        }
        ObjectOutputStream b = null;
        try {
            ByteArrayOutputStream bb = new ByteArrayOutputStream();
            b = new ObjectOutputStream(bb);
            b.writeObject(object);
            return bb.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        } finally {
            try {
                if (b != null) {
                    b.close();
                }
            } catch (IOException e) {
            }
        }
    }

}

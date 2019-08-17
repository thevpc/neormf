/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpc.neormf.commons.beans;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import org.vpc.neormf.commons.util.IOUtils;

/**
 *
 * @author vpc
 */
public class BlobData implements Serializable {

    private File file;
    private byte[] bytes;
    private long length;
    private String name;

    public BlobData(File file) throws IOException{
        this(file, false,null);
    }

    public BlobData(File file, boolean header,String name) throws IOException{
        if (!header) {
            this.file = file;
            this.length = file.length();
            this.name = name==null?file.getName():name;
        }else{
            init(new FileInputStream(file), header, name);
        }
    }

    public BlobData(byte[] bytes, boolean header, String name) throws IOException {
        init(bytes, header, name);
    }

    public BlobData(InputStream bytes, boolean header, String name) throws IOException {
        init(bytes, header, name);
    }
    
    public BlobData(Blob blob, boolean readHeaderFromBlob,String name) throws SQLException, IOException {
        long length0 = blob == null ? 0 : blob.length();
        if (length0 == 0) {
            bytes = new byte[0];
            name = "noname";
            length = 0;
        } else {
            long ll = (int) length0;
            if (ll != length0) {
                init(blob.getBinaryStream(), readHeaderFromBlob, name);
            } else {
                init(blob.getBytes(1, (int) length0), readHeaderFromBlob, name);
            }
        }
    }
    
    private void init(InputStream bytes, boolean readHeaderFromStream, String name) throws IOException {
        if (readHeaderFromStream && name != null) {
            throw new IllegalArgumentException("header could not be true if name is specified");
        }
        if (readHeaderFromStream) {
            int ch1 = bytes.read();
            int ch2 = bytes.read();
            int ch3 = bytes.read();
            int ch4 = bytes.read();
            if ((ch1 | ch2 | ch3 | ch4) < 0) {
                throw new EOFException();
            }
            int nameLength = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
            byte[] _name = new byte[nameLength];
            int i = bytes.read(_name);
            if (_name.length != i) {
                throw new EOFException();
            }
            this.name=new String(_name);
            //retrive extension
        }else{
            this.name=name;
        }
        if(this.name==null){
            this.name="";
        }
        if(this.name.length()==0){
            this.name="noname";
        }
        int li=name.lastIndexOf('.');
        String extension=".tmp";
        String prefix=name;
        if(li>0 && li<(name.length()-1)){
            prefix=name.substring(0,li);
            extension=name.substring(li+1);
        }
        file = File.createTempFile(prefix, extension);
        IOUtils.copy(bytes, file);
        bytes.close();
        this.length=file.length();
    }

    
    private void init(byte[] bytes, boolean header, String name) throws IOException {
        if (header && name != null) {
            throw new IllegalArgumentException("header could not be true if name is specified");
        }
        int pos0=0;
        if (header) {
            if(bytes.length<4){
                throw new EOFException();
            }
            int ch1 = bytes[0];
            int ch2 = bytes[1];
            int ch3 = bytes[2];
            int ch4 = bytes[3];
            int nameLength = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
            byte[] _name = new byte[nameLength];
            System.arraycopy(bytes, 4, _name, 0, nameLength);
            this.name=new String(_name);
            pos0=4+nameLength;
            this.length=bytes.length-pos0;
            //retrive extension
        }else{
            this.name=name;
            this.length=bytes.length;
        }
        if(this.name==null){
            this.name="";
        }
        if(this.name.length()==0){
            this.name="noname";
        }
        this.bytes=new byte[bytes.length-pos0];
        System.arraycopy(bytes, pos0, this.bytes, 0, this.bytes.length);
    }

    public BlobData(byte[] bytes) throws IOException{
        this(bytes, "");
    }

    public BlobData(byte[] bytes, String name) throws IOException{
        this(bytes,false,name);
    }

    public BlobData(Blob blob) throws SQLException, IOException {
        this(blob,true,null);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (file != null) {
            file.deleteOnExit();
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    public File getFile() {
        return file;
    }

    public long getLength() {
        return length+4+name.getBytes().length;
    }
    
    public long getDataLength() {
        return length;
    }

    public InputStream getInputStream() throws IOException {
        final File tempInputStreamFile = File.createTempFile("blob_getInputStream_", ".tmp");
        FileOutputStream fos = new FileOutputStream(tempInputStreamFile);
        byte[] nameBytes = name.getBytes();
        int v = nameBytes.length;
        fos.write(((v >>> 24) & 0xFF));
        fos.write(((v >>> 16) & 0xFF));
        fos.write(((v >>> 8) & 0xFF));
        fos.write(((v >>> 0) & 0xFF));
        fos.write(nameBytes);
        if (file != null) {
            IOUtils.copy(file, fos);
        } else if (bytes != null) {
            fos.write(bytes);
        } else {
            //return new ByteArrayInputStream(new byte[0]);
        }
        return new FileInputStream(tempInputStreamFile) {

            @Override
            public void close() throws IOException {
                super.close();
                tempInputStreamFile.delete();
            }
        };
    }

    public String getName() {
        return name;
    }
    
}

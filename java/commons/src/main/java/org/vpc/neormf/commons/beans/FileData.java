package org.vpc.neormf.commons.beans;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class FileData implements Serializable {
    public static final long serialVersionUID = -1231231231230000006L;
    public static String CONTEXT_TARGET_FOLDER = "fileData.targetFolder";
    protected byte[] data;
    protected String sourceName;

    public FileData(String sourceName,byte[] data) {
        this.sourceName = sourceName;
        this.data = data != null ? data : new byte[0];
    }

    public FileData(File file)
            throws IOException {
        load(file.getName(), new FileInputStream(file));
    }

    public FileData(InputStream inputStream)
            throws IOException {
        load(null, inputStream);
    }

    public FileData(String file)
            throws IOException {
        load(file, new FileInputStream(file));
    }

    public FileData(URL url)
            throws IOException {
        load(getURLName(url), url.openStream());
    }

    private final String getURLName(URL url) {
        String p = url.getPath();
        int slash = p.lastIndexOf('/');
        if (slash < 0) {
            slash = p.lastIndexOf(':');
        }
        return slash==0?p: p.substring(slash);
    }

    public byte[] getData() {
//        byte newData[] = new byte[data.length];
//        System.arraycopy(data, 0, newData, 0, data.length);
//        return newData;
        return data;
    }

    private synchronized void load(String src, InputStream inputStream)
            throws IOException {
        if (inputStream == null) {
            data = null;
            sourceName = src;
        } else {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            sourceName = src;
        }
    }

    public void save(File file)
            throws IOException {
        file.getParentFile().mkdirs();
        save(((OutputStream) (new FileOutputStream(file))));
    }

    public void save(OutputStream outputStream)
            throws IOException {
        outputStream.write(getData());
    }

    public void save(String file)
            throws IOException {
        (new File(file)).getParentFile().mkdirs();
        save(((OutputStream) (new FileOutputStream(file))));
    }

    public void save(URL url)
            throws IOException {
        save(url.openConnection().getOutputStream());
    }

    public long size() {
        return data != null ? data.length : -1L;
    }

    public String getSourceName(){
        return sourceName;
    }

    public String getFileType(){
        return sourceName==null ? null : getFileExtension(sourceName);
    }

    private static String getFileExtension(String fileName) {
        int x = fileName.lastIndexOf('.');
        if (x > 0) {
            if (x + 1 < fileName.length()) {
                return fileName.substring(x + 1);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }


    public String getHumanReadableString(HashMap context) {
        return "file";//todo Swings.getResources().get("file");
    }


}

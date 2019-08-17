package org.vpc.neormf.commons.beans;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public final class ImageData extends FileData {
    public static final long serialVersionUID = -1231231231230000007L;

    public ImageData(String sourceName, byte[] data) {
        super(sourceName, data);
    }

    public ImageData(File file)
            throws IOException {
        super(file);
    }

    public ImageData(InputStream inputStream)
            throws IOException {
        super(inputStream);
    }

    public ImageData(String file)
            throws IOException {
        super(file);
    }

    public ImageData(URL url)
            throws IOException {
        super(url);
    }

    public ImageIcon getImage() {
        if (image == null && data != null)
            image = new ImageIcon(data);
        return image;
    }

    public String getHumanReadableString(HashMap context) {
        return "image";//todo Swings.getResources().get("image");
    }

    private transient ImageIcon image;
}

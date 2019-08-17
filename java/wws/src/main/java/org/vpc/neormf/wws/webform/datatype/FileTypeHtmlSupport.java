package org.vpc.neormf.wws.webform.datatype;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.wws.html.HtmlWidget;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.vpc.neormf.commons.beans.BlobData;
import org.vpc.neormf.commons.util.IOUtils;
import org.vpc.neormf.wws.html.HtmlAnchor;
import org.vpc.neormf.wws.html.HtmlContainer;
import org.vpc.neormf.wws.html.HtmlFile;
import org.vpc.neormf.wws.html.HtmlLabel;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 5 août 2005
 * Time: 18:16:45
 * To change this template use File | Settings | File Templates.
 */
public class FileTypeHtmlSupport extends DefaultDataTypeHtmlSupport {

    public FileTypeHtmlSupport() {
    }
    
    @Override
    public HtmlWidget generateHtmlValue(String name, Object value, DataType type, HttpServletRequest request) {
        return value == null ? new HtmlLabel("")  : new HtmlAnchor(getDownloadURL(value, type, request), "download");
    }
    
    public String convertObjectToHtmlString(Object v, DataType type, HttpServletRequest request) {
        return v == null ? "" : "file";
    }

    protected String getDownloadURL(Object v, DataType type, HttpServletRequest request) {
        InputStream in = null;
        try {
            if(v==null){
                return "";
            }
            if(v instanceof String){
                System.out.println(">>>>>>>>>>  getDownloadURL(String) " + v);
                return v.toString();
            }
            System.out.println(">>>>>>>>>>  getDownloadURL ("+v.getClass().getName()+") :" + v);
            BlobData blob = (BlobData) v;
            in = blob.getInputStream();
            String multipart_outbox=request.getSession().getServletContext().getRealPath("/multipart-outbox");
            System.out.println(">>>>>>>>>>  getDownloadURL ("+v.getClass().getName()+") $(multipart_outbox="+multipart_outbox+")");
            File tempFolder = new File(multipart_outbox);
            tempFolder.mkdirs();
            File out = File.createTempFile("download-", (blob.getName()==null || blob.getName().length()==0)? "noname.tmp":blob.getName(),tempFolder);
            System.out.println(">>>>>>>>>>>>>> Temp file created  = " + out.getAbsolutePath());
            IOUtils.copy(in, out, 1024);
            in.close();
//        System.out.println("request.getServletPath():"+request.getServletPath());
//        System.out.println("request.getServletPath():"+request.getContextPath());
//        System.out.println("request.getServletPath():"+request.getPathInfo());
//        System.out.println("request.getServletPath():"+request.getPathTranslated());
//        File ff=request.getSession().getServletContext().getRealPath("/temp/");
//        System.out.println("request.getServletPath():"+ff.get);
            return "multipart-outbox/" + out.getName();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public Object convertHtmlStringToObject(String[] s, DataType type, HttpServletRequest request) {
        try {
            if (s[0] == null || s[0].length() == 0) {
                return null;
            }
            String filePath = request.getSession().getServletContext().getRealPath(s[0]);
            System.out.println(">>>>>>>>>>  convertHtmlStringToObject (" + s[0] + ") =" + filePath + ")");
            return new BlobData(new File(filePath));
        } catch (IOException ex) {
            Logger.getLogger(FileTypeHtmlSupport.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    protected HtmlWidget generateHtmlEditor(String name, String[] value, DataType type, HttpServletRequest request) {
        return new HtmlContainer(new HtmlAnchor(getDownloadURL(value[0], type, request), "download"), new HtmlFile(name, value[0]));
    }
}

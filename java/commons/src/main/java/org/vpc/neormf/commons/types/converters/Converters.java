/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vpc.neormf.commons.types.converters;

import org.vpc.neormf.commons.beans.FileData;
import org.vpc.neormf.commons.beans.ImageData;

/**
 *
 * @author vpc
 */
public final class Converters {
    public static final DataTypeConverter BLOB_TO_OBJECT = new BlobConverter(Object.class,true);
    public static final DataTypeConverter BLOB_TO_FILE_DATA = new BlobConverter(FileData.class,true);
    public static final DataTypeConverter BLOB_TO_IMAGE_DATA = new BlobConverter(ImageData.class,true);
    public static final DataTypeConverter INTEGER_TO_BOOLEAN = new IntegerToBoolean(true);
    public static final DataTypeConverter STRING1_YN_TO_BOOLEAN = new String1ToBoolean('Y', 'N',false);

    private Converters() {
    }
    
}

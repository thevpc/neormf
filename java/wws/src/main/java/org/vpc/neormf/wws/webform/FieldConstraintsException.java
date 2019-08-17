package org.vpc.neormf.wws.webform;

import org.vpc.neormf.wws.common.WUtils;
import org.vpc.neormf.wws.common.WMessages;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * Exception de validation des champs
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */

public class FieldConstraintsException extends RuntimeException {
    public FieldConstraintsException(String errorKey,Object value,String fieldId,String fieldName,HttpServletRequest request) {
        super(
            MessageFormat.format(
                WMessages.getString("ERROR." + errorKey, request)
            , new Object[]{WUtils.toDisplayable(request,value, "", 20), fieldId, fieldName})
        );
    }
}

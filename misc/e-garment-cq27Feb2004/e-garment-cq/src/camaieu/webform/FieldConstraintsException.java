package camaieu.webform;

import camaieu.common.WUtils;
import camaieu.tool.web.language.Messages;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * Exception de validation des champs
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */

public class FieldConstraintsException extends RuntimeException {
    public FieldConstraintsException(String errorKey,Object value,String fieldId,String fieldName,HttpServletRequest request) {
        super(
            MessageFormat.format(
                Messages.getString("ERROR." + errorKey, request)
            , new Object[]{WUtils.toDisplayable(request,value, "", 20), fieldId, fieldName})
        );
    }
}

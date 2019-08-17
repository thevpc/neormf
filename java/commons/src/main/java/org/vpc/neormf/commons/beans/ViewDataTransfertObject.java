package org.vpc.neormf.commons.beans;

import java.util.Hashtable;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 24 mars 2004 Time: 09:55:28
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class ViewDataTransfertObject extends DataTransfertObject {
    protected Hashtable holdersMap;
    protected Hashtable expressionsMap;

    public ViewDataTransfertObject() {
    }

    public String getFieldHolderName(String fieldName) {
        if (holdersMap != null) {
            return (String) holdersMap.get(fieldName);
        }
        return null;
    }

    public String getFieldExpression(String fieldName) {
        if (expressionsMap != null) {
            return (String) expressionsMap.get(fieldName);
        }
        return null;
    }

    public void setFieldHolderName(String fieldName, String holderName) {
        if (holdersMap == null) {
            holdersMap = new Hashtable(5);
        }
        holdersMap.put(fieldName, holderName);
    }

    public void setFieldExpression(String fieldName, String expression) {
        if (expressionsMap == null) {
            expressionsMap = new Hashtable(5);
        }
        expressionsMap.put(fieldName, expression);
    }
}

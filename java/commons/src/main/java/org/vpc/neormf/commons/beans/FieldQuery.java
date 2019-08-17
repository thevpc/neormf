package org.vpc.neormf.commons.beans;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 20 avr. 2004 Time: 16:28:06
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class FieldQuery implements FieldFormula {
    private String query;
    private DTOMetaData dataInfo;

    public FieldQuery(String query, DTOMetaData dataInfo) {
        this.query = query;
        this.dataInfo = dataInfo;
    }

    public Object compute(DataKey dataKey) {
        return null;//return "SELECT "+pattern+" FROM "+dataInfo.get ;
    }
}

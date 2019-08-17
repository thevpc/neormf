package org.vpc.neormf.commons.beans;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 11:45:15
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class NamedDataTransfertObject extends DataTransfertObject {
    String dataContentName;

    public NamedDataTransfertObject(String dataContentName) {
        this.dataContentName = dataContentName;
    }

    public String getDataContentName() {
        return dataContentName;
    }

    public void setDataContentName(String dataContentName) {
        this.dataContentName = dataContentName;
    }
}

package org.vpc.neormf.commons.beans;

import org.vpc.neormf.commons.util.Utils;

import java.io.Serializable;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 25 mars 2004 Time: 15:50:39
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public abstract class DataKey implements Serializable {
    public abstract Object keyPartAt(int position);

    public abstract int keySize();

    public int hashCode() {
        int hash = 17;
        for (int i = keySize() - 1; i >= 0; i--) {
            hash = 31 * hash + keyPartAt(i).hashCode();
        }
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        for (int i = keySize() - 1; i >= 0; i--) {
            Object p1 = keyPartAt(i);
            Object p2 = ((DataKey) obj).keyPartAt(i);
            if (!p1.equals(p2)) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append(Utils.getClassName(this.getClass()));
        sb.append("(");
        int max=keySize();
        for (int i = 0; i < max; i++) {
            if(i>0){
                sb.append(",");
            }
           sb.append(Utils.dump(keyPartAt(i)));
        }
        sb.append(")");
        return sb.toString();
    }
    
    public DTOMetaData metadata() {
//        throw new UnsupportedOperationException();
        return null;
    }

    public DataTransfertObject toDTO() {
        DTOMetaData nfo= metadata();
        if(nfo==null){
            DataTransfertObject d=new DataTransfertObject();
            int ks = keySize();
            for(int i=0;i< ks;i++){
                d.setProperty("k"+(i+1),keyPartAt(i));
            }
            return d;
        }else{
            DTOFieldMetaData[] kfields = nfo.getKeyFields();
            DataTransfertObject d=nfo.createDTO();
            int ks = keySize();
            for(int i=0;i< ks;i++){
                d.setProperty(kfields[i].getFieldName(),keyPartAt(i));
            }
            return d;
        }
    }

}

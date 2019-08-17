package org.vpc.neormf.wws.webform;

import org.vpc.neormf.wws.common.WUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Vector;

/**
 * Classe de gestion des erreurs
 *
 * @author tbensalah (Taha Ben Salah)
 * @creation_date date 21/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public class ErrorList implements Serializable {
    /**
     * liste ordonnee des messages d'erreurs
     */
    private Vector ordredErrors;

    /**
     * ensemble utilise pour eliminer les duplicata
     */
    private HashSet set;

    /**
     * Creer une nouvelle liste d'erreurs
     * @param noDuplicates si vrai, les message dupliques seront elimines automatiquement
     */
    public ErrorList(boolean noDuplicates) {
        ordredErrors = new Vector();
        if (noDuplicates) {
            set = new HashSet();
        }
    }

    /**
     * rajout d'un nouveau message d'erreur
     * @param error
     */
    public synchronized void addError(String error) {
        if(error==null){
            throw new NullPointerException("Error could not be null");
        }
        if (set != null) {
            if (set.add(error)) {
                ordredErrors.add(error);
            }
        } else {
            ordredErrors.add(error);
        }
    }

    /**
     * formatter la liste d'erreur en format HTML
     * @return
     */
    public synchronized String toHtml() {
        StringBuffer sb = new StringBuffer();
        int size = ordredErrors.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("<BR>");
            }
            String m=(String) ordredErrors.get(i);
            sb.append(WUtils.plainTextToHtml(m==null?"":m));
        }
        return sb.toString();
    }

    /**
     * nombre d'erreurs
     * @return
     */
    public int size() {
        return ordredErrors.size();
    }

    /**
     * vrai si aucune erreur
     * @return vrai si aucune erreur
     */
    public boolean isEmpty() {
        return ordredErrors.size() == 0;
    }
}

package camaieu.common;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe utilitaire pour la manipulation des chaines
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 20/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */

public final class StringUtils {
    /**
     * constructeur priv�
     */
    private StringUtils() {
    }

    /**
     * casser la chaine selon le s�parateur donn�
     * @param strValue chaine � casser
     * @param delemeter le d�limiteur
     * @param returnNulls si vrai deux s�parateur successifs reverront un null
     * @return les tokens de la chaine cass�e
     */
    public static String[] split(String strValue, String delemeter, boolean returnNulls) {
        if (returnNulls) {
            StringTokenizer st = new StringTokenizer(strValue, delemeter, true);
            boolean lastWasDelemeter = false;
            Vector elements = new Vector();
            while (st.hasMoreTokens()) {
                String t = st.nextToken();
                if (delemeter.indexOf(t) >= 0) {
                    if (lastWasDelemeter) {
                        elements.add(null);
                    }
                    lastWasDelemeter = true;
                } else {
                    lastWasDelemeter = false;
                    elements.add(t);
                }
            }
            return (String[]) elements.toArray(new String[elements.size()]);
        } else {
            StringTokenizer st = new StringTokenizer(strValue, delemeter, false);
            Vector elements = new Vector();
            while (st.hasMoreTokens()) {
                elements.add(st.nextToken());
            }
            return (String[]) elements.toArray(new String[elements.size()]);
        }
    }

    /**
     * transpose le tableau (colonnes&lt;--&gt;lignes)
     * @param table
     * @return
     */
    public static String[][] transpose(String[][] table){
        if(table.length==0){
            return table;
        }
        String[][] table2=new String[table[0].length][table.length];
        for(int i=0;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                table2[j][i]=table[i][j];
            }
        }
        return table2;
    }

    /**
     * concat�ne les valeurs de <code>table</code>
     * @param table les valeurs � concat�ner
     * @param lineSep s�parateur des lignes
     * @param colSep s�parateur des colonnes
     * @param ignoreEmptyCol si vrai les lignes vides seront ignor�es
     * @param ignorEmptyLine si vrai les colonnes vides seront ignor�es
     * @return une cha�ne r�sultant de la concat�nation de toutes les cellules de la table
     */
    public static String concat(String[][] table,String colSep,boolean ignoreEmptyCol,String lineSep,boolean ignorEmptyLine){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<table.length;i++){
            StringBuffer sbl=new StringBuffer();
            for(int j=0;j<table[i].length;j++){
                if(!ignoreEmptyCol || (table[i][j]!=null && table[i][j].length()!=0)){
                    if(sbl.length()>0){
                        sbl.append(colSep);
                    }
                    sbl.append(table[i][j]);
                }
            }
            if(!ignorEmptyLine || sbl.length()!=0){
                if(sb.length()>0){
                    sb.append(lineSep);
                }
                sb.append(sbl);
            }
        }
        return sb.toString();
    }

    /**
     * concat�ne les valeurs de <code>table</code>
     * @param table les valeurs � concat�ner
     * @param colSep s�parateur des colonnes
     * @param ignoreEmptyCol si vrai les lignes vides seront ignor�es
     * @return une cha�ne r�sultant de la concat�nation de toutes les cellules de la table
     */
    public static String concat(String[] table,String colSep,boolean ignoreEmptyCol){
        StringBuffer sbl=new StringBuffer();
        for(int j=0;j<table.length;j++){
            if(!ignoreEmptyCol || (table[j]!=null && table[j].length()!=0)){
                if(sbl.length()>0){
                    sbl.append(colSep);
                }
                sbl.append(table[j]);
            }
        }
        return sbl.toString();
    }

}

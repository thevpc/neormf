package camaieu.webform;



/**
 * Classe abstraite de base pour g�rer des donn�es h�t�rog�nes
 * encapsulant des donn�es de plusieurs tables � la fois.
 * <BR> Elle permet �galement de g�rer les erreurs sur chacun des champs
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 16/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public abstract class AbstractHeteroData {
    /**
     * instance de RecordEditorHelper associ�
     */
    private RecordEditorHelper helper;

    /**
     * constructer par d�faut
     */
    public AbstractHeteroData() {
    }

    /**
     * l'instance de RecordEditorHelper associ�e
     * @return RecordEditorHelper
     */
    public RecordEditorHelper getHelper() {
        if (helper == null) {
            helper = new RecordEditorHelper();
        }
        return helper;
    }
}

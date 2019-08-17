package camaieu.webform;



/**
 * Classe abstraite de base pour gérer des données hétérogènes
 * encapsulant des données de plusieurs tables à la fois.
 * <BR> Elle permet également de gérer les erreurs sur chacun des champs
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 16/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public abstract class AbstractHeteroData {
    /**
     * instance de RecordEditorHelper associé
     */
    private RecordEditorHelper helper;

    /**
     * constructer par défaut
     */
    public AbstractHeteroData() {
    }

    /**
     * l'instance de RecordEditorHelper associée
     * @return RecordEditorHelper
     */
    public RecordEditorHelper getHelper() {
        if (helper == null) {
            helper = new RecordEditorHelper();
        }
        return helper;
    }
}

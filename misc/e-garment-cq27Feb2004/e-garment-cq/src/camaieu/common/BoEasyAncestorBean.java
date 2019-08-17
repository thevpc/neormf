package camaieu.common;

import wg4.bean.ancestor.BoAncestorBean;
import wg4.bean.ancestor.TechniqueException;
import wg4.fwk.dataobject.DataObject;
import wg4.fwk.dataobject.IDoDescription;

/**
 * Classe de base pour les Bo.
 * <BR> Elle donne des facilit�s en plus que celles donn�es par BoAncestorBean.
 *
 * @author tbensalah (Taha Ben Salah, ADD'IT Tunisie)
 * @creation_date date 21/01/2004
 * @last_modification_date date 21/01/2004
 * @status pour validation
 */
public class BoEasyAncestorBean extends BoAncestorBean {

    /**
     * instance priv�e de DataObject
     */
    private DataObject sampleInstance;
    /**
     * instance priv�e de IDoDescription
     */
    private IDoDescription doDescInstance;

    /**
     * nom par d�faut de la datasource
     */
    private String defaultDatasourceName;

    /**
     * constructeur unique
     * @param someDoCalss la classe des DataObjects associ�s
     * @param defaultDatasourceName nom par d�faut de la datasource
     */
    public BoEasyAncestorBean(Class someDoCalss, String defaultDatasourceName) {
        this.defaultDatasourceName = defaultDatasourceName;
        try {
            sampleInstance = (DataObject) someDoCalss.newInstance();
        } catch (InstantiationException e) {
            throw new ClassCastException(someDoCalss + " is not a legal DataObject class");
        } catch (ClassCastException e) {
            throw new ClassCastException(someDoCalss + " is not a legal DataObject class");
        } catch (IllegalAccessException e) {
            throw new ClassCastException(someDoCalss + " is not a legal DataObject class");
        }
        doDescInstance = sampleInstance.getDescription();
    }

    /**
     * recharge toutes les donn�es du DataObject
     * decrit par la clef primaire donn�e par <code>object</code>
     * <BR> la datasource par d�faut sera utilis�e
     * @param object contient la clef (tous les champs de la clef primaire)
     * @return une nouvelle instance de DataObject contenant les champs charg�s
     * @throws TechniqueException
     */
    public DataObject reload(DataObject object) throws TechniqueException {
        return reload(defaultDatasourceName, object, null);
    }

    /**
     * recharge les champs <codes>fields</code> du DataObject
     * decrit par la clef primaire donn�e par <code>object</code>
     * <BR> la datasource par d�faut sera utilis�e
     * @param object contient la clef (tous les champs de la clef primaire)
     * @param fields la liste des champs � charger. si null, tous les champs seront charg�s
     * @return une nouvelle instance de DataObject contenant les champs charg�s
     * @throws TechniqueException
     */
    public DataObject reload(DataObject object, String[] fields) throws TechniqueException {
        return reload(defaultDatasourceName, object, fields);
    }

    /**
     * recharge les champs <codes>fields</code> du DataObject
     * decrit par la clef primaire donn�e par <code>object</code>
     * <BR> la datasource <code>poolName</code> sera utilis�e
     * @param poolName nom de la datasource
     * @param object contient la clef (tous les champs de la clef primaire)
     * @param fields la liste des champs � charger. si null, tous les champs seront charg�s
     * @return une nouvelle instance de DataObject contenant les champs charg�s
     * @throws TechniqueException
     */
    public DataObject reload(String poolName, DataObject object, String[] fields) throws TechniqueException {
        if (object == null) {
            throw new NullPointerException("Object could not be null");
        }
        // ce code est d�sactiv� � cause d'un probleme sur tomcat
//        if(sampleInstance.getClass().isAssignableFrom(object.getClass())){
//            throw new ClassCastException("Object must be from class "+sampleInstance.getClass()+" but class "+object.getClass()+" was found");
//        }
        StringBuffer sb = new StringBuffer();
        sb.append("Select ");
        if (fields == null || fields.length == 0) {
            sb.append("*");
        } else {
            for (int i = 0; i < fields.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(fields[i]);
            }
        }
        sb.append(" FROM ");
        sb.append(doDescInstance.getTableName());
        sb.append(" WHERE ");
        Object[] key = getKey(object);
        String[] pkKeys = doDescInstance.getPkColName();
        for (int i = 0; i < pkKeys.length; i++) {
            if (i > 0) {
                sb.append(" AND ");
            }
            sb.append(pkKeys[i]);
            sb.append("= ? ");
        }
        DataObject[][] o = retrieve(poolName, sb.toString(), key, new Class[]{sampleInstance.getClass()}, 0, 1);
        if (o.length == 0 || o[0].length==0) {
            return null;
        } else {
            return o[0][0];
        }
    }

    /**
     * charge les colonnes <code>fields</code> de toutes les lignes de la table
     * @param poolName nom de la datasource
     * @param fields les champs � charger, si nul tous les champs seront charg�s
     * @return
     * @throws TechniqueException
     */
    public DataObject[] retrieveAll(String poolName, String[] fields) throws TechniqueException {
        StringBuffer sb = new StringBuffer();
        sb.append("Select ");
        if (fields == null || fields.length == 0) {
            sb.append("*");
        } else {
            for (int i = 0; i < fields.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(fields[i]);
            }
        }
        sb.append(" FROM ");
        sb.append(doDescInstance.getTableName());
        return retrieve(poolName, sb.toString(), new Object[0], new Class[]{sampleInstance.getClass()})[0];
    }

    /**
     * r�cup�re la clef � partir d'un DataObject
     * @param dataObject contient la clef (tous les champs de la clef primaire)
     * @return la clef primaire
     * @throws IllegalArgumentException si l'un des champs de la clef est nul
     */
    public Object[] getKey(DataObject dataObject) throws IllegalArgumentException{
        int[] pkKeysIndexes = doDescInstance.getPkColNum();
        Object[] keyValues = new Object[pkKeysIndexes.length];
        for (int i = 0; i < keyValues.length; i++) {
            keyValues[i] = dataObject.get(pkKeysIndexes[i]);
            if (keyValues[i] == null) {
                throw new IllegalArgumentException("Key cannot be null. Expected non null value for key part (index " + i + ") " + doDescInstance.getTableName() + "." + doDescInstance.getPkColName()[i]);
            }
        }
        return keyValues;
    }
}

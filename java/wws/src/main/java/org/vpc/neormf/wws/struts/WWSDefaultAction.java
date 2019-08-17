package org.vpc.neormf.wws.struts;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.vpc.neormf.commons.beans.*;
import org.vpc.neormf.commons.ejb.EjbSessionBusinessDelegate;
import org.vpc.neormf.wws.webform.WWSForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 21 nov. 2006 11:42:32
 */
public class WWSDefaultAction extends WWSAbstractAction {
    public static final String WWS_SEARCH_PREFIX = "WWS_S_";
    public static final String WWS_ATTRIBUTE_EDIT_FORM = "WWS_ATTRIBUTE_EDIT_FORM";
    public static final String WWS_ATTRIBUTE_LIST_RESULT = "WWS_ATTRIBUTE_LIST_RESULT";
    public static final String WWS_ATTRIBUTE_LIST_FORM = "WWS_ATTRIBUTE_LIST_FORM";
    public static final String WWS_ATTRIBUTE_EDIT_IS_CLEAR_FORM = "WWS_ATTRIBUTE_EDIT_IS_CLEAR_FORM";
    private EjbSessionBusinessDelegate client;
    private DataTransfertObject proptotype = new DataTransfertObject();
    private PropertyList fieldToShowInEdit = null;
    private PropertyList fieldToSaveInEdit = null;
    private PropertyList fieldToShowInList = null;
    private PropertyList fieldCriteriaInList = null;
    private OrderList defaultOrder = null;


    public WWSDefaultAction(DataTransfertObject proptotype, PropertyList fieldToShow, PropertyList fieldToShowInList, PropertyList fieldToSaveInEdit, PropertyList fieldCriteriaInList, OrderList defaultOrder, String[] privileges) {
        super(privileges);
        this.proptotype = proptotype;     // c'est a dire type adresse par exemple
        String clientConnectorClassName = proptotype.info().getProperties().getProperty("ClientConnectorClassName");
        try {
            this.client = (EjbSessionBusinessDelegate) Class.forName(clientConnectorClassName).newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        this.fieldToShowInEdit = fieldToShow;  // les champs a afficher dans l'edit
        this.fieldToShowInList = fieldToShowInList; // les champs a afficher dans la liste
        this.fieldCriteriaInList = fieldCriteriaInList; // le critere de recherche pour afficher une liste
        this.fieldToSaveInEdit = fieldToSaveInEdit; // les colonnes mis � jour
        this.defaultOrder = defaultOrder;  // ordre d'afichage
    }


    public ActionForward executeEdit(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //d'abord essayer de recuperer les donnes de l'operation precedante (insert/update)
        WWSForm wajout = (WWSForm) request.getAttribute(WWS_ATTRIBUTE_EDIT_FORM);// de la page precedente
        DataTransfertObject recherche = getProptotype().info().createDTO();
        if (wajout == null) {// aucune donnes trouvee donc relire depuis la base
            DataTransfertObject ajout = proptotype.info().createDTO(); // base

            wajout = new WWSForm(request, ajout);
            PropertyList all = proptotype.info().createPropertyList();
            PropertyList theFieldsToShowInEdit = getFieldToShowInEdit();
            DataField[] keyFields = proptotype.info().getKeyFields();
            for (int i = 0; i < keyFields.length; i++) {
                all.addProperty(keyFields[i].getFieldName());
                theFieldsToShowInEdit.addProperty(keyFields[i].getFieldName());
            }

            wajout.fillFromRequest(all); //
            if (wajout.isCorrect()) {
                Method getMethod = client.getClass().getMethod("findByKey" + proptotype.info().getBeanName(), new Class[]{
                        proptotype.info().getPropertyListClass(),
                        proptotype.info().getDataKeyClass()
                });

                ajout = (DataTransfertObject) getMethod.invoke(client, new Object[]{theFieldsToShowInEdit, ajout.getDataKey()});
                wajout = new WWSForm(request, ajout);
                request.setAttribute(WWS_ATTRIBUTE_EDIT_FORM, wajout);
                return mapping.findForward("editjsp");
            } else {
                request.setAttribute(WWS_ATTRIBUTE_EDIT_FORM, null);
                return mapping.findForward("editjsp");
            }
        } else {
            return mapping.findForward("editjsp");
        }
    }

    public ActionForward executeList(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // creation du connecteur vers l'EJB SEssion (Methodes metier)
        //ReferencielClient client = new ReferencielClient();

        //creation d'un DTO (Data Transfert Object = Data Content) vide
        Boolean doClear = (Boolean) request.getAttribute(WWS_ATTRIBUTE_EDIT_IS_CLEAR_FORM);

        DataTransfertObject recherche = getProptotype().info().createDTO();
        recherche.setDTOValidatable(false);
        //Encapsulation du Data Content dans un object de presentation(HTML=WEB)
        WWSForm wrecherche = new WWSForm(request, recherche);
        wrecherche.setPrefix(WWS_SEARCH_PREFIX);
        // WWSForm wrecherche = new WWSForm(request,null);
        //remplir L'objet Wrecherche avec les parametre de la requete
        // validation du contenu du formulaire (l'entr�e) = lister les erreur
        if (doClear == null || !doClear.booleanValue()) {
            wrecherche.fillFromRequest(getFieldCriteriaInList());
        }

        DataTransfertObject proto = getProptotype().info().createDTO();
        proto.setDTOValidatable(false);
        for (Iterator rech = recherche.keySet().iterator(); rech.hasNext();) {
            String k = (String) rech.next();
            if (recherche.getProperty(k) == null) {
                rech.remove();
                //proto.setProperty(k,"%");
            } else {
                proto.setProperty(k, recherche.getProperty(k));
            }
        }

        //proto.addAllDeclaredProperties(recherche);

        Method getAllMethod = getClient().getClass().getMethod("find" + getProptotype().info().getBeanName(), new Class[]{
                getProptotype().info().getPropertyListClass(),
                getProptotype().info().getDTOClass(),
                OrderList.class
        });
        DataField[] keyFields = getProptotype().info().getKeyFields();
        PropertyList all = getFieldToShowInList();
        for (int i = 0; i < keyFields.length; i++) {
            all.addProperty(keyFields[i].getFieldName());
        }

        Collection allRecords = (Collection) getAllMethod.invoke(getClient(),
                new Object[]{
                        all,
                        proto,
                        getDefaultOrder()
                }
        );
        request.setAttribute(WWS_ATTRIBUTE_LIST_RESULT, allRecords);
        request.setAttribute(WWS_ATTRIBUTE_LIST_FORM, wrecherche);
        return mapping.findForward("listjsp");
    }

    public EjbSessionBusinessDelegate getClient() {
        return client;
    }

    public DataTransfertObject getProptotype() {
        return proptotype;
    }

    public PropertyList getFieldToShowInEdit() {
        return fieldToShowInEdit;
    }

    public PropertyList getFieldToShowInList() {
        return fieldToShowInList;
    }

    public PropertyList getFieldCriteriaInList() {
        return fieldCriteriaInList;
    }

    public OrderList getDefaultOrder() {
        return defaultOrder;
    }

    public ActionForward executeSave(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //createtion de l'objet manipul�
        DataTransfertObject data = getProptotype().info().createDTO();

        // creation d'un objet g�n�rique pour stoquer la clef
        DataTransfertObject idOnly = getProptotype().info().createDTO();
        WWSForm wdataIdOnly = new WWSForm(request, idOnly);

        //r�cup�ration des colonnes clefs
        DataField[] keyFields = getProptotype().info().getKeyFields();
        PropertyList colonnesClefs = getProptotype().info().createPropertyList();
        for (int i = 0; i < keyFields.length; i++) {
            colonnesClefs.addProperty(keyFields[i].getFieldName());
        }

        //lire la clef depuis l'url
        wdataIdOnly.fillFromRequest(colonnesClefs);

        //Encapsulation pour la minipulation Web
        WWSForm wdata = new WWSForm(request, data);
        boolean isUpdate = false;
        if (wdataIdOnly.isCorrect()) {
            //r�cuparation des param�tre pass�s
            wdata.fillFromRequest(colonnesClefs);
            wdata.fillFromRequest(getFieldToSaveInEdit());
            if (wdata.isCorrect()) {
                try {
                    Method findByKeyMethod = getClient().getClass().getMethod("defaultExists" + getProptotype().info().getBeanName(), new Class[]{
                            DataKey.class,
                    });
                    isUpdate = ((Boolean) findByKeyMethod.invoke(getClient(),
                            new Object[]{
                                    data.getDataKey(),
                            }
                    )).booleanValue();
                } catch (Throwable e) {
                    wdata.addError(e);
                }
            }
        }
        if (wdata.isCorrect()) {
            try {
                if (isUpdate) {
                    Method setDataMethod = getClient().getClass().getMethod("update" + getProptotype().info().getBeanName(), new Class[]{
                            getProptotype().info().getDTOClass(),
                    });
                    setDataMethod.invoke(getClient(),
                            new Object[]{
                                    data,
                            }
                    );
                    request.setAttribute(WWS_ATTRIBUTE_EDIT_FORM, null);
                    request.setAttribute(WWS_ATTRIBUTE_EDIT_IS_CLEAR_FORM, Boolean.TRUE);
                    return mapping.findForward("list");
                } else {
                    //r�cuparation des param�tre pass�s
                    wdata.fillFromRequest(getFieldToSaveInEdit());
                    //introspection (reflexion) de la m�thode � partir de son nom
                    Method addDataMethod = getClient().getClass().getMethod("add" + getProptotype().info().getBeanName(), new Class[]{
                            getProptotype().info().getDTOClass(),
                    });
                    //execution de la m�thode
                    addDataMethod.invoke(getClient(),
                            new Object[]{
                                    data,
                            }
                    );
                    request.setAttribute(WWS_ATTRIBUTE_EDIT_FORM, null);
                    request.setAttribute(WWS_ATTRIBUTE_EDIT_IS_CLEAR_FORM, Boolean.TRUE);
                    return mapping.findForward("list");
                }
            } catch (Exception e) {
                wdata.addError(e);
            }
        }
        request.setAttribute(WWS_ATTRIBUTE_EDIT_FORM, wdata);
        return mapping.findForward("edit");
    }

//
//    public ActionForward executeSave(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //createtion de l'objet manipul�
//        TypeAdresseData data=new TypeAdresseData();
//        TypeAdresseData idOnly=new TypeAdresseData();
//        WWSForm wdataIdOnly=new WWSForm(request,idOnly);
//        wdataIdOnly.fillFromRequest(new TypeAdressePropertyList().addIdTypeAdresse());
//        //Encapsulation pour la minipulation Web
//        WWSForm wdata=new WWSForm(request,data);
//        if(wdataIdOnly.isCorrect()){
//            //r�cuparation des param�tre pass�s
//            wdata.fillFromRequest(null);
//            if(wdata.isCorrect()){
//                try {
//                    ((ReferencielClient)getClient()).setDataTypeAdresse(data);
//                    return mapping.findForward("list");
//                } catch (Exception e) {
//                    wdata.addError(e);
//                }
//            }
//        }else{
//            //r�cuparation des param�tre pass�s
//            wdata.fillFromRequest(null);
//            if(wdata.isCorrect()){
//                try {
//                    ((ReferencielClient)getClient()).addTypeAdresse(data);
//                    return mapping.findForward("list");
//                } catch (Exception e) {
//                    wdata.addError(e);
//                }
//            }
//        }
//        request.setAttribute(WWS_ATTRIBUTE_EDIT_FORM,wdata);
//        return mapping.findForward("edit");
//    }//

    public ActionForward executeDelete(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //createtion de l'objet manipul�
        DataTransfertObject idOnly = getProptotype().info().createDTO();
        WWSForm wdataIdOnly = new WWSForm(request, idOnly);
        //r�cup�ration des colonnes clefs
        DataField[] keyFields = getProptotype().info().getKeyFields();
        PropertyList colonnesClefs = getProptotype().info().createPropertyList();
        for (int i = 0; i < keyFields.length; i++) {
            colonnesClefs.addProperty(keyFields[i].getFieldName());
        }
        wdataIdOnly.fillFromRequest(colonnesClefs);
        if (wdataIdOnly.isCorrect()) {
            try {
                Method removeDataMethod = getClient().getClass().getMethod("delete" + getProptotype().info().getBeanName(), new Class[]{
                        getProptotype().info().getDataKeyClass(),
                });
                removeDataMethod.invoke(getClient(),
                        new Object[]{
                                idOnly.getDataKey(),
                        }
                );
                return mapping.findForward("list");
            } catch (Exception e) {
                wdataIdOnly.addError(e);
            }
        }
        return mapping.findForward("list");
    }


    public PropertyList getFieldToSaveInEdit() {
        return fieldToSaveInEdit;
    }

    public void setFieldToSaveInEdit(PropertyList fieldToSaveInEdit) {
        this.fieldToSaveInEdit = fieldToSaveInEdit;
    }
}

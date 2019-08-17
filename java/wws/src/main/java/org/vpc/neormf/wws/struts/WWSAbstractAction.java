package org.vpc.neormf.wws.struts;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 21 nov. 2006 11:37:46
 */
public abstract class WWSAbstractAction extends Action {
    public static final String WWS_ATTRIBUTE_ACTION_OPERATION = "WWS_ATTRIBUTE_ACTION_OPEARTION";
    public static final String WWS_ATTRIBUTE_ACTION = "WWS_ATTRIBUTE_ACTION";
    private TreeSet neededRoles;

    protected WWSAbstractAction(String[] neededRoles) {
        this.neededRoles = neededRoles==null? new TreeSet() :  new TreeSet(Arrays.asList(neededRoles));
    }


    public Collection getNeededRoles() {
        return neededRoles;
    }

    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        try{
            // trait de secu

            return executeImpl(actionMapping, actionForm, httpServletRequest, httpServletResponse);    //To change body of overridden methods use File | Settings | File Templates.
        }catch(SecurityException e){
            e.printStackTrace();
            return actionMapping.findForward("wws_security_error");
        }catch(Throwable e){
            e.printStackTrace();
            return actionMapping.findForward("wws_error");
        }
    }

    public ActionForward executeImpl(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String gestOperation = request.getParameter("wws_operation");
        if(gestOperation==null){
            gestOperation=(String) request.getAttribute("wws_operation");
        }
        request.setAttribute(WWS_ATTRIBUTE_ACTION_OPERATION,gestOperation);
        request.setAttribute(WWS_ATTRIBUTE_ACTION,this);
//        String roleName=gestOperation+"_"+beanName;
        if ("edit".equals(gestOperation)) {                 //ecran saisie
            return executeEdit(mapping, action, request, response);
        } else if ("save".equals(gestOperation)) {      //ajout modification
            return executeSave(mapping, action, request, response);
        } else if ("list".equals(gestOperation)) {            // pour lister
            return executeList(mapping, action, request, response);
        } else if ("delete".equals(gestOperation)) {            //suppression
            return executeDelete(mapping, action, request, response);
        }
        return executeDefault(mapping, action, request, response);
    }

    public abstract ActionForward executeEdit(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public abstract ActionForward executeSave(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public abstract ActionForward executeDelete(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public abstract ActionForward executeList(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ActionForward executeDefault(ActionMapping mapping, ActionForm action, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new IllegalArgumentException("Operation non supportee");
    }
}

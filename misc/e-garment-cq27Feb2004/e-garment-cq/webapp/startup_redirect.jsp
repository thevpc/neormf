<%-- 
	// Page appelée après authentification dans login.jsp 
	// Module : authentification
	// Elle permet de rediriger vers supplier_selection.jsp 
	// ou suppliser_main_menu.jsp selon que l'utilisateur
	// authentifié est un adminstrateur ou un fournisseur simple
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie --%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>


<%@ page import="camaieu.egarmentcq.common.EGCQSessionConstants" %>
<%
	Boolean isAdmin= (Boolean) request.getSession().getAttribute(EGCQSessionConstants.IS_ADMIN);
	if(isAdmin==null){
		response.sendRedirect("loginAction.do");
	}else if(isAdmin.booleanValue()){
		response.sendRedirect("supplier_selection.jsp");
	}else{
		response.sendRedirect("supplier_main_menu.jsp");
	}
%>
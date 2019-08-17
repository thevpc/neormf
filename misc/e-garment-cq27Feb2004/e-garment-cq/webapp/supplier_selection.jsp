<%-- 
	// Page de selection du fournisseur
	// Module : administration
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>

<%@ page import="camaieu.egarmentcq.common.EGCQUtils,
                 camaieu.egarmentcq.common.EGCQSessionConstants,
                 camaieu.common.WUtils"%>
<%
    EGCQUtils.checkIsAdmin(request,response,null);
%>
<html>
<head>
<title><%=WUtils.getHtml(request,"TITLE")%></title>
<link rel="stylesheet" type="text/css" href="<%=WUtils.getPath(request,"b2b.css")%>">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

<table cellpadding=0 cellspacing=0 border="0">
<tr><td><img src="<%=WUtils.getImageUrl(request,"LOGO")%>" align=absmiddle></td>
<td style="padding-left: 10px;">
	<font class=pageTitre><%=WUtils.getHtml(request,"TITLE")%></font>
</td></tr></table>

<form name="form" action="supplierSelectionAction.do">
	<table cellpadding=5 cellspacing=0 border="0" align=center>
		<tr>
			<td colspan=2 align=right><a href="<%=WUtils.getHelpPath(request)%>" target=help><img src="<%=WUtils.getImageUrl(request,"HELP")%>" border="0" alt="<%=WUtils.getHtml(request,"HELP")%>" hspace="10"></a></td>
		</tr>
 		<tr>
			<td colspan=2>
 				<font class=lienMenu color="#800080"><%=WUtils.getHtml(request,"SUPPLIER_SELECTION_MSG")%></font>
        	</td>
      	</tr>
      	<tr>
      		<td>
      			<font class=lienMenu color="#800080"><b><%=WUtils.getHtml(request,"SUPPLIER_SELECTION_CODE")%></b></font>
      		</td>
      		<td>
      			<input type="text" name="<%=EGCQSessionConstants.SUPPLIER_CODE%>" class="font4">
      		</td>
      	</tr>
      	<tr><td colspan=2 height=15></td></tr>
      	<tr>
      		<td colspan=2 align=center>
                <input type="image" alt="<%=WUtils.getHtml(request,"SUBMIT")%>" border="0" name="submit" src="<%=WUtils.getImageUrl(request,"SUBMIT")%>" width="39" height="16">
      		</td>
      	</tr>
	</table>
</form>

</body>
</html>
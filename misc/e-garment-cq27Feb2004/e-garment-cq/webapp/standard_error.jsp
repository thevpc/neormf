<%-- 
	// Page d'erreur standard
	// Module : *
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>

<%@ page import="camaieu.egarmentcq.common.EGCQRequestConstants,
                 camaieu.common.WUtils" %>
<%
  String errorMsg=(String)request.getAttribute(EGCQRequestConstants.ERROR_MSG);
%>
<head>
<title><%=WUtils.getHtml(request,"TITLE")%></title>
<link rel="stylesheet" type="text/css" href="<%=WUtils.getPath(request,"b2b.css")%>">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

<table cellpadding=0 cellspacing=0 border="0">
<tr><td><img src='<%=WUtils.getImageUrl(request,"LOGO")%>' align=absmiddle></td>
<td style="padding-left: 10px;">
	<font class=pageTitre><%=WUtils.getHtml(request,"TITLE")%></font>
</td></tr></table>

<br><br><br>
<CENTER>
<Font class="erreur"><B>
<%
	if(errorMsg!=null){
		out.print(errorMsg);
	}else{
		out.print(WUtils.getHtml(request,"ERROR.BAD_PASSWORD"));
	}
%>
</B></Font>
<CENTER>
<BR><BR><BR>
<%
	String errorRedirect=(String)request.getAttribute(EGCQRequestConstants.ERROR_REDIRECT);
	errorRedirect=WUtils.getPath(request,errorRedirect);
	out.print("<a href='"+errorRedirect+"'>");
	out.print(WUtils.getHtml(request,"BACK"));
	out.print("</a>");
%>

</body>
</html>

<%-- 
	// Page d'authentification
	// Module : authentification
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>

<%@ page import="camaieu.common.WUtils" %>

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
<table align=center width="1013" height="500" cellpadding=0 cellspacing=0 border="0">
	<tr>
		<td align=center valign=middle>
        <font class="tableauTitre">
            <%=WUtils.getHtml(request,"EXIT.MSG")%>
        </font></td>
	</tr>
</table>

<BR><BR><BR>
</body>
</html>

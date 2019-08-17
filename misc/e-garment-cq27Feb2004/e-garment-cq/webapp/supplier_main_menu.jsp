<%-- 
	// Page de menu principal du fournisseur
	// Module : fournisseur
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>

<%@ page import="camaieu.common.WUtils,
                 java.sql.Timestamp"%>
<html>
<head>
<title><%=WUtils.getHtml(request,"TITLE")%></title>
<link rel="stylesheet" type="text/css" href="<%=WUtils.getPath(request,"b2b.css")%>">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" background="<%=WUtils.getImageUrl(request,"BKG")%>" leftmargin="0" topmargin="0" style="BackGround-Repeat: No-Repeat;">

<table cellpadding=0 cellspacing=0 border="0">
<tr><td><img src="<%=WUtils.getImageUrl(request,"LOGO")%>" align=absmiddle></td>
<td style="padding-left: 10px;">
	<font class=pageTitre><%=WUtils.getHtml(request,"TITLE")%></font>
</td></tr></table>

<table border="0" cellpadding=5 cellspacing=0 style="margin-top: 50px; margin-left:90px;">
  <tr>
  	<td align=right><a href="<%=WUtils.getHelpPath(request)%>" target=help><img src="<%=WUtils.getImageUrl(request,"HELP")%>" border="0"></a></td>
  </tr>
  <tr>
  	<td align=center>
  		<font class=TableauTitre><%=WUtils.getHtml(request,"SUPPLIER_MAIN_MENU.TITLE")%></font>
  	</td>
  </tr>

  <tr>
  	<td style="padding-top: 20px;"><a href="<%=WUtils.getPath(request,"planningAction.do")%>" class=lienMenu><%=WUtils.getHtml(request,"SUPPLIER_MAIN_MENU.PLANNING.TITLE")%></a></td>
  </tr>
  <tr>
  	<td style="padding-left: 30px;"><%=WUtils.getHtml(request,"SUPPLIER_MAIN_MENU.PLANNING.MSG")%></td>
  </tr>

  <tr>
  	<td style="padding-top: 20px;"><a href=<%=WUtils.getPath(request,"itemsDeliveryReceiptsAction.do")%> class=lienMenu><%=WUtils.getHtml(request,"SUPPLIER_MAIN_MENU.DELIVERY_RECEIPTS.TITLE")%></a></td>
  </tr>
  <tr>
  	<td style="padding-left: 30px;"><%=WUtils.getHtml(request,"SUPPLIER_MAIN_MENU.DELIVERY_RECEIPTS.MSG")%></td>
  </tr>

</table>

</body>
</html>

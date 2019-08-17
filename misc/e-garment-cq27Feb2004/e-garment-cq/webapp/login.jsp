 <%--
	// Page d'authentification
	// Module : authentification
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>

<%@ page import="camaieu.common.WUtils"%>
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

<br><br><br>
<center>
  <form method="POST" name="form" action='<%= response.encodeURL("j_security_check") %>' >
    <table border="0">
      <tr>
        <td><%=WUtils.getHtml(request,"LOGIN")%></td>
        <td><input type="text"maxsize=20 maxlength=20 name="j_username"></td>
      </tr>
      <tr>
        <td><%=WUtils.getHtml(request,"PASSWORD")%></td>
        <td><input type="password" name="j_password"></td>
      </tr>
      <tr>
        <td colspan="2" align="middle">
          <div align="center">
            <input type="image" alt="<%=WUtils.getHtml(request,"SUBMIT")%>" border="0" name="submit" src="<%=WUtils.getImageUrl(request,"SUBMIT")%>" width="39" height="16">
          </div>
    </td></tr></table>
    <p>&nbsp;
  </p></form>
</center>
</body>
</html>

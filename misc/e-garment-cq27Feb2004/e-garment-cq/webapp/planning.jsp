<%-- 
	// Page d'authentification
	// Module : authentification
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 14/01/2004 --%>
<%-- @last_modification_date 26/01/2004 --%>
<%-- @status pour validation --%>

<%@ page import="camaieu.egarmentcq.common.EGCQUtils,
                 camaieu.egarmentcq.common.EGCQRequestConstants,
                 camaieu.egarmentcq.bo.BoDtCmdeAchatTete,
                 camaieu.egarmentcq.dataobject.DoXnFour,
                 camaieu.egarmentcq.common.EGCQSessionConstants,
                 camaieu.egarmentcq.common.EGCQBusinessConstants,
                 camaieu.common.WUtils"%>
<%
    DoXnFour doXnFour=(DoXnFour) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_DO);
    BoDtCmdeAchatTete.CommandeData[] commandes=(BoDtCmdeAchatTete.CommandeData[]) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_PLANNING);
    if(commandes==null){
        request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
        response.sendRedirect("standard_error.jsp");
    }
    String errorMsg=(String) request.getAttribute(EGCQRequestConstants.ERROR_MSG);
%>
<html>
<head>
<title><%=WUtils.getHtml(request,"TITLE")%></title>
<link rel="stylesheet" type="text/css" href="<%=WUtils.getPath(request,"b2b.css")%>">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

<FORM method="POST" action="<%=WUtils.getPath(request,"planningApplyAction.do")%>">
<table cellpadding=0 cellspacing=0 border="0" width="100%">
	<tr>
		<td rowspan=2 width=85><img src="<%=WUtils.getImageUrl(request,"LOGO")%>" align=absmiddle></td>
		<td style="padding-left: 10px;" rowspan=2>
			<font class=pageTitre><%=WUtils.getHtml(request,"TITLE")%></font>
			<br><font class=nomFournisseur><b><%=WUtils.getHtml(request,"PLANNING.TITLE")%></b>
            <br><%=doXnFour==null ? WUtils.getHtml(request,"UNKNOWN"):WUtils.toHtml(request, doXnFour.getFouNom())%>
            <br><%=doXnFour==null ? "":(WUtils.toHtml(request,doXnFour.getFouAdr1())+", "+WUtils.toHtml(request,doXnFour.getFouPostal(),"")+" "+WUtils.toHtml(request,doXnFour.getFouVille(),""))%>
            </font>
		</td>
		<td></td>
		<td height=73></td>
	</tr>
	<tr>
		<td height=20></td>
		<td rowspan=2 align=right style="padding-bottom: 10px;">
            <a href="<%=WUtils.getPath(request,"supplier_main_menu.jsp")%>"><img src="<%=WUtils.getImageUrl(request,"BACK")%>" alt="<%=WUtils.getHtml(request,"PLANNING.BACK")%>" hspace="10" border="0"></a>
            <input type="image" alt="<%=WUtils.getHtml(request,"SAVE")%>" border="0" name="submit" src="<%=WUtils.getImageUrl(request,"SAVE")%>" size="8">
            <img src="<%=WUtils.getImageUrl(request,"PRINT")%>" hspace="10" onClick="window.print();" alt="<%=WUtils.getHtml(request,"PLANNING.PRINT")%>" border="0" >
            <a href="planningAction.do?<%=EGCQSessionConstants.EXPORT_TYPE%>=<%=EGCQUtils.EXPORT_TYPE_PLAIN_TEXT%>"><img src="<%=WUtils.getImageUrl(request,"EXPORT")%>" hspace="10" alt="<%=WUtils.getHtml(request,"PLANNING.EXPORT")%>" border="0" ></a>
            <a href="<%=WUtils.getHelpPath(request)%>" target="help"><img src="<%=WUtils.getImageUrl(request,"HELP")%>" alt="<%=WUtils.getHtml(request,"HELP")%>" hspace="10" border="0"></a>
            <a href="logoutAction.do"><img border="0" src="<%=WUtils.getImageUrl(request,"LOGOUT")%>" hspace="10"  alt="<%=WUtils.getHtml(request,"LOGOUT")%>"></a>
        </td>
	</tr>
	<tr>
		<td colspan=2 height=32></td>
		<td></td>
	</tr>
</table>
<CENTER>
<Font class="erreur">
    <%
        if(errorMsg!=null){
            out.print(errorMsg);
            %><BR><BR><%
        }
    %>
</Font>
</CENTER>
<table border="0" cellspacing="1" cellpadding="2" bordercolor="#01304B">
  <tr ALIGN="CENTER" BGCOLOR="#F5D0AF">
    <th><B><%=WUtils.getHtml(request,"PLANNING.SUPPLIER")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.PROD_PLACE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.ORDER_NBR")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.ITEM_CODE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.ITEM_NAME")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.COLOR_CODE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.COLOR")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.ORDERED_QTY")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.ORDERED_DATE_PLANNING")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.DEPART_DATE_SHIFT")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.INSPECT_DATE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.INSPECT_SHIFT")%></B></th>
    <th><B><%=WUtils.getHtml(request,"PLANNING.INSPECT_TYPE")%></B></th>
    <th>&nbsp;</td>
    <th>&nbsp;</td>
    <th>&nbsp;</td>
  </tr>
  <%
  boolean firstDeletedItem=true;
  for(int i=0;i<commandes.length;i++){
    String rowBkColor=((i%2)==0)? "#FAE4D1":"#FAE9E1";
    if(EGCQBusinessConstants.INDEX_DELETED.equals(commandes[i].getRstIndex())){
        if(firstDeletedItem){
            %><tr ALIGN="LEFT"><td colspan="16"><B><%=WUtils.getHtml(request,"PLANNING.DELETED_ITEMS")%><B></td></tr><%
            firstDeletedItem=false;
        }
        rowBkColor="RED";
    }
    %>
        <tr ALIGN="CENTER" BGCOLOR="<%=rowBkColor%>">
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getFouNom())%></td>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getDepNom())+" "+WUtils.toHtml(request,commandes[i].getDepPostal())+" "+WUtils.toHtml(request,commandes[i].getDepVille())+" "%></td>

<%--        <td nowrap>RCS_RAM_RAT_NO_CMDE + '-' + RCS_RAM_RAT_NO_VERSION</td>--%>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCatNoCmde())+"-"+WUtils.toHtml(request,commandes[i].getCatNoVersion())%></td>

<%--        <td nowrap>RCS_RAM_ART_CODE</td>--%>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCamArtCode())%></td>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getModDes())%></td>

<%--        <td>RCS_RAM_ART_VAR1</td>--%>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCamArtVar1())%></td>

        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCouLib())%></td>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCamQteCmde())%></td>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCatDtCmde())%></td>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getCatDtDepart2())%></td>
        <td nowrap><%=WUtils.toHtml(request,commandes[i].getRstDtCtrlSourcePrev())%></td>
        <td nowrap>
        <%
            if(EGCQBusinessConstants.INDEX_DELETED.equals(commandes[i].getRstIndex())){
                // TODO    : je n'utilise pas les tags droits pour le html (c'est buggé selon Miachael)
                %><%=WUtils.toHtml(request,commandes[i].getRstDtCtrlSourceSouhait(),"")%><Input Type="hidden" name="rstDtCtrlSourceSouhait" value="<%=WUtils.toHtml(request,commandes[i].getRstDtCtrlSourceSouhait(),"",-1)%>"><%
            }else{
                commandes[i].getHelper().startHtmlEditor(request,out,"rstDtCtrlSourceSouhait");
                %><Input Type="text" name="rstDtCtrlSourceSouhait" value="<%=commandes[i].getHelper().getHtmlEditingValue(request,"rstDtCtrlSourceSouhait",commandes[i].getRstDtCtrlSourceSouhait())%>"><%
                commandes[i].getHelper().endHtmlEditor(request,out,"rstDtCtrlSourceSouhait");
            }
        %>
        </td>

<%--        <td>RCS_NO_CONTROLE</td>--%>
        <td nowrap><%=WUtils.getHtml(request,"PLANNING.RST_NO_CONTROLE.PATTERN",new Object[]{commandes[i].getRstNoControle()})%></td>

        <td BGCOLOR="#FED9D3" nowrap>
        <% if(!EGCQBusinessConstants.INDEX_DELETED.equals(commandes[i].getRstIndex())){%>
            <a href="inspectionReportAction.do?index=<%=i%>"><img src=<%=WUtils.getImageUrl(request,"INPUT")%> border="0" alt="<%=WUtils.getHtml(request,"PLANNING.EDIT_QUALITY_REPORT")%>"></a>
        <%}%>
        </td>
		<td BGCOLOR="#FED9D3" nowrap>
        <% if(!EGCQBusinessConstants.INDEX_DELETED.equals(commandes[i].getRstIndex())){%>
            <a href="orderReportAction.do?index=<%=i%>" target="_blank"><img src=<%=WUtils.getImageUrl(request,"PRINT")%> vspace=2 hspace=2 border="0" alt="<%=WUtils.getHtml(request,"PLANNING.PRINT_BC")%>"></a>
        <%}%>
        </td>
        <td BGCOLOR="#FED9D3" nowrap>
        <% if(!EGCQBusinessConstants.INDEX_DELETED.equals(commandes[i].getRstIndex())){%>
            <a href="labelReportAction.do?index=<%=i%>" target="_blank"><img src=<%=WUtils.getImageUrl(request,"LABEL")%> border="0" alt="<%=WUtils.getHtml(request,"PLANNING.PRINT_TICKETS")%>"></a>
        <%}%>
        </td>
        </tr>
      <%
  }
  %>
</table>
</FORM>
</body>
</html>

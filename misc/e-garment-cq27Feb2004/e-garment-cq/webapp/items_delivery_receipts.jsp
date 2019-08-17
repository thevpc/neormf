 <%--
	// Page d'authentification
	// Module : rapport d'inspection
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 21/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>
<%@ page import="camaieu.common.WUtils,
                 camaieu.egarmentcq.common.EGCQSessionConstants,
                 camaieu.egarmentcq.dataobject.DoXnFour,
                 camaieu.egarmentcq.common.EGCQUtils,
                 camaieu.egarmentcq.bo.BoDtEnvoiCqSource,
                 java.sql.Timestamp,
                 camaieu.egarmentcq.common.EGCQRequestConstants"%>
<%
    DoXnFour doXnFour=(DoXnFour) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_DO);
    BoDtEnvoiCqSource.ItemDeliveryReceiptData[] receiptsData=(BoDtEnvoiCqSource.ItemDeliveryReceiptData[]) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_ITEM_DELIVERY_RECEIPTS);
    if(receiptsData==null){
        request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
        response.sendRedirect("standard_error.jsp");
    }
    String errorMsg=(String) request.getAttribute(EGCQRequestConstants.ERROR_MSG);
    //TODO je n'utilise pas les tags droits pour le html (il sont buggés selon michael/arnaud philippe)
%>
<html>
<head>
<title><%=WUtils.getHtml(request,"TITLE")%></title>
<link rel="stylesheet" type="text/css" href="<%=WUtils.getPath(request,"b2b.css")%>">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

<FORM method="POST" action="<%=WUtils.getPath(request,"itemsDeliveryReceiptsApplyAction.do")%>">
<table cellpadding=0 cellspacing=0 border="0" width="100%">
	<tr>
		<td rowspan=2 width=85><img src="<%=WUtils.getImageUrl(request,"LOGO")%>" align=absmiddle></td>
		<td style="padding-left: 10px;" rowspan=2>
			<font class=pageTitre><%=WUtils.getHtml(request,"TITLE")%></font>
			<br><font class=nomFournisseur><b><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.TITLE")%></b>
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
            <input type="image" alt="<%=WUtils.getHtml(request,"SAVE")%>" border="0" name="submit" src="<%=WUtils.getImageUrl(request,"SAVE")%>">
            <img border="0" src="<%=WUtils.getImageUrl(request,"PRINT")%>" hspace="10" onClick="window.print();" alt="<%=WUtils.getHtml(request,"PLANNING.PRINT")%>">
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
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.SUPPLIER_NAME")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.FAMILY_CODE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.FAMILY")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.ITEM_REFERENCE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.ITEM")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.COLOR_CODE")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.CAMAIEU_COLOR")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.SIZES")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.SAMPLE_SENT")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.SAMPLE_DELIVERED")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.SAMPLE_TRAKLING_NBR")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.COLOR_SAMPLE_SENT")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.COLOR_SAMPLE_DELIVERED")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.COLOR_SAMPLE_TRAKING_NBR")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.TECH_FILE_SENT")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.TECH_FILE_DELIVERED")%></B></th>
    <th><B><%=WUtils.getHtml(request,"ITEMS_DELIVERY_RECEIPTS.TECH_FILE_TRAKING_NBR")%></B></th>
  </tr>
  <%for(int i=0;i<receiptsData.length;i++){
    String rowBkColor=((i%2)==0)? "#FAE4D1":"#FAE9E1";
  %>
        <tr ALIGN="CENTER" BGCOLOR="<%=rowBkColor%>">
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getFouNom())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getEcsFaaCode())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getFaaLib())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getEcsArtCode())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getModDes())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getEcsArtVar1())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getCouLib())%></td>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getTaiMin())%> - <%=WUtils.toHtml(request,receiptsData[i].getTaiMax())%></td>

            <%-- EcsDtEnvoiEchan--%>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getEcsDtEnvoiEchan())%></td>
            <td nowrap>
                <%
                //ESC_DT_RECEP_ECHAN
                    Timestamp ecsDtRecepEchan = receiptsData[i].getEcsDtRecepEchan();
                    if(ecsDtRecepEchan==null){
                %><Input type="checkbox" name="ecsDtRecepEchan"><%
                    }else{
                        out.print(WUtils.getHtml(request,"OK"));
                %><Input type="hidden" name="ecsDtRecepEchan" value="">
                <%
                    }
                %>
             </td>
             <td nowrap>
                <%receiptsData[i].getHelper().startHtmlEditor(request,out, "ecsNoColisEchan");%>
                <Input Type="text" name="ecsNoColisEchan" value="<%=receiptsData[i].getHelper().getHtmlEditingValue(request,"ecsNoColisEchan",receiptsData[i].getEcsNoColisEchan())%>" size="8">
                <%receiptsData[i].getHelper().endHtmlEditor(request,out, "ecsNoColisEchan");%>
             </td>


            <%-- EcsDtEnvoiBib--%>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getEcsDtEnvoiBib())%></td>
            <td nowrap>
                <%
                  Timestamp ecsDtRecepBib = receiptsData[i].getEcsDtRecepBib();
                  if(ecsDtRecepBib==null){
                        %><Input type="checkbox" name="ecsDtRecepBib"><%
                  }else{
                      out.print(WUtils.getHtml(request,"OK"));
                        %><Input type="hidden" name="ecsDtRecepBib" value=""><%
                  }
                %>
             </td>
             <td nowrap>
                <%receiptsData[i].getHelper().startHtmlEditor(request,out, "ecsNoColisBib");%>
                <Input Type="text" name="ecsNoColisBib" value="<%=receiptsData[i].getHelper().getHtmlEditingValue(request,"ecsNoColisBib",receiptsData[i].getEcsNoColisBib())%>" size="8">
                <%receiptsData[i].getHelper().endHtmlEditor(request,out, "ecsNoColisBib");%>
             </td>

            <%-- EcsDtEnvoiDtech--%>
            <td nowrap><%=WUtils.toHtml(request,receiptsData[i].getEcsDtEnvoiDtech())%></td>
            <td nowrap>
                <%
                  Timestamp ecsDtRecepDtech = receiptsData[i].getEcsDtRecepDtech();
                  if(ecsDtRecepDtech==null){
                       %><Input type="checkbox" name="ecsDtRecepDtech"><%
                  }else{
                      out.print(WUtils.getHtml(request,"OK"));
                    %><Input type="hidden" name="ecsDtRecepDtech" value=""><%
                  }
                %>
             </td>
             <td nowrap>
                <%receiptsData[i].getHelper().startHtmlEditor(request,out, "ecsNoColisDtech");%>
                <Input Type="text" name="ecsNoColisDtech" value="<%=receiptsData[i].getHelper().getHtmlEditingValue(request,"ecsNoColisDtech",receiptsData[i].getEcsNoColisDtech())%>" size="8">
                <%receiptsData[i].getHelper().endHtmlEditor(request,out, "ecsNoColisDtech");%>
             </td>

        </tr>
  <%
    }
  %>
</table>
</FORM>
</body>
</html>

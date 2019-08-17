<%--
	// Page d'authentification
	// Module : rapport d'inspection
--%>
<%-- @author taha Ben Salah, ADD'IT Tunisie--%>
<%-- @creation_date 19/01/2004 --%>
<%-- @last_modification_date 21/01/2004 --%>
<%-- @status pour validation --%>
<%@ page import="camaieu.egarmentcq.dataobject.DoXnFour,
                 camaieu.egarmentcq.common.EGCQSessionConstants,
                 camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceTete,
                 camaieu.webform.RecordEditorHelper,
                 camaieu.egarmentcq.dataobject.DoDtRecepCtrlSourceColis,
                 camaieu.egarmentcq.dataobject.DoDtRecepCtrlSource,
                 camaieu.egarmentcq.common.EGCQBusinessConstants,
                 camaieu.common.WUtils,
                 camaieu.egarmentcq.common.EGCQRequestConstants,
                 camaieu.egarmentcq.common.InspectionReportUploadHelper,
                 java.io.File"%>
<%
    DoXnFour doXnFour=(DoXnFour) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_DO);
    DoDtRecepCtrlSourceTete doDtRecepCtrlSourceTete=(DoDtRecepCtrlSourceTete)request.getSession().getAttribute(EGCQSessionConstants.DO_DT_RECEPT_CTRL_SOURCE_TETE);
    InspectionReportUploadHelper fileUploadHelper=(InspectionReportUploadHelper) request.getAttribute(EGCQRequestConstants.UPLOAD_HELPER);
    if(doDtRecepCtrlSourceTete==null || fileUploadHelper==null){
        request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
        response.sendRedirect("standard_error.jsp");
    }

    DoDtRecepCtrlSourceColis[] colis =  doDtRecepCtrlSourceTete.getDtRecepCtrlSourceColis();
    DoDtRecepCtrlSource [] tests =  doDtRecepCtrlSourceTete.getDtRecepCtrlSources();
    RecordEditorHelper helper=doDtRecepCtrlSourceTete.getHelper();
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

<FORM name="form" method="POST" action="<%=WUtils.getPath(request,"inspectionReportApplyAction.do")%>" ENCTYPE="multipart/form-data">
<table cellpadding=0 cellspacing=0 border="0" width="100%">
	<tr>
		<td rowspan=2 width=85><img src="<%=WUtils.getImageUrl(request,"LOGO")%>" align=absmiddle></td>
		<td style="padding-left: 10px;" rowspan=2>
			<font class=pageTitre><%=WUtils.getHtml(request,"TITLE")%></font>
			<br><font class=nomFournisseur><b><%=WUtils.getHtml(request,"INSPECTION_REPORT.FILL_IN")%></b>
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
            <a href="<%=WUtils.getPath(request,"planning.jsp")%>"><img src="<%=WUtils.getImageUrl(request,"BACK")%>" alt="<%=WUtils.getHtml(request,"BACK")%>" hspace="10" border="0"></a>
            <input type="image" alt="<%=WUtils.getHtml(request,"SAVE")%>" border="0" name="submit" src="<%=WUtils.getImageUrl(request,"SAVE")%>">
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

<TABLE WIDTH='80%' BORDER=0 BORDERCOLOR='DC8F85' CELLPADDING=2 CELLSPACING=1 align=center>
<TR BGCOLOR="#FAE4D1">
	<TD COLSPAN=4 ALIGN='center' style="padding-top: 5px; padding-bottom: 5px">
		<FONT SIZE ="+1"><%=WUtils.getHtml(request,"INSPECTION_REPORT.TITLE")%> : <B>Order <%=doDtRecepCtrlSourceTete.getRstCamNoCmde()%></B></FONT>
	</TD>
</TR>
<TR>
	<TD COLSPAN=4 BGCOLOR="#F5D0AF" ALIGN='center' style="padding-top: 5px; padding-bottom: 5px">
		<B><FONT SIZE ="+1"><%=WUtils.getHtml(request,"INSPECTION_REPORT.GENERAL")%></FONT></B>
	</TD>
</TR>
<TR BGCOLOR="#FAE4D1">
	<TD width="25%" nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.CODE_ITEM")%></TD>
	<TD width="25%" nowrap><%=doDtRecepCtrlSourceTete.getExtraArtCode()%> - <%=doDtRecepCtrlSourceTete.getExtraArtName()%></TD>
	<TD width="25%" nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.CODE_COLOR")%></TD>
	<TD width="25%" nowrap><%=doDtRecepCtrlSourceTete.getExtraColorCode()%> - <%=doDtRecepCtrlSourceTete.getExtraColorName()%></TD>
</TR>
<TR BGCOLOR="#FAE4D1">
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.PART_SENT_TO_LAB")%></TD>
	<TD nowrap>
    <%helper.startHtmlEditor(request,out,"rstDtCtrlSourceLabo");%>
    <Input Type="text" name="rstDtCtrlSourceLabo" value="<%=helper.getHtmlEditingValue(request,"rstDtCtrlSourceLabo",doDtRecepCtrlSourceTete.getRstDtCtrlSourceLabo())%>">
    <%helper.endHtmlEditor(request,out,"rstDtCtrlSourceLabo");%>
    </TD>
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.INSPECTION_TYPE")%></TD>
	<TD nowrap><%=WUtils.getHtml(request,"PLANNING.RST_NO_CONTROLE.PATTERN",new Object[]{doDtRecepCtrlSourceTete.getRstNoControle()})%></TD>
</TR>
<TR BGCOLOR="#FAE4D1">
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.INSPECTION_DATE")%></TD>
	<TD nowrap>
    <%helper.startHtmlEditor(request,out,"rstDtCtrlSourceReel");%>
    <Input Type="text" name="rstDtCtrlSourceReel" value="<%=helper.getHtmlEditingValue(request,"rstDtCtrlSourceReel",doDtRecepCtrlSourceTete.getRstDtCtrlSourceReel())%>">
    <%helper.endHtmlEditor(request,out,"rstDtCtrlSourceReel");%>
    </TD>
	<TD colspan=2>&nbsp;</TD>
</TR>
<TR>
	<TD COLSPAN=4 BGCOLOR="#F5D0AF" ALIGN='center' style="padding-top: 5px; padding-bottom: 5px" nowrap>
		<B><FONT SIZE ="+1"><%=WUtils.getHtml(request,"INSPECTION_REPORT.CONTROL")%></FONT></B>
	</TD>
</TR>
<TR BGCOLOR="#FAE4D1">
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.QTY_READY_FOR_INSPECTION")%></TD>
	<TD nowrap>
        <%helper.startHtmlEditor(request,out,"rstQtePresCtrlSource");%>
        <Input Type="text" name="rstQtePresCtrlSource" value="<%=helper.getHtmlEditingValue(request,"rstQtePresCtrlSource",doDtRecepCtrlSourceTete.getRstQtePresCtrlSource())%>">
        <%helper.endHtmlEditor(request,out,"rstQtePresCtrlSource");%>
    </TD>
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.QTY_PICKED_UP")%></TD>
	<TD nowrap>
        <%helper.startHtmlEditor(request,out,"rstQtePrelCtrlSource");%>
        <Input Type="text" name="rstQtePrelCtrlSource" value="<%=helper.getHtmlEditingValue(request,"rstQtePrelCtrlSource",doDtRecepCtrlSourceTete.getRstQtePrelCtrlSource())%>">
        <%helper.endHtmlEditor(request,out,"rstQtePrelCtrlSource");%>
    </TD>
</TR>

<%
    int max=tests.length;
    if(max%2==1){
        max++;
    }
    for(int i=0;i<max;i++){
        if(i%2==0){
           %><TR BGCOLOR="#FAE4D1"><%
        }
            if(i<tests.length){
                %><TD nowrap>
                    <%=WUtils.toHtml(request,tests[i].getExtraCccDescripcion1())%>
                  </TD>
                  <TD nowrap>
                    <%
                    if(tests[i].getRcsPrestataire()!=null){
                    %>
                        <%=WUtils.getHtml(request,"INSPECTION_REPORT.CONTROLLED_BY",new Object[]{tests[i].getExtraRcsPrestataireNom()})%>
                        <BR>
                    <%}%>
                    <select name="rcsCtrlCritere">
                        <option value=""   <%=WUtils.valueByComp(null,tests[i].getRcsCtrlCritere(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_NULL")%></option>
                        <option value="CA" <%=WUtils.valueByComp("CA",tests[i].getRcsCtrlCritere(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_CA")%></option>
                        <option value="NA" <%=WUtils.valueByComp("NA",tests[i].getRcsCtrlCritere(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_NA")%></option>
                        <option value="NC" <%=WUtils.valueByComp("NC",tests[i].getRcsCtrlCritere(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_NC")%></option>
                    </select>
                    <br>
                    <%helper.startHtmlEditor(request,out,"rcsValeurSaisie");%>
                        <textarea name="rcsValeurSaisie"><%=helper.getHtmlEditingValue(request,"rcsValeurSaisie",tests[i].getRcsValeurSaisie())%></textarea>
                    <%helper.endHtmlEditor(request,out,"rcsValeurSaisie");%>
                  </TD>
                <%
            }else{
                %><TD colspan=2>&nbsp;</TD><%
            }
        %><%
        if(i%2==1){
            %></TR><%
        }
    }
%>
<TR BGCOLOR="#FAE4D1">
<TD nowrap><u><%=WUtils.getHtml(request,"INSPECTION_REPORT.FINAL_DECISION")%></u></TD>
<TD nowrap>
                    <select name="rstOkCtrlSource">
                        <option value=""   <%=WUtils.valueByComp(null,doDtRecepCtrlSourceTete.getRstOkCtrlSource(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_NULL")%></option>
                        <option value="CA" <%=WUtils.valueByComp("CA",doDtRecepCtrlSourceTete.getRstOkCtrlSource(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_CA")%></option>
                        <option value="NA" <%=WUtils.valueByComp("NA",doDtRecepCtrlSourceTete.getRstOkCtrlSource(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_NA")%></option>
                        <option value="NC" <%=WUtils.valueByComp("NC",doDtRecepCtrlSourceTete.getRstOkCtrlSource(),"selected=\"selected\"","")%>><%=WUtils.getHtml(request,"INSPECTION_REPORT.CRITERE_NC")%></option>
                    </select>
                  <br>
                    <%helper.startHtmlEditor(request,out,"rstCommentaire");%>
                  <textarea name="rstCommentaire" ><%=helper.getHtmlEditingValue(request,"rstCommentaire",doDtRecepCtrlSourceTete.getRstCommentaire())%></textarea>
                    <%helper.endHtmlEditor(request,out,"rstCommentaire");%>
</TD>
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.PICKED_UP_CARTON_NBRS")%></TD>
	<TD nowrap>
    <%helper.startHtmlEditor(request,out,"rccNoColis");%>
        <%String rccNoColis="";
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<colis.length;i++){
                if(i>0){
                    sb.append(";");
                }
                sb.append(colis[i].getRscNoColis());
            }
            rccNoColis=sb.toString();
        %>
        <textarea name="rccNoColis"><%=helper.getHtmlEditingValue(request,"rccNoColis",rccNoColis)%></textarea>
    <%helper.endHtmlEditor(request,out,"rccNoColis");%>
    </TD>
</TR>

<%--<TR BGCOLOR="#FAE4D1">--%>
<%--	<TD>Lab tests</TD>--%>
<%--	<TD><select><option><option>Conforme accepté<option>Non conforme accepté<option>Non conforme</select><br><textarea></textarea></TD>--%>
<%--	<TD>Aspect conformity</TD>--%>
<%--	<TD><select><option><option>Conforme accepté<option>Non conforme accepté<option>Non conforme</select><br><textarea></textarea></TD>--%>
<%--</TR>--%>
<%--<TR BGCOLOR="#FAE4D1">--%>
<%--	<TD>Packaging</TD>--%>
<%--	<TD><select><option><option>Conforme accepté<option>Non conforme accepté<option>Non conforme</select><br><textarea></textarea></TD>--%>
<%--	<TD>Measurements</TD>--%>
<%--	<TD><select><option><option>Conforme accepté<option>Non conforme accepté<option>Non conforme</select><br><textarea></textarea></TD>--%>
<%--</TR>--%>
<%--<TR BGCOLOR="#FAE4D1">--%>
<%--	<TD>Defects</TD>--%>
<%--	<TD><select><option><option>Conforme accepté<option>Non conforme accepté<option>Non conforme</select><br><textarea></textarea></TD>--%>
<%--	<TD><u>Final decision</u></TD>--%>
<%--	<TD><select><option><option>Conforme accepté<option>Non conforme accepté<option>Non conforme</select><br><textarea></textarea></TD>--%>
<%--</TR>--%>
<%--<TR BGCOLOR="#FAE4D1">--%>
<%--	<TD>Picked up cartons numbers</TD>--%>
<%--	<TD><textarea></textarea></TD>--%>
<%--	<TD colspan=2>&nbsp;</TD>--%>
<%--</TR>--%>
<TR>
	<TD COLSPAN=4 BGCOLOR="#F5D0AF" ALIGN='center' style="padding-top: 5px; padding-bottom: 5px" nowrap>
		<B><FONT SIZE ="+1"><%=WUtils.getHtml(request,"INSPECTION_REPORT.SAMPLE_SENT_TO_CAMAIEU")%></FONT></B>
	</TD>
</TR>
<TR BGCOLOR="#FAE4D1">
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.SAMPLE_SEALED")%></TD>
	<TD nowrap>
        <input type="checkbox" name="rstEchantScelle01" <%=WUtils.valueByComp(doDtRecepCtrlSourceTete.getRstEchantScelle01(),EGCQBusinessConstants.BOOLEAN_YES,"checked=checked","")%>> Yes/No
        <br>
    <%helper.startHtmlEditor(request,out,"rstMotifScelle");%>
        <textarea name="rstMotifScelle"><%=helper.getHtmlEditingValue(request,"rstMotifScelle",doDtRecepCtrlSourceTete.getRstMotifScelle())%></textarea>
    <%helper.endHtmlEditor(request,out,"rstMotifScelle");%>
    </TD>
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.SAMPLE_SENT_BY_CONTROL_SUPPLIER")%></TD>
    <%
        // TODO    : le champ suivant manque dans le schema de la base actuelle
        // TODO... : attendre lesmodification de NODHOS
    %>
<%--    <TD><input type="checkbox" name="rstEchEnvoiPrest" <%=QCUtils.valueByComp(doDtRecepCtrlSourceTete.getRstEEchantScelle01(),QCBusinessConstants.BOOLEAN_YES,"checked=checked","")%>></TD>--%>
    <TD nowrap><input type="checkbox" name="rstFouEnvoiEchan" <%=WUtils.valueByComp("???",EGCQBusinessConstants.BOOLEAN_YES,"checked=checked","")%>></TD>
</TR>
<TR BGCOLOR="#FAE4D1">
	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.SAMPLE_TRAKING_NBR")%></TD>
	<TD nowrap>
        <%helper.startHtmlEditor(request,out,"rstNoColisEchant");%>
        <input type="text" name="rstNoColisEchant" value="<%=helper.getHtmlEditingValue(request,"rstNoColisEchant",doDtRecepCtrlSourceTete.getRstNoColisEchant())%>">
        <%helper.endHtmlEditor(request,out,"rstNoColisEchant");%>
    </TD>

	<TD nowrap><%=WUtils.getHtml(request,"INSPECTION_REPORT.SAMPLE_SHIPPING_DATE")%></TD>

	<TD nowrap>
        <%helper.startHtmlEditor(request,out,"rstDtEnvoiEchant");%>
        <input type="text" name="rstDtEnvoiEchant" value="<%=helper.getHtmlEditingValue(request,"rstDtEnvoiEchant",doDtRecepCtrlSourceTete.getRstDtEnvoiEchant())%>">
        <%helper.endHtmlEditor(request,out,"rstDtEnvoiEchant");%>
    </TD>
</TR>
<TR>
	<TD COLSPAN=4 BGCOLOR="#F5D0AF" ALIGN='center' style="padding-top: 5px; padding-bottom: 5px" nowrap>
		<B><FONT SIZE ="+1"><%=WUtils.getHtml(request,"INSPECTION_REPORT.FILES")%></FONT></B>
	</TD>
</TR>
<%
    System.out.println("2.fileUploadHelper="+fileUploadHelper);
    File[] files=fileUploadHelper.getFiles();
    if(files.length>0){
        %><TR><TD COLSPAN=4 BGCOLOR="#FAE4D1" ALIGN='center' style="padding-top: 5px; padding-bottom: 5px" nowrap>
        <TABLE><%
        for(int i=0;i<files.length;i++){
            %><TR>
            <TD>
                <a href="<%=WUtils.getWebPath(request,config.getServletContext(), files[i])%>" target="_blank"><%=files[i].getName()%></a>
            </TD>
            <TD>
                <input type="image" alt="<%=WUtils.getHtml(request,"DELETE")%>" border="0" name="submit" src="<%=WUtils.getImageUrl(request,"DELETE_FILE")%>" onclick='document.form.actionType.value="deleteFile:<%=i%>";document.form.submit()'>
            </TD>
            <TR>
        <%}%>
        </TABLE></TD></TR><%
    }
%>

<TR BGCOLOR="#FAE4D1">
	<TD colspan=4 nowrap>
        <%=WUtils.getHtml(request,"INSPECTION_REPORT.JOIN_FILES")%> :
        <input type=file name="newFile">
        <input type=button name="buttonAdd" value="<%=WUtils.getHtml(request,"INSPECTION_REPORT.ADD_FILE")%>" onclick='document.form.actionType.value="addFile";document.form.submit()'>
        <input type=hidden name="actionType" value="">
    </TD>
</TR>
<!--<TR BGCOLOR="#FAE4D1">
	<TD colspan=4 style="padding-top: 20px; padding-bottom: 20px;" align=center><input type=submit value=Envoyer>&nbsp;&nbsp;<input type=reset value=Effacer></TD>
</TR>-->
</TABLE>
</FORM>
</body>
</html>
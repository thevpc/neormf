<%--
	// Page d'authentification
	// Module : authentification
-- @author taha Ben Salah, ADD'IT Tunisie
-- @creation_date 15/01/2004
-- @last_modification_date 27/01/2004
-- @status pour validation
--%><%@ page import="camaieu.egarmentcq.bo.BoDtCmdeAchatTete,
                 camaieu.egarmentcq.common.EGCQSessionConstants,
                 camaieu.common.WUtils,
                     camaieu.egarmentcq.common.EGCQRequestConstants"%><%

    BoDtCmdeAchatTete.CommandeData[] commandes=(BoDtCmdeAchatTete.CommandeData[]) request.getSession().getAttribute(EGCQSessionConstants.SUPPLIER_PLANNING);
     if(commandes==null){
         request.setAttribute(EGCQRequestConstants.ERROR_MSG, WUtils.getDisplayable(request,"ERROR.FATAL_ERROR"));
         response.sendRedirect("standard_error.jsp");
     }
    response.setContentType("text/text");
    out.print(WUtils.getCsv(request, "PLANNING.SUPPLIER"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.PROD_PLACE"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.ORDER_NBR"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.ITEM_CODE"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.ITEM_NAME"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.COLOR_CODE"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.COLOR"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.ORDERED_QTY"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.ORDERED_DATE_PLANNING"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.DEPART_DATE_SHIFT"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.INSPECT_DATE"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.INSPECT_SHIFT"));
    out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.getCsv(request,"PLANNING.INSPECT_TYPE"));
    out.println();
    for(int i=0;i<commandes.length;i++){
        out.print(WUtils.toDisplayable(request,commandes[i].getFouNom()));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getDepNom())+" "+WUtils.toDisplayable(request,commandes[i].getDepPostal(),"")+" "+WUtils.toDisplayable(request,commandes[i].getDepVille())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCatNoCmde())+"-"+WUtils.toDisplayable(request,commandes[i].getCatNoVersion())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCamArtCode())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getModDes())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCamArtVar1())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCouLib())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCamQteCmde())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCatDtCmde())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getCatDtDepart2())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getRstDtCtrlSourceSouhait())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getRstDtCtrlSourcePrev())));
        out.print(WUtils.CSV_SEPARATOR);out.print(WUtils.toCsv(WUtils.toDisplayable(request,commandes[i].getRstNoControle())));
        out.println();
    }
%>
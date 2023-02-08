<%@page import="model.entity.Playa"%>
<%@page import="model.entity.TownSelectView"%>
<%@page import="model.entity.ProvinceSelectView"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.CcaaSelectView"%>
<%@page import="controller.servlet.RootPGR"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.servlet.RootPGR.Option"%>
<%@page import="model.entity.Usuario"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  Playa selectedBeach = ServletUtils.getSess(session, SessVars.SELECTED_BEACH);
  String flag = String.format(
    "img/ccaa/%d.png",
    selectedBeach.getMunicipio().getProvincia().getCcaa().getId()
  );
%>

<h2>
  <%=selectedBeach.getNombre()%>
  <img src="<%=flag%>" style="height: 1em" />
  <%=selectedBeach.getMunicipio().getNombre()%>
  (<%=selectedBeach.getMunicipio().getProvincia().getNombre()%>)
  <img src="<%=flag%>" style="height: 1em" />
</h2>
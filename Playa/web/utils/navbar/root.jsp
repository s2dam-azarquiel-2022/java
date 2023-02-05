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
  List<CcaaSelectView> ccaas = ServletUtils.getSess(session, SessVars.CCAA_SELECT_VIEWS);
  Short selectedCcaa = ServletUtils.getSess(session, SessVars.SELECTED_CCAA);
  List<ProvinceSelectView> provinces = ServletUtils.getSess(session, SessVars.PROVINCE_SELECT_VIEWS);
  Short selectedProvince = ServletUtils.getSess(session, SessVars.SELECTED_PROVINCE);
  List<TownSelectView> towns = ServletUtils.getSess(session, SessVars.TOWN_SELECT_VIEWS);
  Integer selectedTown = ServletUtils.getSess(session, SessVars.SELECTED_TOWN);
  Usuario user = ServletUtils.getSess(session, SessVars.LOGIN);
%>

<div class="d-flex flex-column flex-lg-row gap-3">
  <form <%=PageUtils.mainFormSetup(RootPGR.opt(Option.SET_CURRENT_CCAA))%>>
    <select <%=PageUtils.mainSelectOnlySetup(SessVars.SELECTED_CCAA)%>>
      <% if (selectedCcaa == null || selectedCcaa == -1) { %>
        <option value="" selected class="d-none">Elije ccaa</option>
        <% for (CcaaSelectView ccaa : ccaas) { %>
          <option value="<%=ccaa.id%>"><%=ccaa.name%></option>
        <% } %>
      <% } else { %>
        <option value="-1">Ninguna</option>
        <% for (CcaaSelectView ccaa : ccaas) { %>
          <% if (ccaa.id == selectedCcaa) { %>
            <option value="<%=ccaa.id%>" selected><%=ccaa.name%></option>
          <% } else { %>
            <option value="<%=ccaa.id%>"><%=ccaa.name%></option>
          <% } %>
        <% } %>
      <% } %>
    </select>
  </form>
  <% if (provinces != null) { %>
    <form <%=PageUtils.mainFormSetup(RootPGR.opt(Option.SET_CURRENT_PROVINCE))%>>
      <select <%=PageUtils.mainSelectOnlySetup(SessVars.SELECTED_PROVINCE)%>>
        <% if (selectedProvince == null || selectedProvince == -1) { %>
          <option value="" selected class="d-none">Elije provincia</option>
          <% for (ProvinceSelectView province : provinces) { %>
            <option value="<%=province.id%>"><%=province.name%></option>
          <% } %>
        <% } else { %>
          <option value="-1">Ninguna</option>
          <% for (ProvinceSelectView province : provinces) { %>
            <% if (province.id == selectedProvince) { %>
              <option value="<%=province.id%>" selected><%=province.name%></option>
            <% } else { %>
              <option value="<%=province.id%>"><%=province.name%></option>
            <% } %>
          <% } %>
        <% } %>
      </select>
    </form>
  <% } %>
  <% if (towns != null) { %>
    <form <%=PageUtils.mainFormSetup(RootPGR.opt(Option.SET_CURRENT_TOWN))%>>
      <select <%=PageUtils.mainSelectOnlySetup(SessVars.SELECTED_TOWN)%>>
        <% if (selectedTown == null || selectedTown == -1) { %>
          <option value="" selected class="d-none">Elije municipio</option>
          <% for (TownSelectView town : towns){ %>
            <option value="<%=town.id%>"><%=town.name%></option>
          <% } %>
        <% } else { %>
          <option value="-1">Ninguna</option>
          <% for (TownSelectView town : towns) { %>
            <% if (town.id.equals(selectedTown)) { %>
              <option value="<%=town.id%>" selected><%=town.name%></option>
            <% } else { %>
              <option value="<%=town.id%>"><%=town.name%></option>
            <% } %>
          <% } %>
        <% } %>
      </select>
    </form>
  <% } %>
  <% if (user == null) { %>
    <button <%=PageUtils.mainModalButtonSetup("loginModal")%>>Login</button>
  <% } else { %>
    <p class="p-0 m-0">
      Welcome <%=user.getNick()%>
      <a href="/<%=PageUtils.pageName%>/<%=RootPGR.opt(Option.LOGOUT)%>" class="btn btn-danger">Logout</a>
    </p>
  <% } %>
</div>
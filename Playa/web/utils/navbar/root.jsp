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
  Usuario user = ServletUtils.getSess(session, SessVars.LOGIN);
%>

<div class="d-flex flex-direction-row gap-3">
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
  <% if (user == null) { %>
    <button <%=PageUtils.mainModalButtonSetup("loginModal")%>>Login</button>
  <% } else { %>
    <p class="p-0 m-0">
      Welcome <%=user.getNick()%>
      <a href="/<%=PageUtils.pageName%>/<%=RootPGR.opt(Option.LOGOUT)%>" class="btn btn-danger">Logout</a>
    </p>
  <% } %>
</div>
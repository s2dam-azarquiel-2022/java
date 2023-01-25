<%@page import="controller.utils.ServletUtils"%>
<%@page import="model.entity.Usuario"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div>
  <% Usuario user = ServletUtils.getSess(session, SessVars.LOGIN); %>
  <% if (user == null) { %>
    <button <%=PageUtils.mainModalButtonSetup("loginModal")%>>Login</button>
  <% } else { %>
    <span>Welcome <%=user.getNombre()%></span>
    <a href="/<%=PageUtils.pageName%>/RootPGR?OPTION=LOGOUT" class="btn btn-danger">Logout</a>
  <% } %>
</div>

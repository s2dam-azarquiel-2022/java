<%@page import="model.entity.Usuario"%>
<%@page import="controller.servlet.ServletConfig.SessVars"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div>
  <% Usuario user = (Usuario) session.getAttribute(SessVars.LOGIN.name()); %>
  <% if (user == null) { %>
    <button <%=PageUtils.mainModalButtonSetup("loginModal")%>>Login</button>
  <% } else { %>
    <span>Welcome <%=user.getNombre()%></span>
    <a href="/<%=PageUtils.pageName%>/Logout" class="btn btn-danger">Logout</a>
  <% } %>
</div>

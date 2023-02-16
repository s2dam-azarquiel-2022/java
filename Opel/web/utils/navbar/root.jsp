<%@page import="controller.servlet.Root"%>
<%@page import="controller.servlet.Root.Option"%>
<%@page import="controller.utils.ServletUtils"  %>
<%@page import="controller.utils.ServletConfig.SessVars"  %>
<%@page import="model.entity.Usuario"%>
<%@page import="view.PageUtils"%>
<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<% Usuario login = ServletUtils.getAttr(session, SessVars.LOGIN); %>

<% if (login == null) { %>
  <button <%=PageUtils.mainModalButtonSetup("login")%>>Login</button>
<% } else { %>
  <div class="d-flex flex-row gap-2 align-items-center">
    <span>Welcome <%=login.getNick()%></span>
    <button <%=PageUtils.mainModalButtonSetup("cita")%>>Pedir Cita</button>
    <a <%=PageUtils.mainLinkButton(Root.opt(Option.LOGOUT), "btn-danger")%>>Logout</a>
  </div>
<% } %>
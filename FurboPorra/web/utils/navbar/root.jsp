<%@page import="controller.servlet.ServletConfig.SessVars"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div>
  <% String login = request.getParameter(SessVars.LOGIN.name()); %>
  <% if (login == null) { %>
    <button <%=PageUtils.mainModalButtonSetup("loginModal")%>>Login</button>
  <% } else { %>
    <span>Welcome <%=login%></span>
    <button <%=PageUtils.mainModalButtonSetup("logoutModal")%>>Logout</button>
  <% } %>
</div>

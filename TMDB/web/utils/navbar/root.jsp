<%@page import="controller.utils.ServletUtils"%>
<%@page import="model.entity.Usuario"%>
<%@page import="controller.servlet.Root"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  Usuario login = ServletUtils.getAttr(session, SessVars.LOGIN);
%>

<% if (login == null) { %>
  <form <%=PageUtils.mainFormSetup(Root.opt(Root.Option.LOGIN), "d-flex flex-row gap-3")%>>
    <input <%=PageUtils.mainInputSetup(ReqVars.DNI)%> />
    <input <%=PageUtils.mainInputSetup(ReqVars.NAME)%> />
    <button <%=PageUtils.mainSubmitButton("btn-success w-100")%>>Login | Register</button>
  </form>
<% } else { %>
  <div class="d-flex flex-row gap-3 align-items-center">
    <p class="p-0 m-0">Welcome <%=login.getNombre()%></p>
    <a <%=PageUtils.mainLinkButton(Root.opt(Root.Option.LOGOUT))%>>Logout</a>
  </div>
<% } %>
<%@page import="controller.servlet.RootPGR"%>
<%@page import="controller.servlet.RootPGR.Option"%>
<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Images"%>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="model.entity.Playa"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  Playa selectedBeach = ServletUtils.getSess(session, SessVars.SELECTED_BEACH);
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title><%=PageUtils.pageName%></title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <% if (selectedBeach == null) { %>
      <jsp:include page="beaches.jsp"></jsp:include>
    <% } else { %>
      <jsp:include page="beach.jsp"></jsp:include>
    <% } %>
  </body>
</html>

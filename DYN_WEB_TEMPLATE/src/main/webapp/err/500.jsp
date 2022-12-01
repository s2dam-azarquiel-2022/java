<%@page import="controller.servlet.ServletConfig.requestVars"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
String errTitle = (String) request.getAttribute(requestVars.ERR_TITLE.name());
String errMsg = (String) request.getAttribute(requestVars.ERR_MESSAGE.name());
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="../utils/setupHead.html"></jsp:include>
    <title>Internal Server Error</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="../utils/navbar/navbar.jsp"></jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <h1 class="text-center">
        <% if (errTitle != null) { %>
          <%=errTitle%>
        <% } else { %>
          Internal Server Error
        <% } %>
      </h1>
      <p>
        <% if (errMsg != null) { %>
          <%=errMsg%>
        <% } else { %>
          Contact the server admin
        <% } %>
      </p>
    </div>
    <jsp:include page="../utils/setupBodyEnd.html"></jsp:include>
  </body>
</html>
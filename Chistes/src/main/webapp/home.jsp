<%@page import="controller.servlet.ServletConfig.requestVars"%>
<%@page import="model.entity.Joke"%>
<%@page import="java.util.ArrayList"%>
<%@page import="view.PageUtils"%>
<%@page import="model.entity.Page"%>
<%@page import="controller.servlet.ServletConfig"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
@SuppressWarnings("unchecked")
ArrayList<Joke> jokes = (ArrayList<Joke>)
  request.getAttribute(requestVars.JOKES.name());
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title>Chistes</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp"></jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <div class="d-grid gap-3">
        <% for (Joke joke : jokes) { %>
          <div class="p-2 border border-3 rounded bg-dark">
            <h1><%=joke.title%></h1>
            <p><%=joke.description%></p>
          </div>
        <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
  </body>
</html>

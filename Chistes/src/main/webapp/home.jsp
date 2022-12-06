<%@page import="controller.servlet.ServletConfig.ReqVars"%>
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
  request.getAttribute(ReqVars.JOKES.name());
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title>Chistes</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="additionalItemsFile" value="root.jsp" />
    </jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <div class="d-grid gap-3">
        <% for (Joke joke : jokes) { %>
          <div class="p-2 border border-3 rounded bg-dark">
            <h1>
              <%=joke.title%>
              -
              <%=joke.reviews%>
              <span class="d-inline-flex">
                <% for (int i = 0; i < 5; i++) { %>
                  <% String color = i < joke.stars ? "text-warning" : "text-muted"; %>
                  <span class="<%=color%>">★</span>
                <% } %>
              </span>
            </h1>
            <p><%=joke.description%></p>
          </div>
        <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
    <script src="./js/index.js"></script>
  </body>
</html>

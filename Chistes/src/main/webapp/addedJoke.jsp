<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.ServletConfig.ReqVars"%>
<%@page import="model.entity.Joke"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
Joke joke = (Joke) request.getAttribute(ReqVars.ADDED_JOKE.name());
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title>Chiste nuevo</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp"></jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <div class="d-grid gap-3">
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
      </div>
    </div>
    <jsp:include page="utils/modals/addJoke.jsp"></jsp:include>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
  </body>
</html>

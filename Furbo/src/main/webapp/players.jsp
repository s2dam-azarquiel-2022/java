<%@page import="controller.ServletConfig.requestVars"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entity.Player"%>
<%@page import="view.PageUtils"%>
<%@page import="model.entity.Page"%>
<%@page import="controller.ServletConfig"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<%
@SuppressWarnings("unchecked")
ArrayList<Player> players = (ArrayList<Player>)
  request.getAttribute(requestVars.PLAYERS.name());
%>
<html>
  <head>
    <jsp:include page="utils/setupTop.html"></jsp:include>
    <title>Furbo</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp"></jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <div class="row">
        <% for (Player p : players) { %>
	        <div class="col-12 col-lg-6 col-xl-4 mb-4">
	          <div class="card h-100 text-bg-dark" >
	            <div class="card-body">
	              <p class="fs-2">
                  <h1 class="text-center"><%=p.name%></h1>
	              </p>
	            </div>
	          </div>
	        </div>
	      <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBottom.html"></jsp:include>
  </body>
</html>
<%@page import="model.entity.PositionPlayers"%>
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
ArrayList<PositionPlayers> positionPlayers = (ArrayList<PositionPlayers>)
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
      <% for (PositionPlayers p : positionPlayers) { %>
        <h1 class="text-center"><%=p.position.desc%></h1>
        <% if (p.players.size() > 0) { %>
        <div class="row">
          <% for (Player player : p.players) { %>
	          <div class="col-12 col-lg-6 col-xl-4 mb-4">
	            <div class="card h-100 text-bg-dark" >
	              <div class="card-body">
                  <h1 class="text-center"><%=player.name%></h1>
	              </div>
	            </div>
	          </div>
	        <% } %>
        </div>
        <% } else { %>
          <h4 class="text-center">No hay equipos registrados</h4>
        <% } %>
      <% } %>
    </div>
    <jsp:include page="utils/setupBottom.html"></jsp:include>
  </body>
</html>
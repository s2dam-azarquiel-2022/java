<%@page import="view.PageUtils"%>
<%@page import="model.entity.DivisionTeams"%>
<%@page import="controller.ServletConfig.sessionVars"%>
<%@page import="model.entity.Team"%>
<%@page import="java.util.ArrayList"%>
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
ArrayList<DivisionTeams> divisionTeams = (ArrayList<DivisionTeams>)
  request.getAttribute(sessionVars.TEAMS.name());
%>
<html>
  <head>
    <jsp:include page="utils/setupTop.html"></jsp:include>
    <title>Furbo</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="additionalItemsFile" value="teams.jsp"/>
    </jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <% for (DivisionTeams d : divisionTeams) { %>
        <h1 class="text-center"><%=d.division%>&#170; division</h1>
        <% if (d.teams.size() > 0) { %>
        <div class="row">
          <% for (Team team : d.teams) { %>
	          <div class="col-12 col-lg-6 col-xl-4 mb-4">
	            <div class="card h-100 text-bg-dark" >
	              <div class="card-body">
	                <p class="fs-2">
	                  <img style="height: 1.5em" src="img/shield_<%=team.id%>.png" />
	                  <%=team.name%>
	                </p>
	                <h5>Puntos: <%=team.points%></h5>
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
<%@page import="controller.ServletConfig.sessionVars"%>
<%@page import="model.entity.Team"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entity.Page"%>
<%@page import="controller.ServletConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
ArrayList<Team> teams = (ArrayList<Team>) request.getAttribute(sessionVars.TEAMS.name());
%>
<html>
  <head>
    <jsp:include page="utils/setupTop.html"></jsp:include>
    <title>Furbo</title>
  </head>
  <body>
    <jsp:include page="utils/navbar.jsp"></jsp:include>
    <div class="container-fluid bg-dark">
      <div class="row">
        <% for (Team team : teams) { %>
	        <div class="col-12 col-md-6 col-lg-4 mb-4">
	          <div class="card h-100 bg-dark text-white" >
	            <div class="card-body">
	              <h5><%=team.name%></h5>
	            </div>
	          </div>
	        </div>
        <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBottom.html"></jsp:include>
  </body>
</html>
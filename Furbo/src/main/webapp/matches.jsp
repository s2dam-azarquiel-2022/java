<%@page import="model.entity.Page"%>
<%@page import="controller.ServletConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupTop.html"></jsp:include>
    <title>Furbo</title>
  </head>
  <body>
    <jsp:include page="utils/navbar.jsp"></jsp:include>
    <div class="container-fluid bg-dark">
    </div>
    <jsp:include page="utils/setupBottom.html"></jsp:include>
  </body>
</html>
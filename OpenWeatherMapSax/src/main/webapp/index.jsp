<%@page import="java.nio.file.Path"%>
<%@page import="dao.OpenWeatherApp"%>
<%@page import="model.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
      crossorigin="anonymous" />
  </head>
  <body>
    <div class="container my-5">
      <div class="row">
        <%
        Path p = Path.of(getServletContext().getRealPath("/appid.secret"));
        for (Time time : OpenWeatherApp.getForecast(p, "Toledo", "es")) {
        %>
        <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2 d-flex">
          <div class="card mb-4 flex-fill">
            <img class="card-img-top w-50 mx-auto my-2" alt="" src="http://openweathermap.org/img/w/<%=time.icon%>.png" />
            <div class="card-body">
              <h5 class="card-title"><%=time.description%></h5>
              <h6 class="card-title"><%=time.precipitation%>%</h6>
              <h6 class="card-title"><%=String.format("%.2f", time.temperature)%>°C</h6>
              <p class="card-text"><%=time.dateStart%></p>
            </div>
          </div>
        </div>
        <% } %>
      </div>
    </div>
		<!-- Cosas a importar -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
      crossorigin="anonymous">
    </script>
  </body>
</html>

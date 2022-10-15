<%@page import="model.Daily"%>
<%@page import="model.Time"%>
<%@page import="dao.DarkSky"%>
<%@page import="java.nio.file.Path"%>
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
    <%
    Path p = Path.of(getServletContext().getRealPath("/appid.secret"));
    DarkSky darkSky = DarkSky.getForecasts(p);
    %>
    <div class="container my-5">
      <div>
        <% Time currently = darkSky.getCurrently(); %>
        <h1 class="text-center color-primary">Tiempo actual</h1>
        <hr />
        <div class="text-center">
          <h3 class="">
            <%=currently.getSummary()%>
          </h3>
          <h6>(<%=currently.getPrecipProbability()%>% de precipitaciones)</h6>
          <img class="w-25" alt="" src="<%=currently.getIcon()%>" />
          <h6 class="">Temperatura: <%=currently.getTemperature()%>°C</h6>
        </div>
      </div>
      <div class="mt-5">
        <% Daily nextDays = darkSky.getDaily(); %>
        <h1 class="text-center color-primary">Tiempo de los proximos dias</h1>
        <hr />
        <div class="text-center">
          <h3 class="">
            <%=nextDays.getSummary()%>
          </h3>
          <img class="w-25" alt="" src="<%=nextDays.getIcon()%>" />
        </div>
        <div class="row">
          <% for (Time time : nextDays.getData()) { %>
          <div class="col-12 col-md-4 col-lg-3 d-flex">
            <div class="card mb-4 flex-fill">
              <img class="card-img-top w-50 mx-auto my-2" alt="" src="<%=time.getIcon()%>" />
              <div class="card-body">
                <h5 class="card-title"><%=time.getSummary()%></h5>
                <h6 class="card-title"><%=time.getPrecipProbability()%>%</h6>
                <h6 class="card-title"><%=time.getTemperature()%>°C</h6>
                <p class="card-text"><%=time.getTime()%></p>
              </div>
            </div>
          </div>
          <% } %>
        </div>
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

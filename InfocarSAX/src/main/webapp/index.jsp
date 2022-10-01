<%@page import="dao.Infocar"%>
<%@page import="model.Incidence"%>
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
    <div class="container my-5">
      <div class="row">
        <%
        for (Incidence incidence : Infocar.getIncidences("13")) {
        %>
        <div class="col-12 d-flex">
          <div class="card mb-4 flex-fill">
            <div class="card-body">
              <h5 class="card-title">
                <a href="<%=incidence.link%>">
                  <%=incidence.title%>
                </a>
              </h5>
              <h6 class="card-title"><%=incidence.pubDate%></h6>
              <p class="card-text"><%=incidence.description%></p>
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

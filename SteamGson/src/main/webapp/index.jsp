<%@page import="model.Game"%>
<%@page import="dao.Main"%>
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
    <script src="detail.js"></script>
  </head>
  <body>
    <%
    Main main = Main.getMain();
    %>
    <div class="container my-5">
      <div class="mt-5">
        <h1 class="text-center color-primary">Juegos de Steam</h1>
        <hr />
        <div class="row">
          <% for (Game game : main.getApplist().getApps()) { %>
          <div class="col-12 col-md-4 d-flex">
            <div class="card mb-4 flex-fill">
              <div class="card-body text-center d-flex flex-column">
                <h5 class="card-title mb-3"><%=game.getName()%></h5>
                <a onClick="getDetail('<%=game.getAppid()%>')" class="d-block mt-auto">
                  Ver juego
                </a>
              </div>
            </div>
          </div>
          <% } %>
        </div>
      </div>
    </div>
    <div id="detail-container">
    </div>
		<!-- Cosas a importar -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
      crossorigin="anonymous">
    </script>
  </body>
</html>

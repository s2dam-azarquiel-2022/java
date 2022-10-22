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
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
      crossorigin="anonymous" />
    <link rel="stylesheet" type="text/css" href="style.css" />
    <title>Steam Games</title>
  </head>
  <body onload="setup()">
    <%
    Main main = Main.getMain();
    %>
    <div class="container my-5">
      <div class="mt-5">
        <h1 class="text-center color-primary">Steam Games</h1>
        <hr />
        <div class="row">
          <% for (Game game : main.getApplist().getApps()) { %>
          <div class="col-12 col-md-4 d-flex">
            <div class="card mb-4 flex-fill">
              <div class="card-body text-center d-flex flex-column">
                <h5 class="card-title mb-3"><%=game.getName()%></h5>
                <button onClick="getDetail('<%=game.getAppid()%>')" class="d-block mt-auto btn btn-primary">
                  Checkout game
                </button>
              </div>
            </div>
          </div>
          <% } %>
        </div>
      </div>
      <div class="bg-primary p-3 mb-0 text-center mt-auto rounded">
        <h1 class="m-0">Alberto</h1>
      </div>
    </div>
    <div id="detail-container"></div>
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
      <div id="err-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header text-danger">
          <svg class="err-icon me-2 text-danger" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
          </svg>
          <strong class="me-auto">Steam</strong>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
          Seems like the game doesn't exist anymore in Steam!
        </div>
      </div>
    </div>
		<!-- Cosas a importar -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
      crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="detail.js"></script>
  </body>
</html>

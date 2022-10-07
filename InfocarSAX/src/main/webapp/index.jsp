<%@page import="dao.Infocar"%>
<%@page import="model.Incidence"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Infocar</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
      crossorigin="anonymous" />
  </head>
  <body>
    <div class="container min-vh-100 py-5 d-flex flex-column">
      <%
      String caId = request.getParameter("ca");
      String ca = Infocar.getCAName(caId);
      %>
      <form method="get" action="index.jsp">
        <div class="form-group">
          <select class="form-control" name="ca" onchange="this.form.submit()">
            <option selected class="d-none">
              <%=ca%>
            </option>
            <% for (int i = 0; i < Infocar.CAS.length; i++) { %>
              <% if (!Infocar.CAS[i].equals(ca)) { %>
              <option value="<%=i+1%>"><%=Infocar.CAS[i]%></option>
              <% } %>
            <% } %>
          </select>
        </div>
      </form>
      <% if (caId != null) { %>
      <h1 class="text-center text-primary mt-4">
        Comunidad de <%=ca%>
      </h1>
      <div class="row mt-4">
        <% for (Incidence incidence : Infocar.getIncidences(caId)) { %>
        <div class="col-12 d-flex">
          <div class="card mb-4 flex-fill">
            <div class="card-body">
              <h5 class="card-title">
                <%=incidence.image%>
                <a href="<%=incidence.link%>"><%=incidence.title%></a>
              </h5>
              <h6 class="card-title"><%=incidence.pubDate%></h6>
              <%=incidence.description%>
            </div>
          </div>
        </div>
        <% } %>
      </div>
      <% } %>
      <div class="bg-primary p-3 text-center mt-auto rounded">
        <h1>Alberto</h1>
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

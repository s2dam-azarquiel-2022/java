<%@page import="java.util.HashMap"%>
<%@page import="model.entity.Brand"%>
<%@page import="model.entity.Bike"%>
<%@page import="java.util.ArrayList"%>
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
    <title>AllTricks</title>
  </head>
  <body>
  	<% 
  	ArrayList<Brand> brands = (ArrayList<Brand>)session.getAttribute("brands");
  	ArrayList<Bike> bikes = (ArrayList<Bike>)request.getAttribute("bikes");
  	HashMap<Integer, Brand> idToBrandMap = new HashMap<>();
  	brands.forEach(brand -> idToBrandMap.put(brand.getId(), brand));
  	%>
    <div class="container shadow p-0">
      <header class="bg-primary p-3"><img src="img/logo.png" alt=""></header>
      <div class="row justify-content-center my-3">
        <div class="col-lg-4 pt-3">
          <form action="Controller?option=brand" method="post">
            <div class="form-group">
              <select class="form-control" name="brand" id="" onchange="this.form.submit()">
                <option value="" disabled selected>Elija marca</option>
                <option value="%">Todas</option>
                <% for (Brand brand : brands) { %>
                  <option value="<%=idToBrandMap.get(brand.getId()).getName()%>">
                    <%=brand.getName()%>
                  </option>
                <% } %>
              </select>
            </div>
          </form>
        </div>
        <div class="col-lg-4 pt-3">
          <form action="Controller?op=order" method="post">
            <div class="form-group">
              <select class="form-control" name="order" id="" onchange="this.form.submit()">
                <option value="" disabled selected>Ordenada por</option>
                <option value="price->asc">Precio ascendente</option>
                <option value="price->desc">Precio descendente</option>
                <option value="brand">Marca</option>
              </select>
            </div>
          </form>
        </div>
        <div class="col-lg-2 text-right">
          <span class="display-4">&#9733;</span>
        </div>
      </div>
      <div class="row p-3">
        <% for (Bike bike : bikes) { %>
	        <div class="col-md-6 col-lg-4">
	          <div class="card">
	            <img class="card-img-top" src="<%=bike.getPhoto()%>" alt="" />
	            <div class="card-body">
	                <h5 class="card-title"><%=bike.getDescription()%></h5>
	                <h6 class="card-text"><%=idToBrandMap.get(bike.getBrand()).getName()%></h6>
	            </div>
	          </div>
	        </div>
        <% } %>
      </div>
      <div class="bg-primary p-3 mb-0 text-center mt-auto rounded">
        <h1 class="m-0">Alberto</h1>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
      crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </body>
</html>

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
    int selectedBrand = -1;
    try {
      selectedBrand = Integer.valueOf((String) session.getAttribute("currentBrand"));
    } catch (NumberFormatException e) {
      selectedBrand = -1;
    }
    String selectedOrder = (String) session.getAttribute("currentOrder");
  	%>
    <div class="container shadow p-0">
      <header class="bg-primary p-3"><img src="img/logo.png" alt=""></header>
      <div class="row justify-content-center my-3">
        <div class="col-lg-4 pt-3">
          <form action="Controller?option=brand" method="post">
            <div class="form-group">
              <select class="form-control" name="brand" id="" onchange="this.form.submit()">
                <% if (selectedBrand == -1) { %>
                  <option class="d-none" value="" selected>Elija marca</option>
                <% } else { %>
                  <option value="%">Todas</option>
                <% } %>
                <% for (Brand brand : brands) { %>
                  <% if (brand.getId() == selectedBrand) { %>
                    <option value="<%=brand.getId()%>" selected>
                      <%=brand.getName()%>
                    </option>
                  <% } else { %>
                    <option value="<%=brand.getId()%>">
                      <%=brand.getName()%>
                    </option>
                  <% } %>
                <% } %>
              </select>
            </div>
          </form>
        </div>
        <div class="col-lg-4 pt-3">
          <form action="Controller?option=order" method="post">
            <div class="form-group">
              <select class="form-control" name="order" id="order" onchange="this.form.submit()">
                <% if (selectedOrder == null) { %>
                  <option class="d-none" value="" selected>Ordenada por</option>
                <% } else { %>
                  <option value="reset">Por defecto</option>
                <% } %>
                <% for (String orderField : model.dao.Bike.allowedOrderFields) { %>
                  <% for (int i = 0; i < model.dao.Bike.allowedOrders.length; i++) { %>
                    <% String value = orderField + "->" + model.dao.Bike.allowedOrders[i]; %>
                    <% if (value.equals(selectedOrder)) { %>
                      <option value="<%=value%>" selected>
                        <%=orderField%> <%=model.dao.Bike.allowedOrdersLongNames[i]%>
                      </option>
                    <% } else { %>
                      <option value="<%=value%>">
                        <%=orderField%> <%=model.dao.Bike.allowedOrdersLongNames[i]%>
                      </option>
                    <% } %>
                  <% } %>
                <% } %>
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

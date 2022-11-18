<%@page import="model.dao.CarDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.entity.Car"%>
<%@page import="model.entity.Brand"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./styles/reduced.css" rel="stylesheet" />
    <title>CliCars</title>
  </head>
  <body>
  	<% 
  	ArrayList<Brand> brands = (ArrayList<Brand>)session.getAttribute("brands");
  	ArrayList<Car> cars = (ArrayList<Car>)request.getAttribute("cars");
    int selectedBrand = -1;
    try {
      selectedBrand = Integer.valueOf((String) session.getAttribute("currentBrand"));
    } catch (NumberFormatException e) {
      selectedBrand = -1;
    }
    String selectedOrder = (String) session.getAttribute("currentOrder");
  	%>
    <div class="container-fluid shadow p-0">
      <nav class="navbar navbar-expand-xl bg-light sticky-top px-3">
        <a class="navbar-brand" href="Controller?option=home">
          <img class="svg-logo" alt="CliCars" src="./img/logo.svg" />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="row collapse navbar-collapse" id="navbarNavDropdown">
          <div class="col-12 col-xl-auto ms-auto mt-3 mt-xl-0">
            <form action="Controller?option=brand" method="post">
              <select class="form-control" name="brand" id="brand" onchange="this.form.submit()">
                <% if (selectedBrand == -1) { %>
                  <option class="d-none" value="" selected>Elija marca</option>
                <% } else { %>
                  <option value="%">Todas</option>
                <% } %>
                <% for (Brand brand : brands) { %>
                  <option value="<%=brand.id%>">
                    <%=brand.name%>
                  </option>
                <% } %>
              </select>
            </form>
          </div>
          <div class="col-12 col-xl-auto mt-3 mt-xl-0">
            <form action="Controller?option=order" method="post">
              <select class="form-control" name="order" id="order" onchange="this.form.submit()">
                <% if (selectedOrder == null) { %>
                  <option class="d-none" value="" selected>Ordenada por</option>
                <% } else { %>
                  <option value="reset">Por defecto</option>
                <% } %>
                <% for (int i = 0; i < CarDAO.allowedOrderFields.length; i++) { %>
                  <% for (int j = 0; j < CarDAO.allowedOrders.length; j++) { %>
                    <option value="<%=CarDAO.allowedOrderFields[i] + "->" + CarDAO.allowedOrders[j]%>">
                      <%=CarDAO.allowedOrderFieldsLongName[i]%> <%=CarDAO.allowedOrdersLongNames[j]%>
                    </option>
                  <% } %>
                <% } %>
              </select>
            </form>
          </div>
          <div class="col-12 col-xl-auto mt-3 mt-xl-0">
            <form action="Controller?option=search" method="post" class="d-flex" role="search">
              <%
                String currentSearchText = (String)session.getAttribute("currentSearch");
                if (currentSearchText == null) currentSearchText = "";
              %>
              <input
                name="search"
                value="<%=currentSearchText%>"
                class="form-control me-2"
                type="search"
                placeholder="Buscar ..."
                aria-label="Buscar ...">
              <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
          </div>
        </div>
      </nav>
      <div class="row p-3">
        <% for (Car car : cars) { %>
	        <div class="col-12 col-md-6 col-lg-4 mb-4">
	          <div class="card h-100 bg-dark text-white" >
	            <img class="card-img-top" src="<%=car.photo%>" alt="" />
	            <div class="card-body">
	              <% String color = car.favorite ? "text-warning" : "text-muted"; %>
                <h3 class="text-end setFavoriteBtn <%=color%>" value="<%=car.id%>">
                  &#9733;
                </h3>
                <div class="row">
                  <div class="col-8 col-sm-8 flex-fill">
	                  <h5 class="card-title"><%=car.name%></h5>
	                  <h6 class="card-text"><%=car.model%></h6>
	                  <p><%=car.year%> | <%=car.km%>km | <%=car.cv%>CV</p>
                  </div>
                  <div class="col-auto col-sm-auto text-end">
                    <p class="text-danger"><%=car.priceBefore%> &euro;</p>
                    <p class="text-success"><%=car.priceNow%> &euro;</p>
                  </div>
                </div>
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
    <script>
      for (option of document.getElementById("brand").children) {
    	  if (option.value == "<%=selectedBrand%>") {
    		  option.selected = true;
    		  break;
    	  }
    	}

      for (option of document.getElementById("order").children) {
    	  if (option.value == "<%=selectedOrder%>") {
    		  option.selected = true;
    		  break;
    	  }
    	}
    </script>
    <script type="text/javascript" src="clicars.js"></script>
  </body>
</html>
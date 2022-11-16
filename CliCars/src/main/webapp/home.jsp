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
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
      crossorigin="anonymous" />
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
    <div class="container shadow p-0">
      <header class="bg-primary p-3"><img src="img/logo.png" alt=""></header>
      <div class="row justify-content-center my-3">
        <div class="col-lg-4 pt-3">
          <form action="Controller?option=brand" method="post">
            <div class="form-group">
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
                <% for (String orderField : CarDAO.allowedOrderFields) { %>
                  <% for (int i = 0; i < CarDAO.allowedOrders.length; i++) { %>
                    <option value="<%=orderField + "->" + CarDAO.allowedOrders[i]%>">
                      <%=orderField%> <%=CarDAO.allowedOrdersLongNames[i]%>
                    </option>
                  <% } %>
                <% } %>
              </select>
            </div>
          </form>
        </div>
      </div>
      <div class="row p-3">
        <% for (Car car : cars) { %>
	        <div class="col-md-6 col-lg-4">
	          <div class="card">
	            <img class="card-img-top" src="<%=car.photo%>" alt="" />
	            <div class="card-body row">
                <div class="col-12">
                  <h3 class="text-end">&#9733;</h3>
                </div>
                <div class="col-12 col-md-8">
	                <h5 class="card-title"><%=car.name%></h5>
	                <h6 class="card-text"><%=car.model%></h6>
	                <p><%=car.year%> | <%=car.km%>km | <%=car.cv%>CV
                </div>
                <div class="col-12 col-md-4">
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
  </body>
</html>
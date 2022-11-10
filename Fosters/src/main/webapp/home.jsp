<%@page import="java.util.HashMap"%>
<%@page import="model.entity.Product"%>
<%@page import="model.entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
      crossorigin="anonymous" />
    <link rel="stylesheet" href="./foster.css" />
    <title>Foster</title>
  </head>
  <body class="bg-1">
  	<% 
  	ArrayList<Category> categories = (ArrayList<Category>)session.getAttribute("categories");
  	ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
  	HashMap<Integer, Category> idToCategoryMap = new HashMap<>();
  	categories.forEach(category -> idToCategoryMap.put(category.id, category));
  	%>
    <a name="top"></a>
	  <div class="container-fluid p-0">
		  <ul class="navbar m-0 navbar-expand-xl navbar-light bg-blue-dark">
			  <a class="navbar-brand" href="Controller?option=home">
			    <img alt="LOGO" src="./img/logo.png" />
			  </a>
			  <button class="navbar-toggler" type="button" data-toggle="collapse"
				  data-target="#navbarTogglerDemo02" aria-expanded="false"
				  aria-controls="navbarTogglerDemo02" aria-label="Toggle navigation">
				  <span class="navbar-toggler-icon"></span>
			  </button>
			  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
				  <form
				    class="navbar-nav mr-auto mt-2 mt-lg-0"
				    action="Controller?option=category" method="post">
				    <% for (Category category : categories) { %>
						  <button
						    class="btn text-light p-1 mx-0 link-info"
						    name="id"
						    value="<%=category.id%>">
						    <%=category.name%>
						  </button>
						<% } %>
				  </form>
				  <form class="form-inline ms-auto me-3">
				    <btn class="btn text-light bg-red-btn py-1 px-2">PIDE ONLINE</btn>
				  </form>
			  </div>
		  </ul>
		  <div class="row m-0">
			  <div id="mainCarousel" class="carousel slide" data-ride="carousel">
				  <div class="carousel-inner">
					  <div class="carousel-item active">
						  <img src="./img/img1.jpg" class="d-block w-100" />
					  </div>
					  <div class="carousel-item">
						  <img src="./img/img2.jpg" class="d-block w-100" />
					  </div>
					  <div class="carousel-item">
						  <img src="./img/img3.jpg" class="d-block w-100" />
					  </div>
					  <div class="carousel-item">
						  <img src="./img/img4.jpg" class="d-block w-100" />
					  </div>
				  </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#mainCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#mainCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
			  </div>
			  <div class="row mt-5 justify-content-center">
				  <% for (Product product : products) { %>
						<div class="col-12 col-sm-6 col-md-4 col-lg-3 d-flex">
							<div class="card flex-fill mb-4 text-center">
								<img class="card-img-top" alt="Imagen de <%=product.title%>" src="<%=product.image%>" />
								<div class="card-body d-flex flex-column">
									<h3 class="card-title fg-3">
                    <%=product.title%>
									</h3>
                  <%=product.body%>
								</div>
							</div>
						</div>
				  <% } %>
			  </div>
			  <div class="w-100 bg-red-footer">
				  <h1 class="text-light text-center py-2">LA CARTA DE FOSTER'S
					  HOLLYWOOD
					</h1>
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
  </body>
</html>

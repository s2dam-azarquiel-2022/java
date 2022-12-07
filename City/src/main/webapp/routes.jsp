<%@page import="controller.servlet.ServletConfig.ReqVars"%>
<%@page import="model.entity.Route"%>
<%@page import="java.util.ArrayList"%>
<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<% ArrayList<Route> routes = (ArrayList<Route>) request.getAttribute(ReqVars.ROUTES.name()); %>
<div class="row justify-content-center">
  <% for (Route route : routes) { %>
    <div class="col-12 col-lg-6 d-flex mb-4">
      <div class="card flex-fill text-bg-dark">
        <a href="<%=route.link%>"><img src="<%=route.image%>" class="card-img-top" /></a>
        <div class="card-body d-flex flex-column">
          <div class="card-title"><%=route.name%></div>
          <h5 class="d-inline-flex">
            <% for (int i = 0; i < 5; i++) { %>
              <% String color = i < route.reviews.avg ? "text-warning" : "text-muted"; %>
              <span class="<%=color%>">★</span>
            <% } %>
          </h5>
          <div class="card-text mb-2"><%=route.description%></div>
          <div class="d-flex align-items-center mt-auto">
            <p class="p-0 m-0">Calificar:</p>
            <div class="stars ms-2 d-flex" routeID="<%=route.id%>">
              <% for (int i = 1; i <= 5; i++) { %>
                <button class="star text-muted" val="<%=i%>">&#9733;</button>
              <% } %>
            </div>
          </div>
        </div>
      </div>
    </div>
  <% } %>
</div>

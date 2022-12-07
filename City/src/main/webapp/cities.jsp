<%@page import="controller.servlet.ServletConfig.ReqVars"%>
<%@page import="model.entity.City"%>
<%@page import="java.util.ArrayList"%>
<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<% ArrayList<City> cities = (ArrayList<City>) request.getAttribute(ReqVars.CITIES.name()); %>
<div class="row justify-content-center">
  <% for (City city : cities) { %>
    <div class="col-12 col-lg-6 d-flex mb-4">
      <div class="card flex-fill text-bg-dark">
        <a href="<%=city.link%>"><img src="<%=city.image%>" class="card-img-top" /></a>
        <div class="card-body d-flex flex-column">
          <div class="card-title"><%=city.name%></div>
          <div class="card-text"><%=city.description%></div>
          <img src="<%=city.map%>" class="mt-auto ms-auto" />
        </div>
      </div>
    </div>
  <% } %>
</div>

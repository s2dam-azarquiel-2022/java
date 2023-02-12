<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="model.entity.Movie"%>
<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<% for (Movie movie : ServletUtils.<List<Movie>>getAttr(request, ReqVars.MOVIES)) { %>
  <div class="row">
    <div class="col-2">
      <% if (movie.getPoster() == null) { %>
        <img src="img/null.png" class="card-img-top" />
      <% } else { %>
        <img src="https://image.tmdb.org/t/p/w500<%=movie.getPoster()%>" class="card-img-top" />
      <% } %>
    </div>
    <div class="col-10">
      <h3><%=movie.getTitulo()%></h3>
      <p><%=movie.getFecha()%></p>
    </div>
  </div>
<% } %>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="model.entity.Movie"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
%>

<%
  List<Movie> movies = ServletUtils.getAttr(session, SessVars.MOVIES);
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title><%=PageUtils.pageName%></title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="ADDITIONAL_ITEMS_FILE" value="root.jsp" />
    </jsp:include>
    <div <%=PageUtils.mainDivSetup("container")%>>
      <div class="row">
        <% for (Movie movie : movies) { %>
          <div class="col-12 col-md-6  mb-4 d-flex">
            <div <%=PageUtils.mainCardSetup%>>
              <div class="card-body">
                <div class="row">
                  <div class="col">
                    <% if (movie.getPoster() == null) { %>
                      <img src="img/null.png" class="card-img-top" />
                    <% } else { %>
                      <img src="https://image.tmdb.org/t/p/w500<%=movie.getPoster()%>" class="card-img-top" />
                    <% } %>
                  </div>
                  <div class="col">
                    <h3><%=movie.getTitulo()%></h3>
                    <h5><%=movie.getFecha()%></h5>
                    <p><%=movie.getTrama()%></p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
  </body>
</html>

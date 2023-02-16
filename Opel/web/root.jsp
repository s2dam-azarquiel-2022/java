<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="model.entity.Modelo"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <link href="css/index.css" type="text/css" rel="stylesheet" />
    <title><%=PageUtils.pageName%></title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="ADDITIONAL_ITEMS_FILE" value="root.jsp" />
    </jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <div class="row">
        <% for (Modelo model : ServletUtils.<List<Modelo>>getAttr(session, SessVars.COCHES)) { %>
          <div class="col-12 col-md-6 col-lg-4 mb-4 d-flex">
            <div
              class="card flex-fill text-white bg-dark car-card"
              data-id="<%=model.getId()%>"
              data-name="<%=model.getNombre()%>"
              data-img="<%=model.getImagen()%>"
            >
              <img class="card-img-top" src="img/coches/<%=model.getImagen()%>" />
              <div class="card-body d-flex">
                <h5 class="card-title mt-auto"><%=model.getNombre()%></h5>
              </div>
            </div>
          </div>
        <% } %>
      </div>
    </div>
    <footer class="text-center bg-dark text-white m-0 p-4">
      <h1 class="m-0">Alberto Robles Gomez - Azarquiel 2022</h1>
    </footer>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
    <jsp:include page="utils/modals/login.jsp"></jsp:include>
    <jsp:include page="utils/modals/cita.jsp"></jsp:include>
    <jsp:include page="utils/modals/detail.jsp"></jsp:include>
    <script src="js/cars.js"></script>
  </body>
</html>

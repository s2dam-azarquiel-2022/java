<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="model.entity.Habitacion"%>
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
    <title><%=PageUtils.pageName%></title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="ADDITIONAL_ITEMS_FILE" value="root.jsp" />
    </jsp:include>
    <div <%=PageUtils.mainDivSetup("container d-flex")%>>
      <div class='card text-white bg-dark align-self-center w-100'>
        <div class="card-body">
          <h5 class="card-title text-center text-uppercase">listado de habitaciones para consulta de reservas</h5>
          <table class="table table-dark table-striped">
            <thead>
              <tr>
                <th scope="col">N Habitacion</th>
                <th scope="col">N Persona</th>
                <th scope="col">Precio</th>
                <th scope="col">Ocupada (Si/No)</th>
              </tr>
            </thead>
            <tbody>
              <% for (Habitacion habitacion : ServletUtils.<List<Habitacion>>getAttr(session, SessVars.HABITACIONES)) { %>
                <tr>
                  <th scope="col">
                    <button
                      <%=PageUtils.mainModalButtonSetup("reservas")%>
                      data-n="<%=habitacion.getNhabitacion()%>"
                    >Reservas de la <%=habitacion.getNhabitacion()%></button>
                  </th>
                  <td><%=habitacion.getNpersonas()%></td>
                  <td><%=habitacion.getPrecio()%></td>
                  <td><%=habitacion.getOcupada() == 0 ? "No" : "Si"%></td>
                </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
    <jsp:include page="utils/modals/reservas.jsp"></jsp:include>
    <script src="js/reservas.js"></script>
  </body>
</html>

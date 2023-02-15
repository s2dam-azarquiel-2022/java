<%@page import="model.entity.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  List<Reserva> reservas = ServletUtils.getAttr(request, ReqVars.RESERVAS);
%>

<% if (reservas.isEmpty()) { %>
  <h5 class="text-center">No hay reservas</h5>
<% } else { %>
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th scope="col">N Reserva</th>
        <th scope="col">Fecha In</th>
        <th scope="col">Fecha Out</th>
        <th scope="col">DNI</th>
      </tr>
    </thead>
    <tbody>
      <% for (Reserva reserva : reservas) { %>
        <tr>
          <th scope="col"><%=reserva.getNreserva()%></th>
          <td><%=reserva.getFechae()%></td>
          <td><%=reserva.getFechas()%></td>
          <td><%=reserva.getDni()%></td>
        </tr>
      <% } %>
    </tbody>
  </table>
<% } %>

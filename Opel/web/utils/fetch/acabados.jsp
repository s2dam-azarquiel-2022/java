<%@page import="model.entity.Itemacabado"%>
<%@page import="model.entity.Acabado"%>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="model.entity.Modeloacabado"%>

<% List<Modeloacabado> acabados = ServletUtils.getAttr(request, ReqVars.ACABADOS); %>

<% if (acabados != null && acabados.size() > 0) { %>
<div class="accordion" id="acabados">
  <% for (Modeloacabado acabado : acabados) { %>
    <div class="accordion-item">
      <h2 class="accordion-header" id="acabados-h<%=acabado.getId()%>">
        <button
          class="accordion-button text-center bg-dark text-white"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#acabados-c<%=acabado.getId()%>"
          aria-expanded="true"
          aria-controls="acabados-c<%=acabado.getId()%>"
        ><%=acabado.getAcabado().getNombre()%></button>
      </h2>
      <div
        id="acabados-c<%=acabado.getId()%>"
        class="accordion-collapse collapse bg-dark"
        aria-labelledby="acabados-h<%=acabado.getId()%>"
      >
        <div class="accordion-body text-white text-center">
          <% for (Itemacabado item : acabado.getItemacabadoList()) { %>
          <p><%=item.getNombre()%></p>
          <% } %>
        </div>
      </div>
    </div>
  <% } %>
</div>
<% } %>
<%@page import="controller.servlet.RootPGR"%>
<%@page import="controller.servlet.RootPGR.Option"%>
<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Images"%>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="model.entity.Playa"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  List<Playa> beaches = ServletUtils.getSess(session, SessVars.BEACHES);
  Usuario login = ServletUtils.getSess(session, SessVars.LOGIN);
%>

<jsp:include page="utils/navbar/navbar.jsp">
  <jsp:param name="ADDITIONAL_ITEMS_FILE" value="root.jsp" />
</jsp:include>
<div <%=PageUtils.mainDivSetup%>>
  <div class="row">
    <% if (beaches != null) for (Playa beach : beaches) { %>
      <div class="col-12 col-md-6 d-flex mb-4">
        <div <%=PageUtils.mainCardSetup%>>
          <% List<Images> imgs = beach.getImagesList(); %>
          <% if (imgs != null && !imgs.isEmpty()) { %>
            <img src="./img/beach/<%=beach.getId()%>_<%=imgs.get(0).getId()%>.jpg" class="card-img-top" />
          <% } %>
          <div class="card-body d-flex flex-column">
            <h5 class="card-title"><%=beach.getNombre()%></h5>
            <p class="card-text"><%=beach.getDescripcion()%></p>
            <% if (login != null) { %>
              <div class="d-flex justify-content-between mt-auto">
                <button
                  <%=PageUtils.mainModalButtonSetup("califications")%>
                  data-beach-id="<%=beach.getId()%>"
                  data-beach-name="<%=beach.getNombre()%>"
                >CALIFICACIONES</button>
                <% String url = String.format(
                  "%s&%s=%d",
                  RootPGR.opt(RootPGR.Option.SET_CURRENT_BEACH),
                  ReqVars.BEACH_ID.name(),
                  beach.getId()
                ); %>
                <a <%=PageUtils.mainLinkButton(url)%>>CALIFICAR</a>
              </div>
            <% } %>
          </div>
        </div>
      </div>
    <% } %>
  </div>
</div>
<jsp:include page="utils/setupBodyEnd.html"></jsp:include>
<jsp:include page="utils/modals/login.jsp"></jsp:include>
<jsp:include page="utils/modals/califications.jsp"></jsp:include>
<script src="js/califications.js"></script>

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
  Playa selectedBeach = ServletUtils.getSess(session, SessVars.SELECTED_BEACH);
  List<Images> imgs = selectedBeach.getImagesList();
  Double calification = ServletUtils.getSess(session, SessVars.CALIFICATION);
%>

<jsp:include page="utils/navbar/navbar.jsp">
  <jsp:param name="ADDITIONAL_ITEMS_FILE" value="beach.jsp" />
</jsp:include>
<div <%=PageUtils.mainDivSetup("container d-flex flex-column align-items-center gap-3")%>>
  <p class="rounded p-3 bg-dark text-white"><%=selectedBeach.getDescripcion()%></p>
  <% if (calification != null) { %>
    <p class="rounded p-3 bg-dark text-white">
      <img src="img/rate/<%=Math.round(calification)%>.png" style="height: 2em" />
    </p>
  <% } %>
  <div id="beachImages" class="carousel slide w-100" data-bs-ride="true">
    <div class="carousel-inner">
      <% if (imgs != null && !imgs.isEmpty()) { %>
        <% String url = "./img/beach/" + selectedBeach.getId(); %>
        <div class="carousel-item active"><img src="<%=url%>_<%=imgs.get(0).getId()%>.jpg" class="d-block w-100"></div>
        <% for (int i = 1; i < imgs.size(); i++) { %>
          <div class="carousel-item"><img src="<%=url%>_<%=imgs.get(i).getId()%>.jpg" class="d-block rounded w-100"></div>
        <% } %>
      <% } %>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#beachImages" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#beachImages" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <div class="rounded p-3 bg-dark text-white">
    <% for (int i = 1; i <= 5; i++) { %>
      <% String url = String.format(
        "/%s/%s&%s=%d",
        PageUtils.pageName,
        RootPGR.opt(RootPGR.Option.RATE),
        ReqVars.CALIFIACTION.name(),
        i
      ); %>
      <a class="text-decoration-none" href="<%=url%>">
        <img src="img/rate/<%=i%>.png" style="height: 2em" />
      </a>
    <% } %>
  </div>
  <a <%=PageUtils.mainLinkButton(RootPGR.opt(Option.TO_BEACHES), "btn-danger")%>>VOLVER</a>
</div>
<jsp:include page="utils/setupBodyEnd.html"></jsp:include>

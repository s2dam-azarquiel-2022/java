<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.ServletConfig"%>
<%@page import="model.entity.Page"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<nav <%=PageUtils.mainNavSetup%>>
  <div class="container-fluid">
    <!-- Home link -->
    <a class="navbar-brand p-0 m-0" aria-current="page" href="/Furbo">
      <img class="d-block" style="height: 2em;" src="img/logo.png" />
    </a>

    <!-- Toggler -->
    <button <%=PageUtils.mainNavbarTogglerButtonSetup%>>
      <span class="navbar-toggler-icon"></span>
    </button>

    <div <%=PageUtils.mainNavbarSetup%>>
      <!-- Other pages links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <% for (Page webPage : ServletConfig.pages) { %>
          <li class="nav-item">
            <a class="nav-link text-capitalize" href="<%=webPage.name%>">
              <%=webPage.displayName%>
            </a>
          </li>
        <% } %>
      </ul>

      <% String includeFile = request.getParameter("additionalItemsFile"); %>
      <% if (includeFile != null) { %>
        <!-- Included items -->
        <jsp:include page="<%=includeFile%>"></jsp:include>
      <% } %>
    </div>
  </div>
</nav>

<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="model.utils.Page"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

  <nav <%=PageUtils.mainNavSetup%>>
  <div class="container-fluid">
    <!-- Home link -->
    <form
      action="/<%=PageUtils.pageName%>/RootPGR"
      method="post"
      class="navbar-brand p-0 m-0"
      aria-current="page"
      id="logo-form"
    >
      <!-- Here reset session vars like this: -->
      <!-- <input < %=PageUtils.hiddenInputSetup(SessVars.VAR_NAME, "-1")%> /> -->
      <!--         ^ obviously remove this space -->
      <img class="d-block" style="height: 2em;" src="img/logo.png" id="logo" />
    </form>

    <!-- Toggler -->
    <button <%=PageUtils.mainNavbarTogglerButtonSetup%>>
      <span class="navbar-toggler-icon"></span>
    </button>

    <div <%=PageUtils.mainNavbarSetup%>>
      <!-- Other pages links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <% for (Page webPage : ServletConfig.pages) { %>
          <li class="nav-item">
            <a class="nav-link text-capitalize" href="<%=webPage.href%>">
              <%=webPage.displayName%>
            </a>
          </li>
        <% } %>
      </ul>

      <% String includeFile = (String) request.getParameter("ADDITIONAL_ITEMS_FILE"); %>
      <% if (includeFile != null) { %>
        <!-- Included items -->
        <jsp:include page="<%=includeFile%>"></jsp:include>
      <% } %>
    </div>
  </div>
</nav>

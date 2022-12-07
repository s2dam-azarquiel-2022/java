<%@page import="model.entity.CitySelectOption"%>
<%@page import="controller.servlet.ServletConfig.ReqVars"%>
<%@page import="model.entity.City"%>
<%@page import="java.util.ArrayList"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.Root.RootOptions"%>
<%@page import="controller.servlet.ServletConfig.SessVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<form action="." method="get" class="me-3 mb-2 mb-lg-0">
  <input
    name="<%=SessVars.ROOT_OPTION.name()%>"
    value="<%=RootOptions.CITIES.name()%>"
    class="d-none"
  />
  <button type="submit" class="btn border-1 border-white bg-dark text-white">Ciudades</button>
</form>

<%
ArrayList<CitySelectOption> cities = (ArrayList<CitySelectOption>) session.getAttribute(SessVars.CITY_SELECT_OPTIONS.name());
%>

<form action="." method="get" class="me-3">
  <input
    name="<%=SessVars.ROOT_OPTION.name()%>"
    value="<%=RootOptions.ROUTES.name()%>"
    class="d-none"
  />
  <select
    name="<%=ReqVars.CITY_ID.name()%>"
    <%=PageUtils.mainFormControlSetup%>
    onchange="this.form.submit()">
    <option value="" selected class="d-none">Rutas</option>
    <% for (CitySelectOption city : cities) { %>
      <option value="<%=city.id%>"><%=city.name%></option>
    <% } %>
  </select>
</form>
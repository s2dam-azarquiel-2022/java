<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.ServletConfig.SessVars"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entity.Category"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
@SuppressWarnings("unchecked")
ArrayList<Category> categories = (ArrayList<Category>)
  session.getAttribute(SessVars.CATEGORIES.name());

int selectedCategory;
try {
  selectedCategory = Integer.valueOf(
    (String) session.getAttribute(SessVars.SELECTED_CATEGORY.name())
  );
} catch (Exception e) {
  selectedCategory = -1;
}
%>

<form action="." method="post" class="me-3">
  <select
    <%=PageUtils.mainFormControlSetup%>
    name="<%=SessVars.SELECTED_CATEGORY.name()%>"
    id="category"
    onchange="this.form.submit()"
  >
    <% if (selectedCategory == -1) { %>
      <option value="" selected class="d-none">Elije categoria</option>
      <% for (Category category : categories) { %>
        <option value="<%=category.id%>"><%=category.name%></option>
      <% } %>
    <% } else { %>
      <option value="-1">Todas</option>
      <% for (Category category : categories) { %>
        <% if (category.id == selectedCategory) { %>
          <option value="<%=category.id%>" selected><%=category.name%></option>
        <% } else { %>
          <option value="<%=category.id%>"><%=category.name%></option>
        <% } %>
      <% } %>
    <% } %>
  </select>
</form>

<button <%=PageUtils.mainModalButtonSetup("addJokeModal")%>>
  Nueva broma
</button>

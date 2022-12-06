<%@page import="model.entity.Category"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.ServletConfig.SessVars"%>
<%@page import="java.util.ArrayList"%>

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

<div <%=PageUtils.mainModalSetup("addJokeModal")%>>
  <div class="modal-dialog">
    <div class="modal-content bg-dark text-white">
      <div class="modal-header">
        <h1 class="modal-title fs-5">Nuevo chiste</h1>
      </div>
      <div class="modal-body">
        <form action="/Chistes/AddJoke" method="post" class="d-grid gap-3">
          <div>
            <label for="nickname" class="form-label">Apodo</label>
            <input id="nickname" type="text" <%=PageUtils.mainFormControlSetup%> />
          </div>
          <div>
            <label for="categoryID" class="form-label">Categoria</label>
            <select id="categoryID" <%=PageUtils.mainFormControlSetup%>>
              <option value="" selected class="d-none">Elije categoria</option>
              <% for (Category category : categories) { %>
                <option value="<%=category.id%>"><%=category.name%></option>
              <% } %>
            </select>
          </div>
          <div>
            <label for="title" class="form-label">Titulo</label>
            <input id="title" type="text" <%=PageUtils.mainFormControlSetup%> />
          </div>
          <div>
            <label for="description" class="form-label">Descripcion</label>
            <input id="description" type="text" <%=PageUtils.mainFormControlSetup%> />
          </div>
          <div class="d-flex justify-content-between">
            <button class="btn btn-primary" type="submit">Aceptar</button>
            <button <%=PageUtils.mainModalCloseBtnSetup%>>Cancelar</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

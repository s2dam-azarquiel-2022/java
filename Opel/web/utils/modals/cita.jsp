<%@page import="java.util.List"%>
<%@page import="model.entity.Modelo"%>
<%@page import="controller.servlet.Root"%>
<%@page import="controller.servlet.Root.Option"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="controller.servlet.Root"%>
<%@page import="controller.servlet.Root.Option"%>
<%@page import="controller.utils.ServletUtils"  %>
<%@page import="controller.utils.ServletConfig.SessVars"  %>
<%@page import="model.entity.Usuario"%>
<%@page import="view.PageUtils"%>


<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<% Usuario login = ServletUtils.getAttr(session, SessVars.LOGIN); %>

<% if (login != null) { %>
  <div <%=PageUtils.mainModalSetup("cita")%>>
    <div class="modal-dialog">
      <div <%=PageUtils.mainModalContentSetup%>>
        <div class="modal-header">
          <h5 class="modal-title mx-auto">Peticion de prueba</h5>
        </div>
        <div class="modal-body text-center">
          <form <%=PageUtils.mainFormSetup(Root.opt(Option.PEDIR_CITA))%>>
            <p>Se va a proceder a registrar cita a <%=login.getNick()%> a fecha de hoy del siguiente modelo:</p>
            <select <%=PageUtils.mainSelecSetup(ReqVars.MODEL_ID)%>>
              <option value="-1" selected="true">Elige modelo</option>
              <% for (Modelo model : ServletUtils.<List<Modelo>>getAttr(session, SessVars.COCHES)) { %>
                <option value="<%=model.getId()%>"><%=model.getNombre()%></option>
              <% } %>
            </select>
            <p class="">Le avisaremos informando del dia que puede realizar la prueba.</p>
            <div class="d-flex justify-content-end gap-2">
              <button <%=PageUtils.mainModalCloseBtnSetup%>>Cancel</button>
              <button class="btn btn-success" type="submit">Solicitar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
<% } %>

<%@page import="controller.servlet.Root"%>
<%@page import="controller.servlet.Root.Option"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("reservas", "modal-xl")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">RESERVAS</h5>
      </div>
      <div class="modal-body">
        <h3 class="habitacion text-center"></h3>
        <div class="reservas mt-4"></div>
      </div>
    </div>
  </div>
</div>
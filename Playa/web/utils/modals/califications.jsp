<%@page import="controller.servlet.RootPGR"%>
<%@page import="controller.servlet.RootPGR.Option"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("califications")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Calificacion de la playa</h5>
      </div>
      <div class="modal-body">
        <h3 class="beach-name text-center"></h3>
        <div class="califications mt-4"></div>
      </div>
    </div>
  </div>
</div>

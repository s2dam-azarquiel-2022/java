<%@page import="model.entity.Porra"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="controller.servlet.ServletUtils"%>
<%@page import="model.entity.Partido"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.servlet.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("matchBetsModal")%>>
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-xl">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Apuestas del x - y</h5>
        <button <%=PageUtils.mainModalCloseBtnXSetup%>></button>
      </div>
      <div class="modal-body text-center">
      </div>
    </div>
  </div>
</div>

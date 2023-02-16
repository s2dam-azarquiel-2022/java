<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("detail", "modal-lg")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-body">
        <h3 class="model-name text-center"></h3>
        <img class="model-img mt-4 img-fluid w-100"></img>
        <div class="model-acabados mt-4"></div>
      </div>
    </div>
  </div>
</div>
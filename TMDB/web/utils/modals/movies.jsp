<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("movies")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title text-center text-success w-100">Movies</h5>
      </div>
      <div class="modal-body">
        <h3 class="person-name text-center"></h3>
        <div class="movies mt-4"></div>
      </div>
    </div>
  </div>
</div>
<%@page import="controller.servlet.RootPGR"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("loginModal")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Login | Register</h5>
      </div>
      <div class="modal-body">
        <form <%=PageUtils.mainFormSetup("RootPGR")%>>
          <input <%=PageUtils.hiddenInputSetup(ReqVars.OPTION, RootPGR.Option.LOGIN.name())%> />
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.DNI)%>>DNI</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.DNI)%> />
          </div>
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.USERNAME)%>>User</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.USERNAME)%> />
          </div>
          <div class="d-flex justify-content-between">
            <button class="btn btn-success" type="submit">Login | Register</button>
            <button <%=PageUtils.mainModalCloseBtnSetup%>>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

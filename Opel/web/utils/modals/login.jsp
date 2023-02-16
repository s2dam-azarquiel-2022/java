<%@page import="controller.servlet.Root"%>
<%@page import="controller.servlet.Root.Option"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("login")%>>
  <div class="modal-dialog">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Login | Register</h5>
      </div>
      <div class="modal-body">
        <form <%=PageUtils.mainFormSetup(Root.opt(Option.LOGIN))%>>
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.NICK)%>>Nickname</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.NICK)%> />
          </div>
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.PASSWORD)%>>Password</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.PASSWORD, "password")%> />
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
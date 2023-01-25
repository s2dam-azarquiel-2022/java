<%@page import="model.entity.Porra"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="model.entity.Partido"%>
<%@page import="view.PageUtils"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<div <%=PageUtils.mainModalSetup("addBetModal")%>>
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-xl">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Apuesta para el x - y</h5>
      </div>
      <div class="modal-body">
        <form <%=PageUtils.mainFormSetup("AddBet")%>>
          <input <%=PageUtils.hiddenInputSetup(ReqVars.SELECTED_MATCH, "-1")%> />
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.SCORE_LOCAL)%>>Goles local</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.SCORE_LOCAL)%> />
          </div>
          <div>
            <label <%=PageUtils.mainLabelSetup(ReqVars.SCORE_VISITANT)%>>Goles visitante</label>
            <input <%=PageUtils.mainInputSetup(ReqVars.SCORE_VISITANT)%> />
          </div>
          <div class="d-flex justify-content-between">
            <button class="btn btn-success" type="submit">Apostar</button>
            <button <%=PageUtils.mainModalCloseBtnSetup%>>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

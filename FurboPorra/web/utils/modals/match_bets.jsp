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

<%
  Partido match = ServletUtils.getReq(request, ReqVars.SELECTED_MATCH);
  HashMap<String, Integer> bets = new HashMap();
  for (Porra bet : match.getPorraList()) {
    bets.merge(
      bet.getGoleslocal() + "-" + bet.getGolesvisitante(),
      1,
      Integer::sum
    );
  }
%>

<div <%=PageUtils.mainModalSetup("matchBetsModal-" + match.getIdpartido())%>>
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-xl">
    <div <%=PageUtils.mainModalContentSetup%>>
      <div class="modal-header">
        <h5 class="modal-title">Apuestas del <%=match.getLocal().getNombre()%> - <%=match.getVisitante().getNombre()%></h5>
        <button <%=PageUtils.mainModalCloseBtnXSetup%>></button>
      </div>
      <div class="modal-body text-center">
        <% for (final Entry<String, Integer> bet : bets.entrySet()) { %>
          <h6><%=bet.getKey()%> -> <%=bet.getValue()%> apuestas</h6>
        <% } %>
      </div>
    </div>
  </div>
</div>

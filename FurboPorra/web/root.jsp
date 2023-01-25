<%@page import="model.entity.Partido"%>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="model.entity.RoundSelectView"%>
<%@page import="view.PageUtils"%>
<%@page import="model.utils.Page"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  List<RoundSelectView> rounds = ServletUtils.getSess(session, SessVars.ROUND_SELECT_VIEWS);
  int selectedRound;
  try { selectedRound = Integer.valueOf(ServletUtils.getSess(session, SessVars.SELECTED_ROUND)); }
  catch (Exception e) { selectedRound = -1; }
  List<Partido> matches = ServletUtils.getReq(request, ReqVars.MATCH_LIST);
  boolean logedIn = ServletUtils.getSess(session, SessVars.LOGIN) != null;
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title><%=PageUtils.pageName%></title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="ADDITIONAL_ITEMS_FILE" value="root.jsp" />
    </jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
      <form <%=PageUtils.mainFormSetup("")%>>
        <select <%=PageUtils.mainSelectOnlySetup(SessVars.SELECTED_ROUND)%>>
          <% if (selectedRound == -1) { %>
            <option value="" selected class="d-none">Elije jornada</option>
            <% for (RoundSelectView round : rounds) { %>
              <option value="<%=round.id%>"><%=round.name%></option>
            <% } %>
          <% } else { %>
            <option value="-1">Ninguna</option>
            <% for (RoundSelectView round : rounds) { %>
              <% if (round.id == selectedRound) { %>
                <option value="<%=round.id%>" selected><%=round.name%></option>
              <% } else { %>
                <option value="<%=round.id%>"><%=round.name%></option>
              <% } %>
            <% } %>
          <% } %>
        </select>
      </form>
      <div class="d-grid gap-3 mt-4">
        <% for (Partido match : matches) { %>
          <div class="p-2 bg-dark row text-center align-items-center gap-3 gap-md-0 py-4 py-md-1">
            <div class="col-md-2 col-12">
              <% if (logedIn) { %>
                <button
                  role="button"
                  matchID="<%=match.getIdpartido()%>"
                  local="<%=match.getLocal().getNombre()%>"
                  visitant="<%=match.getVisitante().getNombre()%>"
                  class="btn btn-success matchBets"
                >Info</button>
              <% } %>
            </div>
            <div class="col-md-1 d-none d-md-block"><img class="img-fluid" src="<%=match.getLocal().getEscudo()%>" /></div>
            <div class="col-md-2"><%=match.getLocal().getNombre()%></div>
            <div class="col-md-2"><%=match.getGoleslocal()%> - <%=match.getGolesvisitante()%></div>
            <div class="col-md-2"><%=match.getVisitante().getNombre()%></div>
            <div class="col-md-1 d-none d-md-block"><img class="img-fluid" src="<%=match.getVisitante().getEscudo()%>" /></div>
            <div class="col-md-2 col-12">
              <% if (logedIn) { %>
                <button
                  role="button"
                  matchID="<%=match.getIdpartido()%>"
                  local="<%=match.getLocal().getNombre()%>"
                  visitant="<%=match.getVisitante().getNombre()%>"
                  class="btn btn-success addBet"
                >Apostar</button>
              <% } %>
            </div>
          </div>
        <% } %>
      </div>
    </div>
    <div id="matchBetsCache"></div>
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
      <div id="errToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header text-danger">
          <svg class="err-icon me-2 text-danger" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
          </svg>
          <strong class="me-auto"><%=PageUtils.pageName%></strong>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
          There was an error when fetching this matches' bets.
        </div>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
    <jsp:include page="utils/modals/login.jsp"></jsp:include>
    <jsp:include page="utils/modals/match_bets.jsp"></jsp:include>
    <jsp:include page="utils/modals/add_bet.jsp"></jsp:include>
    <script src="js/match_bets.js"></script>
    <script src="js/add_bet.js"></script>
  </body>
</html>

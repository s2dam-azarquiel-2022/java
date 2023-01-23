<%@page import="model.entity.Partido"%>
<%@page import="java.util.List"%>
<%@page import="controller.servlet.ServletUtils"%>
<%@page import="model.entity.RoundSelectView"%>
<%@page import="view.PageUtils"%>
<%@page import="model.utils.Page"%>
<%@page import="controller.servlet.ServletConfig.ReqVars"%>
<%@page import="controller.servlet.ServletConfig.SessVars"%>

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
          <div class="p-2 bg-dark row text-center align-items-center gap-3 gap-md-0 py-4 py-md-0">
            <div class="col-md-2 col-12"><button role="button" class="btn btn-success">Info</button></div>
            <div class="col-md-1 d-none d-md-block"><img class="img-fluid" src="<%=match.getLocal().getEscudo()%>" /></div>
            <div class="col-md-2"><%=match.getLocal().getNombre()%></div>
            <div class="col-md-2"><%=match.getGoleslocal()%> - <%=match.getGolesvisitante()%></div>
            <div class="col-md-2"><%=match.getVisitante().getNombre()%></div>
            <div class="col-md-1 d-none d-md-block"><img class="img-fluid" src="<%=match.getVisitante().getEscudo()%>" /></div>
            <div class="col-md-2 col-12"><button role="button" class="btn btn-success">Apostar</button></div>
          </div>
        <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
    <jsp:include page="utils/modals/login.jsp"></jsp:include>
  </body>
</html>

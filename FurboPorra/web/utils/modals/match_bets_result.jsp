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
  HashMap<String, Integer> bets = new HashMap();
  for (Porra bet : ServletUtils.<Partido>getReq(request, ReqVars.SELECTED_MATCH).getPorraList()) {
    bets.merge(bet.getGoleslocal() + "-" + bet.getGolesvisitante(), 1, Integer::sum);
  }
%>

<% for (final Entry<String, Integer> bet : bets.entrySet()) { %>
  <h6><%=bet.getKey()%> -> <%=bet.getValue()%> apuestas</h6>
<% } %>

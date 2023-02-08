<%@page import="model.entity.Calification"%>
<%@page import="javax.persistence.Tuple"%>
<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Images"%>
<%@page import="java.util.List"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="model.entity.Playa"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<% for (Calification calification : ServletUtils.<List<Calification>>getReqAttr(request, ReqVars.CALIFICATIONS)) { %>
  <p class="text-center">
    <img src="img/rate/<%=calification.id%>.png" style="height: 2em" />
    <%=calification.count%>
  </p>
<% } %>

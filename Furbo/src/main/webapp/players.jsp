<%@page import="view.PageUtils"%>
<%@page import="model.entity.Page"%>
<%@page import="controller.ServletConfig"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupTop.html"></jsp:include>
    <title>Furbo</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp"></jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
    </div>
    <jsp:include page="utils/setupBottom.html"></jsp:include>
  </body>
</html>
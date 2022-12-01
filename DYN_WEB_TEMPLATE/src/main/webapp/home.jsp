<%@page import="view.PageUtils"%>
<%@page import="model.entity.Page"%>
<%@page import="controller.servlet.ServletConfig"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <title>Furbo</title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp"></jsp:include>
    <div <%=PageUtils.mainDivSetup%>>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
  </body>
</html>

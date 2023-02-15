<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="controller.servlet.Root"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
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
    <div <%=PageUtils.mainDivSetup("container d-flex")%>>
      <div class='card text-white bg-dark align-self-center w-100'>
        <div class="card-body">
          <h5 class="card-title text-center text-uppercase">entradas al hotel</h5>
          <form <%=PageUtils.mainFormSetup(Root.opt(Root.Option.HOSPEDAR))%>>
            <div>
              <label <%=PageUtils.mainLabelSetup(ReqVars.DNI)%>>DNI</label>
              <input <%=PageUtils.mainInputSetup(ReqVars.DNI)%> />
            </div>
            <div>
              <label <%=PageUtils.mainLabelSetup(ReqVars.HABITACION)%>>Habitacion</label>
              <select <%=PageUtils.mainSelecSetup(ReqVars.HABITACION)%>>
              </select>
            </div>
            <div>
              <label <%=PageUtils.mainLabelSetup(ReqVars.FECHA_ENTRADA)%>>Fecha entrada</label>
              <input <%=PageUtils.mainInputSetup(ReqVars.FECHA_ENTRADA, "date")%> />
            </div>
            <div>
              <label <%=PageUtils.mainLabelSetup(ReqVars.FECHA_SALIDA)%>>Fecha salida</label>
              <input <%=PageUtils.mainInputSetup(ReqVars.FECHA_SALIDA, "date")%> />
            </div>
          </form>
        </div>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
  </body>
</html>

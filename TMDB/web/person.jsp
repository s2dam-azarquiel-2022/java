<%@page import="controller.servlet.Root"%>
<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.PersonView"%>
<%@page import="controller.utils.ServletUtils"%>
<%@page import="controller.utils.ServletConfig.SessVars"%>
<%@page import="controller.utils.ServletConfig.ReqVars"%>
<%@page import="java.util.List"%>
<%@page import="view.PageUtils"%>

<%@page
     language="java"
     contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
%>

<%
  List<PersonView> persons = ServletUtils.getAttr(session, SessVars.PERSONS);
  Usuario login = ServletUtils.getAttr(session, SessVars.LOGIN);
  boolean loggedIn = login != null;
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="utils/setupHead.html"></jsp:include>
    <link href="css/stars.css" rel="stylesheet" />
    <title><%=PageUtils.pageName%></title>
  </head>
  <body <%=PageUtils.mainBodySetup%>>
    <jsp:include page="utils/navbar/navbar.jsp">
      <jsp:param name="ADDITIONAL_ITEMS_FILE" value="root.jsp" />
    </jsp:include>
    <div <%=PageUtils.mainDivSetup("container")%>>
      <div class="row">
        <% for (PersonView person : persons) { %>
          <div class="col-12 col-md-6 col-lg-4 mb-4 d-flex">
            <div <%=PageUtils.mainCardSetup%>>
              <% if (person.photo == null) { %>
                <img src="img/null.png" class="card-img-top" />
              <% } else { %>
                <img src="https://image.tmdb.org/t/p/w500<%=person.photo%>" class="card-img-top" />
              <% } %>
              <div class="card-body text-center mt-auto flex-grow-0">
                <h1 class="text-warning"><% for (int i = 1; i <= person.stars; i++) { %>&#9733;<% } %></h1>
                <h5 class="card-title"><%=person.name%></h5>
                <h3 class="stars">
                  <% String href = String.format(
                      "%s%s&%s=%%d&%s=%s",
                      PageUtils.path,
                      Root.opt(Root.Option.RATE),
                      ReqVars.RATE.name(),
                      ReqVars.PERSON_ID,
                      person.id
                    ); %>
                  <% if (loggedIn) for (int i = 1; i <= 5; i++) { %>
                    <a href="<%=String.format(href, i)%>">&#9733;</a>
                  <% } %>
                </h3>
              </div>
              <% if (loggedIn) { %>
                <div class="card-footer d-flex justify-content-center">
                  <button
                    <%=PageUtils.mainModalButtonSetup("movies")%>
                    data-person-name="<%=person.name%>"
                    data-person-id="<%=person.id%>"
                  >MOVIES</button>
                </div>
              <% } %>
            </div>
          </div>
        <% } %>
      </div>
    </div>
    <jsp:include page="utils/setupBodyEnd.html"></jsp:include>
    <jsp:include page="utils/modals/movies.jsp"></jsp:include>
    <script src="js/movies-modal.js"></script>
  </body>
</html>

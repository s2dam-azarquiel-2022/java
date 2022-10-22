<%@page import="java.io.IOException"%>
<%@page import="model.Requirements"%>
<%@page import="model.Platforms"%>
<%@page import="model.Price"%>
<%@page import="model.Category"%>
<%@page import="model.Screenshot"%>
<%@page import="model.Data"%>
<%@page import="model.Price"%>
<%@page import="model.Platforms"%>
<%@page import="dao.Detail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String appID = request.getParameter("appID");
  Data data = null;
  try { data = Detail.getDetail(appID).getData(); }
  catch (IOException e) { response.sendError(404); }
%>
<div class="modal" id="detail-<%=appID%>" tabindex="-1">
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h6 class="p-0 m-0 align-self-center text-muted">[<%=data.getType()%>]</h6>
        <h5 class="modal-title text-center w-100"><%=data.getName()%></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body mb-2">
        <div class="row">
          <div class="col-12 col-lg-6 col-xl-4">
            <img class="w-100" src="<%=data.getHeader_image()%>" />
          </div>
          <div class="col-12 col-lg-6 col-xl-8">
            <p><%=data.getShort_description()%></p>
          </div>
          <div class="col-12 col-lg-6 col-xl-4">
            <p class="mt-2 mb-0">
              Genres:
              <% for (Category genre : data.getGenres()) { %>
                <span class="badge rounded-pill text-bg-info">
                  <%=genre.getDescription()%>
                </span>
              <% } %>
            </p>
            <p class="mt-1 mb-3">
              Categories:
              <% for (Category category : data.getCategories()) { %>
                <span class="badge rounded-pill text-bg-success">
                  <%=category.getDescription()%>
                </span>
              <% } %>
            </p>
          </div>
          <div class="col-12 col-lg-6 col-xl-8">
            <p class="mt-2 mb-0">Required age: <%=data.getRequired_age()%></p>
            <p class="mt-1 mb-3">Supported languages: <%=data.getSupported_languages()%></p>
          </div>
          <div class="col-12 col-lg-6 col-xl-4">
            <p class="mb-0">
              <% if (data.getIs_free()) { %>
                Free!
              <% } else { %>
                Price:
                <% Price price = data.getPrice_overview(); %>
                <% if (price.getDiscount_percent() != 0) { %>
                  <del class="text-danger"><%=price.getInitial_formatted()%></del>&nbsp;
                <% } %>
                <%=price.getFinal_formatted()%>
              <% } %>
            </p>
          </div>
          <div class="col-12 col-lg-6 col-xl-8">
            <div class="badge rounded-pill text-bg-secondary px-2 mb-0">
              <span class="align-middle">Supported platforms:</span>
              <%
                Platforms platforms = data.getPlatforms();
                String iconUrl = "https://store.cloudflare.steamstatic.com/public/images/v6/icon_platform_";
              %>
              <% if (platforms.isWindows()) { %>
                <div class="dropdown d-inline">
                  <img src="<%=iconUrl%>win.png"
                    class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" />
                  <div class="dropdown-menu p-2">
                    <% Requirements winRequirements = data.getPc_requirements(); %>
                    <%=winRequirements.getMinimum()!=null ? winRequirements.getMinimum() : ""%>
                    <%=winRequirements.getRecommended()!=null ? winRequirements.getRecommended() : ""%>
                  </div>
                </div>
              <% } %>
              <% if (platforms.isLinux()) { %>
                <div class="dropdown d-inline">
                  <img src="<%=iconUrl%>linux.png"
                    class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" />
                  <div class="dropdown-menu p-2">
                    <% Requirements linuxRequirements = data.getLinux_requirements(); %>
                    <%=linuxRequirements.getMinimum()!=null ? linuxRequirements.getMinimum() : ""%>
                    <%=linuxRequirements.getRecommended()!=null ? linuxRequirements.getRecommended() : ""%>
                  </div>
                </div>
              <% } %>
              <% if (platforms.isMac()) { %>
                <div class="dropdown d-inline">
                  <img src="<%=iconUrl%>mac.png"
                    class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside" />
                  <div class="dropdown-menu p-2">
                    <% Requirements macRequirements = data.getMac_requirements(); %>
                    <%=macRequirements.getMinimum()!=null ? macRequirements.getMinimum() : ""%>
                    <%=macRequirements.getRecommended()!=null ? macRequirements.getRecommended() : ""%>
                  </div>
                </div>
              <% } %>
            </div>
          </div>
        </div>
        <div class="mt-5">
          <p><%=data.getDetailed_description()%></p>
          <% for (Screenshot screenshot : data.getScreenshots()) { %>
            <img class="w-100 my-3" src="<%=screenshot.getPath_full()%>" />
          <% } %>
          <h5>Developers: <%=data.getDevelopers()%></h5>
          <h5>Publishers: <%=data.getPublishers()%></h5>
          <h5><a href="<%=data.getWebsite()%>">Website</a></h5>
        </div>
      </div>
    </div>
  </div>
</div>
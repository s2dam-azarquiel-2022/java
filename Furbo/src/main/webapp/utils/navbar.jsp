<%@page import="controller.ServletConfig"%>
<%@page import="model.entity.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-dark bg-secondary text-white navbar-expand-lg sticky-top">
  <div class="container-fluid">
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarToggler"
      aria-controls="navbarToggler"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarToggler">
      <a class="navbar-brand" aria-current="page" href="#">
        <img style="height: 2em;" src="img/logo.png" />
      </a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <% for (Page webPage : ServletConfig.pages) { %>
          <li class="nav-item">
            <a class="nav-link text-capitalize" href="<%=webPage.name%>">
              <%=webPage.displayName%>
            </a>
          </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>

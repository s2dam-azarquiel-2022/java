package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Util;

public class Root extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Root() {
    super();
  }

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    HttpSession session = request.getSession();
    try {
      Util.checkConnection(session);
    } catch (Exception e) {
      // TODO: 500 page
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

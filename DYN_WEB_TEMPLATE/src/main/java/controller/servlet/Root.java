package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.servlet.ServletConfig.requestVars;

public class Root extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Root() { super(); }

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
    HttpSession session = request.getSession();
    try {
      ServletUtils.checkConnection(session);
    } catch (Exception e) {
      request.setAttribute(
        requestVars.ERR_TITLE.name(),
        e.toString()
      );
      request.setAttribute(
        requestVars.ERR_MESSAGE.name(),
        e.getMessage()
      );
      dispatcher = request.getRequestDispatcher("err/500.jsp");
    } finally {
      dispatcher.forward(request, response);
    }
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

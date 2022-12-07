package controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.servlet.ServletConfig.ReqVars;
import model.dao.RouteDAO;

@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public AddReview() { super(); }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    ServletUtils.servletTry(req, response, null, (sess, dispatcher) -> {
      Connection con = ServletUtils.checkConnection(sess);
      RouteDAO.addReview(
        con,
        Integer.valueOf(req.getParameter(ReqVars.ROUTE_ID.name())),
        Integer.valueOf(req.getParameter(ReqVars.STARS.name()))
      );
    });
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

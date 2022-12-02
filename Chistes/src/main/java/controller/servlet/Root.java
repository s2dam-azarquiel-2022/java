package controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.servlet.ServletConfig.ReqVars;
import model.dao.JokeDAO;

public class Root extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Root() { super(); }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
    HttpSession sess = req.getSession();
    try {
      Connection con = ServletUtils.checkConnection(sess);
      ServletUtils.setCategoriesIfNull(sess, req, con);
      int selectedCategory = ServletUtils.updateSelectedCategory(sess, req);
      req.setAttribute(ReqVars.JOKES.name(), JokeDAO.get(con, selectedCategory));
    } catch (Exception e) {
      req.setAttribute(ReqVars.ERR_TITLE.name(), e.toString());
      req.setAttribute(ReqVars.ERR_MESSAGE.name(), e.getMessage());
      dispatcher = req.getRequestDispatcher("err/500.jsp");
      e.printStackTrace();
    } finally {
      dispatcher.forward(req, response);
    }
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

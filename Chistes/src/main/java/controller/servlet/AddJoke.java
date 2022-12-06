package controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.servlet.ServletConfig.ReqVars;
import model.dao.JokeDAO;
import model.entity.Joke;

@WebServlet("/AddJoke")
public class AddJoke extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public AddJoke() { super(); }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("addedJoke.jsp");
    HttpSession sess = req.getSession();
    try {
      Connection con = ServletUtils.checkConnection(sess);
      Joke addedJoke = ServletUtils.getAddedJokeAndSetIt(req);
      JokeDAO.add(con, addedJoke);
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

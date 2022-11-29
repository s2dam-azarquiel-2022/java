package controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ServletConfig.requestVars;
import controller.Util;
import model.dao.TeamDAO;

@WebServlet("/teams")
public class Teams extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Teams() {
    super();
  }

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Connection connection = Util.checkConnection(session);
    String currentSeason = Util.checkCurrentSeason(session, connection);
    try {
      request.setAttribute(
        requestVars.TEAMS.name(),
        TeamDAO.getAll(connection, currentSeason)
      );
    } catch (SQLException e) {
      // TODO: 500 page
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("teams.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

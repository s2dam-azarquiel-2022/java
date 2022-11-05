package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionHandler;
import model.entity.Bike;
import model.entity.Brand;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Controller() {
    super();
  }

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Connection connection = (Connection) session.getAttribute("connection");
    if (connection == null) {
      connection = ConnectionHandler.connect();
      session.setAttribute("connection", connection);
    }

    RequestDispatcher dispatcher;
    String option = request.getParameter("option");
    if (option.equals("home")) {
      ArrayList<Bike> bikes = model.dao.Bike.getBikes("%", connection);
      ArrayList<Brand> brands = model.dao.Brand.getBrands(connection);

      session.setAttribute("brands", brands);
      request.setAttribute("bikes", bikes);

      dispatcher = request.getRequestDispatcher("home.jsp");
      dispatcher.forward(request, response);
    } else if (option.equals("brand")) {
      String brand = request.getParameter("brand");
      ArrayList<Bike> bikes = model.dao.Bike.getBikes(brand, connection);

      request.setAttribute("bikes", bikes);

      dispatcher = request.getRequestDispatcher("home.jsp");
      dispatcher.forward(request, response);
    } else if (option.equals("order")) {
      String order = request.getParameter("order");

      dispatcher = request.getRequestDispatcher("home.jsp");
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

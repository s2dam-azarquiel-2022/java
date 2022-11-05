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
    if (option.equals("home") || session.getAttribute("brands") == null) {
      ArrayList<Brand> brands = model.dao.Brand.getBrands(connection);
      session.setAttribute("brands", brands);
    } else if (option.equals("brand")) {
      session.setAttribute("currentBrand", request.getParameter("brand"));
    } else if (option.equals("order")) {
      session.setAttribute("currentOrder", request.getParameter("order"));
    }

    String brand = (String) session.getAttribute("currentBrand");
    String orderRaw = (String) session.getAttribute("currentOrder");
    String[] orderArr = orderRaw == null ? new String[0] : orderRaw.split("->");
    ArrayList<Bike> bikes = model.dao.Bike.getBikes(
        brand == null ? "%" : brand,
        connection,
        orderArr.length > 0 ? orderArr[0] : "",
        orderArr.length > 1 ? orderArr[1] : ""
    );

    request.setAttribute("bikes", bikes);

    dispatcher = request.getRequestDispatcher("home.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

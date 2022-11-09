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
import model.dao.CategoryDAO;
import model.dao.ProductDAO;
import model.entity.Category;
import model.entity.Product;

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
    String categoryID = request.getParameter("id");

    if (option.equals("home") || session.getAttribute("brands") == null) {
      ArrayList<Category> categories = CategoryDAO.getCategories(connection);
      session.setAttribute("categories", categories);
    } if (option.equals("category")) {
      session.setAttribute("currentCategory", categoryID);
    }

    ArrayList<Product> products = ProductDAO.getProducts(
      connection,
      categoryID == null ? "%" : categoryID
    );
    request.setAttribute("products", products);

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

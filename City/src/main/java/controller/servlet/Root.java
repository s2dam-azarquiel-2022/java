package controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.servlet.ServletConfig.ReqVars;
import controller.servlet.ServletConfig.SessVars;
import model.dao.CityDAO;
import model.dao.RouteDAO;

public class Root extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Root() { super(); }

  public static enum RootOptions {
    CITIES,
    ROUTES,
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, "/home.jsp", (sess, dispatcher) -> {
      Connection con = ServletUtils.checkConnection(sess);
      ServletUtils.setIfNull(sess, SessVars.CITY_SELECT_OPTIONS, () -> {
        return CityDAO.getSelectOptions(con);
      });
      String option = ServletUtils.updateIfNotNullAndGet(
        sess, req, SessVars.ROOT_OPTION, () -> {
          return RootOptions.CITIES.name();
        }
      );
      switch (RootOptions.valueOf(option)) {
      case CITIES:
        req.setAttribute(ReqVars.CITIES.name(), CityDAO.get(con));
        break;
      case ROUTES:
        req.setAttribute(
          ReqVars.ROUTES.name(),
          RouteDAO.get(con, req.getParameter(ReqVars.CITY_ID.name()))
        );
        break;
      default: throw new Exception("Unknown option");
      }
    });
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

package controller;

import java.sql.Connection;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import controller.ServletConfig.sessionVars;
import controller.connection.ConnectionHandler;
import model.dao.SeasonDAO;

public class Util {
  private static <T> T checkNull(
    HttpSession session,
    sessionVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    @SuppressWarnings("unchecked")
    T val = (T) session.getAttribute(var.name());
    if (val == null) {
      val = fDefaultVal.call();
      session.setAttribute(var.name(), val);
    }
    return val;
  }

  public static Connection checkConnection(HttpSession s) throws Exception {
    return checkNull(s, sessionVars.CONNECTION, () -> {
      return ConnectionHandler.connect();
    });
  }

  public static String checkCurrentSeason(
    HttpSession s,
    Connection c
  ) throws Exception {
    return checkNull(s, sessionVars.CURRENT_SEASON, () -> {
      return SeasonDAO.getMostRecent(c);
    });
  }
}

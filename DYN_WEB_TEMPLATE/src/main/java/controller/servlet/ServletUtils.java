package controller.servlet;

import java.sql.Connection;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import controller.connection.ConnectionHandler;
import controller.servlet.ServletConfig.sessionVars;

public class ServletUtils {
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
}

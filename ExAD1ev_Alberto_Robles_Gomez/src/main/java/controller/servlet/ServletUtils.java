package controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.concurrent.Callable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.connection.ConnectionHandler;
import controller.servlet.ServletConfig.ReqVars;
import controller.servlet.ServletConfig.SessVars;
import model.utils.ServletTryFunction;

public class ServletUtils {
  private static <T> T checkNull(
    HttpSession sess,
    SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    @SuppressWarnings("unchecked")
    T val = (T) sess.getAttribute(var.name());
    if (val == null) {
      val = fDefaultVal.call();
      sess.setAttribute(var.name(), val);
    }
    return val;
  }

  private static <T> T updateIfNotNull(
    HttpSession sess,
    HttpServletRequest req,
    SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    @SuppressWarnings("unchecked")
    T val = (T) req.getParameter(var.name());
    @SuppressWarnings("unchecked")
    T sVal = (T) sess.getAttribute(var.name());
    if (val == null && sVal == null) {
      val = fDefaultVal.call();
      sess.setAttribute(var.name(), val);
    } else if (val == null) {
      val = sVal;
    } else {
      sess.setAttribute(var.name(), val);
    }
    return val;
  }

  private static <T> void setIfNull(
    HttpSession sess,
    HttpServletRequest req,
    SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    if (sess.getAttribute(var.name()) == null) {
      sess.setAttribute(var.name(), fDefaultVal.call());
    }
  }

  public static Connection checkConnection(
    HttpSession sess
  ) throws Exception {
    return checkNull(sess, SessVars.CONNECTION, () -> {
      return ConnectionHandler.connect();
    });
  }

  public static void servletTry(
    HttpServletRequest req,
    HttpServletResponse response,
    String defaultDispatchedFile,
    ServletTryFunction f
  ) throws ServletException, IOException {
    HttpSession sess = req.getSession();
    RequestDispatcher dispatcher = req.getRequestDispatcher(defaultDispatchedFile);
    try { f.run(sess, dispatcher); }
    catch (Exception e) {
      req.setAttribute(ReqVars.ERR_TITLE.name(), e.toString());
      req.setAttribute(ReqVars.ERR_MESSAGE.name(), e.getMessage());
      dispatcher = req.getRequestDispatcher("/err/500.jsp");
    } finally {
      dispatcher.forward(req, response);
    }
  }
}

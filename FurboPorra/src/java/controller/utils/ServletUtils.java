package controller.utils;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import javax.persistence.EntityManager;
import model.utils.ServletTryFunction;

public class ServletUtils {
  public static <T> T getCheckingNull(
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

  public static <T> void setIfNull(
    HttpSession sess,
    SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    if (sess.getAttribute(var.name()) == null) {
      sess.setAttribute(var.name(), fDefaultVal.call());
    }
  }

  public static <T> T updateIfNotNullAndGet(
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

  public static <T> void setCheckingNull(
    HttpSession sess,
    SessVars var,
    Object pk,
    Class<T> c,
    EntityManager entityManager,
    Callable<T> fDefaultVal
  ) throws Exception {
    sess.setAttribute(var.name(), JPAUtils.getCheckingNull(pk, c, entityManager, fDefaultVal));
  }

  public static <T> T getSess(
    HttpSession sess,
    SessVars var
  ) { return (T) sess.getAttribute(var.name()); }

  public static <T> T getReq(
    HttpServletRequest req,
    ReqVars var
  ) { return (T) req.getAttribute(var.name()); }

  @SuppressWarnings("CallToPrintStackTrace")
  public static void servletTry(
    HttpServletRequest req,
    HttpServletResponse response,
    String defaultDispatchedFile,
    ServletTryFunction f
  ) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher(defaultDispatchedFile);
    try {
      HttpSession sess = req.getSession();
      EntityManager entityManager = getCheckingNull(sess, SessVars.ENTITY_MANAGER, () -> {
        return JPAUtils.getEntityManagerFactory().createEntityManager();
      });
      f.run(sess, entityManager, dispatcher);
    } catch (Exception e) {
      req.setAttribute(ReqVars.ERR_TITLE.name(), e.toString());
      req.setAttribute(ReqVars.ERR_MESSAGE.name(), e.getMessage());
      dispatcher = req.getRequestDispatcher("/err/500.jsp");
      e.printStackTrace();
    } finally {
      if (dispatcher != null) dispatcher.forward(req, response);
    }
  }
}

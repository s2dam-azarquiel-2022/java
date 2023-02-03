package controller.utils;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.utils.ServletTryFunction;

public class ServletUtils {
  public static <T> T getSettingIfNull(
    HttpSession sess,
    SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
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

  public static <T> T getUpdatingSessViaReq(
    HttpSession sess,
    HttpServletRequest req,
    SessVars var,
    Function<String, T> f
  ) {
    T val = f.apply(req.getParameter(var.name()));
    sess.setAttribute(var.name(), val);
    return val;
  }

  public static <T> void updateSessViaReq(
    HttpSession sess,
    HttpServletRequest req,
    SessVars var,
    Function<String, T> f
  ) { sess.setAttribute(var.name(), f.apply(req.getParameter(var.name()))); }

  public static <T> void setCheckingNull(
    HttpSession sess,
    SessVars var,
    Object pk,
    Class<T> c,
    EntityManager entityManager,
    Callable<T> fDefaultVal
  ) throws Exception {
    sess.setAttribute(var.name(), JPAUtils.getSettingIfNull(pk, c, entityManager, fDefaultVal));
  }

  public static <T> T getSess(
    HttpSession sess,
    SessVars var
  ) { return (T) sess.getAttribute(var.name()); }

  public static <T> T getReqAttr(
    HttpServletRequest req,
    ReqVars var
  ) { return (T) req.getAttribute(var.name()); }

  public static <T> T getReqParam(
    HttpServletRequest req,
    ReqVars var,
    Function<String, T> f
  ) { return f.apply(req.getParameter(var.name())); }

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
      EntityManager entityManager = getSettingIfNull(sess, SessVars.ENTITY_MANAGER, () -> {
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

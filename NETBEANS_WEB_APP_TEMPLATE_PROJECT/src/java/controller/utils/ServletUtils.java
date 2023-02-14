package controller.utils;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import java.util.concurrent.Callable;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

  public static <T> void set(
    HttpSession sess,
    SessVars var,
    T val
  ) { sess.setAttribute(var.name(), val); }

  public static <T> void set(
    HttpServletRequest req,
    ReqVars var,
    T val
  ) { req.setAttribute(var.name(), val); }

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

  public static <T> T getAttr(
    HttpSession sess,
    SessVars var
  ) { return (T) sess.getAttribute(var.name()); }

  public static <T> T getAttr(
    HttpServletRequest req,
    ReqVars var
  ) { return (T) req.getAttribute(var.name()); }

  public static <T> T getParam(
    HttpServletRequest req,
    ReqVars var,
    Function<String, T> f
  ) { return f.apply(req.getParameter(var.name())); }

  public static String getParam(
    HttpServletRequest req,
    ReqVars var
  ) { return req.getParameter(var.name()); }
}

package controller.servlet;

import java.sql.Connection;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.connection.ConnectionHandler;
import controller.servlet.ServletConfig.ReqVars;
import controller.servlet.ServletConfig.SessVars;
import model.dao.CategoryDAO;
import model.entity.Joke;

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

  public static int updateSelectedCategory(
    HttpSession sess,
    HttpServletRequest req
  ) throws Exception {
    return Integer.valueOf(
      updateIfNotNull(sess, req, SessVars.SELECTED_CATEGORY, () -> {
        return "-1";
      })
    );
  }

  public static void setCategoriesIfNull(
    HttpSession sess,
    HttpServletRequest req,
    Connection con
  ) throws Exception {
    setIfNull(sess, req, SessVars.CATEGORIES, () -> {
      return CategoryDAO.get(con);
    });
  }

  public static Joke getAddedJokeAndSetIt(
    HttpServletRequest req
  ) throws Exception {
    System.out.println(req.getParameter(ReqVars.TITLE.name()));
    System.out.println(req.getParameter(ReqVars.DESCRIPTION.name()));
    System.out.println(req.getParameter(ReqVars.NICKNAME.name()));
    Joke addedJoke = new Joke(
      0,
      Integer.valueOf(req.getParameter(ReqVars.CATEGORY.name())),
      req.getParameter(ReqVars.TITLE.name()),
      req.getParameter(ReqVars.DESCRIPTION.name()),
      req.getParameter(ReqVars.NICKNAME.name()),
      0,
      0
    );
    req.setAttribute(ReqVars.ADDED_JOKE.name(), addedJoke);
    return addedJoke;
  }
}

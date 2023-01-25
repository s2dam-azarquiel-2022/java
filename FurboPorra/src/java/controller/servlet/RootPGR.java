package controller.servlet;

import controller.utils.JPAUtils;
import controller.utils.ServletConfig;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.Porra;
import model.entity.Usuario;
import view.PageUtils;

@WebServlet("/RootPGR")
public class RootPGR extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public RootPGR() { super(); }

  private HttpSession sess;
  private HttpServletRequest req;
  private EntityManager entityManager;

  public static enum Option {
    SET_CURRENT_ROUND,
    ADD_BET,
    LOGIN,
    LOGOUT,
  };

  private void setCurrentRound() throws Exception {
    ServletUtils.updateIfNotNullAndGet(
      sess,
      req,
      ServletConfig.SessVars.SELECTED_ROUND,
      () -> { return "-1"; }
    );
  }

  private void addBet() throws Exception {
    Porra bet = new Porra(
      ServletUtils.<Usuario>getSess(sess, ServletConfig.SessVars.LOGIN).getDni(),
      Integer.valueOf(req.getParameter(ReqVars.SELECTED_MATCH.name()))
    );
    bet.setGoleslocal(Short.valueOf(req.getParameter(ReqVars.SCORE_LOCAL.name())));
    bet.setGolesvisitante(Short.valueOf(req.getParameter(ReqVars.SCORE_VISITANT.name())));
    try { JPAUtils.add(Porra.class, entityManager, bet); }
    catch (Exception e) { sess.setAttribute(ServletConfig.SessVars.STATUS.name(), Root.Status.DUPLICATE_BET); }
  }

  private void login() throws Exception {
    String dni = req.getParameter(ReqVars.DNI.name());
    ServletUtils.setCheckingNull(sess, ServletConfig.SessVars.LOGIN, dni, Usuario.class, entityManager, () -> {
      Usuario user = new Usuario(dni);
      user.setNombre((String) req.getParameter(ReqVars.USERNAME.name()));
      return user;
    });
  }

  private void logout() throws Exception {
    sess.setAttribute(ServletConfig.SessVars.LOGIN.name(), null);
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, null, (sess, entityManager, dispatcher) -> {
      this.req = req;
      this.sess = sess;
      this.entityManager = entityManager;
      switch (Option.valueOf(req.getParameter(ReqVars.OPTION.name()))) {
        case SET_CURRENT_ROUND: setCurrentRound(); break;
        case ADD_BET: addBet(); break;
        case LOGIN: login(); break;
        case LOGOUT: logout(); break;
      }
      response.sendRedirect("/" + PageUtils.pageName);
    });
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

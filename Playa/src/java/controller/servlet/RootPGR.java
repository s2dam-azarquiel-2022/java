package controller.servlet;

import controller.utils.JPAUtils;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Usuario;
import view.PageUtils;

@WebServlet("/RootPGR")
public class RootPGR extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public RootPGR() { super(); }

  public static enum Option {
    LOGIN,
    SET_CURRENT_CCAA,
  };

  public static String opt(Option option) {
    return String.format("RootPGR?OPTION=%s", option.name());
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, null, (sess, entityManager, dispatcher) -> {
      switch (Option.valueOf(req.getParameter(ReqVars.OPTION.name()))) {
        case LOGIN:
          String nick = ServletUtils.getReqParam(req, ReqVars.NICK);
          String password = ServletUtils.getReqParam(req, ReqVars.PASSWORD);
          List<Usuario> users = entityManager
            .createNamedQuery("Usuario.findByNick", Usuario.class)
            .setParameter("nick", nick)
            .getResultList();
          if (users.isEmpty()) {
            Usuario user = new Usuario();
            user.setNick(nick);
            user.setPass(password);
            JPAUtils.add(entityManager, user);
            sess.setAttribute(
              SessVars.LOGIN.name(),
              entityManager
                .createNamedQuery("Usuario.findByNick", Usuario.class)
                .setParameter("nick", nick)
                .getResultList()
                .get(0)
            );
          } else {
            Usuario user = users.get(0);
            if (user.getPass().equals(password)) {
              sess.setAttribute(SessVars.LOGIN.name(), user);
            }
          }
          break;

        case SET_CURRENT_CCAA:
          ServletUtils.updateSessViaReq(sess, req, SessVars.SELECTED_CCAA, Short::valueOf);
          break;
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

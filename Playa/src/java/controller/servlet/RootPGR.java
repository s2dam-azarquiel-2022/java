package controller.servlet;

import controller.utils.JPAUtils;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Municipio;
import model.entity.ProvinceSelectView;
import model.entity.Provincia;
import model.entity.TownSelectView;
import model.entity.Usuario;
import view.PageUtils;

@WebServlet("/RootPGR")
public class RootPGR extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public RootPGR() { super(); }

  public static enum Option {
    LOGIN,
    LOGOUT,
    SET_CURRENT_CCAA,
    SET_CURRENT_PROVINCE,
    SET_CURRENT_TOWN,
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

        case LOGOUT:
          sess.setAttribute(SessVars.LOGIN.name(), null);
          break;

        case SET_CURRENT_CCAA:
          short selectedCcaa = ServletUtils.getUpdatingSessViaReq(sess, req, SessVars.SELECTED_CCAA, Short::valueOf);
          sess.setAttribute(
            SessVars.PROVINCE_SELECT_VIEWS.name(),
            selectedCcaa == -1 ? null :
            entityManager.createNamedQuery("Provincia.findByCcaa", Provincia.class)
              .setParameter("ccaaId", selectedCcaa)
              .getResultList()
              .stream()
              .map(ProvinceSelectView::toSelectView)
              .collect(Collectors.toList())
          );
          sess.setAttribute(SessVars.TOWN_SELECT_VIEWS.name(), null);
          break;

        case SET_CURRENT_PROVINCE:
          short selectedProvince = ServletUtils.getUpdatingSessViaReq(sess, req, SessVars.SELECTED_PROVINCE, Short::valueOf);
          sess.setAttribute(
            SessVars.TOWN_SELECT_VIEWS.name(),
            selectedProvince == -1 ? null :
            entityManager.createNamedQuery("Municipio.findByProvincia", Municipio.class)
              .setParameter("provinciaId", selectedProvince)
              .getResultList()
              .stream()
              .map(TownSelectView::toSelectView)
              .collect(Collectors.toList())
          );
          break;

        case SET_CURRENT_TOWN:
          ServletUtils.updateSessViaReq(sess, req, SessVars.SELECTED_TOWN, Integer::valueOf);
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

package controller.servlet;

import controller.utils.JPAUtils;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.Municipio;
import model.entity.Playa;
import model.entity.ProvinceSelectView;
import model.entity.Provincia;
import model.entity.Punto;
import model.entity.TownSelectView;
import model.entity.Usuario;
import view.PageUtils;

@WebServlet("/RootPGR")
public class RootPGR extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public RootPGR() { super(); }

  public static enum Option {
    RESET,
    LOGIN,
    LOGOUT,
    SET_CURRENT_CCAA,
    SET_CURRENT_PROVINCE,
    SET_CURRENT_TOWN,
    SET_CURRENT_BEACH,
    TO_BEACHES,
    RATE,
  };

  public static String opt(Option option) {
    return String.format("RootPGR?OPTION=%s", option.name());
  }

  private static void setCalification(
    HttpSession sess,
    EntityManager entityManager,
    Integer id
  ) {
    ServletUtils.set(
      sess,
      SessVars.CALIFICATION,
      entityManager.createQuery(
        "SELECT AVG(p.puntos) FROM Punto p WHERE p.playa.id = :id",
        Double.class
      ).setParameter("id", id).getSingleResult()
    );
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, (sess, entityManager) -> {
      switch (Option.valueOf(req.getParameter(ReqVars.OPTION.name()))) {
        case RESET:
          ServletUtils.set(sess, SessVars.SELECTED_CCAA, null);
          ServletUtils.set(sess, SessVars.SELECTED_PROVINCE, null);
          ServletUtils.set(sess, SessVars.SELECTED_TOWN, null);
          ServletUtils.set(sess, SessVars.SELECTED_BEACH, null);
          ServletUtils.set(sess, SessVars.PROVINCE_SELECT_VIEWS, null);
          ServletUtils.set(sess, SessVars.TOWN_SELECT_VIEWS, null);
          ServletUtils.set(sess, SessVars.BEACHES, null);
          break;

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
            ServletUtils.set(
              sess,
              SessVars.LOGIN,
              entityManager
                .createNamedQuery("Usuario.findByNick", Usuario.class)
                .setParameter("nick", nick)
                .getResultList()
                .get(0)
            );
          } else {
            Usuario user = users.get(0);
            if (user.getPass().equals(password)) {
              ServletUtils.set(sess, SessVars.LOGIN, user);
            }
          }
          break;

        case LOGOUT:
          ServletUtils.set(sess, SessVars.LOGIN, null);
          break;

        case SET_CURRENT_CCAA:
          short selectedCcaa = ServletUtils.getUpdatingSessViaReq(sess, req, SessVars.SELECTED_CCAA, Short::valueOf);
          ServletUtils.set(
            sess,
            SessVars.PROVINCE_SELECT_VIEWS,
            selectedCcaa == -1 ? null :
            entityManager.createNamedQuery("Provincia.findByCcaa", Provincia.class)
              .setParameter("ccaaId", selectedCcaa)
              .getResultList()
              .stream()
              .map(ProvinceSelectView::toSelectView)
              .collect(Collectors.toList())
          );
          ServletUtils.set(sess, SessVars.SELECTED_PROVINCE, null);
          ServletUtils.set(sess, SessVars.SELECTED_TOWN, null);
          ServletUtils.set(sess, SessVars.TOWN_SELECT_VIEWS, null);
          ServletUtils.set(sess, SessVars.BEACHES, null);
          break;

        case SET_CURRENT_PROVINCE:
          short selectedProvince = ServletUtils.getUpdatingSessViaReq(sess, req, SessVars.SELECTED_PROVINCE, Short::valueOf);
          ServletUtils.set(
            sess,
            SessVars.TOWN_SELECT_VIEWS,
            selectedProvince == -1 ? null :
            entityManager.createNamedQuery("Municipio.findByProvincia", Municipio.class)
              .setParameter("provinciaId", selectedProvince)
              .getResultList()
              .stream()
              .map(TownSelectView::toSelectView)
              .collect(Collectors.toList())
          );
          ServletUtils.set(sess, SessVars.SELECTED_TOWN, null);
          ServletUtils.set(sess, SessVars.BEACHES, null);
          break;

        case SET_CURRENT_TOWN:
          int selectedMunicipio = ServletUtils.getUpdatingSessViaReq(sess, req, SessVars.SELECTED_TOWN, Integer::valueOf);
          ServletUtils.set(
            sess,
            SessVars.BEACHES,
            entityManager
              .createNamedQuery("Playa.findByMunicipio")
              .setParameter("municipioId", selectedMunicipio)
              .getResultList()
          );
          break;

        case SET_CURRENT_BEACH:
          Integer id = ServletUtils.getReqParam(req, ReqVars.BEACH_ID, Integer::valueOf);
          ServletUtils.set(
            sess,
            SessVars.SELECTED_BEACH,
            entityManager.find(Playa.class, id)
          );
          setCalification(sess, entityManager, id);
          break;

        case TO_BEACHES:
          ServletUtils.set(sess, SessVars.SELECTED_BEACH, null);
          break;

        case RATE:
          Punto point = new Punto();
          Playa beach = ServletUtils.getSess(sess, SessVars.SELECTED_BEACH);
          point.setPlaya(beach);
          point.setUsuario(ServletUtils.getSess(sess, SessVars.LOGIN));
          point.setPuntos(ServletUtils.getReqParam(req, ReqVars.CALIFIACTION, Short::valueOf));
          JPAUtils.add(entityManager, point);
          setCalification(sess, entityManager, beach.getId());
          break;
      }
      response.sendRedirect("/" + PageUtils.pageName);
      return null;
    });
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

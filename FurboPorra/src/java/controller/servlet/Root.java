package controller.servlet;

import controller.utils.ServletConfig;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Jornada;
import model.entity.RoundSelectView;

public class Root extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Root() { super(); }

  public static enum Status {
    OK,
    DUPLICATE_BET,
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, "/root.jsp", (sess, entityManager, dispatcher) -> {
      ServletUtils.setIfNull(sess, ServletConfig.SessVars.ROUND_SELECT_VIEWS, () -> {
        return entityManager
          .createNamedQuery("Jornada.findAll", Jornada.class)
          .getResultList()
          .stream()
          .map(RoundSelectView::toSelectView)
          .collect(Collectors.toList());
      });
      Jornada selectedRound;
      try { selectedRound = entityManager.find(Jornada.class, Short.valueOf(
          ServletUtils.getSess(sess, ServletConfig.SessVars.SELECTED_ROUND)
      )); } catch(NumberFormatException e) { selectedRound = null; }
      req.setAttribute(
        ReqVars.MATCH_LIST.name(),
        selectedRound == null ? Collections.emptyList() : selectedRound.getPartidoList()
      );
    });
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

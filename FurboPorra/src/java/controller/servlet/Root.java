package controller.servlet;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Jornada;
import model.entity.RoundSelectView;
import controller.servlet.ServletConfig.ReqVars;
import java.util.Collections;

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
    ServletUtils.servletTry(req, response, "/home.jsp", (sess, entityManager, dispatcher) -> {
      ServletUtils.setIfNull(sess, ServletConfig.SessVars.ROUND_SELECT_VIEWS, () -> {
        return entityManager
          .createNamedQuery("Jornada.findAll", Jornada.class)
          .getResultList()
          .stream()
          .map(RoundSelectView::toSelectView)
          .collect(Collectors.toList());
      });
      Jornada selectedRound = entityManager.find(Jornada.class, Short.valueOf(
        ServletUtils.updateIfNotNullAndGet(
          sess,
          req,
          ServletConfig.SessVars.SELECTED_ROUND,
          () -> { return "-1"; }
        )
      ));
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

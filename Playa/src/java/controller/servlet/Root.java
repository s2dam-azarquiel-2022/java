package controller.servlet;

import controller.utils.ServletConfig;
import controller.utils.ServletUtils;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Ccaa;
import model.entity.CcaaSelectView;

public class Root extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Root() { super(); }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, "/root.jsp", (sess, entityManager, dispatcher) -> {
      ServletUtils.setIfNull(sess, ServletConfig.SessVars.CCAA_SELECT_VIEWS, () -> {
        return entityManager.createNamedQuery("Ccaa.findAll", Ccaa.class)
          .getResultList()
          .stream()
          .map(CcaaSelectView::toSelectView)
          .collect(Collectors.toList());
      });
    });
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

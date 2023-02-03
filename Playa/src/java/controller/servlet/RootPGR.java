package controller.servlet;

import controller.utils.JPAUtils;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import view.PageUtils;

@WebServlet("/RootPGR")
public class RootPGR extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public RootPGR() { super(); }

  public static enum Option {
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

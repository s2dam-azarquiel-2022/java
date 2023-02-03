package controller.servlet;

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
import view.PageUtils;

@WebServlet("/RootPGR")
public class RootPGR extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public RootPGR() { super(); }

  private HttpSession sess;
  private HttpServletRequest req;
  private EntityManager entityManager;

  public static enum Option {
  };

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

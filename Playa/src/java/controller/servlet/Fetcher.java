package controller.servlet;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Calification;

@WebServlet("/Fetcher")
public class Fetcher extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Fetcher() { super(); }

  public static enum Option {
    CALIFICATIONS,
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, (sess, entityManager) -> {
      switch (Option.valueOf(req.getParameter(ReqVars.OPTION.name()))) {
        case CALIFICATIONS:
          ServletUtils.set(
            req,
            ReqVars.CALIFICATIONS,
            entityManager.createQuery(
              "SELECT NEW model.entity.Calification(p.puntos, COUNT(p)) FROM Punto p WHERE p.playa.id = :id GROUP BY p.puntos ORDER BY p.puntos ASC",
              Calification.class
            ).setParameter("id", ServletUtils.getReqParam(req, ReqVars.BEACH_ID, Integer::valueOf)).getResultList()
          );
          return "/califications.jsp";
      }

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

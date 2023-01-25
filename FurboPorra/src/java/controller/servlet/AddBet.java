/*
 * Copyright (C) 2023 aru
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller.servlet;

import controller.utils.ServletUtils;
import controller.utils.JPAUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import model.entity.Porra;
import model.entity.Usuario;
import view.PageUtils;

/**
 *
 * @author aru
 */
@WebServlet("/AddBet")
public class AddBet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public AddBet() { super(); };

  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, null, (sess, entityManager, dispatcher) -> {
      Porra bet = new Porra(
        ServletUtils.<Usuario>getSess(sess, SessVars.LOGIN).getDni(),
        Integer.valueOf(req.getParameter(ReqVars.SELECTED_MATCH.name()))
      );
      bet.setGoleslocal(Short.valueOf(req.getParameter(ReqVars.SCORE_LOCAL.name())));
      bet.setGolesvisitante(Short.valueOf(req.getParameter(ReqVars.SCORE_VISITANT.name())));
      try { JPAUtils.add(Porra.class, entityManager, bet); }
      catch (Exception e) { sess.setAttribute(SessVars.STATUS.name(), Root.Status.DUPLICATE_BET); }
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

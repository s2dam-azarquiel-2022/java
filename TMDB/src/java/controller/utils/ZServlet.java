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
package controller.utils;

import controller.utils.ServletConfig.ReqVars;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.utils.ZResponse;

/**
 *
 * @author aru
 */
public abstract class ZServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public abstract ZResponse run(
    ZServletData data
  );

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ZResponse res = null;
    try {
      ZServletData data = new ZServletData(req);
      res = run(data);
    } catch (Exception e) {
      req.setAttribute(ReqVars.ERR_TITLE.name(), e.toString());
      req.setAttribute(ReqVars.ERR_MESSAGE.name(), e.getMessage());
      res = new ZResponse("/err/500.jsp", ZResponse.Type.FORWARD);
      e.printStackTrace();
    } finally {
      if (res != null) switch (res.type) {
        case FORWARD:
          req.getRequestDispatcher(res.resource).forward(req, response);
          break;

        case REDIRECT:
          response.sendRedirect(res.resource);
          break;
      }
    }
  }

  @Override
  protected void doPost(
    HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}

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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.servlet.ServletConfig.ReqVars;
import controller.servlet.ServletConfig.SessVars;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import view.PageUtils;

/**
 *
 * @author aru
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public Login() { super(); };
  
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse response
  ) throws ServletException, IOException {
    ServletUtils.servletTry(req, response, null, (sess, entityManager, dispatcher) -> {
      String username = (String) req.getAttribute(ReqVars.USERNAME.name());
      String hashedPassword = new String(MessageDigest.getInstance("SHA-256").digest(req.getParameter(ReqVars.PASSWORD.name()).getBytes(StandardCharsets.UTF_8)));
      System.out.println(req.getParameter(ReqVars.PASSWORD.name()));
      System.out.println(hashedPassword);
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

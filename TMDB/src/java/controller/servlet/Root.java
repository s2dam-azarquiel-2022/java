package controller.servlet;

import controller.utils.ZServlet;
import controller.utils.ZServletData;
import model.utils.ZResponse;

public class Root extends ZServlet {
  @Override
  public ZResponse run(ZServletData data) {
    return new ZResponse("/root.jsp", ZResponse.Type.FORWARD);
  }
}

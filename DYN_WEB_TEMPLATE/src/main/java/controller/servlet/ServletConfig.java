package controller.servlet;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    CONNECTION,
  };

  public static enum ReqVars {
    // ...

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,
  };
}

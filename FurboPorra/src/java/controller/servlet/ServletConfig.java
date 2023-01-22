package controller.servlet;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    CONNECTION,
    ENTITY_MANAGER,
    LOGIN,
  };

  public static enum ReqVars {
    USERNAME,
    PASSWORD,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,
  };
}

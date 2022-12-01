package controller.servlet;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum sessionVars {
    CONNECTION,
  };

  public static enum requestVars {
    JOKES,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,
  };
}

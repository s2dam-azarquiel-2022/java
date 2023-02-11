package controller.utils;

import model.utils.Page;

public class ServletConfig {
  public static Page[] pages = {
    new Page("#", "Person"),
    new Page("#", "Movie"),
  };

  public static enum SessVars {
    // Entity manager
    ENTITY_MANAGER,
    LOGIN,
  };

  public static enum ReqVars {
    // Login
    DNI,
    NAME,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

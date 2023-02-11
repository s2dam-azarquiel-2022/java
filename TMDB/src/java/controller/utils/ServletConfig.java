package controller.utils;

import model.utils.Page;

public final class ServletConfig {
  public static final Page[] pages = {
    new Page("/person.jsp", "Person"),
    new Page("/movie.jsp", "Movie"),
  };

  public static enum SessVars {
    // Entity manager
    ENTITY_MANAGER,

    // Current page
    PAGE,

    // User loged in as
    LOGIN,
  };

  public static enum ReqVars {
    // Login
    DNI,
    NAME,

    // Page changing
    PAGE,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

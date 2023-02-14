package controller.utils;

import model.utils.Page;

public final class ServletConfig {
  public static final Page[] pages = {
    new Page("/entrada.jsp", "Entrada"),
    new Page("/habitaciones.jsp", "Habitaciones")
  };

  public static enum SessVars {
    // Entity manager
    ENTITY_MANAGER,

    // Current page
    PAGE,
  };

  public static enum ReqVars {
    // Page changing
    PAGE,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

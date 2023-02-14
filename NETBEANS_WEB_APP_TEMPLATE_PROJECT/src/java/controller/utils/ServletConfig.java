package controller.utils;

import model.utils.Page;

public final class ServletConfig {
  public static final Page[] pages = {
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

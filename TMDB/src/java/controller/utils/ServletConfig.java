package controller.utils;

import model.utils.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    // Entity manager
    ENTITY_MANAGER,
  };

  public static enum ReqVars {
    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

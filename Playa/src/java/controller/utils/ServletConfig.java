package controller.utils;

import model.utils.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    ENTITY_MANAGER,
    LOGIN,
    CCAA_SELECT_VIEWS,
    SELECTED_CCAA,
  };

  public static enum ReqVars {
    NICK,
    PASSWORD,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

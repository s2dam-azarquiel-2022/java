package controller.utils;

import model.utils.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    ENTITY_MANAGER,
    CCAA_SELECT_VIEWS,
    SELECTED_CCAA,
  };

  public static enum ReqVars {
    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

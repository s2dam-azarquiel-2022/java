package controller.utils;

import model.utils.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    CONNECTION,
    ENTITY_MANAGER,
    LOGIN,
    ROUND_SELECT_VIEWS,
    SELECTED_ROUND,

    STATUS,
  };

  public static enum ReqVars {
    DNI,
    USERNAME,
    MATCH_LIST,

    SELECTED_MATCH,

    SCORE_LOCAL,
    SCORE_VISITANT,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

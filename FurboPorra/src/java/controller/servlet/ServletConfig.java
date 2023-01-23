package controller.servlet;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    CONNECTION,
    ENTITY_MANAGER,
    LOGIN,
    ROUND_SELECT_VIEWS,
    SELECTED_ROUND,
  };

  public static enum ReqVars {
    DNI,
    USERNAME,
    MATCH_LIST,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,
  };
}

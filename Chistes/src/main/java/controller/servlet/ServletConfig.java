package controller.servlet;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    CONNECTION,
    CATEGORIES,
    SELECTED_CATEGORY,
  };

  public static enum ReqVars {
    JOKES,
    ADDED_JOKE,

    // Modals
    NICKNAME,
    CATEGORY,
    TITLE,
    DESCRIPTION,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,
  };
}

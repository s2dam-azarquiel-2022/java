package controller.utils;

import model.utils.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    ENTITY_MANAGER,
    LOGIN,
    CCAA_SELECT_VIEWS,
    PROVINCE_SELECT_VIEWS,
    TOWN_SELECT_VIEWS,
    SELECTED_CCAA,
    SELECTED_PROVINCE,
    SELECTED_TOWN,
    SELECTED_BEACH,
    CALIFICATION,
    BEACHES,
  };

  public static enum ReqVars {
    NICK,
    PASSWORD,

    CALIFICATIONS,
    CALIFIACTION,
    BEACH_ID,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

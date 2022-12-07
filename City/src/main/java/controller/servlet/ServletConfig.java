package controller.servlet;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
  };

  public static enum SessVars {
    CONNECTION,
    ROOT_OPTION,

    CITY_SELECT_OPTIONS,
  };

  public static enum ReqVars {
    CITIES,
    ROUTES,

    CITY_ID,

    ROUTE_ID,
    STARS,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,
  };
}

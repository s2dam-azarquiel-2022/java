package controller.utils;

import model.utils.Page;

public final class ServletConfig {
  public static final Page[] pages = {
    new Page("/entrada.jsp", "Entrada"),
    new Page("/habitaciones.jsp", "Habitaciones")
  };

  public static enum SessVars {
    HABITACIONES,

    // Entity manager
    ENTITY_MANAGER,

    // Current page
    PAGE,
  };

  public static enum ReqVars {
    DNI,
    HABITACION,
    FECHA_SALIDA,
    FECHA_ENTRADA,

    N_HABITACION,

    RESERVAS,

    // Page changing
    PAGE,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

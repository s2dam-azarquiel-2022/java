package controller.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.entity.Tipo;
import model.utils.Page;

public final class ServletConfig {
  public static final List<Page> pages =
    Stream.concat(
      Stream.of(new Page("Todos", Short.MIN_VALUE)),
      JPAUtils.getEntityManagerFactory().createEntityManager()
        .createNamedQuery("Tipo.findAll", Tipo.class)
        .getResultList()
        .stream()
        .map(Tipo::toPage)
    ).collect(Collectors.toList());

  public static enum SessVars {
    LOGIN,

    COCHES,

    // Entity manager
    ENTITY_MANAGER,

    // Current page
    PAGE,
  };

  public static enum ReqVars {
    FILTER,

    NICK,
    PASSWORD,

    MODEL_ID,

    ACABADOS,

    // Page changing
    PAGE,

    // Errors
    ERR_TITLE,
    ERR_MESSAGE,

    // PGR
    OPTION,
  };
}

package controller.servlet;

import controller.utils.ServletConfig;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ZServlet;
import controller.utils.ZServletData;
import java.util.stream.Collectors;
import model.entity.Person;
import model.entity.PersonView;
import model.entity.Rating;
import model.entity.Usuario;
import model.utils.ZResponse;
import view.PageUtils;

public final class Root extends ZServlet {
  public static enum Option {
    LOGIN,
    LOGOUT,
    CHANGE_PAGE,
    RATE,

    FETCH_MOVIES,

    RESET,
  };

  public static String opt(Root.Option option) {
    return String.format(OPT_STR, option.name());
  }

  private static final String nullRedirect = String.format(
    "%s%s&%s=%d",
    PageUtils.path,
    opt(Option.CHANGE_PAGE),
    ReqVars.PAGE.name(),
    0
  );

  @Override
  public final ZResponse run(ZServletData data) throws Exception {
    String opt = data.getParam(ReqVars.OPTION);
    data.setIfNull(SessVars.PERSONS, () -> {
      return data.em
        .createNamedQuery("Person.findAll", Person.class)
        .getResultList()
        .stream()
        .map(person -> {
          return new PersonView(
            person.getId(),
            person.getNombre(),
            person.getFoto(),
            (int) Math.round(
              data.em.createNamedQuery("Person.avgPoints", Number.class)
                .setParameter("id", person.getId())
                .getSingleResult()
                .doubleValue()
            )
          );
        })
        .collect(Collectors.toList());
    });
    data.setIfNull(SessVars.MOVIES, () -> {
      return data.em.createNamedQuery("Movie.findAll").getResultList();
    });
    if (opt == null) {
      String page = data.getAttr(SessVars.PAGE);
      return page == null
        ? new ZResponse(nullRedirect, ZResponse.Type.REDIRECT)
        : new ZResponse(page, ZResponse.Type.FORWARD);
    }
    switch (Option.valueOf(opt)) {
      case LOGIN:
        String dni = data.getParam(ReqVars.DNI);
        data.set(SessVars.LOGIN, data.getSettingIfNull(dni, Usuario.class, () -> {
          Usuario login = new Usuario(dni);
          login.setNombre(data.getParam(ReqVars.NAME));
          return login;
        }));
        break;

      case LOGOUT:
        data.set(SessVars.LOGIN, null);
        break;

      case CHANGE_PAGE:
        data.updateSessViaReq(SessVars.PAGE, s -> {
          try {
            int id = Integer.valueOf(s);
            if (id < ServletConfig.pages.length) {
              return ServletConfig.pages[id].file;
            }
          } catch (NumberFormatException e) { }
          return "/err/500.jsp";
        });
        break;

      case RATE:
        Rating rating = new Rating();
        rating.setDni(data.getAttr(SessVars.LOGIN));
        rating.setIdperson(data.getParam(ReqVars.PERSON_ID, id -> new Person(Integer.valueOf(id))));
        rating.setPuntos(data.getParam(ReqVars.RATE, Short::valueOf));
        data.add(rating);
        break;

      case FETCH_MOVIES:
        data.set(
          ReqVars.MOVIES,
          data.em
            .find(Person.class, data.getParam(ReqVars.PERSON_ID, Integer::valueOf))
            .getMovieList()
        );
        return new ZResponse("/utils/fetch/movies.jsp", ZResponse.Type.FORWARD);

      case RESET:
        break;
    }
    return new ZResponse("/" + PageUtils.pageName, ZResponse.Type.REDIRECT);
  }
}

package controller.servlet;

import controller.utils.ServletConfig;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ZServlet;
import controller.utils.ZServletData;
import model.entity.Habitacion;
import model.utils.ZResponse;
import view.PageUtils;

public final class Root extends ZServlet {
  public static enum Option {
    HOSPEDAR,
    FETCH_RESERVAS,
    CHANGE_PAGE,
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
    data.setIfNull(SessVars.HABITACIONES, () -> {
      return data.em.createNamedQuery("Habitacion.findAll").getResultList();
    });
    if (opt == null) {
      String page = data.getAttr(SessVars.PAGE);
      return page == null
        ? new ZResponse(nullRedirect, ZResponse.Type.REDIRECT)
        : new ZResponse(page, ZResponse.Type.FORWARD);
    }
    switch (Option.valueOf(opt)) {
      case HOSPEDAR:
        String dni = data.getParam(ReqVars.DNI);
        String dateS = data.getParam(ReqVars.FECHA_ENTRADA);
        String dateE = data.getParam(ReqVars.FECHA_SALIDA);
        String room = data.getParam(ReqVars.HABITACION);
        break;

      case FETCH_RESERVAS:
        data.set(
          ReqVars.RESERVAS,
          data.em.find(
            Habitacion.class,
            data.getParam(ReqVars.N_HABITACION)
          ).getReservaList()
        );
        return new ZResponse("/utils/fetch/reservas.jsp", ZResponse.Type.FORWARD);

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

      case RESET:
        break;
    }
    return new ZResponse("/" + PageUtils.pageName, ZResponse.Type.REDIRECT);
  }
}

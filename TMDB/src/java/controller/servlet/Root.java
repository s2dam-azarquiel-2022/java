package controller.servlet;

import controller.utils.ServletConfig;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ZServlet;
import controller.utils.ZServletData;
import model.entity.Usuario;
import model.utils.ZResponse;
import view.PageUtils;

public final class Root extends ZServlet {
  public static enum Option {
    LOGIN,
    LOGOUT,
    CHANGE_PAGE,
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
    }
    return new ZResponse("/" + PageUtils.pageName, ZResponse.Type.REDIRECT);
  }
}

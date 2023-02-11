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

  @Override
  public final ZResponse run(ZServletData data) throws Exception {
    String opt = data.getParam(ReqVars.OPTION);
    if (opt == null) {
      String page = data.getAttr(SessVars.PAGE);
      return new ZResponse(page == null ? "/person.jsp" : page, ZResponse.Type.FORWARD);
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
          String page = "/err/500.jsp";
          try {
            int id = Integer.valueOf(s);
            if (id < ServletConfig.pages.length) {
              page = ServletConfig.pages[id].file;
            }
          } catch (NumberFormatException e) {
          } finally {
            return page;
          }
        });
        break;
    }
    return new ZResponse("/" + PageUtils.pageName, ZResponse.Type.REDIRECT);
  }
}

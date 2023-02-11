package controller.servlet;

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
  };

  public static String opt(Root.Option option) {
    return String.format(OPT_STR, option.name());
  }

  @Override
  public final ZResponse run(ZServletData data) throws Exception {
    String opt = data.getParam(ReqVars.OPTION);
    if (opt == null) {
      return new ZResponse("/root.jsp", ZResponse.Type.FORWARD);
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
    }
    return new ZResponse("/" + PageUtils.pageName, ZResponse.Type.REDIRECT);
  }
}

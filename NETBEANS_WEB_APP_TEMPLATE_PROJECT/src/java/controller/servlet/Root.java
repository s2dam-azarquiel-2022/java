package controller.servlet;

import controller.utils.ServletConfig;
import controller.utils.ServletConfig.ReqVars;
import controller.utils.ZServlet;
import static controller.utils.ZServlet.OPT_STR;
import controller.utils.ZServletData;
import model.utils.ZResponse;
import view.PageUtils;

public final class Root extends ZServlet {
  public static enum Option {
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
    if (opt == null) {
      String page = data.getAttr(ServletConfig.SessVars.PAGE);
      return page == null
        ? new ZResponse(nullRedirect, ZResponse.Type.REDIRECT)
        : new ZResponse(page, ZResponse.Type.FORWARD);
    }
    switch (Option.valueOf(opt)) {
      case CHANGE_PAGE:
        data.updateSessViaReq(ServletConfig.SessVars.PAGE, s -> {
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

package controller.servlet;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;
import controller.utils.ZServlet;
import controller.utils.ZServletData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.entity.Cita;
import model.entity.Modelo;
import model.entity.Usuario;
import model.utils.ZResponse;
import view.PageUtils;

public final class Root extends ZServlet {
  public static enum Option {
    LOGIN,
    LOGOUT,
    FETCH_ACABADOS,
    PEDIR_CITA,
    FILTER,
    RESET,
  };

  public static String opt(Root.Option option) {
    return String.format(OPT_STR, option.name());
  }

  @Override
  public final ZResponse run(ZServletData data) throws Exception {
    String opt = data.getParam(ReqVars.OPTION);
    if (opt == null) {
      data.setIfNull(SessVars.COCHES, () -> {
        return data.em
          .createNamedQuery("Modelo.findAll", Modelo.class)
          .getResultList();
      });
      return new ZResponse("root.jsp", ZResponse.Type.FORWARD);
    }
    switch (Option.valueOf(opt)) {
      case LOGIN:
        String nick = data.getParam(ReqVars.NICK);
        String password = data.getParam(ReqVars.PASSWORD);
        List<Usuario> users = data.em
          .createNamedQuery("Usuario.findByNick", Usuario.class)
          .setParameter("nick", nick)
          .getResultList();
        if (users.isEmpty()) {
          Usuario user = new Usuario();
          user.setNick(nick);
          user.setPass(password);
          data.add(user);
          data.set(
            SessVars.LOGIN,
            data.em
              .createNamedQuery("Usuario.findByNick", Usuario.class)
              .setParameter("nick", nick)
              .getSingleResult()
          );
        } else {
          Usuario user = users.get(0);
          if (user.getPass().equals(password)) {
            data.set(SessVars.LOGIN, user);
          }
        }
        break;

      case LOGOUT:
        data.set(SessVars.LOGIN, null);
        break;

      case FILTER:
        Short id = data.getParam(ReqVars.FILTER, Short::valueOf);
        data.set(
          SessVars.COCHES,
          id == Short.MIN_VALUE
            ? null
            : data.em
              .createNamedQuery("Modelo.findById", Modelo.class)
              .setParameter("id", id)
              .getResultList()
        );
        break;

      case FETCH_ACABADOS:
        data.set(
          ReqVars.ACABADOS,
          data.em
            .find(Modelo.class, data.getParam(ReqVars.MODEL_ID, Short::valueOf))
            .getModeloacabadoList()
        );
        return new ZResponse("/utils/fetch/acabados.jsp", ZResponse.Type.FORWARD);

      case PEDIR_CITA:
        Cita cita = new Cita();
        cita.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        cita.setModelo(new Modelo(data.getParam(ReqVars.MODEL_ID, Short::valueOf)));
        cita.setUsuario(data.getAttr(SessVars.LOGIN));
        data.add(cita);
        break;

      case RESET:
        data.set(SessVars.COCHES, null);
        break;
    }
    return new ZResponse("/" + PageUtils.pageName, ZResponse.Type.REDIRECT);
  }
}

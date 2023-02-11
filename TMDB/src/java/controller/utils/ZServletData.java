/*
 * Copyright (C) 2023 aru
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller.utils;

import controller.utils.ServletConfig.SessVars;
import java.util.concurrent.Callable;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author aru
 */
public final class ZServletData {
  private final HttpServletRequest req;
  private final HttpSession sess;
  private final EntityManager em;

  public ZServletData(
    HttpServletRequest req
  ) throws Exception {
    this.req = req;
    this.sess = req.getSession();
    em = getSettingIfNull(SessVars.ENTITY_MANAGER, () -> {
        return JPAUtils.getEntityManagerFactory().createEntityManager();
    });
  }

  public <T> T getAttr(
    SessVars var
  ) { return (T) sess.getAttribute(var.name()); }

  public <T> T getAttr(
    ServletConfig.ReqVars var
  ) { return (T) req.getAttribute(var.name()); }

  public <T> T getParam(
    ServletConfig.ReqVars var,
    Function<String, T> f
  ) { return f.apply(req.getParameter(var.name())); }

  public String getParam(
    ServletConfig.ReqVars var
  ) { return req.getParameter(var.name()); }

  public <T> void set(
    SessVars var,
    T val
  ) { sess.setAttribute(var.name(), val); }

  public <T> void set(
    ServletConfig.ReqVars var,
    T val
  ) { req.setAttribute(var.name(), val); }

  public <T> T getSettingIfNull(
    ServletConfig.SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    T val = (T) sess.getAttribute(var.name());
    if (val == null) {
      val = fDefaultVal.call();
      sess.setAttribute(var.name(), val);
    }
    return val;
  }

  public <T> void setIfNull(
    SessVars var,
    Callable<T> fDefaultVal
  ) throws Exception {
    if (sess.getAttribute(var.name()) == null) {
      sess.setAttribute(var.name(), fDefaultVal.call());
    }
  }

  public <T> T getUpdatingSessViaReq(
    SessVars var,
    Function<String, T> f
  ) {
    T val = f.apply(req.getParameter(var.name()));
    sess.setAttribute(var.name(), val);
    return val;
  }

  public <T> void updateSessViaReq(
    SessVars var,
    Function<String, T> f
  ) { sess.setAttribute(var.name(), f.apply(req.getParameter(var.name()))); }

  public <T> T getSettingIfNull(
    Object pk,
    Class<T> c,
    Callable<T> fDefaultVal
  ) throws Exception {
    T val = em.find(c, pk);
    if (val == null) {
      val = fDefaultVal.call();
      add(val);
    }
    return val;
  }

  public <T> void add(
    T val
  ) throws DatabaseException, RollbackException {
    EntityTransaction t = em.getTransaction();
    t.begin();
    em.persist(val);
    t.commit();
  }
}

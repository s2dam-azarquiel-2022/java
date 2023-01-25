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

import java.util.concurrent.Callable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import org.eclipse.persistence.exceptions.DatabaseException;
import view.PageUtils;

/**
 *
 * @author aru
 */
public class JPAUtils {
  private static final EntityManagerFactory entityManager;

  static {
    try {
      entityManager = Persistence.createEntityManagerFactory(PageUtils.pageName + "PU");
    } catch (Throwable e) {
      System.err.println("Error while creating SessionFactory");
      e.printStackTrace();
      throw new ExceptionInInitializerError(e);
    }
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return entityManager;
  }

  public static <T> T getCheckingNull(
    Object pk,
    Class<T> c,
    EntityManager entityManager,
    Callable<T> fDefaultVal
  ) throws Exception {
    T val = entityManager.find(c, pk);
    if (val == null) {
      val = fDefaultVal.call();
      add(c, entityManager, val);
    }
    return val;
  }

  public static <T> void add(
    Class<T> c,
    EntityManager entityManager,
    T val
  ) throws DatabaseException, RollbackException {
    EntityTransaction t = entityManager.getTransaction();
    t.begin();
    entityManager.persist(val);
    t.commit();
  }
}

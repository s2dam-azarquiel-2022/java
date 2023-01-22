package model.utils;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface ServletTryFunction {
  void run(
    HttpSession sess,
    EntityManager entityManager,
    RequestDispatcher dispatcher
  ) throws Exception;
}

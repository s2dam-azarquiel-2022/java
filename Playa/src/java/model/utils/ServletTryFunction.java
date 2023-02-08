package model.utils;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface ServletTryFunction {
  String run(
    HttpSession sess,
    EntityManager entityManager
  ) throws Exception;
}

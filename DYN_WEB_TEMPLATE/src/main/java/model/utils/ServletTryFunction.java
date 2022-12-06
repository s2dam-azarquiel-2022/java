package model.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface ServletTryFunction {
  void run(
    HttpSession sess,
    RequestDispatcher dispatcher
  ) throws Exception;
}

package view;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;

public final class PageUtils {
  public static final String pageName = "Hotel";
  public static final String path = String.format("/%s/", pageName);

  public static final String mainDivSetup = mainDivSetup("container-fluid");

  public static String mainDivSetup(String container) {
    return String.format(
      "class='%s flex-grow-1 bg-dark text-white p-4' " +
      "style='--bs-bg-opacity: 0'",
      container
    );
  };

  public static final String mainBodySetup =
    "class='min-vh-100 d-flex flex-column bg-dark' " +
    "style='--bs-bg-opacity: .90'"
  ;

  public static final String mainNavSetup =
    "class='navbar navbar-dark bg-dark text-white navbar-expand-lg sticky-top'"
  ;

  public static final String mainNavbarSetup =
    "class='collapse navbar-collapse' id='navbarToggler'"
  ;

  public static final String mainNavbarTogglerButtonSetup =
    "class='navbar-toggler ms-auto' " +
    "type='button' " +
    "data-bs-toggle='collapse' " +
    "data-bs-target='#navbarToggler' " +
    "aria-controls='navbarToggler' " +
    "aria-expanded='false' " +
    "aria-label='Toggle navigation'"
  ;

  public static String mainFormSetup(String action, String clazz) {
    return String.format(
      "action='%s%s' method='post' class='%s'",
      PageUtils.path,
      action,
      clazz
    );
  }

  public static String mainFormSetup(String action) {
    return mainFormSetup(action, "d-grid gap-3");
  }

  private static String mainSelectSetup(String s) {
    return String.format(
      "name='%s' %s",
      s,
      mainFormControlSetup
    );
  }

  public static String mainSelecSetup(ReqVars var) {
    return mainSelectSetup(var.name());
  }

  public static String mainSelectSetup(SessVars var) {
    return mainSelectSetup(var.name());
  }

  private static String mainSelectOnlySetup(String s) {
    return mainSelectSetup(s) + " onchange='this.form.submit()'";
  }

  public static String mainSelectOnlySetup(ReqVars var) {
    return mainSelectOnlySetup(var.name());
  }

  public static String mainSelectOnlySetup(SessVars var) {
    return mainSelectOnlySetup(var.name());
  }

  public static final String mainModalContentSetup =
    "class='modal-content bg-dark text-white'"
  ;

  public static final String mainFormControlSetup =
    "class='form-control bg-dark text-white'"
  ;

  public static String mainInputSetup(ReqVars var) {
    return String.format(
      "name='%s' type='text' %s",
      var.name(),
      mainFormControlSetup
    );
  }

  public static String mainInputSetup(ReqVars var, String type) {
    return String.format(
      "name='%s' type='%s' %s",
      var.name(),
      type,
      mainFormControlSetup
    );
  }

  private static String hiddenInputSetup(String var, String val) {
    return String.format(
      "name='%s' value='%s' class='d-none'",
      var,
      val
    );
  }

  public static String hiddenInputSetup(SessVars var, String val) {
    return hiddenInputSetup(var.name(), val);
  }

  public static String hiddenInputSetup(ReqVars var, String val) {
    return hiddenInputSetup(var.name(), val);
  }


  public static String mainLabelSetup(ReqVars var) {
    return String.format(
      "for='%s' class='form-label'",
      var.name()
    );
  }

  public static final String mainModalCloseBtnSetup =
    "class='btn btn-secondary' " +
    "type='button' " +
    "data-bs-dismiss='modal'"
  ;

  public static final String mainModalCloseBtnXSetup =
    "type='button' " +
    "class='btn-close btn-close-white' " +
    "aria-label='Close' " +
    "data-bs-dismiss='modal'"
  ;

  public static String mainModalSetup(String modalName, String clazz) {
    return String.format(
      "class='modal fade %s' " +
      "tabindex='-1' " +
      "aria-hidden='true' " +
      "id='%s' " +
      "aria-labelledby='%s'"
    , clazz, modalName, modalName);
  }

  public static String mainModalSetup(String modalName) {
    return mainModalSetup(modalName, "");
  }

  public static String mainModalButtonSetup(String modalName) {
    return String.format(
      "type='button' " +
      "class='btn btn-primary' " +
      "data-bs-toggle='modal' " +
      "data-bs-target='#%s'"
    , modalName);
  }

  public static String mainCardSetup =
    "class='card flex-fill text-white bg-dark'"
  ;

  public static String mainLinkButton(String href, String clazz) {
    return String.format(
      "href='%s%s' role='button' class='btn %s'",
      PageUtils.path,
      href,
      clazz
    );
  }

  public static String mainLinkButton(String href) {
    return mainLinkButton(href, "btn-success");
  }

  public static String mainSubmitButton =
    "class='btn btn-success' type='submit'"
  ;

  public static String mainSubmitButton(String clazz) {
    return String.format(
      "class='btn %s' type='submit'",
      clazz
    );
  }
}

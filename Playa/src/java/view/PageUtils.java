package view;

import controller.utils.ServletConfig.ReqVars;
import controller.utils.ServletConfig.SessVars;

public class PageUtils {
  public static final String pageName = "Playa";

  public static final String mainDivSetup =
   "class='container-fluid flex-grow-1 bg-dark text-white p-4' " +
   "style='--bs-bg-opacity: .9'"
  ;

  public static final String mainBodySetup =
    "class='min-vh-100 d-flex flex-column' "
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

  public static String mainFormSetup(String action) {
    return String.format(
      "action='/%s/%s' method='post' class='d-grid gap-3'",
      PageUtils.pageName,
      action
    );
  }

  private static String mainSelectOnlySetup(String s) {
    return String.format(
      "name='%s' onchange='this.form.submit()' %s",
      s,
      mainFormControlSetup
    );
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

  public static String mainModalSetup(String modalName) {
    return String.format(
      "class='modal fade' " +
      "tabindex='-1' " +
      "aria-hidden='true' " +
      "id='%s' " +
      "aria-labelledby='%s'"
    , modalName, modalName);
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
    "class='card flex-fill text-bg-dark'"
  ;
}

package view;

public class PageUtils {
  public static final String pageName = "City";

  public static final String mainDivSetup = """
   class="container-fluid flex-grow-1 bg-dark text-white p-4"
   style="--bs-bg-opacity: .9"
  """;

  public static final String mainBodySetup = """
    class="min-vh-100 d-flex flex-column"
  """;

  public static final String mainNavSetup = """
    class="navbar navbar-dark bg-dark text-white navbar-expand-lg sticky-top"
  """;

  public static final String mainNavbarSetup = """
    class="collapse navbar-collapse" id="navbarToggler"
  """;

  public static final String mainNavbarTogglerButtonSetup = """
    class="navbar-toggler ms-auto"
    type="button"
    data-bs-toggle="collapse"
    data-bs-target="#navbarToggler"
    aria-controls="navbarToggler"
    aria-expanded="false"
    aria-label="Toggle navigation"
  """;

  public static final String mainFormControlSetup = """
    class="form-control bg-dark text-white"
  """;

  public static final String mainModalCloseBtnSetup = """
    class="btn btn-secondary"
    type="button"
    data-bs-dismiss="modal"
  """;

  public static String mainModalSetup(String modalName) {
    return String.format("""
      class="modal fade"
      tabindex="-1"
      aria-hidden="true"
      id="%s"
      aria-labelledby="%s"
    """, modalName, modalName);
  }

  public static String mainModalButtonSetup(String modalName) {
    return String.format("""
      type="button"
      class="btn btn-primary"
      data-bs-toggle="modal"
      data-bs-target="#%s"
    """, modalName);
  }
}

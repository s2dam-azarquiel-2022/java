package view;

public class PageUtilsDark {
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
}

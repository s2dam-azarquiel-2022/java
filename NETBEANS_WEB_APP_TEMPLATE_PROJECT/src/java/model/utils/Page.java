package model.utils;

public final class Page {
  public final String href;
  public final String displayName;

  public Page(String name, String displayName) {
    this.href = name;
    this.displayName = displayName;
  }
}

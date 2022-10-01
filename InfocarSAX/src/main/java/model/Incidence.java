package model;

public class Incidence {
  public String title = "";
  public String link = "";
  public String pubDate = "";
  public String description = "";

  public Incidence() {
  }

  public Incidence(
    String title,
    String link,
    String pubDate,
    String description
  ) {
    this.title = title;
    this.link = link;
    this.pubDate = pubDate;
    this.description = description;
  }

  @Override
  public String toString() {
    return
      String.format(
        "Title: %s\n" +
        "Link: %s\n" +
        "Publication date: %s\n" +
        "Description: %s\n",
        title,
        link,
        pubDate,
        description
      );
  }
}

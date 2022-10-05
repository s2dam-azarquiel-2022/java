package model;

public class Incidence {
  public String title;
  public String link;
  public String pubDate;
  public String description;
  public String image;

  public Incidence() {
  }

  @Override
  public String toString() {
    return
      String.format(
        "Title: %s\n" +
        "Link: %s\n" +
        "Publication date: %s\n" +
        "Description: %s\n",
        "Image: %s\n",
        title,
        link,
        pubDate,
        description,
        image
      );
  }
}

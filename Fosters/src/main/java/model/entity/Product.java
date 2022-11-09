package model.entity;

public class Product {
  public int id;
  public int category;
  public String body;
  public String title;
  public String summary;
  public String background;
  public String image;

  public Product(
    int id,
    int category,
    String body,
    String title,
    String summary,
    String background,
    String image
  ) {
    this.id = id;
    this.category = category;
    this.body = body;
    this.title = title;
    this.summary = summary;
    this.background = background;
    this.image = image;
  }
}

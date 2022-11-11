package model.entity;

public class Product {
  public int id;
  public String title;
  public String image;
  public String body;
  public int stars;
  public int reviews;

  public Product(
    int id,
    String title,
    String image,
    String body,
    int stars,
    int reviews
  ) {
    super();
    this.id = id;
    this.title = title;
    this.image = image;
    this.body = body;
    this.stars = stars;
    this.reviews = reviews;
  }
}

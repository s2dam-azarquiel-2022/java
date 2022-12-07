package model.entity;

public class Route {
  public String link;
  public String description;
  public String image;
  public String name;
  public String city;
  public int id;
  public Reviews reviews;

  public Route(
    String link, String description, String image, String name, String city,
    int id, Reviews reviews
  ) {
    this.link = link;
    this.description = description;
    this.image = image;
    this.name = name;
    this.city = city;
    this.id = id;
    this.reviews = reviews;
  }
}

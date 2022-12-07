package model.entity;

public class City {
  public String map;
  public String link;
  public String description;
  public String image;
  public String name;
  public int id;

  public City(
    String map, String link, String description, String image, String name,
    int id
  ) {
    super();
    this.map = map;
    this.link = link;
    this.description = description;
    this.image = image;
    this.name = name;
    this.id = id;
  }
}

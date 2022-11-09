package model.entity;

public class Category {
  public int id;
  public String garrison;
  public String description;
  public String name;

  public Category(
    int id,
    String garrison,
    String description,
    String name
  ) {
    this.id = id;
    this.garrison = garrison;
    this.description = description;
    this.name = name;
  }
}

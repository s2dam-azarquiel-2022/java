package model.entity;

public class Joke {
  public int id;
  public int categoryID;
  public String title;
  public String description;
  public String nickname;
  public int stars;
  public int reviews;

  public Joke(
    int id, int categoryID, String title, String description, String nickname,
    int stars, int reviews
  ) {
    super();
    this.id = id;
    this.categoryID = categoryID;
    this.title = title;
    this.description = description;
    this.nickname = nickname;
    this.stars = stars;
    this.reviews = reviews;
  }
}

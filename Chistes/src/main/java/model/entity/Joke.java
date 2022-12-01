package model.entity;

public class Joke {
  public int id;
  public int categoryID;
  public String title;
  public String desc;
  public String nickname;

  public Joke(
    int id, int categoryID, String title, String desc, String nickname
  ) {
    this.id = id;
    this.categoryID = categoryID;
    this.title = title;
    this.desc = desc;
    this.nickname = nickname;
  }
}

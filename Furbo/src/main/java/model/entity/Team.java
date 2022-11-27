package model.entity;

public class Team {
  public int id;
  public String name;
  public String president;
  public String trainer;
  public int creationYear;
  public String stadium;
  public String web;
  public int points;

  public Team(
    int id,
    String name,
    String president,
    String trainer,
    int creationYear,
    String stadium,
    String web,
    int puntos
  ) {
    this.id = id;
    this.name = name;
    this.president = president;
    this.trainer = trainer;
    this.creationYear = creationYear;
    this.stadium = stadium;
    this.web = web;
    this.points = puntos;
  }
}

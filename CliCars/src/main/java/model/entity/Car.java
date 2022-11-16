package model.entity;

public class Car {
  public String name;
  public String model;
  public int brand;
  public int id;
  public int priceNow;
  public int priceBefore;
  public int year;
  public int km;
  public int cv;
  public boolean favorite;
  public String photo;

  public Car(
    String name,
    String model,
    int brand,
    int id,
    int priceNow,
    int priceBefore,
    int year,
    int km,
    int cv,
    boolean favorite,
    String photo
  ) {
    this.name = name;
    this.model = model;
    this.brand = brand;
    this.id = id;
    this.priceNow = priceNow;
    this.priceBefore = priceBefore;
    this.year = year;
    this.km = km;
    this.cv = cv;
    this.favorite = favorite;
    this.photo = photo;
  }
}
